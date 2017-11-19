/*
 * ComprobanteEmpleadoBean.java
 * Creado el 22/nov/2016 4:20:15 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ComprobanteEmpleadoBean implements ComprobanteEmpleado {

    private static final long serialVersionUID = -7029108286383735953L;

    @Inject
    private ComprobanteEmpleadoService comprobanteEmpleadoService;

    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if (ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException(
                    "El ID del producto no debe ser nulo o menor que uno",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return comprobanteEmpleadoService.generarReporte(idProductoNomina);
    }

}
