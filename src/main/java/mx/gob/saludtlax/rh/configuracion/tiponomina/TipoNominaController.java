
package mx.gob.saludtlax.rh.configuracion.tiponomina;

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

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "tipoNomina")
@ViewScoped
public class TipoNominaController {

    private TipoNominaView view;
    @Inject
    private TipoNominaEJB ejb;

    private Boolean dialogo;

    @PostConstruct
    public void initCuentaBancaria() {
        view = new TipoNominaView();
        irPrincipal();
        cargarListaCatalogo();
    }

    public String irPrincipal() {
        view.setListTipoNomina(ejb.obtenerTipoNominaLista());
        // view.setTipoNominaLista(ejb.obtenerListaTipoNomina());
        view.panelPrincipal();
        return null;
    }

    public void cargarListaCatalogo() {
        view.setListClasificacion(ejb.obtenerClasificacionLista());
        view.setListFuente(ejb.obtenerFuenteLista());
        view.setListSubfuente(ejb.obtenerSubfuenteLista());
        view.setListTipoRecurso(ejb.obtenerListaTipoRecurso());
    }

    public String irNuevoTipoNomina() {
        view.setTipoNomina(ejb.nuevoTipoNomina());
        view.setOperacionNuevo(Boolean.TRUE);
        view.setOpEliminar(Boolean.FALSE);
        cargarListaCatalogo();
        view.panelGestion();
        return null;
    }

    public void eliminarTipoNomina() {
        ejb.eliminarTipoNomina(view.getTipoNominaSelect());
        dialogo = Boolean.FALSE;

        view.panelPrincipal();
        irPrincipal();
        JSFUtils.infoMessage("Eliminación Tipo Nomina: ", "Se realizo correctamente.");
    }

    public String irGestionarTipoNomina() {
        view.setTipoNomina(ejb.obtenerTipoNomina(view.getTipoNominaSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.setOpEliminar(Boolean.TRUE);
        view.setListClasificacion(ejb.obtenerClasificacionLista());
        view.setListFuente(ejb.obtenerFuenteLista());
        view.setListSubfuente(ejb.obtenerSubfuenteLista());
        view.panelGestion();

        return null;
    }

    public TipoNominaView getView() {
        return view;
    }

    public void guardarTipoNomina() {
        if (view.getOperacionNuevo()) {
            ejb.crearTipoNomina(view.getTipoNomina());
            JSFUtils.infoMessage("Registro Tipo Nomina: ", "Se realizo correctamente.");
        } else {
            ejb.actualizarTipoNomina(view.getTipoNomina());
            JSFUtils.infoMessage("Actualización Tipo Nomina: ", "Se realizo correctamente.");
        }
        view.panelGestion();
        irPrincipal();

    }

    public void mostrarDialogo() {
        dialogo = Boolean.TRUE;
    }

    public void ocultarDialogo() {
        dialogo = Boolean.FALSE;
    }

    public void onRowSelectTipoNomina(SelectEvent event) {
        view.setDisabledIrGestionar(Boolean.FALSE);
    }

    public void onRowUnselectTipoNomina(UnselectEvent event) {
        view.setDisabledIrGestionar(Boolean.TRUE);
    }

    public Boolean getDialogo() {
        return dialogo;
    }

    public void setDialogo(Boolean dialogo) {
        this.dialogo = dialogo;
    }

    // <<<<<<Validator>>>>>>

    public void validatorTipo(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "Descripcion":
                String Descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(Descripcion)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una descripcion.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "Idta":
                String Idta = (String) value;

                if (ValidacionUtil.esCadenaVacia(Idta)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un tipo de afectación.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "idc":
                String idc = (String) value;

                if (ValidacionUtil.esCadenaVacia(idc)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una clasificación.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "Idr":
                //			boolean Idr = (boolean) value;

                // if (Idr) {
                // FacesMessage facesMessage1 = new
                // FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                // "Por favor seleccione si es reposicion.");
                // context.addMessage(component.getClientId(), facesMessage1);
                // throw new ValidatorException(facesMessage1);
                // }
                break;
            default:
                break;
        }

    }
}