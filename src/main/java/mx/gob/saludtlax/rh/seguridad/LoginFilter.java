package mx.gob.saludtlax.rh.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.gob.saludtlax.rh.excepciones.SeguridadException;
import mx.gob.saludtlax.rh.seguridad.autenticacion.Autenticacion;
import mx.gob.saludtlax.rh.seguridad.usuario.Usuario;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

import org.jboss.logging.Logger;

/**
 *
 * @author Luis A. Pérez
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class LoginFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class.getName());

    // Arreglo con los recursos que no quiera que se filtren
    private static final String [] LISTA_BLANCA_DE_RECURSOS = {
        ConfiguracionConst.LOGIN_PAGE_URL,
        "/resources",
        "/plantillas"
    };

    @Inject private Autenticacion autenticacionEJB;
    @Inject private Usuario usuarioEJB;

    private String contextPath;
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private HttpSession httpSession;
    private UsuarioDTO usuarioEnSesion;
    private int intentos = 0;
    
    private List<String> recursosUsuario = new ArrayList<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        contextPath = config.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) servletRequest;
        httpResponse = (HttpServletResponse) servletResponse;
        String recursoSolicitado = getRequestResource(httpRequest.getRequestURI());
        httpSession = httpRequest.getSession();
        usuarioEnSesion = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        
        // Si el usuario no está en sesión pero hay una cookie de duración 
        // larga.
        // Aplica cuando el usuario marco la casilla de recordar contraseña, 
        // entonces aun cuando el servidor sea reiniciado o el explorador sea
        // cerrado pero no se hallan eliminado las cookies la sesión se iniciara
        // automáticamente para el usuario.
        if (usuarioEnSesion == null && ConfiguracionConst.existCookieNamed(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN)) {
            comeAliveSession(ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN);

            // Si el usuario no está en sesión pero hay una cookie de duración 
            // corta.
            // Esto es valido cuando el usuario tenía iniciada su sesión y el 
            // servidor se reinicio, pero no cerro el explorador de Internet.            
        } else if (usuarioEnSesion == null && ConfiguracionConst.existCookieNamed(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN)) {
            comeAliveSession(ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN);
        } else {
            
        }

        if (recursoSolicitado != null && recursoSolicitado.endsWith(".xhtml")) {
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpResponse.setHeader("Pragma", "no-cache");
            httpResponse.setDateHeader("Expires", 0);
        }

        // Si el usuario no está en sesión pero el recurso que solicita está 
        // dentro de la lista de recursos accesibles para todos entonces se le
        // permite continuar.
        if (recursoSolicitado != null
                && isWhiteLista(recursoSolicitado)
                && usuarioEnSesion == null) {
        
            chain.doFilter(servletRequest, servletResponse);

            // Si el recurso solicitado es la página de inicio y el usuario no está
            // en sesión.
        } else if (recursoSolicitado != null
                && recursoSolicitado.equals(ConfiguracionConst.HOME_PAGE_URL)
                && usuarioEnSesion == null) {

            if (httpRequest.getParameter("login") != null && httpRequest.getParameter("login").equals("login")) {
                String username = httpRequest.getParameter("login:nombreUsuario");
                String password = httpRequest.getParameter("login:contrasenya");
                boolean keepLogin = httpRequest.getParameter("login:mantenerSesion_input") == null
                        ? false : httpRequest.getParameter("login:mantenerSesion_input").equals("on");

                if(!autenticacionEJB.ipEstaBloqueada(ConfiguracionConst.obtenerIp(httpRequest))) {
                    try {
                        String token = login(username, password, keepLogin);
                       
                        if (token != null) {
                            intentos = 0;
                            UsuarioDTO usuarioAccesando = usuarioEJB.obtenerUsuarioPorToken(token);
                            
                            recursosUsuario.addAll(usuarioEJB.recursosUsuario(usuarioAccesando.getIdUsuario()));
                            chain.doFilter(servletRequest, servletResponse);
                        }

                    } catch (SeguridadException seguridadException) {
                        switch (seguridadException.getCodigoError()) {
                            case NOMBRE_DE_USUARIO_NO_EXISTE:
                            case CONTRASENYA_INVALIDA:
                                intentos++;
                                break;
                            default:
                                throw seguridadException;
                        }
                        
                        if (intentos > ConfiguracionConst.LIMITE_DE_INTENTOS_DE_INICIO_SESION) {
                           String ip = ConfiguracionConst.obtenerIp(httpRequest);
                           autenticacionEJB.registrarIpBloqueada(ip);
                           LOGGER.infov("El usuario: {0} con IP {1} ha superado el número máximo de intentos de inicio de sesión.", username, ip);
                           //servletResponse.getWriter().print("Muchos intentos fallidos");
                        }

                        httpResponse.sendRedirect(contextPath + ConfiguracionConst.LOGIN_PAGE_URL);
                    }
                } else {
                    httpResponse.sendRedirect(contextPath + ConfiguracionConst.LOGIN_PAGE_URL);
                }
            } else {
                httpResponse.sendRedirect(contextPath + ConfiguracionConst.LOGIN_PAGE_URL);
            }

            // Si el usuario está en sesión y solicita la página del iniciarSesion es 
            // redirigido a la página de inicio.
        } else if (recursoSolicitado != null
                && recursoSolicitado.equals(ConfiguracionConst.LOGIN_PAGE_URL)
                && usuarioEnSesion != null) {
            httpResponse.sendRedirect(contextPath + ConfiguracionConst.HOME_PAGE_URL);

        } else if (recursoSolicitado != null
                && recursoSolicitado.equals(ConfiguracionConst.LOGOUT_PAGE_URL)
                && usuarioEnSesion != null) {
            String token;

            if (ConfiguracionConst.existCookieNamed(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN)) {
                token = ConfiguracionConst.getCookieValue(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN);
                logout(token);
                chain.doFilter(servletRequest, servletResponse);
            } else if (ConfiguracionConst.existCookieNamed(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN)) {
                token = ConfiguracionConst.getCookieValue(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN);
                logout(token);
                chain.doFilter(servletRequest, servletResponse);
            } else {
                removeSession();
                chain.doFilter(servletRequest, servletResponse);
                //El sistema no es multisesion
            }

            // Si el usuario está en sesión y solicita una página dentro de la
            // carpeta contenido se le permite continuar.
        } else if (recursoSolicitado != null
                && recursoSolicitado.startsWith("/contenido/")
                && usuarioEnSesion != null) {
        	recursosUsuario.clear();
        	 recursosUsuario.addAll(usuarioEJB.recursosUsuario(usuarioEnSesion.getIdUsuario()));
        	
        	if(usuarioEnSesion.getCargo().contentEquals("Super_Administrador")){
        		chain.doFilter(servletRequest, servletResponse);        	
        	}else
        	
        	if(recursoSolicitado.contentEquals("/contenido/inicio.xhtml") || recursoSolicitado.contains("/contenido/seguridad/logout.xhtml")
        			|| recursoSolicitado.contains("/contenido/seguridad/seccionExpiro.xhtml")
        			|| recursoSolicitado.contains("/contenido/seguridad/error.xhtml") || recursoSolicitado.contains("/contenido/seguridad/error404.xhtml")
        			|| recursoSolicitado.contains("/contenido/seguridad/error500.xhtml")
        				|| recursoSolicitado.contains("/contenido/seguridad/.xhtml")|| recursoSolicitado.contains("/contenido/seguridad/cambiarPassword.xhtml")){
        		chain.doFilter(servletRequest, servletResponse);        		
        	} 
        	else
        	if(recursosUsuario.contains(recursoSolicitado)){
        		//System.out.println("Este usuario si puede accesar a :" + recursoSolicitado);
        		  chain.doFilter(servletRequest, servletResponse);
        	}else{
        	
        		//System.out.println("Este usuario no deberia poder ingresar a este recurso: " + recursoSolicitado + " recursoUsuario "+recursosUsuario);
        		 httpResponse.sendRedirect(contextPath + ConfiguracionConst.ERROR_PAGE_URL);
        		
        	}
          // chain.doFilter(servletRequest, servletResponse);

            // Si el usuario no está en sesión y solicita una página fuera de la
            // carpeta contenido se le redirige al loggin para que inicie sesión.
        } else {
            httpResponse.sendRedirect(contextPath + ConfiguracionConst.LOGIN_PAGE_URL);
        }
    }

    
    
    
    @Override
    public void destroy() {
        LOGGER.debug("Finaliza el filtro");
    }

    /**
     * Permite iniciar la sesión de un usuario y devuelve un token de inicio de
     * sesión.
     *
     * @param username el nombre de usuario.
     * @param password la contraseña.
     * @param keepLogin si se mantendra la sesión iniciada.
     * @return un token de la sesión.
     */
    private String login(String username, String password, boolean keepLogin) {
        String token = autenticacionEJB.iniciarSesion(username, password, keepLogin);
        usuarioEnSesion = usuarioEJB.obtenerUsuarioPorToken(token);
        createSession(usuarioEnSesion, token, keepLogin);

        return token;
    }

    /**
     * Permite cerrar la sesión de un usuario por medio del token de donde
     * inicio sesión.
     *
     * @param token el token de la sesión a cerrar.
     */
    private void logout(String token) {
        autenticacionEJB.cerrarSesion(token);
        removeSession();
    }

    /**
     * Permite guardar una cookie en el cliente con un token que identifique la
     * sesión del usuario además de que coloca la información del usuario en
     * sesión.
     *
     * @param userSession la información del usario que se mantendra en sesión.
     * @param token el token que se genera al iniciar la sesión.
     * @param persistLongTime si se mantendra la sesión iniciada.
     */
    private void createSession(UsuarioDTO userSession, String token, boolean persistLongTime) {
        Cookie cookie;
        int expiry;
        int interval;

        if (persistLongTime) {
            cookie = new Cookie(ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN, token);
            expiry = interval = ConfiguracionConst.DURACION_MAXIMA_SESION;
        } else {
            cookie = new Cookie(ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN, token);
            expiry = -1; // Durante la sesión
            interval = ConfiguracionConst.DURACION_MINIMA_SESION;
        }

        cookie.setMaxAge(expiry);
        cookie.setPath(contextPath);
        httpResponse.addCookie(cookie);

        httpSession.setAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER, userSession);
        httpSession.setMaxInactiveInterval(interval);
    }

    /**
     * Recupera el token que está en el cliente, sí es un token valido lo coloca
     * en sesión.
     */
    private void comeAliveSession(String cookieName) {
        String token = ConfiguracionConst.getCookieValue(httpRequest, cookieName);

        if (!autenticacionEJB.caducoToken(token) && ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN.equals(cookieName)) {
            usuarioEnSesion = usuarioEJB.obtenerUsuarioPorToken(token);
            httpSession.setAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER, usuarioEnSesion);
            httpSession.setMaxInactiveInterval(ConfiguracionConst.DURACION_MAXIMA_SESION);
        } else if (!autenticacionEJB.caducoToken(token) && ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN.equals(cookieName)) {
            usuarioEnSesion = usuarioEJB.obtenerUsuarioPorToken(token);
            httpSession.setAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER, usuarioEnSesion);
            httpSession.setMaxInactiveInterval(ConfiguracionConst.DURACION_MINIMA_SESION);
        } else {
            LOGGER.debug("Sesion ya caduco");
        }
    }

    /**
     * Permite eliminar al usuario de la sesión y después elimina la sesión.
     */
    private void removeSession() {
        Cookie opentoken;

        if (ConfiguracionConst.existCookieNamed(httpRequest, ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN)) {
            opentoken = new Cookie(ConfiguracionConst.COOKIE_NAME_FOR_LONG_LOGIN, null);
        } else {
            opentoken = new Cookie(ConfiguracionConst.COOKIE_NAME_FOR_SHORT_LOGIN, null);
        }

        opentoken.setMaxAge(0);
        opentoken.setPath(contextPath);
        httpResponse.addCookie(opentoken);

        // Verificar como evitar que se cree el facesContext.
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        if (httpSession != null) {
            httpSession.invalidate();
        }
    }

    /**
     * Valida que la rua solicitada este dentro del conexto y luego elimina el
     * contexto de la ruta.
     *
     * <p>
     * En caso de que el recurso solicitado no esté dentro del contexto retorna
     * un null.
     *
     * @param recursoURI el ruta del recurso solicitado que debe incluir el
     * contexto.
     * @return la ruta del recurso sin el contexto.
     */
    private String getRequestResource(String recursoURI) {
        if (recursoURI != null) {
            if (recursoURI.startsWith(contextPath)) {
                return recursoURI.substring(contextPath.length());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Valida si un recurso solicitado está dentro de la lista blanca.
     *
     * @param recursoSolicitado la ruta del recurso solicitado.
     * @return true si el recurso está en la lista blanca.
     */
    private boolean isWhiteLista(String recursoSolicitado) {
        for (String recurso : LISTA_BLANCA_DE_RECURSOS) {
            if (recursoSolicitado.startsWith(recurso)) {
                return true;
            }
        }

        return false;
    }
}
