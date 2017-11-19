/*
 * ProgramaDTOBuilder.java
 * Creado el 11/Jul/2017 6:14:58 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProgramaDTOBuilder {
    private Integer idPrograma;
    private String programa;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private Map<String, UnidadResponsableDTO> unidadesResponsables;

    public ProgramaDTOBuilder(Integer idPrograma, String programa,
            Date inicioPeriodo, Date finPeriodo) {
        this.idPrograma = idPrograma;
        this.programa = programa;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        unidadesResponsables = null;
    }

    public ProgramaDTOBuilder setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
        return this;
    }

    public ProgramaDTOBuilder setPrograma(String programa) {
        this.programa = programa;
        return this;
    }

    public ProgramaDTOBuilder setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
        return this;
    }

    public ProgramaDTOBuilder setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
        return this;
    }

    public ProgramaDTOBuilder setUnidadesResponsables(
            Map<String, UnidadResponsableDTO> unidadesResponsables) {
        this.unidadesResponsables = unidadesResponsables;
        return this;
    }

    public ProgramaDTO createProgramaDTO() {
        return new ProgramaDTO(idPrograma, programa, inicioPeriodo, finPeriodo,
                unidadesResponsables);
    }
}
