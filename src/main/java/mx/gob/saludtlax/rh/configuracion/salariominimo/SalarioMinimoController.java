/*
*
 * 
 */
package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "salarioMinimo")
@ViewScoped
public class SalarioMinimoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8412051015471358991L;

	@Inject
	private SalarioMinimo salarioMinimo;

	private SalarioMinimoView view;

	@PostConstruct
	private void init() {
		this.view = new SalarioMinimoView();

		obtenerListaSalarioMinimo();
	}

	/**
	 * Obteniendo la lista de salarios minimos registrados en la bd
	 */
	public void obtenerListaSalarioMinimo() {
		List<SalarioMinimoDTO> lista = salarioMinimo.obtenerListaSalarioMinimo();

		this.view.setListaSalariosMinimos(lista);
	}

	public void crearSalarioMinimo() {
		try {
			salarioMinimo.crearSalarioMinimo(this.view.getCreaSalarioMinimo());
			JSFUtils.infoMessage("Registro Salario Minimo: ", "Se realizo correctamente.");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

	public void actualizarSalarioMinimo() {
		try {
			salarioMinimo.actualizarSalarioMinimo(this.view.getActualizarSalarioMinimo());
			JSFUtils.infoMessage("Actualización Salario Minimo: ", "Se realizo correctamente.");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

	public void eliminarSalarioMinimo() {
		try {
			salarioMinimo.eliminarSalarioMinimo(this.view.getIdSalarioMinimoSeleccionado());
			JSFUtils.infoMessage("Eliminación Salario Minimo: ", "Se realizo correctamente.");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

	public void mostrarNuevoRegistro() {
		this.view.setCreaSalarioMinimo(new SalarioMinimoDTO());
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(true);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(false);
	}

	public void mostrarActualizacion(SalarioMinimoDTO dto) {
		this.view.setActualizarSalarioMinimo(dto);
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(true);
		this.view.setDialogEliminar(false);
	}

	public void mostrarDialogEliminar(Integer idSalarioMinimo) {
		this.view.setIdSalarioMinimoSeleccionado(idSalarioMinimo);
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(true);
	}

	public void mostrarPrincipal() {
		this.view.setIdSalarioMinimoSeleccionado(0);
		this.view.setActualizarSalarioMinimo(new SalarioMinimoDTO());
		this.view.setCreaSalarioMinimo(new SalarioMinimoDTO());
		this.view.setPanelPrincipal(true);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(false);
		obtenerListaSalarioMinimo();
	}

	public void regresarModulo() throws IOException {
		JSFUtils.redireccionar("/siayf-rh/contenido/configuracion/salariosMinimos.xhtml?faces-redirect=true");
	}

	public SalarioMinimoView getView() {
		return view;
	}

	public void setView(SalarioMinimoView view) {
		this.view = view;
	}

}
