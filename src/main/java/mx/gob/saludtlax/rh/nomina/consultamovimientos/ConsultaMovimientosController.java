
package mx.gob.saludtlax.rh.nomina.consultamovimientos;

/**
 *
 * @author José Pablo
 *
 */
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "consultaMovimientos")
@ViewScoped
public class ConsultaMovimientosController implements Serializable {
    private static final long serialVersionUID = 8404127577835863917L;

    @Inject
    private Empleado empleado;
    @Inject
    private ConsultaMovimientosEJB ejb;
    @Inject
    private ConsultaMovimientosView view;

    @PostConstruct
    public void configuracionNominaEmpleado() {
        view.panelBusqueda();
    }

    public void validatorConsulta(FacesContext context, UIComponent component,
            Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;
                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterio.trim().length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void buscarEmpleado() {
        System.out.println(
                "view.isMostrarBusqueda():: " + view.isMostrarBusqueda());
        try {
            view.panelBusqueda();
            System.out
                    .println("this.view.getCriterio():: " + view.getCriterio());
            view.setEmpleados(empleado.consultaPorCriterio(view.getCriterio()));
            if (view.getEmpleados().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "",
                        "No se encontrarón registros con el criterio "
                                + view.getCriterio());
            } else {
                view.setMostrarResultados(true);
            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
        System.out.println(
                "view.isMostrarBusqueda():: " + view.isMostrarBusqueda());
    }

    public void seleccionarEmpleado(Integer idEmpleadoSeleccionado) {
        try {
            view.panelFormulario();
            view.setEmpleadoDatos(empleado
                    .obtenerInformacionEmpleado(idEmpleadoSeleccionado));
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }

    }

    public ConsultaMovimientosView getView() {
        return view;
    }
}
