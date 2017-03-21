    /*
 * JasperReportsCompilador.java
 * Creado el 9/Sep/2016 1:37:04 PM
 *
 */

package mx.gob.saludtlax.rh.reportes.jasperreports;

import static mx.gob.saludtlax.rh.util.Configuracion.DATASOURCE_ESPEJO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mx.gob.saludtlax.rh.util.ArchivoUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.jboss.logging.Logger;

/**
 * Esta clase ayuda en la compilación de los archivos de JasperReport.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author eduardo
 */
public class JasperReportsCompilador {

    private static final Logger LOGGER = Logger
            .getLogger(JasperReportsCompilador.class.getName());

    /**
     * Este método compila el archivo <code>.jrxml</code> devolviendo el una
     * instancia de tipo JasperReport.
     *
     * @param inputStream El stream de entrada que representa al archivo
     * <code>.jrxml</code>
     * @return Una instancia de tipo JasperReport
     * <code>.jrxml</code>.
     */
    public JasperReport compilar(InputStream inputStream) {
        LOGGER.debug("Iniciando la compilación del archivo .jrxml");
        JasperReport jasper = null;

        try {
            jasper = JasperCompileManager.compileReport(inputStream);
            LOGGER.debug("La compilación del archivo .jrxml se ha completado");
            inputStream.close();
        } catch (JRException ex) {
            LOGGER.errorv("Error durante la compilaci\u00f3n de archivo JRXML. {0} ", ex.getMessage());
        } catch (IOException ex) {
            LOGGER.errorv("Error al cerrar el inputStream: {0}", ex);
        }

        return jasper;
    }

    /**
     * Devuelve un arreglo de bytes cuando el reporte se ha generado
     * correctamente que representa un archivo en formato PDF.
     *
     * @param inputStream un objeto que representa el archivo
     * <code>.jrxml</code> sin compilar.
     * @param parametros los parámetros del reporte.
     * @return un arreglo de bytes con el reporte en formato PDF.
     */
    protected byte[] generarReporte(InputStream inputStream,
            Map<String, Object> parametros) {
        return generarReporte(compilar(inputStream), parametros, "pdf");
    }
    
    /**
     * Devuelve un arreglo de bytes que representa un archivo en formato PDF, 
     * cuando el reporte se ha generado correctamente.
     *
     * @param jasper el archivo .jrxml compilado.
     * @param parametros los parámetros del reporte.
     * @param tipoReporte el tipo de reporte que se generara.
     * @return un arreglo de bytes con el reporte según el tipo de reporte 
     * requerido.
     */
    protected byte[] generarReporte(JasperReport jasper,
            Map<String, Object> parametros, String tipoReporte) {
        byte[] bytesReporte = null;

        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup(DATASOURCE_ESPEJO);

            try (Connection conexion = ds.getConnection()) {
                if("pdf".equalsIgnoreCase(tipoReporte)) {
                    LOGGER.info("Iniciando la generación del reporte pdf");
                    bytesReporte = JasperRunManager
                            .runReportToPdf(jasper, parametros, conexion);
                    LOGGER.info("La generación del archivo .pdf se ha completado");
                } else if("txt".equalsIgnoreCase(tipoReporte)) {
                    LOGGER.info("Iniciando la generación del reporte txt");
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, conexion);
                        SimpleExporterInput sei = new SimpleExporterInput(jasperPrint);
                        SimpleTextReportConfiguration strc = new SimpleTextReportConfiguration();
                        SimpleWriterExporterOutput sweo = new SimpleWriterExporterOutput(baos);
                        
                        JRTextExporter exporter = new JRTextExporter();
                        exporter.setExporterInput(sei);
                        exporter.setConfiguration(strc);
                        exporter.setExporterOutput(sweo);
                        exporter.exportReport();

                        bytesReporte = ArchivoUtil.codificarComoWindows(baos.toByteArray());
                        LOGGER.info("La generación del reporte se ha completado.");
                    } catch (IOException ex) {
                        LOGGER.error(ex.getCause(), ex);
                    }
                    LOGGER.info("La generación del archivo .txt se ha completado");
                }
            }

            return bytesReporte;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al tratar de resolver el nombre: {0}", 
                    DATASOURCE_ESPEJO);
        } catch (SQLException ex) {
            LOGGER.errorv("Error de datos.\n{0}", ex.getMessage());
        } catch (JRException ex) {
            LOGGER.errorv("Error durante la generaci\u00f3n del archivo {0}. Detalle del error:\n{1}", tipoReporte, ex.getMessage());
        }

        return bytesReporte;
    }
}
