/*
 * NominaEmpleadoDTO.java
 * Creado el 27/Jun/2017 3:28:25 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaEmpleadoDTO {

    private String rfc;
    private String nombre;
    private Map<String, PercepcionDTO> percepciones;
    private Map<String, DeduccionDTO> deducciones;

    public Map<String, PercepcionDTO> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(Map<String, PercepcionDTO> percepciones) {
        this.percepciones = percepciones;
    }

    public Map<String, DeduccionDTO> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(Map<String, DeduccionDTO> deducciones) {
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
