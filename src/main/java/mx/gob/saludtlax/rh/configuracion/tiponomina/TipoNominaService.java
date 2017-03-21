package mx.gob.saludtlax.rh.configuracion.tiponomina;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.clasificacionnomina.ClasificacionNominaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.persistencia.TipoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;

@Stateless
public class TipoNominaService {
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private TipoNominaRepository tipoNominaDAO;
	@Inject
	private TipoRecursoRepository tipoRecursoRepository;

	public List<TipoNominaDTO> listaTipoNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_tipo_nomina AS idTipoNomina, " + "descripcion AS descripcion, "
				+ "id_tipo_afectacion_nomina AS idTipoAfectacionNomina, "
				+ "id_clasificacion_nomina AS idClasificacionNomina, " + "es_reposicion AS esReposicion, "
				+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "id_tipo_recurso AS idTipoRecurso, ordinaria, retroactiva, prima_vacacional AS primaVacacional, aguinaldo, area  "
				+ "FROM tipos_nominas");
		query.setResultTransformer(Transformers.aliasToBean(TipoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNominaDTO> result = (List<TipoNominaDTO>) query.list();
		return result;
	}

	public List<TipoNominaDTO> listaTipoNominaPorDepartamento(Integer tipoDepartamento) {

		String contratos = " id_clasificacion_nomina = 'C' " + " OR " + " id_clasificacion_nomina = 'S' ";
		String federalizados = " id_clasificacion_nomina <> 'C' " + " AND " + " id_clasificacion_nomina <> 'S' ";

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_tipo_nomina AS idTipoNomina, " + " descripcion AS descripcion, "
				+ " id_tipo_afectacion_nomina AS idTipoAfectacionNomina, "
				+ " id_clasificacion_nomina AS idClasificacionNomina, " + " es_reposicion AS esReposicion, "
				+ " id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ " id_subfuente_financiamiento AS idSubfuenteFinanciamiento, " + " id_tipo_recurso AS idTipoRecurso "
				+ " FROM tipos_nominas " + " WHERE " + (tipoDepartamento == 2 ? contratos : federalizados));
		query.setResultTransformer(Transformers.aliasToBean(TipoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNominaDTO> result = (List<TipoNominaDTO>) query.list();
		return result;
	}

	// <<<<<< Lista TipoNomina Alternativa >>>>>>
	public List<TipoNominaListaDTO> tipoNominaLista() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT * FROM tipos_nominas_lista");
		query.setResultTransformer(Transformers.aliasToBean(TipoNominaListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNominaListaDTO> result = (List<TipoNominaListaDTO>) query.list();
		return result;
	}

	// <<<<<< Fin Lista Alternativa >>>>>>

	public TipoNominaDTO nuevoTipoNomina() {
		TipoNominaDTO dto = new TipoNominaDTO();
		dto.setDescripcion(null);
		dto.setIdTipoAfectacionNomina(null);
		dto.setIdClasificacionNomina(null);

		dto.setIdFuenteFinanciamiento(null);
		dto.setIdSubfuenteFinanciamiento(null);
		dto.setIdTipoRecurso(null);
		return dto;
	}

	public TipoNominaDTO crearTipoNomina(TipoNominaDTO dto) {

		TipoNominaEntity entity = new TipoNominaEntity();
		entity.setDescripcion(dto.getDescripcion());
		entity.setIdTipoAfectacionNomina(dto.getIdTipoAfectacionNomina());
		entity.setIdClasificacionNomina(dto.getIdClasificacionNomina());
		entity.setEsReposicion(dto.getEsReposicion());
		entity.setIdFuenteFinanciemaiento(dto.getIdFuenteFinanciamiento());
		entity.setIdSubfuenteFinanciemaiento(dto.getIdSubfuenteFinanciamiento());
		entity.setIdTipoRecurso(dto.getIdTipoRecurso());
		entity.setOrdinaria(dto.isOrdinaria());
		entity.setRetroactiva(dto.isRetroactiva());
		entity.setPrimaVacacional(dto.isPrimaVacacional());
		entity.setAguinaldo(dto.isAguinaldo());
		entity.setArea(dto.getArea());

		tipoNominaDAO.crear(entity);
		return obtenerTipoNominaPorId(entity.getIdTipoNomina());
	}

	public TipoNominaDTO obtenerTipoNominaPorId(Integer idTipoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_tipo_nomina AS idTipoNomina, " + "descripcion AS descripcion, "
						+ "id_tipo_afectacion_nomina AS idTipoAfectacionNomina, "
						+ "id_clasificacion_nomina AS idClasificacionNomina, " + "es_reposicion AS esReposicion, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ "id_tipo_recurso AS idTipoRecurso, ordinaria, retroactiva, prima_vacacional AS primaVacacional, aguinaldo, area "
						+ "FROM tipos_nominas WHERE id_tipo_nomina= :idTipoNomina")
				.setParameter("idTipoNomina", idTipoNomina);
		query.setResultTransformer(Transformers.aliasToBean(TipoNominaDTO.class));
		TipoNominaDTO result = (TipoNominaDTO) query.list().get(0);
		return result;
	}

	public TipoNominaDTO actualizarTipoNomina(TipoNominaDTO dto) {

		TipoNominaEntity entity = tipoNominaDAO.obtenerPorId(dto.getIdTipoNomina());

		entity.setDescripcion(dto.getDescripcion());
		entity.setIdTipoAfectacionNomina(dto.getIdTipoAfectacionNomina());
		entity.setIdClasificacionNomina(dto.getIdClasificacionNomina());
		entity.setEsReposicion(dto.getEsReposicion());
		entity.setIdFuenteFinanciemaiento(dto.getIdFuenteFinanciamiento());
		entity.setIdSubfuenteFinanciemaiento(dto.getIdSubfuenteFinanciamiento());
		entity.setIdTipoRecurso(dto.getIdTipoRecurso());
		entity.setOrdinaria(dto.isOrdinaria());
		entity.setRetroactiva(dto.isRetroactiva());
		entity.setPrimaVacacional(dto.isPrimaVacacional());
		entity.setAguinaldo(dto.isAguinaldo());
		entity.setArea(dto.getArea());

		tipoNominaDAO.actualizar(entity);
		return obtenerTipoNominaPorId(entity.getIdTipoNomina());
	}

	public void eliminarTipoNomina(TipoNominaDTO dto) {
		TipoNominaEntity entity = entityManager.find(TipoNominaEntity.class, dto.getIdTipoNomina());
		entityManager.remove(entity);
	}

	// <<<<<<Listas para el Llenado>>>>>>

	public List<ClasificacionNominaDTO> obtenerClasificacion() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT clasificacion_nomina AS clasificacionNomina, "
				+ "descripcion AS descripcion " + "FROM clasificaciones_nominas");
		query.setResultTransformer(Transformers.aliasToBean(ClasificacionNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<ClasificacionNominaDTO> result = (List<ClasificacionNominaDTO>) query.list();
		return result;
	}

	public List<FuenteFinanciamientoDTO> obtenerFuente() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "descripcion AS descripcion " + "FROM fuentes_financiamientos");
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<FuenteFinanciamientoDTO> result = (List<FuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public List<SubfuenteFinanciamientoDTO> obtenerSubfuente() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "descripcion AS descripcion " + "FROM subfuentes_financiamientos");
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public List<TipoRecursoDTO> obtenerListaTipoRecurso() {

		List<TipoRecursoDTO> listaDTO = new ArrayList<>();

		List<TipoRecursoEntity> listaEntities = tipoRecursoRepository.consultarTipoRecurso();

		for (TipoRecursoEntity tipoRecursoEntity : listaEntities) {
			TipoRecursoDTO dto = new TipoRecursoDTO();

			dto.setIdTipoRecurso(tipoRecursoEntity.getIdTipoRecurso());
			dto.setIdBase36(tipoRecursoEntity.getIdBase36());
			dto.setDescripcion(tipoRecursoEntity.getDescripcion());

			listaDTO.add(dto);

		}

		return listaDTO;
	}

}