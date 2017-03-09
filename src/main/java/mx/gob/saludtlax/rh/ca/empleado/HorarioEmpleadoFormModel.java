package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.Date;

/**
 * Formulario con datos de entrada para el horario del empleado.
 * 
 * @author Juan Carlos Ivan Ganzo Dominguez.
 *
 */
public class HorarioEmpleadoFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8698351898631076160L;

	private Integer idHorarioEmpleado;

	private Integer idEmpleado;

	private Date fechaInicio;

	private Date fechaFin;

	private Integer idJornada;
	

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

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}



}
