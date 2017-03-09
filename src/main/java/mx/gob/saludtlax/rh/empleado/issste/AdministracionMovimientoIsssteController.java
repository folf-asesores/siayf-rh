package mx.gob.saludtlax.rh.empleado.issste;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleado.issste.AdministracionMovimientsIsssteView;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "movimientosIssste")
@ViewScoped
public class AdministracionMovimientoIsssteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Catalogo catalogo;
	@Inject
	private Empleado empleado;
	@Inject
	private Issste issste;

	private AdministracionMovimientsIsssteView view;

	@PostConstruct
	public void inicio() {
		this.view = new AdministracionMovimientsIsssteView();

		this.view.setListaCausaBaja(SelectItemsUtil.listaCatalogos(catalogo.obtenerListaCausaBajaIssste()));
		this.view.setListaNombramiento(SelectItemsUtil.listaCatalogos(catalogo.obtenerListaTipoNombramiento()));
		this.view.setListaNivelSalario(SelectItemsUtil.listaCatalogos(catalogo.obtenerListaNivelSalarial()));
	}

	public void consultarEmpleados() {
		try {
			
			this.view.setListaEmpleados(empleado.empleadosPorCriterioConNombramiento(this.view.getCriterio()));

			if (this.view.getListaEmpleados().isEmpty()) {
				JSFUtils.infoMessageEspecifico("info", "",
						"No se encontrarón registros en el criterio" + this.view.getCriterio());
			}
			this.view.setVentanaNuevoReporte(false);
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("Error: ", validatorException.getMessage());
		}
	}

	public void consultarMovimiento() {
		try {
			this.view.setListaMovimiento(issste.obtenerListaMovimientoPorCriterio(this.view.getCriterio()));

			if (this.view.getListaMovimiento().isEmpty()) {
				JSFUtils.infoMessageEspecifico("info", "",
						"No se encontrarón registros en el criterio" + this.view.getCriterio());
			}
			
			this.view.setVentanaNuevoReporte(false);
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("Error: ", validatorException.getMessage());
		}
	}

	public void seleccionarEmpleado(InfoEmpleadoDTO empleadoSeleccionar) {

		if (issste.existeEmpleado(empleadoSeleccionar.getIdEmpleado())) {
			this.view.setVentanaNuevoReporte(false);
			JSFUtils.errorMessage("Seleccionar Empleado: ", "El empleado ya se encuentra registrado");
		} else {
			this.view.setIdEmpleado(empleadoSeleccionar.getIdEmpleado());
			this.view.setEmpleadoSeleccionado(empleadoSeleccionar);
			this.view.setFormularioAltaTrabajador(true);
			this.view.getAltaTrabajadorDTO().setSueldoIssste(empleadoSeleccionar.getSueldoActualEmpleado());
			this.view.setVentanaNuevoReporte(false);
		}
	}

	public void verFormatoAltas() throws IOException {
		try {
			this.view.setAdministracionMovimientos(false);
			this.view.setAltaTrabajador(false);
			this.view.setBajaIssste(true);
			this.view.setSueldoTrabajador(true);

		} catch (NullPointerException | IllegalArgumentException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void altaTrabajador() {
		try {

			MovimientoIsssteEmpleadoDTO dto = this.view.getAltaTrabajadorDTO();

			dto.setIdEmpleado(this.view.getIdEmpleado());
			dto.setIdUsuario(this.obtenerIdUsuarioSession());

			Integer idMovimiento = issste.altaIsssteMovimiento(dto);

			if (ValidacionUtil.esNumeroPositivoInt(idMovimiento)) {

				this.view.setUrlReporte("AdministracionMovimientoIsssteServlet?" + "idMovimientoIssste=" + idMovimiento
						+ "&idAccionMovimientoIssste=" + EnumAccionMovimientoIssste.VISUALIZAR
						+ "&idTipoMovimientoIssste=" + EnumTipoMovimientoIssste.ALTA_TRABAJADOR);

				this.view.setVentanaNuevoReporte(true);

				JSFUtils.infoMessage("Alta Trabajador: ", "¡Se registro correctamente!");
				mostrarPrincipal();
				
			}

		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void modificarSueldo() {
		try {

			issste.modificacionIsssteMovimiento(this.view.getModificacionTrabajadorDTO());

			this.view.setUrlReporte("AdministracionMovimientoIsssteServlet?" + "idMovimientoIssste="
					+ this.view.getModificacionTrabajadorDTO().getIdMovimientoIsssteEmpleado()
					+ "&idAccionMovimientoIssste=" + EnumAccionMovimientoIssste.VISUALIZAR + "&idTipoMovimientoIssste="
					+ EnumTipoMovimientoIssste.MODIFICACIÓN_SUELDO);

			this.view.setVentanaNuevoReporte(true);

			JSFUtils.infoMessage("Modificación Sueldo Trabajador: ", "¡Se modifico correctamente!");
			mostrarPrincipal();

		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void bajaTrabajador() {
		try {
			issste.bajaIsssteMovimiento(this.view.getBajaTrabajadorDTO());

			this.view.setUrlReporte("AdministracionMovimientoIsssteServlet?" + "idMovimientoIssste="
					+ this.view.getBajaTrabajadorDTO().getIdMovimientoIsssteEmpleado() + "&idAccionMovimientoIssste="
					+ EnumAccionMovimientoIssste.VISUALIZAR + "&idTipoMovimientoIssste="
					+ EnumTipoMovimientoIssste.BAJA_ISSSTE);
			this.view.setVentanaNuevoReporte(true);

			JSFUtils.infoMessage("Baja Trabajador: ", "¡Se dio de baja correctamente!");
			mostrarPrincipal();
		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void descargarMovimientoIssste(InfoMovimientoIsssteDTO infoMovimientoIsssteDTO) {

		this.view.setUrlReporte("AdministracionMovimientoIsssteServlet?" + "idMovimientoIssste="
				+ infoMovimientoIsssteDTO.getIdMovimientoIsssteEmpleado() + "&idAccionMovimientoIssste="
				+ EnumAccionMovimientoIssste.DESCARGAR + "&idTipoMovimientoIssste="
				+ infoMovimientoIsssteDTO.getIdTipoMovimientoIssste());
		this.view.setVentanaNuevoReporte(true);

	}

	public void mostrarPanelAlta() {
		this.view.setCriterio("");
		this.view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
		this.view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
		this.view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
		this.view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
		this.view.setAdministracionMovimientos(false);
		this.view.setAltaTrabajador(true);
		this.view.setFormularioAltaTrabajador(false);
		this.view.setBajaIssste(false);
		this.view.setSueldoTrabajador(false);
		this.view.setVentanaNuevoReporte(false);
	}

	public void mostrarPrincipal() {
		this.view.setCriterio("");
		this.view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
		this.view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
		this.view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
		this.view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
		this.view.setListaMovimiento(new ArrayList<InfoMovimientoIsssteDTO>());
		this.view.setAdministracionMovimientos(true);
		this.view.setAltaTrabajador(false);
		this.view.setFormularioAltaTrabajador(false);
		this.view.setBajaIssste(false);
		this.view.setSueldoTrabajador(false);
		this.view.setOpcionDisponibles(true);
//		this.view.setVentanaNuevoReporte(false);
	}

	public void mostrarPanelModificacion() {

		this.view.getModificacionTrabajadorDTO()
				.setIdMovimientoIsssteEmpleado(this.view.getMovimientoSeleccinado().getIdMovimientoIsssteEmpleado());
		this.view.getModificacionTrabajadorDTO()
				.setSueldoIssste(this.view.getMovimientoSeleccinado().getSueldoIssste());
		this.view.getModificacionTrabajadorDTO().setSueldoSar(this.view.getMovimientoSeleccinado().getSueldoSar());
		this.view.getModificacionTrabajadorDTO()
				.setTotalRemuneracion(this.view.getMovimientoSeleccinado().getTotalRemuneracion());

		this.view.setCriterio("");
		this.view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
		this.view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
		this.view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
		this.view.setAdministracionMovimientos(false);
		this.view.setAltaTrabajador(false);
		this.view.setFormularioAltaTrabajador(false);
		this.view.setBajaIssste(false);
		this.view.setSueldoTrabajador(true);
		this.view.setVentanaNuevoReporte(false);

	}

	public void mostrarPanelBaja() {

		this.view.getBajaTrabajadorDTO()
				.setIdMovimientoIsssteEmpleado(this.view.getMovimientoSeleccinado().getIdMovimientoIsssteEmpleado());
		this.view.getBajaTrabajadorDTO().setSueldoIssste(this.view.getMovimientoSeleccinado().getSueldoIssste());
		this.view.getBajaTrabajadorDTO().setSueldoSar(this.view.getMovimientoSeleccinado().getSueldoSar());
		this.view.getBajaTrabajadorDTO()
				.setTotalRemuneracion(this.view.getMovimientoSeleccinado().getTotalRemuneracion());
		this.view.getBajaTrabajadorDTO().setIdCausaBaja(this.view.getMovimientoSeleccinado().getIdCausaBaja());

		this.view.setCriterio("");
		this.view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
		this.view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
		this.view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
		this.view.setAdministracionMovimientos(false);
		this.view.setAltaTrabajador(false);
		this.view.setFormularioAltaTrabajador(false);
		this.view.setBajaIssste(true);
		this.view.setSueldoTrabajador(false);
		this.view.setVentanaNuevoReporte(false);
	}

	public void cerrarFormularioAltaTrabajador() {
		this.view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
		this.view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
		this.view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
		this.view.setAdministracionMovimientos(false);
		this.view.setAltaTrabajador(true);
		this.view.setFormularioAltaTrabajador(false);
		this.view.setBajaIssste(false);
		this.view.setSueldoTrabajador(false);
		this.view.setVentanaNuevoReporte(false);
	}

	public void onRowSelect(SelectEvent event) {
		this.view.setOpcionDisponibles(false);
		this.view.setVentanaNuevoReporte(false);
	}

	public void onRowUnselect(UnselectEvent event) {
		this.view.setOpcionDisponibles(true);
		this.view.setVentanaNuevoReporte(false);
	}

	/************* Validar *************/
	public void validatorConsulta(FacesContext context, UIComponent component, Object value) {
		String nombreComponete = component.getId();

		switch (nombreComponete) {
		case "criterio":
			String criterio = (String) value;

			if (ValidacionUtil.esCadenaVacia(criterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			} else {
				if (criterio.trim().length() < 5) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}

			break;

		case "criterios":
			String criterios = (String) value;

			if (ValidacionUtil.esCadenaVacia(criterios)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			} else {
				if (criterios.trim().length() < 5) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}

			break;
		default:
			JSFUtils.errorMessage("Error: ", "Validar criterio");
			break;
		}
	}

	public Integer obtenerIdUsuarioSession() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

		return usuario.getIdUsuario();
	}

	public void regresar() {
		try {
			JSFUtils.redireccionar(
					"/siayf-rh/contenido/reportesLaborales/administracionMovimientoIssste.xhtml?faces-redirect=true");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public AdministracionMovimientsIsssteView getView() {
		return view;
	}

	public void setView(AdministracionMovimientsIsssteView view) {
		this.view = view;
	}
}
