/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 05/09/2016 19:08:15
 */
public class IncapacidadDTO {
    private String diagnostico;
    private Date fechaInicio;
    private Date fechaFin;
    private int numeroDias;
    private String unidadAtencion;
    private Date fechaExpedicion;

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getUnidadAtencion() {
        return unidadAtencion;
    }

    public void setUnidadAtencion(String unidadAtencion) {
        this.unidadAtencion = unidadAtencion;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

}
