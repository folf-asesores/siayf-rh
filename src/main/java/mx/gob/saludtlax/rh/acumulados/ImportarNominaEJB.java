/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.acumulados;

import javax.ejb.Stateless;

import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17:50:39 24/09/2016
 */
@Stateless
public class ImportarNominaEJB implements ImportarNomina {

	@Inject
	private ImportarNominaService importarNominaService;

	/*
	 * (non-Javadoc)
	 * idContexto = numeroArchivo + 
	 * @see
	 * mx.gob.saludtlax.rh.acumulados.ImportarNomina#importarNominaTheosToSIIF(
	 * org.primefaces.model.UploadedFile, org.primefaces.model.UploadedFile)
	 */
	@Override
	public void importarNominaTheosToSIIF(UploadedFile dat, UploadedFile tra, String idContexto) {
		
		importarNominaService.importarNominaTheosToSIIF(dat, tra, idContexto);

	}

	@Override
	public void importarNominaExcelDatTra(UploadedFile dat, UploadedFile tra, String idContexto) {
		
		importarNominaService.importarNominaExcelDatTra(dat, tra, idContexto);
	}

}
