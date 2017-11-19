/*
 *
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class FormatoFiliacionEJB implements FormatoFiliacion {

    @Inject
    private FormatoFiliacionService formatoFiliacionService;

    @Override
    public Integer crearFormatoFiliacion(
            FormatoFiliacionDTO formatoFiliacionDTO) {
        return formatoFiliacionService
                .crearFormatoFiliacion(formatoFiliacionDTO);
    }

}
