package mx.gob.saludtlax.rh.modulos;

import java.util.List;

import mx.gob.saludtlax.rh.acciones.AccionDTO;

public interface ConfiguracionModuloAccion {

	void crear(ConfiguracionModuloAccionDTO dto);
	
	void editar(ConfiguracionModuloAccionDTO dto);
	
	void eliminar(Integer id);
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTO();
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorModulo(Integer idModulo);
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorAccion(Integer idAccion);
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorUsuario(Integer idUsuario);
	
	ConfiguracionModuloAccionDTO obtenerConfAccModPorId(Integer IdConfAccMod);
	
	List<AccionDTO> obtenerAccionesNoRegistradasEnConfg(Integer idConfiguracion, List<Integer> idAcciones);
}
