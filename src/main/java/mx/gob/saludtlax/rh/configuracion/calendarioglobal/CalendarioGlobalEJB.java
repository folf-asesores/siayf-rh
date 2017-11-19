/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class CalendarioGlobalEJB implements Serializable, CalendarioGlobal {

    /**
     *
     */
    private static final long serialVersionUID = 419992812652433745L;

    @Inject
    private CalendarioGlobalService calendarioGlobalService;

    @Override
    public void creaCalendarioGlobal(CalendarioGlobalDTO dto) {
        calendarioGlobalService.creaCalendarioGlobal(dto);
    }

    @Override
    public void actualizarCalendarioGlobal(CalendarioGlobalDTO dto) {
        calendarioGlobalService.actualizarCalendarioGlobal(dto);
    }

    @Override
    public void eliminarCalendarioGlobal(Integer idCalendarioGlobal) {
        calendarioGlobalService.eliminarCalendarioGlobal(idCalendarioGlobal);
    }

    @Override
    public List<CalendarioGlobalDTO> obtenerListaCalendarioGlobal() {

        return calendarioGlobalService.obtenerListaCalendarioGlobal();
    }

}
