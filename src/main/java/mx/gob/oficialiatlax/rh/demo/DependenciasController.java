
package mx.gob.oficialiatlax.rh.demo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class DependenciasController {

    @Inject
    DependenciasEJB dependenciasEJB;

    private List<TipoDependenciasDTO> listTipos = new ArrayList<>();
    private Integer tipoSeleccionado;
    private List<SelectItem> tipos = new ArrayList<>();

    private List<DependenciasDTO> lisDependencias = new ArrayList<>();
    private Integer dependenciaSelecciada;
    private List<SelectItem> itemsDep = new ArrayList<>();

    @PostConstruct
    public void inicio() {
        listTipos = dependenciasEJB.obtenerTiposDependencias();
        for (TipoDependenciasDTO dpdto : listTipos) {
            SelectItem item = new SelectItem(dpdto.getId(),
                    dpdto.getTipoDependencia());
            tipos.add(item);
        }
        System.out.println("tipos" + tipos.size());
    }

    public void mostrarDepdenciasPorTipo() {
        List<DependenciasDTO> listd = new ArrayList<>();
        listd = dependenciasEJB.obtenerDependencias(tipoSeleccionado);
        lisDependencias.clear();
        lisDependencias.addAll(listd);
        itemsDep.clear();
        for (DependenciasDTO dto : listd) {
            SelectItem item = new SelectItem(dto.getIdDependencia(),
                    dto.getDescripcion());

            itemsDep.add(item);
        }

    }

    public List<TipoDependenciasDTO> getListTipos() {
        return listTipos;
    }

    public void setListTipos(List<TipoDependenciasDTO> listTipos) {
        this.listTipos = listTipos;
    }

    public Integer getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(Integer tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public List<SelectItem> getTipos() {
        return tipos;
    }

    public void setTipos(List<SelectItem> tipos) {
        this.tipos = tipos;
    }

    public List<DependenciasDTO> getLisDependencias() {
        return lisDependencias;
    }

    public void setLisDependencias(List<DependenciasDTO> lisDependencias) {
        this.lisDependencias = lisDependencias;
    }

    public Integer getDependenciaSelecciada() {
        return dependenciaSelecciada;
    }

    public void setDependenciaSelecciada(Integer dependenciaSelecciada) {
        this.dependenciaSelecciada = dependenciaSelecciada;
    }

    public List<SelectItem> getItemsDep() {
        return itemsDep;
    }

    public void setItemsDep(List<SelectItem> itemsDep) {
        this.itemsDep = itemsDep;
    }

}
