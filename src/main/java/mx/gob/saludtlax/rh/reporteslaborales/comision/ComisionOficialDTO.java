package mx.gob.saludtlax.rh.reporteslaborales.comision;

import java.io.Serializable;

/**
 * @author Daniela Hernandez
 *
 */

public class ComisionOficialDTO implements Serializable {

	
	
	private Integer idMovimiento;
	private String nombreCompleto ;
	private String rfc;
	private String curp;
	private String lugar;
	private String adscripcion;
	private String fecha;
	private String directorAdministracion;
	
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}
	public String getDirectorAdministracion() {
		return directorAdministracion;
	}
	public void setDirectorAdministracion(String directorAdministracion) {
		this.directorAdministracion = directorAdministracion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
