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
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 18:41:48
 *
 */
@Entity
@Table(name = "subclasificacion_tabulador")
public class SubclasificacionTabuladorEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5732053163080587933L;

    @Id
    @Column(name = "id_subclasificacion_tabulador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubclasificacion;

    @Column(name = "subclasificacion")
    private String subclasificacion;

    public String getSubclasificacion() {
        return subclasificacion;
    }

    public void setSubclasificacion(String subclasificacion) {
        this.subclasificacion = subclasificacion;
    }

    public Integer getIdSubclasificacion() {
        return idSubclasificacion;
    }

}
