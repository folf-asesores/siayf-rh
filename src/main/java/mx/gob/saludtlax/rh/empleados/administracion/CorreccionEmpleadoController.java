/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.expediente.empleado.ExpedienteEmpleado;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboral;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
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
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 11:03:39
 *
 */

@Named(value = "correccionEmpleado")
@ViewScoped
public class CorreccionEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6374216763540948370L;
    private static final Logger LOGGER = Logger.getLogger(CorreccionEmpleadoController.class.getName());
    private static final short DATOS_GENERALES = 1;
    private static final short DOMICILIO = 2;
    private static final short HISTORIAL_ACADEMICO = 3;
    private static final short EXPERIENCIA_LABORAL = 4;
    private static final short EXPEDIENTE = 5;
    private static final short DEPENDIENTES_ECONOMICOS = 6;

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

    private CorreccionEmpleadoView view;

    @PostConstruct
    public void inicio() {
        view = new CorreccionEmpleadoView();
        view.setMostrarResultadoConsulta(true);
        view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
        view.setListaTiposSexos(SelectItemsUtil.listaTiposSexo());
        view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
        view.setListaEscolaridades(SelectItemsUtil.listaCatalogos(catalogo.listaEscolaridades()));
        view.setListaComprobantesEstudios(SelectItemsUtil.listaCatalogos(catalogo.listaComprobantesEstudios()));
        view.setListaTiposParentescos(SelectItemsUtil.listaParentescos());
        view.setListaEstados(SelectItemsUtil.listaCatalogos(catalogo.listaEstados()));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());
    }

    public void consultarEmpleados() {
        view.setMostrarPanelCorrecciones(false);
        view.setMostrarResultadoConsulta(true);
        view.setEmpleados(empleado.consultaPorCriterio(view.getCriterio()));

        if (view.getEmpleados().isEmpty()) {
            JSFUtils.infoMessageEspecifico("info", "", "No se encontraron registros con el criterio " + view.getCriterio());
        }
    }

    public void seleccionarEmpleado(Integer idEmpleadoSeleccionado, String estatus) {
        if (EnumEstatusEmpleado.ACTIVO.equals(estatus)) {
            view.setMostrarResultadoConsulta(false);
            view.setMostrarPanelCorrecciones(true);
            view.setMostrarDatosGenerales(true);
            view.setIdEmpleadoSeleccionado(idEmpleadoSeleccionado);
            view.setVisualizarBotonDependientes(false);

            DatosGeneralesDTO datosGeneralesDTO = empleado.obtenerDatosGenerales(view.getIdEmpleadoSeleccionado());
            view.setDatosGenerales(datosGeneralesDTO);

            if (view.getDatosGenerales().getTienePersonasDependientes() != null || view.getDatosGenerales().getTienePersonasDependientes()) {
                view.setVisualizarBotonDependientes(true);
            }

            if (expedienteEmpleado.tieneExpedienteAperturado(view.getIdEmpleadoSeleccionado())) {
                String numeroExpediente = expedienteEmpleado.numeroExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                Integer idExpediente = expedienteEmpleado.obtenerIdExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
                view.setImagenExpediente("expediente_aperturado.png");
                view.setMostrarActualizacionExpediente(true);
                view.setNumeroExpediente(numeroExpediente);
                view.setIdExpediente(idExpediente);
            } else {
                view.setImagenExpediente("sin_expediente.png");
                view.setMostrarAperturaExpediente(true);
            }
        } else {
            JSFUtils.infoMessageEspecifico("info", "", "Empleado inactivo");
        }
    }

    /**
     * Renderiza el panel que selecciona el usuario, obteniendo la información
     * correspondiente a lo seleccionado
     *
     * @param panel
     *            el identificado del panel a renderizar.
     */
    public void renderizarPanelesCorrecciones(short panel) {
        view.setMostrarDatosGenerales(false);
        view.setMostrarDomicilio(false);
        view.setMostrarHistorialAcademico(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarExpediente(false);
        view.setVisualizarBotonDependientes(false);
        view.setMostrarDependientesEconomicos(false);

        switch (panel) {
            case DATOS_GENERALES:
                DatosGeneralesDTO datosGeneralesDTO = empleado.obtenerDatosGenerales(view.getIdEmpleadoSeleccionado());
                view.setDatosGenerales(datosGeneralesDTO);
                view.setMostrarDatosGenerales(true);
                break;
            case DOMICILIO:
                DomicilioDTO domicilioDTO = empleado.obtenerDomicilio(view.getIdEmpleadoSeleccionado());
                view.setDomicilio(domicilioDTO);
                view.setMostrarDomicilio(true);
                if (view.getDomicilio().isTieneDireccion()) {
                    List<CatalogoDTO> municipios = catalogo.consultarMunicipiosPorEstado(view.getDomicilio().getIdEstado());
                    view.getListaMuncipios().clear();
                    view.setListaMuncipios(SelectItemsUtil.listaCatalogos(municipios));

                    List<CatalogoDTO> asentamientos = catalogo.consultarAsantamientosPorMunicipios(view.getDomicilio().getIdMunicipio());
                    view.getListaPoblaciones().clear();
                    view.setListaPoblaciones(SelectItemsUtil.listaCatalogos(asentamientos));

                }
                break;
            case HISTORIAL_ACADEMICO:
                List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
                List<CatalogoDTO> documentosAdjuntables = catalogo
                        .consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_HISTORIAL);
                view.setListaDocumentosHistorialAcademico(SelectItemsUtil.listaCatalogos(documentosAdjuntables));
                view.setHistorialesAcademicos(historial);
                view.setMostrarHistorialAcademico(true);
                break;
            case EXPERIENCIA_LABORAL:
                List<ExperienciaLaboralDTO> experiencias = experienciaLaboral.consultaExperienciaLaboralEmpleado(view.getIdEmpleadoSeleccionado());
                view.setExperienciasLaborales(experiencias);
                view.setMostrarExperienciaLaboral(true);
                break;
            case EXPEDIENTE: {
                List<CatalogoDTO> lista = catalogo.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_PERSONALES);
                view.setListaTiposDocumentosExpediente(SelectItemsUtil.listaCatalogos(lista));
                view.setMostrarExpediente(true);
                List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
                view.setDocumentosExpedientes(documentosExpedientes);
                break;
            }
            case DEPENDIENTES_ECONOMICOS: {
                List<CatalogoDTO> lista = catalogo.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_DEPENDIENTES);
                view.setListaDocumentosDependientes(SelectItemsUtil.listaCatalogos(lista));
                view.setMostrarDependientesEconomicos(true);
                List<InfoDependienteEconomicoDTO> dependientes = empleado.consultarDependientesEmpleado(view.getIdEmpleadoSeleccionado());
                view.setDependientesEconomicos(dependientes);
                break;
            }
            default:
                break;
        }
    }

    public void municipiosPorEstado() {
        Integer idEstado = view.getDomicilio().getIdEstado();

        if (idEstado != 0) {
            List<CatalogoDTO> municipios = catalogo.consultarMunicipiosPorEstado(idEstado);
            view.getListaMuncipios().clear();
            view.setListaMuncipios(SelectItemsUtil.listaCatalogos(municipios));
        }
    }

    public void asentamientosPorMunicipio() {
        Integer idMunicipio = view.getDomicilio().getIdMunicipio();

        if (idMunicipio != 0) {
            List<CatalogoDTO> asentamientos = catalogo.consultarAsantamientosPorMunicipios(idMunicipio);
            view.getListaPoblaciones().clear();
            view.setListaPoblaciones(SelectItemsUtil.listaCatalogos(asentamientos));
        }
    }

    public void aperturarExpediente() {
        view.getExpediente().setIdEmpleado(view.getIdEmpleadoSeleccionado());
        expedienteEmpleado.crearExpediente(view.getExpediente());
        view.setMostrarAperturaExpediente(false);
        view.setMostrarActualizacionExpediente(true);
        view.setNumeroExpediente(view.getExpediente().getNumeroExpediente().toUpperCase());
        view.setImagenExpediente("expediente_aperturado.png");
        Integer idExpediente = expedienteEmpleado.obtenerIdExpedienteEmpleado(view.getIdEmpleadoSeleccionado());
        view.setIdExpediente(idExpediente);
    }

    public void registrarHistorialAcademico() {

        view.getHistorial().setIdEmpleado(view.getIdEmpleadoSeleccionado());
        historialAcademico.crearHistorialAcademico(view.getHistorial(), true);

        List<HistorialAcademicoDTO> historial = historialAcademico.consultarHistorialAcademicoEmpleado(view.getIdEmpleadoSeleccionado());
        view.setHistorialesAcademicos(historial);

        view.setMostrarRegistroHistorial(false);
        NuevoHistorialDTO nuevoHistorial = new NuevoHistorialDTO();
        view.setHistorial(nuevoHistorial);
        JSFUtils.infoMessageEspecifico("info", "", "El historial academico ha sido registrado con éxito.");
    }

    public void registrarDependienteEconomico() {
        LOGGER.infov("Registrar Dependiente economico: {0}", view.getDependiente().toString());
        if (view.getDatosGenerales().getTienePersonasDependientes()) {
            view.getDependiente().setIdEmpleado(view.getIdEmpleadoSeleccionado());
            empleado.crearDependienteEconomicoEmpleado(view.getDependiente());
            List<InfoDependienteEconomicoDTO> dependientes = empleado.consultarDependientesEmpleado(view.getIdEmpleadoSeleccionado());
            view.setDependientesEconomicos(dependientes);
            DependienteEconomicoDTO nuevo = new DependienteEconomicoDTO();
            view.setDependiente(nuevo);
            JSFUtils.infoMessageEspecifico("info", "", "El dependiente economico ha sido registrado con éxito.");
        } else {
            throw new BusinessException("No ha indicado tener personas dependientes.");
        }
    }

    public void registrarExperienciaLaboral() {
        // System.out.println("entro a registrar experiencia");
        experienciaLaboral.crearExperienciaLaboralEmpleado(view.getExperienciaLaboral(), view.getIdEmpleadoSeleccionado());

        List<ExperienciaLaboralDTO> experiencias = experienciaLaboral.consultaExperienciaLaboralEmpleado(view.getIdEmpleadoSeleccionado());

        view.setExperienciasLaborales(experiencias);
        ExperienciaLaboralDTO nuevaExperiencia = new ExperienciaLaboralDTO();
        view.setExperienciaLaboral(nuevaExperiencia);
        JSFUtils.infoMessageEspecifico("info", "", "La experiencia laboral ha sido registrada con éxito.");
    }

    public void actualizarDomicilio() {
        LOGGER.infov("Actualizar Domicilio {0}", view.getDomicilio().toString());
        view.getDomicilio().setIdUsuarioEnSesion(view.getIdUsuarioLogeado());
        empleado.actualizarDomicilio(view.getIdEmpleadoSeleccionado(), view.getIdUsuarioLogeado(), view.getDomicilio());
        JSFUtils.infoMessageEspecifico("info", "", "El domicilio ha sido actualizado con éxito.");
    }

    public void actualizarDatosGenerales() {
        LOGGER.infov("Actualizar Datos Generales {0}", view.getDatosGenerales().toString());

        view.getDatosGenerales().setIdUsuarioEnSesion(view.getIdUsuarioLogeado());
        empleado.actualizarDatosGenerales(view.getDatosGenerales());
        DatosGeneralesDTO datosGeneralesDTO = empleado.obtenerDatosGenerales(view.getIdEmpleadoSeleccionado());
        view.setDatosGenerales(datosGeneralesDTO);
        if (view.getDatosGenerales().getTienePersonasDependientes()) {
            view.setVisualizarBotonDependientes(true);
        }
        JSFUtils.infoMessageEspecifico("info", "", "Los datos generales han sido actualizados con éxito.");
    }

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
        info.setIdEmpleado(view.getDatosGenerales().getIdEmpleado());
        info.setIdExpediente(view.getIdExpediente());

        adjuntoEmpleado.crear(info, adjunto);
        view.getDocumentosExpedientes().clear();
        List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
        view.setDocumentosExpedientes(documentosExpedientes);
    }

    public void eliminarAdjunto(Integer idAdjunto) {
        adjuntoEmpleado.elimnar(idAdjunto);
        view.getDocumentosExpedientes().clear();
        List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado.consultarInformacionAdjuntosPorIdEmpleado(view.getIdEmpleadoSeleccionado());
        view.setDocumentosExpedientes(documentosExpedientes);
        JSFUtils.infoMessageEspecifico("info", "", "El documento se ha eliminado correctamente.");
    }

    public void visualizarImagen(Integer idImagenExpediente) {
        view.setIdImagenExpediente(idImagenExpediente);
        view.setMostrarImagenExpediente(true);
    }

    public void visualizarAdjuntarDocumentosHistorial() {
        view.setMostrarAdjuntarDocumentoHistorial(true);
        view.setMostrarRegistroHistorial(true);
    }

    public void visualizarRegistroHistorial() {
        view.setMostrarRegistroHistorial(true);
        view.setMostrarAdjuntarDocumentoHistorial(false);
    }

    public void visualizarDatosRequeridosCurso() {
        if (view.getHistorial().getIdEscolaridad() > 10) {
            view.setVisualizarDatosCurso(true);
        } else {
            view.setVisualizarDatosCurso(false);
        }
    }

    public void visualizarrOtroParentesco() {
        view.setMostrarOtroParentesco(false);

        if (view.getDependiente().getParentesco().equals("OTROS")) {
            view.setMostrarOtroParentesco(true);
        }
    }

    public void visualizarAdjuntarDocumentoHistorial(HistorialAcademicoDTO historialAcademicoDTO) {
        view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);

        List<String> documentosAdjuntosGradoAcademico = adjuntoEmpleado.consultarDocumentosAdjuntosPorEntidadContextoIdEntidadContexto(
                EntidadContexto.HISTORIAL_ACADEMICO, view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());
        view.setDocumentacionActualHistorial(documentosAdjuntosGradoAcademico);
        view.setMostrarRegistroHistorial(false);
        view.setMostrarAdjuntarDocumentoHistorial(true);
    }

    public void visualizarAdjuntarDocumentoDependiente(InfoDependienteEconomicoDTO dependienteEconomicoDTO) {
        view.setDependienteSeleccionado(dependienteEconomicoDTO);
        view.setMostrarAdjuntarDocumentoDependiente(true);
    }

    public void subirDocumentoAdjuntoHistorial(FileUploadEvent evento) {

        if (!ValidacionUtil.esNumeroPositivo(view.getIdDocumentoAdjuntableHistorial())) {
            throw new BusinessException("Adjuntar documento: Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.");
        } else if (ValidacionUtil.esCadenaVacia(view.getNumeroExpediente())) {
            throw new BusinessException("Adjuntar documento historial: Por favor aperture el expediente para poder adjuntarle el documento.");
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

    public void subirDocumentoAdjuntoDependiente(FileUploadEvent evento) {
        if (!ValidacionUtil.esNumeroPositivo(view.getIdDocumentoAduntableDependiente())) {
            throw new BusinessException("Adjuntar documento: Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.");
        } else if (ValidacionUtil.esCadenaVacia(view.getNumeroExpediente())) {
            throw new BusinessException("Adjuntar documento dependiente: Por favor aperture el expediente para poder adjuntarle el documento.");
        } else {
            UploadedFile archivo = evento.getFile();
            String nombreAdjunto = archivo.getFileName();
            byte[] adjunto = archivo.getContents();

            TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(archivo.getContentType());

            InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

            DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
            dto.setIdDocumentoAdjuntable(view.getIdDocumentoAduntableDependiente());

            info.setEntidadContexto(EntidadContexto.DEPENDIENTE_ECONOMICO);
            info.setIdEntidadContexto(view.getDependienteSeleccionado().getIdDependiente());

            info.setIdAdjunto(null);
            info.setNombreAdjunto(nombreAdjunto);
            info.setExtension(extension);
            info.setDocumentoAdjuntable(dto);
            info.setIdEmpleado(view.getIdEmpleadoSeleccionado());
            info.setIdExpediente(view.getIdExpediente());

            view.setMostrarAdjuntarDocumentoDependiente(false);

            adjuntoEmpleado.crear(info, adjunto);
            JSFUtils.infoMessageEspecifico("info", "", "El documento se ha subido con exito.");
        }
    }

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
                break;
        }

    }

    public void validatorBusqueda(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
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
                break;
        }
    }

    public void validatorDomicilio(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "estado":
                Integer estado = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(estado)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un estado.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "municipio":
                Integer municipio = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(municipio)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un municipio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "asentamiento":
                Integer asentamiento = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(asentamiento)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un asentamiento.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "calle":
                String calle = (String) value;

                if (ValidacionUtil.esCadenaVacia(calle)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una calle.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "exterior":
                String exterior = (String) value;

                if (ValidacionUtil.esCadenaVacia(exterior)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un número exterior.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
        }

    }

    public void validatorDatosGenerales(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "curp":
                String curp = (String) value;

                if (ValidacionUtil.esCadenaVacia(curp)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una curp.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    String curpActual = empleado.obtenerCurpEmpleado(view.getIdEmpleadoSeleccionado());
                    if (curpActual.compareToIgnoreCase(curp.trim()) != 0) {
                        try {
                            empleado.validarCurpEmpleado(curp);
                        } catch (BusinessException exception) {
                            FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", exception.getMessage());
                            context.addMessage(component.getClientId(), facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;

            case "nombre":
                String nombre = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombre)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un nombre.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "apellidoPaterno":
                String apellidoPaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoPaterno)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un apellido paterno.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "apellidoMaterno":
                String apellidoMaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoMaterno)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un apellido materno.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "sexo":
                String sexo = (String) value;

                if (ValidacionUtil.esCadenaVacia(sexo)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un sexo.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "estadoCivil":
                String estadoCivil = (String) value;

                if (ValidacionUtil.esCadenaVacia(estadoCivil)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un estado civil.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fechaNacimiento":
                Date fechaNacimiento = (Date) value;

                if (fechaNacimiento == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una fecha de nacimiento.");
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
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un lugar de nacimiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public void validatorDependientesEconomicos(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "parentescoDependiente":
                String parentescoDependiente = (String) value;

                if (ValidacionUtil.esCadenaVacia(parentescoDependiente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el partensco del dependiente.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "otro":
                String otro = (String) value;

                if (ValidacionUtil.esCadenaVacia(otro)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el nombre del otro parentesco");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "nombreDependiente":
                String nombreDependiente = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombreDependiente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el nombre del dependiente.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "maternoDependiente":
                String maternoDependiente = (String) value;

                if (ValidacionUtil.esCadenaVacia(maternoDependiente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el apellido materno del dependiente.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            /*
             * case "curpDependiente": String curpDependiente =
             * String.valueOf(value);
             *
             * if (ValidacionUtil.esCadenaVacia(curpDependiente)) { FacesMessage
             * facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR, "",
             * "Por favor ingrese la curp del dependiente.");
             * context.addMessage(component.getClientId(), facesMessage); }
             *
             * break;
             */
            case "fechaNacimientoDependiente":
                Date fechaNacimientoDependiente = (Date) value;

                if (fechaNacimientoDependiente == null) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese la fecha de nacimiento del dependiente.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "sexoDependiente":
                String sexoDependiente = (String) value;

                if (ValidacionUtil.esCadenaVacia(sexoDependiente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el sexo del dependiente.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                break;
        }
    }

    public void validatorExperienciaLaboral(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "nombreEmpresa":
                String nombreEmpresa = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombreEmpresa)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese la empresa o institución donde laboró.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "puestoDesempeado":
                String puestoDesempeado = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeado)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            /*
             * case "anioInicial": Date anioInicial = (Date) value;
             *
             * if (anioInicial == null) { FacesMessage facesMessage = new
             * FacesMessage( FacesMessage.SEVERITY_ERROR, "",
             * "Por favor ingrese la fecha en la que inició sus labores.");
             * context.addMessage(component.getClientId(), facesMessage); throw new
             * ValidatorException(facesMessage); }
             *
             * break; case "anioFinal": Date anioFinal = (Date) value;
             *
             * if (anioFinal == null) { FacesMessage facesMessage = new
             * FacesMessage( FacesMessage.SEVERITY_ERROR, "",
             * "Por favor ingrese la fecha en la que finalizó sus labores.");
             * context.addMessage(component.getClientId(), facesMessage); throw new
             * ValidatorException(facesMessage); }
             *
             * break;
             */
            default:
                break;
        }

    }

    public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {
        try {
            byte[] bytes = adjuntoEmpleado.obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

            JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(), adjunto.getExtension().getMIMEType());
            JSFUtils.infoMessage("Descarga iniciada", "La descarga del archivo ha iniciado.");
        } catch (IOException e) {
            JSFUtils.errorMessage("Error al iniciar la descarga", "No se ha logrado iniciar la descarga del archivo.");
            LOGGER.error(e);
        }
    }

    public CorreccionEmpleadoView getView() {
        return view;
    }

    public void setView(CorreccionEmpleadoView view) {
        this.view = view;
    }

}
