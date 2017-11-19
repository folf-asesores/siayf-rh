/*
 *
 */

package mx.gob.saludtlax.rh.empleados.detallesempleado;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class DetalleEmpleadoEJB implements DetalleEmpleado, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8741572482572801041L;

    @Inject
    private DetalleEmpleadoService detalleEmpleadoService;

    @Override
    public List<DetalleEmpleadoDTO> detalleEmpleadoPorIdTipoContratacion(Integer idTipoContratacion) {

        return detalleEmpleadoService.detalleEmpleadoPorIdTipoContratacion(idTipoContratacion);
    }

}
