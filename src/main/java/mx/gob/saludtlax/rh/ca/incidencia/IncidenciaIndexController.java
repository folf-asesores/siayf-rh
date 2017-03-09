package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "incidenciaIndexController")
@ViewScoped
public class IncidenciaIndexController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7316476586962771878L;
	@Inject
	IncidenciaClienteRest incidenciaClienteRest;
	@Inject
	ServiciosWebEJB serviocWebEJB;

	private List<IncidenciaModelView> listadoIncidencias;

	private List<IncidenciaModelView> listadoIncidenciasFiltrado;

	private IncidenciaModelView incidenciaSelecionada;

	private IncidenciaFormModel incidenciaFormModel = new IncidenciaFormModel();

	private UploadedFile imagen;

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

		try {
			listadoIncidencias = incidenciaClienteRest.listadoIncidencias();
		} catch (RESTClientException e) {

			e.printStackTrace();
			JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
		}

	}

	public void nuevaIncidencia(ActionEvent actionEvent) {

		incidenciaFormModel = new IncidenciaFormModel();
		incidenciaFormModel.setEsImagen(1);
		imagen = null;
		RequestContext.getCurrentInstance().execute("PF('dlgNuevaIncidencia').show()");

	}

	public void seleccionarTipoMarca() {

	}

	public void guardarNuevaIncidencia() throws IOException {

		try {

			if (incidenciaFormModel.getDescripcion().length() == 0) {
				JSFUtils.errorMessage("Descripción", ListadoMensajesSistema.CAIV001.getMensaje());
				return;
			}

			if (incidenciaFormModel.getEsImagen() == 1) {
				if (imagen == null) {

					JSFUtils.errorMessage("Imagen", ListadoMensajesSistema.CAIV002.getMensaje());
					return;

				} else {

					if (!imagen.getFileName().matches("(.*).png")) {

					}

					if (imagen.getSize() > 254000) {
						JSFUtils.errorMessage("Imagen", ListadoMensajesSistema.CAIV005.getMensaje());
						return;
					}
				}

				incidenciaFormModel.setImagenMarca(imagen.getInputstream());
			} else {
				if (incidenciaFormModel.getMarcaReporte().length() == 0) {
					JSFUtils.errorMessage("Descripción", ListadoMensajesSistema.CAIV003.getMensaje());
					return;
				}
			}

			incidenciaClienteRest.guardarIncidencia(incidenciaFormModel);
			listadoIncidencias = incidenciaClienteRest.listadoIncidencias();
			incidenciaFormModel = new IncidenciaFormModel();
			imagen = null;
			RequestContext.getCurrentInstance().execute("PF('dlgNuevaIncidencia').hide()");
			JSFUtils.infoMessage("Incidencia", ListadoMensajesSistema.CA001.getMensaje());
		} catch (RESTClientException e) {
			e.printStackTrace();
			JSFUtils.errorMessage("Guardar Incidencia", e.getMessage());
		}
	}

	public void editarIncidencia() throws IOException {

		incidenciaFormModel = new IncidenciaFormModel();

		incidenciaFormModel.setDescripcion(incidenciaSelecionada.getDescripcion());
		incidenciaFormModel.setEsImagen(incidenciaSelecionada.getEsImagen());
		incidenciaFormModel.setIdIncidencia(incidenciaSelecionada.getIdIncidencia());
		incidenciaFormModel.setMarcaReporte(incidenciaSelecionada.getMarcaReporte());
		incidenciaFormModel.setTipoRegistro(incidenciaSelecionada.getIdTipoRegistro());
		try {

			if (incidenciaFormModel.getDescripcion().length() == 0) {
				JSFUtils.errorMessage("Descripción", ListadoMensajesSistema.CAIV001.getMensaje());
				return;
			}

			if (incidenciaFormModel.getEsImagen() == 1) {
				if (imagen != null) {

					incidenciaFormModel.setImagenMarca(imagen.getInputstream());

				}

			} else {
				if (incidenciaFormModel.getMarcaReporte().length() == 0) {
					JSFUtils.errorMessage("Descripción", ListadoMensajesSistema.CAIV003.getMensaje());
					return;
				}
			}

			incidenciaClienteRest.actualizarIncidencia(incidenciaFormModel);
			listadoIncidencias = incidenciaClienteRest.listadoIncidencias();
			incidenciaFormModel = new IncidenciaFormModel();
			imagen = null;
			RequestContext.getCurrentInstance().execute("PF('dlgEditarIncidencia').hide()");
			JSFUtils.infoMessage("Incidencia", ListadoMensajesSistema.CA001.getMensaje());
		} catch (

		RESTClientException e) {
			e.printStackTrace();
			JSFUtils.errorMessage("Guardar Incidencia", e.getMessage());
		}
	}

	public List<IncidenciaModelView> getListadoIncidencias() {
		return listadoIncidencias;
	}

	public void setListadoIncidencias(List<IncidenciaModelView> listadoIncidencias) {
		this.listadoIncidencias = listadoIncidencias;
	}

	public String rutaImagen() {
		return incidenciaClienteRest.rutaImangen();
	}

	public IncidenciaFormModel getIncidenciaFormModel() {
		return incidenciaFormModel;
	}

	public void setIncidenciaFormModel(IncidenciaFormModel incidenciaFormModel) {
		this.incidenciaFormModel = incidenciaFormModel;
	}

	public UploadedFile getImagen() {
		return imagen;
	}

	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {
			incidenciaFormModel.setImagenMarca(event.getFile().getInputstream());
			incidenciaFormModel.setMarcaReporte(event.getFile().getFileName());
			imagen = event.getFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public List<IncidenciaModelView> getListadoIncidenciasFiltrado() {
		return listadoIncidenciasFiltrado;
	}

	public void setListadoIncidenciasFiltrado(List<IncidenciaModelView> listadoIncidenciasFiltrado) {
		this.listadoIncidenciasFiltrado = listadoIncidenciasFiltrado;
	}

	public IncidenciaModelView getIncidenciaSelecionada() {
		return incidenciaSelecionada;
	}

	public void setIncidenciaSelecionada(IncidenciaModelView incidenciaSelecionada) {
		this.incidenciaSelecionada = incidenciaSelecionada;
	}

}
