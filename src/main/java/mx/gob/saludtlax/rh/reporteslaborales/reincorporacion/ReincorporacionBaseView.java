
package mx.gob.saludtlax.rh.reporteslaborales.reincorporacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class ReincorporacionBaseView implements Serializable {

    private static final long serialVersionUID = 1204000306622707738L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<ReincorporacionBaseDTO> reincorporacionBaseLista = new ArrayList<>();
    private List<ReincorporacionBaseDetalleDTO> reincorporacionBaseDetalleDTO = new ArrayList<>();
    private ReincorporacionBaseDTO reincorporacionBaseDTO = new ReincorporacionBaseDTO();
    private Integer idMovimiento;
    private String criterio;
    private byte[] bytes = null;
    private String comision;

    private boolean mostrarReincorporacion = false;
    private boolean mostrarPrincipal = true;
    private boolean mostrarOpcionDescarga = false;
    private boolean mostrarEdicion = false;

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<ReincorporacionBaseDTO> getReincorporacionBaseLista() {
        return reincorporacionBaseLista;
    }

    public void setReincorporacionBaseLista(List<ReincorporacionBaseDTO> reincorporacionBaseLista) {
        this.reincorporacionBaseLista = reincorporacionBaseLista;
    }

    public List<ReincorporacionBaseDetalleDTO> getReincorporacionBaseDetalleDTO() {
        return reincorporacionBaseDetalleDTO;
    }

    public void setReincorporacionBaseDetalleDTO(List<ReincorporacionBaseDetalleDTO> reincorporacionBaseDetalleDTO) {
        this.reincorporacionBaseDetalleDTO = reincorporacionBaseDetalleDTO;
    }

    public ReincorporacionBaseDTO getReincorporacionBaseDTO() {
        return reincorporacionBaseDTO;
    }

    public void setReincorporacionBaseDTO(ReincorporacionBaseDTO reincorporacionBaseDTO) {
        this.reincorporacionBaseDTO = reincorporacionBaseDTO;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public boolean isMostrarReincorporacion() {
        return mostrarReincorporacion;
    }

    public void setMostrarReincorporacion(boolean mostrarReincorporacion) {
        this.mostrarReincorporacion = mostrarReincorporacion;
    }

    public boolean isMostrarPrincipal() {
        return mostrarPrincipal;
    }

    public void setMostrarPrincipal(boolean mostrarPrincipal) {
        this.mostrarPrincipal = mostrarPrincipal;
    }

    public boolean isMostrarOpcionDescarga() {
        return mostrarOpcionDescarga;
    }

    public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
        this.mostrarOpcionDescarga = mostrarOpcionDescarga;
    }

    public boolean isMostrarEdicion() {
        return mostrarEdicion;
    }

    public void setMostrarEdicion(boolean mostrarEdicion) {
        this.mostrarEdicion = mostrarEdicion;
    }

}