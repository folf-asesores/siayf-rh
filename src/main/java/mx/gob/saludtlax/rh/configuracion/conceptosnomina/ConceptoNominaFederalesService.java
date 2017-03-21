package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.ConceptoNominaFactory;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaEmpleadoService;
import mx.gob.saludtlax.rh.persistencia.CategoriaSatRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConceptoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.quinquenios.ConfiguracionQuinquenioDTO;
import mx.gob.saludtlax.rh.quinquenios.ConfiguracionQuinquenioService;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class ConceptoNominaFederalesService implements Serializable {
	private static final long serialVersionUID = -2132654175834907863L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private ConceptoNominaFederalesRepository conceptoNominaDAO;
	@Inject
	private EstatusConceptoNominaRepository estatusConceptoNominaDAO;
	@Inject
	private CategoriaSatRepository categoriaSatDAO;
	@Inject
	private TiposNombramientosRepository tiposNombramientosRepository;

	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;

	@Inject
	private NominaEmpleadoService nominaEmpleadoService;

	@Inject
	ConfiguracionQuinquenioService configuracionQuinquenio;

	public List<EstatusConceptoNominaDTO> listaEstatusConceptoNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estatus_concepto_nomina AS idEstatusConceptoNomina, estatus FROM estatus_conceptos_nomina");
		query.setResultTransformer(Transformers.aliasToBean(EstatusConceptoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<EstatusConceptoNominaDTO> result = (List<EstatusConceptoNominaDTO>) query.list();
		return result;
	}

	public List<NivelEmpleadoDTO> listaNivelEmpleado() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_nivel_empleado AS idNivelEmpleado, nivel_empleado AS nivelEmpleado  FROM niveles_empleados");
		query.setResultTransformer(Transformers.aliasToBean(NivelEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NivelEmpleadoDTO> result = (List<NivelEmpleadoDTO>) query.list();
		return result;
	}

	public List<TipoNombramientoDTO> listaNombramiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_tipo_nombramiento AS idTipoNombramiento, descripcion AS nombramiento FROM tipos_nombramientos");
		query.setResultTransformer(Transformers.aliasToBean(TipoNombramientoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNombramientoDTO> result = (List<TipoNombramientoDTO>) query.list();
		return result;
	}

	public List<CategoriaSatDTO> listaCategoriaSatPorTipo(Integer tipo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_obtener_categorias_sat(:tipoparametro)")
				.setParameter("tipoparametro", tipo);
		query.setResultTransformer(Transformers.aliasToBean(CategoriaSatDTO.class));
		@SuppressWarnings("unchecked")
		List<CategoriaSatDTO> result = (List<CategoriaSatDTO>) query.list();
		return result;
	}

	public ConceptoNominaFederalesDTO nuevoConceptoNomina() {
		ConceptoNominaFederalesDTO dto = new ConceptoNominaFederalesDTO();
		dto.setAguinaldo(Boolean.FALSE);
		dto.setAlta(FechaUtil.fechaActual());
		dto.setBase(Boolean.FALSE);
		dto.setCategoriaSAT(null);
		dto.setClave("");
		dto.setDescripcion("");
		dto.setFormula("");
		dto.setIdCategoriaSAT(null);
		dto.setIdConceptoNomina(null);
		dto.setIdEstatusConceptoNomina(1);
		dto.setIdNivelEmpleado(null);
		dto.setIdNombramiento(null);
		dto.setNivelEmpleado("");
		dto.setNombramiento("");
		dto.setObservacion("");
		dto.setRetroactivo(Boolean.FALSE);
		dto.setTipo(null);
		dto.setTratamiento(Boolean.FALSE);
		dto.setDescripcion("");
		List<TiposNombramientosEntity> tiposNombramientosEntities = tiposNombramientosRepository
				.nombramientosConSubfuente();
		List<ConceptoNominaNombramientoDTO> conceptoNominaNombramientoLista = new ArrayList<>();
		for (TiposNombramientosEntity nombramientosEntity : tiposNombramientosEntities) {
			ConceptoNominaNombramientoDTO conceptoNominaNombramientoDTO = new ConceptoNominaNombramientoDTO();
			conceptoNominaNombramientoDTO.setAplica(false);
			conceptoNominaNombramientoDTO.setIdConceptoNomina(null);
			conceptoNominaNombramientoDTO.setIdTipoNombramiento(nombramientosEntity.getIdTipoNombramiento());
			conceptoNominaNombramientoLista.add(conceptoNominaNombramientoDTO);
		}
		dto.setConceptoNominaNombramientoLista(conceptoNominaNombramientoLista);
		return dto;
	}

	public ConceptoNominaFederalesDTO actualizarConceptoNomina(ConceptoNominaFederalesDTO dto) {
		ConceptoNominaFederalesEntity entity = conceptoNominaDAO.obtenerPorId(dto.getIdConceptoNomina());
		entity.setAguinaldo(dto.getAguinaldo());
		entity.setAlta(FechaUtil.fechaActualSinHora());
		entity.setBase(dto.getBase());
		entity.setClave(dto.getClave());
		entity.setDescripcion(dto.getDescripcion());
		if (dto.getIdEstatusConceptoNomina() != null) {
			entity.setEstatusConceptoNomina(estatusConceptoNominaDAO.obtenerPorId(dto.getIdEstatusConceptoNomina()));
		}

		entity.setFormula(dto.getFormula());

		entity.setObservacion(dto.getObservacion());
		entity.setRetroactivo(dto.getRetroactivo());
		// entity.setTipo(dto.getTipo());
		entity.setTratamiento(dto.getTratamiento());
		conceptoNominaDAO.crear(entity);
		return obtenerConceptoNominaPorId(entity.getIdConceptoNomina());
	}

	public ConceptoNominaFederalesDTO crearConceptoNomina(ConceptoNominaFederalesDTO dto) {
		ConceptoNominaFederalesEntity entity = new ConceptoNominaFederalesEntity();
		entity.setAguinaldo(dto.getAguinaldo());
		entity.setAlta(FechaUtil.fechaActualSinHora());
		entity.setBase(dto.getBase());

		entity.setCategoriaSAT(categoriaSatDAO.obtenerPorId(dto.getIdCategoriaSAT()));
		entity.setClave(dto.getClave());
		entity.setDescripcion(dto.getDescripcion());
		if (dto.getIdEstatusConceptoNomina() != null) {
			entity.setEstatusConceptoNomina(estatusConceptoNominaDAO.obtenerPorId(dto.getIdEstatusConceptoNomina()));
		}

		System.out.println("----" + dto.getFormula());
		entity.setFormula(dto.getFormula());

		entity.setObservacion(dto.getObservacion());
		entity.setRetroactivo(dto.getRetroactivo());
		entity.setTipo(dto.getTipo());
		entity.setTratamiento(dto.getTratamiento());
		conceptoNominaDAO.crear(entity);
		return obtenerConceptoNominaPorId(entity.getIdConceptoNomina());
	}

	public List<ConceptoNominaFederalesDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_conceptos_nomina_federales(:tipoparametro)")
				.setParameter("tipoparametro", tipo.getIdTipoConceptoNomina());
		query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaFederalesDTO.class));
		@SuppressWarnings("unchecked")

		List<ConceptoNominaFederalesDTO> result = (List<ConceptoNominaFederalesDTO>) query.list();

		for (ConceptoNominaFederalesDTO dto : result) {
			if (dto.getIdNombramiento() != null)
				dto.setNombramiento(
						tiposNombramientosRepository.nombramientoPorId(dto.getIdNombramiento()).getDescripcion());
		}

		return result;
	}

	public ConceptoNominaFederalesDTO obtenerConceptoNominaPorId(Integer idConceptoNomina) {

		ConceptoNominaFederalesEntity entity = entityManager.find(ConceptoNominaFederalesEntity.class,
				idConceptoNomina);

		ConceptoNominaFederalesDTO dto = new ConceptoNominaFederalesDTO();

		dto.setAguinaldo(entity.getAguinaldo());
		dto.setAlta(entity.getAlta());
		dto.setBase(entity.getBase());
		dto.setCategoriaSAT(entity.getCategoriaSAT() != null ? entity.getCategoriaSAT().getClave() : null);
		dto.setClave(entity.getClave());
		// dto.setConceptoNominaNombramientoLista();
		dto.setDescripcion(entity.getDescripcion());
		dto.setEstatusConceptoNomina(
				entity.getEstatusConceptoNomina() != null ? entity.getEstatusConceptoNomina().getEstatus() : null);
		dto.setFormula(entity.getFormula());
		dto.setIdCategoriaSAT(entity.getCategoriaSAT() != null ? entity.getCategoriaSAT().getIdCategoriaSAT() : null);
		dto.setIdConceptoNomina(entity.getIdConceptoNomina());
		dto.setIdEstatusConceptoNomina(entity.getEstatusConceptoNomina() != null
				? entity.getEstatusConceptoNomina().getIdEstatusConceptoNomina() : null);

		dto.setObservacion(entity.getObservacion());
		dto.setRetroactivo(entity.getRetroactivo());
		dto.setTipo(entity.getTipo());
		dto.setTratamiento(entity.getTratamiento());

		return dto;
	}

	public FormulaDTO listaFormula() {
		return null;
	}

	public List<ConceptoNominaFederalesDTO> listaConceptosNomina() {
		List<ConceptoNominaFederalesEntity> conceptoNominaEntities = new ArrayList<>();
		conceptoNominaEntities = conceptoNominaDAO.consultarTodos();
		List<ConceptoNominaFederalesDTO> listdtos = new ArrayList<>();
		for (ConceptoNominaFederalesEntity ent : conceptoNominaEntities) {
			ConceptoNominaFederalesDTO dto = ConceptoNominaFactory.crearConceptoNominaFederalesDTO(ent,
					new ConceptoNominaFederalesDTO());
			listdtos.add(dto);
		}
		return listdtos;
	}

	/**
	 * Obtiene los conceptos de nomina a partir de los movimientos programados
	 * para las quincenas
	 * 
	 * @param idConfiguracion
	 * @return
	 */
	public List<ConceptoNominaFederalesDTO> obtenerConceptosMovimientosProgramados(Integer idConfiguracion) {
		ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = new ConfiguracionPresupuestoEntity();
		try {
			configuracionPresupuestoEntity = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		} catch (NoResultException e) {
			throw new ValidacionException("No se encontro la configuracion presupuestal correspondiente",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		List<ConceptoNominaFederalesDTO> listaConceptosFinal = new ArrayList<>();
		if (configuracionPresupuestoEntity != null) {

			Calendar fecha = Calendar.getInstance();
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			Integer quincenaActual = nominaEmpleadoService.obtenerNumeroQuincena(FechaUtil.mesActual(), dia);

			Session session = entityManager.unwrap(Session.class);
			Query query = session.createSQLQuery("CALL usp_conceptos_movimientos_programados(:idPuesto,:periodo)")
					.setParameter("idPuesto", configuracionPresupuestoEntity.getPuesto().getIdPuestoGeneral())
					.setParameter("periodo", quincenaActual);
			query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaFederalesDTO.class));
			@SuppressWarnings("unchecked")

			List<ConceptoNominaFederalesDTO> resultQuery1 = (List<ConceptoNominaFederalesDTO>) query.list();

			if (resultQuery1 != null) {
				listaConceptosFinal.addAll(resultQuery1);
			}

		}
		System.out.println("Conceptos movimientos Programados" + listaConceptosFinal.size());
		return listaConceptosFinal;
	}

	/**
	 * Obtiene los conceptos del empleado a partir de los movimientos fijos
	 * 
	 * @param idConfiguracion
	 * @return
	 */
	public List<ConceptoNominaFederalesDTO> obtenerConceptosMovimientosFijos(Integer idConfiguracion) {
		ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = new ConfiguracionPresupuestoEntity();
		try {
			configuracionPresupuestoEntity = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		} catch (NoResultException e) {
			throw new ValidacionException("No se encontro la configuracion presupuestal correspondiente",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		List<ConceptoNominaFederalesDTO> listaConceptosFinal = new ArrayList<>();
		if (configuracionPresupuestoEntity != null) {

			Session session2 = entityManager.unwrap(Session.class);
			Query query2 = session2.createSQLQuery("CALL usp_conceptos_por_movimiento_federal(:idEmpleado)")
					.setParameter("idEmpleado", configuracionPresupuestoEntity.getEmpleado().getIdEmpleado());
			query2.setResultTransformer(Transformers.aliasToBean(ConceptoNominaFederalesDTO.class));
			@SuppressWarnings("unchecked")

			List<ConceptoNominaFederalesDTO> resultQuery2 = (List<ConceptoNominaFederalesDTO>) query2.list();

			if (resultQuery2 != null) {
				listaConceptosFinal.addAll(resultQuery2);
			}
		}
		return listaConceptosFinal;
	}

	/**
	 * obtiene la lista de conceptos de empleado a partir del puesto
	 * 
	 * @param idConfiguracion
	 * @return
	 */
	public List<ConceptoNominaFederalesDTO> obtenerConceptosPorPuesto(Integer idConfiguracion) {
		ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = new ConfiguracionPresupuestoEntity();
		try {
			configuracionPresupuestoEntity = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		} catch (NoResultException e) {
			throw new ValidacionException("No se encontro la configuracion presupuestal correspondiente",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		List<ConceptoNominaFederalesDTO> listaConceptosFinal = new ArrayList<>();
		if (configuracionPresupuestoEntity != null) {

			Session session3 = entityManager.unwrap(Session.class);
			Query query3 = session3.createSQLQuery("CALL usp_conceptos_por_puesto(:idPuesto)").setParameter("idPuesto",
					configuracionPresupuestoEntity.getPuesto().getIdPuestoGeneral());
			query3.setResultTransformer(Transformers.aliasToBean(ConceptoNominaFederalesDTO.class));
			@SuppressWarnings("unchecked")

			List<ConceptoNominaFederalesDTO> resultQuery3 = (List<ConceptoNominaFederalesDTO>) query3.list();

			if (resultQuery3 != null) {
				listaConceptosFinal.addAll(resultQuery3);
			}
		}
		return listaConceptosFinal;
	}

	/**
	 * obtiene la lista de conceptos de empleado a partir del tipo de
	 * contratacion
	 * 
	 * @param idConfiguracion
	 * @return
	 */
	public List<ConceptoNominaFederalesDTO> obtenerConceptosPorTipoContratacion(Integer idConfiguracion) {
		ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = new ConfiguracionPresupuestoEntity();
		try {
			configuracionPresupuestoEntity = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		} catch (NoResultException e) {
			throw new ValidacionException("No se encontro la configuracion presupuestal correspondiente",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		List<ConceptoNominaFederalesDTO> listaConceptosFinal = new ArrayList<>();
		if (configuracionPresupuestoEntity != null) {
			Calendar fecha = Calendar.getInstance();
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			Integer quincenaActual = nominaEmpleadoService.obtenerNumeroQuincena(FechaUtil.mesActual(), dia);

			Session session3 = entityManager.unwrap(Session.class);
			Query query3 = session3.createSQLQuery("CALL usp_conceptos_tipo_contratacion(:idTipoContratacion,:periodo)")
					.setParameter("idTipoContratacion", configuracionPresupuestoEntity.getTipoContratacion().getId())
					.setParameter("periodo", quincenaActual);
			query3.setResultTransformer(Transformers.aliasToBean(ConceptoNominaFederalesDTO.class));
			@SuppressWarnings("unchecked")

			List<ConceptoNominaFederalesDTO> resultQuery3 = (List<ConceptoNominaFederalesDTO>) query3.list();

			if (resultQuery3 != null) {
				listaConceptosFinal.addAll(resultQuery3);
			}
		}
		return listaConceptosFinal;
	}

	public ConceptoNominaFederalesDTO obtenerConceptoQuinquenioPorIdConfiguracion(Integer idConfiguracion) {
		ConfiguracionQuinquenioDTO configuracionQuinq = configuracionQuinquenio
				.obtenerConfiguracionPorConfiguracionPresup(idConfiguracion);

		ConceptoNominaFederalesDTO quinqueniodto = new ConceptoNominaFederalesDTO();

		if (configuracionQuinq != null) {
			if (configuracionQuinq.getClave_concepto() != null) {
				ConceptoNominaFederalesEntity entity = conceptoNominaDAO
						.obtenerConceptoPorClave(configuracionQuinq.getClave_concepto());

				quinqueniodto.setAguinaldo(entity.getAguinaldo());
				quinqueniodto.setAlta(entity.getAlta());
				quinqueniodto.setBase(entity.getBase());
				quinqueniodto.setCategoriaSAT(
						entity.getCategoriaSAT() != null ? entity.getCategoriaSAT().getCategoriaSAT() : null);
				quinqueniodto.setClave(entity.getClave());
				quinqueniodto.setDescripcion(entity.getDescripcion());
				quinqueniodto.setEstatusConceptoNomina(entity.getEstatusConceptoNomina().getEstatus());
				quinqueniodto.setFormula(entity.getFormula());
				quinqueniodto.setIdCategoriaSAT(
						entity.getCategoriaSAT() != null ? entity.getCategoriaSAT().getIdCategoriaSAT() : null);
				quinqueniodto.setIdConceptoNomina(entity.getIdConceptoNomina());
				quinqueniodto
						.setIdEstatusConceptoNomina(entity.getEstatusConceptoNomina().getIdEstatusConceptoNomina());
				quinqueniodto.setObservacion(entity.getObservacion());
				quinqueniodto.setRetroactivo(entity.getRetroactivo());
				quinqueniodto.setTipo(entity.getTipo());
				quinqueniodto.setTratamiento(entity.getTratamiento());
				return quinqueniodto;
			}
		}
		return null;

	}

	/**
	 * obtiene la lista de coceptos por idConfiguracionPresupuestal
	 * 
	 * @param idConfiguracion
	 * @return
	 */
	public List<ConceptoNominaFederalesDTO> obtenerConceptosPorConfiguracionPresupuestal(Integer idConfiguracion) {
		ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = new ConfiguracionPresupuestoEntity();

		List<ConceptoNominaFederalesDTO> listaConceptosFinal = new ArrayList<>();
		if (configuracionPresupuestoEntity != null) {
			listaConceptosFinal.addAll(obtenerConceptosMovimientosFijos(idConfiguracion));
			listaConceptosFinal.addAll(obtenerConceptosMovimientosProgramados(idConfiguracion));
			listaConceptosFinal.addAll(obtenerConceptosPorPuesto(idConfiguracion));
			listaConceptosFinal.addAll(obtenerConceptosPorTipoContratacion(idConfiguracion));
			if (obtenerConceptoQuinquenioPorIdConfiguracion(idConfiguracion) != null) {
				listaConceptosFinal.add(obtenerConceptoQuinquenioPorIdConfiguracion(idConfiguracion));
			}
		}
		return listaConceptosFinal;

	}
}