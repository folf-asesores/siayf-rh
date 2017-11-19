/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 26/08/2016 05:15:29
 */
public class AltaEmpleadoView {
    private List<InfoPuestoDTO> vacantesDisponibles = new ArrayList<>();
    private DetalleConfiguracionPresupuestoDTO detalleVacante = new DetalleConfiguracionPresupuestoDTO();
    private InfoCandidatoDTO candidato = new InfoCandidatoDTO();
    private AltaEmpleadoDTO altaEmpleado = new AltaEmpleadoDTO();
    private List<SelectItem> listaTiposContratacion;
    private List<SelectItem> listaTiposJornadas;
    private List<SelectItem> listaBancos;
    private List<SelectItem> listaMetodosPago;

    private Integer idTipoContratacion;
    private boolean mostrarCamposFederales;
    private boolean mostrarCamposEventuales;
    private boolean mostrarTablaResultado;
    private boolean mostrarPanelAlta;

    public List<SelectItem> getListaMetodosPago() {
        return listaMetodosPago;
    }

    public void setListaMetodosPago(List<SelectItem> listaMetodosPago) {
        this.listaMetodosPago = listaMetodosPago;
    }

    public boolean isMostrarCamposFederales() {
        return mostrarCamposFederales;
    }

    public void setMostrarCamposFederales(boolean mostrarCamposFederales) {
        this.mostrarCamposFederales = mostrarCamposFederales;
    }

    public boolean isMostrarCamposEventuales() {
        return mostrarCamposEventuales;
    }

    public void setMostrarCamposEventuales(boolean mostrarCamposEventuales) {
        this.mostrarCamposEventuales = mostrarCamposEventuales;
    }

    public boolean isMostrarPanelAlta() {
        return mostrarPanelAlta;
    }

    public void setMostrarPanelAlta(boolean mostrarPanelAlta) {
        this.mostrarPanelAlta = mostrarPanelAlta;
    }

    public List<SelectItem> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(List<SelectItem> listaBancos) {
        this.listaBancos = listaBancos;
    }

    public List<SelectItem> getListaTiposJornadas() {
        return listaTiposJornadas;
    }

    public void setListaTiposJornadas(List<SelectItem> listaTiposJornadas) {
        this.listaTiposJornadas = listaTiposJornadas;
    }

    public AltaEmpleadoDTO getAltaEmpleado() {
        return altaEmpleado;
    }

    public void setAltaEmpleado(AltaEmpleadoDTO altaEmpleado) {
        this.altaEmpleado = altaEmpleado;
    }

    public InfoCandidatoDTO getCandidato() {
        return candidato;
    }

    public void setCandidato(InfoCandidatoDTO candidato) {
        this.candidato = candidato;
    }

    public DetalleConfiguracionPresupuestoDTO getDetalleVacante() {
        return detalleVacante;
    }

    public void setDetalleVacante(
            DetalleConfiguracionPresupuestoDTO detalleVacante) {
        this.detalleVacante = detalleVacante;
    }

    public boolean isMostrarTablaResultado() {
        return mostrarTablaResultado;
    }

    public void setMostrarTablaResultado(boolean mostrarTablaResultado) {
        this.mostrarTablaResultado = mostrarTablaResultado;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public List<SelectItem> getListaTiposContratacion() {
        return listaTiposContratacion;
    }

    public void setListaTiposContratacion(
            List<SelectItem> listaTiposContratacion) {
        this.listaTiposContratacion = listaTiposContratacion;
    }

    public List<InfoPuestoDTO> getVacantesDisponibles() {
        return vacantesDisponibles;
    }

    public void setVacantesDisponibles(
            List<InfoPuestoDTO> vacantesDisponibles) {
        this.vacantesDisponibles = vacantesDisponibles;
    }

}
