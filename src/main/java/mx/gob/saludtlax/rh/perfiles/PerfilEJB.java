package mx.gob.saludtlax.rh.perfiles;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PerfilEJB implements Perfil{

	@Inject
	private PerfilService perfilService;
	
	@Override
	public void crearPerfil(PerfilDTO perfilDTO) {
		perfilService.crearPerfil(perfilDTO);
	}

	@Override
	public void editarPerfil(PerfilDTO perfilDTO) {
	perfilService.editarPerfil(perfilDTO);
	}

	@Override
	public void eliminarPerfil(Integer idPerfil) {
	perfilService.eliminarPerfil(idPerfil);
	}

	@Override
	public List<PerfilDTO> listaPerfiles() {
		return perfilService.listaPerfil();
	}

}
