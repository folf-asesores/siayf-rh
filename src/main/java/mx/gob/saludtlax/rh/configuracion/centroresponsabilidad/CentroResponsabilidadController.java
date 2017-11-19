
package mx.gob.saludtlax.rh.configuracion.centroresponsabilidad;

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

/**
 *
 * @author José Pablo
 *
 */
@ManagedBean(name = "centroResponsabilidad")
@ViewScoped
public class CentroResponsabilidadController {
    @Inject
    private CentroResponsabilidadView view;
    @Inject
    private CentroResponsabilidadEJB ejb;
    private Boolean dialogo;

    @PostConstruct
    public void initCentroResponsabilidad() {
        view = new CentroResponsabilidadView();
        irPrincipal();
        System.out.println("hola");
    }

    public String irPrincipal() {
        view.setListCentroResponsabilidad(ejb.obtenerCentroresponsabilidadLista());
        view.panelPrincipal();
        return null;
    }

    public String irNuevoCentroresponsabilidad() {
        view.setCentroResponsabilidad(ejb.nuevoCentroresponsabilidad());
        view.setOperacionNuevo(Boolean.TRUE);
        view.setOpEliminar(Boolean.FALSE);
        view.panelGestion();
        return null;
    }

    public void eliminarCentroresponsabilidad(Integer idCentroResponsabilidad) {
        ejb.eliminarCentroresponsabilidad(idCentroResponsabilidad);
        view.panelPrincipal();
        dialogo = Boolean.FALSE;
        irPrincipal();
    }

    public String irGestionarCentroresponsabilidad(CentroResponsabilidadDTO centroSeleccionado) {
        view.setCentroResponsabilidad(ejb.obtenerCuentaBancaria(centroSeleccionado));
        view.setOperacionNuevo(Boolean.FALSE);
        view.setOpEliminar(Boolean.TRUE);
        view.panelGestion();
        return null;
    }

    public CentroResponsabilidadView getView() {
        return view;
    }

    public String guardarCentroresponsabilidad() {
        if (view.getOperacionNuevo()) {
            ejb.crearCentroResponsabilidad(view.getCentroResponsabilidad());
        } else {
            ejb.actualizarCentroResponsabilidad(view.getCentroResponsabilidad());
        }
        view.panelGestion();
        irPrincipal();
        return null;
    }

    public void mostrarDialogo() {
        dialogo = Boolean.TRUE;
    }

    public void ocultarDialogo() {
        dialogo = Boolean.FALSE;
    }

    public void onRowSelectCentroResponsabilidad(SelectEvent event) {
        view.setDisabledIrGestionar(Boolean.FALSE);
    }

    public void onRowUnselectCentroResponsabilidad(UnselectEvent event) {
        view.setDisabledIrGestionar(Boolean.TRUE);
    }

    public Boolean getDialogo() {
        return dialogo;
    }

    public void setDialogo(Boolean dialogo) {
        this.dialogo = dialogo;
    }

    

    public void validatorCentroResponsabilidad(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "Clave":
                Integer Clave = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(Clave)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor introcuzca una clave.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "Adscripcion":
                String Descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(Descripcion)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una adscripción.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                break;
        }
    }
}
