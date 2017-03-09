package mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.CategoriaSatDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.EstatusConceptoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.TipoConceptoNominaEnum;
import mx.gob.saludtlax.rh.persistencia.CategoriaSatRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConceptoNominaRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class ConceptoNominaContratosService implements Serializable {
	private static final long serialVersionUID = -2132654175834907863L;

	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject private ConceptoNominaContratosRepository conceptoNominaContratosRepository;
	@Inject private EstatusConceptoNominaRepository estatusConceptoNominaRepository;
	@Inject private CategoriaSatRepository categoriaSatRepository;

	public List<EstatusConceptoNominaDTO> listaEstatusConceptoNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_estatus_concepto_nomina AS idEstatusConceptoNomina, estatus FROM estatus_conceptos_nomina");
		query.setResultTransformer(Transformers.aliasToBean(EstatusConceptoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<EstatusConceptoNominaDTO> result = (List<EstatusConceptoNominaDTO>) query.list();
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

	public ConceptoNominaContratosDTO nuevoConceptoNomina() {
		ConceptoNominaContratosDTO dto = new ConceptoNominaContratosDTO();
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
		dto.setObservacion("");
		dto.setRetroactivo(Boolean.FALSE);
		dto.setTipo(null);
		dto.setTratamiento(Boolean.FALSE);
		dto.setDescripcion("");
		return dto;
	}

	public ConceptoNominaContratosDTO crearConceptoNomina(ConceptoNominaContratosDTO dto) {
		ConceptoNominaContratosEntity entity = new ConceptoNominaContratosEntity();
		entity.setAguinaldo(dto.getAguinaldo());
		entity.setAlta(FechaUtil.fechaActual());
		entity.setBase(dto.getBase());
        if (dto.getIdCategoriaSAT() != null) {
            entity.setCategoriaSAT(categoriaSatRepository.obtenerPorId(dto.getIdCategoriaSAT()));
        }
		entity.setClave(dto.getClave());
		entity.setDescripcion(dto.getDescripcion());
		if (dto.getIdEstatusConceptoNomina() != null) {
			entity.setEstatusConceptoNomina(estatusConceptoNominaRepository.obtenerPorId(dto.getIdEstatusConceptoNomina()));
		}
		entity.setFormula(dto.getFormula());

		entity.setObservacion(dto.getObservacion());
		entity.setRetroactivo(dto.getRetroactivo());
		entity.setTipo(dto.getTipo());
		entity.setTratamiento(dto.getTratamiento());
		conceptoNominaContratosRepository.crear(entity);
		return obtenerConceptoNominaContratosPorId(entity.getIdConceptoNomina());
	}

	public List<ConceptoNominaContratosDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_conceptos_nomina_contratos(:tipoparametro)")
				.setParameter("tipoparametro", tipo.getIdTipoConceptoNomina());
		query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaContratosDTO.class));
		@SuppressWarnings("unchecked")
		List<ConceptoNominaContratosDTO> result = (List<ConceptoNominaContratosDTO>) query.list();
		return result;
	}

	public ConceptoNominaContratosDTO obtenerConceptoNominaContratosPorId(Integer idConceptoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT "
				+ "  id_concepto_nomina_contrato AS idConceptoNomina,"
				+ "  clave                       AS clave,"
				+ "  tipo                        AS tipo,"
				+ "  descripcion                 AS descripcion,"
				+ "  formula                     AS formula,"
				+ "  id_estatus_concepto_nomina  AS idEstatusConceptoNomina,"
				+ "  base                        AS base,"
				+ "  aguinaldo                   AS aguinaldo,"
				+ "  retroactivo                 AS retroactivo,"
				+ "  tratamiento                 AS tratamiento,"
				+ "  id_categoria_sat            AS idCategoriaSAT,"
				+ "  alta                        AS alta,"
				+ "  observacion                 AS observacion"
				+ " FROM conceptos_nominas_contratos "
				+ " WHERE "
				+ " id_concepto_nomina_contrato = :idConceptoNomina")
				.setParameter("idConceptoNomina", idConceptoNomina);
		query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaContratosDTO.class));
		ConceptoNominaContratosDTO result = (ConceptoNominaContratosDTO) query.list().get(0);
		return result;
	}

    public ConceptoNominaContratosDTO actualizarConceptoNomina(ConceptoNominaContratosDTO dto) {
        ConceptoNominaContratosEntity entity = conceptoNominaContratosRepository.obtenerPorId(dto.getIdConceptoNomina());
        entity.setAguinaldo(dto.getAguinaldo());
        entity.setBase(dto.getBase());
        if (dto.getIdCategoriaSAT() != null) {
            entity.setCategoriaSAT(categoriaSatRepository.obtenerPorId(dto.getIdCategoriaSAT()));
        }
        entity.setClave(dto.getClave());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getIdEstatusConceptoNomina() != null) {
            entity.setEstatusConceptoNomina(estatusConceptoNominaRepository.obtenerPorId(dto.getIdEstatusConceptoNomina()));
        }
        entity.setFormula(dto.getFormula());
        entity.setObservacion(dto.getObservacion());
        entity.setRetroactivo(dto.getRetroactivo());
        entity.setTipo(dto.getTipo());
        entity.setTratamiento(dto.getTratamiento());
        conceptoNominaContratosRepository.actualizar(entity);
        return obtenerConceptoNominaContratosPorId(entity.getIdConceptoNomina());
   }

	public List<ConceptoNominaContratosDTO> obtenerConceptosLista(boolean aplicaMovimiento) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT "
				+ "  id_concepto_nomina_contrato AS idConceptoNomina,"
				+ "  clave                       AS clave,"
				+ "  tipo                        AS tipo,"
				+ "  descripcion                 AS descripcion,"
				+ "  formula                     AS formula,"
				+ "  id_estatus_concepto_nomina  AS idEstatusConceptoNomina,"
				+ "  base                        AS base,"
				+ "  aguinaldo                   AS aguinaldo,"
				+ "  retroactivo                 AS retroactivo,"
				+ "  tratamiento                 AS tratamiento,"
				+ "  id_categoria_sat            AS idCategoriaSAT,"
				+ "  alta                        AS alta,"
				+ "  observacion                 AS observacion,"
				+ "  id_tipo_movimiento          AS idTipoMovimiento"
				+ " FROM conceptos_nominas_contratos "
				+ " WHERE "
				+ " aplica_movimientos = :aplicaMovimiento ")
				.setParameter("aplicaMovimiento", aplicaMovimiento);
		query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaContratosDTO.class));
		@SuppressWarnings("unchecked")
		List<ConceptoNominaContratosDTO> result = (List<ConceptoNominaContratosDTO> ) query.list();
		return result;
	}

	public boolean esMovimientoFijo(Integer idConceptoContratos) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(" SELECT "
                + " id_tipo_movimiento "
				+ " FROM conceptos_nominas_contratos"
				+ " WHERE "
                + " id_concepto_nomina_contrato = :idConceptoContratos ")
                .setParameter("idConceptoContratos", idConceptoContratos);
        Integer idTipoMovimiento = (Integer) query.uniqueResult();
        return idTipoMovimiento == 2;
	}
}