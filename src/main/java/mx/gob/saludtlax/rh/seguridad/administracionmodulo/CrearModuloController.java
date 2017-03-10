/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.administracionmodulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.areas.Areas;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "crearModulo")
@ViewScoped
public class CrearModuloController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773234668768784817L;

	@Inject
	private Modulos modulos;
	@Inject
	private Areas areaEJB;

	private CrearModuloView view;

	private List<AccionDTO> acciones = new ArrayList<AccionDTO>();

	@PostConstruct
	public void init() {
		vistaPrincipal();
	}

	public void vistaPrincipal() {

		this.view = new CrearModuloView();

		this.acciones = new ArrayList<AccionDTO>();

		List<AreaDTO> listaAreas = areaEJB.obtenerAreas();

		if (!listaAreas.isEmpty()) {
			this.view.setListaAreas(listaAreas);
		}
	}

	public void registrarModulo() {

		try {
			ModuloDTO moduloDTO = this.view.getCrearModulo();

			moduloDTO.setAcciones(acciones);

			modulos.crearModulo(moduloDTO);
			vistaPrincipal();
			JSFUtils.infoMessage("Registrar Modulo: ", "¡El registro se realizo correctamente!");

		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}

	}

	public void validatorModulo(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "nombreModulo":
			String nombre = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombre)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un nombre.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "url":
			String descripcionArea = (String) value;

			if (ValidacionUtil.esCadenaVacia(descripcionArea)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese url.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "idArea":
			Integer idPeriodoCalendario = (Integer) value;
			if (!ValidacionUtil.esNumeroPositivo(idPeriodoCalendario)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seleccione una area.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		}
	}

	public void validatorAccion(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "claveAccion":
			String claveAccion = (String) value;

			if (ValidacionUtil.esCadenaVacia(claveAccion)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una clave.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "descripcion":
			String descripcion = (String) value;

			if (ValidacionUtil.esCadenaVacia(descripcion)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese descripcion.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}

		}
	}

	public void onRowEdit(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Actualizado: ", ((AccionDTO) event.getObject()).getClave());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion Cancelada: ", ((AccionDTO) event.getObject()).getClave());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void eliminarAccionDeLaLista(AccionDTO accion) {

		this.acciones.remove(accion);

		JSFUtils.infoMessage("Eliminar Acción: ", "¡Se elimino correctamente!");
	}

	public void registrarAccion() {
		// Copia
		List<AccionDTO> listaAcciones = new ArrayList<AccionDTO>();

		listaAcciones.addAll(this.acciones);

		listaAcciones.add(this.view.getAccion());

		// limpia
		this.acciones.clear();

		this.acciones.addAll(listaAcciones);

		cerrarFormularioAccion();

		JSFUtils.infoMessage("Agregar Acción: ", "¡Se agrego correctamente!");

	}

	public void mostrarFormularioAccion() {
		this.view.setAccion(new AccionDTO());
		this.view.setMostrarTablaAccciones(false);
		this.view.setMostrarFormularioAccion(true);
	}

	public void cerrarFormularioAccion() {
		this.view.setAccion(new AccionDTO());
		this.view.setMostrarTablaAccciones(true);
		this.view.setMostrarFormularioAccion(false);
	}

	/************ Getters and Setters ****************/
	/**
	 * @return the view
	 */
	public CrearModuloView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(CrearModuloView view) {
		this.view = view;
	}

	/**
	 * @return the acciones
	 */
	public List<AccionDTO> getAcciones() {
		return acciones;
	}

	/**
	 * @param acciones
	 *            the acciones to set
	 */
	public void setAcciones(List<AccionDTO> acciones) {
		this.acciones = acciones;
	}

}
