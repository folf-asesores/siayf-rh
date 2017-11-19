/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

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

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 13:29:49 13/09/2016
 */
@ManagedBean(name = "impresionNombramiento")
@ViewScoped
public class ImpresionNombramientoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8820506125977536557L;

    @Inject
    private Catalogo catalogo;
    @Inject
    private Nombramiento nombramiento;
    @Inject
    private PuestosAutorizadosEmpleados puestosEmpleados;

    private ImpresionNombramientoView view;

    @PostConstruct
    public void init() {
        view = new ImpresionNombramientoView();

        view.setItemsTipoNombramiento(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerTipoNombramiento()));

        obtenerListaInfoNombramiento();
    }

    public void obtenerListaInfoNombramiento() {
        try {

            List<InfoNombramientoDTO> listaNombramiento = nombramiento
                    .obtenerListaInfoNombramiento();

            if (!listaNombramiento.isEmpty()) {
                view.setListaNombramiento(listaNombramiento);
            } else {
                view.setListaNombramiento(new ArrayList<InfoNombramientoDTO>());
            }

        } catch (ValidacionException validacionException) {
            throw new ValidacionException(validacionException.getMessage(),
                    null);
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(),
                    reglaNegocioException.getCodigoError());
        }
    }

    public void obtenerListaInfoNombramientoPoTipo() {
        try {

            List<InfoNombramientoDTO> listaNombramiento = nombramiento
                    .obtenerListaInfoNombramientoPorTipo(
                            view.getTipoNombramiento());

            if (!listaNombramiento.isEmpty()) {
                view.setListaNombramiento(listaNombramiento);
            } else {
                view.setListaNombramiento(new ArrayList<InfoNombramientoDTO>());
            }

        } catch (ValidacionException validacionException) {
            throw new ValidacionException(validacionException.getMessage(),
                    null);
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(),
                    reglaNegocioException.getCodigoError());
        }
    }

    public void mostrarDetalleNombramiento(Integer idNombramiento,
            String tipoNombramiento) {
        try {

            String nombreTipoNombramiento = "NOMBRAMIENTO " + tipoNombramiento;

            view.setNombreTipoNombramiento(nombreTipoNombramiento);

            Integer idInventario = nombramiento
                    .obtenerInventarioVacantePorIdNombramiento(idNombramiento);

            view.setPuestoEmpleadoDTO(
                    puestosEmpleados.obtenerInformacionPuesto(idInventario));

            view.setInfoLugarAdscripcionNombramientoDTO(
                    nombramiento.obtenerInfoLugarAdscripcion(
                            view.getPuestoEmpleadoDTO().getIdAdscripcion(),
                            view.getPuestoEmpleadoDTO().getIdSubadscripcion(),
                            view.getPuestoEmpleadoDTO().getIdServicio()));

            view.setIdNombramiento(idNombramiento);

            administarcioVista(view.getNombreTipoNombramiento());

            view.getItemsTiposAdscripcion().put(
                    EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE + ": "
                            + view.getPuestoEmpleadoDTO()
                                    .getUnidadResponsable(),
                    EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE);
            view.getItemsTiposAdscripcion().put(
                    EnumTipoAdscripcionNombramiento.ADSCRIPCION + ": "
                            + view.getInfoLugarAdscripcionNombramientoDTO()
                                    .getAdscripcion(),
                    EnumTipoAdscripcionNombramiento.ADSCRIPCION);
            view.getItemsTiposAdscripcion().put(
                    EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION + ": "
                            + view.getInfoLugarAdscripcionNombramientoDTO()
                                    .getAreaAdscripcion(),
                    EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION);
            view.getItemsTiposAdscripcion().put(
                    EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION + ": "
                            + view.getInfoLugarAdscripcionNombramientoDTO()
                                    .getLugarAdscripcion(),
                    EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION);

        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());

        }
    }

    public static String ucFirst(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }

    public void descargarNombramiento() {
        try {

            // String nombreReporte =
            // nombreReporte(this.view.getNombreTipoNombramiento());

            view.getClasificacionReporteDTO()
                    .setClasificacionReporte(view.getNombreTipoNombramiento());
            view.getClasificacionReporteDTO()
                    .setHorarioTrabajo("El que le asigne su Unidad");
            view.getClasificacionReporteDTO()
                    .setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

            Integer idClasificacion = nombramiento
                    .actualizarEstructuraNombramiento(
                            view.getClasificacionReporteDTO());

            actualizarNombramientoImpreso(view.getTipoAdscripcion());

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "nombramiento-generico", "TIPO_REPORTE", "docx",
                    "ID_NOMBRAMIENTO", String.valueOf(view.getIdNombramiento()),
                    "ID_CLASIFICACION", String.valueOf(idClasificacion) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            view.setBytes(admintradorReportes.obtenerReporte(referencia));

            if (view.getBytes() != null) {
                JSFUtils.descargarArchivo(view.getBytes(),
                        CadenaUtil.converterSpace(
                                view.getNombreTipoNombramiento()),
                        TipoArchivo.getMIMEType("docx"));

            }

            JSFUtils.infoMessage("Descargar Nombramiento: ",
                    "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {
            System.err.println(validacionException.getMessage());
            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    public void descargarNombramientoFormalizado() {
        try {

            // String nombreReporte =
            // nombreReporte(this.view.getNombreTipoNombramiento());

            view.getClasificacionReporteDTO()
                    .setClasificacionReporte(view.getNombreTipoNombramiento());
            view.getClasificacionReporteDTO()
                    .setHorarioTrabajo("El que le asigne su Unidad");
            view.getClasificacionReporteDTO()
                    .setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

            Integer idClasificacion = nombramiento
                    .actualizarEstructuraNombramiento(
                            view.getClasificacionReporteDTO());

            actualizarNombramientoImpreso(view.getTipoAdscripcion());

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "nombramiento-formalizado-fase", "TIPO_REPORTE", "docx",
                    "ID_NOMBRAMIENTO", String.valueOf(view.getIdNombramiento()),
                    "ID_CLASIFICACION", String.valueOf(idClasificacion) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            view.setBytes(admintradorReportes.obtenerReporte(referencia));

            if (view.getBytes() != null) {
                JSFUtils.descargarArchivo(view.getBytes(),
                        CadenaUtil.converterSpace(
                                view.getNombreTipoNombramiento()),
                        TipoArchivo.getMIMEType("docx"));

            }

            JSFUtils.infoMessage("Descargar Nombramiento: ",
                    "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {
            System.err.println(validacionException.getMessage());
            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    public void descargarNombramientoInterinato() {
        try {

            // String nombreReporte =
            // nombreReporte(this.view.getNombreTipoNombramiento());

            view.getClasificacionReporteDTO()
                    .setClasificacionReporte(view.getNombreTipoNombramiento());
            view.getClasificacionReporteDTO()
                    .setHorarioTrabajo("El que le asigne su Unidad");
            view.getClasificacionReporteDTO()
                    .setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

            Integer idClasificacion = nombramiento
                    .actualizarEstructuraNombramiento(
                            view.getClasificacionReporteDTO());

            actualizarNombramientoImpreso(view.getTipoAdscripcion());

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "nombramiento-interino", "TIPO_REPORTE", "docx",
                    "ID_NOMBRAMIENTO", String.valueOf(view.getIdNombramiento()),
                    "ID_CLASIFICACION", String.valueOf(idClasificacion) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            view.setBytes(admintradorReportes.obtenerReporte(referencia));

            if (view.getBytes() != null) {
                JSFUtils.descargarArchivo(view.getBytes(),
                        CadenaUtil.converterSpace(
                                view.getNombreTipoNombramiento()),
                        TipoArchivo.getMIMEType("docx"));

            }

            JSFUtils.infoMessage("Descargar Nombramiento: ",
                    "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {
            System.err.println(validacionException.getMessage());
            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    public void administarcioVista(String nombreClasificaiconNombramiento) {

        view.setMostrarConfirmacionImpresion(false);
        view.setMostrarFormalizado(false);
        view.setMostrarInterinato(false);
        view.setMostrarPrincipal(false);
        view.setMostrarOpcionDescarga(true);

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROVISIONAL)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoProvisional);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosProvisional);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROMOCION)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoPromocion);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosPromocion);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROFESIONALIZACION)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoProfesionalizacion);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosProfesionalizacion);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.ESCALAFON)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoEscalafon);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosEscalafon);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.DEFINITIVO)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoDefinitivo);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosDefinitivo);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.CONFIANZA)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoConfianza);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosConfianza);

            view.setMostrarConfirmacionImpresion(true);
        }

        if (nombreClasificaiconNombramiento.equals(
                EnumClasificacionNombramiento.DEFINITIVO_FORMALIZADO_FASE_II)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoFormalizadoDos);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosFormalizadoDos);

            view.setMostrarFormalizado(true);
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.INTERINATO)) {

            view.getClasificacionReporteDTO().setTextoUno(
                    EnumPlantillaNombramiento.posicionUnoInterinato);
            view.getClasificacionReporteDTO().setTextoDos(
                    EnumPlantillaNombramiento.posicionDosInterinato);

            view.setMostrarInterinato(true);
        }

    }

    public String nombreReporte(String nombreClasificaiconNombramiento) {
        String nombreReporte = "";
        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROVISIONAL)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROMOCION)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.PROFESIONALIZACION)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.ESCALAFON)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.DEFINITIVO)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.CONFIANZA)) {
            nombreReporte = "nombramiento-generico";
        }

        if (nombreClasificaiconNombramiento.equals(
                EnumClasificacionNombramiento.DEFINITIVO_FORMALIZADO_FASE_II)) {
            nombreReporte = "nombramiento-formalizado-fase";
        }

        if (nombreClasificaiconNombramiento
                .equals(EnumClasificacionNombramiento.INTERINATO)) {
            nombreReporte = "nombramiento-interinato";
        }

        return nombreReporte;
    }

    public void actualizarNombramientoImpreso(String tipoAdscripcion) {
        if (tipoAdscripcion
                .equals(EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE)) {
            nombramiento.actualizarNombramientoImpreso(view.getIdNombramiento(),
                    view.getPuestoEmpleadoDTO().getUnidadResponsable(),
                    view.isImprimirNombramiento());
        }

        if (tipoAdscripcion
                .equals(EnumTipoAdscripcionNombramiento.ADSCRIPCION)) {
            nombramiento
                    .actualizarNombramientoImpreso(view.getIdNombramiento(),
                            view.getInfoLugarAdscripcionNombramientoDTO()
                                    .getAdscripcion(),
                            view.isImprimirNombramiento());
        }

        if (tipoAdscripcion
                .equals(EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION)) {
            nombramiento.actualizarNombramientoImpreso(view.getIdNombramiento(),
                    view.getInfoLugarAdscripcionNombramientoDTO()
                            .getAreaAdscripcion(),
                    view.isImprimirNombramiento());
        }

        if (tipoAdscripcion
                .equals(EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION)) {
            nombramiento.actualizarNombramientoImpreso(view.getIdNombramiento(),
                    view.getInfoLugarAdscripcionNombramientoDTO()
                            .getLugarAdscripcion(),
                    view.isImprimirNombramiento());
        }
    }

    public void validatorTipoAdscripcion(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();

        switch (nombreComponete) {

            case "adscripcion":
                String adscripcion = (String) value;

                if (ValidacionUtil.esCadenaVacia(adscripcion)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una opción valida.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);

                } else if (!ValidacionUtil.esCadenaVacia(adscripcion)) {
                    if (adscripcion.equals(
                            EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE)) {
                        if (ValidacionUtil
                                .esCadenaVacia(view.getPuestoEmpleadoDTO()
                                        .getUnidadResponsable())) {
                            FacesMessage facesMessage = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El empleado no tiene una unidad responsable registrado.");
                            context.addMessage(component.getClientId(),
                                    facesMessage);
                            throw new ValidatorException(facesMessage);
                        }
                    }

                    if (adscripcion.equals(
                            EnumTipoAdscripcionNombramiento.ADSCRIPCION)) {
                        if (ValidacionUtil.esCadenaVacia(
                                view.getInfoLugarAdscripcionNombramientoDTO()
                                        .getAdscripcion())) {
                            FacesMessage facesMessage = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El empleado no tiene una adscripción registrado.");
                            context.addMessage(component.getClientId(),
                                    facesMessage);
                            throw new ValidatorException(facesMessage);
                        }
                    }

                    if (adscripcion.equals(
                            EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION)) {
                        if (ValidacionUtil.esCadenaVacia(
                                view.getInfoLugarAdscripcionNombramientoDTO()
                                        .getAreaAdscripcion())) {
                            FacesMessage facesMessage = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El empleado no tiene un area adscripción registrado.");
                            context.addMessage(component.getClientId(),
                                    facesMessage);
                            throw new ValidatorException(facesMessage);
                        }
                    }

                    if (adscripcion.equals(
                            EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION)) {
                        if (ValidacionUtil.esCadenaVacia(
                                view.getInfoLugarAdscripcionNombramientoDTO()
                                        .getLugarAdscripcion())) {
                            FacesMessage facesMessage = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El empleado no tiene un lugar adscripción registrado.");
                            context.addMessage(component.getClientId(),
                                    facesMessage);
                            throw new ValidatorException(facesMessage);
                        }
                    }
                }

                break;
            default:
                JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
                break;
        }

    }

    public void validatorTipoNombramiento(FacesContext context,
            UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();

        switch (nombreComponete) {

            case "tipoNombramiento":
                Integer tipoNombramiento = (Integer) value;

                obtenerListaInfoNombramiento();

                if (!ValidacionUtil.esNumeroPositivo(tipoNombramiento)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un tipo nombramiento.");
                    context.addMessage(component.getClientId(), facesMessage1);

                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
                break;
        }

    }

    public void regresarModulo() {
        try {
            JSFUtils.redireccionar(
                    "/siayf-rh/contenido/empleado/impresionNombramiento.xhtml?faces-redirect=true");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * @return the view
     */
    public ImpresionNombramientoView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(ImpresionNombramientoView view) {
        this.view = view;
    }

}
