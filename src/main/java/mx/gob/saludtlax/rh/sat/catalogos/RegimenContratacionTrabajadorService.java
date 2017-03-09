/*
 *  RegimenContratacionTrabajadorService.java
 *  Creado el May 25, 2016 2:00:45 PM
 * 
 */
package mx.gob.saludtlax.rh.sat.catalogos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.RegimenContratacionTrabajadorEntity;
import mx.gob.saludtlax.rh.persistencia.RegimenContratacionTrabajadorRepository;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class RegimenContratacionTrabajadorService {
    private static final Logger LOGGER = Logger.getLogger(RegimenContratacionTrabajadorService.class.getName());
    
    @Inject private RegimenContratacionTrabajadorRepository regimenContratacionTrabajadorRepository;
    
    /**
     * 
     * @param regimenContratacionTrabajadorDTO
     * @return 
     */
    protected int crear(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO) {
        RegimenContratacionTrabajadorEntity entidad = convertirDTOAEntidad(regimenContratacionTrabajadorDTO);
        regimenContratacionTrabajadorRepository.crear(entidad);
        
        return entidad.getClave();
    }

    /**
     * 
     * @param regimenContratacionTrabajadorDTO 
     */
    protected void actualizar(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO) {
        RegimenContratacionTrabajadorEntity entidad = convertirDTOAEntidad(regimenContratacionTrabajadorDTO);
        regimenContratacionTrabajadorRepository.actualizar(entidad);
    }
    
    /**
     * 
     * @param idRegimenContratacionTrabajador
     * @return 
     */
    protected RegimenContratacionTrabajadorDTO obtenerPorId(int idRegimenContratacionTrabajador) {
        RegimenContratacionTrabajadorEntity entidad = regimenContratacionTrabajadorRepository.obtenerPorId(idRegimenContratacionTrabajador);
        RegimenContratacionTrabajadorDTO dto = convertirEntidadADTO(entidad);
        
        return dto;
    }
    
    protected List<RegimenContratacionTrabajadorDTO> obtenerRegimenContratacionTrabajadores() {
        List<RegimenContratacionTrabajadorEntity> entidades = regimenContratacionTrabajadorRepository.obtenerRegimenContratacionTrabajadores();
        List<RegimenContratacionTrabajadorDTO> dtos = convertirEntidadesADTOs(entidades);
        
        return dtos;
    }
    
    protected void eliminar(int idRegimenContratacionTrabajador) {
        RegimenContratacionTrabajadorEntity entidad = regimenContratacionTrabajadorRepository.obtenerPorId(idRegimenContratacionTrabajador);
        
        if(entidad != null){
            regimenContratacionTrabajadorRepository.eliminar(entidad);
        } else {
            LOGGER.warnv("El ID {0} no se ha encontrado. Por ello no se pudo realizar la eliminación.", idRegimenContratacionTrabajador);
        }
    }
    /**
     * 
     * @param dto
     * @return 
     */
    private static RegimenContratacionTrabajadorEntity convertirDTOAEntidad(RegimenContratacionTrabajadorDTO dto) {
        RegimenContratacionTrabajadorEntity entidad = new RegimenContratacionTrabajadorEntity();
        entidad.setClave(dto.getClave());
        entidad.setDescripcion(dto.getDescripcion());
        
        return entidad;
    }

    /**
     * 
     * @param entidad
     * @return 
     */
    private static RegimenContratacionTrabajadorDTO convertirEntidadADTO(RegimenContratacionTrabajadorEntity entidad) {
        RegimenContratacionTrabajadorDTO dto = new RegimenContratacionTrabajadorDTO();
        dto.setClave(entidad.getClave());
        dto.setDescripcion(entidad.getDescripcion());
        
        return dto;
    }

    /**
     * 
     * @param entidades
     * @return 
     */
    private static List<RegimenContratacionTrabajadorDTO> convertirEntidadesADTOs(List<RegimenContratacionTrabajadorEntity> entidades) {
        List<RegimenContratacionTrabajadorDTO> dtos = new ArrayList<>();
        
        for (RegimenContratacionTrabajadorEntity entidad : entidades) {
            RegimenContratacionTrabajadorDTO dto = convertirEntidadADTO(entidad);
            
            dtos.add(dto);
        }
        
        return dtos;
    }
}
