
package mx.gob.saludtlax.rh.siif;

import java.util.List;

public class ConsultaNominaView {
    private String rfcCriterio;

    private List<EstructuraNominaDatDTO> listEstructuraNomina;
    private EstructuraNominaDatDTO estructuraNomina;
    private EstructuraNominaDatDTO estructuraNominaSelect;
    private ConsultaDatosEncabezadoDTO estructuraNominaSeleccionada;

    private List<EstructuraNominaTrailersDTO> listEstructuraNominaTrailers;
    private EstructuraNominaTrailersDTO estructuraNominaTrailers;
    private EstructuraNominaTrailersDTO estructuraNominaTrailersSelect;

    private Boolean panelPrincipal;
    private Boolean panelDatos;
    private Boolean panelTrailers;
    private Boolean tabDat;
    private Boolean tabTra;
    private Boolean busqueda;
    private Boolean operacionNuevo;

    private Boolean disabledIrGestionarDatos;
    private Boolean disabledIrGestionarTrailers;

    public void panelPrincipal() {
        this.estructuraNominaTrailersSelect = null;
        this.estructuraNominaSelect = null;
        this.panelPrincipal = Boolean.TRUE;
        this.disabledIrGestionarDatos = Boolean.TRUE;
        this.disabledIrGestionarTrailers = Boolean.TRUE;
        this.panelDatos = Boolean.FALSE;
        this.panelTrailers = Boolean.FALSE;
        this.tabDat = Boolean.FALSE;
        this.tabTra = Boolean.FALSE;
        this.busqueda = Boolean.TRUE;
    }

    public void panelDatos() {
        this.panelPrincipal = Boolean.FALSE;
        this.panelDatos = Boolean.TRUE;
        this.busqueda = Boolean.FALSE;
    }

    public void panelTrailers() {
        this.panelPrincipal = Boolean.FALSE;
        this.panelTrailers = Boolean.TRUE;
        this.busqueda = Boolean.FALSE;
    }
    //  <<<<<Getters & Setters>>>>>

    public List<EstructuraNominaDatDTO> getListEstructuraNomina() {
        return this.listEstructuraNomina;
    }

    public String getRfcCriterio() {
        return this.rfcCriterio;
    }

    public void setRfcCriterio(String rfcCriterio) {
        this.rfcCriterio = rfcCriterio;
    }

    public void setListEstructuraNomina(List<EstructuraNominaDatDTO> listEstructuraNomina) {
        this.listEstructuraNomina = listEstructuraNomina;
    }

    public EstructuraNominaDatDTO getEstructuraNomina() {
        return this.estructuraNomina;
    }

    public void setEstructuraNomina(EstructuraNominaDatDTO estructuraNomina) {
        this.estructuraNomina = estructuraNomina;
    }

    public EstructuraNominaDatDTO getEstructuraNominaSelect() {
        return this.estructuraNominaSelect;
    }

    public void setEstructuraNominaSelect(EstructuraNominaDatDTO estructuraNominaSelect) {
        this.estructuraNominaSelect = estructuraNominaSelect;
    }

    public Boolean getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public List<EstructuraNominaTrailersDTO> getListEstructuraNominaTrailers() {
        return this.listEstructuraNominaTrailers;
    }

    public void setListEstructuraNominaTrailers(List<EstructuraNominaTrailersDTO> listEstructuraNominaTrailers) {
        this.listEstructuraNominaTrailers = listEstructuraNominaTrailers;
    }

    public EstructuraNominaTrailersDTO getEstructuraNominaTrailers() {
        return this.estructuraNominaTrailers;
    }

    public void setEstructuraNominaTrailers(EstructuraNominaTrailersDTO estructuraNominaTrailers) {
        this.estructuraNominaTrailers = estructuraNominaTrailers;
    }

    public EstructuraNominaTrailersDTO getEstructuraNominaTrailersSelect() {
        return this.estructuraNominaTrailersSelect;
    }

    public void setEstructuraNominaTrailersSelect(EstructuraNominaTrailersDTO estructuraNominaTrailersSelect) {
        this.estructuraNominaTrailersSelect = estructuraNominaTrailersSelect;
    }

    public Boolean getPanelDatos() {
        return this.panelDatos;
    }

    public void setPanelDatos(Boolean panelDatos) {
        this.panelDatos = panelDatos;
    }

    public Boolean getPanelTrailers() {
        return this.panelTrailers;
    }

    public void setPanelTrailers(Boolean panelTrailers) {
        this.panelTrailers = panelTrailers;
    }

    public Boolean getTabDat() {
        return this.tabDat;
    }

    public void setTabDat(Boolean tabDat) {
        this.tabDat = tabDat;
    }

    public Boolean getTabTra() {
        return this.tabTra;
    }

    public void setTabTra(Boolean tabTra) {
        this.tabTra = tabTra;
    }

    public Boolean getBusqueda() {
        return this.busqueda;
    }

    public void setBusqueda(Boolean busqueda) {
        this.busqueda = busqueda;
    }

    public Boolean getDisabledIrGestionarDatos() {
        return this.disabledIrGestionarDatos;
    }

    public void setDisabledIrGestionarDatos(Boolean disabledIrGestionarDatos) {
        this.disabledIrGestionarDatos = disabledIrGestionarDatos;
    }

    public Boolean getDisabledIrGestionarTrailers() {
        return this.disabledIrGestionarTrailers;
    }

    public void setDisabledIrGestionarTrailers(Boolean disabledIrGestionarTrailers) {
        this.disabledIrGestionarTrailers = disabledIrGestionarTrailers;
    }

    public Boolean getOperacionNuevo() {
        return this.operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public ConsultaDatosEncabezadoDTO getEstructuraNominaSeleccionada() {
        return this.estructuraNominaSeleccionada;
    }

    public void setEstructuraNominaSeleccionada(ConsultaDatosEncabezadoDTO estructuraNominaSeleccionada) {
        this.estructuraNominaSeleccionada = estructuraNominaSeleccionada;
    }

}