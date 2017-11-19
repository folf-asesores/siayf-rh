
package mx.gob.saludtlax.rh.configuracion.isr;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;

public class TablaRetencionView {
    private String periodicidad;
    private Integer ejercicioFiscal;

    private List<TablaRetencionListaDTO> listTablaRetencion;
    private TablaRetencionDTO tablaRetencion;
    private TablaRetencionListaDTO tablaRetencionSelect;
    private List<TipoPeriodoDTO> listPeriodicidad;

    private List<TablaSubsidioListaDTO> listTablaSubsidio;
    private TablaSubsidioDTO tablaSubsidio;
    private TablaSubsidioListaDTO tablaSubsidioSelect;
    private List<TablaRetencionListaDTO> listAnio;

    private Boolean disabledIrGestionarTablaRetencion;
    private Boolean disabledIrGestionarTablaSubsidio;

    private Boolean panelPrincipal;
    private Boolean panelTablaRetencion;
    private Boolean panelTablaSubsidio;
    private Boolean operacionNuevo1;
    private Boolean operacionNuevo2;

    private TipoPeriodoDTO altaPeriodicidad = new TipoPeriodoDTO();

    public void panelPrincipal() {
        tablaRetencionSelect = null;
        tablaSubsidioSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelTablaRetencion = Boolean.FALSE;
        panelTablaSubsidio = Boolean.FALSE;
        disabledIrGestionarTablaRetencion = Boolean.TRUE;
        disabledIrGestionarTablaSubsidio = Boolean.TRUE;
        operacionNuevo1 = null;
        operacionNuevo2 = null;
    }

    public void panelTablaRetencion() {
        panelPrincipal = Boolean.FALSE;
        panelTablaRetencion = Boolean.TRUE;
        panelTablaSubsidio = Boolean.FALSE;
    }

    public void panelTablaSubsidio() {
        panelPrincipal = Boolean.FALSE;
        panelTablaRetencion = Boolean.FALSE;
        panelTablaSubsidio = Boolean.TRUE;
    }

    public List<TablaRetencionListaDTO> getListTablaRetencion() {
        return listTablaRetencion;
    }

    public void setListTablaRetencion(
            List<TablaRetencionListaDTO> listTablaRetencion) {
        this.listTablaRetencion = listTablaRetencion;
    }

    public TablaRetencionListaDTO getTablaRetencionSelect() {
        return tablaRetencionSelect;
    }

    public void setTablaRetencionSelect(
            TablaRetencionListaDTO tablaRetencionSelect) {
        this.tablaRetencionSelect = tablaRetencionSelect;
    }

    public TablaRetencionDTO getTablaRetencion() {
        return tablaRetencion;
    }

    public void setTablaRetencion(TablaRetencionDTO tablaRetencion) {
        this.tablaRetencion = tablaRetencion;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public TipoPeriodoDTO getAltaPeriodicidad() {
        return altaPeriodicidad;
    }

    public void setAltaPeriodicidad(TipoPeriodoDTO altaPeriodicidad) {
        this.altaPeriodicidad = altaPeriodicidad;
    }

    public List<TipoPeriodoDTO> getListPeriodicidad() {
        return listPeriodicidad;
    }

    public void setListPeriodicidad(List<TipoPeriodoDTO> listPeriodicidad) {
        this.listPeriodicidad = listPeriodicidad;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public List<TablaSubsidioListaDTO> getListTablaSubsidio() {
        return listTablaSubsidio;
    }

    public void setListTablaSubsidio(
            List<TablaSubsidioListaDTO> listTablaSubsidio) {
        this.listTablaSubsidio = listTablaSubsidio;
    }

    public TablaSubsidioListaDTO getTablaSubsidioSelect() {
        return tablaSubsidioSelect;
    }

    public TablaSubsidioDTO getTablaSubsidio() {
        return tablaSubsidio;
    }

    public void setTablaSubsidio(TablaSubsidioDTO tablaSubsidio) {
        this.tablaSubsidio = tablaSubsidio;
    }

    public void setTablaSubsidioSelect(
            TablaSubsidioListaDTO tablaSubsidioSelect) {
        this.tablaSubsidioSelect = tablaSubsidioSelect;
    }

    public List<TablaRetencionListaDTO> getListAnio() {
        return listAnio;
    }

    public void setListAnio(List<TablaRetencionListaDTO> listAnio) {
        this.listAnio = listAnio;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Boolean getOperacionNuevo2() {
        return operacionNuevo2;
    }

    public void setOperacionNuevo2(Boolean operacionNuevo2) {
        this.operacionNuevo2 = operacionNuevo2;
    }

    public Boolean getOperacionNuevo1() {
        return operacionNuevo1;
    }

    public void setOperacionNuevo1(Boolean operacionNuevo1) {
        this.operacionNuevo1 = operacionNuevo1;
    }

    public Boolean getPanelTablaRetencion() {
        return panelTablaRetencion;
    }

    public void setPanelTablaRetencion(Boolean panelTablaRetencion) {
        this.panelTablaRetencion = panelTablaRetencion;
    }

    public Boolean getPanelTablaSubsidio() {
        return panelTablaSubsidio;
    }

    public void setPanelTablaSubsidio(Boolean panelTablaSubsidio) {
        this.panelTablaSubsidio = panelTablaSubsidio;
    }

    public Boolean getDisabledIrGestionarTablaRetencion() {
        return disabledIrGestionarTablaRetencion;
    }

    public void setDisabledIrGestionarTablaRetencion(
            Boolean disabledIrGestionarTablaRetencion) {
        this.disabledIrGestionarTablaRetencion = disabledIrGestionarTablaRetencion;
    }

    public Boolean getDisabledIrGestionarTablaSubsidio() {
        return disabledIrGestionarTablaSubsidio;
    }

    public void setDisabledIrGestionarTablaSubsidio(
            Boolean disabledIrGestionarTablaSubsidio) {
        this.disabledIrGestionarTablaSubsidio = disabledIrGestionarTablaSubsidio;
    }

}