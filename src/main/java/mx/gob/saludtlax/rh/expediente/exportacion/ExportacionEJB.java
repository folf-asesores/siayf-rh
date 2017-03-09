/*
 * ExportacionEJB.java
 * Creado el Sep 9, 2016 2:48:23 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Stateless
public class ExportacionEJB implements Exportacion {
    @Inject private ExportacionService exportacionService;

    @Override
    public ExportacionDTO obtenerPorId(Integer idAdjunto) {
        return exportacionService.obtenerPorIdAdjunto(idAdjunto);
    }
    
    @Override
    public List<Integer> consultarIdsPaginado(int cantidad, int inicio) {
        return exportacionService.consultarIdsPaginado(cantidad, inicio);
    }
    
    @Override
    public List<ExportacionDTO> consultarPaginado(int cantidad, int inicio){
        return exportacionService.consultarPaginado(cantidad, inicio);
    }

}
