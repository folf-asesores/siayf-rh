package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

public interface ConfiguracionUsuarioModulo {

	void crear(ConfiguracionUsuarioModuloDTO dto);
	
	void editar(ConfiguracionUsuarioModuloDTO dto);
	
	void eliminar(Integer id);
	
	List<ConfiguracionUsuarioModuloDTO> obtenerLista();
	
	List<ConfiguracionUsuarioModuloDTO> obtenerListaPorUsuario(Integer idUsuario);
	
	Boolean tienePermiso(String permiso,Integer idUsuario);

	List<ConfiguracionUsuarioModuloDTO> obtenerListaRestantePorUsuario(Integer idUsuario);
	
}
