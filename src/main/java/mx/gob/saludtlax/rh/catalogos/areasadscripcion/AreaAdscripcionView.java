package mx.gob.saludtlax.rh.catalogos.areasadscripcion;
import java.util.List;

import mx.gob.saludtlax.rh.persistencia.AreaAdscripcionEntity2;

public class AreaAdscripcionView {
 private String buscarClave;
 private List<AreaAdscripcionDTO> listArea;
 private List<AreaAdscripcionEntity2> listaAreasE;
 private Boolean panelPrincipal;
 private Boolean panelNuevo;
 
 
 public void panelPrincipal() {
		
		panelPrincipal = Boolean.TRUE;
		panelNuevo = Boolean.FALSE;
		
	}

	public void panelNuevo() {
		panelPrincipal = Boolean.FALSE;
		panelNuevo = Boolean.TRUE;
	}

	public String getBuscarClave() {
		return buscarClave;
	}

	public void setBuscarClave(String buscarClave) {
		this.buscarClave = buscarClave;
	}

	public List<AreaAdscripcionDTO> getListArea() {
		return listArea;
	}

	public void setListArea(List<AreaAdscripcionDTO> listArea) {
		this.listArea = listArea;
	}

	public Boolean getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(Boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public Boolean getPanelNuevo() {
		return panelNuevo;
	}

	public void setPanelNuevo(Boolean panelNuevo) {
		this.panelNuevo = panelNuevo;
	}

	public List<AreaAdscripcionEntity2> getListaAreasE() {
		return listaAreasE;
	}

	public void setListaAreasE(List<AreaAdscripcionEntity2> listaAreasE) {
		this.listaAreasE = listaAreasE;
	}
	
	
	
}
