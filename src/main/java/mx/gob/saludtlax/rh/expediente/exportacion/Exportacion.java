/*
 * Exportacion.java
 * Creado el Sep 9, 2016 3:48:09 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public interface Exportacion {

    ExportacionDTO obtenerPorId(Integer idAdjunto);
    
    List<Integer> consultarIdsPaginado(int cantidad, int inicio);
    
    List<ExportacionDTO> consultarPaginado(int cantidad, int inicio);
    
}
