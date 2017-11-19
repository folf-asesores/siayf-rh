
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TerminoConfianzaReincorporacionWord {

    public final String RUTA = "plantillas/termino/TERMINO_CONFIANZA_Y_REINCORPORACION_BASE.docx";

    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    public byte[] generar(TerminoDTO terminoDTO) {

        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(RUTA);
            XWPFDocument plantilla = new XWPFDocument(inputStream);
            Map<String, String> camposReporte = new HashMap<>();

            camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                    terminoDTO.getAsunto());
            camposReporte.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE,
                    terminoDTO.getPresenteNombre());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                    terminoDTO.getPresenteClaveUno());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                    terminoDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "fechaTermino" + SIGNO_CIERRE,
                    terminoDTO.getFechaTermino());
            camposReporte.put(
                    SIGNO_APERTURA + "clavePesupuestal" + SIGNO_CIERRE,
                    terminoDTO.getClavePresupuestal());
            camposReporte.put(SIGNO_APERTURA + "fechaPlaza" + SIGNO_CIERRE,
                    terminoDTO.getFechaPlaza());
            camposReporte.put(SIGNO_APERTURA + "nuevaClave" + SIGNO_CIERRE,
                    terminoDTO.getNuevaClave());
            camposReporte.put(SIGNO_APERTURA + "jefe" + SIGNO_CIERRE,
                    terminoDTO.getJefe());
            camposReporte.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE,
                    terminoDTO.getSecretarioSalud());

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
