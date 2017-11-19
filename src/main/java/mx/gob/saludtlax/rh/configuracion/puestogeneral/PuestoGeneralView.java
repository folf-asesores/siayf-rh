/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 21/07/2016 13:43:44
 */
public class PuestoGeneralView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1812302169427889509L;

    private List<PuestoGeneralDTO> listaPuestoGeneral = new ArrayList<>();

    private List<SelectItem> listaTipoPuesto;

    private List<SelectItem> listaRama;

    private PuestoGeneralDTO puestoGeneralDTO = new PuestoGeneralDTO();

    private PuestoGeneralDTO puestoGeneralDTOSeleccionado = new PuestoGeneralDTO();

    private Boolean dialogoEliminar = Boolean.FALSE;

    private Integer idPuestoGeneral;

    private boolean panelRegistro = false;

    private boolean panelActualizar = false;

    /**
     * @return the listaPuestoGeneral
     */
    public List<PuestoGeneralDTO> getListaPuestoGeneral() {
        return listaPuestoGeneral;
    }

    /**
     * @param listaPuestoGeneral
     *            the listaPuestoGeneral to set
     */
    public void setListaPuestoGeneral(
            List<PuestoGeneralDTO> listaPuestoGeneral) {
        this.listaPuestoGeneral = listaPuestoGeneral;
    }

    /**
     * @return the puestoGeneralDTO
     */
    public PuestoGeneralDTO getPuestoGeneralDTO() {
        return puestoGeneralDTO;
    }

    /**
     * @param puestoGeneralDTO
     *            the puestoGeneralDTO to set
     */
    public void setPuestoGeneralDTO(PuestoGeneralDTO puestoGeneralDTO) {
        this.puestoGeneralDTO = puestoGeneralDTO;
    }

    /**
     * @return the listaRama
     */
    public List<SelectItem> getListaRama() {
        return listaRama;
    }

    /**
     * @param listaRama
     *            the listaRama to set
     */
    public void setListaRama(List<SelectItem> listaRama) {
        this.listaRama = listaRama;
    }

    /**
     * @return the dialogoEliminar
     */
    public Boolean getDialogoEliminar() {
        return dialogoEliminar;
    }

    /**
     * @param dialogoEliminar
     *            the dialogoEliminar to set
     */
    public void setDialogoEliminar(Boolean dialogoEliminar) {
        this.dialogoEliminar = dialogoEliminar;
    }

    /**
     * @return the idPuestoGeneral
     */
    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    /**
     * @param idPuestoGeneral
     *            the idPuestoGeneral to set
     */
    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public List<SelectItem> getListaTipoPuesto() {
        return listaTipoPuesto;
    }

    public void setListaTipoPuesto(List<SelectItem> listaTipoPuesto) {
        this.listaTipoPuesto = listaTipoPuesto;
    }

    /**
     * @return the panelRegistro
     */
    public boolean isPanelRegistro() {
        return panelRegistro;
    }

    /**
     * @param panelRegistro
     *            the panelRegistro to set
     */
    public void setPanelRegistro(boolean panelRegistro) {
        this.panelRegistro = panelRegistro;
    }

    /**
     * @return the panelActualizar
     */
    public boolean isPanelActualizar() {
        return panelActualizar;
    }

    /**
     * @param panelActualizar
     *            the panelActualizar to set
     */
    public void setPanelActualizar(boolean panelActualizar) {
        this.panelActualizar = panelActualizar;
    }

    /**
     * @return the puestoGeneralDTOSeleccionado
     */
    public PuestoGeneralDTO getPuestoGeneralDTOSeleccionado() {
        return puestoGeneralDTOSeleccionado;
    }

    /**
     * @param puestoGeneralDTOSeleccionado
     *            the puestoGeneralDTOSeleccionado to set
     */
    public void setPuestoGeneralDTOSeleccionado(
            PuestoGeneralDTO puestoGeneralDTOSeleccionado) {
        this.puestoGeneralDTOSeleccionado = puestoGeneralDTOSeleccionado;
    }

}
