/*
 * PagoGeneralReporteEJB.java
 * Creado el 15/Feb/2017 5:20:53 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Stateless
public class PagoGeneralReporteEJB implements PagoGeneralReporte {

    private static final long serialVersionUID = 3687555233714779856L;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralReporteEJB.class.getName());

    @Inject
    private PagoGeneralReporteService pagoGeneralService;
    @Inject
    private PagoGeneralReporteExcelService pagoGeneralExcelService;
    
    @Override
    public byte [] generarReporte(Integer idProductoNomina) {
        if(ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException(
                    "El ID del producto de nomina no puede ser cero o menor que uno",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        List<String> titulos = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        try {
            pagoGeneralService.obtenerInformacion(idProductoNomina, titulos, datos);
            return pagoGeneralExcelService.obtenerBytes(titulos, datos);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }
}
