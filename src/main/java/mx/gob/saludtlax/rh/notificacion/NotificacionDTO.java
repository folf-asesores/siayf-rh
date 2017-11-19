/*
 * NotificacionDTO.java
 * Creado el Aug 3, 2016 4:42:33 PM
 *
 */

package mx.gob.saludtlax.rh.notificacion;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NotificacionDTO implements Serializable {

    private static final long serialVersionUID = 3792033464231614169L;

    private Integer idNotificacion;
    private Integer idRemitente;
    private Date fechaPublicacion;
    private Date horaPublicacion;
    private Modulo modulo;
    private String asunto;
    private String cuerpo;
    /** Contiene el ID de los destinatarios y el token de cada destinatario */
    private Map<Integer, String> idsDestinatarios;
    /** Contiene los parametros y sus valores como cadenas */
    private Map<String, String> parametros;

    public NotificacionDTO() {
    }

    public NotificacionDTO(Integer idRemitente, Modulo modulo, String asunto, String cuerpo, Map<Integer, String> idsDestinatarios,
            Map<String, String> parametros) {
        this.idRemitente = idRemitente;
        this.modulo = modulo;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.idsDestinatarios = idsDestinatarios;
        this.parametros = parametros;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Integer getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Integer idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getHoraPublicacion() {
        return horaPublicacion;
    }

    public void setHoraPublicacion(Date horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

    public Map<Integer, String> getIdsDestinatarios() {
        return idsDestinatarios;
    }

    public void setIdsDestinatarios(Map<Integer, String> idsDestinatarios) {
        this.idsDestinatarios = idsDestinatarios;
    }

}
