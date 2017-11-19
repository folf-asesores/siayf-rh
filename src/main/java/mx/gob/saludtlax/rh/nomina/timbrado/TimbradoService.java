package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.ssl.Base64;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.AsyncResult;

import com.ibm.icu.text.SimpleDateFormat;

import mx.gob.saludtlax.rh.retenciones.DatosCertificado;
import mx.gob.saludtlax.rh.retenciones.SelloDigital;
import mx.gob.saludtlax.rh.excepciones.ImportarPaqueteNominaException;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.TimbradoCFDINominaException;
import mx.gob.saludtlax.rh.nomina.importar.cfdi.ComprobanteNominaService;
import mx.gob.saludtlax.rh.nomina.importar.cfdi.ComprobanteNominaView;
import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalEntity;
import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.DeduccionComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.DeduccionComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.HoraExtraComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.HoraExtraComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.IncapacidadComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.OtrosPagosComprobantesEntity;
import mx.gob.saludtlax.rh.persistencia.OtrosPagosComprobantesReporitory;
import mx.gob.saludtlax.rh.persistencia.IncapacidadComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.PercepcionComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.PercepcionComprobanteEntity;
import mx.gob.saludtlax.rh.sat.xml.cfdi.Comprobante;
import mx.gob.saludtlax.rh.sat.xml.cfdi.TUbicacion;
import mx.gob.saludtlax.rh.sat.xml.cfdi.TUbicacionFiscal;
import mx.gob.saludtlax.rh.sat.xml.nomina12.COrigenRecurso;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Emisor.EntidadSNCF;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.OtrosPagos;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.OtrosPagos.OtroPago;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo;
import mx.gob.saludtlax.rh.sat.xml.nomina.Nomina;
import mx.gob.saludtlax.rh.sat.xml.nomina12.CEstado;
import mx.gob.saludtlax.rh.sat.xml.nomina12.CTipoNomina;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Deducciones;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Deducciones.Deduccion;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Emisor;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Percepciones;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Percepciones.Percepcion;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12.Receptor;
import mx.gob.saludtlax.rh.util.CadenaOriginalServices;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
@Asynchronous
@LocalBean
public class TimbradoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754230445150568712L;

	@Inject
	CertificadoSelloDigitalRepository certificadoSelloDigitalRepository;
	@Inject
	CadenaOriginalServices cadenaOriginaServices;
	@Inject
	SelloDigital selloDigital;
	@Inject
	TimbradoCFDIClientRest timbradoCFDIClientRest;
	@Inject
	private PercepcionComprobanteRepository percepcionComprobanteDAO;
	@Inject
	private IncapacidadComprobanteRepository incapacidadComprobanteDAO;
	@Inject
	private DeduccionComprobanteRepository deduccionComprobanteDAO;
	@Inject
	private HoraExtraComprobanteRepository horasExtraComprobanteDAO;
	@Inject
	private OtrosPagosComprobantesReporitory otrosPagosComprobanteDAO;
	@Inject
	ComprobanteNominaService comprobanteNominaService;

	/**
	 * 
	 * @param datosCFDINomina
	 * @param pathXSLT
	 * @return
	 */

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Future<DatosCFDITimbrado> generarCFDI(ComprobanteEntity comprobatenEntity) {

		DatosCFDINomina datosCFDINomina = toDatosCFDINomina(comprobatenEntity);

		Comprobante cfdiNomina = new Comprobante();

		Comprobante.Emisor datosSalud = new Comprobante.Emisor();
		Comprobante.Emisor.RegimenFiscal regimen = new Comprobante.Emisor.RegimenFiscal();
		TUbicacionFiscal domicilioSalud = new TUbicacionFiscal();

		Comprobante.Receptor empleado = new Comprobante.Receptor();
		TUbicacion domicilioEmpleado = new TUbicacion();

		// Informacion EMISOR
		domicilioSalud.setCalle("IGNACIO PICAZO NORTE");
		domicilioSalud.setCodigoPostal("90800");
		domicilioSalud.setColonia("CENTRO");
		domicilioSalud.setEstado("TLAXCALA");
		domicilioSalud.setMunicipio("SANTA ANA CHIAUTEMPAN");
		domicilioSalud.setNoExterior("25");
		domicilioSalud.setPais("MEXICO");

		regimen.setRegimen("PERSONA MORAL CON FINES NO LUCRATIVO");

		datosSalud.setRfc("STL961105HT8");
		datosSalud.setNombre("SALUD DE TLAXCALA");
		datosSalud.getRegimenFiscal().add(regimen);
		datosSalud.setDomicilioFiscal(domicilioSalud);

		// Informacion RECEPTOR
		if (datosCFDINomina.getCalle() != null && !datosCFDINomina.getCalle().isEmpty())
			domicilioEmpleado.setCalle(datosCFDINomina.getCalle());
		if (datosCFDINomina.getCodigoPostal() != null && !datosCFDINomina.getCodigoPostal().isEmpty())
			domicilioEmpleado.setCodigoPostal(datosCFDINomina.getCodigoPostal());
		if (datosCFDINomina.getColonia() != null && !datosCFDINomina.getColonia().isEmpty())
			domicilioEmpleado.setColonia(datosCFDINomina.getColonia());
		if (datosCFDINomina.getEstado() != null && !datosCFDINomina.getEstado().isEmpty())
			domicilioEmpleado.setEstado(datosCFDINomina.getEstado());
		if (datosCFDINomina.getMunicipio() != null && !datosCFDINomina.getMunicipio().isEmpty())
			domicilioEmpleado.setMunicipio(datosCFDINomina.getMunicipio());
		if (datosCFDINomina.getNoExterio() != null && !datosCFDINomina.getNoExterio().isEmpty())
			domicilioEmpleado.setNoExterior(datosCFDINomina.getNoExterio());
		if (datosCFDINomina.getNoInterior() != null && !datosCFDINomina.getNoInterior().isEmpty())
			domicilioEmpleado.setNoInterior(datosCFDINomina.getNoInterior());
		domicilioEmpleado.setPais("MEXICO");

		empleado.setDomicilio(domicilioEmpleado);
		empleado.setNombre(datosCFDINomina.getNombre());
		empleado.setRfc(datosCFDINomina.getRfc());

		Nomina complementoNomina = toNomina(datosCFDINomina);

		GregorianCalendar fechaPago = new GregorianCalendar();
		fechaPago.setTime(new Date());

		cfdiNomina.setEmisor(datosSalud);
		cfdiNomina.setReceptor(empleado);
		cfdiNomina.setTipoDeComprobante("egreso");
		cfdiNomina.setMetodoDePago(datosCFDINomina.getMetodoPago());
		if (datosCFDINomina.getNumeroCuentaPago() != null) {
			if (datosCFDINomina.getNumeroCuentaPago().length() == 4) {
				cfdiNomina.setNumCtaPago(datosCFDINomina.getNumeroCuentaPago());
			}
		}

		cfdiNomina.setMotivoDescuento("Deducciones Nomina");
		cfdiNomina.setFormaDePago("PAGO EN UNA SOLA EXHIBICION");
		cfdiNomina.setSerie(datosCFDINomina.getSerie());
		cfdiNomina.setFolio(datosCFDINomina.getFolio());
		cfdiNomina.setVersion("3.2");
		cfdiNomina.setLugarExpedicion("MEXICO");
		try {
			cfdiNomina.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaPago));
		} catch (DatatypeConfigurationException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		BigDecimal subTotalNomina = subTotal(complementoNomina);
		BigDecimal descuentoNomina = descuentos(complementoNomina);
		BigDecimal totalISRNomina = totalISRNomina(complementoNomina);
		BigDecimal total = subTotalNomina.subtract(descuentoNomina).subtract(totalISRNomina);
		cfdiNomina.setSubTotal(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setDescuento(descuentoNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setTotal(total.setScale(2, RoundingMode.HALF_UP));

		Comprobante.Conceptos.Concepto concepto = new Comprobante.Conceptos.Concepto();
		concepto.setCantidad(new BigDecimal("1"));
		concepto.setDescripcion("Pago Nomina");
		concepto.setUnidad("Servicio");
		concepto.setValorUnitario(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		concepto.setImporte(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		concepto.setNoIdentificacion("a");
		Comprobante.Conceptos conceptos = new Comprobante.Conceptos();
		conceptos.getConcepto().add(concepto);

		cfdiNomina.setConceptos(conceptos);

		Comprobante.Impuestos impuesto = new Comprobante.Impuestos();
		impuesto.setTotalImpuestosRetenidos(totalISRNomina.setScale(2, RoundingMode.HALF_UP));

		Comprobante.Impuestos.Retenciones.Retencion retencionISR = new Comprobante.Impuestos.Retenciones.Retencion();
		Comprobante.Impuestos.Retenciones retenciones = new Comprobante.Impuestos.Retenciones();
		retencionISR.setImporte(totalISRNomina.setScale(2, RoundingMode.HALF_UP));
		retencionISR.setImpuesto("ISR");
		retenciones.getRetencion().add(retencionISR);

		impuesto.setRetenciones(retenciones);

		cfdiNomina.setImpuestos(impuesto);
		Comprobante.Complemento complemento = new Comprobante.Complemento();
		complemento.getAny().add(complementoNomina);
		cfdiNomina.setComplemento(complemento);

		String cadenaOriginal;
		try {
			cadenaOriginal = cadenaOriginaServices.generar(generarXMLStream(cfdiNomina));
		} catch (TransformerConfigurationException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (IOException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (TransformerException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (JAXBException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		}
		cfdiNomina = sellarCFDI(cfdiNomina, cadenaOriginal);

		ByteArrayOutputStream xmlSellado;
		String xmlBase64;
		try {
			xmlSellado = generarXMLStream(cfdiNomina);

			xmlBase64 = new String(Base64.encodeBase64(xmlSellado.toByteArray()));
		} catch (JAXBException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		String xmlTimbrado = "";
		CFDIRespuesta cfdiRespuesta;

		try {

			cfdiRespuesta = timbradoCFDIClientRest.timbrarCFDI(xmlBase64);
			System.out.println(cfdiRespuesta.getCodigo());
			System.out.println(cfdiRespuesta.getMensaje());
			System.out.println(cfdiRespuesta.getUuid());
			if (cfdiRespuesta.getCodigo().equals("100")) {

				xmlTimbrado = new String(Base64.decodeBase64(cfdiRespuesta.getXml().getBytes()));

				try (PrintWriter out = new PrintWriter(mx.gob.saludtlax.rh.util.Configuracion.PATH_XML_TIMBRADO
						+ cfdiNomina.getReceptor().getRfc() + "-" + cfdiRespuesta.getUuid() + ".xml")) {
					out.println(xmlTimbrado);

				}
				comprobanteNominaService.tranformComprobanteToNominaTimbrado(xmlTimbrado);

			} else {
				System.out.println("Fallo " + cfdiNomina.getReceptor().getRfc());
			}

			System.out.println(xmlTimbrado);

		} catch (RESTClientException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (ImportarPaqueteNominaException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}
		DatosCFDITimbrado cfdiNominaTimbrado = new DatosCFDITimbrado();
		cfdiNominaTimbrado.setComprobante(cfdiNomina);
		cfdiNominaTimbrado.setSello(cfdiNomina.getSello());
		cfdiNominaTimbrado.setUuid(cfdiRespuesta.getUuid());
		cfdiNominaTimbrado.setXMLSellado(xmlTimbrado);
		cfdiNominaTimbrado.setCadenaOriginal(cadenaOriginal);

		return new AsyncResult<DatosCFDITimbrado>(cfdiNominaTimbrado);

	}

	/**
	 * 
	 * @param datosCFDINomina
	 * @param pathXSLT
	 * @return
	 */

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Future<DatosCFDITimbrado> generarCFDI2(ComprobanteEntity comprobatenEntity) {

		DatosCFDINomina datosCFDINomina = toDatosCFDINomina(comprobatenEntity);

		Integer idComprobante = comprobatenEntity.getIdComprobante();
		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante cfdiNomina = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante();

		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Emisor datosSalud = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Emisor();
		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Emisor.RegimenFiscal regimen = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Emisor.RegimenFiscal();
		mx.gob.saludtlax.rh.sat.xml.cfdi12.TUbicacionFiscal domicilioSalud = new mx.gob.saludtlax.rh.sat.xml.cfdi12.TUbicacionFiscal();

		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Receptor empleado = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Receptor();
		mx.gob.saludtlax.rh.sat.xml.cfdi12.TUbicacion domicilioEmpleado = new mx.gob.saludtlax.rh.sat.xml.cfdi12.TUbicacion();

		// Informacion EMISOR
		domicilioSalud.setCalle("IGNACIO PICAZO NORTE");
		domicilioSalud.setCodigoPostal("90800");
		domicilioSalud.setColonia("CENTRO");
		domicilioSalud.setEstado("TLAXCALA");
		domicilioSalud.setMunicipio("SANTA ANA CHIAUTEMPAN");
		domicilioSalud.setNoExterior("25");
		domicilioSalud.setPais("MEXICO");

		regimen.setRegimen("603");

		datosSalud.setRfc("STL961105HT8");
		datosSalud.setNombre("SALUD DE TLAXCALA");
		datosSalud.getRegimenFiscal().add(regimen);
		//datosSalud.setDomicilioFiscal(domicilioSalud);

		// Informacion RECEPTOR
		if (datosCFDINomina.getCalle() != null && !datosCFDINomina.getCalle().isEmpty())
			domicilioEmpleado.setCalle(datosCFDINomina.getCalle());
		if (datosCFDINomina.getCodigoPostal() != null && !datosCFDINomina.getCodigoPostal().isEmpty())
			domicilioEmpleado.setCodigoPostal(datosCFDINomina.getCodigoPostal());
		if (datosCFDINomina.getColonia() != null && !datosCFDINomina.getColonia().isEmpty())
			domicilioEmpleado.setColonia(datosCFDINomina.getColonia());
		if (datosCFDINomina.getEstado() != null && !datosCFDINomina.getEstado().isEmpty())
			domicilioEmpleado.setEstado(datosCFDINomina.getEstado());
		if (datosCFDINomina.getMunicipio() != null && !datosCFDINomina.getMunicipio().isEmpty())
			domicilioEmpleado.setMunicipio(datosCFDINomina.getMunicipio());
		if (datosCFDINomina.getNoExterio() != null && !datosCFDINomina.getNoExterio().isEmpty())
			domicilioEmpleado.setNoExterior(datosCFDINomina.getNoExterio());
		if (datosCFDINomina.getNoInterior() != null && !datosCFDINomina.getNoInterior().isEmpty())
			domicilioEmpleado.setNoInterior(datosCFDINomina.getNoInterior());
		domicilioEmpleado.setPais("MEXICO");

		//empleado.setDomicilio(domicilioEmpleado);
		empleado.setNombre(datosCFDINomina.getNombre());
		empleado.setRfc(datosCFDINomina.getRfc());

		Nomina12 complementoNomina = toNomina12(datosCFDINomina);

		DateTime fechaCom = new DateTime();
		
		GregorianCalendar fechaPago = new GregorianCalendar(fechaCom.getYear(),fechaCom.getMonthOfYear(),fechaCom.getDayOfMonth(),fechaCom.getHourOfDay(),fechaCom.getMinuteOfHour());
		fechaPago.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		
		//fechaPago.setTime(new Date());
		

		cfdiNomina.setEmisor(datosSalud);
		cfdiNomina.setReceptor(empleado);
		cfdiNomina.setTipoDeComprobante("egreso");
	//	cfdiNomina.setMetodoDePago(datosCFDINomina.getMetodoPago());
		cfdiNomina.setMetodoDePago("NA");
		/*if (datosCFDINomina.getNumeroCuentaPago() != null) {
			if (datosCFDINomina.getNumeroCuentaPago().length() == 4) {
				cfdiNomina.setNumCtaPago(datosCFDINomina.getNumeroCuentaPago());
			}
		}*/

		//cfdiNomina.setMotivoDescuento("Deducciones Nomina");
		cfdiNomina.setFormaDePago("En una sola exhibición");
		cfdiNomina.setSerie(datosCFDINomina.getSerie());
		cfdiNomina.setFolio(datosCFDINomina.getFolio());
		cfdiNomina.setVersion("3.2");
		cfdiNomina.setLugarExpedicion("90800");
		try {
			cfdiNomina.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())));
		} catch (DatatypeConfigurationException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		BigDecimal subTotalNomina = subTotal(complementoNomina);
		BigDecimal descuentoNomina = BigDecimal.ZERO;
		BigDecimal totalISRNomina = BigDecimal.ZERO;
		if (complementoNomina.getDeducciones() != null ) {
			descuentoNomina = complementoNomina.getTotalDeducciones();
			totalISRNomina = complementoNomina.getDeducciones().getTotalImpuestosRetenidos();
		}

		BigDecimal total = subTotalNomina.subtract(descuentoNomina);
		cfdiNomina.setSubTotal(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setDescuento(descuentoNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setTotal(total.setScale(2, RoundingMode.HALF_UP));

		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Conceptos.Concepto concepto = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Conceptos.Concepto();
		concepto.setCantidad(new BigDecimal("1"));
		concepto.setDescripcion("Pago de nómina");
		concepto.setUnidad("ACT");
		concepto.setValorUnitario(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		concepto.setImporte(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		//concepto.setNoIdentificacion("a");
		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Conceptos conceptos = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Conceptos();
		conceptos.getConcepto().add(concepto);

		cfdiNomina.setConceptos(conceptos);

		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos impuesto = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos();
		//impuesto.setTotalImpuestosRetenidos(totalISRNomina.setScale(2, RoundingMode.HALF_UP));

		//mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos.Retenciones.Retencion retencionISR = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos.Retenciones.Retencion();
		//mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos.Retenciones retenciones = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Impuestos.Retenciones();
		//retencionISR.setImporte(totalISRNomina.setScale(2, RoundingMode.HALF_UP));
		//retencionISR.setImpuesto("ISR");
		//retenciones.getRetencion().add(retencionISR);

		//impuesto.setRetenciones(retenciones);

		cfdiNomina.setImpuestos(impuesto);
		mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Complemento complemento = new mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.Complemento();
		complemento.getAny().add(complementoNomina);
		cfdiNomina.setComplemento(complemento);
		cfdiNomina.setMoneda("MXN");

		String cadenaOriginal;
		try {
			cadenaOriginal = cadenaOriginaServices.generar(generarXMLStream12(cfdiNomina));
		} catch (TransformerConfigurationException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (IOException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (TransformerException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (JAXBException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		}
		cfdiNomina = sellarCFDI(cfdiNomina, cadenaOriginal);

		ByteArrayOutputStream xmlSellado;
		String xmlBase64;
		try {
			xmlSellado = generarXMLStream12(cfdiNomina);
			
			System.out.println(xmlSellado);

			xmlBase64 = new String(Base64.encodeBase64(xmlSellado.toByteArray()));

			/*
			 * System.out.println(new
			 * String(Base64.decodeBase64(xmlBase64.getBytes())));
			 */
		} catch (JAXBException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		String xmlTimbrado = "";
		CFDIRespuesta cfdiRespuesta;

		try {

			cfdiRespuesta = timbradoCFDIClientRest.timbrarCFDI(xmlBase64);
			System.out.println(cfdiRespuesta.getCodigo());
			System.out.println(cfdiRespuesta.getMensaje());
			System.out.println(cfdiRespuesta.getUuid());
			if (cfdiRespuesta.getCodigo().equals("100")) {

				xmlTimbrado = new String(Base64.decodeBase64(cfdiRespuesta.getXml().getBytes()));

				try (PrintWriter out = new PrintWriter(mx.gob.saludtlax.rh.util.Configuracion.PATH_XML_TIMBRADO
						+ cfdiNomina.getReceptor().getRfc() + "-" + cfdiRespuesta.getUuid() + ".xml")) {
					out.println(xmlTimbrado);

				}

				comprobanteNominaService.tranformComprobanteToNominaTimbrado12(xmlTimbrado, idComprobante);

			} else {
				System.out.println("Fallo " + cfdiNomina.getReceptor().getRfc());
			}

			System.out.println(xmlTimbrado);

		} catch (RESTClientException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (ImportarPaqueteNominaException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		DatosCFDITimbrado cfdiNominaTimbrado = new DatosCFDITimbrado();
		// cfdiNominaTimbrado.setComprobante(cfdiNomina);
		cfdiNominaTimbrado.setSello(cfdiNomina.getSello());
		cfdiNominaTimbrado.setUuid(cfdiRespuesta.getUuid());
		cfdiNominaTimbrado.setXMLSellado(xmlTimbrado);
		cfdiNominaTimbrado.setCadenaOriginal(cadenaOriginal);

		return new AsyncResult<DatosCFDITimbrado>(cfdiNominaTimbrado);

	}

	public DatosCFDITimbrado generarProductoNominaCFDI(DatosCFDINomina datosCFDINomina) {

		// DatosCFDINomina datosCFDINomina =
		// toDatosCFDINomina(comprobatenEntity);

		Comprobante cfdiNomina = new Comprobante();

		Comprobante.Emisor datosSalud = new Comprobante.Emisor();
		Comprobante.Emisor.RegimenFiscal regimen = new Comprobante.Emisor.RegimenFiscal();
		TUbicacionFiscal domicilioSalud = new TUbicacionFiscal();

		Comprobante.Receptor empleado = new Comprobante.Receptor();
		TUbicacion domicilioEmpleado = new TUbicacion();

		// Informacion EMISOR
		domicilioSalud.setCalle("IGNACIO PICAZO NORTE");
		domicilioSalud.setCodigoPostal("90800");
		domicilioSalud.setColonia("CENTRO");
		domicilioSalud.setEstado("TLAXCALA");
		domicilioSalud.setMunicipio("SANTA ANA CHIAUTEMPAN");
		domicilioSalud.setNoExterior("25");
		domicilioSalud.setPais("MEXICO");

		regimen.setRegimen("PERSONA MORAL CON FINES NO LUCRATIVO");

		datosSalud.setRfc("STL961105HT8");
		datosSalud.setNombre("SALUD DE TLAXCALA");
		datosSalud.getRegimenFiscal().add(regimen);
		datosSalud.setDomicilioFiscal(domicilioSalud);

		// Informacion RECEPTOR
		if (datosCFDINomina.getCalle() != null && !datosCFDINomina.getCalle().isEmpty())
			domicilioEmpleado.setCalle(datosCFDINomina.getCalle());
		if (datosCFDINomina.getCodigoPostal() != null && !datosCFDINomina.getCodigoPostal().isEmpty())
			domicilioEmpleado.setCodigoPostal(datosCFDINomina.getCodigoPostal());
		if (datosCFDINomina.getColonia() != null && !datosCFDINomina.getColonia().isEmpty())
			domicilioEmpleado.setColonia(datosCFDINomina.getColonia());
		if (datosCFDINomina.getEstado() != null && !datosCFDINomina.getEstado().isEmpty())
			domicilioEmpleado.setEstado(datosCFDINomina.getEstado());
		if (datosCFDINomina.getMunicipio() != null && !datosCFDINomina.getMunicipio().isEmpty())
			domicilioEmpleado.setMunicipio(datosCFDINomina.getMunicipio());
		if (datosCFDINomina.getNoExterio() != null && !datosCFDINomina.getNoExterio().isEmpty())
			domicilioEmpleado.setNoExterior(datosCFDINomina.getNoExterio());
		if (datosCFDINomina.getNoInterior() != null && !datosCFDINomina.getNoInterior().isEmpty())
			domicilioEmpleado.setNoInterior(datosCFDINomina.getNoInterior());
		domicilioEmpleado.setPais("MEXICO");

		empleado.setDomicilio(domicilioEmpleado);
		empleado.setNombre(datosCFDINomina.getNombre());
		empleado.setRfc(datosCFDINomina.getRfc());

		Nomina complementoNomina = toNomina(datosCFDINomina);

		GregorianCalendar fechaPago = new GregorianCalendar();
		fechaPago.setTime(new Date());

		cfdiNomina.setEmisor(datosSalud);
		cfdiNomina.setReceptor(empleado);
		cfdiNomina.setTipoDeComprobante("egreso");
		cfdiNomina.setMetodoDePago("DEPOSITO");
		cfdiNomina.setMotivoDescuento("Deducciones Nomina");
		cfdiNomina.setFormaDePago("PAGO EN UNA SOLA EXHIBICION");
		cfdiNomina.setSerie(datosCFDINomina.getSerie());
		cfdiNomina.setFolio(datosCFDINomina.getFolio());
		cfdiNomina.setVersion("3.2");
		cfdiNomina.setLugarExpedicion("MEXICO");
		try {
			cfdiNomina.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaPago));
		} catch (DatatypeConfigurationException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		BigDecimal subTotalNomina = subTotal(complementoNomina);
		BigDecimal descuentoNomina = descuentos(complementoNomina);
		BigDecimal totalISRNomina = totalISRNomina(complementoNomina);
		BigDecimal total = subTotalNomina.subtract(descuentoNomina).subtract(totalISRNomina);
		cfdiNomina.setSubTotal(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setDescuento(descuentoNomina.setScale(2, RoundingMode.HALF_UP));
		cfdiNomina.setTotal(total.setScale(2, RoundingMode.HALF_UP));

		Comprobante.Conceptos.Concepto concepto = new Comprobante.Conceptos.Concepto();
		concepto.setCantidad(new BigDecimal("1"));
		concepto.setDescripcion("Pago Nomina");
		concepto.setUnidad("Servicio");
		concepto.setValorUnitario(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		concepto.setImporte(subTotalNomina.setScale(2, RoundingMode.HALF_UP));
		concepto.setNoIdentificacion("a");
		Comprobante.Conceptos conceptos = new Comprobante.Conceptos();
		conceptos.getConcepto().add(concepto);

		cfdiNomina.setConceptos(conceptos);

		Comprobante.Impuestos impuesto = new Comprobante.Impuestos();
		impuesto.setTotalImpuestosRetenidos(totalISRNomina.setScale(2, RoundingMode.HALF_UP));

		Comprobante.Impuestos.Retenciones.Retencion retencionISR = new Comprobante.Impuestos.Retenciones.Retencion();
		Comprobante.Impuestos.Retenciones retenciones = new Comprobante.Impuestos.Retenciones();
		retencionISR.setImporte(totalISRNomina.setScale(2, RoundingMode.HALF_UP));
		retencionISR.setImpuesto("ISR");
		retenciones.getRetencion().add(retencionISR);

		impuesto.setRetenciones(retenciones);

		cfdiNomina.setImpuestos(impuesto);
		Comprobante.Complemento complemento = new Comprobante.Complemento();
		complemento.getAny().add(complementoNomina);
		cfdiNomina.setComplemento(complemento);

		String cadenaOriginal;
		try {
			cadenaOriginal = cadenaOriginaServices.generar(generarXMLStream(cfdiNomina));
		} catch (TransformerConfigurationException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (IOException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (TransformerException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		} catch (JAXBException e1) {
			throw new TimbradoCFDINominaException(e1.getMessage());
		}
		cfdiNomina = sellarCFDI(cfdiNomina, cadenaOriginal);

		ByteArrayOutputStream xmlSellado;
		String xmlBase64;
		try {
			xmlSellado = generarXMLStream(cfdiNomina);

			xmlBase64 = new String(Base64.encodeBase64(xmlSellado.toByteArray()));
		} catch (JAXBException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		String xmlTimbrado = "";
		CFDIRespuesta cfdiRespuesta;
		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();

		try {

			cfdiRespuesta = timbradoCFDIClientRest.timbrarCFDI(xmlBase64);

			System.out.println(cfdiRespuesta.getCodigo());
			System.out.println(cfdiRespuesta.getMensaje());
			System.out.println(cfdiRespuesta.getUuid());
			if (cfdiRespuesta.getCodigo().equals("100")) {

				xmlTimbrado = new String(Base64.decodeBase64(cfdiRespuesta.getXml().getBytes()));

				try (PrintWriter out = new PrintWriter(mx.gob.saludtlax.rh.util.Configuracion.PATH_XML_TIMBRADO
						+ cfdiNomina.getReceptor().getRfc() + "-" + cfdiRespuesta.getUuid() + ".xml")) {
					out.println(xmlTimbrado);

				}
				comprobanteNominaView = comprobanteNominaService.tranformComprobanteToNominaSimple(xmlTimbrado);

			} else {
				System.out.println("Fallo " + cfdiNomina.getReceptor().getRfc());
			}

			System.out.println(xmlTimbrado);

		} catch (RESTClientException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());

		} catch (IOException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (ImportarPaqueteNominaException e) {
			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		DatosCFDITimbrado cfdiNominaTimbrado = new DatosCFDITimbrado();
		cfdiNominaTimbrado.setComprobante(cfdiNomina);
		cfdiNominaTimbrado.setSello(cfdiNomina.getSello());
		cfdiNominaTimbrado.setUuid(cfdiRespuesta.getUuid());
		cfdiNominaTimbrado.setXMLSellado(xmlTimbrado);
		cfdiNominaTimbrado.setCadenaOriginal(cadenaOriginal);
		cfdiNominaTimbrado.setIdComprobante(comprobanteNominaView.getId());

		return cfdiNominaTimbrado;

	}

	private DatosCFDINomina toDatosCFDINomina(ComprobanteEntity comprobante) {

		DatosCFDINomina datosCFDI = new DatosCFDINomina();

		Calendar cal = Calendar.getInstance();
		cal.setTime(comprobante.getFechaPago());
		Integer year = cal.get(Calendar.YEAR);

		datosCFDI.setAntiguedad(1);
		datosCFDI.setTipoRegimenEmpleado(comprobante.getTipoRegimenEmpleado());
		datosCFDI.setBanco(comprobante.getBanco());
		datosCFDI.setCalle(comprobante.getCalle());
		datosCFDI.setCodigoPostal(comprobante.getCodigoPostal());
		datosCFDI.setColonia(comprobante.getColonia());
		datosCFDI.setCurp(comprobante.getcURP());
		datosCFDI.setDepartamento(comprobante.getDepartamento());
		datosCFDI.setEstado(comprobante.getEstado());
		datosCFDI.setFechaFinalPago(comprobante.getFechaFinalPago());
		datosCFDI.setFechaInicalPago(comprobante.getFechaInicialPago());
		datosCFDI.setFechaInicioRelLaboral(comprobante.getFechaInicioRelacionLaboral());
		datosCFDI.setFechaPago(comprobante.getFechaPago());
		datosCFDI.setFolio(comprobante.getIdComprobante() + "");
		datosCFDI.setSerie(year.toString() + "B");
		datosCFDI.setMunicipio(comprobante.getMunicipio());
		datosCFDI.setNoExterio(comprobante.getNoExterior());
		datosCFDI.setNoInterior(comprobante.getNoInterior());
		datosCFDI.setNombre(comprobante.getNombre());
		datosCFDI.setNumeroDiasPagados(comprobante.getNumeroDiasPagados());
		datosCFDI.setNumeroEmpleado(comprobante.getNumeroEmpleado());
		datosCFDI.setNumeroSeguroSocial(comprobante.getNumeroSeguridadSocial());
		datosCFDI.setPais(comprobante.getPais());
		datosCFDI.setPeriocidadPago(comprobante.getPeriocidadPago());
		datosCFDI.setPuesto(comprobante.getPuesto());
		datosCFDI.setRfc(comprobante.getrFC());
		datosCFDI.setRiesgoPuesto(comprobante.getRiesgoPuesto());
		datosCFDI.setSalarioBaseCotApor(comprobante.getSalarioBaseCotizacionAport());
		datosCFDI.setSalarioDiarioIntegrado(comprobante.getSalarioDiarioIntegrado());
		datosCFDI.setTipoContrato(comprobante.getTipoContrato());
		datosCFDI.setTipoJornada(comprobante.getTipoJornada());
		datosCFDI.setNumeroEmpleado(comprobante.getrFC());
		datosCFDI.setTipoRegimen(1);
		datosCFDI.setMetodoPago(comprobante.getMetodoPago());
		datosCFDI.setNumeroCuentaPago(comprobante.getNumeroCuentaPago());
		datosCFDI.setTipoNomina(comprobante.getTipoNomina());
		datosCFDI.setOrigenRecursos(comprobante.getOrigenRecurso());
		datosCFDI.setMontoRecursosPropios(comprobante.getMontoRecursoPropio());

		List<OtrosPagosComprobantesEntity> listadoOtrosPagosEntity = otrosPagosComprobanteDAO
				.obtenerOtrosPagosPorIdComprobante(comprobante.getIdComprobante());

		if (listadoOtrosPagosEntity.size() > 0) {
			List<OtroPagoCFDI> listadoOtrosPagosCFDI = new ArrayList<OtroPagoCFDI>();
			for (OtrosPagosComprobantesEntity otroPagoComprobanteEntity : listadoOtrosPagosEntity) {
				OtroPagoCFDI otroPagoCFDI = new OtroPagoCFDI();
				otroPagoCFDI.setClave(otroPagoComprobanteEntity.getClave());
				otroPagoCFDI.setConcepto(otroPagoComprobanteEntity.getConcepto());
				otroPagoCFDI.setImporte(otroPagoComprobanteEntity.getImporte());
				otroPagoCFDI.setTipo(otroPagoComprobanteEntity.getClaveSat());
				listadoOtrosPagosCFDI.add(otroPagoCFDI);

			}
			datosCFDI.setOtrosPagosCFDI(listadoOtrosPagosCFDI);
		}

		List<PercepcionCFDI> listadoPercepciones = new ArrayList<PercepcionCFDI>();

		PercepcionCFDI percepcionCFDI = null;

		// Obtenemos las percepciones del empleado
		List<PercepcionComprobanteEntity> listadoPercepcionesComprobanteEntity = percepcionComprobanteDAO
				.obtenerPercepcionesPorIdComprobante(comprobante.getIdComprobante());

		for (PercepcionComprobanteEntity percepcion : listadoPercepcionesComprobanteEntity) {
			percepcionCFDI = new PercepcionCFDI();
			percepcionCFDI.setClave(percepcion.getClave());
			percepcionCFDI.setConcepto(percepcion.getConcepto());
			percepcionCFDI.setImporteExcento(percepcion.getImporteExento());
			percepcionCFDI.setImporteGravado(percepcion.getImporteGravado());
			percepcionCFDI.setTipoPercepcion(percepcion.getTipoPercepcion());
			listadoPercepciones.add(percepcionCFDI);
		}

		// En caso que no tenga nada la lista no se envian percepciones.
		if (listadoPercepciones.size() > 0) {
			datosCFDI.setPercepciones(listadoPercepciones);
		}

		// Ahora trabajamos con las deducciones
		List<DeduccionCFDI> listadoDeduccion = new ArrayList<DeduccionCFDI>();
		DeduccionCFDI deduccionCFDI = null;

		List<DeduccionComprobanteEntity> listadoDeduccionEntity = deduccionComprobanteDAO
				.obtenerPercepcionesPorIdComprobante(comprobante.getIdComprobante());

		for (DeduccionComprobanteEntity deduccion : listadoDeduccionEntity) {
			deduccionCFDI = new DeduccionCFDI();
			deduccionCFDI.setClave(deduccion.getClave());
			deduccionCFDI.setConcepto(deduccion.getConcepto());
			deduccionCFDI.setImporteExcento(deduccion.getImporteExcento());
			deduccionCFDI.setImporteGravado(deduccion.getImporteGravado());
			deduccionCFDI.setTipoPercepcion(deduccion.getTipoDeduccion());
			listadoDeduccion.add(deduccionCFDI);
		}

		// Al igual que en el caso anterior primero verificamos si tiene
		// deducciones antes de agregarlas
		if (listadoDeduccion.size() > 0) {
			datosCFDI.setDeducciones(listadoDeduccion);
		}

		List<HorasExtraCFDI> listadoHorasExtra = new ArrayList<HorasExtraCFDI>();
		List<HoraExtraComprobanteEntity> listadoHorarExtraEntity = horasExtraComprobanteDAO
				.obtenerPercepcionesPorIdComprobante(comprobante.getIdComprobante());
		for (HoraExtraComprobanteEntity horaExtraComprobanteEntity : listadoHorarExtraEntity) {

			HorasExtraCFDI horaExtra = new HorasExtraCFDI();
			horaExtra.setDias(horaExtraComprobanteEntity.getDia());
			horaExtra.setHorasExtra(horaExtraComprobanteEntity.getHoraExtra());
			horaExtra.setImportePagado(horaExtraComprobanteEntity.getImportePagado());
			horaExtra.setTipoHoras(horaExtraComprobanteEntity.getTipoHora());
			listadoHorasExtra.add(horaExtra);

		}
		if (listadoHorasExtra.size() > 0) {
			datosCFDI.setHorasExtra(listadoHorasExtra);
		}

		List<IncapacidadCFDI> listadoIncapacidad = new ArrayList<IncapacidadCFDI>();
		List<IncapacidadComprobanteEntity> listadoIncapacidadEntity = new ArrayList<IncapacidadComprobanteEntity>();

		for (IncapacidadComprobanteEntity incapacidadComprobanteEntity : listadoIncapacidadEntity) {
			IncapacidadCFDI incapacidadCFDI = new IncapacidadCFDI();
			incapacidadCFDI.setDescuento(incapacidadComprobanteEntity.getDescuento());
			incapacidadCFDI.setDiasIncapacidad(incapacidadComprobanteEntity.getDiaIncapacidad());
			incapacidadCFDI.setTipoIncapacidad(incapacidadComprobanteEntity.getTipoIncapacidad());
			listadoIncapacidad.add(incapacidadCFDI);
		}

		if (listadoIncapacidad.size() > 0) {
			datosCFDI.setIncapacidadCFDI(listadoIncapacidad);
		}
		return datosCFDI;

	}

	public void cancelarCFDI() {
		CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = certificadoSelloDigitalRepository
				.obtenerCertificadoSelloDigitalActivo();
		InputStream certificadoDigital = new ByteArrayInputStream(certificadoSelloDigitalEntity.getCertificado());
		InputStream llaveCertificado = new ByteArrayInputStream(certificadoSelloDigitalEntity.getArchivoKey());
		try {
			byte[] llaveCertificadoByte = IOUtils.toByteArray(llaveCertificado);
			byte[] certificadoDigitalByte = IOUtils.toByteArray(certificadoDigital);
			byte[] laveBase64Byte = org.apache.commons.codec.binary.Base64.encodeBase64(llaveCertificadoByte);
			byte[] certificadoDigital64Byte = org.apache.commons.codec.binary.Base64
					.encodeBase64(certificadoDigitalByte);

			String llaveBase64 = new String(laveBase64Byte);
			String CertificadoBase64 = new String(certificadoDigital64Byte);
		} catch (IOException e) {
			 
			e.printStackTrace();
		}

	}

	private mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante sellarCFDI(
			mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante cfdiNomina, String cadenaOriginal) {

		try {
			CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = certificadoSelloDigitalRepository
					.obtenerCertificadoSelloDigitalActivo();
			InputStream certificadoDigital = new ByteArrayInputStream(certificadoSelloDigitalEntity.getCertificado());
			InputStream llaveCertificado = new ByteArrayInputStream(certificadoSelloDigitalEntity.getArchivoKey());
			DatosCertificado datosCertificado = getDatosCertificado(certificadoDigital);

			cfdiNomina.setNoCertificado(datosCertificado.getNumeroCertficiado());
			cfdiNomina.setCertificado(datosCertificado.getInformacionBase64());

			String selloDigitalCadena = selloDigital.generarSello(llaveCertificado,
					certificadoSelloDigitalEntity.getClave(), cadenaOriginal);

			cfdiNomina.setSello(selloDigitalCadena);
			;

		} catch (CertificateException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (GeneralSecurityException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		return cfdiNomina;

	}

	private Comprobante sellarCFDI(Comprobante cfdiNomina, String cadenaOriginal) {

		try {
			CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = certificadoSelloDigitalRepository
					.obtenerCertificadoSelloDigitalActivo();
			InputStream certificadoDigital = new ByteArrayInputStream(certificadoSelloDigitalEntity.getCertificado());
			InputStream llaveCertificado = new ByteArrayInputStream(certificadoSelloDigitalEntity.getArchivoKey());
			DatosCertificado datosCertificado = getDatosCertificado(certificadoDigital);

			cfdiNomina.setNoCertificado(datosCertificado.getNumeroCertficiado());
			cfdiNomina.setCertificado(datosCertificado.getInformacionBase64());

			String selloDigitalCadena = selloDigital.generarSello(llaveCertificado,
					certificadoSelloDigitalEntity.getClave(), cadenaOriginal);

			cfdiNomina.setSello(selloDigitalCadena);
			;

		} catch (CertificateException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		} catch (GeneralSecurityException e) {

			e.printStackTrace();
			throw new TimbradoCFDINominaException(e.getMessage());
		}

		return cfdiNomina;

	}

	private ByteArrayOutputStream generarXMLStream(Comprobante comprobante) throws JAXBException {
		ByteArrayOutputStream resultadoMarshall = new ByteArrayOutputStream();

		JAXBContext jaxbContext;

		jaxbContext = JAXBContext.newInstance("mx.gob.saludtlax.rh.sat.xml.cfdi");

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd http://www.sat.gob.mx/nomina http://www.sat.gob.mx/sitio_internet/cfd/nomina/nomina11.xsd");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(comprobante, resultadoMarshall);
		return resultadoMarshall;

	}

	private ByteArrayOutputStream generarXMLStream12(mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante comprobante)
			throws JAXBException {
		ByteArrayOutputStream resultadoMarshall = new ByteArrayOutputStream();

		JAXBContext jaxbContext;

		jaxbContext = JAXBContext.newInstance("mx.gob.saludtlax.rh.sat.xml.cfdi12");

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd http://www.sat.gob.mx/nomina12 http://www.sat.gob.mx/sitio_internet/cfd/nomina/nomina12.xsd");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(comprobante, resultadoMarshall);
		return resultadoMarshall;

	}

	private DatosCertificado getDatosCertificado(InputStream x509Certificate) throws CertificateException, IOException {
		DatosCertificado datosCertificado = new DatosCertificado();
		X509Certificate certificado = getX509Certificate(x509Certificate);
		datosCertificado.setNumeroCertficiado(getNoCertificado(certificado));
		datosCertificado.setInformacionBase64(getCertificadoBase64(certificado));
		return datosCertificado;

	}

	private X509Certificate getX509Certificate(InputStream x509Certificate) throws CertificateException, IOException {

		InputStream file = x509Certificate;
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			return (X509Certificate) cf.generateCertificate(file);

		} finally {
			if (file != null) {
				file.close();
			}
		}

	}

	private String getNoCertificado(X509Certificate cert) {

		BigInteger serial = cert.getSerialNumber();
		byte[] sArr = serial.toByteArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < sArr.length; i++) {
			buffer.append((char) sArr[i]);
		}
		return buffer.toString();
	}

	private String getCertificadoBase64(X509Certificate cert) throws CertificateEncodingException {
		return new String(Base64.encodeBase64(cert.getEncoded()));
	}

	private BigDecimal totalISRNomina(Nomina complementoNomina) {
		BigDecimal totalISR = new BigDecimal("0");

		if (complementoNomina.getDeducciones() != null) {
			for (Nomina.Deducciones.Deduccion deduccion : complementoNomina.getDeducciones().getDeduccion()) {

				if (deduccion.getTipoDeduccion().equals("002")) {

					if (deduccion.getImporteGravado() != null) {
						totalISR = totalISR.add(deduccion.getImporteGravado());
					}
					if (deduccion.getImporteExento() != null) {
						totalISR = totalISR.add(deduccion.getImporteExento());
					}
				}
			}
		}
		return totalISR;
	}

	private BigDecimal subTotal(Nomina complementoNomina) {

		BigDecimal subTotal = new BigDecimal("0");
		if (complementoNomina.getPercepciones().getTotalExento() != null) {
			subTotal = subTotal.add(complementoNomina.getPercepciones().getTotalExento());
		}

		if (complementoNomina.getPercepciones().getTotalGravado() != null) {
			subTotal = subTotal.add(complementoNomina.getPercepciones().getTotalGravado());
		}

		if (complementoNomina.getHorasExtras() != null) {
			for (Nomina.HorasExtras.HorasExtra horaExtra : complementoNomina.getHorasExtras().getHorasExtra()) {

				if (horaExtra.getImportePagado() != null) {
					subTotal = subTotal.add(horaExtra.getImportePagado());
				}

			}
		}

		return subTotal;

	}

	private BigDecimal subTotal(Nomina12 complementoNomina) {

		BigDecimal subTotal = new BigDecimal("0");
		/*if (complementoNomina.getPercepciones().getTotalExento() != null) {
			subTotal = subTotal.add(complementoNomina.getPercepciones().getTotalExento());
		}

		if (complementoNomina.getPercepciones().getTotalGravado() != null) {
			subTotal = subTotal.add(complementoNomina.getPercepciones().getTotalGravado());
		}*/
		subTotal = complementoNomina.getTotalPercepciones();
		
		if(complementoNomina.getOtrosPagos() != null){
			subTotal = subTotal.add(complementoNomina.getTotalOtrosPagos());
		}

		return subTotal;

	}

	private BigDecimal descuentos(Nomina complementoNomina) {

		BigDecimal totalDescuentos = new BigDecimal("0");

		if (complementoNomina.getDeducciones() != null) {
			for (Nomina.Deducciones.Deduccion deduccion : complementoNomina.getDeducciones().getDeduccion()) {

				if (!deduccion.getTipoDeduccion().equals("002")) {

					if (deduccion.getImporteGravado() != null) {
						totalDescuentos = totalDescuentos.add(deduccion.getImporteGravado());
					}
					if (deduccion.getImporteExento() != null) {
						totalDescuentos = totalDescuentos.add(deduccion.getImporteExento());
					}
				}
			}
		}

		if (complementoNomina.getIncapacidades() != null) {
			for (Nomina.Incapacidades.Incapacidad incapacidad : complementoNomina.getIncapacidades().getIncapacidad()) {
				if (incapacidad.getDescuento() != null) {
					totalDescuentos = totalDescuentos.add(incapacidad.getDescuento());
				}
			}
		}

		return totalDescuentos;

	}

	private Nomina12 toNomina12(DatosCFDINomina datosCFDINomina) {
		Nomina12 complementoNomina12 = new Nomina12();
		Emisor emisor = new Emisor();
		emisor.setRfcPatronOrigen("STL961105HT8");

		Receptor receptor = new Receptor();
		// receptor.setAntiguedad("P" + new
		// Integer(datosCFDINomina.getAntiguedad()).toString() + "Y1M1D");
		receptor.setBanco(datosCFDINomina.getBanco());

		if (datosCFDINomina.getSalarioBaseCotApor() != null) {
			receptor.setSalarioBaseCotApor(datosCFDINomina.getSalarioBaseCotApor());
		}

		receptor.setClaveEntFed(CEstado.TLA);
		if (datosCFDINomina.getCurp() != null) {
			if (datosCFDINomina.getCurp().length() > 0) {
				receptor.setCurp(datosCFDINomina.getCurp());

			}
		}

		if (datosCFDINomina.getDepartamento() != null) {
			receptor.setDepartamento(datosCFDINomina.getDepartamento());
		}

		GregorianCalendar fechaInicioRelacionLaboral = new GregorianCalendar();

		if (datosCFDINomina.getFechaInicioRelLaboral() != null) {
			fechaInicioRelacionLaboral.setTime(datosCFDINomina.getFechaInicioRelLaboral());
			try {
				int anyo = fechaInicioRelacionLaboral.get(Calendar.YEAR);
				int mes = fechaInicioRelacionLaboral.get(Calendar.MONTH) + 1;
				int dia = fechaInicioRelacionLaboral.get(Calendar.DAY_OF_MONTH);

				receptor.setFechaInicioRelLaboral(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(anyo, mes,
						dia, DatatypeConstants.FIELD_UNDEFINED));
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
		}

		EntidadSNCF entidadSNFC = new EntidadSNCF();

		entidadSNFC.setOrigenRecurso(COrigenRecurso.valueOf(datosCFDINomina.getOrigenRecursos()));
		entidadSNFC.setMontoRecursoPropio(datosCFDINomina.getMontoRecursosPropios());

		emisor.setEntidadSNCF(entidadSNFC);

		receptor.setNumEmpleado(datosCFDINomina.getNumeroEmpleado());
		if (datosCFDINomina.getNumeroSeguroSocial() != null) {
			if (datosCFDINomina.getNumeroSeguroSocial().length() > 0) {
				receptor.setNumSeguridadSocial(datosCFDINomina.getNumeroSeguroSocial());
			}
		}

		if (datosCFDINomina.getPeriocidadPago() != null) {
			receptor.setPeriodicidadPago(datosCFDINomina.getPeriocidadPago());
		}
		if (datosCFDINomina.getPuesto() != null) {
			receptor.setPuesto(datosCFDINomina.getPuesto());
		}
		if (datosCFDINomina.getRiesgoPuesto() != null) {
			receptor.setRiesgoPuesto(datosCFDINomina.getRiesgoPuesto().toString());
		}

		if (datosCFDINomina.getSalarioBaseCotApor() != null) {
			receptor.setSalarioBaseCotApor(datosCFDINomina.getSalarioBaseCotApor());
		}

		if (datosCFDINomina.getSalarioDiarioIntegrado() != null) {
			receptor.setSalarioDiarioIntegrado(datosCFDINomina.getSalarioDiarioIntegrado());
		}

		if (datosCFDINomina.getTipoContrato() != null) {
			receptor.setTipoContrato(datosCFDINomina.getTipoContrato());
		}

		if (datosCFDINomina.getTipoJornada() != null) {
			receptor.setTipoJornada(datosCFDINomina.getTipoJornada());
		}

		receptor.setTipoRegimen(datosCFDINomina.getTipoRegimenEmpleado());

		complementoNomina12.setReceptor(receptor);
		complementoNomina12.setEmisor(emisor);

		BigDecimal totalImpuestosRetenidos = new BigDecimal("0");
		BigDecimal totalOtrasDeducciones = new BigDecimal("0");
		BigDecimal totalDeduciones = new BigDecimal("0");

		if (datosCFDINomina.getDeducciones() != null) {
			Deducciones deducciones = new Deducciones();

			for (DeduccionCFDI deduccionCFDI : datosCFDINomina.getDeducciones()) {
				Deduccion deduccion = new Deduccion();
				deduccion.setClave(deduccionCFDI.getClave());
				deduccion.setConcepto(deduccionCFDI.getConcepto());
				deduccion.setImporte(deduccionCFDI.getImporteExcento().add(deduccionCFDI.getImporteGravado()));
				deduccion.setTipoDeduccion(deduccionCFDI.getTipoPercepcion());
				deducciones.getDeduccion().add(deduccion);
				if (deduccionCFDI.getTipoPercepcion().equals("002")) {
					totalImpuestosRetenidos = totalImpuestosRetenidos.add(deduccionCFDI.getImporteGravado());
					deducciones.setTotalImpuestosRetenidos(totalImpuestosRetenidos);
				} else {
					totalOtrasDeducciones = totalOtrasDeducciones.add(deduccionCFDI.getImporteGravado());
					deducciones.setTotalOtrasDeducciones(totalOtrasDeducciones);			
				}
				totalDeduciones = totalDeduciones.add(deduccionCFDI.getImporteExcento().add(deduccionCFDI.getImporteGravado()));

			}
			
			
			
			complementoNomina12.setDeducciones(deducciones);
			complementoNomina12.setTotalDeducciones(totalDeduciones);
		}

		try {
			GregorianCalendar fechaFinalPago = new GregorianCalendar();
			fechaFinalPago.setTime(datosCFDINomina.getFechaFinalPago());
			GregorianCalendar fechaInicialPago = new GregorianCalendar();
			fechaInicialPago.setTime(datosCFDINomina.getFechaInicalPago());
			GregorianCalendar fechaPago = new GregorianCalendar();
			fechaPago.setTime(datosCFDINomina.getFechaPago());
			complementoNomina12.setFechaFinalPago(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
					fechaFinalPago.get(Calendar.YEAR), fechaFinalPago.get(Calendar.MONTH) + 1,
					fechaFinalPago.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED));
			complementoNomina12.setFechaInicialPago(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
					fechaInicialPago.get(Calendar.YEAR), fechaInicialPago.get(Calendar.MONTH) + 1,
					fechaInicialPago.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED));
			complementoNomina12.setFechaPago(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
					fechaPago.get(Calendar.YEAR), fechaPago.get(Calendar.MONTH) + 1,
					fechaPago.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED));

		} catch (DatatypeConfigurationException e) {
		}
		complementoNomina12.setNumDiasPagados(datosCFDINomina.getNumeroDiasPagados().setScale(3, RoundingMode.UP));

		BigDecimal totalGravado = BigDecimal.ZERO;
		BigDecimal totalExcento = BigDecimal.ZERO;

		Percepciones percepciones = new Percepciones();
		for (PercepcionCFDI percepcionCFDI : datosCFDINomina.getPercepciones()) {

			Percepcion percepcion = new Percepcion();
			percepcion.setClave(percepcionCFDI.getClave());
			percepcion.setConcepto(percepcionCFDI.getConcepto());
			percepcion.setImporteExento(percepcionCFDI.getImporteExcento().setScale(2, RoundingMode.HALF_UP));
			percepcion.setImporteGravado(percepcionCFDI.getImporteGravado().setScale(2, RoundingMode.HALF_UP));
			percepcion.setTipoPercepcion(percepcionCFDI.getTipoPercepcion());
			percepciones.getPercepcion().add(percepcion);
			totalGravado = totalGravado.add(percepcionCFDI.getImporteGravado());
			totalExcento = totalExcento.add(percepcionCFDI.getImporteExcento());

		}

		if (datosCFDINomina.getOtrosPagosCFDI() != null) {
			BigDecimal totalOtrosPagos = BigDecimal.ZERO;
			OtrosPagos otrosPagos = new OtrosPagos();
			for (OtroPagoCFDI otrosPagosCFDI : datosCFDINomina.getOtrosPagosCFDI()) {
				OtroPago otroPago = new OtroPago();
				otroPago.setClave(otrosPagosCFDI.getClave());
				otroPago.setConcepto(otrosPagosCFDI.getConcepto());
				otroPago.setImporte(otrosPagosCFDI.getImporte());
				otroPago.setTipoOtroPago(otrosPagosCFDI.getTipo());
				SubsidioAlEmpleo subsidioAlEmpleo = new SubsidioAlEmpleo();
				subsidioAlEmpleo.setSubsidioCausado(otrosPagosCFDI.getImporte());
				otroPago.setSubsidioAlEmpleo(subsidioAlEmpleo);
				totalOtrosPagos = totalOtrosPagos.add(otrosPagosCFDI.getImporte());
				otrosPagos.getOtroPago().add(otroPago);
			}			
			complementoNomina12.setOtrosPagos(otrosPagos);
			complementoNomina12.setTotalOtrosPagos(totalOtrosPagos);
		}

		percepciones.setTotalExento(totalExcento);
		percepciones.setTotalGravado(totalGravado);
		percepciones.setTotalSueldos(totalExcento.add(totalGravado));
		
		complementoNomina12.setPercepciones(percepciones);
//		complementoNomina12.setTotalOtrosPagos(to);
		complementoNomina12.setTipoNomina(CTipoNomina.valueOf(datosCFDINomina.getTipoNomina()));
		
		complementoNomina12.setTotalPercepciones(totalExcento.add(totalGravado));
		complementoNomina12.setVersion("1.2");

		return complementoNomina12;
	}

	private Nomina toNomina(DatosCFDINomina datosCFDINomina) {

		Nomina complementoNomina = new Nomina();
		try {
			complementoNomina.setAntiguedad(datosCFDINomina.getAntiguedad());
			complementoNomina.setBanco(datosCFDINomina.getBanco());
			complementoNomina.setCURP(datosCFDINomina.getCurp());
			if (datosCFDINomina.getDepartamento() != null) {
				complementoNomina.setDepartamento(datosCFDINomina.getDepartamento());
			}

			GregorianCalendar fechaFinalPago = new GregorianCalendar();
			fechaFinalPago.setTime(datosCFDINomina.getFechaFinalPago());
			GregorianCalendar fechaInicialPago = new GregorianCalendar();
			fechaInicialPago.setTime(datosCFDINomina.getFechaInicalPago());
			GregorianCalendar fechaInicioRelacionLaboral = new GregorianCalendar();
			if (datosCFDINomina.getFechaInicioRelLaboral() != null)
				fechaInicioRelacionLaboral.setTime(datosCFDINomina.getFechaInicioRelLaboral());
			GregorianCalendar fechaPago = new GregorianCalendar();
			fechaPago.setTime(datosCFDINomina.getFechaPago());

			complementoNomina.setFechaFinalPago(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaFinalPago));
			complementoNomina
					.setFechaInicialPago(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaInicialPago));

			if (datosCFDINomina.getFechaInicioRelLaboral() != null)
				complementoNomina.setFechaInicioRelLaboral(
						DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaInicioRelacionLaboral));

			complementoNomina.setFechaPago(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaPago));
			complementoNomina.setNumDiasPagados(datosCFDINomina.getNumeroDiasPagados());
			if (datosCFDINomina.getNumeroEmpleado() != null && !datosCFDINomina.getNumeroEmpleado().isEmpty())
				complementoNomina.setNumEmpleado(datosCFDINomina.getNumeroEmpleado());
			if (datosCFDINomina.getNumeroSeguroSocial() != null && !datosCFDINomina.getNumeroSeguroSocial().isEmpty())
				complementoNomina.setNumSeguridadSocial(datosCFDINomina.getNumeroSeguroSocial());

			complementoNomina.setPeriodicidadPago(datosCFDINomina.getPeriocidadPago());

			complementoNomina.setPuesto(datosCFDINomina.getPuesto());

			complementoNomina.setRegistroPatronal("XXXXXXXXX");
			complementoNomina.setRiesgoPuesto(datosCFDINomina.getRiesgoPuesto());
			if (datosCFDINomina.getSalarioBaseCotApor() != null)
				complementoNomina.setSalarioBaseCotApor(
						datosCFDINomina.getSalarioBaseCotApor().setScale(2, RoundingMode.HALF_UP));
			if (datosCFDINomina.getSalarioDiarioIntegrado() != null)
				complementoNomina.setSalarioDiarioIntegrado(
						datosCFDINomina.getSalarioDiarioIntegrado().setScale(2, RoundingMode.HALF_UP));
			complementoNomina.setTipoContrato(datosCFDINomina.getTipoContrato());
			complementoNomina.setTipoJornada(datosCFDINomina.getTipoJornada());
			complementoNomina.setTipoRegimen(datosCFDINomina.getTipoRegimen());
			complementoNomina.setVersion("1.1");

			Nomina.Percepciones percepciones = new Nomina.Percepciones();

			// Armamos percepciones
			BigDecimal totalGravado = new BigDecimal("0");
			BigDecimal totalExcento = new BigDecimal("0");
			for (PercepcionCFDI percepcionCFDI : datosCFDINomina.getPercepciones()) {

				Nomina.Percepciones.Percepcion percepcion = new Nomina.Percepciones.Percepcion();
				percepcion.setClave(percepcionCFDI.getClave());
				percepcion.setConcepto(percepcionCFDI.getConcepto());
				percepcion.setImporteExento(percepcionCFDI.getImporteExcento().setScale(2, RoundingMode.HALF_UP));
				percepcion.setImporteGravado(percepcionCFDI.getImporteGravado().setScale(2, RoundingMode.HALF_UP));
				percepcion.setTipoPercepcion(percepcionCFDI.getTipoPercepcion());

				percepciones.getPercepcion().add(percepcion);
				if (percepcionCFDI.getImporteGravado() != null) {
					totalGravado = totalGravado.add(percepcionCFDI.getImporteGravado());
				}
				if (percepcionCFDI.getImporteExcento() != null) {
					totalExcento = totalExcento.add(percepcionCFDI.getImporteExcento());
				}

			}
			percepciones.setTotalExento(totalExcento.setScale(2, RoundingMode.HALF_UP));
			percepciones.setTotalGravado(totalGravado.setScale(2, RoundingMode.HALF_UP));

			complementoNomina.setPercepciones(percepciones);

			Nomina.Deducciones deducciones = new Nomina.Deducciones();
			totalGravado = new BigDecimal("0");
			totalExcento = new BigDecimal("0");

			// Armamos deducciones
			if (datosCFDINomina.getDeducciones() != null) {

				for (DeduccionCFDI deduccionCFDI : datosCFDINomina.getDeducciones()) {
					Nomina.Deducciones.Deduccion deduccion = new Nomina.Deducciones.Deduccion();
					deduccion.setClave(deduccionCFDI.getClave());
					deduccion.setConcepto(deduccionCFDI.getConcepto());
					deduccion.setImporteExento(deduccionCFDI.getImporteExcento().setScale(2, RoundingMode.HALF_UP));
					deduccion.setImporteGravado(deduccionCFDI.getImporteGravado().setScale(2, RoundingMode.HALF_UP));
					deduccion.setTipoDeduccion(deduccionCFDI.getTipoPercepcion());

					deducciones.getDeduccion().add(deduccion);
					if (deduccionCFDI.getImporteExcento() != null) {
						totalExcento = totalExcento.add(deduccionCFDI.getImporteExcento());
					}
					if (deduccionCFDI.getImporteGravado() != null) {
						totalGravado = totalGravado.add(deduccionCFDI.getImporteGravado());
					}
				}
				deducciones.setTotalExento(totalExcento.setScale(2, RoundingMode.HALF_UP));
				deducciones.setTotalGravado(totalGravado.setScale(2, RoundingMode.HALF_UP));
				complementoNomina.setDeducciones(deducciones);

			}

			Nomina.HorasExtras horasExtra = null;

			if (datosCFDINomina.getHorasExtra() != null && !datosCFDINomina.getHorasExtra().isEmpty()) {
				horasExtra = new Nomina.HorasExtras();
				for (HorasExtraCFDI horaExtraCFDI : datosCFDINomina.getHorasExtra()) {
					Nomina.HorasExtras.HorasExtra horaExtra = new Nomina.HorasExtras.HorasExtra();
					horaExtra.setDias(horaExtraCFDI.getDias());
					horaExtra.setHorasExtra(horaExtra.getHorasExtra());
					horaExtra.setImportePagado(horaExtraCFDI.getImportePagado().setScale(2, RoundingMode.HALF_UP));
					horaExtra.setTipoHoras(horaExtraCFDI.getTipoHoras());
					horasExtra.getHorasExtra().add(horaExtra);
				}
			}

			complementoNomina.setHorasExtras(horasExtra);

			Nomina.Incapacidades incapacidades = null;

			if (datosCFDINomina.getIncapacidadCFDI() != null && !datosCFDINomina.getIncapacidadCFDI().isEmpty()) {
				incapacidades = new Nomina.Incapacidades();
				for (IncapacidadCFDI incapacidadCFDI : datosCFDINomina.getIncapacidadCFDI()) {
					Nomina.Incapacidades.Incapacidad incapacidad = new Nomina.Incapacidades.Incapacidad();

					incapacidad.setDescuento(incapacidadCFDI.getDescuento().setScale(2, RoundingMode.HALF_UP));
					incapacidad.setDiasIncapacidad(incapacidadCFDI.getDiasIncapacidad());
					incapacidad.setTipoIncapacidad(incapacidadCFDI.getTipoIncapacidad());

					incapacidades.getIncapacidad().add(incapacidad);
				}
			}

			complementoNomina.setIncapacidades(incapacidades);

			return complementoNomina;

		} catch (DatatypeConfigurationException e) {

			e.printStackTrace();
		}
		return null;
	}

}
