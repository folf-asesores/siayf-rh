/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/11/2016 19:25:47
 */
@ManagedBean(name = "consultaVoluntario")
@ViewScoped
public class ConsultaVoluntarioController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4254567418057231969L;

    @Inject
    private Voluntarios voluntarios;

    private ConsultaVoluntarioView view;

    @PostConstruct
    public void inicio() {
        view = new ConsultaVoluntarioView();
        view.setListaTiposConsulta(
                SelectItemsUtil.listaTiposBusquedaVoluntarios());
    }

    public void seleccionarTipoBusqueda() {
        view.setMostrarCriterio(false);
        view.getVoluntarios().clear();
        if (view.getConsulta()
                .getTipoConsulta() == EnumTipoConsultaVoluntario.NOMBRE) {
            view.getConsulta().setCriterio("");
            view.setMostrarCriterio(true);
        } else if (view.getConsulta()
                .getTipoConsulta() == EnumTipoConsultaVoluntario.ACTIVOS) {
            view.setVoluntarios(
                    voluntarios.consultarVoluntarios(view.getConsulta()));
            if (view.getVoluntarios().isEmpty()) {
                JSFUtils.warningMessage("",
                        "No se encontraron voluntarios activos");
            }
        }
    }

    public void consultarVoluntarios() {
        view.setVoluntarios(
                voluntarios.consultarVoluntarios(view.getConsulta()));
    }

    public ConsultaVoluntarioView getView() {
        return view;
    }

    public void setView(ConsultaVoluntarioView view) {
        this.view = view;
    }

}
