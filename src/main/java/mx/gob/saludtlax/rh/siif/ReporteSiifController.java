package mx.gob.saludtlax.rh.siif;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DefaultStreamedContent;

import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

import mx.gob.saludtlax.rh.siif.layout.SIIFLayout;
import mx.gob.saludtlax.rh.siif.serica.Serica;

/**
 * 
 * @author pablinsky
 * 
 */

@ManagedBean(name = "reporteSiif")
@SessionScoped
public class ReporteSiifController {
	private static final Logger LOGGER = Logger.getLogger(ReporteSiifController.class.getName());

	@Inject
	private SIIFLayout generarLayout;
	@Inject
	private ReporteSiifEJB ejb;
	@Inject
	private Serica serica;
	private ReporteSiifView view;

	@PostConstruct
	public void initReporteSiif() {
		view = new ReporteSiifView();
		view.setCuentaBancariaList(ejb.obtenerCuentaBancariaList());
		view.setTipoNominaList(ejb.obtenerTipoNominaList());
		// view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(bitacoraDTO));
		// view.setSubfuenteFinanciamientoList(ejb.obtenerSubfuenteFinanciamientoList());
		irPrincipal();
	}

	public String irPrincipal() {
		view.panelPrincipal();
		return null;
	}

	public String irDeudores() {
		view.panelDeudores();
		view.setTrailersLista(ejb.listaDeudores(view.getReporteSiifSelect().getIdSiifBitacora()));
		return null;
	}

	public String irDispersion() {
		view.setEncabezadoDTO(ejb.actualizarCheques(view.getSiifEncabezadoSelect(), view.getPeriodoCriterio()));
		// view.setTrailersLista(ejb.listaDispersion(view.getSiifEncabezadoSelect().getIdSIIFBitacora()));
		// view.setEncabezadoDTO(ejb.actualizarCheques(view.getSiifEncabezadoSelect(),
		// view.getSiifEncabezadoSelect().getIdSIIFBitacora()));

		return null;
	}

	public String filtrarReporteSiif() {
		view.setListReporteSiif(ejb.obtenerReporteSiifPorPeriodo(view.getPeriodoCriterio(), view.getAnioCriterio()));
		view.setPanelResul(Boolean.TRUE);
		view.setPanelDownload(Boolean.FALSE);
		return null;
	}

	public String importar() {
		view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		view.setPanelUpload(Boolean.TRUE);
		view.setPanelNomina(Boolean.TRUE);
		view.setPanelResume(Boolean.FALSE);
		view.setPanelContrato(Boolean.FALSE);
		return "/contenido/siif/reportarsiiffederales.xhtml?faces-redirect=true";
	}

	public String importarContrato() {
		view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		view.setPanelUpload(Boolean.TRUE);
		view.setPanelResume(Boolean.FALSE);
		view.setPanelNomina(Boolean.FALSE);
		view.setPanelContrato(Boolean.TRUE);
		// return
		// "/contenido/siif/reportarsiiffederalescontrato.xhtml?faces-redirect=true";
		return "/contenido/siif/reportarsiiffederales.xhtml?faces-redirect=true";
	}

	public String importarProspera() {
		view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		view.setPanelUpload(Boolean.TRUE);
		view.setPanelResume(Boolean.FALSE);
		return "/contenido/siif/reportarsiifafaspe.xhtml?faces-redirect=true";
	}

	public String consultar() {
		view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getReporteSiifSelect()));
		view.setPanelUpload(Boolean.FALSE);
		view.setPanelResume(Boolean.TRUE);
		return "/contenido/siif/reportarsiiffederales.xhtml?faces-redirect=true";
	}

	public String consultarRH() {
		view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getReporteSiifSelect()));
		view.setPanelUpload(Boolean.FALSE);
		view.setPanelResume(Boolean.TRUE);
		return "/contenido/siifrh/reportarsiiffederales.xhtml?faces-redirect=true";
	}

	public String encabezado() {
		view.setEncabezadoList(ejb.generarEncabezadoSiif(view.getAnioCriterio(), view.getPeriodoCriterio()));
		view.setPanelUpload(Boolean.FALSE);
		view.setPanelResume(Boolean.TRUE);
		return "/contenido/siif/reportarsiifencabezado.xhtml?faces-redirect=true";
	}

	public void deudores() {
		ejb.deudores(view.getReporteSiifSelect().getIdSiifBitacora(), view.getReporteSiifSelect().getIdTipoNomina(),
				view.getReporteSiifSelect().getPeriodo());
		JSFUtils.infoMessage("Se cambiaron los conceptos de los deudores", "da click en la pantalla");
		// view.setPanelResul(false);
		// view.setPanelDeudores(true);
		irDeudores();
	}

	public void bitacora() {
		ejb.bitacora(view.getReporteSiifSelect().getIdSiifBitacora(), view.getReporteSiifSelect().getIdTipoNomina(),
				view.getReporteSiifSelect().getPeriodo());
		JSFUtils.infoMessage("Se elimino la bitacora y encabezados correspondientes", "");
		// view.setPanelResul(false);
		// view.setPanelDeudores(true);
	}

	public String generarLayout() {
		byte[] layout = generarLayout.getLayoutComoZip(view.getPeriodoCriterio(), view.getAnioCriterio());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "layout.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
	}

	public String generarLayoutFinal() {
		byte[] layout = generarLayout.getLayoutFinalComoZip(view.getPeriodoCriterio(), view.getAnioCriterio());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "layout.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
	}

	public String generarDatTra() {
		// byte[] layout =
		// generarLayout.getLayoutComoDatTra(view.getPeriodoCriterio());
		byte[] layout = generarLayout.getDatTra(view.getReporteSiifSelect().getIdSiifBitacora());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "datytra.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
	}

	public String generarDatTraRH() {

		if (view.getReporteSiifSelect() != null) {

			byte[] layout = generarLayout.getDatTraRH(view.getReporteSiifSelect().getIdSiifBitacora());
			InputStream is = new ByteArrayInputStream(layout);
			DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "datytra.zip");

			view.setFile(dfc);
			view.setPanelResul(Boolean.FALSE);
			view.setPanelDownload(Boolean.TRUE);
			return "/contenido/siifrh/reportarSiif.xhtml?faces-redirect=true";

		} else {
			JSFUtils.infoMessage("Elija un registro", "para continuar");
			return null;
		}

	}

	public String generarDatTraContrato() {
		byte[] layout = generarLayout.getDatTraContrato(view.getPeriodoCriterio());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(),
				"datytraContrato.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siif/reporteDatTra.xhtml?faces-redirect=true";
	}

	public void generarSeguroPopularReporte() {
		try {
			String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "seguro_popular", "TIPO_REPORTE",
					"xlsx", };

			AdministradorReportes admin = new AdministradorReportes();
			String referencia = admin.obtenerReferencia(parametros);
			byte[] result = admin.obtenerReporte(referencia);
			JSFUtils.descargarArchivo(result, "seguro-popular-reporte", TipoArchivo.XLSX);
		} catch (IOException ex) {
			LOGGER.error(ex);
		}
	}

	public void generarSeguroPopularReporteLuis() {
		try {
			String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "seguro_popular_reporte",
					"TIPO_REPORTE", "xlsx", "ANYO", "2016", "QUINCENA", "20", };

			AdministradorReportes admin = new AdministradorReportes();
			String referencia = admin.obtenerReferencia(parametros);
			byte[] result = admin.obtenerReporte(referencia);
			JSFUtils.descargarArchivo(result, "seguro-popular-reporte", TipoArchivo.XLSX);
		} catch (IOException ex) {
			LOGGER.error(ex);
		}
	}

	public String generarSeguroPopular() {
		byte[] layout = generarLayout.getLayoutSeguroPopular(view.getPeriodoCriterio());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "SeguroPopular.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
	}

	public String generarSeguroPopularRH() {
		byte[] layout = generarLayout.getLayoutSeguroPopular(view.getPeriodoCriterio());
		InputStream is = new ByteArrayInputStream(layout);

		DefaultStreamedContent dfc = new DefaultStreamedContent(is, TipoArchivo.ZIP.getMIMEType(), "SeguroPopular.zip");

		view.setFile(dfc);
		view.setPanelResul(Boolean.FALSE);
		view.setPanelDownload(Boolean.TRUE);
		return "/contenido/siifrh/reportarSiif.xhtml?faces-redirect=true";
	}

	public String generarDetalleSerica() {

		// byte[] layout = serica.getDetalleSerica();

		byte[] layout = serica.getDetallerSericaPeriodo(Integer.valueOf(view.getPeriodoCriterio()),
				view.getAnioCriterio());
		
		InputStream is = new ByteArrayInputStream(layout);
		
		 DefaultStreamedContent dfc = new DefaultStreamedContent(is,
		 TipoArchivo.ZIP.getMIMEType(), "NOM-01229001Q201622O1.zip");
		
		 view.setFile(dfc);
		 view.setPanelResul(Boolean.FALSE);
		 view.setPanelDownload(Boolean.TRUE);

		return "/contenido/siifrh/reportarSiif.xhtml?faces-redirect=true";
	}

	public String irGestionarEncabezado() {
		view.setEncabezadoDTO(ejb.obtenerEncabezado(view.getSiifEncabezadoSelect()));
		view.setPanelEncabezado(Boolean.TRUE);
		return null;
	}

	public String guardarEncabezado() {
		view.setEncabezadoDTO(ejb.actualizarEncabezado(view.getEncabezadoDTO()));
		view.setPanelEncabezado(Boolean.FALSE);
		view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getReporteSiifSelect()));
		return "/contenido/siif/reportarsiiffederales.xhtml?faces-redirect=true";
		// return null;
	}

	public String cancelarEncabezado() {
		view.setPanelEncabezado(Boolean.FALSE);
		return null;
	}

	public void enviar() {
		JSFUtils.infoMessage("Su envio se realizo con exito", "puede continuar");
	}

	public String importarFedCont() {

		if (view.getPanelContrato()) {
			// proceso de contrato
			System.out.println("Procesando Nomina Contrato");
			importarNominaContrato();

		} else if (view.getPanelNomina()) {
			// procesa la nomina
			System.out.println("Procesando Nomina Federal");
			importarNomina();
		}
		return null;
	}

	public String importarNomina() {

		boolean eval = true;
		if (view.getPaqueteEntrada().getDat() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getDat().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo DAT es requerido, El Archivo debe tener la extención *.dat");
			eval = false;
		}
		if (view.getPaqueteEntrada().getTra() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getTra().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo TRA es requerido, El Archivo debe tener la extención *.tra");
			eval = false;
		}

		// if
		// (ArchivoUtil.validarTipoArchivo(view.getPaqueteEntrada().getDat().getFileName(),
		// view.getPaqueteEntrada().getDat()
		// .getContentType(), view.getPaqueteEntrada().getDat().getContents(),
		// "text/plain", ".dat")) {
		// JSFUtils.errorMessage("Extensión invalida", "Verifique la extensión
		// del archivo DAT y/o el formato.");
		// eval = false;
		// }
		//
		// if
		// (ArchivoUtil.validarTipoArchivo(view.getPaqueteEntrada().getDat().getFileName(),
		// view.getPaqueteEntrada().getDat()
		// .getContentType(), view.getPaqueteEntrada().getDat().getContents(),
		// "text/plain", ".tra")) {
		// JSFUtils.errorMessage("Extensión invalida", "Verifique la extensión
		// del archivo TRA y/o el formato.");
		// eval = false;
		// }

		if (eval) {
			LOGGER.info(view.getPaqueteEntrada().getDat().getFileName());
			LOGGER.info(view.getPaqueteEntrada().getTra().getFileName());

			try {
				JSFUtils.infoMessage("En este momento han terminado de subir los archivos y se empieza a procesar", "");
				view.setSiifBitacoraProcesada(ejb.procesarNominaTheosToSIIF(view.getPaqueteEntrada()));
				view.setSiifBitacoraProcesada(ejb.clasificaClaveConceptos(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.clasificaNominaTarjetas(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.clasificaNominaCheques(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.clasificaNominaTarjetas610(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.clasificaNominaNombramientoSubfuente(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.crearEncabezadosSiif(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.asignarEncabezadosDats(view.getSiifBitacoraProcesada()));
				view.setEncabezadoListSiif(ejb.obtenerEncabezadosSiif(view.getSiifBitacoraProcesada()));
				
				view.setSiifBitacoraProcesada(ejb.procesarNominaTheosToSIIF_3(view.getEncabezadoListSiif(),view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.calcularEncabezados(view.getSiifBitacoraProcesada()));
				view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getSiifBitacoraProcesada()));
				view.setPanelUpload(Boolean.FALSE);
				view.setPanelResume(Boolean.TRUE);

				JSFUtils.infoMessage("En este momento se han terminado de procesar los archivos", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String importarNominaContrato() {
		boolean eval = true;
		if (view.getPaqueteEntrada().getCont() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getCont().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo XLS es requerido, El Archivo debe tener la extención *.xls");
			eval = false;
		}

		if (eval) {
			LOGGER.info(view.getPaqueteEntrada().getCont().getFileName());

			try {
				JSFUtils.infoMessage("En este momento han terminado de subir los archivos y se empieza a procesar", "");
				view.setSiifBitacoraProcesada(ejb.procesarNominaContTheosToSIIF(view.getPaqueteEntrada()));
				view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getSiifBitacoraProcesada()));
				view.setPanelUpload(Boolean.FALSE);
				view.setPanelResume(Boolean.TRUE);

				JSFUtils.infoMessage("En este momento se han terminado de procesar los archivos", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String cancelar() {
		view.panelPrincipal();
		view.setListReporteSiif(ejb.obtenerReporteSiifPorPeriodo(view.getPeriodoCriterio(), view.getAnioCriterio()));
		return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
	}

	public String cancelarRH() {
		view.panelPrincipal();
		view.setListReporteSiif(ejb.obtenerReporteSiifPorPeriodo(view.getPeriodoCriterio(), view.getAnioCriterio()));
		return "/contenido/siifrh/reportarSiif.xhtml?faces-redirect=true";
	}

	public String cancelarDatTra() {
		view.panelPrincipal();
		view.setListReporteSiif(ejb.obtenerReporteSiifPorPeriodo(view.getPeriodoCriterio(), view.getAnioCriterio()));
		return "/contenido/siif/reporteDatTra.xhtml?faces-redirect=true";
	}

	public String retroceder() {
		view.panelPrincipal();
		return "/contenido/siif/reportarsiiffederales.xhtml?faces-redirect=true";
	}

	public String consultarNomina() {
		view.panelPrincipal();
		view.setListReporteSiif(ejb.obtenerReporteSiifPorPeriodo(view.getPeriodoCriterio(), view.getAnioCriterio()));
		return null;
	}

	public void onRowSelectReporteSiif(SelectEvent event) {
		view.setDisabledImportar(Boolean.FALSE);
		view.setPanelElimina(Boolean.FALSE);
	}

	public void onRowUnselectReporteSiif(UnselectEvent event) {
		view.setDisabledImportar(Boolean.TRUE);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public ReporteSiifView getView() {
		return view;
	}

	// > > > > Validadores < < < <

	public void validatorReporteSiif(FacesContext context, UIComponent component, Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "anioCriterio":
			Integer anioCriterio = (Integer) value;
			if (!ValidacionUtil.esNumeroPositivo(anioCriterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			} else {
				if (anioCriterio < 999) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese 4 digitos");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				} else {
					if (anioCriterio > 9999) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"Por favor ingrese 4 digitos");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}
			}
			break;
		case "periodoCriterio":
			String periodoCriterio = (String) value;
			if (ValidacionUtil.esCadenaVacia(periodoCriterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "idCuentaBancaria":
			Integer idCuentaBancaria = (Integer) value;
			if (!ValidacionUtil.esNumeroPositivo(idCuentaBancaria)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione una Cuenta Bancaria.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "idTipoNomina":
			Integer idTipoNomina = (Integer) value;
			if (!ValidacionUtil.esNumeroPositivo(idTipoNomina)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor Seleccione un Tipo de Nomina.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "idNominaEncabezado":
			Integer ID = (Integer) value;
			if (!ValidacionUtil.esNumeroPositivo(ID)) {
				System.out.println("Si entro");
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un ID de Nomina Correcto");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}			break;
		default:
			break;
		}
	}
}
