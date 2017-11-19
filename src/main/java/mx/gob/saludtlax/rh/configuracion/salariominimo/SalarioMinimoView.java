/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class SalarioMinimoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4449529231307220171L;

    private List<SalarioMinimoDTO> listaSalariosMinimos = new ArrayList<>();

    private SalarioMinimoDTO creaSalarioMinimo = new SalarioMinimoDTO();

    private SalarioMinimoDTO actualizarSalarioMinimo = new SalarioMinimoDTO();

    private Integer idSalarioMinimoSeleccionado;

    private boolean panelPrincipal = true;
    private boolean panelCrear = false;
    private boolean panelActualizar = false;
    private boolean dialogEliminar = false;

    public List<SalarioMinimoDTO> getListaSalariosMinimos() {
        return listaSalariosMinimos;
    }

    public void setListaSalariosMinimos(
            List<SalarioMinimoDTO> listaSalariosMinimos) {
        this.listaSalariosMinimos = listaSalariosMinimos;
    }

    public SalarioMinimoDTO getCreaSalarioMinimo() {
        return creaSalarioMinimo;
    }

    public void setCreaSalarioMinimo(SalarioMinimoDTO creaSalarioMinimo) {
        this.creaSalarioMinimo = creaSalarioMinimo;
    }

    public SalarioMinimoDTO getActualizarSalarioMinimo() {
        return actualizarSalarioMinimo;
    }

    public void setActualizarSalarioMinimo(
            SalarioMinimoDTO actualizarSalarioMinimo) {
        this.actualizarSalarioMinimo = actualizarSalarioMinimo;
    }

    public Integer getIdSalarioMinimoSeleccionado() {
        return idSalarioMinimoSeleccionado;
    }

    public void setIdSalarioMinimoSeleccionado(
            Integer idSalarioMinimoSeleccionado) {
        this.idSalarioMinimoSeleccionado = idSalarioMinimoSeleccionado;
    }

    public boolean isPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public boolean isPanelCrear() {
        return panelCrear;
    }

    public void setPanelCrear(boolean panelCrear) {
        this.panelCrear = panelCrear;
    }

    public boolean isPanelActualizar() {
        return panelActualizar;
    }

    public void setPanelActualizar(boolean panelActualizar) {
        this.panelActualizar = panelActualizar;
    }

    public boolean isDialogEliminar() {
        return dialogEliminar;
    }

    public void setDialogEliminar(boolean dialogEliminar) {
        this.dialogEliminar = dialogEliminar;
    }

}
