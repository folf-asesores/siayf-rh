/*
 * NominaEmpleado.java
 * Creado el 27/Jun/2017 3:28:25 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaEmpleado {

    private String rfc;
    private String nombre;
    private Map<String, Percepcion> percepciones;
    private Map<String, Deduccion> deducciones;

    public Map<String, Percepcion> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(Map<String, Percepcion> percepciones) {
        this.percepciones = percepciones;
    }

    public Map<String, Deduccion> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(Map<String, Deduccion> deducciones) {
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
