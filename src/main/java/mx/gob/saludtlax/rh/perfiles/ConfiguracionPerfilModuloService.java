
package mx.gob.saludtlax.rh.perfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloRepository;
import mx.gob.saludtlax.rh.persistencia.PerfilRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

public class ConfiguracionPerfilModuloService {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private PerfilRepository perfilDAO;

    @Inject
    private ConfiguracionPerfilModuloRepository configuracionPerfilModuloRepository;

    @Inject
    private ConfiguracionModuloAccionRepository configuracionModuloAccionRepository;

    public void crearConfiguracion(ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO) {
        ConfiguracionPerfilModuloEntity entity = new ConfiguracionPerfilModuloEntity();
        entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository
                .obtenerPorId(configuracionPerfilModuloDTO.getConfiguracionModuloAccionDTO().getIdConfiguracionModuloAccion()));
        entity.setFechaCreacion(new Date());
        entity.setPerfil(perfilDAO.obtenerPorId(configuracionPerfilModuloDTO.getPerfil().getIdPerfil()));

        configuracionPerfilModuloRepository.crear(entity);
    }

    public void editarConfiguracion(ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO) {
        ConfiguracionPerfilModuloEntity entity = configuracionPerfilModuloRepository.obtenerPorId(configuracionPerfilModuloDTO.getId_perfil_modulo());
        entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository
                .obtenerPorId(configuracionPerfilModuloDTO.getConfiguracionModuloAccionDTO().getIdConfiguracionModuloAccion()));
        entity.setFechaCreacion(new Date());
        entity.setPerfil(perfilDAO.obtenerPorId(configuracionPerfilModuloDTO.getPerfil().getIdPerfil()));

        configuracionPerfilModuloRepository.crear(entity);
    }

    public void eliminarConfiguracion(Integer id) {
        configuracionPerfilModuloRepository.eliminarPorIdPerfil(id);
    }

    public List<ConfiguracionPerfilModuloDTO> obtenerRegistros() {
        List<ConfiguracionPerfilModuloDTO> listDto = new ArrayList<>();
        List<ConfiguracionPerfilModuloEntity> listEntity = configuracionPerfilModuloRepository.consultarTodos();

        for (ConfiguracionPerfilModuloEntity cE : listEntity) {
            ConfiguracionPerfilModuloDTO dto = new ConfiguracionPerfilModuloDTO();

            dto.setId_perfil_modulo(cE.getId_perfil_modulo());
            dto.setFechaCreacion(new Date());

            ConfiguracionModuloAccionDTO configuracionModuloAccionDTO = new ConfiguracionModuloAccionDTO();
            configuracionModuloAccionDTO.setIdConfiguracionModuloAccion(cE.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
            AccionDTO accion = new AccionDTO();
            //			accion.setClave(cE.getConfiguracionModuloAccion().getId_accion().getClave());
            //			accion.setDescripcion(cE.getConfiguracionModuloAccion().getId_accion().getDescripcion());
            //			accion.setId_accion(cE.getConfiguracionModuloAccion().getId_accion().getId_accion());
            //			accion.setIdArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getIdArea());
            //			accion.setNombreArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getNombreArea());
            //			configuracionModuloAccionDTO.setAccion(accion);

            ModuloDTO modulo = new ModuloDTO();
            modulo.setHabilitado(cE.getConfiguracionModuloAccion().getModulo().getHabilitado());
            modulo.setIdModulo(cE.getConfiguracionModuloAccion().getModulo().getIdModulo());
            modulo.setIdArea(cE.getConfiguracionModuloAccion().getModulo().getArea().getIdArea());
            modulo.setNombre(cE.getConfiguracionModuloAccion().getModulo().getNombre());
            modulo.setNombreArea(cE.getConfiguracionModuloAccion().getModulo().getArea().getNombreArea());
            modulo.setUrl(cE.getConfiguracionModuloAccion().getModulo().getUrl());

            configuracionModuloAccionDTO.setModulo(modulo);

            dto.setConfiguracionModuloAccionDTO(configuracionModuloAccionDTO);

            listDto.add(dto);
        }

        return listDto;

    }

    public List<ConfiguracionPerfilModuloDTO> obtenerRegistrosPorIdPerfil(Integer idPerfil) {
        List<ConfiguracionPerfilModuloDTO> listDto = new ArrayList<>();
        List<ConfiguracionPerfilModuloEntity> listEntity = configuracionPerfilModuloRepository.obtenerListaPorIdPerfil(idPerfil);

        for (ConfiguracionPerfilModuloEntity cE : listEntity) {
            ConfiguracionPerfilModuloDTO dto = new ConfiguracionPerfilModuloDTO();

            dto.setId_perfil_modulo(cE.getId_perfil_modulo());
            dto.setFechaCreacion(new Date());

            ConfiguracionModuloAccionDTO configuracionModuloAccionDTO = new ConfiguracionModuloAccionDTO();
            configuracionModuloAccionDTO.setIdConfiguracionModuloAccion(cE.getConfiguracionModuloAccion().getIdConfiguracionModuloAccion());
            AccionDTO accion = new AccionDTO();
            //			accion.setClave(cE.getConfiguracionModuloAccion().getId_accion().getClave());
            //			accion.setDescripcion(cE.getConfiguracionModuloAccion().getId_accion().getDescripcion());
            //			accion.setId_accion(cE.getConfiguracionModuloAccion().getId_accion().getId_accion());
            //			accion.setIdArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getIdArea());
            //			accion.setNombreArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getNombreArea());
            //			configuracionModuloAccionDTO.setAccion(accion);

            ModuloDTO modulo = new ModuloDTO();
            modulo.setHabilitado(cE.getConfiguracionModuloAccion().getModulo().getHabilitado());
            modulo.setIdModulo(cE.getConfiguracionModuloAccion().getModulo().getIdModulo());
            modulo.setIdArea(cE.getConfiguracionModuloAccion().getModulo().getArea().getIdArea());
            modulo.setNombre(cE.getConfiguracionModuloAccion().getModulo().getNombre());
            modulo.setNombreArea(cE.getConfiguracionModuloAccion().getModulo().getArea().getNombreArea());
            modulo.setUrl(cE.getConfiguracionModuloAccion().getModulo().getUrl());

            configuracionModuloAccionDTO.setModulo(modulo);

            dto.setConfiguracionModuloAccionDTO(configuracionModuloAccionDTO);

            listDto.add(dto);
        }

        return listDto;

    }

}
