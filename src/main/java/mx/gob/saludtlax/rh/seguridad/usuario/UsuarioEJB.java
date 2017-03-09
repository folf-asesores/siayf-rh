package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.perfiles.PerfilService;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.perfiles.PerfilDTO;
import mx.gob.saludtlax.rh.util.Crypto;

@Stateless
public class UsuarioEJB implements Usuario {

	@Inject
	private PerfilService perfilService;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private Catalogo catalogos;

	@Override
	public void crear(UsuarioDTO usuario) {
		usuarioService.crear(usuario);
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return usuarioService.obtenerUsuarioPorNombreUsuario(nombreUsuario);
	}

	/**
	 * Devuelve un usuario por token.
	 *
	 * @param token
	 *            el token a buscar.
	 * @return el usuario.
	 */
	@Override
	public UsuarioDTO obtenerUsuarioPorToken(String token) {
		String hashToken = Crypto.hmac(token);
		return usuarioService.obtenerUsuarioPorHashToken(hashToken);
	}

	@Override
	public List<UsuarioDTO> consultarTodosUsuarios() {
		return usuarioService.consultarTodosUsuarios();
	}

	@Override
	public List<UsuarioDTO> consultarUsuariosActivos() {
		return usuarioService.consultarUsuariosActivos();
	}

	/**
	 * Consulta la lista de los usuarios activos en el sistema
	 */
	@Override
	public List<UsuarioDTO> consultarUsuariosInactivos() {
		return usuarioService.consultarUsuariosInactivos();
	}

	@Override
	public void actualizar(UsuarioDTO usuario) {
		usuarioService.actualizar(usuario);
	}

	@Override
	public void eliminar(Integer Id) {
		usuarioService.eliminarUsuario(Id);
	}

	@Override
	public boolean existeUsuario(String username) {
		return usuarioService.existeUsuario(username);
	}

	// <<<<Metodos de Consultas de Lista Externa>>>>>
	@Override
	public List<PerfilDTO> obtenerPerfilLista() {
		return perfilService.listaPerfil();
	}

	@Override
	public void usuarioInactivo(Integer idUsuario) {
		UsuarioDTO usr = usuarioService.obtenerUsuarioPorId(idUsuario);
		usr.setActivo(false);
		usuarioService.actualizar(usr);
	}

	@Override
	public List<String> recursosUsuario(Integer idUsuario) {
		return usuarioService.recursosPorUsuario(idUsuario);
	}

	@Override
	public List<CatalogoDTO> obtenerAdscripciones() {
		// TODO Auto-generated method stub
		return catalogos.consultarAdscripciones();
	}



	@Override
	public List<InfoUsuarioDTO> consultarInfoUsuariosActivos() {
		return usuarioService.consultarUsuariosInfoActivos();
	}
}
