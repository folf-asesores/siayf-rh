/*
 * ExpedientesWriter.java
 * Creado el Sep 5, 2016 12:31:47 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.ejb.EJB;
import javax.inject.Named;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named("expedientesWriter")
public class ExpedientesWriter extends AbstractItemWriter {
    
    private static final Logger LOGGER = Logger.getLogger(ExpedientesWriter.class.getName());

    
    @EJB
    private AdjuntoEmpleado adjuntoEmpleadoEJB;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        super.open(checkpoint);
        LOGGER.info("Iniciando la escritura");
    }
    
    @Override
    public void writeItems(List<Object> items) throws Exception {
        LOGGER.info("Escribiendo items");
        
        for (Object obj : items) {
            ExportacionDTO exportacionDTO = ((ExportacionDTO) obj);
            LOGGER.infov("ID (old): {0}, Nombre Adjunto: {1}, ID expediente: {2}", 
                    exportacionDTO.getIdAdjunto(), 
                    exportacionDTO.getNombreAdjunto(),
                    exportacionDTO.getIdExpediente());
            try {
                adjuntoEmpleadoEJB.crear(exportacionDTO, exportacionDTO.getAdjunto());
            } catch(ValidacionException ve ) {
                LOGGER.error(ve.getMessage());
                LOGGER.errorv("ID (old): {0}, Nombre Adjunto: {1}, ID expediente: {2}", 
                    exportacionDTO.getIdAdjunto(), 
                    exportacionDTO.getNombreAdjunto(),
                    exportacionDTO.getIdExpediente());
            }
        }
    }
}
