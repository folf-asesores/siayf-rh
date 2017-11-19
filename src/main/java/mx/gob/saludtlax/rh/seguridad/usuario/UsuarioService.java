
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.SeguridadCodigoError;
import mx.gob.saludtlax.rh.excepciones.SeguridadException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

/**
 * Esta es una clase ayudante del EJB de usuario.
 */
public class UsuarioService {

    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());

    @Inject
    private UsuarioRepository usuarioRepository;
    @Inject
    private PerfilUsuarioRepository perfilUsuarioDAO;

    /**
     * Guarda/Registra un usuario
     *
     * @param dto
     */
    protected void crear(UsuarioDTO dto) {
        try {
            // PerfilUsuarioEntity perfil =
            // perfilUsuarioDAO.obtenerPorId(dto.getIdUsuario());
            UsuarioEntity entity = convertirDTOAEntidad(dto, null);

            usuarioRepository.crear(entity);
        } catch (PersistenceException ex) {
            throw new SistemaException("Error al registrar un usuario", SistemaCodigoError.IMPOSIBLE_PERSISTIR_OBJETO);
        }
    }

    /**
     * Actualiza la información de un usuario
     *
     * @param dto
     */
    protected void actualizar(UsuarioDTO dto) {
        if (dto == null) {
            throw new ValidationException("No es posible actualizar un usuario nulo", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getIdUsuario() < 1) {
            throw new ValidationException("No es posible actualizar un usuario con un ID negativo o cero", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        try {
            PerfilUsuarioEntity perfil = perfilUsuarioDAO.obtenerPorId(dto.getIdUsuario());
            UsuarioEntity entity = usuarioRepository.obtenerPorId(dto.getIdUsuario());
            entity = convertirDTOAEntidad(dto, entity, perfil);

            usuarioRepository.actualizar(entity);
        } catch (PersistenceException ex) {
            LOGGER.error(ex);
            throw new SistemaException("Error al actualizar", SistemaCodigoError.IMPOSIBLE_ACTUALIZAR_OBJETO);
        }
    }

    /**
     * Busca un usuario registrado por ID.
     *
     * @param idUsuario
     *            el ID del usuario a buscar.
     * @return un DTO con la información si se encontró.
     */
    public UsuarioDTO obtenerUsuarioPorId(Integer idUsuario) {
        UsuarioEntity usuario = usuarioRepository.obtenerPorId(idUsuario);
        return convertirEntidadADTO(usuario);
    }

    /**
     * Devuelde la información de un usuario de acuerdo al criterio de Token
     *
     * @param hashToken
     * @return
     */
    public UsuarioDTO obtenerUsuarioPorHashToken(String hashToken) {
        UsuarioEntity usuario = usuarioRepository.obtenerUsuarioPorHashToken(hashToken);
        return convertirEntidadADTO(usuario);
    }

    /**
     * Devuelve la información de un usuario de acuerdo a su username
     *
     * @param nombreUsuario
     *            el nombre de usuario.
     * @return el usuario que que coincide con el nombre de usuario.
     */
    public UsuarioDTO obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        UsuarioEntity usuario = usuarioRepository.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        return convertirEntidadADTO(usuario);
    }

    /**
     * Consulta la lista de los usuarios registrados en el sistema
     *
     * @return
     */
    public List<UsuarioDTO> consultarTodosUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.consultarTodosUsuarios();

        return convertirEntidadesADTOs(usuarios);
    }

    /**
     * Consulta la lista de los usuarios activos en el sistema.
     *
     * @return una lista con los usuarios activos.
     */
    public List<UsuarioDTO> consultarUsuariosActivos() {
        List<UsuarioEntity> usuarios = usuarioRepository.consultarUsuariosActivos();

        return convertirEntidadesADTOs(usuarios);
    }

    /**
     * Consulta la lista de los usuarios inactivos en el sistema.
     *
     * @return una lista con los usuarios inactivos.
     */
    public List<UsuarioDTO> consultarUsuariosInactivos() {
        List<UsuarioEntity> usuarios = usuarioRepository.consultarUsuariosInactivos();

        return convertirEntidadesADTOs(usuarios);
    }

    public boolean existeUsuario(String username) {
        return usuarioRepository.existeUsuario(username);
    }

    /**
     * Eliminar un usuario registrado
     *
     * @param dto
     *            el usuario a eliminar
     */
    public void eliminarUsuario(Integer id) {
        usuarioRepository.eliminarPorId(id);
    }

    private static UsuarioEntity convertirDTOAEntidad(UsuarioDTO dto, PerfilUsuarioEntity perfil) {
        return convertirDTOAEntidad(dto, null, perfil);
    }

    private static UsuarioEntity convertirDTOAEntidad(UsuarioDTO dto, UsuarioEntity entity, PerfilUsuarioEntity perfil) {
        if (entity == null) {
            entity = new UsuarioEntity();
        }

        // entity.setPerfil(perfil);
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setActivo(true);
        entity.setNombre(dto.getNombre());
        entity.setApellidoPaterno(dto.getApellidoPaterno());
        entity.setApellidoMaterno(dto.getApellidoMaterno());
        entity.setCorreo(dto.getCorreo());
        entity.setTelefono(dto.getTelefono());
        entity.setCelular(dto.getCelular());
        entity.setCargo(dto.getCargo());
        entity.setId_adscripcion(dto.getIdAdscripcion());
        entity.setId_lugar_adscripcion(dto.getIdLugarAdscripcion());
        entity.setId_area_adscripcion(dto.getIdAreaAdscripcion());
        return entity;
    }

    private UsuarioDTO convertirEntidadADTO(UsuarioEntity entidad) {
        if (entidad == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();

        dto.setIdUsuario(entidad.getIdUsuario());
        dto.setUserName(entidad.getUserName());
        dto.setPassword(entidad.getPassword());
        dto.setActivo(entidad.isActivo());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setCorreo(entidad.getCorreo());
        dto.setCargo(entidad.getCargo());
        dto.setIdPerfil(entidad.getPerfil() == null ? null : entidad.getPerfil().getIdPerfil());
        dto.setIdAdscripcion(entidad.getId_adscripcion());
        dto.setIdAreaAdscripcion(entidad.getId_area_adscripcion());
        dto.setIdLugarAdscripcion(entidad.getId_lugar_adscripcion());
        return dto;
    }

    private List<UsuarioDTO> convertirEntidadesADTOs(List<UsuarioEntity> entidades) {
        List<UsuarioDTO> dtos = new ArrayList<>();

        for (UsuarioEntity entidad : entidades) {
            UsuarioDTO dto = convertirEntidadADTO(entidad);

            dtos.add(dto);
        }

        return dtos;
    }

    public List<String> recursosPorUsuario(Integer idUsuario) {
        return usuarioRepository.recursosPorUsuario(idUsuario);
    }

    public List<InfoUsuarioDTO> consultarUsuariosInfoActivos() {
        List<UsuarioEntity> usuariosActivos = usuarioRepository.consultarUsuariosActivos();
        List<InfoUsuarioDTO> lista = new ArrayList<>();
        if (!usuariosActivos.isEmpty()) {
            for (UsuarioEntity u : usuariosActivos) {
                InfoUsuarioDTO dto = new InfoUsuarioDTO();
                dto.setIdUsuario(u.getIdUsuario());
                dto.setNombre(u.nombreCompleto());
                lista.add(dto);
            }
        }
        return lista;
    }

    /**
     * Valida que el usuario exista y esté activo
     *
     * @param idUsuario
     *            identificador del usuario
     * @return UsuarioEntity entidad del usuario
     **/
    public UsuarioEntity validarUsuario(int idUsuario) {
        UsuarioEntity usuario = usuarioRepository.obtenerPorId(idUsuario);
        if (usuario == null) {
            throw new SistemaException("No se encontró usuario con identificador " + idUsuario, SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (!usuario.isActivo()) {
            throw new SeguridadException("El usuario está inactivo, no puede realizar la operación", SeguridadCodigoError.USUARIO_INACTIVO);
        }
        return usuario;
    }

}