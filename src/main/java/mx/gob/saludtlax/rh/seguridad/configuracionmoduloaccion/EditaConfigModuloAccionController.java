/**
 *
 */
package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

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

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "editarConfigModuloAccion")
@ViewScoped
public class EditaConfigModuloAccionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5606590257873527391L;

    // private static final Logger LOGGER =
    // Logger.getLogger(CrearConfigModuloAccionController.class);
    @Inject
    private ConfiguracionModuloAccion configuracionModuloAccion;
    @Inject
    private Accion accionEJB;
    @Inject
    private Modulos moduloEJB;

    private EditarConfigModuloAccionView view;

    /**
     * Valida si hay un id de la configuración
     */
    @PostConstruct
    public void init() {

        this.view = new EditarConfigModuloAccionView();

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String idConfigModuloAccion = params.get("i");

        if (!ValidacionUtil.esCadenaVacia(idConfigModuloAccion)) {

            this.view.setIdConfigModuloAccion(new Integer(idConfigModuloAccion));
            vistaPrincipal();
        }

    }

    /**
     * Construye la Vista principal
     */
    public void vistaPrincipal() {

        try {
            this.view.setPanelPrincipal(true);

            List<ModuloDTO> listaModulos = moduloEJB.listaModulos();
            this.view.setListaModulos(listaModulos);

            ConfiguracionModuloAccionDTO dto = configuracionModuloAccion
                    .obtenerConfAccModPorId(this.view.getIdConfigModuloAccion());

            this.view.setConfigModuloAccionEditar(dto);

            ModuloDTO modulo = new ModuloDTO();

            for (ModuloDTO mod : this.view.getListaModulos()) {
                if (mod.getIdModulo()
                        .compareTo(this.view.getConfigModuloAccionEditar().getModulo().getIdModulo()) == 0) {
                    modulo = mod;
                }
            }

            // Setea el id del modulo para luego compararlo
            this.view.setIdModuloConparator(this.view.getConfigModuloAccionEditar().getModulo().getIdModulo());

            //Identificadores para realizar la busqueda de acciones filtradas
            Integer idModulo = modulo.getIdModulo();
            List<Integer> idAccionFiltro = new ArrayList<>();

            for (AccionDTO ac : this.view.getConfigModuloAccionEditar().getAcciones()) {
                idAccionFiltro.add(ac.getIdAccion());
            }

            List<AccionDTO> accionSource = accionEJB.obtenerAccionesFiltradas(idModulo, idAccionFiltro);

            List<AccionDTO> accionTarget = this.view.getConfigModuloAccionEditar().getAcciones();

            // Construye el piklist
            this.view.setPikListAcciones(new DualListModel<>(accionSource, accionTarget));

        } catch (ReglaNegocioException | ValidacionException exception) {
            this.view.setPanelPrincipal(false);
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }

    }

    /**
     * *
     * Selecciona el modulo y construye el piklist
     */
    public void accionesPorModuloSeleccionado() {
        // si la configuracion es la misma refresca la vista al principal
        if (this.view.getConfigModuloAccionEditar().getModulo().getIdModulo()
                .equals(this.view.getIdModuloConparator())) {
            vistaPrincipal();
        } else {

            ModuloDTO modulo = new ModuloDTO();

            for (ModuloDTO mod : this.view.getListaModulos()) {
                if (mod.getIdModulo()
                        .compareTo(this.view.getConfigModuloAccionEditar().getModulo().getIdModulo()) == 0) {
                    modulo = mod;
                }
            }

            List<AccionDTO> accionSource = accionEJB.obtenerListaAccionesPorModulo(modulo.getIdModulo());
            List<AccionDTO> accionTarget = new ArrayList<>();

            this.view.setPikListAcciones(new DualListModel<>(accionSource, accionTarget));

        }

    }

    /**
     * Actualiza la configuración
     */
    public void actualizarConfiguracionModuloAccion() {

        List<AccionDTO> acciones = (List<AccionDTO>) this.view.getPikListAcciones().getTarget();

        this.view.getConfigModuloAccionEditar().setAcciones(acciones);

        configuracionModuloAccion.editar(this.view.getConfigModuloAccionEditar());

        JSFUtils.infoMessage("Configuración: ", "¡Se actualizo correctamente!");

        vistaPrincipal();

    }

    public void validatorConfiguracionModuloAccion(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "modulo":
                Integer modulo = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(modulo)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seleccione un modulo.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

        }
    }

    // ************ Getters and Setters ****************
    /**
     * @return the view
     */
    public EditarConfigModuloAccionView getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(EditarConfigModuloAccionView view) {
        this.view = view;
    }

}
