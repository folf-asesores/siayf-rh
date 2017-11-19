/*
 * ProgramaDTO.java
 * Creado el 27/Jun/2017 3:15:11 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ProgramaDTO implements Iterable<UnidadResponsableDTO> {

    private final Integer idPrograma;
    private final String programa;
    private final Date inicioPeriodo;
    private final Date finPeriodo;
    private final Map<String, UnidadResponsableDTO> unidadesResponsables;

    public ProgramaDTO(Integer idPrograma, String programa, Date inicioPeriodo,
            Date finPeriodo,
            Map<String, UnidadResponsableDTO> unidadesResponsables) {
        this.idPrograma = idPrograma;
        this.programa = programa;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.unidadesResponsables = unidadesResponsables;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public Map<String, UnidadResponsableDTO> getUnidadesResponsables() {
        return unidadesResponsables;
    }

    @Override
    public Iterator<UnidadResponsableDTO> iterator() {
        return unidadesResponsables.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(idPrograma);
        hash = 47 * hash + Objects.hashCode(programa);
        hash = 47 * hash + Objects.hashCode(inicioPeriodo);
        hash = 47 * hash + Objects.hashCode(finPeriodo);
        hash = 47 * hash + Objects.hashCode(unidadesResponsables);
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
        final ProgramaDTO other = (ProgramaDTO) obj;
        if (!Objects.equals(programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(idPrograma, other.idPrograma)) {
            return false;
        }
        if (!Objects.equals(inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(finPeriodo, other.finPeriodo)) {
            return false;
        }
        return Objects.equals(unidadesResponsables, other.unidadesResponsables);
    }

    @Override
    public String toString() {
        return "Programa{" + "idPrograma=" + idPrograma + ", programa="
                + programa + ", inicioPeriodo=" + inicioPeriodo
                + ", finPeriodo=" + finPeriodo + ", unidadesResponsables="
                + unidadesResponsables + '}';
    }
}
