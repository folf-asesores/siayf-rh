/*
 *
 */

package mx.gob.saludtlax.rh.empleado.issste;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public interface Issste {

    public Integer altaIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO);

    public void modificacionIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO);

    public void bajaIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO);

    public boolean existeEmpleado(Integer idEmpleado);

    public List<InfoMovimientoIsssteDTO> obtenerListaMovimientoPorCriterio(String criterio);

}
