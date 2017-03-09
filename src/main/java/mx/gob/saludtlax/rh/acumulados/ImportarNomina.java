/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.acumulados;

import org.primefaces.model.UploadedFile;


/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17:50:25 24/09/2016
 */
public interface ImportarNomina {

	public void importarNominaTheosToSIIF(UploadedFile dat, UploadedFile tra, String idContexto);
	
	public void importarNominaExcelDatTra(UploadedFile dat, UploadedFile tra, String idContexto);

}
