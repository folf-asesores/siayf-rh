/*
 * BitacoraReporte.java
 * Creado el 9/Sep/2016 1:37:04 PM
 *
 */

package mx.gob.saludtlax.rh.reportes;

import java.util.Map;

import javax.ejb.Local;

/**
 * Esta interfaz define las operaciones para registrar en la bitácora la
 * generación de los reportes.
 *
 * @see AdministradorReportes
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 * @author Eduardo Mex
 */
@Local
public interface BitacoraReporte {

    /**
     * Guarda en el almacen de datos los parámetros del reporte que se va a
     * generar y luego retorna una referencia (ticket) para poder generar el
     * reporte.
     *
     * @param parametros
     *            los parámetros que requiere el reporte para su
     *            generación.
     * @return una referencia para generar el reporte.
     */
    String obtenerReferencia(Map<String, String> parametros);

    /**
     * Permite conocer los parámetros del reporte a través de la referencia (ticket).
     *
     * @param referencia
     *            la referencia que se genera al guardar los parámetros
     *            en la bitácora.
     * @return si la referencia es valida retorna un {@link Map} con los
     *         parámetros en caso contrario devuelve <code>null</code>.
     */
    Map<String, String> obtenerParametros(String referencia);

    /**
     * Permite conocer el nombre del reporte que se generar con la refencia
     * (ticket).
     *
     * @param referencia
     *            la referencia que se genera al guardar los parámetros
     *            en la bitácora.
     * @return si la referencia es valida se devolverá el nombre del reporte en
     *         caso contrario devuelve <code>null</code>.
     */
    String obtenerNombreReporte(String referencia);

    /**
     * Permite conocer el tipo de reporte que se puede generar según la
     * referencia (ticket).
     *
     * @param referencia
     *            la referencia que se genera al guardar los parámetros
     *            en la bitácora.
     * @return si la referencia es valida se devolverá el tipo de reporte en
     *         caso contrario devuelve <code>null</code>.
     */
    String obtenerTipoReporte(String referencia);

}
