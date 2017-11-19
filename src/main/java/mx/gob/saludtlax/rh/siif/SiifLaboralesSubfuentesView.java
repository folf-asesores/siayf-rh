
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;

public class SiifLaboralesSubfuentesView {

    private List<SiifLaboralesSubfuentesDTO> listSiifLaboralesSubfuentes;
    private SiifLaboralesSubfuentesDTO siifLaboralesSubfuentes;
    private SiifLaboralesSubfuentesDTO siifLaboralesSubfuentesSelect;

    private List<FuenteFinanciamientoDTO> listFuenteF;
    private List<SubfuenteFinanciamientoDTO> listSubfuenteF;

    private Boolean panelPrincipal;
    private Boolean operacionNuevo;
    private Boolean disabledEliminar;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        disabledEliminar = Boolean.TRUE;
    }

    public List<SiifLaboralesSubfuentesDTO> getListSiifLaboralesSubfuentes() {
        return listSiifLaboralesSubfuentes;
    }

    public void setListSiifLaboralesSubfuentes(
            List<SiifLaboralesSubfuentesDTO> listSiifLaboralesSubfuentes) {
        this.listSiifLaboralesSubfuentes = listSiifLaboralesSubfuentes;
    }

    public SiifLaboralesSubfuentesDTO getSiifLaboralesSubfuentes() {
        return siifLaboralesSubfuentes;
    }

    public void setSiifLaboralesSubfuentes(
            SiifLaboralesSubfuentesDTO siifLaboralesSubfuentes) {
        this.siifLaboralesSubfuentes = siifLaboralesSubfuentes;
    }

    public SiifLaboralesSubfuentesDTO getSiifLaboralesSubfuentesSelect() {
        return siifLaboralesSubfuentesSelect;
    }

    public void setSiifLaboralesSubfuentesSelect(
            SiifLaboralesSubfuentesDTO siifLaboralesSubfuentesSelect) {
        this.siifLaboralesSubfuentesSelect = siifLaboralesSubfuentesSelect;
    }

    public List<FuenteFinanciamientoDTO> getListFuenteF() {
        return listFuenteF;
    }

    public void setListFuenteF(List<FuenteFinanciamientoDTO> listFuenteF) {
        this.listFuenteF = listFuenteF;
    }

    public List<SubfuenteFinanciamientoDTO> getListSubfuenteF() {
        return listSubfuenteF;
    }

    public void setListSubfuenteF(
            List<SubfuenteFinanciamientoDTO> listSubfuenteF) {
        this.listSubfuenteF = listSubfuenteF;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getDisabledEliminar() {
        return disabledEliminar;
    }

    public void setDisabledEliminar(Boolean disabledEliminar) {
        this.disabledEliminar = disabledEliminar;
    }

}