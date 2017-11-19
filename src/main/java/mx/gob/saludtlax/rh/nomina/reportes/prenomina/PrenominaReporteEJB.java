/*
 * PrenominaReporteEJB.java
 * Creado el 11/Jul/2017 6:53:59 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

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
public class PrenominaReporteEJB implements PrenominaReporte {

    @Inject
    private PrenominaReporteService prenominaReporteService;

    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if (ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException("El ID del producto no debe ser nulo o menor que uno", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        ProductoNominaDTO productoNomina = prenominaReporteService.obtenerProductoNomina(idProductoNomina);
        PrenominaReporteTextoPlano prenominaReporteTextoPlano = new PrenominaReporteTextoPlano();
        return prenominaReporteTextoPlano.generar(productoNomina);
    }

}
