/*
 * Notificacion.java
 * Creado el Aug 3, 2016 5:16:14 PM
 *
 */

package mx.gob.saludtlax.rh.notificacion;

import java.util.List;

import javax.ejb.Local;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

/**
 * Esta interfaz define los elemetos esenciales para el manejo de las
 * notificaciones.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Notificacion {

    /**
     * Permite enviar una notificación tanto dentro del sistema como vía correo
     * electrónico, el correo electrónico debe estar en la configuracion del
     * usuario.
     *
     * @param notificacion
     *            un <code>DTO</code> con la información de la
     *            notificación a enviar.
     */
    void enviar(NotificacionDTO notificacion);

    /**
     * Permite obtener una notificación asociada a un token.
     *
     * @param token
     *            el token de la notificacion a buscar.
     * @return un <code>DTO</code> con los detalles de la notificación.
     * @throws ReglaNegocioException
     *             en caso de que no se encuentre ninguna
     *             notificación asociada al token, la excepción envia el código de error
     *             {@link ReglaNegocioCodigoError#NOTIFICACION_NO_ENCONTRADA}.
     */
    NotificacionDTO obtenerPorToken(String token) throws ReglaNegocioException;

    /**
     * Permite indicar que una notificación ya ha sido vista y que no se
     * mostrará en el menú de notificaciones.
     *
     * @param token
     *            el token de la notificacion a buscar.
     */
    void marcarComoVista(String token);

    /**
     * Permite obtener la notificaciones de un usuario por medio de su ID.
     *
     * @param idUsuario
     *            el <code>ID</code> del usuario del cual se requiere sus
     *            notificaciones.
     * @return una lista con las notificaciones del usuario, en caso de no tener
     *         notificaciones se retorna una lista vacia.
     */
    List<NotificacionDTO> consultarNotificacionesPorIdUsuario(
            Integer idUsuario);

    /**
     * Permite obtener la notificaciones que el usuario no ha visto por medio
     * de su ID.
     *
     * @param idUsuario
     *            el <code>ID</code> del usuario del cual se requiere sus
     *            notificaciones.
     * @return una lista con las notificaciones del usuario, en caso de no tener
     *         notificaciones se retorna una lista vacia.
     */
    List<NotificacionDTO> consultarNotificacionesPorIdUsuarioNoVistas(
            Integer idUsuario);

}
