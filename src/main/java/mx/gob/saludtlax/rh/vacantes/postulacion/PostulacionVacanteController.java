/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

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
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-06
 *
 */
@ManagedBean(name = "postulacionVacante")
@ViewScoped
public class PostulacionVacanteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -510049843933785548L;

    @Inject
    private Catalogo catalogo;
    @Inject
    private Postulacion postulacion;
    @Inject
    private PuestosAutorizadosEmpleados puestosAutorizadosEmpleados;
    @Inject
    private Empleado empleado;
    @Inject
    private BolsaTrabajo bolsaTrabajo;
    @Inject
    private PostulacionVacanteView view;

    @PostConstruct
    public void inicio() {

        view = new PostulacionVacanteView();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.getPostulacion().setIdUsuario(usuario.getIdUsuario());
        view.setListaTipoCandidato(SelectItemsUtil.listaTipoCandidato());
        view.setMostrarBusqueda(true);
        view.setPuestosDisponibles(postulacion.consultarPuestosDisponibles());
        view.setListaPuestos(SelectItemsUtil.listaCatalogos(catalogo.listaPuestos()));

    }

    public void seleccionarVacante(Integer idVacante) {

        try {
            view.setMostrarCapturaPuesto(false);
            view.setMostrarDetallePuesto(false);
            view.setPuesto(puestosAutorizadosEmpleados.obtenerInformacionPuesto(idVacante));
            view.setMostrarBusqueda(false);
            view.setMostrarPostulacion(true);
            view.setIdPostulacion(postulacion.obtenerIdPostulacionActiva(idVacante));
            view.getPostulacion().setIdVacante(idVacante);
            view.setTipoCandidato(EnumTipoCandidato.ASPIRANTE);

            if (view.getIdPostulacion() != null) {
                view.setCandidatosPostulados(postulacion.consultarCandidatosPostulacion(view.getIdPostulacion()));
            }
            if (view.getPuesto().getPuesto().equals("SIN PUESTO")) {
                view.setMostrarCapturaPuesto(true);
            } else {
                view.setMostrarDetallePuesto(true);
            }

        } catch (ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void seleccionarTipoCandidato() {

        view.setMostrarConsultaAspirante(false);
        view.setMostrarConsultaEmpleado(false);
        view.setMostrarDetalleAspirante(false);
        view.setMostrarDetalleEmpleado(false);
        view.setCriterioBusqueda("");
        if (view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
            view.setMostrarConsultaAspirante(true);
        } else if (view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            view.setMostrarConsultaEmpleado(true);
        }

    }

    public void consultarCandidato() {

        view.setMostrarConsultaAspirante(false);
        view.setMostrarConsultaEmpleado(false);
        view.setMostrarDetalleAspirante(false);
        view.setMostrarDetalleEmpleado(false);
        if (view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {

            try {
                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
                view.setAspirantes(bolsaTrabajo.consultarPorCriterio(filtroDTO));
                view.setMostrarConsultaAspirante(true);
            } catch (ValidacionException exception) {
                JSFUtils.errorMessage("", exception.getMessage());
            }
        } else if (view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            try {
                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);

                view.setEmpleados(empleado.consultaEmpleado(filtroDTO));
                view.setMostrarConsultaEmpleado(true);

            } catch (ValidacionException exception) {
                JSFUtils.errorMessage("", exception.getMessage());
            }
        }

    }

    public void seleccionarEmpleado(Integer idEmpleado) {

        view.setEmpleado(empleado.obtenerInformacionEmpleado(idEmpleado));
        view.setMostrarDetalleEmpleado(true);
        view.setMostrarConsultaEmpleado(false);
        view.getPostulacion().setIdEmpleado(idEmpleado);
        view.setMostrarDetalle(true);
        if (view.getEmpleado().getEstatus().equals(EnumEstatusEmpleado.ACTIVO)) {
            view.setMostrarDetallePuestoActivo(true);
        }

    }

    public void seleccionarAspirante(Integer idAspirante) {
        try {
            view.setAspirante(bolsaTrabajo.obtenerDetalleAspirantePorId(idAspirante));
            view.setMostrarConsultaAspirante(false);
            view.setMostrarDetalleAspirante(true);
            view.getPostulacion().setIdAspirante(idAspirante);
            view.setMostrarDetalle(true);
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void postularCandidato() {
        try {

            Integer idPostulacion = postulacion.postularCandidato(view.getPostulacion());
            view.getCandidatosPostulados().clear();
            view.setCandidatosPostulados(postulacion.consultarCandidatosPostulacion(idPostulacion));
            view.setMostrarDetalle(false);

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("errorPostulacion", "", exception.getMessage());
        }

    }

    public void ocultarDetalle() {
        view.setMostrarDetalle(false);
    }

    public PostulacionVacanteView getView() {
        return view;
    }

    public void setView(PostulacionVacanteView view) {
        this.view = view;
    }

}
