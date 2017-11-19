/*
 *
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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
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
        view.setListaFiltros(SelectItemsUtil.listaFiltrosConsultaAspirantes());
        view.setListaTiposSexos(SelectItemsUtil.listaTiposSexo());
        view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
        view.setListaEscolaridades(
                SelectItemsUtil.listaCatalogos(catalogo.listaEscolaridades()));
        view.setListaComprobantesEstudios(SelectItemsUtil
                .listaCatalogos(catalogo.listaComprobantesEstudios()));
        view.setListaTiposParentescos(SelectItemsUtil.listaParentescos());
        view.setListaEstados(
                SelectItemsUtil.listaCatalogos(catalogo.listaEstados()));
        view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
    }

    public void obtenerConsultaEmpleado() {

        view.getFiltro()
                .setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
        List<InfoEmpleadoDTO> listaInfoEmpleado = empleado
                .consultaEmpleado(view.getFiltro());

        view.setMostrarMenuDetalles(false);

        if (!listaInfoEmpleado.isEmpty()) {

            view.setEmpleados(listaInfoEmpleado);
            view.setMostrarResultadoConsulta(true);

            view.setMostrarTipoBusquedaHeader(false);
            view.setTipoBusquedaHeader("");

        } else {
            view.setEmpleados(new ArrayList<InfoEmpleadoDTO>());
            view.setMostrarResultadoConsulta(false);

            view.setFiltro(new FiltroDTO());
            JSFUtils.errorMessage("Consulta Empleado",
                    "No se encontrarón resultados, intentelo de nuevo");
        }
    }

    public void menu(int panel) {

        try {

            initMenu();

            switch (panel) {

                case DATOS_GENERALES:

                    view.setMostrarDatosGenerales(true);

                    break;

                case DOMICILIO:

                    view.setMostrarDomicilio(true);

                    DomicilioDTO domicilioDTO = empleado
                            .obtenerDomicilio(view.getIdEmpleado());

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

                    view.setMostrarHistorialAcademico(true);

                    List<HistorialAcademicoDTO> historial = historialAcademico
                            .consultarHistorialAcademicoEmpleado(
                                    view.getIdEmpleado());

                    view.setListaHistorialesAcademicos(historial);

                    break;

                case EXPERIENCIA_LABORAL:

                    view.setMostrarExperienciaLaboral(true);

                    List<ExperienciaLaboralDTO> experiencias = experienciaLaboral
                            .consultaExperienciaLaboralEmpleado(
                                    view.getIdEmpleado());
                    view.setListaExperienciasLaborales(experiencias);

                    break;

                case DEPENDIENTES_ECONOMICOS:

                    view.setMostrarDependientesEconomicos(true);

                    List<CatalogoDTO> lista = catalogo
                            .consultarDocumentosExpedientesClasificacion(
                                    EnumClasificacionExpediente.DOCUMENTOS_DEPENDIENTES);
                    view.setListaDocumentosDependientes(
                            SelectItemsUtil.listaCatalogos(lista));
                    view.setMostrarDependientesEconomicos(true);
                    List<InfoDependienteEconomicoDTO> dependientes = empleado
                            .consultarDependientesEmpleado(
                                    view.getIdEmpleado());
                    view.setDependientesEconomicos(dependientes);

                    break;

                case EXPEDIENTE:

                    view.setMostrarExpediente(true);

                    List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado
                            .consultarInformacionAdjuntosPorIdEmpleado(
                                    view.getIdEmpleado());

                    if (!documentosExpedientes.isEmpty()) {
                        view.setDocumentosExpedientes(documentosExpedientes);
                    } else {
                        view.setDocumentosExpedientes(
                                new ArrayList<InformacionAdjuntoDTO>());
                        JSFUtils.warningMessage("Documento Expediente: ",
                                "No tiene registrado ningún documento");
                    }

                    break;
                case PRESUPUESTAL:
                    view.setMostrarPuesto(true);
                    view.setPuesto(puestosAutorizados
                            .obtenerInformacionPuestoIdEmpleado(
                                    view.getIdEmpleado()));

                    break;

                case BITACORA:
                    view.setMostrarBitacora(true);
                    view.setBitacorasMovimientos(
                            empleado.consultarBitacorasMovimientos(
                                    view.getIdEmpleado()));

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
        view.setMostrarDatosGenerales(false);
        view.setMostrarDomicilio(false);
        view.setMostrarHistorialAcademico(false);
        view.setMostrarProfesion(false);
        view.setMostrarEspecialidad(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarDependientesEconomicos(false);
        view.setMostrarExpediente(false);
        view.setMostrarImagenExpediente(false);
        view.setMostrarAdjuntoDocumentoHistorial(false);
        view.setMostrarPuesto(false);
        view.setMostrarBitacora(false);
    }

    public void verMenuDetalle(Integer idEmpleadoSeleccionado) {
        try {
            view.setIdEmpleado(idEmpleadoSeleccionado);
            view.setMostrarMenuDetalles(true);
            view.setMostrarDatosGenerales(true);
            view.setMostrarResultadoConsulta(false);

            // this.view.setVisualizarBotonDependientes(false);
            DatosGeneralesDTO datosGeneralesDTO = empleado
                    .obtenerDatosGenerales(view.getIdEmpleado());
            view.setDatoGeneral(datosGeneralesDTO);

            if (view.getDatoGeneral().getTienePersonasDependientes() != null
                    || view.getDatoGeneral().getTienePersonasDependientes()) {
                // this.view.setVisualizarBotonDependientes(true);
            }
            if (expedienteEmpleado
                    .tieneExpedienteAperturado(view.getIdEmpleado())) {
                String numeroExpediente = expedienteEmpleado
                        .numeroExpedienteEmpleado(view.getIdEmpleado());
                Integer idExpediente = expedienteEmpleado
                        .obtenerIdExpedienteEmpleado(view.getIdEmpleado());
                view.setImagenExpediente("expediente_aperturado.png");
                view.setMostrarActualizacionExpediente(true);
                view.setNumeroExpediente(numeroExpediente);
                view.setIdExpediente(idExpediente);
            } else {
                view.setImagenExpediente("sin_expediente.png");
                view.setMostrarAperturaExpediente(true);
            }

        } catch (ReglaNegocioException exception) {

            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void visualizarAdjuntoDocumentoHistorial(
            HistorialAcademicoDTO historialAcademicoDTO) {
        try {
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
        }
    }

    public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {

        try {
            byte[] bytes = adjuntoEmpleado
                    .obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

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
        view.setIdImagenExpediente(idImagenExpediente);
        view.setMostrarImagenExpediente(true);
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
