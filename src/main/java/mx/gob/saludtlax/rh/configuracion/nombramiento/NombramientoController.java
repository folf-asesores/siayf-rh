
package mx.gob.saludtlax.rh.configuracion.nombramiento;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "nombramiento")
@ViewScoped
public class NombramientoController {

    private NombramientoView view;
    @Inject
    private TipoNombramientoEJB ejb;

    @PostConstruct
    public void initNombramineto() {
        view = new NombramientoView();
        irPrincipal();
    }

    public String irPrincipal() {
        view.setListNombramiento(ejb.obtenerNombramientoLista());
        view.panelPrincipal();
        return null;
    }

    public NombramientoView getView() {
        return view;
    }

    public void validatorNombramiento(FacesContext context,
            UIComponent component, Object value) {

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
            case "Nombramiento":
                String Nombramineto = (String) value;

                if (ValidacionUtil.esCadenaVacia(Nombramineto)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una Nombramineto");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (Nombramineto.length() > 2) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Nombramineto es de un maximo de 2 caracteres");
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

}