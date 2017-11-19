
package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.Serializable;

import javax.ejb.Stateless;

@Stateless
public class PeriodoEsperaFormModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3447030190476970038L;

    private Integer idIncidenciaOrigen;

    private Integer idTipoContratacion;

    private Integer idTiempoEspera;

    private Boolean diasNaturales;

    private Integer idIncidencia;

    private Integer idPeriodosEsperaIncidencia;

    public Integer getIdIncidenciaOrigen() {
        return idIncidenciaOrigen;
    }

    public void setIdIncidenciaOrigen(Integer idIncidenciaOrigen) {
        this.idIncidenciaOrigen = idIncidenciaOrigen;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Integer getIdTiempoEspera() {
        return idTiempoEspera;
    }

    public void setIdTiempoEspera(Integer idTiempoEspera) {
        this.idTiempoEspera = idTiempoEspera;
    }

    public Boolean getDiasNaturales() {
        return diasNaturales;
    }

    public void setDiasNaturales(Boolean diasNaturales) {
        this.diasNaturales = diasNaturales;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Integer getIdPeriodosEsperaIncidencia() {
        return idPeriodosEsperaIncidencia;
    }

    public void setIdPeriodosEsperaIncidencia(
            Integer idPeriodosEsperaIncidencia) {
        this.idPeriodosEsperaIncidencia = idPeriodosEsperaIncidencia;
    }

}
