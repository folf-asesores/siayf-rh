/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-22:04:09
 */
public class BitacoraEmpleadoDTO {
	private Integer idBitacora;
	private String comentarios;
	private String lccAnterior;
	private String lccActual;
	private Integer tipoMovimientoEmpleado;
	private String tipoModificacion;
	private Integer empleado;
	private Integer idUsuario;
	private String usuario;
	private Date fecha;
	private String nombreEmpleado;

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoModificacion() {
		return tipoModificacion;
	}

	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	public Integer getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(Integer idBitacora) {
		this.idBitacora = idBitacora;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getLccAnterior() {
		return lccAnterior;
	}

	public void setLccAnterior(String lccAnterior) {
		this.lccAnterior = lccAnterior;
	}

	public String getLccActual() {
		return lccActual;
	}

	public void setLccActual(String lccActual) {
		this.lccActual = lccActual;
	}

	public Integer getTipoMovimientoEmpleado() {
		return tipoMovimientoEmpleado;
	}

	public void setTipoMovimientoEmpleado(Integer tipoMovimientoEmpleado) {
		this.tipoMovimientoEmpleado = tipoMovimientoEmpleado;
	}

}
