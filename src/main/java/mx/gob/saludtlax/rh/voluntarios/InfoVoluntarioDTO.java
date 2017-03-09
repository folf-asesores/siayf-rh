/**
 * 
 */
package mx.gob.saludtlax.rh.voluntarios;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 24/11/2016 18:48:28
 */
public class InfoVoluntarioDTO {
	private Integer idPuesto;
	private String curp;
	private String voluntario;
	private Date fechaIngreso;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer idPrograma;
	private String programa;
	private Integer idAdscripcion;
	private String adscripcion;
	private Integer idSubadscripcion;
	private String subadscripcion;
	private Integer idServicio;
	private String servicio;
	private Integer idFuncion;
	private String funcion;
	private BigDecimal sueldo;
	private String numeroCuenta;

	public String getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(String subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(String voluntario) {
		this.voluntario = voluntario;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public Integer getIdSubadscripcion() {
		return idSubadscripcion;
	}

	public void setIdSubadscripcion(Integer idSubadscripcion) {
		this.idSubadscripcion = idSubadscripcion;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

}
