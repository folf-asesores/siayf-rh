package mx.gob.saludtlax.rh.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

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
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean
@ViewScoped
public class AreasController implements Serializable {

	private static final long serialVersionUID = 1708501946095782317L;

	@Inject
	Areas areas;
	
	private List<AreaDTO> listaAreas = new ArrayList<>();
	private AreaDTO areaSeleccionada;
	private AreaDTO areaNew = new AreaDTO();
	
	@PostConstruct
	public void inicio(){
		List<AreaDTO> areasTemp = new ArrayList<>();
		areasTemp = areas.obtenerAreas();
		listaAreas.clear();
		listaAreas.addAll(areasTemp);
	}
	
	public void validatorArea(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "nombreArea":
			String nombre = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombre)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un nombre.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "descArea":
			String descripcionArea = (String) value;

			if (ValidacionUtil.esCadenaVacia(descripcionArea)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese descripcion.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "titularArea":
			String titular = (String) value;

			if (ValidacionUtil.esCadenaVacia(titular)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el nombre del titular.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		}
	}
	
	public void agregarArea(){
		areas.crearArea(areaNew);
		JSFUtils.infoMessage("Exito:", "El area se creo correctamente.");
		inicio();
		areaNew = new AreaDTO();
	}
	
	
	public void onRowEdit(RowEditEvent event) {

		try {

			AreaDTO area = ((AreaDTO) event.getObject());
			areas.editarArea(area);
			
			FacesMessage msg = new FacesMessage("Actualizado:",
					((AreaDTO) event.getObject()).getNombreArea());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}

		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion Cancelada:",
				((AreaDTO) event.getObject()).getNombreArea());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
	public void eliminarArea(){
	Boolean res=areas.eliminarArea(areaSeleccionada.getIdArea());
	
	System.out.println(res);
	 if(!res){
		 JSFUtils.warningMessage("","EL registro de Area no se puede eliminar ya que se encuentra usado por configuraciones de acciones o modulos.");
	 }
		inicio();
	
	}
	

	public List<AreaDTO> getListaAreas() {
		return listaAreas;
	}


	public void setListaAreas(List<AreaDTO> listaAreas) {
		this.listaAreas = listaAreas;
	}


	public AreaDTO getAreaSeleccionada() {
		return areaSeleccionada;
	}


	public void setAreaSeleccionada(AreaDTO areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}


	public AreaDTO getAreaNew() {
		return areaNew;
	}


	public void setAreaNew(AreaDTO areaNew) {
		this.areaNew = areaNew;
	}
	

	
	
}
