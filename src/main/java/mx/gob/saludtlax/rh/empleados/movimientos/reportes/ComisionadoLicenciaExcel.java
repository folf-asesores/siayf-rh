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
 * @author Eduardo Mex
 *
 */
public class ComisionadoLicenciaExcel {

    private final InputStream is = ComisionadoLicenciaExcel.class.getResourceAsStream("/plantillas/comisionadoLicencia/Comisionado_Licencia.xlsx");

    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Comisionado_Licencia";

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

    private static final int COLUMNA_TIPO_MOVIMIENTO = 0;
    private static final int COLUMNA_APELLIDO_PATERNO = 1;
    private static final int COLUMNA_APELLIDO_MATERNO = 2;
    private static final int COLUMNA_NOMBRE_EMPLEADO = 3;
    private static final int COLUMNA_TIPO_PLAZA = 4;
    private static final int COLUMNA_NUMERO_HORAS = 5;
    private static final int COLUMNA_FUNCIONES_ESPECIFICAS = 6;
    private static final int COLUMNA_CLAVE_PAGO = 7;
    private static final int COLUMNA_FECHA_INICIO = 8;
    private static final int COLUMNA_FECHA_CONCLUSION = 9;
    private static final int COLUMNA_CENTRO_TRABAJO_ORIGEN = 10;
    private static final int COLUMNA_CENTRO_TRABAJO_DESTINO = 11;

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
    public byte[] generar(List<ComisionadoLicenciaExcelDTO> detalles) {
        try {

            cargarPlantilla();
            llenarDetalles(detalles);

            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Ocurrio un error al leer la platilla", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }

    }

    private void llenarDetalles(List<ComisionadoLicenciaExcelDTO> estructura) {
        int i = FILA_INICIO_DETALLE;

        for (ComisionadoLicenciaExcelDTO detalle : estructura) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaTipoMov = filaDetalle.createCell(COLUMNA_TIPO_MOVIMIENTO);
            celdaTipoMov.setCellValue(detalle.getTipoMovimiento());

            Cell celdaApA = filaDetalle.createCell(COLUMNA_APELLIDO_PATERNO);
            celdaApA.setCellValue(detalle.getApellidoPaterno());

            Cell celdaApM = filaDetalle.createCell(COLUMNA_APELLIDO_MATERNO);
            celdaApM.setCellValue(detalle.getApellidoMaterno());

            Cell celdaNombreE = filaDetalle.createCell(COLUMNA_NOMBRE_EMPLEADO);
            celdaNombreE.setCellValue(detalle.getNombreEmpleado());

            Cell celdaTipoPlaza = filaDetalle.createCell(COLUMNA_TIPO_PLAZA);
            celdaTipoPlaza.setCellValue(detalle.getTipoPlaza());

            // Estan por defininir las hrs (por el momento lo trae vacio)
            Cell celdaNumeroHr = filaDetalle.createCell(COLUMNA_NUMERO_HORAS);
            celdaNumeroHr.setCellValue(detalle.getNumeroHoras());
            // Cell celdaNumeroHr =
            // filaDetalle.createCell(COLUMNA_NUMERO_HORAS);
            // celdaNumeroHr.setCellValue(FechaUtil.formatoHora(detalle.getNumeroHoras()));

            Cell celdaFnE = filaDetalle.createCell(COLUMNA_FUNCIONES_ESPECIFICAS);
            celdaFnE.setCellValue(detalle.getFuncionesEspecificas());

            Cell celdaCP = filaDetalle.createCell(COLUMNA_CLAVE_PAGO);
            celdaCP.setCellValue(detalle.getClavePago());

            Cell celdaFI = filaDetalle.createCell(COLUMNA_FECHA_INICIO);
            celdaFI.setCellValue(FechaUtil.formatoFecha(detalle.getFechaInicio()));

            Cell celdaFC = filaDetalle.createCell(COLUMNA_FECHA_CONCLUSION);
            celdaFC.setCellValue(FechaUtil.formatoFecha(detalle.getFechaConclusion()));

            Cell celdaCTO = filaDetalle.createCell(COLUMNA_CENTRO_TRABAJO_ORIGEN);
            celdaCTO.setCellValue(detalle.getCentroTrabajoOrigen());

            Cell celdaCTD = filaDetalle.createCell(COLUMNA_CENTRO_TRABAJO_DESTINO);
            celdaCTD.setCellValue(detalle.getCentroTrabajoDestino());

            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
