/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "reporteMovimientoEmpleado")
@ViewScoped
public class ReporteMovimientoEmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7864109409853836597L;

	private MovimientoEmpleadoReporteView view;

	@PostConstruct
	public void init() {
		this.view = new MovimientoEmpleadoReporteView();
		this.view.setListaTipoReporte(SelectItemsUtil.listaReporteMovimientoEmpleado());
	}

	/******* Process ********/

	public void descargarExcel() {

		if (this.view.getFechaInicialComisionadoLicencia().after(this.view.getFechaFinalComisionadoLicencia())) {
			JSFUtils.errorMessage("Error: ", "La fecha final no puede ser mayor a la fecha inicial");
		} else {

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

			String nombreReporte = "";

			if (this.view.getTipoReporte().equals(EnumTipoReporteMovimientoEmpleado.COMISIONADO_LICENCIA)) {
				nombreReporte = "comisionado_licencia";
			}

			if (this.view.getTipoReporte().equals(EnumTipoReporteMovimientoEmpleado.CONSENTRADO_ALTA_BAJA)) {
				nombreReporte = "consentrado_alta_baja";
			}

			try {

				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				HttpSession httpSession = request.getSession(false);
				UsuarioDTO usuario = (UsuarioDTO) httpSession
						.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

				String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
						nombreReporte, "TIPO_REPORTE", "xlsx", "ID_TIPO_CONTRATACION",
						String.valueOf(this.view.getIdTipoContratacion()), "FECHA_INICIAL",
						String.valueOf(formatoDelTexto.format(this.view.getFechaInicialComisionadoLicencia())),
						"FECHA_FINAL",
						String.valueOf(formatoDelTexto.format(this.view.getFechaFinalComisionadoLicencia())) };

				AdministradorReportes admintradorReportes = new AdministradorReportes();
				String referencia = admintradorReportes.obtenerReferencia(parametros);

				this.view.setBytes(admintradorReportes.obtenerReporte(referencia));

				if (this.view.getBytes() != null) {
					JSFUtils.descargarArchivo(this.view.getBytes(),
							this.view.getTipoReporte().equals(EnumTipoReporteMovimientoEmpleado.COMISIONADO_LICENCIA)
									? "Comisionado_Licencia" : "Consentrado_Altas_Bajas",
							TipoArchivo.getMIMEType("xlsx"));
				}

			} catch (NullPointerException | IllegalArgumentException | IOException exception) {

				System.err.println(exception.getMessage());
				exception.printStackTrace();

			} catch (ReglaNegocioException reglaNegocioException) {

				reglaNegocioException.printStackTrace();
				JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());

			} catch (ValidacionException validacionException) {

				validacionException.printStackTrace();
				JSFUtils.errorMessage("Error: ", validacionException.getMessage());

			}
		}

	}

	/******* Validators **/

	public void validatorHabilidadPersonal(FacesContext context, UIComponent component, Object value) {
		String nombreComponente = component.getId();
		switch (nombreComponente) {
		case "fechaInicial":
			Date fechaInicial = (Date) value;

			if (fechaInicial == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione la fecha inicial.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		case "fechaFinal":
			Date fechaFinal = (Date) value;

			if (fechaFinal == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione la fecha final.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			JSFUtils.errorMessage("Error: ", "Validar campos..");
			break;
		}
	}

	public void validatorTipoReporte(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String nombreComponente = component.getId();
		switch (nombreComponente) {
		case "tipoReporte":
			String tipoReporte = (String) value;

			if (tipoReporte == null) {

				this.view.setMostrarPanelDescargaReporte(false);
				this.view.setFechaFinalComisionadoLicencia(null);
				this.view.setFechaInicialComisionadoLicencia(null);
				this.view.setTipoReporte("");

				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione el tipo de reporte.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			if (ValidacionUtil.esCadenaVacia(tipoReporte)) {

				this.view.setMostrarPanelDescargaReporte(false);
				this.view.setFechaFinalComisionadoLicencia(null);
				this.view.setFechaInicialComisionadoLicencia(null);
				this.view.setTipoReporte("");

				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione el tipo de reporte.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		default:
			JSFUtils.errorMessage("Error: ", "Validar campos..");
			break;
		}
	}

	/****** Renderes *****/
	public void mostrarPanelDescarga() {
		this.view.setMostrarPanelDescargaReporte(false);
		this.view.setFechaFinalComisionadoLicencia(null);
		this.view.setFechaInicialComisionadoLicencia(null);
		this.view.setMostrarSelectTipoContratacion(false);
		this.view.setIdTipoContratacion(0);

		if (!ValidacionUtil.esCadenaVacia(this.view.getTipoReporte())) {
			this.view.setMostrarPanelDescargaReporte(true);
			if (this.view.getTipoReporte().equals("Consentrado de Altas y Bajas")) {
				this.view.setMostrarSelectTipoContratacion(true);
			}
		}

	}

	/****** Getters and Setters *******/

	/**
	 * @return the view
	 */
	public MovimientoEmpleadoReporteView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(MovimientoEmpleadoReporteView view) {
		this.view = view;
	}

}
