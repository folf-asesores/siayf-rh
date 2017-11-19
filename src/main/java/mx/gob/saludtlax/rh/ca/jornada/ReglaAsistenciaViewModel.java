
package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;

/**
 * Vista de los datos de una jornada.
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class ReglaAsistenciaViewModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1702811364819011245L;

    private Integer idReglaAsistencia;

    private Integer idJornada;

    private Integer idIncidencia;

    private Integer minutoInicial;

    private Integer minutoFinal;

    private Integer requiereTramite;

    private String jornada;

    private String incidencia;

    public Integer getIdReglaAsistencia() {
        return idReglaAsistencia;
    }

    public void setIdReglaAsistencia(Integer idReglaAsistencia) {
        this.idReglaAsistencia = idReglaAsistencia;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Integer getMinutoInicial() {
        return minutoInicial;
    }

    public void setMinutoInicial(Integer minutoInicial) {
        this.minutoInicial = minutoInicial;
    }

    public Integer getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(Integer minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public Integer getRequiereTramite() {
        return requiereTramite;
    }

    public void setRequiereTramite(Integer requiereTramite) {
        this.requiereTramite = requiereTramite;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

}
