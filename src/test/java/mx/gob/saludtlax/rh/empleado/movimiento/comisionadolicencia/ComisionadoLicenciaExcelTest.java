/*
 *
 */

package mx.gob.saludtlax.rh.empleado.movimiento.comisionadolicencia;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcel;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;
import mx.gob.saludtlax.rh.util.ArchivoUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ComisionadoLicenciaExcelTest {

    private static final Logger LOGGER = Logger.getLogger(ComisionadoLicenciaExcelTest.class.getName());

    //	@Test
    public void excelTest() throws IOException {
        List<ComisionadoLicenciaExcelDTO> detalles = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ComisionadoLicenciaExcelDTO dto = new ComisionadoLicenciaExcelDTO();

            dto.setTipoMovimiento(i);
            dto.setApellidoPaterno("ROJAS");
            dto.setApellidoMaterno("PEREZ");
            dto.setNombreEmpleado("ZENAIDA");
            dto.setTipoPlaza("M02035 ");
            //			dto.setNumeroHoras(new Time(8, 0, 0));
            dto.setFuncionesEspecificas("10");
            dto.setClavePago("416");
            dto.setFechaInicio(new Date());
            dto.setFechaConclusion(new Date());
            dto.setCentroTrabajoOrigen("TLSSA001480");
            dto.setCentroTrabajoDestino("TLSSA001480");

            detalles.add(dto);
        }

        ComisionadoLicenciaExcel excel = new ComisionadoLicenciaExcel();

        byte[] archivo = excel.generar(detalles);

        try {
            ArchivoUtil.guardarEnCarpetaUsuario(archivo, "Comisionado_Licencia.xlsx");
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }

        assertNotNull(archivo);
    }

}
