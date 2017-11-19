/*
 *
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

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
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "salarioMinimo")
@ViewScoped
public class SalarioMinimoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8412051015471358991L;

    @Inject
    private SalarioMinimo salarioMinimo;

    private SalarioMinimoView view;

    @PostConstruct
    private void init() {
        view = new SalarioMinimoView();

        obtenerListaSalarioMinimo();
    }

    /**
     * Obteniendo la lista de salarios minimos registrados en la bd
     */
    public void obtenerListaSalarioMinimo() {
        List<SalarioMinimoDTO> lista = salarioMinimo.obtenerListaSalarioMinimo();

        view.setListaSalariosMinimos(lista);
    }

    public void crearSalarioMinimo() {
        try {
            salarioMinimo.crearSalarioMinimo(view.getCreaSalarioMinimo());
            JSFUtils.infoMessage("Registro Salario Minimo: ", "Se realizo correctamente.");
            mostrarPrincipal();
        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void actualizarSalarioMinimo() {
        try {
            salarioMinimo.actualizarSalarioMinimo(view.getActualizarSalarioMinimo());
            JSFUtils.infoMessage("Actualización Salario Minimo: ", "Se realizo correctamente.");
            mostrarPrincipal();
        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void eliminarSalarioMinimo() {
        try {
            salarioMinimo.eliminarSalarioMinimo(view.getIdSalarioMinimoSeleccionado());
            JSFUtils.infoMessage("Eliminación Salario Minimo: ", "Se realizo correctamente.");
            mostrarPrincipal();
        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
        }
    }

    public void mostrarNuevoRegistro() {
        view.setCreaSalarioMinimo(new SalarioMinimoDTO());
        view.setPanelPrincipal(false);
        view.setPanelCrear(true);
        view.setPanelActualizar(false);
        view.setDialogEliminar(false);
    }

    public void mostrarActualizacion(SalarioMinimoDTO dto) {
        view.setActualizarSalarioMinimo(dto);
        view.setPanelPrincipal(false);
        view.setPanelCrear(false);
        view.setPanelActualizar(true);
        view.setDialogEliminar(false);
    }

    public void mostrarDialogEliminar(Integer idSalarioMinimo) {
        view.setIdSalarioMinimoSeleccionado(idSalarioMinimo);
        view.setPanelPrincipal(false);
        view.setPanelCrear(false);
        view.setPanelActualizar(false);
        view.setDialogEliminar(true);
    }

    public void mostrarPrincipal() {
        view.setIdSalarioMinimoSeleccionado(0);
        view.setActualizarSalarioMinimo(new SalarioMinimoDTO());
        view.setCreaSalarioMinimo(new SalarioMinimoDTO());
        view.setPanelPrincipal(true);
        view.setPanelCrear(false);
        view.setPanelActualizar(false);
        view.setDialogEliminar(false);
        obtenerListaSalarioMinimo();
    }

    public void regresarModulo() throws IOException {
        JSFUtils.redireccionar("/siayf-rh/contenido/configuracion/salariosMinimos.xhtml?faces-redirect=true");
    }

    public SalarioMinimoView getView() {
        return view;
    }

    public void setView(SalarioMinimoView view) {
        this.view = view;
    }

}
