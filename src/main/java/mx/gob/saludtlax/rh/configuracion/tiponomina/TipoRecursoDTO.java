/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.tiponomina;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 *
 */
public class TipoRecursoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5108436873029750021L;
    private Integer idTipoRecurso;
    private String idBase36;
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
