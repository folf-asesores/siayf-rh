
package mx.gob.saludtlax.rh.siif;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "siifLaboralesSubfuentes")
@ViewScoped
public class SiifLaboralesSubfuentesController {
    private SiifLaboralesSubfuentesView view;
    @Inject
    private SiifLaboralesSubfuentesEJB ejb;

    @PostConstruct
    public void initSiifLaboralesSubfuentes() {
        view = new SiifLaboralesSubfuentesView();
        view.setListSiifLaboralesSubfuentes(
                ejb.obtenerSiifLaboralesSubfuentes());
        view.setListSubfuenteF(ejb.obtenerSubfuentesF());
        //		view.setListFuenteF(ejb.obtenerFuentesF());
        //		view.setListSubfuenteF(ejb.obtenerSubfuentesFPorId(view.getSiifLaboralesSubfuentesSelect().getIdFuenteFinanciamiento()));
        irPrincipal();
    }

    public String irPrincipal() {

        view.panelPrincipal();
        return null;
    }

    public SiifLaboralesSubfuentesView getView() {
        return view;
    }

    //	Opciones para ESiif Laborales Subfuentes

    public String irNuevoSubfuente() {
        ejb.crearDatos(view.getSiifLaboralesSubfuentes());
        return null;
    }

    public void eliminarDatos() {
        ejb.eliminarDatos(view.getSiifLaboralesSubfuentesSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String guardarLaboralesSubfuentes() {
        ejb.actualizarDatos(view.getSiifLaboralesSubfuentes(), view
                .getSiifLaboralesSubfuentes().getIdSiifLaboralesSubfuentes());
        return null;
    }

    //Variables

    public void setView(SiifLaboralesSubfuentesView view) {
        this.view = view;
    }

    public void onRowSelectSubfuentes(SelectEvent event) {
        view.setDisabledEliminar(Boolean.FALSE);
    }

    public void onRowUnselectSubfuentes(UnselectEvent event) {
        view.setDisabledEliminar(Boolean.TRUE);
    }

}