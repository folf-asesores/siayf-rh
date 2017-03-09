/*
 * ProyectoService.java
 * Creado el 23/07/2016 09:40:16 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.ProyectoEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ProyectoService {

    @Inject private ProyectoRepository proyectoRepository;
    
    protected ProyectoDTO obtenerPorId(int idProyecto) {
        ProyectoEntity entidad = proyectoRepository.obtenerPorId(idProyecto);
        
        return convertirEntidadADTO(entidad);
    }
    
    protected int crear(ProyectoDTO dto) {
        if(dto == null) {
            throw new ValidationException("Proyecto no debe ser nulo.", 
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        if(ValidacionUtil.esCadenaVacia(dto.getNombre())) {
            throw new ValidationException("Proyecto no debe ser nulo.", 
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        if(ValidacionUtil.esMenorQueUno(dto.getEjercicioFiscal())) {
            throw new ValidationException("El ejercicio fiscal no puede ser"
                    + " nulo, cero o negativo.", 
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        if(proyectoRepository.existeProyectoEnEjercicioFiscal(dto.getNombre(),
                dto.getEjercicioFiscal())) {
            throw new ReglaNegocioException("Ya existe un proyecto con el mismo"
                    + " nombre en el ejercicio fiscal (" 
                    + dto.getEjercicioFiscal() + ").", 
                    ReglaNegocioCodigoError.PROYECTO_DUPLICADO);
        }
        
        ProyectoEntity entidad = convertirDTOAEntidad(dto);
        entidad.setIdProyecto(null);
        ProyectoEntity proyecto = proyectoRepository.crear(entidad);
        
        return proyecto.getIdProyecto();
    }
    
    protected void actualizar(ProyectoDTO dto) {
        ProyectoEntity entidad = convertirDTOAEntidad(dto);
        proyectoRepository.actualizar(entidad);
    }
    
    protected List<ProyectoDTO> consultarProyectosPorEjercicioFiscal(int ejercicioFiscal) {
        List<ProyectoEntity> entidades = proyectoRepository.consultarProyectosPorEjercicioFiscal(ejercicioFiscal);
        
        return convertirEntidadesADTOs(entidades);
    }

    protected void eliminar(int idProyecto) {
        proyectoRepository.eliminarPorId(idProyecto);
    }
    
    private static ProyectoDTO convertirEntidadADTO(ProyectoEntity entidad) {
        ProyectoDTO dto = new ProyectoDTO();

        dto.setIdProyecto(entidad.getIdProyecto());
        dto.setIdAreaAdscripcion(entidad.getIdAreaAdscripcion());
        dto.setIdDependencia(entidad.getIdDependencia());
        dto.setIdEstrategia(entidad.getIdEstrategia());
        dto.setIdLineaAccion(entidad.getIdLineaAccion());
        dto.setIdSector(entidad.getIdSector());
        dto.setIdUnidadResponsable(entidad.getIdUnidadResponsable());
        dto.setNombre(entidad.getNombre());
        dto.setBase36(entidad.getBase36());
        dto.setClave(entidad.getClave());
        dto.setClaveAreaAdscripcion(entidad.getClaveAreaAdscripcion());
        dto.setEjercicioFiscal(entidad.getEjercicioFiscal());
        
        return dto;
    }


    private ProyectoEntity convertirDTOAEntidad(ProyectoDTO dto) {
        ProyectoEntity entidad = new ProyectoEntity();
        
        entidad.setIdProyecto(dto.getIdProyecto());
        entidad.setIdAreaAdscripcion(dto.getIdAreaAdscripcion());
        entidad.setIdDependencia(dto.getIdDependencia());
        entidad.setIdEstrategia(dto.getIdEstrategia());
        entidad.setIdLineaAccion(dto.getIdLineaAccion());
        entidad.setIdSector(dto.getIdSector());
        entidad.setIdUnidadResponsable(dto.getIdUnidadResponsable());
        entidad.setNombre(dto.getNombre());
        entidad.setBase36(dto.getBase36());
        entidad.setClave(dto.getClave());
        entidad.setClaveAreaAdscripcion(dto.getClaveAreaAdscripcion());
        entidad.setEjercicioFiscal(dto.getEjercicioFiscal());
        
        return entidad;
    }

    private List<ProyectoDTO> convertirEntidadesADTOs(List<ProyectoEntity> entidades) {
        List<ProyectoDTO> dtos = new ArrayList<>();
        
        for (ProyectoEntity entidad : entidades) {
            ProyectoDTO dto = convertirEntidadADTO(entidad);
            dtos.add(dto);
        }
        
        return dtos;
    }
}
