package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

@Stateless
public class ConfiguracionUsuarioModuloEJB implements ConfiguracionUsuarioModulo {

    @Inject
    private ConfiguracionUsuarioModuloRepository configuracionUsuarioModuloRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private ConfiguracionModuloAccionRepository configuracionModuloAccionRepository;

    @Inject
    private DetalleConfiguracionModuloAccionRepository detalleconfiguracionModuloAccionRepository;

    @Override
    public void crear(ConfiguracionUsuarioModuloDTO dto) {
        ConfiguracionUsuarioModuloEntity entity = new ConfiguracionUsuarioModuloEntity();
        entity.setFechaCreacion(new Date());
        entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository.obtenerPorId(dto.getConfiguracionModulo().getIdConfiguracionModuloAccion()));
        entity.setUsuario(usuarioRepository.obtenerPorId(dto.getUsuario().getIdUsuario()));

        configuracionUsuarioModuloRepository.crear(entity);
    }

    @Override
    public void editar(ConfiguracionUsuarioModuloDTO dto) {
        ConfiguracionUsuarioModuloEntity entity = configuracionUsuarioModuloRepository.obtenerPorId(dto.getIdConfiguracionUsuarioModulo());
        entity.setFechaCreacion(new Date());
        entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository.obtenerPorId(dto.getConfiguracionModulo().getIdConfiguracionModuloAccion()));
        entity.setUsuario(usuarioRepository.obtenerPorId(dto.getUsuario().getIdUsuario()));

        configuracionUsuarioModuloRepository.actualizar(entity);
    }

    @Override
    public void eliminar(Integer id) {
        configuracionUsuarioModuloRepository.eliminarPorId(id);
    }

    @Override
    public List<ConfiguracionUsuarioModuloDTO> obtenerLista() {
        List<ConfiguracionUsuarioModuloEntity> listEntity = configuracionUsuarioModuloRepository.obtenerRegistros();
        List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
        for (ConfiguracionUsuarioModuloEntity ent : listEntity) {
            ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();

            dto.setIdConfiguracionUsuarioModulo(ent.getIdConfiguracionUsuarioModulo());
            UsuarioDTO usrDto = new UsuarioDTO();
            usrDto.setIdUsuario(ent.getUsuario().getIdUsuario());
            usrDto.setUserName(ent.getUsuario().getUserName());
            dto.setUsuario(usrDto);

            ConfiguracionModuloAccionDTO cfn = new ConfiguracionModuloAccionDTO();
            cfn.setIdConfiguracionModuloAccion(ent.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
            ModuloDTO mdl = new ModuloDTO();
            mdl.setIdModulo(ent.getConfiguracionModuloAccion().getModulo().getIdModulo());
            mdl.setNombre(ent.getConfiguracionModuloAccion().getModulo().getNombre());
            cfn.setModulo(mdl);
            cfn.setNombreConfiguracion(ent.getConfiguracionModuloAccion().getDescripcion());
            List<AccionDTO> listaAcciones = new ArrayList<>();

            List<DetalleConfiguracionModuloAccionEntity> detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
                    ent.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
            for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
                listaAcciones.add(new AccionDTO(detalle.getAccion().getIdAccion(), detalle.getAccion().getClave(), detalle.getAccion().getDescripcion(), detalle.getAccion().getArea().getIdArea(), detalle.getAccion().getModulo().getIdModulo(), detalle.getAccion().getArea().getNombreArea()));
            }
            cfn.setAcciones(listaAcciones);
            dto.setConfiguracionModulo(cfn);

            listDto.add(dto);

        }
        return listDto;
    }

    @Override
    public List<ConfiguracionUsuarioModuloDTO> obtenerListaPorUsuario(Integer idUsuario) {
        List<ConfiguracionUsuarioModuloEntity> listEntity = configuracionUsuarioModuloRepository.obtenerRegistrosPorUsuario(idUsuario);
        List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
        for (ConfiguracionUsuarioModuloEntity ent : listEntity) {
            ConfiguracionUsuarioModuloDTO dto = toDto(ent);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public Boolean tienePermiso(String permiso, Integer idUsuario) {
        List<String> permisosUsuario = obtenerAccionesdeUsuario(idUsuario);
        return permisosUsuario.contains(permiso);
    }

    // obtiene la lista de claves de acciones que tiene asignadas un usuario
    public List<String> obtenerAccionesdeUsuario(Integer idUsuario) {
        List<ConfiguracionUsuarioModuloDTO> confDtos = obtenerListaPorUsuario(idUsuario);

        List<String> accionesDeUsuario = new ArrayList<>();
        for (ConfiguracionUsuarioModuloDTO dto : confDtos) {

            List<DetalleConfiguracionModuloAccionEntity> detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
                    dto.getConfiguracionModulo().getIdConfiguracionModuloAccion());
            for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
                accionesDeUsuario.add(detalle.getAccion().getClave());
            }
        }
        return accionesDeUsuario;
    }

    @Override
    public List<ConfiguracionUsuarioModuloDTO> obtenerListaRestantePorUsuario(Integer idUsuario) {
        List<ConfiguracionUsuarioModuloEntity> listEntity = configuracionUsuarioModuloRepository.obtenerRegistrosPorUsuario(idUsuario);
        List<ConfiguracionUsuarioModuloEntity> listCompletaEntity = configuracionUsuarioModuloRepository.consultarTodos();
        List<ConfiguracionUsuarioModuloEntity> listRestanteEntity = new ArrayList<>();

        System.out.println("listEntity: " + listEntity.size() );
        
        System.out.println("listCompleta: " + listCompletaEntity.size() );
        
        for (ConfiguracionUsuarioModuloEntity ent : listCompletaEntity) {
            if (!listEntity.contains(ent)) {
                listRestanteEntity.add(ent);
            }
        }
        List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
        System.out.println("listCompleta: " + listRestanteEntity.size() );
        for (ConfiguracionUsuarioModuloEntity ent : listRestanteEntity) {
            ConfiguracionUsuarioModuloDTO dto = toDto(ent);
            listDto.add(dto);
        }
        return listDto;
    }

    private ConfiguracionUsuarioModuloDTO toDto(ConfiguracionUsuarioModuloEntity ent) {

        ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();

        dto.setIdConfiguracionUsuarioModulo(ent.getIdConfiguracionUsuarioModulo());
        UsuarioDTO usrDto = new UsuarioDTO();
        usrDto.setIdUsuario(ent.getUsuario().getIdUsuario());
        usrDto.setUserName(ent.getUsuario().getUserName());
        dto.setUsuario(usrDto);

        ConfiguracionModuloAccionDTO cfn = new ConfiguracionModuloAccionDTO();
        cfn.setIdConfiguracionModuloAccion(ent.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
        ModuloDTO mdl = new ModuloDTO();
        mdl.setIdModulo(ent.getConfiguracionModuloAccion().getModulo().getIdModulo());
        mdl.setNombre(ent.getConfiguracionModuloAccion().getModulo().getNombre());
        cfn.setModulo(mdl);
        cfn.setNombreConfiguracion(ent.getConfiguracionModuloAccion().getDescripcion());
        List<AccionDTO> listaAcciones = new ArrayList<>();

        List<DetalleConfiguracionModuloAccionEntity> detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
                ent.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
        if (!detalles.isEmpty()) {
            for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
                listaAcciones.add(new AccionDTO(detalle.getAccion().getIdAccion(),
                        detalle.getAccion().getClave(), detalle.getAccion().getDescripcion(),
                        detalle.getAccion().getArea().getIdArea(), detalle.getAccion().getModulo().getIdModulo(),
                        detalle.getAccion().getArea().getNombreArea()));
            }
        }
        cfn.setAcciones(listaAcciones);
        dto.setConfiguracionModulo(cfn);
        return dto;
    }
}
