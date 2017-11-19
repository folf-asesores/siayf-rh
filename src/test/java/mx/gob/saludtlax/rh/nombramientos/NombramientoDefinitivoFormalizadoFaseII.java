
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

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jboss.logging.Logger;
import org.junit.Test;

import mx.gob.saludtlax.rh.util.NumeroUtil;

/**
 * @author Daniela Hernandez
 *
 */

public class NombramientoDefinitivoFormalizadoFaseII {

    private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

    private static final String RUTA = "plantillas/nombramiento/";
    private static final String PLANTILLA = "NOMBRAMIENTO_FORMALIZADO_FASE_II.docx";
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String NOMBRE_ARCHIVO = "NOMBRAMIENTO_FORMALIZADO_FASE_II.docx";

    // Valores de Word
    private static final String posicionUno = "En uso de las facultades conferidas por el artículo 30, fracción X, de la ley de Salud del Estado de Tlaxcala y, con base en lo dispuesto por los artículos 15, de la Ley Federal de  los Trabajadores al Servicio del Estado y 14, 15, 16 y 17, Fracción I de las Condiciones Generales de Trabajo";
    private static final String nombreTipoNombramiento = "NOMBRAMIENTO DEFINITIVO";
    private static final String nombreEmpleado = "Hernández León Julia";
    private static final String rfc = "HELJ5412288S4";
    private static final String curp = "HELJ541228MTLRNL00";
    private static final String edad = "61 años";
    private static final String sexo = "femenino";
    private static final String estadoCivil = "Soltera";
    private static final String domicilioEmpleado = "Avenida 12 de Octubre No. 6, Barrio de San Lucas, Huamantla, Tlaxcala";
    private static final String clavePresupuestal = "I0024161103 M02035 29004 0270";;
    private static final String tipoNombramiento = "Base (Federal)";
    private static final String funcion = "Enfermera General Titulada 2ª";
    private static final String rama = "Medica";
    private static final String jornadaTrabajo = "8 horas";
    private static final BigDecimal sueldoMensual = new BigDecimal("9468.78");
    private static final String lugarAdscripcion = "C.S. Chapultepec, Jurisdicción Sanitaria No. II";
    private static final String posicionDos = "Con copia para: " + "I.Q. y Enf. Blanca Águila Lima.- Sria. Gral. De la Secc. XXVII del S.N.T.S.A..-Presente "
            + "C.P. Luz María Portillo García.- Directora de Administración.-Edificio"
            + "Dr. Efrén Samuel Orrico Torres.- Director de Servicios de Salud.-Edificio."
            + "Lic. Víctor José Leal Cruz.- Jefe del Departamento de Recursos Humanos.- Edificio"
            + "C.P. J. Guadalupe Badillo Flores- Jefe de la Oficina de Operación y Pagos.- Edificio"
            + "Biól. Francisco Méndez García.- Jefe de la Oficina de Relaciones Laborales.- Edificio" + "LMPG/VJLC/FMG/omar*";
    private static final String nombreSecretario = "DR. ALEJANDRO GUARNEROS CHUMACERO";

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

            camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
            camposCombinar.put(SIGNO_APERTURA + "nombreTipoNombramiento" + SIGNO_CIERRE, nombreTipoNombramiento);
            camposCombinar.put(SIGNO_APERTURA + "nombreEmpleado" + SIGNO_CIERRE, nombreEmpleado);
            camposCombinar.put(SIGNO_APERTURA + "rfc" + SIGNO_CIERRE, rfc);
            camposCombinar.put(SIGNO_APERTURA + "curp" + SIGNO_CIERRE, curp);
            camposCombinar.put(SIGNO_APERTURA + "edad" + SIGNO_CIERRE, edad);
            camposCombinar.put(SIGNO_APERTURA + "sexo" + SIGNO_CIERRE, sexo);
            camposCombinar.put(SIGNO_APERTURA + "estadoCivil" + SIGNO_CIERRE, estadoCivil);
            camposCombinar.put(SIGNO_APERTURA + "domicilioEmpleado" + SIGNO_CIERRE, domicilioEmpleado);
            camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
            camposCombinar.put(SIGNO_APERTURA + "tipoNombramiento" + SIGNO_CIERRE, tipoNombramiento);
            camposCombinar.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, funcion);
            camposCombinar.put(SIGNO_APERTURA + "rama" + SIGNO_CIERRE, rama);
            camposCombinar.put(SIGNO_APERTURA + "jornadaTrabajo" + SIGNO_CIERRE, jornadaTrabajo);
            camposCombinar.put(SIGNO_APERTURA + "sueldoMensual" + SIGNO_CIERRE, "$" + NumeroUtil.formatBigDecimal(sueldoMensual));
            camposCombinar.put(SIGNO_APERTURA + "lugarAdscripcion" + SIGNO_CIERRE, lugarAdscripcion);
            camposCombinar.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE, posicionDos);
            camposCombinar.put(SIGNO_APERTURA + "nombreSecretario" + SIGNO_CIERRE, nombreSecretario);

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposCombinar);
            }

            Map<String, String> mapaTablas = new HashMap<>();

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