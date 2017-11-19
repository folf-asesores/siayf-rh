/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "tipos_recursos")
public class TipoRecursoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4771841148156283457L;

    @Id
    //	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_recurso")
    private Integer idTipoRecurso;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
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
