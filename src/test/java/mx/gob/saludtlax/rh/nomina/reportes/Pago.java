package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteExcelService;
import mx.gob.saludtlax.rh.util.ArchivoUtil;

public class Pago {
	
	@Test
	public void test() throws IOException {
		PagoGeneralReporteExcelService excelService = new PagoGeneralReporteExcelService();
		List<String> titulos = new ArrayList<>();
		List<Object[]> datos = new ArrayList<>();
		
		Object []  obj = new Object[2] ;
		titulos.add("Juan");
		obj [0] = "23";
		
		titulos.add("Juan");
		obj [1] = "3";

		byte [] bytes = excelService.obtenerBytes(titulos, datos);
		ArchivoUtil.guardarEnCarpetaUsuario(bytes, "test.xlsx");
	}
}
