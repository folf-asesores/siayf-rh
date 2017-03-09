package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

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

import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsable;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "unidadResponsable")
@ViewScoped
public class UnidadResponsableController {

	private UnidadResponsableView view;
	@Inject
	private UnidadResponsable ejb;
	
	@PostConstruct
	public void initUnidadResponsable() {
		view = new UnidadResponsableView();
		irPrincipal();
	}

	public String irPrincipal() {
		view.setListUnidadResponsable(ejb.obtenerUnidadResponsableLista());
		view.panelPrincipal();
		return null;
	}

	public String irNuevoUnidadResponsable() {
		view.setUnidadResponsable(ejb.nuevoUnidadResponsable());
		view.setOperacionNuevo(Boolean.TRUE);
		view.panelGestion();
		return null;
	}
	
	public void  eliminarUnidadResponsable() {
		ejb.eliminarUnidadResponsable(view.getUnidadResponsableSelect());
		view.panelPrincipal();
		irPrincipal();
	}

	public String irGestionarUnidadResponsable() {
		view.setUnidadResponsable(ejb.obtenerUnidadResponsable(view.getUnidadResponsableSelect()));
		view.setOperacionNuevo(Boolean.FALSE);
		view.panelGestion();
		return null;
	}

	public UnidadResponsableView getView() {
		return view;
	}

	public String guardarUnidadResponsable() {
		if (view.getOperacionNuevo()) {
			ejb.crearUnidadResponsable(view.getUnidadResponsable());
		} else {
			ejb.actualizarUnidadResponsable(view.getUnidadResponsable());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}
	
	public void onRowSelectUnidadResponsable(SelectEvent event) {
		view.setDisabledIrGestionar(Boolean.FALSE);
	}

	public void onRowUnselectUnidadResponsable(UnselectEvent event) {
		view.setDisabledIrGestionar(Boolean.TRUE);
	}
	
//	> > > > > > Validadores < < < < < <
	
	public void validatorUnidad(FacesContext context, UIComponent component,
			Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "UnidadResponsable":
			String UnidadResponsable = (String) value;

			if (ValidacionUtil.esCadenaVacia(UnidadResponsable)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una Unidad Responsable");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
				break;	
			case "Dependencia":
				Integer Dependencia = (Integer) value;

				if (!ValidacionUtil.esNumeroPositivo(Dependencia)) {
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una Dependencia");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}else{
					if(Dependencia > 999){
						FacesMessage facesMessage1 = new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								"Dependencia es de un maximo de 3 dígitos");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
					}
				}
				break;
			case "UnidadXDependencia":
				Integer UnidadXDependencia = (Integer) value;

				if (!ValidacionUtil.esNumeroPositivo(UnidadXDependencia)) {
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una Unidad por Dependencia");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}else{
					if(UnidadXDependencia > 999){
						FacesMessage facesMessage1 = new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								"Unidad por Dependencia es de un maximo de 3 dígitos");
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