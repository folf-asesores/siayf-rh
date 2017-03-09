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

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

/**
 * @author Daniela Hernandez
 *
 */

public class CambioAdscripcion {

	private static final Logger LOGGER = Logger.getLogger(CambioAdscripcion.class.getName());

	private static final String RUTA = "plantillas/cambio/";
	private static final String PLANTILLA = "CAMBIO_ADSCRIPCION.docx";
	private static final char SIGNO_APERTURA = '\u00AB';
	private static final char SIGNO_CIERRE = '\u00BB';

	private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String NOMBRE_ARCHIVO = "CAMBIO_ADSCRIPCION.docx";

	// Valores de Word
	private static final String asunto = "Cambio de Adscripción.";
	private static final String presenteNombre = "C.D. AMERICA VAZQUEZ PEREZ";
	private static final String presenteClaveUno = "TRABAJADORA REGULARIZADA";
	private static final String presenteClaveDos = "VAPA851127F70";
	private static final String fecha ="17 de mayo de 2016";
	private static final String fechaCambio = "16 de Septiembre del presente año";
	private static final String cambioAdscripcion = "Hospital Comunitario de Tlaxco al Centro de Salud Urbano de Chiautempan";
	private static final String funcion = "Cirujano Dentista “A”";
	private static final String clavePresupuestal = "U004REG1103 M01007 000220012";
	private static final String turno ="Matutino";
	private static final String encargadoLabores =" Dra. Eva Xochitiotzi Bautista, Directora de la Unidad de referencia, quien le asignará su área y horario de labores";
	private static final String secretarioSalud = "C.P. LUZ MARIA PORTILLO GARCIA";

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
			camposCombinar.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE, fecha);
			camposCombinar.put(SIGNO_APERTURA + "fechaCambio" + SIGNO_CIERRE, fechaCambio);
			camposCombinar.put(SIGNO_APERTURA + "cambioAdscripcion" + SIGNO_CIERRE, cambioAdscripcion);
			camposCombinar.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, funcion);
			camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
			camposCombinar.put(SIGNO_APERTURA + "turno" + SIGNO_CIERRE, turno);
			camposCombinar.put(SIGNO_APERTURA + "encargadoLabores" + SIGNO_CIERRE, encargadoLabores);
			camposCombinar.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE, secretarioSalud);

			for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
				remplazarCampos(parrafo, camposCombinar);
			}

			Map<String, String> mapaTablas = new HashMap<String, String>();

			remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());

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
