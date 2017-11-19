/*
 * NotificacionController.java
 * Creado el 14/08/2016 04:25:18 PM
 *
 */

package mx.gob.saludtlax.rh.notificacion;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * Esta clase se encarga de controlar las notificaciones de la plantilla.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@ManagedBean
@SessionScoped
public class NotificacionController {

    @Inject
    private Notificacion notificacionEJB;

    private String contextPath;
    private Integer idUsuario;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        HttpSession sesion = (HttpSession) externalContext.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        contextPath = externalContext.getRequestContextPath();

        if (usuario != null) {
            idUsuario = usuario.getIdUsuario();
        } else {
            idUsuario = 0;
        }
    }

    public void llenarNotificaciones() {
        List<NotificacionDTO> notificaciones = getNotificaciones();
        StringBuilder script = new StringBuilder();
        RequestContext contextPrimeFaces = RequestContext.getCurrentInstance();

        if (notificaciones != null && !notificaciones.isEmpty()) {
            addScriptCantidadNotificaciones(script, notificaciones.size());
            addScriptNotificaciones(script, notificaciones);
        } else {
            script.append("$(\'#boton-notificaciones-vinculo > .boton-notificaciones-numero\').css(\'display\',\'none\');\n");
            script.append("$(\'#notificaciones-menu > section > .notificacion-item\').remove();\n");
            script.append("$(\'#notificaciones-menu > section\').append(\'<div class=\"notificacion-item\"><p>No hay notificaciones nuevas</p></div>');\n");
        }

        contextPrimeFaces.execute(script.toString());
    }

    private List<NotificacionDTO> getNotificaciones() {
        List<NotificacionDTO> notificaciones;

        if (idUsuario != null && idUsuario > 0) {
            notificaciones = notificacionEJB.consultarNotificacionesPorIdUsuarioNoVistas(idUsuario);
        } else {
            notificaciones = new ArrayList<>();
        }

        return notificaciones;
    }

    private String addScriptCantidadNotificaciones(StringBuilder sb, int cantidadNotificaciones) {
        sb.append("$(\'#boton-notificaciones-vinculo > .boton-notificaciones-numero\').css(\'display\',\'inline-block\');\n");
        sb.append("$(\'#boton-notificaciones-vinculo >");
        sb.append(" .boton-notificaciones-numero\').text(");
        sb.append(cantidadNotificaciones);
        sb.append(");\n");

        return sb.toString();
    }

    private void addScriptNotificaciones(StringBuilder sb, List<NotificacionDTO> notificaciones) {
        sb.append("$(\'#notificaciones-menu > section > .notificacion-item\').remove();\n");

        for (NotificacionDTO notificacion : notificaciones) {
            sb.append("$(\'#notificaciones-menu > section\').append(\'<div class=\"notificacion-item\">");
            //TODO: Hacer que en lugar de que salga la fecha mostrar el tiempo que lleva desde que se dio la notificación.
            sb.append(MessageFormat.format(
                    "<h4>{0}</h4><p>{1}</p><div class=\"notificacion-item-pie\"><span style=\"padding-right: 5px;\"><a href=\"{2}\" title=\"Ir a notificación\">Ver</a></span><span><a href=\"{4}\" title=\"Marcar notificación como visto\">Visto</a></span><span class=\"fecha-publicacion\">{3}</span></div>",
                    notificacion.getAsunto(), notificacion.getCuerpo(), obtenerUrlIr(notificacion.getIdsDestinatarios()),
                    FechaUtil.formatearFecha("dd MMMM yyyy", notificacion.getFechaPublicacion()),
                    obtenerUrlMarcarComoVisto(notificacion.getIdsDestinatarios())));
            sb.append("</div>\');\n");
        }

    }

    private String obtenerUrlIr(Map<Integer, String> idsDestinatarios) {
        String token = idsDestinatarios.get(idUsuario);
        StringBuilder sb = new StringBuilder(contextPath);

        if ('/' != sb.charAt(sb.length() - 1)) {
            sb.append('/');
        }

        sb.append("contenido/notificaciones/ver-notificacion?token=");
        sb.append(token);

        return sb.toString();
    }

    private String obtenerUrlMarcarComoVisto(Map<Integer, String> idsDestinatarios) {
        String token = idsDestinatarios.get(idUsuario);
        StringBuilder sb = new StringBuilder(contextPath);

        if ('/' != sb.charAt(sb.length() - 1)) {
            sb.append('/');
        }

        sb.append("contenido/notificaciones/notificacion-vista?token=");
        sb.append(token);
        sb.append("&url=");

        FacesContext fc = FacesContext.getCurrentInstance();
        String url;

        try {
            url = URLEncoder.encode(fc.getExternalContext().getRequestServletPath(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            url = fc.getExternalContext().getRequestServletPath();
        }

        sb.append(url);

        return sb.toString();
    }
}
