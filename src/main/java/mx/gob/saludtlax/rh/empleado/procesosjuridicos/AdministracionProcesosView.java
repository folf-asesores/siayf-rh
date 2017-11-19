
package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class AdministracionProcesosView {

    private FiltroDTO filtro = new FiltroDTO();
    private InfoEmpleadoDTO empleado;
    private ProcesoDTO proceso = new ProcesoDTO();
    private ProcesoDTO procesoSelect = new ProcesoDTO();

    private List<SelectItem> listaFiltros;
    private List<SelectItem> listaProceso;
    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<ProcesoDTO> procesoListo;

    private String tipoBusquedaHeader = "";

    private Boolean panelPrincipal;
    private Boolean panelGestion;
    private Boolean panelConsulta;

    private Boolean mostrarMenuDetalles = false;
    private Boolean operacionNuevo;
    private boolean mostrarResultadoConsulta = false;
    private boolean mostrarTipoBusquedaHeader = false;
    private Boolean dialogoEliminar = Boolean.FALSE;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        panelConsulta = Boolean.FALSE;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
        panelConsulta = Boolean.FALSE;
    }

    public void panelConsulta() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.FALSE;
        panelConsulta = Boolean.TRUE;
    }

    public FiltroDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    public InfoEmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(InfoEmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public ProcesoDTO getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public ProcesoDTO getProcesoSelect() {
        return procesoSelect;
    }

    public void setProcesoSelect(ProcesoDTO procesoSelect) {
        this.procesoSelect = procesoSelect;
    }

    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public List<SelectItem> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<SelectItem> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<ProcesoDTO> getProcesoListo() {
        return procesoListo;
    }

    public void setProcesoListo(List<ProcesoDTO> procesoListo) {
        this.procesoListo = procesoListo;
    }

    public String getTipoBusquedaHeader() {
        return tipoBusquedaHeader;
    }

    public void setTipoBusquedaHeader(String tipoBusquedaHeader) {
        this.tipoBusquedaHeader = tipoBusquedaHeader;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getPanelGestion() {
        return panelGestion;
    }

    public void setPanelGestion(Boolean panelGestion) {
        this.panelGestion = panelGestion;
    }

    public Boolean getPanelConsulta() {
        return panelConsulta;
    }

    public void setPanelConsulta(Boolean panelConsulta) {
        this.panelConsulta = panelConsulta;
    }

    public Boolean getMostrarMenuDetalles() {
        return mostrarMenuDetalles;
    }

    public void setMostrarMenuDetalles(Boolean mostrarMenuDetalles) {
        this.mostrarMenuDetalles = mostrarMenuDetalles;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public boolean isMostrarResultadoConsulta() {
        return mostrarResultadoConsulta;
    }

    public void setMostrarResultadoConsulta(boolean mostrarResultadoConsulta) {
        this.mostrarResultadoConsulta = mostrarResultadoConsulta;
    }

    public boolean isMostrarTipoBusquedaHeader() {
        return mostrarTipoBusquedaHeader;
    }

    public void setMostrarTipoBusquedaHeader(boolean mostrarTipoBusquedaHeader) {
        this.mostrarTipoBusquedaHeader = mostrarTipoBusquedaHeader;
    }

    public Boolean getDialogoEliminar() {
        return dialogoEliminar;
    }

    public void setDialogoEliminar(Boolean dialogoEliminar) {
        this.dialogoEliminar = dialogoEliminar;
    }

}
