package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.ca.empleado.EmpleadoClientRest;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "descargarInformacionEmpleadoControlller")
@ViewScoped
public class DescargarInformacionEmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1624466449869171143L;

	@Inject
	ServiciosWebEJB serviocWebEJB;

	@Inject
	Empleado empleadoService;

	@Inject
	EmpleadoClientRest empleadoClienteRest;

	@Inject
	BiometricoClientRest biometricoClientRest;

	private List<BiometricoFormModel> listadoEquiposBiometricos;

	private InfoEmpleadoDTO empleadoDTO;

	private AsignarEmpleadoRegistroBiometricoForm asignarEmpleadoRegistroBiometricoForm;

	public void init() {

		try {
			ServiciosRSEntity servicioRSEntity = serviocWebEJB.getServicioActivo(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
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
		if (!FacesContext.getCurrentInstance().isPostback()) {
			try {
				asignarEmpleadoRegistroBiometricoForm = new AsignarEmpleadoRegistroBiometricoForm();
				listadoEquiposBiometricos = biometricoClientRest.listadoEquiposBiometricos();

			} catch (RESTClientException e) {

				e.printStackTrace();
				JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
			}
		}

	}

	public List<InfoEmpleadoDTO> buscarEmpleadoAutoComplete(String query) {

		List<InfoEmpleadoDTO> listadoEmpleadoDTO = null;

		if (query == "") {
			query = ".";
		}

		if (query.length() > 4) {
			listadoEmpleadoDTO = empleadoService.consultaPorCriterio(query);
		}

		return listadoEmpleadoDTO;

	}

	public void descagarInformacionEmpleadoBiometrico() {

		try {
			
			//System.out.println("Descarga info: " + asignarEmpleadoRegistroBiometricoForm.getIdBiometrico()+"-- "+ asignarEmpleadoRegistroBiometricoForm.getIdEmpleado()+"--"+asignarEmpleadoRegistroBiometricoForm.getIdRegistroBiometrico());
			BiometricoClientRestResponse response = biometricoClientRest
					.asignarEmpleadoIdBiometrico(asignarEmpleadoRegistroBiometricoForm);

			if (response.isExitoso()) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Descargar Informacion",
						response.getMensaje());
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			} else {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descargar Informacion",
						response.getMensaje());
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
		} catch (RESTClientException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}

	public List<BiometricoFormModel> getListadoEquiposBiometricos() {
		return listadoEquiposBiometricos;
	}

	public void setListadoEquiposBiometricos(List<BiometricoFormModel> listadoEquiposBiometricos) {
		this.listadoEquiposBiometricos = listadoEquiposBiometricos;
	}

	public InfoEmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}

	public AsignarEmpleadoRegistroBiometricoForm getAsignarEmpleadoRegistroBiometricoForm() {
		return asignarEmpleadoRegistroBiometricoForm;
	}

	public void setAsignarEmpleadoRegistroBiometricoForm(
			AsignarEmpleadoRegistroBiometricoForm asignarEmpleadoRegistroBiometricoForm) {
		this.asignarEmpleadoRegistroBiometricoForm = asignarEmpleadoRegistroBiometricoForm;
	}

}
