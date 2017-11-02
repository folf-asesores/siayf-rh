/*
 * DependienteEconomicoView.java
 * Creado el Sep 26, 2016 10:47:41 AM
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.List;
import javax.faces.model.SelectItem;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DependienteEconomicoView {

    private String busqueda;
    private InfoEmpleadoDTO empleadoSelecionado;
    private List<InfoEmpleadoDTO> empleadosEncontrados;
    private DependienteEconomicoDTO dependienteEconomico;
    private List<InfoDependienteEconomicoDTO> dependientesEconomicos;
    private Boolean panelBusqueda;
    private Boolean panelDependienteEconomico;
    private Boolean panelNuevo;
    private Boolean panelActualizar;

    public DependienteEconomicoView() {
        empleadoSelecionado = new InfoEmpleadoDTO();
        dependienteEconomico = new DependienteEconomicoDTO();
        panelBusqueda = true;
        panelDependienteEconomico = false;
        panelNuevo = false;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public DependienteEconomicoDTO getDependienteEconomico() {
        return dependienteEconomico;
    }

    public void setDependienteEconomico(DependienteEconomicoDTO dependienteEconomico) {
        this.dependienteEconomico = dependienteEconomico;
    }

    public InfoEmpleadoDTO getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(InfoEmpleadoDTO empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
    }

    public List<InfoEmpleadoDTO> getEmpleadosEncontrados() {
        return empleadosEncontrados;
    }

    public void setEmpleadosEncontrados(List<InfoEmpleadoDTO> empleadosEncontrados) {
        this.empleadosEncontrados = empleadosEncontrados;
    }

    public List<InfoDependienteEconomicoDTO> getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(List<InfoDependienteEconomicoDTO> dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    public List<SelectItem> getSexos() {
        return SelectItemsUtil.listaTiposSexo();
    }

    public List<SelectItem> getParentescos() {
        return SelectItemsUtil.listaParentescos();
    }

    public Boolean getPanelBusqueda() {
        return panelBusqueda;
    }

    public void setPanelBusqueda(Boolean panelBusqueda) {
        this.panelBusqueda = panelBusqueda;
    }

    public Boolean getPanelDependienteEconomico() {
        return panelDependienteEconomico;
    }

    public void setPanelDependienteEconomico(Boolean panelDependienteEconomico) {
        this.panelDependienteEconomico = panelDependienteEconomico;
    }

    public Boolean getPanelNuevo() {
        return panelNuevo;
    }

    public void setPanelNuevo(Boolean panelNuevo) {
        this.panelNuevo = panelNuevo;
    }

    public Boolean getPanelActualizar() {
        return panelActualizar;
    }

    public void setPanelActualizar(Boolean panelActualizar) {
        this.panelActualizar = panelActualizar;
    }
}
