/*
 * PagoGeneralReporteExcelService.java
 * Creado el 13/Feb/2017 4:41:32 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.saludtlax.rh.util.FechaUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class PagoGeneralReporteExcelService implements Serializable {

    private static final long serialVersionUID = -5421623153289958107L;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralReporteExcelService.class.getName());

    /** Instancia que representa el libro de Excel sobre el que se trabajará. */
    private Workbook libro;
    /** Es la fila apartir de la cual inicia el detalle. */
    private static final int FILA_INICIO_DETALLE = 1;

    private void inicializar() {
        libro = new XSSFWorkbook();
    }
    
    private void llenarTitulos(Sheet hoja, List<String> titulos) {
        Row fila = hoja.createRow(0);

        CellStyle estiloTitulo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        estiloTitulo.setFont(font);

        for (int i = 0; i < titulos.size(); i++) {
            Cell celda = fila.createCell(i, Cell.CELL_TYPE_STRING);
            celda.setCellValue(titulos.get(i));
            celda.setCellStyle(estiloTitulo);
        }
    }
    
    private void llenarDetalle(Sheet hoja, List<Object []> datos) {
        int i = FILA_INICIO_DETALLE;

        CellStyle estiloMoneda = libro.createCellStyle();
        DataFormat monedaDataFormat = libro.createDataFormat();
        estiloMoneda.setDataFormat(monedaDataFormat.getFormat("$#,#0.00"));

        DataFormat fechaDataFormat = libro.createDataFormat();
        String patronFecha = DateFormatConverter.convert(FechaUtil.LUGAR_MEXICO, FechaUtil.PATRON_FECHA_CORTA);
        CellStyle estiloFecha = libro.createCellStyle();
        estiloFecha.setDataFormat(fechaDataFormat.getFormat(patronFecha));

        for(Object[] row : datos) {
            Row fila = hoja.createRow(i);
            for(int j = 0; j < row.length; j++) {
                Object column = row[j];
                if(column instanceof String) {
                    Cell celda = fila.createCell(j, Cell.CELL_TYPE_STRING);
                    celda.setCellValue((String) column);
                } else if(column instanceof BigDecimal) {
                    Cell celda = fila.createCell(j, Cell.CELL_TYPE_NUMERIC);
                    BigDecimal decimal = (BigDecimal) column;
                    celda.setCellValue(decimal.doubleValue());
                    celda.setCellStyle(estiloMoneda);
                } else if(column instanceof Long) {
                    Cell celda = fila.createCell(j, Cell.CELL_TYPE_NUMERIC);
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
     * @param titulos los titulos de las columnas del reporte.
     * @param datos los datos que se pondrán en la hoja de calculos.
     * @return un arreglo de bytes que representa una hoja de calculos del
     * reporte de pago general.
     * @throws IOException en caso de que haya algún error al crear el archivo.
     */
    public byte[] obtenerBytes(List<String> titulos, List<Object []> datos) throws IOException {
        inicializar();
        if (titulos.contains("idPagoNomina")) {
            int posicion = 0;
            while (posicion < titulos.size() && !"idPagoNomina".equals(titulos.get(posicion))) {
                posicion++;
            }
            Map<Integer, List<Object[]>> indiceHojas = new HashMap<>();
            for (Object[] fila : datos) {
                Integer idPagoNomina = (Integer) fila[posicion];

                if (!indiceHojas.containsKey(idPagoNomina)) {
                    List<Object[]> obj = new ArrayList<>();
                    obj.add(fila);
                    indiceHojas.put(idPagoNomina, obj);
                } else {
                    List<Object[]> obj = indiceHojas.get(idPagoNomina);
                    obj.add(fila);
                    indiceHojas.put(idPagoNomina, obj);
                }
            }
            titulos.remove(posicion);
            int contadorHojas = 1;
            for (Map.Entry<Integer, List<Object[]>> entrada : indiceHojas.entrySet()) {
                String nombreHoja = "PAGO " + contadorHojas;
                String nombreHojaSeguro = WorkbookUtil.createSafeSheetName(nombreHoja, '_');
                Sheet hoja = libro.createSheet(nombreHojaSeguro);
                llenarTitulos(hoja, titulos);
                llenarDetalle(hoja, entrada.getValue());
                contadorHojas++;
            }
            String nombreHojaSeguro = WorkbookUtil.createSafeSheetName("QNA", '_');
            Sheet hoja = libro.createSheet(nombreHojaSeguro);
            llenarTitulos(hoja, titulos);
            llenarDetalle(hoja, datos);
        } else {
            String nombreHojaSeguro = WorkbookUtil.createSafeSheetName("Nueva hoja", '_');
            Sheet hoja = libro.createSheet(nombreHojaSeguro);
        }
        return finalizar();
    }
}
