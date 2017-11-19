/*
 *
 * Sector.java
 * Creado el Jul 27, 2016 12:22:41 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.sector;

import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Sector {

    /**
     * Permite obtener un sector por medio de su ID.
     *
     * @param idSector
     *            el ID del sector a consultar.
     * @return una instancia con la información del sector si existe sino
     *         devuelve null.
     */
    SectorDTO obtenerPorId(Integer idSector);

    List<String> consultarDescripcionSectorPorCriterio(String consulta);

    Integer consultarIdSectorPorDescripcion(String descripcion);

}
