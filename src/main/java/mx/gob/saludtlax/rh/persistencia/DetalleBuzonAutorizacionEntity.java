/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 23:24:05
 */
@Entity
@Table(name = "detalle_buzon_autorizaciones")
public class DetalleBuzonAutorizacionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8618805905090489656L;

    @Id
    @Column(name = "id_buzon_usuarios")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBuzon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buzon")
    private BuzonAutorizacionesEntity buzon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;

    @Column(name = "autorizado")
    private boolean autorizado;

    @Column(name = "fecha_autorizacion")
    private Date fechaAutorizacion;

    @Column(name = "hora_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaAutorizacion;

    public Integer getIdBuzon() {
        return idBuzon;
    }

    public void setIdBuzon(Integer idBuzon) {
        this.idBuzon = idBuzon;
    }

    public BuzonAutorizacionesEntity getBuzon() {
        return buzon;
    }

    public void setBuzon(BuzonAutorizacionesEntity buzon) {
        this.buzon = buzon;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getHoraAutorizacion() {
        return horaAutorizacion;
    }

    public void setHoraAutorizacion(Date horaAutorizacion) {
        this.horaAutorizacion = horaAutorizacion;
    }

}
