/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/09/2016 17:43:15
 * 
 */
public class DisponiblesInterinatoDTO {
	private Integer idPuesto;
	private String empleado;
	private String contratacion;
	private String adscripcion;
	private String subadscripcion;
	private String servicio;
	private String funcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String puesto;
	private String motivoPermiso;
	private Integer idMovimiento;

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getMotivoPermiso() {
		return motivoPermiso;
	}

	public void setMotivoPermiso(String motivoPermiso) {
		this.motivoPermiso = motivoPermiso;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getContratacion() {
		return contratacion;
	}

	public void setContratacion(String contratacion) {
		this.contratacion = contratacion;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public String getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(String subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
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

}
