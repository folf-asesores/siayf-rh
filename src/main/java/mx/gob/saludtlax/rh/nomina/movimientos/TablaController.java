
package mx.gob.saludtlax.rh.nomina.movimientos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneral;
import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;

@ManagedBean
@ViewScoped
public class TablaController {

    @Inject
    PuestoGeneral puestoGeneral;

    private List<PuestoGeneralDTO> puestos = new ArrayList<>();
    private DualListModel<PuestoGeneralDTO> puestosSeleccion;
    private List<PuestoGeneralDTO> puestosTarget = new ArrayList<>();

    private List<String> up = new ArrayList<>();
    private List<String> down = new ArrayList<>();
    private DualListModel<String> puestosSeleccion2;

    @PostConstruct
    public void inicio() {
        puestos = puestoGeneral.consultarListaPuestoGeneral();
        for (PuestoGeneralDTO codigo : puestos) {
            up.add(codigo.getCodigo());
        }

        puestosSeleccion2 = new DualListModel<>(up, down);

        puestosSeleccion = new DualListModel<>(puestos, puestosTarget);

    }

    public List<PuestoGeneralDTO> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<PuestoGeneralDTO> puestos) {
        this.puestos = puestos;
    }

    public DualListModel<PuestoGeneralDTO> getPuestosSeleccion() {
        return puestosSeleccion;
    }

    public void setPuestosSeleccion(
            DualListModel<PuestoGeneralDTO> puestosSeleccion) {
        this.puestosSeleccion = puestosSeleccion;
    }

    public List<String> getUp() {
        return up;
    }

    public void setUp(List<String> up) {
        this.up = up;
    }

    public List<String> getDown() {
        return down;
    }

    public void setDown(List<String> down) {
        this.down = down;
    }

    public DualListModel<String> getPuestosSeleccion2() {
        return puestosSeleccion2;
    }

    public void setPuestosSeleccion2(DualListModel<String> puestosSeleccion2) {
        this.puestosSeleccion2 = puestosSeleccion2;
    }

}
