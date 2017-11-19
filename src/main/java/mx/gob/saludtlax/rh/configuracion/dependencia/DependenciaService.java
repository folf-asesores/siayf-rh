
package mx.gob.saludtlax.rh.configuracion.dependencia;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempRepository;

public class DependenciaService {

    private static final Logger LOGGER = Logger.getLogger(DependenciaService.class.getName());

    @Inject
    private DependenciaTempRepository dependenciaRepository;

    public List<DependenciaDTO> listaDependencia() {
        List<DependenciaTempEntity> dependencias = dependenciaRepository.consultarDependencias();
        return convertirEntidadesADTOs(dependencias);
    }

    protected DependenciaDTO nuevoDependencia() {
        DependenciaDTO dto = new DependenciaDTO();
        dto.setDescripcion(null);
        dto.setIdSector(null);
        dto.setIdBase(null);
        return dto;
    }

    protected DependenciaDTO crearDependencia(DependenciaDTO dto) {
        DependenciaTempEntity entity = new DependenciaTempEntity();
        entity.setIdRamo(4);
        entity.setIdClasificacionOrganismo(3);
        entity.setIdEntePublico(9);
        entity.setIdSector(dto.getIdSector());
        entity.setIdBase36(dto.getIdBase());
        entity.setDescripcion(dto.getDescripcion());
        dependenciaRepository.crear(entity);
        return obtenerDependenciaPorId(entity.getIdDependencia());
    }

    protected DependenciaDTO obtenerDependenciaPorId(Integer idDependencia) {
        DependenciaTempEntity entidad = dependenciaRepository.obtenerPorId(idDependencia);
        DependenciaDTO dto = convertirEntidadADTO(entidad);

        return dto;
    }

    protected List<String> consultarDescripcionDependenciasPorCriterio(String consulta) {
        return dependenciaRepository.consultarDescripcionDependenciasPorCriterio(consulta);
    }

    protected Integer consultarIdDependenciaPorDescripcion(String descripcion) {
        return dependenciaRepository.consultarIdDependenciaPorDescripcion(descripcion);
    }

    protected DependenciaDTO actualizarDependencia(DependenciaDTO dto) {
        DependenciaTempEntity DependenciaTempEntity = dependenciaRepository.obtenerPorId(dto.getIdDependencia());
        DependenciaTempEntity.setIdSector(dto.getIdSector());
        DependenciaTempEntity.setIdBase36(dto.getIdBase());
        DependenciaTempEntity.setDescripcion(dto.getDescripcion());
        dependenciaRepository.actualizar(DependenciaTempEntity);
        return obtenerDependenciaPorId(DependenciaTempEntity.getIdDependencia());
    }

    protected void eliminarDependencia(DependenciaDTO dto) {
        if (dto != null) {
            dependenciaRepository.eliminarPorId(dto.getIdDependencia());
        } else {
            LOGGER.warn("No se eliminado la dependencia porque es nula.");
        }
    }

    private static DependenciaDTO convertirEntidadADTO(DependenciaTempEntity entidad) {
        DependenciaDTO dto = new DependenciaDTO();

        dto.setIdSector(entidad.getIdSector());
        dto.setIdRamo(entidad.getIdRamo());
        dto.setIdEntePublico(entidad.getIdEntePublico());
        dto.setIdDependencia(entidad.getIdDependencia());
        dto.setIdClasificacionOrganismo(entidad.getIdClasificacionOrganismo());
        dto.setIdBase(entidad.getIdBase36());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    private static List<DependenciaDTO> convertirEntidadesADTOs(List<DependenciaTempEntity> entidades) {
        List<DependenciaDTO> dtos = new ArrayList<>();

        for (DependenciaTempEntity entidad : entidades) {
            DependenciaDTO dto = convertirEntidadADTO(entidad);

            dtos.add(dto);
        }

        return dtos;
    }
}
