package mx.gob.saludtlax.rh.perfiles;

import java.util.List;

public interface ConfiguracionPerfilModulo {

	void crearConfiguracionPerfilModulo(ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO);
	
	void eliminarConfiguracionPerfilModuloPorIdPerfil(Integer idPerfil);
	
	List<ConfiguracionPerfilModuloDTO> listaConfiguracionPerfilModulo();

	List<ConfiguracionPerfilModuloDTO> listaConfiguracionPerfilModuloPorIdPerfil(Integer id);
	
}
