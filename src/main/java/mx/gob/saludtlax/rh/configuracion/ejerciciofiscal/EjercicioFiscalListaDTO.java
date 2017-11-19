
package mx.gob.saludtlax.rh.configuracion.ejerciciofiscal;

import java.util.Date;

public class EjercicioFiscalListaDTO {
    private Integer idEjercicioFiscal;
    private Integer ejercicioFiscal;
    private Date inicio;
    private Date fin;
    private String periodicidad;

    public EjercicioFiscalListaDTO() {
    }

    public EjercicioFiscalListaDTO(Integer idEjercicioFiscal,
            Integer ejercicioFiscal, Date inicio, Date fin,
            String periodicidad) {
        this.idEjercicioFiscal = idEjercicioFiscal;
        this.ejercicioFiscal = ejercicioFiscal;
        this.inicio = inicio;
        this.fin = fin;
        this.periodicidad = periodicidad;
    }

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
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

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }
}
