/*
 * NotificacionDestinatarioEntity.java
 * Creado el Aug 10, 2016 11:17:06 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "notificaciones_destinatarios")
public class NotificacionDestinatarioEntity implements Serializable {

    private static final long serialVersionUID = -5325416124011597131L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_destinatario")
    private Integer idNotificacionDestinatario;

    @ManyToOne
    @JoinColumn(name = "id_notificacion", referencedColumnName = "id_notificacion")
    private NotificacionEntity notificacion;

    @ManyToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "id_usuario")
    private UsuarioEntity destinatario;

    @Column(name = "token")
    private String token;

    @Column(name = "visto")
    private Boolean visto;

    public NotificacionDestinatarioEntity() {
    }

    public NotificacionDestinatarioEntity(Integer idNotificacionDestinatario) {
        this.idNotificacionDestinatario = idNotificacionDestinatario;
    }

    public Integer getIdNotificacionDestinatario() {
        return idNotificacionDestinatario;
    }

    public void setIdNotificacionDestinatario(
            Integer idNotificacionDestinatario) {
        this.idNotificacionDestinatario = idNotificacionDestinatario;
    }

    public UsuarioEntity getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioEntity destinatario) {
        this.destinatario = destinatario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NotificacionEntity getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(NotificacionEntity notificacion) {
        this.notificacion = notificacion;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }
}
