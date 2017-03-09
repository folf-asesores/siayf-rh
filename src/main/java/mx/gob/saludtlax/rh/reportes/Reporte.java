/*
 * Reporte.java
 * Creado el 9/Sep/2016 1:37:04 PM
 * 
 */

package mx.gob.saludtlax.rh.reportes;

import java.io.InputStream;
import org.jboss.logging.Logger;

/**
 * Esta clase define a los reportes.
 * 
 * <p>Su función principal es ayudar a cargar la plantilla del reporte.</p>
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author Eduardo Mex
 */
public class Reporte {

    private final String nombreArchivo;
    private final String ruta;

    private static final Logger LOGGER = Logger.getLogger(Reporte.class.getName());

    /**
     * Crea una nueva instancia de un reporte.
     * 
     * @param nombreArchivo el nombre del reporte o plantilla.
     * @param ruta la ruta en la que se ubica el reporte o plantilla.
     */
    public Reporte(String nombreArchivo, String ruta) {
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
    }

    /**
     * Permite obtener el reporte o la plantilla como un {@link InputStream}.
     * 
     * @return si la ruta es correcta un <code>InputStream</code> con el reporte
     * o plantilla en caso contrario <code>null</code>.
     */
    public InputStream getInputStream() {
        LOGGER.debugv("Cargando archivo: {0}", nombreArchivo);
        InputStream is = getClass().getClassLoader().getResourceAsStream(ruta + nombreArchivo);

        if (is == null) {
            LOGGER.errorv("El archivo \"{0}\" no se encontro.", nombreArchivo);
        }

        LOGGER.debugv("El archivo \"{0}\" se ha cargado correctamente.", nombreArchivo);

        return is;
    }

}
