package mx.gob.saludtlax.rh.modulos;

import java.util.List;

public interface ConfiguracionModuloAccion {

	void crear(ConfiguracionModuloAccionDTO dto);
	
	void editar(ConfiguracionModuloAccionDTO dto);
	
	void eliminar(Integer id);
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTO();
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorModulo(Integer idModulo);
	
	List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorAccion(Integer idAccion);
}
