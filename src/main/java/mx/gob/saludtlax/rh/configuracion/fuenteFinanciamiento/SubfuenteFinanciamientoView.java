
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.List;

public class SubfuenteFinanciamientoView {
    private Integer fuenteFinanciamientoCriterio;
    private List<FuenteFinanciamientoDTO> listFuenteFinanciamiento;

    private Integer fuenteFinanciamientoOPDCriterio;
    private List<FuenteFinanciamientoOPDDTO> listFuenteFinanciamientoOPD;

    private Integer subfuenteFinanciamientoCriterio;
    private List<SubfuenteFinanciamientoDTO> listSubfuenteFinanciamiento;
    private SubfuenteFinanciamientoDTO subfuenteFinanciamientoSelect;
    private SubfuenteFinanciamientoDTO subfuenteFinanciamiento;

    private Boolean disabledIrGestionarSub;
    private Boolean operacionNuevoSubfuente;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionarSub = Boolean.TRUE;
        operacionNuevoSubfuente = null;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
    }

    public Integer getFuenteFinanciamientoCriterio() {
        return fuenteFinanciamientoCriterio;
    }

    public void setFuenteFinanciamientoCriterio(
            Integer fuenteFinanciamientoCriterio) {
        this.fuenteFinanciamientoCriterio = fuenteFinanciamientoCriterio;
    }

    public List<FuenteFinanciamientoDTO> getListFuenteFinanciamiento() {
        return listFuenteFinanciamiento;
    }

    public void setListFuenteFinanciamiento(
            List<FuenteFinanciamientoDTO> listFuenteFinanciamiento) {
        this.listFuenteFinanciamiento = listFuenteFinanciamiento;
    }

    public Integer getFuenteFinanciamientoOPDCriterio() {
        return fuenteFinanciamientoOPDCriterio;
    }

    public void setFuenteFinanciamientoOPDCriterio(
            Integer fuenteFinanciamientoOPDCriterio) {
        this.fuenteFinanciamientoOPDCriterio = fuenteFinanciamientoOPDCriterio;
    }

    public List<FuenteFinanciamientoOPDDTO> getListFuenteFinanciamientoOPD() {
        return listFuenteFinanciamientoOPD;
    }

    public void setListFuenteFinanciamientoOPD(
            List<FuenteFinanciamientoOPDDTO> listFuenteFinanciamientoOPD) {
        this.listFuenteFinanciamientoOPD = listFuenteFinanciamientoOPD;
    }

    public Integer getSubfuenteFinanciamientoCriterio() {
        return subfuenteFinanciamientoCriterio;
    }

    public void setSubfuenteFinanciamientoCriterio(
            Integer subfuenteFinanciamientoCriterio) {
        this.subfuenteFinanciamientoCriterio = subfuenteFinanciamientoCriterio;
    }

    public List<SubfuenteFinanciamientoDTO> getListSubfuenteFinanciamiento() {
        return listSubfuenteFinanciamiento;
    }

    public void setListSubfuenteFinanciamiento(
            List<SubfuenteFinanciamientoDTO> listSubfuenteFinanciamiento) {
        this.listSubfuenteFinanciamiento = listSubfuenteFinanciamiento;
    }

    public SubfuenteFinanciamientoDTO getSubfuenteFinanciamientoSelect() {
        return subfuenteFinanciamientoSelect;
    }

    public void setSubfuenteFinanciamientoSelect(
            SubfuenteFinanciamientoDTO subfuenteFinanciamientoSelect) {
        this.subfuenteFinanciamientoSelect = subfuenteFinanciamientoSelect;
    }

    public SubfuenteFinanciamientoDTO getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(
            SubfuenteFinanciamientoDTO subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Boolean getDisabledIrGestionarSub() {
        return disabledIrGestionarSub;
    }

    public void setDisabledIrGestionarSub(Boolean disabledIrGestionarSub) {
        this.disabledIrGestionarSub = disabledIrGestionarSub;
    }

    public Boolean getOperacionNuevoSubfuente() {
        return operacionNuevoSubfuente;
    }

    public void setOperacionNuevoSubfuente(Boolean operacionNuevoSubfuente) {
        this.operacionNuevoSubfuente = operacionNuevoSubfuente;
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