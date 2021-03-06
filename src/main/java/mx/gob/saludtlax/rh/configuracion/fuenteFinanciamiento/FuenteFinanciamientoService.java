package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.ArrayList;
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
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@Stateless
public class FuenteFinanciamientoService {
	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
	@Inject
	private FuenteFinanciamientoOPDRepository fuenteFinanciamientoOPDDAO;
	@Inject
	private SubFuenteFinanciamientoTempRepository subfuenteFinanciamientoDAO;
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
		if (subfuente.getFuenteFinanciamientoOPD().getIdFuenteFinanciamientoOPD() > 0) {
			query = session
					.createSQLQuery("SELECT id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
							+ "id_fuente_financiamiento AS idFuenteFinanciamiento, " + "descripcion AS descripcion "
							+ "FROM fuentes_financiamientos_opd " + " WHERE "
							+ "id_fuente_financiamiento_opd = :idFuenteFinanciamientoOPD")
					.setParameter("idFuenteFinanciamientoOPD", subfuente.getFuenteFinanciamientoOPD());
		} else {
			if (subfuente.getFuenteFinanciamiento() != null) {
				query = session
						.createSQLQuery("SELECT id_fuente_financiamiento_opd AS idFuenteFinanciamientoOPD, "
								+ "id_fuente_financiamiento AS idFuenteFinanciamiento, " + "descripcion AS descripcion "
								+ "FROM fuentes_financiamientos_opd " + " WHERE "
								+ "id_fuente_financiamiento = :idFuenteFinanciamiento")
						.setParameter("idFuenteFinanciamiento", subfuente.getFuenteFinanciamiento());
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
		List<SubFuenteFinanciamientoTempEntity> subFEntity = new ArrayList<>();
		subFEntity = entityManager.createQuery("Select sf From SubFuenteFinanciamientoTempEntity as sf", SubFuenteFinanciamientoTempEntity.class).getResultList();
		
		List<SubfuenteFinanciamientoDTO> result = new ArrayList<>();
	
		for(SubFuenteFinanciamientoTempEntity sf : subFEntity){
		SubfuenteFinanciamientoDTO dto = new SubfuenteFinanciamientoDTO();
		dto.setDescripcion(sf.getDescripcion());
		dto.setIdBase36(sf.getIdBase36());
		dto.setIdSubfuenteFinanciamiento(sf.getIdSubfuenteFinanciamiento());
		dto.setNombramiento(sf.getNombramiento());
		dto.setEstatales(sf.getEstatales());
		dto.setFederales(sf.getFederales());
		
		FuenteFinanciamientoEntity fEntity = entityManager.find(FuenteFinanciamientoEntity.class, sf.getIdFuenteFinanciamiento());
		FuenteFinanciamientoDTO fuente = new FuenteFinanciamientoDTO();
		fuente.setDescripcion(fEntity.getDescripcion());
		fuente.setIdBase36(fEntity.getIdBase36());
		fuente.setIdFuenteFinanciamiento(fEntity.getIdFuenteFinanciamiento());
		
		dto.setFuenteFinanciamiento(fuente);
		
		FuenteFinanciamientoOPDEntity fopd = entityManager.find(FuenteFinanciamientoOPDEntity.class, sf.getIdFuenteFinanciamientoOpd());
		FuenteFinanciamientoOPDDTO fuenteOpd = new FuenteFinanciamientoOPDDTO();
		fuenteOpd.setDescripcion(fopd.getDescripcion());
		fuenteOpd.setIdFuenteFinanciamiento(fopd.getIdFuenteFinanciamiento().getIdFuenteFinanciamiento());
		fuenteOpd.setIdFuenteFinanciamientoOPD(fopd.getIdFuenteFinanciamientoOPD());
		
		dto.setFuenteFinanciamientoOPD(fuenteOpd);
		result.add(dto);
		
		}
		return result;
	}

	public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamientoNominaPorDepartamento(
			Integer tipoDepartamento) {
		String condicionDepartamento = null;
		switch (tipoDepartamento) {
		case 1:
			condicionDepartamento = " nombramiento = true                                       ";
			break;
		case 2:
			condicionDepartamento = " estatales = true                                          ";
			break;
		case 3:
			condicionDepartamento = " federales = true                                          ";
			break;
		}
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT                                           "
				+ " id_subfuente_financiamiento AS idSubfuenteFinanciamiento,                   "
				+ " id_base_36 AS idBase36,                                                     "
				+ " descripcion AS descripcion                                                  "
				+ " FROM subfuentes_financiamientos_temp                                        "
				+ " WHERE                                                                       "
				+ condicionDepartamento);
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public SubfuenteFinanciamientoDTO nuevoSubfuenteFinanciamiento() {
		SubfuenteFinanciamientoDTO dto = new SubfuenteFinanciamientoDTO();
		dto.setDescripcion(null);
		dto.setFuenteFinanciamiento(null);
		dto.setFuenteFinanciamientoOPD(null);
		dto.setIdBase36(null);
		dto.setNombramiento(null);
		dto.setEstatales(null);
		dto.setFederales(null);
		return dto;
	}


	public SubfuenteFinanciamientoDTO crearSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubFuenteFinanciamientoTempEntity entity = new SubFuenteFinanciamientoTempEntity();
		entity.setIdBase36(dto.getIdBase36());
		entity.setIdFuenteFinanciamiento(dto.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
		entity.setDescripcion(dto.getDescripcion());
		entity.setIdFuenteFinanciamientoOpd(dto.getFuenteFinanciamientoOPD().getIdFuenteFinanciamientoOPD());
		entity.setNombramiento(dto.getNombramiento());
		entity.setEstatales(dto.getEstatales());
		entity.setFederales(dto.getFederales());
		subfuenteFinanciamientoDAO.crear(entity);
		return obtenerSubfuenteFinanciamientoPorId(entity.getIdSubfuenteFinanciamiento());
	}

	public SubfuenteFinanciamientoDTO obtenerSubfuenteFinanciamientoPorId(Integer idSubfuenteFinanciamiento) {
		SubFuenteFinanciamientoTempEntity sf =new SubFuenteFinanciamientoTempEntity();
		sf = entityManager.createQuery("Select sf From SubFuenteFinanciamientoTempEntity as sf where sf.idSubfuenteFinanciamiento=:idSubfuente", SubFuenteFinanciamientoTempEntity.class).setParameter("idSubfuente", idSubfuenteFinanciamiento).getSingleResult();
		
		
		
		SubfuenteFinanciamientoDTO dto = new SubfuenteFinanciamientoDTO();
		dto.setDescripcion(sf.getDescripcion());
		dto.setIdBase36(sf.getIdBase36());
		dto.setIdSubfuenteFinanciamiento(sf.getIdSubfuenteFinanciamiento());
		
		
		FuenteFinanciamientoEntity fEntity = entityManager.find(FuenteFinanciamientoEntity.class, sf.getIdFuenteFinanciamiento());
		FuenteFinanciamientoDTO fuente = new FuenteFinanciamientoDTO();
		fuente.setDescripcion(fEntity.getDescripcion());
		fuente.setIdBase36(fEntity.getIdBase36());
		fuente.setIdFuenteFinanciamiento(fEntity.getIdFuenteFinanciamiento());
		
		dto.setFuenteFinanciamiento(fuente);
		
		
		FuenteFinanciamientoOPDEntity fopd = entityManager.find(FuenteFinanciamientoOPDEntity.class, sf.getIdFuenteFinanciamientoOpd());
		FuenteFinanciamientoOPDDTO fuenteOpd = new FuenteFinanciamientoOPDDTO();
		fuenteOpd.setDescripcion(fopd.getDescripcion());
		fuenteOpd.setIdFuenteFinanciamiento(fopd.getIdFuenteFinanciamiento().getIdFuenteFinanciamiento());
		fuenteOpd.setIdFuenteFinanciamientoOPD(fopd.getIdFuenteFinanciamientoOPD());
		
		dto.setFuenteFinanciamientoOPD(fuenteOpd);
		
		
		return dto;
	}

	public SubfuenteFinanciamientoDTO actualizarSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubFuenteFinanciamientoTempEntity entity = subfuenteFinanciamientoDAO
				.obtenerPorId(dto.getIdSubfuenteFinanciamiento());
		entity.setIdBase36(dto.getIdBase36());
		entity.setIdFuenteFinanciamiento(dto.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
		entity.setDescripcion(dto.getDescripcion());
		entity.setIdFuenteFinanciamientoOpd(dto.getFuenteFinanciamientoOPD().getIdFuenteFinanciamientoOPD());
		subfuenteFinanciamientoDAO.actualizar(entity);
		return obtenerSubfuenteFinanciamientoPorId(entity.getIdSubfuenteFinanciamiento());
	}

	public void eliminarSubfuenteFinanciamiento(SubfuenteFinanciamientoDTO dto) {
		SubFuenteFinanciamientoTempEntity entity = entityManager.find(SubFuenteFinanciamientoTempEntity.class,
				dto.getIdSubfuenteFinanciamiento());
		entityManager.remove(entity);
	}
}