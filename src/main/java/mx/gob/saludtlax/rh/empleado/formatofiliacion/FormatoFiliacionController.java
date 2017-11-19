/**
 * 
 */
package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoDependienteEconomicoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.reportes.ReporteParamDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "formatoFiliacion")
@ViewScoped
public class FormatoFiliacionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3215671781558762301L;

	@Inject
	private Empleado empleado;
	@Inject
	private FormatoFiliacion formatoFiliacion;

	private FormatoFiliacionView view;
	private String urlReporte;

	@PostConstruct
	public void init() {
		view = new FormatoFiliacionView();
		view.setListaBoca(SelectItemsUtil.listaBoca());
		view.setListaCabello(SelectItemsUtil.listaCabello());
		view.setListaCeja(SelectItemsUtil.listaCejas());
		view.setListaColorPiel(SelectItemsUtil.listaColorPiel());
		view.setListaFrente(SelectItemsUtil.listaFrente());
		view.setListaNariz(SelectItemsUtil.listaNariz());
		view.setListaOjos(SelectItemsUtil.listaOjos());
	}

	/**************** Consultas ***************/
	public void consultarEmpleados() {
		try {
			view.setListaEmpleados(empleado.consultaEmpleadosFederales(view.getCriterio()));

			if (view.getListaEmpleados().isEmpty()) {
				JSFUtils.warningMessage("", "No se encontrarón registros con el criterio " + this.view.getCriterio());
			}

		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("", validatorException.getMessage());
		}
	}

	/*** Accciones *********/

	public void verFormatoFiliacion() throws IOException {
		try {

			view.getFormatoFiliacionDTO().setIdEmpleado(this.view.getIdEmpleado());

			formatoFiliacion.crearFormatoFiliacion(view.getFormatoFiliacionDTO());

			urlReporte = "FormatoFiliacionServlet?" + "idEmpleado=" + view.getIdEmpleado();
			view.setFormulario(false);
			view.setMostrarReporteNuevaVentana(true);
			view.setMostrarExitoReporte(true);
			JSFUtils.infoMessage("Formato Filiación: ", "El proceso de realizo correctamente");

		} catch (NullPointerException | IllegalArgumentException exception) {

			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void verFormatoFiliacionDos() throws IOException {

		try {

			this.view.getFormatoFiliacionDTO().setIdEmpleado(this.view.getIdEmpleado());

			this.view.setIdFormato(formatoFiliacion.crearFormatoFiliacion(this.view.getFormatoFiliacionDTO()));

			byte[] bytes = null;
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"formato-filiacion", "TIPO_REPORTE", "pdf", "ID_EMPLEADO",
					String.valueOf(this.view.getIdEmpleado()) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			bytes = admintradorReportes.obtenerReporte(referencia);

			if (bytes != null) {

				// JSFUtils.descargarArchivo(bytes,
				// "Formato_Filiacion",
				// TipoArchivo.getMIMEType("pdf"));

				ReporteParamDTO reporteParamDTO = new ReporteParamDTO();

				reporteParamDTO.setParametros(parametros);
				reporteParamDTO.setNombreReporte("Formato_Filiacion.pdf");
				reporteParamDTO.setTituloReporte("Reporte Formato Filiación");
				reporteParamDTO.setSubtituloReporte("Reporte Formato Filiación");
				reporteParamDTO.setBytes(bytes);

				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				session.setAttribute("reporteParam", reporteParamDTO);

				urlReporte = "contenido/reportesLaborales/administradorReporte.xhtml?faces-redirect=true";
				this.view.setFormulario(false);
				this.view.setMostrarReporteNuevaVentana(true);
				this.view.setMostrarExitoReporte(true);

			}

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

	/********** Render ****************/
	public void mostrarFormulario(Integer idEmpleado, String nombreEmpleado) {

		view.setPrincipal(false);
		view.setFormulario(true);
		view.setIdEmpleado(idEmpleado);
		view.setNombreEmpleado(nombreEmpleado);
		JSFUtils.infoMessage("",
				"En caso de ser necesaria la información de los padres y conyuge, es requerido que previamente sean registrados en el módulo de dependientes economicos.");

		List<InfoDependienteEconomicoDTO> dependienteEconomicoDTOs = empleado
				.consultarDependientesEconomicoPadres(idEmpleado);

		if (!dependienteEconomicoDTOs.isEmpty()) {

			for (InfoDependienteEconomicoDTO infoDependienteEconomicoDTO : dependienteEconomicoDTOs) {

				if (infoDependienteEconomicoDTO.getParentesco().equals("PADRES")) {

					if (infoDependienteEconomicoDTO.getParentesco().equals("CONYUGE")) {
						this.view.getFormatoFiliacionDTO()
								.setNombreConyuge(infoDependienteEconomicoDTO.getNombreCompleto());
					}

					if (infoDependienteEconomicoDTO.getSexo().equals("MASCULINO")) {
						this.view.getFormatoFiliacionDTO()
								.setNombrePadre(infoDependienteEconomicoDTO.getNombreCompleto());
					}

					if (infoDependienteEconomicoDTO.getSexo().equals("FEMENINO")) {
						this.view.getFormatoFiliacionDTO()
								.setNombreMadre(infoDependienteEconomicoDTO.getNombreCompleto());
					}

				}
			}

		}

	}

	/************* Ajax ****************/

	public void ajaxColorPiel() {
		this.view.getFormatoFiliacionDTO().setColorBlanco(false);
		this.view.getFormatoFiliacionDTO().setColorNegro(false);
		this.view.getFormatoFiliacionDTO().setColorMorenoClaro(false);
		this.view.getFormatoFiliacionDTO().setColorMorenoOscuro(false);
		this.view.getFormatoFiliacionDTO().setColorAmarillo(false);

		if (this.view.getIdColorPiel().equals(EnumColorPiel.COLOR_BLANCO)) {
			this.view.getFormatoFiliacionDTO().setColorBlanco(true);
		}
		if (this.view.getIdColorPiel().equals(EnumColorPiel.COLOR_NEGRO)) {
			this.view.getFormatoFiliacionDTO().setColorNegro(true);
		}
		if (this.view.getIdColorPiel().equals(EnumColorPiel.COLOR_MORENO_CLARO)) {
			this.view.getFormatoFiliacionDTO().setColorMorenoClaro(true);
		}
		if (this.view.getIdColorPiel().equals(EnumColorPiel.COLOR_MORENO_OSCURO)) {
			this.view.getFormatoFiliacionDTO().setColorMorenoOscuro(true);
		}
		if (this.view.getIdColorPiel().equals(EnumColorPiel.COLOR_AMARILLO)) {
			this.view.getFormatoFiliacionDTO().setColorAmarillo(true);
		}
	}

	public void ajaxCabello() {
		this.view.getFormatoFiliacionDTO().setCabelloCastClaro(false);
		this.view.getFormatoFiliacionDTO().setCabelloCastOscuro(false);
		this.view.getFormatoFiliacionDTO().setCabelloNegro(false);
		this.view.getFormatoFiliacionDTO().setCabelloRubio(false);
		this.view.getFormatoFiliacionDTO().setCabelloRojo(false);
		this.view.getFormatoFiliacionDTO().setCabelloAlbino(false);
		this.view.getFormatoFiliacionDTO().setCabelloEntrecano(false);
		this.view.getFormatoFiliacionDTO().setCabelloCano(false);

		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_CASTANIO_CLARO)) {
			this.view.getFormatoFiliacionDTO().setCabelloCastClaro(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_CASTANIO_OSCURO)) {
			this.view.getFormatoFiliacionDTO().setCabelloCastOscuro(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_NEGRO)) {
			this.view.getFormatoFiliacionDTO().setCabelloNegro(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_RUBIO)) {
			this.view.getFormatoFiliacionDTO().setCabelloRubio(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_ROJO)) {
			this.view.getFormatoFiliacionDTO().setCabelloRojo(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_ALBINO)) {
			this.view.getFormatoFiliacionDTO().setCabelloAlbino(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_ENTRECANO)) {
			this.view.getFormatoFiliacionDTO().setCabelloEntrecano(true);
		}
		if (this.view.getIdCabello().equals(EnumCabello.CABELLO_CANO)) {
			this.view.getFormatoFiliacionDTO().setCabelloCano(true);
		}
	}

	public void ajaxFrente() {
		this.view.getFormatoFiliacionDTO().setFrentePequenia(false);
		this.view.getFormatoFiliacionDTO().setFrenteMediana(false);
		this.view.getFormatoFiliacionDTO().setFrenteGrande(false);

		if (this.view.getIdFrente().equals(EnumFrente.FRENTE_PEQUENIA)) {
			this.view.getFormatoFiliacionDTO().setFrentePequenia(true);
		}
		if (this.view.getIdFrente().equals(EnumFrente.FRENTE_MEDIANA)) {
			this.view.getFormatoFiliacionDTO().setFrenteMediana(true);
		}
		if (this.view.getIdFrente().equals(EnumFrente.FRENTE_GRANDE)) {
			this.view.getFormatoFiliacionDTO().setFrenteGrande(true);
		}
	}

	public void ajaxCejas() {
		this.view.getFormatoFiliacionDTO().setCejasAbundantes(false);
		this.view.getFormatoFiliacionDTO().setCejasEscasas(false);
		this.view.getFormatoFiliacionDTO().setCejasRegulares(false);

		if (this.view.getIdCeja().equals(EnumCeja.CEJAS_ABUNDANTES)) {
			this.view.getFormatoFiliacionDTO().setCejasAbundantes(true);
		}
		if (this.view.getIdCeja().equals(EnumCeja.CEJAS_ESCASAS)) {
			this.view.getFormatoFiliacionDTO().setCejasEscasas(true);
		}
		if (this.view.getIdCeja().equals(EnumCeja.CEJAS_REGULARES)) {
			this.view.getFormatoFiliacionDTO().setCejasRegulares(true);
		}
	}

	public void ajaxOjos() {
		this.view.getFormatoFiliacionDTO().setOjosAzules(false);
		this.view.getFormatoFiliacionDTO().setOjosVerdes(false);
		this.view.getFormatoFiliacionDTO().setOjosCastClaro(false);
		this.view.getFormatoFiliacionDTO().setOjosCastOscuro(false);
		this.view.getFormatoFiliacionDTO().setOjosPardos(false);
		this.view.getFormatoFiliacionDTO().setOjosVerdosos(false);
		this.view.getFormatoFiliacionDTO().setOjosNegros(false);

		if (this.view.getIdOjos().equals(EnumOjos.OJOS_AZULES)) {
			this.view.getFormatoFiliacionDTO().setOjosAzules(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_VERDES)) {
			this.view.getFormatoFiliacionDTO().setOjosVerdes(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_CASTANIO_CLARO)) {
			this.view.getFormatoFiliacionDTO().setOjosCastClaro(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_CASTANIO_OSCURO)) {
			this.view.getFormatoFiliacionDTO().setOjosCastOscuro(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_PARDOS)) {
			this.view.getFormatoFiliacionDTO().setOjosPardos(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_VERDOSOS)) {
			this.view.getFormatoFiliacionDTO().setOjosVerdosos(true);
		}
		if (this.view.getIdOjos().equals(EnumOjos.OJOS_NEGROS)) {
			this.view.getFormatoFiliacionDTO().setOjosNegros(true);
		}
	}

	public void ajaxNariz() {
		this.view.getFormatoFiliacionDTO().setNarizConvexa(false);
		this.view.getFormatoFiliacionDTO().setNarizConcava(false);
		this.view.getFormatoFiliacionDTO().setNarizRectilinea(false);

		if (this.view.getIdNariz().equals(EnumNariz.NARIZ_CONVEXA)) {
			this.view.getFormatoFiliacionDTO().setNarizConvexa(true);
		}
		if (this.view.getIdNariz().equals(EnumNariz.NARIZ_CONCAVA)) {
			this.view.getFormatoFiliacionDTO().setNarizConcava(true);
		}
		if (this.view.getIdNariz().equals(EnumNariz.NARIZ_RECTILINEA)) {
			this.view.getFormatoFiliacionDTO().setNarizRectilinea(true);
		}
	}

	public void ajaxBoca() {
		this.view.getFormatoFiliacionDTO().setBocaPequenia(false);
		this.view.getFormatoFiliacionDTO().setBocaRegular(false);
		this.view.getFormatoFiliacionDTO().setBocaGrande(false);

		if (this.view.getIdBoca().equals(EnumBoca.BOCA_PEQUENIA)) {
			this.view.getFormatoFiliacionDTO().setBocaPequenia(true);
		}
		if (this.view.getIdBoca().equals(EnumBoca.BOCA_REGULAR)) {
			this.view.getFormatoFiliacionDTO().setBocaRegular(true);
		}
		if (this.view.getIdBoca().equals(EnumBoca.BOCA_GRANDE)) {
			this.view.getFormatoFiliacionDTO().setBocaGrande(true);
		}
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

	public void regresar() {
		try {
			JSFUtils.redireccionar("/siayf-rh/contenido/empleado/formatoFiliacion.xhtml?faces-redirect=true");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public FormatoFiliacionView getView() {
		return view;
	}

	public void setView(FormatoFiliacionView view) {
		this.view = view;
	}

	/**
	 * @return the urlReporte
	 */
	public String getUrlReporte() {
		return urlReporte;
	}

	/**
	 * @param urlReporte
	 *            the urlReporte to set
	 */
	public void setUrlReporte(String urlReporte) {
		this.urlReporte = urlReporte;
	}

}
