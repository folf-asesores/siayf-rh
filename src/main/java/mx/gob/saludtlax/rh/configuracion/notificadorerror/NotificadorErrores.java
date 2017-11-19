/*
 *  NotificadorErrores.java
 *  Creado el Jun 16, 2016 4:38:53 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface NotificadorErrores extends Serializable {

    /**
     * Agrega un un correo nueva a la lista de notificación.
     *
     * @param correo
     *            un DTO que representa correo electrónico a notificar.
     */
    void crear(CorreoNotificacionDTO correo);

    /**
     * Actualiza la dirección de correo electrónico de alguien que ya está en
     * la lista de correos electrónicos.
     *
     * @param correo
     *            un DTO que representa correo electrónico a notificar
     *            el cual será modificado.
     */
    void actualizar(CorreoNotificacionDTO correo);

    /**
     * Trae una lista con los correos de quienes serán notificados al ocurrir
     * una excepción.
     *
     * @return una lista con los correos de quienes serán notificados.
     */
    List<CorreoNotificacionDTO> consutarCorreosNotificacion();

    /**
     * Elimina un correo de la lista de notificación.
     *
     * @param idCorreoNotificacion
     *            el <code>ID</code> del correo correo de
     *            quien ya no será notificado.
     */
    void eliminar(int idCorreoNotificacion);
}
