
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

public class ConfiguracionUsuarioModuloService {

    @Inject
    private ConfiguracionUsuarioModuloRepository dao;

    @Inject
    private ConfiguracionModuloAccionRepository configuracionModuloDao;

    @Inject
    private UsuarioRepository usuarioDao;

    @Inject
    private DetalleConfiguracionModuloAccionRepository configuracionModuloAccionRepository;

    public void crearConfiguracion(ConfiguracionUsuarioModuloDTO dto) {
        ConfiguracionUsuarioModuloEntity entity = new ConfiguracionUsuarioModuloEntity();
        entity.setFechaCreacion(new Date());
        entity.setConfiguracionModuloAccion(configuracionModuloDao.obtenerPorId(dto.getConfiguracionModulo().getIdConfiguracionModuloAccion()));
        entity.setUsuario(usuarioDao.obtenerPorId(dto.getUsuario().getIdUsuario()));

        dao.crear(entity);

    }

    public void editarConfiguracion(ConfiguracionUsuarioModuloDTO dto) {
        ConfiguracionUsuarioModuloEntity entity = dao.obtenerPorId(dto.getIdConfiguracionUsuarioModulo());

        entity.setFechaCreacion(new Date());
        entity.setConfiguracionModuloAccion(configuracionModuloDao.obtenerPorId(dto.getConfiguracionModulo().getIdConfiguracionModuloAccion()));
        entity.setUsuario(usuarioDao.obtenerPorId(dto.getUsuario().getIdUsuario()));

        dao.actualizar(entity);

    }

    public void eliminar(Integer id) {

        dao.eliminarPorId(id);
    }

    public List<ConfiguracionUsuarioModuloDTO> obtenerLista() {
        List<ConfiguracionUsuarioModuloEntity> listEntity = dao.obtenerRegistros();

        if (listEntity == null) {
            listEntity = new ArrayList<>();
        }

        List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
        for (ConfiguracionUsuarioModuloEntity cE : listEntity) {
            ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();

            dto.setIdConfiguracionUsuarioModulo((cE.getIdConfiguracionUsuarioModulo()));

            dto.setFechaCreacion(new Date());

            UsuarioDTO usuarioDto = new UsuarioDTO();

            usuarioDto.setActivo(cE.getUsuario().isActivo());
            usuarioDto.setApellidoMaterno(cE.getUsuario().getApellidoMaterno());
            usuarioDto.setApellidoPaterno(cE.getUsuario().getApellidoPaterno());
            usuarioDto.setCargo(cE.getUsuario().getCargo());
            usuarioDto.setCorreo(cE.getUsuario().getCorreo());
            usuarioDto.setFechaAlta(cE.getUsuario().getFechaAlta());
            usuarioDto.setIdUsuario(cE.getUsuario().getIdUsuario());
            usuarioDto.setNombre(cE.getUsuario().getNombre());
            usuarioDto.setPassword(cE.getUsuario().getPassword());
            usuarioDto.setUserName(cE.getUsuario().getUserName());

            dto.setUsuario(usuarioDto);

            ConfiguracionModuloAccionDTO confModuloAccion = new ConfiguracionModuloAccionDTO();
            confModuloAccion.setIdConfiguracionModuloAccion(cE.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());

            dto.setConfiguracionModulo(confModuloAccion);

            listDto.add(dto);
        }

        return listDto;
    }

    public List<ConfiguracionUsuarioModuloDTO> obtenerListaPorUsuario(Integer idUsuario) {
        List<ConfiguracionUsuarioModuloEntity> listEntity = dao.obtenerRegistrosPorUsuario(idUsuario);

        if (listEntity == null) {
            listEntity = new ArrayList<>();
        }

        List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
        for (ConfiguracionUsuarioModuloEntity cE : listEntity) {
            ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();

            dto.setIdConfiguracionUsuarioModulo((cE.getIdConfiguracionUsuarioModulo()));

            dto.setFechaCreacion(new Date());

            UsuarioDTO usuarioDto = new UsuarioDTO();

            usuarioDto.setActivo(cE.getUsuario().isActivo());
            usuarioDto.setApellidoMaterno(cE.getUsuario().getApellidoMaterno());
            usuarioDto.setApellidoPaterno(cE.getUsuario().getApellidoPaterno());
            usuarioDto.setCargo(cE.getUsuario().getCargo());
            usuarioDto.setCorreo(cE.getUsuario().getCorreo());
            usuarioDto.setFechaAlta(cE.getUsuario().getFechaAlta());
            usuarioDto.setIdUsuario(cE.getUsuario().getIdUsuario());
            usuarioDto.setNombre(cE.getUsuario().getNombre());
            usuarioDto.setPassword(cE.getUsuario().getPassword());
            usuarioDto.setUserName(cE.getUsuario().getUserName());

            dto.setUsuario(usuarioDto);

            ConfiguracionModuloAccionDTO confModuloAccion = new ConfiguracionModuloAccionDTO();
            confModuloAccion.setIdConfiguracionModuloAccion(cE.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());

            dto.setConfiguracionModulo(confModuloAccion);
            listDto.add(dto);
        }

        return listDto;
    }

    // obtiene la lista de claves de acciones que tiene asignadas un usuario
    public List<String> obtenerAccionesdeUsuario(Integer idUsuario) {
        List<ConfiguracionUsuarioModuloDTO> confDtos = obtenerListaPorUsuario(idUsuario);

        List<String> accionesDeUsuario = new ArrayList<>();
        for (ConfiguracionUsuarioModuloDTO dto : confDtos) {

            List<DetalleConfiguracionModuloAccionEntity> detalles = configuracionModuloAccionRepository
                    .obtenerDetallesPorIdConfiguracion(dto.getConfiguracionModulo().getIdConfiguracionModuloAccion());
            for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
                accionesDeUsuario.add(detalle.getAccion().getClave());
            }
        }
        return accionesDeUsuario;
    }

}