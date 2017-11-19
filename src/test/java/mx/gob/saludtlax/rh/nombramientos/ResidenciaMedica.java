
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

public class ResidenciaMedica {

    private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

    private static final String RUTA = "plantillas/residencia/";
    private static final String PLANTILLA = "RESIDENCIA_MEDICA.docx";
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String NOMBRE_ARCHIVO = "RESIDENCIA_MEDICA.docx";

    // Valores de Word
    private static final String asunto = ("Se concede prorroga de licencia sin goce de sueldo en el puesto con funciones de base, para cursar el tercer año de la residencia médica de la especialidad en Imagenología.");
    private static final String presenteNombre = "C. VERÓNICA VIDAL PICAZO";
    private static final String presenteClaveUno = "VIPV741226LE8";
    private static final String presenteClaveDos = "VIPV741226MPLDCR03";
    private static final String fecha = "16 de febrero de 2016, se le concede prorroga de licencia sin goce de sueldo en el puesto con funciones de base, a efecto de que curse el tercer año de la residencia médica de la Especialidad en Imagenología";
    private static final String encargado = "Dr. Rafael Lucio";
    private static final String clavePresupuestal = "I0024161103 M02107 290040025";
    private static final String denominacion = "Enfermera Especialista “C”";
    private static final String adscripcion = "Hospital Regional “Lic. Emilio Sánchez Piedras”";
    private static final String vigencia = "Del 01 de marzo de 2016 al 28 de febrero de 2017";
    private static final String solicitud = "deberá presentar ante la Comisión Central Mixta de Capacitación y a la Oficina de Relaciones Laborales de ésta Secretaría, en la primera quincena de diciembre de cada año, comprobantes de que subsisten las causas que dieron origen a esta licencia";
    private static final String posicionUno = "los Artículos 151 Párrafo Segundo de las Condiciones Generales de Trabajo y 55 del Reglamento mencionado, deberá reincorporarse a su puesto de base, en su lugar de adscripción, dentro de los seis días hábiles siguientes al término de dicha licencia, por lo que deberá dar aviso de reincorporación a ésta Dirección, 15 días antes de que concluya la residencia médica o no prosiga con la misma";
    private static final String posicionDos = "de acuerdo al Artículo 56 del Reglamento, una vez concluida la beca que le fue autorizada, deberá de prestar sus servicios a la Secretaría por el tiempo equivalente al de su duración. De no hacerlo, la Secretaría, con apoyo en las disposiciones aplicables, procederá al fincamiento de responsabilidades en su contra";
    private static final String jefe = "LIC. VICTOR JOSE LEAL CRUZ";

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
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, presenteClaveUno);
            camposCombinar.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, presenteClaveDos);
            camposCombinar.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE, fecha);
            camposCombinar.put(SIGNO_APERTURA + "encargado" + SIGNO_CIERRE, encargado);
            camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
            camposCombinar.put(SIGNO_APERTURA + "denominacion" + SIGNO_CIERRE, denominacion);
            camposCombinar.put(SIGNO_APERTURA + "adscripcion" + SIGNO_CIERRE, adscripcion);
            camposCombinar.put(SIGNO_APERTURA + "vigencia" + SIGNO_CIERRE, vigencia);
            camposCombinar.put(SIGNO_APERTURA + "solicitud" + SIGNO_CIERRE, solicitud);
            camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
            camposCombinar.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE, posicionDos);
            camposCombinar.put(SIGNO_APERTURA + "jefe" + SIGNO_CIERRE, jefe);

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposCombinar);
            }

            Map<String, String> mapaTablas = new HashMap<>();

            //	remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());

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