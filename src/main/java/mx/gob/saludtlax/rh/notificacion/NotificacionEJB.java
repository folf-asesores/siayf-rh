/*
 * NotificacionEJB.java
 * Creado el Aug 3, 2016 5:16:14 PM
 * 
 */
package mx.gob.saludtlax.rh.notificacion;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * Este <code>EJB</code> es una implentación de las notificaciones, en esta se
 * realiza la mayoría de las validaciónes antes de delegar el proceso completo 
 * a clases ayudantes (helpers).
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class NotificacionEJB implements Notificacion {

    @Inject private NotificacionService notificacionService;
    
    @Override
    @Asynchronous
    public void enviar(NotificacionDTO notificacion) {
        if (notificacion == null) {
            throw new ValidationException("No se puede enviar una notifición nula.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        notificacionService.crear(notificacion);
    }
    
    @Override
    public NotificacionDTO obtenerPorToken(String token) throws ReglaNegocioException {
        if (ValidacionUtil.esCadenaVacia(token)) {
            throw new ValidationException("El token no debe estar vacio.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        try{
            return notificacionService.obtenerPorToken(token);
        } catch (SistemaException se) {
            if (SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS.equals(se.getCodigoError())) {
                throw new ReglaNegocioException("No se ha encontrado ninguna notificación", se, ReglaNegocioCodigoError.NOTIFICACION_NO_ENCONTRADA);
            } else {
                throw se;
            }
        }
    }

    @Override
    public void marcarComoVista(String token) {
        if (ValidacionUtil.esCadenaVacia(token)) {
            throw new ValidationException("El token no debe estar vacio.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        notificacionService.marcarComoVista(token);
    }
    
    @Override
    public List<NotificacionDTO> consultarNotificacionesPorIdUsuario(Integer idUsuario) {
        if (ValidacionUtil.esMenorQueUno(idUsuario)) {
            throw new ValidationException("El ID de un usuario no puede ser cero o un número negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return notificacionService.consultarNotificacionesPorIdUsuario(idUsuario);
    }
    
    @Override
    public List<NotificacionDTO> consultarNotificacionesPorIdUsuarioNoVistas(Integer idUsuario) {
        if (ValidacionUtil.esMenorQueUno(idUsuario)) {
            throw new ValidationException("El ID de un usuario no puede ser cero o un número negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return notificacionService.consultarNotificacionesPorIdUsuarioVisto(idUsuario, false);
    }
}
