/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 11:38:14
 *
 */
public class InfoSueldoDTO {
    private Integer idTabulador;
    private BigDecimal sueldoBrutoMensual;
    private BigDecimal asignacionBrutaMensual;
    private BigDecimal agaBrutaMensual;
    private BigDecimal totalBrutoMensual;
    private BigDecimal sueldoBaseMensualMinimo;
    private BigDecimal sueldoBaseMensualMedio;
    private BigDecimal sueldoBaseMensualMaximo;
    private String subClasificacion;

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

    public String getSubClasificacion() {
        return subClasificacion;
    }

    public void setSubClasificacion(String subClasificacion) {
        this.subClasificacion = subClasificacion;
    }

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public BigDecimal getSueldoBrutoMensual() {
        return sueldoBrutoMensual;
    }

    public void setSueldoBrutoMensual(BigDecimal sueldoBrutoMensual) {
        this.sueldoBrutoMensual = sueldoBrutoMensual;
    }

    public BigDecimal getAsignacionBrutaMensual() {
        return asignacionBrutaMensual;
    }

    public void setAsignacionBrutaMensual(BigDecimal asignacionBrutaMensual) {
        this.asignacionBrutaMensual = asignacionBrutaMensual;
    }

    public BigDecimal getAgaBrutaMensual() {
        return agaBrutaMensual;
    }

    public void setAgaBrutaMensual(BigDecimal agaBrutaMensual) {
        this.agaBrutaMensual = agaBrutaMensual;
    }

    public BigDecimal getTotalBrutoMensual() {
        return totalBrutoMensual;
    }

    public void setTotalBrutoMensual(BigDecimal totalBrutoMensual) {
        this.totalBrutoMensual = totalBrutoMensual;
    }

}
