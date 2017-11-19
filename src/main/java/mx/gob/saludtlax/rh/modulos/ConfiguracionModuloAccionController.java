
package mx.gob.saludtlax.rh.modulos;

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

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean
@ViewScoped
public class ConfiguracionModuloAccionController implements Serializable {

    private static final long serialVersionUID = 2989747764675851876L;

    @Inject
    ConfiguracionModuloAccion configuracionModuloAccion;
    @Inject
    Accion accionEJB;
    @Inject
    Modulos moduloEJB;

    private List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccion = new ArrayList<>();
    private List<ModuloDTO> listaModulos = new ArrayList<>();
    private List<AccionDTO> listaAcciones = new ArrayList<>();
    private List<AccionDTO> listaAccionesSeleccionadas = new ArrayList<>();
    private ConfiguracionModuloAccionDTO configuracionModuloAccionSelect = new ConfiguracionModuloAccionDTO();
    private ConfiguracionModuloAccionDTO configuracionModuloAccionDTONew = new ConfiguracionModuloAccionDTO();

    private Boolean activarPanelAlta = false;
    private Boolean activarPanelEdicion = false;
    private Boolean activarTablaPrincipal = true;

    @PostConstruct
    public void inicio() {
        List<ConfiguracionModuloAccionDTO> configuracionModuloAccionTemp = configuracionModuloAccion
                .obtenerListaConfiguracionModuloAccionDTO();
        listaConfiguracionModuloAccion.clear();
        listaConfiguracionModuloAccion.addAll(configuracionModuloAccionTemp);

        List<ModuloDTO> lista = moduloEJB.listaModulos();
        listaModulos.addAll(lista);
    }

    public void accionesPorModuloSeleccionado() {

        listaAcciones = new ArrayList<>();
        ModuloDTO modulo = new ModuloDTO();
        for (ModuloDTO mod : listaModulos) {
            if (mod.getIdModulo().compareTo(configuracionModuloAccionDTONew
                    .getModulo().getIdModulo()) == 0) {
                modulo = mod;
            }
        }
        listaAcciones = accionEJB
                .obtenerListaAccionesPorArea(modulo.getIdArea());
        System.out.println("acciones::" + listaAcciones);
    }

    public void validatorConfiguracionModuloAccion(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "modulo":
                Integer modulo = (Integer) value;

                System.out.println("value:: " + modulo);

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
                System.out.println("value2:: " + accion);
                if (!ValidacionUtil.esNumeroPositivo(accion)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una accioó.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public void mostrarPanelAlta() {
        activarPanelAlta = true;
        activarPanelEdicion = false;
        activarTablaPrincipal = false;
    }

    public void mostrarPanelEdicion() {
        activarPanelAlta = false;
        activarPanelEdicion = true;
        activarTablaPrincipal = false;
    }

    public void mostrarPanelTablaPrincipal() {
        activarPanelAlta = false;
        activarPanelEdicion = false;
        activarTablaPrincipal = true;
    }

    public void agregarConfiguracionModuloAccion() {
        configuracionModuloAccionDTONew.setAcciones(listaAccionesSeleccionadas);
        configuracionModuloAccion.crear(configuracionModuloAccionDTONew);
        JSFUtils.warningMessage("",
                "Configuracion Modulo accion guardada correctamente");
        inicio();
        configuracionModuloAccionDTONew = new ConfiguracionModuloAccionDTO();
        listaAccionesSeleccionadas = new ArrayList<>();
    }

    public void onRowEdit(RowEditEvent event) {

        try {

            ConfiguracionModuloAccionDTO configuracionModulo = ((ConfiguracionModuloAccionDTO) event
                    .getObject());
            configuracionModuloAccion.editar(configuracionModulo);

            FacesMessage msg = new FacesMessage("",
                    "Actualizado Correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (BusinessException ex) {
            JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada:",
                "Actualizado Correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminarConfiguracion() {
        configuracionModuloAccion.eliminar(configuracionModuloAccionSelect
                .getIdConfiguracionModuloAccion());
        inicio();
    }

    public List<ConfiguracionModuloAccionDTO> getListaConfiguracionModuloAccion() {
        return listaConfiguracionModuloAccion;
    }

    public void setListaConfiguracionModuloAccion(
            List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccion) {
        this.listaConfiguracionModuloAccion = listaConfiguracionModuloAccion;
    }

    public ConfiguracionModuloAccionDTO getConfiguracionModuloAccionSelect() {
        return configuracionModuloAccionSelect;
    }

    public void setConfiguracionModuloAccionSelect(
            ConfiguracionModuloAccionDTO configuracionModuloAccionSelect) {
        this.configuracionModuloAccionSelect = configuracionModuloAccionSelect;
    }

    public ConfiguracionModuloAccionDTO getConfiguracionModuloAccionDTONew() {
        return configuracionModuloAccionDTONew;
    }

    public void setConfiguracionModuloAccionDTONew(
            ConfiguracionModuloAccionDTO configuracionModuloAccionDTONew) {
        this.configuracionModuloAccionDTONew = configuracionModuloAccionDTONew;
    }

    public List<ModuloDTO> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<ModuloDTO> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public List<AccionDTO> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<AccionDTO> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public Boolean getActivarPanelAlta() {
        return activarPanelAlta;
    }

    public void setActivarPanelAlta(Boolean activarPanelAlta) {
        this.activarPanelAlta = activarPanelAlta;
    }

    public Boolean getActivarPanelEdicion() {
        return activarPanelEdicion;
    }

    public void setActivarPanelEdicion(Boolean activarPanelEdicion) {
        this.activarPanelEdicion = activarPanelEdicion;
    }

    public Boolean getActivarTablaPrincipal() {
        return activarTablaPrincipal;
    }

    public void setActivarTablaPrincipal(Boolean activarTablaPrincipal) {
        this.activarTablaPrincipal = activarTablaPrincipal;
    }

    public List<AccionDTO> getListaAccionesSeleccionadas() {
        return listaAccionesSeleccionadas;
    }

    public void setListaAccionesSeleccionadas(
            List<AccionDTO> listaAccionesSeleccionadas) {
        this.listaAccionesSeleccionadas = listaAccionesSeleccionadas;
    }

}
