package mx.gob.saludtlax.rh.modulos;
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

import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.areas.Areas;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name ="modulosController")
@ViewScoped
public class ModulosController implements Serializable {

	private static final long serialVersionUID = 1708501946095782317L;

	@Inject
	Modulos modulos;
	@Inject
	Areas areaEJB;

	private List<ModuloDTO> listaModulos = new ArrayList<>();
	private List<AreaDTO> listaAreas = new ArrayList<>();
	private ModuloDTO moduloSeleccionado;
	private ModuloDTO moduloNew = new ModuloDTO();

	private Boolean habilitarPanelEdicion = false;
	private Boolean habilitarTablaPrincipal = true;

	@PostConstruct
	public void inicio(){
		List<ModuloDTO> moduloTemp = new ArrayList<>();
		moduloTemp = modulos.listaModulos();
		listaModulos.clear();
		listaModulos.addAll(moduloTemp);
		List<AreaDTO> list= areaEJB.obtenerAreas();
		listaAreas.clear();
		listaAreas.addAll(list);
	}

	public void validatorModulo(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

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
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione una area.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		}
	}

	public void habilitarPanelEdicion(ModuloDTO moduloSelect){
		habilitarPanelEdicion = true;
		habilitarTablaPrincipal = false;
		moduloSeleccionado = new ModuloDTO();
		moduloSeleccionado = moduloSelect;
	}

	public void regresarPrincipal(){
		habilitarPanelEdicion = false;
		habilitarTablaPrincipal = true;
	}

	public void agregarModulo(){
		modulos.crearModulo(moduloNew);
		inicio();
		moduloNew = new ModuloDTO();
	}

	public void onRowEdit(RowEditEvent event) {

		try {

			ModuloDTO modulo = ((ModuloDTO) event.getObject());
			modulos.editarModulo(modulo);

			FacesMessage msg = new FacesMessage("Actualizado:",
					((ModuloDTO) event.getObject()).getNombre());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}


	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion Cancelada:",
				((ModuloDTO) event.getObject()).getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void eliminarModulo(){
	 Boolean res =modulos.eliminarModulo(moduloSeleccionado.getIdModulo());
	 if(!res){
		 JSFUtils.warningMessage("","EL registro de Modulo no se puede eliminar ya que se encuentra usado por configuraciones de modulos.");
	 }
		inicio();
	}

	public List<ModuloDTO> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<ModuloDTO> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public ModuloDTO getModuloSeleccionado() {
		return moduloSeleccionado;
	}

	public void setModuloSeleccionado(ModuloDTO moduloSeleccionado) {
		this.moduloSeleccionado = moduloSeleccionado;
	}

	public ModuloDTO getModuloNew() {
		return moduloNew;
	}

	public void setModuloNew(ModuloDTO moduloNew) {
		this.moduloNew = moduloNew;
	}

	public List<AreaDTO> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<AreaDTO> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public Boolean getHabilitarPanelEdicion() {
		return habilitarPanelEdicion;
	}

	public void setHabilitarPanelEdicion(Boolean habilitarPanelEdicion) {
		this.habilitarPanelEdicion = habilitarPanelEdicion;
	}

	public Boolean getHabilitarTablaPrincipal() {
		return habilitarTablaPrincipal;
	}

	public void setHabilitarTablaPrincipal(Boolean habilitarTablaPrincipal) {
		this.habilitarTablaPrincipal = habilitarTablaPrincipal;
	}


}
