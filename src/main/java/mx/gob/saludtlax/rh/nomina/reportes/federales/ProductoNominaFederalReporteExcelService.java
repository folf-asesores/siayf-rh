/*
 * ProductoNominaFederalReporteExcelService.java
 * Creado el 16/Mar/2017 11:24:10 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.federales;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaFederalReporteExcelService implements Serializable {

    private static final long serialVersionUID = -5362019182861266115L;
    private static final Logger LOGGER = Logger.getLogger(
            ProductoNominaFederalReporteExcelService.class.getName());

    /** Instancia que representa el libro de Excel sobre el que se trabajará. */
    private Workbook libro;
    /** Instancia que representa la unica hoja del reporte. */
    private Sheet hoja;
    /** Es la fila apartir de la cual inicia el detalle. */
    private static final int FILA_INICIO_DETALLE = 1;

    private void inicializar() {
        libro = new XSSFWorkbook();
        String nombreHojaSeguro = WorkbookUtil
                .createSafeSheetName("PRODUCTO NOMINA FEDERAL", '_');
        hoja = libro.createSheet(nombreHojaSeguro);
    }

    private void llenarTitulos(final List<String> titulos) {
        Row fila = hoja.createRow(0);

        CellStyle estiloTitulo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        estiloTitulo.setFont(font);

        for (int i = 0; i < titulos.size(); i++) {
            Cell celda = fila.createCell(i, CellType.STRING);
            celda.setCellValue(titulos.get(i));
            celda.setCellStyle(estiloTitulo);
        }
    }

    private void llenarDetalle(final List<Object[]> datos) {
        int i = FILA_INICIO_DETALLE;

        CellStyle estiloMoneda = libro.createCellStyle();
        DataFormat monedaDataFormat = libro.createDataFormat();
        estiloMoneda.setDataFormat(monedaDataFormat.getFormat("$#,#0.00"));

        DataFormat fechaDataFormat = libro.createDataFormat();
        String patronFecha = DateFormatConverter.convert(FechaUtil.LUGAR_MEXICO,
                FechaUtil.PATRON_FECHA_CORTA);
        CellStyle estiloFecha = libro.createCellStyle();
        estiloFecha.setDataFormat(fechaDataFormat.getFormat(patronFecha));

        Double[] totales = new Double[datos.get(0).length];

        for (Object[] row : datos) {
            Row fila = hoja.createRow(i);
            for (int j = 0; j < row.length; j++) {
                Object column = row[j];
                if (column instanceof String) {
                    Cell celda = fila.createCell(j, CellType.STRING);
                    celda.setCellValue((String) column);
                } else if (column instanceof BigDecimal) {
                    Cell celda = fila.createCell(j, CellType.NUMERIC);
                    BigDecimal decimal = (BigDecimal) column;
                    double valor = decimal.doubleValue();

                    if (totales[j] == null) {
                        totales[j] = 0.0;
                    }

                    totales[j] = totales[j] + valor;

                    celda.setCellValue(valor);
                    celda.setCellStyle(estiloMoneda);
                } else if (column instanceof Long) {
                    Cell celda = fila.createCell(j, CellType.NUMERIC);
                    Long numero = (Long) column;
                    celda.setCellValue(numero.intValue());
                } else if (column instanceof Date) {
                    Cell celda = fila.createCell(j);
                    Date fecha = (Date) column;
                    celda.setCellValue(fecha);
                    celda.setCellStyle(estiloFecha);
                }
            }
            i++;
        }

        llenarTotales(totales);
        totales = null;
    }

    private void llenarTotales(final Double[] totales) {
        CellStyle estiloMoneda = libro.createCellStyle();
        DataFormat monedaDataFormat = libro.createDataFormat();
        estiloMoneda.setDataFormat(monedaDataFormat.getFormat("$#,#0.00"));

        int ultimaFila = hoja.getLastRowNum();
        Row fila = hoja.createRow(ultimaFila + 1);

        for (int j = 0; j < totales.length; j++) {
            if (totales[j] != null) {
                Cell celda = fila.createCell(j, CellType.NUMERIC);
                celda.setCellValue(totales[j]);
                celda.setCellStyle(estiloMoneda);
            }
        }
    }

    private byte[] finalizar() throws IOException {
        byte[] archivo;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            libro.write(baos);
            archivo = baos.toByteArray();
            libro.close();
        }

        return archivo;
    }

    /**
     * Permite generar un reporte en una hoja de calculo con los datos del pago
     * general.
     *
     * @param titulos
     *            los titulos de las columnas del reporte.
     * @param datos
     *            los datos que se pondrán en la hoja de calculos.
     * @return un arreglo de bytes que representa una hoja de calculos del
     *         reporte de pago general.
     * @throws IOException
     *             en caso de que haya algún error al crear el archivo.
     */
    public byte[] obtenerBytes(List<String> titulos, List<Object[]> datos)
            throws IOException {
        inicializar();
        llenarTitulos(titulos);
        llenarDetalle(datos);
        return finalizar();
    }

}
