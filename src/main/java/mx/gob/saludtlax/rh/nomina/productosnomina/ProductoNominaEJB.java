/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class ProductoNominaEJB implements Serializable, ProductoNomina{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3124501000578420524L;
	
	@Inject
	private ProductoNominaService productoNominaService;

	@Override
	public List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProducto(Integer idProducto) {
		
		return productoNominaService.obtenerListaProductoNominaPorIdProducto(idProducto);
	}
	
	

}
