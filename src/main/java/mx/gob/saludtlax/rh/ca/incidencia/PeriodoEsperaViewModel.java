
package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.Serializable;

public class PeriodoEsperaViewModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6732451117820620917L;

    private Integer idPeriodoEsperaIncidencias;
    private Integer idTipoContratacion;
    private Integer idIncidencia;
    private Integer tiempoEspera;
    private Boolean diasNaturales;
    private Integer idIncidenciaOrigen;
    private String tipoContratacion;
    private String incidencia;
    private String incidenciaOrigen;

    public String getIncidenciaOrigen() {
        return incidenciaOrigen;
    }

    public void setIncidenciaOrigen(String incidenciaOrigen) {
        this.incidenciaOrigen = incidenciaOrigen;
    }

    public Integer getIdPeriodoEsperaIncidencias() {
        return idPeriodoEsperaIncidencias;
    }

    public void setIdPeriodoEsperaIncidencias(
            Integer idPeriodoEsperaIncidencias) {
        this.idPeriodoEsperaIncidencias = idPeriodoEsperaIncidencias;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Integer getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(Integer tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public Boolean getDiasNaturales() {
        return diasNaturales;
    }

    public void setDiasNaturales(Boolean diasNaturales) {
        this.diasNaturales = diasNaturales;
    }

    public Integer getIdIncidenciaOrigen() {
        return idIncidenciaOrigen;
    }

    public void setIdIncidenciaOrigen(Integer idIncidenciaOrigen) {
        this.idIncidenciaOrigen = idIncidenciaOrigen;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

}
