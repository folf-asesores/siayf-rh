/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 19:23:01
 */
public class RiesgoPuestoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4983428741390069317L;

    private List<RiesgoPuestoDTO> obtenerListaPuestoDTOs = new ArrayList<>();

    private RiesgoPuestoDTO riesgoPuestoDTO = new RiesgoPuestoDTO();

    private String accionRiesgoPuesto = "Registrar";

    private Integer idRiesgoPuesto;

    private Boolean dialogRiesgoPuestoEliminar = Boolean.FALSE;

    /**
     * @return the obtenerListaPuestoDTOs
     */
    public List<RiesgoPuestoDTO> getObtenerListaPuestoDTOs() {
        return obtenerListaPuestoDTOs;
    }

    /**
     * @param obtenerListaPuestoDTOs
     *            the obtenerListaPuestoDTOs to set
     */
    public void setObtenerListaPuestoDTOs(List<RiesgoPuestoDTO> obtenerListaPuestoDTOs) {
        this.obtenerListaPuestoDTOs = obtenerListaPuestoDTOs;
    }

    /**
     * @return the riesgoPuestoDTO
     */
    public RiesgoPuestoDTO getRiesgoPuestoDTO() {
        return riesgoPuestoDTO;
    }

    /**
     * @param riesgoPuestoDTO
     *            the riesgoPuestoDTO to set
     */
    public void setRiesgoPuestoDTO(RiesgoPuestoDTO riesgoPuestoDTO) {
        this.riesgoPuestoDTO = riesgoPuestoDTO;
    }

    /**
     * @return the accionRiesgoPuesto
     */
    public String getAccionRiesgoPuesto() {
        return accionRiesgoPuesto;
    }

    /**
     * @param accionRiesgoPuesto
     *            the accionRiesgoPuesto to set
     */
    public void setAccionRiesgoPuesto(String accionRiesgoPuesto) {
        this.accionRiesgoPuesto = accionRiesgoPuesto;
    }

    /**
     * @return the idRiesgoPuesto
     */
    public Integer getIdRiesgoPuesto() {
        return idRiesgoPuesto;
    }

    /**
     * @param idRiesgoPuesto
     *            the idRiesgoPuesto to set
     */
    public void setIdRiesgoPuesto(Integer idRiesgoPuesto) {
        this.idRiesgoPuesto = idRiesgoPuesto;
    }

    /**
     * @return the dialogRiesgoPuestoEliminar
     */
    public Boolean getDialogRiesgoPuestoEliminar() {
        return dialogRiesgoPuestoEliminar;
    }

    /**
     * @param dialogRiesgoPuestoEliminar
     *            the dialogRiesgoPuestoEliminar to set
     */
    public void setDialogRiesgoPuestoEliminar(Boolean dialogRiesgoPuestoEliminar) {
        this.dialogRiesgoPuestoEliminar = dialogRiesgoPuestoEliminar;
    }

}
