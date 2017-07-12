/*
 * PrenominaReporte.java
 * Creado el 11/Jul/2017 6:51:06 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface PrenominaReporte {

    byte[] generarReporte(Integer idProductoNomina);
}
