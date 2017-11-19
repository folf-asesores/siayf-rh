/*
 *
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class FormatoFiliacionView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1252782281030336529L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();

    private FormatoFiliacionDTO formatoFiliacionDTO = new FormatoFiliacionDTO();

    private List<SelectItem> listaColorPiel = new ArrayList<>();
    private List<SelectItem> listaCabello = new ArrayList<>();
    private List<SelectItem> listaFrente = new ArrayList<>();
    private List<SelectItem> listaCeja = new ArrayList<>();
    private List<SelectItem> listaOjos = new ArrayList<>();
    private List<SelectItem> listaNariz = new ArrayList<>();
    private List<SelectItem> listaBoca = new ArrayList<>();

    private Integer idEmpleado;
    private Integer idFormato;

    private String idColorPiel;
    private String idCabello;
    private String idFrente;
    private String idCeja;
    private String idOjos;
    private String idNariz;
    private String idBoca;
    private String criterio;
    private String nombreEmpleado;

    private boolean principal = true;
    private boolean formulario = false;
    private boolean habilitarDescarga = false;
    private boolean mostrarReporteNuevaVentana = false;
    private boolean mostrarExitoReporte = false;

    public FormatoFiliacionDTO getFormatoFiliacionDTO() {
        return formatoFiliacionDTO;
    }

    public void setFormatoFiliacionDTO(
            FormatoFiliacionDTO formatoFiliacionDTO) {
        this.formatoFiliacionDTO = formatoFiliacionDTO;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdColorPiel() {
        return idColorPiel;
    }

    public void setIdColorPiel(String idColorPiel) {
        this.idColorPiel = idColorPiel;
    }

    public String getIdFrente() {
        return idFrente;
    }

    public void setIdFrente(String idFrente) {
        this.idFrente = idFrente;
    }

    public String getIdCeja() {
        return idCeja;
    }

    public void setIdCeja(String idCeja) {
        this.idCeja = idCeja;
    }

    public String getIdOjos() {
        return idOjos;
    }

    public void setIdOjos(String idOjos) {
        this.idOjos = idOjos;
    }

    public String getIdNariz() {
        return idNariz;
    }

    public void setIdNariz(String idNariz) {
        this.idNariz = idNariz;
    }

    public String getIdBoca() {
        return idBoca;
    }

    public void setIdBoca(String idBoca) {
        this.idBoca = idBoca;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isFormulario() {
        return formulario;
    }

    public void setFormulario(boolean formulario) {
        this.formulario = formulario;
    }

    public List<SelectItem> getListaColorPiel() {
        return listaColorPiel;
    }

    public void setListaColorPiel(List<SelectItem> listaColorPiel) {
        this.listaColorPiel = listaColorPiel;
    }

    public List<SelectItem> getListaCabello() {
        return listaCabello;
    }

    public void setListaCabello(List<SelectItem> listaCabello) {
        this.listaCabello = listaCabello;
    }

    public List<SelectItem> getListaFrente() {
        return listaFrente;
    }

    public void setListaFrente(List<SelectItem> listaFrente) {
        this.listaFrente = listaFrente;
    }

    public List<SelectItem> getListaCeja() {
        return listaCeja;
    }

    public void setListaCeja(List<SelectItem> listaCeja) {
        this.listaCeja = listaCeja;
    }

    public List<SelectItem> getListaOjos() {
        return listaOjos;
    }

    public void setListaOjos(List<SelectItem> listaOjos) {
        this.listaOjos = listaOjos;
    }

    public List<SelectItem> getListaNariz() {
        return listaNariz;
    }

    public void setListaNariz(List<SelectItem> listaNariz) {
        this.listaNariz = listaNariz;
    }

    public List<SelectItem> getListaBoca() {
        return listaBoca;
    }

    public void setListaBoca(List<SelectItem> listaBoca) {
        this.listaBoca = listaBoca;
    }

    public String getIdCabello() {
        return idCabello;
    }

    public void setIdCabello(String idCabello) {
        this.idCabello = idCabello;
    }

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * @return the mostrarReporteNuevaVentana
     */
    public boolean isMostrarReporteNuevaVentana() {
        return mostrarReporteNuevaVentana;
    }

    /**
     * @param mostrarReporteNuevaVentana
     *            the mostrarReporteNuevaVentana to set
     */
    public void setMostrarReporteNuevaVentana(
            boolean mostrarReporteNuevaVentana) {
        this.mostrarReporteNuevaVentana = mostrarReporteNuevaVentana;
    }

    /**
     * @return the idFormato
     */
    public Integer getIdFormato() {
        return idFormato;
    }

    /**
     * @param idFormato
     *            the idFormato to set
     */
    public void setIdFormato(Integer idFormato) {
        this.idFormato = idFormato;
    }

    /**
     * @return the mostrarExitoReporte
     */
    public boolean isMostrarExitoReporte() {
        return mostrarExitoReporte;
    }

    /**
     * @param mostrarExitoReporte
     *            the mostrarExitoReporte to set
     */
    public void setMostrarExitoReporte(boolean mostrarExitoReporte) {
        this.mostrarExitoReporte = mostrarExitoReporte;
    }

    public boolean isHabilitarDescarga() {
        return habilitarDescarga;
    }

    public void setHabilitarDescarga(boolean habilitarDescarga) {
        this.habilitarDescarga = habilitarDescarga;
    }

}
