/*
 * PlantillaMensaje.java
 * Creado el 16/Mar/2017 11:58:39 AM
 * 
 */
package mx.gob.saludtlax.rh.util;

import java.text.MessageFormat;
import org.jboss.logging.Logger;

/**
 * Esta clase contiene las plantillas de mensajes que principalmente se emplean
 * para enviar al log.
 * 
 * @see MessageFormat
 * @see Logger#infov(java.lang.String, java.lang.Object...)
 * @see Logger#warnv(java.lang.String, java.lang.Object...)
 * @see Logger#errorv(java.lang.String, java.lang.Object...)
 * @see Logger#debugv(java.lang.String, java.lang.Object...)
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class PlantillaMensaje {
    
    public static final String SQL_ERROR_MESSAGE =
        "\n\tSQLException: {0}\n\tSQLState: {1}\n\tVendorError: {2}";
    public static String REPORTE_ERROR_BEAN_NO_ENCONTRADO = 
            "Error al buscar el bean: {0}\t{1}";

    private PlantillaMensaje () {
    }
    
}
