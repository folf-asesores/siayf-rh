package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.Date;

public class ConsultarIncidenciasEmpleadoModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841125477804586628L;
	
	public Integer idEmpleado;
	
	public Date fechaInicio;
	
	public Date fechaFin;

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
