/*
 * NominaMandoMedioDTO.java
 * Creado el 29/Nov/2016 1:34:21 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaMandoMedioDTO implements Serializable {

    private static final long serialVersionUID = -3907124701083193488L;

    private Integer idNominaMandoMedio;
    private Integer idEmpleado;
    private String nombreEmpleado;
    private String rfc;
    private Integer idAdscripcion;
    private String adscripcion;
    private String puesto;
    private Integer idPuestoGeneral;
    private String codigoFuncional;
    private String descripcionCodigo;
    private String tipoContratacion;
    private BigDecimal complemento;
    private BigDecimal isr;
    private BigDecimal neto;

    /**
     * Get the value of idNominaMandoMedio
     *
     * @return the value of idNominaMandoMedio
     */
    public Integer getIdNominaMandoMedio() {
        return idNominaMandoMedio;
    }

    /**
     * Set the value of idNominaMandoMedio
     *
     * @param idNominaMandoMedio
     *            new value of idNominaMandoMedio
     */
    public void setIdNominaMandoMedio(Integer idNominaMandoMedio) {
        this.idNominaMandoMedio = idNominaMandoMedio;
    }

    /**
     * Get the value of idEmpleado
     *
     * @return the value of idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Set the value of idEmpleado
     *
     * @param idEmpleado
     *            new value of idEmpleado
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Get the value of nombreEmpleado
     *
     * @return the value of nombreEmpleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Set the value of nombreEmpleado
     *
     * @param nombreEmpleado
     *            new value of nombreEmpleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Get the value of rfc
     *
     * @return the value of rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Set the value of rfc
     *
     * @param rfc
     *            new value of rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Get the value of idAdscripcion
     *
     * @return the value of idAdscripcion
     */
    public Integer getIdAdscripcion() {
        return idAdscripcion;
    }

    /**
     * Set the value of idAdscripcion
     *
     * @param idAdscripcion
     *            new value of idAdscripcion
     */
    public void setIdAdscripcion(Integer idAdscripcion) {
        this.idAdscripcion = idAdscripcion;
    }

    /**
     * Get the value of adscripcion
     *
     * @return the value of adscripcion
     */
    public String getAdscripcion() {
        return adscripcion;
    }

    /**
     * Set the value of adscripcion
     *
     * @param adscripcion
     *            new value of adscripcion
     */
    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    /**
     * Get the value of puesto
     *
     * @return the value of puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Set the value of puesto
     *
     * @param puesto
     *            new value of puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Get the value of idPuestoGeneral
     *
     * @return the value of idPuestoGeneral
     */
    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    /**
     * Set the value of idPuestoGeneral
     *
     * @param idPuestoGeneral
     *            new value of idPuestoGeneral
     */
    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    /**
     * Get the value of codigoFuncional
     *
     * @return the value of codigoFuncional
     */
    public String getCodigoFuncional() {
        return codigoFuncional;
    }

    /**
     * Set the value of codigoFuncional
     *
     * @param codigoFuncional
     *            new value of codigoFuncional
     */
    public void setCodigoFuncional(String codigoFuncional) {
        this.codigoFuncional = codigoFuncional;
    }

    /**
     * Get the value of descripcionCodigo
     *
     * @return the value of descripcionCodigo
     */
    public String getDescripcionCodigo() {
        return descripcionCodigo;
    }

    /**
     * Set the value of descripcionCodigo
     *
     * @param descripcionCodigo
     *            new value of descripcionCodigo
     */
    public void setDescripcionCodigo(String descripcionCodigo) {
        this.descripcionCodigo = descripcionCodigo;
    }

    /**
     * Get the value of tipoContratacion
     *
     * @return the value of tipoContratacion
     */
    public String getTipoContratacion() {
        return tipoContratacion;
    }

    /**
     * Set the value of tipoContratacion
     *
     * @param tipoContratacion
     *            new value of tipoContratacion
     */
    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    /**
     * Get the value of complemento
     *
     * @return the value of complemento
     */
    public BigDecimal getComplemento() {
        return complemento;
    }

    /**
     * Set the value of complemento
     *
     * @param complemento
     *            new value of complemento
     */
    public void setComplemento(BigDecimal complemento) {
        this.complemento = complemento;
    }

    /**
     * Get the value of isr
     *
     * @return the value of isr
     */
    public BigDecimal getIsr() {
        return isr;
    }

    /**
     * Set the value of isr
     *
     * @param isr
     *            new value of isr
     */
    public void setIsr(BigDecimal isr) {
        this.isr = isr;
    }

    /**
     * Get the value of neto
     *
     * @return the value of neto
     */
    public BigDecimal getNeto() {
        return neto;
    }

    /**
     * Set the value of neto
     *
     * @param neto
     *            new value of neto
     */
    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

}
