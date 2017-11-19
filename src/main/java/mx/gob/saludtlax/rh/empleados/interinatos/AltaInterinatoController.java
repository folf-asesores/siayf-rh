/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.BolsaTrabajo;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.empleados.movimientos.MovimientosEmpleados;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/09/2016 14:25:35
 *
 */
@ManagedBean(name = "interinato")
@ViewScoped
public class AltaInterinatoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 43927896632213917L;

    @Inject
    private BolsaTrabajo bolsaTrabajo;
    @Inject
    private ConfiguracionPresupuestal datosLaborales;
    @Inject
    private Empleado empleado;
    @Inject
    private Interinatos interinatos;
    @Inject
    private MovimientosEmpleados movimientosEmpleados;

    private AltaInterinatoView view = new AltaInterinatoView();

    @PostConstruct
    public void inicio() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setListaTipoCandidato(SelectItemsUtil.listaTipoCandidato());
        view.setTiposBusqueda(SelectItemsUtil.listaTiposBusquedaInterinato());
        view.setMostrarBusqueda(true);
        view.setIdUsuario(usuario.getIdUsuario());

    }

    public void consultarCandidatosInterinato() {
        view.setPuestosDisponibles(interinatos
                .consultarCandidatosInterinato(view.getTipoBusqueda()));
        if (view.getPuestosDisponibles().isEmpty()) {
            JSFUtils.warningMessage("",
                    "No se encontraron puestos disponibles para interinato.");
        }
    }

    public void ocultarRegistro() {
        view.setMostrarRegistro(false);
        view.setMostrarBusqueda(true);
    }

    public void seleccionarPuesto(DisponiblesInterinatoDTO dto) {
        view.getRegistro().setIdPuesto(dto.getIdPuesto());
        view.setPuestoSeleccionado(dto);
        view.setDetalle(
                datosLaborales.obtenerDatosLaboralesId(dto.getIdPuesto()));
        view.setDetalleMovimiento(movimientosEmpleados
                .obtenerDetalleMovimiento(dto.getIdMovimiento()));

        view.setMostrarBusqueda(false);
        view.setMostrarRegistro(true);
        view.setMostrarConsultaAspirante(true);
        view.getRegistro().setTipoCandidato(EnumTipoCandidato.ASPIRANTE);
    }

    public void consultarCandidato() {

        view.setMostrarConsultaAspirante(false);
        view.setMostrarConsultaEmpleado(false);
        if (!ValidacionUtil
                .esNumeroPositivo(view.getRegistro().getTipoCandidato())) {
            JSFUtils.warningMessage("",
                    "Es requerido seleccionar el tipo de candidato para realizar la búsqueda.");
        } else {
            if (view.getRegistro()
                    .getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {

                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(
                        EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
                view.setAspirantes(
                        bolsaTrabajo.consultarPorCriterio(filtroDTO));
                view.setMostrarConsultaAspirante(true);
                if (view.getAspirantes().isEmpty()) {
                    JSFUtils.warningMessage("",
                            "No se encontró registro con el criterio"
                                    + view.getCriterioBusqueda());
                }

            } else if (view.getRegistro()
                    .getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {

                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(
                        EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
                view.setEmpleados(empleado.consultaEmpleado(filtroDTO));
                view.setMostrarConsultaEmpleado(true);

                if (view.getEmpleados().isEmpty()) {
                    JSFUtils.warningMessage("",
                            "No se encontró registro con el criterio "
                                    + view.getCriterioBusqueda());
                }

            }
        }
    }

    public void seleccionarEmpleado(Integer idEmpleado) {
        view.setEmpleado(empleado.obtenerInformacionEmpleado(idEmpleado));
        view.getRegistro().setIdContexto(idEmpleado);
        view.getRegistro().setTipoCandidato(EnumTipoCandidato.EMPLEADO);
        if (view.getEmpleado().getEstatus()
                .equals(EnumEstatusEmpleado.ACTIVO)) {
            view.setMostrarDetallePuestoActivo(true);
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idEmpleado", idEmpleado);
        view.setConsulta("consultaEmpleado.xhtml");
        view.setMostrarConfirmacionEmpleado(true);

    }

    public void seleccionarAspirante(Integer idAspirante) {
        view.setAspirante(
                bolsaTrabajo.obtenerDetalleAspirantePorId(idAspirante));
        view.getRegistro().setIdContexto(idAspirante);
        view.getRegistro().setTipoCandidato(EnumTipoCandidato.ASPIRANTE);
        view.setMostrarConfirmacionAspirante(true);
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idAspirante", idAspirante);
        view.setConsulta("consultaAspirante.xhtml");
    }

    public void solicitarInterinato() {
        try {
            view.getRegistro().setIdUsuario(view.getIdUsuario());
            interinatos.solicitarInterinato(view.getRegistro());
            view.setMostrarConfirmacionAspirante(false);
            view.setMostrarConfirmacionEmpleado(false);
            view.setMostrarRegistro(false);
            view.setMostrarBusqueda(true);
            view.getPuestosDisponibles().clear();

        } catch (ReglaNegocioException exception) {
            if (view.getRegistro()
                    .getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
                JSFUtils.errorMessageEspecifico("errorEmpleado", "",
                        exception.getMessage());
            } else {
                JSFUtils.errorMessageEspecifico("errorAspirante", "",
                        exception.getMessage());
            }

        }
    }

    public void ocultarAspirante() {
        view.setMostrarConfirmacionAspirante(false);
        view.setMostrarConsultaAspirante(true);
    }

    public void ocultarEmpleado() {
        view.setMostrarConfirmacionEmpleado(false);
        view.setMostrarConsultaEmpleado(true);
    }

    public AltaInterinatoView getView() {
        return view;
    }

    public void setView(AltaInterinatoView view) {
        this.view = view;
    }

}
