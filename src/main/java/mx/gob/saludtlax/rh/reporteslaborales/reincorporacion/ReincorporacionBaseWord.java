
package mx.gob.saludtlax.rh.reporteslaborales.reincorporacion;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * @author Daniela Hernández
 *
 */

public class ReincorporacionBaseWord {
    public final String RUTA = "plantillas/reincorporacion/REINCORPORACION_BASE.docx";

    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';
    private static DateFormat formatFecha = DateFormat
            .getDateInstance(DateFormat.LONG);

    public byte[] generar(ReincorporacionBaseDTO reincorporacionBaseDTO) {

        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(RUTA);
            XWPFDocument plantilla = new XWPFDocument(inputStream);
            Map<String, String> camposReporte = new HashMap<>();

            camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getAsunto());
            camposReporte.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getPresenteNombre());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getPresenteClaveUno());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getFecha());
            camposReporte.put(
                    SIGNO_APERTURA + "fechaNombramiento" + SIGNO_CIERRE,
                    formatFecha.format(
                            reincorporacionBaseDTO.getFechaNombramiento()));
            camposReporte.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getFuncion());
            camposReporte.put(
                    SIGNO_APERTURA + "clavePresupuestal()" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getClavePresupuestal());
            camposReporte.put(SIGNO_APERTURA + "directoraUnidad" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getDirectoraUnidad());
            camposReporte.put(
                    SIGNO_APERTURA + "directoraAdministracion" + SIGNO_CIERRE,
                    reincorporacionBaseDTO.getDirectoraAdministracion());

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
