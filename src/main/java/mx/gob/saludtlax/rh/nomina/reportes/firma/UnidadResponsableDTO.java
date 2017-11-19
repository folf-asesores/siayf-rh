/*
 * UnidadResponsableDTO.java
 * Creado el 27/jun/2017 3:16:41 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class UnidadResponsableDTO implements Serializable,
        Comparable<UnidadResponsableDTO>, Iterable<ProgramaDTO> {

    private static final long serialVersionUID = -8412099433503592992L;

    private final String numeroUnidadResponsable;
    private final String unidadResponsable;
    private final Map<Integer, ProgramaDTO> programas;

    public UnidadResponsableDTO(String numeroUnidadResponsable,
            String unidadResponsable, Map<Integer, ProgramaDTO> programas) {
        this.numeroUnidadResponsable = numeroUnidadResponsable;
        this.unidadResponsable = unidadResponsable;
        this.programas = programas;
    }

    public String getNumeroUnidadResponsable() {
        return numeroUnidadResponsable;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public Map<Integer, ProgramaDTO> getProgramas() {
        return programas;
    }

    @Override
    public int compareTo(UnidadResponsableDTO o) {
        if (numeroUnidadResponsable == null
                && o.numeroUnidadResponsable == null) {
            return 0;
        }
        if (numeroUnidadResponsable == null) {
            return -1;
        }
        if (o.numeroUnidadResponsable == null) {
            return 1;
        }
        return numeroUnidadResponsable.compareTo(o.numeroUnidadResponsable);
    }

    @Override
    public Iterator<ProgramaDTO> iterator() {
        return programas.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(numeroUnidadResponsable);
        hash = 31 * hash + Objects.hashCode(unidadResponsable);
        hash = 31 * hash + Objects.hashCode(programas);
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
        if (!Objects.equals(numeroUnidadResponsable,
                other.numeroUnidadResponsable)) {
            return false;
        }
        if (!Objects.equals(unidadResponsable, other.unidadResponsable)) {
            return false;
        }
        return Objects.equals(programas, other.programas);
    }

    @Override
    public String toString() {
        return "UnidadResponsableDTO{" + "numeroUnidadResponsable : "
                + numeroUnidadResponsable + ", unidadResponsable : "
                + unidadResponsable + ", programas : [" + programas + "]}";
    }

    public static final class Builder {
        private String unidadResponsable;
        private String numeroUnidadResponsable;
        private Map<Integer, ProgramaDTO> programas;

        public Builder(String numeroUnidadResponsable,
                String unidadResponsable) {
            this.unidadResponsable = unidadResponsable;
            this.numeroUnidadResponsable = numeroUnidadResponsable;
            programas = null;
        }

        public Builder setUnidadResponsable(String unidadResponsable) {
            this.unidadResponsable = unidadResponsable;
            return this;
        }

        public Builder setNumeroUnidadResponsable(
                String numeroUnidadResponsable) {
            this.numeroUnidadResponsable = numeroUnidadResponsable;
            return this;
        }

        public Builder setProgramas(Map<Integer, ProgramaDTO> programas) {
            this.programas = programas;
            return this;
        }

        public UnidadResponsableDTO construirUnidadResponsableDTO() {
            return new UnidadResponsableDTO(numeroUnidadResponsable,
                    unidadResponsable, programas);
        }
    }
}
