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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 13:18:16 21/09/2016
 */
@ManagedBean(name = "actualizarEmpleado")
@ViewScoped
public class ActualizarEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6374216763540948370L;

    private static final int DATOS_GENERALES = 1;
    private static final int DOMICILIO = 2;
    private static final int HISTORIAL_ACADEMICO = 3;
    private static final int EXPERIENCIA_LABORAL = 4;

    private Boolean mostrarDialogoConfirmacionDomicilio = Boolean.FALSE;

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

    private ActualizarEmpleadoView view;

    @PostConstruct
    private void init() {
        view = new ActualizarEmpleadoView();
        cargarCatalogos();
    }

    public void cargarCatalogos() {
        view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
        view.setListaEscolaridades(
                SelectItemsUtil.listaCatalogos(catalogo.listaEscolaridades()));
        view.setListaComprobantesEstudios(SelectItemsUtil
                .listaCatalogos(catalogo.listaComprobantesEstudios()));
        view.setListaTiposParentescos(SelectItemsUtil.listaParentescos());
        view.setListaEstados(
                SelectItemsUtil.listaCatalogos(catalogo.listaEstados()));
        view.setListaTiposSexos(SelectItemsUtil.listaTiposSexo());
    }

    public void seleccionarEmpleado(Integer idEmpleadoSeleccionado) {

        try {

            view.setMostrarResultadoConsulta(false);
            view.setMostrarPanelCorrecciones(true);
            view.setMostrarDatosGenerales(true);
            view.setIdEmpleadoSeleccionado(idEmpleadoSeleccionado);
            view.setVisualizarBotonDependientes(false);
            DatosGeneralesDTO datosGeneralesDTO = empleado
                    .obtenerDatosGenerales(view.getIdEmpleadoSeleccionado());
            view.setDatosGenerales(datosGeneralesDTO);

            if (view.getDatosGenerales().getTienePersonasDependientes() != null
                    || view.getDatosGenerales()
                            .getTienePersonasDependientes()) {
                view.setVisualizarBotonDependientes(true);
            }

            if (expedienteEmpleado.tieneExpedienteAperturado(
                    view.getIdEmpleadoSeleccionado())) {
                String numeroExpediente = expedienteEmpleado
                        .numeroExpedienteEmpleado(
                                view.getIdEmpleadoSeleccionado());
                Integer idExpediente = expedienteEmpleado
                        .obtenerIdExpedienteEmpleado(
                                view.getIdEmpleadoSeleccionado());
                view.setImagenExpediente("expediente_aperturado.png");
                view.setMostrarActualizacionExpediente(true);
                view.setNumeroExpediente(numeroExpediente);
                view.setIdExpediente(idExpediente);
            } else {
                view.setImagenExpediente("sin_expediente.png");
                view.setMostrarAperturaExpediente(true);
            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }

    }

    public void visualizarAdjuntoDocumentoHistorial(
            HistorialAcademicoDTO historialAcademicoDTO) {
        try {
            view.setMostrarActualizarHistorial(false);
            view.setMostrarRegistroHistorial(false);
            view.setIdDocumentoAdjuntableHistorial(0);
            view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);

            List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
                    .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
                            EntidadContexto.HISTORIAL_ACADEMICO,
                            view.getHistorialAcademicoSeleccionado()
                                    .getIdHistorialAcademico());

            view.setDocumentosAdjuntosGradoAcademico(
                    documentosAdjuntosGradoAcademico);

            view.setMostrarAdjuntoDocumentoHistorial(true);
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void initMenu() {
        view.setMostrarPanelCorrecciones(true);
        view.setMostrarResultadoConsulta(false);
        view.setMostrarDatosGenerales(false);
        view.setMostrarDomicilio(false);
        view.setMostrarHistorialAcademico(false);
        view.setMostrarRegistroHistorial(false);
        view.setMostrarAdjuntoDocumentoHistorial(false);
        view.setIdDocumentoAdjuntableHistorial(0);
        view.setMostrarImagenExpediente(false);
        view.setMostrarActualizarHistorial(false);
        view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());
        view.setHistorial(new NuevoHistorialDTO());
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarRegistroExperiencia(false);
        view.setMostrarActualizacionExpediente(false);
    }

    public void menu(int panel) {
        try {
            initMenu();

            switch (panel) {

                case DATOS_GENERALES:

                    DatosGeneralesDTO datosGeneralesDTO = empleado
                            .obtenerDatosGenerales(
                                    view.getIdEmpleadoSeleccionado());
                    view.setDatosGenerales(datosGeneralesDTO);
                    view.setMostrarDatosGenerales(true);

                    break;

                case DOMICILIO:
                    view.setMostrarDomicilio(true);

                    DomicilioDTO domicilioDTO = empleado
                            .obtenerDomicilio(view.getIdEmpleadoSeleccionado());

                    view.setDomicilio(domicilioDTO);

                    if (view.getDomicilio().isTieneDireccion()) {
                        List<CatalogoDTO> municipios = catalogo
                                .consultarMunicipiosPorEstado(
                                        view.getDomicilio().getIdEstado());
                        view.getListaMuncipios().clear();
                        view.setListaMuncipios(
                                SelectItemsUtil.listaCatalogos(municipios));

                        List<CatalogoDTO> asentamientos = catalogo
                                .consultarAsantamientosPorMunicipios(
                                        view.getDomicilio().getIdMunicipio());
                        view.getListaPoblaciones().clear();
                        view.setListaPoblaciones(
                                SelectItemsUtil.listaCatalogos(asentamientos));
                    }

                    break;

                case HISTORIAL_ACADEMICO:

                    List<HistorialAcademicoDTO> historial = historialAcademico
                            .consultarHistorialAcademicoEmpleado(
                                    view.getIdEmpleadoSeleccionado());
                    List<CatalogoDTO> documentosAdjuntables = catalogo
                            .consultarDocumentosExpedientesClasificacion(
                                    EnumClasificacionExpediente.DOCUMENTOS_HISTORIAL);

                    view.setListaDocumentosHistorialAcademico(SelectItemsUtil
                            .listaCatalogos(documentosAdjuntables));
                    view.setHistorialesAcademicos(historial);
                    view.setMostrarHistorialAcademico(true);

                    break;

                case EXPERIENCIA_LABORAL:
                    List<ExperienciaLaboralDTO> experiencias = experienciaLaboral
                            .consultaExperienciaLaboralEmpleado(
                                    view.getIdEmpleadoSeleccionado());
                    view.setExperienciasLaborales(experiencias);
                    view.setMostrarExperienciaLaboral(true);
                    break;

                default:
                    JSFUtils.errorMessage("Error: ",
                            "El menu seleccionado es incorrecto...");
                    break;

            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }

    }

    public void actualizarDatosGenerales() {
        if (ValidacionUtil.esCadenaVacia(
                view.getDatosGenerales().getComentarioMovimiento())) {
            JSFUtils.errorMessage("Error: ",
                    "Ingrese el comentario de modificación");
        } else {
            try {
                // Obtiene el id del usuario en sesion para los movimientos de
                // la
                // bitacora
                view.getDatosGenerales()
                        .setIdUsuarioEnSesion(obtenerIdUsuario());

                empleado.actualizarDatosGenerales(view.getDatosGenerales());
                DatosGeneralesDTO datosGeneralesDTO = empleado
                        .obtenerDatosGenerales(
                                view.getIdEmpleadoSeleccionado());

                view.setDatosGenerales(datosGeneralesDTO);
                if (view.getDatosGenerales().getTienePersonasDependientes()) {
                    view.setVisualizarBotonDependientes(true);
                }
                cerrarDialogoConfirmacionDatosGenerales();
                JSFUtils.infoMessageEspecifico("info", "",
                        "Los datos generales han sido actualizados con éxito.");
            } catch (ReglaNegocioException reglaNegocioException) {
                JSFUtils.errorMessage("Error: ",
                        reglaNegocioException.getMessage());
            } catch (ValidatorException validatorException) {
                JSFUtils.errorMessage("Error: ",
                        validatorException.getMessage());
            }
        }

    }

    public void actualizarDomicilio() {
        try {

            view.getDomicilio().setIdUsuarioEnSesion(obtenerIdUsuario());

            empleado.actualizarDomicilio(view.getIdEmpleadoSeleccionado(), null,
                    view.getDomicilio());
            menu(DOMICILIO);
            JSFUtils.infoMessageEspecifico("info", "",
                    "El domicilio ha sido actualizado con éxito.");
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void registrarHistorialAcademico() {
        try {
            view.getHistorial().setIdEmpleado(view.getIdEmpleadoSeleccionado());
            historialAcademico.crearHistorialAcademico(view.getHistorial(),
                    true);

            List<HistorialAcademicoDTO> historial = historialAcademico
                    .consultarHistorialAcademicoEmpleado(
                            view.getIdEmpleadoSeleccionado());
            view.setHistorialesAcademicos(historial);

            view.setMostrarRegistroHistorial(false);
            NuevoHistorialDTO nuevoHistorial = new NuevoHistorialDTO();
            view.setHistorial(nuevoHistorial);
            JSFUtils.infoMessageEspecifico("info", "",
                    "El historial academico ha sido registrado con éxito.");

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void actualizarHistorialAcademico() {
        try {

            view.getHistorialAcademicoSeleccionado()
                    .setIdUsuarioEnSesion(obtenerIdUsuario());

            view.getHistorialAcademicoSeleccionado()
                    .setIdEmpleado(view.getIdEmpleadoSeleccionado());
            historialAcademico.actualizarHistorialAcademico(
                    view.getHistorialAcademicoSeleccionado());

            List<HistorialAcademicoDTO> historial = historialAcademico
                    .consultarHistorialAcademicoEmpleado(
                            view.getIdEmpleadoSeleccionado());
            view.setHistorialesAcademicos(historial);

            view.setMostrarActualizarHistorial(false);

            view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());

            JSFUtils.infoMessageEspecifico("info", "",
                    "El historial academico ha sido actualizado con éxito.");

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void subirDocumentoAdjuntoHistorial(FileUploadEvent evento) {
        try {

            if (!ValidacionUtil.esNumeroPositivo(
                    view.getIdDocumentoAdjuntableHistorial())) {
                JSFUtils.errorMessage("Error: ",
                        "Adjuntar documento: Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.");

            } else if (ValidacionUtil
                    .esCadenaVacia(view.getNumeroExpediente())) {
                JSFUtils.errorMessage("Error: ",
                        "Adjuntar documento historial: Por favor aperture el expediente para poder adjuntarle el documento.");

            } else {
                UploadedFile archivo = evento.getFile();
                String nombreAdjunto = archivo.getFileName();
                byte[] adjunto = archivo.getContents();

                TipoArchivo extension = TipoArchivo
                        .getTipoArchivoPorMIMEType(archivo.getContentType());

                InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

                DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
                dto.setIdDocumentoAdjuntable(
                        view.getIdDocumentoAdjuntableHistorial());

                info.setEntidadContexto(EntidadContexto.HISTORIAL_ACADEMICO);
                info.setIdEntidadContexto(
                        view.getHistorialAcademicoSeleccionado()
                                .getIdHistorialAcademico());

                info.setIdAdjunto(null);
                info.setNombreAdjunto(nombreAdjunto);
                info.setExtension(extension);
                info.setDocumentoAdjuntable(dto);
                info.setIdEmpleado(view.getIdEmpleadoSeleccionado());
                info.setIdExpediente(view.getIdExpediente());

                view.setMostrarAdjuntoDocumentoHistorial(false);
                view.setMostrarImagenExpediente(false);

                adjuntoEmpleado.crear(info, adjunto);
                historialAcademico.actualizarAdjuntoHistorial(
                        view.getHistorialAcademicoSeleccionado()
                                .getIdHistorialAcademico());

                List<HistorialAcademicoDTO> historial = historialAcademico
                        .consultarHistorialAcademicoEmpleado(
                                view.getIdEmpleadoSeleccionado());
                view.setHistorialesAcademicos(historial);

            }

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void consultarEmpleados() {
        try {
            view.setMostrarPanelCorrecciones(false);
            view.setMostrarResultadoConsulta(true);
            FiltroDTO filtroDTO = new FiltroDTO();
            filtroDTO.setCriterio(view.getCriterio());
            filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_ACTIVOS);
            view.setEmpleados(empleado.consultaEmpleado(filtroDTO));

            if (view.getEmpleados().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "",
                        "No se encontraron empleados activos con el criterio  "
                                + view.getCriterio());
            }

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void municipiosPorEstado() {
        Integer idEstado = view.getDomicilio().getIdEstado();
        if (idEstado != 0) {
            List<CatalogoDTO> municipios = catalogo
                    .consultarMunicipiosPorEstado(idEstado);
            view.getListaMuncipios().clear();
            view.setListaMuncipios(SelectItemsUtil.listaCatalogos(municipios));
        }
    }

    public void asentamientosPorMunicipio() {
        Integer idMunicipio = view.getDomicilio().getIdMunicipio();
        if (idMunicipio != 0) {
            List<CatalogoDTO> asentamientos = catalogo
                    .consultarAsantamientosPorMunicipios(idMunicipio);
            view.getListaPoblaciones().clear();
            view.setListaPoblaciones(
                    SelectItemsUtil.listaCatalogos(asentamientos));

        }
    }

    public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {
        try {
            view.setMostrarImagenExpediente(false);
            byte[] bytes = adjuntoEmpleado
                    .obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

            JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(),
                    adjunto.getExtension().getMIMEType());
            JSFUtils.infoMessage("Descarga iniciada",
                    "La descarga del archivo ha iniciado.");

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        } catch (IOException ioException) {
            JSFUtils.errorMessage("Error: ", ioException.getMessage());
        }
    }

    public void eliminarAdjunto(Integer idAdjunto) {
        try {
            view.setMostrarImagenExpediente(false);
            adjuntoEmpleado.elimnar(idAdjunto);

            List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
                    .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
                            EntidadContexto.HISTORIAL_ACADEMICO,
                            view.getHistorialAcademicoSeleccionado()
                                    .getIdHistorialAcademico());

            view.setDocumentosAdjuntosGradoAcademico(
                    documentosAdjuntosGradoAcademico);

            view.setMostrarAdjuntoDocumentoHistorial(true);

            JSFUtils.infoMessageEspecifico("info", "",
                    "El documento se ha eliminado correctamente.");

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void registrarExperienciaLaboral() {
        try {

            experienciaLaboral.crearExperienciaLaboralEmpleado(
                    view.getExperienciaLaboral(),
                    view.getIdEmpleadoSeleccionado());

            List<ExperienciaLaboralDTO> experiencias = experienciaLaboral
                    .consultaExperienciaLaboralEmpleado(
                            view.getIdEmpleadoSeleccionado());

            view.setExperienciasLaborales(experiencias);
            view.setMostrarRegistroExperiencia(false);
            view.setExperienciaLaboral(new ExperienciaLaboralDTO());
            JSFUtils.infoMessageEspecifico("info", "",
                    "La experiencia laboral ha sido registrada con éxito.");

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void actualizarExperienciaLaboral() {
        try {
            view.getExperienciaLaboralSeleccionado()
                    .setIdUsuarioEnSesion(obtenerIdUsuario());

            experienciaLaboral.actualizarExperienciaLaboralEmpleado(
                    view.getExperienciaLaboralSeleccionado(),
                    view.getIdEmpleadoSeleccionado());

            List<ExperienciaLaboralDTO> experiencias = experienciaLaboral
                    .consultaExperienciaLaboralEmpleado(
                            view.getIdEmpleadoSeleccionado());

            view.setExperienciasLaborales(experiencias);
            view.setMostrarActualizacionExperiencia(false);
            view.setExperienciaLaboralSeleccionado(new ExperienciaLaboralDTO());
            JSFUtils.infoMessageEspecifico("info", "",
                    "La experiencia laboral ha sido actualizada con éxito.");
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    /***
     * Obtiene el id del usuario en sesión
     *
     * @return
     */
    public Integer obtenerIdUsuario() {

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

        return usuario.getIdUsuario();
    }

    public void visualizarRegistroHistorial() {
        view.setMostrarRegistroHistorial(true);
        view.setMostrarAdjuntoDocumentoHistorial(false);
        view.setMostrarActualizarHistorial(false);
    }

    public void visualizarActualizarHistorial(
            HistorialAcademicoDTO historialAcademicoDTO) {
        view.setHistorialAcademicoSeleccionado(historialAcademicoDTO);
        view.setMostrarRegistroHistorial(false);
        view.setMostrarAdjuntoDocumentoHistorial(false);
        view.setMostrarActualizarHistorial(true);
    }

    public void visualizarRegistroExperiencia() {
        view.setExperienciaLaboral(new ExperienciaLaboralDTO());
        view.setMostrarRegistroExperiencia(true);
        view.setMostrarActualizacionExperiencia(false);
    }

    public void visualizarActualizarExperiencia(
            ExperienciaLaboralDTO experienciaLaboralDTO) {
        view.setExperienciaLaboralSeleccionado(experienciaLaboralDTO);
        view.setMostrarRegistroExperiencia(false);
        view.setMostrarActualizacionExperiencia(true);
    }

    public void visualizarDatosRequeridosCurso() {
        if (view.getHistorial().getIdEscolaridad() > 10) {
            view.setVisualizarDatosCurso(true);
        } else {
            view.setVisualizarDatosCurso(false);
        }

        if (view.getHistorialAcademicoSeleccionado().getEscolaridad() > 10) {
            view.setVisualizarDatosCurso(true);
        } else {
            view.setVisualizarDatosCurso(false);
        }
    }

    public void visualizarImagen(Integer idImagenExpediente) {
        view.setIdImagenExpediente(idImagenExpediente);
        view.setMostrarImagenExpediente(true);
    }

    public void validatorConsulta(FacesContext context, UIComponent component,
            Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterio.trim().length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }

                break;
            default:
                break;
        }
    }

    public void validatorDatosGenerales(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "curp":
                String curp = (String) value;

                if (ValidacionUtil.esCadenaVacia(curp)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una curp.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    String curpActual = empleado.obtenerCurpEmpleado(
                            view.getIdEmpleadoSeleccionado());
                    if (curpActual.compareToIgnoreCase(curp.trim()) != 0) {
                        try {
                            empleado.validarCurpEmpleado(curp);
                        } catch (ReglaNegocioException exception) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    exception.getMessage());
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;

            case "rfc":
                String rfc = (String) value;

                if (ValidacionUtil.esCadenaVacia(rfc)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el rfc.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    String rfcActual = empleado.obtenerRfcEmpleado(
                            view.getIdEmpleadoSeleccionado());
                    if (rfcActual.compareToIgnoreCase(rfc.trim()) != 0) {
                        try {
                            empleado.validarRfcEmpleado(rfc);
                        } catch (ReglaNegocioException exception) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    exception.getMessage());
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;

            case "nombre":
                String nombre = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombre)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un nombre.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "apellidoPaterno":
                String apellidoPaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoPaterno)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un apellido paterno.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "apellidoMaterno":
                String apellidoMaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoMaterno)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un apellido materno.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "sexo":
                String sexo = (String) value;

                if (ValidacionUtil.esCadenaVacia(sexo)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un sexo.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "estadoCivil":
                String estadoCivil = (String) value;

                if (ValidacionUtil.esCadenaVacia(estadoCivil)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un estado civil.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fechaNacimiento":
                Date fechaNacimiento = (Date) value;

                if (fechaNacimiento == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una fecha de nacimiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    if (ValidacionUtil.esFechaFutura(fechaNacimiento)) {
                        FacesMessage facesMessage1 = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese una fecha que no sea mayor a la fecha actual.");
                        context.addMessage(component.getClientId(),
                                facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    }
                }
                break;
            case "lugarNacimiento":
                String lugarNacimiento = (String) value;

                if (ValidacionUtil.esCadenaVacia(lugarNacimiento)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un lugar de nacimiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }

    }

    public void validatorDomicilio(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "estado":
                Integer estado = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(estado)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un estado.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "municipio":
                Integer municipio = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(municipio)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un municipio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "asentamiento":
                Integer asentamiento = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(asentamiento)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un asentamiento.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "calle":
                String calle = (String) value;

                if (ValidacionUtil.esCadenaVacia(calle)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una calle.");
                    context.addMessage(component.getClientId(), facesMessage);
                }
                break;
            case "exterior":
                String exterior = (String) value;

                if (ValidacionUtil.esCadenaVacia(exterior)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un número exterior.");
                    context.addMessage(component.getClientId(), facesMessage);
                }
                break;

            case "comentarioDomicilio":
                String comentarioDomicilio = (String) value;

                if (ValidacionUtil.esCadenaVacia(comentarioDomicilio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el comentario de modificación");
                    context.addMessage(component.getClientId(), facesMessage);
                }
                break;
        }

    }

    public void validatorExperienciaLaboral(FacesContext context,
            UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "nombreEmpresa":
                String nombreEmpresa = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombreEmpresa)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese la empresa o institución donde laboró.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "puestoDesempeado":
                String puestoDesempeado = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeado)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            case "nombreEmpresaActualizar":
                String nombreEmpresaActualizar = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombreEmpresaActualizar)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese la empresa o institución donde laboró.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "puestoDesempeadoActualizar":
                String puestoDesempeadoActualizar = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeadoActualizar)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            default:
                break;
        }

    }

    public void validatorComentarioDatoGeneral(FacesContext context,
            UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "comentarioRfc":
                String comentarioRfc = (String) value;

                if (ValidacionUtil.esCadenaVacia(comentarioRfc)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el comentario.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "puestoDesempeado":
                String puestoDesempeado = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeado)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            case "nombreEmpresaActualizar":
                String nombreEmpresaActualizar = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombreEmpresaActualizar)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese la empresa o institución donde laboró.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "puestoDesempeadoActualizar":
                String puestoDesempeadoActualizar = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeadoActualizar)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            default:
                break;
        }

    }

    public void mostrarDialogoConfirmacionDatosGenerales() {
        view.setMostrarDialogoConfirmacionDatosGenerales(true);

    }

    public void cerrarDialogoConfirmacionDatosGenerales() {
        view.setMostrarDialogoConfirmacionDatosGenerales(false);

    }

    public void mostrarDialogoConfirmacionDomicilioButton() {
        System.out.println("entrabdo dlg domicilio");
        setMostrarDialogoConfirmacionDomicilio(true);
    }

    public void cerrarDialogoConfirmacionDomicilio() {
        setMostrarDialogoConfirmacionDomicilio(false);
    }

    public void mostrarDialogoConfirmacionHistorial() {
        view.setMostrarDialogoConfirmacionHistorial(true);
    }

    public void cerrarDialogoConfirmacionHistorial() {
        view.setMostrarDialogoConfirmacionHistorial(false);
    }

    public void mostrarDialogoConfirmacionExperiencia() {
        view.setMostrarDialogoConfirmacionExperiencia(true);
    }

    public void cerrarDialogoConfirmacionExperiencia() {
        view.setMostrarDialogoConfirmacionExperiencia(false);
    }

    /**
     * @return the view
     */
    public ActualizarEmpleadoView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(ActualizarEmpleadoView view) {
        this.view = view;
    }

    /**
     * @return the mostrarDialogoConfirmacionDomicilio
     */
    public Boolean getMostrarDialogoConfirmacionDomicilio() {
        return mostrarDialogoConfirmacionDomicilio;
    }

    /**
     * @param mostrarDialogoConfirmacionDomicilio
     *            the mostrarDialogoConfirmacionDomicilio to set
     */
    public void setMostrarDialogoConfirmacionDomicilio(
            Boolean mostrarDialogoConfirmacionDomicilio) {
        this.mostrarDialogoConfirmacionDomicilio = mostrarDialogoConfirmacionDomicilio;
    }

}
