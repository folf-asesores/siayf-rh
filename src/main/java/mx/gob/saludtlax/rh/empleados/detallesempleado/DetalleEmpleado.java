/*
 *
 */

package mx.gob.saludtlax.rh.empleados.detallesempleado;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public interface DetalleEmpleado {

    public List<DetalleEmpleadoDTO> detalleEmpleadoPorIdTipoContratacion(Integer idTipoContratacion);

}
