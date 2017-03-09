/*
 * AlmacenReportes.java
 * Creado el 9/Sep/2016 1:37:04 PM
 * 
 */

package mx.gob.saludtlax.rh.reportes;

/**
 * Esta interfaz define las caracteristicas deben tener los almacenes de reportes.
 * 
 * <p>
 * El almacen debe funcionar como su nombre como un depósito para poder 
 * identificar los reportes del sistema.
 * </p>
 * @author eduardo
 * @param <T> el tipo del reportes que llevará el almacen.
 *
 */
public interface AlmacenReportes<T extends Reporte> {

    /**
     * Obtiene una instancia con los detalles del reporte para su generación.
     * 
     * @param nombreReporte el nombre del reporte a obtener.
     * @return si el reporte existe devolvera una instancia con los detalles
     * del reporte, en caso contrario devolvera <code>null</code>.
     */
    T obtenerReporte(String nombreReporte);

    /**
     * Permite saber si dentro del almacen está disponible un reporte.
     * 
     * @param nombreReporte el nombre del reporte que se desea buscar.
     * @return <code>true</code> si el reporte existe en el almacen en caso 
     * contrario se devolvera <code>false</code>.
     */
    boolean extisteReporte(String nombreReporte);

}
