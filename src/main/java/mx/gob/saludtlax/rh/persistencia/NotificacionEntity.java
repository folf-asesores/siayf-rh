/*
 * NotificacionEntity.java
 * Creado el Aug 10, 2016 11:17:06 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.gob.saludtlax.rh.notificacion.Modulo;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "notificaciones")
public class NotificacionEntity implements Serializable {

    private static final long serialVersionUID = 485846273097451593L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @ManyToOne
    @JoinColumn(name = "id_remitente", referencedColumnName = "id_usuario")
    private UsuarioEntity remitente;

    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

    @Column(name = "hora_publicacion")
    @Temporal(TemporalType.TIME)
    private Date horaPublicacion;

    @Enumerated(EnumType.STRING)
    private Modulo modulo;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "cuerpo")
    private String cuerpo;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "notificacion")
    private Collection<NotificacionDestinatarioEntity> destinatarios;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "notificacion")
    private Collection<NotificacionParametroEntity> parametros;

    public NotificacionEntity() {
    }

    public NotificacionEntity(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public NotificacionEntity(Integer idNotificacion, Date fechaPublicacion, Date horaPublicacion, Modulo modulo) {
        this.idNotificacion = idNotificacion;
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.modulo = modulo;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public UsuarioEntity getRemitente() {
        return remitente;
    }

    public void setRemitente(UsuarioEntity remitente) {
        this.remitente = remitente;
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

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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

    public Collection<NotificacionDestinatarioEntity> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(Collection<NotificacionDestinatarioEntity> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Collection<NotificacionParametroEntity> getParametros() {
        return parametros;
    }

    public void setParametros(Collection<NotificacionParametroEntity> parametros) {
        this.parametros = parametros;
    }

}
