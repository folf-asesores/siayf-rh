/*
 * ExpedientesReader.java
 * Creado el Sep 5, 2016 12:28:30 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named("expedientesReader")
public class ExpedientesReader extends AbstractItemReader {

    private static final Logger LOGGER = Logger.getLogger(ExpedientesReader.class.getName());

    @Inject private Exportacion exportacionEJB;
    @Inject private JobContext jobContext;
    
    private Iterator<Integer> iterator;    
    
    @Override
    public void open(Serializable e) throws Exception {
        LOGGER.info("Iniciando reader");
        
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        Integer posicion = Integer.parseInt((String) jobParameters.get("posicion"));
		Integer tamanyo = Integer.parseInt((String) jobParameters.get("tamanyo"));

        List<Integer> ids = exportacionEJB.consultarIdsPaginado(tamanyo, posicion);
        
        iterator = ids != null ? ids.iterator() : null;
    }
    
    @Override
    public Object readItem() throws Exception {
        if(iterator.hasNext()){
            Integer idAdjunto = iterator.next();
            LOGGER.infov("Leyendo idAdjunto: {0}", idAdjunto);
            try {
                return exportacionEJB.obtenerPorId(idAdjunto);
            } catch(NoResultException nre) {
                LOGGER.errorv("No se encontro la informacion del idAdjunto: {0}", idAdjunto);
                return new ExportacionDTO();
            }
        } else {
            LOGGER.info("No hay más adjuntos por leer.");
            return null;
        }
    }
    
}
