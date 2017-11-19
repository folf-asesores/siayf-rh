/*
 *
 */

package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@ManagedBean(name = "historialPago")
@ViewScoped
public class HistorialPagoController implements Serializable {

    private static final long serialVersionUID = 2781958930360373395L;

    @Inject
    private Empleado empleado;

    private HistorialPagoView view;

    @PostConstruct
    public void init() {
        view = new HistorialPagoView();
    }

    /**
     * Consulta el empleado
     *
     */
    public void obtenerConsultaEmpleado() {

        view.getFiltro()
                .setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
        List<InfoEmpleadoDTO> listaInfoEmpleado = empleado
                .consultaEmpleado(view.getFiltro());

        if (!listaInfoEmpleado.isEmpty()) {

            view.setEmpleados(listaInfoEmpleado);
            view.setMostrarResultadoConsulta(true);

        } else {
            view.setEmpleados(new ArrayList<InfoEmpleadoDTO>());
            view.setMostrarResultadoConsulta(false);

            view.setFiltro(new FiltroDTO());
            JSFUtils.errorMessage("Consulta Empleado: ",
                    "No se encontrarón resultados, intentelo de nuevo");
        }
    }

    public void descargarProductoNomina(Integer idEmpleado) {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "historial_pago", "TIPO_REPORTE", "xlsx", "ID_EMPLEADO",
                    String.valueOf(idEmpleado) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            byte[] bytes = null;

            bytes = admintradorReportes.obtenerReporte(referencia);

            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes,
                        CadenaUtil.converterSpace("Historiales_de_Pagos"),
                        TipoArchivo.getMIMEType("xlsx"));

            }

            JSFUtils.infoMessage("Descargar Historial Pago: ",
                    "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {

            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {

            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    /**
     * Validator Consulta del empleado
     *
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    public void validarConsulta(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String nombreComponente = component.getId();
        String contexto = "Campo requerido.";

        switch (nombreComponente) {

            case "criterio":

                String criterio = String.valueOf(value);

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, contexto,
                            "Ingrese el criterio");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                if (criterio.trim().length() < 4) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, contexto,
                            "El criterio de la busqueda debe contener minimo 4 letras");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            default:
                break;
        }

    }

    /**
     * @return the view
     */
    public HistorialPagoView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(HistorialPagoView view) {
        this.view = view;
    }

}
