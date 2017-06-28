/*
 * UnidadResponsable.java
 * Creado el 27/Jun/2017 3:16:41 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class UnidadResponsable implements Iterable<NominaEmpleado> {

    private List<NominaEmpleado> nominasEmpleados;
    private String unidadResponsable;
    private Integer numeroUnidadResponsable;

    public List<NominaEmpleado> getNominasEmpleados() {
        return nominasEmpleados;
    }

    public void setNominasEmpleados(List<NominaEmpleado> nominasEmpleados) {
        this.nominasEmpleados = nominasEmpleados;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public Integer getNumeroUnidadResponsable() {
        return numeroUnidadResponsable;
    }

    public void setNumeroUnidadResponsable(Integer numeroUnidadResponsable) {
        this.numeroUnidadResponsable = numeroUnidadResponsable;
    }



    @Override
    public Iterator<NominaEmpleado> iterator() {
        return nominasEmpleados.iterator();
    }
}
