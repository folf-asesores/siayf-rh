
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "fuenteFinanciamientoOPD")
@ViewScoped
public class FuenteFinanciamientoOPDController {

    private FuenteFinanciamientoOPDView view;
    @Inject
    private FuenteFinanciamientoEJB ejb;

    @PostConstruct
    public void initFuenteFinanciamientoOPD() {
        view = new FuenteFinanciamientoOPDView();
        irPrincipal();
    }

    public FuenteFinanciamientoOPDView getView() {
        return view;
    }

    //	Los diferentes Regresos

    public String irPrincipal() {
        view.setListFuenteFinanciamientoOPDLista(
                ejb.obtenerFuenteFinanciamientoOPDLista());
        view.setListFuenteFinanciamiento(
                ejb.obtenerFuenteFinanciamientoLista());
        view.panelPrincipal();
        return null;
    }

    public String irGestionarFuenteFinanciamientoOPD() {
        view.setFuenteFinanciamientoOPD(ejb.obtenerFuenteFinanciamientoOPD(
                view.getFuenteFinanciamientoOPDSelect()));
        view.setListFuenteFinanciamiento(
                ejb.obtenerFuenteFinanciamientoLista());
        view.setOperacionNuevoOPD(Boolean.FALSE);
        view.panelGestion();
        return null;
    }

    public String irOPD() {
        return "contenido/configuracion/fuenteFinanciamientoODP.xhtml?faces-redirect=true";
    }

    public String irSub() {
        return "contenido/configuracion/subfuenteFinanciamiento.xhtml?faces-redirect=true";
    }

    public String irFF() {
        return "contenido/configuracion/fuenteFinanciamiento.xhtml?faces-redirect=true";
    }

    // Inicia Procesos de Fuente Financiamiento OPD

    public String irNuevoFuenteFinanciamientoOPD() {
        view.setFuenteFinanciamientoOPD(ejb.nuevoFuenteFinanciamientoOPD());
        view.setOperacionNuevoOPD(Boolean.TRUE);
        view.panelGestion();
        return null;
    }

    public void eliminarFuenteFinanciamientoOPD() {
        ejb.eliminarFuenteFinanciamientoOPD(
                view.getFuenteFinanciamientoOPDSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String guardarFuenteFinanciamientoOPD() {
        if (view.getOperacionNuevoOPD()) {
            ejb.crearFuenteFinanciamientoOPD(view.getFuenteFinanciamientoOPD());
        } else {
            ejb.actualizarFuenteFinanciamientoOPD(
                    view.getFuenteFinanciamientoOPD());
        }
        view.panelGestion();
        irPrincipal();
        return null;
    }

    // Inician los procesos botones dinamicos

    public void onRowSelectFuenteFinanciamientoOPD(SelectEvent event) {
        view.setDisabledIrGestionarOPD(Boolean.FALSE);
    }

    public void onRowUnselectFuenteFinanciamientoOPD(UnselectEvent event) {
        view.setDisabledIrGestionarOPD(Boolean.TRUE);
    }

    //	>>>>>Validadores<<<<<

    public void validatorFuenteOPD(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "Descripcion":
                String Descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(Descripcion)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una Descripción");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "idFF":
                Integer idFF = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(idFF)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Fuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                break;
        }
    }
}