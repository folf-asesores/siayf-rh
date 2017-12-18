/**
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

        this.view = new PostulacionVacanteView();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        this.view.getPostulacion().setIdUsuario(usuario.getIdUsuario());
        this.view.setListaTipoCandidato(SelectItemsUtil.listaTipoCandidato());
        this.view.setMostrarBusqueda(true);
        this.view.setPuestosDisponibles(this.postulacion.consultarPuestosDisponibles());
        this.view.setListaPuestos(SelectItemsUtil.listaCatalogos(this.catalogo.listaPuestos()));

    }

    public void seleccionarVacante(Integer idVacante) {
        System.out.println("id??" + idVacante);
        try {
            this.view.setMostrarCapturaPuesto(false);
            this.view.setMostrarDetallePuesto(false);
            this.view.setPuesto(this.puestosAutorizadosEmpleados.obtenerInformacionPuesto(idVacante));
            this.view.setMostrarBusqueda(false);
            this.view.setMostrarPostulacion(true);
            this.view.setIdPostulacion(this.postulacion.obtenerIdPostulacionActiva(idVacante));
            this.view.getPostulacion().setIdVacante(idVacante);
            this.view.setTipoCandidato(EnumTipoCandidato.ASPIRANTE);

            if (this.view.getIdPostulacion() != null) {
                this.view.setCandidatosPostulados(this.postulacion.consultarCandidatosPostulacion(this.view.getIdPostulacion()));
            }
            if (this.view.getPuesto().getPuesto().equals("SIN PUESTO")) {
                this.view.setMostrarCapturaPuesto(true);
            } else {
                this.view.setMostrarDetallePuesto(true);
            }

        } catch (ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void seleccionarTipoCandidato() {

        this.view.setMostrarConsultaAspirante(false);
        this.view.setMostrarConsultaEmpleado(false);
        this.view.setMostrarDetalleAspirante(false);
        this.view.setMostrarDetalleEmpleado(false);
        this.view.setCriterioBusqueda("");
        if (this.view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
            this.view.setMostrarConsultaAspirante(true);
        } else if (this.view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            this.view.setMostrarConsultaEmpleado(true);
        }

    }

    public void consultarCandidato() {

        this.view.setMostrarConsultaAspirante(false);
        this.view.setMostrarConsultaEmpleado(false);
        this.view.setMostrarDetalleAspirante(false);
        this.view.setMostrarDetalleEmpleado(false);
        if (this.view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {

            try {
                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(this.view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
                this.view.setAspirantes(this.bolsaTrabajo.consultarPorCriterio(filtroDTO));
                this.view.setMostrarConsultaAspirante(true);
            } catch (ValidacionException exception) {
                JSFUtils.errorMessage("", exception.getMessage());
            }
        } else if (this.view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            try {
                FiltroDTO filtroDTO = new FiltroDTO();
                filtroDTO.setCriterio(this.view.getCriterioBusqueda());
                filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);

                this.view.setEmpleados(this.empleado.consultaEmpleado(filtroDTO));
                this.view.setMostrarConsultaEmpleado(true);

            } catch (ValidacionException exception) {
                JSFUtils.errorMessage("", exception.getMessage());
            }
        }

    }

    public void seleccionarEmpleado(Integer idEmpleado) {

        this.view.setEmpleado(this.empleado.obtenerInformacionEmpleado(idEmpleado));
        this.view.setMostrarDetalleEmpleado(true);
        this.view.setMostrarConsultaEmpleado(false);
        this.view.getPostulacion().setIdEmpleado(idEmpleado);
        this.view.setMostrarDetalle(true);
        if (this.view.getEmpleado().getEstatus().equals(EnumEstatusEmpleado.ACTIVO)) {
            this.view.setMostrarDetallePuestoActivo(true);
        }

    }

    public void seleccionarAspirante(Integer idAspirante) {
        try {
            this.view.setAspirante(this.bolsaTrabajo.obtenerDetalleAspirantePorId(idAspirante));
            this.view.setMostrarConsultaAspirante(false);
            this.view.setMostrarDetalleAspirante(true);
            this.view.getPostulacion().setIdAspirante(idAspirante);
            this.view.setMostrarDetalle(true);
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void postularCandidato() {
        try {

            Integer idPostulacion = this.postulacion.postularCandidato(this.view.getPostulacion());
            this.view.getCandidatosPostulados().clear();
            this.view.setCandidatosPostulados(this.postulacion.consultarCandidatosPostulacion(idPostulacion));
            this.view.setMostrarDetalle(false);

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("errorPostulacion", "", exception.getMessage());
        }

    }

    public void ocultarDetalle() {
        this.view.setMostrarDetalle(false);
    }

    public PostulacionVacanteView getView() {
        return this.view;
    }

    public void setView(PostulacionVacanteView view) {
        this.view = view;
    }

}
