/*
 * ComprobanteEmpleadoRepTest.java
 * Creado el 18/Nov/2016 6:59:22 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoRepTest {

    @Ignore
    @Test
    public void test() {
        List<ComprobanteEmpleadoDTO> comprobantes = new ArrayList<>();
        Calendar fechaPago = Calendar.getInstance();
        fechaPago.set(Calendar.DAY_OF_MONTH, 30);
        fechaPago.set(Calendar.MONTH, Calendar.NOVEMBER);
        fechaPago.set(Calendar.YEAR, 2016);
        
        Calendar inicioPeriodo = Calendar.getInstance();
        inicioPeriodo.set(Calendar.DAY_OF_MONTH, 16);
        inicioPeriodo.set(Calendar.MONTH, Calendar.NOVEMBER);
        inicioPeriodo.set(Calendar.YEAR, 2016);
        
        Calendar finPeriodo = Calendar.getInstance();
        finPeriodo.set(Calendar.DAY_OF_MONTH, 16);
        finPeriodo.set(Calendar.MONTH, Calendar.NOVEMBER);
        finPeriodo.set(Calendar.YEAR, 2016);
        
        List<ConceptoComprobanteDTO> conceptos1 = new ArrayList<>();
        
        for(int i = 0; i < 3; i++){
            ConceptoComprobanteDTO concepto1 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.SUPLENCIAS, new BigDecimal("1998"));
            conceptos1.add(concepto1);
            ConceptoComprobanteDTO concepto2 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.SUBSIDIO, new BigDecimal("72"));
            conceptos1.add(concepto2);
        }
        
        comprobantes.add(new ComprobanteEmpleadoDTO(
                "CAPORAL MENDIETA MA ELENA ",
                "CAME750818R3A",
                fechaPago.getTime(),
                "1300",
                inicioPeriodo.getTime(),
                finPeriodo.getTime(),
                new BigDecimal("2070"),
                BigDecimal.ZERO,
                new BigDecimal("2070"),
                conceptos1)
        );
        
        List<ConceptoComprobanteDTO> conceptos2 = new ArrayList<>();

        ConceptoComprobanteDTO concepto3 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.SUPLENCIAS, new BigDecimal("3960"));
        conceptos2.add(concepto3);
        ConceptoComprobanteDTO concepto4 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.ISR, new BigDecimal("342.5"));
            conceptos2.add(concepto4);

        comprobantes.add(new ComprobanteEmpleadoDTO(
                "CORDERO GONZALEZ OSVALDO",
                "PIMP690204HTLTYU78", 
                fechaPago.getTime(),
                "1301",
                inicioPeriodo.getTime(),
                finPeriodo.getTime(),
                new BigDecimal("3960"),
                new BigDecimal("342.5"),
                new BigDecimal("3617.5"),
                conceptos2)
        );

        List<ConceptoComprobanteDTO> conceptos3 = new ArrayList<>();
        ConceptoComprobanteDTO concepto5 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.SUPLENCIAS, new BigDecimal("1453"));
        conceptos3.add(concepto5);
        ConceptoComprobanteDTO concepto6 = new ConceptoComprobanteDTO(ConceptoComprobanteDTO.SUBSIDIO, new BigDecimal("118.5"));
        conceptos3.add(concepto6);

        comprobantes.add(new ComprobanteEmpleadoDTO(
                "CONTRERAS HERNANDEZ SANDRA NELY",
                "COHS910628CD4",
                fechaPago.getTime(),
                "1302",
                inicioPeriodo.getTime(),
                finPeriodo.getTime(),
                new BigDecimal("1571.5"),
                BigDecimal.ZERO,
                new BigDecimal("1571.5"),
                conceptos3)
        );

//        comprobantes.add(new ComprobanteEmpleadoDTO(
//                "JIMENEZ MACIAS JUANA MARÍA DOLORES DE LA PAZ",
//                "JIMM950202MTLHNI45",
//                fechaPago.getTime(),
//                "1303",
//                inicioPeriodo.getTime(),
//                finPeriodo.getTime(),
//                BigDecimal.ZERO,
//                BigDecimal.ONE,
//                BigDecimal.ZERO,
//                null)
//        );
        
        ComprobanteEmpleadoRep rep = new ComprobanteEmpleadoRep();
        rep.obtenerArchivo(comprobantes);
    }
}
