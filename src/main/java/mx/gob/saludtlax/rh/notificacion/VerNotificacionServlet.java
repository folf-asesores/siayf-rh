/*
 * VerNotificacionServlet.java
 * Creado el Aug 4, 2016 5:13:50 PM
 * 
 */
package mx.gob.saludtlax.rh.notificacion;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@WebServlet(
    name = "VerNotificacionServlet", 
    urlPatterns = {
        "/contenido/notificaciones/ver-notificacion", 
        "/contenido/notificaciones/notificacion-vista"
    }, 
    initParams = {
        @WebInitParam(name = "token", value = "")
    }
)
public class VerNotificacionServlet extends HttpServlet {

    private static final long serialVersionUID = 8714343555749201297L;
    private static final Logger LOGGER = Logger.getLogger(VerNotificacionServlet.class.getName());
    
    @Inject private Notificacion notificacionEJB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String url = request.getParameter("url");
        
        if(request.getServletPath().endsWith("ver-notificacion")) {
            verNotificacion(token, request, response);
        } else if(request.getServletPath().endsWith("notificacion-vista")) {
            marcarNotificacionComoVista(token, url, request, response);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    /**
     * Permite validader si el ID del usuario que está en el mensaje y el que 
     * está en sesión son el mismo.
     * 
     * @param idUsuario el ID del usuario que indica la notificación que
     * debe recibir el mensaje.
     * @param sesion la sesión de la cual que se obtentra el ususario en sesión.
     * @return <code>true</code> si el ID del usuario del mensaje y el de sesión
     * son el mismo en caso contrario retornana <code>false</code>.
     */
    private boolean esParaUsuarioEnSesion(Integer idUsuario, 
            HttpSession sesion) {
        UsuarioDTO usuarioEnSesion = (UsuarioDTO) sesion
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

        return usuarioEnSesion != null 
                && usuarioEnSesion.getIdUsuario() != null 
                && idUsuario != null 
                && usuarioEnSesion.getIdUsuario().compareTo(idUsuario) == 0;
    }
    
    /**
     * Permite redireccionar al usuario a la información con la que está 
     * relacionada la notificación que recibió.
     * 
     * @param token el token que identifica la notificación.
     * @param request el HTTP request para poder obtener información sobre 
     * la solicitud.
     * @param response el HTTP reponse para redireccionar a la página adecuada.
     * @throws IOException si ocurre algún error al escribir o leer la 
     * información en la respuesta.
     */
    private void verNotificacion(String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(token != null && !ValidacionUtil.esCadenaVacia(token)) {
            
            try {
                
                NotificacionDTO notificacion = notificacionEJB.obtenerPorToken(token);
                HttpSession session = request.getSession(true);
                
                Integer idUsuario = 0;
                
                for (Map.Entry<Integer, String> idDestinatarioMap : notificacion.getIdsDestinatarios().entrySet()) {
                    Integer key = idDestinatarioMap.getKey();
                    String value = idDestinatarioMap.getValue();
                    
                    if(token.equals(value)){
                        idUsuario = key;
                        break;
                    }
                }
                
                if(!esParaUsuarioEnSesion(idUsuario, session)){
                    response.sendRedirect(request.getContextPath() + "/contenido/notificaciones/sin-autorizacion.xhtml");
                }

                for (Map.Entry<String, String> parametro : notificacion.getParametros().entrySet()) {
                    LOGGER.debugv("{0},\t\t{1}\n", parametro.getKey(), parametro.getValue());
                    session.setAttribute(parametro.getKey(), parametro.getValue());
                }

                notificacionEJB.marcarComoVista(token);

                if(!Modulo.SIN_MODULO.equals(notificacion.getModulo())) {
                    response.sendRedirect(request.getContextPath() + notificacion.getModulo().getUrl());
                }
            } catch (ReglaNegocioException rne) {
                if(ReglaNegocioCodigoError.NOTIFICACION_NO_ENCONTRADA.equals(rne.getCodigoError())) {
                    response.sendRedirect(request.getContextPath() + "/contenido/notificaciones/token-invalido.xhtml");
                }
            }
            
        } else {
            response.sendRedirect(request.getContextPath() + "/contenido/notificaciones/token-invalido.xhtml");
        }
    }

    /**
     * Permite maracar una notificación como vista.
     * 
     * @param token el token que identifica la notificación.
     * @param url la URL a la que se debe redirecionar al usuario.
     * @param request el HTTP request para poder obtener información sobre 
     * la solicitud.
     * @param response el HTTP reponse para redireccionar a la página adecuada.
     * @throws IOException si ocurre algún error al escribir o leer la 
     * información en la respuesta.
     */
    private void marcarNotificacionComoVista(String token, String url, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(token != null && !ValidacionUtil.esCadenaVacia(token)) {
            notificacionEJB.marcarComoVista(token);
        }
        
        if(url != null && url.startsWith("/contenido/")) {
            response.sendRedirect(request.getContextPath() + url);
        } else {
            response.sendRedirect(request.getContextPath() + ConfiguracionConst.HOME_PAGE_URL);
        }
    }
}
