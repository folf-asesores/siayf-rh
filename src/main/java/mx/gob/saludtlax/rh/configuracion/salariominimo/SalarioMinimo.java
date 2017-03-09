/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public interface SalarioMinimo {

	public void crearSalarioMinimo(SalarioMinimoDTO dto);

	public void actualizarSalarioMinimo(SalarioMinimoDTO dto);

	public void eliminarSalarioMinimo(Integer idSalarioMinimo);

	public List<SalarioMinimoDTO> obtenerListaSalarioMinimo();

}
