/*
 * Dispersion.java
 * Creado el 07/Dec/2016 6:34:41 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.Serializable;
import javax.ejb.Local;

/**
 * Esta interfaz describe lo necesario para la generación del reporte de 
 * disperción de nómina.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Dispersion extends Serializable {

    /**
     * Permite generar el reporte de dispersión de nómina apartir del ID del
     * producto de nómina.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @return un arreglo de bytes que representan el archivo de texto plano con
     *         el reporte de dispersión de nómina.
     */
    byte[] generarReporte(Integer idProductoNomina);
    
    /**
     * Permite generar el reporte de dispersión de nómina apartir del ID del
     * producto de nómina.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @param excel si el archivo se generará como un archivo de Excel
     * @return un arreglo de bytes que representan el archivo de Excel con
     *         el reporte de dispersión de nómina.
     */
    byte[] generarReporte(Integer idProductoNomina, boolean excel);
}
