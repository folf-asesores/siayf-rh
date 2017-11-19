/*
 * Autenticacion.java
 * Creado el Oct 5, 2016 8:16:23 PM
 *
 */

package mx.gob.saludtlax.rh.seguridad.autenticacion;

import mx.gob.saludtlax.rh.excepciones.SeguridadException;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public interface Autenticacion {

    /**
     * Permite iniciar la sesión de un usuario, si el nombre de usuario y la
     * contraseña son validos se devuelve un token de inicio de sesión en caso
     * contrario lanza una excepción indicaqndo que ocurrio un error de inicio
     * de sesión.
     *
     * @param nombreUsuario
     *            el nombre del usuario.
     * @param contrasenya
     *            la contraseña del usuario.
     * @param mantenerSesion
     *            si se mantentra la sesión el plazo máximo.
     * @return un token de inicio de sesión.
     * @throws SeguridadException
     *             en caso de que ocurra un erro de
     *             inico de sesión.
     */
    String iniciarSesion(String nombreUsuario, String contrasenya,
            boolean mantenerSesion);

    /**
     * Permite cerrar la sesión del usuario que conincide con el token de
     * inicio de sesión.
     *
     * @param token
     *            el token de inicio de sesión.
     */
    void cerrarSesion(String token);

    /**
     * Permite saber si un token aun está activo o no.
     *
     * @param token
     *            el token de inicio de sesión.
     * @return <code>true</code> si el token es valido en caso contrario <code>false</code>.
     */
    boolean caducoToken(String token);

    /**
     * Permite saber si una IP está bloqueada por excederse la cantida de
     * intentos permitidos.
     *
     * @param ip
     *            la IP a validar.
     * @return <code>true</code> si la IP está bloqueada en caso contrario <code>false</code>.
     */
    boolean ipEstaBloqueada(String ip);

    /**
     * Registra en el almacen una IP como bloqueada por exceder la cantidad de
     * intentos permitidos.
     *
     * @param ip
     *            la IP a registrar.
     */
    void registrarIpBloqueada(String ip);

}
