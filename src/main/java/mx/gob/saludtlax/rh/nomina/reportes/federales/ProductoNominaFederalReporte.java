/*
 * ProductoNominaFederalReporte.java
 * Creado el 16/Mar/2017 11:10:36 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.federales;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Local
public interface ProductoNominaFederalReporte extends Serializable {
    
    /**
     * Permite obtener el reporte de producto de nómina, como un arreglo de bytes.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @return Un arreglo de bytes que representa el un archivo de Excel con el
     * reporte de producto de nóminas de empleados federales.
     */
    byte [] generarReporte(Integer idProductoNomina);    
}
