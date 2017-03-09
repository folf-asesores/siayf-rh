package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.io.Serializable;

public class UsuarioConfiguracionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6419563171384080550L;
	
	private Integer idConfiguracionAprobacion;
	private Integer idAccionUsuario;
	private String accion;
	private String usuario;
	private Integer idUsuario;
	private String estatus;
	private String movimiento;
	private Integer idTipoMovimiento;

	// <<<<<Getters & Setters>>>>>

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Integer getIdAccionUsuario() {
		return idAccionUsuario;
	}

	public void setIdAccionUsuario(Integer idAccionUsuario) {
		this.idAccionUsuario = idAccionUsuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the idConfiguracionAprobacion
	 */
	public Integer getIdConfiguracionAprobacion() {
		return idConfiguracionAprobacion;
	}

	/**
	 * @param idConfiguracionAprobacion the idConfiguracionAprobacion to set
	 */
	public void setIdConfiguracionAprobacion(Integer idConfiguracionAprobacion) {
		this.idConfiguracionAprobacion = idConfiguracionAprobacion;
	}

}
