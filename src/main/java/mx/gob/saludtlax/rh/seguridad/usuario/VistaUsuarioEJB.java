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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(VistaUsuarioDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VistaUsuarioDTO> obtenerLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VistaUsuarioDTO> obtenerListaPorUsuario(Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
