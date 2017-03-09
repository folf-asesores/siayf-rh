package mx.gob.saludtlax.rh.configuracion.dependencia;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;

public class DependenciaView {
	private Integer dependenciaCriterio;
	private List<DependenciaDTO> listDependencia;
	private DependenciaDTO dependenciaSelect;
	private DependenciaDTO dependencia;

	private Boolean disabledIrGestionar;
	private Boolean operacionNuevo;	
	private Boolean panelPrincipal;
	private Boolean panelGestion;
	
	

	public void panelPrincipal() {
		dependenciaSelect = null;
		panelPrincipal = Boolean.TRUE;
		panelGestion = Boolean.FALSE;
		disabledIrGestionar = Boolean.TRUE;
		operacionNuevo = null;
	}

	public void panelGestion() {
		panelPrincipal = Boolean.FALSE;
		panelGestion = Boolean.TRUE;
	}

//	<Getters & Setters>
	
	public Integer getDependenciaCriterio() {
		return dependenciaCriterio;
	}

	public void setDependenciaCriterio(Integer dependenciaCriterio) {
		this.dependenciaCriterio = dependenciaCriterio;
	}

	public List<DependenciaDTO> getListDependencia() {
		return listDependencia;
	}

	public void setListDependencia(List<DependenciaDTO> listDependencia) {
		this.listDependencia = listDependencia;
	}

	public DependenciaDTO getDependenciaSelect() {
		return dependenciaSelect;
	}

	public void setDependenciaSelect(DependenciaDTO dependenciaSelect) {
		this.dependenciaSelect = dependenciaSelect;
	}

	public DependenciaDTO getDependencia() {
		return dependencia;
	}

	public void setDependencia(DependenciaDTO dependencia) {
		this.dependencia = dependencia;
	}

	public Boolean getDisabledIrGestionar() {
		return disabledIrGestionar;
	}

	public void setDisabledIrGestionar(Boolean disabledIrGestionar) {
		this.disabledIrGestionar = disabledIrGestionar;
	}

	public Boolean getOperacionNuevo() {
		return operacionNuevo;
	}

	public void setOperacionNuevo(Boolean operacionNuevo) {
		this.operacionNuevo = operacionNuevo;
	}

	public Boolean getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(Boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public Boolean getPanelGestion() {
		return panelGestion;
	}

	public void setPanelGestion(Boolean panelGestion) {
		this.panelGestion = panelGestion;
	}	
}