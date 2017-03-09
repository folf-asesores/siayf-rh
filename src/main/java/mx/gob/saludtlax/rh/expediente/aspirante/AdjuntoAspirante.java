/*
 * AdjuntoAspirante.java
 * Creado el May 12, 2016 11:05:44 AM
 * 
 */
package mx.gob.saludtlax.rh.expediente.aspirante;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import java.util.List;
import mx.gob.saludtlax.rh.expediente.Adjunto;

/**
 * Esta interfaz describe la administración de los adjuntos del aspirante.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public interface AdjuntoAspirante extends Adjunto {

    /**
     * Este método devuelve todos los adjuntos que tiene un aspirante.
     * 
     * @param idAspirante el ID del aspirante.
     * @return todos los adjuntos que tiene el aspirante.
     */
    List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdAspirante(
                    int idAspirante);
}