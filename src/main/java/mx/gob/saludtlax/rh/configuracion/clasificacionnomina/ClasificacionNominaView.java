
package mx.gob.saludtlax.rh.configuracion.clasificacionnomina;

import java.util.List;

public class ClasificacionNominaView {
    private Integer clasificacionNominaCriterio;
    private List<ClasificacionNominaDTO> listClasificacionNomina;
    private ClasificacionNominaDTO clasificacionNominaSelect;
    private ClasificacionNominaDTO clasificacionNomina;

    private Boolean disabledIrGestionar;
    private Boolean operacionNuevo;
    private Boolean opEliminar;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    public void panelPrincipal() {
        clasificacionNominaSelect = null;
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

    public Integer getClasificacionNominaCriterio() {
        return clasificacionNominaCriterio;
    }

    public void setClasificacionNominaCriterio(
            Integer clasificacionNominaCriterio) {
        this.clasificacionNominaCriterio = clasificacionNominaCriterio;
    }

    public List<ClasificacionNominaDTO> getListClasificacionNomina() {
        return listClasificacionNomina;
    }

    public void setListClasificacionNomina(
            List<ClasificacionNominaDTO> listClasificacionNomina) {
        this.listClasificacionNomina = listClasificacionNomina;
    }

    public ClasificacionNominaDTO getClasificacionNominaSelect() {
        return clasificacionNominaSelect;
    }

    public void setClasificacionNominaSelect(
            ClasificacionNominaDTO clasificacionNominaSelect) {
        this.clasificacionNominaSelect = clasificacionNominaSelect;
    }

    public ClasificacionNominaDTO getClasificacionNomina() {
        return clasificacionNomina;
    }

    public void setClasificacionNomina(
            ClasificacionNominaDTO clasificacionNomina) {
        this.clasificacionNomina = clasificacionNomina;
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