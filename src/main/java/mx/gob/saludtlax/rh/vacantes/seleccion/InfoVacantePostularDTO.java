/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;

/**
 * @author eduardo
 *
 */
public class InfoVacantePostularDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5720891190517644945L;

	private Integer idEmpleadoAspirante;

	private String rfc;

	private String curp;

	private String nombreCompleto;

	private String direccionCompleta;

	private String status;

	private String nombreProfesionEspecialidad;

	/************* Constructors ***********/

	/**
	 * 
	 */
	public InfoVacantePostularDTO() {
		super();
	}

	public InfoVacantePostularDTO(Integer idEmpleadoAspirante, String rfc, String curp, String nombreCompleto,
			String direccionCompleta, String status, String nombreProfesionEspecialidad) {

		this.idEmpleadoAspirante = idEmpleadoAspirante;
		this.rfc = rfc;
		this.curp = curp;
		this.nombreCompleto = nombreCompleto;
		this.direccionCompleta = direccionCompleta;
		this.status = status;
		this.nombreProfesionEspecialidad = nombreProfesionEspecialidad;
	}

	public InfoVacantePostularDTO(Integer idEmpleadoAspirante, String rfc, String curp, String nombreCompleto,
			String direccionCompleta, String status) {

		this.idEmpleadoAspirante = idEmpleadoAspirante;
		this.rfc = rfc;
		this.curp = curp;
		this.nombreCompleto = nombreCompleto;
		this.direccionCompleta = direccionCompleta;
		this.status = status;

	}

	/************ Getters and Setters **************/

	public Integer getIdEmpleadoAspirante() {
		return idEmpleadoAspirante;
	}

	public void setIdEmpleadoAspirante(Integer idEmpleadoAspirante) {
		this.idEmpleadoAspirante = idEmpleadoAspirante;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the nombreProfesionEspecialidad
	 */
	public String getNombreProfesionEspecialidad() {
		return nombreProfesionEspecialidad;
	}

	/**
	 * @param nombreProfesionEspecialidad
	 *            the nombreProfesionEspecialidad to set
	 */
	public void setNombreProfesionEspecialidad(String nombreProfesionEspecialidad) {
		this.nombreProfesionEspecialidad = nombreProfesionEspecialidad;
	}

}
