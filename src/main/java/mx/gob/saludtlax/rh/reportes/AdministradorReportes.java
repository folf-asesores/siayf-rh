/*
 * AdministradorReportes.java
 * Creado el 9/Sep/2016 1:37:04 PM
 * 
 */

package mx.gob.saludtlax.rh.reportes;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.gob.saludtlax.rh.reportes.excel.AlmacenReportesExcel;
import mx.gob.saludtlax.rh.reportes.excel.ExcelGenerador;
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReportsGenerador;
import mx.gob.saludtlax.rh.reportes.txt.AlmacenReportesTxt;
import mx.gob.saludtlax.rh.reportes.txt.TxtGenerador;
import mx.gob.saludtlax.rh.reportes.word.AlmacenReporteWord;
import mx.gob.saludtlax.rh.reportes.word.WordGenerador;
import org.jboss.logging.Logger;

/**
 * <p>Esta clase tiene como objetivo llevar una bitácora de los reportes que se
 * generan en el sistema así como ayudar en la generación de los reportes del
 * sistema.</p>
 *
 * <p>La generación del reporte se realiza en dos pasos.</p>
 *
 * <ol>
 * <li>Obtener el ID de referencia, para ello se requiere de una lista de
 * parametros.</li>
 * <li>Generar el reporte, para ello se requiere del ID de referencia del paso
 * anterior.</li>
 * </ol>
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author Eduardo Mex
 */
public class AdministradorReportes {

    private static final String ETAPA_PERSISTENCIA_DE_DATOS = "A guardar en la base de datos";
    private static final String ETAPA_RECUPERACION_DE_DATOS = "Almacenados en la base de datos";
    private static final String BITACORA_REPORTES_BEAN = "java:module/BitacoraReporteEJB";
    private static final Logger LOGGER = Logger.getLogger(AdministradorReportes.class.getName());

    private final BitacoraReporte bitacoraReporte;

    /**
     * Crea una nueva instancia del administrador de reportes.
     */
    public AdministradorReportes() {
        bitacoraReporte = getBitacoraReportes();
    }

    /**
     * Permite obtener una referencia que se empleará para la generación del
     * reporte apartir de un arreglo de parámetros.
     * 
     * <p>
     * Ejemplo de parametros para obtener la referencia.
     * <code><pre>
     * String[] parametros = new String[] {
     *      "ID_USUARIO", String.valueOf(usuario.getIdUsuario()),
     *      "REPORTE_NOMBRE", nombreReporte,
     *      "TIPO_REPORTE",  "pdf",
     *      ...
     * };
     * </pre></code>
     * </p>
     *
     * @param parametros un arreglo de parámetros.
     * @return una cadena con la referencia para generar el reporte.
     * @throws IllegalArgumentException Si los parametros no son pares.
     * @throws NullPointerException Si los paramtros están nulos o vacios.
     */
    public String obtenerReferencia(String[] parametros) throws NullPointerException, IllegalArgumentException {
        Map<String, String> mapaParametros = separarClaveValor(parametros);
        
        imprimirParametros(mapaParametros, ETAPA_PERSISTENCIA_DE_DATOS);

        System.out.println("Mapa de Parametros:" + mapaParametros);
        return bitacoraReporte.obtenerReferencia(mapaParametros);
    }

    /**
     * Genera un reporte del cual previamente se han almacenado los parámetros
     * en la base de datos.
     *
     * @param referencia cadena que permite recuperar los parametros almacenados
     *                   en la base de datos.
     * @return los bytes que representa el archivo.
     * @throws NullPointerException si la es nula o vacia.
     * @throws IllegalArgumentException si la cadena no tiene los 32 cáracteres.
     */
    public byte[] obtenerReporte(String referencia) throws NullPointerException, IllegalArgumentException {
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new NullPointerException("La referencia está vacia.");
        }

        if ((referencia.length() < 32) || (referencia.length() > 32)) {
            throw new IllegalArgumentException("La referencia es debe ser de 32 cárcteres.");
        }

        Map<String, String> parametros = bitacoraReporte.obtenerParametros(referencia);
        imprimirParametros(parametros, ETAPA_RECUPERACION_DE_DATOS);

        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        String tipoReporte = parametros.get("TIPO_REPORTE");
        Generador generador = null;

        switch (tipoReporte) {
            case "docx":
                if (new AlmacenReporteWord().extisteReporte(nombreReporte)) {
                    generador = new WordGenerador();
                }
                break;
            case "pdf":
                if (new AlmacenReportesJasperReports().extisteReporte(nombreReporte)) {
                        generador = new JasperReportsGenerador();
                }
                break;
            case "txt":
                if (new AlmacenReportesJasperReports().extisteReporte(nombreReporte)) {
                        generador = new JasperReportsGenerador();
                }

                if (new AlmacenReportesTxt().extisteReporte(nombreReporte)) {
                        generador = new TxtGenerador();
                }
                break;
            case "xlsx":
                if (new AlmacenReportesExcel().extisteReporte(nombreReporte)) {
                    generador = new ExcelGenerador();
                }
                break;
        }

        if (generador != null) {
            return generador.obtenerReporte(parametros);
        } else {
            LOGGER.warnv("El reporte {0} de tipo {1} no se ha encontrado.", nombreReporte, tipoReporte);
            return null;
        }
    }

    /**
     * Permite obtener el nombre de un reporte que se vaya a generar o se haya
     * generado mediante la referencia.
     * 
     * @param referencia
     *            cadena que permite recuperar los parametros almacenados en la
     *            base de datos.
     * @return el nombre del reporte si la referencia es valida.
     */
    public String obtenerNombreReporte(String referencia) {
        return bitacoraReporte.obtenerNombreReporte(referencia);
    }

    /**
     * Permite obtener el tipo de un reporte que se vaya a generar o se haya
     * generado mediante la referencia.
     * 
     * <p>Como tipo de un reporte se debe entender por ejemplo:</p>
     * <ul>
     * <li>docx</li>
     * <li>pdf</li>
     * <li>txt</li>
     * <li>xlsx</li>
     * <li>etc.</li>
     * </ul>
     * <p>
     * <strong>Nota:</strong> el tipo no siempre es la extensión del archivo.
     * </p>
     * 
     * @param referencia cadena que permite recuperar los parametros almacenados
     *                   en la base de datos.
     * @return el tipo del reporte.
     */
    public String obtenerTipoReporte(String referencia) {
        return bitacoraReporte.obtenerTipoReporte(referencia);
    }

    /**
     * Convierte de manera básica el arreglo de parametros que recibe en un
     * <code>Map</code> de clave - valor.
     *
     * @param parametros los parametrso del reporte a separar.
     * @return los paramtrso separados.
     */
    private Map<String, String> separarClaveValor(String[] parametros)
            throws NullPointerException, IllegalArgumentException {

        if (parametros == null) {
            throw new NullPointerException("No se ha encontrado ningún parametro.");
        }

        int tamanyo = parametros.length;

        if (tamanyo == 0) {
            throw new IllegalArgumentException("Los parámetros no pueden estar vacios.");
        }

        if ((tamanyo % 2) != 0) {
            throw new IllegalArgumentException(
                    "Los parámetros deben tener un valor para que siempre haya una clave y valor");
        }

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < tamanyo; i++) {
            if ((i % 2) == 0) {
                int iClave = i;
                int iValor = i + 1;

                String clave = parametros[iClave];
                if (clave != null) {
                        clave = clave.trim().toUpperCase().replace(' ', '_');
                        map.put(clave, parametros[iValor]);
                }
            }
        }
        return map;
    }

    /**
     * Imprime los detalles de los parametros que del reporte en la bitacora del
     * servidor.
     *
     * @param parametros los parametros a imprimir.
     * @param etapa representa la etapa en la que está la generación del
     *              reporte.
     */
    private void imprimirParametros(Map<String, String> parametros, 
            String etapa) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================");
        sb.append("========================================\n");
        sb.append("Parametros del reporte (");
        sb.append(etapa);
        sb.append(")\n");
        sb.append("========================================");
        sb.append("========================================\n");
        sb.append("Clave\t\tValor\n");
        sb.append("========================================");
        sb.append("========================================\n");

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                sb.append(key);
                sb.append("\t\t");
                sb.append(value);
                sb.append('\n');
        }

        LOGGER.debug(sb.toString());
    }

    /**
     * Obtiene el EJB para persistir la información en la bitacora de los
     * reportes.
     *
     * @return una instancia del EJB de la bitacora de reportes.
     */
    private BitacoraReporte getBitacoraReportes() {
        try {
            Context initContext = new InitialContext();
            BitacoraReporte bitacoraReportes = (BitacoraReporte) initContext.lookup(BITACORA_REPORTES_BEAN);
            return bitacoraReportes;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", 
                    BITACORA_REPORTES_BEAN, ex.getCause());
        }

        return null;
    }

}
