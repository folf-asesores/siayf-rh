
package mx.gob.saludtlax.rh.siif.reportarfederales;

import org.primefaces.model.UploadedFile;

public class ReportarSiifFederalesView {
    private UploadedFile dat;
    private UploadedFile tra;

    private Integer idNombramiento;
    private String periodo;
    private Integer anio;

    public void panelPrincipal() {
    }

    public UploadedFile getDat() {
        return dat;
    }

    public void setDat(UploadedFile dat) {
        this.dat = dat;
    }

    public UploadedFile getTra() {
        return tra;
    }

    public void setTra(UploadedFile tra) {
        this.tra = tra;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
