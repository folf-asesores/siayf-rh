/*
 * UnidadResponsableDTO.java
 * Creado el 27/Jun/2017 3:16:41 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class UnidadResponsableDTO implements Iterable<NominaEmpleadoDTO> {

    private final String numeroUnidadResponsable;
    private final String unidadResponsable;
    private final Map<String, NominaEmpleadoDTO> nominasEmpleados;

    public UnidadResponsableDTO(String numeroUnidadResponsable, String unidadResponsable, Map<String, NominaEmpleadoDTO> nominasEmpleados) {
        this.unidadResponsable = unidadResponsable;
        this.numeroUnidadResponsable = numeroUnidadResponsable;
        this.nominasEmpleados = nominasEmpleados;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public String getNumeroUnidadResponsable() {
        return numeroUnidadResponsable;
    }

    public Map<String, NominaEmpleadoDTO> getNominasEmpleados() {
        return nominasEmpleados;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.unidadResponsable);
        hash = 47 * hash + Objects.hashCode(this.numeroUnidadResponsable);
        hash = 47 * hash + Objects.hashCode(this.nominasEmpleados);
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
        final UnidadResponsableDTO other = (UnidadResponsableDTO) obj;
        if (!Objects.equals(this.unidadResponsable, other.unidadResponsable)) {
            return false;
        }
        if (!Objects.equals(this.numeroUnidadResponsable, other.numeroUnidadResponsable)) {
            return false;
        }
        return Objects.equals(this.nominasEmpleados, other.nominasEmpleados);
    }

    @Override
    public Iterator<NominaEmpleadoDTO> iterator() {
        return nominasEmpleados.values().iterator();
    }
}
