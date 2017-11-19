
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFLayout;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * @author José Pablo
 */
@Named(value = "productosNomina")
@SessionScoped
public class ProductosNominaController implements Serializable {

    private static final long serialVersionUID = 350883556927088002L;
    private static final Logger LOGGER = Logger.getLogger(ProductosNominaController.class.getName());

    @Inject
    private ProcesoEJB procesoEjb;
    @Inject
    private ProductosNominaEJB ejb;
    @Inject
    private SIIFLayout generarLayout;
    private ProductosNominaView view;
    private ProductoNominaValidator validator;
    private StreamedContent reporte;

    @PostConstruct
    public void init() {
        this.validator = new ProductoNominaValidator();
        this.view = new ProductosNominaView();
        this.view.setSubfuenteFinanciamientoList(this.ejb.obtenerSubfuenteFinanciamientoNominaList());
        this.view.setFuenteFinanciamientoList(this.ejb.obtenerFuentesFinanciamientoNominaList());
        this.view.setTipoNominaList(this.ejb.obtenerTipoNominaList());
        this.view.setEstatusProductoNominaLista(this.ejb.obtenerEstatusProductoNominaList());
        this.view.setTipoContratacionList(this.ejb.obtenerTipoContratacionList());
        this.reporte = new DefaultStreamedContent();
        irPrincipal();
    }

    public String irPrincipal() {
        this.view.showPanelPrincipal();
        this.view.setFiltro(new ProductoNominaFiltroDTO());
        return "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
    }

    public String abrirNomina() {
        if (this.view.getProductoNomina() == null) {
            JSFUtils.errorMessage("", "No se detecta proceso de nómina para abrir.");
            return null;
        } else {
            this.ejb.abrirProductoNomina(this.view.getProductoNomina());
            this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
            this.view.showPanelPrincipalDetalle();
            this.view.showPanelPrincipal();
            this.view.setMostrarNominaErronea(Boolean.FALSE);
            // if (view.getProductoNomina().getIdTipoContratacion() ==
            // EnumTipoContratacion.SUPLENCIA) {
            // procesoEjb.detenerProceso(2);
            // }
            return "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
        }
    }

    public String abrirNominaPorRfc() {
        if (this.view.getProductoNomina() == null) {
            JSFUtils.errorMessage("", "No se detecta proceso de nómina para abrir.");
            return null;
        } else {
            this.view.showPanelPrincipal();
            return "/contenido/nomina/productos/abrirProductoNominaRfc.xhtml?faces-redirect=true&idProductoNomina="
                    + this.view.getProductoNomina().getIdProductoNomina();
        }
    }

    public String irNuevo() {
        this.view.setOperacion(Boolean.TRUE);
        ProductoNominaDTO productoNomina = new ProductoNominaDTO();
        productoNomina.setIdEstatusProductoNomina(1);
        this.view.setProductoNomina(productoNomina);
        this.view.showPanelForm();
        this.view.setHabilitarEstatus(Boolean.FALSE);
        return null;
    }

    public String irGestionar() {
        this.view.setOperacion(Boolean.FALSE);
        this.view.setProductoNomina(this.ejb.obtenerProductoNomina(this.view.getProductoNominaSelect().getIdProductoNomina()));
        this.view.setTipoPeriodoLista(this.ejb.obtenerTipoPeriodoLista(this.view.getProductoNomina().getEjercicioFiscal()));
        String url = null;
        if (this.view.getProductoNomina().getIdEstatusProductoNomina() == 1) {
            this.view.showPanelForm();
            url = "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
        }
        if (this.view.getProductoNomina().getIdEstatusProductoNomina() > 1 && this.view.getProductoNomina().getIdEstatusProductoNomina() < 5) {
            this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
            this.view.showPanelPrincipalDetalle();
            if (this.view.getProductoNomina().getIdEstatusProductoNomina() > 2) {
                this.view.setNominasErroneas(this.ejb.consultarNominasErroneas(this.view.getProductoNomina().getIdProductoNomina()));
                this.view.setMostrarNominaErronea(!this.view.getNominasErroneas().isEmpty());
            }
            this.view.showPanelPrincipal();
            url = "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
        }
        if (this.view.getProductoNomina().getIdEstatusProductoNomina() >= 5) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("idProductoNomina", this.view.getProductoNomina().getIdProductoNomina());
            this.view.showPanelPrincipal();
            url = "/contenido/nomina/productos/ejecutarProductoNomina.xhtml?faces-redirect=true";
        }
        return url;
    }

    public String irPrincipalDetalleNomina() {
        this.view.showPanelPrincipalDetalle();
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        return "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
    }

    public String irDetalleNomina(NominaEmpleadoDTO dto) {
        this.view.setNominaEmpleadoSelect(this.ejb.obtenerNominaEmpleadoDetalle(dto));
        this.view.setOperacion(Boolean.TRUE);
        this.view.showPanelDetalle();
        return null;
    }

    public String regresarDetalleNomina() {
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.showPanelDetalle();
        return null;
    }

    public String cambiarTipoPeriodoLista() {
        this.view.setTipoPeriodoLista(this.ejb.obtenerTipoPeriodoLista(this.view.getProductoNomina().getEjercicioFiscal()));
        if (this.view.getTipoPeriodoLista().size() == 1) {
            Integer idTipoPeriodo = this.view.getTipoPeriodoLista().get(0).getIdTipoPeriodo();
            this.view.getProductoNomina().setIdTipoPeriodo(idTipoPeriodo);
        }
        return null;
    }

    public String cambiarFechasPeriodos() {
        this.view.getProductoNomina().setIdEjercicioFiscal(this.ejb.obtenerIdEjercicioFiscal(this.view.getProductoNomina()));
        this.view.setProductoNomina(this.ejb.cambiarFechasPerido(this.view.getProductoNomina()));
        return null;
    }

    public String cambiarFuenteFinanciamiento() {
        this.view.setCambiarFuenteFinanciamiento(this.view.getProductoNomina().getCambiarFuenteFinanciamiento());
        return null;
    }

    public String actualizarFuenteFinanciamiento() {
        this.view.setProductoNomina(this.ejb.obtenerFuentePorSubfuente(this.view.getProductoNomina()));
        return null;
    }

    public String actualizarFuenteFinanciamientoFiltro() {
        this.view.setProductoNomina(this.ejb.obtenerFuentePorSubfuente(this.view.getProductoNomina()));
        return null;
    }

    public String irCalcularNomina() {
        this.view.panelCalculoNomina();
        return null;
    }

    public String irActualizarNomina() {
        this.view.setActualizarNominaEmpleadoList(this.ejb.obtenerActualizarNomina(this.view.getProductoNomina()));
        this.view.panelActualizarNomina();
        return null;
    }

    public String actualizarNomina() {
        this.ejb.actualizarNomina(this.view.getActualizarNominaEmpleadoSelectList(), this.view.getProductoNomina());
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.setActualizarNominaEmpleadoList(this.ejb.obtenerActualizarNomina(this.view.getProductoNomina()));
        JSFUtils.infoMessage("", "La actualización de los datos se ha procesado con éxito.");
        return null;
    }

    public String calcularNomina() {
        this.ejb.actualizarProductoNomina(this.view.getProductoNomina());
        this.view.setProductoNomina(this.ejb.calcularProductoNomina(this.view.getProductoNomina()));
        this.view.setNominaEmpleadoList(this.ejb.obtenerNominaEmpleadoList(this.view.getProductoNomina()));
        this.view.setNominasErroneas(this.ejb.consultarNominasErroneas(this.view.getProductoNomina().getIdProductoNomina()));
        this.view.setMostrarNominaErronea(!this.view.getNominasErroneas().isEmpty());
        // procesoEjb.detenerProceso(1);
        JSFUtils.infoMessage("", "El cálculo ha sido procesado con éxito.");
        this.view.showPanelPrincipalDetalle();
        return null;
    }

    public String validarNomina() {
        this.ejb.validarProductoNomina(this.view.getProductoNomina());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idProductoNomina", this.view.getProductoNomina().getIdProductoNomina());
        this.view.showPanelPrincipal();
        return "/contenido/nomina/productos/ejecutarProductoNomina.xhtml?faces-redirect=true";
    }

    public void guardarProductoNomina() {
        if (this.view.getOperacion()) {
            this.view.setProductoNomina(this.ejb.crearProductoNomina(this.view.getProductoNomina()));
            this.view.setOperacion(Boolean.FALSE);
            JSFUtils.infoMessage("El registro producto nómina se ha guardado exitosamente", "");
        } else {
            this.ejb.actualizarProductoNomina(this.view.getProductoNomina());
        }
    }

    public void descargarNominaFederales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        try {
            AdministradorReportes adm = new AdministradorReportes();
            String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "nomina_federales", "TIPO_REPORTE", "txt",
                    "ID_PRODUCTO_NOMINA", this.view.getProductoNomina().getIdProductoNomina().toString() };
            String referencia = adm.obtenerReferencia(parametros);
            LOGGER.debugv("Referencia: {0}", referencia);
            byte[] bytesReporte = adm.obtenerReporte(referencia);
            JSFUtils.descargarArchivo(bytesReporte, "nomina-federales", "text/plain");
        } catch (IOException ex) {
            LOGGER.errorv("Error de lectuta/escritura: {0}", ex);
        }
    }

    public void descargarPrenominaEventuales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "prenomina_eventuales", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", this.view.getProductoNomina().getIdProductoNomina().toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        byte[] bytesReporte = adm.obtenerReporte(referencia);

        ByteArrayInputStream bis = new ByteArrayInputStream(bytesReporte);
        setReporte(new DefaultStreamedContent(bis, "text/plain", "prenomina_" + this.view.getProductoNomina().getNombreProducto() + ".txt"));
    }

    public void descargarNominaSuplencia(Integer idProductoNomina) {
        System.out.println("idProductoNomina:: " + idProductoNomina);

        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        System.out.println("usuario:: " + usuario);
        try {
            AdministradorReportes adm = new AdministradorReportes();
            String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "nomina_suplencias", "TIPO_REPORTE", "txt",
                    "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
            String referencia = adm.obtenerReferencia(parametros);
            LOGGER.debugv("Referencia: {0}", referencia);
            byte[] bytesReporte = adm.obtenerReporte(referencia);
            JSFUtils.descargarArchivo(bytesReporte, "nomina-suplencias", "text/plain");
        } catch (IOException ex) {
            LOGGER.errorv("Error de lectuta/escritura: {0}", ex);
        }
    }

    public void descargarDispersion(Integer idProductoNomina) {
        LOGGER.debugv("idProductoNomina :: {0}", idProductoNomina);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        LOGGER.debugv("usuario :: {0}", usuario);
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", usuario.getIdUsuario().toString(), "REPORTE_NOMBRE", "dispersion_nomina", "TIPO_REPORTE", "xlsx",
                "ID_PRODUCTO_NOMINA", idProductoNomina.toString() };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        byte[] reporteBytes = adm.obtenerReporte(referencia);
        ByteArrayInputStream bis = new ByteArrayInputStream(reporteBytes);
        setReporte(new DefaultStreamedContent(bis, TipoArchivo.XLSX.getMIMEType(), "dispersion-nomina.xlsx"));
    }

    public void descargarProductoNominaFederales() {
        try {
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina_federales", "TIPO_REPORTE", "xlsx",
                    "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);
            byte[] bytes = admintradorReportes.obtenerReporte(referencia);
            if (bytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                String nombreProducto = CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto());
                setReporte(new DefaultStreamedContent(bis, TipoArchivo.getMIMEType("xlsx"), "producto_nomina_federales_" + nombreProducto + ".xlsx"));
                JSFUtils.infoMessage("Descargar Productos Nomina: " + nombreProducto, "Se descargo correctamente.");
            } else {
                JSFUtils.errorMessage("Error: ", "");
            }
        } catch (ReglaNegocioException e) {
            LOGGER.error(e.getMessage(), e);
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void descargarProductoNomina() {
        try {
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina", "TIPO_REPORTE", "xlsx",
                    "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);
            byte[] bytes = admintradorReportes.obtenerReporte(referencia);
            if (bytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                setReporte(new DefaultStreamedContent(bis, TipoArchivo.getMIMEType("xlsx"),
                        "Productos_Nomina_" + this.view.getProductoNomina().getNombreProducto() + ".xlsx"));
                JSFUtils.infoMessage("Descargar Productos Nomina: " + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                        "Se descargo correctamente.");
            } else {
                JSFUtils.errorMessage("Error: ", "");
            }
        } catch (ReglaNegocioException e) {
            LOGGER.error(e.getMessage(), e);
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void descargarProductoNominaProgramas() {
        try {
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina_programas", "TIPO_REPORTE", "xlsx",
                    "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);
            byte[] bytes = admintradorReportes.obtenerReporte(referencia);
            if (bytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                setReporte(new DefaultStreamedContent(bis, TipoArchivo.getMIMEType("xlsx"),
                        "Productos_Nomina_" + this.view.getProductoNomina().getNombreProducto() + ".xlsx"));
                JSFUtils.infoMessage("Descargar Productos Nomina: " + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                        "Se descargo correctamente.");
            } else {
                JSFUtils.errorMessage("Error: ", "");
            }
        } catch (ReglaNegocioException e) {
            LOGGER.error(e.getMessage(), e);
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void descargarProductoNominaRetenido() {
        try {
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina_estatus", "TIPO_REPORTE", "xlsx",
                    "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()), "ID_ESTATUS",
                    String.valueOf(EnumEstatusProductoNomina.RETENIDO) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);
            byte[] bytes = admintradorReportes.obtenerReporte(referencia);
            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes, "Productos_Nomina_Retenido_" + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                        TipoArchivo.getMIMEType("xlsx"));
                JSFUtils.infoMessage("Descargar Productos Nomina: " + this.view.getProductoNomina().getNombreProducto(), "Se descargo correctamente.");
            }
        } catch (NullPointerException | IllegalArgumentException | IOException | ReglaNegocioException | ValidacionException exception) {
            LOGGER.error(exception.getMessage(), exception);
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void descargarProductoNominaCancelado() {
        try {
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina_estatus", "TIPO_REPORTE", "xlsx",
                    "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()), "ID_ESTATUS",
                    String.valueOf(EnumEstatusProductoNomina.CANCELADO) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);
            byte[] bytes = admintradorReportes.obtenerReporte(referencia);
            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes, "Productos_Nomina_Cancelado_" + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                        TipoArchivo.getMIMEType("xlsx"));

                JSFUtils.infoMessage("Descargar Productos Nomina: " + this.view.getProductoNomina().getNombreProducto(), "Se descargo correctamente.");
            }
        } catch (NullPointerException | IllegalArgumentException | IOException | ReglaNegocioException | ValidacionException exception) {
            LOGGER.error(exception.getMessage(), exception);
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void descargarProductoNominaSuplencia() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "producto_nomina_suplencia", "TIPO_REPORTE", "xlsx",
                "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()) };
        AdministradorReportes admintradorReportes = new AdministradorReportes();
        String referencia = admintradorReportes.obtenerReferencia(parametros);
        byte[] bytes = admintradorReportes.obtenerReporte(referencia);
        if (bytes != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            setReporte(new DefaultStreamedContent(bis, TipoArchivo.XLSX.getMIMEType(),
                    "Productos_nomina_suplencia" + this.view.getProductoNomina().getNombreProducto() + ".xlsx"));
            JSFUtils.infoMessage("Descargar Productos Nomina: " + this.view.getProductoNomina().getNombreProducto(), "Se descargo correctamente.");
        } else {
            JSFUtils.errorMessage("Error al generar el reporte: " + this.view.getProductoNomina().getNombreProducto(),
                    "Ocurrio un error inesperado durante la generación del reporte.");
        }
    }

    public void descargarPagoGeneral() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "pago_general", "TIPO_REPORTE", "xlsx",
                "ID_PRODUCTO_NOMINA", String.valueOf(this.view.getProductoNomina().getIdProductoNomina()) };
        AdministradorReportes admintradorReportes = new AdministradorReportes();
        String referencia = admintradorReportes.obtenerReferencia(parametros);
        byte[] bytes = admintradorReportes.obtenerReporte(referencia);

        if (bytes != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            setReporte(new DefaultStreamedContent(bis, TipoArchivo.XLSX.getMIMEType(),
                    "pago_general" + this.view.getProductoNomina().getNombreProducto() + ".xlsx"));
            JSFUtils.infoMessage("Descargar Productos Nomina: " + this.view.getProductoNomina().getNombreProducto(), "Se descargo correctamente.");
        } else {
            JSFUtils.errorMessage("Error al generar el reporte: " + this.view.getProductoNomina().getNombreProducto(),
                    "Ocurrio un error inesperado durante la generación del reporte.");
        }
    }

    public void estaEnProcesoCalculo(int proceso) {
        this.view.setProceso(this.procesoEjb.obtenerProceso(proceso));
    }

    public void filtrarProductoNomina() {
        this.view.setFiltroProductoNominaList(this.ejb.filtrarProductoNomina(this.view.getFiltro()));
    }

    public String irDividirNomina() {
        this.view.setOperacion(Boolean.TRUE);
        this.view.showPanelForm();
        this.view.setHabilitarEstatus(Boolean.FALSE);

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idProductoNomina", this.view.getProductoNomina().getIdProductoNomina());

        return "/contenido/nomina/productos/dividirProductoNomina.xhtml?faces-redirect=true";
    }

    public String irGestionarConcepto(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        this.view.setConceptosNominaSelect(conceptoNominaEmpleado);
        List<FaltaContadaDTO> faltasContadas = (this.ejb.obtenerFaltasContadas(conceptoNominaEmpleado));
        List<FaltaContadaDTO> faltasNoContadas = (this.ejb.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado));
        this.view.setFaltasGestionar(new DualListModel<>(faltasContadas, faltasNoContadas));
        this.view.showGestionFaltas();
        return null;
    }

    public String recalcularNominaEmpleado() {
        this.ejb.recalcularNominaEmpleado(this.view.getProductoNomina(), this.view.getNominaEmpleadoSelect());
        this.view.setNominaEmpleadoSelect(this.ejb.obtenerNominaEmpleadoDetalle(this.view.getNominaEmpleadoSelect()));
        this.view.showPanelDetalle();
        return null;
    }

    public String recalcularConcepto() {
        this.ejb.actualizarConcepto(this.view.getConceptosNominaSelect(), this.view.getFaltasGestionar().getSource());
        this.ejb.recalcularNominaEmpleado(this.view.getProductoNomina(), this.view.getNominaEmpleadoSelect());
        this.view.setNominaEmpleadoSelect(this.ejb.obtenerNominaEmpleadoDetalle(this.view.getNominaEmpleadoSelect()));
        this.view.showPanelDetalle();
        return null;
    }

    public String irPensiones() {
        this.view.setPensionesNominaList(this.ejb.obtenerPensionesNominaList(this.view.getProductoNomina()));
        this.view.showPanelPension();
        return null;
    }

    public String eliminarProductoNomina() {
        this.ejb.eliminarProductoNomina(this.view.getProductoNomina());
        this.view.setFiltroProductoNominaList(new ArrayList<ProductoNominaListaDTO>());
        return irPrincipal();
    }

    public ProductosNominaView getView() {
        return this.view;
    }

    public ProductoNominaValidator getValidator() {
        return this.validator;
    }

    public void generarDatTra() {
        try {
            if (this.generarLayout.verificaProductoNomina(this.view.getProductoNomina().getIdProductoNomina()) <= 0) {
                this.generarLayout.crearDatTraProdNom(this.view.getProductoNomina().getIdProductoNomina());
            }
            if (this.view.getProductoNomina().getIdTipoContratacion() == 1) {
                byte[] layout = this.generarLayout.getDatTraProdNomRH_Cont(this.view.getProductoNomina().getIdProductoNomina());
                // JSFUtils.descargarArchivo(layout, "datytra",
                // TipoArchivo.ZIP.getMIMEType());
                if (layout != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(layout);
                    setReporte(new DefaultStreamedContent(bis, TipoArchivo.ZIP.getMIMEType(),
                            "Dat-Tra_" + this.view.getProductoNomina().getNombreProducto() + ".zip"));
                    JSFUtils.infoMessage("Descargar DAT y TRA: " + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                            "Se descargo correctamente.");
                } else {
                    JSFUtils.errorMessage("Error: ", "");
                }
            } else {
                byte[] layout = this.generarLayout.getDatTraProdNomRH(this.view.getProductoNomina().getIdProductoNomina());
                // JSFUtils.descargarArchivo(layout, "datytra",
                // TipoArchivo.ZIP.getMIMEType());
                if (layout != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(layout);
                    setReporte(new DefaultStreamedContent(bis, TipoArchivo.ZIP.getMIMEType(),
                            "Dat-Tra_" + this.view.getProductoNomina().getNombreProducto() + ".zip"));
                    JSFUtils.infoMessage("Descargar DAT y TRA: " + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                            "Se descargo correctamente.");
                } else {
                    JSFUtils.errorMessage("Error: ", "");
                }
            }
        } catch (ReglaNegocioException e) {
            LOGGER.error(e.getMessage(), e);
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

    public StreamedContent getReporte() {
        return this.reporte;
    }

    public void generarLayout() {
        try {
            byte[] layout = this.generarLayout.getLayoutComoZipRH(this.view.getProductoNomina().getIdProductoNomina());
            //JSFUtils.descargarArchivo(layout, "layout", TipoArchivo.ZIP.getMIMEType());
            if (layout != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(layout);
                setReporte(
                        new DefaultStreamedContent(bis, TipoArchivo.ZIP.getMIMEType(), "Layout_" + this.view.getProductoNomina().getNombreProducto() + ".zip"));
                JSFUtils.infoMessage("Descargar Layout: " + CadenaUtil.converterSpace(this.view.getProductoNomina().getNombreProducto()),
                        "Se descargo correctamente.");
            } else {
                JSFUtils.errorMessage("Error: ", "");
            }
        } catch (ReglaNegocioException e) {
            LOGGER.error(e.getMessage(), e);
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void generarSeguroPopular() {
        try {
            byte[] layout = this.generarLayout.getLayoutSeguroPopularRH(this.view.getProductoNomina().getIdProductoNomina());
            JSFUtils.descargarArchivo(layout, "seg-popular", TipoArchivo.ZIP.getMIMEType());
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    public void generarSeguroPopularRH() {
        try {
            String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "seguro_popular", "TIPO_REPORTE", "xlsx", };

            AdministradorReportes admin = new AdministradorReportes();
            String referencia = admin.obtenerReferencia(parametros);
            byte[] result = admin.obtenerReporte(referencia);
            JSFUtils.descargarArchivo(result, "seguro-popular-reporte", TipoArchivo.XLSX);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

}
