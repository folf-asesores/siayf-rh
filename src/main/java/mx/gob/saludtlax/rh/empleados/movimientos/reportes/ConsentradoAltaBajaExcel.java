/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ConsentradoAltaBajaExcel {

    private final InputStream is = ConsentradoAltaBajaExcel.class
            .getResourceAsStream(
                    "/plantillas/concentradoAltaBaja/Concentrado_Altas_Bajas.xlsx");

    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Concentrado_Altas_Bajas";

    /**
     * Instancia de la plantilla de Excel en memoria
     */
    private Workbook libro;
    /**
     * Instancia que representa la hoja de Excel en la que se esta trabajando
     */
    private Sheet hoja;
    /**
     * Fila en la que se iniciara a escribir los detalles.
     */
    private static final int FILA_INICIO_DETALLE = 5;

    private static final int COLUMNA_NO = 0;
    private static final int COLUMNA_RFC = 1;
    private static final int COLUMNA_FECHA = 2;
    private static final int COLUMNA_NOMBRE = 3;
    private static final int COLUMNA_CLAVE = 4;
    private static final int COLUMNA_ADSCRIPCION = 5;
    private static final int COLUMNA_TIPO_NOMBRAMIENTO = 6;
    private static final int COLUMNA_MOVIMIENTO = 7;
    private static final int COLUMNA_OBSERVACIONES = 8;

    /**
     * Este método carga de la plantilla y prepara el libro y la hoja que se
     * pueda usaer.
     *
     * @throws IOException
     *             en caso de que no se encuentre el archivo o este dañado
     *             lanzara esta excepción.
     */
    private void cargarPlantilla() throws IOException {
        libro = new XSSFWorkbook(is);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException
     *             en caso de no poder tener acceso al archivo se lanzara esta
     *             excepción.
     */
    private byte[] obtenerBytes() throws IOException {
        byte[] bytes;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            libro.close();
            is.close();
        }

        return bytes;
    }

    /**
     * Permite crear un reporte con los detalle con una lista de acumulados.
     *
     * @param detalles
     *            una lista de comisionado o licencia.
     * @return un arreglo de bytes que representa el archivo de excel.
     */
    public byte[] generar(List<ConsentradoAltaBajaExcelDTO> detalles) {
        try {

            cargarPlantilla();
            llenarDetalles(detalles);

            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Ocurrio un error al leer la platilla",
                    SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }

    }

    private void llenarDetalles(List<ConsentradoAltaBajaExcelDTO> estructura) {
        int i = FILA_INICIO_DETALLE;
        int numeroConsecutivo = 1;

        for (ConsentradoAltaBajaExcelDTO detalle : estructura) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaNum = filaDetalle.createCell(COLUMNA_NO);
            celdaNum.setCellValue(numeroConsecutivo);

            Cell celdaRfc = filaDetalle.createCell(COLUMNA_RFC);
            celdaRfc.setCellValue(detalle.getRfc());

            Cell celdaFecha = filaDetalle.createCell(COLUMNA_FECHA);
            celdaFecha.setCellValue(FechaUtil.formatoFecha(detalle.getFecha()));

            Cell celdaNombre = filaDetalle.createCell(COLUMNA_NOMBRE);
            celdaNombre.setCellValue(detalle.getNombreCompleto());

            Cell celdaClave = filaDetalle.createCell(COLUMNA_CLAVE);
            celdaClave.setCellValue(detalle.getClavePresupuestal());

            Cell celdaAdscripcion = filaDetalle.createCell(COLUMNA_ADSCRIPCION);
            celdaAdscripcion.setCellValue(detalle.getAdscripcion());

            Cell celdaTipo = filaDetalle.createCell(COLUMNA_TIPO_NOMBRAMIENTO);
            celdaTipo.setCellValue(detalle.getTipoNombramiento());

            Cell celdaMov = filaDetalle.createCell(COLUMNA_MOVIMIENTO);
            celdaMov.setCellValue(detalle.getMovimiento());

            Cell celdaOb = filaDetalle.createCell(COLUMNA_OBSERVACIONES);
            celdaOb.setCellValue(detalle.getObservaciones());

            numeroConsecutivo++;
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
