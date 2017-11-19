
package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;

public class IncidenciaEmpleadoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3660721530731786930L;

    private Integer idIncidenciaEmpleado;

    private String descripcionIncidencia;

    private boolean imagen;

    private String fecha;

    private String oficio;

    private String fechaInicio;

    private String fechaFin;
    private String horaInicio;
    private String horaFin;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdIncidenciaEmpleado() {
        return idIncidenciaEmpleado;
    }

    public void setIdIncidenciaEmpleado(Integer idIncidenciaEmpleado) {
        this.idIncidenciaEmpleado = idIncidenciaEmpleado;
    }

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public boolean isImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

}
