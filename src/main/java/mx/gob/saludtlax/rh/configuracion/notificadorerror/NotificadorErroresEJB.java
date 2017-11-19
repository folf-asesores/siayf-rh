/*
 *  NotificadorErroresEJB.java
 *  Creado el Jun 16, 2016 4:38:52 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.EmailValidator;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class NotificadorErroresEJB implements NotificadorErrores {

    private static final long serialVersionUID = -1831806027964566754L;

    @Inject
    private NotificadorErroresService notificadorErroresService;

    @Override
    public void crear(CorreoNotificacionDTO correo) {
        if (correo == null) {
            throw new ValidacionException("El DTO no puede der nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(correo.getAlias())) {
            throw new ValidacionException("Debe indicar un alias.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(correo.getCorreoElectronico())) {
            throw new ValidacionException("Debe indicar un correo electrónico.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!EmailValidator.validate(correo.getCorreoElectronico())) {
            throw new ValidacionException("El formato del correo electrónico es invalido.", ValidacionCodigoError.FORMATO_INVALIDO);
        }

        correo.setIdCorreoNotificacion(null);
        notificadorErroresService.crear(correo);
    }

    @Override
    public void actualizar(CorreoNotificacionDTO correo) {
        if (correo == null) {
            throw new ValidacionException("El DTO no puede der nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(correo.getAlias())) {
            throw new ValidacionException("Debe indicar un alias.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(correo.getCorreoElectronico())) {
            throw new ValidacionException("Debe indicar un correo electrónico.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!EmailValidator.validate(correo.getCorreoElectronico())) {
            throw new ValidacionException("El formato del correo electrónico es invalido.", ValidacionCodigoError.FORMATO_INVALIDO);
        }

        notificadorErroresService.actualizar(correo);
    }

    @Override
    public List<CorreoNotificacionDTO> consutarCorreosNotificacion() {
        return notificadorErroresService.consutarCorreosNotificacion();
    }

    @Override
    public void eliminar(int idCorreoNotificacion) {
        if (ValidacionUtil.esMenorQueUno(idCorreoNotificacion)) {
            throw new ValidacionException("El ID no puede ser nulo, cero o menor que uno.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        notificadorErroresService.eliminar(idCorreoNotificacion);
    }

}
