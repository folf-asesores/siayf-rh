
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
        estructuraNominaTrailersSelect = null;
        estructuraNominaSelect = null;
        panelPrincipal = Boolean.TRUE;
        disabledIrGestionarDatos = Boolean.TRUE;
        disabledIrGestionarTrailers = Boolean.TRUE;
        panelDatos = Boolean.FALSE;
        panelTrailers = Boolean.FALSE;
        tabDat = Boolean.FALSE;
        tabTra = Boolean.FALSE;
        busqueda = Boolean.TRUE;
    }

    public void panelDatos() {
        panelPrincipal = Boolean.FALSE;
        panelDatos = Boolean.TRUE;
        busqueda = Boolean.FALSE;
    }

    public void panelTrailers() {
        panelPrincipal = Boolean.FALSE;
        panelTrailers = Boolean.TRUE;
        busqueda = Boolean.FALSE;
    }

    public List<EstructuraNominaDatDTO> getListEstructuraNomina() {
        return listEstructuraNomina;
    }

    public String getRfcCriterio() {
        return rfcCriterio;
    }

    public void setRfcCriterio(String rfcCriterio) {
        this.rfcCriterio = rfcCriterio;
    }

    public void setListEstructuraNomina(List<EstructuraNominaDatDTO> listEstructuraNomina) {
        this.listEstructuraNomina = listEstructuraNomina;
    }

    public EstructuraNominaDatDTO getEstructuraNomina() {
        return estructuraNomina;
    }

    public void setEstructuraNomina(EstructuraNominaDatDTO estructuraNomina) {
        this.estructuraNomina = estructuraNomina;
    }

    public EstructuraNominaDatDTO getEstructuraNominaSelect() {
        return estructuraNominaSelect;
    }

    public void setEstructuraNominaSelect(EstructuraNominaDatDTO estructuraNominaSelect) {
        this.estructuraNominaSelect = estructuraNominaSelect;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public List<EstructuraNominaTrailersDTO> getListEstructuraNominaTrailers() {
        return listEstructuraNominaTrailers;
    }

    public void setListEstructuraNominaTrailers(List<EstructuraNominaTrailersDTO> listEstructuraNominaTrailers) {
        this.listEstructuraNominaTrailers = listEstructuraNominaTrailers;
    }

    public EstructuraNominaTrailersDTO getEstructuraNominaTrailers() {
        return estructuraNominaTrailers;
    }

    public void setEstructuraNominaTrailers(EstructuraNominaTrailersDTO estructuraNominaTrailers) {
        this.estructuraNominaTrailers = estructuraNominaTrailers;
    }

    public EstructuraNominaTrailersDTO getEstructuraNominaTrailersSelect() {
        return estructuraNominaTrailersSelect;
    }

    public void setEstructuraNominaTrailersSelect(EstructuraNominaTrailersDTO estructuraNominaTrailersSelect) {
        this.estructuraNominaTrailersSelect = estructuraNominaTrailersSelect;
    }

    public Boolean getPanelDatos() {
        return panelDatos;
    }

    public void setPanelDatos(Boolean panelDatos) {
        this.panelDatos = panelDatos;
    }

    public Boolean getPanelTrailers() {
        return panelTrailers;
    }

    public void setPanelTrailers(Boolean panelTrailers) {
        this.panelTrailers = panelTrailers;
    }

    public Boolean getTabDat() {
        return tabDat;
    }

    public void setTabDat(Boolean tabDat) {
        this.tabDat = tabDat;
    }

    public Boolean getTabTra() {
        return tabTra;
    }

    public void setTabTra(Boolean tabTra) {
        this.tabTra = tabTra;
    }

    public Boolean getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Boolean busqueda) {
        this.busqueda = busqueda;
    }

    public Boolean getDisabledIrGestionarDatos() {
        return disabledIrGestionarDatos;
    }

    public void setDisabledIrGestionarDatos(Boolean disabledIrGestionarDatos) {
        this.disabledIrGestionarDatos = disabledIrGestionarDatos;
    }

    public Boolean getDisabledIrGestionarTrailers() {
        return disabledIrGestionarTrailers;
    }

    public void setDisabledIrGestionarTrailers(Boolean disabledIrGestionarTrailers) {
        this.disabledIrGestionarTrailers = disabledIrGestionarTrailers;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public ConsultaDatosEncabezadoDTO getEstructuraNominaSeleccionada() {
        return estructuraNominaSeleccionada;
    }

    public void setEstructuraNominaSeleccionada(ConsultaDatosEncabezadoDTO estructuraNominaSeleccionada) {
        this.estructuraNominaSeleccionada = estructuraNominaSeleccionada;
    }

}