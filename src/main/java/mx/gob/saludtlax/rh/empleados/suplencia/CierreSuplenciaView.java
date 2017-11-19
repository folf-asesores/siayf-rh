/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 08/11/2016 18:08:10
 */
public class CierreSuplenciaView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4999023077097749815L;

    private List<SelectItem> listaQuincenas = new ArrayList<>();
    private List<SelectItem> listaEstatus = new ArrayList<>();
    private List<SelectItem> listaProyectos = new ArrayList<>();
    private List<SelectItem> listaDependencias = new ArrayList<>();
    private List<SelectItem> listaUnidadesResponsables = new ArrayList<>();
    private List<SelectItem> listaFuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaSubfuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaTiposRecursos = new ArrayList<>();
    private List<SelectItem> listaCuentaFinanciamiento = new ArrayList<>();

    private List<DetalleSuplenciaDTO> listaDetalles = new ArrayList<>();
    private List<DetalleSuplenciaDTO> desglose = new ArrayList<>();
    private CierreQuincenaDTO cierre = new CierreQuincenaDTO();
    private boolean mostrarDesglose;
    private boolean mostrarBusqueda;
    private boolean mostrarColumnasCierre;
    private boolean bloquearFinanciamientos;

    private String suplente;
    private int numeroQuincena;
    private String estatus;
    private BigDecimal total;
    private int totalDias;

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public boolean isBloquearFinanciamientos() {
        return bloquearFinanciamientos;
    }

    public void setBloquearFinanciamientos(boolean bloquearFinanciamientos) {
        this.bloquearFinanciamientos = bloquearFinanciamientos;
    }

    public boolean isMostrarColumnasCierre() {
        return mostrarColumnasCierre;
    }

    public void setMostrarColumnasCierre(boolean mostrarColumnasCierre) {
        this.mostrarColumnasCierre = mostrarColumnasCierre;
    }

    public CierreQuincenaDTO getCierre() {
        return cierre;
    }

    public void setCierre(CierreQuincenaDTO cierre) {
        this.cierre = cierre;
    }

    public List<SelectItem> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<SelectItem> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public List<SelectItem> getListaDependencias() {
        return listaDependencias;
    }

    public void setListaDependencias(List<SelectItem> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public List<SelectItem> getListaUnidadesResponsables() {
        return listaUnidadesResponsables;
    }

    public void setListaUnidadesResponsables(
            List<SelectItem> listaUnidadesResponsables) {
        this.listaUnidadesResponsables = listaUnidadesResponsables;
    }

    public List<SelectItem> getListaFuentesFinanciamiento() {
        return listaFuentesFinanciamiento;
    }

    public void setListaFuentesFinanciamiento(
            List<SelectItem> listaFuentesFinanciamiento) {
        this.listaFuentesFinanciamiento = listaFuentesFinanciamiento;
    }

    public List<SelectItem> getListaSubfuentesFinanciamiento() {
        return listaSubfuentesFinanciamiento;
    }

    public void setListaSubfuentesFinanciamiento(
            List<SelectItem> listaSubfuentesFinanciamiento) {
        this.listaSubfuentesFinanciamiento = listaSubfuentesFinanciamiento;
    }

    public List<SelectItem> getListaTiposRecursos() {
        return listaTiposRecursos;
    }

    public void setListaTiposRecursos(List<SelectItem> listaTiposRecursos) {
        this.listaTiposRecursos = listaTiposRecursos;
    }

    public List<SelectItem> getListaCuentaFinanciamiento() {
        return listaCuentaFinanciamiento;
    }

    public void setListaCuentaFinanciamiento(
            List<SelectItem> listaCuentaFinanciamiento) {
        this.listaCuentaFinanciamiento = listaCuentaFinanciamiento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<SelectItem> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<SelectItem> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public String getSuplente() {
        return suplente;
    }

    public void setSuplente(String suplente) {
        this.suplente = suplente;
    }

    public List<DetalleSuplenciaDTO> getDesglose() {
        return desglose;
    }

    public void setDesglose(List<DetalleSuplenciaDTO> desglose) {
        this.desglose = desglose;
    }

    public int getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(int numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public List<SelectItem> getListaQuincenas() {
        return listaQuincenas;
    }

    public void setListaQuincenas(List<SelectItem> listaQuincenas) {
        this.listaQuincenas = listaQuincenas;
    }

    public List<DetalleSuplenciaDTO> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleSuplenciaDTO> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public boolean isMostrarDesglose() {
        return mostrarDesglose;
    }

    public void setMostrarDesglose(boolean mostrarDesglose) {
        this.mostrarDesglose = mostrarDesglose;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

}
