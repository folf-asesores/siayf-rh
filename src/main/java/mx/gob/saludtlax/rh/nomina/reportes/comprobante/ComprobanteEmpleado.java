/*
 * ComprobanteEmpleado.java
 * Creado el 22/nov/2016 4:20:15 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;

import javax.ejb.Local;

/**
 * Esta interfaz define lo necesario para la generación del reporte de
 * comprobates de pago (cheques).
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface ComprobanteEmpleado extends Serializable {

    /**
     * Permite obtener el reporte de comprobantes de pago (cheques) en formato
     * de texto plano como un arreglo de bytes.
     *
     * @param idProductoNomina
     *            el ID del producto de nómina que se desea.
     * @return el reporte como un arreglo de bytes.
     */
    byte[] generarReporte(Integer idProductoNomina);

}
