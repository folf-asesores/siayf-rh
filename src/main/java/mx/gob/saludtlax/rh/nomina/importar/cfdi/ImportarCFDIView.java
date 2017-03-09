package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import org.primefaces.model.UploadedFile;

public class ImportarCFDIView {
	private UploadedFile file;

	public void panelPrincipal() {
//		conceptoNominaSelect = null;
//		panelPrincipal = Boolean.TRUE;
//		panelGestion = Boolean.FALSE;
//		disabledIrGestionar = Boolean.TRUE;
//		disabledVerPercepcion = tipoSelect == 1;
//		disabledVerDeduccion = tipoSelect == 0;
//		disabledVerTodos = (disabledVerPercepcion && disabledVerDeduccion);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}