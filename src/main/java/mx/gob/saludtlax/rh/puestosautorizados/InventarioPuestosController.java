/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/08/2016 21:25:59
 */
@ViewScoped
@ManagedBean(name = "inventario")
public class InventarioPuestosController implements Serializable {

    private static final long serialVersionUID = 5105057423640347663L;
    private InventarioPuestosView view = new InventarioPuestosView();
    @Inject
    private PuestosAutorizadosEmpleados vacante;
    private static final Logger LOGGER = Logger.getLogger(InventarioPuestosController.class.getName());

    @PostConstruct
    public void inicio() {
        view.setInventarios(vacante.consultarInventariosVacantes());
        view.setMostrarInventario(true);
    }

    public void mostrarResumenCodigos(Integer idTipoContratacionSeleccionado) {
        view.setResumenPuestos(vacante.consultarDetallesCodigosPorContratacion(idTipoContratacionSeleccionado));
        if (!view.getResumenPuestos().isEmpty()) {
            view.setMostrarResumenCodigos(true);
            view.setMostrarInventario(false);
        }
    }

    public void mostrarDetallePuesto(Integer idPuesto) {
        LOGGER.info("idPuesto:: " + idPuesto);
        view.setDetallePuesto(vacante.obtenerPuesto(idPuesto));
        if (view.getDetallePuesto() != null) {
            view.setMostrarResumenCodigos(false);
            view.setMostrarInventario(false);
            view.setMostrarDetalleEmpleados(false);
            view.setMostrarDetallePuesto(true);
        }
    }

    public void mostrarActivas(Integer tipoContratacionSeleccionado) {
        view.setDetallesEmpleados(vacante.porContratacionYEstatus(tipoContratacionSeleccionado, EnumEstatusPuesto.EMPLEADO_ACTIVO));
        if (!view.getDetallesEmpleados().isEmpty()) {
            view.setMostrarDetalleEmpleados(true);
            view.setMostrarInventario(false);
        }
    }

    public void mostrarDisponibles(Integer tipoContratacionSeleccionado) {
        view.setDetallesEmpleados(vacante.porContratacionYEstatus(tipoContratacionSeleccionado, EnumEstatusPuesto.LIBERADA));
        if (!view.getDetallesEmpleados().isEmpty()) {
            view.setMostrarDetalleEmpleados(true);
            view.setMostrarInventario(false);
        }
    }

    public void regresarInventario() {
        view.setMostrarDetalleEmpleados(false);
        view.setMostrarInventario(true);
        view.setMostrarResumenCodigos(false);
    }

    public void regresarEmpleados() {
        view.setMostrarResumenCodigos(false);
        view.setMostrarInventario(false);
        view.setMostrarDetalleEmpleados(true);
        view.setMostrarDetallePuesto(false);
    }

    public void descargarContratoProyeccion(Integer idTipoContratacion) {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "detalle_empleado", "TIPO_REPORTE", "xlsx",
                    "ID_TIPO_CONTRATACION", String.valueOf(idTipoContratacion) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);

            byte[] bytes = null;

            bytes = admintradorReportes.obtenerReporte(referencia);

            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes, CadenaUtil.converterSpace("Detalles_Empleados"), TipoArchivo.getMIMEType("xlsx"));

            }

            JSFUtils.infoMessage("Descarga Detalle Empleado: ", "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException | IOException exception) {

            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {

            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    public InventarioPuestosView getView() {
        return view;
    }

    public void setView(InventarioPuestosView view) {
        this.view = view;
    }

}
