
package mx.gob.saludtlax.rh.empleado.issste;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "movimientosIssste")
@ViewScoped
public class AdministracionMovimientoIsssteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -329222968449414674L;
    /**
     *
     */

    @Inject
    private Catalogo catalogo;
    @Inject
    private Empleado empleado;
    @Inject
    private Issste issste;

    private AdministracionMovimientsIsssteView view;

    @PostConstruct
    public void inicio() {
        view = new AdministracionMovimientsIsssteView();

        view.setListaCausaBaja(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaCausaBajaIssste()));
        view.setListaNombramiento(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaTipoNombramiento()));
        view.setListaNivelSalario(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaNivelSalarial()));
    }

    public void consultarEmpleados() {
        try {

            view.setListaEmpleados(empleado
                    .empleadosPorCriterioConNombramiento(view.getCriterio()));

            if (view.getListaEmpleados().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "",
                        "No se encontrarón registros en el criterio"
                                + view.getCriterio());
            }
            view.setVentanaNuevoReporte(false);
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void consultarMovimiento() {
        try {
            view.setListaMovimiento(issste
                    .obtenerListaMovimientoPorCriterio(view.getCriterio()));

            if (view.getListaMovimiento().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "",
                        "No se encontrarón registros en el criterio"
                                + view.getCriterio());
            }

            view.setVentanaNuevoReporte(false);
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void seleccionarEmpleado(InfoEmpleadoDTO empleadoSeleccionar) {

        if (issste.existeEmpleado(empleadoSeleccionar.getIdEmpleado())) {
            view.setVentanaNuevoReporte(false);
            JSFUtils.errorMessage("Seleccionar Empleado: ",
                    "El empleado ya se encuentra registrado");
        } else {
            view.setIdEmpleado(empleadoSeleccionar.getIdEmpleado());
            view.setEmpleadoSeleccionado(empleadoSeleccionar);
            view.setFormularioAltaTrabajador(true);
            view.getAltaTrabajadorDTO().setSueldoIssste(
                    empleadoSeleccionar.getSueldoActualEmpleado());
            view.setVentanaNuevoReporte(false);
        }
    }

    public void verFormatoAltas() throws IOException {
        try {
            view.setAdministracionMovimientos(false);
            view.setAltaTrabajador(false);
            view.setBajaIssste(true);
            view.setSueldoTrabajador(true);

        } catch (NullPointerException | IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void altaTrabajador() {
        try {

            MovimientoIsssteEmpleadoDTO dto = view.getAltaTrabajadorDTO();

            dto.setIdEmpleado(view.getIdEmpleado());
            dto.setIdUsuario(obtenerIdUsuarioSession());

            Integer idMovimiento = issste.altaIsssteMovimiento(dto);

            if (ValidacionUtil.esNumeroPositivoInt(idMovimiento)) {

                view.setUrlReporte("AdministracionMovimientoIsssteServlet?"
                        + "idMovimientoIssste=" + idMovimiento
                        + "&idAccionMovimientoIssste="
                        + EnumAccionMovimientoIssste.VISUALIZAR
                        + "&idTipoMovimientoIssste="
                        + EnumTipoMovimientoIssste.ALTA_TRABAJADOR);

                view.setVentanaNuevoReporte(true);

                JSFUtils.infoMessage("Alta Trabajador: ",
                        "¡Se registro correctamente!");
                mostrarPrincipal();

            }

        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void modificarSueldo() {
        try {

            issste.modificacionIsssteMovimiento(
                    view.getModificacionTrabajadorDTO());

            view.setUrlReporte("AdministracionMovimientoIsssteServlet?"
                    + "idMovimientoIssste="
                    + view.getModificacionTrabajadorDTO()
                            .getIdMovimientoIsssteEmpleado()
                    + "&idAccionMovimientoIssste="
                    + EnumAccionMovimientoIssste.VISUALIZAR
                    + "&idTipoMovimientoIssste="
                    + EnumTipoMovimientoIssste.MODIFICACIÓN_SUELDO);

            view.setVentanaNuevoReporte(true);

            JSFUtils.infoMessage("Modificación Sueldo Trabajador: ",
                    "¡Se modifico correctamente!");
            mostrarPrincipal();

        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void bajaTrabajador() {
        try {
            issste.bajaIsssteMovimiento(view.getBajaTrabajadorDTO());

            view.setUrlReporte("AdministracionMovimientoIsssteServlet?"
                    + "idMovimientoIssste="
                    + view.getBajaTrabajadorDTO()
                            .getIdMovimientoIsssteEmpleado()
                    + "&idAccionMovimientoIssste="
                    + EnumAccionMovimientoIssste.VISUALIZAR
                    + "&idTipoMovimientoIssste="
                    + EnumTipoMovimientoIssste.BAJA_ISSSTE);
            view.setVentanaNuevoReporte(true);

            JSFUtils.infoMessage("Baja Trabajador: ",
                    "¡Se dio de baja correctamente!");
            mostrarPrincipal();
        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void descargarMovimientoIssste(
            InfoMovimientoIsssteDTO infoMovimientoIsssteDTO) {

        view.setUrlReporte(
                "AdministracionMovimientoIsssteServlet?" + "idMovimientoIssste="
                        + infoMovimientoIsssteDTO
                                .getIdMovimientoIsssteEmpleado()
                        + "&idAccionMovimientoIssste="
                        + EnumAccionMovimientoIssste.DESCARGAR
                        + "&idTipoMovimientoIssste="
                        + infoMovimientoIsssteDTO.getIdTipoMovimientoIssste());
        view.setVentanaNuevoReporte(true);

    }

    public void mostrarPanelAlta() {
        view.setCriterio("");
        view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
        view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
        view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
        view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
        view.setAdministracionMovimientos(false);
        view.setAltaTrabajador(true);
        view.setFormularioAltaTrabajador(false);
        view.setBajaIssste(false);
        view.setSueldoTrabajador(false);
        view.setVentanaNuevoReporte(false);
    }

    public void mostrarPrincipal() {
        view.setCriterio("");
        view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
        view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
        view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
        view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
        view.setListaMovimiento(new ArrayList<InfoMovimientoIsssteDTO>());
        view.setAdministracionMovimientos(true);
        view.setAltaTrabajador(false);
        view.setFormularioAltaTrabajador(false);
        view.setBajaIssste(false);
        view.setSueldoTrabajador(false);
        view.setOpcionDisponibles(true);
        //		this.view.setVentanaNuevoReporte(false);
    }

    public void mostrarPanelModificacion() {

        view.getModificacionTrabajadorDTO().setIdMovimientoIsssteEmpleado(view
                .getMovimientoSeleccinado().getIdMovimientoIsssteEmpleado());
        view.getModificacionTrabajadorDTO().setSueldoIssste(
                view.getMovimientoSeleccinado().getSueldoIssste());
        view.getModificacionTrabajadorDTO()
                .setSueldoSar(view.getMovimientoSeleccinado().getSueldoSar());
        view.getModificacionTrabajadorDTO().setTotalRemuneracion(
                view.getMovimientoSeleccinado().getTotalRemuneracion());

        view.setCriterio("");
        view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
        view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
        view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
        view.setAdministracionMovimientos(false);
        view.setAltaTrabajador(false);
        view.setFormularioAltaTrabajador(false);
        view.setBajaIssste(false);
        view.setSueldoTrabajador(true);
        view.setVentanaNuevoReporte(false);

    }

    public void mostrarPanelBaja() {

        view.getBajaTrabajadorDTO().setIdMovimientoIsssteEmpleado(view
                .getMovimientoSeleccinado().getIdMovimientoIsssteEmpleado());
        view.getBajaTrabajadorDTO().setSueldoIssste(
                view.getMovimientoSeleccinado().getSueldoIssste());
        view.getBajaTrabajadorDTO()
                .setSueldoSar(view.getMovimientoSeleccinado().getSueldoSar());
        view.getBajaTrabajadorDTO().setTotalRemuneracion(
                view.getMovimientoSeleccinado().getTotalRemuneracion());
        view.getBajaTrabajadorDTO().setIdCausaBaja(
                view.getMovimientoSeleccinado().getIdCausaBaja());

        view.setCriterio("");
        view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
        view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
        view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
        view.setAdministracionMovimientos(false);
        view.setAltaTrabajador(false);
        view.setFormularioAltaTrabajador(false);
        view.setBajaIssste(true);
        view.setSueldoTrabajador(false);
        view.setVentanaNuevoReporte(false);
    }

    public void cerrarFormularioAltaTrabajador() {
        view.setEmpleadoSeleccionado(new InfoEmpleadoDTO());
        view.setAltaTrabajadorDTO(new MovimientoIsssteEmpleadoDTO());
        view.setMovimientoSeleccinado(new InfoMovimientoIsssteDTO());
        view.setAdministracionMovimientos(false);
        view.setAltaTrabajador(true);
        view.setFormularioAltaTrabajador(false);
        view.setBajaIssste(false);
        view.setSueldoTrabajador(false);
        view.setVentanaNuevoReporte(false);
    }

    public void onRowSelect(SelectEvent event) {
        view.setOpcionDisponibles(false);
        view.setVentanaNuevoReporte(false);
    }

    public void onRowUnselect(UnselectEvent event) {
        view.setOpcionDisponibles(true);
        view.setVentanaNuevoReporte(false);
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

            case "criterios":
                String criterios = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterios)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterios.trim().length() < 5) {
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
                JSFUtils.errorMessage("Error: ", "Validar criterio");
                break;
        }
    }

    public Integer obtenerIdUsuarioSession() {

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

        return usuario.getIdUsuario();
    }

    public void regresar() {
        try {
            JSFUtils.redireccionar(
                    "/siayf-rh/contenido/reportesLaborales/administracionMovimientoIssste.xhtml?faces-redirect=true");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public AdministracionMovimientsIsssteView getView() {
        return view;
    }

    public void setView(AdministracionMovimientsIsssteView view) {
        this.view = view;
    }
}
