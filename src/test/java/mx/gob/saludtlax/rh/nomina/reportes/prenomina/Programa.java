/*
 * Programa.java
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
public class Programa implements Iterable<UnidadResponsable> {

    private Integer idPrograma;
    private String programa;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private Map<Integer, UnidadResponsable> unidadesResponsables;

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public Map<Integer, UnidadResponsable> getUnidadesResponsables() {
        return unidadesResponsables;
    }

    public void setUnidadesResponsables(Map<Integer, UnidadResponsable> unidadesResponsables) {
        this.unidadesResponsables = unidadesResponsables;
    }

    @Override
    public Iterator<UnidadResponsable> iterator() {
        return unidadesResponsables.values().iterator();
    }

}
