/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class HistorialPagoEJB implements HistorialPago , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7934794779526103095L;
	
	@Inject
	private HistorialPagoService historialPagoService;

	@Override
	public List<HistorialPagoDetalleDTO> obtenerListaHistorialPagoPorIdEmpleado(Integer idEmpleado) {
		
		return historialPagoService.obtenerListaHistorialPagoPorIdEmpleado(idEmpleado);
	}

}
