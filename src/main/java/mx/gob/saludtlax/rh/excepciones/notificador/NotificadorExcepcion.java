/*
 *  NotificadorExcepcion.java
 *  Creado el Jun 16, 2016 1:19:52 PM
 *
 */

package mx.gob.saludtlax.rh.excepciones.notificador;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public interface NotificadorExcepcion extends Serializable {

    /**
     * Envia por correo electrónico los detalles del error al o los
     * administradores del sistema.
     *
     * @param nombreUsuario
     *            el nombre del usuario al que se le mostro la excepcion.
     * @param tipo
     *            El tipo de error.
     * @param mensaje
     *            El mensaje de error.
     * @param pilaSeguimiento
     *            La pila de seguimiento (stackTrace).
     * @param fechaHoraException
     *            La fecha y hora en la que ocurrio la excepción.
     */
    void notificar(String nombreUsuario, String tipo, String mensaje,
            String pilaSeguimiento, String fechaHoraException);

}
