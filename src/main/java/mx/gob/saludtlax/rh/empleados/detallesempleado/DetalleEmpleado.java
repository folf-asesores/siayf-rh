/*
 *
 */

package mx.gob.saludtlax.rh.empleados.detallesempleado;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface DetalleEmpleado {

    public List<DetalleEmpleadoDTO> detalleEmpleadoPorIdTipoContratacion(
            Integer idTipoContratacion);

}
