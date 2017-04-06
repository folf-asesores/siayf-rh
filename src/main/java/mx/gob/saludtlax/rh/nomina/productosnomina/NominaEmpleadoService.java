package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoEmpleado;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.aguinaldo.AguinaldoParams;
import mx.gob.saludtlax.rh.nomina.aguinaldo.AguinaldoResult;
import mx.gob.saludtlax.rh.nomina.aguinaldo.CalculoAguinaldoService;
import mx.gob.saludtlax.rh.nomina.calculoisr.CalculoIsrService;
import mx.gob.saludtlax.rh.nomina.calculoisr.ConfiguracionIsrDTO;
import mx.gob.saludtlax.rh.nomina.calculoisr.IsrDTO;
import mx.gob.saludtlax.rh.nomina.movimientoscontrato.InfoMovimientoDTO;
import mx.gob.saludtlax.rh.nomina.movimientoscontrato.MovimientoContratosDTO;
import mx.gob.saludtlax.rh.nomina.movimientoscontrato.MovimientosContratosService;
import mx.gob.saludtlax.rh.nomina.pensionalimenticia.BeneficiarioPensionAlimienticiaDTO;
import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalParams;
import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalResult;
import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalService;
import mx.gob.saludtlax.rh.nomina.primavacional.TipoCalculoPrimaVacacionalEnum;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionQuincenaRepository;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaEntity;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaRepository;
import mx.gob.saludtlax.rh.persistencia.MetodoPagoRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientosNominaContratosEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientosNominaContratosRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaEntity;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaNominaEntity;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaNominaRepository;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaRepository;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.persistencia.TipoCoutaPensionAlimenticiaEntity;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * 
 * @author José Pablo
 *
 */
@Stateless
public class NominaEmpleadoService {

	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	@Inject
	private NominaEmpleadoRepository nominaEmpleadoRepository;
	@Inject
	private ConceptoNominaContratosRepository conceptoNominaContratosRepository;
	@Inject
	private CalculoIsrService calculoIsrService;
	@Inject
	private ConceptosNominaEmpleadosRepository conceptosNominaEmpleadosRepository;
	@Inject
	private ConfiguracionQuincenaRepository configuracionQuincenaRepository;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
	@Inject
	private MovimientosContratosService movimientosContratosService;
	@Inject
	private CalculoAguinaldoService calculoAguinaldoService;
	@Inject
	private PrimaVacacionalService primaVacacionalService;
	@Inject
	private PensionAlimenticiaRepository pensionAlimenticiaRepository;
	@Inject
	private PensionAlimenticiaNominaRepository pensionAlimenticiaNominaRepository;
	@Inject
	private MetodoPagoRepository metodoPagoRepository;
	@Inject
	private FaltaContadaRepository fataContadaRepository;
	@Inject
	private EstatusNominasEmpleadoRepository estatusNominasEmpleadoRepository;
	@Inject
	private QuincenasSuplenciasRepository quincenasSuplenciasRepository;
	@Inject
	private MovimientosNominaContratosRepository movimientosNominaContratosRepository;

	private static final Logger LOGGER = Logger.getLogger(ProductosNominaEJB.class);

	public Integer obntenerNominaActivaPorEmpleado(Integer idEmpleado) {
		try {
			return nominaEmpleadoRepository.obtenerNominaPorEmpleado(idEmpleado);
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<NominaEmpleadoDTO> obntenerNominasActivaPorEmpleado(Integer idEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT " + " ne.id_nomina_empleado AS idNominaEmpleado, "
				+ " ne.id_producto_nomina AS idProductoNomina, " + " ne.id_empleado AS idEmpleado, "
				+ " ne.consecutivo AS consecutivo, " + " ne.percepciones AS percepciones, "
				+ " ne.deducciones AS deducciones, " + " ne.neto AS neto " + " FROM nomina_empleado AS ne " + " WHERE "
				+ " ne.id_empleado = :idEmpleado " + " AND " + " ne.id_estatus_nomina_empleado < 3 " + " GROUP BY "
				+ " ne.id_nomina_empleado ").setParameter("idEmpleado", idEmpleado);
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		return result;
	}

	public void crea(NominaEmpleadoDTO datos) {
		try {
			NominaEmpleadoEntity entity = new NominaEmpleadoEntity();
			entity.setPercepciones(datos.getPercepciones());
			entity.setDeducciones(datos.getDeducciones());
			entity.setNeto(datos.getNeto());
			nominaEmpleadoRepository.crear(entity);
		} catch (PersistenceException px) {
			px.printStackTrace();
		}
	}

	public void actualiza(NominaEmpleadoDTO datos) {
		try {
			NominaEmpleadoEntity entity = new NominaEmpleadoEntity();
			entity.setIdNominaEmpleado(datos.getIdNominaEmpleado());
			entity.setPercepciones(datos.getPercepciones());
			entity.setDeducciones(datos.getDeducciones());
			entity.setNeto(datos.getNeto());
			nominaEmpleadoRepository.actualizar(entity);
		} catch (PersistenceException px) {
			px.printStackTrace();
		}
	}

	public void elimina(NominaEmpleadoDTO datos) {
		nominaEmpleadoRepository.eliminarPorId(datos.getIdNominaEmpleado());
	}

	public List<NominaEmpleadoDTO> listaNominaEmpleado(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT                                                                      "
						+ " ne.id_nomina_empleado 			AS idNominaEmpleado,                              "
						+ " ne.id_producto_nomina 			AS idProductoNomina,                              "
						+ " ne.id_empleado 					AS idEmpleado,                                    "
						+ " ne.consecutivo 					AS consecutivo,                                   "
						+ " e.rfc 							AS rfc,                                           "
						+ " e.nombre_completo 				AS empleado,                                      "
						+ " ne.percepciones 				AS percepciones,                                  "
						+ " ne.deducciones 					AS deducciones,                                   "
						+ " ne.neto 						AS neto,                                          "
						+ " ne.numero_cheque                AS numeroCheque                                   "
						+ " FROM nomina_empleado 			AS ne                                             "
						+ " INNER JOIN empleados 			AS e                                              "
						+ " ON e.id_empleado = ne.id_empleado                                                 "
						+ " WHERE                                                                             "
						+ " ne.id_producto_nomina = :idProductoNomina                                         "
						+ " ORDER BY                                                                          "
						+ " ne.consecutivo                                                                    ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		return result;
	}

	public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListPorPago(PagoNominaDTO pagoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT                                                                "
						+ " ne.id_nomina_empleado 			AS idNominaEmpleado,                        "
						+ " ne.id_producto_nomina 			AS idProductoNomina,                        "
						+ " ne.id_empleado 					AS idEmpleado,                              "
						+ " ne.consecutivo 					AS consecutivo,                             "
						+ " e.rfc 							AS rfc,                                     "
						+ " e.nombre_completo 				AS empleado,                                "
						+ " ne.percepciones 				AS percepciones,                            "
						+ " ne.deducciones 					AS deducciones,                             "
						+ " ne.neto 						AS neto,                                    "
						+ " ne.numero_cheque                AS numeroCheque                             "
						+ " FROM nomina_empleado AS ne                                                  "
						+ " INNER JOIN empleados AS e                                                   "
						+ " ON e.id_empleado = ne.id_empleado                                           "
						+ " WHERE                                                                       "
						+ " ne.id_pago_nomina = :idPagoNomina                                           "
						+ " ORDER BY                                                                    "
						+ " ne.consecutivo                                                              ")
				.setParameter("idPagoNomina", pagoNomina.getIdPagoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		return result;
	}

	public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListSinPago(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT                                                      "
						+ " ne.id_nomina_empleado AS idNominaEmpleado,                        "
						+ " ne.id_producto_nomina AS idProductoNomina,                        "
						+ " ne.id_empleado AS idEmpleado,                                     "
						+ " ne.consecutivo AS consecutivo,                                    "
						+ " e.rfc AS rfc,                                                     "
						+ " e.nombre_completo AS empleado,                                    "
						+ " ne.percepciones AS percepciones,                                  "
						+ " ne.deducciones AS deducciones,                                    "
						+ " ne.neto AS neto                                                   "
						+ " FROM nomina_empleado AS ne                                        "
						+ " INNER JOIN empleados AS e                                         "
						+ " ON e.id_empleado = ne.id_empleado                                 "
						+ " WHERE                                                             "
						+ " ne.id_producto_nomina = :idProductoNomina                         "
						+ " AND                                                               "
						+ " ne.id_pago_nomina IS NULL                                         "
						+ " ORDER BY                                                          "
						+ " ne.consecutivo                                                    ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();

		return result;
	}

	public NominaEmpleadoDTO obtenerNominaEmpleadoDetalle(NominaEmpleadoDTO nominaEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("" + "  SELECT " + "  ne.id_nomina_empleado AS idNominaEmpleado, "
				+ "  ne.id_producto_nomina AS idProductoNomina, " + "  ne.id_empleado AS idEmpleado, "
				+ "  ne.id_configuracion_presupuestal AS idConfiguracionPresupuestal, "
				+ "  ne.id_proyecto AS idProyecto, " + "  ne.id_dependencia AS idDependencia, "
				+ "  ne.id_unidad_responsable AS idUnidadResponsable, "
				+ "  ne.id_tipo_contratacion AS idTipoContratacion, "
				+ "  ne.id_tipo_nombramiento AS idTipoNombramiento, " + "  ne.id_puesto_general AS idPuestoGeneral, "
				+ "  ne.id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "  ne.id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "  ne.id_tipo_recurso AS idTipoRecurso, " + "  ne.id_cuenta_bancaria AS idCuentaBancaria, "
				+ "  ne.id_tabulador AS idTabulador, " + "  ne.id_estatus_nomina_empleado AS idEstatusNominaEmpleado, "
				+ "  e.nombre_completo AS empleado, " + "  e.rfc AS rfc, "
				+ "  ne.fecha_alta_configuracion AS fechaAltaConfiguracion, " + "  ne.sueldo AS sueldo, "
				+ "  CONCAT(pg.codigo, ' ', pg.puesto) AS puestoGeneral, " + "  p.descripcion AS proyecto, "
				+ "  d.descripcion AS dependencia, " + "  tn.descripcion AS tipoNombramiento, "
				+ "  tc.tipo_contratacion tipoContratacion,  " + "  ur.descripcion AS unidadResponsable, "
				+ "  ff.descripcion AS fuenteFinanciamiento,  " + "  sff.descripcion AS subfuenteFinanciamiento, "
				+ "  tr.descripcion AS tipoRecurso,  " + "  cr.descripcion AS centroResponsabilidad, "
				+ "  prg.id_programa AS idPrograma, " + "  prg.programa AS programa, "
				+ "  mp.id_metodo_pago AS idMetodoPago, " + "  mp.descripcion AS metodoPago, "
				+ "  ne.inicio_periodo AS inicioPeriodo,  " + "  ne.fin_periodo AS finPeriodo, "
				+ "  ne.fecha_pago AS fechaPago,  " + "  ne.numero_cuenta AS numeroCuenta, "
				+ "  ne.consecutivo AS consecutivo,  " + "  ne.numero_cheque AS numeroCheque, "
				+ "  ne.percepciones AS percepciones,  " + "  ne.deducciones AS deducciones, "
				+ "  cpe.fecha_inicio_labores AS fechaInicioLabores, " + "  ne.neto AS neto "
				+ "  FROM nomina_empleado AS ne " + "  INNER JOIN empleados AS e "
				+ "  ON ne.id_empleado = e.id_empleado "
				+ "  LEFT JOIN configuraciones_presupuestales_empleados AS cpe "
				+ "  ON cpe.id_configuracion_presupuestal = ne.id_configuracion_presupuestal "
				+ "  LEFT JOIN puestos_generales AS pg " + "  ON pg.id_puesto_general = ne.id_puesto_general "
				+ "  INNER JOIN tipos_nombramientos AS tn " + "  ON tn.id_tipo_nombramiento = ne.id_tipo_nombramiento "
				+ "  INNER JOIN tipos_contratacion AS tc " + "  ON tc.id_tipo_contratacion = ne.id_tipo_contratacion "
				+ "  LEFT JOIN proyectos_temp AS p " + "  ON p.id_proyecto = ne.id_proyecto "
				+ "  LEFT JOIN dependencias_temp AS d " + "  ON d.id_dependencia = ne.id_dependencia "
				+ "  LEFT JOIN unidades_responsables AS ur "
				+ "   ON ur.id_unidad_responsable = ne.id_unidad_responsable "
				+ "  LEFT JOIN fuentes_financiamientos AS ff "
				+ "   ON ff.id_fuente_financiamiento = ne.id_fuente_financiamiento "
				+ "  LEFT JOIN subfuentes_financiamientos_temp AS sff "
				+ "   ON sff.id_subfuente_financiamiento = ne.id_subfuente_financiamiento "
				+ "  LEFT JOIN tipos_recursos_temp AS tr" + "  ON tr.id_tipo_recurso = ne.id_tipo_recurso "
				+ "  LEFT JOIN centro_responsabilidad AS cr "
				+ "  ON cr.id_centro_responsabilidad = ne.id_centro_responsabilidad " + "  LEFT JOIN programas AS prg "
				+ "  ON prg.id_programa = ne.id_programa " + "  LEFT JOIN metodos_pago AS mp "
				+ "  ON mp.id_metodo_pago = ne.id_metodo_pago " + " WHERE "
				+ " ne.id_nomina_empleado = :idNominaEmpleado ")
				.setParameter("idNominaEmpleado", nominaEmpleado.getIdNominaEmpleado());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		nominaEmpleado = (NominaEmpleadoDTO) query.uniqueResult();

		List<ConceptosNominaEmpleadosDTO> percepcionesList = obtenerListaConceptosNominaEmpleados(
				nominaEmpleado.getIdNominaEmpleado(), 1);
		nominaEmpleado.setPercepcionesList(percepcionesList);
		List<ConceptosNominaEmpleadosDTO> deduccionesList = obtenerListaConceptosNominaEmpleados(
				nominaEmpleado.getIdNominaEmpleado(), 2);
		nominaEmpleado.setDeduccionesList(deduccionesList);
		return nominaEmpleado;
	}

	private List<ConceptosNominaEmpleadosDTO> obtenerListaConceptosNominaEmpleados(Integer idNominaEmpleado,
			Integer tipo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT " + " cne.id_conceptos_nomina_empleados AS idConceptosNominaEmpleado, "
						+ " cne.id_nomina_empleado AS idNominaEmpleado, " + " cne.tipo AS tipo, "
						+ " cne.clave AS clave, " + " cne.tipo_sat AS tipoSat, " + " cne.concepto AS concepto, "
						+ " cne.importe_gravado AS importeGravado, " + " cne.importe_excento AS importeExcento, "
						+ " cne.id_concepto_nomina_eventuales AS idConceptoNominaEventuales, "
						+ " cne.id_concepto_nomina_federales AS idConceptoNominaFederales "
						+ " FROM conceptos_nomina_empleados AS cne " + " WHERE "
						+ " cne.id_nomina_empleado = :idNominaEmpleado " + " AND " + " cne.tipo = :tipo ")
				.setParameter("idNominaEmpleado", idNominaEmpleado).setParameter("tipo", tipo);
		query.setResultTransformer(Transformers.aliasToBean(ConceptosNominaEmpleadosDTO.class));
		@SuppressWarnings("unchecked")
		List<ConceptosNominaEmpleadosDTO> result = (List<ConceptosNominaEmpleadosDTO>) query.list();
		return result;
	}

	public List<NominaEmpleadoDTO> obtenerNominaEmpleadoList(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println("productoNomina.getIdProductoNomina():: " + productoNomina.getIdProductoNomina());
		Query query = session.createSQLQuery(" SELECT " + " ne.id_nomina_empleado AS idNominaEmpleado, "
				+ " ne.id_configuracion_presupuestal AS idConfiguracionPresupuestal, "
				+ " ne.id_empleado AS idEmpleado " + " FROM nomina_empleado AS ne " + " INNER JOIN empleados AS e "
				+ " ON e.id_empleado = ne.id_empleado " + " WHERE " + " ne.id_producto_nomina = :idProductoNomina ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));

		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		return result;
	}

	public List<NominaEmpleadoDTO> obtenerNominaEmpleadosSuplentes(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT " + " ne.id_nomina_empleado AS idNominaEmpleado, "
						+ " ne.id_configuracion_presupuestal AS idConfiguracionPresupuestal, "
						+ " ne.id_empleado AS idEmpleado " + " FROM nomina_empleado AS ne "
						+ " INNER JOIN empleados AS e " + " ON e.id_empleado = ne.id_empleado " + " WHERE "
						+ " ne.id_producto_nomina = :idProductoNomina AND ne.calculado = false")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));

		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		return result;
	}

	// @Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void calcularProductoNomina(ProductoNominaDTO productoNomina, NominaEmpleadoDTO nominaEmpleado,
			Boolean aplicarFaltas) {
		System.out.println("nominaEmpleado.getIdNominaEmpleado():: " + nominaEmpleado.getIdNominaEmpleado());

		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
		nominaEmpleadoEntity.setCalculado(true);
		limpiarConceptos(nominaEmpleadoEntity);
		Session session = entityManager.unwrap(Session.class);

		ConfiguracionPresupuestoEntity configuracionPresupuestal = nominaEmpleadoEntity
				.getIdConfiguracionPresupuestal();
		BigDecimal sueldo01 = (configuracionPresupuestal.getSueldo01() == null ? BigDecimal.ZERO
				: configuracionPresupuestal.getSueldo01());
		BigDecimal sueldo14 = (configuracionPresupuestal.getSueldo14() == null ? BigDecimal.ZERO
				: configuracionPresupuestal.getSueldo14());

		BigDecimal sueldoQuincenal01 = sueldo01.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
		BigDecimal sueldoQuincenal14 = sueldo14.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
		BigDecimal percepcionPeriodo = sueldoQuincenal01.add(sueldoQuincenal14);
		// FALTAS Y RETARDOS
		if (aplicarFaltas) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String inicioPeriodo = format.format(productoNomina.getInicioRangoFaltas());
			String finPeriodo = format.format(productoNomina.getFinRangoFaltas());
			Query query = session
					.createSQLQuery("CALL usp_total_descontar_rango_fecha(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
					.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
					.setParameter("idEmpleado", nominaEmpleado.getIdEmpleado());
			BigInteger numeroFaltas = (BigInteger) query.uniqueResult();
			if (numeroFaltas.compareTo(BigInteger.ZERO) == 1) {
				query = session
						.createSQLQuery("CALL usp_fechas_descontar_rango(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
						.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
						.setParameter("idEmpleado", nominaEmpleado.getIdEmpleado());
				query.setResultTransformer(Transformers.aliasToBean(FaltaContadaDTO.class));
				@SuppressWarnings("unchecked")
				List<FaltaContadaDTO> faltasPeriodo = (List<FaltaContadaDTO>) query.list();
				Integer faltas = contarFaltas(faltasPeriodo, nominaEmpleadoEntity, aplicarFaltas);
				System.out.println("faltas:: " + faltas);
				BigDecimal sueldoDiario01 = sueldo01.divide(new BigDecimal(30), 2, RoundingMode.HALF_UP);
				BigDecimal sueldoDiario14 = sueldo14.divide(new BigDecimal(30), 2, RoundingMode.HALF_UP);
				BigDecimal factorFaltas01 = sueldoDiario01.multiply(new BigDecimal(1.4));
				BigDecimal factorFaltas14 = sueldoDiario14.multiply(new BigDecimal(1.4));
				BigDecimal descuentoFaltas01 = factorFaltas01.multiply(new BigDecimal(faltas));
				BigDecimal descuentoFaltas14 = factorFaltas14.multiply(new BigDecimal(faltas));
				BigDecimal importeExcento = BigDecimal.ZERO;
				BigDecimal importeGravado = descuentoFaltas01.add(descuentoFaltas14);
				if (importeGravado.compareTo(BigDecimal.ZERO) == 1) {
					crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 13, importeExcento, importeGravado);
				}
			}
		}
		// HONORARIOS ASIMILABLES A SUELDOS
		if (sueldo01.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = sueldoQuincenal01;
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 2, importeExcento, importeGravado);
		}

		// PERCEPCIÓN COMPLEMENTARIA
		if (sueldo14.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = sueldoQuincenal14;
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 5, importeExcento, importeGravado);
		}

		// PRIMA VACACIONAL
		if (productoNomina.getIdTipoNomina() == 52) {
			try {
				if (configuracionPresupuestal.getFechaInicioLabores() != null) {
					BigDecimal diasPagar = BigDecimal.ZERO;
					BigDecimal diasExentoPagar = BigDecimal.ZERO;
					diasPagar = productoNomina.getDiasPrimaVacasional();
					diasExentoPagar = productoNomina.getDiasExentoPrimaVacasional();
					PrimaVacacionalParams primaVacacionalParams = new PrimaVacacionalParams();
					primaVacacionalParams.setBasePrima(sueldo01);
					primaVacacionalParams.setConfiguracionPresupuesto(configuracionPresupuestal);
					primaVacacionalParams.setDiasExentoPagar(diasExentoPagar);
					primaVacacionalParams.setDiasPagar(diasPagar);
					primaVacacionalParams.setTipoCalculo(TipoCalculoPrimaVacacionalEnum.MENSUAL);

					PrimaVacacionalResult primaVacacionalResult = primaVacacionalService
							.calcular(primaVacacionalParams);
					if (primaVacacionalResult.getTotal() != null
							&& primaVacacionalResult.getTotal().compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal importeExcento = primaVacacionalResult.getExcento();
						BigDecimal importeGravado = primaVacacionalResult.getGravado();
						crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 9, importeExcento, importeGravado);
					}

				} else {
					nominaEmpleadoEntity.setMotivo(
							"No se calculo la Prima Vacacional, dado que no se tiene registrada la fecha de inicio de labores");
					nominaEmpleadoEntity.setCalculado(false);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				nominaEmpleadoEntity.setMotivo(e.getMessage());
				nominaEmpleadoEntity.setCalculado(false);
			}
		}

		// PENSIÓN ALIMENTICIA
		List<BeneficiarioPensionAlimienticiaDTO> beneficiarioPensionAlimienticiaList = obtenerPensionesActivas(
				nominaEmpleado.getIdEmpleado());
		for (BeneficiarioPensionAlimienticiaDTO beneficiarioPensionAlimienticia : beneficiarioPensionAlimienticiaList) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = clacularPension(beneficiarioPensionAlimienticia, percepcionPeriodo);
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 19, importeExcento, importeGravado);
			crearNominaEmpleadoPensionAlimienticia(beneficiarioPensionAlimienticia, nominaEmpleadoEntity,
					importeGravado);
		}

		/*
		 * DÍAS ECONÓMICOS BONO RETROACTIVO BONIFICACIÓN DE FALTAS OTROS
		 * RESPONSABILIDADES PRÉSTAMO JUICIO MERCANTIL
		 */
		BigDecimal subsidiosEntregar = BigDecimal.ZERO;
		BigDecimal impuestoRetener = BigDecimal.ZERO;
		List<InfoMovimientoDTO> movimientoList = movimientosContratosService
				.obtenerInfoMovimientos(nominaEmpleadoEntity.getIdNominaEmpleado());
		for (InfoMovimientoDTO movimientoNominaContrato : movimientoList) {
			if (movimientoNominaContrato.getIdConcepto().equals(11)) {
				ConfiguracionIsrDTO configuracionIsrRetroactivo = new ConfiguracionIsrDTO();
				configuracionIsrRetroactivo.setBaseGravable(percepcionPeriodo);
				configuracionIsrRetroactivo.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
				IsrDTO isrRetroactivo = calculoIsrService.calcularIsrEmpleado(configuracionIsrRetroactivo);
				subsidiosEntregar = isrRetroactivo.getSubsidiosEntregar().multiply(new BigDecimal(-1));
				impuestoRetener = isrRetroactivo.getImpuestoRetener();
				BigDecimal numeroPeriodos = movimientoNominaContrato.getMontoPeriodo().divide(percepcionPeriodo);
				// SUBSIDIO
				if (subsidiosEntregar.compareTo(BigDecimal.ZERO) < 0) {
					subsidiosEntregar = subsidiosEntregar.multiply(numeroPeriodos);
				}

				// I.S.R.
				if (impuestoRetener.compareTo(BigDecimal.ZERO) == 1) {
					impuestoRetener = impuestoRetener.multiply(numeroPeriodos);
				}
			} else {
				if (movimientoNominaContrato.getTipoConcepto() == 1) {
					percepcionPeriodo.add(movimientoNominaContrato.getMontoPeriodo());
				}
			}
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = movimientoNominaContrato.getMontoPeriodo();
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, movimientoNominaContrato.getIdConcepto(),
					importeExcento, importeGravado);
		}

		// RETROACTIVO
		if (configuracionPresupuestal.getAplicaPrimerPago() != null
				&& configuracionPresupuestal.getAplicaPrimerPago()) {
			Date fechaInicioLabores = configuracionPresupuestal.getFechaInicioLabores();
			Integer numeroPeriodos = FechaUtil.calcularNumeroPeriodos(fechaInicioLabores,
					nominaEmpleadoEntity.getInicioPeriodo(), productoNomina.getIdTipoPeriodo());
			// Menos el actual
			System.out.println("numeroPeriodos:: " + numeroPeriodos);
			numeroPeriodos--;
			if (numeroPeriodos > 0) {
				BigDecimal importeExcento = BigDecimal.ZERO;
				BigDecimal importeGravado = percepcionPeriodo.multiply(new BigDecimal(numeroPeriodos));
				crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 11, importeExcento, importeGravado);
				configuracionPresupuestal.setAplicaPrimerPago(Boolean.FALSE);
				configuracionPresupuestoRepository.actualizar(configuracionPresupuestal);
				ConfiguracionIsrDTO configuracionIsrRetroactivo = new ConfiguracionIsrDTO();
				configuracionIsrRetroactivo.setBaseGravable(percepcionPeriodo);
				configuracionIsrRetroactivo.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
				IsrDTO isrRetroactivo = calculoIsrService.calcularIsrEmpleado(configuracionIsrRetroactivo);

				subsidiosEntregar = isrRetroactivo.getSubsidiosEntregar().multiply(new BigDecimal(-1));
				System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
				impuestoRetener = isrRetroactivo.getImpuestoRetener();
				System.out.println("impuestoRetener:: " + impuestoRetener);

				// SUBSIDIO
				if (subsidiosEntregar.compareTo(BigDecimal.ZERO) < 0) {
					subsidiosEntregar = subsidiosEntregar.multiply(new BigDecimal(numeroPeriodos));
					System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
				}

				// I.S.R.
				if (impuestoRetener.compareTo(BigDecimal.ZERO) == 1) {
					impuestoRetener = impuestoRetener.multiply(new BigDecimal(numeroPeriodos));
					System.out.println("impuestoRetener:: " + impuestoRetener);
				}
			}
		}

		ConfiguracionIsrDTO configuracionIsr = new ConfiguracionIsrDTO();
		configuracionIsr.setBaseGravable(percepcionPeriodo);
		configuracionIsr.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
		configuracionIsr.setIdEmpleado(nominaEmpleado.getIdEmpleado());

		IsrDTO isr = calculoIsrService.calcularIsrEmpleado(configuracionIsr);
		// SUBSIDIO
		if (isr.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = subsidiosEntregar.add(isr.getSubsidiosEntregar().multiply(new BigDecimal(-1)));
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 8, importeExcento, importeGravado);
		}

		// I.S.R.
		if (isr.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = impuestoRetener.add(isr.getImpuestoRetener());
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 14, importeExcento, importeGravado);
		}
		EstatusNominasEmpleadoEntity estatusNominaEmpleado = estatusNominasEmpleadoRepository.obtenerPorId(2);
		nominaEmpleadoEntity.setIdEstatusNominaEmpleado(estatusNominaEmpleado);
		nominaEmpleadoRepository.actualizar(nominaEmpleadoEntity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void calcularProductoNominaContratoFederal(ProductoNominaDTO productoNomina,
			NominaEmpleadoDTO nominaEmpleado, Boolean aplicarFaltas) {
		System.out.println("nominaEmpleado.getIdNominaEmpleado():: " + nominaEmpleado.getIdNominaEmpleado());
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
		limpiarConceptos(nominaEmpleadoEntity);

		ConfiguracionPresupuestoEntity configuracionPresupuestal = nominaEmpleadoEntity
				.getIdConfiguracionPresupuestal();
		BigDecimal sueldo = configuracionPresupuestal.getSueldo();

		BigDecimal sueldoQuincenal = sueldo.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);

		if (aplicarFaltas) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String inicioPeriodo = format.format(productoNomina.getInicioRangoFaltas());
			String finPeriodo = format.format(productoNomina.getFinRangoFaltas());

			System.out.println("idEmpleado:: " + nominaEmpleado.getIdEmpleado());
			System.out.println("inicioPeriodo:: " + inicioPeriodo);
			System.out.println("finPeriodo:: " + finPeriodo);
			Session session = entityManager.unwrap(Session.class);
			Query query = session
					.createSQLQuery("CALL usp_total_descontar_rango_fecha(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
					.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
					.setParameter("idEmpleado", nominaEmpleado.getIdEmpleado());
			BigInteger numeroFaltas = (BigInteger) query.uniqueResult();

			// FALTAS Y RETARDOS
			if (numeroFaltas.compareTo(BigInteger.ZERO) == 1) {
				query = session
						.createSQLQuery("CALL usp_fechas_descontar_rango(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
						.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
						.setParameter("idEmpleado", nominaEmpleado.getIdEmpleado());
				query.setResultTransformer(Transformers.aliasToBean(FaltaContadaDTO.class));
				@SuppressWarnings("unchecked")
				List<FaltaContadaDTO> faltasPeriodo = (List<FaltaContadaDTO>) query.list();
				Integer faltas = contarFaltas(faltasPeriodo, nominaEmpleadoEntity, aplicarFaltas);
				System.out.println(nominaEmpleado.getIdEmpleado());
				System.out.println("faltas:: " + faltas);
				BigDecimal sueldoDiario = sueldo.divide(new BigDecimal(30), 2, RoundingMode.HALF_UP);
				BigDecimal factorFaltas = sueldoDiario.multiply(new BigDecimal(1.4));
				BigDecimal descuentoFaltas = factorFaltas.multiply(new BigDecimal(faltas));

				BigDecimal importeExcento = BigDecimal.ZERO;
				BigDecimal importeGravado = descuentoFaltas;
				crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 13, importeExcento, importeGravado);
			}
		}

		// HONORARIOS ASIMILABLES A SUELDOS
		if (sueldo.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = sueldoQuincenal;
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 2, importeExcento, importeGravado);
		}

		// PENSIÓN ALIMENTICIA
		List<BeneficiarioPensionAlimienticiaDTO> beneficiarioPensionAlimienticiaList = obtenerPensionesActivas(
				nominaEmpleado.getIdEmpleado());
		for (BeneficiarioPensionAlimienticiaDTO beneficiarioPensionAlimienticia : beneficiarioPensionAlimienticiaList) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = clacularPension(beneficiarioPensionAlimienticia, sueldoQuincenal);
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 19, importeExcento, importeGravado);
			crearNominaEmpleadoPensionAlimienticia(beneficiarioPensionAlimienticia, nominaEmpleadoEntity,
					sueldoQuincenal);
		}

		/*
		 * DÍAS ECONÓMICOS BONO BONIFICACIÓN DE FALTAS OTROS RESPONSABILIDADES
		 * PRÉSTAMO JUICIO MERCANTIL
		 */
		List<InfoMovimientoDTO> movimientoList = movimientosContratosService
				.obtenerInfoMovimientos(nominaEmpleadoEntity.getIdNominaEmpleado());
		for (InfoMovimientoDTO movimientoNominaContrato : movimientoList) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = movimientoNominaContrato.getMontoPeriodo();
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, movimientoNominaContrato.getIdConcepto(),
					importeExcento, importeGravado);
		}

		// RETROACTIVO
		BigDecimal subsidiosEntregar = BigDecimal.ZERO;
		BigDecimal impuestoRetener = BigDecimal.ZERO;
		if (configuracionPresupuestal.getId().equals(6017)) {
			System.out.println("configuracionPresupuestal.getAplicaPrimerPago():: "
					+ configuracionPresupuestal.getAplicaPrimerPago());
		}
		if (configuracionPresupuestal.getAplicaPrimerPago()) {
			Date fechaInicioLabores = configuracionPresupuestal.getFechaInicioLabores();
			Integer numeroPeriodos = FechaUtil.calcularNumeroPeriodos(fechaInicioLabores,
					nominaEmpleadoEntity.getInicioPeriodo(), productoNomina.getIdTipoPeriodo());
			// Menos el actual
			System.out.println("numeroPeriodos:: " + numeroPeriodos);
			numeroPeriodos--;
			if (numeroPeriodos > 0) {
				BigDecimal importeExcento = BigDecimal.ZERO;
				BigDecimal importeGravado = sueldoQuincenal.multiply(new BigDecimal(numeroPeriodos));
				crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 11, importeExcento, importeGravado);

				ConfiguracionIsrDTO configuracionIsrRetroactivo = new ConfiguracionIsrDTO();
				configuracionIsrRetroactivo.setBaseGravable(sueldoQuincenal);
				configuracionIsrRetroactivo.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
				IsrDTO isrRetroactivo = calculoIsrService.calcularIsrEmpleado(configuracionIsrRetroactivo);

				subsidiosEntregar = isrRetroactivo.getSubsidiosEntregar().multiply(new BigDecimal(-1));
				System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
				impuestoRetener = isrRetroactivo.getImpuestoRetener();
				System.out.println("impuestoRetener:: " + impuestoRetener);

				// SUBSIDIO
				if (subsidiosEntregar.compareTo(BigDecimal.ZERO) < 0) {
					subsidiosEntregar = subsidiosEntregar.multiply(new BigDecimal(numeroPeriodos));
					System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
				}

				// I.S.R.
				if (impuestoRetener.compareTo(BigDecimal.ZERO) == 1) {
					impuestoRetener = impuestoRetener.multiply(new BigDecimal(numeroPeriodos));
					System.out.println("impuestoRetener:: " + impuestoRetener);
				}
			}
		}

		ConfiguracionIsrDTO configuracionIsr = new ConfiguracionIsrDTO();

		configuracionIsr.setBaseGravable(sueldoQuincenal);
		configuracionIsr.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
		configuracionIsr.setIdEmpleado(nominaEmpleado.getIdEmpleado());

		IsrDTO isr = calculoIsrService.calcularIsrEmpleado(configuracionIsr);
		// SUBSIDIO
		if (isr.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = subsidiosEntregar.add(isr.getSubsidiosEntregar().multiply(new BigDecimal(-1)));
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 8, importeExcento, importeGravado);
		}

		// I.S.R.
		if (isr.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = impuestoRetener.add(isr.getImpuestoRetener());
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 14, importeExcento, importeGravado);
		}
	}

	private void crearConceptoNominaEmpleadoEventual(NominaEmpleadoEntity nominaEmpleado, Integer idConcepto,
			BigDecimal excento, BigDecimal gravado) {
		ConceptoNominaContratosEntity conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(idConcepto);
		ConceptosNominaEmpleadosEntity conceptoNominaEmpleadoEntity = null;
		conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
		conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
		conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
		conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
		conceptoNominaEmpleadoEntity.setImporteExcento(excento.setScale(0, RoundingMode.HALF_DOWN));
		conceptoNominaEmpleadoEntity.setImporteGravado(gravado.setScale(0, RoundingMode.HALF_DOWN));
		conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleado);
		conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
		conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
		conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
	}

	private void limpiarConceptos(NominaEmpleadoEntity nominaEmpleadoEntity) {
		List<ConceptosNominaEmpleadosEntity> conceptosNominaEmpleadosEntities = conceptosNominaEmpleadosRepository
				.listaConceptosNominaPorIdNominaEmpleado(nominaEmpleadoEntity.getIdNominaEmpleado().intValue());
		for (ConceptosNominaEmpleadosEntity empleadosEntity : conceptosNominaEmpleadosEntities) {
			List<PensionAlimenticiaNominaEntity> listaPensionAlimenticiaNomina = pensionAlimenticiaNominaRepository
					.listaPensionAlimenticiaNominaPorIdNominaEmpleado(nominaEmpleadoEntity);
			for (PensionAlimenticiaNominaEntity pensionAlimenticiaNominaEntity : listaPensionAlimenticiaNomina) {
				pensionAlimenticiaNominaRepository.eliminar(pensionAlimenticiaNominaEntity);
			}
			conceptosNominaEmpleadosRepository.eliminar(empleadosEntity);
		}
		QuincenasSuplenciasEntity quincenasSuplenciasEntity = quincenasSuplenciasRepository
				.obtenerPorNominaEmpleado(nominaEmpleadoEntity);
		if (quincenasSuplenciasEntity != null) {
			quincenasSuplenciasEntity.setIdNomina(null);
			quincenasSuplenciasRepository.actualizar(quincenasSuplenciasEntity);
		}
	}

	private Integer contarFaltas(List<FaltaContadaDTO> faltasPeriodo, NominaEmpleadoEntity nominaEmpleadoEntity,
			Boolean aplicarFaltas) {
		List<FaltaContadaEntity> fatasContadas = fataContadaRepository.consultarFatasContadas(nominaEmpleadoEntity);
		if (fatasContadas.isEmpty() && aplicarFaltas) {
			Integer faltas = 0;
			for (FaltaContadaDTO faltaContada : faltasPeriodo) {
				if (!fataContadaRepository.faltaEstaContada(faltaContada.getIdFalta())) {
					FaltaContadaEntity fataContadaEntity = new FaltaContadaEntity();
					fataContadaEntity.setFechaFalta(faltaContada.getFechaFalta());
					fataContadaEntity.setEmpleado(nominaEmpleadoEntity.getIdEmpleado());
					fataContadaEntity.setIdFalta(faltaContada.getIdFalta());
					fataContadaEntity.setNominaEmpleado(nominaEmpleadoEntity);
					fataContadaRepository.crear(fataContadaEntity);
					faltas++;
				}
			}
			return faltas;
		} else {
			return fatasContadas.size();
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void calcularProductoNominaSuplencias(ProductoNominaDTO productoNomina, NominaEmpleadoDTO nominaEmpleado) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
		ConceptosNominaEmpleadosEntity conceptoNominaEmpleadoEntity = null;
		ConceptoNominaContratosEntity conceptoNomina = null;
		Integer idEmpleado = nominaEmpleadoEntity.getIdEmpleado().getIdEmpleado();
		BigDecimal sueldo05 = nominaEmpleadoEntity.getSueldo();

		limpiarConceptos(nominaEmpleadoEntity);

		if (nominaEmpleadoEntity.getIdEmpleado().getTipoEmpleado().getIdTipoEmpleado() == EnumTipoEmpleado.EMPLEADO) {
			crearConceptosEmpleado(idEmpleado, productoNomina, nominaEmpleadoEntity);
		} else {
			if (nominaEmpleadoEntity.getIdEmpleado().getTipoEmpleado()
					.getIdTipoEmpleado() == EnumTipoEmpleado.SUPLENTE) {
				ConfiguracionIsrDTO configuracionIsrSuplente = new ConfiguracionIsrDTO();
				BigDecimal sueldoQuincenalSuplente = sueldo05;
				configuracionIsrSuplente.setBaseGravable(sueldoQuincenalSuplente);
				configuracionIsrSuplente.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
				configuracionIsrSuplente.setIdEmpleado(nominaEmpleado.getIdEmpleado());
				IsrDTO isr = calculoIsrService.calcularIsrEmpleado(configuracionIsrSuplente);
				// SUBSIDIO
				if (isr.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) < 0) {
					conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(8);
					conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
					conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
					conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
					conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
					conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
					conceptoNominaEmpleadoEntity
							.setImporteGravado(isr.getSubsidiosEntregar().multiply(new BigDecimal(-1)));
					conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
					conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
					conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
					conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
					nominaEmpleadoEntity.setCalculado(true);
				}

				// I.S.R.
				if (isr.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
					conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(14);
					conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
					conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
					conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
					conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
					conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
					conceptoNominaEmpleadoEntity.setImporteGravado(isr.getImpuestoRetener());
					conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
					conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
					conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
					conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
					nominaEmpleadoEntity.setCalculado(true);
				}
			}
		}

		// SUPLENCIAS
		if (sueldo05.compareTo(BigDecimal.ZERO) == 1) {
			conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(3);
			conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
			conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
			conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
			conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
			conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
			conceptoNominaEmpleadoEntity.setImporteGravado(sueldo05);
			conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
			conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
			conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
			conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
		}

		// PENSIÓN ALIMENTICIA
		List<BeneficiarioPensionAlimienticiaDTO> beneficiarioPensionAlimienticiaList = obtenerPensionesActivas(
				nominaEmpleado.getIdEmpleado());
		for (BeneficiarioPensionAlimienticiaDTO beneficiarioPensionAlimienticia : beneficiarioPensionAlimienticiaList) {
			conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(19);
			conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
			conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
			conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
			conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
			conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
			conceptoNominaEmpleadoEntity
					.setImporteGravado(beneficiarioPensionAlimienticia.getValor().setScale(0, RoundingMode.HALF_DOWN));
			conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
			conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
			conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
			conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
			crearNominaEmpleadoPensionAlimienticia(beneficiarioPensionAlimienticia, nominaEmpleadoEntity,
					BigDecimal.ZERO);
		}

		System.out.println("nominaEmpleadoEntity.getIdNominaEmpleado():: " + nominaEmpleadoEntity);
		System.out
				.println("nominaEmpleadoEntity.getIdNominaEmpleado():: " + nominaEmpleadoEntity.getIdNominaEmpleado());
		/*
		 * DÍAS ECONÓMICOS BONO BONIFICACIÓN DE FALTAS OTROS RESPONSABILIDADES
		 * PRÉSTAMO JUICIO MERCANTIL
		 */
		List<InfoMovimientoDTO> movimientoList = movimientosContratosService
				.obtenerInfoMovimientos(nominaEmpleadoEntity.getIdNominaEmpleado());
		for (InfoMovimientoDTO movimientoNominaContrato : movimientoList) {
			conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(movimientoNominaContrato.getIdConcepto());
			BigDecimal monto = movimientoNominaContrato.getMontoPeriodo();
			conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
			conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
			conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
			conceptoNominaEmpleadoEntity.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
			conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
			conceptoNominaEmpleadoEntity.setImporteGravado(monto.setScale(0, RoundingMode.HALF_DOWN));
			conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
			conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
			conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
			conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);

		}
	}

	private void crearConceptosEmpleado(Integer idEmpleado, ProductoNominaDTO productoNomina,
			NominaEmpleadoEntity nominaEmpleadoEntity) {
		// Obtener las nóminas del empleado.
		List<NominaAcumuladoDTO> nominasEmpleado = nominaEmpleadoRepository.obtenerNominaAutorizadaEmpleado(idEmpleado,
				productoNomina.getNumeroPeriodo(), productoNomina.getAnyoEjercicioFiscal());
		ConceptosNominaEmpleadosEntity conceptoNominaEmpleadoEntity = null;
		ConceptoNominaContratosEntity conceptoNomina = null;

		// Procesar si tiene nóminas
		if (!nominasEmpleado.isEmpty()) {

			List<BigDecimal> conceptosImporteGravado = new ArrayList<>();
			List<BigDecimal> conceptosImporteIsr = new ArrayList<>();
			List<BigDecimal> conceptosImporteSubsidio = new ArrayList<>();
			for (NominaAcumuladoDTO nomina : nominasEmpleado) {
				if (nomina.getTipoNomina() != EnumTipoNomina.ORDINARIA_SUPLENCIAS) {

					if (nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO
							|| nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO_610
							|| nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO_CONTRATO
							|| nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO_HOMOLOGADOS
							|| nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO_SUPLENCIAS
							|| nomina.getTipoNomina() != EnumTipoNomina.RETROACTIVO_X00) {

						System.out.println("es retroactivo");

					} else {
						List<BigDecimal> conceptos = conceptosNominaEmpleadosRepository
								.obtenerImportesGravadosPorIdNomina(nomina.getIdNominaEmpleado());
						conceptosImporteGravado.addAll(conceptos);

						List<BigDecimal> importeIsr = conceptosNominaEmpleadosRepository
								.obtenerImportesISRPorIdNomina(nomina.getIdNominaEmpleado());
						conceptosImporteIsr.addAll(importeIsr);

						List<BigDecimal> importesSubsidio = conceptosNominaEmpleadosRepository
								.obtenerImportesSubsidioPorIdNomina(nomina.getIdNominaEmpleado());
						conceptosImporteSubsidio.addAll(importesSubsidio);

					}

				}

			}

			// Si tiene conceptos con importe gravados se procesa
			if (!conceptosImporteGravado.isEmpty()) {

				// Se realiza la sumatoria de todos los importes grabados
				BigDecimal sumaImporteGravado = BigDecimal.ZERO;
				for (BigDecimal importeGravado : conceptosImporteGravado) {
					sumaImporteGravado = sumaImporteGravado.add(importeGravado);
				}

				// Si la suma de los importes grabados es mayor a cero
				// entonces se procesa
				if (sumaImporteGravado.compareTo(BigDecimal.ZERO) == 1) {
					// Se calcula el importe gravado acumulado para generar
					// el nuevo ISR
					BigDecimal importeGravadoAcumulado = sumaImporteGravado.add(nominaEmpleadoEntity.getSueldo());

					// Se calcula el nuevo ISR
					ConfiguracionIsrDTO calculoIsrAcumulado = new ConfiguracionIsrDTO();
					calculoIsrAcumulado.setBaseGravable(importeGravadoAcumulado);
					calculoIsrAcumulado.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
					calculoIsrAcumulado.setIdEmpleado(nominaEmpleadoEntity.getIdEmpleado().getIdEmpleado());
					IsrDTO isrAcumulado = calculoIsrService.calcularIsrEmpleado(calculoIsrAcumulado);

					// Si el ISR Acumulado calcula ISR Entonces se procede a
					// generar el cálculo para el nuevo isr y el concepto.
					if (isrAcumulado.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {

						// Se obtienen todos los importes isr del empleado y
						// se suman

						// Si tiene un concepto isr o más se procesa
						if (!conceptosImporteIsr.isEmpty()) {

							// Se obtiene la suma de los importes isr
							// pagados.
							BigDecimal sumaImporteISR = BigDecimal.ZERO;
							for (BigDecimal importeISR : conceptosImporteGravado) {
								sumaImporteISR = sumaImporteISR.add(importeISR);
							}

							// Si la suma de los isr es mayor a cero se
							// procede a realizar el isr final
							if (sumaImporteISR.compareTo(BigDecimal.ZERO) == 1) {

								BigDecimal ISRFinal = isrAcumulado.getImpuestoRetener().subtract(sumaImporteISR);

								if (ISRFinal.compareTo(BigDecimal.ZERO) == 1) {

									conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(14);
									conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
									conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
									conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
									conceptoNominaEmpleadoEntity
											.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
									conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
									conceptoNominaEmpleadoEntity.setImporteGravado(ISRFinal);
									conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
									conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
									conceptoNominaEmpleadoEntity
											.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
									conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
									nominaEmpleadoEntity.setCalculado(true);

								} else {
									nominaEmpleadoEntity.setMotivo("ISR Incorrecto: ISR final :" + ISRFinal);
								}

							} else {
								nominaEmpleadoEntity.setMotivo("La sumatoria de su ISR es igual a $" + sumaImporteISR);
							}

						}

					} else if (isrAcumulado.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) == -1) {
						// Se obtienen todos los conceptos de subsidios del
						// empleado.

						if (!conceptosImporteSubsidio.isEmpty()) {
							BigDecimal sumaImporteSubsidio = BigDecimal.ZERO;
							for (BigDecimal importeSubsidio : conceptosImporteSubsidio) {
								sumaImporteSubsidio = sumaImporteSubsidio.add(importeSubsidio);
							}
							// Si la sumatoria de los subsidios es mayor a
							// cero
							if (sumaImporteSubsidio.compareTo(BigDecimal.ZERO) == 1) {
								BigDecimal subsidioFinal = isrAcumulado.getSubsidiosEntregar()
										.subtract(sumaImporteSubsidio);

								// SUBSIDIO
								if (subsidioFinal.compareTo(BigDecimal.ZERO) < 0) {
									conceptoNomina = conceptoNominaContratosRepository.obtenerPorId(8);
									conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
									conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
									conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
									conceptoNominaEmpleadoEntity
											.setIdConceptoEventuales(conceptoNomina.getIdConceptoNomina());
									conceptoNominaEmpleadoEntity.setImporteExcento(new BigDecimal(0));
									conceptoNominaEmpleadoEntity
											.setImporteGravado(subsidioFinal.multiply(new BigDecimal(-1)));
									conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleadoEntity);
									conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
									conceptoNominaEmpleadoEntity
											.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
									conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
									nominaEmpleadoEntity.setCalculado(true);

								} else {
									nominaEmpleadoEntity.setMotivo("Subsidio incorrecto: $" + sumaImporteSubsidio);
								}

							} else {
								nominaEmpleadoEntity.setMotivo("La sumatoria del subsidio es: $" + sumaImporteSubsidio);
							}

						} else {
							nominaEmpleadoEntity.setMotivo("No se encontraron conceptos de subsidio.");
						}

					}

				} else {
					nominaEmpleadoEntity
							.setMotivo("La suma de sus importes gravados como empleado es $" + sumaImporteGravado);
				}

			} else {
				nominaEmpleadoEntity.setMotivo("No tiene conceptos asignados.");
			}

		} else {
			System.out.println(
					"===========================================================================================================");
			nominaEmpleadoEntity.setMotivo(
					"No se ha procesado su nómina como empleado, es requerida para generar el acumulado ISR.");
			nominaEmpleadoRepository.actualizar(nominaEmpleadoEntity);
		}
	}

	private void crearNominaEmpleadoPensionAlimienticia(BeneficiarioPensionAlimienticiaDTO pension,
			NominaEmpleadoEntity nominaEmpleado, BigDecimal montoPension) {
		PensionAlimenticiaNominaEntity pensionAlimenticia = new PensionAlimenticiaNominaEntity();
		pensionAlimenticia.setConsecutivo(nominaEmpleado.getConsecutivo());
		pensionAlimenticia.setFechaPago(nominaEmpleado.getFechaPago());
		pensionAlimenticia.setFinPeriodo(nominaEmpleado.getFinPeriodo());
		pensionAlimenticia.setCentroResponsabilidad(nominaEmpleado.getIdCentroResponsabilidad());
		pensionAlimenticia.setEmpleado(nominaEmpleado.getIdEmpleado());
		pensionAlimenticia.setEstatusNominaEmpleado(nominaEmpleado.getIdEstatusNominaEmpleado());
		pensionAlimenticia.setMetodoPago(metodoPagoRepository.obtenerPorId(nominaEmpleado.getIdMetodoPago()));
		pensionAlimenticia.setNominaEmpleado(nominaEmpleado);
		pensionAlimenticia
				.setPensionAlimenticia(pensionAlimenticiaRepository.obtenerPorId(pension.getIdPensionAlimenticia()));
		pensionAlimenticia.setConfiguracionPresupuesto(nominaEmpleado.getIdConfiguracionPresupuestal());
		pensionAlimenticia.setBanco(null); // Pendiente
		pensionAlimenticia.setPrograma(nominaEmpleado.getPrograma());
		pensionAlimenticia.setProductoNomina(nominaEmpleado.getIdProductoNomina());
		pensionAlimenticia.setInicioPeriodo(nominaEmpleado.getInicioPeriodo());
		pensionAlimenticia.setNumeroCheque(nominaEmpleado.getNumeroCheque());
		pensionAlimenticia.setNumeroCuenta(nominaEmpleado.getNumeroCuenta());
		pensionAlimenticia.setMonto(montoPension);

		pensionAlimenticiaNominaRepository.crear(pensionAlimenticia);
	}

	private BigDecimal clacularPension(BeneficiarioPensionAlimienticiaDTO beneficiarioPensionAlimienticia,
			BigDecimal basePensionPeriodo) {
		BigDecimal montoPension = null;
		montoPension = beneficiarioPensionAlimienticia.getValor();

		switch (beneficiarioPensionAlimienticia.getIdTipoCoutasPension()) {

		// FIJA
		case 1:
			montoPension = beneficiarioPensionAlimienticia.getValor();
			break;

		// PORCENTAJE DE TODAS LAS PERCEPCIONES
		case 2:

			break;

		// PORCENTAJE DEL SUELDO
		case 3:

			break;

		// FIJA CON INCREMENTO PORCENTUAL EN RELACION A LAS MODIFICACIONES AL
		// SALARIO MINIMO
		case 4:

			break;

		default:
			montoPension = BigDecimal.ZERO;
		}
		return montoPension;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<BeneficiarioPensionAlimienticiaDTO> obtenerPensionesActivas(int idEmpleado) {

		List<PensionAlimenticiaEntity> listadoPensionAlimenticiaEntity = pensionAlimenticiaRepository
				.obtenerPensionesActivasPorIdEmpleado(idEmpleado);

		List<BeneficiarioPensionAlimienticiaDTO> listadoBeneficiariosDTO = new ArrayList<BeneficiarioPensionAlimienticiaDTO>();
		if (listadoPensionAlimenticiaEntity != null) {
			for (PensionAlimenticiaEntity pensionAlimenticiaEntity : listadoPensionAlimenticiaEntity) {
				BeneficiarioPensionAlimienticiaDTO pension = new BeneficiarioPensionAlimienticiaDTO();
				pension.setBeneficiario(pensionAlimenticiaEntity.getBeneficiario());
				pension.setEstatus("Activo");
				TipoCoutaPensionAlimenticiaEntity tipoCouta = pensionAlimenticiaEntity.getTipoCoutaAlimenticia();
				pension.setClaveCouta(tipoCouta.getClave());
				pension.setIdTipoCoutasPension(tipoCouta.getIdTipoCoutaPensionAlimenticia());
				pension.setFechaAlta(pensionAlimenticiaEntity.getFechaAlta());
				pension.setFechaBaja(pensionAlimenticiaEntity.getFechaBaja());
				pension.setIdPensionAlimenticia(pensionAlimenticiaEntity.getIdPensionAlimenticia());
				pension.setNumeroExpediente(pensionAlimenticiaEntity.getNumeroExpediente());
				pension.setNumeroJuzgado(pensionAlimenticiaEntity.getNumeroJuzgado());
				pension.setOficio(pensionAlimenticiaEntity.getOficio());
				pension.setRfc(pensionAlimenticiaEntity.getRfc());
				pension.setValor(pensionAlimenticiaEntity.getValor());
				listadoBeneficiariosDTO.add(pension);
			}
		}
		return listadoBeneficiariosDTO;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarTotalesNominaEmpleado(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_totales_nominas_empleado(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarTotalesPorNominaEmpleado(NominaEmpleadoDTO nominaEmpleado) {
		System.out.println("actualizarTotalesPorNominaEmpleado - nominaEmpleado.getIdNominaEmpleado():: "
				+ nominaEmpleado.getIdNominaEmpleado());
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_totales_nominas_por_empleado(:idNominaEmpleado) ")
				.setParameter("idNominaEmpleado", nominaEmpleado.getIdNominaEmpleado());
		query.executeUpdate();
	}

	public Integer numeroQuincena(Integer tipoPeriodo, Integer ejercicioFiscal, Date fechaActual) {
		Integer res = 0;

		try {

			EjercicioFiscalEntity ejercicioF = entityManager
					.createQuery("Select e from EjercicioFiscalEntity as e where e.ejercicioFiscal=:ejercicioFiscal",
							EjercicioFiscalEntity.class)
					.setParameter("ejercicioFiscal", ejercicioFiscal).getSingleResult();
			PeriodoCalendariosEntity query = entityManager
					.createQuery("SELECT pc FROM PeriodoCalendariosEntity as pc "
							+ "WHERE pc.tipoPeriodo.idTipoPeriodo=:tipoPeriodo and pc.idEjercicioFiscal=:ejercicioFiscal and :fecha "
							+ "BETWEEN pc.inicioPeriodo AND pc.finPeriodo", PeriodoCalendariosEntity.class)
					.setParameter("tipoPeriodo", tipoPeriodo)
					.setParameter("ejercicioFiscal", ejercicioF.getIdEjercicioFiscal())
					.setParameter("fecha", fechaActual).getSingleResult();

			return query.getNumeroPeriodo();
		} catch (NoResultException e) {
			return res;
		}
	}

	// Metodo con configuracion de leila
	public Integer obtenerNumeroQuincena(Integer mes, Integer dia) {
		return configuracionQuincenaRepository.obtenerConfiguracionQuincena(mes, dia);
	}

	public Integer obtenerMes(Integer qna) {
		return configuracionQuincenaRepository.mesPorNumQuincena(qna);
	}

	public List<NominaErroneaDTO> consultarNominasErroneas(Integer idProductoNomina) {
		if (!ValidacionUtil.esNumeroPositivo(idProductoNomina)) {
			throw new ValidacionException("Para obtener los cálculos erroneos es requerido el  producto nómina",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		List<NominaErroneaDTO> nominaErroneaList = nominaEmpleadoRepository
				.consultarNominaConErrorEmpleado(idProductoNomina);
		return nominaErroneaList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void validarProductoNomina(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_validar_producto_nomina(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.executeUpdate();
	}

	public List<FaltaContadaDTO> obtenerFaltasContadas(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(conceptoNominaEmpleado.getIdNominaEmpleado());
		List<FaltaContadaEntity> fatasContadas = fataContadaRepository.consultarFatasContadas(nominaEmpleadoEntity);
		List<FaltaContadaDTO> faltasContadasDTO = new ArrayList<>();
		for (FaltaContadaEntity fataContada : fatasContadas) {
			FaltaContadaDTO faltaContadaDTO = new FaltaContadaDTO();
			faltaContadaDTO.setFechaFalta(fataContada.getFechaFalta());
			faltaContadaDTO.setIdFalta(fataContada.getIdFalta());
			faltaContadaDTO.setIdFaltaContada(fataContada.getIdFaltaContada());
			faltaContadaDTO.setIdNominaEmpleado(conceptoNominaEmpleado.getIdNominaEmpleado());
			faltasContadasDTO.add(faltaContadaDTO);
		}
		return faltasContadasDTO;
	}

	public List<FaltaContadaDTO> obtenerFaltasNoContadas(List<FaltaContadaDTO> faltasContadas,
			ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(conceptoNominaEmpleado.getIdNominaEmpleado());
		ProductoNominaEntity productoNomina = nominaEmpleadoEntity.getIdProductoNomina();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String inicioPeriodo = format.format(productoNomina.getInicioRangoFaltas());
		String finPeriodo = format.format(productoNomina.getFinRangoFaltas());

		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("CALL usp_fechas_descontar_rango(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
				.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
				.setParameter("idEmpleado", nominaEmpleadoEntity.getIdEmpleado());
		query.setResultTransformer(Transformers.aliasToBean(FaltaContadaDTO.class));
		@SuppressWarnings("unchecked")
		List<FaltaContadaDTO> faltasPeriodo = (List<FaltaContadaDTO>) query.list();
		List<FaltaContadaDTO> faltasNoContadasDTO = new ArrayList<>();
		for (FaltaContadaDTO fata : faltasPeriodo) {
			boolean esta = false;
			for (FaltaContadaDTO fataContada : faltasContadas) {
				if (fataContada.getIdFalta().equals(fata.getIdFalta())) {
					esta = true;
					break;
				}
			}

			if (!esta) {
				FaltaContadaDTO faltaNoContadaDTO = new FaltaContadaDTO();
				faltaNoContadaDTO.setFechaFalta(fata.getFechaFalta());
				faltaNoContadaDTO.setIdFalta(fata.getIdFalta());
				faltaNoContadaDTO.setIdNominaEmpleado(conceptoNominaEmpleado.getIdNominaEmpleado());
				faltasNoContadasDTO.add(faltaNoContadaDTO);
			}
		}
		return faltasNoContadasDTO;
	}

	public void actualizarConcepto(ConceptosNominaEmpleadosDTO conceptosNomina,
			List<FaltaContadaDTO> faltasContadasDTO) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(conceptosNomina.getIdNominaEmpleado());
		List<FaltaContadaEntity> faltasContadas = fataContadaRepository.consultarFatasContadas(nominaEmpleadoEntity);
		if (!faltasContadas.isEmpty()) {
			for (FaltaContadaEntity fataContada : faltasContadas) {
				fataContadaRepository.eliminar(fataContada);
			}
		}
		for (FaltaContadaDTO faltaContada : faltasContadasDTO) {
			if (!fataContadaRepository.faltaEstaContada(faltaContada.getIdFalta())) {
				FaltaContadaEntity fataContadaEntity = new FaltaContadaEntity();
				fataContadaEntity.setFechaFalta(faltaContada.getFechaFalta());
				fataContadaEntity.setEmpleado(nominaEmpleadoEntity.getIdEmpleado());
				fataContadaEntity.setIdFalta(faltaContada.getIdFalta());
				fataContadaEntity.setNominaEmpleado(nominaEmpleadoEntity);
				fataContadaRepository.crear(fataContadaEntity);
			}
		}

	}

	public void cambiarEstatusNominaEmpleado(ProductoNominaDTO productoNomina, Integer idEstatusNominaEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT " + " ne.id_nomina_empleado AS idNominaEmpleado "
						+ " FROM nomina_empleado AS ne " + " WHERE " + " ne.id_producto_nomina = :idProductoNomina "
						+ " ORDER BY " + " ne.consecutivo  " + " ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));
		@SuppressWarnings("unchecked")
		List<NominaEmpleadoDTO> result = (List<NominaEmpleadoDTO>) query.list();
		EstatusNominasEmpleadoEntity estatusNominaEmpleado = estatusNominasEmpleadoRepository
				.obtenerPorId(idEstatusNominaEmpleado);
		for (NominaEmpleadoDTO nominaEmpleado : result) {
			NominaEmpleadoEntity entity = nominaEmpleadoRepository.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
			entity.setIdEstatusNominaEmpleado(estatusNominaEmpleado);
			switch (idEstatusNominaEmpleado) {
			case 3:
				List<MovimientosNominaContratosEntity> movimientos = movimientosNominaContratosRepository
						.obtenerMovimientosNominaContratosPorIdNominaEmpleado(entity.getIdNominaEmpleado());
				for (MovimientosNominaContratosEntity movimientosNominaContratosEntity : movimientos) {
					// 2 Proceso
					movimientosNominaContratosEntity.setIdEstatus(2);
					movimientosNominaContratosRepository.actualizar(movimientosNominaContratosEntity);
				}
				break;
			default:
				break;
			}
			nominaEmpleadoRepository.actualizar(entity);
		}
	}

	public void calcularProductoNominaAguinaldoContrato(ProductoNominaDTO productoNomina,
			NominaEmpleadoDTO nominaEmpleado) {

		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
		limpiarConceptos(nominaEmpleadoEntity);
		ConfiguracionPresupuestoEntity configuracionPresupuestal = nominaEmpleadoEntity
				.getIdConfiguracionPresupuestal();
		BigDecimal sueldo01 = configuracionPresupuestal.getSueldo01();

		AguinaldoParams aguinaldoParams = new AguinaldoParams();
		aguinaldoParams.setCalculoFiniquito(false);
		BigDecimal diasPagar = productoNomina.getDiasAguinaldo() == null ? BigDecimal.ZERO
				: productoNomina.getDiasAguinaldo();
		BigDecimal diasExentoPagar = productoNomina.getDiasExentoAguinaldo() == null ? BigDecimal.ZERO
				: productoNomina.getDiasExentoAguinaldo();

		aguinaldoParams.setDiasPagar(diasPagar);
		aguinaldoParams.setDiasExentoPagar(diasExentoPagar);
		aguinaldoParams.setFechaCalculo(nominaEmpleadoEntity.getFinPeriodo());
		aguinaldoParams.setIdEmpleado(nominaEmpleadoEntity.getIdEmpleado().getIdEmpleado());
		aguinaldoParams.setBaseAguinaldo(sueldo01);

		AguinaldoResult aguinaldoResult = calculoAguinaldoService.calcular(aguinaldoParams);

		if (aguinaldoResult.getTotal() != null && aguinaldoResult.getTotal().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal importeExcento = aguinaldoResult.getExcento();
			BigDecimal importeGravado = aguinaldoResult.getGravado();
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 7, importeExcento, importeGravado);
		}

		// PENSIÓN ALIMENTICIA
		List<BeneficiarioPensionAlimienticiaDTO> beneficiarioPensionAlimienticiaList = obtenerPensionesActivas(
				nominaEmpleado.getIdEmpleado());
		for (BeneficiarioPensionAlimienticiaDTO beneficiarioPensionAlimienticia : beneficiarioPensionAlimienticiaList) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = clacularPension(beneficiarioPensionAlimienticia, aguinaldoResult.getTotal());
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 19, importeExcento, importeGravado);
			crearNominaEmpleadoPensionAlimienticia(beneficiarioPensionAlimienticia, nominaEmpleadoEntity,
					aguinaldoResult.getTotal());
		}

		ConfiguracionIsrDTO configuracionIsr = new ConfiguracionIsrDTO();
		BigDecimal sueldoQuincenal = aguinaldoResult.getGravado();
		IsrDTO isrAcumulado = obtenerIsrAcumulado(nominaEmpleadoEntity);

		configuracionIsr.setBaseGravable(sueldoQuincenal.add(isrAcumulado.getIngresoGravable()));
		configuracionIsr.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
		configuracionIsr.setIdEmpleado(nominaEmpleado.getIdEmpleado());
		IsrDTO isr = calculoIsrService.calcularIsrEmpleado(configuracionIsr);
		// SUBSIDIO
		if (isr.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = (isr.getSubsidiosEntregar().multiply(new BigDecimal(-1)))
					.subtract(isrAcumulado.getSubsidiosEntregar());
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 8, importeExcento, importeGravado);
		}

		// I.S.R.
		if (isr.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal importeExcento = BigDecimal.ZERO;
			BigDecimal importeGravado = isr.getImpuestoRetener().subtract(isrAcumulado.getImpuestoRetener());
			crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 14, importeExcento, importeGravado);
		}
	}

	private IsrDTO obtenerIsrAcumulado(NominaEmpleadoEntity nominaEmpleadoEntity) {
		IsrDTO isr = new IsrDTO();
		ProductoNominaEntity idProductoNomina = nominaEmpleadoEntity.getIdProductoNomina();
		Integer numeroPeriodo = idProductoNomina.getNumeroPeriodo();

		List<ConceptosNominaEmpleadosDTO> result = obtenerConceptosAcumulados(numeroPeriodo, 1,
				nominaEmpleadoEntity.getIdNominaEmpleado());

		isr.setIngresoGravable(BigDecimal.ZERO);
		isr.setSubsidiosEntregar(BigDecimal.ZERO);
		for (ConceptosNominaEmpleadosDTO dto : result) {
			if (dto.getClave().equals("26")) {
				isr.setSubsidiosEntregar(isr.getSubsidiosEntregar().add(dto.getImporteGravado()));
			} else {
				isr.setIngresoGravable(isr.getIngresoGravable().add(dto.getImporteGravado()));
			}
		}

		isr.setImpuestoRetener(BigDecimal.ZERO);
		for (ConceptosNominaEmpleadosDTO dto : result) {
			if (dto.getClave().equals("52")) {
				isr.setImpuestoRetener(isr.getImpuestoRetener().add(dto.getImporteGravado()));
			}
		}

		return isr;
	}

	private List<ConceptosNominaEmpleadosDTO> obtenerConceptosAcumulados(Integer numeroPeriodo, Integer tipo,
			Integer idNominaEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT                                                                      "
						+ " cne.id_conceptos_nomina_empleados AS idConceptosNominaEmpleado,                   "
						+ " cne.id_nomina_empleado AS idNominaEmpleado,                                       "
						+ " cne.tipo AS tipo,                                                                 "
						+ " cne.clave AS clave,                                                               "
						+ " cne.tipo_sat AS tipoSat,                                                          "
						+ " cne.concepto AS concepto,                                                         "
						+ " cne.importe_gravado AS importeGravado,                                            "
						+ " cne.importe_excento AS importeExcento,                                            "
						+ " cne.id_concepto_nomina_eventuales AS idConceptoNominaEventuales,                  "
						+ " cne.id_concepto_nomina_federales AS idConceptoNominaFederales                     "
						+ " FROM conceptos_nomina_empleados AS cne                                            "
						+ " INNER JOIN nomina_empleado AS ne                                                  "
						+ " ne.id_nomina_empleado = cne.id_nomina_empleado                                    "
						+ " INNER JOIN productos_nomina AS pn                                                 "
						+ " pn.id_producto_nomina = ne.id_producto_nomina                                     "
						+ " WHERE                                                                             "
						+ " pn.numero_periodo = :numeroPeriodo                                                "
						+ " AND                                                                               "
						+ " cne.tipo = :tipo                                                                  "
						+ " AND                                                                               "
						+ " ne.id_nomina_empleado <> :idNominaEmpleado                                        ")
				.setParameter("numeroPeriodo", numeroPeriodo).setParameter("tipo", tipo)
				.setParameter("idNominaEmpleado", idNominaEmpleado);
		query.setResultTransformer(Transformers.aliasToBean(ConceptosNominaEmpleadosDTO.class));
		@SuppressWarnings("unchecked")
		List<ConceptosNominaEmpleadosDTO> result = (List<ConceptosNominaEmpleadosDTO>) query.list();
		return result;
	}

	public List<PensionesNominaDTO> obtenerPensionesNominaList(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_obtener_pensiones_prodcuto(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(PensionesNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<PensionesNominaDTO> pensionesNomina = (List<PensionesNominaDTO>) query.list();
		return pensionesNomina;
	}

	public BigDecimal calcularDescuentoFaltas(MovimientoContratosDTO movimientoContratos) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(movimientoContratos.getIdNominaEmpleado());
		Integer faltas = movimientoContratos.getFaltaContadaList().size();
		ConfiguracionPresupuestoEntity configuracionPresupuestal = nominaEmpleadoEntity
				.getIdConfiguracionPresupuestal();
		BigDecimal sueldo01 = (configuracionPresupuestal.getSueldo01() == null ? BigDecimal.ZERO
				: configuracionPresupuestal.getSueldo01());
		BigDecimal sueldo14 = (configuracionPresupuestal.getSueldo14() == null ? BigDecimal.ZERO
				: configuracionPresupuestal.getSueldo14());

		BigDecimal sueldoDiario01 = sueldo01.divide(new BigDecimal(30), 2, RoundingMode.HALF_UP);
		BigDecimal sueldoDiario14 = sueldo14.divide(new BigDecimal(30), 2, RoundingMode.HALF_UP);
		BigDecimal factorFaltas01 = sueldoDiario01.multiply(new BigDecimal(1.4));
		BigDecimal factorFaltas14 = sueldoDiario14.multiply(new BigDecimal(1.4));
		BigDecimal descuentoFaltas01 = factorFaltas01.multiply(new BigDecimal(faltas));
		BigDecimal descuentoFaltas14 = factorFaltas14.multiply(new BigDecimal(faltas));
		BigDecimal monto = descuentoFaltas01.add(descuentoFaltas14);
		return monto.setScale(0, RoundingMode.HALF_DOWN);
	}

	public Integer obtenerUltimoNumeroCheque() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT                                                             "
						+ " MAX(n.numero_cheque)                                                     "
						+ " FROM nomina_empleado AS n                                                "
						+ " INNER JOIN tipos_contratacion AS tc                                      "
						+ " ON tc.id_tipo_contratacion = n.id_tipo_contratacion                      "
						+ " WHERE                                                                    "
						+ " tc.area_responsable = :areaResponsable                                   ")
				.setParameter("areaResponsable", 2);
		String ultimoNumeroChequeStr = (String) query.uniqueResult();
		Integer ultimoNumeroCheque = Integer.parseInt(ultimoNumeroChequeStr);
		if (ultimoNumeroCheque == 0) {
			ultimoNumeroCheque = 1;
		}
		return ultimoNumeroCheque;
	}
}