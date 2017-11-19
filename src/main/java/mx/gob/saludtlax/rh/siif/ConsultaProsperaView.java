
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;

public class ConsultaProsperaView {
    private String rfcCriterio;

    private List<EstructuraContratosDatDTO> listEstructuraProspera;
    private EstructuraContratosDatDTO estructuraProspera;
    private EstructuraContratosDatDTO estructuraProsperaSelect;

    private List<EstructuraContratosTrailersDTO> listEstructuraProsperaTrailers;
    private EstructuraContratosTrailersDTO estructuraProsperaTrailers;
    private EstructuraContratosTrailersDTO estructuraProsperaTrailersSelect;

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
        estructuraProsperaTrailersSelect = null;
        estructuraProsperaSelect = null;
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

    public String getRfcCriterio() {
        return rfcCriterio;
    }

    public void setRfcCriterio(String rfcCriterio) {
        this.rfcCriterio = rfcCriterio;
    }

    public List<EstructuraContratosDatDTO> getListEstructuraProspera() {
        return listEstructuraProspera;
    }

    public void setListEstructuraProspera(List<EstructuraContratosDatDTO> listEstructuraProspera) {
        this.listEstructuraProspera = listEstructuraProspera;
    }

    public EstructuraContratosDatDTO getEstructuraProspera() {
        return estructuraProspera;
    }

    public void setEstructuraProspera(EstructuraContratosDatDTO estructuraProspera) {
        this.estructuraProspera = estructuraProspera;
    }

    public EstructuraContratosDatDTO getEstructuraProsperaSelect() {
        return estructuraProsperaSelect;
    }

    public void setEstructuraProsperaSelect(EstructuraContratosDatDTO estructuraProsperaSelect) {
        this.estructuraProsperaSelect = estructuraProsperaSelect;
    }

    public List<EstructuraContratosTrailersDTO> getListEstructuraProsperaTrailers() {
        return listEstructuraProsperaTrailers;
    }

    public void setListEstructuraProsperaTrailers(List<EstructuraContratosTrailersDTO> listEstructuraProsperaTrailers) {
        this.listEstructuraProsperaTrailers = listEstructuraProsperaTrailers;
    }

    public EstructuraContratosTrailersDTO getEstructuraProsperaTrailers() {
        return estructuraProsperaTrailers;
    }

    public void setEstructuraProsperaTrailers(EstructuraContratosTrailersDTO estructuraProsperaTrailers) {
        this.estructuraProsperaTrailers = estructuraProsperaTrailers;
    }

    public EstructuraContratosTrailersDTO getEstructuraProsperaTrailersSelect() {
        return estructuraProsperaTrailersSelect;
    }

    public void setEstructuraProsperaTrailersSelect(EstructuraContratosTrailersDTO estructuraProsperaTrailersSelect) {
        this.estructuraProsperaTrailersSelect = estructuraProsperaTrailersSelect;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
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

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
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

    /**
     * Cambiar los de aqui abajo para sustiruir los de arriba
     *
     * @param nuevoDatos
     */

    public void setEstructuraProspera(EstructuraNominaDatDTO nuevoDatos) {

    }

    public void setEstructuraProsperaTrailers(EstructuraNominaTrailersDTO nuevoTrailers) {

    }

}