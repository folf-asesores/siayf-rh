/*
 *
 * LineaAccionService.java
 * Creado el Jul 27, 2016 1:51:03 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.lineaaccion;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.LineaAccionEntity;
import mx.gob.saludtlax.rh.persistencia.LineaAccionRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class LineaAccionService {
    @Inject
    private LineaAccionRepository lineaAccionRepository;

    protected LineaAccionDTO obtenerPorId(int idLineaAccion) {
        LineaAccionEntity entidad = lineaAccionRepository.obtenerPorId(idLineaAccion);

        if (entidad != null) {
            return convertirEntidadADTO(entidad);
        } else {
            return null;
        }
    }

    protected List<String> consultarDescripcionLineaAccionPorCriterio(String consulta) {
        return lineaAccionRepository.consultarDescripcionLineaAccionPorCriterio(consulta);
    }

    protected Integer consultarIdLineaAccionPorDescripcion(String descripcion) {
        return lineaAccionRepository.consultarIdLineaAccionPorDescripcion(descripcion);
    }

    private static LineaAccionDTO convertirEntidadADTO(LineaAccionEntity entidad) {
        LineaAccionDTO dto = new LineaAccionDTO();

        dto.setIdLineaAccion(entidad.getIdLineaAccion());
        dto.setCodigoLinea(entidad.getCodigoLinea());
        dto.setDescripcion(entidad.getLineaAccion());

        return dto;
    }

}
