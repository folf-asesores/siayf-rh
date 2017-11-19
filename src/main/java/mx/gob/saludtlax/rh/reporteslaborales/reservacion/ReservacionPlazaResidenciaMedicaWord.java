
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * @author Daniela Hernández
 *
 */

public class ReservacionPlazaResidenciaMedicaWord {
    public final String RUTA = "plantillas/reservacion/RESERVACION_PLAZA_RESIDENCIA_MEDICA.docx";

    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    public byte[] generar(ReservacionDTO reservacionDTO) {

        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(RUTA);
            XWPFDocument plantilla = new XWPFDocument(inputStream);
            Map<String, String> camposReporte = new HashMap<>();

            camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                    reservacionDTO.getAsunto());
            camposReporte.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE,
                    reservacionDTO.getPresenteNombre());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                    reservacionDTO.getPresenteClaveUno());
            camposReporte.put(
                    SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                    reservacionDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE,
                    reservacionDTO.getFecha());
            camposReporte.put(SIGNO_APERTURA + "encargado" + SIGNO_CIERRE,
                    reservacionDTO.getEncargado());
            camposReporte.put(
                    SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE,
                    reservacionDTO.getClavePresupuestal());
            camposReporte.put(SIGNO_APERTURA + "denominacion" + SIGNO_CIERRE,
                    reservacionDTO.getDenominacion());
            camposReporte.put(SIGNO_APERTURA + "adscripcion" + SIGNO_CIERRE,
                    reservacionDTO.getAdscripcion());
            camposReporte.put(SIGNO_APERTURA + "vigencia" + SIGNO_CIERRE,
                    reservacionDTO.getVigencia());
            camposReporte.put(SIGNO_APERTURA + "solicitud" + SIGNO_CIERRE,
                    reservacionDTO.getSolicitud());
            camposReporte.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                    reservacionDTO.getPosicionUno());
            camposReporte.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE,
                    reservacionDTO.getPosicionDos());
            camposReporte.put(SIGNO_APERTURA + "jefe" + SIGNO_CIERRE,
                    reservacionDTO.getJefe());

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
