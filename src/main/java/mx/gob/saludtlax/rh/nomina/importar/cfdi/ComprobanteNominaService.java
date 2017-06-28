package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.Serializable;
import java.io.StringReader;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;

import mx.gob.saludtlax.rh.excepciones.ImportarPaqueteNominaException;
import mx.gob.saludtlax.rh.persistencia.ComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.DeduccionComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.DeduccionComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.HoraExtraComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.HoraExtraComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.IncapacidadComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.IncapacidadComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.PercepcionComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.PercepcionComprobanteEntity;
import mx.gob.saludtlax.rh.sat.xml.cfdi.Comprobante;
import mx.gob.saludtlax.rh.sat.xml.nomina.Nomina;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12;
import mx.gob.saludtlax.rh.sat.xml.timbre.TimbreFiscalDigital;
import mx.gob.saludtlax.rh.util.CadenaOriginalServices;

/**
 * 
 * @author Juan Carlos Ivan Ganzo Domínguez
 *
 */
@Stateless
@LocalBean

public class ComprobanteNominaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9159668199868042011L;

	@Inject
	CadenaOriginalServices cadenaOriginalService;
	@Inject
	ComprobanteRepository comprobanteDAO;
	@Inject
	PercepcionComprobanteRepository percepcionComprobanteDAO;
	@Inject
	DeduccionComprobanteRepository deduccionComprobanteDAO;
	@Inject
	HoraExtraComprobanteRepository horaExtraComprobanteDAO;
	@Inject
	IncapacidadComprobanteRepository incapacidadComprobanteDAO;

	@Asynchronous
	public Future<ComprobanteNominaView> tranformComprobanteToNomina(String comprobante, Integer idComprobanteNomina,
			Integer numComprobanteNomina) throws ImportarPaqueteNominaException {

		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();
		comprobanteNominaView.setId(idComprobanteNomina);
		comprobanteNominaView.setNum(numComprobanteNomina);
		comprobanteNominaView.setComprobanteXML(comprobante);

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader xmlNomina = new StringReader(comprobante);
			Comprobante comprobanteCFDI = (Comprobante) jaxbUnmarshaller.unmarshal(xmlNomina);

			comprobanteNominaView.setComprobanteCFDI(comprobanteCFDI);

			Object complementoTimbrado = comprobanteCFDI.getComplemento().getAny().get(1);

			Nomina cNomina = (Nomina) comprobanteCFDI.getComplemento().getAny().get(0);

			comprobanteNominaView.setComplementoNomina(cNomina);

			JAXBContext contextComplementoTimbreFiscal = JAXBContext.newInstance(TimbreFiscalDigital.class);
			Unmarshaller umTimbre = contextComplementoTimbreFiscal.createUnmarshaller();
			TimbreFiscalDigital cTimbreDigital = (TimbreFiscalDigital) umTimbre.unmarshal((Node) complementoTimbrado);

			comprobanteNominaView.setComplementoTimbre(cTimbreDigital);

			String cadenaOriginal = cadenaOriginalService.generar(comprobante);

			comprobanteNominaView.setCadenaOriginal(cadenaOriginal);

			if (!comprobanteDAO.buscarPorUUID(comprobanteNominaView.getComplementoTimbre().getUUID())) {
				guardarComprobante(comprobanteNominaView);
			}

			return new AsyncResult<ComprobanteNominaView>(comprobanteNominaView);

		} catch (JAXBException e) {

			throw new ImportarPaqueteNominaException(e.getMessage());

		} catch (TransformerException e) {
			throw new ImportarPaqueteNominaException(e.getMessage());
		}

	}

	@Asynchronous
	public Future<ComprobanteNominaView> tranformComprobanteToNomina(String comprobante)
			throws ImportarPaqueteNominaException {

		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();
		comprobanteNominaView.setId(0);
		comprobanteNominaView.setNum(0);
		comprobanteNominaView.setComprobanteXML(comprobante);

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader xmlNomina = new StringReader(comprobante);
			Comprobante comprobanteCFDI = (Comprobante) jaxbUnmarshaller.unmarshal(xmlNomina);

			comprobanteNominaView.setComprobanteCFDI(comprobanteCFDI);

			Object complementoTimbrado = comprobanteCFDI.getComplemento().getAny().get(0);

			Nomina cNomina = (Nomina) comprobanteCFDI.getComplemento().getAny().get(1);

			comprobanteNominaView.setComplementoNomina(cNomina);

			JAXBContext contextComplementoTimbreFiscal = JAXBContext.newInstance(TimbreFiscalDigital.class);
			Unmarshaller umTimbre = contextComplementoTimbreFiscal.createUnmarshaller();
			TimbreFiscalDigital cTimbreDigital = (TimbreFiscalDigital) umTimbre.unmarshal((Node) complementoTimbrado);

			comprobanteNominaView.setComplementoTimbre(cTimbreDigital);

			String cadenaOriginal = cadenaOriginalService.generar(comprobante);

			comprobanteNominaView.setCadenaOriginal(cadenaOriginal);

			if (!comprobanteDAO.buscarPorUUID(comprobanteNominaView.getComplementoTimbre().getUUID())) {
				guardarComprobante(comprobanteNominaView);
			}

			return new AsyncResult<ComprobanteNominaView>(comprobanteNominaView);

		} catch (JAXBException e) {

			throw new ImportarPaqueteNominaException(e.getMessage());

		} catch (TransformerException e) {
			throw new ImportarPaqueteNominaException(e.getMessage());
		}

	}

	public ComprobanteNominaView tranformComprobanteToNominaSimple(String comprobante)
			throws ImportarPaqueteNominaException {

		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();
		comprobanteNominaView.setId(0);
		comprobanteNominaView.setNum(0);
		comprobanteNominaView.setComprobanteXML(comprobante);

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader xmlNomina = new StringReader(comprobante);
			Comprobante comprobanteCFDI = (Comprobante) jaxbUnmarshaller.unmarshal(xmlNomina);

			comprobanteNominaView.setComprobanteCFDI(comprobanteCFDI);

			Object complementoTimbrado = comprobanteCFDI.getComplemento().getAny().get(1);

			Nomina cNomina = (Nomina) comprobanteCFDI.getComplemento().getAny().get(0);

			comprobanteNominaView.setComplementoNomina(cNomina);

			JAXBContext contextComplementoTimbreFiscal = JAXBContext.newInstance(TimbreFiscalDigital.class);
			Unmarshaller umTimbre = contextComplementoTimbreFiscal.createUnmarshaller();
			TimbreFiscalDigital cTimbreDigital = (TimbreFiscalDigital) umTimbre.unmarshal((Node) complementoTimbrado);

			comprobanteNominaView.setComplementoTimbre(cTimbreDigital);

			String cadenaOriginal = cadenaOriginalService.generar(comprobante);

			comprobanteNominaView.setCadenaOriginal(cadenaOriginal);

			if (!comprobanteDAO.buscarPorUUID(comprobanteNominaView.getComplementoTimbre().getUUID())) {
				comprobanteNominaView.setId(guardarComprobanteProducto(comprobanteNominaView));
			}

			return comprobanteNominaView;

		} catch (JAXBException e) {

			throw new ImportarPaqueteNominaException(e.getMessage());

		} catch (TransformerException e) {
			throw new ImportarPaqueteNominaException(e.getMessage());
		}

	}

	@Asynchronous
	public Future<ComprobanteNominaView> tranformComprobanteToNominaTimbrado(String comprobante)
			throws ImportarPaqueteNominaException {

		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();
		comprobanteNominaView.setId(0);
		comprobanteNominaView.setNum(0);
		comprobanteNominaView.setComprobanteXML(comprobante);

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader xmlNomina = new StringReader(comprobante);
			Comprobante comprobanteCFDI = (Comprobante) jaxbUnmarshaller.unmarshal(xmlNomina);

			comprobanteNominaView.setComprobanteCFDI(comprobanteCFDI);

			Object complementoTimbrado = comprobanteCFDI.getComplemento().getAny().get(1);

			Nomina cNomina = (Nomina) comprobanteCFDI.getComplemento().getAny().get(0);

			comprobanteNominaView.setComplementoNomina(cNomina);

			JAXBContext contextComplementoTimbreFiscal = JAXBContext.newInstance(TimbreFiscalDigital.class);
			Unmarshaller umTimbre = contextComplementoTimbreFiscal.createUnmarshaller();
			TimbreFiscalDigital cTimbreDigital = (TimbreFiscalDigital) umTimbre.unmarshal((Node) complementoTimbrado);

			comprobanteNominaView.setComplementoTimbre(cTimbreDigital);

			String cadenaOriginal = cadenaOriginalService.generar(comprobante);

			comprobanteNominaView.setCadenaOriginal(cadenaOriginal);

			if (!comprobanteDAO.buscarPorUUID(comprobanteNominaView.getComplementoTimbre().getUUID())) {
				System.out.println("Guarda" + comprobanteNominaView.getComplementoTimbre().getUUID());
				actualizarComprobante(comprobanteNominaView);
			}

			return new AsyncResult<ComprobanteNominaView>(comprobanteNominaView);

		} catch (JAXBException e) {

			throw new ImportarPaqueteNominaException(e.getMessage());

		} catch (TransformerException e) {
			throw new ImportarPaqueteNominaException(e.getMessage());
		}

	}
	
	
	@Asynchronous
	public Future<ComprobanteNominaView> tranformComprobanteToNominaTimbrado12(String comprobante,Integer idComprobante)
			throws ImportarPaqueteNominaException {

		ComprobanteNominaView comprobanteNominaView = new ComprobanteNominaView();
		comprobanteNominaView.setId(0);
		comprobanteNominaView.setNum(0);
		comprobanteNominaView.setComprobanteXML(comprobante);

		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader xmlNomina = new StringReader(comprobante);
			mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante comprobanteCFDI = (mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante) jaxbUnmarshaller.unmarshal(xmlNomina);

			comprobanteNominaView.setComprobanteCFDI12(comprobanteCFDI);

			Object complementoTimbrado = comprobanteCFDI.getComplemento().getAny().get(1);

			Nomina12 cNomina = (Nomina12) comprobanteCFDI.getComplemento().getAny().get(0);

			comprobanteNominaView.setComplementoNomina12(cNomina);

			JAXBContext contextComplementoTimbreFiscal = JAXBContext.newInstance(TimbreFiscalDigital.class);
			Unmarshaller umTimbre = contextComplementoTimbreFiscal.createUnmarshaller();
			TimbreFiscalDigital cTimbreDigital = (TimbreFiscalDigital) umTimbre.unmarshal((Node) complementoTimbrado);

			comprobanteNominaView.setComplementoTimbre(cTimbreDigital);

			String cadenaOriginal = cadenaOriginalService.generar(comprobante);

			comprobanteNominaView.setCadenaOriginal(cadenaOriginal);
			comprobanteNominaView.setId(idComprobante);

			if (!comprobanteDAO.buscarPorUUID(comprobanteNominaView.getComplementoTimbre().getUUID())) {
				System.out.println("Guarda" + comprobanteNominaView.getComplementoTimbre().getUUID());
				actualizarComprobante12(comprobanteNominaView);
			}

			return new AsyncResult<ComprobanteNominaView>(comprobanteNominaView);

		} catch (JAXBException e) {

			throw new ImportarPaqueteNominaException(e.getMessage());

		} catch (TransformerException e) {
			throw new ImportarPaqueteNominaException(e.getMessage());
		}

	}

	private void guardarComprobante(ComprobanteNominaView datosComprobante) {

		// Empezamos por crear el comprobante
		ComprobanteEntity comprobanteEntity = new ComprobanteEntity();

		comprobanteEntity.setAntiguedad(datosComprobante.getComplementoNomina().getAntiguedad());
		comprobanteEntity.setBanco(datosComprobante.getComplementoNomina().getBanco());
		comprobanteEntity.setCadenaOriginal(datosComprobante.getCadenaOriginal());
		comprobanteEntity.setCalle(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCalle());
		if (datosComprobante.getComplementoNomina().getCLABE() != null)
			comprobanteEntity.setcLABE(datosComprobante.getComplementoNomina().getCLABE().toString());
		comprobanteEntity
				.setCodigoPostal(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCodigoPostal());
		comprobanteEntity.setColonia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getColonia());
		comprobanteEntity.setComprobanteXML(datosComprobante.getComprobanteXML().getBytes());
		comprobanteEntity.setcURP(datosComprobante.getComplementoNomina().getCURP());
		comprobanteEntity
				.setDeduccionTotalExcento(datosComprobante.getComplementoNomina().getDeducciones().getTotalExento());
		comprobanteEntity
				.setDeduccionTotalGravado(datosComprobante.getComplementoNomina().getDeducciones().getTotalGravado());
		comprobanteEntity.setDepartamento(datosComprobante.getComplementoNomina().getDepartamento());
		comprobanteEntity.setDescuento(datosComprobante.getComprobanteCFDI().getDescuento());
		comprobanteEntity.setEstado(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getEstado());

		comprobanteEntity.setFecha(datosComprobante.getComprobanteCFDI().getFecha().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaFinalPago(
				datosComprobante.getComplementoNomina().getFechaFinalPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaFolioFiscalOriginal(
				datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaInicialPago(
				datosComprobante.getComplementoNomina().getFechaInicialPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaInicioRelacionLaboral(
				datosComprobante.getComplementoNomina().getFechaInicioRelLaboral().toGregorianCalendar().getTime());
		comprobanteEntity
				.setFechaPago(datosComprobante.getComplementoNomina().getFechaPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaTimbrado(
				datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
		comprobanteEntity.setFolio(datosComprobante.getComprobanteCFDI().getFolio());
		comprobanteEntity.setFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getFolioFiscalOrig());
		comprobanteEntity.setFormaPago(datosComprobante.getComprobanteCFDI().getFormaDePago());
		comprobanteEntity.setIdXML(datosComprobante.getId().toString());
		comprobanteEntity
				.setLocalidad(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getLocalidad());
		comprobanteEntity.setLugarExpedicion(datosComprobante.getComprobanteCFDI().getLugarExpedicion());
		comprobanteEntity.setMetodoPago(datosComprobante.getComprobanteCFDI().getMetodoDePago());
		comprobanteEntity.setMoneda(datosComprobante.getComprobanteCFDI().getMoneda());
		comprobanteEntity.setMotivoDescuento(datosComprobante.getComprobanteCFDI().getMotivoDescuento());
		comprobanteEntity
				.setMunicipio(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getMunicipio());
		comprobanteEntity.setNoCertificado(datosComprobante.getComprobanteCFDI().getNoCertificado());
		comprobanteEntity
				.setNoExterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoExterior());
		comprobanteEntity
				.setNoInterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoInterior());
		comprobanteEntity.setNombre(datosComprobante.getComprobanteCFDI().getReceptor().getNombre());
		comprobanteEntity.setNumeroCertificadoSAT(datosComprobante.getComplementoTimbre().getNoCertificadoSAT());
		comprobanteEntity.setNumeroCuentaPago(datosComprobante.getComprobanteCFDI().getNumCtaPago());
		comprobanteEntity.setNumeroDiasPagados(datosComprobante.getComplementoNomina().getNumDiasPagados());
		comprobanteEntity.setNumeroEmpleado(datosComprobante.getComplementoNomina().getNumEmpleado());
		comprobanteEntity.setNumeroSeguridadSocial(datosComprobante.getComplementoNomina().getNumSeguridadSocial());
		comprobanteEntity.setNumXML(datosComprobante.getNum().toString());
		comprobanteEntity.setPais(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getPais());
		comprobanteEntity
				.setPercepcionTotalExcento(datosComprobante.getComplementoNomina().getPercepciones().getTotalExento());
		comprobanteEntity
				.setPercepcionTotalGravado(datosComprobante.getComplementoNomina().getPercepciones().getTotalGravado());
		comprobanteEntity.setPeriocidadPago(datosComprobante.getComplementoNomina().getPeriodicidadPago());
		comprobanteEntity.setPuesto(datosComprobante.getComplementoNomina().getPuesto());
		comprobanteEntity
				.setReferencia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getReferencia());
		comprobanteEntity.setRegistroPatronal(datosComprobante.getComplementoNomina().getRegistroPatronal());
		comprobanteEntity.setrFC(datosComprobante.getComprobanteCFDI().getReceptor().getRfc());
		comprobanteEntity.setRiesgoPuesto(datosComprobante.getComplementoNomina().getRiesgoPuesto());
		comprobanteEntity
				.setSalarioBaseCotizacionAport(datosComprobante.getComplementoNomina().getSalarioBaseCotApor());
		comprobanteEntity
				.setSalarioDiarioIntegrado(datosComprobante.getComplementoNomina().getSalarioDiarioIntegrado());
		comprobanteEntity.setSello(datosComprobante.getComprobanteCFDI().getSello());
		comprobanteEntity.setSelloCFDI(datosComprobante.getComplementoTimbre().getSelloCFD());
		comprobanteEntity.setSerie(datosComprobante.getComprobanteCFDI().getSerie());
		comprobanteEntity.setSerieFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getSerieFolioFiscalOrig());
		comprobanteEntity.setSubTotal(datosComprobante.getComprobanteCFDI().getSubTotal());
		comprobanteEntity.setTipoCambio(datosComprobante.getComprobanteCFDI().getTipoCambio());
		comprobanteEntity.setTipoComprobante(datosComprobante.getComprobanteCFDI().getTipoDeComprobante());
		comprobanteEntity.setTipoContrato(datosComprobante.getComplementoNomina().getTipoContrato());
		comprobanteEntity.setTipoJornada(datosComprobante.getComplementoNomina().getTipoJornada());
		comprobanteEntity.setTotal(datosComprobante.getComprobanteCFDI().getTotal());
		comprobanteEntity.setTotalImpuestoRetenidos(
				datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosRetenidos());
		comprobanteEntity.setTotalImpuestoTrasladados(
				datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosTrasladados());
		;
		comprobanteEntity.setuUID(datosComprobante.getComplementoTimbre().getUUID());
		comprobanteEntity.setVersion(datosComprobante.getComprobanteCFDI().getVersion());
		comprobanteEntity.setVersionNomina(datosComprobante.getComplementoNomina().getVersion());
		comprobanteEntity.setVersionTimbre(datosComprobante.getComplementoTimbre().getVersion());
		comprobanteEntity.setSelloSAT(datosComprobante.getComplementoTimbre().getSelloSAT());

		comprobanteEntity = comprobanteDAO.crear(comprobanteEntity);

		// Se empiezan a guarda el detalle de las percepciones.
		if (datosComprobante.getComplementoNomina().getPercepciones() != null) {
			if (datosComprobante.getComplementoNomina().getPercepciones().getPercepcion() != null) {
				for (Nomina.Percepciones.Percepcion percepcionComprobante : datosComprobante.getComplementoNomina()
						.getPercepciones().getPercepcion()) {
					PercepcionComprobanteEntity percepcionEntity = new PercepcionComprobanteEntity();
					percepcionEntity.setClave(percepcionComprobante.getClave());
					percepcionEntity.setConcepto(percepcionComprobante.getConcepto());
					percepcionEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					percepcionEntity.setImporteExento(percepcionComprobante.getImporteExento());
					percepcionEntity.setImporteGravado(percepcionComprobante.getImporteGravado());
					percepcionEntity.setTipoPercepcion(percepcionComprobante.getTipoPercepcion());
					percepcionComprobanteDAO.crear(percepcionEntity);

				}
			}
		}

		// Se guardan las deducciones
		if (datosComprobante.getComplementoNomina().getDeducciones() != null) {
			if (datosComprobante.getComplementoNomina().getDeducciones().getDeduccion() != null) {
				for (Nomina.Deducciones.Deduccion deduccionNomina : datosComprobante.getComplementoNomina()
						.getDeducciones().getDeduccion()) {

					DeduccionComprobanteEntity deduccionComprobanteEntity = new DeduccionComprobanteEntity();
					deduccionComprobanteEntity.setClave(deduccionNomina.getClave());
					deduccionComprobanteEntity.setConcepto(deduccionNomina.getConcepto());
					deduccionComprobanteEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					deduccionComprobanteEntity.setImporteExcento(deduccionNomina.getImporteExento());
					deduccionComprobanteEntity.setImporteGravado(deduccionNomina.getImporteGravado());
					deduccionComprobanteEntity.setTipoDeduccion(deduccionNomina.getTipoDeduccion());
					deduccionComprobanteDAO.crear(deduccionComprobanteEntity);

				}

			}
		}
		// Se guardan las horas extra
		if (datosComprobante.getComplementoNomina().getHorasExtras() != null) {
			if (datosComprobante.getComplementoNomina().getHorasExtras().getHorasExtra() != null) {
				for (Nomina.HorasExtras.HorasExtra horaExtraNomina : datosComprobante.getComplementoNomina()
						.getHorasExtras().getHorasExtra()) {
					HoraExtraComprobanteEntity horaExtraEntity = new HoraExtraComprobanteEntity();
					horaExtraEntity.setDia(horaExtraNomina.getDias());
					horaExtraEntity.setHoraExtra(horaExtraNomina.getHorasExtra());
					horaExtraEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					horaExtraEntity.setImportePagado(horaExtraNomina.getImportePagado());
					horaExtraEntity.setTipoHora(horaExtraNomina.getTipoHoras());
					horaExtraComprobanteDAO.crear(horaExtraEntity);
				}

			}
		}
		// Se guadan las incapacidades
		if (datosComprobante.getComplementoNomina().getIncapacidades() != null) {
			if (datosComprobante.getComplementoNomina().getIncapacidades().getIncapacidad() != null) {
				for (Nomina.Incapacidades.Incapacidad incapacidadNomina : datosComprobante.getComplementoNomina()
						.getIncapacidades().getIncapacidad()) {
					IncapacidadComprobanteEntity incapacidadComprobanteEntity = new IncapacidadComprobanteEntity();

					incapacidadComprobanteEntity.setDescuento(incapacidadNomina.getDescuento());
					incapacidadComprobanteEntity.setDiaIncapacidad(incapacidadNomina.getDiasIncapacidad());
					incapacidadComprobanteEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					incapacidadComprobanteEntity.setTipoIncapacidad(incapacidadNomina.getTipoIncapacidad());

					incapacidadComprobanteDAO.crear(incapacidadComprobanteEntity);

				}

			}
		}

	}

	private Integer guardarComprobanteProducto(ComprobanteNominaView datosComprobante) {

		// Empezamos por crear el comprobante
		ComprobanteEntity comprobanteEntity = new ComprobanteEntity();

		comprobanteEntity.setAntiguedad(datosComprobante.getComplementoNomina().getAntiguedad());
		comprobanteEntity.setBanco(datosComprobante.getComplementoNomina().getBanco());
		comprobanteEntity.setCadenaOriginal(datosComprobante.getCadenaOriginal());
		comprobanteEntity.setCalle(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCalle());
		if (datosComprobante.getComplementoNomina().getCLABE() != null)
			comprobanteEntity.setcLABE(datosComprobante.getComplementoNomina().getCLABE().toString());
		comprobanteEntity
				.setCodigoPostal(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCodigoPostal());
		comprobanteEntity.setColonia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getColonia());
		comprobanteEntity.setComprobanteXML(datosComprobante.getComprobanteXML().getBytes());
		comprobanteEntity.setcURP(datosComprobante.getComplementoNomina().getCURP());
		comprobanteEntity
				.setDeduccionTotalExcento(datosComprobante.getComplementoNomina().getDeducciones().getTotalExento());
		comprobanteEntity
				.setDeduccionTotalGravado(datosComprobante.getComplementoNomina().getDeducciones().getTotalGravado());
		comprobanteEntity.setDepartamento(datosComprobante.getComplementoNomina().getDepartamento());
		comprobanteEntity.setDescuento(datosComprobante.getComprobanteCFDI().getDescuento());
		comprobanteEntity.setEstado(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getEstado());

		comprobanteEntity.setFecha(datosComprobante.getComprobanteCFDI().getFecha().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaFinalPago(
				datosComprobante.getComplementoNomina().getFechaFinalPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaFolioFiscalOriginal(
				datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaInicialPago(
				datosComprobante.getComplementoNomina().getFechaInicialPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaInicioRelacionLaboral(
				datosComprobante.getComplementoNomina().getFechaInicioRelLaboral().toGregorianCalendar().getTime());
		comprobanteEntity
				.setFechaPago(datosComprobante.getComplementoNomina().getFechaPago().toGregorianCalendar().getTime());
		comprobanteEntity.setFechaTimbrado(
				datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
		comprobanteEntity.setFolio(datosComprobante.getComprobanteCFDI().getFolio());
		comprobanteEntity.setFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getFolioFiscalOrig());
		comprobanteEntity.setFormaPago(datosComprobante.getComprobanteCFDI().getFormaDePago());
		comprobanteEntity.setIdXML(datosComprobante.getId().toString());
		comprobanteEntity
				.setLocalidad(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getLocalidad());
		comprobanteEntity.setLugarExpedicion(datosComprobante.getComprobanteCFDI().getLugarExpedicion());
		comprobanteEntity.setMetodoPago(datosComprobante.getComprobanteCFDI().getMetodoDePago());
		comprobanteEntity.setMoneda(datosComprobante.getComprobanteCFDI().getMoneda());
		comprobanteEntity.setMotivoDescuento(datosComprobante.getComprobanteCFDI().getMotivoDescuento());
		comprobanteEntity
				.setMunicipio(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getMunicipio());
		comprobanteEntity.setNoCertificado(datosComprobante.getComprobanteCFDI().getNoCertificado());
		comprobanteEntity
				.setNoExterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoExterior());
		comprobanteEntity
				.setNoInterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoInterior());
		comprobanteEntity.setNombre(datosComprobante.getComprobanteCFDI().getReceptor().getNombre());
		comprobanteEntity.setNumeroCertificadoSAT(datosComprobante.getComplementoTimbre().getNoCertificadoSAT());
		comprobanteEntity.setNumeroCuentaPago(datosComprobante.getComprobanteCFDI().getNumCtaPago());
		comprobanteEntity.setNumeroDiasPagados(datosComprobante.getComplementoNomina().getNumDiasPagados());
		comprobanteEntity.setNumeroEmpleado(datosComprobante.getComplementoNomina().getNumEmpleado());
		comprobanteEntity.setNumeroSeguridadSocial(datosComprobante.getComplementoNomina().getNumSeguridadSocial());
		comprobanteEntity.setNumXML(datosComprobante.getNum().toString());
		comprobanteEntity.setPais(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getPais());
		comprobanteEntity
				.setPercepcionTotalExcento(datosComprobante.getComplementoNomina().getPercepciones().getTotalExento());
		comprobanteEntity
				.setPercepcionTotalGravado(datosComprobante.getComplementoNomina().getPercepciones().getTotalGravado());
		comprobanteEntity.setPeriocidadPago(datosComprobante.getComplementoNomina().getPeriodicidadPago());
		comprobanteEntity.setPuesto(datosComprobante.getComplementoNomina().getPuesto());
		comprobanteEntity
				.setReferencia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getReferencia());
		comprobanteEntity.setRegistroPatronal(datosComprobante.getComplementoNomina().getRegistroPatronal());
		comprobanteEntity.setrFC(datosComprobante.getComprobanteCFDI().getReceptor().getRfc());
		comprobanteEntity.setRiesgoPuesto(datosComprobante.getComplementoNomina().getRiesgoPuesto());
		comprobanteEntity
				.setSalarioBaseCotizacionAport(datosComprobante.getComplementoNomina().getSalarioBaseCotApor());
		comprobanteEntity
				.setSalarioDiarioIntegrado(datosComprobante.getComplementoNomina().getSalarioDiarioIntegrado());
		comprobanteEntity.setSello(datosComprobante.getComprobanteCFDI().getSello());
		comprobanteEntity.setSelloCFDI(datosComprobante.getComplementoTimbre().getSelloCFD());
		comprobanteEntity.setSerie(datosComprobante.getComprobanteCFDI().getSerie());
		comprobanteEntity.setSerieFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getSerieFolioFiscalOrig());
		comprobanteEntity.setSubTotal(datosComprobante.getComprobanteCFDI().getSubTotal());
		comprobanteEntity.setTipoCambio(datosComprobante.getComprobanteCFDI().getTipoCambio());
		comprobanteEntity.setTipoComprobante(datosComprobante.getComprobanteCFDI().getTipoDeComprobante());
		comprobanteEntity.setTipoContrato(datosComprobante.getComplementoNomina().getTipoContrato());
		comprobanteEntity.setTipoJornada(datosComprobante.getComplementoNomina().getTipoJornada());
		comprobanteEntity.setTotal(datosComprobante.getComprobanteCFDI().getTotal());
		comprobanteEntity.setTotalImpuestoRetenidos(
				datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosRetenidos());
		comprobanteEntity.setTotalImpuestoTrasladados(
				datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosTrasladados());
		;
		comprobanteEntity.setuUID(datosComprobante.getComplementoTimbre().getUUID());
		comprobanteEntity.setVersion(datosComprobante.getComprobanteCFDI().getVersion());
		comprobanteEntity.setVersionNomina(datosComprobante.getComplementoNomina().getVersion());
		comprobanteEntity.setVersionTimbre(datosComprobante.getComplementoTimbre().getVersion());
		comprobanteEntity.setSelloSAT(datosComprobante.getComplementoTimbre().getSelloSAT());

		comprobanteEntity = comprobanteDAO.crear(comprobanteEntity);

		// Se empiezan a guarda el detalle de las percepciones.
		if (datosComprobante.getComplementoNomina().getPercepciones() != null) {
			if (datosComprobante.getComplementoNomina().getPercepciones().getPercepcion() != null) {
				for (Nomina.Percepciones.Percepcion percepcionComprobante : datosComprobante.getComplementoNomina()
						.getPercepciones().getPercepcion()) {
					PercepcionComprobanteEntity percepcionEntity = new PercepcionComprobanteEntity();
					percepcionEntity.setClave(percepcionComprobante.getClave());
					percepcionEntity.setConcepto(percepcionComprobante.getConcepto());
					percepcionEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					percepcionEntity.setImporteExento(percepcionComprobante.getImporteExento());
					percepcionEntity.setImporteGravado(percepcionComprobante.getImporteGravado());
					percepcionEntity.setTipoPercepcion(percepcionComprobante.getTipoPercepcion());
					percepcionComprobanteDAO.crear(percepcionEntity);

				}
			}
		}

		// Se guardan las deducciones
		if (datosComprobante.getComplementoNomina().getDeducciones() != null) {
			if (datosComprobante.getComplementoNomina().getDeducciones().getDeduccion() != null) {
				for (Nomina.Deducciones.Deduccion deduccionNomina : datosComprobante.getComplementoNomina()
						.getDeducciones().getDeduccion()) {

					DeduccionComprobanteEntity deduccionComprobanteEntity = new DeduccionComprobanteEntity();
					deduccionComprobanteEntity.setClave(deduccionNomina.getClave());
					deduccionComprobanteEntity.setConcepto(deduccionNomina.getConcepto());
					deduccionComprobanteEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					deduccionComprobanteEntity.setImporteExcento(deduccionNomina.getImporteExento());
					deduccionComprobanteEntity.setImporteGravado(deduccionNomina.getImporteGravado());
					deduccionComprobanteEntity.setTipoDeduccion(deduccionNomina.getTipoDeduccion());
					deduccionComprobanteDAO.crear(deduccionComprobanteEntity);

				}

			}
		}
		// Se guardan las horas extra
		if (datosComprobante.getComplementoNomina().getHorasExtras() != null) {
			if (datosComprobante.getComplementoNomina().getHorasExtras().getHorasExtra() != null) {
				for (Nomina.HorasExtras.HorasExtra horaExtraNomina : datosComprobante.getComplementoNomina()
						.getHorasExtras().getHorasExtra()) {
					HoraExtraComprobanteEntity horaExtraEntity = new HoraExtraComprobanteEntity();
					horaExtraEntity.setDia(horaExtraNomina.getDias());
					horaExtraEntity.setHoraExtra(horaExtraNomina.getHorasExtra());
					horaExtraEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					horaExtraEntity.setImportePagado(horaExtraNomina.getImportePagado());
					horaExtraEntity.setTipoHora(horaExtraNomina.getTipoHoras());
					horaExtraComprobanteDAO.crear(horaExtraEntity);
				}

			}
		}
		// Se guadan las incapacidades
		if (datosComprobante.getComplementoNomina().getIncapacidades() != null) {
			if (datosComprobante.getComplementoNomina().getIncapacidades().getIncapacidad() != null) {
				for (Nomina.Incapacidades.Incapacidad incapacidadNomina : datosComprobante.getComplementoNomina()
						.getIncapacidades().getIncapacidad()) {
					IncapacidadComprobanteEntity incapacidadComprobanteEntity = new IncapacidadComprobanteEntity();

					incapacidadComprobanteEntity.setDescuento(incapacidadNomina.getDescuento());
					incapacidadComprobanteEntity.setDiaIncapacidad(incapacidadNomina.getDiasIncapacidad());
					incapacidadComprobanteEntity.setIdComprobante(comprobanteEntity.getIdComprobante());
					incapacidadComprobanteEntity.setTipoIncapacidad(incapacidadNomina.getTipoIncapacidad());

					incapacidadComprobanteDAO.crear(incapacidadComprobanteEntity);

				}

			}
		}

		return comprobanteEntity.getIdComprobante();

	}

	private void actualizarComprobante(ComprobanteNominaView datosComprobante) {

		// Empezamos por crear el comprobante
		ComprobanteEntity comprobanteEntity = comprobanteDAO.buscarComprobanteTimbrado(
				datosComprobante.getComprobanteCFDI().getReceptor().getRfc(),
				datosComprobante.getComplementoNomina().getFechaPago().toGregorianCalendar().getTime(),
				datosComprobante.getComprobanteCFDI().getTotal(),
				new Integer(datosComprobante.getComprobanteCFDI().getFolio()));

		if (comprobanteEntity != null) {

			//comprobanteEntity.setAntiguedad(datosComprobante.getComplementoNomina12().getAntiguedad());
			//comprobanteEntity.setBanco(datosComprobante.getComplementoNomina().getBanco());
			comprobanteEntity.setCadenaOriginal(datosComprobante.getCadenaOriginal());
			comprobanteEntity.setCalle(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCalle());
			if (datosComprobante.getComplementoNomina().getCuentaBancaria() != null)
				comprobanteEntity.setcLABE(datosComprobante.getComplementoNomina().getCuentaBancaria().toString());
			comprobanteEntity.setCodigoPostal(
					datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getCodigoPostal());
			comprobanteEntity
					.setColonia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getColonia());
			comprobanteEntity.setComprobanteXML(datosComprobante.getComprobanteXML().getBytes());
			comprobanteEntity.setcURP(datosComprobante.getComplementoNomina().getCURP());
			/*if (datosComprobante.getComplementoNomina12().getDeducciones() != null) {
				comprobanteEntity.setDeduccionTotalExcento(
						datosComprobante.getComplementoNomina12().getDeducciones().getTotalExento());
				comprobanteEntity.setDeduccionTotalGravado(
						datosComprobante.getComplementoNomina().getDeducciones().getTotalGravado());
			}*/

			comprobanteEntity.setDepartamento(datosComprobante.getComplementoNomina().getDepartamento());
			comprobanteEntity.setDescuento(datosComprobante.getComprobanteCFDI().getDescuento());
			comprobanteEntity.setEstado(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getEstado());

			comprobanteEntity
					.setFecha(datosComprobante.getComprobanteCFDI().getFecha().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaFinalPago(
					datosComprobante.getComplementoNomina().getFechaFinalPago().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaFolioFiscalOriginal(
					datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaInicialPago(
					datosComprobante.getComplementoNomina().getFechaInicialPago().toGregorianCalendar().getTime());
			if (datosComprobante.getComplementoNomina().getFechaInicioRelLaboral() != null) {
				comprobanteEntity.setFechaInicioRelacionLaboral(datosComprobante.getComplementoNomina()
						.getFechaInicioRelLaboral().toGregorianCalendar().getTime());
			}
			comprobanteEntity.setFechaPago(
					datosComprobante.getComplementoNomina().getFechaPago().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaTimbrado(
					datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
			comprobanteEntity.setFolio(datosComprobante.getComprobanteCFDI().getFolio());
			comprobanteEntity.setFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getFolioFiscalOrig());
			comprobanteEntity.setFormaPago(datosComprobante.getComprobanteCFDI().getFormaDePago());
			comprobanteEntity.setIdXML(datosComprobante.getId().toString());
			comprobanteEntity
					.setLocalidad(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getLocalidad());
			comprobanteEntity.setLugarExpedicion(datosComprobante.getComprobanteCFDI().getLugarExpedicion());
			comprobanteEntity.setMetodoPago(datosComprobante.getComprobanteCFDI().getMetodoDePago());
			comprobanteEntity.setMoneda(datosComprobante.getComprobanteCFDI().getMoneda());
			comprobanteEntity.setMotivoDescuento(datosComprobante.getComprobanteCFDI().getMotivoDescuento());
			comprobanteEntity
					.setMunicipio(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getMunicipio());
			comprobanteEntity.setNoCertificado(datosComprobante.getComprobanteCFDI().getNoCertificado());
			comprobanteEntity
					.setNoExterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoExterior());
			comprobanteEntity
					.setNoInterior(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getNoInterior());
			comprobanteEntity.setNombre(datosComprobante.getComprobanteCFDI().getReceptor().getNombre());
			comprobanteEntity.setNumeroCertificadoSAT(datosComprobante.getComplementoTimbre().getNoCertificadoSAT());
			comprobanteEntity.setNumeroCuentaPago(datosComprobante.getComprobanteCFDI().getNumCtaPago());
			comprobanteEntity.setNumeroDiasPagados(datosComprobante.getComplementoNomina().getNumDiasPagados());
			comprobanteEntity.setNumeroEmpleado(datosComprobante.getComplementoNomina().getNumEmpleado());
			comprobanteEntity.setNumeroSeguridadSocial(datosComprobante.getComplementoNomina().getNumSeguridadSocial());
			comprobanteEntity.setNumXML(datosComprobante.getNum().toString());
			comprobanteEntity.setPais(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getPais());
			/*comprobanteEntity.setPercepcionTotalExcento(
					datosComprobante.getComplementoNomina().getPercepciones().getTotalExento());
			comprobanteEntity.setPercepcionTotalGravado(
					datosComprobante.getComplementoNomina().getPercepciones().getTotalGravado());*/
			comprobanteEntity.setPeriocidadPago(datosComprobante.getComplementoNomina().getPeriodicidadPago());
			comprobanteEntity.setPuesto(datosComprobante.getComplementoNomina().getPuesto());
			comprobanteEntity
					.setReferencia(datosComprobante.getComprobanteCFDI().getReceptor().getDomicilio().getReferencia());
			comprobanteEntity.setRegistroPatronal(datosComprobante.getComplementoNomina().getRegistroPatronal());
			comprobanteEntity.setrFC(datosComprobante.getComprobanteCFDI().getReceptor().getRfc());
			//comprobanteEntity.setRiesgoPuesto(new  Integer(datosComprobante.getComplementoNomina().getRiesgoPuesto()));
			comprobanteEntity
					.setSalarioBaseCotizacionAport(datosComprobante.getComplementoNomina().getSalarioBaseCotApor());
			comprobanteEntity
					.setSalarioDiarioIntegrado(datosComprobante.getComplementoNomina().getSalarioDiarioIntegrado());
			comprobanteEntity.setSello(datosComprobante.getComprobanteCFDI().getSello());
			comprobanteEntity.setSelloCFDI(datosComprobante.getComplementoTimbre().getSelloCFD());
			comprobanteEntity.setSerie(datosComprobante.getComprobanteCFDI().getSerie());
			comprobanteEntity
					.setSerieFolioFiscalOriginal(datosComprobante.getComprobanteCFDI().getSerieFolioFiscalOrig());
			comprobanteEntity.setSubTotal(datosComprobante.getComprobanteCFDI().getSubTotal());
			comprobanteEntity.setTipoCambio(datosComprobante.getComprobanteCFDI().getTipoCambio());
			comprobanteEntity.setTipoComprobante(datosComprobante.getComprobanteCFDI().getTipoDeComprobante());
			comprobanteEntity.setTipoContrato(datosComprobante.getComplementoNomina().getTipoContrato());
			comprobanteEntity.setTipoJornada(datosComprobante.getComplementoNomina().getTipoJornada());
			comprobanteEntity.setTotal(datosComprobante.getComprobanteCFDI().getTotal());
			comprobanteEntity.setTotalImpuestoRetenidos(
					datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosRetenidos());
			comprobanteEntity.setTotalImpuestoTrasladados(
					datosComprobante.getComprobanteCFDI().getImpuestos().getTotalImpuestosTrasladados());
			;
			comprobanteEntity.setuUID(datosComprobante.getComplementoTimbre().getUUID());
			comprobanteEntity.setVersion(datosComprobante.getComprobanteCFDI().getVersion());
			comprobanteEntity.setVersionNomina(datosComprobante.getComplementoNomina().getVersion());
			comprobanteEntity.setVersionTimbre(datosComprobante.getComplementoTimbre().getVersion());
			comprobanteEntity.setSelloSAT(datosComprobante.getComplementoTimbre().getSelloSAT());

			comprobanteEntity = comprobanteDAO.actualizar(comprobanteEntity);
		}

	}
	
	private void actualizarComprobante12(ComprobanteNominaView datosComprobante) {
		
		

		// Empezamos por crear el comprobante
		ComprobanteEntity comprobanteEntity = comprobanteDAO.obtenerPorId(datosComprobante.getId()); /*comprobanteDAO.buscarComprobanteTimbrado(
				datosComprobante.getComprobanteCFDI12().getReceptor().getRfc(),
				datosComprobante.getComplementoNomina12().getFechaPago().toGregorianCalendar().getTime(),
				datosComprobante.getComprobanteCFDI12().getTotal(),
				new Integer(datosComprobante.getComprobanteCFDI12().getFolio()));*/

		if (comprobanteEntity != null) {

			//comprobanteEntity.setAntiguedad(datosComprobante.getComplementoNomina12().getAntiguedad());
			//comprobanteEntity.setBanco(datosComprobante.getComplementoNomina().getBanco());
			comprobanteEntity.setCadenaOriginal(datosComprobante.getCadenaOriginal());
			//comprobanteEntity.setCalle(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getCalle());
			//if (datosComprobante.getComplementoNomina12().getReceptor().getCuentaBancaria() != null)
		//		comprobanteEntity.setcLABE(datosComprobante.getComplementoNomina12().getReceptor().getCuentaBancaria().toString());
			//comprobanteEntity.setCodigoPostal(
				//	datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getCodigoPostal());
			//comprobanteEntity
				//	.setColonia(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getColonia());
			comprobanteEntity.setComprobanteXML(datosComprobante.getComprobanteXML().getBytes());
			comprobanteEntity.setcURP(datosComprobante.getComplementoNomina12().getReceptor().getCurp());
			/*if (datosComprobante.getComplementoNomina12().getDeducciones() != null) {
				comprobanteEntity.setDeduccionTotalExcento(
						datosComprobante.getComplementoNomina12().getDeducciones().getTotalExento());
				comprobanteEntity.setDeduccionTotalGravado(
						datosComprobante.getComplementoNomina().getDeducciones().getTotalGravado());
			}*/

			comprobanteEntity.setDepartamento(datosComprobante.getComplementoNomina12().getReceptor().getDepartamento());
			comprobanteEntity.setDescuento(datosComprobante.getComprobanteCFDI12().getDescuento());
			//comprobanteEntity.setEstado(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getEstado());

			comprobanteEntity
					.setFecha(datosComprobante.getComprobanteCFDI12().getFecha().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaFinalPago(
					datosComprobante.getComplementoNomina12().getFechaFinalPago().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaFolioFiscalOriginal(
					datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaInicialPago(
					datosComprobante.getComplementoNomina12().getFechaInicialPago().toGregorianCalendar().getTime());
			if (datosComprobante.getComplementoNomina12().getReceptor().getFechaInicioRelLaboral() != null) {
				comprobanteEntity.setFechaInicioRelacionLaboral(datosComprobante.getComplementoNomina12().getReceptor()
						.getFechaInicioRelLaboral().toGregorianCalendar().getTime());
			}
			comprobanteEntity.setFechaPago(
					datosComprobante.getComplementoNomina12().getFechaPago().toGregorianCalendar().getTime());
			comprobanteEntity.setFechaTimbrado(
					datosComprobante.getComplementoTimbre().getFechaTimbrado().toGregorianCalendar().getTime());
			comprobanteEntity.setFolio(datosComprobante.getComprobanteCFDI12().getFolio());
			comprobanteEntity.setFolioFiscalOriginal(datosComprobante.getComprobanteCFDI12().getFolioFiscalOrig());
			comprobanteEntity.setFormaPago(datosComprobante.getComprobanteCFDI12().getFormaDePago());
			comprobanteEntity.setIdXML(datosComprobante.getId().toString());
			//comprobanteEntity
				//	.setLocalidad(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getLocalidad());
			comprobanteEntity.setLugarExpedicion(datosComprobante.getComprobanteCFDI12().getLugarExpedicion());
			comprobanteEntity.setMetodoPago(datosComprobante.getComprobanteCFDI12().getMetodoDePago());
			comprobanteEntity.setMoneda(datosComprobante.getComprobanteCFDI12().getMoneda());
			comprobanteEntity.setMotivoDescuento(datosComprobante.getComprobanteCFDI12().getMotivoDescuento());
			//comprobanteEntity
				//	.setMunicipio(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getMunicipio());
			comprobanteEntity.setNoCertificado(datosComprobante.getComprobanteCFDI12().getNoCertificado());
			//comprobanteEntity
				//	.setNoExterior(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getNoExterior());
			//comprobanteEntity
				//	.setNoInterior(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getNoInterior());
			comprobanteEntity.setNombre(datosComprobante.getComprobanteCFDI12().getReceptor().getNombre());
			comprobanteEntity.setNumeroCertificadoSAT(datosComprobante.getComplementoTimbre().getNoCertificadoSAT());
			comprobanteEntity.setNumeroCuentaPago(datosComprobante.getComprobanteCFDI12().getNumCtaPago());
			comprobanteEntity.setNumeroDiasPagados(datosComprobante.getComplementoNomina12().getNumDiasPagados());
			comprobanteEntity.setNumeroEmpleado(datosComprobante.getComplementoNomina12().getReceptor().getNumEmpleado());
			comprobanteEntity.setNumeroSeguridadSocial(datosComprobante.getComplementoNomina12().getReceptor().getNumSeguridadSocial());
			comprobanteEntity.setNumXML(datosComprobante.getNum().toString());
			//comprobanteEntity.setPais(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getPais());
			/*comprobanteEntity.setPercepcionTotalExcento(
					datosComprobante.getComplementoNomina().getPercepciones().getTotalExento());
			comprobanteEntity.setPercepcionTotalGravado(
					datosComprobante.getComplementoNomina().getPercepciones().getTotalGravado());*/
			comprobanteEntity.setPeriocidadPago(datosComprobante.getComplementoNomina12().getReceptor().getPeriodicidadPago());
			comprobanteEntity.setPuesto(datosComprobante.getComplementoNomina12().getReceptor().getPuesto());
			//comprobanteEntity
				//	.setReferencia(datosComprobante.getComprobanteCFDI12().getReceptor().getDomicilio().getReferencia());
			comprobanteEntity.setRegistroPatronal(datosComprobante.getComplementoNomina12().getEmisor().getRegistroPatronal());
			comprobanteEntity.setrFC(datosComprobante.getComprobanteCFDI12().getReceptor().getRfc());
			//comprobanteEntity.setRiesgoPuesto(new  Integer(datosComprobante.getComplementoNomina12().getReceptor().getRiesgoPuesto()));
			comprobanteEntity
					.setSalarioBaseCotizacionAport(datosComprobante.getComplementoNomina12().getReceptor().getSalarioBaseCotApor());
			comprobanteEntity
					.setSalarioDiarioIntegrado(datosComprobante.getComplementoNomina12().getReceptor().getSalarioDiarioIntegrado());
			comprobanteEntity.setSello(datosComprobante.getComprobanteCFDI12().getSello());
			comprobanteEntity.setSelloCFDI(datosComprobante.getComplementoTimbre().getSelloCFD());
			comprobanteEntity.setSerie(datosComprobante.getComprobanteCFDI12().getSerie());
			comprobanteEntity
					.setSerieFolioFiscalOriginal(datosComprobante.getComprobanteCFDI12().getSerieFolioFiscalOrig());
			comprobanteEntity.setSubTotal(datosComprobante.getComprobanteCFDI12().getSubTotal());
			comprobanteEntity.setTipoCambio(datosComprobante.getComprobanteCFDI12().getTipoCambio());
			comprobanteEntity.setTipoComprobante(datosComprobante.getComprobanteCFDI12().getTipoDeComprobante());
			comprobanteEntity.setTipoContrato(datosComprobante.getComplementoNomina12().getReceptor().getTipoContrato());
			comprobanteEntity.setTipoJornada(datosComprobante.getComplementoNomina12().getReceptor().getTipoJornada());
			comprobanteEntity.setTotal(datosComprobante.getComprobanteCFDI12().getTotal());
			comprobanteEntity.setTotalImpuestoRetenidos(
					datosComprobante.getComprobanteCFDI12().getImpuestos().getTotalImpuestosRetenidos());
			comprobanteEntity.setTotalImpuestoTrasladados(
					datosComprobante.getComprobanteCFDI12().getImpuestos().getTotalImpuestosTrasladados());
			;
			comprobanteEntity.setuUID(datosComprobante.getComplementoTimbre().getUUID());
			comprobanteEntity.setVersion(datosComprobante.getComprobanteCFDI12().getVersion());
			comprobanteEntity.setVersionNomina(datosComprobante.getComplementoNomina12().getVersion());
			comprobanteEntity.setVersionTimbre(datosComprobante.getComplementoTimbre().getVersion());
			comprobanteEntity.setSelloSAT(datosComprobante.getComplementoTimbre().getSelloSAT());

			comprobanteEntity = comprobanteDAO.actualizar(comprobanteEntity);
		}

	}


}
