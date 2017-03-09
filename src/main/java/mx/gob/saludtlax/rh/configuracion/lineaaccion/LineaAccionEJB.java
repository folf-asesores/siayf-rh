/*
 * 
 * LineaAccionEJB.java
 * Creado el Jul 27, 2016 1:29:17 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.lineaaccion;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Stateless
public class LineaAccionEJB implements LineaAccion {
    
    @Inject LineaAccionService lineaAccionService;
    
    @Override
    public LineaAccionDTO obtenerPorId(int idLineaAccion) {
        return lineaAccionService.obtenerPorId(idLineaAccion);
    }

    @Override
    public List<String> consultarDescripcionLineaAccionPorCriterio(String consulta) {
        return lineaAccionService.consultarDescripcionLineaAccionPorCriterio(consulta);
    }

    @Override
    public Integer consultarIdLineaAccionPorDescripcion(String descripcion) {
        return lineaAccionService.consultarIdLineaAccionPorDescripcion(descripcion);
    }
}
