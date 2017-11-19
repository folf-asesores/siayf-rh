
package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;
import java.util.List;

/**
 * Vista de los datos de una jornada.
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class JornadaViewModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 627567143668361978L;

    private Integer idJornada;

    private String descripcion;

    private Integer trabajaDiasNoLaborables;

    private List<ReglaAsistenciaViewModel> reglasAsistencia;

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTrabajaDiasNoLaborables() {
        return trabajaDiasNoLaborables;
    }

    public void setTrabajaDiasNoLaborables(Integer trabajaDiasNoLaborables) {
        this.trabajaDiasNoLaborables = trabajaDiasNoLaborables;
    }

    public List<ReglaAsistenciaViewModel> getReglasAsistencia() {
        return reglasAsistencia;
    }

    public void setReglasAsistencia(List<ReglaAsistenciaViewModel> reglasAsistencia) {
        this.reglasAsistencia = reglasAsistencia;
    }

}
