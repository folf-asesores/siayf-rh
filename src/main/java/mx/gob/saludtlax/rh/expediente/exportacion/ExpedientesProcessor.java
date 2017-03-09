/*
 * ExpedientesProcessor.java
 * Creado el Sep 5, 2016 12:24:12 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named("expedientesProcessor")
public class ExpedientesProcessor implements ItemProcessor {
    
    private static final Logger LOGGER = Logger.getLogger(ExpedientesProcessor.class.getName());
    
    @Override
    public Object processItem(Object item) throws Exception {
        LOGGER.info("Procesando item");

        return item;
    }
}
