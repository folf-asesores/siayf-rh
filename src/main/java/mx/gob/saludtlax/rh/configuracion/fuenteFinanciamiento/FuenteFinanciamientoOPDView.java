
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.List;

public class FuenteFinanciamientoOPDView {
    private Integer fuenteFinanciamientoCriterio;
    private List<FuenteFinanciamientoDTO> listFuenteFinanciamiento;

    private Integer fuenteFinanciamientoOPDCriterio;
    private List<FuenteFinanciamientoOPDDTO> listFuenteFinanciamientoOPD;
    private List<FuenteFinanciamientoOPDDTO> listFuenteFinanciamientoOPDLista;
    private FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPDSelect;
    private FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPD;

    private Integer subfuenteFinanciamientoCriterio;
    private List<SubfuenteFinanciamientoDTO> listSubfuenteFinanciamiento;

    private Boolean disabledIrGestionarOPD;
    private Boolean operacionNuevoOPD;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionarOPD = Boolean.TRUE;
        operacionNuevoOPD = null;
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

    public List<FuenteFinanciamientoOPDDTO> getListFuenteFinanciamientoOPDLista() {
        return listFuenteFinanciamientoOPDLista;
    }

    public void setListFuenteFinanciamientoOPDLista(
            List<FuenteFinanciamientoOPDDTO> listFuenteFinanciamientoOPDLista) {
        this.listFuenteFinanciamientoOPDLista = listFuenteFinanciamientoOPDLista;
    }

    public FuenteFinanciamientoOPDDTO getFuenteFinanciamientoOPDSelect() {
        return fuenteFinanciamientoOPDSelect;
    }

    public void setFuenteFinanciamientoOPDSelect(
            FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPDSelect) {
        this.fuenteFinanciamientoOPDSelect = fuenteFinanciamientoOPDSelect;
    }

    public FuenteFinanciamientoOPDDTO getFuenteFinanciamientoOPD() {
        return fuenteFinanciamientoOPD;
    }

    public void setFuenteFinanciamientoOPD(
            FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPD) {
        this.fuenteFinanciamientoOPD = fuenteFinanciamientoOPD;
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

    public Boolean getDisabledIrGestionarOPD() {
        return disabledIrGestionarOPD;
    }

    public void setDisabledIrGestionarOPD(Boolean disabledIrGestionarOPD) {
        this.disabledIrGestionarOPD = disabledIrGestionarOPD;
    }

    public Boolean getOperacionNuevoOPD() {
        return operacionNuevoOPD;
    }

    public void setOperacionNuevoOPD(Boolean operacionNuevoOPD) {
        this.operacionNuevoOPD = operacionNuevoOPD;
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