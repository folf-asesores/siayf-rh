
package mx.gob.saludtlax.rh.nombramientos;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;
import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCamposTablas;
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
 * @author Daniela Hernandez
 *
 */

public class ComisionOficial {

    private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

    private static final String RUTA = "plantillas/comision/";
    private static final String PLANTILLA = "COMISION_OFICIAL.docx";
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String NOMBRE_ARCHIVO = "COMISION_OFICIAL.docx";

    // Valores de Word
    private static final String asunto = "Se comunica prorroga de comisión oficial.";
    private static final String nombrePresente = "E.E.P. ELIA DOMINGUEZ CARPINTERO";
    private static final String presenteClaveUno = "DOCE680620415";
    private static final String presenteClaveDos = "DOCE680620MTLMRL08";
    private static final String fechaNombramiento = "5018/102/2016 de fecha 19 de agosto del año en curso";
    private static final String jefaEnfermeras = "M.S.P. Modesta Flores Vásquez";
    private static final String posicionUno = "autoriza comisión oficial del C.S. de Terrenate al C.S. de Ixtenco de la Jurisdicción Sanitaria No. II, para el seguimiento del Modelo de Atención de Enfermería Obstétrica y la Maternidad, durante el período del 01 de julio al 31 de diciembre de 2016.";
    private static final String jefeUnidad = "Dr. Alejandro Roberto Morales Torres";
    private static final String directoraAdministracion = "C.P. LUZ MARIA PORTILLO GARCIA";

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
            camposCombinar.put(SIGNO_APERTURA + "nombrePresente" + SIGNO_CIERRE, nombrePresente);
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, presenteClaveUno);
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, presenteClaveDos);
            camposCombinar.put(SIGNO_APERTURA + "fechaNombramiento" + SIGNO_CIERRE, fechaNombramiento);
            camposCombinar.put(SIGNO_APERTURA + "jefaEnfermeras" + SIGNO_CIERRE, jefaEnfermeras);
            camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
            camposCombinar.put(SIGNO_APERTURA + "jefeUnidad" + SIGNO_CIERRE, jefeUnidad);
            camposCombinar.put(SIGNO_APERTURA + "directoraAdministracion" + SIGNO_CIERRE, directoraAdministracion);

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposCombinar);
            }

            Map<String, String> mapaTablas = new HashMap<>();

            remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());

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