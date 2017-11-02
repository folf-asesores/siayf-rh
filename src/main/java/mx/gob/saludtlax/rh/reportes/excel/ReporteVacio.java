/*
 * ReporteVacio.java
 * Creado el 15/Feb/2017 10:00:47 AM
 * 
 */
package mx.gob.saludtlax.rh.reportes.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;


/**
 * Esta clase permite crer un archivo de Excel vacio.
 * 
 * <p>Esta clase se ha pensado como una alternariva para cuando ocurra un error
 * poder enviar algo al usuario.</p>
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ReporteVacio implements Serializable {

    private static final long serialVersionUID = 7107911995230489541L;
    private static final Logger LOGGER = Logger.getLogger(ReporteVacio.class.getName());

    private ReporteVacio() {
    }
    
    /**
     * Genera un archivo de Excel sin datos.
     * 
     * @return un arreglo de bytes que contiene un archivo de Excel vacio.
     */
    public static byte[] obtenerBytes() {
        String nombreHojaSeguro = WorkbookUtil.createSafeSheetName("Hoja 1", '_');
        byte[] archivo;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Workbook libro = new XSSFWorkbook()) {
            libro.createSheet(nombreHojaSeguro);
            libro.write(baos);
            archivo = baos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
        return archivo;
    }

}
