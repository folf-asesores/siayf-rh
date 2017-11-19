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
    private String unidadResponsable;
    private String numeroUnidadResponsable;
    private Map<String, NominaEmpleadoDTO> nominasEmpleados;

    public UnidadResponsableDTOBuilder(String numeroUnidadResponsable, String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
        this.numeroUnidadResponsable = numeroUnidadResponsable;
        nominasEmpleados = null;
    }

    public UnidadResponsableDTOBuilder setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
        return this;
    }

    public UnidadResponsableDTOBuilder setNumeroUnidadResponsable(String numeroUnidadResponsable) {
        this.numeroUnidadResponsable = numeroUnidadResponsable;
        return this;
    }

    public UnidadResponsableDTOBuilder setNominasEmpleados(Map<String, NominaEmpleadoDTO> nominasEmpleados) {
        this.nominasEmpleados = nominasEmpleados;
        return this;
    }

    public UnidadResponsableDTO createUnidadResponsableDTO() {
        return new UnidadResponsableDTO(numeroUnidadResponsable, unidadResponsable, nominasEmpleados);
    }
}
