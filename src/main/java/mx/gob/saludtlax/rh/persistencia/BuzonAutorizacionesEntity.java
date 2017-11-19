/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:09:01
 */
@Entity
@Table(name = "buzon_autorizaciones")
public class BuzonAutorizacionesEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8968360853556676168L;

    @Id
    @Column(name = "id_buzon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBuzon;

    @Column(name = "id_entidad_contexto")
    private Integer idEntidadContexto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accion")
    private OperacionSistemaEntity accion;

    @Column(name = "finalizado")
    private boolean finalizado;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "cancelado")
    private boolean cancelado;

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEntidadContexto() {
        return idEntidadContexto;
    }

    public void setIdEntidadContexto(Integer idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    public OperacionSistemaEntity getAccion() {
        return accion;
    }

    public void setAccion(OperacionSistemaEntity accion) {
        this.accion = accion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getIdBuzon() {
        return idBuzon;
    }

}
