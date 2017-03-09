package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Datos de entrada de la incidencia del empleado
 * 
 * @author Juan Carlos Ivan Ganzo Dominguez.
 *
 */
public class IncidenciaEmpleadoFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1160864148853353136L;

	private Integer idIncidenciaEmpleado;

	private Integer idEmpleado;

	private Integer idIncidencia;

	private Date fechaInicio;

	private Date fechaFin;

	private Date fechaOficio;

	private String observaciones;

	private Integer idUsuario;

	private Date horaInicio;

	private Date horaFin;

	private String numeroOficio;
	
	private boolean ignorarValidacionReglaIncidencia;

	public String getNumeroOficio() {
		return numeroOficio;
	}

	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	public Integer getIdIncidenciaEmpleado() {
		return idIncidenciaEmpleado;
	}

	public void setIdIncidenciaEmpleado(Integer idIncidenciaEmpleado) {
		this.idIncidenciaEmpleado = idIncidenciaEmpleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
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

	public Date getFechaOficio() {
		return fechaOficio;
	}

	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public boolean isIgnorarValidacionReglaIncidencia() {
		return ignorarValidacionReglaIncidencia;
	}

	public void setIgnorarValidacionReglaIncidencia(boolean ignorarValidacionReglaIncidencia) {
		this.ignorarValidacionReglaIncidencia = ignorarValidacionReglaIncidencia;
	}

	

}