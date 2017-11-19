
package mx.gob.saludtlax.rh.configuracion.clasificacionnomina;

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

@ManagedBean(name = "clasificacionNomina")
@ViewScoped
public class ClasificacionNominaController {

    private ClasificacionNominaView view;
    @Inject
    private ClasificacionNominaEJB ejb;

    private Boolean dialogo;

    @PostConstruct
    public void initCuentaBancaria() {
        view = new ClasificacionNominaView();
        irPrincipal();
    }

    public String irPrincipal() {
        view.setListClasificacionNomina(ejb.obtenerClasificacionNominaLista());
        view.panelPrincipal();
        return null;
    }

    public String irNuevoClasificacionNomina() {
        view.setClasificacionNomina(ejb.nuevoClasificacionNomina());
        view.setOperacionNuevo(Boolean.TRUE);
        view.setOpEliminar(Boolean.FALSE);
        view.panelGestion();
        return null;
    }

    public void eliminarClasificacionNomina() {
        ejb.eliminarClasificacionNomina(view.getClasificacionNominaSelect());
        view.panelPrincipal();
        dialogo = Boolean.FALSE;
        irPrincipal();
    }

    public String irGestionarClasificacionNomina() {
        view.setClasificacionNomina(ejb.obtenerClasificacionNomina(view.getClasificacionNominaSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.setOpEliminar(Boolean.TRUE);
        view.panelGestion();
        return null;
    }

    public ClasificacionNominaView getView() {
        return view;
    }

    public String guardarClasificacionNomina() {
        if (view.getOperacionNuevo()) {
            ejb.crearClasificacionNomina(view.getClasificacionNomina());
        } else {
            ejb.actualizarClasificacionNomina(view.getClasificacionNomina());
        }
        view.panelGestion();
        irPrincipal();
        return null;
    }

    public void onRowSelectClasificacionNomina(SelectEvent event) {
        view.setDisabledIrGestionar(Boolean.FALSE);
    }

    public void onRowUnselectClasificacionNomina(UnselectEvent event) {
        view.setDisabledIrGestionar(Boolean.TRUE);
    }

    public Boolean getDialogo() {
        return dialogo;
    }

    public void setDialogo(Boolean dialogo) {
        this.dialogo = dialogo;
    }

    public void mostrarDialogo() {
        dialogo = Boolean.TRUE;
    }

    public void ocultarDialogo() {
        dialogo = Boolean.FALSE;
    }

    public void validatorClasificacion(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "Descripcion":
                String Descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(Descripcion)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese una Descripción");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "ID":
                String ID = (String) value;

                if (ValidacionUtil.esCadenaVacia(ID)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese una ID de la Clasificacion");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (ID.length() > 1) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El ID es de un maximo de 1 caracter");
                        context.addMessage(component.getClientId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }
}