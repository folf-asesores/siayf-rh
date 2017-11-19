/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class SeguroVidaInstitucionalEJB implements SeguroVidaInstitucional {

    @Inject
    private SeguroVidaInstitucionalService seguroVidaInstitucionalService;

    @Override
    public Integer crearSeguroVida(
            SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTO) {

        return seguroVidaInstitucionalService
                .crearSeguroVida(seguroVidaInstitucionalDTO);
    }

    @Override
    public boolean existeNumeroExpediente(String numeroExpediente) {

        return seguroVidaInstitucionalService
                .existeNumeroExpediente(numeroExpediente);
    }

    @Override
    public Integer existeEmpleado(Integer idEmpleado) {

        return seguroVidaInstitucionalService.existeEmpleado(idEmpleado);
    }

}
