package mx.gob.saludtlax.rh.configuracion.dependencia;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.configuracion.dependencia.Dependencia;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "dependencia")
@ViewScoped
public class DependenciaController {

	private DependenciaView view;
	@Inject
	private Dependencia ejb;
	
	@PostConstruct
	public void initDependencia() {
		view = new DependenciaView();
		irPrincipal();
	}

	public String irPrincipal() {
		view.setListDependencia(ejb.obtenerDependenciaLista());
		view.panelPrincipal();
		return null;
	}

	public String irNuevoDependencia() {
		view.setDependencia(ejb.nuevoDependencia());
		view.setOperacionNuevo(Boolean.TRUE);
		view.panelGestion();
		return null;
	}
	
	public void  eliminarDependencia() {
		ejb.eliminarDependencia(view.getDependenciaSelect());
		view.panelPrincipal();
		irPrincipal();
	}

	public String irGestionarDependencia() {
		view.setDependencia(ejb.obtenerDependencia(view.getDependenciaSelect()));
		view.setOperacionNuevo(Boolean.FALSE);
		view.panelGestion();
		return null;
	}

	public DependenciaView getView() {
		return view;
	}

	public String guardarDependencia() {
		if (view.getOperacionNuevo()) {
			ejb.crearDependencia(view.getDependencia());
		} else {
			ejb.actualizarDependencia(view.getDependencia());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}
	
	public void onRowSelectDependencia(SelectEvent event) {
		view.setDisabledIrGestionar(Boolean.FALSE);
	}

	public void onRowUnselectDependencia(UnselectEvent event) {
		view.setDisabledIrGestionar(Boolean.TRUE);
	}
	
//	> > > > > > Validadores < < < < < <
	
	public void validatorDependencia(FacesContext context, UIComponent component,
			Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "Dependencia":
			String Dependencia = (String) value;

			if (ValidacionUtil.esCadenaVacia(Dependencia)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una Dependencia");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "Sector":
			Integer Sector = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(Sector)) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un Sector");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}else{
				if(Sector>999){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Sector es de un maximo de 3 digitos");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
			}
			break;	
		case "Base36":
			String Base36 = (String) value;

			if (ValidacionUtil.esCadenaVacia(Base36)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Ingrese una Base 36");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}else{
				if(Base36.length()>3){
					FacesMessage facesMessage = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Base 36 es de un maximo de 3 caracteres");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}
			break;
		default:
			break;
		}
	}
	
}