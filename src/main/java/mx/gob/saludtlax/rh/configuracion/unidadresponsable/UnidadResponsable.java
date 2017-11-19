/*
 *
 * UnidadResponsable.java
 * Creado el Jul 30, 2016 11:16:35 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface UnidadResponsable {

    UnidadResponsableDTO actualizarUnidadResponsable(UnidadResponsableDTO dto);

    List<String> consultarDescripcionUnidadesResponsablesPorCriterio(
            String consulta);

    Integer consultarIdUnidadResponsablePorDescripcion(String descripcion);

    UnidadResponsableDTO crearUnidadResponsable(UnidadResponsableDTO dto);

    void eliminarUnidadResponsable(UnidadResponsableDTO dto);

    UnidadResponsableDTO nuevoUnidadResponsable();

    UnidadResponsableDTO obtenerUnidadResponsable(
            UnidadResponsableDTO unidadResponsable);

    List<UnidadResponsableDTO> obtenerUnidadResponsableLista();

}
