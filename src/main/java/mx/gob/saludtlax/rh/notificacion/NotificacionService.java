/*
 * NotificacionService.java
 * Creado el Aug 3, 2016 5:18:11 PM
 *
 */

package mx.gob.saludtlax.rh.notificacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacion;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.NotificacionDestinatarioEntity;
import mx.gob.saludtlax.rh.persistencia.NotificacionEntity;
import mx.gob.saludtlax.rh.persistencia.NotificacionParametroEntity;
import mx.gob.saludtlax.rh.persistencia.NotificacionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * Esta es una clase ayudante de {@link NotificacionEJB} que se encarga de
 * realizar la lógica de las notificaciones.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NotificacionService {

    private static final Logger LOGGER = Logger
            .getLogger(NotificacionService.class.getName());
    private static final String REMITENTE = "noresponder@folfasesores.com";
    private static final String REMITENTE_CONTRASENYA = "noresponder2016";
    private static final String SERVER_HOST = "lilith.rxmx.net";
    private static final Properties PROPIEDADES_SMTP;

    static {
        PROPIEDADES_SMTP = new Properties();
        PROPIEDADES_SMTP.put("mail.smtp.auth", "true");
        PROPIEDADES_SMTP.put("mail.smtp.starttls.enable", "true");
        PROPIEDADES_SMTP.put("mail.smtp.ssl.enable", "true");
        PROPIEDADES_SMTP.put("mail.smtp.host", SERVER_HOST);
        PROPIEDADES_SMTP.put("mail.smtp.port", "465");
    }

    @Inject
    private ConfiguracionAplicacion configuracionAplicacion;
    @Inject
    private NotificacionRepository notificacionRepository;
    @Inject
    private UsuarioRepository usuarioRepository;

    protected void crear(NotificacionDTO notificacion) {
        UsuarioEntity usuario = usuarioRepository
                .obtenerPorId(notificacion.getIdRemitente());

        List<NotificacionParametroEntity> parametrosEntities = new ArrayList<>();

        for (Map.Entry<String, String> parametro : notificacion.getParametros()
                .entrySet()) {
            NotificacionParametroEntity parametroEntity = new NotificacionParametroEntity();
            parametroEntity.setNombre(parametro.getKey());
            parametroEntity.setValor(parametro.getValue());

            parametrosEntities.add(parametroEntity);
        }

        List<NotificacionDestinatarioEntity> destinatarios = new ArrayList<>();

        for (Map.Entry<Integer, String> idDestinatario : notificacion
                .getIdsDestinatarios().entrySet()) {
            UsuarioEntity destinatario = usuarioRepository
                    .obtenerPorId(idDestinatario.getKey());
            NotificacionDestinatarioEntity destinatarioEntity = new NotificacionDestinatarioEntity();
            destinatarioEntity.setDestinatario(destinatario);
            destinatarioEntity.setToken(generarToken());
            destinatarioEntity.setVisto(false);

            destinatarios.add(destinatarioEntity);
        }

        NotificacionEntity notificacionEntity = convertirDTOAEntidad(
                new NotificacionEntity(), notificacion, usuario, destinatarios,
                parametrosEntities);

        Date fecha = Calendar.getInstance().getTime();

        notificacionEntity.setIdNotificacion(null);
        notificacionEntity.setFechaPublicacion(fecha);
        notificacionEntity.setHoraPublicacion(fecha);

        notificacionRepository.crear(notificacionEntity);

        String rootPath = configuracionAplicacion.getConfiguracion("app.path");

        if (ValidacionUtil.esCadenaVacia(rootPath)) {
            throw new ValidationException(
                    "El root path no debe estar vacio. No se enviará el correo electrónico de notificación.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        } else {
            enviarCorreoE(notificacionEntity, rootPath);
        }
    }

    protected NotificacionDTO obtenerPorToken(String token)
            throws SistemaException {
        try {
            NotificacionEntity notificacion = notificacionRepository
                    .obtenerPorToken(token);
            NotificacionDTO notificacionDTO = convertirEntidadADTO(
                    notificacion);

            List<Integer> idsEliminar = new ArrayList<>();

            for (Map.Entry<Integer, String> idsDestinatarios : notificacionDTO
                    .getIdsDestinatarios().entrySet()) {
                Integer idDestinatario = idsDestinatarios.getKey();
                String tokenI = idsDestinatarios.getValue();

                if (!token.equals(tokenI)) {
                    idsEliminar.add(idDestinatario);
                }
            }

            for (Integer id : idsEliminar) {
                notificacionDTO.getIdsDestinatarios().remove(id);
            }

            return notificacionDTO;
        } catch (NoResultException ex) {
            throw new SistemaException("Token no encontrado", ex,
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }
    }

    protected void marcarComoVista(String token) {
        notificacionRepository.marcarComoVista(token);
    }

    protected List<NotificacionDTO> consultarNotificacionesPorIdUsuario(
            Integer idUsuario) {
        List<NotificacionEntity> notificaciones = notificacionRepository
                .consutarNotificacionesPorIdUsuario(idUsuario);
        List<NotificacionDTO> notificacionesDTOs = convertirEntidadesADTOs(
                notificaciones);

        for (NotificacionDTO notificacionDTO : notificacionesDTOs) {
            List<Integer> idsEliminar = new ArrayList<>();

            for (Map.Entry<Integer, String> idsDestinatarios : notificacionDTO
                    .getIdsDestinatarios().entrySet()) {
                Integer idDestinatario = idsDestinatarios.getKey();

                if (idUsuario.intValue() != idDestinatario.intValue()) {
                    idsEliminar.add(idDestinatario);
                }
            }

            for (Integer id : idsEliminar) {
                notificacionDTO.getIdsDestinatarios().remove(id);
            }
        }

        return notificacionesDTOs;
    }

    protected List<NotificacionDTO> consultarNotificacionesPorIdUsuarioVisto(
            Integer idUsuario, boolean visto) {
        List<NotificacionEntity> notificaciones = notificacionRepository
                .consutarNotificacionesPorIdUsuarioVisto(idUsuario, visto);
        List<NotificacionDTO> notificacionesDTOs = convertirEntidadesADTOs(
                notificaciones);

        for (NotificacionDTO notificacionDTO : notificacionesDTOs) {
            List<Integer> idsEliminar = new ArrayList<>();

            for (Map.Entry<Integer, String> idsDestinatarios : notificacionDTO
                    .getIdsDestinatarios().entrySet()) {
                Integer idDestinatario = idsDestinatarios.getKey();

                if (idUsuario.intValue() != idDestinatario.intValue()) {
                    idsEliminar.add(idDestinatario);
                }
            }

            for (Integer id : idsEliminar) {
                notificacionDTO.getIdsDestinatarios().remove(id);
            }
        }

        return notificacionesDTOs;
    }

    private void enviarCorreoE(NotificacionEntity notificacion,
            String rootPath) {
        try {

            Collection<NotificacionDestinatarioEntity> destinatarios = notificacion
                    .getDestinatarios();
            if (destinatarios != null && !destinatarios.isEmpty()) {
                for (NotificacionDestinatarioEntity destinatario : destinatarios) {
                    Session session = Session.getInstance(PROPIEDADES_SMTP,
                            new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(REMITENTE,
                                            REMITENTE_CONTRASENYA);
                                }
                            });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(REMITENTE));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(destinatario.getDestinatario()
                                    .getCorreo()));
                    message.setSubject(
                            "Notificación: " + notificacion.getAsunto());

                    StringBuilder cuerpoCorreo = new StringBuilder();

                    cuerpoCorreo.append(notificacion.getCuerpo());
                    cuerpoCorreo.append(
                            "<hr /><p>Para ver mayor: información:</p>");
                    cuerpoCorreo.append("<p>Haga clic <a href=\"");
                    cuerpoCorreo.append(rootPath);
                    cuerpoCorreo
                            .append("/notificaciones/ver-notificacion?token=");
                    cuerpoCorreo.append(destinatario.getToken());
                    cuerpoCorreo.append("\">aquí</a> </p>");
                    cuerpoCorreo.append(
                            "<p>En caso de la de no ver poder entrar con el vinculo copie y pege esta ruta en su navegador: ");
                    cuerpoCorreo.append(rootPath);
                    cuerpoCorreo
                            .append("/notificaciones/ver-notificacion?token=");
                    cuerpoCorreo.append(destinatario.getToken());
                    cuerpoCorreo.append("</p>");

                    message.setContent(cuerpoCorreo.toString(), "text/html");

                    Transport.send(message);
                }
            } else {
                LOGGER.error(
                        "La notificación no se envio porque no exitia ningún destinatario (email).");
            }
        } catch (MessagingException ex) {
            LOGGER.error("Error durante el envio del email.", ex);
        }

    }

    private String generarToken() {
        String token = RandomStringUtils.randomAlphanumeric(128);
        return notificacionRepository.existeToken(token) ? generarToken()
                : token;
    }

    private static NotificacionDTO convertirEntidadADTO(
            NotificacionEntity entidad) {
        if (entidad == null) {
            return null;
        }

        NotificacionDTO dto = new NotificacionDTO();

        dto.setIdNotificacion(entidad.getIdNotificacion());
        dto.setIdRemitente(entidad.getRemitente().getIdUsuario());
        dto.setFechaPublicacion(entidad.getFechaPublicacion());
        dto.setHoraPublicacion(entidad.getHoraPublicacion());
        dto.setModulo(entidad.getModulo());
        dto.setAsunto(entidad.getAsunto());
        dto.setCuerpo(entidad.getCuerpo());

        Map<Integer, String> idsDestinatarios = new HashMap<>();

        for (NotificacionDestinatarioEntity destinatario : entidad
                .getDestinatarios()) {
            idsDestinatarios.put(destinatario.getDestinatario().getIdUsuario(),
                    destinatario.getToken());
        }

        dto.setIdsDestinatarios(idsDestinatarios);

        Map<String, String> parametros = new HashMap<>();

        for (NotificacionParametroEntity parametro : entidad.getParametros()) {
            parametros.put(parametro.getNombre(), parametro.getValor());
        }

        dto.setParametros(parametros);

        return dto;
    }

    private static NotificacionEntity convertirDTOAEntidad(
            NotificacionEntity entidad, NotificacionDTO dto,
            UsuarioEntity remitente,
            List<NotificacionDestinatarioEntity> destinatarios,
            List<NotificacionParametroEntity> parametros) {
        if (entidad == null) {
            entidad = new NotificacionEntity();
        }

        entidad.setIdNotificacion(dto.getIdNotificacion());
        entidad.setRemitente(remitente);
        entidad.setFechaPublicacion(dto.getFechaPublicacion());
        entidad.setHoraPublicacion(dto.getHoraPublicacion());
        entidad.setModulo(dto.getModulo());
        entidad.setAsunto(dto.getAsunto());
        entidad.setCuerpo(dto.getCuerpo());

        for (NotificacionParametroEntity parametro : parametros) {
            parametro.setNotificacion(entidad);
        }

        entidad.setParametros(parametros);

        for (NotificacionDestinatarioEntity destinatario : destinatarios) {
            destinatario.setNotificacion(entidad);
        }

        entidad.setDestinatarios(destinatarios);

        return entidad;
    }

    private List<NotificacionDTO> convertirEntidadesADTOs(
            List<NotificacionEntity> entidades) {
        List<NotificacionDTO> dtos = new ArrayList<>();

        for (NotificacionEntity entidad : entidades) {
            NotificacionDTO dto = convertirEntidadADTO(entidad);

            dtos.add(dto);
        }

        return dtos;
    }

}
