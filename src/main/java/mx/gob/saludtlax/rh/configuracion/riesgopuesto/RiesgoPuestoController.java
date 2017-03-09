/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 19:21:12
 */
@ManagedBean(name = "riesgoPuesto")
@ViewScoped
public class RiesgoPuestoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1545175823479673131L;

	@Inject
	private RiesgoPuesto riesgoPuesto;

	private RiesgoPuestoView view;

	@PostConstruct
	private void init() {
		view = new RiesgoPuestoView();
		obtenerListaRiesgoPuesto();
	}

	public void obtenerListaRiesgoPuesto() {

		List<RiesgoPuestoDTO> obtenerLista = riesgoPuesto.obtenerListaRiesgoPuesto();

		if (!obtenerLista.isEmpty()) {
			this.view.setObtenerListaPuestoDTOs(obtenerLista);
		} else {
			this.view.setObtenerListaPuestoDTOs(new ArrayList<RiesgoPuestoDTO>());
		}
	}

	public void accionRiesgoPuesto() {
		try {
			if (this.view.getAccionRiesgoPuesto().equals("Registrar")) {
				riesgoPuesto.crearRiesgoPuesto(this.view.getRiesgoPuestoDTO());
				JSFUtils.infoMessage("Registro Riesgo Puesto: ", "Se realizo correctamente");
				limpiarVista();
			} else if (this.view.getAccionRiesgoPuesto().equals("Actualizar")) {
				riesgoPuesto.actualizarRiesgoPuesto(this.view.getRiesgoPuestoDTO());
				JSFUtils.infoMessage("Actualización Riesgo Puesto: ", "Se realizo correctamente");
				limpiarVista();
			}
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}

	}

	public void eliminarRiesgoPuesto() {
		try {
			riesgoPuesto.eliminarRiesgoPuesto(this.view.getIdRiesgoPuesto());
			JSFUtils.infoMessage("Eliminación Riesgo Puesto: ", "Se realizo correctamente");
			cerrarDialogEliminar();
			limpiarVista();
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void seleccionarRiesgoPuesto(RiesgoPuestoDTO dto) {
		this.view.setRiesgoPuestoDTO(dto);
		this.view.setAccionRiesgoPuesto("Actualizar");
	}

	public void seleccionarRiesgoEliminar(Integer idRiesgoPuesto) {
		this.view.setIdRiesgoPuesto(idRiesgoPuesto);
		this.view.setDialogRiesgoPuestoEliminar(true);
	}

	public void cerrarDialogEliminar() {
		this.view.setAccionRiesgoPuesto("Registrar");
		this.view.setRiesgoPuestoDTO(new RiesgoPuestoDTO());
		this.view.setDialogRiesgoPuestoEliminar(false);
		obtenerListaRiesgoPuesto();
	}

	public void limpiarVista() {
		this.view.setAccionRiesgoPuesto("Registrar");
		this.view.setRiesgoPuestoDTO(new RiesgoPuestoDTO());
		obtenerListaRiesgoPuesto();
	}

	/********************** Getters and Setters *************************/
	/**
	 * @return the view
	 */
	public RiesgoPuestoView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(RiesgoPuestoView view) {
		this.view = view;
	}

}
