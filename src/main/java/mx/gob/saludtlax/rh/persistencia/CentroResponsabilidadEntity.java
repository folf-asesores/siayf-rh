
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author José Pablo
 */
@Entity
@Table(name = "centro_responsabilidad")
public class CentroResponsabilidadEntity implements Serializable {
    private static final long serialVersionUID = 7212628000214644356L;

    @Id
    @Column(name = "id_centro_responsabilidad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCentroResponsabilidad;

    @Column(name = "clave")
    private String clave;

    @Column(name = "descripcion")
    private String descripcion;

    public Integer getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
