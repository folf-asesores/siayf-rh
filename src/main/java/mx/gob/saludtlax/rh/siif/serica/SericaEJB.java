/*
 *
 */

package mx.gob.saludtlax.rh.siif.serica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.google.common.io.Files;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class SericaEJB implements Serica, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2613208996790829992L;

    private static final Logger LOGGER = Logger
            .getLogger(SericaEJB.class.getName());

    @Inject
    private SericaService sericaService;

    @Override
    public byte[] getDetalleSerica() {

        try {
            List<DetalleSericaDTO> consultarDetalleSericaEncabezado = sericaService
                    .consultarDetalleSericaEncabezados();
            LOGGER.debugv("Cantidad de Encabezados en el txt: {0}",
                    consultarDetalleSericaEncabezado.size());
            List<DetalleSericaDTO> consultarDetalleSerica = sericaService
                    .consultarDetalleSerica();
            LOGGER.debugv("Cantidad de elementos en el txt: {0}",
                    consultarDetalleSerica.size());
            byte[] txt = sericaService.generarTxt(
                    consultarDetalleSericaEncabezado, consultarDetalleSerica);

            // Generación del archivo zip
            File file = File.createTempFile("txt", ".zip");

            try (FileOutputStream fos = new FileOutputStream(file)) {
                try (ZipOutputStream zos = new ZipOutputStream(fos)) {

                    ZipEntry zipDat = new ZipEntry("NOM-01229001Q201622O1.txt");
                    zos.putNextEntry(zipDat);
                    zos.write(txt);
                    zos.closeEntry();

                }

                byte[] bytes = Files.toByteArray(file);
                file.delete();
                return bytes;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }

    }

    @Override
    public byte[] getDetallerSericaPeriodo(Integer periodo,
            Integer ejercicioFiscal) {

        try {

            List<DetalleSericaDTO> consultarDetalleSericaEncabezado = sericaService
                    .consultarDetalleSericaEncabezados();

            // Declaramos el Iterador e imprimimos los Elementos del ArrayList
            Iterator<DetalleSericaDTO> nombreEncabezadoIterator = consultarDetalleSericaEncabezado
                    .iterator();
            while (nombreEncabezadoIterator.hasNext()) {
                DetalleSericaDTO elemento = nombreEncabezadoIterator.next();
                LOGGER.info(elemento.toString());
            }

            List<SericaDTO> consultarDetalleSerica = sericaService
                    .consultarDetalleSericaEncabezadosPeriodo(periodo,
                            ejercicioFiscal);

            // Declaramos el Iterador e imprimimos los Elementos del ArrayList
            Iterator<SericaDTO> nombreIterator = consultarDetalleSerica
                    .iterator();
            while (nombreIterator.hasNext()) {
                SericaDTO elemento = nombreIterator.next();
                // LOGGER.info(elemento.toString());
            }

            byte[] txt = sericaService.generarDetailTxt(
                    consultarDetalleSericaEncabezado, consultarDetalleSerica);

            // Generación del archivo zip
            File file = File.createTempFile("txt", ".zip");

            try (FileOutputStream fos = new FileOutputStream(file)) {
                try (ZipOutputStream zos = new ZipOutputStream(fos)) {

                    ZipEntry zipDat = new ZipEntry("NOM-01229001Q201622O1.txt");
                    zos.putNextEntry(zipDat);
                    zos.write(txt);
                    zos.closeEntry();

                }

                byte[] bytes = Files.toByteArray(file);
                file.delete();
                return bytes;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

}
