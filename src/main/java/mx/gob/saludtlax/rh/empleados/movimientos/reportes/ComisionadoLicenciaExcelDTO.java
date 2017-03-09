/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.sql.Time;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class ComisionadoLicenciaExcelDTO {
	
	private Integer tipoMovimiento;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreEmpleado;
	private String tipoPlaza;
	private String numeroHoras;
	private String funcionesEspecificas;
	private String clavePago;
	private Date fechaInicio;
	private Date fechaConclusion;
	private String centroTrabajoOrigen;
	private String centroTrabajoDestino;
	
	
	
	public Integer getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getTipoPlaza() {
		return tipoPlaza;
	}
	public void setTipoPlaza(String tipoPlaza) {
		this.tipoPlaza = tipoPlaza;
	}
	public String getNumeroHoras() {
		return numeroHoras;
	}
	public void setNumeroHoras(String numeroHoras) {
		this.numeroHoras = numeroHoras;
	}
	public String getFuncionesEspecificas() {
		return funcionesEspecificas;
	}
	public void setFuncionesEspecificas(String funcionesEspecificas) {
		this.funcionesEspecificas = funcionesEspecificas;
	}
	public String getClavePago() {
		return clavePago;
	}
	public void setClavePago(String clavePago) {
		this.clavePago = clavePago;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaConclusion() {
		return fechaConclusion;
	}
	public void setFechaConclusion(Date fechaConclusion) {
		this.fechaConclusion = fechaConclusion;
	}
	public String getCentroTrabajoOrigen() {
		return centroTrabajoOrigen;
	}
	public void setCentroTrabajoOrigen(String centroTrabajoOrigen) {
		this.centroTrabajoOrigen = centroTrabajoOrigen;
	}
	public String getCentroTrabajoDestino() {
		return centroTrabajoDestino;
	}
	public void setCentroTrabajoDestino(String centroTrabajoDestino) {
		this.centroTrabajoDestino = centroTrabajoDestino;
	}

	

}
