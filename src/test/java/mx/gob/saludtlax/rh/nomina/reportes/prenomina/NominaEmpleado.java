/*
 * NominaEmpleado.java
 * Creado el 27/Jun/2017 3:28:25 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaEmpleado {

    private String rfc;
    private String nombre;
    private List<Percepcion> percepciones;
    private List<Deduccion> deducciones;

    public List<Percepcion> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(List<Percepcion> percepciones) {
        this.percepciones = percepciones;
    }

    public List<Deduccion> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(List<Deduccion> deducciones) {
        this.deducciones = deducciones;
    }

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

}
