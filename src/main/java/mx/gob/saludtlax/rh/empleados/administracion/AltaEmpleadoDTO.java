/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;

import mx.gob.saludtlax.rh.empleados.nombramientos.NombramientoDTO;
import mx.gob.saludtlax.rh.persistencia.ContratoEmpleadoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 14:53:28
 */
public class AltaEmpleadoDTO {
	private Integer idVacante;
	private Date fechaIngreso;
	private String numeroCuenta;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaInicioLabores;
	private Integer idUsuario;
	private Integer numeroEmpleado;
	private Integer numeroLaboral;
	private Integer idBanco;
	private Integer idMetodoPago;
	private Integer idTipoJornada;
	private NombramientoDTO nombramiento = new NombramientoDTO();
	private ContratoEmpleadoDTO contrato = new ContratoEmpleadoDTO();

	public Integer getIdTipoJornada() {
		return idTipoJornada;
	}

	public void setIdTipoJornada(Integer idTipoJornada) {
		this.idTipoJornada = idTipoJornada;
	}

	public Integer getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(Integer idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public Integer getNumeroLaboral() {
		return numeroLaboral;
	}

	public void setNumeroLaboral(Integer numeroLaboral) {
		this.numeroLaboral = numeroLaboral;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public Date getFechaInicioLabores() {
		return fechaInicioLabores;
	}

	public void setFechaInicioLabores(Date fechaInicioLabores) {
		this.fechaInicioLabores = fechaInicioLabores;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ContratoEmpleadoDTO getContrato() {
		return contrato;
	}

	public void setContrato(ContratoEmpleadoDTO contrato) {
		this.contrato = contrato;
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

	public NombramientoDTO getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(NombramientoDTO nombramiento) {
		this.nombramiento = nombramiento;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

}
