/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que será utilizada para la administración del sistema.
 *
 */
@Entity
@Table(name = "unidades_responsables")
public class UnidadResponsableEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_unidad_responsable")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidadResponsable;

    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Column(name = "id_unidad_x_dependencia")
    private Integer idUnidadXDependencia;
    @Column(name = "id_base_36")
    private String idBase36;
    @Column(name = "descripcion")
    private String descripcion;

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdUnidadXDependencia() {
        return idUnidadXDependencia;
    }

    public void setIdUnidadXDependencia(Integer idUnidadXDependencia) {
        this.idUnidadXDependencia = idUnidadXDependencia;
    }

    public String getIdBase36() {
        return idBase36;
    }

    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
