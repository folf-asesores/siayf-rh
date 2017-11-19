
package mx.gob.saludtlax.rh.reporteslaborales.cambio;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * @author Daniela
 *
 */

public class CambioAdscripcionWord {
    public final String RUTA = "plantillas/cambio/CAMBIO_ADSCRIPCION.docx";

    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    public byte[] generar(CambioAdscripcionDTO cambioAdscripcionDTO) {

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RUTA);
            XWPFDocument plantilla = new XWPFDocument(inputStream);

            Map<String, String> camposReporte = new HashMap<>();

            camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE, cambioAdscripcionDTO.getAsunto());
            camposReporte.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteNombre());
            camposReporte.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteClaveUno());
            camposReporte.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE, cambioAdscripcionDTO.getFecha());
            camposReporte.put(SIGNO_APERTURA + "fechaCambio" + SIGNO_CIERRE, cambioAdscripcionDTO.getFechaCambio());
            camposReporte.put(SIGNO_APERTURA + "cambioAdscripcion" + SIGNO_CIERRE, cambioAdscripcionDTO.getCambioAdscripcion());
            camposReporte.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteNombre());
            camposReporte.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, cambioAdscripcionDTO.getFuncion());
            camposReporte.put(SIGNO_APERTURA + "turno" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteNombre());
            camposReporte.put(SIGNO_APERTURA + "encargadoLabores" + SIGNO_CIERRE, cambioAdscripcionDTO.getTurno());
            camposReporte.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE, cambioAdscripcionDTO.getSecretarioSalud());

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposReporte);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            plantilla.write(out);
            plantilla.close();

            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
