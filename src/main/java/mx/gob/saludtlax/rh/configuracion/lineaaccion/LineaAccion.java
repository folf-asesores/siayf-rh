/*
 * 
 * LineaAccion.java
 * Creado el Jul 27, 2016 1:29:18 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.lineaaccion;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface LineaAccion {
    
    /**
     * Permite obtener una línea de acción por medio de su ID.
     * 
     * @param idLineaAccion el ID de la línea de acción a buscar.
     * @return la línea de acción si existe sino retorna un null.
     */
    LineaAccionDTO obtenerPorId(int idLineaAccion);

    /**
     * Permite consultar las líneas de acción que coinciden con el criterio de 
     * busqueda.
     * 
     * @param consulta el criterio a buscar.
     * @return una lista con los nombres de las líneas de acción que coinciden.
     */
    List<String> consultarDescripcionLineaAccionPorCriterio(String consulta);

    /**
     * Permite obtener el ID de la línea de acción que coincide con la 
     * descripción.
     * 
     * @param descripcion una descripción a buscar entre la línea de acción.
     * @return el ID de la línea de acción que coincide con la descripción.
     */
    Integer consultarIdLineaAccionPorDescripcion(String descripcion);
}
