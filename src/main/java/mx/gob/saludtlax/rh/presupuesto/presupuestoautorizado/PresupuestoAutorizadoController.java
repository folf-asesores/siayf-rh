
package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 *
 * @author José Pablo
 *
 */

@ManagedBean(name = "presupuestoAutorizado")
@ViewScoped
public class PresupuestoAutorizadoController implements Serializable {

    private static final long serialVersionUID = 2116402280296565216L;

    @Inject
    private PresupuestoAutorizado presupuestoAutorizado;

    @Inject
    private PresupuestoAutorizadoView view;

    @PostConstruct
    private void init() {
        view = new PresupuestoAutorizadoView();
        mostrarPrincipal();
    }

    public void vistaPrincipal() {
        view.setListaPresupuestoAutorizado(presupuestoAutorizado.obtenerListaPresupuestoAutorizado());
        view.setActualizarPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
        view.setCreaPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
        view.setPanelPrincipal(true);
        view.setPanelCrear(false);
        view.setPanelActualizar(false);
    }

    public void obtenerListaPresupuestoAutorizado() {
        List<PresupuestoAutorizadoDTO> lista = presupuestoAutorizado.obtenerListaPresupuestoAutorizado();

        view.setListaPresupuestoAutorizado(lista);
    }

    public void crearPresupuestoAutorizado() {
        try {

            presupuestoAutorizado.crearPresupuestoAutorizado(view.getCreaPresupuestoAutorizado());

            mostrarPrincipal();

            JSFUtils.infoMessage("Registro Correcto", "¡Se registro correctamente!");

        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void actualizarPresupuestoAutorizado() {
        try {

            presupuestoAutorizado.actualizarPresupuestoAutorizado(view.getActualizarPresupuestoAutorizado());

            mostrarPrincipal();

            JSFUtils.infoMessage("Actualización Correcta", "¡Se actualizo correctamente!");

        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void mostrarNuevoRegistro() {
        view.setCreaPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
        view.setPanelPrincipal(false);
        view.setPanelCrear(true);
        view.setPanelActualizar(false);
    }

    public void mostrarActualizacion(PresupuestoAutorizadoDTO dto) {
        view.setActualizarPresupuestoAutorizado(dto);
        view.setPanelPrincipal(false);
        view.setPanelCrear(false);
        view.setPanelActualizar(true);
    }

    public void eliminarPresupuestoAutorizado(Integer idPresupuestoAutorizado) {
        presupuestoAutorizado.eliminarPresupuestoAutorizado(idPresupuestoAutorizado);
        JSFUtils.infoMessage("Eliminación Correcta", "¡Se elimino correctamente!");
        mostrarPrincipal();
    }

    public void mostrarPrincipal() {
        view.setActualizarPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
        view.setCreaPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
        view.setPanelPrincipal(true);
        view.setPanelCrear(false);
        view.setPanelActualizar(false);
        obtenerListaPresupuestoAutorizado();
    }

    public void regresarModulo() throws IOException {
        JSFUtils.redireccionar("/siayf-rh/contenido/presupuesto/presupuestoAutorizado.xhtml?faces-redirect=true");
    }

    public PresupuestoAutorizadoView getView() {
        return view;
    }
}
