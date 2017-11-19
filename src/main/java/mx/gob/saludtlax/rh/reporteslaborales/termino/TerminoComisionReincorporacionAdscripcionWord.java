
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TerminoComisionReincorporacionAdscripcionWord {

    public final String RUTA = "plantillas/termino/TERMINO_COMISION_Y_REINCORPORACION_ADSCRIPCION.docx";

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
            camposReporte.put(SIGNO_APERTURA + "puesto" + SIGNO_CIERRE,
                    terminoDTO.getPuesto());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                    terminoDTO.getPresenteClaveUno());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                    terminoDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "numeroOficio" + SIGNO_CIERRE,
                    terminoDTO.getNumeroOficio());
            camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE,
                    terminoDTO.getFecha());
            camposReporte.put(SIGNO_APERTURA + "encargado" + SIGNO_CIERRE,
                    terminoDTO.getEncargado());
            camposReporte.put(SIGNO_APERTURA + "fechaComision" + SIGNO_CIERRE,
                    terminoDTO.getFechaComision());
            camposReporte.put(
                    SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE,
                    terminoDTO.getClavePresupuestal());
            camposReporte.put(SIGNO_APERTURA + "asignacion" + SIGNO_CIERRE,
                    terminoDTO.getAsignacion());
            camposReporte.put(
                    SIGNO_APERTURA + "encargadoAdministracion" + SIGNO_CIERRE,
                    terminoDTO.getEncargadoAdministracion());

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
