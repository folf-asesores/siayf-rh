/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 06/11/2016 21:13:00
 */
public class AltaSuplenciaDTO {
	private Integer idSuplenteAutorizado;
	private Integer idEmpleado;
	private Integer idCausaSuplencia;
	private Date fechaInicio;
	private Date fechaFin;
	private int dias;
	private int numeroQuincena;
	private int ejercicioFiscal;
	private BigDecimal cantidadDiaria;
	private BigDecimal total;
	private Integer idMovimiento;
	private Integer idUsuarioLogeado;
	private Integer idJornada;
	private Integer idTabulador;
	private String observaciones;

	public int getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(int ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

	public int getNumeroQuincena() {
		return numeroQuincena;
	}

	public void setNumeroQuincena(int numeroQuincena) {
		this.numeroQuincena = numeroQuincena;
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

	public Integer getIdCausaSuplencia() {
		return idCausaSuplencia;
	}

	public void setIdCausaSuplencia(Integer idCausaSuplencia) {
		this.idCausaSuplencia = idCausaSuplencia;
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

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

}
