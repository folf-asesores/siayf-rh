/*
 * DispersionExcelServiceTest.java
 * Creado el 29/Jan/2017 4:15:57 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class DispersionExcelServiceTest {
    
    public DispersionExcelServiceTest() {
    }

    /**
     * Test of llenarDetalle method, of class DispersionExcelService.
     * @throws java.io.IOException
     */
    @Ignore
    @Test
    public void testLlenarDetalle() throws IOException {
        System.out.println("llenarDetalle");
        DispersionExcelService instance = new DispersionExcelService();
        List<DispersionDTO> detalles = new ArrayList<>();
        Random rnd = new Random();
        int fin = rnd.nextInt(80000);
        for(int i = 0; i < fin; i++) {
            DispersionDTO dispersionDTO = new DispersionDTO(
                    "PEdRO PEREZ", 
                    String.format("%05d", i + 1),
                    new BigDecimal(rnd.nextDouble(), MathContext.DECIMAL32),
                    rnd.nextBoolean() ? "FF 1" : "FF 2",
                    Calendar.getInstance().getTime()
            );
            detalles.add(dispersionDTO);
        }
        System.out.println("Tamaño:" + detalles.size());
        byte[] archivo = instance.obtenerBytes(detalles);
        ArchivoUtil.guardarEnCarpetaUsuario(archivo, "dispersion_test.xlsx");
        assertNotNull(archivo);
    }
    
}
