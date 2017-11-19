
package mx.gob.saludtlax.rh.configuracion.nombramiento;

import java.util.List;

public class NombramientoView {
    private Integer nombramientoCriterio;
    private List<TipoNombramientoDTO> listNombramiento;
    private TipoNombramientoDTO nombramientoSelect;
    private TipoNombramientoDTO nombramiento;

    private Boolean disabledIrGestionar;
    private Boolean operacionNuevo;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    public void panelPrincipal() {
        nombramientoSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionar = Boolean.TRUE;
        operacionNuevo = null;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
    }

    public Integer getNombramientoCriterio() {
        return nombramientoCriterio;
    }

    public void setNombramientoCriterio(Integer nombramientoCriterio) {
        this.nombramientoCriterio = nombramientoCriterio;
    }

    public List<TipoNombramientoDTO> getListNombramiento() {
        return listNombramiento;
    }

    public void setListNombramiento(List<TipoNombramientoDTO> listNombramiento) {
        this.listNombramiento = listNombramiento;
    }

    public TipoNombramientoDTO getNombramientoSelect() {
        return nombramientoSelect;
    }

    public void setNombramientoSelect(TipoNombramientoDTO nombramientoSelect) {
        this.nombramientoSelect = nombramientoSelect;
    }

    public TipoNombramientoDTO getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(TipoNombramientoDTO nombramiento) {
        this.nombramiento = nombramiento;
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