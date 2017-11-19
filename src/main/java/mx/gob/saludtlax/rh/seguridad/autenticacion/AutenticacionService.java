/*
 * AutenticacionService.java
 * Creado el Oct 5, 2016 8:34:10 PM
 *
 */

package mx.gob.saludtlax.rh.seguridad.autenticacion;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.SeguridadCodigoError;
import mx.gob.saludtlax.rh.excepciones.SeguridadException;
import mx.gob.saludtlax.rh.persistencia.TokenEntity;
import mx.gob.saludtlax.rh.persistencia.TokenRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.util.Crypto;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AutenticacionService {
    @Inject
    private TokenRepository tokenRepository;
    @Inject
    private UsuarioRepository usuarioRepository;
    private static final Logger LOGGER = Logger
            .getLogger(AutenticacionService.class.getName());

    protected String iniciarSesion(String nombreUsuario, String contrasenya,
            boolean mantenerSesion) {

        if (!usuarioRepository.existeUsuario(nombreUsuario)) {
            throw new SeguridadException("El usuario indicado no existe.",
                    SeguridadCodigoError.NOMBRE_DE_USUARIO_NO_EXISTE);
        }

        UsuarioEntity usuario = usuarioRepository
                .obtenerUsuarioPorNombreUsuario(nombreUsuario);

        String hashPassword = Crypto.hmac(contrasenya);

        if (!usuario.getPassword().equals(hashPassword)) {
            throw new SeguridadException("Las credenciales son incorrectas",
                    SeguridadCodigoError.CONTRASENYA_INVALIDA);
        }

        if (!usuario.isActivo()) {
            throw new SeguridadException("El usuario no se encuentra activo",
                    SeguridadCodigoError.USUARIO_INACTIVO);
        }

        String token = RandomStringUtils.randomAlphabetic(20);
        String hashToken = Crypto.hmac(token);

        int duracionEnSegundos = mantenerSesion
                ? ConfiguracionConst.DURACION_MAXIMA_SESION
                : ConfiguracionConst.DURACION_MINIMA_SESION;

        Calendar fechaCreacion = Calendar.getInstance();
        Calendar fechaExpira = (Calendar) fechaCreacion.clone();
        fechaExpira.add(Calendar.SECOND, duracionEnSegundos);

        TokenEntity tokenNuevo = new TokenEntity();
        tokenNuevo.setUsuario(usuario);
        tokenNuevo.setFechaCreacion(fechaCreacion.getTime());
        tokenNuevo.setHoraCreacion(fechaCreacion.getTime());
        tokenNuevo.setFechaExpira(fechaExpira.getTime());
        tokenNuevo.setHoraExpira(fechaExpira.getTime());
        tokenNuevo.setToken(hashToken);
        tokenNuevo.setActivo(true);

        tokenRepository.crear(tokenNuevo);

        return token;
    }

    protected void cerrarSesion(String token) {
        String hashToken = Crypto.hmac(token);

        LOGGER.debugv("Token: {0}, Hash: {1}", token, hashToken);
        tokenRepository.cerrarSesion(hashToken);
    }

    protected boolean expiroToken(String token) {
        String hashToken = Crypto.hmac(token);
        TokenEntity tokenEntity = tokenRepository
                .obtenerPorHashToken(hashToken);

        if (tokenEntity == null) {
            return true;
        }

        if (!tokenEntity.getActivo()) {
            return true;
        }

        Date fechaExpira = tokenEntity.getFechaExpira();
        Date fechaActual = FechaUtil.fechaActualSinTiempo().toDate();
        boolean expiro = fechaActual.compareTo(fechaExpira) > 0;

        if (expiro) {
            cerrarSesion(token);
        }

        return expiro;
    }
}
