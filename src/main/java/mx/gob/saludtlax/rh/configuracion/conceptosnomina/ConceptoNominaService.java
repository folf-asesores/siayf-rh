package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.ConceptoNominaFactory;
import mx.gob.saludtlax.rh.persistencia.CategoriaSatRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConceptoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class ConceptoNominaService implements Serializable {
	private static final long serialVersionUID = -2132654175834907863L;

	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private ConceptoNominaRepository conceptoNominaDAO;
	@Inject
	private EstatusConceptoNominaRepository estatusConceptoNominaDAO;
	@Inject
	private CategoriaSatRepository categoriaSatDAO;
	@Inject
	private TiposNombramientosRepository tiposNombramientosRepository;

	public List<EstatusConceptoNominaDTO> listaEstatusConceptoNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_estatus_concepto_nomina AS idEstatusConceptoNomina, estatus FROM estatus_conceptos_nomina");
		query.setResultTransformer(Transformers.aliasToBean(EstatusConceptoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<EstatusConceptoNominaDTO> result = (List<EstatusConceptoNominaDTO>) query.list();
		return result;
	}

	public List<NivelEmpleadoDTO> listaNivelEmpleado() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_nivel_empleado AS idNivelEmpleado, nivel_empleado AS nivelEmpleado  FROM niveles_empleados");
		query.setResultTransformer(Transformers.aliasToBean(NivelEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NivelEmpleadoDTO> result = (List<NivelEmpleadoDTO>) query.list();
		return result;
	}

	public List<TipoNombramientoDTO> listaNombramiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_tipo_nombramiento AS idTipoNombramiento, descripcion AS nombramiento FROM tipos_nombramientos");
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

	public ConceptoNominaDTO nuevoConceptoNomina() {
		ConceptoNominaDTO dto = new ConceptoNominaDTO();
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
		List<TiposNombramientosEntity> tiposNombramientosEntities = tiposNombramientosRepository.nombramientosConSubfuente();
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

	public ConceptoNominaDTO crearConceptoNomina(ConceptoNominaDTO dto) {
		ConceptoNominaEntity entity = new ConceptoNominaEntity();
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

	public List<ConceptoNominaDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_conceptos_nomina(:tipoparametro)")
				.setParameter("tipoparametro", tipo.getIdTipoConceptoNomina());
		query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<ConceptoNominaDTO> result = (List<ConceptoNominaDTO>) query.list();
		return result;
	}

	public ConceptoNominaDTO obtenerConceptoNominaPorId(Integer idConceptoNomina) {
		
		ConceptoNominaEntity entity = entityManager.find(ConceptoNominaEntity.class, idConceptoNomina);
		
		ConceptoNominaDTO dto = new ConceptoNominaDTO();
		
		dto.setAguinaldo(entity.getAguinaldo());
		dto.setAlta(entity.getAlta());
		dto.setBase(entity.getBase());
		dto.setCategoriaSAT(entity.getCategoriaSAT().getClave());
		dto.setClave(entity.getClave());
		//dto.setConceptoNominaNombramientoLista();
		dto.setDescripcion(entity.getDescripcion());
		dto.setEstatusConceptoNomina(entity.getEstatusConceptoNomina().getEstatus());
		dto.setFormula(entity.getFormula());
		dto.setIdCategoriaSAT(entity.getCategoriaSAT().getIdCategoriaSAT());
		dto.setIdConceptoNomina(entity.getIdConceptoNomina());
		dto.setIdEstatusConceptoNomina(entity.getEstatusConceptoNomina().getIdEstatusConceptoNomina());

		dto.setObservacion(entity.getObservacion());
		dto.setRetroactivo(entity.getRetroactivo());
		dto.setTipo(entity.getTipo());
		dto.setTratamiento(entity.getTratamiento());
		
		return dto;
	}

	public FormulaDTO listaFormula() {
		return null;
	}
	
	public List<ConceptoNominaDTO> listaConceptosNomina(){
		List<ConceptoNominaEntity> conceptoNominaEntities = new ArrayList<>();
		conceptoNominaEntities = conceptoNominaDAO.consultarTodos();
		List<ConceptoNominaDTO> listdtos = new ArrayList<>();
		for(ConceptoNominaEntity ent:conceptoNominaEntities){
			ConceptoNominaDTO dto = ConceptoNominaFactory.crearConceptoNominaDTO(ent, new ConceptoNominaDTO());
			listdtos.add(dto);
		}
		return listdtos;
	}
}