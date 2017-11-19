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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2017
 */
@ManagedBean(name = "estructuraNomina")
@ViewScoped
public class EstructuraNominaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8088983899129614271L;
    @Inject
    private Empleado empleado;
    @Inject
    private PuestosAutorizadosEmpleados puestosAutorizados;

    private EstructuraNominaView view;

    @PostConstruct
    public void inicio() {
        view = new EstructuraNominaView();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());
    }

    public void consultarEmpleado() {
        view.getFiltro().setTipoFiltro(EnumTipoFiltro.CRITERIO_FEDERALES);
        view.setEmpleados(empleado.consultarEmpleadosConPuestosActivos(view.getFiltro()));
        if (view.getEmpleados().isEmpty()) {
            JSFUtils.warningMessage("", "No se encontraron empleados con el criterio " + view.getFiltro().getCriterio());
        }
    }

    public void ocultarEstructura() {
        view.setMostrarInformacionPuesto(false);
    }

    public void actualizarEstructuras() {
        try {
            view.getEstructuraNomina().setIdUsuario(view.getIdUsuarioLogeado());
            puestosAutorizados.actualizarEstructuraNomina(view.getEstructuraNomina());
            view.setMostrarInformacionPuesto(false);

            JSFUtils.infoMessage("", "¡La información ha sido actualizada con éxito!");

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
        }

    }

    public void seleccionarEmpleado(Integer idPuesto) {
        view.setMostrarInformacionPuesto(true);
        view.setPuesto(puestosAutorizados.obtenerInformacionIdPuesto(idPuesto));
        view.setEstructuraNomina(puestosAutorizados.obtenerEstructuraNominaPuesto(idPuesto));
        view.getEstructuraNomina().setIdPuesto(idPuesto);
    }

    public EstructuraNominaView getView() {
        return view;
    }

    public void setView(EstructuraNominaView view) {
        this.view = view;
    }

}
