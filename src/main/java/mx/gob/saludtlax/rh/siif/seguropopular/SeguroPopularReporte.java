/*
 * SeguroPopularReporte.java
 * Creado el 09/Dec/2016 5:38:10 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.seguropopular;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Local
public interface SeguroPopularReporte extends Serializable {

    /**
     * Genera y devuelve un arreglo de bytes que representan el reporte de
     * seguro popular en formato Excel.
     * 
     * @return un arreglo de bytes que representan el reporte de seguro popular.
     */
    byte[] obtenerReporte();

    /**
     * Genera y devuelve un arreglo de bytes que representan el reporte de
     * seguro popular en formato Excel.
     * 
     * @param anyo el periodo (año) 
     * @param quincena la quincena
     * @return 
     */
    byte[] obtenerReporte(String anyo, Integer quincena);
    
}
