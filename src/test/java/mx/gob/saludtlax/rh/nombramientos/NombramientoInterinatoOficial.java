package mx.gob.saludtlax.rh.nombramientos;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;
import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCamposTablas;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import mx.gob.saludtlax.rh.util.NumeroUtil;

public class NombramientoInterinatoOficial {

	private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

	private static final String RUTA = "plantillas/nombramiento/";
	private static final String PLANTILLA = "NOMBRAMIENTO_INTERINATO.docx";
	private static final char SIGNO_APERTURA = '\u00AB';
	private static final char SIGNO_CIERRE = '\u00BB';

	private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String NOMBRE_ARCHIVO = "NOMBRAMIENTO_INTERINATO.docx";

	// Valores de Word
	private static final String posicionUno = "C. ROSENDO MORALES ROCHA "
			+ "MORR660508N44"
			+ "MORR660508HPLRCS03";
    private	static final String fechaNombramiento ="01 de mayo del 2016, queda adscrito a Dirección General de Oficina Central ";
	private static final String funcion = "femenino";
	private static final String clavePresupuestal = "Soltera";
	private static final String propietarioPlaza = "Avenida 12 de Octubre No. 6, Barrio de San Lucas, Huamantla, Tlaxcala";
	private static final String tipoRecurso = "I0024161103 M02035 29004 0270";;
	private static final String tipoNombramiento = "Base (Federal)";
	private static final String vigencia = "C.S. Chapultepec, Jurisdicción Sanitaria No. II";
	private static final String posicionDos = "Por lo que deberá presentarse con el Dr. Alejandro Guarneros Chumacero, Director de la Unidad de referencia, quien le asignará su área, turno y horario de labores.";
	private static final String nombreSecretario = "DR. ALEJANDRO GUARNEROS CHUMACERO";
	private static final String posicionTres = "Con copia para:"
			+ "C.P. Luz María Portillo García.- Directora de Administración.- Edificio."
			+ "Lic. Víctor José Leal Cruz.- Jefe del Departamento de Recursos Humanos.- Edificio."
			+ "C.P. J. Guadalupe Badillo Flores.- Jefe de la Oficina de Operación y Pagos.- Edificio."
			+ "Biol. Francisco Méndez García.- Jefe de la Oficina de Relaciones Laborales.- Edificio."
			+ "LMPG*VJLC*FMG*omar**";

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

			camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
			camposCombinar.put(SIGNO_APERTURA + "fechaNombramiento" + SIGNO_CIERRE, fechaNombramiento);
			camposCombinar.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, funcion);
			camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
			camposCombinar.put(SIGNO_APERTURA + "propietarioPlaza" + SIGNO_CIERRE, propietarioPlaza);
			camposCombinar.put(SIGNO_APERTURA + "tipoRecurso" + SIGNO_CIERRE, tipoRecurso);
			camposCombinar.put(SIGNO_APERTURA + "tipoNombramiento" + SIGNO_CIERRE, tipoNombramiento);
			camposCombinar.put(SIGNO_APERTURA + "vigencia" + SIGNO_CIERRE, vigencia);
			camposCombinar.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE, posicionDos);
			camposCombinar.put(SIGNO_APERTURA + "nombreSecretario" + SIGNO_CIERRE, nombreSecretario);
			camposCombinar.put(SIGNO_APERTURA + "posicionTres" + SIGNO_CIERRE, posicionTres);

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