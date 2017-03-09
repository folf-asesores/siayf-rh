/*
 * AdjuntoEmpleado.java
 * Creado el May 12, 2016 11:05:44 AM
 * 
 */
package mx.gob.saludtlax.rh.expediente.empleado;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import java.util.List;
import mx.gob.saludtlax.rh.expediente.Adjunto;

/**
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 *
 */
public interface AdjuntoEmpleado extends Adjunto {

    /**
     * Este método devuelve todos los adjuntos del empleado.
     * 
     * @param idEmpleado el ID del empleado.
     * @return todos los adjuntos del empleado.
     */
    List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdEmpleado(
                    int idEmpleado);

    /**
     * Retorna la lista de documentos que tiene adjunto el empleado por tipo de
     * documento.
     * 
     * @param entidadContexto al que pertenece el adjunto (ejemplo historiales 
     *                        academicos)
     * @param idEntidadContexto identificador del tipo de documento.
     * @return una lista con el nombre de documentos que tiene adjunto el 
     *         empleado.
     * @author Leila Schiaffini Ehuan
     */
    List<String> consultarDocumentosAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto entidadContexto, 
            Integer idEntidadContexto);
    
    /**
     * Permite importar el expediente de un aspirante, para que sea empleado.
     * 
     * @param idAspirantente el ID del aspirante.
     * @param idEmpleado el ID del empleado.
     */
    void importarExpedienteAspirante(Integer idAspirantente, Integer idEmpleado);
}