/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.BolsaTrabajo;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/11/2016 13:56:19
 */
@ManagedBean(name = "altaVoluntario")
@ViewScoped
public class AltaVoluntarioController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1103504496186542320L;

    @Inject
    private BolsaTrabajo bolsaTrabajo;
    @Inject
    private Catalogo catalogo;
    @Inject
    private Voluntarios voluntarios;

    private AltaVoluntarioView view;

    @PostConstruct
    public void inicio() {
        view = new AltaVoluntarioView();
        view.setMostrarBusqueda(true);
        view.setProgramas(
                SelectItemsUtil.listaCatalogos(catalogo.consultarProgramas()));
        view.setListaAdscripciones(SelectItemsUtil
                .listaCatalogos(catalogo.consultarAdscripciones()));
        view.setListaSubadscripcion(SelectItemsUtil
                .listaCatalogos(catalogo.consultarSubadscripciones()));
        view.setListaServicio(
                SelectItemsUtil.listaCatalogos(catalogo.consultarServicios()));
        view.setListaFuncion(
                SelectItemsUtil.listaCatalogos(catalogo.consultarFunciones()));
    }

    public void consultarCandidato() {

        FiltroDTO filtroDTO = new FiltroDTO();
        filtroDTO.setCriterio(view.getCriterio());
        filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
        view.setAspirantes(bolsaTrabajo.consultarPorCriterio(filtroDTO));
        if (view.getAspirantes().isEmpty()) {
            JSFUtils.warningMessage("",
                    "No se encontraron resultados con el criterio "
                            + view.getCriterio());
        }

    }

    public void seleccionarAspirante(Integer idAspirante) {
        try {
            view.getAlta().setIdAspirante(idAspirante);
            view.setAspirante(
                    bolsaTrabajo.obtenerDetalleAspirantePorId(idAspirante));
            view.setMostrarBusqueda(false);
            view.setMostrarAlta(true);
            view.getAspirantes().clear();
            view.setCriterio("");
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void registrarVoluntario() {

        try {
            voluntarios.registrarVoluntario(view.getAlta());
            view.setMostrarAlta(false);
            view.setMostrarBusqueda(true);
            AltaVoluntarioDTO alta = new AltaVoluntarioDTO();
            view.setAlta(alta);
            JSFUtils.infoMessage("",
                    "El alta del voluntario se ha registrado de manera exitosa.");

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void cancelar() {
        view.setMostrarAlta(false);
        view.setMostrarBusqueda(true);
    }

    public AltaVoluntarioView getView() {
        return view;
    }

    public void setView(AltaVoluntarioView view) {
        this.view = view;
    }

}
