/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 * @since 14/09/2016 15:30:09
 *
 */
public class InfoMovimientoDTO {

    private Integer idMovimiento;
    private String movimientoHijo;
    private String empleado;
    private Date fechaMovimiento;
    private Date fechaInicio;
    private Date fechaFin;
    private String usuario;

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getMovimientoHijo() {
        return movimientoHijo;
    }

    public void setMovimientoHijo(String movimientoHijo) {
        this.movimientoHijo = movimientoHijo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
