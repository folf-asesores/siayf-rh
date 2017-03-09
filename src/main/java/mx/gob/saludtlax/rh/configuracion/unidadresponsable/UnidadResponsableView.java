package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

public class UnidadResponsableView {
	private Integer unidadResponsableCriterio;
	private List<UnidadResponsableDTO> listUnidadResponsable;
	private UnidadResponsableDTO unidadResponsableSelect;
	private UnidadResponsableDTO unidadResponsable;

	private Boolean disabledIrGestionar;
	private Boolean operacionNuevo;	
	private Boolean panelPrincipal;
	private Boolean panelGestion;
	
	

	public void panelPrincipal() {
		unidadResponsableSelect = null;
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

	public Integer getUnidadResponsableCriterio() {
		return unidadResponsableCriterio;
	}

	public void setUnidadResponsableCriterio(Integer unidadResponsableCriterio) {
		this.unidadResponsableCriterio = unidadResponsableCriterio;
	}

	public List<UnidadResponsableDTO> getListUnidadResponsable() {
		return listUnidadResponsable;
	}

	public void setListUnidadResponsable(List<UnidadResponsableDTO> listUnidadResponsable) {
		this.listUnidadResponsable = listUnidadResponsable;
	}

	public UnidadResponsableDTO getUnidadResponsableSelect() {
		return unidadResponsableSelect;
	}

	public void setUnidadResponsableSelect(UnidadResponsableDTO unidadResponsableSelect) {
		this.unidadResponsableSelect = unidadResponsableSelect;
	}

	public UnidadResponsableDTO getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(UnidadResponsableDTO unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
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