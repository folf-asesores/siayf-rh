package mx.gob.saludtlax.rh.modulos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
public class ConfiguracionModuloAccionDTO implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -6726015902023365547L;

	private Integer id_configuracion_modulo_accion;
	
	private ModuloDTO modulo = new ModuloDTO();
	
	private String nombreConfiguracion;

	private List<AccionDTO> acciones = new ArrayList<>();

	public Integer getId_configuracion_modulo_accion() {
		return id_configuracion_modulo_accion;
	}

	public void setId_configuracion_modulo_accion(Integer id_configuracion_modulo_accion) {
		this.id_configuracion_modulo_accion = id_configuracion_modulo_accion;
	} 

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	
	public String getNombreConfiguracion() {
		return nombreConfiguracion;
	}

	public void setNombreConfiguracion(String nombreConfiguracion) {
		this.nombreConfiguracion = nombreConfiguracion;
	}

	public List<AccionDTO> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionDTO> acciones) {
		this.acciones = acciones;
	}

	
	


}
