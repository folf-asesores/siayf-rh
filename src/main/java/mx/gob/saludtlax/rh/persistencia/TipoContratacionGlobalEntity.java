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
 * @author Leila Schiaffini Ehuan
 *
 * @since 12/10/2016 18:08:22
 */
@Entity
@Table(name = "tipo_contratacion_global")
public class TipoContratacionGlobalEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4117927414115070714L;

    @Id
    @Column(name = "tipo_contratacion")
    private String tipoContratacion;

    @Column(name = "id_tipoContratacion")
    private int id_tipo_contratacion;

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public int getId_tipo_contratacion() {
        return id_tipo_contratacion;
    }

    public void setId_tipo_contratacion(int id_tipo_contratacion) {
        this.id_tipo_contratacion = id_tipo_contratacion;
    }

}
