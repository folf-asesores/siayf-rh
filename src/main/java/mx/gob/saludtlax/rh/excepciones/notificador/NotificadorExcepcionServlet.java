/*
 * NotificadorExcepcionServlet.java
 * Creado el Jun 16, 2016 1:08:03 PM
 * 
 * Este servlet se encarga de enviar los detalles de una excepción al back-end 
 * cuando ocurre para que sea procesada y enviada a los administradores del 
 * sistema si es necesario.
 */
package mx.gob.saludtlax.rh.excepciones.notificador;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import org.jboss.logging.Logger;

/**
 * Este servlet envia la información de una excepción al back-end para ser
 * enviada al o los administradores del sistema.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@WebServlet(name = "notificador", urlPatterns = {"/notificador-de-excepciones", "/notificador-de-error"}, initParams = {
    @WebInitParam(name = "he", value = ""),
    @WebInitParam(name = "tp", value = ""),
    @WebInitParam(name = "msg", value = ""),
    @WebInitParam(name = "st", value = ""),
    @WebInitParam(name = "un", value = ""),
    @WebInitParam(name = "et", value = "")})
public class NotificadorExcepcionServlet extends HttpServlet {
    
    private static final long serialVersionUID = 5393837855516135464L;
    private static final Logger LOGGER = Logger.getLogger(NotificadorExcepcionServlet.class.getName());
    private static final boolean DEBUG;
    
    @Inject private NotificadorExcepcion notificadorExcepcion;
    
    static {
        DEBUG = false;
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
        boolean hasException = Boolean.valueOf(request.getParameter("he"));
        
        if(hasException){
            HttpSession session = request.getSession();            
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
            
            String type = request.getParameter("tp");
            String message = request.getParameter("msg");
            String stackTrace = request.getParameter("st");
            String username = usuario.getUserName();
            String exceptionTime = request.getParameter("et");
            
            log(Logger.Level.INFO, "Tipo: {0}", type);
            log(Logger.Level.INFO, "Mensaje: {0}", message);
            log(Logger.Level.INFO, "Pila de seguimiento: {0}", stackTrace);
            log(Logger.Level.INFO, "Hora y fecha de la excepción: {0}", exceptionTime);
            log(Logger.Level.INFO, "Nombre de usuario: {0}", username);
            
            switch(type) {
                case "java.lang.NullPointerException" :
                case "javax.ejb.EJBException" :
                case "java.lang.RuntimeException" :
                case "mx.gob.saludtlax.rh.excepciones.BusinessException" :
                case "mx.gob.saludtlax.rh.excepciones.ReglaNegocioException" :
                case "mx.gob.saludtlax.rh.excepciones.SistemaException" :
                    notificadorExcepcion.notificar(username, type, message, stackTrace, exceptionTime);
                    break;
                default:
                    //Do nothing
                    break;
            }            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This Servlet allows to notify when some exception occurred and sending it via e-mail.";
    }
    
    private void log(Logger.Level level, String mensaje, Object parametro){
        if(DEBUG){
            LOGGER.logv(level, mensaje, parametro);
        }
    }
}