/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/04/2016-17:40:19
 */
public class DatosEmpleadoDTO {
    private String numeroEmpleado;
    private String nombramiento;
    private Date fechaIngreso;
    private Integer idAreaAdscripcion;
    private Integer nivel;
    private Integer idCargo;
    private Integer idDepartamento;
    private Integer idPlaza;

    public Integer getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(Integer idPlaza) {
        this.idPlaza = idPlaza;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdAreaAdscripcion() {
        return idAreaAdscripcion;
    }

    public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
        this.idAreaAdscripcion = idAreaAdscripcion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

}
