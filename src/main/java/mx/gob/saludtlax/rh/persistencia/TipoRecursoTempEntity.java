/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 * @email	Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 25/07/2016 14:20:07
 */
@Entity
@Table(name = "tipos_recursos_temp")
public class TipoRecursoTempEntity implements Serializable {

    private static final long serialVersionUID = -9060780883349036497L;

    @Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_recurso")
    private Integer idTipoRecurso;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "TipoRecursoEntity [id tipo recurso=" + idTipoRecurso + ", id base 36=" + idBase36 + ", descripcion="
                + descripcion + "]";
    }

    /**
     * @return the idTipoRecurso
     */
    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    /**
     * @param idTipoRecurso the idTipoRecurso to set
     */
    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    /**
     * @return the idBase36
     */
    public String getIdBase36() {
        return idBase36;
    }

    /**
     * @param idBase36 the idBase36 to set
     */
    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
