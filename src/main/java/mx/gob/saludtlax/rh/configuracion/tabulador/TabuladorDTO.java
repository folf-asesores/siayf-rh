/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 28/07/2016 13:11:18
 */
public class TabuladorDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5301339598562541691L;

    private Integer idTabulador;

    private Integer idPuestoGeneral = 0;

    private Integer idTipoTabulador = 0;

    private Integer idEjercicioFiscal = 0;

    private String descripcionTipoTabulador;

    private Integer ejercicioFiscal;

    private String codigoPuestoGeneral;

    private String nivelTipoPuesto;

    private String nombreRamaPuesto;

    private String nombrePuestoGeneral;

    private BigDecimal sueldoBrutoMensual;

    private BigDecimal asignacionBrutaMensual;

    private BigDecimal agaBrutaMensual;

    private BigDecimal totalBrutoMensual;

    private BigDecimal sueldoBaseMensualMinimo;

    private BigDecimal sueldoBaseMensualMedio;

    private BigDecimal sueldoBaseMensualMaximo;

    private BigDecimal sueldoDiario;

    private String subClasificacion;

    private Integer idSubClasificacion = 0;

    /**
     * @return the idTabulador
     */

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public BigDecimal getSueldoDiario() {
        return sueldoDiario;
    }

    public void setSueldoDiario(BigDecimal sueldoDiario) {
        this.sueldoDiario = sueldoDiario;
    }

    public String getSubClasificacion() {
        return subClasificacion;
    }

    public void setSubClasificacion(String subClasificacion) {
        this.subClasificacion = subClasificacion;
    }

    public BigDecimal getSueldoBaseMensualMinimo() {
        return sueldoBaseMensualMinimo;
    }

    public void setSueldoBaseMensualMinimo(BigDecimal sueldoBaseMensualMinimo) {
        this.sueldoBaseMensualMinimo = sueldoBaseMensualMinimo;
    }

    public BigDecimal getSueldoBaseMensualMedio() {
        return sueldoBaseMensualMedio;
    }

    public void setSueldoBaseMensualMedio(BigDecimal sueldoBaseMensualMedio) {
        this.sueldoBaseMensualMedio = sueldoBaseMensualMedio;
    }

    public BigDecimal getSueldoBaseMensualMaximo() {
        return sueldoBaseMensualMaximo;
    }

    public void setSueldoBaseMensualMaximo(BigDecimal sueldoBaseMensualMaximo) {
        this.sueldoBaseMensualMaximo = sueldoBaseMensualMaximo;
    }

    /**
     * @param idTabulador
     *            the idTabulador to set
     */
    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    /**
     * @return the codigoPuestoGeneral
     */
    public String getCodigoPuestoGeneral() {
        return codigoPuestoGeneral;
    }

    /**
     * @param codigoPuestoGeneral
     *            the codigoPuestoGeneral to set
     */
    public void setCodigoPuestoGeneral(String codigoPuestoGeneral) {
        this.codigoPuestoGeneral = codigoPuestoGeneral;
    }

    /**
     * @return the nivelTipoPuesto
     */
    public String getNivelTipoPuesto() {
        return nivelTipoPuesto;
    }

    /**
     * @param nivelTipoPuesto
     *            the nivelTipoPuesto to set
     */
    public void setNivelTipoPuesto(String nivelTipoPuesto) {
        this.nivelTipoPuesto = nivelTipoPuesto;
    }

    /**
     * @return the nombreRamaPuesto
     */
    public String getNombreRamaPuesto() {
        return nombreRamaPuesto;
    }

    /**
     * @param nombreRamaPuesto
     *            the nombreRamaPuesto to set
     */
    public void setNombreRamaPuesto(String nombreRamaPuesto) {
        this.nombreRamaPuesto = nombreRamaPuesto;
    }

    /**
     * @return the nombrePuestoGeneral
     */
    public String getNombrePuestoGeneral() {
        return nombrePuestoGeneral;
    }

    /**
     * @param nombrePuestoGeneral
     *            the nombrePuestoGeneral to set
     */
    public void setNombrePuestoGeneral(String nombrePuestoGeneral) {
        this.nombrePuestoGeneral = nombrePuestoGeneral;
    }

    /**
     * @return the sueldoBrutoMensual
     */
    public BigDecimal getSueldoBrutoMensual() {
        return sueldoBrutoMensual;
    }

    /**
     * @param sueldoBrutoMensual
     *            the sueldoBrutoMensual to set
     */
    public void setSueldoBrutoMensual(BigDecimal sueldoBrutoMensual) {
        this.sueldoBrutoMensual = sueldoBrutoMensual;
    }

    /**
     * @return the asignacionBrutaMensual
     */
    public BigDecimal getAsignacionBrutaMensual() {
        return asignacionBrutaMensual;
    }

    /**
     * @param asignacionBrutaMensual
     *            the asignacionBrutaMensual to set
     */
    public void setAsignacionBrutaMensual(BigDecimal asignacionBrutaMensual) {
        this.asignacionBrutaMensual = asignacionBrutaMensual;
    }

    /**
     * @return the agaBrutaMensual
     */
    public BigDecimal getAgaBrutaMensual() {
        return agaBrutaMensual;
    }

    /**
     * @param agaBrutaMensual
     *            the agaBrutaMensual to set
     */
    public void setAgaBrutaMensual(BigDecimal agaBrutaMensual) {
        this.agaBrutaMensual = agaBrutaMensual;
    }

    /**
     * @return the totalBrutoMensual
     */
    public BigDecimal getTotalBrutoMensual() {
        return totalBrutoMensual;
    }

    /**
     * @param totalBrutoMensual
     *            the totalBrutoMensual to set
     */
    public void setTotalBrutoMensual(BigDecimal totalBrutoMensual) {
        this.totalBrutoMensual = totalBrutoMensual;
    }

    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    /**
     * @return the descripcionTipoTabulador
     */
    public String getDescripcionTipoTabulador() {
        return descripcionTipoTabulador;
    }

    /**
     * @param descripcionTipoTabulador
     *            the descripcionTipoTabulador to set
     */
    public void setDescripcionTipoTabulador(String descripcionTipoTabulador) {
        this.descripcionTipoTabulador = descripcionTipoTabulador;
    }

    /**
     * @return the idTipoTabulador
     */
    public Integer getIdTipoTabulador() {
        return idTipoTabulador;
    }

    /**
     * @param idTipoTabulador
     *            the idTipoTabulador to set
     */
    public void setIdTipoTabulador(Integer idTipoTabulador) {
        this.idTipoTabulador = idTipoTabulador;
    }

    /**
     * @return the ejercicioFiscal
     */
    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    /**
     * @param ejercicioFiscal
     *            the ejercicioFiscal to set
     */
    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    /**
     * @return the idEjercicioFiscal
     */
    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    /**
     * @param idEjercicioFiscal
     *            the idEjercicioFiscal to set
     */
    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }

    /**
     * @return the idSubClasificacion
     */
    public Integer getIdSubClasificacion() {
        return idSubClasificacion;
    }

    /**
     * @param idSubClasificacion
     *            the idSubClasificacion to set
     */
    public void setIdSubClasificacion(Integer idSubClasificacion) {
        this.idSubClasificacion = idSubClasificacion;
    }

}
