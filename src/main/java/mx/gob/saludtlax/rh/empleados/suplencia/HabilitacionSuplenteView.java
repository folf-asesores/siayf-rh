/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 *
 */
public class HabilitacionSuplenteView {

    private List<SelectItem> listaTiposBusqueda;
    private List<SelectItem> tiposCandidatos;
    private List<SelectItem> listaTiposSexo;
    private List<SelectItem> listaEstados;
    private List<SelectItem> listaMunicipios;
    private List<SelectItem> listaAsentamientos;
    private List<SelectItem> listaBancos;
    private List<SelectItem> listaProyectos;
    private List<SelectItem> listaDependencias;
    private List<SelectItem> listaUnidadesResponsables = new ArrayList<>();
    private List<SelectItem> listaCentrosResponsabilidades;
    private List<SelectItem> listaPuestos;
    private List<SuplenteDTO> suplentes = new ArrayList<>();
    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();
    private RegistroSuplenteDTO registroSuplente = new RegistroSuplenteDTO();
    private EmpleadoDetalladoDTO empleado = new EmpleadoDetalladoDTO();
    private FiltroSuplenciaDTO filtro = new FiltroSuplenciaDTO();

    private boolean mostrarBusqueda;
    private boolean mostrarHabilitacionSuplente;
    private boolean mostrarDetalleEmpleado;
    private boolean mostrarDetalleLaboral;
    private boolean mostrarAltaSuplente;
    private boolean mostrarEmpleado;
    private boolean mostrarCriterio;
    private boolean mostrarActivacion;
    private String criterio;
    private Integer idTipoCandidato;
    private Integer idSuplente;
    private Integer idUsuarioLogeado;

    public Integer getIdUsuarioLogeado() {
        return idUsuarioLogeado;
    }

    public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
        this.idUsuarioLogeado = idUsuarioLogeado;
    }

    public Integer getIdSuplente() {
        return idSuplente;
    }

    public void setIdSuplente(Integer idSuplente) {
        this.idSuplente = idSuplente;
    }

    public boolean isMostrarActivacion() {
        return mostrarActivacion;
    }

    public void setMostrarActivacion(boolean mostrarActivacion) {
        this.mostrarActivacion = mostrarActivacion;
    }

    public boolean isMostrarDetalleLaboral() {
        return mostrarDetalleLaboral;
    }

    public void setMostrarDetalleLaboral(boolean mostrarDetalleLaboral) {
        this.mostrarDetalleLaboral = mostrarDetalleLaboral;
    }

    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public boolean isMostrarCriterio() {
        return mostrarCriterio;
    }

    public void setMostrarCriterio(boolean mostrarCriterio) {
        this.mostrarCriterio = mostrarCriterio;
    }

    public List<SelectItem> getListaTiposBusqueda() {
        return listaTiposBusqueda;
    }

    public void setListaTiposBusqueda(List<SelectItem> listaTiposBusqueda) {
        this.listaTiposBusqueda = listaTiposBusqueda;
    }

    public List<SelectItem> getListaCentrosResponsabilidades() {
        return listaCentrosResponsabilidades;
    }

    public void setListaCentrosResponsabilidades(
            List<SelectItem> listaCentrosResponsabilidades) {
        this.listaCentrosResponsabilidades = listaCentrosResponsabilidades;
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

    public List<SelectItem> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(List<SelectItem> listaBancos) {
        this.listaBancos = listaBancos;
    }

    public boolean isMostrarEmpleado() {
        return mostrarEmpleado;
    }

    public void setMostrarEmpleado(boolean mostrarEmpleado) {
        this.mostrarEmpleado = mostrarEmpleado;
    }

    public FiltroSuplenciaDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroSuplenciaDTO filtro) {
        this.filtro = filtro;
    }

    public List<SelectItem> getListaTiposSexo() {
        return listaTiposSexo;
    }

    public void setListaTiposSexo(List<SelectItem> listaTiposSexo) {
        this.listaTiposSexo = listaTiposSexo;
    }

    public List<SelectItem> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<SelectItem> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<SelectItem> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<SelectItem> getListaAsentamientos() {
        return listaAsentamientos;
    }

    public void setListaAsentamientos(List<SelectItem> listaAsentamientos) {
        this.listaAsentamientos = listaAsentamientos;
    }

    public boolean isMostrarHabilitacionSuplente() {
        return mostrarHabilitacionSuplente;
    }

    public void setMostrarHabilitacionSuplente(
            boolean mostrarHabilitacionSuplente) {
        this.mostrarHabilitacionSuplente = mostrarHabilitacionSuplente;
    }

    public boolean isMostrarAltaSuplente() {
        return mostrarAltaSuplente;
    }

    public void setMostrarAltaSuplente(boolean mostrarAltaSuplente) {
        this.mostrarAltaSuplente = mostrarAltaSuplente;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public EmpleadoDetalladoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDetalladoDTO empleado) {
        this.empleado = empleado;
    }

    public boolean isMostrarDetalleEmpleado() {
        return mostrarDetalleEmpleado;
    }

    public void setMostrarDetalleEmpleado(boolean mostrarDetalleEmpleado) {
        this.mostrarDetalleEmpleado = mostrarDetalleEmpleado;
    }

    public Integer getIdTipoCandidato() {
        return idTipoCandidato;
    }

    public void setIdTipoCandidato(Integer idTipoCandidato) {
        this.idTipoCandidato = idTipoCandidato;
    }

    public List<SelectItem> getTiposCandidatos() {
        return tiposCandidatos;
    }

    public void setTiposCandidatos(List<SelectItem> tiposCandidatos) {
        this.tiposCandidatos = tiposCandidatos;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<SuplenteDTO> getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(List<SuplenteDTO> suplentes) {
        this.suplentes = suplentes;
    }

    public RegistroSuplenteDTO getRegistroSuplente() {
        return registroSuplente;
    }

    public void setRegistroSuplente(RegistroSuplenteDTO registroSuplente) {
        this.registroSuplente = registroSuplente;
    }

}
