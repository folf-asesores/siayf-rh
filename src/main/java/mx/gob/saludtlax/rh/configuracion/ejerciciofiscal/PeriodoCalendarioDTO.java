
package mx.gob.saludtlax.rh.configuracion.ejerciciofiscal;

import java.util.Date;

public class PeriodoCalendarioDTO {
    private Integer idPeriodoCalendario;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private Integer idTipoPeriodo;
    private Integer numeroPeriodo;
    private Integer idEjercicioFiscal;

    public Integer getIdPeriodoCalendario() {
        return idPeriodoCalendario;
    }

    public void setIdPeriodoCalendario(Integer idPeriodoCalendario) {
        this.idPeriodoCalendario = idPeriodoCalendario;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public Integer getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(Integer numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }
}
