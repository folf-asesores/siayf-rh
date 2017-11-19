/*
 *
 * SectorEJB.java
 * Creado el Jul 27, 2016 12:22:40 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.sector;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class SectorEJB implements Sector {

    @Inject
    private SectorService sectorService;

    @Override
    public SectorDTO obtenerPorId(Integer idSector) {
        return sectorService.obtenerPorId(idSector);
    }

    @Override
    public List<String> consultarDescripcionSectorPorCriterio(String consulta) {
        return sectorService.consultarDescripcionSectorPorCriterio(consulta);
    }

    @Override
    public Integer consultarIdSectorPorDescripcion(String descripcion) {
        return sectorService.consultarIdSectorPorDescripcion(descripcion);
    }
}
