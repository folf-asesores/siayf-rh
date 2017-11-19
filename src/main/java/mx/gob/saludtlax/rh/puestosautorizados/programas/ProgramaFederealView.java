/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-21
 *
 */
public class ProgramaFederealView {

    private Integer idUsuarioLogeado;
    private boolean mostrarRegistroPrograma;
    private boolean mostrarRegistroDetalle;

    private ProgramaDTO nuevoPrograma = new ProgramaDTO();
    private DetalleProgramaDTO detallePrograma = new DetalleProgramaDTO();
    private ProgramaDTO edicionPrograma = new ProgramaDTO();
    private List<InfoProgramaDTO> programas = new ArrayList<>();
    private List<InfoDetallePrograma> detalles = new ArrayList<>();

    private List<SelectItem> listaProyectos = new ArrayList<>();
    private List<SelectItem> listaDependencias = new ArrayList<>();
    private List<SelectItem> listaUnidadesResponsables = new ArrayList<>();
    private List<SelectItem> listaPuestos = new ArrayList<>();
    private List<SelectItem> listaFuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaSubfuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaTiposRecursos = new ArrayList<>();
    private List<SelectItem> listaCuentaFinanciamiento = new ArrayList<>();
    private boolean mostrarDetallePartida;
    private boolean mostrarEdicion;

    public boolean isMostrarEdicion() {
        return mostrarEdicion;
    }

    public void setMostrarEdicion(boolean mostrarEdicion) {
        this.mostrarEdicion = mostrarEdicion;
    }

    public ProgramaDTO getEdicionPrograma() {
        return edicionPrograma;
    }

    public void setEdicionPrograma(ProgramaDTO edicionPrograma) {
        this.edicionPrograma = edicionPrograma;
    }

    public boolean isMostrarDetallePartida() {
        return mostrarDetallePartida;
    }

    public void setMostrarDetallePartida(boolean mostrarDetallePartida) {
        this.mostrarDetallePartida = mostrarDetallePartida;
    }

    public DetalleProgramaDTO getDetallePrograma() {
        return detallePrograma;
    }

    public void setDetallePrograma(DetalleProgramaDTO detallePrograma) {
        this.detallePrograma = detallePrograma;
    }

    public List<InfoDetallePrograma> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<InfoDetallePrograma> detalles) {
        this.detalles = detalles;
    }

    public boolean isMostrarRegistroDetalle() {
        return mostrarRegistroDetalle;
    }

    public void setMostrarRegistroDetalle(boolean mostrarRegistroDetalle) {
        this.mostrarRegistroDetalle = mostrarRegistroDetalle;
    }

    public Integer getIdUsuarioLogeado() {
        return idUsuarioLogeado;
    }

    public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
        this.idUsuarioLogeado = idUsuarioLogeado;
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

    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
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

    public ProgramaDTO getNuevoPrograma() {
        return nuevoPrograma;
    }

    public void setNuevoPrograma(ProgramaDTO nuevoPrograma) {
        this.nuevoPrograma = nuevoPrograma;
    }

    public List<InfoProgramaDTO> getProgramas() {
        return programas;
    }

    public void setProgramas(List<InfoProgramaDTO> programas) {
        this.programas = programas;
    }

    public boolean isMostrarRegistroPrograma() {
        return mostrarRegistroPrograma;
    }

    public void setMostrarRegistroPrograma(boolean mostrarRegistroPrograma) {
        this.mostrarRegistroPrograma = mostrarRegistroPrograma;
    }

}
