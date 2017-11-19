
package mx.gob.saludtlax.rh.reporteslaborales.reincorporacion;

import java.io.Serializable;

public class ReincorporacionBaseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1372872742440298408L;
    private Integer idMovimiento;
    private String asunto;
    private String presenteNombre;
    private String presenteClaveUno;
    private String presenteClaveDos;
    private String fecha;
    private String fechaNombramiento;
    private String funcion;
    private String clavePresupuestal;
    private String directoraUnidad;
    private String directoraAdministracion;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getPresenteNombre() {
        return presenteNombre;
    }

    public void setPresenteNombre(String presenteNombre) {
        this.presenteNombre = presenteNombre;
    }

    public String getPresenteClaveUno() {
        return presenteClaveUno;
    }

    public void setPresenteClaveUno(String presenteClaveUno) {
        this.presenteClaveUno = presenteClaveUno;
    }

    public String getPresenteClaveDos() {
        return presenteClaveDos;
    }

    public void setPresenteClaveDos(String presenteClaveDos) {
        this.presenteClaveDos = presenteClaveDos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaNombramiento() {
        return fechaNombramiento;
    }

    public void setFechaNombramiento(String fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public String getDirectoraUnidad() {
        return directoraUnidad;
    }

    public void setDirectoraUnidad(String directoraUnidad) {
        this.directoraUnidad = directoraUnidad;
    }

    public String getDirectoraAdministracion() {
        return directoraAdministracion;
    }

    public void setDirectoraAdministracion(String directoraAdministracion) {
        this.directoraAdministracion = directoraAdministracion;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

}
