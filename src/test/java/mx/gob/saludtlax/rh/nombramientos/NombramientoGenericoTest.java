/*
 *
 */

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jboss.logging.Logger;
import org.junit.Test;

import mx.gob.saludtlax.rh.util.NumeroUtil;

/**
 * @author Eduardo Mex
 *
 */
public class NombramientoGenericoTest {

    private static final Logger LOGGER = Logger.getLogger(NombramientoGenericoTest.class.getName());

    private static final String RUTA = "plantillas/nombramiento/";
    private static final String PLANTILLA = "NOMBRAMIENTO_GENERICO.docx";
    private static final String PATRON_FECHA_LARGA = "dd 'de' MMMM 'del' YYYY";
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';

    private static final String home = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String NOMBRE_ARCHIVO = "NOMBRAMIENTO_GENERICO.docx";

    // Valores de Word
    private static final String posicionUno = "Hago de su conocimiento que con motivo del Proceso de Escalafón en el que usted participó, se le comunica que a partir del 31 de enero de 2014, deja la función de Apoyo Administrativo en Salud-A5, con clave presupuestal I0024161103 M03021290040002 (federal); de acuerdo a lo anterior y con fundamento en los Artículos 90 del Reglamento de Escalafón y 17 de las Condiciones Generales de Trabajo de la Secretaría de Salud";
    private static final String nombreTipoNombramiento = "Nombramiento por Escalafon";
    private static final String nombreEmpleado = "Hernández León Julia";
    private static final String rfc = "HELJ5412288S4";
    private static final String curp = "HELJ541228MTLRNL00";
    private static final String edad = "61 años";
    private static final String nacionalidad = "Mexicana";
    private static final String sexo = "femenino";
    private static final String estadoCivil = "Soltera";
    private static final String domicilioEmpleado = "Avenida 12 de Octubre No. 6, Barrio de San Lucas, Huamantla, Tlaxcala";
    private static final String clavePresupuestal = "I0024161103 M02035 29004 0270";
    private static final String funcion = "Enfermera General Titulada 2ª";
    private static final String tipoNombramiento = "Base (Federal)";
    private static final String jornadaTrabajo = "8 horas";
    private static final String horarioTrabajo = "El que le asigne su Unidad";
    private static final BigDecimal sueldo = new BigDecimal("9468.78");
    private static final String lugarAdscripcion = "C.S. Chapultepec, Jurisdicción Sanitaria No. II";
    private static final String vigenciaFechaIngresoEmpleado = "1980-01-01";
    private static final String sustituye = "C. Ma. De Lourdes Vázquez Jiménez (por jubilación)";
    private static final String posicionDos = "Por lo que deberá presentarse con el Dr. José Gómez González, Director de la Unidad de referencia, quien le asignará su área, turno y horario de labores.Asimismo se le exhorta a cumplir de mejor forma en el desempeño de las tareas inherentes a su nuevo código funcional.";
    private static final String nombreSecretario = "DR. ALEJANDRO GUARNEROS CHUMACERO";

    private XWPFDocument plantilla;
    private String nombreArchivo;

    // private Logger log =
    // Logger.getLogger(MandamientoCitatorioWordProcesador.class);

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

            // mapa.put(SIGNO_APERTURA + "mandamiento" + SIGNO_CIERRE,
            // citatorio.getMandamiento());
            // mapa.put(SIGNO_APERTURA + "notificador" + SIGNO_CIERRE,
            // citatorio.getNotificador());
            // mapa.put(SIGNO_APERTURA + "deudor" + SIGNO_CIERRE,
            // citatorio.getDeudor());
            // mapa.put(SIGNO_APERTURA + "localizacion" + SIGNO_CIERRE,
            // citatorio.getLocalizacion());
            // mapa.put(SIGNO_APERTURA + "oficio" + SIGNO_CIERRE,
            // citatorio.getOficio());
            // mapa.put(SIGNO_APERTURA + "folio" + SIGNO_CIERRE,
            // citatorio.getFolio());
            // mapa.put(SIGNO_APERTURA + "fecha_mandamiento" + SIGNO_CIERRE,
            // formatearFecha(PATRON_FECHA_LARGA,
            // citatorio.getFechaMandamiento()));

            DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG);

            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

            Date vigencia = null;

            vigencia = formatoDelTexto.parse(vigenciaFechaIngresoEmpleado);

            camposCombinar.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE, posicionUno);
            camposCombinar.put(SIGNO_APERTURA + "nombreTipoNombramiento" + SIGNO_CIERRE, nombreTipoNombramiento);
            camposCombinar.put(SIGNO_APERTURA + "nombreEmpleado" + SIGNO_CIERRE, nombreEmpleado);
            camposCombinar.put(SIGNO_APERTURA + "rfc" + SIGNO_CIERRE, rfc);
            camposCombinar.put(SIGNO_APERTURA + "curp" + SIGNO_CIERRE, curp);
            camposCombinar.put(SIGNO_APERTURA + "edad" + SIGNO_CIERRE, edad);
            camposCombinar.put(SIGNO_APERTURA + "nacionalidad" + SIGNO_CIERRE, nacionalidad);
            camposCombinar.put(SIGNO_APERTURA + "sexo" + SIGNO_CIERRE, sexo);
            camposCombinar.put(SIGNO_APERTURA + "estadoCivil" + SIGNO_CIERRE, estadoCivil);
            camposCombinar.put(SIGNO_APERTURA + "domicilioEmpleado" + SIGNO_CIERRE, domicilioEmpleado);
            camposCombinar.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, clavePresupuestal);
            camposCombinar.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, funcion);
            camposCombinar.put(SIGNO_APERTURA + "tipoNombramiento" + SIGNO_CIERRE, tipoNombramiento);
            camposCombinar.put(SIGNO_APERTURA + "jornadaTrabajo" + SIGNO_CIERRE, jornadaTrabajo);
            camposCombinar.put(SIGNO_APERTURA + "horarioTrabajo" + SIGNO_CIERRE, horarioTrabajo);
            camposCombinar.put(SIGNO_APERTURA + "sueldo" + SIGNO_CIERRE, "$" + NumeroUtil.formatBigDecimal(sueldo));
            camposCombinar.put(SIGNO_APERTURA + "lugarAdscripcion" + SIGNO_CIERRE, lugarAdscripcion);
            camposCombinar.put(SIGNO_APERTURA + "vigenciaFechaIngresoEmpleado" + SIGNO_CIERRE, formatoFecha.format(vigencia));
            camposCombinar.put(SIGNO_APERTURA + "sustituye" + SIGNO_CIERRE, sustituye);
            camposCombinar.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE, posicionDos);
            camposCombinar.put(SIGNO_APERTURA + "NOMBRESECRETARIO" + SIGNO_CIERRE, nombreSecretario);

            for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                remplazarCampos(parrafo, camposCombinar);
            }

            Map<String, String> mapaTablas = new HashMap<>();
            // mapaTablas.put(SIGNO_APERTURA + "mandamiento" + SIGNO_CIERRE,
            // citatorio.getMandamiento());

            remplazarCamposTablas(mapaTablas, plantilla.getTablesIterator());

            //			setNombreArchivo("NOMBRAMIENTO_GENERICO");

            return escribirArchivo();
        } catch (IOException e) {
            // throw new ReglaNegocioException("Ocurrio un error de lectura o
            // escritura");
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
        // MandamientoCitatorioDTO citatorio = new MandamientoCitatorioDTO(
        // "TMSI/MFNF/15/2015",
        // Calendar.getInstance().getTime(),
        // "ALIANZA PARA EL DESARROLLO AGOPECUARIO, PESQUERO Y ACUICOLA, A. C.",
        // "AV. CAMPECHE MANZANA 1 LOTE 15 AV. LAS PALMAS Y PEDRO SAINZ DE
        // BARANDA, COL. VILLAS RESIDENCIAL AH KIM PECH",
        // "LIC. DIEGO DEL CARMEN MANZANERO CARDOZO Y/O LIC. ANGEL DANIEL PÃ‰REZ
        // LORETO Y/O LIC. ALBERTO DEL JESUS MASES CANCHE Y/O LIC. LUIS ANGEL
        // CAMORLINGA VILLALOBOS",
        // "TM/DJ/SI/003/2015 Y/0 TM/DJ/SI/004/2015 Y/0 TM/DJ/SI/005/2015 Y/0
        // TM/DJ/SI/006/2015",
        // "003 Y/O 004 Y/O 005 Y/O 006"
        // );

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
