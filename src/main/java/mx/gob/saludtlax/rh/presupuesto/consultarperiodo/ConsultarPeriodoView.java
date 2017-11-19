
package mx.gob.saludtlax.rh.presupuesto.consultarperiodo;

import java.util.List;

public class ConsultarPeriodoView {
    private List<String> periodoList;
    private String periodo;
    private List<ConsultaPeriodo> consultaPeriodoList;
    private ConsultaPeriodo consultaPeriodo;
    private Boolean butonDownload;

    public List<String> getPeriodoList() {
        return periodoList;
    }

    public void setPeriodoList(List<String> periodoList) {
        this.periodoList = periodoList;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public ConsultaPeriodo getConsultaPeriodo() {
        return consultaPeriodo;
    }

    public void setConsultaPeriodo(ConsultaPeriodo consultaPeriodo) {
        this.consultaPeriodo = consultaPeriodo;
    }

    public List<ConsultaPeriodo> getConsultaPeriodoList() {
        return consultaPeriodoList;
    }

    public void setConsultaPeriodoList(List<ConsultaPeriodo> consultaPeriodoList) {
        this.consultaPeriodoList = consultaPeriodoList;
    }

    public Boolean getButonDownload() {
        return butonDownload;
    }

    public void setButonDownload(Boolean butonDownload) {
        this.butonDownload = butonDownload;
    }
}