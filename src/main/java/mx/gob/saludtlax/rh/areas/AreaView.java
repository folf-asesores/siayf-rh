
package mx.gob.saludtlax.rh.areas;

import java.util.ArrayList;
import java.util.List;

public class AreaView {

    private AreaDTO areaDTO = new AreaDTO();
    private AreaDTO editarAreaDTO = new AreaDTO();
    private AreaDTO detailsAreaDTO = new AreaDTO();
    private List<AreaDTO> listaArea = new ArrayList<>();
    private AreaDTO seleccionarArea = new AreaDTO();
    private boolean habilitarFormulario;
    private boolean habilitarFormularioEdicion;
    private boolean habilitarPanelPrincipal;
    private boolean habilitarCancelarEdicion;
    private boolean habilitarBotonNuevaArea;
    private boolean habilitarBotonEdicion;
    private boolean habilitarBotonEliminar;
    private boolean habilitarBotonInformacion;

    public void inabilitar() {
        habilitarBotonNuevaArea = true;
        habilitarBotonEdicion = true;
        habilitarBotonEliminar = true;
        habilitarBotonInformacion = true;
    }

    public void habilitar() {
        habilitarBotonNuevaArea = false;
        habilitarBotonEdicion = false;
        habilitarBotonEliminar = false;
        habilitarBotonInformacion = false;
    }

    public AreaDTO getAreaDTO() {
        return areaDTO;
    }

    public void setAreaDTO(AreaDTO areaDTO) {
        this.areaDTO = areaDTO;
    }

    public AreaDTO getEditarAreaDTO() {
        return editarAreaDTO;
    }

    public void setEditarAreaDTO(AreaDTO editarAreaDTO) {
        this.editarAreaDTO = editarAreaDTO;
    }

    public List<AreaDTO> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<AreaDTO> listaArea) {
        this.listaArea = listaArea;
    }

    public AreaDTO getSeleccionarArea() {
        return seleccionarArea;
    }

    public void setSeleccionarArea(AreaDTO seleccionarArea) {
        this.seleccionarArea = seleccionarArea;
    }

    public boolean isHabilitarFormulario() {
        return habilitarFormulario;
    }

    public void setHabilitarFormulario(boolean habilitarFormulario) {
        this.habilitarFormulario = habilitarFormulario;
    }

    public boolean isHabilitarFormularioEdicion() {
        return habilitarFormularioEdicion;
    }

    public void setHabilitarFormularioEdicion(boolean habilitarFormularioEdicion) {
        this.habilitarFormularioEdicion = habilitarFormularioEdicion;
    }

    public boolean isHabilitarPanelPrincipal() {
        return habilitarPanelPrincipal;
    }

    public void setHabilitarPanelPrincipal(boolean habilitarPanelPrincipal) {
        this.habilitarPanelPrincipal = habilitarPanelPrincipal;
    }

    public boolean isHabilitarBotonEdicion() {
        return habilitarBotonEdicion;
    }

    public void setHabilitarBotonEdicion(boolean habilitarBotonEdicion) {
        this.habilitarBotonEdicion = habilitarBotonEdicion;
    }

    public boolean isHabilitarCancelarEdicion() {
        return habilitarCancelarEdicion;
    }

    public void setHabilitarCancelarEdicion(boolean habilitarCancelarEdicion) {
        this.habilitarCancelarEdicion = habilitarCancelarEdicion;
    }

    public boolean isHabilitarBotonNuevaArea() {
        return habilitarBotonNuevaArea;
    }

    public void setHabilitarBotonNuevaArea(boolean habilitarBotonNuevaArea) {
        this.habilitarBotonNuevaArea = habilitarBotonNuevaArea;
    }

    public boolean isHabilitarBotonEliminar() {
        return habilitarBotonEliminar;
    }

    public void setHabilitarBotonEliminar(boolean habilitarBotonEliminar) {
        this.habilitarBotonEliminar = habilitarBotonEliminar;
    }

    public boolean isHabilitarBotonInformacion() {
        return habilitarBotonInformacion;
    }

    public void setHabilitarBotonInformacion(boolean habilitarBotonInformacion) {
        this.habilitarBotonInformacion = habilitarBotonInformacion;
    }

    public AreaDTO getDetailsAreaDTO() {
        return detailsAreaDTO;
    }

    public void setDetailsAreaDTO(AreaDTO detailsAreaDTO) {
        this.detailsAreaDTO = detailsAreaDTO;
    }
}
