
package mx.gob.saludtlax.rh.seguridad.autenticacion;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.SeguridadCodigoError;
import mx.gob.saludtlax.rh.excepciones.SeguridadException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@Stateless
public class AutenticacionEJB implements Autenticacion {

    @Inject
    private AutenticacionService autenticacionService;
    @Inject
    private IpBloqueadaService ipService;

    @Override
    public String iniciarSesion(String nombreUsuario, String contrasenya, boolean mantenerSesion) {

        if (ValidacionUtil.esCadenaVacia(nombreUsuario)) {
            throw new SeguridadException("El nombre de usuario es requerido", SeguridadCodigoError.NOMBRE_DE_USUARIO_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(contrasenya)) {
            throw new SeguridadException("Se requiere que ingrese una contraseña.", SeguridadCodigoError.CONTRASENYA_REQUERIDA);
        }

        return autenticacionService.iniciarSesion(nombreUsuario, contrasenya, mantenerSesion);
    }

    @Override
    public void cerrarSesion(String token) {
        autenticacionService.cerrarSesion(token);
    }

    @Override
    public boolean caducoToken(String token) {
        return autenticacionService.expiroToken(token);
    }

    @Override
    public boolean ipEstaBloqueada(String ip) {
        if (ValidacionUtil.esCadenaVacia(ip)) {
            throw new ValidacionException("La IP está vacio o es nula.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return ipService.ipEstaBloqueada(ip);
    }

    @Override
    public void registrarIpBloqueada(String ip) {
        if (ip == null) {
            throw new ValidacionException("La IP está vacio o es nula.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        ipService.registrarIpBloqueada(ip);
    }
}
