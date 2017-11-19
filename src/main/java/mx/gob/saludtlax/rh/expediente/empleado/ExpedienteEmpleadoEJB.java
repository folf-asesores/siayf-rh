/*
 *
 */

package mx.gob.saludtlax.rh.expediente.empleado;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 11:08:55
 *
 */
@Stateless
public class ExpedienteEmpleadoEJB implements ExpedienteEmpleado {

    @Inject
    private ExpedienteEmpleadoService expedienteService;

    @Override
    public void crearExpediente(ExpedienteDTO expedienteDTO) {
        expedienteService.crearExpediente(expedienteDTO);

    }

    @Override
    public boolean tieneExpedienteAperturado(Integer idEmpleado) {
        return expedienteService.tieneExpedienteAperturado(idEmpleado);
    }

    @Override
    public String numeroExpedienteEmpleado(Integer idEmpleado) {
        return expedienteService.numeroExpedienteEmpleado(idEmpleado);
    }

    @Override
    public Integer obtenerIdExpedienteEmpleado(Integer idEmpleado) {
        return expedienteService.obtenerIdExpedienteEmpleado(idEmpleado);
    }

}
