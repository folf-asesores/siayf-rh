package mx.gob.saludtlax.rh.configuracion.dependencia;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.DependenciaRepository;
import mx.gob.saludtlax.rh.persistencia.DependenciaEntity;

import org.jboss.logging.Logger;

public class DependenciaService {

    private static final Logger LOGGER = Logger.getLogger(DependenciaService.class.getName());
    
    @Inject
    private DependenciaRepository dependenciaRepository;

    public List<DependenciaDTO> listaDependencia() {
        List<DependenciaEntity> dependencias = dependenciaRepository.consultarDependencias();
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
        DependenciaEntity entity = new DependenciaEntity();
        entity.setIdRamo(4);
        entity.setIdClasificacionOrganismo(3);
        entity.setIdEntePublico(9);
        entity.setIdSector(dto.getIdSector());
        entity.setIdBase(dto.getIdBase());
        entity.setDescripcion(dto.getDescripcion());
        dependenciaRepository.crear(entity);
        return obtenerDependenciaPorId(entity.getIdDependencia());
    }

    protected DependenciaDTO obtenerDependenciaPorId(Integer idDependencia) {
        DependenciaEntity entidad = dependenciaRepository.obtenerPorId(idDependencia);
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
        DependenciaEntity dependenciaEntity = dependenciaRepository.obtenerPorId(dto.getIdDependencia());
        dependenciaEntity.setIdSector(dto.getIdSector());
        dependenciaEntity.setIdBase(dto.getIdBase());
        dependenciaEntity.setDescripcion(dto.getDescripcion());
        dependenciaRepository.actualizar(dependenciaEntity);
        return obtenerDependenciaPorId(dependenciaEntity.getIdDependencia());
    }

    protected void eliminarDependencia(DependenciaDTO dto) {
        if(dto != null) {
            dependenciaRepository.eliminarPorId(dto.getIdDependencia());
        } else {
            LOGGER.warn("No se eliminado la dependencia porque es nula.");
        }
    }

    private static DependenciaDTO convertirEntidadADTO(DependenciaEntity entidad) {
        DependenciaDTO dto = new DependenciaDTO();
        
        dto.setIdSector(entidad.getIdSector());
        dto.setIdRamo(entidad.getIdRamo());
        dto.setIdEntePublico(entidad.getIdEntePublico());
        dto.setIdDependencia(entidad.getIdDependencia());
        dto.setIdClasificacionOrganismo(entidad.getIdClasificacionOrganismo());
        dto.setIdBase(entidad.getIdBase());
        dto.setDescripcion(entidad.getDescripcion());
        
        return dto;
    }

    private static List<DependenciaDTO> convertirEntidadesADTOs(List<DependenciaEntity> entidades) {
        List<DependenciaDTO> dtos = new ArrayList<>();
        
        for (DependenciaEntity entidad : entidades) {
            DependenciaDTO dto = convertirEntidadADTO(entidad);
            
            dtos.add(dto);
        }
        
        return dtos;
    }
}
