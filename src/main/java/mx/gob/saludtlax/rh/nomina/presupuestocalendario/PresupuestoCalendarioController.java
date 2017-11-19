/*
 *
 */

package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "presupuestoCalendario")
@ViewScoped
public class PresupuestoCalendarioController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1628601104971308846L;

    @Inject
    private PresupuestoCalendario presupuestoCalendario;

    private PresupuestoCalendarioView view;

    @PostConstruct
    public void init() {
        view = new PresupuestoCalendarioView();
        vistaPrincipal();
    }

    public void vistaPrincipal() {
        view.setListaPresupuestoCalendario(presupuestoCalendario.obtenerListaPresupuestoCalendario());
        view.setActualizarPresupuestoCalendario(new PresupuestoCalendarioDTO());
        view.setCreaPresupuestoCalendario(new PresupuestoCalendarioDTO());
        view.setMostrarVistaPrincipal(true);
        view.setMostrarVistaCrear(false);
        view.setMostrarVistaActualizar(false);
    }

    public void restarVistaSinBusquedaAnio() {
        if (!ValidacionUtil.esNumeroPositivoInt(view.getAnioCriterio())) {
            vistaPrincipal();
        }
    }

    public void obtenerListaPresupuestoCalendarioPorAnio() {
        try {

            if (ValidacionUtil.esNumeroPositivoInt(view.getAnioCriterio())) {
                List<PresupuestoCalendarioDTO> lista = presupuestoCalendario.obtenerListaPresupuestoCalendarioPorAnio(view.getAnioCriterio());

                if (!lista.isEmpty()) {
                    view.setListaPresupuestoCalendario(lista);
                } else {
                    vistaPrincipal();
                    throw new ValidacionException(
                            "No se encontrarón resultados con el año " + view.getAnioCriterio().toString() + ", por favor ingrese el año correctamente.",
                            ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
                }

            } else {
                vistaPrincipal();
                throw new ValidacionException("El año es requerido, por favor ingrese el año correctamente.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void crearPresupuestoCalendario() {
        try {

            presupuestoCalendario.crearPresupuestoCalendario(view.getCreaPresupuestoCalendario());

            vistaPrincipal();

            JSFUtils.infoMessage("Presupuesto Calendario: ", "¡Se registro correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void actualizarPresupuestoCalendario() {
        try {

            presupuestoCalendario.actualizarPresupuestoCalendario(view.getActualizarPresupuestoCalendario());

            vistaPrincipal();

            JSFUtils.infoMessage("Presupuesto Calendario: ", "¡Se actualizo correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void eliminarPresupuestoCalensario(Integer idPresupuestoCalendario) {
        try {

            presupuestoCalendario.eliminarPresupuestoCalendario(idPresupuestoCalendario);

            vistaPrincipal();

            JSFUtils.infoMessage("Presupuesto Calendario: ", "¡Se elimino correctamente!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        }
    }

    public void mostrarVistaCrearPresupuestoCalendario() {
        view.setCreaPresupuestoCalendario(new PresupuestoCalendarioDTO());
        view.setMostrarVistaPrincipal(false);
        view.setMostrarVistaCrear(true);
        view.setMostrarVistaActualizar(false);
    }

    public void mostrarVistaActualizarPresupuestoCalendario(PresupuestoCalendarioDTO dto) {
        view.setActualizarPresupuestoCalendario(dto);
        view.setMostrarVistaPrincipal(false);
        view.setMostrarVistaCrear(false);
        view.setMostrarVistaActualizar(true);
    }

    /**
     * @return the view
     */
    public PresupuestoCalendarioView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(PresupuestoCalendarioView view) {
        this.view = view;
    }

}
