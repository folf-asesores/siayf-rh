
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Daniela Hernández
 *
 */

public class ReservacionView implements Serializable {

    private static final long serialVersionUID = 1204000306622707738L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<ReservacionDetalleDTO> reservacionDetalleDTO = new ArrayList<>();
    private List<ReservacionDTO> reservacionListaDTO = new ArrayList<>();
    private ReservacionDTO reservacionDTO = new ReservacionDTO();
    private Integer idMovimiento;
    private String criterio;
    private byte[] bytes = null;
    private String comision;

    private boolean mostrarReservacion = false;
    private boolean mostrarPrincipal = true;
    private boolean mostrarOpcionDescarga = false;
    private boolean mostrarEdicion = false;

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<ReservacionDTO> getReservacionListaDTO() {
        return reservacionListaDTO;
    }

    public void setReservacionListaDTO(
            List<ReservacionDTO> reservacionListaDTO) {
        this.reservacionListaDTO = reservacionListaDTO;
    }

    public ReservacionDTO getReservacionDTO() {
        return reservacionDTO;
    }

    public void setReservacionDTO(ReservacionDTO reservacionDTO) {
        this.reservacionDTO = reservacionDTO;
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

    public boolean isMostrarReservacion() {
        return mostrarReservacion;
    }

    public void setMostrarReservacion(boolean mostrarReservacion) {
        this.mostrarReservacion = mostrarReservacion;
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

    public List<ReservacionDetalleDTO> getReservacionDetalleDTO() {
        return reservacionDetalleDTO;
    }

    public void setReservacionDetalleDTO(
            List<ReservacionDetalleDTO> reservacionDetalleDTO) {
        this.reservacionDetalleDTO = reservacionDetalleDTO;
    }

    public boolean isMostrarEdicion() {
        return mostrarEdicion;
    }

    public void setMostrarEdicion(boolean mostrarEdicion) {
        this.mostrarEdicion = mostrarEdicion;
    }

}
