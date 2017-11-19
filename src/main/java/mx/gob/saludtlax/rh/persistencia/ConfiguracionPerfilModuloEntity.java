
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
@Table(name = "configuracion_perfil_modulo")
public class ConfiguracionPerfilModuloEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6335865192257730628L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_modulo")
    private Integer id_perfil_modulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private PerfilEntity perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_modulo_accion")
    private ConfiguracionModuloAccionEntity configuracionModuloAccion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public Integer getId_perfil_modulo() {
        return id_perfil_modulo;
    }

    public void setId_perfil_modulo(Integer id_perfil_modulo) {
        this.id_perfil_modulo = id_perfil_modulo;
    }

    public PerfilEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEntity perfil) {
        this.perfil = perfil;
    }

    public ConfiguracionModuloAccionEntity getConfiguracionModuloAccion() {
        return configuracionModuloAccion;
    }

    public void setConfiguracionModuloAccion(
            ConfiguracionModuloAccionEntity configuracionModuloAccion) {
        this.configuracionModuloAccion = configuracionModuloAccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
