/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaProgramasExcelDTO;
import mx.gob.saludtlax.rh.reporteslaborales.productonomina.ProductoNominaExcel;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class ProductoNominaEJB implements ProductoNomina {

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
    
    @Override
    public List<ProductosNominaProgramasExcelDTO> obtenerListaProductoNominaProgramasPorIdProducto(Integer idProducto) {
        return productoNominaService.obtenerListaProductoNominaProgramasPorIdProducto(idProducto);
    }

    @Override
    public byte[] obtenerReporteProductoNominaSuplencia(Integer idProductoNomina) throws ReglaNegocioException {
        List<ProductosNominaExcelDTO> productoNomina = productoNominaService.obtenerListaProductoNominaSuplenciaPorIdProducto(idProductoNomina);
        
        if(productoNomina != null && !productoNomina.isEmpty()) {
            ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();
            return productoNominaExcel.generar(productoNomina);
        } else {
            throw new ReglaNegocioException(
                    "No se encontrarón resultados con el identificador del producto nomina: "
                            + idProductoNomina.toString(),
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
    }

    @Override
    public List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProductoEstatus(Integer idProducto,
                    Integer estatus) {
        return productoNominaService.obtenerListaProductoNominaPorIdProductoEstatus(idProducto, estatus);
    }

}
