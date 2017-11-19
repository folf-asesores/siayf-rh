package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VistaUsuarioEJB implements VistaUsuario{
	@Inject
	ConfiguracionUsuarioModuloService configuracionUsuarioModuloService;
	
	public List<ConfiguracionUsuarioModuloDTO> getListaUsuarios(){
		return configuracionUsuarioModuloService.obtenerLista();
	}

	@Override
	public void crear(VistaUsuarioDTO dto) {
		
		
	}

	@Override
	public void editar(VistaUsuarioDTO dto) {
		
		
	}

	@Override
	public void eliminar(Integer id) {
		
		
	}

	@Override
	public List<VistaUsuarioDTO> obtenerLista() {
		
		return null;
	}

	@Override
	public List<VistaUsuarioDTO> obtenerListaPorUsuario(Integer idUsuario) {
		
		return null;
	}

}
