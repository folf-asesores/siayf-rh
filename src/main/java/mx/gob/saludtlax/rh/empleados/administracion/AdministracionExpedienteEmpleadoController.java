/*
 * AdministracionExpedienteEmpleadoController.java
 * Creado el Oct 18, 2016 11:53:40 AM
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.expediente.empleado.ExpedienteEmpleado;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademico;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.historialacademico.NuevoHistorialDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "administracionExpedienteEmpleado")
@ViewScoped
public class AdministracionExpedienteEmpleadoController implements Serializable {

    private static final long serialVersionUID = 7543246042385364144L;
    private static final Logger LOGGER = Logger.getLogger(AdministracionExpedienteEmpleadoController.class.getName());

    @Inject
    private AdjuntoEmpleado adjuntoEmpleado;
    @Inject
    private Catalogo catalogo;
    @Inject
    private Empleado empleado;
    @Inject
    private ExpedienteEmpleado expedienteEmpleado;
    @Inject
    private HistorialAcademico historialAcademico;

    private AdministracionExpedienteEmpleadoView view;

    public AdministracionExpedienteEmpleadoController() {
        view = new AdministracionExpedienteEmpleadoView();
    }

    @PostConstruct
    public void inicio() {
        view.setListaEscolaridades(SelectItemsUtil.listaCatalogos(catalogo.listaEscolaridades()));
        view.setListaComprobantesEstudios(SelectItemsUtil.listaCatalogos(catalogo.listaComprobantesEstudios()));

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());
    }

    /**
     * Permite consultar la los empleados que coinciden con el criterio de
     * búsqueda.
     *
     * @param actionEvent
     *            el evento.
     */
    public void consultarEmpleados(ActionEvent actionEvent) {
        view.setMostrarPanelCorrecciones(false);
        view.setMostrarResultadoConsulta(true);

        view.setEmpleados(empleado.consultaPorCriterio(view.getCriterio()));

        if (view.getEmpleados().isEmpty()) {
            JSFUtils.infoMessageEspecifico("info", "", "No se encontraron registros con el criterio " + view.getCriterio());
        }
    }

    /**
     * Permite obtener la información necesaria del empleado para poder mostrar
     * la información de su expediente.
     *
     * @param idEmpleadoSeleccionado
     *            el ID del empleado.
     */
    public void seleccionarEmpleado(Integer idEmpleadoSeleccionado) {
        EmpleadoDetalladoDTO informacionEmpleado = empleado.obtenerInformacionEmpleado(idEmpleadoSeleccionado);
        view.setEmpleadoSeleccionado(informacionEmpleado);
        if (informacionEmpleado != null && EnumEstatusEmpleado.ACTIVO.equals(informacionEmpleado.getEstatus())) {
            view.setMostrarResultadoConsulta(false);
            view.setMostrarPanelCorrecciones(true);
            view.setMostrarExpediente(true);
            view.setIdEmpleadoSeleccionado(idEmpleadoSeleccionado);

            if (expedienteEmpleado.tieneExpedienteAperturado(view.getIdEmpleadoSeleccionado())) {
                String numeroExpediente = expedienteEmpleado.numeroExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                Integer idExpediente = expedienteEmpleado.obtenerIdExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                view.setImagenExpediente("expediente_aperturado.png");
                view.setMostrarActualizacionExpediente(true);
                view.setMostrarAperturaExpediente(false);
                view.setNumeroExpediente(numeroExpediente);
                view.setIdExpediente(idExpediente);

                List<CatalogoDTO> documentosPersonales = catalogo
                        .consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_PERSONALES);
                view.setListaTiposDocumentosExpediente(SelectItemsUtil.listaCatalogos(documentosPersonales));

                List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
                view.setDocumentosExpedientes(documentosExpedientes);

                List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
                List<CatalogoDTO> documentosAdjuntables = catalogo
                        .consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_HISTORIAL);
                view.setListaDocumentosHistorialAcademico(SelectItemsUtil.listaCatalogos(documentosAdjuntables));
                view.setHistorialesAcademicos(historial);
            } else {
                view.setImagenExpediente("sin_expediente.png");
                view.setMostrarAperturaExpediente(true);
                view.setMostrarActualizacionExpediente(false);
                view.setMostrarResultadoConsulta(false);
                view.setMostrarAdjuntarDocumentoHistorial(false);
                view.setHistorialesAcademicos(new ArrayList<HistorialAcademicoDTO>());
            }
        } else {
            JSFUtils.infoMessageEspecifico("info", "", "Empleado inactivo");
        }
    }

    /**
     * Permite actualizar el estado de la vista cuando la pestaña (tab) cambia.
     *
     * @param event
     *            el evento de cambio de pestaña.
     */
    public void onChange(TabChangeEvent event) {
        Tab tab = event.getTab();
        switch (tab.getId()) {
            case "tabExpediente":
                if (expedienteEmpleado.tieneExpedienteAperturado(view.getIdEmpleadoSeleccionado())) {
                    String numeroExpediente = expedienteEmpleado.numeroExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                    Integer idExpediente = expedienteEmpleado.obtenerIdExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                    view.setImagenExpediente("expediente_aperturado.png");
                    view.setMostrarActualizacionExpediente(true);
                    view.setMostrarAperturaExpediente(false);
                    view.setNumeroExpediente(numeroExpediente);
                    view.setIdExpediente(idExpediente);

                    List<CatalogoDTO> documentosPersonales = catalogo
                            .consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_PERSONALES);
                    view.setListaTiposDocumentosExpediente(SelectItemsUtil.listaCatalogos(documentosPersonales));

                    List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado
                            .consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
                    view.setDocumentosExpedientes(documentosExpedientes);
                } else {
                    view.setImagenExpediente("sin_expediente.png");
                    view.setMostrarAperturaExpediente(true);
                    view.setMostrarActualizacionExpediente(false);
                    view.setMostrarResultadoConsulta(false);
                }
                break;
            case "tabHistorialAcademico":
                if (expedienteEmpleado.tieneExpedienteAperturado(view.getIdEmpleadoSeleccionado())) {
                    List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
                    view.setHistorialesAcademicos(historial);
                } else {
                    view.setHistorialesAcademicos(new ArrayList<HistorialAcademicoDTO>());
                }

                view.setMostrarRegistroHistorial(false);
                view.setMostrarAdjuntarDocumentoHistorial(false);

                break;
        }
    }

    /**
     * Permite aperturar el expediente del empleado.
     *
     * @param actionEvent
     *            el evento.
     */
    public void aperturarExpediente(ActionEvent actionEvent) {
        view.getExpediente().setIdEmpleado(view.getIdEmpleadoSeleccionado());
        expedienteEmpleado.crearExpediente(view.getExpediente());
        view.setMostrarAperturaExpediente(false);
        view.setMostrarActualizacionExpediente(true);
        view.setNumeroExpediente(view.getExpediente().getNumeroExpediente().toUpperCase());
        view.setImagenExpediente("expediente_aperturado.png");
        Integer idExpediente = expedienteEmpleado.obtenerIdExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
        view.setIdExpediente(idExpediente);
    }

    /**
     * Ayuda en la vizualición del documento adjunto.
     *
     * @param idImagenExpediente
     *            el ID de la imagén que se mostrará.
     */
    public void visualizarImagen(Integer idImagenExpediente) {
        view.setIdImagenExpediente(idImagenExpediente);
        view.setMostrarImagenExpediente(true);
    }

    /**
     * Permite adjuntar un documento al expedienete del empleado.
     *
     * @param evento
     *            el evento para subir el archivo.
     */
    public void subirDocumentoAdjunto(FileUploadEvent evento) {
        UploadedFile archivo = evento.getFile();
        String nombreAdjunto = archivo.getFileName();
        byte[] adjunto = archivo.getContents();

        TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(archivo.getContentType());

        Integer idEmpleado = (Integer) evento.getComponent().getAttributes().get("idEmpleado");
        Integer idDocAdj = (Integer) evento.getComponent().getAttributes().get("idDocAdj");

        InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

        DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
        dto.setIdDocumentoAdjuntable(idDocAdj);
        info.setEntidadContexto(EntidadContexto.EMPLEADO);
        info.setIdEntidadContexto(idEmpleado);
        info.setIdAdjunto(null);
        info.setNombreAdjunto(nombreAdjunto);
        info.setExtension(extension);
        info.setDocumentoAdjuntable(dto);
        info.setIdEmpleado(view.getIdEmpleadoSeleccionado());
        info.setIdExpediente(view.getIdExpediente());

        adjuntoEmpleado.crear(info, adjunto);
        view.getDocumentosExpedientes().clear();
        List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
        view.setDocumentosExpedientes(documentosExpedientes);
    }

    /**
     * Permite descargar el archivo adjunto del expediente.
     *
     * @param adjunto
     *            la información sobre el adjunto que será descargado.
     */
    public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {
        try {
            byte[] bytes = adjuntoEmpleado.obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

            JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(), adjunto.getExtension().getMIMEType());
            JSFUtils.infoMessage("Descarga iniciada", "La descarga del archivo ha iniciado.");
        } catch (IOException e) {
            JSFUtils.errorMessage("Error al iniciar la descarga", "No se ha logrado iniciar la descarga del archivo.");
            LOGGER.errorv("Error de lectura al descargar el adjunto: {0}", e);
        }
    }

    /**
     * Permite eliminar un adjunto.
     *
     * @param idAdjunto
     *            el ID del adjunto que será eliminado del expediente.
     */
    public void eliminarAdjunto(Integer idAdjunto) {
        adjuntoEmpleado.elimnar(idAdjunto);

        view.getDocumentosExpedientes().clear();
        List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
        view.setDocumentosExpedientes(documentosExpedientes);
        JSFUtils.infoMessageEspecifico("info", "", "El documento se ha eliminado correctamente.");
    }

    public void visualizarRegistroHistorial() {
        view.setMostrarRegistroHistorial(true);
        view.setMostrarAdjuntarDocumentoHistorial(false);
    }

    public void visualizarAdjuntarDocumentoHistorial(HistorialAcademicoDTO historialAcademicoDTO) {
        view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);

        List<String> documentosAdjuntosGradoAcademico = adjuntoEmpleado.consultarDocumentosAdjuntosPorEntidadContextoIdEntidadContexto(
                EntidadContexto.HISTORIAL_ACADEMICO, view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());
        view.setDocumentacionActualHistorial(documentosAdjuntosGradoAcademico);

        view.setMostrarRegistroHistorial(false);
        view.setMostrarAdjuntarDocumentoHistorial(true);
    }

    public void visualizarDatosRequeridosCurso() {
        if (view.getHistorial().getIdEscolaridad() > 10) {
            view.setVisualizarDatosCurso(true);
        } else {
            view.setVisualizarDatosCurso(false);
        }
    }

    public void registrarHistorialAcademico(ActionEvent actionEvent) {
        view.getHistorial().setIdEmpleado(view.getIdEmpleadoSeleccionado());
        historialAcademico.crearHistorialAcademico(view.getHistorial(), true);

        List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
        view.setHistorialesAcademicos(historial);

        view.setMostrarRegistroHistorial(false);
        NuevoHistorialDTO nuevoHistorial = new NuevoHistorialDTO();
        view.setHistorial(nuevoHistorial);
        JSFUtils.infoMessageEspecifico("info", "", "El historial academico ha sido registrado con éxito.");
    }

    public void subirDocumentoAdjuntoHistorial(FileUploadEvent evento) {
        if (!ValidacionUtil.esNumeroPositivo(view.getIdDocumentoAdjuntableHistorial())) {
            throw new ValidacionException("Adjuntar documento: Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        } else if (ValidacionUtil.esCadenaVacia(view.getNumeroExpediente())) {
            throw new ValidacionException("Adjuntar documento historial: Por favor aperture el expediente para poder adjuntarle el documento.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
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
            info.setIdEmpleado(view.getIdEmpleadoSeleccionado());
            info.setIdExpediente(view.getIdExpediente());

            view.setMostrarAdjuntarDocumentoHistorial(false);

            adjuntoEmpleado.crear(info, adjunto);
            historialAcademico.actualizarAdjuntoHistorial(view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());

            List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
            view.setHistorialesAcademicos(historial);

        }
    }

    /**
     * Permite validar el campo que se usa como criterio de búsqueda de empleado
     * antes que de sea enviado al EJB.
     *
     * @param context
     *            el contexto actual de la vista.
     * @param component
     *            el componente que será validado.
     * @param value
     *            el valor del componente a validar.
     */
    public void validatorBusqueda(FacesContext context, UIComponent component, Object value) {
        switch (component.getId()) {
            case "txtCriterio":
                String criterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
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
                // No hace nada.
                break;
        }
    }

    /**
     * Permite validar el nombre del expediente.
     *
     * @param context
     *            el contexto actual de la vista.
     * @param component
     *            el componente que será validado.
     * @param value
     *            el valor del componente a validar.
     */
    public void validatorExpediente(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "numeroExpediente":
                String numeroExpediente = (String) value;

                if (ValidacionUtil.esCadenaVacia(numeroExpediente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese un número de expediente");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                // No hace nada
                break;
        }
    }

    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public AdministracionExpedienteEmpleadoView getView() {
        return view;
    }

    /**
     * Set the value of view
     *
     * @param view
     *            new value of view
     */
    public void setView(AdministracionExpedienteEmpleadoView view) {
        this.view = view;
    }

}
