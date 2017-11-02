/*
 * PagoGeneralReporte.java
 * Creado el 15/Feb/2017 5:20:54 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface PagoGeneralReporte extends Serializable {

    byte [] generarReporte(Integer idProductoNomina);

}
