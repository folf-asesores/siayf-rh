/*
 *
 */

package mx.gob.saludtlax.rh.empleado.movimiento.concentrado;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Test;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ConsentradoAltaBajaExcel;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ConsentradoAltaBajaExcelDTO;
import mx.gob.saludtlax.rh.util.ArchivoUtil;

/**
 * @author Eduardo Mex
 *
 */
public class ConcentradoAltaBajaTest {

    private static final Logger LOGGER = Logger.getLogger(ConcentradoAltaBajaTest.class.getName());

    @Test
    public void excelTest() throws IOException {
        List<ConsentradoAltaBajaExcelDTO> detalles = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ConsentradoAltaBajaExcelDTO dto = new ConsentradoAltaBajaExcelDTO();

            //			dto.setNumero(i);
            dto.setRfc("GUHC591023TY6");
            dto.setFecha(new Date());
            dto.setNombreCompleto("GUILLEN,HERNANDEZ/CELIA");
            dto.setClavePresupuestal("I0024161103 M02035 29004 0138");
            dto.setAdscripcion("CENTRO DE SALUD URBANO DE APIZACO");
            dto.setTipoNombramiento("BASE FEDERAL");
            dto.setMovimiento("BAJA (LICENCIA PREJUBILATORIA");
            dto.setObservaciones("A PARTIR DEL 01/01/2015 AL 31/03/2015");

            detalles.add(dto);
        }

        ConsentradoAltaBajaExcel excel = new ConsentradoAltaBajaExcel();

        byte[] archivo = excel.generar(detalles);

        try {
            ArchivoUtil.guardarEnCarpetaUsuario(archivo, "Concentrado_Altas_Bajas.xlsx");
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }

        assertNotNull(archivo);
    }

}
