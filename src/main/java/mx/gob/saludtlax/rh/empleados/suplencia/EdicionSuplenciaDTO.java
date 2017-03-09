package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.Date;

public class EdicionSuplenciaDTO {
	private Integer idDetalleSuplencia;
	private Integer idTabulador;
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal cantidadDiaria;
	private int dias;
	private BigDecimal total;
	private Integer idJornada;
	private String observaciones;

	public Integer getIdTabulador() {
		return idTabulador;
	}

	public void setIdTabulador(Integer idTabulador) {
		this.idTabulador = idTabulador;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getIdDetalleSuplencia() {
		return idDetalleSuplencia;
	}

	public void setIdDetalleSuplencia(Integer idDetalleSuplencia) {
		this.idDetalleSuplencia = idDetalleSuplencia;
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

	public BigDecimal getCantidadDiaria() {
		return cantidadDiaria;
	}

	public void setCantidadDiaria(BigDecimal cantidadDiaria) {
		this.cantidadDiaria = cantidadDiaria;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

}
