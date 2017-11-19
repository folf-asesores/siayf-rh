/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/11/2016 16:15:04
 */
public class AltaVoluntarioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3216754999435777280L;
    private Integer idAspirante;
    private BigDecimal sueldoMensual;
    private Date fechaInicial;
    private Date fechaFinal;
    private String numeroCuenta;
    private Date fechaIngreso;
    private Integer idPrograma;
    private Integer idAdscripcion;
    private Integer idSubadscripcion;
    private Integer idServicio;
    private Integer idFuncion;

    public Integer getIdAdscripcion() {
        return idAdscripcion;
    }

    public void setIdAdscripcion(Integer idAdscripcion) {
        this.idAdscripcion = idAdscripcion;
    }

    public Integer getIdSubadscripcion() {
        return idSubadscripcion;
    }

    public void setIdSubadscripcion(Integer idSubadscripcion) {
        this.idSubadscripcion = idSubadscripcion;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
