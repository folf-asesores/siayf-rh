
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

@Entity
@Table(name = "configuracion_modulo_accion")
public class ConfiguracionModuloAccionEntity implements Serializable {

    /**
     * @author Edgar RZM
     */
    private static final long serialVersionUID = -8103539765440484242L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_modulo_accion")
    private Integer idConfiguracionModuloAccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo")
    private ModuloEntity modulo;

    @Column(name = "nombre_configuracion")
    private String descripcion;

    public Integer getIdConfiguracionModuloAccion() {
        return idConfiguracionModuloAccion;
    }

    public void setIdConfiguracionModuloAccion(Integer idConfiguracionModuloAccion) {
        this.idConfiguracionModuloAccion = idConfiguracionModuloAccion;
    }

    public ModuloEntity getModulo() {
        return modulo;
    }

    public void setModulo(ModuloEntity modulo) {
        this.modulo = modulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
