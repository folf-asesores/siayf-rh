/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 22/02/2017 13:39:34
 */
@ManagedBean(name = "estructuraContrato")
@ViewScoped
public class EstructurasContratosController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1883823415107478787L;
    @Inject
    private Empleado empleado;
    @Inject
    private PuestosAutorizadosEmpleados puestosAutorizados;

    private EstructurasContratosView view = new EstructurasContratosView();

    @PostConstruct
    public void inicio() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());
        view.setListaFunciones(SelectItemsUtil.listaFuncionesEspecificas());
        view.setListaJornadas(SelectItemsUtil.listaJornadas());
        view.setListaFinanciamientos(SelectItemsUtil.listaFinanciamientos());
    }

    public void consultarEmpleado() {
        view.getFiltro().setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_CONTRATACION);
        view.getFiltro().setId(EnumTipoContratacion.CONTRATO_ESTATAL);
        view.setEmpleados(empleado.consultarEmpleadosConPuestosActivos(view.getFiltro()));
        if (view.getEmpleados().isEmpty()) {
            JSFUtils.warningMessage("", "No se encontraron empleados con el criterio " + view.getFiltro().getCriterio());
        }
    }

    public void seleccionarEmpleado(Integer idPuesto) {
        view.setMostrarInformacionPuesto(true);

        view.setPuesto(puestosAutorizados.obtenerInformacionIdPuesto(idPuesto));
        view.setEstructuraContrato(puestosAutorizados.obtenerEstructuraContratoPuesto(idPuesto));
        view.getEstructuraContrato().setIdPuesto(idPuesto);

    }

    public void ocultarEstructura() {
        view.setMostrarInformacionPuesto(false);
    }

    public void actualizarEstructuras() {
        try {
            view.getEstructuraContrato().setIdUsuario(view.getIdUsuarioLogeado());
            puestosAutorizados.modificarEstructuraNominaContratos(view.getEstructuraContrato());
            view.setMostrarInformacionPuesto(false);
            EstructuraContratoDTO estructura = new EstructuraContratoDTO();
            view.setEstructuraContrato(estructura);
            JSFUtils.infoMessage("", "¡La información ha sido actualizada con éxito!");

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public EstructurasContratosView getView() {
        return view;
    }

    public void setView(EstructurasContratosView view) {
        this.view = view;
    }

}
