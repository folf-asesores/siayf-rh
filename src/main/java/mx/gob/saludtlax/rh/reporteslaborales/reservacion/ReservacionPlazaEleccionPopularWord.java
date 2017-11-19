
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

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
 * @author Daniela
 *
 */

public class ReservacionPlazaEleccionPopularWord {

    public final String RUTA = "plantillas/reservacion/RESERVACION_PLAZA_ELECCION_POPULAR.docx";

    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';
    private static DateFormat formatFecha = DateFormat.getDateInstance(DateFormat.LONG);

    public byte[] generar(ReservacionDTO reservacionDTO) {

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RUTA);
            XWPFDocument plantilla = new XWPFDocument(inputStream);
            Map<String, String> camposReporte = new HashMap<>();

            camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE, reservacionDTO.getAsunto());
            camposReporte.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE, reservacionDTO.getPresenteNombre());
            camposReporte.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, reservacionDTO.getPresenteClaveUno());
            camposReporte.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, reservacionDTO.getPresenteClaveDos());
            camposReporte.put(SIGNO_APERTURA + "comunicado" + SIGNO_CIERRE, reservacionDTO.getComunicado());
            camposReporte.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, reservacionDTO.getPosicionUno());
            camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE, reservacionDTO.getFecha());
            camposReporte.put(SIGNO_APERTURA + "fechaIngreso" + SIGNO_CIERRE, formatFecha.format(reservacionDTO.getFechaIngreso()));
            camposReporte.put(SIGNO_APERTURA + "claveOriginal" + SIGNO_CIERRE, reservacionDTO.getClaveOriginal());
            camposReporte.put(SIGNO_APERTURA + "denominacion" + SIGNO_CIERRE, reservacionDTO.getDenominacionAlta());
            camposReporte.put(SIGNO_APERTURA + "adscripcionAlta" + SIGNO_CIERRE, reservacionDTO.getAdscripcionAlta());
            camposReporte.put(SIGNO_APERTURA + "fechaDesignacion" + SIGNO_CIERRE, reservacionDTO.getFechaDesignacion());
            camposReporte.put(SIGNO_APERTURA + "denominacion" + SIGNO_CIERRE, reservacionDTO.getDenominacion());
            camposReporte.put(SIGNO_APERTURA + "posiciondos" + SIGNO_CIERRE, reservacionDTO.getPosiciondos());
            camposReporte.put(SIGNO_APERTURA + "directoraAdministracion" + SIGNO_CIERRE, reservacionDTO.getDirectoraAdministracion());

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
