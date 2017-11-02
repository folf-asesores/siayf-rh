/*
 *  NotificadorErroresService.java
 *  Creado el Jun 16, 2016 4:49:26 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.CorreoNotificacionEntity;
import mx.gob.saludtlax.rh.persistencia.CorreoNotificacionRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NotificadorErroresService implements Serializable {

    private static final long serialVersionUID = 3800931464086025686L;

    @Inject private CorreoNotificacionRepository correoNotificacionRepository;

    protected void crear(CorreoNotificacionDTO correo) {
        if(correoNotificacionRepository.existeCorreo(correo.getCorreoElectronico())) {
            throw new ReglaNegocioException("El correo electrónico que intenta registrar ya existe", ReglaNegocioCodigoError.CORREO_NOTIFICACION_DUPLICADO);
        }
        
        CorreoNotificacionEntity correoEntity = convertirDTOAEntidad(correo, new CorreoNotificacionEntity());
        correoNotificacionRepository.crear(correoEntity);
    }
    
    protected void actualizar(CorreoNotificacionDTO correo) {
        CorreoNotificacionEntity correoEntity = correoNotificacionRepository.obtenerPorId(correo.getIdCorreoNotificacion());
        convertirDTOAEntidad(correo, correoEntity);
        correoNotificacionRepository.actualizar(correoEntity);
    }

    protected List<CorreoNotificacionDTO> consutarCorreosNotificacion() {
        List<CorreoNotificacionEntity> entidades = correoNotificacionRepository.consultarTodos();
        return convertirEntidadesADTOs(entidades);
    }
    
    private CorreoNotificacionEntity convertirDTOAEntidad(CorreoNotificacionDTO correo, CorreoNotificacionEntity correoNotificacionEntity) {
        if(correo == null){
            throw new NullPointerException("No se puede realizar la conversión de un DTO nullo");
        }
        
        if(correoNotificacionEntity == null){
            correoNotificacionEntity = new CorreoNotificacionEntity();
            correoNotificacionEntity.setIdCorreoNotificacion(null);
        }
        
        correoNotificacionEntity.setNickname(correo.getAlias());
        correoNotificacionEntity.setCorreoElectronico(correo.getCorreoElectronico());
        return correoNotificacionEntity;
    }
    
    protected void eliminar(int idCorreoNotificacion) {
        CorreoNotificacionEntity correo = correoNotificacionRepository.obtenerPorId(idCorreoNotificacion);
        correoNotificacionRepository.eliminar(correo);
    }

    private CorreoNotificacionDTO convertirEntidadADTO(CorreoNotificacionEntity entidad) {
        CorreoNotificacionDTO dto = new CorreoNotificacionDTO();
        dto.setIdCorreoNotificacion(entidad.getIdCorreoNotificacion());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setAlias(entidad.getNickname());
        return dto;
    }

    private List<CorreoNotificacionDTO> convertirEntidadesADTOs(List<CorreoNotificacionEntity> entidades) {
        List<CorreoNotificacionDTO> dtos = new ArrayList<>();

        for(CorreoNotificacionEntity entidad : entidades) {
            CorreoNotificacionDTO dto = convertirEntidadADTO(entidad);
            dtos.add(dto);
        }
        
        return dtos;
    }

}
