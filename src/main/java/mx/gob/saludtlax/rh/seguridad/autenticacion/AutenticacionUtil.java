
package mx.gob.saludtlax.rh.seguridad.autenticacion;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;

public class AutenticacionUtil {

    /**
     * Por se una utilería no se permite creat una instancia de esta clase
     * usando la palabra reservada <code>new</code>.
     */
    private AutenticacionUtil() {
    }

    /**
     * Permite obtener el obterner la información del usuario que está en sesión.
     *
     * @return un DTO con la informacion del usario en sesión.
     */
    public static UsuarioDTO recuperarUsuarioSesion() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        return usuario;
    }
}