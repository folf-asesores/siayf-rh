/*
 * ExportacionService.java
 * Creado el Sep 9, 2016 2:50:37 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntosEmpleadosOldEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntosEmpleadosOldRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ExportacionService {
    
    @Inject private InformacionAdjuntosEmpleadosOldRepository iaeor;

    protected ExportacionDTO obtenerPorIdAdjunto(Integer idAdjunto) {
        return  convertirEntidadADTO(iaeor.obtenerPorIdAdjunto(idAdjunto));
    }    
    
    protected List<Integer> consultarIdsPaginado(int cantidad, int inicio) {
        return iaeor.consultarIdsPaginado(cantidad, inicio);
    }

    protected List<ExportacionDTO> consultarPaginado(int cantidad, int inicio){
        return convertirEntidadesADTOs(iaeor.consultarPaginado(cantidad, inicio));
    }
    
    
    private ExportacionDTO convertirEntidadADTO(InformacionAdjuntosEmpleadosOldEntity entidad) {
        ExportacionDTO dto = new ExportacionDTO();
        
        dto.setIdAdjunto(entidad.getIdInformacionAdjuntoEmpleado());
        dto.setNombreAdjunto(entidad.getNombreAdjunto());
        dto.setExtension(entidad.getExtension());
        dto.setAdjunto(entidad.getAdjunto().getArchivo());
        dto.setEntidadContexto(entidad.getEntidadContexto());
        dto.setIdEntidadContexto(entidad.getIdEntidadContexto());
        dto.setDocumentoAdjuntable(new DocumentoAdjuntableDTO(entidad.getDocumentoAdjuntable().getIdDocumentoAdjuntable()));
        dto.setIdEmpleado(entidad.getEmpleado().getIdEmpleado());
        dto.setIdExpediente(entidad.getExpediente().getIdExpedienteEmpleado());
        
        return dto;
    }

    private List<ExportacionDTO> convertirEntidadesADTOs(List<InformacionAdjuntosEmpleadosOldEntity> entidades) {
        List<ExportacionDTO> dtos = new ArrayList<>();
        
        for(InformacionAdjuntosEmpleadosOldEntity entidad : entidades) {
            ExportacionDTO dto = convertirEntidadADTO(entidad);
            dtos.add(dto);
        }
        
        return dtos;
    }

}
