package mx.gob.saludtlax.rh.perfiles;

import java.util.List;

public interface Perfil {

	
	void crearPerfil(PerfilDTO perfilDTO);
	
	void editarPerfil(PerfilDTO perfilDTO);
	
	void eliminarPerfil(Integer idPerfil);
	
	List<PerfilDTO> listaPerfiles();
	
}
