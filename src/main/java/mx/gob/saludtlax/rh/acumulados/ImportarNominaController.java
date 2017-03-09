/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.acumulados;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.siif.PaqueteEntradaFederalDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;
import mx.gob.saludtlax.rh.util.GenerateUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 18:18:45 24/09/2016
 */
@ManagedBean(name = "importarNomina")
@ViewScoped
public class ImportarNominaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8192401318131614693L;
	private static final Logger LOGGER = Logger.getLogger(ImportarNominaController.class.getName());

	@Inject
	private ImportarNomina importarNomina;

	private ImportarNominaView view;

	@PostConstruct
	private void init() {
		view = new ImportarNominaView();
	}

	public void importarDatTra() {
		boolean eval = true;
		if (view.getPaqueteEntrada().getDat() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getDat().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo DAT es requerido, El Archivo debe tener la extención *.dat");
			eval = false;
		}
		if (view.getPaqueteEntrada().getTra() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getTra().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo TRA es requerido, El Archivo debe tener la extención *.tra");
			eval = false;
		}

		if (eval) {
			LOGGER.info(view.getPaqueteEntrada().getDat().getFileName());
			LOGGER.info(view.getPaqueteEntrada().getTra().getFileName());

			try {

				importarNomina.importarNominaTheosToSIIF(this.view.getPaqueteEntrada().getDat(),
						this.view.getPaqueteEntrada().getTra(), GenerateUtil.generarId());

				this.view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());

				JSFUtils.infoMessage("Importar Nomina: ",
						"En este momento se han terminado de importar los archivos DAT TRA");
			} catch (Exception e) {
				JSFUtils.errorMessage("Error: ", e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void importarExcel() throws ValidacionException, EstructuraException {
		boolean eval = true;
		if (view.getPaqueteEntrada().getDat() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getDat().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo EXCEL DAT es requerido, El Archivo debe tener la extención *.xls, *.xlsx");
			eval = false;
		}
		if (view.getPaqueteEntrada().getTra() == null
				|| StringUtils.isEmpty(view.getPaqueteEntrada().getTra().getFileName())) {
			JSFUtils.errorMessage("Archivo Requerido",
					"El Archivo EXCEL TRA es requerido, El Archivo debe tener la extención *.xls, *.xlsx");
			eval = false;
		}

		if (eval) {
			LOGGER.info(view.getPaqueteEntrada().getDat().getFileName());
			LOGGER.info(view.getPaqueteEntrada().getTra().getFileName());

			try {

//				importarNomina.importarNominaTheosToSIIF(this.view.getPaqueteEntrada().getDat(),
//						this.view.getPaqueteEntrada().getTra(), GenerateUtil.generarId());

				importarNomina.importarNominaExcelDatTra(this.view.getPaqueteEntrada().getDat(),
						this.view.getPaqueteEntrada().getTra(), GenerateUtil.generarId());

				this.view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());

				JSFUtils.infoMessage("Importar Nomina Excel: ",
						"En este momento se han terminado de importar los archivos EXCEL");
			} catch (Exception e) {
				JSFUtils.errorMessage("Error: ", e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void mostrarPanelDatTra() {
		this.view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		this.view.setMostrarDatTra(true);
		this.view.setMostrarExcel(false);
	}

	public void mostrarPanelExcel() {
		this.view.setPaqueteEntrada(new PaqueteEntradaFederalDTO());
		this.view.setMostrarDatTra(false);
		this.view.setMostrarExcel(true);
	}

	/**
	 * @return the view
	 */
	public ImportarNominaView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(ImportarNominaView view) {
		this.view = view;
	}

}
