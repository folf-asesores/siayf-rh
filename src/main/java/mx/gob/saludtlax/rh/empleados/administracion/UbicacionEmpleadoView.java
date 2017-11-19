/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.puestosautorizados.PuestoEmpleadoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/09/2016 01:43:33
 */
public class UbicacionEmpleadoView {
    private List<SelectItem> listaAdscripciones;
    private List<SelectItem> listaSubadscripcion;
    private List<SelectItem> listaServicio;
    private List<SelectItem> listaFuncion;
    private List<SelectItem> listaClues;
    private List<SelectItem> listaFiltros;
    private List<SelectItem> listaDependencias = new ArrayList<>();
    private List<SelectItem> listaUnidadesResponsables = new ArrayList<>();
    private List<SelectItem> listaCentrosResponsabilidad;

    private UbicacionEmpleadoDTO ubicacion = new UbicacionEmpleadoDTO();
    private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
    private List<InfoPuestoDTO> puestosEmpleados = new ArrayList<>();
    private Integer idFiltro;
    private Integer idContexto;
    private Integer idDependencia;
    private Integer idUsuario;
    private String criterio;

    private boolean mostrarUnidades;
    private boolean mostrarCriterio;
    private boolean mostrarBusqueda;
    private boolean mostrarDetalle;
    private boolean bloquearAdscripcion;
    private boolean bloquearSubadscripcion;
    private boolean bloquearServicio;
    private boolean bloquearFuncion;
    private boolean bloquearClues;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<SelectItem> getListaCentrosResponsabilidad() {
        return listaCentrosResponsabilidad;
    }

    public void setListaCentrosResponsabilidad(
            List<SelectItem> listaCentrosResponsabilidad) {
        this.listaCentrosResponsabilidad = listaCentrosResponsabilidad;
    }

    public List<SelectItem> getListaClues() {
        return listaClues;
    }

    public void setListaClues(List<SelectItem> listaClues) {
        this.listaClues = listaClues;
    }

    public List<SelectItem> getListaSubadscripcion() {
        return listaSubadscripcion;
    }

    public void setListaSubadscripcion(List<SelectItem> listaSubadscripcion) {
        this.listaSubadscripcion = listaSubadscripcion;
    }

    public List<SelectItem> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<SelectItem> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public List<SelectItem> getListaFuncion() {
        return listaFuncion;
    }

    public void setListaFuncion(List<SelectItem> listaFuncion) {
        this.listaFuncion = listaFuncion;
    }

    public boolean isBloquearAdscripcion() {
        return bloquearAdscripcion;
    }

    public void setBloquearAdscripcion(boolean bloquearAdscripcion) {
        this.bloquearAdscripcion = bloquearAdscripcion;
    }

    public boolean isBloquearSubadscripcion() {
        return bloquearSubadscripcion;
    }

    public void setBloquearSubadscripcion(boolean bloquearSubadscripcion) {
        this.bloquearSubadscripcion = bloquearSubadscripcion;
    }

    public boolean isBloquearServicio() {
        return bloquearServicio;
    }

    public void setBloquearServicio(boolean bloquearServicio) {
        this.bloquearServicio = bloquearServicio;
    }

    public boolean isBloquearClues() {
        return bloquearClues;
    }

    public void setBloquearClues(boolean bloquearClues) {
        this.bloquearClues = bloquearClues;
    }

    public boolean isBloquearFuncion() {
        return bloquearFuncion;
    }

    public void setBloquearFuncion(boolean bloquearFuncion) {
        this.bloquearFuncion = bloquearFuncion;
    }

    public UbicacionEmpleadoDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEmpleadoDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isMostrarDetalle() {
        return mostrarDetalle;
    }

    public void setMostrarDetalle(boolean mostrarDetalle) {
        this.mostrarDetalle = mostrarDetalle;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public PuestoEmpleadoDTO getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoEmpleadoDTO puesto) {
        this.puesto = puesto;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isMostrarCriterio() {
        return mostrarCriterio;
    }

    public void setMostrarCriterio(boolean mostrarCriterio) {
        this.mostrarCriterio = mostrarCriterio;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public List<SelectItem> getListaDependencias() {
        return listaDependencias;
    }

    public void setListaDependencias(List<SelectItem> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public boolean isMostrarUnidades() {
        return mostrarUnidades;
    }

    public void setMostrarUnidades(boolean mostrarUnidades) {
        this.mostrarUnidades = mostrarUnidades;
    }

    public Integer getIdContexto() {
        return idContexto;
    }

    public void setIdContexto(Integer idContexto) {
        this.idContexto = idContexto;
    }

    public List<SelectItem> getListaUnidadesResponsables() {
        return listaUnidadesResponsables;
    }

    public void setListaUnidadesResponsables(
            List<SelectItem> listaUnidadesResponsables) {
        this.listaUnidadesResponsables = listaUnidadesResponsables;
    }

    public List<InfoPuestoDTO> getPuestosEmpleados() {
        return puestosEmpleados;
    }

    public Integer getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(Integer idFiltro) {
        this.idFiltro = idFiltro;
    }

    public void setPuestosEmpleados(List<InfoPuestoDTO> puestosEmpleados) {
        this.puestosEmpleados = puestosEmpleados;
    }

    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public List<SelectItem> getListaUbicaciones() {
        return listaFiltros;
    }

    public void setListaUbicaciones(List<SelectItem> listaUbicaciones) {
        listaFiltros = listaUbicaciones;
    }

    public List<SelectItem> getListaAdscripciones() {
        return listaAdscripciones;
    }

    public void setListaAdscripciones(List<SelectItem> listaAdscripciones) {
        this.listaAdscripciones = listaAdscripciones;
    }

}
