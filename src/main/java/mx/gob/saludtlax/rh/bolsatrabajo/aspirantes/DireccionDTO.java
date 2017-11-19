/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-13:17:08
 */
public class DireccionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5227783616243884721L;

    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private int codigoPostal;
    private Integer idEstado = 0;
    private Integer idMunicipio = 0;
    private Integer idAsentamiento = 0;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * @return the idAsentamiento
     */
    public Integer getIdAsentamiento() {
        return idAsentamiento;
    }

    /**
     * @param idAsentamiento
     *            the idAsentamiento to set
     */
    public void setIdAsentamiento(Integer idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    /**
     * @return the idEstado
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado
     *            the idEstado to set
     */
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

}
