/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

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

import org.jboss.logging.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@ManagedBean(name = "crearConfigModuloAccion")
@ViewScoped
public class CrearConfigModuloAccionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6478581873009415107L;

    private static final Logger LOGGER = Logger
            .getLogger(CrearConfigModuloAccionController.class);

    @Inject
    private Modulos moduloEJB;
    @Inject
    private Accion accionEJB;
    @Inject
    private ConfiguracionModuloAccion configuracionModuloAccion;

    private CrearConfigModuloAccionView view;

    @PostConstruct
    public void init() {

        view = new CrearConfigModuloAccionView();

        List<ModuloDTO> listaModulos = moduloEJB.listaModulos();
        view.setListaModulos(listaModulos);

    }

    public void accionesPorModuloSeleccionado() {

        view.setListaAcciones(new ArrayList<AccionDTO>());

        ModuloDTO modulo = new ModuloDTO();

        for (ModuloDTO mod : view.getListaModulos()) {
            if (mod.getIdModulo()
                    .compareTo(view.getConfiguracionModuloAccionDTONew()
                            .getModulo().getIdModulo()) == 0) {
                modulo = mod;
            }
        }
        view.setListaAcciones(accionEJB
                .obtenerListaAccionesPorModulo((modulo.getIdModulo())));

        List<AccionDTO> accionSource = accionEJB
                .obtenerListaAccionesPorModulo((modulo.getIdModulo()));
        List<AccionDTO> accionTarget = new ArrayList<>();

        view.setPikListAcciones(
                new DualListModel<>(accionSource, accionTarget));

    }

    public void agregarConfiguracionModuloAccion() {

        List<AccionDTO> acciones = view.getPikListAcciones().getTarget();

        view.getConfiguracionModuloAccionDTONew().setAcciones(acciones);

        configuracionModuloAccion
                .crear(view.getConfiguracionModuloAccionDTONew());

        JSFUtils.infoMessage("Configuración: ", "¡Se registro correctamente!");
        init();

    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((AccionDTO) item).getDescripcion())
                    .append("<br />");
        }

        LOGGER.debug("Acciones agregadas: " + builder.toString());

        // FacesMessage msg = new FacesMessage();
        // msg.setSeverity(FacesMessage.SEVERITY_INFO);
        // msg.setSummary("Provando transferencia");
        // msg.setDetail(builder.toString());
        //
        // FacesContext.getCurrentInstance().addMessage(null, msg);
        List<AccionDTO> acciones = view.getPikListAcciones().getTarget();

        for (AccionDTO accion : acciones) {
            LOGGER.debug(accion.toString());
        }

    }

    public void validatorConfiguracionModuloAccion(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "modulo":
                Integer modulo = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(modulo)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione un modulo.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "accion":
                Integer accion = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(accion)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una acción.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    /**
     * @return the view
     */
    public CrearConfigModuloAccionView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(CrearConfigModuloAccionView view) {
        this.view = view;
    }

}
