
package mx.gob.saludtlax.rh.reporteslaborales.cambio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Daniela
 *
 */

public class CambioAdscripcionView implements Serializable {

    private static final long serialVersionUID = 6898060539285305143L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<CambioAdscripcionDetalleDTO> cambioDetalle = new ArrayList<>();
    private List<CambioAdscripcionDTO> cambioAdscripcion = new ArrayList<>();
    private CambioAdscripcionDTO cambioAdscripcionDTO = new CambioAdscripcionDTO();
    private Integer idMovimiento;
    private String criterio;
    private byte[] bytes = null;
    private String comision;

    private boolean mostrarCambio = false;
    private boolean mostrarPrincipal = true;
    private boolean mostrarOpcionDescarga = false;
    private boolean mostrarEdicion = false;

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<CambioAdscripcionDetalleDTO> getCambioDetalle() {
        return cambioDetalle;
    }

    public void setCambioDetalle(List<CambioAdscripcionDetalleDTO> cambioDetalle) {
        this.cambioDetalle = cambioDetalle;
    }

    public List<CambioAdscripcionDTO> getCambioAdscripcion() {
        return cambioAdscripcion;
    }

    public void setCambioAdscripcion(List<CambioAdscripcionDTO> cambioAdscripcion) {
        this.cambioAdscripcion = cambioAdscripcion;
    }

    public CambioAdscripcionDTO getCambioAdscripcionDTO() {
        return cambioAdscripcionDTO;
    }

    public void setCambioAdscripcionDTO(CambioAdscripcionDTO cambioAdscripcionDTO) {
        this.cambioAdscripcionDTO = cambioAdscripcionDTO;
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

    public boolean isMostrarCambio() {
        return mostrarCambio;
    }

    public void setMostrarCambio(boolean mostrarCambio) {
        this.mostrarCambio = mostrarCambio;
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
