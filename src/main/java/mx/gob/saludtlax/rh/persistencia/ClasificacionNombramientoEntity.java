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
 *
 * @since 04/09/2016 19:15:17
 */
@Entity
@Table(name = "clasificacion_nombramientos")
public class ClasificacionNombramientoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1481429631366360519L;

    @Id
    @Column(name = "id_clasificacion_nombramiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClasificacionNombramiento;

    @Column(name = "clasificacion_nombramiento")
    private String clasificacionNombramiento;

    public String getClasificacionNombramiento() {
        return clasificacionNombramiento;
    }

    public void setClasificacionNombramiento(String clasificacionNombramiento) {
        this.clasificacionNombramiento = clasificacionNombramiento;
    }

    public Integer getIdClasificacionNombramiento() {
        return idClasificacionNombramiento;
    }

}
