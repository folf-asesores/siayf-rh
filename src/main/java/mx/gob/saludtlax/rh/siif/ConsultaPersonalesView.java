
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.plazas.PlazaDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

public class ConsultaPersonalesView {

    private String rfcCriterio;

    private List<DatosPersonalesDTO> listDatosPersonales;
    private List<DatosPersonalesDTO> listDatosPersonalesLista;
    private DatosPersonalesDTO datoPersonal;
    private DatosPersonalesDTO datoPersonalesSelect;

    private List<SiifDatosLaboralesDTO> listSiifDatosLaborales;
    private List<SiifDatosLaboralesListaDTO> listSiifDatosLaboralesLista;
    private SiifDatosLaboralesDTO siifDatosLaborales;
    private SiifDatosLaboralesDTO siifDatosLaboralesSelect;

    private List<SiifLaboralesSubfuentesDTO> listSiifLaboralesSubfuentes;
    private SiifLaboralesSubfuentesDTO siifLaboralesSubfuentes;
    private SiifLaboralesSubfuentesDTO siifLaboralesSubfuentesSelect;

    private List<FuenteFinanciamientoDTO> listFuenteF;
    private List<SubfuenteFinanciamientoDTO> listSubfuenteF;

    private Boolean panelPrincipal;
    private Boolean panelDatosPersonales;
    private Boolean panelDatosLaborales;
    private Boolean operacionNuevo;
    private Boolean disabledIrGestionarDatosPersonales;
    private Boolean disabledIrGestionarDatosLaborales;
    private Boolean disabledEliminar;
    private Boolean busqueda;
    private Boolean tabPersonales;
    private Boolean tabLaborales;
    private Boolean dialogoEliminar;
    private Boolean panelSubfuentes;
    private Boolean idPersonalNuevo;
    private Integer idPersonalVerifica;

    private List<DependenciaDTO> listDependencia;
    private List<UnidadResponsableDTO> listUnidad;
    private List<TipoNombramientoDTO> listNombramiento;
    private List<TipoNombramientoDTO> listNombramientoID;
    private List<PlazaDTO> listPlaza;
    private List<PuestosGeneralesDTO> listPuesto;
    private List<FuenteFinanciamientoDTO> listFF;
    private List<SubfuenteFinanciamientoDTO> listSF;
    private List<ProyectosTempDTO> listProyecto;

    public void panelPrincipal() {
        datoPersonalesSelect = null;
        siifDatosLaboralesSelect = null;
        panelPrincipal = Boolean.TRUE;
        disabledIrGestionarDatosPersonales = Boolean.TRUE;
        disabledIrGestionarDatosLaborales = Boolean.TRUE;
        panelDatosPersonales = Boolean.FALSE;
        panelDatosLaborales = Boolean.FALSE;
        tabLaborales = Boolean.FALSE;
        tabPersonales = Boolean.FALSE;
        busqueda = Boolean.TRUE;
        idPersonalNuevo = Boolean.TRUE;

        panelSubfuentes = Boolean.FALSE;
    }

    public void panelDatosPersonales() {
        panelPrincipal = Boolean.FALSE;
        panelDatosPersonales = Boolean.TRUE;
        busqueda = Boolean.FALSE;
    }

    public void panelDatosLaborales() {
        panelPrincipal = Boolean.FALSE;
        panelDatosLaborales = Boolean.TRUE;
        busqueda = Boolean.FALSE;
    }

    public String getRfcCriterio() {
        return rfcCriterio;
    }

    public List<DatosPersonalesDTO> getListDatosPersonales() {
        return listDatosPersonales;
    }

    public void setListDatosPersonales(
            List<DatosPersonalesDTO> listDatosPersonales) {
        this.listDatosPersonales = listDatosPersonales;
    }

    public List<DatosPersonalesDTO> getListDatosPersonalesLista() {
        return listDatosPersonalesLista;
    }

    public void setListDatosPersonalesLista(
            List<DatosPersonalesDTO> listDatosPersonalesLista) {
        this.listDatosPersonalesLista = listDatosPersonalesLista;
    }

    public DatosPersonalesDTO getDatoPersonal() {
        return datoPersonal;
    }

    public void setDatoPersonal(DatosPersonalesDTO datoPersonal) {
        this.datoPersonal = datoPersonal;
    }

    public DatosPersonalesDTO getDatoPersonalesSelect() {
        return datoPersonalesSelect;
    }

    public void setDatoPersonalesSelect(
            DatosPersonalesDTO datoPersonalesSelect) {
        this.datoPersonalesSelect = datoPersonalesSelect;
    }

    public void setRfcCriterio(String rfcCriterio) {
        this.rfcCriterio = rfcCriterio;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getTabPersonales() {
        return tabPersonales;
    }

    public void setTabPersonales(Boolean tabPersonales) {
        this.tabPersonales = tabPersonales;
    }

    public Boolean getTabLaborales() {
        return tabLaborales;
    }

    public void setTabLaborales(Boolean tabLaborales) {
        this.tabLaborales = tabLaborales;
    }

    public Boolean getPanelDatosPersonales() {
        return panelDatosPersonales;
    }

    public void setPanelDatosPersonales(Boolean panelDatosPersonales) {
        this.panelDatosPersonales = panelDatosPersonales;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getDisabledIrGestionarDatosPersonales() {
        return disabledIrGestionarDatosPersonales;
    }

    public void setDisabledIrGestionarDatosPersonales(
            Boolean disabledIrGestionarDatosPersonales) {
        this.disabledIrGestionarDatosPersonales = disabledIrGestionarDatosPersonales;
    }

    public Boolean getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Boolean busqueda) {
        this.busqueda = busqueda;
    }

    public List<SiifDatosLaboralesDTO> getListSiifDatosLaborales() {
        return listSiifDatosLaborales;
    }

    public void setListSiifDatosLaborales(
            List<SiifDatosLaboralesDTO> listSiifDatosLaborales) {
        this.listSiifDatosLaborales = listSiifDatosLaborales;
    }

    public List<SiifDatosLaboralesListaDTO> getListSiifDatosLaboralesLista() {
        return listSiifDatosLaboralesLista;
    }

    public void setListSiifDatosLaboralesLista(
            List<SiifDatosLaboralesListaDTO> listSiifDatosLaboralesLista) {
        this.listSiifDatosLaboralesLista = listSiifDatosLaboralesLista;
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

    public Boolean getPanelDatosLaborales() {
        return panelDatosLaborales;
    }

    public void setPanelDatosLaborales(Boolean panelDatosLaborales) {
        this.panelDatosLaborales = panelDatosLaborales;
    }

    public Boolean getDisabledIrGestionarDatosLaborales() {
        return disabledIrGestionarDatosLaborales;
    }

    public void setDisabledIrGestionarDatosLaborales(
            Boolean disabledIrGestionarDatosLaborales) {
        this.disabledIrGestionarDatosLaborales = disabledIrGestionarDatosLaborales;
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

    public List<TipoNombramientoDTO> getListNombramientoID() {
        return listNombramientoID;
    }

    public void setListNombramientoID(
            List<TipoNombramientoDTO> listNombramientoID) {
        this.listNombramientoID = listNombramientoID;
    }

    public List<PlazaDTO> getListPlaza() {
        return listPlaza;
    }

    public void setListPlaza(List<PlazaDTO> listPlaza) {
        this.listPlaza = listPlaza;
    }

    public List<PuestosGeneralesDTO> getListPuesto() {
        return listPuesto;
    }

    public void setListPuesto(List<PuestosGeneralesDTO> listPuesto) {
        this.listPuesto = listPuesto;
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

    public List<ProyectosTempDTO> getListProyecto() {
        return listProyecto;
    }

    public void setListProyecto(List<ProyectosTempDTO> listProyecto) {
        this.listProyecto = listProyecto;
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

    public Boolean getDisabledEliminar() {
        return disabledEliminar;
    }

    public void setDisabledEliminar(Boolean disabledEliminar) {
        this.disabledEliminar = disabledEliminar;
    }

    public Boolean getDialogoEliminar() {
        return dialogoEliminar;
    }

    public void setDialogoEliminar(Boolean dialogoEliminar) {
        this.dialogoEliminar = dialogoEliminar;
    }

    public Boolean getPanelSubfuentes() {
        return panelSubfuentes;
    }

    public void setPanelSubfuentes(Boolean panelSubfuentes) {
        this.panelSubfuentes = panelSubfuentes;
    }

    public Boolean getIdPersonalNuevo() {
        return idPersonalNuevo;
    }

    public void setIdPersonalNuevo(Boolean idPersonalNuevo) {
        this.idPersonalNuevo = idPersonalNuevo;
    }

    public Integer getIdPersonalVerifica() {
        return idPersonalVerifica;
    }

    public void setIdPersonalVerifica(Integer idPersonalVerifica) {
        this.idPersonalVerifica = idPersonalVerifica;
    }

}