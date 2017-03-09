/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.historialpago;

import java.util.List;


/**
 * @author Eduardo Mex
 *
 */
public interface HistorialPago {
	
	List<HistorialPagoDetalleDTO> obtenerListaHistorialPagoPorIdEmpleado(Integer idEmpleado);

}
