/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoDependienteEconomicoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "seguroVidaInstitucional")
@ViewScoped
public class SeguroVidaInstitucionalController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3519560480351058003L;

	@Inject
	private Empleado empleado;
	@Inject
	private SeguroVidaInstitucional seguroVidaInstitucional;

	private SeguroVidaInstitucionalView view;

	@PostConstruct
	public void init() {
		view = new SeguroVidaInstitucionalView();
	}

	/**************** Consultas ***************/
	public void consultarEmpleados() {

		try {

			view.setListaDependientesEconomicos(new ArrayList<InfoDependienteEconomicoDTO>());
			view.setFormularioAltaSeguro(false);
			view.setGenerarReporte(false);
			view.setDatosEmpleadoSeleccionado(false);
			view.setDialogPorcentaje(false);
			view.setPrincipal(true);
			view.setVentanaNuevoReporte(false);
			view.setReporteExitoso(false);

			view.setListaEmpleados(empleado.consultaEmpleadosFederales(view.getCriterio()));

			if (view.getListaEmpleados().isEmpty()) {
				JSFUtils.infoMessageEspecifico("info", "",
						"No se encontrarón registros con el criterio " + view.getCriterio());
			}

		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("Error: ", validatorException.getMessage());
		}
	}

	/************** Select **********/
	public void seleccionarEmpleado(InfoEmpleadoDTO infoEmpleadoDTO) {

		view.setInfoEmpleadoDTO(infoEmpleadoDTO);

		Integer idSeguroVida = seguroVidaInstitucional.existeEmpleado(infoEmpleadoDTO.getIdEmpleado());

		if (ValidacionUtil.esNumeroPositivoInt(idSeguroVida)) {
			view.setIdSeguroVida(idSeguroVida);
			view.setGenerarReporte(true);
			view.setDatosEmpleadoSeleccionado(true);
			view.setFormularioAltaSeguro(false);
			view.setDialogPorcentaje(false);
			view.setPrincipal(false);
			view.setVentanaNuevoReporte(false);
			view.setReporteExitoso(false);
		} else {

			List<InfoDependienteEconomicoDTO> dependienteEconomicoDTOs = empleado
					.consultarDependientesEmpleado(infoEmpleadoDTO.getIdEmpleado());

			if (!dependienteEconomicoDTOs.isEmpty()) {

				view.setListaDependientesEconomicos(dependienteEconomicoDTOs);
				view.setFormularioAltaSeguro(true);
				view.setDatosEmpleadoSeleccionado(true);
				view.setPrincipal(false);

			} else {
				view.setFormularioAltaSeguro(false);
				view.setDatosEmpleadoSeleccionado(false);

				JSFUtils.warningMessage("",
						"Para el registro del seguro de vida, previamente deben registrarse los dependientes economicos del empleado."
								+ infoEmpleadoDTO.getNombre());
			}
		}

	}

	public void seleccionarBeneficiario(InfoDependienteEconomicoDTO dependienteEconomicoDTO) {

		boolean existeDependienteEconomico = false;

		view.setDialogPorcentaje(false);
		Integer total = 100;
		if (!view.getBeneficiariosDTOsNuevos().isEmpty()) {

			if (view.getTotalPorcentaje() == total) {
				JSFUtils.errorMessage("Error: ", "limite maximo de porcentaje.");
				view.setDialogPorcentaje(false);
				view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());
			} else {
				view.setDialogPorcentaje(false);
				for (BeneficiariosDTO beneficiariosDTO : view.getBeneficiariosDTOsNuevos()) {

					if (beneficiariosDTO.getIdDependienteEconomico() == dependienteEconomicoDTO.getIdDependiente()) {
						existeDependienteEconomico = true;
					}
				}

				if (existeDependienteEconomico) {
					JSFUtils.errorMessage("Error: ", "El dependiente economico ya se encuentra seleccionado.");
					view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());
					view.setDialogPorcentaje(false);
				} else {
					view.setInfoDependienteEconomicoDTOSeleccionado(dependienteEconomicoDTO);
					view.setDialogPorcentaje(true);
				}
			}
		} else {
			if (view.getTotalPorcentaje() == total) {
				JSFUtils.errorMessage("Error: ", "limite maximo de porcentaje.");
				view.setDialogPorcentaje(false);
				view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());
			} else {
				view.setInfoDependienteEconomicoDTOSeleccionado(dependienteEconomicoDTO);
				view.setDialogPorcentaje(true);
			}
		}

	}

	/******** Accion ***/

	public void registrarSeguro() {

		if (!view.getBeneficiariosDTOsNuevos().isEmpty()) {

			try {

				view.getSeguroVidaInstitucionalDTONuevo().setBeneficiariosDTOs(view.getBeneficiariosDTOsNuevos());

				view.getSeguroVidaInstitucionalDTONuevo().setIdEmpleado(view.getInfoEmpleadoDTO().getIdEmpleado());

				Integer idSeguroVida = seguroVidaInstitucional
						.crearSeguroVida(view.getSeguroVidaInstitucionalDTONuevo());

				view.setPrincipal(false);
				view.setDialogPorcentaje(false);
				view.setFormularioAltaSeguro(false);
				view.setVentanaNuevoReporte(true);
				view.setReporteExitoso(true);
				view.setGenerarReporte(false);
				view.setDatosEmpleadoSeleccionado(false);

				view.setUrlReporte("SeguroVidaInstitucionalServlet?" + "idSeguro=" + idSeguroVida);

				JSFUtils.infoMessage("Registro Seguro de Vida Institucional: ", "Se realizo correctamente");

			} catch (ValidacionException validacionException) {
				JSFUtils.errorMessage("Error: ", validacionException.getMessage());
			} catch (ReglaNegocioException reglaNegocioException) {
				JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
			}

		} else {
			JSFUtils.errorMessageEspecifico("error", "", "Agregue un Beneficiario.");
		}

	}

	public void generarSeguro() {
		try {
			view.setPrincipal(false);
			view.setDialogPorcentaje(false);
			view.setFormularioAltaSeguro(false);
			view.setVentanaNuevoReporte(true);
			view.setReporteExitoso(true);
			view.setGenerarReporte(false);
			view.setDatosEmpleadoSeleccionado(false);

			view.setUrlReporte("SeguroVidaInstitucionalServlet?" + "idSeguro=" + view.getIdSeguroVida());

			JSFUtils.infoMessage("Reporte Seguro de Vida Institucional: ", "Se genero correctamente");
		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}

	}

	public void agregarBeneficiario() {

		BeneficiariosDTO beneficiariosDTONuevo = new BeneficiariosDTO();
		BigDecimal porcentajeLimite = new BigDecimal(100);
		BigDecimal porcentajeTotal = BigDecimal.ZERO;
		Integer total = 100;

		if (!view.getBeneficiariosDTOsNuevos().isEmpty()) {

			if (view.getTotalPorcentaje() == total) {
				JSFUtils.errorMessage("Error: ", "limite maximo de porcentaje.");
				view.setDialogPorcentaje(false);
			} else {

				for (BeneficiariosDTO beneficiariosDTO : view.getBeneficiariosDTOsNuevos()) {

					porcentajeTotal = porcentajeTotal
							.add(new BigDecimal(String.valueOf(beneficiariosDTO.getPorcetaje())));
				}

				porcentajeTotal = porcentajeTotal.add(new BigDecimal(String.valueOf(view.getPorcentajeBeneficiario())));

				if (porcentajeTotal.compareTo(porcentajeLimite) > 0) {
					JSFUtils.errorMessage("Error: ", "limite maximo de porcentaje.");
					view.setDialogPorcentaje(true);
				} else {

					beneficiariosDTONuevo.setIdDependienteEconomico(
							view.getInfoDependienteEconomicoDTOSeleccionado().getIdDependiente());
					beneficiariosDTONuevo.setCurp(view.getInfoDependienteEconomicoDTOSeleccionado().getCurp());
					beneficiariosDTONuevo
							.setFechaNacimiento(view.getInfoDependienteEconomicoDTOSeleccionado().getFechaNacimiento());
					beneficiariosDTONuevo
							.setNombreCompleto(view.getInfoDependienteEconomicoDTOSeleccionado().getNombreCompleto());
					beneficiariosDTONuevo
							.setOtroParentesco(view.getInfoDependienteEconomicoDTOSeleccionado().getOtroParentesco());
					beneficiariosDTONuevo
							.setParentesco(view.getInfoDependienteEconomicoDTOSeleccionado().getParentesco());
					beneficiariosDTONuevo.setSexo(view.getInfoDependienteEconomicoDTOSeleccionado().getSexo());
					beneficiariosDTONuevo.setPorcetaje(view.getPorcentajeBeneficiario());

					view.setTotalPorcentaje(porcentajeTotal.intValue());

					view.getBeneficiariosDTOsNuevos().add(beneficiariosDTONuevo);
					view.setDialogPorcentaje(false);
					view.setPorcentajeBeneficiario(0);
					view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());

				}
			}
		} else {

			porcentajeTotal = porcentajeTotal.add(new BigDecimal(String.valueOf(view.getPorcentajeBeneficiario())));

			if (porcentajeTotal.compareTo(porcentajeLimite) > 0) {
				JSFUtils.errorMessage("Error: ", "limite maximo de porcentaje.");
				view.setDialogPorcentaje(true);
			} else {

				beneficiariosDTONuevo.setIdDependienteEconomico(
						view.getInfoDependienteEconomicoDTOSeleccionado().getIdDependiente());
				beneficiariosDTONuevo.setCurp(view.getInfoDependienteEconomicoDTOSeleccionado().getCurp());
				beneficiariosDTONuevo
						.setFechaNacimiento(view.getInfoDependienteEconomicoDTOSeleccionado().getFechaNacimiento());
				beneficiariosDTONuevo
						.setNombreCompleto(view.getInfoDependienteEconomicoDTOSeleccionado().getNombreCompleto());
				beneficiariosDTONuevo
						.setOtroParentesco(view.getInfoDependienteEconomicoDTOSeleccionado().getOtroParentesco());
				beneficiariosDTONuevo.setParentesco(view.getInfoDependienteEconomicoDTOSeleccionado().getParentesco());
				beneficiariosDTONuevo.setSexo(view.getInfoDependienteEconomicoDTOSeleccionado().getSexo());
				beneficiariosDTONuevo.setPorcetaje(view.getPorcentajeBeneficiario());

				view.setTotalPorcentaje(porcentajeTotal.intValue());

				view.getBeneficiariosDTOsNuevos().add(beneficiariosDTONuevo);
				view.setDialogPorcentaje(false);
				view.setPorcentajeBeneficiario(0);
				view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());

			}

		}

	}

	public void eliminarBeneficiario(BeneficiariosDTO beneficiariosDTO) {

		Integer totalPorcentaje = view.getTotalPorcentaje();
		Integer nuevoPorcentaje = 0;

		nuevoPorcentaje = totalPorcentaje - beneficiariosDTO.getPorcetaje();

		view.setTotalPorcentaje(nuevoPorcentaje);

		view.getBeneficiariosDTOsNuevos().remove(beneficiariosDTO);
	}

	public void cerrarDialogo() {
		view.setDialogPorcentaje(false);
		view.setPorcentajeBeneficiario(0);
		view.setInfoDependienteEconomicoDTOSeleccionado(new InfoDependienteEconomicoDTO());
	}

	
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
		default:
			JSFUtils.errorMessage("Error: ", "Validar criterio");
			break;
		}
	}

	public void validatorCampos(FacesContext context, UIComponent component, Object value) {
		String nombreComponete = component.getId();

		switch (nombreComponete) {
		case "numeroExpediente":
			String numeroExpediente = (String) value;

			if (ValidacionUtil.esCadenaVacia(numeroExpediente)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el numero de expediente.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			} else {
				if (seguroVidaInstitucional.existeNumeroExpediente(numeroExpediente)) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"El número de expediente ya se encuentra registrado.");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}

			break;

		case "porcentaje":

			Integer porcentaje = (Integer) value;

			if (ValidacionUtil.esNumeroPositivoInt(porcentaje)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el porcentaje.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		default:
			JSFUtils.errorMessage("Error: ", "Validar criterio");
			break;
		}
	}

	public void regresar() {
		try {
			JSFUtils.redireccionar("/siayf-rh/contenido/reportesLaborales/reporteSeguroVida.xhtml?faces-redirect=true");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public SeguroVidaInstitucionalView getView() {
		return view;
	}

	public void setView(SeguroVidaInstitucionalView view) {
		this.view = view;
	}

}
