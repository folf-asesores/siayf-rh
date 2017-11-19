
package mx.gob.saludtlax.rh.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AccionesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;

public class ConfiguracionModuloAccionService implements Serializable {

    private static final long serialVersionUID = -1800073664586345602L;
    private static final Logger LOGGER = Logger
            .getLogger(ConfiguracionModuloAccionService.class);

    @Inject
    private ConfiguracionModuloAccionRepository configuracionModuloAccionRepository;

    @Inject
    private ConfiguracionUsuarioModuloRepository configuracionUsuarioModuloRepository;

    @Inject
    private DetalleConfiguracionModuloAccionRepository detalleConfiguracionModuloAccionRepository;

    @Inject
    private ModuloRepository moduloRepository;

    @Inject
    private AccionesRepository accionesRepository;

    public void crear(ConfiguracionModuloAccionDTO dto) {
        ConfiguracionModuloAccionEntity entity = new ConfiguracionModuloAccionEntity();

        entity.setDescripcion(dto.getNombreConfiguracion());
        entity.setModulo(
                moduloRepository.obtenerPorId(dto.getModulo().getIdModulo()));

        ConfiguracionModuloAccionEntity conf = configuracionModuloAccionRepository
                .crear(entity);

        guardarDetalleConfiguracion(conf.getIdConfiguracionModuloAccion(),
                dto.getAcciones());
    }

    private void guardarDetalleConfiguracion(Integer idConfiguracion,
            List<AccionDTO> acciones) {

        // primero buscara si ya hay detalles de la configuracion de ser asi
        // los borrara y guardara la nueva lista de acciones.
        List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

        detallesExistentes = detalleConfiguracionModuloAccionRepository
                .obtenerDetallesPorIdConfiguracion(idConfiguracion);
        if (detallesExistentes != null) {
            for (DetalleConfiguracionModuloAccionEntity detalleDelet : detallesExistentes) {
                detalleConfiguracionModuloAccionRepository.eliminarPorId(
                        detalleDelet.getIdDetalleConfiguracionModuloAccion());
            }
        }
        if (!acciones.isEmpty()) { // Valida si hay acciones por registrar
            for (AccionDTO dto : acciones) {
                LOGGER.debug("Acción registrando: " + dto.toString());
                DetalleConfiguracionModuloAccionEntity detalle = new DetalleConfiguracionModuloAccionEntity();
                detalle.setId_accion(
                        accionesRepository.obtenerPorId(dto.getIdAccion()));
                detalle.setIdConfiguracionModuloAccion(idConfiguracion);
                // guarda los detalles de las acciones
                detalleConfiguracionModuloAccionRepository.crear(detalle);
            }
        }

    }

    public void editar(ConfiguracionModuloAccionDTO dto) {
        ConfiguracionModuloAccionEntity entity = configuracionModuloAccionRepository
                .obtenerPorId(dto.getIdConfiguracionModuloAccion());
        entity.setDescripcion(dto.getNombreConfiguracion());
        entity.setModulo(
                moduloRepository.obtenerPorId(dto.getModulo().getIdModulo()));

        ConfiguracionModuloAccionEntity conf = configuracionModuloAccionRepository
                .actualizar(entity);

        guardarDetalleConfiguracion(conf.getIdConfiguracionModuloAccion(),
                dto.getAcciones());
    }

    public void eliminar(Integer id) {
        ConfiguracionModuloAccionEntity entity = configuracionModuloAccionRepository
                .obtenerPorId(id);

        // primero buscara si ya hay detalles de la configuracion de ser asi los
        // borrara y guardara la nueva lista de acciones.
        List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

        detallesExistentes = detalleConfiguracionModuloAccionRepository
                .obtenerDetallesPorIdConfiguracion(
                        entity.getIdConfiguracionModuloAccion());
        if (detallesExistentes != null) {
            for (DetalleConfiguracionModuloAccionEntity detalleDelet : detallesExistentes) {
                detalleConfiguracionModuloAccionRepository.eliminarPorId(
                        detalleDelet.getIdDetalleConfiguracionModuloAccion());
            }
        }

        configuracionModuloAccionRepository.eliminar(entity);
    }

    public ConfiguracionModuloAccionDTO obtenerConfAccModPorId(
            Integer IdConfAccMod) {

        ConfiguracionModuloAccionEntity entity = configuracionModuloAccionRepository
                .obtenerPorId(IdConfAccMod);

        ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

        dto.setIdConfiguracionModuloAccion(
                entity.getIdConfiguracionModuloAccion());
        dto.setNombreConfiguracion(entity.getDescripcion());

        ModuloDTO moduloDto = new ModuloDTO();
        moduloDto.setHabilitado(entity.getModulo().getHabilitado());
        moduloDto.setIdModulo(entity.getModulo().getIdModulo());
        moduloDto.setIdArea(entity.getModulo().getArea().getIdArea());
        moduloDto.setNombre(entity.getModulo().getNombre());
        moduloDto.setNombreArea(entity.getModulo().getArea().getNombreArea());
        moduloDto.setUrl(entity.getModulo().getUrl());

        dto.setModulo(moduloDto);

        List<AccionDTO> listAcciones = new ArrayList<>();

        // primero buscara si ya hay detalles de la configuracion de ser asi
        List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

        detallesExistentes = detalleConfiguracionModuloAccionRepository
                .obtenerDetallesPorIdConfiguracion(IdConfAccMod);
        if (!detallesExistentes.isEmpty()) {
            for (DetalleConfiguracionModuloAccionEntity detalleConfiguracionModuloAccionEntity : detallesExistentes) {

                AccionDTO accion = new AccionDTO();

                accion.setIdAccion(detalleConfiguracionModuloAccionEntity
                        .getAccion().getIdAccion());
                accion.setIdArea(detalleConfiguracionModuloAccionEntity
                        .getAccion().getArea().getIdArea());
                accion.setClave(detalleConfiguracionModuloAccionEntity
                        .getAccion().getClave());
                accion.setDescripcion(detalleConfiguracionModuloAccionEntity
                        .getAccion().getDescripcion());
                accion.setNombreArea(detalleConfiguracionModuloAccionEntity
                        .getAccion().getArea().getNombreArea());

                Integer idModulo = entity.getModulo().getIdModulo();

                ModuloEntity moduloEntity = moduloRepository
                        .obtenerPorId(idModulo);

                if (moduloEntity == null) {
                    throw new ValidacionException(
                            "Obtener Configuración Modulo Acción: no se encontrarón resultados con el identificador del modulo "
                                    + detalleConfiguracionModuloAccionEntity
                                            .getAccion().getModulo()
                                            .getIdModulo().toString(),
                            ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
                } else {
                    accion.setIdModulo(moduloEntity.getIdModulo());
                }

                listAcciones.add(accion);

            }
        }

        dto.setAcciones(listAcciones);

        return dto;
    }

    public List<ConfiguracionModuloAccionDTO> obtenerRegistros() {
        List<ConfiguracionModuloAccionEntity> listEntity = configuracionModuloAccionRepository
                .consultarTodos();

        List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();

        for (ConfiguracionModuloAccionEntity cE : listEntity) {
            ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

            dto.setIdConfiguracionModuloAccion(
                    cE.getIdConfiguracionModuloAccion());
            dto.setNombreConfiguracion(cE.getDescripcion());

            ModuloDTO moduloDto = new ModuloDTO();
            moduloDto.setHabilitado(cE.getModulo().getHabilitado());
            moduloDto.setIdModulo(cE.getModulo().getIdModulo());
            moduloDto.setIdArea(cE.getModulo().getArea().getIdArea());
            moduloDto.setNombre(cE.getModulo().getNombre());
            moduloDto.setNombreArea(cE.getModulo().getArea().getNombreArea());
            moduloDto.setUrl(cE.getModulo().getUrl());

            dto.setModulo(moduloDto);

            List<AccionDTO> acciones = obtenerAccionesPorConfiguracion(
                    cE.getIdConfiguracionModuloAccion());
            dto.setAcciones(acciones);
            listDto.add(dto);
        }

        return listDto;

    }

    private List<AccionDTO> obtenerAccionesPorConfiguracion(
            Integer idConfiguracion) {
        List<AccionDTO> listAcciones = new ArrayList<>();

        List<DetalleConfiguracionModuloAccionEntity> detalles = detalleConfiguracionModuloAccionRepository
                .obtenerDetallesPorIdConfiguracion(idConfiguracion);

        for (DetalleConfiguracionModuloAccionEntity ent : detalles) {
            AccionDTO accionDto = new AccionDTO();
            accionDto.setIdAccion(ent.getAccion().getIdAccion());
            accionDto.setClave(ent.getAccion().getClave());
            accionDto.setDescripcion(ent.getAccion().getDescripcion());
            accionDto.setIdArea(ent.getAccion().getArea().getIdArea());
            accionDto.setNombreArea(ent.getAccion().getArea().getNombreArea());

            listAcciones.add(accionDto);
        }

        return listAcciones;
    }

    public List<ConfiguracionModuloAccionDTO> obtenerRegistrosPorModulo(
            Integer idModulo) {
        List<ConfiguracionModuloAccionEntity> listEntity = new ArrayList<>();

        listEntity = configuracionModuloAccionRepository
                .obtenerRegistrosPorModulo(idModulo);

        List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();
        for (ConfiguracionModuloAccionEntity cE : listEntity) {
            ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

            dto.setIdConfiguracionModuloAccion(
                    cE.getIdConfiguracionModuloAccion());

            ModuloDTO moduloDto = new ModuloDTO();
            moduloDto.setHabilitado(cE.getModulo().getHabilitado());
            moduloDto.setIdModulo(cE.getModulo().getIdModulo());
            moduloDto.setIdArea(cE.getModulo().getArea().getIdArea());
            moduloDto.setNombre(cE.getModulo().getNombre());
            moduloDto.setNombreArea(cE.getModulo().getArea().getNombreArea());
            moduloDto.setUrl(cE.getModulo().getUrl());

            dto.setModulo(moduloDto);

            listDto.add(dto);
        }

        return listDto;

    }

    public List<ConfiguracionModuloAccionDTO> obtenerRegistrosPorAccion(
            Integer idModulo) {
        List<ConfiguracionModuloAccionEntity> listEntity = new ArrayList<>();

        listEntity = configuracionModuloAccionRepository
                .obtenerRegistrosPorModulo(idModulo);

        List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();
        for (ConfiguracionModuloAccionEntity cE : listEntity) {
            ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

            dto.setIdConfiguracionModuloAccion(
                    cE.getIdConfiguracionModuloAccion());

            ModuloDTO moduloDto = new ModuloDTO();
            moduloDto.setHabilitado(cE.getModulo().getHabilitado());
            moduloDto.setIdModulo(cE.getModulo().getIdModulo());
            moduloDto.setIdArea(cE.getModulo().getArea().getIdArea());
            moduloDto.setNombre(cE.getModulo().getNombre());
            moduloDto.setNombreArea(cE.getModulo().getArea().getNombreArea());
            moduloDto.setUrl(cE.getModulo().getUrl());

            dto.setModulo(moduloDto);

            listDto.add(dto);
        }

        return listDto;

    }

    public AccionDTO obtenerAccionesNoRegistradasEnConfg(Integer idModulo,
            Integer idAccionFiltro) {

        return null;
    }

    public List<ConfiguracionModuloAccionDTO> configuracionPorUsuario(
            Integer idUsuario) {
        List<ConfiguracionUsuarioModuloEntity> listEntity = configuracionUsuarioModuloRepository
                .obtenerRegistrosPorUsuario(idUsuario);
        List<ConfiguracionModuloAccionEntity> listCompletaEntity = configuracionModuloAccionRepository
                .consultarTodos();
        List<ConfiguracionModuloAccionEntity> listRestanteEntity = new ArrayList<>();

        System.out.println("listEntity: " + listEntity.size());

        System.out.println("listCompleta: " + listCompletaEntity.size());

        List<ConfiguracionModuloAccionEntity> configuracionesUsuario = new ArrayList<>();
        for (ConfiguracionUsuarioModuloEntity configuracion : listEntity) {
            configuracionesUsuario
                    .add(configuracion.getConfiguracionModuloAccion());
        }

        for (ConfiguracionModuloAccionEntity ent : listCompletaEntity) {
            if (!configuracionesUsuario.contains(ent)) {
                listRestanteEntity.add(ent);
            }
        }

        List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();
        System.out.println("listCompleta: " + listRestanteEntity.size());
        for (ConfiguracionModuloAccionEntity ent : listRestanteEntity) {
            ConfiguracionModuloAccionDTO dto = toDto(ent);
            listDto.add(dto);
        }
        return listDto;

    }

    private ConfiguracionModuloAccionDTO toDto(
            ConfiguracionModuloAccionEntity ent) {

        ConfiguracionModuloAccionDTO cfn = new ConfiguracionModuloAccionDTO();
        cfn.setIdConfiguracionModuloAccion(
                ent.getIdConfiguracionModuloAccion());
        ModuloDTO mdl = new ModuloDTO();
        mdl.setIdModulo(ent.getModulo().getIdModulo());
        mdl.setNombre(ent.getModulo().getNombre());
        cfn.setModulo(mdl);
        cfn.setNombreConfiguracion(ent.getDescripcion());
        List<AccionDTO> listaAcciones = new ArrayList<>();

        List<DetalleConfiguracionModuloAccionEntity> detalles = detalleConfiguracionModuloAccionRepository
                .obtenerDetallesPorIdConfiguracion(
                        ent.getIdConfiguracionModuloAccion());
        if (!detalles.isEmpty()) {
            for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
                listaAcciones
                        .add(new AccionDTO(detalle.getAccion().getIdAccion(),
                                detalle.getAccion().getClave(),
                                detalle.getAccion().getDescripcion(),
                                detalle.getAccion().getArea().getIdArea(),
                                detalle.getAccion().getModulo().getIdModulo(),
                                detalle.getAccion().getArea().getNombreArea()));
            }
        }
        cfn.setAcciones(listaAcciones);

        return cfn;
    }

}
