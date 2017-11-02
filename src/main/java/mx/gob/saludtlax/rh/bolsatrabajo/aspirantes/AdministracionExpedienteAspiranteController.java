/*
 * AdministracionExpedienteAspiranteController.java
 * Creado el Oct 21, 2016 12:27:37 PM
 * 
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.aspirante.AdjuntoAspirante;
import mx.gob.saludtlax.rh.expediente.aspirante.ExpedienteAspirante;
import mx.gob.saludtlax.rh.expediente.aspirante.ExpedienteAspiranteDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "administracionExpedienteAspirante")
@ViewScoped
public class AdministracionExpedienteAspiranteController implements Serializable {

	private static final long serialVersionUID = -3430132076897945549L;

	@Inject
	private AdjuntoAspirante adjuntoAspirante;
	@Inject
	private BolsaTrabajo bolsaTrabajo;
	@Inject
	private Catalogo catalogo;
	@Inject
	private ExpedienteAspirante expedienteAspirante;

	private AdministrarExpedienteAspiranteView view;

	/**
	 * Crea una nueva instancia de AdministracionExpedienteAspiranteController
	 */
	public AdministracionExpedienteAspiranteController() {
		view = new AdministrarExpedienteAspiranteView();
	}

	@PostConstruct
	public void init() {
		view.setListaEscolaridades(SelectItemsUtil.listaCatalogos(catalogo.listaEscolaridades()));
		view.setListaComprobantesEstudios(SelectItemsUtil.listaCatalogos(catalogo.listaComprobantesEstudios()));
	}

	public void consultarAspirantes(ActionEvent actionEvent) {
		try {
			FiltroDTO filtroDTO = new FiltroDTO();
			filtroDTO.setCriterio(view.getCriterio());
			filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP);

			view.setAspirantes(bolsaTrabajo.consultarPorCriterio(filtroDTO));
			view.setMostrarPanelCorrecciones(false);
			view.setMostrarResultadoConsulta(true);
		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		}

	}

	public void seleccionarAspirante(Integer idAspirante, String estatus) {
		view.setIdAspiranteSeleccionado(idAspirante);
		view.setMostrarPanelCorrecciones(true);
		view.setMostrarResultadoConsulta(false);

		if (expedienteAspirante.tieneExpedienteAperturado(view.getIdAspiranteSeleccionado())) {
			view.setImagenExpediente("expediente_aperturado.png");
			view.setNumeroExpediente(expedienteAspirante.numeroExpedienteAspirante(view.getIdAspiranteSeleccionado()));
			view.setIdExpediente(expedienteAspirante.obtenerIdExpedienteAspirante(view.getIdAspiranteSeleccionado()));

			view.setListaTiposDocumentosExpediente(
					SelectItemsUtil.listaCatalogos(catalogo.consultarDocumentosExpedientesClasificacion(
							EnumClasificacionExpediente.DOCUMENTOS_BOLSA_TRABAJO)));
			view.setDocumentosExpedientes(
					adjuntoAspirante.consultarInformacionAdjuntosPorIdAspirante(view.getIdAspiranteSeleccionado()));

			view.setHistorialesAcademicos(
					bolsaTrabajo.obtenerListaHistorialAcademico(view.getIdAspiranteSeleccionado()));
			view.setMostrarActualizacionExpediente(true);
		} else {
			view.setImagenExpediente("sin_expediente.png");
			view.setExpediente(new ExpedienteAspiranteDTO());
			view.setMostrarAperturaExpediente(true);
		}
	}

	public void aperturarExpediente(ActionEvent actionEvent) {
		view.getExpediente().setIdAspirante(view.getIdAspiranteSeleccionado());
		expedienteAspirante.crearExpediente(view.getExpediente());

		view.setNumeroExpediente(view.getExpediente().getNumeroExpediente().toUpperCase());
		view.setImagenExpediente("expediente_aperturado.png");
		view.setIdExpediente(expedienteAspirante.obtenerIdExpedienteAspirante(view.getIdAspiranteSeleccionado()));
		view.setListaTiposDocumentosExpediente(SelectItemsUtil.listaCatalogos(catalogo
				.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_BOLSA_TRABAJO)));
		view.setDocumentosExpedientes(
				adjuntoAspirante.consultarInformacionAdjuntosPorIdAspirante(view.getIdAspiranteSeleccionado()));

		view.setHistorialesAcademicos(bolsaTrabajo.obtenerListaHistorialAcademico(view.getIdAspiranteSeleccionado()));

		view.setMostrarAperturaExpediente(false);
		view.setMostrarActualizacionExpediente(true);
	}

	public void subirDocumentoAdjunto(FileUploadEvent evento) {
		UploadedFile archivo = evento.getFile();
		String nombreAdjunto = archivo.getFileName();
		byte[] adjunto = archivo.getContents();

		TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(archivo.getContentType());

		Integer idAspirante = (Integer) evento.getComponent().getAttributes().get("idAspirante");
		Integer idDocAdj = (Integer) evento.getComponent().getAttributes().get("idDocAdj");

		InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

		DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
		dto.setIdDocumentoAdjuntable(idDocAdj);
		info.setEntidadContexto(EntidadContexto.ASPIRANTE);
		info.setIdEntidadContexto(idAspirante);
		info.setIdAdjunto(null);
		info.setNombreAdjunto(nombreAdjunto);
		info.setExtension(extension);
		info.setDocumentoAdjuntable(dto);
		info.setIdAspirante(idAspirante);
		info.setIdExpediente(view.getIdExpediente());

		adjuntoAspirante.crear(info, adjunto);
		view.getDocumentosExpedientes().clear();
		List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoAspirante
				.consultarInformacionAdjuntosPorIdAspirante(view.getIdAspiranteSeleccionado());
		view.setDocumentosExpedientes(documentosExpedientes);
	}

	public void visualizarImagen(Integer idImagenExpediente) {
		view.setIdImagenExpediente(idImagenExpediente);
		view.setMostrarImagenExpediente(true);
	}

	public void visualizarRegistroHistorial(ActionEvent actionEvent) {
		if (expedienteAspirante.tieneExpedienteAperturado(view.getIdAspiranteSeleccionado())) {
			view.setMostrarHistorialAcademico(true);
			view.setMostrarAdjuntarDocumentoHistorial(false);
		} else {
			JSFUtils.warningMessage("El expedienete no esta aperturado.",
					"Debe aperturar el expediente para poder agregar un historial.");
		}
	}

	public void visualizarAdjuntarDocumentoHistorial(HistorialAcademicoDTO historialAcademicoDTO) {
		view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);

		List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto = adjuntoAspirante
				.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto.HISTORIAL_ACADEMICO,
						view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());
		List<String> descripciones = new ArrayList<>();

		for (InformacionAdjuntoDTO informacionAdjuntoDTO : consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto) {
			descripciones.add(informacionAdjuntoDTO.getDocumentoAdjuntable().getDescripcion());
		}

		this.view.setDocumentosExpedientes(consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto);

		view.setDocumentacionActualHistorial(descripciones);
		view.setListaDocumentosHistorialAcademico(SelectItemsUtil.listaCatalogos(catalogo
				.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_HISTORIAL)));

		view.setMostrarHistorialAcademico(false);
		view.setMostrarAdjuntarDocumentoHistorial(true);
	}

	public void eliminarAdjunto(Integer idAdjunto) {
		adjuntoAspirante.elimnar(idAdjunto);
		view.getDocumentosExpedientes().clear();

		List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoAspirante
				.consultarInformacionAdjuntosPorIdAspirante(this.view.getIdAspiranteSeleccionado());
		view.setDocumentosExpedientes(documentosExpedientes);
		
//		visualizarAdjuntarDocumentoHistorial(this.view.getHistorialAcademicoSeleccionado());
		
		JSFUtils.infoMessageEspecifico("info", "", "El documento se ha eliminado correctamente.");
	}

	public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {
		try {
			byte[] bytes = adjuntoAspirante.obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

			JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(), adjunto.getExtension().getMIMEType());
			JSFUtils.infoMessage("Descarga iniciada", "La descarga del archivo ha iniciado.");
		} catch (IOException e) {
			JSFUtils.errorMessage("Error al iniciar la descarga", "No se ha logrado iniciar la descarga del archivo.");
		}
	}

	public void registrarHistorialAcademico(ActionEvent actionEvent) {
		view.getHistorial().setAspirante(view.getIdAspiranteSeleccionado());
		bolsaTrabajo.crearHistorialAcademicoAspirante(view.getHistorial());
		view.setHistorialesAcademicos(bolsaTrabajo.obtenerListaHistorialAcademico(view.getIdAspiranteSeleccionado()));
		view.setHistorial(new HistorialAcademicoDTO());

		view.setMostrarHistorialAcademico(false);
		JSFUtils.infoMessageEspecifico("info", "", "El historial academico ha sido registrado con éxito.");
	}

	public void subirDocumentoAdjuntoHistorial(FileUploadEvent evento) {
		if (!ValidacionUtil.esNumeroPositivo(view.getIdDocumentoAdjuntableHistorial())) {
			JSFUtils.errorMessage("Error al adjuntar",
					"Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.");
		} else if (ValidacionUtil.esCadenaVacia(view.getNumeroExpediente())) {
			JSFUtils.errorMessage("Adjuntar documento historial",
					"Por favor aperture el expediente para poder adjuntarle el documento");
		} else {
			UploadedFile archivo = evento.getFile();
			String nombreAdjunto = archivo.getFileName();
			byte[] adjunto = archivo.getContents();

			TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(archivo.getContentType());

			InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

			DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
			dto.setIdDocumentoAdjuntable(view.getIdDocumentoAdjuntableHistorial());

			info.setEntidadContexto(EntidadContexto.HISTORIAL_ACADEMICO);
			info.setIdEntidadContexto(view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());

			info.setIdAdjunto(null);
			info.setNombreAdjunto(nombreAdjunto);
			info.setExtension(extension);
			info.setDocumentoAdjuntable(dto);
			info.setIdAspirante(view.getIdAspiranteSeleccionado());
			info.setIdExpediente(this.view.getIdExpediente());

			view.setMostrarAdjuntarDocumentoHistorial(false);

			adjuntoAspirante.crear(info, adjunto);
			view.getHistorialAcademicoSeleccionado().setTieneDocumentacion(true);
			bolsaTrabajo.actualizarHistorialAcademicoAspirante(view.getHistorialAcademicoSeleccionado());
			view.setHistorialesAcademicos(
					bolsaTrabajo.obtenerListaHistorialAcademico(view.getIdAspiranteSeleccionado()));
		}
	}

	public void mostrarDuracion(ValueChangeEvent valueChangeEvent) {
		Integer idEscolaridad = (Integer) valueChangeEvent.getNewValue();

		if (idEscolaridad > 10) {
			view.setVisualizarDatosCurso(true);
		} else {
			view.setVisualizarDatosCurso(false);
		}
	}

	public void validarBusqueda(FacesContext context, UIComponent component, Object value) {
		switch (component.getId()) {
		case "txtCriterioBusqueda":
			String criterio = (String) value;

			if (ValidacionUtil.esCadenaVacia(criterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			if (criterio.trim().length() < 5) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		default:
			// No hacer nada
			break;
		}
	}

	public void validarExpediente(FacesContext context, UIComponent component, Object value) {

		switch (component.getId()) {
		case "txtNumeroExpediente":
			String numeroExpediente = (String) value;

			if (ValidacionUtil.esCadenaVacia(numeroExpediente)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Ingrese un número de expediente");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		default:
			break;
		}
	}

	public void validarHistorialAcademico(FacesContext context, UIComponent component, Object value) {
		switch (component.getId()) {
		case "escolaridad":
			Integer escolaridad = (Integer) value;

			if (ValidacionUtil.esMenorQueUno(escolaridad)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione una escolaridad");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "institucion":
			String institucion = (String) value;

			if (ValidacionUtil.esCadenaVacia(institucion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Indique el nombre de una institución");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "documentoComprobatorio":
			Integer documentoComprobatorio = (Integer) value;

			if (ValidacionUtil.esMenorQueUno(documentoComprobatorio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione el último comprobante estudio del Grado Academico");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "fechaInicial":
			Date fechaInicial = (Date) value;

			if (fechaInicial == null) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor indique una fecha inicial.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "nombreCurso":
			String nombreCurso = (String) value;

			if (ValidacionUtil.esCadenaVacia(nombreCurso)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Indique el nombre de la escolaridad cursada");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "duracion":
			String duracion = (String) value;

			if (view.getVisualizarDatosCurso() && ValidacionUtil.esCadenaVacia(duracion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Indique la duración de la Escolaridad Complementaria");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Obtiene el valor de view
	 *
	 * @return el valor de view
	 */
	public AdministrarExpedienteAspiranteView getView() {
		return view;
	}

	/**
	 * Pone el valor de view
	 *
	 * @param view
	 *            nuevo valor de view
	 */
	public void setView(AdministrarExpedienteAspiranteView view) {
		this.view = view;
	}

}
