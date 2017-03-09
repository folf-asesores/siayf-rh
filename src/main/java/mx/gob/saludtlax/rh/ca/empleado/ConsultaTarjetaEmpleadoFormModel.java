package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.Date;

public class ConsultaTarjetaEmpleadoFormModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7248719430542732293L;
	
	private Integer idEmpleado;
	
	private Date fechaInicio;
	
	private Date fechaFin;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
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
