/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.administracionmodulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.areas.Areas;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@ManagedBean(name = "actualizarModulo")
@ViewScoped
public class ActualizarModuloController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6933203945400316147L;

    @Inject
    private Accion accionEJB;
    @Inject
    private Modulos modulos;
    @Inject
    private Areas areaEJB;

    private ActualizarModuloView view;

    private List<AccionDTO> acciones = new ArrayList<>();

    @PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String idModulo = params.get("i");

        if (ValidacionUtil.esCadenaVacia(idModulo)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "administracionModulo.xhtml?faces-redirect=true";
            facesContext.getApplication().getNavigationHandler()
                    .handleNavigation(facesContext, null, outcome);
        } else {
            view = new ActualizarModuloView();
            view.setIdModulo(new Integer(idModulo));
            vistaPrincipal();
        }

    }

    public void vistaPrincipal() {

        view.setActualizarModulo(
                modulos.obtenerModuloPorId(view.getIdModulo()));

        acciones.clear();

        acciones.addAll(view.getActualizarModulo().getAcciones());

        List<AreaDTO> listaAreas = areaEJB.obtenerAreas();

        if (!listaAreas.isEmpty()) {

            view.getListaAreas().clear();

            view.setListaAreas(listaAreas);
        }
    }

    public void registrarAccion() {

        try {
            AccionDTO accionDTO = view.getAccion();

            accionDTO.setIdModulo(view.getIdModulo());
            accionDTO.setIdArea(view.getActualizarModulo().getIdArea());

            accionEJB.crearAccion(accionDTO);

            vistaPrincipal();

            cerrarFormularioAccion();

            JSFUtils.infoMessage("Agregar Acción: ",
                    "¡Se agrego correctamente!");

        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }

    }

    public void actualizarModulo() {
        try {
            ModuloDTO moduloDTO = view.getActualizarModulo();

            moduloDTO.setAcciones(acciones);

            modulos.editarModulo(moduloDTO);
            vistaPrincipal();
            JSFUtils.infoMessage("Actualizar Modulo: ",
                    "¡La actualización se realizo correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }

    }

    public void eliminarAccion(Integer idAccion) {

        Boolean res = accionEJB.eliminarAccion(idAccion);
        if (!res) {
            JSFUtils.warningMessage("",
                    "EL registro de accion no se puede eliminar ya que se encuentra usado por configuraciones de modulos.");
        } else {
            vistaPrincipal();
            JSFUtils.infoMessage("Eliminar Acción: ",
                    "¡Se elimino correctamente!");
        }

    }

    public void validatorModulo(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "nombreModulo":
                String nombre = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombre)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un nombre.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "url":
                String descripcionArea = (String) value;

                if (ValidacionUtil.esCadenaVacia(descripcionArea)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese url.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idArea":
                Integer idPeriodoCalendario = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idPeriodoCalendario)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una area.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public void validatorAccion(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "claveAccion":
                String claveAccion = (String) value;

                if (ValidacionUtil.esCadenaVacia(claveAccion)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una clave.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "descripcion":
                String descripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(descripcion)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese descripcion.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }

        }
    }

    public void onRowEdit(RowEditEvent event) {

        try {

            AccionDTO accion = ((AccionDTO) event.getObject());
            accionEJB.editarAccion(accion);

            vistaPrincipal();

            FacesMessage msg = new FacesMessage("Actualizado:",
                    ((AccionDTO) event.getObject()).getClave());
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Actualizar Acción: ",
                    "No se actualizo la acción.");
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada: ",
                ((AccionDTO) event.getObject()).getClave());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void mostrarFormularioAccion() {
        view.setAccion(new AccionDTO());
        view.setMostrarTablaAccciones(false);
        view.setMostrarFormularioAccion(true);
    }

    public void cerrarFormularioAccion() {
        view.setAccion(new AccionDTO());
        view.setMostrarTablaAccciones(true);
        view.setMostrarFormularioAccion(false);
    }

    /**
     * @return the view
     */
    public ActualizarModuloView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(ActualizarModuloView view) {
        this.view = view;
    }

    /**
     * @return the acciones
     */
    public List<AccionDTO> getAcciones() {
        return acciones;
    }

    /**
     * @param acciones
     *            the acciones to set
     */
    public void setAcciones(List<AccionDTO> acciones) {
        this.acciones = acciones;
    }

}
