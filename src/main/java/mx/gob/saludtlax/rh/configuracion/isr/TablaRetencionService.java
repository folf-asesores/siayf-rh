package mx.gob.saludtlax.rh.configuracion.isr;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.TablaSubsidioRepositroy;
import mx.gob.saludtlax.rh.persistencia.TablaSubsidioEntity;
import mx.gob.saludtlax.rh.persistencia.TarifaRetencionRepository;
import mx.gob.saludtlax.rh.persistencia.TarifaRetencionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoEntity;

@Stateless
public class TablaRetencionService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private TarifaRetencionRepository tarifaRetencionDAO;
	@Inject
	private TablaSubsidioRepositroy tablaSubsidioDAO;
	@Inject
	private TipoPeriodoRepository tipoPeriodoDAO;
	// private TipoPeriodoRepository tipoPeriodoDAO;

	public List<TablaRetencionListaDTO> listaTablaRetencion() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT " + " tr.id_tarifa_retencion AS idTarifaRetencion,"
				+ " tr.ejercicio_fiscal AS ejercicioFiscal, " + " tr.limite_superior AS limiteSuperior,"
				+ " tr.limite_inferior AS limiteInferior," + " tr.cuota_fija AS cuotaFija,"
				+ " tr.porcentaje_aplicable AS porcentajeAplicable," + " tp.tipo_periodo AS tipoPeriodo, "
				+ " tr.id_tipo_periodo AS idTipoPeriodo " + " FROM tarifas_retenciones AS tr "
				+ "	INNER JOIN tipos_periodos AS tp " + " ON tr.id_tipo_periodo = tp.id_tipo_periodo"
				+ " ORDER BY tr.ejercicio_fiscal, tr.id_tipo_periodo DESC");
		query.setResultTransformer(Transformers.aliasToBean(TablaRetencionListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TablaRetencionListaDTO> result = (List<TablaRetencionListaDTO>) query.list();
		return result;
	}

	public List<TablaSubsidioListaDTO> listaTablaSubsidio() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT " + " id_tabla_subsidio AS idTablaSubsidio,"
				+ " ejercicio_fiscal AS ejercicioFiscal," + " limite_superior AS limiteSuperior,"
				+ " limite_inferior AS limiteInferior," + " subsidio AS subsidio," + " id_tipo_periodo AS idTipoPeriodo"
				+ " FROM tablas_subsidios " + " ORDER BY ejercicio_fiscal, id_tipo_periodo DESC");
		query.setResultTransformer(Transformers.aliasToBean(TablaSubsidioListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TablaSubsidioListaDTO> result = (List<TablaSubsidioListaDTO>) query.list();
		return result;
	}

	public List<TablaRetencionListaDTO> listaTablaRetencionPorejercicioFiscal(Integer ejercicioFiscal,
			String periodicidad) {

		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT tr.id_tarifa_retencion AS idTarifaRetencion,"
						+ " tr.ejercicio_fiscal AS ejercicioFiscal, tr.limite_superior AS limiteSuperior,"
						+ " tr.limite_inferior AS limiteInferior, tr.cuota_fija AS cuotaFija,"
						+ " tr.porcentaje_aplicable AS porcentajeAplicable, tp.tipo_periodo AS tipoPeriodo,"
						+ " tp.id_tipo_periodo AS idTipoPeriodo FROM tarifas_retenciones AS tr "
						+ "	INNER JOIN tipos_periodos AS tp ON tp.id_tipo_periodo = tr.id_tipo_periodo"
						+ " WHERE ejercicio_fiscal =:ejercicioFiscal AND tp.id_tipo_periodo =:idTipoPeriodo")
				.setParameter("ejercicioFiscal", ejercicioFiscal)
				.setParameter("idTipoPeriodo", Integer.valueOf(periodicidad));
		query.setResultTransformer(Transformers.aliasToBean(TablaRetencionListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TablaRetencionListaDTO> result = (List<TablaRetencionListaDTO>) query.list();
		return result;
	}

	public TablaRetencionDTO obtenerTablaRetencionPorId(Integer idTablaRetencion) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_tarifa_retencion AS idTarifaRetencion, "
						+ "limite_inferior AS limiteInferior, " + "limite_superior AS limiteSuperior, "
						+ "cuota_fija AS cuotaFija, " + "porcentaje_aplicable AS porcentajeAplicable, "
						+ "id_tipo_periodo AS idTipoPeriodo, " + "ejercicio_fiscal AS ejercicioFiscal "
						+ "FROM tarifas_retenciones WHERE id_tarifa_retencion = :idTarifaRetencion")
				.setParameter("idTarifaRetencion", idTablaRetencion);
		query.setResultTransformer(Transformers.aliasToBean(TablaRetencionDTO.class));
		TablaRetencionDTO result = (TablaRetencionDTO) query.list().get(0);
		return result;
	}

	public TablaSubsidioDTO obtenerTablaSubsidioPorId(Integer idTablaSubsidio) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_tabla_subsidio AS idTablaSubsidio, " + "limite_inferior AS limiteInferior, "
						+ "limite_superior AS limiteSuperior, " + "subsidio AS subsidio, "
						+ "id_tipo_periodo AS idTipoPeriodo, " + "ejercicio_fiscal AS ejercicioFiscal "
						+ "FROM tablas_subsidios WHERE id_tabla_subsidio= :idTablaSubsidio")
				.setParameter("idTablaSubsidio", idTablaSubsidio);
		query.setResultTransformer(Transformers.aliasToBean(TablaSubsidioDTO.class));
		TablaSubsidioDTO result = (TablaSubsidioDTO) query.list().get(0);
		return result;
	}

	public List<TablaRetencionListaDTO> listaAnio() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT DISTINCT ejercicio_fiscal AS ejercicioFiscal FROM tarifas_retenciones");
		query.setResultTransformer(Transformers.aliasToBean(TablaRetencionListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TablaRetencionListaDTO> result = (List<TablaRetencionListaDTO>) query.list();
		return result;
	}

	public List<TablaSubsidioListaDTO> listaTablaSubsidioPorejercicioFiscal(Integer ejercicioFiscal,
			String periodicidad) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(
						"SELECT ts.id_tabla_subsidio AS idTablaSubsidio, ts.limite_inferior AS limiteInferior, ts.limite_superior AS limiteSuperior, ts.subsidio AS subsidio, ts.id_tipo_periodo AS idTipoPeriodo, ts.ejercicio_fiscal AS ejercicioFiscal FROM tablas_subsidios AS ts INNER JOIN tipos_periodos AS tp ON tp.id_tipo_periodo = ts.id_tipo_periodo WHERE ts.ejercicio_fiscal =:ejercicioFiscal AND tp.id_tipo_periodo =:idTipoPeriodo")
				.setParameter("ejercicioFiscal", ejercicioFiscal)
				.setParameter("idTipoPeriodo", Integer.valueOf(periodicidad));
		query.setResultTransformer(Transformers.aliasToBean(TablaSubsidioListaDTO.class));
		@SuppressWarnings("unchecked")
		List<TablaSubsidioListaDTO> result = (List<TablaSubsidioListaDTO>) query.list();
		return result;
	}

	// Opciones para Tabla Retencion

	BigDecimal LIR = new BigDecimal("0.0");
	BigDecimal all = new BigDecimal("0.0");

	public TablaRetencionDTO nuevaTablaRetencion() {
		TablaRetencionDTO dto = new TablaRetencionDTO();
		dto.setLimiteInferior(LIR);
		dto.setLimiteSuperior(all);
		dto.setCuotaFija(all);
		dto.setPorcentajeAplicable(all);
		dto.setIdTipoPeriodo(12);
		dto.setEjercicioFiscal(null);
		return dto;
	}

	public TablaRetencionDTO crearTablaRetencion(TablaRetencionDTO dto) {
		TarifaRetencionEntity entity = new TarifaRetencionEntity();
		entity.setLimiteInferior(dto.getLimiteInferior());
		entity.setLimiteSuperior(dto.getLimiteSuperior());
		entity.setCuotaFija(dto.getCuotaFija());
		entity.setPorcentajeAplicable(dto.getPorcentajeAplicable());
		TipoPeriodoEntity periodicidadEntity = tipoPeriodoDAO.obtenerPorId(dto.getIdTipoPeriodo());
		entity.setIdTipoPeriodo(periodicidadEntity);
		entity.setEjercicioFiscal(dto.getEjercicioFiscal());
		tarifaRetencionDAO.crear(entity);
		return obtenerTablaRetencionPorId(entity.getIdTarifaRetencion());
	}

	public TablaRetencionDTO actualizarTablaRetencion(TablaRetencionDTO dto) {
		TarifaRetencionEntity entity = tarifaRetencionDAO.obtenerPorId(dto.getIdTarifaRetencion());
		entity.setLimiteInferior(dto.getLimiteInferior());
		entity.setLimiteSuperior(dto.getLimiteSuperior());
		entity.setCuotaFija(dto.getCuotaFija());
		entity.setPorcentajeAplicable(dto.getPorcentajeAplicable());
		TipoPeriodoEntity periodicidadEntity = tipoPeriodoDAO.obtenerPorId(dto.getIdTipoPeriodo());
		entity.setIdTipoPeriodo(periodicidadEntity);
		entity.setEjercicioFiscal(dto.getEjercicioFiscal());
		tarifaRetencionDAO.actualizar(entity);
		return obtenerTablaRetencionPorId(entity.getIdTarifaRetencion());
	}

	public TablaRetencionDTO obtenertablaRetencionPorId(Integer idTarifaRetencion) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_tarifa_retencion AS idTarifaRetencion, "
						+ "limite_inferior AS limiteInferior, " + "limite_superior AS limiteSuperior, "
						+ "cuota_fija AS cuotaFija, " + "porcentaje_aplicable AS porcentajeAplicable, "
						+ "id_tipo_periodo AS idTipoPeriodo, " + "ejercicio_fiscal AS ejercicioFiscal "
						+ "FROM  tarifas_retenciones WHERE id_tarifa_retencion = :idTarifaRetencion")
				.setParameter("idTarifaRetencion", idTarifaRetencion);
		query.setResultTransformer(Transformers.aliasToBean(TablaRetencionDTO.class));
		TablaRetencionDTO result = (TablaRetencionDTO) query.list().get(0);
		return result;
	}

	public void eliminarTablaRetencion(TablaRetencionListaDTO dto) {
		TarifaRetencionEntity entity = entityManager.find(TarifaRetencionEntity.class, dto.getIdTarifaRetencion());
		entityManager.remove(entity);
	}

	// Opciones para Tabla Subsidio

	public TablaSubsidioDTO nuevoSubsidio() {
		TablaSubsidioDTO dto = new TablaSubsidioDTO();
		dto.setLimiteInferior(LIR);
		dto.setLimiteSuperior(all);
		dto.setSubsidio(all);
		dto.setIdTipoPeriodo(12);
		dto.setEjercicioFiscal(null);
		return dto;
	}

	public TablaSubsidioDTO crearTablaSubsidio(TablaSubsidioDTO tablaSubsidioDTO) {
		TablaSubsidioEntity entity = new TablaSubsidioEntity();
		entity.setLimiteInferior(tablaSubsidioDTO.getLimiteInferior());
		entity.setLimiteSuperior(tablaSubsidioDTO.getLimiteSuperior());
		entity.setSubsidio(tablaSubsidioDTO.getSubsidio());
		TipoPeriodoEntity periodicidadEntity = tipoPeriodoDAO.obtenerPorId(tablaSubsidioDTO.getIdTipoPeriodo());
		entity.setIdTipoPeriodo(periodicidadEntity);
		entity.setEjercicioFiscal(tablaSubsidioDTO.getEjercicioFiscal());
		tablaSubsidioDAO.crear(entity);
		return obtenerTablaSubsidioPorId(entity.getIdTablaSubsidio());
	}

	public TablaSubsidioDTO actualizarTablaSubsidio(TablaSubsidioDTO tablaSubsidioDTO) {
		TablaSubsidioEntity entity = tablaSubsidioDAO.obtenerPorId(tablaSubsidioDTO.getIdTablaSubsidio());
		entity.setLimiteInferior(tablaSubsidioDTO.getLimiteInferior());
		entity.setLimiteSuperior(tablaSubsidioDTO.getLimiteSuperior());
		entity.setSubsidio(tablaSubsidioDTO.getSubsidio());
		TipoPeriodoEntity periodicidadEntity = tipoPeriodoDAO.obtenerPorId(tablaSubsidioDTO.getIdTipoPeriodo());
		entity.setIdTipoPeriodo(periodicidadEntity);
		entity.setEjercicioFiscal(tablaSubsidioDTO.getEjercicioFiscal());
		tablaSubsidioDAO.actualizar(entity);
		return obtenerTablaSubsidioPorId(entity.getIdTablaSubsidio());
	}

	public TablaSubsidioDTO obtenertablaSubsidioPorId(Integer idTablaSubsidio) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_tabla_subsidio AS idTablaSubsidio, " + "limite_inferior AS limiteInferior, "
						+ "limite_superior AS limiteSuperior, " + "subsidio AS subsidio, "
						+ "id_tipo_periodo AS idTipoPeriodo, " + "ejercicio_fiscal AS ejercicioFiscal "
						+ "FROM tablas_subsidios WHERE id_tabla_subsidio = :idTablaSubsidio")
				.setParameter("idTablaSubsidio", idTablaSubsidio);
		query.setResultTransformer(Transformers.aliasToBean(TablaSubsidioDTO.class));
		TablaSubsidioDTO result = (TablaSubsidioDTO) query.list().get(0);
		return result;
	}

	public void eliminarTablaSubsidio(TablaSubsidioListaDTO tablaSubsidioSelect) {
		TablaSubsidioEntity entity = entityManager.find(TablaSubsidioEntity.class,
				tablaSubsidioSelect.getIdTablaSubsidio());
		entityManager.remove(entity);

	}
}