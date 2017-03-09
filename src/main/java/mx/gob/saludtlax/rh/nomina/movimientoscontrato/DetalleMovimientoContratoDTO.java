package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DetalleMovimientoContratoDTO implements Serializable {
	private static final long serialVersionUID = 4592515682554651325L;

	private Integer idDetalleMovimiento;
	private Integer idMovimientoContrato;
	private Integer numeroPago;
	private BigDecimal monto;
	private BigDecimal abono;
	private BigDecimal saldo;
	private BigDecimal descuento;
	private Date fechaRegistro;

	public Integer getIdDetalleMovimiento() {
		return idDetalleMovimiento;
	}
	public void setIdDetalleMovimiento(Integer idDetalleMovimiento) {
		this.idDetalleMovimiento = idDetalleMovimiento;
	}
	public Integer getIdMovimientoContrato() {
		return idMovimientoContrato;
	}
	public void setIdMovimientoContrato(Integer idMovimientoContrato) {
		this.idMovimientoContrato = idMovimientoContrato;
	}
	public Integer getNumeroPago() {
		return numeroPago;
	}
	public void setNumeroPago(Integer numeroPago) {
		this.numeroPago = numeroPago;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public BigDecimal getAbono() {
		return abono;
	}
	public void setAbono(BigDecimal abono) {
		this.abono = abono;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}