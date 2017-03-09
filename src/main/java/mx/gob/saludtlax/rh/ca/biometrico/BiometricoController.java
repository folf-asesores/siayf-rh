package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "biometricoIndexController")
@ViewScoped
public class BiometricoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7782539103219364042L;

	@Inject
	BiometricoClientRest biometricoClientRest;
	@Inject
	ServiciosWebEJB servicioWebEJB;

	private List<BiometricoFormModel> listadoEquiposBiometricos;

	private BiometricoFormModel equipoBiometricoSeleccionado;

	public void init() {

		try {
			ServiciosRSEntity servicioRSEntity = servicioWebEJB.getServicioActivo(ServicioWebEnum.RELOJ_CHECADOR);
			if (!servicioRSEntity.isProduccion()) {
				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				String url = req.getContextPath().toString();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
						"El servcio configurado como activo para este modulo es de pruebas consulte la <a href='" + url
								+ "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);

			}

		} catch (ServicioWebException e1) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), e1.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}

		try {
			listadoEquiposBiometricos = biometricoClientRest.listadoEquiposBiometricos();

		} catch (RESTClientException e) {

			e.printStackTrace();
			JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
		}

	}

	public List<BiometricoFormModel> getListadoEquiposBiometricos() {
		return listadoEquiposBiometricos;
	}

	public void setListadoEquiposBiometricos(List<BiometricoFormModel> listadoEquiposBiometricos) {
		this.listadoEquiposBiometricos = listadoEquiposBiometricos;
	}

	public BiometricoFormModel getEquipoBiometricoSeleccionado() {
		return equipoBiometricoSeleccionado;
	}

	public void setEquipoBiometricoSeleccionado(BiometricoFormModel equipoBiometricoSeleccionado) {
		this.equipoBiometricoSeleccionado = equipoBiometricoSeleccionado;
	}

}
