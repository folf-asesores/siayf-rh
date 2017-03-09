/*
 * NotificacionParametroEntity.java
 * Creado el Aug 10, 2016 11:17:07 AM
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
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "notificaciones_parametros")
public class NotificacionParametroEntity implements Serializable {

    private static final long serialVersionUID = 2654070062363265626L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_parametro")
    private Integer idNotificacionParametro;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "valor")
    private String valor;
    
    @ManyToOne
    @JoinColumn(name = "id_notificacion", referencedColumnName = "id_notificacion")
    private NotificacionEntity notificacion;

    public NotificacionParametroEntity() {
    }

    public NotificacionParametroEntity(Integer idNotificacionParametro) {
        this.idNotificacionParametro = idNotificacionParametro;
    }

    public NotificacionParametroEntity(Integer idNotificacionParametro, String nombre, String valor, NotificacionEntity notificacion) {
        this.idNotificacionParametro = idNotificacionParametro;
        this.nombre = nombre;
        this.valor = valor;
        this.notificacion = notificacion;
    }

    public Integer getIdNotificacionParametro() {
        return idNotificacionParametro;
    }

    public void setIdNotificacionParametro(Integer idNotificacionParametro) {
        this.idNotificacionParametro = idNotificacionParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public NotificacionEntity getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(NotificacionEntity idNotificacion) {
        this.notificacion = idNotificacion;
    }
}
