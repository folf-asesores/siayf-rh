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
    private Integer idPrograma =  null;
    private String programa = null;
    private Date inicioPeriodo = null;
    private Date finPeriodo = null;
    private Map<String, UnidadResponsableDTO> unidadesResponsables = null;

    public ProgramaDTOBuilder(Integer idPrograma, String programa, Date inicioPeriodo, Date finPeriodo) {
        this.idPrograma =  idPrograma;
        this.programa = programa;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public void setUnidadesResponsables(Map<String, UnidadResponsableDTO> unidadesResponsables) {
        this.unidadesResponsables = unidadesResponsables;
    }

    public ProgramaDTO createProgramaDTO() {
        return new ProgramaDTO(idPrograma, programa, inicioPeriodo, finPeriodo, unidadesResponsables);
    }
}
