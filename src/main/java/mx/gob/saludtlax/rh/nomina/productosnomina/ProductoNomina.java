/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.List;

import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;

/**
 * @author Eduardo Mex
 *
 */
public interface ProductoNomina {
	
	
	List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProducto(Integer idProducto);

}
