
package mx.gob.saludtlax.rh.nombramientos;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jboss.logging.Logger;
import org.junit.Test;

/**
 * @author Daniela Hernández
 *
 */

public class TerminoComisionReincorporacion {

    private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

    private static final String RUTA = "plantillas/termino/";
    private static final String PLANTILLA = "TERMINO_COMISION_Y_REINCORPORACION_ADSCRIPCION.docx";
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String NOMBRE_ARCHIVO = "TERMINO_COMISION_Y_REINCORPORACION_ADSCRIPCION.docx";

    // Valores de Word
    private static final String asunto = "Se comunica término de comisión oficial y reincorporación a lugar de adscripción.";
    private static final String presenteNombre = "C. JOSE JORGE CARRETO CASTULO";
    private static final String puesto = "Trabajador Regularizado comisionado a Oficina Central";
    private static final String presenteClaveUno = "CACJ580719SZ8";
    private static final String presenteClaveDos = "CACJ580719HDFRSR08";
    private static final String numeroOficio = "HGT/RH/00075";
    private static final String fecha = "16 de marzo de 2016";
    private static final String encargado = "Dr. Rafael Lucio";
    private static final String fechaComision = "15 de julio del presente año, se da por terminada su comisión oficial en el Área de Mantenimiento del Departamento de Infraestructura de Oficina Central, por lo anterior, con fecha 16 de julio del año en curso, deberá de reincorporarse a su lugar de adscripción en el Hospital General de Tlaxcala";
    private static final String clavePresupuestal = "U004REG1103 M03025000220423";
    private static final String asignacion = "el Dr. José Gómez González";
    private static final String encargadoAdministracion = "C.P. LUZ MARIA PORTILLO GARCIA";

    private XWPFDocument plantilla;
    private String nombreArchivo;

    /**
     * 
     * @param context
     * @throws IOException
     */
    private void cargarPlantilla() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RUTA + PLANTILLA);

        plantilla = new XWPFDocument(inputStream);
    }

    /**
     * 
     * @return
     * @throws IOException
     */
    private ByteArrayOutputStream escribirArchivo() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        plantilla.write(out);
        return out;
    }

    /**
     * 
     * @param citatorio
     * @return
     */
    public ByteArrayOutputStream generar() {
        try {
            cargarPlantilla();

            Map<String, String> camposCombinar = new HashMap<>();

            camposCombinar.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE, asunto);
            camposCombinar.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE, presenteNombre);
            camposCombinar.put(SIGNO_APERTURA + "puesto" + SIGNO_CIERRE, puesto);
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, presenteClaveUno);
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, presenteClaveDos);
            camposCombinar.put(SIGNO_APERTURA + "numeroOficio" + SIGNO_CIERRE, numeroOficio);
            camposCombinar.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE, fecha);
            camposCombinar.put(SIGNO_APERTURA + "encargado" + SIGNO_CIERRE, encargado);
            camposCombinar.put(SIGNO_APERTURA + "fechaComision" + SIGNO_CIERRE, fechaComision);
            camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
            camposCombinar.put(SIGNO_APERTURA + "asignacion" + SIGNO_CIERRE, asignacion);
            camposCombinar.put(SIGNO_APERTURA + "encargadoAdministracion" + SIGNO_CIERRE, encargadoAdministracion);

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposCombinar);
            }

            Map<String, String> mapaTablas = new HashMap<>();

            //		remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());

            for (XWPFTable tab : plantilla.getTables()) {

                String textoPrimeraCelda = tab.getRow(0).getCell(0).getText();
                if (textoPrimeraCelda != null) {
                    if (textoPrimeraCelda.contains("Asunto:")) {

                        XWPFParagraph paragraph = tab.getRow(0).getCell(1).addParagraph();
                        paragraph.setAlignment(ParagraphAlignment.DISTRIBUTE);
                        XWPFRun run = paragraph.createRun();
                        run.setText(asunto);
                        run.setBold(false);
                        run.setFontFamily("Verdana");
                        run.setFontSize(9);

                    }
                }

            }

            return escribirArchivo();
        } catch (IOException e) {

            e.printStackTrace();
            LOGGER.info("Error: " + e.getMessage());
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            LOGGER.error("Mensaje crítico..." + nullPointerException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error("Ocurrio un error al general el documento: " + exception.getMessage());
        }
        return null;
    }

    @Test
    public void testGenerar() throws IOException {

        ByteArrayOutputStream gen = generar();
        OutputStream outputStream = new FileOutputStream(home + NOMBRE_ARCHIVO);
        gen.writeTo(outputStream);
        gen.close();

        assertNotNull(gen);
    }

    /**
     * @return el campo nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo
     *            el valor de nombreArchivo a poner en el objeto.
     */
    private void setNombreArchivo(String nombreArchivo) {
        String[] nombrePlantilla = PLANTILLA.split(".docx");
        String nuevoNombreArchivo = nombreArchivo.toLowerCase().split(" ")[0].replaceAll("\\/", "-").trim();

        this.nombreArchivo = nombrePlantilla[0] + '-' + nuevoNombreArchivo + ".docx";
    }

}