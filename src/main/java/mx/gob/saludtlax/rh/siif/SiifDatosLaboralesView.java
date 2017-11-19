
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.plazas.PlazaDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

public class SiifDatosLaboralesView {
    private String rfcCriterio;

    private List<SiifDatosLaboralesDTO> listSiifDatosLaborales;
    private List<SiifDatosLaboralesListaDTO> listSiifDatosLaboralesLista;
    private SiifDatosLaboralesDTO siifDatosLaborales;
    private SiifDatosLaboralesDTO siifDatosLaboralesSelect;

    private List<DependenciaDTO> listDependencia;
    private List<UnidadResponsableDTO> listUnidad;
    private List<TipoNombramientoDTO> listNombramiento;
    private List<PlazaDTO> listPlaza;
    private List<PuestosGeneralesDTO> listPuesto;
    private List<FuenteFinanciamientoDTO> listFF;
    private List<SubfuenteFinanciamientoDTO> listSF;

    private Boolean panelPrincipal;
    private Boolean oculto;
    private Boolean panelDatosLaborales;
    private Boolean operacionNuevo;
    private Boolean disabledIrGestionarDatosLaborales;

    public void panelPrincipal() {
        siifDatosLaboralesSelect = null;
        panelPrincipal = Boolean.TRUE;
        disabledIrGestionarDatosLaborales = Boolean.TRUE;
        panelDatosLaborales = Boolean.FALSE;
        oculto = Boolean.FALSE;
    }

    public void panelDatosLaborales() {
        panelPrincipal = Boolean.FALSE;
        panelDatosLaborales = Boolean.TRUE;
        oculto = Boolean.FALSE;
    }

    public String getRfcCriterio() {
        return rfcCriterio;
    }

    public void setRfcCriterio(String rfcCriterio) {
        this.rfcCriterio = rfcCriterio;
    }

    public List<SiifDatosLaboralesDTO> getListSiifDatosLaborales() {
        return listSiifDatosLaborales;
    }

    public void setListSiifDatosLaborales(
            List<SiifDatosLaboralesDTO> listSiifDatosLaborales) {
        this.listSiifDatosLaborales = listSiifDatosLaborales;
    }

    public SiifDatosLaboralesDTO getSiifDatosLaborales() {
        return siifDatosLaborales;
    }

    public void setSiifDatosLaborales(
            SiifDatosLaboralesDTO siifDatosLaborales) {
        this.siifDatosLaborales = siifDatosLaborales;
    }

    public SiifDatosLaboralesDTO getSiifDatosLaboralesSelect() {
        return siifDatosLaboralesSelect;
    }

    public void setSiifDatosLaboralesSelect(
            SiifDatosLaboralesDTO siifDatosLaboralesSelect) {
        this.siifDatosLaboralesSelect = siifDatosLaboralesSelect;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    public Boolean getPanelDatosLaborales() {
        return panelDatosLaborales;
    }

    public void setPanelDatosLaborales(Boolean panelDatosLaborales) {
        this.panelDatosLaborales = panelDatosLaborales;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getDisabledIrGestionarDatosLaborales() {
        return disabledIrGestionarDatosLaborales;
    }

    public void setDisabledIrGestionarDatosLaborales(
            Boolean disabledIrGestionarDatosLaborales) {
        this.disabledIrGestionarDatosLaborales = disabledIrGestionarDatosLaborales;
    }

    public List<SiifDatosLaboralesListaDTO> getListSiifDatosLaboralesLista() {
        return listSiifDatosLaboralesLista;
    }

    public void setListSiifDatosLaboralesLista(
            List<SiifDatosLaboralesListaDTO> listSiifDatosLaboralesLista) {
        this.listSiifDatosLaboralesLista = listSiifDatosLaboralesLista;
    }

    public List<DependenciaDTO> getListDependencia() {
        return listDependencia;
    }

    public void setListDependencia(List<DependenciaDTO> listDependencia) {
        this.listDependencia = listDependencia;
    }

    public List<UnidadResponsableDTO> getListUnidad() {
        return listUnidad;
    }

    public void setListUnidad(List<UnidadResponsableDTO> listUnidad) {
        this.listUnidad = listUnidad;
    }

    public List<TipoNombramientoDTO> getListNombramiento() {
        return listNombramiento;
    }

    public void setListNombramiento(
            List<TipoNombramientoDTO> listNombramiento) {
        this.listNombramiento = listNombramiento;
    }

    public List<PuestosGeneralesDTO> getListPuesto() {
        return listPuesto;
    }

    public void setListPuesto(List<PuestosGeneralesDTO> listPuesto) {
        this.listPuesto = listPuesto;
    }

    public List<PlazaDTO> getListPlaza() {
        return listPlaza;
    }

    public void setListPlaza(List<PlazaDTO> listPlaza) {
        this.listPlaza = listPlaza;
    }

    public List<FuenteFinanciamientoDTO> getListFF() {
        return listFF;
    }

    public void setListFF(List<FuenteFinanciamientoDTO> listFF) {
        this.listFF = listFF;
    }

    public List<SubfuenteFinanciamientoDTO> getListSF() {
        return listSF;
    }

    public void setListSF(List<SubfuenteFinanciamientoDTO> listSF) {
        this.listSF = listSF;
    }

}