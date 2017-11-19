/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 24/05/2016 12:27:23
 */
@ManagedBean(name = "terceroInstitucional")
@ViewScoped
public class TerceroInstitucionalController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8069891535607788590L;

    /**
     *
     */

    @Inject
    private TerceroInstitucional terceroInstitucional;

    private TerceroInstitucionalView view;

    @PostConstruct
    private void init() {
        view = new TerceroInstitucionalView();
        obtenerListaTerceroInstitucional();
    }

    /**
     * obtiene la lista de terceros institucionales registrados en la bd
     */
    public void obtenerListaTerceroInstitucional() {
        try {
            List<TerceroInstitucionalDTO> listaTercero = terceroInstitucional.obtenerListaTerceroInstitucional();

            view.setListaTerceroInstitucional(listaTercero);

        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    /**
     * Crea o Registra un tercero institucional dependiendo de la accion que
     * este realizando el usuario
     */
    public void crearActualizarTerceroInstitucional() {
        try {

            if (view.getAccionButton().equals("Registrar")) {
                terceroInstitucional.crearTerceroInstitucional(view.getTerceroInstitucional());
                JSFUtils.infoMessage("Tercero Institucional: ", "Se realizo correctamente");
            } else if (view.getAccionButton().equals("Actualizar")) {
                terceroInstitucional.actualizarTerceroInstitucional(view.getTerceroInstitucional());
                JSFUtils.infoMessage("Tercero Institucional: ", "Se realizo correctamente");
            }

            deseleccionarTerceroInstitucional();
        } catch (BusinessException | ValidationException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    /**
     * Elimina el registro tercero institucional seleccionado
     */
    public void eliminarTerceroInstitucional() {
        try {
            terceroInstitucional.eliminarTerceroInstitucional(view.getTerceroInstitucional().getIdTerceroInstitucional());
            JSFUtils.infoMessage("Tercero Institucional: ", "Se realizo correctamente");
            deseleccionarTerceroInstitucional();
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    /**
     * Cacha desde la vista el registro seleccionado y se lo setea al objeto
     *
     * @param terceroInstitucionalDTO
     */
    public void seleccionarActualizacionTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucionalDTO) {
        view.setTerceroInstitucional(terceroInstitucionalDTO);
        view.setAccionButton("Actualizar");
    }

    /**
     * Cacha desde la vista el registro seleccionado y se lo setea al objeto
     *
     * @param terceroInstitucionalDTO
     */
    public void seleccionarEliminacionTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucionalDTO) {
        view.setTerceroInstitucional(terceroInstitucionalDTO);
    }

    /**
     * Limpia el registro seleccionado
     */
    public void deseleccionarTerceroInstitucional() {
        view.setTerceroInstitucional(new TerceroInstitucionalDTO());
        obtenerListaTerceroInstitucional();
    }

    public TerceroInstitucionalView getView() {
        return view;
    }

    public void setView(TerceroInstitucionalView view) {
        this.view = view;
    }

}
