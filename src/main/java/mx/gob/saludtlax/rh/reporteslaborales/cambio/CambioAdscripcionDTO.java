
package mx.gob.saludtlax.rh.reporteslaborales.cambio;

import java.io.Serializable;

/**
 * @author Daniela Hernandez
 *
 */

public class CambioAdscripcionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7501600881610290597L;
    private Integer idMovimiento;
    private String asunto;
    private String presenteNombre;
    private String presenteClaveUno;
    private String presenteClaveDos;
    private String fecha;
    private String fechaCambio;
    private String cambioAdscripcion;
    private String funcion;
    private String clavePresupuestal;
    private String turno;
    private String encargadoLabores;
    private String secretarioSalud;

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

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getCambioAdscripcion() {
        return cambioAdscripcion;
    }

    public void setCambioAdscripcion(String cambioAdscripcion) {
        this.cambioAdscripcion = cambioAdscripcion;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEncargadoLabores() {
        return encargadoLabores;
    }

    public void setEncargadoLabores(String encargadoLabores) {
        this.encargadoLabores = encargadoLabores;
    }

    public String getSecretarioSalud() {
        return secretarioSalud;
    }

    public void setSecretarioSalud(String secretarioSalud) {
        this.secretarioSalud = secretarioSalud;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

}
