/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.configuracion.tabulador.InfoSueldoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/05/2016 04/05/2016
 */
public class MovimientoEmpleadoView {

    private List<SelectItem> listaMovimientosHijos;
    private List<SelectItem> listaMovimientosPadre;
    private List<SelectItem> listaPuestos = new ArrayList<>();

    private List<InfoEmpleadoDTO> empleados;

    private InfoEmpleadoDTO infoEmpleado;
    private RegistroMovimientoDTO movimiento = new RegistroMovimientoDTO();
    private InfoSueldoDTO salario = new InfoSueldoDTO();

    private String criterio;
    private Integer idMovimientoPadre;
    private Integer idPuesto;

    private boolean mostrarPanelGroupBusqueda;
    private boolean mostrarPanelGroupMovimiento;
    private boolean mostrarIncapacidad;
    private boolean mostrarFechas;
    private boolean mostrarModificacionPuesto;

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public InfoSueldoDTO getSalario() {
        return salario;
    }

    public void setSalario(InfoSueldoDTO salario) {
        this.salario = salario;
    }

    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public boolean isMostrarModificacionPuesto() {
        return mostrarModificacionPuesto;
    }

    public void setMostrarModificacionPuesto(
            boolean mostrarModificacionPuesto) {
        this.mostrarModificacionPuesto = mostrarModificacionPuesto;
    }

    public boolean isMostrarFechas() {
        return mostrarFechas;
    }

    public void setMostrarFechas(boolean mostrarFechas) {
        this.mostrarFechas = mostrarFechas;
    }

    public RegistroMovimientoDTO getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(RegistroMovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isMostrarIncapacidad() {
        return mostrarIncapacidad;
    }

    public void setMostrarIncapacidad(boolean mostrarIncapacidad) {
        this.mostrarIncapacidad = mostrarIncapacidad;
    }

    public Integer getIdMovimientoPadre() {
        return idMovimientoPadre;
    }

    public void setIdMovimientoPadre(Integer idMovimientoPadre) {
        this.idMovimientoPadre = idMovimientoPadre;
    }

    public boolean isMostrarPanelGroupMovimiento() {
        return mostrarPanelGroupMovimiento;
    }

    public void setMostrarPanelGroupMovimiento(
            boolean mostrarPanelGroupMovimiento) {
        this.mostrarPanelGroupMovimiento = mostrarPanelGroupMovimiento;
    }

    public InfoEmpleadoDTO getInfoEmpleado() {
        return infoEmpleado;
    }

    public void setInfoEmpleado(InfoEmpleadoDTO infoEmpleado) {
        this.infoEmpleado = infoEmpleado;
    }

    public boolean isMostrarPanelGroupBusqueda() {
        return mostrarPanelGroupBusqueda;
    }

    public void setMostrarPanelGroupBusqueda(
            boolean mostrarPanelGroupBusqueda) {
        this.mostrarPanelGroupBusqueda = mostrarPanelGroupBusqueda;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<SelectItem> getListaMovimientosHijos() {
        return listaMovimientosHijos;
    }

    public void setListaMovimientosHijos(
            List<SelectItem> listaMovimientosHijos) {
        this.listaMovimientosHijos = listaMovimientosHijos;
    }

    public List<SelectItem> getListaMovimientosPadre() {
        return listaMovimientosPadre;
    }

    public void setListaMovimientosPadre(
            List<SelectItem> listaMovimientosPadre) {
        this.listaMovimientosPadre = listaMovimientosPadre;
    }

}
