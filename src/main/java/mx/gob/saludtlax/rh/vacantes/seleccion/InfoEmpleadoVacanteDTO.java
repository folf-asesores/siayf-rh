
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.util.Date;

public class InfoEmpleadoVacanteDTO {

    private String rfc;
    private String nombre;
    private Integer idEmpleado;
    private Integer numeroEmpleado;
    private Integer idInventarioVacante;
    private Integer idConfiguracionPresupuestal;
    private Date fechaInicioLabores;
    private Integer idNombramiento;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public Integer getIdInventarioVacante() {
        return idInventarioVacante;
    }

    public void setIdInventarioVacante(Integer idInventarioVacante) {
        this.idInventarioVacante = idInventarioVacante;
    }

    public Integer getIdConfiguracionPresupuestal() {
        return idConfiguracionPresupuestal;
    }

    public void setIdConfiguracionPresupuestal(Integer idConfiguracionPresupuestal) {
        this.idConfiguracionPresupuestal = idConfiguracionPresupuestal;
    }

    public Date getFechaInicioLabores() {
        return fechaInicioLabores;
    }

    public void setFechaInicioLabores(Date fechaInicioLabores) {
        this.fechaInicioLabores = fechaInicioLabores;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
