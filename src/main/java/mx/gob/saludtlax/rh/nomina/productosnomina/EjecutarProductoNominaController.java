package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import mx.gob.saludtlax.rh.autorizaciones.Autorizaciones;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author gh
 *
 */
@Named(value = "ejecutarProductoNomina")
@ViewScoped
public class EjecutarProductoNominaController implements Serializable {

	private static final long serialVersionUID = 4613962158818174277L;
	private static final Logger LOGGER = Logger.getLogger(EjecutarProductoNominaController.class.getName());

	@Inject
	private ProductosNominaEJB ejb;
	@Inject
	private Autorizaciones autorizaciones;
	private EjecutarProductoNominaView view;
	private byte[] reporteBytes;
	private String reporteMimeType;
	private String reporteNombre;

	@PostConstruct
	public void init() {
		view = new EjecutarProductoNominaView();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		Object idBuzonObj = httpSession.getAttribute("idBuzon");
		
		if (idBuzonObj != null) {
			Integer idBuzon = Integer.valueOf(idBuzonObj.toString());
			Integer idEntidadContexto = autorizaciones.obtenerIdEntidadContexto(idBuzon);
			view.setProductoNomina(ejb.obtenerProductoNomina(idEntidadContexto));
		} else {
			Object idProductoNominaObj = httpSession.getAttribute("idProductoNomina");
			if (idProductoNominaObj != null) {
				Integer idProductoNomina = Integer.valueOf(idProductoNominaObj.toString());
				view.setProductoNomina(ejb.obtenerProductoNomina(idProductoNomina));
			}
		}
		if (view.getProductoNomina() != null) {
			view.setSubfuenteFinanciamientoList(ejb.obtenerSubfuenteFinanciamientoNominaList(view.getProductoNomina()));
			view.setFuenteFinanciamientoList(ejb.obtenerFuentesFinanciamientoNominaList());
			view.setEstatusProductoNominaLista(ejb.obtenerEstatusProductoNominaList());
			view.setCuentaBancariaList(ejb.obtenerCuentaBancariaList(view.getProductoNomina()));
			view.setBancoList(ejb.obtenerBancoList());
			view.setPagoNominaList(ejb.obtenerPagosNomina(view.getProductoNomina()));
			view.setEditar(view.getProductoNomina().getIdEstatusProductoNomina() > 5);
			irPrincipal();
		}
	}

	public String irPrincipal() {
		if (view.getProductoNomina().getIdEstatusProductoNomina().equals(EstatusProductoNomina.PREAUTORIZADO)) {
			view.setUsuarioAutoriza(ejb.esUsuarioAutorizaNomina(view.getProductoNomina()));
		}
		view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
		view.setPagoNominaList(ejb.obtenerPagosNomina(view.getProductoNomina()));
		view.showPanelPrincipal();
		return null;
	}

	public String irProductoNomina() {
		view.showPanelPrincipal();
		return null;
	}

	public String irPrincipalFiltro() {
		return "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
	}

	public String pedirAutorizacionProductoNomina() {
		System.out.println("pedirAutorizacionProductoNomina");
		ejb.pedirAutorizacionProductoNomina(view.getProductoNomina());
		JSFUtils.infoMessage("", "La solicitud de autorización ha sido procesada con éxito.");
		view.setProductoNomina(ejb.obtenerProductoNomina(view.getProductoNomina().getIdProductoNomina()));
		view.setEditar(Boolean.FALSE);
		return null;
	}

	public String autorizarProductoNomina() {
		System.out.println("autorizarProductoNomina");
		ejb.autorizarProductoNomina(view.getProductoNomina());
		JSFUtils.infoMessage("", "La autorización ha sido procesada con éxito.");
		view.setProductoNomina(ejb.obtenerProductoNomina(view.getProductoNomina().getIdProductoNomina()));
		view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
		view.setEditar(Boolean.FALSE);
		return null;
	}

	public String devolverProductoNomina() {
		System.out.println("devolverProductoNomina");
		ejb.devolverProductoNomina(view.getProductoNomina());
		JSFUtils.infoMessage("", "La devolvolución ha sido procesada con éxito.");
		view.setProductoNomina(ejb.obtenerProductoNomina(view.getProductoNomina().getIdProductoNomina()));
		view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
		view.setEditar(Boolean.FALSE);
		return null;
	}

	public String irDetalleNomina(NominaEmpleadoDTO dto) {
		view.setNominaEmpleadoSelect(ejb.obtenerNominaEmpleadoDetalle(dto));
		view.showPanelDetalle();
		obtenerEstatusProductoNomina();
		return null;
	}

	public String irGestionarConcepto(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
		view.setConceptosNominaSelect(conceptoNominaEmpleado);
		List<FaltaContadaDTO> faltasContadas = (ejb.obtenerFaltasContadas(conceptoNominaEmpleado));
		List<FaltaContadaDTO> faltasNoContadas = (ejb.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado));
		view.setFaltasGestionar(new DualListModel<>(faltasContadas, faltasNoContadas));
		view.showGestionFaltas();
		return null;
	}

	public void descargarComprobantes(Integer idProductoNomina) {
		LOGGER.debug("idProductoNomina:: " + idProductoNomina);
		UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
		AdministradorReportes adm = new AdministradorReportes();
		String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE",
				"comprobante_nomina", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
		String referencia = adm.obtenerReferencia(parametros);
		LOGGER.debugv("Referencia: {0}", referencia);
		reporteBytes = adm.obtenerReporte(referencia);
		reporteNombre = "comprobates-empleados.txt";
		reporteMimeType = "text/plain";
	}

	public String irPensiones() {
		view.setPensionesNominaList(ejb.obtenerPensionesNominaList(view.getProductoNomina()));
		view.showPanelPension();
		return null;
	}

	public String irCheques() {
		view.setUltimoNumeroCheque(ejb.obtenerUltimoNumeroCheque(view.getProductoNomina()));
		view.getProductoNomina().setNumeroInicioCheques(view.getUltimoNumeroCheque() + 1);
		view.showPanelCheques();
		return null;
	}

	public String generarNumeracionCheques() {
		ejb.generarNumeracionCheques(view.getProductoNomina());
		JSFUtils.infoMessage("La generación de numeración de cheques ", "Se realizo correctamente.");
		return null;
	}

	public void descargarListadoFirmas(Integer idProductoNomina) {
		LOGGER.info("idProductoNomina:: " + idProductoNomina);

		UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
		AdministradorReportes adm = new AdministradorReportes();
		String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE",
				"listado-firmas", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };

		String referencia = adm.obtenerReferencia(parametros);
		LOGGER.debugv("Referencia: {0}", referencia);
		reporteBytes = adm.obtenerReporte(referencia);
		reporteNombre = "listado-firmas.txt";
		reporteMimeType = "text/plain";
	}

	public void descargarNominaFederales() {
		UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();

		AdministradorReportes adm = new AdministradorReportes();
		String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE",
				"nomina_federales", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA",
				view.getProductoNomina().getIdProductoNomina().toString() };
		String referencia = adm.obtenerReferencia(parametros);
		LOGGER.debugv("Referencia: {0}", referencia);
		reporteBytes = adm.obtenerReporte(referencia);
		reporteNombre = "nomina-eventuales.txt";
		reporteMimeType = "text/plain";
	}

	public void descargarNominaEventuales() {
		UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
		AdministradorReportes adm = new AdministradorReportes();
		String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE",
				"nomina_eventuales", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA",
				view.getProductoNomina().getIdProductoNomina().toString() };
		String referencia = adm.obtenerReferencia(parametros);
		LOGGER.debugv("Referencia: {0}", referencia);
		reporteBytes = adm.obtenerReporte(referencia);
		reporteNombre = "nomina-eventuales.txt";
		reporteMimeType = "text/plain";
	}

	public void descargarNominaSuplencia(Integer idProductoNomina) {
		System.out.println("idProductoNomina:: " + idProductoNomina);

		UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
		System.out.println("usuario:: " + usuario);

		AdministradorReportes adm = new AdministradorReportes();
		String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE",
				"nomina_suplencias", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
		String referencia = adm.obtenerReferencia(parametros);
		LOGGER.debugv("Referencia: {0}", referencia);
		reporteBytes = adm.obtenerReporte(referencia);
		reporteNombre = "nomina-suplencias.txt";
		reporteMimeType = "text/plain";
	}

	public StreamedContent getReporte() throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(reporteBytes);
		StreamedContent reporteStreamedContent = new DefaultStreamedContent(bis, reporteMimeType, reporteNombre);
		return reporteStreamedContent;
	}

	/**
	 * Cambia el estatus nomina empleado en retenido
	 */
	public void retenerNominaEmpleado() {
		try {

			Integer idNominaEmpleado = view.getNominaEmpleadoSelect().getIdNominaEmpleado();
			Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
			ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.RETENIDO, idNominaEmpleado,idUsuario);

			JSFUtils.infoMessage("Nomina Empleado Retenido: ", "Se realizo correctamente.");

			obtenerEstatusProductoNomina();

		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}
	}

	/**
	 * Cambia el estatus nomina empleado en cancelado
	 */
	public void cancelarNominaEmpleado() {
		try {

			Integer idNominaEmpleado = view.getNominaEmpleadoSelect().getIdNominaEmpleado();
			Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
			ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.CANCELADO, idNominaEmpleado,idUsuario);
			JSFUtils.infoMessage("Nomina Empleado Cancelado: ", "Se realizo correctamente.");
		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}
	}

	/**
	 * Cambia el estatus nomina empleado en autorizado
	 */
	public void autorizarNominaEmpleado() {
		try {
			Integer idNominaEmpleado = view.getNominaEmpleadoSelect().getIdNominaEmpleado();
			Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
			ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.AUTORIZADO, idNominaEmpleado,idUsuario);
			JSFUtils.infoMessage("Nomina Empleado Habilitado: ", "Se realizo correctamente.");
			obtenerEstatusProductoNomina();
		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}
	}

	public void obtenerEstatusProductoNomina() {
		try {
			view.setHabilitarOpcionAutorizado(true);
			view.setHabilitarOpcionRetener(true);
			Integer idNominaEmpleado = view.getNominaEmpleadoSelect().getIdNominaEmpleado();
			Integer estatus = ejb.obtenerEstatusPorIdProductoNomina(idNominaEmpleado);
			if (estatus == EnumEstatusProductoNomina.RETENIDO) {
				view.setHabilitarOpcionAutorizado(false);
			} else {
				view.setHabilitarOpcionRetener(false);
			}

		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}
	}

    public String obtenerPagoNomina() {
    	view.setOperacion(Boolean.FALSE);
        view.setPagoNominaSelect(ejb.obtenerPagoNomina(view.getPagoNominaSelect()));
		view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoListPorPago(view.getPagoNominaSelect()));
        view.showPanelPagosForm();
        return null;
    }

    public String irNuevoPagoNomina() {
    	view.setOperacion(Boolean.TRUE);
        view.setPagoNominaSelect(ejb.obtenerNuevoPagoNomina(view.getProductoNomina()));
		view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoListSinPago(view.getProductoNomina()));
        view.showPanelPagosForm();
        return null;
    }

    public String guardarPagoNomina() {
    	if (view.getOperacion()) {
    		view.setPagoNominaSelect(ejb.crearPagoNomina(view.getPagoNominaSelect()));
        } else {
            view.setPagoNominaSelect(ejb.actualizarPagoNomina(view.getPagoNominaSelect()));
        }
        JSFUtils.infoMessage("", "El pago de nómina de guardo con éxito.");
    	obtenerPagoNomina();
    	return null;
    }

    public String eliminarPagoNomina() {
        ejb.eliminarPagoNomina(view.getPagoNominaSelect());
        JSFUtils.infoMessage("", "El pago de nómina de elimino con éxito.");
        return irPrincipal();
    }

	public EjecutarProductoNominaView getView() {
		return view;
	}
}