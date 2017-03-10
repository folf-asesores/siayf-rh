/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public interface CalendarioGlobal {

	public void creaCalendarioGlobal(CalendarioGlobalDTO dto);

	public void actualizarCalendarioGlobal(CalendarioGlobalDTO dto);

	public void eliminarCalendarioGlobal(Integer idCalendarioGlobal);

	public List<CalendarioGlobalDTO> obtenerListaCalendarioGlobal();

}
