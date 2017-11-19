
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

@Entity
@Table(name = "configuracion_usuario_modulo")
public class ConfiguracionUsuarioModuloEntity implements Serializable {

    private static final long serialVersionUID = -5814989879639246541L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_usuario_modulo")
    private Integer idConfiguracionUsuarioModulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_modulo_accion")
    private ConfiguracionModuloAccionEntity configuracionModuloAccion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public Integer getIdConfiguracionUsuarioModulo() {
        return idConfiguracionUsuarioModulo;
    }

    public void setIdConfiguracionUsuarioModulo(Integer idConfiguracionUsuarioModulo) {
        this.idConfiguracionUsuarioModulo = idConfiguracionUsuarioModulo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ConfiguracionModuloAccionEntity getConfiguracionModuloAccion() {
        return configuracionModuloAccion;
    }

    public void setConfiguracionModuloAccion(ConfiguracionModuloAccionEntity configuracionModuloAccion) {
        this.configuracionModuloAccion = configuracionModuloAccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
