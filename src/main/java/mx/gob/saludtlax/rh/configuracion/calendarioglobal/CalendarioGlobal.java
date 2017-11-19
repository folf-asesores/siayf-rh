/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface CalendarioGlobal {

    public void creaCalendarioGlobal(CalendarioGlobalDTO dto);

    public void actualizarCalendarioGlobal(CalendarioGlobalDTO dto);

    public void eliminarCalendarioGlobal(Integer idCalendarioGlobal);

    public List<CalendarioGlobalDTO> obtenerListaCalendarioGlobal();

}
