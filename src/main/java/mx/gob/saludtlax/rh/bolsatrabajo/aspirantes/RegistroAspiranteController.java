/*
 *
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
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.configuracion.especialidad.Especialidad;
import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.Profesion;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.aspirante.AdjuntoAspirante;
import mx.gob.saludtlax.rh.expediente.aspirante.ExpedienteAspirante;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/03/2016-12:50:20
 *
 * @modify Eduardo Mex
 */
@ManagedBean(name = "registroAspirante")
@SessionScoped
public class RegistroAspiranteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4047399838811417328L;

    private static final int DATOS_GENERALES = 1;
    private static final int HISTORIAL_ACADEMICO = 2;
    private static final int EXPERIENCIA_LABORAL = 3;
    private static final int HABILIDADES_PERSONALES = 4;
    private static final int EXPEDIENTE = 5;
    private static final int PROFESION = 6;
    private static final int ESPECIALIDAD = 7;

    @Inject
    private Catalogo catalogosEJB;
    @Inject
    private BolsaTrabajo bolsaTrabajo;
    @Inject
    private AdjuntoAspirante adjuntoAspirante;
    @Inject
    private ExpedienteAspirante expedienteAspirante;
    @Inject
    private Especialidad especialidad;
    @Inject
    private Profesion profesion;

    private RegistroAspiranteView view;

    private Integer idAspirante;

    private UploadedFile file;

    @PostConstruct
    public void inicio() {
        view = new RegistroAspiranteView();

        cargarCatalogo();
    }

    public void cargarCatalogo() {
        List<CatalogoDTO> listaPaises = catalogosEJB.listaPaises();
        List<CatalogoDTO> listaPuestos = catalogosEJB.listaPuestos();
        List<CatalogoDTO> listaDepartamentos = catalogosEJB.listaDepartamentos();

        view.setListaPaises(SelectItemsUtil.listaCatalogos(listaPaises));
        view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
        view.setListaNacionalidades(SelectItemsUtil.listaNacionalidad());
        view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
        view.setListaTiposSexo(SelectItemsUtil.listaTiposSexo());
        view.setListaPuestos(SelectItemsUtil.listaCatalogos(listaPuestos));
        view.setListaDepartamentos(SelectItemsUtil.listaCatalogos(listaDepartamentos));
        view.setListaViveCon(SelectItemsUtil.listaViveCon());
        view.setListaDependientes(SelectItemsUtil.listaDependientes());
        view.setListaTiposLicencia(SelectItemsUtil.listaTiposLicencia());
        view.setMostrarDatosPersonales(true);
        view.setListaEstados(SelectItemsUtil.listaCatalogos(catalogosEJB.listaEstados()));
    }

    public void menu(int panel) {
        view.setMostrarDatosPersonales(false);
        view.setMostrarHistorialAcademico(false);
        view.setMostrarAdjuntarDocumentoHistorial(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarEscuestaPersonal(false);
        view.setMostrarExpediente(false);
        view.setMostrarProfesion(false);
        view.setMostrarEspecialidad(false);

        try {

            switch (panel) {

                case DATOS_GENERALES:
                    view.setAltaAspirante(new AspiranteDTO());
                    view.setMostrarDatosPersonales(true);
                    break;

                case HISTORIAL_ACADEMICO:

                    view.setAltaHistorialAcademico(new HistorialAcademicoDTO());
                    view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());

                    view.setMostrarHistorialAcademico(true);
                    view.setMostrarHistorialAcademicoRegistro(true);
                    view.setMostrarAdjuntarDocumentoHistorial(false);

                    view.setListaEscolaridades(SelectItemsUtil.listaEscolaridad(catalogosEJB.listaEscolaridad()));
                    view.setListaComprobantesEstudio(SelectItemsUtil.listaCatalogos(catalogosEJB.listaComprobantesEstudios()));

                    view.setListaHistorialAcademicoAspirante(bolsaTrabajo.obtenerListaHistorialAcademico(idAspirante));

                    break;

                case PROFESION:

                    view.setIdProfesion(0);

                    view.setMostrarProfesion(true);

                    List<ProfesionDTO> profesiones = profesion.obtenerListaProfesionPorIdAspirante(idAspirante);

                    view.setListaProfesion(profesiones);

                    view.setListaTipoProfesion(SelectItemsUtil.listaCatalogos(catalogosEJB.obtenerListaProfesion()));

                    break;

                case ESPECIALIDAD:

                    view.setIdEspecialidad(0);

                    view.setMostrarEspecialidad(true);

                    List<EspecialidadDTO> especialidades = especialidad.obtenerListaEspecialidadPorIdAspirante(idAspirante);

                    view.setListaEspecialidad(especialidades);

                    view.setListaTipoEspecialidad(SelectItemsUtil.listaCatalogos(catalogosEJB.obtenerListaEspecialidad()));

                    break;
                case EXPERIENCIA_LABORAL:

                    view.setAltaExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());

                    view.setMostrarExperienciaLaboral(true);
                    view.setMostrarRazonNosolicitar(true);

                    List<CatalogoDTO> listaEstados = catalogosEJB.listaEstados();
                    view.setListaEstados(SelectItemsUtil.listaCatalogos(listaEstados));

                    view.setListaExperienciaLaboralAspirante(bolsaTrabajo.obtenerListaExperienciaLaboral(idAspirante));

                    break;
                case HABILIDADES_PERSONALES:

                    view.setAltaEncuestaPersonal(new HabilidadesPersonalesAspiranteDTO());
                    view.setMostrarEscuestaPersonal(true);

                    view.setMostrarTxtOtroMedio(true);
                    view.setMostrarTxtNombresParientes(true);
                    view.setMostrarTxtNombreAfianza(true);
                    view.setMostrarTxtNombreSindicato(true);
                    view.setMostrarTxtNombreSegurVida(true);
                    view.setMostrarTxtRazonNoViajar(true);
                    view.setMostrarTxtRazonNoCambioResidencia(true);
                    view.setMostrarTxtOtrosIngresos(true);
                    view.setMostrarTxtConyugeTrabaja(true);
                    view.setMostrarTxtCasaPropia(true);
                    view.setMostrarTxtPagaRenta(true);
                    view.setMostrarTxtAutomovilPropio(true);
                    view.setMostrarTxtTieneDeudas(true);

                    break;

                case EXPEDIENTE:

                    // this.view.setMostrarExpediente(true);

                    List<CatalogoDTO> lista = catalogosEJB.consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_PERSONALES);
                    view.setListaTiposDocumentosExpediente(SelectItemsUtil.listaCatalogos(lista));

                    view.setMostrarExpediente(true);

                    List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoAspirante.consultarInformacionAdjuntosPorIdAspirante(idAspirante);

                    view.setDocumentosExpedientes(documentosExpedientes);

                    if (expedienteAspirante.tieneExpedienteAperturado(idAspirante)) {
                        System.out.println("Tiene aperturado un expediente");
                        String numeroExpediente = expedienteAspirante.numeroExpedienteAspirante(idAspirante);
                        Integer idExpediente = expedienteAspirante.obtenerIdExpedienteAspirante(idAspirante);
                        view.setImagenExpediente("expediente_aperturado.png");
                        view.setMostrarActualizacionExpediente(true);
                        view.setNumeroExpediente(numeroExpediente);
                        view.setIdExpediente(idExpediente);
                    } else {
                        System.out.println("No tiene aperturado un expediente");

                        view.setImagenExpediente("sin_expediente.png");
                        view.setMostrarAperturaExpediente(true);
                    }

                    break;
            }

        } catch (BusinessException exception) {
            throw new BusinessException(exception.getMessage());
        }

    }

    public void municipiosPorEstado() {
        view.setListaAsentamientos(new ArrayList<SelectItem>());
        view.setListaMunicipios(new ArrayList<SelectItem>());
        view.getAltaAspirante().getDireccionDTO().setIdMunicipio(0);
        view.getAltaAspirante().getDireccionDTO().setIdAsentamiento(0);
        Integer idEstado = view.getAltaAspirante().getDireccionDTO().getIdEstado();
        if (idEstado != 0) {
            List<CatalogoDTO> municipios = catalogosEJB.consultarMunicipiosPorEstado(idEstado);

            view.setListaMunicipios(SelectItemsUtil.listaCatalogos(municipios));

        }
    }

    public void asentamientosPorMunicipio() {
        Integer idMunicipio = view.getAltaAspirante().getDireccionDTO().getIdMunicipio();
        if (idMunicipio != 0) {
            List<CatalogoDTO> asentamientos = catalogosEJB.consultarAsantamientosPorMunicipios(idMunicipio);
            view.getListaAsentamientos().clear();
            view.setListaAsentamientos(SelectItemsUtil.listaCatalogos(asentamientos));

        }
    }

    public void registrarDatosGenerales() {
        try {

            if (view.getPersonasDependientes() != null) {
                for (String personaDependiente : view.getPersonasDependientes()) {
                    if (personaDependiente.equals("CONYUGE")) {
                        view.getAltaAspirante().setNumeroConyuges(1);
                        System.out.println("si tiene conyuge");
                    }

                }

            }

            idAspirante = bolsaTrabajo.crearAspirante(view.getAltaAspirante());
            JSFUtils.infoMessage("Registro Datos Generales: ", "Se realizo correctamente");
            mostrarPanelHistorialAcademico();
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void subirFotoAspirate(FileUploadEvent evento) {
        // 3 Mb como limite
        String contexto = "Foto Aspirante: ";

        String png = "image/png";
        String jpg = "image/jpg";
        String jpeg = "image/jpeg";

        if (!evento.getFile().getContentType().equals(png) & !evento.getFile().getContentType().equals(jpg) & !evento.getFile().getContentType().equals(jpeg)) {
            JSFUtils.errorMessage(contexto, "El formato de la foto es invalido: *.png, *.jpg y *.jpeg");
        } else {

            if (idAspirante == 0) {
                JSFUtils.errorMessage(contexto, "Es necesario registrar los datos generales");
            } else {
                try {

                    UploadedFile archivo = evento.getFile();
                    String nombreAdjunto = archivo.getFileName();
                    byte[] adjunto = archivo.getContents();

                    // TipoArchivo extension = TipoArchivo
                    // .getTipoArchivoPorMIMEType(archivo.getContentType());

                    Integer idAspirante = (Integer) evento.getComponent().getAttributes().get("idEmpleado");
                    System.out.println(idAspirante + "¬¬");
                    //
                    // InformacionAdjuntoDTO info = new
                    // InformacionAdjuntoDTO();
                    //
                    // DocumentoAdjuntableDTO dto = new
                    // DocumentoAdjuntableDTO();
                    // dto.setIdDocumentoAdjuntable("FOTO");
                    //
                    // info.setTipoDocumento(TipoDocumento.EMPLEADO);
                    // info.setIdTipoDocumento(idEmpleado);
                    // info.setIdAdjunto(null);
                    // info.setNombreAdjunto(nombreAdjunto);
                    // info.setExtension(extension);
                    // info.setDocumentoAdjuntable(dto);
                    // info.setIdEmpleado(this.view.getDatosGenerales().getIdEmpleado());
                    //
                    // Integer idFotografia =
                    // adjuntoEJB.guardarAdjunto(info,
                    // adjunto);
                    // empleadoEJB.actualizarFotografia(this.view.getDatosGenerales()
                    // .getIdEmpleado(), idFotografia);
                    // this.view.getDatosGenerales().setIdFotografia(idFotografia);
                    view.setMostrarSinFoto(false);
                    view.setMostrarFoto(true);

                    JSFUtils.infoMessageEspecifico("info", "Subir foto: ", "La foto " + nombreAdjunto + " se ha subido correctamente.");
                } catch (Exception ex) {
                    JSFUtils.errorMessageEspecifico("error", "Error al adjuntar el archivo", "El archivo no se ha adjuntado.");
                }
            }
        }
    }

    public void subirDocumentoAdjunto(FileUploadEvent evento) {
        try {

            UploadedFile archivo = evento.getFile();
            String nombreAdjunto = archivo.getFileName();
            byte[] adjunto = archivo.getContents();

            TipoArchivo extension = TipoArchivo.getTipoArchivoPorMIMEType(archivo.getContentType());

            Integer idAspiranteFile = (Integer) evento.getComponent().getAttributes().get("idAspirante");
            Integer idDocAdj = (Integer) evento.getComponent().getAttributes().get("idDocAdj");

            InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

            DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
            dto.setIdDocumentoAdjuntable(idDocAdj);
            info.setEntidadContexto(EntidadContexto.ASPIRANTE);
            info.setIdEntidadContexto(idAspiranteFile);
            info.setIdAdjunto(null);
            info.setNombreAdjunto(nombreAdjunto);
            info.setExtension(extension);
            info.setDocumentoAdjuntable(dto);
            info.setIdAspirante(idAspirante);
            info.setIdExpediente(view.getIdExpediente());

            adjuntoAspirante.crear(info, adjunto);

            view.getDocumentosExpedientes().clear();

            List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoAspirante.consultarInformacionAdjuntosPorIdAspirante(idAspirante);

            view.setDocumentosExpedientes(documentosExpedientes);

        } catch (BusinessException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

    public void visualizarImagen(Integer idImagenExpediente) {
        view.setIdImagenExpediente(idImagenExpediente);
        view.setMostrarImagenExpediente(true);
    }

    public void registrarHistorialAcademico() {
        try {

            HistorialAcademicoDTO altaHistorialAcademico = view.getAltaHistorialAcademico();
            altaHistorialAcademico.setAspirante(idAspirante);

            bolsaTrabajo.crearHistorialAcademicoAspirante(altaHistorialAcademico);

            view.setAltaHistorialAcademico(new HistorialAcademicoDTO());
            view.setListaHistorialAcademicoAspirante(bolsaTrabajo.obtenerListaHistorialAcademico(idAspirante));

            JSFUtils.infoMessage("Registro Historial Académico: ", "Se realizo correctamente");
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void mostrarAdjuntarDocumentoHistorial(HistorialAcademicoDTO historialAcademicoSeleccionado) {

        if (historialAcademicoSeleccionado != null) {

            view.setHistorialAcademicoSeleccionado(historialAcademicoSeleccionado);

            view.setMostrarAdjuntarDocumentoHistorial(true);
            view.setMostrarHistorialAcademicoRegistro(false);

            List<CatalogoDTO> documentosAdjuntables = catalogosEJB
                    .consultarDocumentosExpedientesClasificacion(EnumClasificacionExpediente.DOCUMENTOS_HISTORIAL);

            view.setListaDocumentosHistorialAcademico(SelectItemsUtil.listaCatalogos(documentosAdjuntables));

        } else {
            JSFUtils.warningMessageEspecifico("historialAcademico", "", "Seleccione el grado academico al que desea adjuntarle documentacion.");
        }
    }

    public void subirDocumentoAdjuntoHistorial(FileUploadEvent evento) {
        try {
            if (!ValidacionUtil.esNumeroPositivo(view.getAltaHistorialAcademico().getIdHistorialAcademico())) {
                throw new BusinessException("Adjuntar documento: Es requerido seleccionar el tipo de documento que se está adjuntando, seleccione una opcion.");
            } else if (ValidacionUtil.esCadenaVacia(view.getNumeroExpediente())) {
                throw new BusinessException("Adjuntar documento historial: Por favor aperture el expediente para poder adjuntarle el documento.");
            } else {

            }
            System.out.println(view.getIdDocumentoAdjuntable());
            UploadedFile archivo = evento.getFile();
            String nombreAdjunto = archivo.getFileName();
            byte[] adjunto = archivo.getContents();

            // TipoArchivo extension = TipoArchivo
            // .getTipoArchivoPorMIMEType(archivo.getContentType());
            //
            // InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();
            //
            // DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
            // dto.setIdDocumentoAdjuntable(this.view.getIdDocumentoAdjuntable());
            //
            // info.setTipoDocumento(TipoDocumento.HISTORIAL_ACADEMICO);
            // info.setIdTipoDocumento(this.view.getHistorialSeleccionado()
            // .getIdHistorialAcademico());
            // info.setIdAdjunto(null);
            // info.setNombreAdjunto(nombreAdjunto);
            // info.setExtension(extension);
            // info.setDocumentoAdjuntable(dto);
            // info.setIdEmpleado(this.view.getDatosGenerales().getIdEmpleado());

            view.setMostrarAdjuntarDocumentoHistorial(false);
            view.setIdDocumentoAdjuntable("");

            // adjuntoEJB.guardarAdjunto(info, adjunto);
            // empleadoEJB.actualizarAdjuntoHistorial(this.view
            // .getHistorialSeleccionado().getIdHistorialAcademico());
            //
            // List<HistorialAcademicoDTO> historial = empleadoEJB
            // .historialEmpleado(this.view.getDatosGenerales()
            // .getIdEmpleado());
            // this.view.setHistoriales(historial);
            JSFUtils.infoMessageEspecifico("info", "Adjuntar documento", "El documento " + nombreAdjunto + " se ha adjuntado correctamente.");

            // mostrarPanelHistorialAcademico();

        } catch (Exception ex) {
            JSFUtils.errorMessageEspecifico("error", "Error al adjuntar el archivo", "El archivo no se ha adjuntado.");
        }
    }

    public void registrarProfesionAspirante() {
        try {
            profesion.crearProfesionAspirante(view.getIdProfesion(), idAspirante);
            view.setIdProfesion(0);
            List<ProfesionDTO> profesiones = profesion.obtenerListaProfesionPorIdAspirante(idAspirante);

            view.setListaProfesion(profesiones);

            JSFUtils.infoMessage("Registro Profesión: ", "Se finalizo correctamente");
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void registrarEspecialidadAspirante() {
        try {
            especialidad.crearEspecialidadAspirante(view.getIdEspecialidad(), idAspirante);
            List<EspecialidadDTO> especialidades = especialidad.obtenerListaEspecialidadPorIdAspirante(idAspirante);

            view.setListaEspecialidad(especialidades);
            view.setIdEspecialidad(0);
            JSFUtils.infoMessage("Registro Especialidad: ", "Se finalizo correctamente");
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void registrarExperienciaLaboral() {
        try {

            ExperienciaLaboralAspiranteDTO altaExperienciaLaboral = view.getAltaExperienciaLaboral();
            altaExperienciaLaboral.setIdAspirante(idAspirante);

            bolsaTrabajo.crearExperienciaLaboralAspirante(altaExperienciaLaboral);
            view.setAltaExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());
            view.setListaExperienciaLaboralAspirante(bolsaTrabajo.obtenerListaExperienciaLaboral(idAspirante));
            JSFUtils.infoMessage("Registro Experiencia Laboral: ", "Se realizo correctamente");
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void registrarEncuestaPersonal() {
        try {

            HabilidadesPersonalesAspiranteDTO escuestaPersonal = view.getAltaEncuestaPersonal();
            escuestaPersonal.setIdAspirante(idAspirante);

            bolsaTrabajo.crearHabilidadesPersonalesAspirante(escuestaPersonal);

            // regresarVista();
            limpiarVista();
            JSFUtils.infoMessage("Registro Aspirante: ", "Se finalizo correctamente");
        } catch (BusinessException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }

    }

    public void cargarDocumentoAdjunto(FileUploadEvent event) {

        file = event.getFile();

    }

    public void eliminarExperienciaLaboral() {
        try {
            bolsaTrabajo.eliminarExperienciaLaboral(view.getExperienciaLaboralAspiranteSeleccionado().getIdExperienciaLaboralAspirante());

            view.setMostrarDialogExperiencia(false);

            view.setListaExperienciaLaboralAspirante(bolsaTrabajo.obtenerListaExperienciaLaboral(idAspirante));

            JSFUtils.infoMessage("Experiencia Laboral: ", "Se Elimino Correctamente");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void experienciaLaboralSeleccionado(ExperienciaLaboralAspiranteDTO experienciaLaboral) {
        view.setMostrarDialogExperiencia(true);
        view.setExperienciaLaboralAspiranteSeleccionado(experienciaLaboral);
    }

    public void cerrarDialogExperiencia() {
        view.setMostrarDialogExperiencia(false);
        view.setExperienciaLaboralAspiranteSeleccionado(new ExperienciaLaboralAspiranteDTO());
    }

    public void eliminarHistorialAcademico() {
        try {
            bolsaTrabajo.eliminarHistorialAcademico(view.getHistorialAcademicoSeleccionado().getIdHistorialAcademico());
            view.setListaHistorialAcademicoAspirante(bolsaTrabajo.obtenerListaHistorialAcademico(idAspirante));
            view.setMostrarDialogHistorial(false);

            JSFUtils.infoMessage("Historial Academico: ", "Se Elimino Correctamente");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void historialAcademicoSeleccionado(HistorialAcademicoDTO historialSeleccionado) {
        view.setHistorialAcademicoSeleccionado(historialSeleccionado);
        view.setMostrarDialogHistorial(true);
    }

    public void cerrarDialogHistorial() {
        view.setMostrarDialogHistorial(false);
        view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());

    }

    public void aperturarExpediente() {
        try {

            view.getExpediente().setIdAspirante(idAspirante);

            // Creando expediente del aspirante
            expedienteAspirante.crearExpediente(view.getExpediente());

            view.setMostrarAperturaExpediente(false);
            view.setMostrarActualizacionExpediente(true);
            view.setNumeroExpediente(view.getExpediente().getNumeroExpediente().toUpperCase());
            view.setImagenExpediente("expediente_aperturado.png");

            Integer idExpediente = expedienteAspirante.obtenerIdExpedienteAspirante(idAspirante);
            view.setIdExpediente(idExpediente);

        } catch (BusinessException exception) {
            throw new BusinessException(exception.getMessage());
        }

    }

    

    public void abrirDialogoEliminar() {
        view.setMostrarDialogEliminar(true);
    }

    public void cerrarDialogoEliminar() {

        view.setMostrarDialogEliminar(false);
    }

    public void mostrarDialogoEliminarHistorialAcademico() {
        view.setMostrarDialogoEliminarHistorial(true);
    }

    public void cerrarDialogoEliminarHistorialAcademico() {
        view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());
        view.setMostrarDialogoEliminarHistorial(true);
    }

    

    public void limpiarVista() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        session.removeAttribute("registroAspirante");
    }

    public String regresarVista() {
        return "/contenido/aspirante/registroAspirante.xhtml?faces-redirect=true";
    }

    public void mostrarPanelHistorialAcademico() {
        view.setAltaHistorialAcademico(new HistorialAcademicoDTO());
        view.setHistorialAcademicoSeleccionado(new HistorialAcademicoDTO());
        view.setMostrarDatosPersonales(false);
        view.setMostrarHistorialAcademico(true);
        view.setMostrarHistorialAcademicoRegistro(true);
        view.setMostrarAdjuntarDocumentoHistorial(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarEscuestaPersonal(false);
        view.setMostrarAdjuntarDocumento(false);
        view.setMostrarExpediente(false);

        view.setListaEscolaridades(SelectItemsUtil.listaEscolaridad(catalogosEJB.listaEscolaridad()));
        view.setListaComprobantesEstudio(SelectItemsUtil.listaCatalogos(catalogosEJB.listaComprobantesEstudios()));

        view.setListaHistorialAcademicoAspirante(bolsaTrabajo.obtenerListaHistorialAcademico(idAspirante));
    }

    public void mostrarRazonNoSolicitarInformacion() {
        if (view.getAltaExperienciaLaboral().getSolicitarInformacion()) {
            view.getAltaExperienciaLaboral().setRazonNoSolicitar("");
            view.setMostrarRazonNosolicitar(true);
        } else {
            view.getAltaExperienciaLaboral().setRazonNoSolicitar("");
            view.setMostrarRazonNosolicitar(false);
        }
    }

    public void mostrarTxtOtroMedio() {
        if (view.getAltaEncuestaPersonal().getAnuncio()) {
            view.getAltaEncuestaPersonal().setOtroMedio("");
            view.setMostrarTxtOtroMedio(false);
        } else {
            view.getAltaEncuestaPersonal().setOtroMedio("");
            view.setMostrarTxtOtroMedio(true);
        }
    }

    public void mostrarTxtNombresParientes() {
        if (view.getAltaEncuestaPersonal().isParientes()) {
            view.getAltaEncuestaPersonal().setNombreParientes("");
            view.setMostrarTxtNombresParientes(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreParientes("");
            view.setMostrarTxtNombresParientes(true);
        }
    }

    public void mostrarTxtNombreAfianza() {
        if (view.getAltaEncuestaPersonal().isAfianzado()) {
            view.getAltaEncuestaPersonal().setNombreAfianza("");
            view.setMostrarTxtNombreAfianza(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreAfianza("");
            view.setMostrarTxtNombreAfianza(true);
        }
    }

    public void mostrarTxtNombreSindicato() {
        if (view.getAltaEncuestaPersonal().isSindicato()) {
            view.getAltaEncuestaPersonal().setNombreSindicato("");
            view.setMostrarTxtNombreSindicato(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreAfianza("");
            view.setMostrarTxtNombreSindicato(true);
        }
    }

    public void mostrarTxtNombreSegurVida() {
        if (view.getAltaEncuestaPersonal().isSeguroVida()) {
            view.getAltaEncuestaPersonal().setNombreSeguroVida("");
            view.setMostrarTxtNombreSegurVida(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreSeguroVida("");
            view.setMostrarTxtNombreSegurVida(true);
        }
    }

    public void mostrarTxtRazonNoViajar() {
        if (!view.getAltaEncuestaPersonal().isDisposicionViajar()) {
            view.getAltaEncuestaPersonal().setRazonNoViajar("");
            view.setMostrarTxtRazonNoViajar(false);
        } else {
            view.getAltaEncuestaPersonal().setRazonNoViajar("");
            view.setMostrarTxtRazonNoViajar(true);
        }
    }

    public void mostrarTxtRazonNoCambioResidencia() {
        if (!view.getAltaEncuestaPersonal().isCambioResidencia()) {
            view.getAltaEncuestaPersonal().setRazonNoCambioResidencia("");
            view.setMostrarTxtRazonNoCambioResidencia(false);
        } else {
            view.getAltaEncuestaPersonal().setRazonNoCambioResidencia("");
            view.setMostrarTxtRazonNoCambioResidencia(true);
        }
    }

    public void mostrarTxtOtrosIngresos() {
        if (view.getAltaEncuestaPersonal().isOtroIngreso()) {
            view.getAltaEncuestaPersonal().setNombreOtroIngreso("");
            view.getAltaEncuestaPersonal().setImporteOtroIngreso(new BigDecimal(0));
            view.setMostrarTxtOtrosIngresos(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreOtroIngreso("");
            view.getAltaEncuestaPersonal().setImporteOtroIngreso(new BigDecimal(0));
            view.setMostrarTxtOtrosIngresos(true);
        }
    }

    public void mostrarTxtConyugeTrabaja() {
        if (view.getAltaEncuestaPersonal().isConyugeTrabajando()) {
            view.getAltaEncuestaPersonal().setNombreTrabajoConyuge("");
            view.getAltaEncuestaPersonal().setPercepcionMensualConyuge(new BigDecimal(0));
            view.setMostrarTxtConyugeTrabaja(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreTrabajoConyuge("");
            view.getAltaEncuestaPersonal().setPercepcionMensualConyuge(new BigDecimal(0));
            view.setMostrarTxtConyugeTrabaja(true);
        }
    }

    public void mostrarTxtCasaPropia() {
        if (view.getAltaEncuestaPersonal().isCasaPropia()) {
            view.getAltaEncuestaPersonal().setValorAproximadoCasa(new BigDecimal(0));
            view.setMostrarTxtCasaPropia(false);
        } else {
            view.getAltaEncuestaPersonal().setValorAproximadoCasa(new BigDecimal(0));
            view.setMostrarTxtCasaPropia(true);
        }
    }

    public void mostrarTxtPagaRenta() {
        if (view.getAltaEncuestaPersonal().isRentaCasa()) {
            view.getAltaEncuestaPersonal().setRentaMensual(new BigDecimal(0));
            view.setMostrarTxtPagaRenta(false);
        } else {
            view.getAltaEncuestaPersonal().setRentaMensual(new BigDecimal(0));
            view.setMostrarTxtPagaRenta(true);
        }
    }

    public void mostrarTxtAutomovilPropio() {
        if (view.getAltaEncuestaPersonal().isAutomovilPropio()) {
            view.getAltaEncuestaPersonal().setMarcaAutomovil("");
            view.getAltaEncuestaPersonal().setModeloAutomovil("");
            view.setMostrarTxtAutomovilPropio(false);
        } else {

            view.getAltaEncuestaPersonal().setMarcaAutomovil("");
            view.getAltaEncuestaPersonal().setModeloAutomovil("");
            view.setMostrarTxtAutomovilPropio(true);
        }
    }

    public void mostrarTxtTieneDeudas() {
        if (view.getAltaEncuestaPersonal().isDeudas()) {
            view.getAltaEncuestaPersonal().setNombreDeuda("");
            view.getAltaEncuestaPersonal().setImporteDeuda(new BigDecimal(0));
            view.getAltaEncuestaPersonal().setAbonoMensualDeuda(new BigDecimal(0));
            view.setMostrarTxtTieneDeudas(false);
        } else {
            view.getAltaEncuestaPersonal().setNombreDeuda("");
            view.getAltaEncuestaPersonal().setImporteDeuda(new BigDecimal(0));
            view.getAltaEncuestaPersonal().setAbonoMensualDeuda(new BigDecimal(0));
            view.setMostrarTxtTieneDeudas(true);
        }
    }

    

    public void validatorDatosGenerales(FacesContext context, UIComponent component, Object value) throws ValidatorException, BusinessException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

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

            case "rfc":
                String rfc = (String) value;

                if (ValidacionUtil.esCadenaVacia(rfc)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un rfc.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    try {

                        bolsaTrabajo.validarRfcAspirante(rfc);
                    } catch (BusinessException ex) {
                        FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
                        context.addMessage(component.getClientId(), facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    }

                }

                break;

            case "curp":
                String curp = (String) value;

                if (ValidacionUtil.esCadenaVacia(curp)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una curp.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    try {
                        bolsaTrabajo.validarCurpAspirante(curp);
                    } catch (BusinessException exception) {
                        FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", exception.getMessage());
                        context.addMessage(component.getClientId(), facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    }

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
            /*
             * Se comento por que no requiere ser validado
             * case "estatura": float estatura = (float) value;
             *
             * if (estatura <= 0) { FacesMessage facesMessage1 = new
             * FacesMessage(FacesMessage.SEVERITY_ERROR, "",
             * "Por favor ingrese la estatura.");
             * context.addMessage(component.getClientId(), facesMessage1); throw new
             * ValidatorException(facesMessage1); } break;
             *
             * case "tipoSangre": String tipoSangre = (String) value;
             *
             * if (ValidacionUtil.esCadenaVacia(tipoSangre)) { FacesMessage
             * facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
             * "Por favor seleccione el tipo de sangre.");
             * context.addMessage(component.getClientId(), facesMessage1); throw new
             * ValidatorException(facesMessage1); } break;
             *
             * case "peso": float peso = (float) value;
             *
             * if (peso <= 0) { FacesMessage facesMessage1 = new
             * FacesMessage(FacesMessage.SEVERITY_ERROR, "",
             * "Por favor ingrese el peso.");
             * context.addMessage(component.getClientId(), facesMessage1); throw new
             * ValidatorException(facesMessage1); } break;
             */
            case "telefono":
                String telefono = (String) value;

                if (ValidacionUtil.esCadenaVacia(telefono)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el telefono.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "puesto":
                Integer puesto = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(puesto)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el puesto.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

        }

    }

    public void validatorDomicilio(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();

        if (nombreComponete.equals("estado")) {
            Integer estado = (Integer) value;

            if (!ValidacionUtil.esNumeroPositivo(estado)) {
                FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un estado.");
                context.addMessage(component.getClientId(), facesMessage1);
                throw new ValidatorException(facesMessage1);
            }
        }

        if (nombreComponete.equals("municipio")) {
            Integer municipio = (Integer) value;

            if (!ValidacionUtil.esNumeroPositivo(municipio)) {
                FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un municipio.");
                context.addMessage(component.getClientId(), facesMessage1);
                throw new ValidatorException(facesMessage1);
            }
        }

        if (nombreComponete.equals("asentamiento")) {
            Integer poblacion = (Integer) value;

            if (!ValidacionUtil.esNumeroPositivo(poblacion)) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un asentamiento.");
                context.addMessage(component.getClientId(), facesMessage);
                throw new ValidatorException(facesMessage);
            }
        }

        if (nombreComponete.equals("calle")) {
            String calle = (String) value;

            if (ValidacionUtil.esCadenaVacia(calle)) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una calle.");
                context.addMessage(component.getClientId(), facesMessage);
                throw new ValidatorException(facesMessage);
            }
        }
        if (nombreComponete.equals("exterior")) {
            String exterior = (String) value;

            if (ValidacionUtil.esCadenaVacia(exterior)) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un número exterior.");
                context.addMessage(component.getClientId(), facesMessage);
                throw new ValidatorException(facesMessage);
            }
        }

        if (nombreComponete.equals("cp")) {
            Integer codigoPostal = (Integer) value;

            if (!ValidacionUtil.esNumeroPositivo(codigoPostal)) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el codigo postal.");
                context.addMessage(component.getClientId(), facesMessage);
                throw new ValidatorException(facesMessage);
            }
        }
    }

    public void validatorDatosHistorialAcademico(FacesContext context, UIComponent component, Object value) throws ValidatorException, BusinessException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "escolaridad":
                Integer escolaridad = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(escolaridad)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione la escolaridad.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "institucion":
                String institucion = (String) value;

                if (ValidacionUtil.esCadenaVacia(institucion)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el nombre de la institución.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "comprobanteEstudio":
                Integer comprobanteEstudio = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(comprobanteEstudio)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el comprobante de estudio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "fechaInicial":
                Date fechaInicial = (Date) value;

                if (fechaInicial == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una fecha de inicial.");
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
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el nombre de la escolaridad.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

        }

    }

    public void validatorTipoDocAdjHistorialAcademico(FacesContext context, UIComponent component, Object value) throws ValidatorException, BusinessException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "idDocumentoAdjuntable":
                String idDocumentoAdjuntable = (String) value;

                if (ValidacionUtil.esCadenaVacia(idDocumentoAdjuntable)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el tipo de documento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public void validatorExpediente(FacesContext context, UIComponent component, Object value) {
        String nombreComponente = component.getId();

        switch (nombreComponente) {
            case "numeroExpediente":
                String numeroExpediente = (String) value;

                if (ValidacionUtil.esCadenaVacia(numeroExpediente)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese un número de expediente");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else if (!ValidacionUtil.esCadenaVacia(numeroExpediente)) {
                    try {
                        expedienteAspirante.validarNumeroExpedienteAspirante(numeroExpediente);
                    } catch (BusinessException ex) {
                        FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
                        context.addMessage(component.getClientId(), facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    }
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
            case "puestoDesempeniado":
                String puestoDesempeniado = (String) value;

                if (ValidacionUtil.esCadenaVacia(puestoDesempeniado)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el puesto o labores que desempeñó.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "anioInicial":
                Date anioInicial = (Date) value;

                if (anioInicial == null) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese la fecha en la que inició sus labores.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;
            case "anioFinal":
                Date anioFinal = (Date) value;

                if (anioFinal == null) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese la fecha en la que finalizó sus labores.");
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
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el idioma(s) que domine.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;

            case "nivelIdioma":
                Integer nivelIdioma = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(nivelIdioma)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione el nivel idioma(s) que domine.");
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
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese la funcion(es) de oficina que domine.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;

            case "softwareDomina":
                String softwareDomina = (String) value;

                if (ValidacionUtil.esCadenaVacia(softwareDomina)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el software(s) que domine.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;

            default:
                break;
        }
    }

    public void validarCampoProfesionEspecialidad(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponente = component.getId();
        String contexto = "Campo requerido.";

        switch (nombreComponente) {

            case "tipoProfesion":

                Integer tipoProfesion = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(tipoProfesion)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto, "Seleccione el tipo profesión");
                    context.addMessage(component.getClientId(), facesMessage);

                    throw new ValidatorException(facesMessage);
                }

                break;

            case "tipoEspecialidad":

                Integer tipoEspecialidad = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(tipoEspecialidad)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto, "Seleccione el tipo especialidad");
                    context.addMessage(component.getClientId(), facesMessage);

                    throw new ValidatorException(facesMessage);
                }

                break;

            default:
                JSFUtils.errorMessage("Registro Aspirante: ", "Error de validación...");
                break;
        }

    }

    public RegistroAspiranteView getView() {
        return view;
    }

    public void setView(RegistroAspiranteView view) {
        this.view = view;
    }

    /**
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return idAspirante;
    }

    /**
     * @param idAspirante
     *            the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public static int getProfesion() {
        return PROFESION;
    }

    public static int getEspecialidad() {
        return ESPECIALIDAD;
    }

}
