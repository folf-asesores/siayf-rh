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
 * @since 10/08/2016 22:24:09
 */
@Entity
@Table(name = "configuraciones_aprobaciones")
public class ConfiguracionAprobacionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1376782784935786641L;
    @Id
    @Column(name = "id_configuracion_aprobacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfiguracionAprobacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accion_autorizacion")
    private OperacionSistemaEntity accion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @Column(name = "estatus")
    private String estatus;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento_empleado")
    private TipoMovimientoEmpleadoEntity tipoMovimientoEmpleado;

    public TipoMovimientoEmpleadoEntity getTipoMovimientoEmpleado() {
        return tipoMovimientoEmpleado;
    }

    public void setTipoMovimientoEmpleado(
            TipoMovimientoEmpleadoEntity tipoMovimientoEmpleado) {
        this.tipoMovimientoEmpleado = tipoMovimientoEmpleado;
    }

    public Integer getIdConfiguracionAprobacion() {
        return idConfiguracionAprobacion;
    }

    public void setIdConfiguracionAprobacion(
            Integer idConfiguracionAprobacion) {
        this.idConfiguracionAprobacion = idConfiguracionAprobacion;
    }

    public OperacionSistemaEntity getAccion() {
        return accion;
    }

    public void setAccion(OperacionSistemaEntity accion) {
        this.accion = accion;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
