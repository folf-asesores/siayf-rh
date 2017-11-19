/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Leila Schiaffini Ehuan
 * @since 14/09/2016 16:24:05
 *
 */
public class ConsultaMovimientoView {

    private List<SelectItem> listaFiltros;
    private List<SelectItem> listaMovimientos;
    private List<SelectItem> listaMovimientosHijos;

    private DetalleMovimientoDTO detalleMovimiento;
    private List<InfoMovimientoDTO> movimientos = new ArrayList<>();
    private Integer idPadre;
    private Integer idHijo;
    private Integer idFiltro;
    private String criterio;
    private boolean mostrarBusqueda;
    private boolean mostrarDetalle;
    private boolean mostrarHijos;
    private boolean mostrarPadres;
    private boolean mostrarCriterio;
    private Boolean fechas = Boolean.FALSE;
    private Boolean rfc = Boolean.FALSE;

    private Date fechaInicial;
    private Date fechaFinal;
    private String parametro; // rfc o nombres

    public DetalleMovimientoDTO getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(DetalleMovimientoDTO detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public boolean isMostrarPadres() {
        return mostrarPadres;
    }

    public void setMostrarPadres(boolean mostrarPadres) {
        this.mostrarPadres = mostrarPadres;
    }

    public boolean isMostrarHijos() {
        return mostrarHijos;
    }

    public void setMostrarHijos(boolean mostrarHijos) {
        this.mostrarHijos = mostrarHijos;
    }

    public boolean isMostrarCriterio() {
        return mostrarCriterio;
    }

    public void setMostrarCriterio(boolean mostrarCriterio) {
        this.mostrarCriterio = mostrarCriterio;
    }

    public Integer getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(Integer idFiltro) {
        this.idFiltro = idFiltro;
    }

    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public List<SelectItem> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<SelectItem> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public List<SelectItem> getListaMovimientosHijos() {
        return listaMovimientosHijos;
    }

    public void setListaMovimientosHijos(
            List<SelectItem> listaMovimientosHijos) {
        this.listaMovimientosHijos = listaMovimientosHijos;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public boolean isMostrarDetalle() {
        return mostrarDetalle;
    }

    public void setMostrarDetalle(boolean mostrarDetalle) {
        this.mostrarDetalle = mostrarDetalle;
    }

    public List<InfoMovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<InfoMovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public Integer getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(Integer idHijo) {
        this.idHijo = idHijo;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Boolean getFechas() {
        return fechas;
    }

    public void setFechas(Boolean fechas) {
        this.fechas = fechas;
    }

    public Boolean getRfc() {
        return rfc;
    }

    public void setRfc(Boolean rfc) {
        this.rfc = rfc;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

}
