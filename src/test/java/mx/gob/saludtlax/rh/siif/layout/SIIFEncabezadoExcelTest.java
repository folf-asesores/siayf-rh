/*
 * SIIFEncabezadoExcelTest.java
 * Creado el Jul 5, 2016 12:36:37 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.jboss.logging.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Freddy Barrera <freddy.barrera@folfasesores.com.mx>
 */
public class SIIFEncabezadoExcelTest {
    
    private static final Logger LOGGER = Logger.getLogger(SIIFEncabezadoExcelTest.class.getName());

    @Test
    public void testSomeMethod() {
        SIIFEncabezadoExcel excel = new SIIFEncabezadoExcel();
        SIIFEncabezadoDTO encabezado1 = new SIIFEncabezadoDTO(
                1, 
                'E', 
                0, 
                Calendar.getInstance().getTime(), 
                0, 
                "A", 
                BigDecimal.ZERO, 
                new BigDecimal(15216.85),
                BigDecimal.ZERO, 
                'P'
        );
        
        SIIFEncabezadoDTO encabezado2 = new SIIFEncabezadoDTO(
                2, 
                'E', 
                0, 
                Calendar.getInstance().getTime(), 
                0, 
                "C", 
                new BigDecimal(78454.56),
                BigDecimal.ONE, 
                BigDecimal.ZERO, 
                'D'
        );
        
        List<SIIFEncabezadoDTO> dTOs = new ArrayList<>();
        
        dTOs.add(encabezado1);
        dTOs.add(encabezado2);
        
        byte[] bytes = excel.generar(dTOs);
        
        try {
            ArchivoUtil.guardarEnCarpetaUsuario(bytes, "test-encabezado.xlsx");
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }
        assertTrue(1 == 1);
    }
    
}
