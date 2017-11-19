/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@ManagedBean(name = "calendarioGlobal")
@ViewScoped
public class CalendarioGlobalController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2593671016077613239L;

    @Inject
    private CalendarioGlobal calendarioGlobal;

    private CalendarioGlobalView view;

    @PostConstruct
    public void init() {
        view = new CalendarioGlobalView();

        vistaPrincipal();
    }

    public void vistaPrincipal() {
        view.setListaCalendarioGlobal(
                calendarioGlobal.obtenerListaCalendarioGlobal());
        view.setActualizarCalendarioGlobal(new CalendarioGlobalDTO());
        view.setCreaCalendarioGlobal(new CalendarioGlobalDTO());
        view.setMostrarVistaPrincipal(true);
        view.setMostrarVistaCrear(false);
        view.setMostrarVistaActualizar(false);
        view.setIdCalendarioGlobal(0);
    }

    public void crearCalendarioGlobal() {
        try {

            calendarioGlobal
                    .creaCalendarioGlobal(view.getCreaCalendarioGlobal());

            vistaPrincipal();

            JSFUtils.infoMessage("Calendario Global: ",
                    "¡Se registro correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void actualizarCalendarioGlobal() {
        try {

            calendarioGlobal.actualizarCalendarioGlobal(
                    view.getActualizarCalendarioGlobal());

            vistaPrincipal();

            JSFUtils.infoMessage("Calendario Global: ",
                    "¡Se actualizo correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void eliminarCalendarioGlobal(Integer idCalendarioGlobal) {
        try {

            calendarioGlobal.eliminarCalendarioGlobal(idCalendarioGlobal);

            vistaPrincipal();

            JSFUtils.infoMessage("Calendario Global: ",
                    "¡Se elimino correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void mostrarVistaCrearCalendarioGlobal() {
        view.setCreaCalendarioGlobal(new CalendarioGlobalDTO());
        view.setMostrarVistaPrincipal(false);
        view.setMostrarVistaCrear(true);
        view.setMostrarVistaActualizar(false);
    }

    public void mostrarVistaActualizarCalendarioGlobal(
            CalendarioGlobalDTO dto) {
        view.setActualizarCalendarioGlobal(dto);
        view.setMostrarVistaPrincipal(false);
        view.setMostrarVistaCrear(false);
        view.setMostrarVistaActualizar(true);
    }

    // ----Validaciones--//
    public void validatorRegistro(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "partida8000Crear":
                Integer partida8000 = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(partida8000)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese la partida 8000.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "partida1000Crear":
                Integer partida1000 = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(partida1000)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese la partida 1000.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }

                break;

            case "conceptoCrear":
                String concepto = (String) value;

                if (ValidacionUtil.esCadenaVacia(concepto)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el concepto.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "importeAnualCrear":
                BigDecimal importeAnual = (BigDecimal) value;

                if (importeAnual == BigDecimal.ZERO || importeAnual == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una catidad mayor a 0.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

        }

    }

    /**
     * @return the view
     */
    public CalendarioGlobalView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(CalendarioGlobalView view) {
        this.view = view;
    }

}
