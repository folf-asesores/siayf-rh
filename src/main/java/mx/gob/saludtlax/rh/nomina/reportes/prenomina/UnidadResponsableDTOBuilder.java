/*
 * UnidadResponsableDTOBuilder.java
 * Creado el 11/Jul/2017 6:26:03 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class UnidadResponsableDTOBuilder {
    private String unidadResponsable = null;
    private String numeroUnidadResponsable = null;
    private Map<String, NominaEmpleadoDTO> nominasEmpleados = null;

    public UnidadResponsableDTOBuilder(String unidadResponsable, String numeroUnidadResponsable) {
        this.unidadResponsable = unidadResponsable;
        this.numeroUnidadResponsable = numeroUnidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public void setNumeroUnidadResponsable(String numeroUnidadResponsable) {
        this.numeroUnidadResponsable = numeroUnidadResponsable;
    }

    public void setNominasEmpleados(Map<String, NominaEmpleadoDTO> nominasEmpleados) {
        this.nominasEmpleados = nominasEmpleados;
    }

    public UnidadResponsableDTO createUnidadResponsableDTO() {
        return new UnidadResponsableDTO(unidadResponsable, numeroUnidadResponsable, nominasEmpleados);
    }
}
