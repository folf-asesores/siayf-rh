/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.IOException;
import java.io.Serializable;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.expediente.empleado.ExpedienteEmpleado;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboral;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademico;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 11:14:02 10/08/2016
 */
@ManagedBean(name = "consultaEmpleado")
@ViewScoped
public class ConsultaEmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6722806276589307701L;

	private static final int DATOS_GENERALES = 1;
	private static final int DOMICILIO = 2;
	private static final int HISTORIAL_ACADEMICO = 3;
	private static final int EXPERIENCIA_LABORAL = 6;
	private static final int DEPENDIENTES_ECONOMICOS = 8;
	private static final int EXPEDIENTE = 7;
	private static final int PRESUPUESTAL = 9;
	private static final int BITACORA = 10;

	@Inject
	private AdjuntoEmpleado adjuntoEmpleado;
	@Inject
	private Catalogo catalogo;
	@Inject
	private Empleado empleado;

	@Inject
	private ExpedienteEmpleado expedienteEmpleado;
	@Inject
	private ExperienciaLaboral experienciaLaboral;
	@Inject
	private HistorialAcademico historialAcademico;
	@Inject
	private PuestosAutorizadosEmpleados puestosAutorizados;
	private ConsultaEmpleadoView view;

	@PostConstruct
	public void init() {
		view = new ConsultaEmpleadoView();
		// adjuntoEmpleado.obtenerAdjuntoPorIdAdjunto(idAdjunto)
		cargarCatalogo();

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		Integer idEmpleado = (Integer) httpSession.getAttribute("idEmpleado");
		if (idEmpleado != null) {
			verMenuDetalle(idEmpleado);
			httpSession.removeAttribute("idEmpleado");
		}

	}

	public void cargarCatalogo() {
		this.view.setListaFiltros(SelectItemsUtil
				.listaFiltrosConsultaAspirantes());
		this.view.setListaTiposSexos(SelectItemsUtil.listaTiposSexo());
		this.view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
		this.view.setListaEscolaridades(SelectItemsUtil.listaCatalogos(catalogo
				.listaEscolaridades()));
		this.view.setListaComprobantesEstudios(SelectItemsUtil
				.listaCatalogos(catalogo.listaComprobantesEstudios()));
		this.view.setListaTiposParentescos(SelectItemsUtil.listaParentescos());
		this.view.setListaEstados(SelectItemsUtil.listaCatalogos(catalogo
				.listaEstados()));
		this.view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
	}

	public void obtenerConsultaEmpleado() {

		view.getFiltro()
				.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
		List<InfoEmpleadoDTO> listaInfoEmpleado = empleado
				.consultaEmpleado(view.getFiltro());

		this.view.setMostrarMenuDetalles(false);

		if (!listaInfoEmpleado.isEmpty()) {

			this.view.setEmpleados(listaInfoEmpleado);
			this.view.setMostrarResultadoConsulta(true);

			this.view.setMostrarTipoBusquedaHeader(false);
			this.view.setTipoBusquedaHeader("");

		} else {
			this.view.setEmpleados(new ArrayList<InfoEmpleadoDTO>());
			this.view.setMostrarResultadoConsulta(false);

			this.view.setFiltro(new FiltroDTO());
			JSFUtils.errorMessage("Consulta Empleado",
					"No se encontrarón resultados, intentelo de nuevo");
		}
	}

	public void menu(int panel) {

		try {

			initMenu();

			switch (panel) {

			case DATOS_GENERALES:

				this.view.setMostrarDatosGenerales(true);

				break;

			case DOMICILIO:

				this.view.setMostrarDomicilio(true);

				DomicilioDTO domicilioDTO = empleado.obtenerDomicilio(this.view
						.getIdEmpleado());

				this.view.setDomicilio(domicilioDTO);
				if (this.view.getDomicilio().isTieneDireccion()) {
					List<CatalogoDTO> municipios = catalogo
							.consultarMunicipiosPorEstado(this.view
									.getDomicilio().getIdEstado());
					this.view.getListaMuncipios().clear();
					this.view.setListaMuncipios(SelectItemsUtil
							.listaCatalogos(municipios));

					List<CatalogoDTO> asentamientos = catalogo
							.consultarAsantamientosPorMunicipios(this.view
									.getDomicilio().getIdMunicipio());
					this.view.getListaPoblaciones().clear();
					this.view.setListaPoblaciones(SelectItemsUtil
							.listaCatalogos(asentamientos));

				}

				break;

			case HISTORIAL_ACADEMICO:

				this.view.setMostrarHistorialAcademico(true);

				List<HistorialAcademicoDTO> historial = historialAcademico
						.consultarHistorialAcademicoEmpleado(this.view
								.getIdEmpleado());

				this.view.setListaHistorialesAcademicos(historial);

				break;

			case EXPERIENCIA_LABORAL:

				this.view.setMostrarExperienciaLaboral(true);

				List<ExperienciaLaboralDTO> experiencias = experienciaLaboral
						.consultaExperienciaLaboralEmpleado(this.view
								.getIdEmpleado());
				view.setListaExperienciasLaborales(experiencias);

				break;

			case DEPENDIENTES_ECONOMICOS:

				this.view.setMostrarDependientesEconomicos(true);

				List<CatalogoDTO> lista = catalogo
						.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_DEPENDIENTES);
				this.view.setListaDocumentosDependientes(SelectItemsUtil
						.listaCatalogos(lista));
				this.view.setMostrarDependientesEconomicos(true);
				List<InfoDependienteEconomicoDTO> dependientes = empleado
						.consultarDependientesEmpleado(this.view
								.getIdEmpleado());
				view.setDependientesEconomicos(dependientes);

				break;

			case EXPEDIENTE:

				view.setMostrarExpediente(true);

				List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado
						.consultarInformacionAdjuntosPorIdEmpleado(this.view
								.getIdEmpleado());

				if (!documentosExpedientes.isEmpty()) {
					this.view.setDocumentosExpedientes(documentosExpedientes);
				} else {
					this.view
							.setDocumentosExpedientes(new ArrayList<InformacionAdjuntoDTO>());
					JSFUtils.warningMessage("Documento Expediente: ",
							"No tiene registrado ningún documento");
				}

				break;
			case PRESUPUESTAL:
				view.setMostrarPuesto(true);
				view.setPuesto(puestosAutorizados
						.obtenerInformacionPuestoIdEmpleado(view
								.getIdEmpleado()));

				break;

			case BITACORA:
				view.setMostrarBitacora(true);
				view.setBitacorasMovimientos(empleado
						.consultarBitacorasMovimientos(view.getIdEmpleado()));

			default:
				JSFUtils.errorMessage("Consulta Empleado: ",
						"Seleccione en menu correcto");
				break;
			}

		} catch (ReglaNegocioException reglaNegocioException) {
			throw new ReglaNegocioException(reglaNegocioException.getMessage(),
					reglaNegocioException.getCodigoError());
		}

	}

	public void initMenu() {
		this.view.setMostrarDatosGenerales(false);
		this.view.setMostrarDomicilio(false);
		this.view.setMostrarHistorialAcademico(false);
		this.view.setMostrarProfesion(false);
		this.view.setMostrarEspecialidad(false);
		this.view.setMostrarExperienciaLaboral(false);
		this.view.setMostrarDependientesEconomicos(false);
		view.setMostrarExpediente(false);
		view.setMostrarImagenExpediente(false);
		view.setMostrarAdjuntoDocumentoHistorial(false);
		view.setMostrarPuesto(false);
		view.setMostrarBitacora(false);
	}

	public void verMenuDetalle(Integer idEmpleadoSeleccionado) {
		try {
			this.view.setIdEmpleado(idEmpleadoSeleccionado);
			this.view.setMostrarMenuDetalles(true);
			this.view.setMostrarDatosGenerales(true);
			this.view.setMostrarResultadoConsulta(false);

			// this.view.setVisualizarBotonDependientes(false);
			DatosGeneralesDTO datosGeneralesDTO = empleado
					.obtenerDatosGenerales(this.view.getIdEmpleado());
			this.view.setDatoGeneral(datosGeneralesDTO);

			if (this.view.getDatoGeneral().getTienePersonasDependientes() != null
					|| this.view.getDatoGeneral()
							.getTienePersonasDependientes()) {
				// this.view.setVisualizarBotonDependientes(true);
			}
			if (expedienteEmpleado.tieneExpedienteAperturado(this.view
					.getIdEmpleado())) {
				String numeroExpediente = expedienteEmpleado
						.numeroExpedienteEmpleado(this.view.getIdEmpleado());
				Integer idExpediente = expedienteEmpleado
						.obtenerIdExpedienteEmpleado(this.view.getIdEmpleado());
				this.view.setImagenExpediente("expediente_aperturado.png");
				this.view.setMostrarActualizacionExpediente(true);
				this.view.setNumeroExpediente(numeroExpediente);
				this.view.setIdExpediente(idExpediente);
			} else {
				this.view.setImagenExpediente("sin_expediente.png");
				this.view.setMostrarAperturaExpediente(true);
			}

		} catch (ReglaNegocioException exception) {

			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}
	}

	public void visualizarAdjuntoDocumentoHistorial(
			HistorialAcademicoDTO historialAcademicoDTO) {
		try {
			this.view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);

			List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
					.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
							EntidadContexto.HISTORIAL_ACADEMICO, this.view
									.getHistorialAcademicoSeleccionado()
									.getIdHistorialAcademico());
			this.view
					.setDocumentosAdjuntosGradoAcademico(documentosAdjuntosGradoAcademico);

			this.view.setMostrarAdjuntoDocumentoHistorial(true);
		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		}
	}

	public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {

		try {
			byte[] bytes = adjuntoEmpleado.obtenerAdjuntoPorIdAdjunto(adjunto
					.getIdAdjunto());

			JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(),
					adjunto.getExtension().getMIMEType());
			JSFUtils.infoMessage("Descarga iniciada",
					"La descarga del archivo ha iniciado.");

		} catch (ReglaNegocioException | IOException reglaNegocioException) {
			JSFUtils.errorMessage("Error al iniciar la descarga",
					reglaNegocioException.getMessage());
			reglaNegocioException.printStackTrace();
		}
	}

	public void visualizarImagen(Integer idImagenExpediente) {
		this.view.setIdImagenExpediente(idImagenExpediente);
		this.view.setMostrarImagenExpediente(true);
	}

	public void validarConsulta(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "criterio":

			String criterio = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(criterio)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese el criterio");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			if (criterio.trim().length() < 4) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"El criterio de la busqueda debe contener minimo 4 letras");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	
	/**
	 * @return the view
	 */
	public ConsultaEmpleadoView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(ConsultaEmpleadoView view) {
		this.view = view;
	}

}
