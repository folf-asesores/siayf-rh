package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.util.Date;

public class ProcesoDTO {
	
	private Integer idProcesoEmpleado;
	private Integer idEmpleado;
	private Integer idProceso;
	private Integer idUsuario;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAlta;
	private String numeroOficio;
	private String comentarios;
	
//	< < < < < Getters & Setters > > > > > 
	
	public Integer getIdProcesoEmpleado() {
		return idProcesoEmpleado;
	}
	public void setIdProcesoEmpleado(Integer idProcesoEmpleado) {
		this.idProcesoEmpleado = idProcesoEmpleado;
	}
	
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Integer getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getNumeroOficio() {
		return numeroOficio;
	}
	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
