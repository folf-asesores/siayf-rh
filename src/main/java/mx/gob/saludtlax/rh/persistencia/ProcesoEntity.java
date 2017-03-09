/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "procesos_empleados")
public class ProcesoEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_proceso_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProcesoEmpleado;
	
	@Column(name = "id_empleado")
	private Integer idEmleado;
	@Column(name = "id_proceso")
	private Integer idProceso;
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	@Column(name = "fecha_fin")
	private Date fechaFin;
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	
	@Column(name = "numero_oficio")
	private String numeroOficio;
	@Column(name = "comentarios")
	private String comentarios;
	
//		<<<<<Getters & Setters>>>>>
	
	public Integer getIdProcesoEmpleado() {
		return idProcesoEmpleado;
	}
	public void setIdProcesoEmpleado(Integer idProcesoEmpleado) {
		this.idProcesoEmpleado = idProcesoEmpleado;
	}
	public Integer getIdEmleado() {
		return idEmleado;
	}
	public void setIdEmleado(Integer idEmleado) {
		this.idEmleado = idEmleado;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}