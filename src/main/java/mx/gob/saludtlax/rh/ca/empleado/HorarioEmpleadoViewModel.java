package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.Date;

public class HorarioEmpleadoViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8597756391744044428L;

	private Integer idHorarioEmpleado;

	private Integer idEmpleado;

	private String fechaInicio;

	private String fechaFin;

	private Integer idJornada;

	private String descripcion;

	public Integer getIdHorarioEmpleado() {
		return idHorarioEmpleado;
	}

	public void setIdHorarioEmpleado(Integer idHorarioEmpleado) {
		this.idHorarioEmpleado = idHorarioEmpleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
