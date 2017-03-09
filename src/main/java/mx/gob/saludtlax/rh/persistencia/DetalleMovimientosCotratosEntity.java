package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalle_movimientos_contratos")
public class DetalleMovimientosCotratosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7564092766003862584L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_movimiento")
	private Integer idDetalleMovimiento;
	
	
	@Column(name="id_movimiento_contrato")
	private Integer idMovimientoContrato;
	
	@Column(name="numero_pago")
	private Integer numeroPago;
	
	@Column(name="monto")
	private BigDecimal monto;
	
	@Column(name="abono")
	private BigDecimal abono;
	
	@Column(name="saldo")
	private BigDecimal saldo;
		
	@Column(name="descuento")
	private BigDecimal descuento;
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="id_estatus_detalle")
	private Integer idEstatusDetalle;

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
	public Integer getIdEstatusDetalle() {
		return idEstatusDetalle;
	}
	public void setIdEstatusDetalle(Integer idEstatusDetalle) {
		this.idEstatusDetalle = idEstatusDetalle;
	}
}