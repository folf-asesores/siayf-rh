/*
 * ProductoNominaFederalReporteEJB.java
 * Creado el 16/Mar/2017 11:10:35 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.federales;

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
public class ProductoNominaFederalReporteEJB implements ProductoNominaFederalReporte {

    private static final long serialVersionUID = -6429903585735894314L;
    private static final Logger LOGGER = Logger.getLogger(ProductoNominaFederalReporteEJB.class.getName());
    
    @Inject
    private ProductoNominaFederalReporteService service;
    @Inject
    private ProductoNominaFederalReporteExcelService excelService;

    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if(ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException(
                    "El ID del producto de nomina no puede ser cero o menor que uno",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        List<String> titulos = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        try {
            service.obtenerInformacion(idProductoNomina, titulos, datos);
            byte [] reporte = excelService.obtenerBytes(titulos, datos);
            titulos = null;
            datos = null;
            return reporte;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

}
