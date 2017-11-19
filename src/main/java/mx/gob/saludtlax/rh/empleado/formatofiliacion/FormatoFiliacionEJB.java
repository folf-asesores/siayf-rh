/*
 *
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class FormatoFiliacionEJB implements FormatoFiliacion {

    @Inject
    private FormatoFiliacionService formatoFiliacionService;

    @Override
    public Integer crearFormatoFiliacion(FormatoFiliacionDTO formatoFiliacionDTO) {
        return formatoFiliacionService.crearFormatoFiliacion(formatoFiliacionDTO);
    }

}
