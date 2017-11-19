/*
 * NominaEmpleadoDTO.java
 * Creado el 27/Jun/2017 3:28:25 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(rfc);
        hash = 29 * hash + Objects.hashCode(nombre);
        hash = 29 * hash + Objects.hashCode(percepciones);
        hash = 29 * hash + Objects.hashCode(deducciones);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NominaEmpleadoDTO other = (NominaEmpleadoDTO) obj;
        if (!Objects.equals(rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(percepciones, other.percepciones)) {
            return false;
        }
        return Objects.equals(deducciones, other.deducciones);
    }

    @Override
    public String toString() {
        return "NominaEmpleadoDTO{" + "rfc=" + rfc + ", nombre=" + nombre
                + ", percepciones=" + percepciones + ", deducciones="
                + deducciones + '}';
    }

}
