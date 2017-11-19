/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/09/2016 17:37:19
 *
 */
@Stateless
public class InterinatosBean implements Interinatos {

    @Inject
    private InterinatoService interinatoService;

    @Override
    public List<DisponiblesInterinatoDTO> consultarCandidatosInterinato(Integer tipoBusqueda) {
        return interinatoService.consultarDisponiblesInterinato(tipoBusqueda);
    }

    @Interceptors({ InterinatoValidator.class })
    @Override
    public void solicitarInterinato(InterinatoDTO interinato) {
        interinatoService.crearSolicitudInterinato(interinato);

    }

}
