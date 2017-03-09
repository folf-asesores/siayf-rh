package mx.gob.saludtlax.rh.configuracion.serviciosweb;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "serviciosWebController")
@ViewScoped
public class ServiciosWebController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8314162205506136096L;

	@Inject
	ServiciosWebEJB serviciosWebEJB;

	private ServiciosRSEntity servicioRSEntity = new ServiciosRSEntity();

	private List<ServiciosRSEntity> listadoServiciosRSEntity;

	@PostConstruct
	public void init() {
		listadoServiciosRSEntity = serviciosWebEJB.obtenerListadoServicios();
	}

	public List<ServiciosRSEntity> getListadoServiciosRSEntity() {
		return listadoServiciosRSEntity;
	}

	public void setListadoServiciosRSEntity(List<ServiciosRSEntity> listadoServiciosRSEntity) {
		this.listadoServiciosRSEntity = listadoServiciosRSEntity;
	}

	public ServiciosRSEntity getServicioRSEntity() {
		return servicioRSEntity;
	}

	public String nuevoServicio() {
		try {
			serviciosWebEJB.guardarInformacionServicio(servicioRSEntity);
		} catch (ServicioWebException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage("info", facesMessage);
			return "";
		}
		return "index.xhtml?faces-redirect=true";

	}

	public String editar(Integer id) {

		return "editar.xhtml?faces-redirect=true&includeViewParams=true&id=" + id;
	}

	public void setServicioRSEntity(ServiciosRSEntity servicioRSEntity) {
		this.servicioRSEntity = servicioRSEntity;
	}

	public ServicioWebEnum[] getServicios() {
		return ServicioWebEnum.values();
	}

	public void validarFormulario(FacesContext context, UIComponent component, Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "url":
			Pattern pat = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
			String url = (String) value;
			if (url == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"El campo url es obligatorio");

				throw new ValidatorException(facesMessage);

			}
			
			Matcher mat = pat.matcher(url);
			if (!mat.matches()) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Ingrese una url valida http:// o https://.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		}

	}

}
