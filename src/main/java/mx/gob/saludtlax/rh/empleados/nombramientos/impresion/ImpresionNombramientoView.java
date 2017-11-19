/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.puestosautorizados.PuestoEmpleadoDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 13:31:22 13/09/2016
 */
public class ImpresionNombramientoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6898060539285305143L;

    private List<InfoNombramientoDTO> listaNombramiento = new ArrayList<>();

    private List<SelectItem> itemsTipoNombramiento = new ArrayList<>();

    private PuestoEmpleadoDTO puestoEmpleadoDTO = new PuestoEmpleadoDTO();

    private ClasificacionReporteDTO clasificacionReporteDTO = new ClasificacionReporteDTO();

    private InfoLugarAdscripcionNombramientoDTO infoLugarAdscripcionNombramientoDTO = new InfoLugarAdscripcionNombramientoDTO();

    private Integer tipoNombramiento;

    private Integer idNombramiento;

    private String tipoAdscripcion;

    private byte[] bytes = null;

    private boolean mostrarConfirmacionImpresion = false;

    private boolean mostrarFormalizado = false;

    private boolean mostrarInterinato = false;

    private boolean mostrarPrincipal = true;

    private boolean mostrarOpcionDescarga = false;

    private boolean imprimirNombramiento = false;

    private Map<String, String> itemsTiposAdscripcion = new LinkedHashMap<>();

    private String nombreTipoNombramiento;

    /**
     * @return the tipoNombramiento
     */
    public Integer getTipoNombramiento() {
        return tipoNombramiento;
    }

    /**
     * @param tipoNombramiento
     *            the tipoNombramiento to set
     */
    public void setTipoNombramiento(Integer tipoNombramiento) {
        this.tipoNombramiento = tipoNombramiento;
    }

    /**
     * @return the listaNombramiento
     */
    public List<InfoNombramientoDTO> getListaNombramiento() {
        return listaNombramiento;
    }

    /**
     * @param listaNombramiento
     *            the listaNombramiento to set
     */
    public void setListaNombramiento(
            List<InfoNombramientoDTO> listaNombramiento) {
        this.listaNombramiento = listaNombramiento;
    }

    /**
     * @return the itemsTipoNombramiento
     */
    public List<SelectItem> getItemsTipoNombramiento() {
        return itemsTipoNombramiento;
    }

    /**
     * @param itemsTipoNombramiento
     *            the itemsTipoNombramiento to set
     */
    public void setItemsTipoNombramiento(
            List<SelectItem> itemsTipoNombramiento) {
        this.itemsTipoNombramiento = itemsTipoNombramiento;
    }

    /**
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes
     *            the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * @return the idNombramiento
     */
    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    /**
     * @param idNombramiento
     *            the idNombramiento to set
     */
    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    /**
     * @return the mostrarConfirmacionImpresion
     */
    public boolean isMostrarConfirmacionImpresion() {
        return mostrarConfirmacionImpresion;
    }

    /**
     * @param mostrarConfirmacionImpresion
     *            the mostrarConfirmacionImpresion to set
     */
    public void setMostrarConfirmacionImpresion(
            boolean mostrarConfirmacionImpresion) {
        this.mostrarConfirmacionImpresion = mostrarConfirmacionImpresion;
    }

    /**
     * @return the mostrarPrincipal
     */
    public boolean isMostrarPrincipal() {
        return mostrarPrincipal;
    }

    /**
     * @param mostrarPrincipal
     *            the mostrarPrincipal to set
     */
    public void setMostrarPrincipal(boolean mostrarPrincipal) {
        this.mostrarPrincipal = mostrarPrincipal;
    }

    /**
     * @return the puestoEmpleadoDTO
     */
    public PuestoEmpleadoDTO getPuestoEmpleadoDTO() {
        return puestoEmpleadoDTO;
    }

    /**
     * @param puestoEmpleadoDTO
     *            the puestoEmpleadoDTO to set
     */
    public void setPuestoEmpleadoDTO(PuestoEmpleadoDTO puestoEmpleadoDTO) {
        this.puestoEmpleadoDTO = puestoEmpleadoDTO;
    }

    /**
     * @return the infoLugarAdscripcionNombramientoDTO
     */
    public InfoLugarAdscripcionNombramientoDTO getInfoLugarAdscripcionNombramientoDTO() {
        return infoLugarAdscripcionNombramientoDTO;
    }

    /**
     * @param infoLugarAdscripcionNombramientoDTO
     *            the infoLugarAdscripcionNombramientoDTO to set
     */
    public void setInfoLugarAdscripcionNombramientoDTO(
            InfoLugarAdscripcionNombramientoDTO infoLugarAdscripcionNombramientoDTO) {
        this.infoLugarAdscripcionNombramientoDTO = infoLugarAdscripcionNombramientoDTO;
    }

    /**
     * @return the tipoAdscripcion
     */
    public String getTipoAdscripcion() {
        return tipoAdscripcion;
    }

    /**
     * @param tipoAdscripcion
     *            the tipoAdscripcion to set
     */
    public void setTipoAdscripcion(String tipoAdscripcion) {
        this.tipoAdscripcion = tipoAdscripcion;
    }

    /**
     * @return the itemsTiposAdscripcion
     */
    public Map<String, String> getItemsTiposAdscripcion() {
        return itemsTiposAdscripcion;
    }

    /**
     * @param itemsTiposAdscripcion
     *            the itemsTiposAdscripcion to set
     */
    public void setItemsTiposAdscripcion(
            Map<String, String> itemsTiposAdscripcion) {
        this.itemsTiposAdscripcion = itemsTiposAdscripcion;
    }

    /**
     * @return the imprimirNombramiento
     */
    public boolean isImprimirNombramiento() {
        return imprimirNombramiento;
    }

    /**
     * @param imprimirNombramiento
     *            the imprimirNombramiento to set
     */
    public void setImprimirNombramiento(boolean imprimirNombramiento) {
        this.imprimirNombramiento = imprimirNombramiento;
    }

    /**
     * @return the clasificacionReporteDTO
     */
    public ClasificacionReporteDTO getClasificacionReporteDTO() {
        return clasificacionReporteDTO;
    }

    /**
     * @param clasificacionReporteDTO
     *            the clasificacionReporteDTO to set
     */
    public void setClasificacionReporteDTO(
            ClasificacionReporteDTO clasificacionReporteDTO) {
        this.clasificacionReporteDTO = clasificacionReporteDTO;
    }

    /**
     * @return the nombreTipoNombramiento
     */
    public String getNombreTipoNombramiento() {
        return nombreTipoNombramiento;
    }

    /**
     * @param nombreTipoNombramiento
     *            the nombreTipoNombramiento to set
     */
    public void setNombreTipoNombramiento(String nombreTipoNombramiento) {
        this.nombreTipoNombramiento = nombreTipoNombramiento;
    }

    public boolean isMostrarFormalizado() {
        return mostrarFormalizado;
    }

    public void setMostrarFormalizado(boolean mostrarFormalizado) {
        this.mostrarFormalizado = mostrarFormalizado;
    }

    public boolean isMostrarInterinato() {
        return mostrarInterinato;
    }

    public void setMostrarInterinato(boolean mostrarInterinato) {
        this.mostrarInterinato = mostrarInterinato;
    }

    public boolean isMostrarOpcionDescarga() {
        return mostrarOpcionDescarga;
    }

    public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
        this.mostrarOpcionDescarga = mostrarOpcionDescarga;
    }

}
