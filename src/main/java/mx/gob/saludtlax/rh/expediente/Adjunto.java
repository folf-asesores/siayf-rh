/*
 * 
 * Adjunto.java
 * Creado el Jun 11, 2016 4:48:01 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public interface Adjunto extends Serializable {

    /**
     * Este método permite obtener la información del adjunto sin el archivo
     * adjunto y sin la vista previa.
     *
     * @param idAdjunto el <code>ID</code> del adjunto
     * @return un <code>DTO</code> con toda la información del archivo adjunto.
     */
    InformacionAdjuntoDTO obtenerInformacionAdjuntoPorIdAdjunto(int idAdjunto);

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @return una lista con los adjuntos que conincidan con el filtro.
     */
    List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
            EntidadContexto entidadContexto, int idEntidadContexto);

    /**
     * Este método permite obtener la información de varios adjuntos.
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @param idDocumentoAdjuntable
     * @return the
     * java.util.List<mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO>
     */
    List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto, int idDocumentoAdjuntable);

    /**
     * Este método permite obtener un archivo adjunto por medio de su
     * <code>ID</code>.
     *
     * @param idAdjunto
     * @return
     */
    byte[] obtenerAdjuntoPorIdAdjunto(int idAdjunto);

    /**
     * Este método obtiene la vista previa del archivo adjunto.
     *
     * @param idAdjunto el ID del archivo adjunto.
     * @return un arreglo de bytes que representa la vista previa del archivo
     * adjunto.
     */
    byte[] obtenerVistaPreviaPorIdAdjunto(int idAdjunto);

    /**
     * Este método permite guardar un archivo adjunto junto con toda su
     * información.
     *
     * @param informacionDelAdjunto la información sobre el adjunto.
     * @param adjunto un arreglo de bytes que representa el adjunto.
     * @return el <code>ID</code> del adjunto.
     */
    int crear(InformacionAdjuntoDTO informacionDelAdjunto,
            byte[] adjunto);

    /**
     * <p>
     * Estem método permite modificar uno o varios campos de la información del
     * adjunto o el adjunto en si mismo.</p>
     *
     * <strong>Nota</strong>
     * <ul>
     * <li>El campo idAdjunto siempre es requerido.</li>
     * <li>Cuando se modifica el archivo adjunto se debe incluir el nombre del
     * archivo y la extensión.</li>
     * </ul>
     *
     * @param parametros un mapa con el nombre del campo (clave) y el nuevo
     * valor a ingresar.
     */
    void actualizar(Map<String, Object> parametros);

    /**
     * Este método permite la eliminación de un archivo adjunto en la base de
     * datos.
     *
     * @param idAdjunto
     */
    void elimnar(int idAdjunto);
}
