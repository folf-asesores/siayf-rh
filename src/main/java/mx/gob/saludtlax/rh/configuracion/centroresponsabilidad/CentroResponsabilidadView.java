package mx.gob.saludtlax.rh.configuracion.centroresponsabilidad;

import java.util.List;

/**
 * 
 * @author José Pablo
 *
 */
public class CentroResponsabilidadView {
	private Integer centroResponsabilidadCriterio;
	private List<CentroResponsabilidadDTO> listCentroResponsabilidad;
	private CentroResponsabilidadDTO centroResponsabilidad;

	private Boolean disabledIrGestionar;
	private Boolean operacionNuevo;
	private Boolean opEliminar;
	private Boolean panelPrincipal;
	private Boolean panelGestion;

	public void panelPrincipal() {

		panelPrincipal = Boolean.TRUE;
		panelGestion = Boolean.FALSE;
		disabledIrGestionar = Boolean.TRUE;
		operacionNuevo = null;
		opEliminar = null;
	}

	public void panelGestion() {
		panelPrincipal = Boolean.FALSE;
		panelGestion = Boolean.TRUE;
	}

	public Integer getCentroResponsabilidadCriterio() {
		return centroResponsabilidadCriterio;
	}

	public void setCentroResponsabilidadCriterio(Integer centroResponsabilidadCriterio) {
		this.centroResponsabilidadCriterio = centroResponsabilidadCriterio;
	}

	public List<CentroResponsabilidadDTO> getListCentroResponsabilidad() {
		return listCentroResponsabilidad;
	}

	public void setListCentroResponsabilidad(List<CentroResponsabilidadDTO> listCentroResponsabilidad) {
		this.listCentroResponsabilidad = listCentroResponsabilidad;
	}

	public CentroResponsabilidadDTO getCentroResponsabilidad() {
		return centroResponsabilidad;
	}

	public void setCentroResponsabilidad(CentroResponsabilidadDTO centroResponsabilidad) {
		this.centroResponsabilidad = centroResponsabilidad;
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

	public Boolean getOpEliminar() {
		return opEliminar;
	}

	public void setOpEliminar(Boolean opEliminar) {
		this.opEliminar = opEliminar;
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
