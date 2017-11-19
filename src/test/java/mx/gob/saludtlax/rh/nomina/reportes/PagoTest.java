
package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteExcelService;
import mx.gob.saludtlax.rh.util.ArchivoUtil;

public class PagoTest {

    @Test
    public void test() throws IOException {
        PagoGeneralReporteExcelService excelService = new PagoGeneralReporteExcelService();
        List<String> titulos = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        titulos.add("percepcionNeta");
        titulos.add("deduccionNeta");
        titulos.add("idPagoNomina");

        //fila uno hoja uno	
        Object[] fila1_1 = new Object[3];
        fila1_1[0] = "23";
        fila1_1[1] = "3";
        fila1_1[2] = 1;
        datos.add(fila1_1);
        //Fila uno de hoja dos
        Object[] fila1_2 = new Object[3];
        fila1_2[0] = "12";
        fila1_2[1] = "12";
        fila1_2[2] = 2;
        datos.add(fila1_2);
        //Fila dos de hoja dos
        Object[] fila2_2 = new Object[3];
        fila2_2[0] = "125";
        fila2_2[1] = "152";
        fila2_2[2] = 2;
        datos.add(fila2_2);

        byte[] bytes = excelService.obtenerBytes(titulos, datos);
        ArchivoUtil.guardarEnCarpetaUsuario(bytes, "test.xlsx");
    }
}
