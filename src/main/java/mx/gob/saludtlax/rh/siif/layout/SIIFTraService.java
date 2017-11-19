/*
 * SIIFTraService.java
 * Creado el 06/Dec/2016 10:08:44 PM
 *
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.siif.EstructuraNominaTrailersDTO;

/**
 * Esta clase se encarga de la generación del archivo TRA.
 *
 * @author Zaid Perez
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFTraService {

    private static final Logger LOGGER = Logger
            .getLogger(SIIFTraService.class.getName());
    private File archivoTra;

    protected byte[] generarTra(
            List<EstructuraNominaTrailersDTO> listaDetalles) {
        try {
            Path path = Files.createTempFile("prdo", ".tra");
            archivoTra = path.toFile();
            llenarDetallesTra(listaDetalles);
            return obtenerBytesTra();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException(
                    "No fue posible escribir/leer el archivo temporal",
                    SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private void llenarDetallesTra(
            List<EstructuraNominaTrailersDTO> listaDetallesAnexo)
            throws IOException {
        try (FileWriter writer = new FileWriter(archivoTra)) {
            for (EstructuraNominaTrailersDTO detalle : listaDetallesAnexo) {
                writer.append(detalle.getRfc() == null ? "" : detalle.getRfc());
                writer.append('|');
                writer.append(
                        detalle.getNumEmp() == null ? "" : detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getNumCheq() == null ? ""
                        : detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.gettConcep() == null ? "0"
                        : detalle.gettConcep().toString());
                writer.append('|');
                writer.append(
                        detalle.getConcep() == null ? "" : detalle.getConcep());
                writer.append('|');
                writer.append(detalle.getImporte() == null ? ""
                        : detalle.getImporte().toString());
                writer.append('|');
                writer.append(
                        detalle.getAnio() == null ? "" : detalle.getAnio());
                writer.append('|');
                writer.append(detalle.getQna() == null ? "" : detalle.getQna());
                writer.append('|');
                writer.append(
                        detalle.getPtaAnt() == null ? "" : detalle.getPtaAnt());
                writer.append('|');
                writer.append(detalle.getNumCheq() == null ? ""
                        : detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getTotPagos() == null ? ""
                        : detalle.getTotPagos());
                writer.append('|');
                writer.append(detalle.getPagoEfec() == null ? ""
                        : detalle.getPagoEfec());
                writer.append('|');
                writer.append(detalle.getNumCheq() == null ? ""
                        : detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getNomProd() == null ? ""
                        : detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumCtrol() == null ? "0"
                        : detalle.getNumCtrol().toString());
                writer.append('\n');
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException(
                    "No fue posible escribir/leer el archivo temporal",
                    SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private byte[] obtenerBytesTra() throws IOException {
        try (FileInputStream fis = new FileInputStream(archivoTra);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }

            byte[] bytes = bos.toByteArray();
            archivoTra.delete();
            return bytes;
        }
    }
}
