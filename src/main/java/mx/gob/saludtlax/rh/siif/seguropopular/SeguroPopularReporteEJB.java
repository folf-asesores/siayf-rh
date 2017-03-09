/*
 * SeguroPopularReporteEJB.java
 * Creado el 09/Dec/2016 5:38:10 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.seguropopular;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Stateless
public class SeguroPopularReporteEJB implements SeguroPopularReporte {

    private static final long serialVersionUID = 4503000459957085272L;
    private static final Logger LOGGER = Logger.getLogger(SeguroPopularReporteEJB.class.getName());
    

    @Inject private SeguroPopularReporteService seguroPopularReporteService;
    @Inject private SeguroPopularReporteExcel seguroPopularReporteExcel;
    
    @Override
    public byte[] obtenerReporte() {
        List<SeguroPopularReporteDTO> detalles = seguroPopularReporteService.obtenerInformacion();
        return seguroPopularReporteExcel.generar(detalles);
    }

    @Override
    public byte[] obtenerReporte(String anyo, Integer quincena) {
        List<SeguroPopularReporteDTO> detalles = seguroPopularReporteService.obtenerInformacion(anyo, quincena);
        return seguroPopularReporteExcel.generar(detalles);
    }
}
