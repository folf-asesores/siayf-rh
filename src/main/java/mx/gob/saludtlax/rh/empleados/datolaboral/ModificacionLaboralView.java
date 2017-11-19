/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.InfoSueldoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestoEmpleadoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/12/2016 02:28:05
 */
public class ModificacionLaboralView {
    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();
    private FiltroDTO filtro = new FiltroDTO();
    private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
    private DatoLaboralDTO datoLaboral = new DatoLaboralDTO();
    private InfoSueldoDTO salario = new InfoSueldoDTO();
    private boolean mostrarLaboral;
    private boolean mostrarPrograma;
    private boolean mostrarSalarioFederal;
    private boolean mostrarSalarioEstatal;
    private Integer idUsuarioLogeado;
    private Integer idPuesto;
    private Integer idPrograma;

    private List<SelectItem> listaProyectos = new ArrayList<>();
    private List<SelectItem> listaDependencias = new ArrayList<>();
    private List<SelectItem> listaUnidadesResponsables = new ArrayList<>();
    private List<SelectItem> listaPuestos = new ArrayList<>();
    private List<SelectItem> listaFuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaSubfuentesFinanciamiento = new ArrayList<>();
    private List<SelectItem> listaTiposRecursos = new ArrayList<>();
    private List<SelectItem> listaTiposNombramiento = new ArrayList<>();
    private List<SelectItem> listaProgramas = new ArrayList<>();

    public List<SelectItem> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<SelectItem> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public boolean isMostrarPrograma() {
        return mostrarPrograma;
    }

    public void setMostrarPrograma(boolean mostrarPrograma) {
        this.mostrarPrograma = mostrarPrograma;
    }

    public InfoSueldoDTO getSalario() {
        return salario;
    }

    public void setSalario(InfoSueldoDTO salario) {
        this.salario = salario;
    }

    public boolean isMostrarSalarioFederal() {
        return mostrarSalarioFederal;
    }

    public void setMostrarSalarioFederal(boolean mostrarSalarioFederal) {
        this.mostrarSalarioFederal = mostrarSalarioFederal;
    }

    public boolean isMostrarSalarioEstatal() {
        return mostrarSalarioEstatal;
    }

    public void setMostrarSalarioEstatal(boolean mostrarSalarioEstatal) {
        this.mostrarSalarioEstatal = mostrarSalarioEstatal;
    }

    public Integer getIdUsuarioLogeado() {
        return idUsuarioLogeado;
    }

    public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
        this.idUsuarioLogeado = idUsuarioLogeado;
    }

    public DatoLaboralDTO getDatoLaboral() {
        return datoLaboral;
    }

    public void setDatoLaboral(DatoLaboralDTO datoLaboral) {
        this.datoLaboral = datoLaboral;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public FiltroDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    public PuestoEmpleadoDTO getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoEmpleadoDTO puesto) {
        this.puesto = puesto;
    }

    public boolean isMostrarLaboral() {
        return mostrarLaboral;
    }

    public void setMostrarLaboral(boolean mostrarLaboral) {
        this.mostrarLaboral = mostrarLaboral;
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

    public List<SelectItem> getListaTiposNombramiento() {
        return listaTiposNombramiento;
    }

    public void setListaTiposNombramiento(
            List<SelectItem> listaTiposNombramiento) {
        this.listaTiposNombramiento = listaTiposNombramiento;
    }

}
