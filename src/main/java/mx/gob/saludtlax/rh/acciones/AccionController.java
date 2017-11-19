
package mx.gob.saludtlax.rh.acciones;

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

import org.primefaces.event.RowEditEvent;

import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.areas.Areas;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean
@ViewScoped
public class AccionController implements Serializable {

    private static final long serialVersionUID = 1708501946095782317L;

    @Inject
    Accion acciones;

    @Inject
    Areas areaEJB;

    private List<AccionDTO> listaAcciones = new ArrayList<>();
    private List<AreaDTO> listaAreas = new ArrayList<>();
    private AccionDTO accionSeleccionada;
    private AccionDTO accionNew = new AccionDTO();

    @PostConstruct
    public void inicio() {
        List<AccionDTO> accionesTemp = acciones.obtenerListaAcciones();
        listaAcciones.clear();
        listaAcciones.addAll(accionesTemp);
        System.out.println("1");
        List<AreaDTO> list = areaEJB.obtenerAreas();
        listaAreas.clear();
        listaAreas.addAll(list);

    }

    public void validatorAccion(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "claveAccion":
                String claveAccion = (String) value;

                if (ValidacionUtil.esCadenaVacia(claveAccion)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una clave.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "descripcion":
                String descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(descripcion)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese descripcion.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idArea":
                Integer idPeriodoCalendario = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idPeriodoCalendario)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seleccione una area.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public void agregarAccion() {
        acciones.crearAccion(accionNew);
        inicio();
        accionNew = new AccionDTO();
    }

    public void onRowEdit(RowEditEvent event) {

        try {

            AccionDTO accion = ((AccionDTO) event.getObject());
            acciones.editarAccion(accion);

            FacesMessage msg = new FacesMessage("Actualizado:", ((AccionDTO) event.getObject()).getClave());
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (BusinessException ex) {
            JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada:", ((AccionDTO) event.getObject()).getClave());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarAccion() {
        System.out.println("elimando accion: " + accionSeleccionada.getIdAccion());
        Boolean res = acciones.eliminarAccion(accionSeleccionada.getIdAccion());
        if (!res) {
            JSFUtils.warningMessage("", "EL registro de accion no se puede eliminar ya que se encuentra usado por configuraciones de modulos.");
        }

        inicio();
    }

    public List<AreaDTO> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<AreaDTO> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<AccionDTO> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<AccionDTO> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public AccionDTO getAccionSeleccionada() {
        return accionSeleccionada;
    }

    public void setAccionSeleccionada(AccionDTO accionSeleccionada) {
        this.accionSeleccionada = accionSeleccionada;
    }

    public AccionDTO getAccionNew() {
        return accionNew;
    }

    public void setAccionNew(AccionDTO accionNew) {
        this.accionNew = accionNew;
    }

}
