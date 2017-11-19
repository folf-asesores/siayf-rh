
package mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.TipoConceptoNominaEnum;

@ManagedBean(name = "conceptosNominaContratos")
@ViewScoped
public class ConceptosNominaContratosController implements Serializable {
    private static final long serialVersionUID = 1439823081136660382L;

    @Inject
    private ConceptoNominaContratosEJB ejb;
    @Inject
    private ConceptosNominaContratosView view;

    @PostConstruct
    public void conceptosInit() {
        view.setEstatusConceptoNominaLista(ejb.listaEstatusConceptoNomina());
        view.setTipoSelect(0);
        irPrincipal();
    }

    public String updateCategoriaSAT() {
        view.setCategoriaSatLista(ejb.listaCategoriaSatPorTipo(view.getConceptoNominaSelect().getTipo()));
        return null;
    }

    public String habilitarIrGestionar() {
        view.setDisabledIrGestionar(Boolean.FALSE);
        return null;
    }

    public String deshabilitarIrGestionar() {
        view.setDisabledIrGestionar(Boolean.TRUE);
        return null;
    }

    public String irPrincipal() {
        view.setConceptoNominaLista(ejb.obtenerConceptoNominasLista(TipoConceptoNominaEnum.get(view.getTipoSelect())));
        view.panelPrincipal();
        return null;
    }

    public String irGestionarConceptoNomina() {
        updateCategoriaSAT();
        view.panelGestion();
        view.setOpcion(Boolean.FALSE);
        return null;
    }

    public String irNuevoConceptoNomina() {
        view.setConceptoNominaSelect(ejb.nuevoConceptoNomina());
        view.panelGestion();
        view.setOpcion(Boolean.TRUE);
        return null;
    }

    public String editarFormula() {
        view.setEditarFormula(view.getConceptoNominaSelect().getFormula());
        return null;
    }

    public String agregarFormula() {
        view.getConceptoNominaSelect().setFormula(view.getEditarFormula());
        return null;
    }

    public String guardarConceptoNomina() {
        if (view.getOpcion()) {
            ejb.crearConceptoNomina(view.getConceptoNominaSelect());
        } else {
            ejb.actualizarConceptoNomina(view.getConceptoNominaSelect());
        }
        view.panelGestion();
        return null;
    }

    public String probarFormula() {
        view.setResult(ejb.evaluarFormula(view.getEditarFormula()));
        return null;
    }

    public void onRowSelectConceptoNomina(SelectEvent event) {
        view.setDisabledIrGestionar(Boolean.FALSE);
    }

    public void onRowUnselectConceptoNomina(UnselectEvent event) {
        view.setDisabledIrGestionar(Boolean.TRUE);
    }

    public ConceptosNominaContratosView getView() {
        return view;
    }
}