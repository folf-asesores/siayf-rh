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
import java.util.logging.Logger;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

/**
 * @author Daniela Hernandez
 *
 */

public class TerminoInterinato {

	private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

	private static final String RUTA = "plantillas/termino/";
	private static final String PLANTILLA = "TERMINO_INTERINATO.docx";
	private static final char SIGNO_APERTURA = '\u00AB';
	private static final char SIGNO_CIERRE = '\u00BB';

	private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String NOMBRE_ARCHIVO = "TERMINO_INTERINATO.docx";

	// Valores de Word
	private static final String asunto = "Se comunica término de nombramiento de interinato.";
	private static final String presenteNombre = "DRA. MARGARITA VAZQUEZ RODRIGUEZ";
	private static final String presenteClaveUno = "VARM8403128Z3";
	private static final String presenteClaveDos = "VARM840312MTLZDR03";
	private static final String posicionUno = "Hago de su conocimiento que de acuerdo a los Artículos 46 Fracción II, 63, 64 y 65 de la Ley Federal de los Trabajadores al Servicio del Estado, 18 Fracción IV del Reglamento Interno del Organismo Público Descentralizado Salud de Tlaxcala y debido a la reincorporación del titular";
	private static final String reincorporacionTitular = "Dr. Hugo Díaz Anzures";
	private static final String fechaTermino ="partir del 22 de febrero del año en curso, se da por terminado su nombramiento de interinato";
	private static final String funcion ="Médico General “C” (Coordinadora Médica del Área de Servicios de Salud)";
	private static final String clavePresupuestal = "I0024161103 M01009290040031";
	private static final String secretarioSalud = "DR. ALEJANDRO GUARNEROS CHUMACERO";
	
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

			Map<String, String> camposCombinar = new HashMap<String, String>();

			camposCombinar.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE, asunto);
			camposCombinar.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE, presenteNombre);
			camposCombinar.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE, presenteClaveUno);
			camposCombinar.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE, presenteClaveDos);
			camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
			camposCombinar.put(SIGNO_APERTURA + "reincorporacionTitular" + SIGNO_CIERRE, reincorporacionTitular);
			camposCombinar.put(SIGNO_APERTURA + "fechaTermino" + SIGNO_CIERRE, fechaTermino);
			camposCombinar.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, funcion);	
			camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
			camposCombinar.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE, secretarioSalud);

			for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
				remplazarCampos(parrafo, camposCombinar);
			}

			Map<String, String> mapaTablas = new HashMap<String, String>();
			
		//	remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());
			
			for (XWPFTable tab : plantilla.getTables()) {

				String textoPrimeraCelda = tab.getRow(0).getCell(0).getText();
				if (textoPrimeraCelda != null) {
					if (textoPrimeraCelda.contains("Asunto:")) {

						XWPFParagraph paragraph = tab.getRow(0).getCell(1).addParagraph();
						paragraph.setAlignment(ParagraphAlignment.DISTRIBUTE);
						XWPFRun run=paragraph.createRun();
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
			LOGGER.severe("Mensaje crítico..." + nullPointerException.getMessage());
		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.severe("Ocurrio un error al general el documento: " + exception.getMessage());
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
