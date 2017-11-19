/*
 *
 */

package mx.gob.saludtlax.rh.reporteslaborales.formatoaltaissste;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Daniela
 *
 */
@ManagedBean(name = "formatoAltaIssste")
@ViewScoped
public class FormatoAltaIsssteController {

    @Inject
    private Empleado empleado;

    private FormatoAltaIsssteView view;

    @PostConstruct
    public void inicio() {
        view = new FormatoAltaIsssteView();
    }

    public void consultarEmpleados() {
        try {
            view.setListaEmpleados(empleado.consultaPorCriterio(view.getCriterio()));

            if (view.getListaEmpleados().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "", "No se encontrarón registros en el criterio" + view.getCriterio());
            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void seleccionEmpleado(InfoEmpleadoDTO empleadoSeleccionar) {
        view.setIdEmpleado(empleadoSeleccionar.getIdEmpleado());
        view.setEmpleadoSeleccionado(empleadoSeleccionar);
        view.setPrincipalDatos(false);
        view.setDatosPersona(true);
        view.setVentanaNuevoReporte(false);
        view.setReporteExitoso(false);
    }

    public void verFormatoAltas() throws IOException {
        try {

            view.setUrlReporte("FormatoAltaIsssteServlet?" + "idEmpleado=" + view.getIdEmpleado());
            view.setPrincipalDatos(false);
            view.setDatosPersona(false);
            view.setVentanaNuevoReporte(true);
            view.setReporteExitoso(true);

        } catch (NullPointerException | IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
        }
    }

    public void validatorConsulta(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterio.trim().length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
                        context.addMessage(component.getClientId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }

                break;
            default:
                JSFUtils.errorMessage("Error: ", "Validar criterio");
                break;
        }
    }

    public void regresar() {
        try {
            JSFUtils.redireccionar("/siayf-rh/contenido/reportesLaborales/reporteFormatoAltaIssste.xhtml?faces-redirect=true");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public FormatoAltaIsssteView getView() {
        return view;
    }

    public void setView(FormatoAltaIsssteView view) {
        this.view = view;
    }
}
