
package mx.gob.saludtlax.rh.nomina.timbrado;

import org.primefaces.model.UploadedFile;

public class TimbrarNominaEstatalView {
    private UploadedFile dat;
    private UploadedFile tra;

    public void panelPrincipal() {
        //		conceptoNominaSelect = null;
        //		panelPrincipal = Boolean.TRUE;
        //		panelGestion = Boolean.FALSE;
        //		disabledIrGestionar = Boolean.TRUE;
        //		disabledVerPercepcion = tipoSelect == 1;
        //		disabledVerDeduccion = tipoSelect == 0;
        //		disabledVerTodos = (disabledVerPercepcion && disabledVerDeduccion);
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
}