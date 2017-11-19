/*
 *
 */

package mx.gob.saludtlax.rh.expediente.empleado;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 11:08:43
 *
 */
public interface ExpedienteEmpleado {

    public void crearExpediente(ExpedienteDTO expedienteDTO);

    public boolean tieneExpedienteAperturado(Integer idEmpleado);

    public String numeroExpedienteEmpleado(Integer idEmpleado);

    public Integer obtenerIdExpedienteEmpleado(Integer idEmpleado);

}
