
package mx.gob.saludtlax.rh.configuracion.ejerciciofiscal;

import java.util.Date;
import java.util.List;

public class EjercicioFiscalDTO {
    private Integer idEjercicioFiscal;
    private Integer ejercicioFiscal;
    private Date inicio;
    private Date fin;
    private Integer idPeriodicidad;
    private List<PeriodoCalendarioDTO> listPeriodoCalendario;

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Integer getIdPeriodicidad() {
        return idPeriodicidad;
    }

    public void setIdPeriodicidad(Integer idPeriodicidad) {
        this.idPeriodicidad = idPeriodicidad;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }

    public List<PeriodoCalendarioDTO> getListPeriodoCalendario() {
        return listPeriodoCalendario;
    }

    public void setListPeriodoCalendario(List<PeriodoCalendarioDTO> listPeriodoCalendario) {
        this.listPeriodoCalendario = listPeriodoCalendario;
    }
}
