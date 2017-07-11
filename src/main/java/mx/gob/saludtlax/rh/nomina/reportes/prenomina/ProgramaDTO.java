/*
 * ProgramaDTO.java
 * Creado el 27/Jun/2017 3:15:11 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

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

    public ProgramaDTO(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo, Map<String, UnidadResponsableDTO> unidadesResponsables) {
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
    public String toString() {
        return "Programa{" + "idPrograma=" + idPrograma + ", programa=" + programa + ", inicioPeriodo=" + inicioPeriodo + ", finPeriodo=" + finPeriodo + ", unidadesResponsables=" + unidadesResponsables + '}';
    }
}
