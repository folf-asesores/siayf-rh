package mx.gob.saludtlax.rh.configuracion.plazas;

import java.util.ArrayList;
import java.util.List;

public class PlazaView {

	private PlazaDTO plazaDTO = new PlazaDTO();
	private PlazaDTO editarPlazaDTO = new PlazaDTO();
	private List<PlazaDTO> listaPlazas = new ArrayList<PlazaDTO>();
	private PlazaDTO seleccionarPlaza = new PlazaDTO();
	private boolean habilitarFormulario;
	private boolean habilitarFormularioEdicion;
	private boolean habilitarPanelPrincipal = true;
	private boolean habilitarBotonEdicion;
	private boolean habilitarCancelarEdicion;
	private boolean habilitarBotonNuevaPlaza = true;
	
	public void habilitarFormulario() {
		habilitarFormulario = true;
		habilitarFormularioEdicion = false;
		habilitarPanelPrincipal = false;
		habilitarBotonEdicion = false;
		habilitarCancelarEdicion = false;
		habilitarBotonNuevaPlaza = false;
	}

	public void habilitarFormularioEdicion() {
		habilitarFormulario = false;
		habilitarFormularioEdicion = true;
		habilitarPanelPrincipal = false;
		habilitarBotonEdicion = false;
		habilitarCancelarEdicion = false;
		habilitarBotonNuevaPlaza = false;
	}
	
	public void cancelar(){
		habilitarFormulario = false;
		habilitarFormularioEdicion = false;
		habilitarPanelPrincipal = true;
		habilitarBotonEdicion = false;
		habilitarCancelarEdicion = false;
		habilitarBotonNuevaPlaza = true;
	}
	
	
	

	public PlazaDTO getPlazaDTO() {
		return plazaDTO;
	}

	public void setPlazaDTO(PlazaDTO plazaDTO) {
		this.plazaDTO = plazaDTO;
	}

	public PlazaDTO getEditarPlazaDTO() {
		return editarPlazaDTO;
	}

	public void setEditarPlazaDTO(PlazaDTO editarPlazaDTO) {
		this.editarPlazaDTO = editarPlazaDTO;
	}

	public List<PlazaDTO> getListaPlazas() {
		return listaPlazas;
	}

	public void setListaPlazas(List<PlazaDTO> listaPlazas) {
		this.listaPlazas = listaPlazas;
	}

	public PlazaDTO getSeleccionarPlaza() {
		return seleccionarPlaza;
	}

	public void setSeleccionarPlaza(PlazaDTO seleccionarPlaza) {
		this.seleccionarPlaza = seleccionarPlaza;
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

	public boolean isHabilitarBotonNuevaPlaza() {
		return habilitarBotonNuevaPlaza;
	}

	public void setHabilitarBotonNuevaPlaza(boolean habilitarBotonNuevaPlaza) {
		this.habilitarBotonNuevaPlaza = habilitarBotonNuevaPlaza;
	}
		
}