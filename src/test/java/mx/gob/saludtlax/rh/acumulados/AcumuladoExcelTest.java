/*
 * AcumuladoExcelTest.java
 * Creado el Sep 24, 2016 1:13:19 PM
 *
 */

package mx.gob.saludtlax.rh.acumulados;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;

import mx.gob.saludtlax.rh.util.ArchivoUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AcumuladoExcelTest {

    private static final Logger LOGGER = Logger.getLogger(AcumuladoExcelTest.class.getName());

    @Test
    public void excelTest() throws IOException {
        List<AcumuladosDTO> detalles = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AcumuladosDTO dto = new AcumuladosDTO();

            dto.setIdAcumulado(i);
            dto.setNumEmpleado("2563694");
            dto.setNombre("Lopez, Antonio");
            dto.setRfc("TYUN058445RCD01");
            dto.setFuncion("Funcion");
            dto.setUr("un UR");
            dto.setGf("un GF");
            dto.setFn("un FN");
            dto.setSf("un SF");
            dto.setPartida("una Partida");
            dto.setPuesto("un Puesto");
            dto.setP_qna_i("01-2016");
            dto.setP_qna_f("12-2016");
            dto.setQna_real(16);
            dto.setAnio_real(2016);
            dto.setId_concepto_nomina(i + 1);
            dto.setImporte(new BigDecimal("25987.65"));

            detalles.add(dto);
        }

        AcumuladoExcel excel = new AcumuladoExcel();
        byte[] archivo = excel.generar(detalles);

        try {
            ArchivoUtil.guardarEnCarpetaUsuario(archivo, "acumulados.xlsx");
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }

        assertNotNull(archivo);
    }
}
