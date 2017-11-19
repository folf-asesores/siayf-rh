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
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "clasificaciones_reportes")
public class ClasificacionReporteEntity implements Serializable {

    private static final long serialVersionUID = 6972092391298037619L;

    @Id
    @Column(name = "id_clasificacion_reporte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClasificacionReporte;

    @Column(name = "clasificacion_reporte")
    private String clasificacionReporte;

    public Integer getIdClasificacionReporte() {
        return idClasificacionReporte;
    }

    public void setIdClasificacionReporte(Integer idClasificacionReporte) {
        this.idClasificacionReporte = idClasificacionReporte;
    }

    public String getClasificacionReporte() {
        return clasificacionReporte;
    }

    public void setClasificacionReporte(String clasificacionReporte) {
        this.clasificacionReporte = clasificacionReporte;
    }

}
