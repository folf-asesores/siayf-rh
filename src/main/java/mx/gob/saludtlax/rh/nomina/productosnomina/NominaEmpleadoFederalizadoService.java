package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesService;
import mx.gob.saludtlax.rh.nomina.Evaluador;
import mx.gob.saludtlax.rh.nomina.EvaluadorService;
import mx.gob.saludtlax.rh.nomina.calculoisr.CalculoISRFederalesService;
import mx.gob.saludtlax.rh.nomina.calculoisr.CalculoIsrService;
import mx.gob.saludtlax.rh.nomina.calculoisr.ConfiguracionIsrDTO;
import mx.gob.saludtlax.rh.nomina.calculoisr.IsrDTO;
import mx.gob.saludtlax.rh.nomina.pensionalimenticia.BeneficiarioPensionAlimienticiaDTO;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEspecialEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEspecialRepository;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaEntity;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaRepository;
import mx.gob.saludtlax.rh.persistencia.MetodoPagoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaEntity;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaNominaEntity;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaNominaRepository;
import mx.gob.saludtlax.rh.persistencia.PensionAlimenticiaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoCoutaPensionAlimenticiaEntity;

public class NominaEmpleadoFederalizadoService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	@Inject
	private NominaEmpleadoRepository nominaEmpleadoRepository;
	@Inject
	private CalculoIsrService calculoIsrService;

	@Inject
	private CalculoISRFederalesService calculoIsrFederalesService;
	@Inject
	private ConceptosNominaEspecialRepository conceptosNominaEspecialesRepository;

	@Inject
	private ConceptosNominaEmpleadosRepository conceptosNominaEmpleadosRepository;
	@Inject
	private PensionAlimenticiaRepository pensionAlimenticiaRepository;
	@Inject
	private PensionAlimenticiaNominaRepository pensionAlimenticiaNominaRepository;
	@Inject
	private MetodoPagoRepository metodoPagoRepository;
	@Inject
	private FaltaContadaRepository fataContadaRepository;
	@Inject
	private ConceptoNominaFederalesService conceptoNominaFederalesService;
	@Inject
	private ConceptoNominaFederalesRepository conceptoNominaFederalesRepository;
	@Inject
	private EvaluadorService evaluadorService;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void calcularProductoNominaFederales(ProductoNominaDTO productoNomina, NominaEmpleadoDTO nominaEmpleado) {
		System.out.println("nominaEmpleado.getIdNominaEmpleado():: " + nominaEmpleado.getIdNominaEmpleado());
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository
				.obtenerPorId(nominaEmpleado.getIdNominaEmpleado());
		limpiarConceptos(nominaEmpleadoEntity);
		List<ConceptoNominaFederalesDTO> listaConceptos = conceptoNominaFederalesService
				.obtenerConceptosPorConfiguracionPresupuestal(nominaEmpleado.getIdConfiguracionPresupuestal());
		BigDecimal baseGravable = BigDecimal.ZERO;
		BigDecimal ingresoBase = BigDecimal.ZERO;
		for (ConceptoNominaFederalesDTO conceptoNominaFederales : listaConceptos) {
			BigDecimal excento = BigDecimal.ZERO;
			BigDecimal gravado = evaluadorService.evaluarFormula(conceptoNominaFederales, nominaEmpleadoEntity);
			ConceptoNominaFederalesEntity conceptoNomina = conceptoNominaFederalesRepository
					.obtenerPorId(conceptoNominaFederales.getIdConceptoNomina());
			if (conceptoNomina.getTratamiento()) {
				//System.out.println("Concepto que se toma para isr:: " + conceptoNominaFederales.getClave() + conceptoNominaFederales.getFormula() +" -- " + gravado);
				baseGravable = baseGravable.add(gravado);
			}
			if (conceptoNomina.getBase()) {
				ingresoBase = ingresoBase.add(gravado);
			}
			crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, conceptoNomina, excento, gravado);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String inicioPeriodo = format.format(productoNomina.getInicioRangoFaltas());
		String finPeriodo = format.format(productoNomina.getFinRangoFaltas());

		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("CALL usp_total_descontar_rango_fecha(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
				.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
				.setParameter("idEmpleado", nominaEmpleadoEntity.getIdEmpleado().getIdEmpleado());
		BigInteger numeroFaltas = (BigInteger) query.uniqueResult();
		// FALTAS Y RETARDOS
		if (numeroFaltas.compareTo(BigInteger.ZERO) == 1) {
			query = session.createSQLQuery("CALL usp_fechas_descontar_rango(:inicioPeriodo, :finPeriodo, :idEmpleado) ")
					.setParameter("inicioPeriodo", inicioPeriodo).setParameter("finPeriodo", finPeriodo)
					.setParameter("idEmpleado", nominaEmpleado.getIdEmpleado());
			query.setResultTransformer(Transformers.aliasToBean(FaltaContadaDTO.class));
			@SuppressWarnings("unchecked")
			List<FaltaContadaDTO> faltasPeriodo = (List<FaltaContadaDTO>) query.list();
			Integer faltas = contarFaltas(faltasPeriodo, nominaEmpleadoEntity);
			BigDecimal sueldoDiario = ingresoBase.divide(new BigDecimal(30), 2, RoundingMode.HALF_EVEN);
			BigDecimal factorFaltas = sueldoDiario.multiply(new BigDecimal(1.4));
			BigDecimal excento = BigDecimal.ZERO;
			BigDecimal gravado = factorFaltas.multiply(new BigDecimal(faltas));

			ConceptoNominaFederalesEntity conceptoNomina = conceptoNominaFederalesRepository.obtenerPorId(213);
			crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, conceptoNomina, excento, gravado);
		}
		//
		// // HONORARIOS ASIMILABLES A SUELDOS
		// if (sueldo01.compareTo(BigDecimal.ZERO) == 1) {
		// BigDecimal importeExcento = BigDecimal.ZERO;
		// BigDecimal importeGravado = sueldoQuincenal01;
		//// crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 2,
		// importeExcento, importeGravado);
		// }
		//
		// // PERCEPCIÓN COMPLEMENTARIA
		// if (sueldo14.compareTo(BigDecimal.ZERO) == 1) {
		// BigDecimal importeExcento = BigDecimal.ZERO;
		// BigDecimal importeGravado = sueldoQuincenal14;
		//// crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 5,
		// importeExcento, importeGravado);
		// }
		//
		// // PENSIÓN ALIMENTICIA
		// List<BeneficiarioPensionAlimienticiaDTO>
		// beneficiarioPensionAlimienticiaList =
		// obtenerPensionesActivas(nominaEmpleado.getIdEmpleado());
		// for (BeneficiarioPensionAlimienticiaDTO
		// beneficiarioPensionAlimienticia :
		// beneficiarioPensionAlimienticiaList) {
		// BigDecimal importeExcento = BigDecimal.ZERO;
		// BigDecimal importeGravado =
		// clacularPension(beneficiarioPensionAlimienticia,percepcionPeriodo);
		//// crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity, 19,
		// importeExcento, importeGravado);
		// crearNominaEmpleadoPensionAlimienticia(beneficiarioPensionAlimienticia,
		// nominaEmpleadoEntity, percepcionPeriodo);
		// }
		//
		// /*
		// * DÍAS ECONÓMICOS BONO BONIFICACIÓN DE FALTAS OTROS RESPONSABILIDADES
		// * PRÉSTAMO JUICIO MERCANTIL
		// */
		// List<InfoMovimientoDTO> movimientoList = movimientosContratosService
		// .obtenerInfoMovimientos(nominaEmpleadoEntity.getIdNominaEmpleado());
		// for (InfoMovimientoDTO movimientoNominaContrato : movimientoList) {
		// BigDecimal importeExcento = BigDecimal.ZERO;
		// BigDecimal importeGravado =
		// movimientoNominaContrato.getMontoPeriodo();
		//// crearConceptoNominaEmpleadoEventual(nominaEmpleadoEntity,
		// movimientoNominaContrato.getIdConcepto(), importeExcento,
		// importeGravado);
		// }
		//
		// // RETROACTIVO
		BigDecimal subsidiosEntregar = BigDecimal.ZERO;
		BigDecimal impuestoRetener = BigDecimal.ZERO;
		// if (configuracionPresupuestal.getAplicaPrimerPago() != null
		// && configuracionPresupuestal.getAplicaPrimerPago()) {
		// ConfiguracionPresupuestoEntity configuracionPresupuestal =
		// nominaEmpleadoEntity.getIdConfiguracionPresupuestal();
		// Date fechaInicioLabores =
		// configuracionPresupuestal.getFechaInicioLabores();
		// Integer numeroPeriodos =
		// FechaUtil.calcularNumeroPeriodos(fechaInicioLabores,
		// nominaEmpleadoEntity.getInicioPeriodo(),
		// productoNomina.getIdTipoPeriodo());
		// // Menos el actual
		// System.out.println("numeroPeriodos:: " + numeroPeriodos);
		// numeroPeriodos--;
		// if (numeroPeriodos > 0) {
		// BigDecimal excento = BigDecimal.ZERO;
		// BigDecimal gravado = ingresoBase.multiply(new
		// BigDecimal(numeroPeriodos));
		// ConceptoNominaFederalesEntity conceptoNomina =
		// conceptoNominaFederalesRepository.obtenerPorId(213);
		// crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity,
		// conceptoNomina, excento, gravado);
		//
		// ConfiguracionIsrDTO configuracionIsrRetroactivo = new
		// ConfiguracionIsrDTO();
		// configuracionIsrRetroactivo.setBaseGravable(ingresoBase);
		// configuracionIsrRetroactivo.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
		// IsrDTO isrRetroactivo =
		// calculoIsrService.calcularIsrEmpleado(configuracionIsrRetroactivo);
		//
		// subsidiosEntregar =
		// isrRetroactivo.getSubsidiosEntregar().multiply(new BigDecimal(-1));
		// System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
		// impuestoRetener = isrRetroactivo.getImpuestoRetener();
		// System.out.println("impuestoRetener:: " + impuestoRetener);
		//
		// // SUBSIDIO
		// if (subsidiosEntregar.compareTo(BigDecimal.ZERO) < 0) {
		// subsidiosEntregar = subsidiosEntregar.multiply(new
		// BigDecimal(numeroPeriodos));
		// System.out.println("subsidiosEntregar:: " + subsidiosEntregar);
		// }
		//
		// // I.S.R.
		// if (impuestoRetener.compareTo(BigDecimal.ZERO) == 1) {
		// impuestoRetener = impuestoRetener.multiply(new
		// BigDecimal(numeroPeriodos));
		// System.out.println("impuestoRetener:: " + impuestoRetener);
		// }
		// }
		// }

		ConfiguracionIsrDTO configuracionIsr = new ConfiguracionIsrDTO();
		configuracionIsr.setBaseGravable(baseGravable.setScale(2, RoundingMode.HALF_EVEN));
		configuracionIsr.setIdTipoPeriodo(productoNomina.getIdTipoPeriodo());
		configuracionIsr.setIdEmpleado(nominaEmpleado.getIdEmpleado());

		IsrDTO isrOrdinario = calculoIsrFederalesService.calcularIsrEmpleado(configuracionIsr);

		List<ConceptoNominaFederalesEntity> listaConceptosEspeciales = new ArrayList<>();
		List<ConceptosNominaEspecialEntity> listaConceptosIsrEspecial = new ArrayList<>();
		for (ConceptoNominaFederalesDTO concepto : listaConceptos) {
			ConceptosNominaEspecialEntity conceptoEspecialEntity = conceptosNominaEspecialesRepository
					.obtenerConcepto(concepto.getIdConceptoNomina());
			if (conceptoEspecialEntity != null) {
				listaConceptosEspeciales.add(conceptoEspecialEntity.getIdConceptobase());
				listaConceptosIsrEspecial.add(conceptoEspecialEntity);
			}
		}

		if (!listaConceptosEspeciales.isEmpty()) {
			System.out.println("tiene conceptos especiales::");
			System.out.println("ISR ordinario:" + isrOrdinario.getImpuestoRetener().setScale(2, RoundingMode.HALF_EVEN));
			System.out.println("Base gravable original: " + baseGravable.setScale(2, RoundingMode.HALF_EVEN));
			BigDecimal montoImportesEspeciales=new BigDecimal(0);
			BigDecimal montoPorcetajeParcialConcepto = new BigDecimal(0);
			BigDecimal montoDiferenciaISR = new BigDecimal(0);
			// Saca los importes de los conceptos Extras
			for (ConceptoNominaFederalesEntity cptoEsp : listaConceptosEspeciales) {
				BigDecimal importeConcepto = evaluadorService.evaluarFormula(
						conceptoNominaFederalesService.obtenerConceptoNominaPorId(cptoEsp.getIdConceptoNomina()),
						nominaEmpleadoEntity);
				System.out.println("formula concepto especial: " + cptoEsp.getFormula() + "--" + importeConcepto);
				montoImportesEspeciales = montoImportesEspeciales.add(importeConcepto);
				
			}
			System.out.println("Importe de conceptos extras:" + montoImportesEspeciales);
			montoImportesEspeciales = montoImportesEspeciales.add(configuracionIsr.getBaseGravable());
			configuracionIsr.setBaseGravable(montoImportesEspeciales);
			System.out.println("Base con monto concepto especial; " + configuracionIsr.getBaseGravable());
			IsrDTO isrModificado = calculoIsrFederalesService.calcularIsrEmpleado(configuracionIsr);
			System.out.println("Nuevo isr: " + isrModificado.getImpuestoRetener());
			System.out.println("Diferencias: "
					+ isrModificado.getImpuestoRetener().subtract(isrOrdinario.getImpuestoRetener()));
			montoDiferenciaISR = montoDiferenciaISR.add(isrModificado.getImpuestoRetener().subtract(isrOrdinario.getImpuestoRetener()));
			
			List<ConceptosNominaEspecialEntity> conceptosEspecialesProcesados = new ArrayList<>();
			//importe de la suma de los isr de los conceptos.
			BigDecimal importeTotalConcepto = new BigDecimal(0);
			for(ConceptosNominaEspecialEntity cEspecial: listaConceptosIsrEspecial){
				BigDecimal montoIsrConcepto = new BigDecimal(0);
				BigDecimal importeConcepto = evaluadorService.evaluarFormula(
						conceptoNominaFederalesService.obtenerConceptoNominaPorId(cEspecial.getIdConceptobase().getIdConceptoNomina()),
						nominaEmpleadoEntity);
				montoIsrConcepto = importeConcepto.divide(montoImportesEspeciales, 2, RoundingMode.HALF_EVEN);
				System.out.println("Calculos isr concepto:" + importeConcepto+"/"+montoImportesEspeciales +"="+montoIsrConcepto);
				montoIsrConcepto = montoIsrConcepto.multiply(montoDiferenciaISR);
				System.out.println("Monto ISR concepto : " + montoIsrConcepto);
				if(cEspecial.getIdConceptoCompensacion()!=null){
				cEspecial.getIdConceptoCompensacion().setFormula(""+montoIsrConcepto);
				}
				cEspecial.getIdConceptoISR().setFormula(""+montoIsrConcepto);
				conceptosEspecialesProcesados.add(cEspecial);
				importeTotalConcepto = importeTotalConcepto.add(montoIsrConcepto);
				
			}
			
			importeTotalConcepto = importeTotalConcepto.add(configuracionIsr.getBaseGravable());
			configuracionIsr.setBaseGravable(importeTotalConcepto);
			IsrDTO isrFinal = calculoIsrFederalesService.calcularIsrEmpleado(configuracionIsr);
			System.out.println("ISR Final: " +isrFinal.getImpuestoRetener());			
			System.out.println("Suma de ISR:" + isrFinal.getImpuestoRetener());
			
			if (isrFinal.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal excento = BigDecimal.ZERO;
				BigDecimal gravado = impuestoRetener.add(isrFinal.getImpuestoRetener());
				ConceptoNominaFederalesEntity conceptoNomina = conceptoNominaFederalesRepository.obtenerPorId(156);
				crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, conceptoNomina, excento, gravado);
			}
			
			for(ConceptosNominaEspecialEntity cEsp: conceptosEspecialesProcesados){
				if(cEsp.getIdConceptoCompensacion()!=null){
					crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, cEsp.getIdConceptoCompensacion(), BigDecimal.ZERO, new BigDecimal(cEsp.getIdConceptoCompensacion().getFormula()));
				}
				
				if(cEsp.getIdConceptoISR()!=null){
					crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, cEsp.getIdConceptoISR(), BigDecimal.ZERO, new BigDecimal(cEsp.getIdConceptoISR().getFormula()));
				}
			}
			
			
		}else{
			if (isrOrdinario.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal excento = BigDecimal.ZERO;
				BigDecimal gravado = impuestoRetener.add(isrOrdinario.getImpuestoRetener());
				ConceptoNominaFederalesEntity conceptoNomina = conceptoNominaFederalesRepository.obtenerPorId(156);
				crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, conceptoNomina, excento, gravado);
			}
		}

		
		
		
		// SUBSIDIO
		// if (isr.getSubsidiosEntregar().compareTo(BigDecimal.ZERO) < 0) {
		// BigDecimal excento = BigDecimal.ZERO;
		// BigDecimal gravado =
		// subsidiosEntregar.add(isr.getSubsidiosEntregar().multiply(new
		// BigDecimal(-1)));
		// ConceptoNominaFederalesEntity conceptoNomina =
		// conceptoNominaFederalesRepository.obtenerPorId(213);
		// crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity,
		// conceptoNomina, excento, gravado);
		// }

		// I.S.R.
//		if (isrOrdinario.getImpuestoRetener().compareTo(BigDecimal.ZERO) == 1) {
//			BigDecimal excento = BigDecimal.ZERO;
//			BigDecimal gravado = impuestoRetener.add(isrOrdinario.getImpuestoRetener());
//			ConceptoNominaFederalesEntity conceptoNomina = conceptoNominaFederalesRepository.obtenerPorId(156);
//			crearConceptoNominaEmpleadoFederalizados(nominaEmpleadoEntity, conceptoNomina, excento, gravado);
//		}
	}

	private void crearConceptoNominaEmpleadoFederalizados(NominaEmpleadoEntity nominaEmpleado,
			ConceptoNominaFederalesEntity conceptoNomina, BigDecimal excento, BigDecimal gravado) {
		ConceptosNominaEmpleadosEntity conceptoNominaEmpleadoEntity = null;
		conceptoNominaEmpleadoEntity = new ConceptosNominaEmpleadosEntity();
		conceptoNominaEmpleadoEntity.setClave(conceptoNomina.getClave());
		conceptoNominaEmpleadoEntity.setConcepto(conceptoNomina.getDescripcion());
		conceptoNominaEmpleadoEntity.setIdConceptoFederales(conceptoNomina.getIdConceptoNomina());
		conceptoNominaEmpleadoEntity.setImporteExcento(excento);
		conceptoNominaEmpleadoEntity.setImporteGravado(gravado);
		conceptoNominaEmpleadoEntity.setNominaEmpleado(nominaEmpleado);
		conceptoNominaEmpleadoEntity.setTipo(conceptoNomina.getTipo());
		if (conceptoNomina.getCategoriaSAT() != null) {
			conceptoNominaEmpleadoEntity.setTipoSat(conceptoNomina.getCategoriaSAT().getClave());
		} else {

		}
		conceptosNominaEmpleadosRepository.crear(conceptoNominaEmpleadoEntity);
	}

	private void limpiarConceptos(NominaEmpleadoEntity nominaEmpleadoEntity) {
		List<ConceptosNominaEmpleadosEntity> conceptosNominaEmpleadosEntities = conceptosNominaEmpleadosRepository
				.listaConceptosNominaPorIdNominaEmpleado(nominaEmpleadoEntity.getIdNominaEmpleado().intValue());
		for (ConceptosNominaEmpleadosEntity empleadosEntity : conceptosNominaEmpleadosEntities) {
			conceptosNominaEmpleadosRepository.eliminar(empleadosEntity);
		}
	}

	private Integer contarFaltas(List<FaltaContadaDTO> faltasPeriodo, NominaEmpleadoEntity nominaEmpleadoEntity) {
		List<FaltaContadaEntity> fatasContadas = fataContadaRepository.consultarFatasContadas(nominaEmpleadoEntity);
		if (fatasContadas.isEmpty()) {
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

	private void crearNominaEmpleadoPensionAlimienticia(BeneficiarioPensionAlimienticiaDTO pension,
			NominaEmpleadoEntity nominaEmpleado, BigDecimal montoPension) {
		PensionAlimenticiaNominaEntity pensionAlimenticia = new PensionAlimenticiaNominaEntity();
		pensionAlimenticia.setConsecutivo(0);
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
		}
		return montoPension;
	}

	public List<BeneficiarioPensionAlimienticiaDTO> obtenerPensionesActivas(int idEmpleado) {

		List<PensionAlimenticiaEntity> listadoPensionAlimenticiaEntity = pensionAlimenticiaRepository
				.obtenerPensionesActivasPorIdEmpleado(idEmpleado);

		List<BeneficiarioPensionAlimienticiaDTO> listadoBeneficiariosDTO = new ArrayList<BeneficiarioPensionAlimienticiaDTO>();
		for (PensionAlimenticiaEntity pensionAlimenticiaEntity : listadoPensionAlimenticiaEntity) {

			BeneficiarioPensionAlimienticiaDTO pension = new BeneficiarioPensionAlimienticiaDTO();
			pension.setBeneficiario(pensionAlimenticiaEntity.getBeneficiario());
			pension.setEstatus("Activo");
			TipoCoutaPensionAlimenticiaEntity tipoCouta = pensionAlimenticiaEntity.getTipoCoutaAlimenticia();
			pension.setClaveCouta(tipoCouta.getClave());
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
		return listadoBeneficiariosDTO;
	}

	public void actualizarTotalesNominaEmpleado(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_totales_nominas_empleado(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.executeUpdate();
	}

	public void actualizarTotalesPorNominaEmpleado(NominaEmpleadoDTO nominaEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_totales_nominas_por_empleado(:idNominaEmpleado) ")
				.setParameter("idNominaEmpleado", nominaEmpleado.getIdNominaEmpleado());
		query.executeUpdate();
	}

	public void validarProductoNomina(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_validar_producto_nomina(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.executeUpdate();
	}

}