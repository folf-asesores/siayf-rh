
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
        this.view = new EjecutarProductoNominaView();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Object idBuzonObj = httpSession.getAttribute("idBuzon");

        if (idBuzonObj != null) {
            Integer idBuzon = Integer.valueOf(idBuzonObj.toString());
            Integer idEntidadContexto = this.autorizaciones.obtenerIdEntidadContexto(idBuzon);
            this.view.setProductoNomina(this.ejb.obtenerProductoNomina(idEntidadContexto));
        } else {
            Object idProductoNominaObj = httpSession.getAttribute("idProductoNomina");
            if (idProductoNominaObj != null) {
                Integer idProductoNomina = Integer.valueOf(idProductoNominaObj.toString());
                this.view.setProductoNomina(this.ejb.obtenerProductoNomina(idProductoNomina));
            }
        }
        if (this.view.getProductoNomina() != null) {
            this.view.setSubfuenteFinanciamientoList(this.ejb.obtenerSubfuenteFinanciamientoNominaList(this.view.getProductoNomina()));
            this.view.setFuenteFinanciamientoList(this.ejb.obtenerFuentesFinanciamientoNominaList());
            this.view.setEstatusProductoNominaLista(this.ejb.obtenerEstatusProductoNominaList());
            this.view.setCuentaBancariaList(this.ejb.obtenerCuentaBancariaList(this.view.getProductoNomina()));
            this.view.setBancoList(this.ejb.obtenerBancoList());
            this.view.setPagoNominaList(this.ejb.obtenerPagosNomina(this.view.getProductoNomina()));
            this.view.setEditar(this.view.getProductoNomina().getIdEstatusProductoNomina() > 5);
            irPrincipal();
        }
    }

    public String irPrincipal() {
        if (this.view.getProductoNomina().getIdEstatusProductoNomina().equals(EstatusProductoNomina.PREAUTORIZADO)) {
            this.view.setUsuarioAutoriza(this.ejb.esUsuarioAutorizaNomina(this.view.getProductoNomina()));
        }
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.setPagoNominaList(this.ejb.obtenerPagosNomina(this.view.getProductoNomina()));
        this.view.showPanelPrincipal();
        return null;
    }

    public String irProductoNomina() {
        this.view.showPanelPrincipal();
        return null;
    }

    public String irPrincipalFiltro() {
        return "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
    }

    public String pedirAutorizacionProductoNomina() {
        System.out.println("pedirAutorizacionProductoNomina");
        this.ejb.pedirAutorizacionProductoNomina(this.view.getProductoNomina());
        JSFUtils.infoMessage("", "La solicitud de autorización ha sido procesada con éxito.");
        this.view.setProductoNomina(this.ejb.obtenerProductoNomina(this.view.getProductoNomina().getIdProductoNomina()));
        this.view.setEditar(Boolean.FALSE);
        return null;
    }

    public String autorizarProductoNomina() {
        System.out.println("autorizarProductoNomina");
        this.ejb.autorizarProductoNomina(this.view.getProductoNomina());
        JSFUtils.infoMessage("", "La autorización ha sido procesada con éxito.");
        this.view.setProductoNomina(this.ejb.obtenerProductoNomina(this.view.getProductoNomina().getIdProductoNomina()));
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.setEditar(Boolean.FALSE);
        return null;
    }

    public String devolverProductoNomina() {
        System.out.println("devolverProductoNomina");
        this.ejb.devolverProductoNomina(this.view.getProductoNomina());
        JSFUtils.infoMessage("", "La devolvolución ha sido procesada con éxito.");
        this.view.setProductoNomina(this.ejb.obtenerProductoNomina(this.view.getProductoNomina().getIdProductoNomina()));
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.setEditar(Boolean.FALSE);
        return null;
    }

    public String irDetalleNomina(NominaEmpleadoDTO dto) {
        this.view.setNominaEmpleadoSelect(this.ejb.obtenerNominaEmpleadoDetalle(dto));
        this.view.showPanelDetalle();
        obtenerEstatusProductoNomina();
        return null;
    }

    public String irGestionarConcepto(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        this.view.setConceptosNominaSelect(conceptoNominaEmpleado);
        List<FaltaContadaDTO> faltasContadas = (this.ejb.obtenerFaltasContadas(conceptoNominaEmpleado));
        List<FaltaContadaDTO> faltasNoContadas = (this.ejb.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado));
        this.view.setFaltasGestionar(new DualListModel<>(faltasContadas, faltasNoContadas));
        this.view.showGestionFaltas();
        return null;
    }

    public void descargarComprobantes(Integer idProductoNomina) {
        LOGGER.debug("idProductoNomina:: " + idProductoNomina);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "comprobante_nomina", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        this.reporteBytes = adm.obtenerReporte(referencia);
        this.reporteNombre = "comprobates-empleados.txt";
        this.reporteMimeType = "text/plain";
    }

    public String irPensiones() {
        this.view.setPensionesNominaList(this.ejb.obtenerPensionesNominaList(this.view.getProductoNomina()));
        this.view.showPanelPension();
        return null;
    }

    public String irCheques() {
        this.view.setUltimoNumeroCheque(this.ejb.obtenerUltimoNumeroCheque(this.view.getProductoNomina()));
        this.view.getProductoNomina().setNumeroInicioCheques(this.view.getUltimoNumeroCheque() + 1);
        this.view.showPanelCheques();
        return null;
    }

    public String generarNumeracionCheques() {
        this.ejb.generarNumeracionCheques(this.view.getProductoNomina());
        JSFUtils.infoMessage("La generación de numeración de cheques ", "Se realizo correctamente.");
        return null;
    }

    public void descargarListadoFirmas(Integer idProductoNomina) {
        LOGGER.info("idProductoNomina:: " + idProductoNomina);

        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "listado-firmas", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        this.reporteBytes = adm.obtenerReporte(referencia);
        this.reporteNombre = "listado-firmas.txt";
        this.reporteMimeType = "text/plain";
    }

    public void descargarNominaFederales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();

        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "nomina_federales", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", this.view.getProductoNomina().getIdProductoNomina().toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        this.reporteBytes = adm.obtenerReporte(referencia);
        this.reporteNombre = "nomina-eventuales.txt";
        this.reporteMimeType = "text/plain";
    }

    public void descargarNominaEventuales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "nomina_eventuales", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", this.view.getProductoNomina().getIdProductoNomina().toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        this.reporteBytes = adm.obtenerReporte(referencia);
        this.reporteNombre = "nomina-eventuales.txt";
        this.reporteMimeType = "text/plain";
    }

    public void descargarNominaSuplencia(Integer idProductoNomina) {
        System.out.println("idProductoNomina:: " + idProductoNomina);

        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        System.out.println("usuario:: " + usuario);

        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "nomina_suplencias", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        this.reporteBytes = adm.obtenerReporte(referencia);
        this.reporteNombre = "nomina-suplencias.txt";
        this.reporteMimeType = "text/plain";
    }

    public StreamedContent getReporte() throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.reporteBytes);
        StreamedContent reporteStreamedContent = new DefaultStreamedContent(bis, this.reporteMimeType, this.reporteNombre);
        return reporteStreamedContent;
    }

    /**
     * Cambia el estatus nomina empleado en retenido
     */
    public void retenerNominaEmpleado() {
        try {

            Integer idNominaEmpleado = this.view.getNominaEmpleadoSelect().getIdNominaEmpleado();
            Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
            this.ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.RETENIDO, idNominaEmpleado, idUsuario);

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

            Integer idNominaEmpleado = this.view.getNominaEmpleadoSelect().getIdNominaEmpleado();
            Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
            this.ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.CANCELADO, idNominaEmpleado, idUsuario);
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
            Integer idNominaEmpleado = this.view.getNominaEmpleadoSelect().getIdNominaEmpleado();
            Integer idUsuario = AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario();
            this.ejb.actualizarEstatusNominaEmpleado(EnumEstatusProductoNomina.AUTORIZADO, idNominaEmpleado, idUsuario);
            JSFUtils.infoMessage("Nomina Empleado Habilitado: ", "Se realizo correctamente.");
            obtenerEstatusProductoNomina();
        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void obtenerEstatusProductoNomina() {
        try {
            this.view.setHabilitarOpcionAutorizado(true);
            this.view.setHabilitarOpcionRetener(true);
            Integer idNominaEmpleado = this.view.getNominaEmpleadoSelect().getIdNominaEmpleado();
            Integer estatus = this.ejb.obtenerEstatusPorIdProductoNomina(idNominaEmpleado);
            if (estatus == EnumEstatusProductoNomina.RETENIDO) {
                this.view.setHabilitarOpcionAutorizado(false);
            } else {
                this.view.setHabilitarOpcionRetener(false);
            }

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public String obtenerPagoNomina() {
        this.view.setOperacion(Boolean.FALSE);
        this.view.setPagoNominaSelect(this.ejb.obtenerPagoNomina(this.view.getPagoNominaSelect()));
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoListPorPago(this.view.getPagoNominaSelect()));
        this.view.showPanelPagosForm();
        return null;
    }

    public String irNuevoPagoNomina() {
        this.view.setOperacion(Boolean.TRUE);
        this.view.setPagoNominaSelect(this.ejb.obtenerNuevoPagoNomina(this.view.getProductoNomina()));
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoListSinPago(this.view.getProductoNomina()));
        this.view.showPanelPagosForm();
        return null;
    }

    public String guardarPagoNomina() {
        System.out
                .println("fuente : " + this.view.getPagoNominaSelect().getIdFuenteFinanciamiento() + this.view.getPagoNominaSelect().getFuenteFinanciamiento());
        System.out.println(
                "subfuente : " + this.view.getPagoNominaSelect().getIdSubfuenteFinanciamiento() + this.view.getPagoNominaSelect().getSubfuenteFinanciamiento());
        if (this.view.getOperacion()) {
            this.view.setPagoNominaSelect(this.ejb.crearPagoNomina(this.view.getPagoNominaSelect()));
        } else {
            this.view.setPagoNominaSelect(this.ejb.actualizarPagoNomina(this.view.getPagoNominaSelect()));
        }
        JSFUtils.infoMessage("", "El pago de nómina de guardo con éxito.");
        obtenerPagoNomina();
        return null;
    }

    public String eliminarPagoNomina() {
        this.ejb.eliminarPagoNomina(this.view.getPagoNominaSelect());
        JSFUtils.infoMessage("", "El pago de nómina de elimino con éxito.");
        return irPrincipal();
    }

    public EjecutarProductoNominaView getView() {
        return this.view;
    }
}