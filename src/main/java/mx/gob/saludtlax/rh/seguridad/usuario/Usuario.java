/*
 * Usuario.java
 * Creado el Aug 8, 2016 7:37:54 PM
 *
 */

package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

import javax.ejb.Local;

import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.perfiles.PerfilDTO;

/**
 *
 */
@Local
public interface Usuario {

    /**
     * Permite crear un usuario nuevo.
     *
     * @param usuario
     *            el usuario a crear.
     */
    void crear(UsuarioDTO usuario);

    /**
     * Obtiene los usuarios que se encuentra dentro del almacen de datos.
     *
     * @return un alista con los usuarios del almacen de datos.
     */
    List<UsuarioDTO> consultarTodosUsuarios();

    /**
     * Consulta en el almacen de datos y devuelve una lista con los usuarios
     * que están activos dentro del sistema.
     *
     * @return una lista con los usuarios activos del sistema.
     */
    List<UsuarioDTO> consultarUsuariosActivos();

    /**
     * Consulta en el almacen de datos y devuelve una lista con los usuarios
     * que no están activos en el sistema.
     *
     * @return una lista con los usuarios que no están activos en el sistema.
     */
    List<UsuarioDTO> consultarUsuariosInactivos();

    /**
     * Consulta en el almacen de datos y devuelve un usuario que tenga el token
     * que se está buscando.
     *
     * @param token
     *            el token a buscar.
     * @return el usuario si se encontró.
     */
    UsuarioDTO obtenerUsuarioPorToken(String token);

    /**
     * Consulta en el almacen de datos buscando por nombre de usuario si se
     * encuentra se devuelve el usuario en caso contrario retorna un null.
     *
     * @param nombreUsuario
     * @return
     */
    UsuarioDTO obtenerUsuarioPorNombreUsuario(String nombreUsuario);

    boolean existeUsuario(String nombreUsuario);

    void actualizar(UsuarioDTO usuario);

    void eliminar(Integer idUsuario);

    void usuarioInactivo(Integer idUsuario);

    //	<<<<Metodos de Consultas de Lista Externa>>>>>
    List<PerfilDTO> obtenerPerfilLista();

    List<String> recursosUsuario(Integer idUsuario);

    List<CatalogoDTO> obtenerAdscripciones();

    public List<InfoUsuarioDTO> consultarInfoUsuariosActivos();
}
