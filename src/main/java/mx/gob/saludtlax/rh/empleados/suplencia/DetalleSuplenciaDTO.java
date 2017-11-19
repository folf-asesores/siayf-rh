/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.saludtlax.rh.empleados.movimientos.DetalleMovimientoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/10/2016 15:14:17
 */
public class DetalleSuplenciaDTO {
    private String conDescuento;
    private String observaciones;
    private Integer idDetalleSuplencia;
    private Integer idSuplenteAutorizado;
    private Integer idEmpleado;
    private Integer idEmpleadoSuplente;
    private String nombreEmpleado;
    private Date fechaInicio;
    private Date fechaFin;
    private int dias;
    private BigDecimal cantidadDiaria;
    private BigDecimal total;
    private Date fechaCierre;
    private int numeroQuincena;
    private String tipoSuplencia;
    private Integer idTipoSuplencia;
    private String nombreSuplente;
    private Integer idMovimiento;
    private String estatus;
    private Integer idQuincena;
    private Integer idJornada;
    private String jornada;
    private Integer idTabulador;
    private DetalleMovimientoDTO detalleMovimiento;

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getConDescuento() {
        return conDescuento;
    }

    public void setConDescuento(String conDescuento) {
        this.conDescuento = conDescuento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdEmpleadoSuplente() {
        return idEmpleadoSuplente;
    }

    public void setIdEmpleadoSuplente(Integer idEmpleadoSuplente) {
        this.idEmpleadoSuplente = idEmpleadoSuplente;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdTipoSuplencia() {
        return idTipoSuplencia;
    }

    public void setIdTipoSuplencia(Integer idTipoSuplencia) {
        this.idTipoSuplencia = idTipoSuplencia;
    }

    public DetalleMovimientoDTO getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(DetalleMovimientoDTO detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getIdDetalleSuplencia() {
        return idDetalleSuplencia;
    }

    public void setIdDetalleSuplencia(Integer idDetalleSuplencia) {
        this.idDetalleSuplencia = idDetalleSuplencia;
    }

    public String getNombreSuplente() {
        return nombreSuplente;
    }

    public void setNombreSuplente(String nombreSuplente) {
        this.nombreSuplente = nombreSuplente;
    }

    public String getTipoSuplencia() {
        return tipoSuplencia;
    }

    public void setTipoSuplencia(String tipoSuplencia) {
        this.tipoSuplencia = tipoSuplencia;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Integer getIdSuplenteAutorizado() {
        return idSuplenteAutorizado;
    }

    public void setIdSuplenteAutorizado(Integer idSuplenteAutorizado) {
        this.idSuplenteAutorizado = idSuplenteAutorizado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public BigDecimal getCantidadDiaria() {
        return cantidadDiaria;
    }

    public void setCantidadDiaria(BigDecimal cantidadDiaria) {
        this.cantidadDiaria = cantidadDiaria;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(int numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

}
