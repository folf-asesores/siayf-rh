/*
 * DispersionExcelService.java
 * Creado el 27/Jan/2017 3:50:05 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionExcelService {

    private static final Logger LOGGER = Logger.getLogger(DispersionExcelService.class.getName());

    /** El nombre de la hoja sobre la que se trabajará. */
    private final String nombreHoja = "dispersion";
    /** Instancia que representa el libro de Excel sobre el que se trabajará. */
    private Workbook libro;
    /** Instancia que representa la hoja de Excel en la que se está trabajando. */
    private Sheet hoja;
    /** Es la fila apartir de la cual inicia el detalle. */
    private static final int FILA_INICIO_DETALLE = 1;
    // Columnas
    private static final int COLUMNA_NUMERO_DE_CUENTA = 'A' - 'A';
    private static final int COLUMNA_MONTO = 'B' - 'A';
    private static final int COLUMNA_LEYENDA = 'C' - 'A';
    private static final int COLUMNA_NOMBRE_DEL_EMPLEADO = 'D' - 'A';

    private void llenarTitulos() {
        Row fila = hoja.createRow(0);

        CellStyle estiloTitulo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        estiloTitulo.setFont(font);

        Cell numeroCuenta = fila.createCell(COLUMNA_NUMERO_DE_CUENTA, CellType.STRING);
        numeroCuenta.setCellValue("Número de cuenta");
        numeroCuenta.setCellStyle(estiloTitulo);

        Cell monto = fila.createCell(COLUMNA_MONTO, CellType.STRING);
        monto.setCellValue("Monto");
        monto.setCellStyle(estiloTitulo);

        Cell leyenda = fila.createCell(COLUMNA_LEYENDA, CellType.STRING);
        leyenda.setCellValue("Leyenda");
        leyenda.setCellStyle(estiloTitulo);

        Cell nombreEmpleado = fila.createCell(COLUMNA_NOMBRE_DEL_EMPLEADO, CellType.STRING);
        nombreEmpleado.setCellValue("Nombre del empleado");
        nombreEmpleado.setCellStyle(estiloTitulo);
    }

    private void llenarDetalle(List<DispersionDTO> detalles) {
        int i = FILA_INICIO_DETALLE;
        int contador = 0;
        double totalMonto = 0;
        String fuenteFinanciamiento = "";
        Collections.sort(detalles);

        for (DispersionDTO dispersionDTO : detalles) {
            if (i != FILA_INICIO_DETALLE && !fuenteFinanciamiento.equalsIgnoreCase(dispersionDTO.getFuenteFinanciamiento())) {
                llenarTotal(i, fuenteFinanciamiento, totalMonto, contador);
                i += 2;
                totalMonto = 0;
                contador = 0;
            }

            Row fila = hoja.createRow(i);
            Cell celdaNumeroCuenta = fila.createCell(COLUMNA_NUMERO_DE_CUENTA, CellType.STRING);
            celdaNumeroCuenta.setCellValue(dispersionDTO.getNumeroCuenta());

            Cell celdaMonto = fila.createCell(COLUMNA_MONTO, CellType.NUMERIC);
            double monto = dispersionDTO.getMonto().doubleValue();
            totalMonto += monto;
            celdaMonto.setCellValue(monto);

            Cell celdadLeyenda = fila.createCell(COLUMNA_LEYENDA, CellType.STRING);
            String leyenda = obtenerLeyenda(dispersionDTO.getFechaPago());
            celdadLeyenda.setCellValue(leyenda);

            Cell celdadNombreEmpleado = fila.createCell(COLUMNA_NOMBRE_DEL_EMPLEADO, CellType.STRING);
            celdadNombreEmpleado.setCellValue(dispersionDTO.getNombreEmpleado().toUpperCase());

            i++;
            contador++;
            fuenteFinanciamiento = dispersionDTO.getFuenteFinanciamiento();
        }

        llenarTotal(i, fuenteFinanciamiento, totalMonto, contador);
    }

    private void llenarTotal(int indice, String fuenteFinanciamiento, double totalMonto, int totalEmpleados) {
        CellStyle estiloTotalTitulo = libro.createCellStyle();
        Font fuenteTitulo = libro.createFont();
        fuenteTitulo.setBold(true);
        estiloTotalTitulo.setFont(fuenteTitulo);
        estiloTotalTitulo.setVerticalAlignment(VerticalAlignment.CENTER);
        estiloTotalTitulo.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle estiloTotalValor = libro.createCellStyle();
        estiloTotalValor.setVerticalAlignment(VerticalAlignment.CENTER);
        estiloTotalValor.setAlignment(HorizontalAlignment.RIGHT);

        Row fila = hoja.createRow(indice);
        Cell celdadFuenteFinanciemientoTitulo = fila.createCell(COLUMNA_NUMERO_DE_CUENTA, CellType.STRING);
        celdadFuenteFinanciemientoTitulo.setCellValue(String.format("Total %s", fuenteFinanciamiento));
        celdadFuenteFinanciemientoTitulo.setCellStyle(estiloTotalTitulo);

        Cell celdadFuenteFinanciemientoValor = fila.createCell(COLUMNA_MONTO, CellType.NUMERIC);
        celdadFuenteFinanciemientoValor.setCellValue(totalMonto);
        celdadFuenteFinanciemientoValor.setCellStyle(estiloTotalValor);

        Cell celdadTotalEmpleadosTitulo = fila.createCell(COLUMNA_LEYENDA, CellType.STRING);
        celdadTotalEmpleadosTitulo.setCellValue("Total empleados");
        celdadTotalEmpleadosTitulo.setCellStyle(estiloTotalTitulo);

        Cell celdadTotalEmpleadosValor = fila.createCell(COLUMNA_NOMBRE_DEL_EMPLEADO, CellType.NUMERIC);
        celdadTotalEmpleadosValor.setCellValue(totalEmpleados);
        celdadTotalEmpleadosValor.setCellStyle(estiloTotalValor);
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

    public byte[] obtenerBytes(List<List<DispersionDTO>> dispersionList) throws IOException {
        libro = new XSSFWorkbook();
        Integer number = 1;
        for (List<DispersionDTO> dispersion : dispersionList) {
            String nombreHojaSeguro = WorkbookUtil.createSafeSheetName(nombreHoja + number++, '_');
            LOGGER.info(nombreHojaSeguro);
            hoja = libro.createSheet(nombreHojaSeguro);

            llenarTitulos();
            llenarDetalle(dispersion);
        }
        return finalizar();
    }

    private String obtenerLeyenda(Date fecha) {
        if (fecha == null) {
            return "";
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        String patron = "{0,choice,1#PRIMERA|15#SEGUNDA} QUINCENA DE {1,date,MMMM}";
        Locale lugar = new Locale("es", "MX");
        MessageFormat messageFormat = new MessageFormat(patron, lugar);
        String leyenda = messageFormat.format(new Object[] { dia, fecha });
        return leyenda.toUpperCase();
    }
}
