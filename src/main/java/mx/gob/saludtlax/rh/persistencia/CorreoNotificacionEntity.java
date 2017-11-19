/*
 *  CorreoNotificacionEntity.java
 *  Creado el Jun 17, 2016 2:48:10 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "correos_notificaciones")
public class CorreoNotificacionEntity implements Serializable {

    private static final long serialVersionUID = 1271434046581634449L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_correo_notificacion")
    private Integer idCorreoNotificacion;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String correoElectronico;

    public CorreoNotificacionEntity() {
    }

    public CorreoNotificacionEntity(Integer idCorreoNotificacion) {
        this.idCorreoNotificacion = idCorreoNotificacion;
    }

    public Integer getIdCorreoNotificacion() {
        return idCorreoNotificacion;
    }

    public void setIdCorreoNotificacion(Integer idCorreoNotificacion) {
        this.idCorreoNotificacion = idCorreoNotificacion;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
