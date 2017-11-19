/*
 *
 */

package mx.gob.saludtlax.rh.nomina.historialpago;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface HistorialPago {

    List<HistorialPagoDetalleDTO> obtenerListaHistorialPagoPorIdEmpleado(
            Integer idEmpleado);

}
