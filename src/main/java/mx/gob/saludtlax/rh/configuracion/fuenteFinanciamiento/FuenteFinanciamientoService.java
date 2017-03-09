package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.SubfuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.persistencia.SubfuenteFinanciamientoEntity;

@Stateless
public class FuenteFinanciamientoService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
	@Inject
	private FuenteFinanciamientoOPDRepository fuenteFinanciamientoOPDDAO;
	@Inject
	private SubfuenteFinanciamientoRepository subfuenteFinanciamientoDAO;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;

	// <Comienzan las Operaciones para Fuente de Financiamiento>

	public List<FuenteFinanciamientoDTO> listaFuenteFinanciamiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				" SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, " + " id_base_36 AS idBase36, "
						+ " descripcion AS descripcion " + " FROM fuentes_financiamientos " + " ");
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<FuenteFinanciamientoDTO> result = (List<FuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public void crearFuenteFinanciamiento(FuenteFinanciamientoDTO dto) {
		if (ValidacionUtil.esCadenaVacia(dto.getDescripcion())) {
			throw new ValidacionException("La descripción de la fuente de financiamiento es requerida.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
		if (ValidacionUtil.esCadenaVacia(dto.getIdBase36())) {
			throw new ValidacionException("El id base 36 es requerido.", ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		FuenteFinanciamientoEntity entity = new FuenteFinanciamientoEntity();
		entity.setIdBase36(dto.getIdBase36());
		entity.setDescripcion(dto.getDescripcion());
		fuenteFinanciamientoRepository.crear(entity);

	}

	public FuenteFinanciamientoDTO obtenerFuenteFinanciamientoPorId(Integer idFuenteFinanciamiento) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_base_36 AS idBase36, " + "descripcion AS descripcion "
						+ "FROM fuentes_financiamientos WHERE id_fuente_financiamiento = :idFuenteFinanciamiento")
				.setParameter("idFuenteFinanciamiento", idFuenteFinanciamiento);
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
		FuenteFinanciamientoDTO result = (FuenteFinanciamientoDTO) query.list().get(0);
		return result;
	}

	public void eliminarFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
		if (!ValidacionUtil.esNumeroPositivo(idFuenteFinanciamiento)) {
			throw new ValidacionException("La fuente de financiamiento que desea eliminar es requerida.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		FuenteFinanciamientoEntity entity = entityManager.find(FuenteFinanciamientoEntity.class,
				idFuenteFinanciamiento);

		if (entity == null) {
			throw new ValidacionException(
					"No se encontró fuente de financiamiento con identificador" + idFuenteFinanciamiento,
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
		Integer idConfiguracion = configuracionPresupuestoRepository
				.existeConfiguracionConfuente(idFuenteFinanciamiento);

		if (ValidacionUtil.esNumeroPositivo(idConfiguracion)) {
			throw new ReglaNegocioException("Existen empleados con la fuente de financiamiento "
					+ entity.getDescripcion() + ", no puede ser eliminada.",
					ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
		}

		entityManager.remove(entity);
	}

	// <Comienzan las operqaciones para Fuente de Financiamiento OPD>
	public List<FuenteFinanciamientoOPDDTO> listaFuenteFinanciamientoOPD() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT " + " id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
				+ " id_fuente_financiamiento AS idFuenteFinanciamiento, " + " descripcion "
				+ " FROM fuentes_financiamientos_opd ");
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoOPDDTO.class));
		@SuppressWarnings("unchecked")
		List<FuenteFinanciamientoOPDDTO> result = (List<FuenteFinanciamientoOPDDTO>) query.list();
		return result;
	}

	public FuenteFinanciamientoOPDDTO nuevoFuenteFinanciamientoOPD() {
		FuenteFinanciamientoOPDDTO dto = new FuenteFinanciamientoOPDDTO();
		dto.setDescripcion(null);
		dto.setIdFuenteFinanciamiento(null);
		return dto;
	}

	public FuenteFinanciamientoOPDDTO crearFuenteFinanciamientoOPD(FuenteFinanciamientoOPDDTO dto) {
		FuenteFinanciamientoOPDEntity entity = new FuenteFinanciamientoOPDEntity();
		FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository
				.obtenerPorId(dto.getIdFuenteFinanciamiento());
		entity.setIdFuenteFinanciamiento(fuenteFinanciamientoEntity);
		entity.setDescripcion(dto.getDescripcion());
		fuenteFinanciamientoOPDDAO.crear(entity);
		return obtenerFuenteFinanciamientoOPDPorId(entity.getIdFuenteFinanciamientoOPD());
	}

	public FuenteFinanciamientoOPDDTO obtenerFuenteFinanciamientoOPDPorId(Integer idFuenteFinanciamientoOPD) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, " + "descripcion AS descripcion "
						+ "FROM fuentes_financiamientos_opd WHERE id_fuente_financiamiento_opd = :idFuenteFinanciamientoOPD")
				.setParameter("idFuenteFinanciamientoOPD", idFuenteFinanciamientoOPD);
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoOPDDTO.class));
		FuenteFinanciamientoOPDDTO result = (FuenteFinanciamientoOPDDTO) query.list().get(0);
		return result;
	}

	public FuenteFinanciamientoOPDDTO obtenerFuenteFinanciamientoOPDPorSubFuente(SubfuenteFinanciamientoDTO subfuente) {
		Session session = entityManager.unwrap(Session.class);
		Query query = null;
		if (subfuente.getIdFuenteFinanciamientoOPD() > 0) {
			query = session
					.createSQLQuery("SELECT id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
							+ "id_fuente_financiamiento AS idFuenteFinanciamiento, " + "descripcion AS descripcion "
							+ "FROM fuentes_financiamientos_opd " + " WHERE "
							+ "id_fuente_financiamiento_opd = :idFuenteFinanciamientoOPD")
					.setParameter("idFuenteFinanciamientoOPD", subfuente.getIdFuenteFinanciamientoOPD());
		} else {
			if (subfuente.getIdFuenteFinanciamiento() != null) {
				query = session
						.createSQLQuery("SELECT id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
								+ "id_fuente_financiamiento AS idFuenteFinanciamiento, " + "descripcion AS descripcion "
								+ "FROM fuentes_financiamientos_opd " + " WHERE "
								+ "id_fuente_financiamiento = :idFuenteFinanciamiento")
						.setParameter("idFuenteFinanciamiento", subfuente.getIdFuenteFinanciamiento());
			}
		}
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoOPDDTO.class));
		FuenteFinanciamientoOPDDTO result = (FuenteFinanciamientoOPDDTO) query.list().get(0);
		return result;
	}

	public FuenteFinanciamientoOPDDTO actualizarFuenteFinanciamientoOPD(FuenteFinanciamientoOPDDTO dto) {
		FuenteFinanciamientoOPDEntity entity = fuenteFinanciamientoOPDDAO
				.obtenerPorId(dto.getIdFuenteFinanciamientoOPD());
		FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository
				.obtenerPorId(dto.getIdFuenteFinanciamiento());
		entity.setIdFuenteFinanciamiento(fuenteFinanciamientoEntity);
		entity.setDescripcion(dto.getDescripcion());
		fuenteFinanciamientoOPDDAO.actualizar(entity);
		return obtenerFuenteFinanciamientoOPDPorId(entity.getIdFuenteFinanciamientoOPD());
	}

	public void eliminarFuenteFinanciamientoOPD(FuenteFinanciamientoOPDDTO dto) {
		FuenteFinanciamientoOPDEntity entity = entityManager.find(FuenteFinanciamientoOPDEntity.class,
				dto.getIdFuenteFinanciamientoOPD());
		entityManager.remove(entity);
	}

	// <Comienzan las operqaciones para Subfuente de Financiamiento>
	// public List<SubfuenteFinanciamientoDTO> listaSubuenteFinanciamiento() {
	// Session session = entityManager.unwrap(Session.class);
	// Query query = session.createSQLQuery(
	// "SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
	// + "id_fuente_financiamiento AS idFuenteFinanciamiento, "
	// + "id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
	// + "id_base_36 AS idBase36, "
	// + "descripcion AS descripcion "
	// + "FROM subfuentes_financiamientos");
	// query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
	// @SuppressWarnings("unchecked")
	// List<SubfuenteFinanciamientoDTO> result =
	// (List<SubfuenteFinanciamientoDTO>) query.list();
	// return result;
	// }
	// caaaaaaaaaambios

	public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamientoNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT " + " id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ " id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ " id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, " + " id_base_36 AS idBase36, "
				+ " descripcion AS descripcion " + " FROM subfuentes_financiamientos_temp ");

		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamientoNominaPorDepartamento(
			Integer tipoDepartamento) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT " + " id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ " id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ " id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, " + " id_base_36 AS idBase36, "
						+ " descripcion AS descripcion " + " FROM subfuentes_financiamientos_temp " + " WHERE "
						+ " id_departamento = :tipoDepartamento")
				.setParameter("tipoDepartamento", tipoDepartamento);

		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public SubfuenteFinanciamientoDTO nuevoSubfuenteFinanciamiento() {
		SubfuenteFinanciamientoDTO dto = new SubfuenteFinanciamientoDTO();
		dto.setDescripcion(null);
		dto.setIdFuenteFinanciamiento(null);
		dto.setIdFuenteFinanciamientoOPD(null);
		dto.setIdBase36(null);
		return dto;
	}

	public SubfuenteFinanciamientoDTO crearSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubfuenteFinanciamientoEntity entity = new SubfuenteFinanciamientoEntity();
		entity.setIdBase36(dto.getIdBase36());
		FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository
				.obtenerPorId(dto.getIdFuenteFinanciamiento());
		entity.setIdFuenteFinanciamiento(fuenteFinanciamientoEntity);
		FuenteFinanciamientoOPDEntity fuenteFinanciamientoOPDEntity = fuenteFinanciamientoOPDDAO
				.obtenerPorId(dto.getIdFuenteFinanciamientoOPD());
		entity.setIdFuenteFinanciamientoOPD(fuenteFinanciamientoOPDEntity);
		entity.setDescripcion(dto.getDescripcion());
		subfuenteFinanciamientoDAO.crear(entity);
		return obtenerSubfuenteFinanciamientoPorId(entity.getIdSubfuenteFinanciamiento());
	}

	public SubfuenteFinanciamientoDTO obtenerSubfuenteFinanciamientoPorId(Integer idSubfuenteFinanciamiento) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("   SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ " id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ " id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, " + " id_base_36 AS idBase36, "
						+ " descripcion AS descripcion " + " FROM subfuentes_financiamientos_temp "
						+ " WHERE id_subfuente_financiamiento = :idSubfuenteFinanciamiento")
				.setParameter("idSubfuenteFinanciamiento", idSubfuenteFinanciamiento);
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		SubfuenteFinanciamientoDTO result = (SubfuenteFinanciamientoDTO) query.list().get(0);
		return result;
	}

	public SubfuenteFinanciamientoDTO actualizarSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubfuenteFinanciamientoEntity entity = subfuenteFinanciamientoDAO
				.obtenerPorId(dto.getIdSubfuenteFinanciamiento());
		entity.setIdBase36(dto.getIdBase36());
		FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository
				.obtenerPorId(dto.getIdFuenteFinanciamiento());
		entity.setIdFuenteFinanciamiento(fuenteFinanciamientoEntity);
		FuenteFinanciamientoOPDEntity fuenteFinanciamientoOPDEntity = fuenteFinanciamientoOPDDAO
				.obtenerPorId(dto.getIdFuenteFinanciamientoOPD());
		entity.setIdFuenteFinanciamientoOPD(fuenteFinanciamientoOPDEntity);
		entity.setDescripcion(dto.getDescripcion());
		subfuenteFinanciamientoDAO.actualizar(entity);
		return obtenerSubfuenteFinanciamientoPorId(entity.getIdSubfuenteFinanciamiento());
	}

	public void eliminarSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubfuenteFinanciamientoEntity entity = entityManager.find(SubfuenteFinanciamientoEntity.class,
				dto.getIdSubfuenteFinanciamiento());
		entityManager.remove(entity);
	}
}