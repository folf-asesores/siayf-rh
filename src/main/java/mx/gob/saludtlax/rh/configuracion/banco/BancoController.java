/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:29:17
 */
@ManagedBean(name = "banco")
@ViewScoped
public class BancoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7484039062574113727L;

	@Inject
	private Banco banco;

	private BancoView view;

	@PostConstruct
	public void init() {
		view = new BancoView();

		obtenerListaBanco();
	}

	public void obtenerListaBanco() {
		try {

			List<BancoDTO> listaBanco = banco.obtenerListaBanco();

			if (!listaBanco.isEmpty()) {
				this.view.setListaBanco(listaBanco);
			} else {
				this.view.setListaBanco(new ArrayList<BancoDTO>());
			}

		} catch (ReglaNegocioException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void accionBanco() {
		try {
			if (this.view.getAccionBanco().equals("Registrar")) {
				banco.crearBanco(this.view.getBancoDTO());
				JSFUtils.infoMessage("Registro Banco: ", "Se realizo correctamente");
			} else if (this.view.getAccionBanco().equals("Actualizar")) {
				banco.actualizarBanco(this.view.getBancoDTO());
				JSFUtils.infoMessage("Actualización Banco: ", "Se realizo correctamente");
			}
			limpiarVista();
		} catch (ReglaNegocioException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void eliminarBanco() {
		try {
			banco.eliminarBanco(this.view.getIdBanco());
			JSFUtils.infoMessage("Eliminación Banco: ", "Se realizo correctamente");
			cerrarDialogoEliminar();
			limpiarVista();
		} catch (ReglaNegocioException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void limpiarVista() {
		this.view.setBancoDTO(new BancoDTO());
		this.view.setAccionBanco("Registrar");
		obtenerListaBanco();
	}

	public void seleccionarBancoActualizacion(BancoDTO bancoDTO) {
		this.view.setBancoDTO(bancoDTO);
		this.view.setAccionBanco("Actualizar");
	}

	public void seleccionaBancoEliminar(Integer idBanco) {
		this.view.setIdBanco(idBanco);
		this.view.setDialogEliminarBanco(true);
	}

	public void cerrarDialogoEliminar() {
		this.view.setAccionBanco("Registrar");
		this.view.setBancoDTO(new BancoDTO());
		this.view.setDialogEliminarBanco(false);
		obtenerListaBanco();
	}

	/***************** Getters and Setters *******************/

	/**
	 * @return the view
	 */
	public BancoView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(BancoView view) {
		this.view = view;
	}

}
