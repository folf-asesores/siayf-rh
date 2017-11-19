/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-13:17:08
 */
public class DomicilioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5227783616243884721L;

    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private int codigoPostal;
    private Integer idEstado;
    private Integer idMunicipio;
    private Integer idAsentamiento;
    private boolean tieneDireccion;

    private String comentarioMovimiento = "";
    private Integer idUsuarioEnSesion;

    @Override
    public String toString() {
        return "DomicilioDTO [calle=" + calle + ", numeroExterior="
                + numeroExterior + ", numeroInterior=" + numeroInterior
                + ", codigoPostal=" + codigoPostal + ", idEstado=" + idEstado
                + ", idMunicipio=" + idMunicipio + ", idAsentamiento="
                + idAsentamiento + ", tieneDireccion=" + tieneDireccion + "]";
    }

    public String lccDireccion() {
        return "DomicilioDTO [calle=" + calle + ", numeroExterior="
                + numeroExterior + ", numeroInterior=" + numeroInterior
                + ", codigoPostal=" + codigoPostal + ", idEstado=" + idEstado
                + ", idMunicipio=" + idMunicipio + ", idAsentamiento="
                + idAsentamiento + "]";
    }

    public boolean tieneDatosObligatorios() {
        boolean tieneDatosObligatorios = true;
        if (ValidacionUtil.esCadenaVacia(calle)
                || ValidacionUtil.esCadenaVacia(numeroExterior)) {
            tieneDatosObligatorios = false;
        }

        if (!ValidacionUtil.esNumeroPositivo(codigoPostal)
                || !ValidacionUtil.esNumeroPositivo(idMunicipio)
                || !ValidacionUtil.esNumeroPositivo(idAsentamiento)) {
            tieneDatosObligatorios = false;
        }

        return tieneDatosObligatorios;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public boolean isTieneDireccion() {
        return tieneDireccion;
    }

    public void setTieneDireccion(boolean tieneDireccion) {
        this.tieneDireccion = tieneDireccion;
    }

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

    public Integer getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Integer idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    /**
     * @return the comentarioMovimiento
     */
    public String getComentarioMovimiento() {
        return comentarioMovimiento;
    }

    /**
     * @param comentarioMovimiento
     *            the comentarioMovimiento to set
     */
    public void setComentarioMovimiento(String comentarioMovimiento) {
        this.comentarioMovimiento = comentarioMovimiento;
    }

    /**
     * @return the idUsuarioEnSesion
     */
    public Integer getIdUsuarioEnSesion() {
        return idUsuarioEnSesion;
    }

    /**
     * @param idUsuarioEnSesion
     *            the idUsuarioEnSesion to set
     */
    public void setIdUsuarioEnSesion(Integer idUsuarioEnSesion) {
        this.idUsuarioEnSesion = idUsuarioEnSesion;
    }

}
