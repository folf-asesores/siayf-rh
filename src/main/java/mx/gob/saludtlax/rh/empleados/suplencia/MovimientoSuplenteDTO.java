/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 11/01/2017 11:11:45
 */
public class MovimientoSuplenteDTO {
	private String nombreSuplente;
	private String usuario;
	private int totalDias;
	private Date fechaMovimiento;
	// Alta movimiento
	private String movimiento;
	private Integer idSuplente;
	private Date fechaInicio;
	private Date fechaFin;
	private int ejercicioFiscalPeriodo;
	private int idUsuario;
	private String observaciones;

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getNombreSuplente() {
		return nombreSuplente;
	}

	public void setNombreSuplente(String nombreSuplente) {
		this.nombreSuplente = nombreSuplente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Integer getIdSuplente() {
		return idSuplente;
	}

	public void setIdSuplente(Integer idSuplente) {
		this.idSuplente = idSuplente;
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

	public int getEjercicioFiscalPeriodo() {
		return ejercicioFiscalPeriodo;
	}

	public void setEjercicioFiscalPeriodo(int ejercicioFiscalPeriodo) {
		this.ejercicioFiscalPeriodo = ejercicioFiscalPeriodo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
