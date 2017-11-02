/*
 *  NotificadorExcepcionService.java
 *  Creado el Jun 16, 2016 5:28:39 PM
 * 
 */
package mx.gob.saludtlax.rh.excepciones.notificador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.persistencia.BitacoraExcepcionEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraExcepcionRepository;
import mx.gob.saludtlax.rh.persistencia.CorreoNotificacionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NotificadorExcepcionService implements Serializable {

    private static final long serialVersionUID = -7234499601701710636L;
    
    @Inject private BitacoraExcepcionRepository bitacoraExcepcionRepository;
    @Inject private CorreoNotificacionRepository notificacionRepository;
    @Inject private UsuarioRepository usuarioRepository;
 
    /**
     * Este método devuelve una lista con todos los correos electrónicos a 
     * quienes se les ha de notificar vía correo electrónico al ocurrir una 
     * excepción.
     * 
     * @return una lista con los correos electrónicos.
     */
    protected List<String> consultarCorreos(){
        return notificacionRepository.consutarCorreos();
    }

    /**
     * Este método permite guardar en el almaacen la excepción que haya 
     * ocurrido en tiempo de ejecución.
     * 
     * @param nombreUsuario el nombre del usuario.
     * @param tipo el tipo de excepción (el nombre de la clase).
     * @param mensaje el mensaje de error.
     * @param pilaSeguimiento la pila de seguimiento de la excepción (stack 
     * trace).
     * @param fechaHora la fecha y hora en la que ocurrio la excepción.
     */
    protected void persistirExcepcion(String nombreUsuario, String tipo, String mensaje, String pilaSeguimiento, Date fechaHora) {
        UsuarioEntity usuarioEntity = usuarioRepository.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        BitacoraExcepcionEntity bitacoraExcepcionEntity = new BitacoraExcepcionEntity(null, usuarioEntity, tipo, mensaje, pilaSeguimiento, fechaHora, fechaHora);
        bitacoraExcepcionRepository.crear(bitacoraExcepcionEntity);
    }

}
