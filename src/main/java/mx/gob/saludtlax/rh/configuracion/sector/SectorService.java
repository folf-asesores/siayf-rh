/*
 *
 * SectorService.java
 * Creado el Jul 27, 2016 12:21:27 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.sector;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.SectorEntity;
import mx.gob.saludtlax.rh.persistencia.SectorRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SectorService {

    @Inject
    private SectorRepository sectorRepository;

    protected SectorDTO obtenerPorId(Integer idSector) {
        SectorEntity entidad = sectorRepository.obtenerPorId(idSector);

        if (entidad != null) {
            return convertirEntidadADTO(entidad);
        } else {
            return null;
        }
    }

    protected List<String> consultarDescripcionSectorPorCriterio(String consulta) {
        return sectorRepository.consultarDescripcionSectorPorCriterio(consulta);
    }

    protected Integer consultarIdSectorPorDescripcion(String descripcion) {
        return sectorRepository.consultarIdSectorPorDescripcion(descripcion);
    }

    private static SectorDTO convertirEntidadADTO(SectorEntity entidad) {
        SectorDTO dto = new SectorDTO();

        dto.setIdSectores(entidad.getIdSector());
        dto.setDescripcion(entidad.getSector());

        return dto;
    }

}
