
package mx.gob.saludtlax.rh.siif;

import java.util.List;

public class ConsultaDatosEncabezadoView {
    private Integer idSiifEncabezado;

    private List<ConsultaDatosEncabezadoDTO> listDatosPersonalesLista;
    private ConsultaDatosEncabezadoDTO datoEncabezado;
    private ConsultaDatosEncabezadoDTO datoEncabezadoSelect;

    private Boolean panelPrincipal;
    private Boolean oculto;
    private Boolean panelDatosEncabezado;
    private Boolean operacionNuevo;
    private Boolean disabledIrGestionarDatosEncabezado;

    public void panelPrincipal() {
        datoEncabezadoSelect = null;
        panelPrincipal = Boolean.TRUE;
        disabledIrGestionarDatosEncabezado = Boolean.TRUE;
        panelDatosEncabezado = Boolean.FALSE;
        oculto = Boolean.FALSE;
    }

    public void panelDatosPersonales() {
        panelPrincipal = Boolean.FALSE;
        panelDatosEncabezado = Boolean.TRUE;
        oculto = Boolean.FALSE;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public Integer getIdSiifEncabezado() {
        return idSiifEncabezado;
    }

    public void setIdSiifEncabezado(Integer idSiifEncabezado) {
        this.idSiifEncabezado = idSiifEncabezado;
    }

    public List<ConsultaDatosEncabezadoDTO> getListDatosPersonalesLista() {
        return listDatosPersonalesLista;
    }

    public void setListDatosPersonalesLista(List<ConsultaDatosEncabezadoDTO> listDatosPersonalesLista) {
        this.listDatosPersonalesLista = listDatosPersonalesLista;
    }

    public ConsultaDatosEncabezadoDTO getDatoEncabezado() {
        return datoEncabezado;
    }

    public void setDatoEncabezado(ConsultaDatosEncabezadoDTO datoEncabezado) {
        this.datoEncabezado = datoEncabezado;
    }

    public ConsultaDatosEncabezadoDTO getDatoEncabezadoSelect() {
        return datoEncabezadoSelect;
    }

    public void setDatoEncabezadoSelect(ConsultaDatosEncabezadoDTO datoEncabezadoSelect) {
        this.datoEncabezadoSelect = datoEncabezadoSelect;
    }

    public Boolean getPanelDatosEncabezado() {
        return panelDatosEncabezado;
    }

    public void setPanelDatosEncabezado(Boolean panelDatosEncabezado) {
        this.panelDatosEncabezado = panelDatosEncabezado;
    }

    public Boolean getDisabledIrGestionarDatosEncabezado() {
        return disabledIrGestionarDatosEncabezado;
    }

    public void setDisabledIrGestionarDatosEncabezado(Boolean disabledIrGestionarDatosEncabezado) {
        this.disabledIrGestionarDatosEncabezado = disabledIrGestionarDatosEncabezado;
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

    public Boolean getPanelDatosPersonales() {
        return panelDatosEncabezado;
    }

    public void setPanelDatosPersonales(Boolean panelDatosPersonales) {
        panelDatosEncabezado = panelDatosPersonales;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getDisabledIrGestionarDatosPersonales() {
        return disabledIrGestionarDatosEncabezado;
    }

    public void setDisabledIrGestionarDatosPersonales(Boolean disabledIrGestionarDatosPersonales) {
        disabledIrGestionarDatosEncabezado = disabledIrGestionarDatosPersonales;
    }

}