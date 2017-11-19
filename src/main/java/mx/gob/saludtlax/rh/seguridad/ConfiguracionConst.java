
package mx.gob.saludtlax.rh.seguridad;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ConfiguracionConst {

    /** Define el nombre de la atributo que representa al usuario y que está en la sesión. */
    public static final String SESSION_ATRIBUTE_LOGGED_USER = "SIAYF-RH__USR";
    /** Define el nombre de la cookie que tiene la sesión temporal del usuario. */
    public static final String COOKIE_NAME_FOR_SHORT_LOGIN = "SIAYF-RH__TKN";
    /** Define el nombre de la cookie que tiene la sesión persistente del usuario. */
    public static final String COOKIE_NAME_FOR_LONG_LOGIN = "SIAYF-RH__LGN";
    /** Define la URL de la página principal del proyecto sin incluir el context path. */
    public static final String HOME_PAGE_URL = "/contenido/inicio.xhtml";
    /** Define la URL de la página de inicio de seisión del proyecto sin incluir el context path. */
    public static final String LOGIN_PAGE_URL = "/contenido/seguridad/login.xhtml";
    /** Define la URL de la pagina de error para control de accesos. */
    public static final String ERROR_PAGE_URL = "/contenido/seguridad/error.xhtml";

    /** Define la URL de la página de cierre de sesión del proyecto sin incluir el context path. */
    public static final String LOGOUT_PAGE_URL = "/contenido/seguridad/logout.xhtml";
    /** Define la duracion mínima del token en segundos. */
    public static final int DURACION_MINIMA_SESION = 60 * 60; // Una hora.
    /** Define la duracion máxima del token en segundos. */
    public static final int DURACION_MAXIMA_SESION = 7 * 24 * 60 * 60; // 7 días.
    /** Especifica la contidad de intentos máximos que se permitira ingresar datos incorrectos para iniciar sesión. */
    public static int LIMITE_DE_INTENTOS_DE_INICIO_SESION = 3;

    /**
     * Obtenemos el token si se encuentra en la cookie persistente del browser
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    /**
     * Obtenemos el token si se encuentra en la cookie persistente del browser
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }

    /**
     * Permite saber si una cookie especifica existe en el cliente.
     *
     * @param request
     *            el request para poder obtener las cookies del cliente.
     * @param cookieName
     *            el nombre de la cookie a buscar.
     * @return verdad si se encuentra alguna cookie con el nombre deseado.
     */
    public static boolean existCookieNamed(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookieName.equals(cookie.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Permite obtener la IP del usuario.
     *
     * @param request
     *            el request para obtener la IP.
     * @return una cadena con la IP del cliente.
     */
    public static String obtenerIp(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}
