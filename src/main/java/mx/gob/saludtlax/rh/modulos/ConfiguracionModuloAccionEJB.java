package mx.gob.saludtlax.rh.modulos;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConfiguracionModuloAccionEJB implements ConfiguracionModuloAccion{

	@Inject
	private ConfiguracionModuloAccionService service;
	
	@Override
	public void crear(ConfiguracionModuloAccionDTO dto) {
		service.crear(dto);
		
	}

	@Override
	public void editar(ConfiguracionModuloAccionDTO dto) {
		service.editar(dto);
	}
 
	@Override
	public void eliminar(Integer id) {
		service.eliminar(id);
	}

	@Override
	public List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTO() {
		return service.obtenerRegistros();
	}

	@Override
	public List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorModulo(Integer idModulo) {
		return service.obtenerRegistrosPorModulo(idModulo);
	}

	@Override
	public List<ConfiguracionModuloAccionDTO> obtenerListaConfiguracionModuloAccionDTOPorAccion(Integer idAccion) {
		// TODO Auto-generated method stub
		return service.obtenerRegistrosPorAccion(idAccion);
	}
	
}
