package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;
import java.util.Date;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;

public class ConfiguracionUsuarioModuloDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1142728198588303592L;
	private Integer id_configuracion_usuario_modulo;
	private UsuarioDTO usuario = new UsuarioDTO();
	private ConfiguracionModuloAccionDTO configuracionModulo = new ConfiguracionModuloAccionDTO();
	private Date fechaCreacion;
	
	
	public Integer getId_configuracion_usuario_modulo() {
		return id_configuracion_usuario_modulo;
	}
	public void setId_configuracion_usuario_modulo(Integer id_configuracion_usuario_modulo) {
		this.id_configuracion_usuario_modulo = id_configuracion_usuario_modulo;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public ConfiguracionModuloAccionDTO getConfiguracionModulo() {
		return configuracionModulo;
	}
	public void setConfiguracionModulo(ConfiguracionModuloAccionDTO configuracionModulo) {
		this.configuracionModulo = configuracionModulo;
	}
	
	
	
}
