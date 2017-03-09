package mx.gob.saludtlax.rh.retenciones;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.ssl.Base64;

import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalEntity;
import mx.gob.saludtlax.rh.persistencia.CertificadoSelloDigitalRepository;
import mx.gob.saludtlax.rh.persistencia.ConstanciaSueldoEntity;
import mx.gob.saludtlax.rh.sat.xml.retenciones.Retenciones;
import mx.gob.saludtlax.rh.util.CadenaOriginalServices;

@Stateless
public class RetencionesCFDIServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -998762470589081319L;

	@Inject
	CadenaOriginalServices cadenaOriginaServices;
	@Inject
	SelloDigital selloDigital;
	@Inject
	CertificadoSelloDigitalRepository certificadoSelloDigitalRepository;

	public ConstanciaSueldoEntity toComprobanteRetencionesCFDI(ConstanciaSueldoEntity _constanciaSueldoEntity)
			throws DatatypeConfigurationException, TransformerConfigurationException, IOException, TransformerException,
			JAXBException, GeneralSecurityException {

		Retenciones retencionesCFDI = new Retenciones();

		Retenciones.Emisor emisor = new Retenciones.Emisor();

		GregorianCalendar cal = new GregorianCalendar();

		emisor.setRFCEmisor("STL961105HT8");
		emisor.setNomDenRazSocE("SALUD DE TLAXCALA");

		Retenciones.Receptor receptor = new Retenciones.Receptor();
		Retenciones.Receptor.Nacional nacional = new Retenciones.Receptor.Nacional();
		nacional.setCURPR(_constanciaSueldoEntity.getCURP());
		nacional.setNomDenRazSocR(_constanciaSueldoEntity.getApellidoPaterno() + " "
				+ _constanciaSueldoEntity.getApellidoMaterno() + " " + _constanciaSueldoEntity.getNombre());
		nacional.setRFCRecep(_constanciaSueldoEntity.getRFC());
		receptor.setNacional(nacional);

		Retenciones.Periodo periodo = new Retenciones.Periodo();
		periodo.setEjerc(2015);
		periodo.setMesFin(_constanciaSueldoEntity.getMesFinal());
		periodo.setMesIni(_constanciaSueldoEntity.getMesInicial());

		Retenciones.Totales totales = new Retenciones.Totales();

		totales.setMontoTotExent(_constanciaSueldoEntity.getSumaIngresoPorSueldoExento());
		totales.setMontoTotGrav(_constanciaSueldoEntity.getSumaIngresoPorSueldoGravado());
		totales.setMontoTotOperacion(_constanciaSueldoEntity.getSumaIngresoPorSueldoGravado());
		totales.setMontoTotRet(_constanciaSueldoEntity.getImpuestoRetenidoEjercicioDeclara());

		retencionesCFDI.setEmisor(emisor);
		retencionesCFDI.setReceptor(receptor);
		retencionesCFDI.setCveRetenc(_constanciaSueldoEntity.getClaveSalarioAsimilado());
		retencionesCFDI.setDescRetenc("Servicios profesionales");
		retencionesCFDI.setFechaExp(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
		retencionesCFDI.setFolioInt(_constanciaSueldoEntity.getIdConstanciaSueldo().toString());
		retencionesCFDI.setPeriodo(periodo);
		retencionesCFDI.setSello("");
		retencionesCFDI.setTotales(totales);
		retencionesCFDI.setVersion("1.0");

		RetencionesView retencionesView = generaComprobanteRetencionesCFDI(retencionesCFDI);
		_constanciaSueldoEntity.setXml(retencionesView.getXml());
		_constanciaSueldoEntity.setCadenaOriginal(retencionesView.getCadenaOriginal());
		_constanciaSueldoEntity.setSello(retencionesView.getSello());

		return _constanciaSueldoEntity;
	}

	public RetencionesView generaComprobanteRetencionesCFDI(Retenciones retenciones) throws IOException,
			TransformerConfigurationException, TransformerException, JAXBException, GeneralSecurityException {

		CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = certificadoSelloDigitalRepository
				.obtenerCertificadoSelloDigitalActivo();

		InputStream certificadoDigital = new ByteArrayInputStream(certificadoSelloDigitalEntity.getCertificado());
		InputStream llaveCertificado = new ByteArrayInputStream(certificadoSelloDigitalEntity.getArchivoKey());

		DatosCertificado datosCertificado = getDatosCertificado(certificadoDigital);
		retenciones.setNumCert(datosCertificado.getNumeroCertficiado());
		retenciones.setCert(datosCertificado.getInformacionBase64());

		String cadenaOriginal = cadenaOriginaServices.generar(generarXMLStream(retenciones));

		String selloDigitalCadena = selloDigital.generarSello(llaveCertificado,
				certificadoSelloDigitalEntity.getClave(), cadenaOriginal);

		retenciones.setSello(selloDigitalCadena);

		ByteArrayOutputStream retencionesCFDISellado = generarXMLStream(retenciones);
		Random md = new Random();
		/*
		 * OutputStream outputStream = new FileOutputStream(
		 * "C:\\ArchivosXSLT\\tmp\\comprobanteSellado" + md.nextInt() + ".xml");
		 * retencionesCFDISellado.writeTo(outputStream);
		 */

		RetencionesView retencionesView = new RetencionesView();

		retencionesView.setXml(new String(retencionesCFDISellado.toByteArray()));
		retencionesView.setCadenaOriginal(cadenaOriginal);
		retencionesView.setSello(selloDigitalCadena);

		return retencionesView;

	}

	public ByteArrayOutputStream generarXMLStream(Retenciones retenciones) throws JAXBException {
		ByteArrayOutputStream resultadoMarshall = new ByteArrayOutputStream();

		JAXBContext jaxbContext;

		jaxbContext = JAXBContext.newInstance("mx.gob.saludtlax.rh.sat.xml.retenciones");

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				"http://www.sat.gob.mx/esquemas/retencionpago/1 http://www.sat.gob.mx/esquemas/retencionpago/1/retencionpagov1.xsd");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(retenciones, resultadoMarshall);
		return resultadoMarshall;

	}

	public DatosCertificado getDatosCertificado(InputStream x509Certificate) throws CertificateException, IOException {
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

	private String getCertificadoBase64(X509Certificate cert) throws CertificateEncodingException {
		return new String(Base64.encodeBase64(cert.getEncoded()));
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

}
