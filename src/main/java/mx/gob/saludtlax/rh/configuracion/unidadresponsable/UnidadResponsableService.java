package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempRepository;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableRepository;

public class UnidadResponsableService {

	
    @Inject private UnidadResponsableRepository unidadResponsableRepository;
    
    @Inject private DependenciaTempRepository dependenciaTempRepository;

    public List<UnidadResponsableDTO> listaUnidadResponsable() {
        List<UnidadResponsableEntity> unidadesResponsables = unidadResponsableRepository.consultarUnidadesResponsables();
        List<UnidadResponsableDTO> result = convertirEntidadesADTOs(unidadesResponsables);
                
        return result;
    }
	
    protected UnidadResponsableDTO nuevoUnidadResponsable() {
        UnidadResponsableDTO  dto = new UnidadResponsableDTO ();
        dto.setDescripcion(null);
        dto.setDependencia(null);
        dto.setIdUnidadXDependencia(null);
        dto.setIdBase36(null);
        return dto;
    }
	
    protected UnidadResponsableDTO crearUnidadResponsable(UnidadResponsableDTO dto) {
        UnidadResponsableEntity entity = new UnidadResponsableEntity();
        entity.setIdDependencia(dto.getDependencia().getIdDependencia());
        entity.setIdUnidadXDependencia(dto.getIdUnidadXDependencia());
        entity.setIdBase36(dto.getIdBase36());
        entity.setDescripcion(dto.getDescripcion());
        unidadResponsableRepository.crear(entity);
        return obtenerUnidadResponsablePorId(entity.getIdUnidadResponsable());
    }
	
    protected UnidadResponsableDTO obtenerUnidadResponsablePorId(Integer idUnidadResponsable) {
        UnidadResponsableEntity unidadResponsable = unidadResponsableRepository.obtenerPorId(idUnidadResponsable);
        
        return convertirEntidadADTO(unidadResponsable);
    }

    protected List<String> consultarDescripcionUnidadesResponsablesPorCriterio(String consulta) {
        return unidadResponsableRepository.consultarDescripcionUnidadesResponsablesPorCriterio(consulta);
    }
        
    protected Integer consultarIdUnidadResponsablePorDescripcion(String descripcion) {
        return unidadResponsableRepository.consultarIdUnidadResponsablePorDescripcion(descripcion);
    }
        
    protected UnidadResponsableDTO actualizarUnidadResponsable(UnidadResponsableDTO dto) {
        UnidadResponsableEntity entity = unidadResponsableRepository.obtenerPorId(dto.getIdUnidadResponsable());
        entity.setIdDependencia(dto.getDependencia().getIdDependencia());
        entity.setIdUnidadXDependencia(dto.getIdUnidadXDependencia());
        entity.setIdBase36(dto.getIdBase36());
        entity.setDescripcion(dto.getDescripcion());
        unidadResponsableRepository.actualizar(entity);
        return obtenerUnidadResponsablePorId(entity.getIdUnidadResponsable());
    }

    protected void eliminarUnidadResponsable(UnidadResponsableDTO dto) {
        unidadResponsableRepository.eliminarPorId(dto.getIdUnidadResponsable());
    }

    private  List<UnidadResponsableDTO> convertirEntidadesADTOs(List<UnidadResponsableEntity> entidades) {
        List<UnidadResponsableDTO> dtos = new ArrayList<>();
        
        for (UnidadResponsableEntity entidad : entidades) {
            UnidadResponsableDTO dto = convertirEntidadADTO(entidad);
            
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    private UnidadResponsableDTO convertirEntidadADTO(UnidadResponsableEntity entidad) {
        UnidadResponsableDTO dto = new UnidadResponsableDTO();
        
        dto.setIdUnidadResponsable(entidad.getIdUnidadResponsable());
       
        DependenciaTempEntity depEntity = dependenciaTempRepository.obtenerPorId(entidad.getIdDependencia());
        
        DependenciaDTO dependenciaDto = new DependenciaDTO();
        dependenciaDto.setDescripcion(depEntity.getDescripcion());
        dependenciaDto.setIdBase(depEntity.getIdBase36());
        dependenciaDto.setIdClasificacionOrganismo(depEntity.getIdClasificacionOrganismo());
        dependenciaDto.setIdDependencia(depEntity.getIdDependencia());
        dependenciaDto.setIdEntePublico(depEntity.getIdEntePublico());
        dependenciaDto.setIdRamo(depEntity.getIdRamo());
        dependenciaDto.setIdSector(depEntity.getIdSector());
       
        
        dto.setDependencia(dependenciaDto);
        dto.setIdUnidadXDependencia(entidad.getIdUnidadXDependencia());
        dto.setIdBase36(entidad.getIdBase36());
        dto.setDescripcion(entidad.getDescripcion());
        
        return dto;
    }
}