/*
 * ComprobanteEmpleado.java
 * Creado el 22/nov/2016 4:20:15 AM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Local
public interface ComprobanteEmpleado extends Serializable {

    byte[] generarReporte(Integer idProductoNomina);
    
}
