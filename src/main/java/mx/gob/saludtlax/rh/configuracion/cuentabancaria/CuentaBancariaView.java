
package mx.gob.saludtlax.rh.configuracion.cuentabancaria;

import java.util.List;

public class CuentaBancariaView {
    private Integer cuentaBancariaCriterio;
    private List<CuentaBancariaDTO> listCuentaBancaria;
    private CuentaBancariaDTO cuentaBancariaSelect;
    private CuentaBancariaDTO cuentaBancaria;

    private Boolean disabledIrGestionar;
    private Boolean operacionNuevo;
    private Boolean opEliminar;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    public void panelPrincipal() {
        cuentaBancariaSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionar = Boolean.TRUE;
        operacionNuevo = null;
        opEliminar = null;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
    }

    public Integer getCuentaBancariaCriterio() {
        return cuentaBancariaCriterio;
    }

    public void setCuentaBancariaCriterio(Integer cuentaBancariaCriterio) {
        this.cuentaBancariaCriterio = cuentaBancariaCriterio;
    }

    public List<CuentaBancariaDTO> getListCuentaBancaria() {
        return listCuentaBancaria;
    }

    public void setListCuentaBancaria(List<CuentaBancariaDTO> listCuentaBancaria) {
        this.listCuentaBancaria = listCuentaBancaria;
    }

    public CuentaBancariaDTO getCuentaBancariaSelect() {
        return cuentaBancariaSelect;
    }

    public void setCuentaBancariaSelect(CuentaBancariaDTO cuentaBancariaSelect) {
        this.cuentaBancariaSelect = cuentaBancariaSelect;
    }

    public CuentaBancariaDTO getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Boolean getDisabledIrGestionar() {
        return disabledIrGestionar;
    }

    public void setDisabledIrGestionar(Boolean disabledIrGestionar) {
        this.disabledIrGestionar = disabledIrGestionar;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
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

    public Boolean getOpEliminar() {
        return opEliminar;
    }

    public void setOpEliminar(Boolean opEliminar) {
        this.opEliminar = opEliminar;
    }

}