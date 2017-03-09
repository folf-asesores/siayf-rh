/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.configuracion.especialidad.Especialidad;
import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.Profesion;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 12/05/2016 17:26:18
 */
@ManagedBean(name = "actualizacionAspirante")
@ViewScoped
public class ActualizacionAspiranteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6931321375018533928L;
	private static final Logger LOGGER = Logger.getLogger(ActualizacionAspiranteController.class);

	private static final int DATOS_GENERALES = 1;
	private static final int HISTORIAL_ACADEMICO = 2;
	private static final int EXPERIENCIA_LABORAL = 3;
	private static final int HABILIDADES_PERSONALES = 4;
	private static final int EXPEDIENTE = 5;
	private static final int PROFESION = 6;
	private static final int ESPECIALIDAD = 7;

	@Inject
	private Empleado empleadoEJB;
	@Inject
	private BolsaTrabajo bolsaTrabajoEJB;
	@Inject
	private Catalogo catalogosEJB;
	@Inject
	private Especialidad especialidad;
	@Inject
	private Profesion profesion;

	private ActualizacionAspiranteView view;

	@PostConstruct
	public void init() {

		this.view = new ActualizacionAspiranteView();

		cargarCatalogo();
	}

	public void cargarCatalogo() {
		List<CatalogoDTO> listaPaises = catalogosEJB.listaPaises();
		List<CatalogoDTO> listaPuestos = catalogosEJB.listaPuestos();
		List<CatalogoDTO> listaDepartamentos = catalogosEJB.listaDepartamentos();

		this.view.setListaPaises(SelectItemsUtil.listaCatalogos(listaPaises));
		this.view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
		this.view.setListaNacionalidades(SelectItemsUtil.listaNacionalidad());
		this.view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
		this.view.setListaTiposSexo(SelectItemsUtil.listaTiposSexo());
		this.view.setListaPuestos(SelectItemsUtil.listaCatalogos(listaPuestos));
		this.view.setListaDepartamentos(SelectItemsUtil.listaCatalogos(listaDepartamentos));
		this.view.setListaViveCon(SelectItemsUtil.listaViveCon());
		this.view.setListaDependientes(SelectItemsUtil.listaDependientes());
		this.view.setListaTiposLicencia(SelectItemsUtil.listaTiposLicencia());
		this.view.setMostrarDatosPersonales(true);

		this.view.setListaFiltros(SelectItemsUtil.listaFiltrosConsultaAspirantes());
	}

	public List<InfoAspiranteDTO> consultaAspiranteAutoComplete(String query) {

		List<InfoAspiranteDTO> listadoEmpleadoDTO = null;

		if (query == "") {
			query = ".";
		}

		listadoEmpleadoDTO = empleadoEJB.consultarAspirantesDisponiblesPorCriterio(query);

		return listadoEmpleadoDTO;

	}

	public void obtenerInfoAspirante() {

		view.setIdAspirante(0);
		view.setMostrarPanelDatosAspirante(false);
		view.getFiltro().setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);

		List<InfoAspiranteDTO> listaInfoAspirante = bolsaTrabajoEJB.consultarPorCriterio(this.view.getFiltro());

		if (!listaInfoAspirante.isEmpty()) {
			this.view.setListaInfoAspirante(listaInfoAspirante);
			this.view.setMostrarResultados(true);

			this.view.setMostrarTipoBusquedaHeader(false);
			this.view.setTipoBusquedaHeader("");

		} else {
			this.view.setListaInfoAspirante(new ArrayList<InfoAspiranteDTO>());
			this.view.setMostrarResultados(false);

			this.view.setFiltro(new FiltroDTO());
			JSFUtils.errorMessage("Consulta Aspirante", "No se encontrarón resultados, intentelo de nuevo");
		}
	}

	public void initMenu() {
		view.setMostrarDatosPersonales(false);
		view.setMostrarHistorialAcademico(false);
		view.setMostrarExperienciaLaboral(false);
		view.setMostrarEscuestaPersonal(false);
		view.setMostrarAdjuntarDocumento(false);
		this.view.setMostrarProfesion(false);
		this.view.setMostrarFormularioPofesion(false);
		this.view.setProfesionDTO(new ProfesionDTO());
		this.view.setMostrarEspecialidad(false);
		this.view.setMostrarFormularioEspecialidad(false);
		this.view.setEspecialidadDTO(new EspecialidadDTO());
		this.view.setHistorialAcademico(new HistorialAcademicoDTO());
		this.view.setMostrarPanelHistorialAcedemico(false);
		this.view.setAccionHistorialAcademico("Registrar");
		this.view.setActualizacionExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());
		view.setMostrarRazonNosolicitar(false);
		this.view.setIdProfesion(0);
		this.view.setIdProfesionAspirante(0);
		this.view.setIdEspecialidad(0);
		this.view.setIdEspecialidadAspirante(0);
		this.view.setMostrarFormularioExperiencia(false);

	}

	public void menu(int panel) {

		try {

			initMenu();

			switch (panel) {

			case DATOS_GENERALES:

				view.setMostrarDatosPersonales(true);

				break;

			case HISTORIAL_ACADEMICO:

				this.view.setMostrarHistorialAcademico(true);

				this.view.setListaComprobantesEstudio(
						SelectItemsUtil.listaCatalogos(catalogosEJB.listaComprobantesEstudios()));

				view.setListaEscolaridades(SelectItemsUtil.listaEscolaridad(catalogosEJB.listaEscolaridad()));

				List<HistorialAcademicoDTO> listaHistorial = bolsaTrabajoEJB
						.obtenerListaHistorialAcademico(this.view.getIdAspirante());

				if (!listaHistorial.isEmpty()) {
					view.setListaHistorialAcademicoAspirante(listaHistorial);
				} else {
					view.setListaHistorialAcademicoAspirante(new ArrayList<HistorialAcademicoDTO>());
					JSFUtils.warningMessage("Historial Academico: ",
							"Se ha detectado que no ha registrado ningún historial academico, se recomienda mantener su información actualizada");
				}

				break;
			case PROFESION:

				this.view.setMostrarProfesion(true);

				List<ProfesionDTO> profesiones = profesion
						.obtenerListaProfesionPorIdAspirante(this.view.getIdAspirante());

				this.view.setListaProfesion(profesiones);

				this.view.setListaTipoProfesion(SelectItemsUtil.listaCatalogos(catalogosEJB.obtenerListaProfesion()));

				break;

			case ESPECIALIDAD:

				this.view.setMostrarEspecialidad(true);

				List<EspecialidadDTO> especialidades = especialidad
						.obtenerListaEspecialidadPorIdAspirante(this.view.getIdAspirante());

				this.view.setListaEspecialidad(especialidades);

				this.view.setListaTipoEspecialidad(
						SelectItemsUtil.listaCatalogos(catalogosEJB.obtenerListaEspecialidad()));

				break;

			case EXPERIENCIA_LABORAL:

				view.setMostrarExperienciaLaboral(true);
				view.setMostrarRazonNosolicitar(true);

				List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral = bolsaTrabajoEJB
						.obtenerListaExperienciaLaboral(this.view.getIdAspirante());

				if (!listaExperienciaLaboral.isEmpty()) {
					this.view.setListaExperienciaLaboralAspirante(listaExperienciaLaboral);
				} else {
					this.view.setListaExperienciaLaboralAspirante(new ArrayList<ExperienciaLaboralAspiranteDTO>());
					JSFUtils.warningMessage("Experiencia Laboral: ",
							"Se ha detectado que no ha registrado ninguna experiencia laboral, se recomienda mantener su información actualizada");
				}

				break;
			case HABILIDADES_PERSONALES:

				HabilidadesPersonalesAspiranteDTO encuesta = bolsaTrabajoEJB
						.obtenerHabilidadesPersonalesPorIdAspirante(this.view.getIdAspirante());

				if (encuesta.getIdEncuestaPersonalAspirante() != 0) {
					this.view.setHabilidadesPersonales(encuesta);
					this.view.setAccionEncuestaPersonal("Actualizar");
				} else {
					this.view.setHabilidadesPersonales(new HabilidadesPersonalesAspiranteDTO());
					this.view.setAccionEncuestaPersonal("Registrar");
					JSFUtils.warningMessage("Habilidades Parsonales: ",
							"Se ha detectado que no ha registrado habilidades personales, se recomienda mantener su información actualizada");
				}

				view.setMostrarEscuestaPersonal(true);

				// view.setMostrarTxtOtroMedio(false);
				mostrarTxtOtroMedio();
				// view.setMostrarTxtNombresParientes(false);
				mostrarTxtNombresParientes();
				// view.setMostrarTxtNombreAfianza(false);
				mostrarTxtNombreAfianza();
				// view.setMostrarTxtNombreSindicato(false);
				mostrarTxtNombreSindicato();
				// view.setMostrarTxtNombreSegurVida(false);
				mostrarTxtNombreSegurVida();
				// view.setMostrarTxtRazonNoViajar(false);
				mostrarTxtRazonNoViajar();
				// view.setMostrarTxtRazonNoCambioResidencia(false);
				mostrarTxtRazonNoCambioResidencia();
				// view.setMostrarTxtOtrosIngresos(false);
				mostrarTxtOtrosIngresos();
				// view.setMostrarTxtConyugeTrabaja(false);
				mostrarTxtConyugeTrabaja();
				// view.setMostrarTxtCasaPropia(false);
				mostrarTxtCasaPropia();
				// view.setMostrarTxtPagaRenta(false);
				mostrarTxtPagaRenta();
				// view.setMostrarTxtAutomovilPropio(false);
				mostrarTxtAutomovilPropio();
				// view.setMostrarTxtTieneDeudas(false);
				mostrarTxtTieneDeudas();

				break;

			case EXPEDIENTE:

				break;
			}

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void actualizarDatosGenerales() {
		try {

			if (this.view.getPersonasDependientes() != null) {
				for (String personaDependiente : this.view.getPersonasDependientes()) {
					if (personaDependiente.equals("CONYUGE")) {
						this.view.getActualizacionAspirante().setNumeroConyuges(1);
						System.out.println("SI TIENE CONYUGE");
					} else {
						this.view.getActualizacionAspirante().setNumeroConyuges(0);
					}
				}
			}

			// this.actualizacionAspirante.setIdAspirante(this.view.getIdAspirante());

			this.bolsaTrabajoEJB.actualizarAspirante(this.view.getActualizacionAspirante());

			JSFUtils.infoMessage("", "¡Los datos generales se actualizarón correctamente!");
			// this.mostrarPanelHistorialAcademico();
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void registrarActualizarHistorialAcademico() {
		try {

			this.view.getHistorialAcademico().setAspirante(this.view.getIdAspirante());

			if (this.view.getAccionHistorialAcademico().equals("Registrar")) {
				this.bolsaTrabajoEJB.crearHistorialAcademicoAspirante(this.view.getHistorialAcademico());
				JSFUtils.infoMessage("Registro Historial Académico: ", "Se realizo correctamente");
			} else if (this.view.getAccionHistorialAcademico().equals("Actualizar")) {
				this.bolsaTrabajoEJB.actualizarHistorialAcademicoAspirante(this.view.getHistorialAcademico());
				JSFUtils.infoMessage("Actualización Historial Académico: ", "Se realizo correctamente");
			}

			this.view.setHistorialAcademico(new HistorialAcademicoDTO());
			this.view.setMostrarPanelHistorialAcedemico(false);
			this.view.setAccionHistorialAcademico("Registrar");

			this.listaHistorialAcademico();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void registrarActualizarExperienciaLaboral() {
		try {

			ExperienciaLaboralAspiranteDTO experienciaLaboral = view.getActualizacionExperienciaLaboral();

			experienciaLaboral.setIdAspirante(this.view.getIdAspirante());

			if (this.view.getAccionExperienciaLaboral().equals("Registrar")) {
				this.bolsaTrabajoEJB.crearExperienciaLaboralAspirante(experienciaLaboral);
				JSFUtils.infoMessage("Registro Experiencia Laboral: ", "Se realizo correctamente");
			} else if (this.view.getAccionExperienciaLaboral().equals("Actualizar")) {
				this.bolsaTrabajoEJB.actualizarExperienciaLaboralAspirante(experienciaLaboral);
				JSFUtils.infoMessage("Actualización Experiencia Laboral: ", "Se realizo correctamente");
			}

			cerrarNuevoRegistroExperiencia();

			this.listaExperienciaLaboral();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void registrarActualizarEncuestaPersonal() {
		try {

			HabilidadesPersonalesAspiranteDTO escuestaPersonal = this.view.getHabilidadesPersonales();
			escuestaPersonal.setIdAspirante(this.view.getIdAspirante());

			if (this.view.getAccionEncuestaPersonal().equals("Registrar")) {
				this.bolsaTrabajoEJB.crearHabilidadesPersonalesAspirante(escuestaPersonal);
				JSFUtils.infoMessage("Registro Habilidades Personales: ", "Se realizo correctamente");
			}
			if (this.view.getAccionEncuestaPersonal().equals("Actualizar")) {
				this.bolsaTrabajoEJB.actualizarHabilidadesPersonalesAspirante(escuestaPersonal);
				JSFUtils.infoMessage("Actualización Habilidades Personales: ", "Se realizo correctamente");
			}

			this.menu(HABILIDADES_PERSONALES);

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void seleccionarHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO) {
		this.view.setHistorialAcademico(historialAcademicoDTO);
		this.view.setAccionHistorialAcademico("Actualizar");
		this.view.setMostrarPanelHistorialAcedemico(true);
	}

	public void seleccionarProfesion(ProfesionDTO profesionDTO) {
		this.view.setIdProfesion(profesionDTO.getIdProfesion());
		this.view.setProfesionDTO(profesionDTO);
		this.view.setAccionProfesion("Actualizar");
		this.view.setMostrarFormularioPofesion(true);
	}

	public void seleccionarEspecialidad(EspecialidadDTO especialidadDTO) {
		this.view.setIdEspecialidad(especialidadDTO.getIdEspecialidad());
		this.view.setEspecialidadDTO(especialidadDTO);
		this.view.setAccionEspecialidad("Actualizar");
		this.view.setMostrarFormularioEspecialidad(true);
	}

	public void seleccionarExperienciaLaboral(ExperienciaLaboralAspiranteDTO experienciaLaboralAspiranteDTO) {

		if (experienciaLaboralAspiranteDTO.getSolicitarInformacion()) {
			this.view.setMostrarRazonNosolicitar(true);
		} else {
			this.view.setMostrarRazonNosolicitar(false);
		}
		this.view.setMostrarFormularioExperiencia(true);
		this.view.setActualizacionExperienciaLaboral(experienciaLaboralAspiranteDTO);
		this.view.setAccionExperienciaLaboral("Actualizar");
	}

	public void eliminarHistorialAcademico() {

		try {
			bolsaTrabajoEJB.eliminarHistorialAcademico(this.view.getIdHistorialCademico());

			listaHistorialAcademico();

			JSFUtils.infoMessage("Historial academico: ", "Eliminado Correctamente");
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void dialogoProfesion(Integer idProfesionAspirante) {
		this.view.setIdProfesionAspirante(idProfesionAspirante);
	}

	public void dialogoEspecialidad(Integer idEspecialidadAspirante) {
		this.view.setIdEspecialidadAspirante(idEspecialidadAspirante);
	}

	public void eliminarProfesion() {

		try {
			profesion.eliminarProfesionAspirante(this.view.getIdProfesionAspirante());

			List<ProfesionDTO> profesiones = profesion.obtenerListaProfesionPorIdAspirante(this.view.getIdAspirante());

			this.view.setListaProfesion(profesiones);

			this.view.setIdProfesionAspirante(0);

			JSFUtils.infoMessage("Profesión: ", "Eliminado Correctamente");
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void eliminarEspecialidad() {

		try {

			especialidad.eliminarEspecialidadAspirante(this.view.getIdEspecialidadAspirante());

			this.view.setIdEspecialidadAspirante(0);
			List<EspecialidadDTO> especialidades = especialidad
					.obtenerListaEspecialidadPorIdAspirante(this.view.getIdAspirante());

			this.view.setListaEspecialidad(especialidades);

			JSFUtils.infoMessage("Especialidad: ", "Eliminado Correctamente");
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void eliminarExperienciaLaboral() {
		try {
			bolsaTrabajoEJB.eliminarExperienciaLaboral(this.view.getIdExperienciaLaboral());

			listaExperienciaLaboral();

			JSFUtils.infoMessage("Experiencia Laboral: ", "Eliminado Correctamente");

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());

		}
	}

	public void listaHistorialAcademico() {
		try {

			List<HistorialAcademicoDTO> listaHistorialAcademico = bolsaTrabajoEJB
					.obtenerListaHistorialAcademico(this.view.getIdAspirante());

			if (!listaHistorialAcademico.isEmpty()) {
				view.setListaHistorialAcademicoAspirante(listaHistorialAcademico);
			} else {
				view.setListaHistorialAcademicoAspirante(new ArrayList<HistorialAcademicoDTO>());
			}

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void listaExperienciaLaboral() {
		try {

			List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral = bolsaTrabajoEJB
					.obtenerListaExperienciaLaboral(this.view.getIdAspirante());

			if (!listaExperienciaLaboral.isEmpty()) {
				this.view.setListaExperienciaLaboralAspirante(listaExperienciaLaboral);
			} else {
				this.view.setListaExperienciaLaboralAspirante(new ArrayList<ExperienciaLaboralAspiranteDTO>());

			}

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void mostrarPanelDatosAspirante(Integer idAspirante) {

		try {

			this.view.setMostrarResultados(false);

			this.view.setIdAspirante(idAspirante);

			this.view.setMostrarPanelConsultaAspirante(false);
			this.view.setMostrarPanelDatosAspirante(true);
			this.view.setMostrarDatosPersonales(true);

			AspiranteDTO aspirante = empleadoEJB.consultaAspirantePorIdentificador(this.view.getIdAspirante());

			List<String> personasDependientes = new ArrayList<String>();

			// VALIDANDO DE PERSONAS QUE DEPENDEN DE EL aspirante
			if (aspirante.getNumeroConyuges() != 0) {
				personasDependientes.add("CONYUGE");
			}

			if (aspirante.getNumeroHijos() != 0) {
				personasDependientes.add("HIJOS");
			}

			if (aspirante.getNumeroPadres() != 0) {
				personasDependientes.add("PADRES");
			}

			if (aspirante.getNumeroOtros() != 0) {
				personasDependientes.add("OTROS");
			}

			this.view.setPersonasDependientes(personasDependientes);

			this.view.setActualizacionAspirante(aspirante);
			// this.actualizacionAspirante = aspirante;
			// Llamar complementos del registro
			// poblacionesPorMunicipio();

			this.view.setListaEstados(SelectItemsUtil.listaCatalogos(catalogosEJB.listaEstados()));
			municipiosPorEstado();
			asentamientosPorMunicipio();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void mostrarPanelAdjuntarDocumento() {
		view.setMostrarDatosPersonales(false);
		view.setMostrarHistorialAcademico(false);
		view.setMostrarExperienciaLaboral(false);
		view.setMostrarEscuestaPersonal(false);
		view.setMostrarAdjuntarDocumento(true);

		// view.setListaDocumentoAdjunto(documentoAdjuntoEJB
		// .listaDocumentoAdjunto(idAspirante));

	}

	public void mostrarNuevoRegistroProfesion() {
		this.view.setMostrarFormularioPofesion(true);
		this.view.setAccionProfesion("Registrar");
		this.view.setIdProfesion(0);
	}

	public void cerrarNuevoRegistroProfesion() {
		this.view.setMostrarFormularioPofesion(false);
		this.view.setAccionProfesion("Registrar");
		this.view.setIdProfesion(0);
	}

	public void mostrarNuevoRegistroEspecialidad() {
		this.view.setMostrarFormularioEspecialidad(true);
		this.view.setAccionEspecialidad("Registrar");
		this.view.setIdEspecialidad(0);
	}

	public void cerrarNuevoRegistroEspecialidad() {
		this.view.setMostrarFormularioEspecialidad(false);
		this.view.setAccionEspecialidad("Registrar");
		this.view.setIdEspecialidad(0);
	}

	public void mostrarNuevoRegistroExperiencia() {
		this.view.setMostrarFormularioExperiencia(true);
		this.view.setAccionExperienciaLaboral("Registrar");
		this.view.setActualizacionExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());
	}

	public void cerrarNuevoRegistroExperiencia() {
		this.view.setMostrarFormularioExperiencia(false);
		this.view.setAccionExperienciaLaboral("Registrar");
		this.view.setActualizacionExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());

	}

	// AJAx

	public void municipiosPorEstado() {
		this.view.setListaMunicipios(new ArrayList<SelectItem>());
		this.view.setListaAsentamientos(new ArrayList<SelectItem>());
		Integer idEstado = this.view.getActualizacionAspirante().getDireccionDTO().getIdEstado();

		if (ValidacionUtil.esNumeroPositivo(idEstado)) {
			this.view.setListaMunicipios(
					SelectItemsUtil.listaCatalogos(catalogosEJB.consultarMunicipiosPorEstado(idEstado)));
		}

	}

	public void asentamientosPorMunicipio() {
		Integer idMunicipio = this.view.getActualizacionAspirante().getDireccionDTO().getIdMunicipio();

		if (ValidacionUtil.esNumeroPositivo(idMunicipio)) {

			this.view.setListaAsentamientos(
					SelectItemsUtil.listaCatalogos(catalogosEJB.consultarAsantamientosPorMunicipios(idMunicipio)));
		}

	}

	public void mostrarRazonNoSolicitarInformacion() {
		if (view.getActualizacionExperienciaLaboral().getSolicitarInformacion()) {
			view.getActualizacionExperienciaLaboral().setRazonNoSolicitar("");
			view.setMostrarRazonNosolicitar(true);
		} else {
			view.getActualizacionExperienciaLaboral().setRazonNoSolicitar("");
			view.setMostrarRazonNosolicitar(false);
		}
	}

	public void mostrarTxtOtroMedio() {
		if (view.getHabilidadesPersonales().getAnuncio()) {
			// view.getActualizaciónEncuestaPersonal().setOtroMedio("");
			view.setMostrarTxtOtroMedio(false);
		} else {
			view.getHabilidadesPersonales().setOtroMedio("");
			view.setMostrarTxtOtroMedio(true);
		}
	}

	public void mostrarTxtNombresParientes() {
		if (view.getHabilidadesPersonales().isParientes()) {
			// view.getActualizaciónEncuestaPersonal().setNombreParientes("");
			view.setMostrarTxtNombresParientes(false);
		} else {
			view.getHabilidadesPersonales().setNombreParientes("");
			view.setMostrarTxtNombresParientes(true);
		}
	}

	public void mostrarTxtNombreAfianza() {
		if (view.getHabilidadesPersonales().isAfianzado()) {
			// view.getActualizaciónEncuestaPersonal().setNombreAfianza("");
			view.setMostrarTxtNombreAfianza(false);
		} else {
			view.getHabilidadesPersonales().setNombreAfianza("");
			view.setMostrarTxtNombreAfianza(true);
		}
	}

	public void mostrarTxtNombreSindicato() {
		if (view.getHabilidadesPersonales().isSindicato()) {
			// view.getActualizaciónEncuestaPersonal().setNombreSindicato("");
			view.setMostrarTxtNombreSindicato(false);
		} else {
			view.getHabilidadesPersonales().setNombreAfianza("");
			view.setMostrarTxtNombreSindicato(true);
		}
	}

	public void mostrarTxtNombreSegurVida() {
		if (view.getHabilidadesPersonales().isSeguroVida()) {
			// view.getActualizaciónEncuestaPersonal().setNombreSeguroVida("");
			view.setMostrarTxtNombreSegurVida(false);
		} else {
			view.getHabilidadesPersonales().setNombreSeguroVida("");
			view.setMostrarTxtNombreSegurVida(true);
		}
	}

	public void mostrarTxtRazonNoViajar() {
		if (!view.getHabilidadesPersonales().isDisposicionViajar()) {
			// view.getActualizaciónEncuestaPersonal().setRazonNoViajar("");
			view.setMostrarTxtRazonNoViajar(false);
		} else {
			view.getHabilidadesPersonales().setRazonNoViajar("");
			view.setMostrarTxtRazonNoViajar(true);
		}
	}

	public void mostrarTxtRazonNoCambioResidencia() {
		if (!view.getHabilidadesPersonales().isCambioResidencia()) {
			// view.getActualizaciónEncuestaPersonal().setRazonNoCambioResidencia("");
			view.setMostrarTxtRazonNoCambioResidencia(false);
		} else {
			view.getHabilidadesPersonales().setRazonNoCambioResidencia("");
			view.setMostrarTxtRazonNoCambioResidencia(true);
		}
	}

	public void mostrarTxtOtrosIngresos() {
		if (view.getHabilidadesPersonales().isOtroIngreso()) {
			// view.getActualizaciónEncuestaPersonal().setNombreOtroIngreso("");
			// view.getActualizaciónEncuestaPersonal().setImporteOtroIngreso(new
			// BigDecimal(0));
			view.setMostrarTxtOtrosIngresos(false);
		} else {
			view.getHabilidadesPersonales().setNombreOtroIngreso("");
			view.getHabilidadesPersonales().setImporteOtroIngreso(new BigDecimal(0));
			view.setMostrarTxtOtrosIngresos(true);
		}
	}

	public void mostrarTxtConyugeTrabaja() {
		if (view.getHabilidadesPersonales().isConyugeTrabajando()) {
			// view.getActualizaciónEncuestaPersonal().setNombreTrabajoConyuge("");
			// view.getActualizaciónEncuestaPersonal().setPercepcionMensualConyuge(new
			// BigDecimal(0));
			view.setMostrarTxtConyugeTrabaja(false);
		} else {
			view.getHabilidadesPersonales().setNombreTrabajoConyuge("");
			view.getHabilidadesPersonales().setPercepcionMensualConyuge(new BigDecimal(0));
			view.setMostrarTxtConyugeTrabaja(true);
		}
	}

	public void mostrarTxtCasaPropia() {
		if (view.getHabilidadesPersonales().isCasaPropia()) {
			// view.getActualizaciónEncuestaPersonal().setValorAproximadoCasa(new
			// BigDecimal(0));
			view.setMostrarTxtCasaPropia(false);
		} else {
			view.getHabilidadesPersonales().setValorAproximadoCasa(new BigDecimal(0));
			view.setMostrarTxtCasaPropia(true);
		}
	}

	public void mostrarTxtPagaRenta() {
		if (view.getHabilidadesPersonales().isRentaCasa()) {
			// view.getActualizaciónEncuestaPersonal().setRentaMensual(new
			// BigDecimal(0));
			view.setMostrarTxtPagaRenta(false);
		} else {
			view.getHabilidadesPersonales().setRentaMensual(new BigDecimal(0));
			view.setMostrarTxtPagaRenta(true);
		}
	}

	public void mostrarTxtAutomovilPropio() {
		if (view.getHabilidadesPersonales().isAutomovilPropio()) {
			// view.getActualizaciónEncuestaPersonal().setMarcaAutomovil("");
			// view.getActualizaciónEncuestaPersonal().setModeloAutomovil("");
			view.setMostrarTxtAutomovilPropio(false);
		} else {
			view.getHabilidadesPersonales().setMarcaAutomovil("");
			view.getHabilidadesPersonales().setModeloAutomovil("");
			view.setMostrarTxtAutomovilPropio(true);
		}
	}

	public void mostrarTxtTieneDeudas() {
		if (view.getHabilidadesPersonales().isDeudas()) {
			// view.getActualizaciónEncuestaPersonal().setNombreDeuda("");
			// view.getActualizaciónEncuestaPersonal().setImporteDeuda(new
			// BigDecimal(0));
			// view.getActualizaciónEncuestaPersonal().setAbonoMensualDeuda(new
			// BigDecimal(0));
			view.setMostrarTxtTieneDeudas(false);
		} else {
			view.getHabilidadesPersonales().setNombreDeuda("");
			view.getHabilidadesPersonales().setImporteDeuda(new BigDecimal(0));
			view.getHabilidadesPersonales().setAbonoMensualDeuda(new BigDecimal(0));
			view.setMostrarTxtTieneDeudas(true);
		}
	}

	/***
	 * Obteniendo el id historial academico seleccionado
	 * 
	 * @param historialSeleccionado
	 */
	public void historialAcademicoSeleccionado(HistorialAcademicoDTO historialSeleccionado) {
		this.view.setIdHistorialCademico(historialSeleccionado.getIdHistorialAcademico());
	}

	/**
	 * Obteniendo el id experiencia laboral seleccionado
	 * 
	 * @param experienciaLaboral
	 */
	public void experienciaLaboralSeleccionado(ExperienciaLaboralAspiranteDTO experienciaLaboral) {
		this.view.setIdExperienciaLaboral(experienciaLaboral.getIdExperienciaLaboralAspirante());
	}

	public void mostrarNuevoRegistroHistorialAcademico() {
		this.view.setMostrarPanelHistorialAcedemico(true);
		this.view.setHistorialAcademico(new HistorialAcademicoDTO());
		this.view.setAccionHistorialAcademico("Registrar");
	}

	public void registrarActualizarProfesion() {
		try {

			if (this.view.getAccionProfesion().equals("Registrar")) {
				profesion.crearProfesionAspirante(this.view.getIdProfesion(), this.view.getIdAspirante());
				JSFUtils.infoMessage("Registro Profesión: ", "Se finalizo correctamente");
			}

			if (this.view.getAccionProfesion().equals("Actualizar")) {

				this.view.getProfesionDTO().setIdProfesion(this.view.getIdProfesion());

				profesion.actualizarProfesionAspirante(this.view.getProfesionDTO(), this.view.getIdAspirante());
				JSFUtils.infoMessage("Actualización Profesión: ", "Se finalizo correctamente");
			}

			this.view.setIdProfesion(0);
			List<ProfesionDTO> profesiones = profesion.obtenerListaProfesionPorIdAspirante(this.view.getIdAspirante());

			this.view.setListaProfesion(profesiones);
			cerrarNuevoRegistroProfesion();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void registrarActualizarEspecialidad() {
		try {

			if (this.view.getAccionEspecialidad().equals("Registrar")) {
				especialidad.crearEspecialidadAspirante(this.view.getIdEspecialidad(), this.view.getIdAspirante());
				JSFUtils.infoMessage("Registro Especialidad: ", "Se finalizo correctamente");
			}

			if (this.view.getAccionEspecialidad().equals("Actualizar")) {
				this.view.getEspecialidadDTO().setIdEspecialidad(this.view.getIdEspecialidad());
				especialidad.actualizarEspecialidadAspirante(this.view.getEspecialidadDTO(),
						this.view.getIdAspirante());
				JSFUtils.infoMessage("Actualización Especialidad: ", "Se finalizo correctamente");
			}

			List<EspecialidadDTO> especialidades = especialidad
					.obtenerListaEspecialidadPorIdAspirante(this.view.getIdAspirante());

			this.view.setListaEspecialidad(especialidades);
			this.view.setIdEspecialidad(0);

			cerrarNuevoRegistroEspecialidad();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	/****************** Validacion **************************/

	public void validarConsulta(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {
		case "criterio":

			String criterio = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(criterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese el criterio");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	public void validatorDatosGenerales(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "nombre":
			String nombre = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombre)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un nombre.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "apellidoPaterno":
			String apellidoPaterno = (String) value;

			if (ValidacionUtil.esCadenaVacia(apellidoPaterno)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un apellido paterno.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "apellidoMaterno":
			String apellidoMaterno = (String) value;

			if (ValidacionUtil.esCadenaVacia(apellidoMaterno)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un apellido materno.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "rfc":
			String rfc = (String) value;

			if (ValidacionUtil.esCadenaVacia(rfc)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un RFC.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			} else {
				try {

					if (!bolsaTrabajoEJB.validarRfcyIdAspirante(this.view.getIdAspirante(), rfc)) {
						FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"El RFC ya se encuentra registrado");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
					}
				} catch (ReglaNegocioException exception) {
					FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							exception.getMessage());
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}

			}

			break;

		case "curp":
			String curp = (String) value;

			if (ValidacionUtil.esCadenaVacia(curp)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una CURP.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			} else {

				try {

					if (!bolsaTrabajoEJB.validarCurpyIdAspirante(this.view.getIdAspirante(), curp)) {
						FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"La CURP ya se encuentra registrado");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
					}
				} catch (ReglaNegocioException exception) {
					FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							exception.getMessage());
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}

			}
			break;

		case "sexo":
			String sexo = (String) value;

			if (ValidacionUtil.esCadenaVacia(sexo)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un sexo.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "estadoCivil":
			String estadoCivil = (String) value;

			if (ValidacionUtil.esCadenaVacia(estadoCivil)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un estado civil.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "fechaNacimiento":
			Date fechaNacimiento = (Date) value;

			if (fechaNacimiento == null) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una fecha de nacimiento.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			} else {
				if (ValidacionUtil.esFechaFutura(fechaNacimiento)) {
					FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una fecha que no sea mayor a la fecha actual.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
			}
			break;
		case "lugarNacimiento":
			String lugarNacimiento = (String) value;

			if (ValidacionUtil.esCadenaVacia(lugarNacimiento)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un lugar de nacimiento.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
/*
		case "estatura":
			float estatura = (float) value;

			if (estatura <= 0) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la estatura.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "tipoSangre":
			String tipoSangre = (String) value;

			if (ValidacionUtil.esCadenaVacia(tipoSangre)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione el tipo de sangre.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "peso":
			float peso = (float) value;

			if (peso <= 0) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el peso.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
*/
		case "puesto":
			Integer puesto = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(puesto)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione el puesto.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		}

	}

	public void validatorDomicilio(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();

		if (nombreComponete.equals("estado")) {
			Integer estado = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(estado)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un estado.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
		}

		if (nombreComponete.equals("municipio")) {
			Integer municipio = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(municipio)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un municipio.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
		}

		if (nombreComponete.equals("asentamiento")) {
			Integer poblacion = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(poblacion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un asentamiento.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
		}

		if (nombreComponete.equals("calle")) {
			String calle = (String) value;

			if (ValidacionUtil.esCadenaVacia(calle)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una calle.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
		}
		if (nombreComponete.equals("exterior")) {
			String exterior = (String) value;

			if (ValidacionUtil.esCadenaVacia(exterior)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un número exterior.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
		}

		if (nombreComponete.equals("cp")) {
			Integer codigoPostal = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(codigoPostal)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el codigo postal.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
		}
	}

	public void validatorDatosHistorialAcademico(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "escolaridad":
			Integer escolaridad = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(escolaridad)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione la escolaridad.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "institucion":
			String institucion = (String) value;

			if (ValidacionUtil.esCadenaVacia(institucion)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el nombre de la institución.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "comprobanteEstudio":
			Integer comprobanteEstudio = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(comprobanteEstudio)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione el comprobante de estudio.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "fechaInicial":
			Date fechaInicial = (Date) value;

			if (fechaInicial == null) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una fecha de inicial.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			} else {
				if (ValidacionUtil.esFechaFutura(fechaInicial)) {
					FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una fecha que no sea mayor a la fecha actual.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
			}
			break;

		case "nombreCurso":
			String nombreCurso = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombreCurso)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el nombre de la escolaridad.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		}

	}

	public void validarCampoProfesionEspecialidad(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "tipoProfesion":

			Integer tipoProfesion = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoProfesion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo profesión");
				context.addMessage(component.getClientId(), facesMessage);

				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoEspecialidad":

			Integer tipoEspecialidad = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoEspecialidad)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo especialidad");
				context.addMessage(component.getClientId(), facesMessage);

				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			JSFUtils.errorMessage("Registro Aspirante: ", "Error de validación...");
			break;
		}

	}

	public void validatorExperienciaLaboral(FacesContext context, UIComponent component, Object value) {
		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "nombreEmpresa":
			String nombreEmpresa = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombreEmpresa)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la empresa o institución donde laboró.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		case "puestoDesempeniado":
			String puestoDesempeniado = (String) value;

			if (ValidacionUtil.esCadenaVacia(puestoDesempeniado)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el puesto o labores que desempeñó.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		case "anioInicial":
			Date anioInicial = (Date) value;

			if (anioInicial == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la fecha en la que inició sus labores.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		case "anioFinal":
			Date anioFinal = (Date) value;

			if (anioFinal == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la fecha en la que finalizó sus labores.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		default:
			break;
		}

	}

	public void validatorHabilidadPersonal(FacesContext context, UIComponent component, Object value) {
		String nombreComponente = component.getId();
		switch (nombreComponente) {
		case "idioma":
			String idioma = (String) value;

			if (ValidacionUtil.esCadenaVacia(idioma)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el idioma(s) que domine.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		case "nivelIdioma":
			Integer nivelIdioma = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(nivelIdioma)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione el nivel idioma(s) que domine.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		case "maquina":
			String maquina = (String) value;

			if (ValidacionUtil.esCadenaVacia(maquina)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la maquina de oficina o de taller que domine.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		case "funciones":
			String funciones = (String) value;

			if (ValidacionUtil.esCadenaVacia(funciones)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese la funcion(es) de oficina que domine.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		case "softwareDomina":
			String softwareDomina = (String) value;

			if (ValidacionUtil.esCadenaVacia(softwareDomina)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el software(s) que domine.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;

		default:
			break;
		}
	}

	/**************** Getters and Setters ************/

	public ActualizacionAspiranteView getView() {
		return view;
	}

	public void setView(ActualizacionAspiranteView view) {
		this.view = view;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
