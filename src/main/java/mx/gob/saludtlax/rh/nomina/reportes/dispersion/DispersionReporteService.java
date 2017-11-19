/*
 * DispersionReporteService.java
 * Creado el 07/Dec/2016 8:13:31 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionReporteService implements Serializable {

    private static final long serialVersionUID = -4661079055687311613L;
    private static final Logger LOGGER = Logger.getLogger(DispersionReporteService.class.getName());

    private File archivoDispersion;

    protected byte[] obtenerReporte(List<DispersionDTO> dispersion) {
        try {
            Path path = Files.createTempFile("dispersion", ".txt");
            archivoDispersion = path.toFile();
            escribirArchivo(dispersion);
            return obternerBytes();
        } catch (IOException ex) {
            LOGGER.error(ex);
            throw new SistemaException("No fue posible generar el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private void escribirArchivo(List<DispersionDTO> dispersion) {
        try (FileWriter escritor = new FileWriter(archivoDispersion)) {
            for (DispersionDTO dispersionDTO : dispersion) {
                escritor.append(dispersionDTO.getNumeroCuenta());
                escritor.append(' ');
                escritor.append(dispersionDTO.getNumeroCheque());
                escritor.append(' ');
                escritor.append(dispersionDTO.getNombreEmpleado());
                escritor.append(' ');
                escritor.append(dispersionDTO.getMonto().toString());
                escritor.append(' ');
                escritor.append(dispersionDTO.getNombreProducto());
                escritor.append('\n');
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private byte[] obternerBytes() {
        try (FileInputStream fis = new FileInputStream(archivoDispersion); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];

            for (int readNum; (readNum = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, readNum);
            }

            byte[] bytes = bos.toByteArray();
            archivoDispersion.delete();
            return bytes;
        } catch (IOException ex) {
            LOGGER.error(ex);
            throw new SistemaException("Error escribir/leer archivo final", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

}
