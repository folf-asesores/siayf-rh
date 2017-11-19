
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import org.primefaces.model.UploadedFile;

public class ReportarSiifSeguropopularView {

    private UploadedFile datSP;

    private UploadedFile datDeudores;

    private UploadedFile inputDispersion;

    private UploadedFile datoPersonal;

    private UploadedFile datoLaboral;

    public void panelPrincipal() {

    }

    public UploadedFile getDatSP() {
        return datSP;
    }

    public void setDatSP(UploadedFile datSP) {
        this.datSP = datSP;
    }

    public UploadedFile getDatDeudores() {
        return datDeudores;
    }

    public void setDatDeudores(UploadedFile datDeudores) {
        this.datDeudores = datDeudores;
    }

    public UploadedFile getInputDispersion() {
        return inputDispersion;
    }

    public void setInputDispersion(UploadedFile inputDispersion) {
        this.inputDispersion = inputDispersion;
    }

    public UploadedFile getDatoPersonal() {
        return datoPersonal;
    }

    public void setDatoPersonal(UploadedFile datoPersonal) {
        this.datoPersonal = datoPersonal;
    }

    public UploadedFile getDatoLaboral() {
        return datoLaboral;
    }

    public void setDatoLaboral(UploadedFile datoLaboral) {
        this.datoLaboral = datoLaboral;
    }

}
