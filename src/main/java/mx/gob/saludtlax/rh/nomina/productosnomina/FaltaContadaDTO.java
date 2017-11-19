
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.Date;

public class FaltaContadaDTO {
    private Integer idFaltaContada;
    private Integer idNominaEmpleado;
    private Integer idFalta;
    private Integer idMovimientoEventual;
    private Date fechaFalta;

    public Integer getIdFaltaContada() {
        return idFaltaContada;
    }

    public void setIdFaltaContada(Integer idFaltaContada) {
        this.idFaltaContada = idFaltaContada;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public Integer getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Integer idFalta) {
        this.idFalta = idFalta;
    }

    public Integer getIdMovimientoEventual() {
        return idMovimientoEventual;
    }

    public void setIdMovimientoEventual(Integer idMovimientoEventual) {
        this.idMovimientoEventual = idMovimientoEventual;
    }

    public Date getFechaFalta() {
        return fechaFalta;
    }

    public void setFechaFalta(Date fechaFalta) {
        this.fechaFalta = fechaFalta;
    }
}
