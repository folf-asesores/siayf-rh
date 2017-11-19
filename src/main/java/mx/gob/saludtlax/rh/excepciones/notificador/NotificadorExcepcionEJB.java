/*
 *  NotificadorExcepcionEJB.java
 *  Creado el Jun 16, 2016 1:26:10 PM
 *
 */

package mx.gob.saludtlax.rh.excepciones.notificador;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class NotificadorExcepcionEJB implements NotificadorExcepcion {

    private static final long serialVersionUID = -4047028545054945712L;

    private static final Logger LOGGER = Logger
            .getLogger(NotificadorExcepcionEJB.class.getName());
    private static final String REMITENTE = "noresponder@folfasesores.com";
    private static final String REMITENTE_CONTRASENYA = "noresponder2016";
    private static final String SERVER_HOST = "lilith.rxmx.net";
    private static final Properties PROPIEDADES_SMTP = new Properties();

    @Inject
    private NotificadorExcepcionService notificadorExcepcionService;

    public NotificadorExcepcionEJB() {
        PROPIEDADES_SMTP.put("mail.smtp.auth", "true");
        PROPIEDADES_SMTP.put("mail.smtp.starttls.enable", "true");
        PROPIEDADES_SMTP.put("mail.smtp.ssl.enable", "true");
        PROPIEDADES_SMTP.put("mail.smtp.host", SERVER_HOST);
        PROPIEDADES_SMTP.put("mail.smtp.port", "465");
    }

    @Override
    @Asynchronous
    public void notificar(String nombreUsuario, String tipo, String mensaje,
            String pilaSeguimiento, String fechaHoraException) {
        try {
            persistirExcepcion(nombreUsuario, tipo, mensaje, pilaSeguimiento,
                    fechaHoraException);
            LOGGER.info("Excepción persistida en la base de datos.");
            enviarCorreoE(nombreUsuario, tipo, mensaje, pilaSeguimiento,
                    fechaHoraException);
            LOGGER.info("Correo enviado exitosamente.");
        } catch (MessagingException ex) {
            LOGGER.error("Error durante el envio del email.", ex);
        }
    }

    private void enviarCorreoE(String nombreUsuario, String tipo,
            String mensaje, String pilaSeguimiento, String fechaHoraExcepcion)
            throws MessagingException {
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

        List<String> destinatarios = notificadorExcepcionService
                .consultarCorreos();

        if (destinatarios != null && !destinatarios.isEmpty()) {
            for (String destinatario : destinatarios) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(destinatario));
            }
        } else {
            message.setRecipients(Message.RecipientType.TO, InternetAddress
                    .parse("freddy.barrera@folfasesores.com.mx"));
        }

        message.setSubject("Notificación: Error del sistema");

        StringBuilder cuerpoCorreo = new StringBuilder();
        cuerpoCorreo.append("<strong>Nombre de usuario:</strong> ")
                .append(nombreUsuario).append("<br />")
                .append("<strong>Tipo de error:</strong> ").append(tipo)
                .append("<br />").append("<strong>Mensaje de error:</strong> ")
                .append(mensaje).append("<br />")
                .append("<strong>Fecha y hora:</strong> ")
                .append(fechaHoraExcepcion).append("<br />")
                .append("<strong>Pila de seguimiento:</strong><br />")
                .append(pilaSeguimiento);

        message.setContent(cuerpoCorreo.toString(), "text/html");
        Transport.send(message);
    }

    private void persistirExcepcion(String nombreUsuario, String tipo,
            String mensaje, String pilaSeguimiento, String fechaHoraException) {
        pilaSeguimiento = pilaSeguimiento.replace("<br />", "\n");
        Date fechaHora = FechaUtil.getFecha(fechaHoraException,
                "yyyy-MM-dd H:mm:ss");

        if (fechaHora == null) {
            fechaHora = Calendar.getInstance().getTime();
        }

        notificadorExcepcionService.persistirExcepcion(nombreUsuario, tipo,
                mensaje, pilaSeguimiento, fechaHora);
    }

}
