/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.List;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;

/**
 * @author Eduardo Mex
 *
 */
public interface ProductoNomina extends Serializable {
	
    List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProductoEstatus(Integer idProducto, Integer estatus);

    List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProducto(Integer idProducto);

    /**
     * Permite obtener el reporte del producto de nomina para las suplencias.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @return un arreglo de bytes que representa el archivo con la información
     * del reporte del producto de nomina para las suplencias.
     */
    byte [] obtenerReporteProductoNominaSuplencia(Integer idProductoNomina) throws ReglaNegocioException;
}
