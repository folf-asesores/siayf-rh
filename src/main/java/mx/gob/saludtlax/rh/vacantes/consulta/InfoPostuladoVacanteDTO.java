/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.consulta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 16/08/2016 11:56:11
 */
public class InfoPostuladoVacanteDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3705557752809875104L;

    private Integer idPostuladoVacante;

    private String tipoContratacion;

    private String nombramiento;

    private String folioVacante;

    private String codigoPuesto;

    private String puesto;

    private BigDecimal sueldo;

    private List<InfoCandidatoDTO> listaInfoCandidatoVacante;

    /**
     *
     */
    public InfoPostuladoVacanteDTO() {
        super();
    }

    /**
     * @return the tipoContratacion
     */
    public String getTipoContratacion() {
        return tipoContratacion;
    }

    /**
     * @param tipoContratacion
     *            the tipoContratacion to set
     */
    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    /**
     * @return the nombramiento
     */
    public String getNombramiento() {
        return nombramiento;
    }

    /**
     * @param nombramiento
     *            the nombramiento to set
     */
    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    /**
     * @return the folioVacante
     */
    public String getFolioVacante() {
        return folioVacante;
    }

    /**
     * @param folioVacante
     *            the folioVacante to set
     */
    public void setFolioVacante(String folioVacante) {
        this.folioVacante = folioVacante;
    }

    /**
     * @return the codigoPuesto
     */
    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    /**
     * @param codigoPuesto
     *            the codigoPuesto to set
     */
    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto
     *            the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the sueldo
     */
    public BigDecimal getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo
     *            the sueldo to set
     */
    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * @return the listaInfoCandidatoVacante
     */
    public List<InfoCandidatoDTO> getListaInfoCandidatoVacante() {
        return listaInfoCandidatoVacante;
    }

    /**
     * @param listaInfoCandidatoVacante
     *            the listaInfoCandidatoVacante to set
     */
    public void setListaInfoCandidatoVacante(
            List<InfoCandidatoDTO> listaInfoCandidatoVacante) {
        this.listaInfoCandidatoVacante = listaInfoCandidatoVacante;
    }

    /**
     * @return the idPostuladoVacante
     */
    public Integer getIdPostuladoVacante() {
        return idPostuladoVacante;
    }

    /**
     * @param idPostuladoVacante
     *            the idPostuladoVacante to set
     */
    public void setIdPostuladoVacante(Integer idPostuladoVacante) {
        this.idPostuladoVacante = idPostuladoVacante;
    }

}
