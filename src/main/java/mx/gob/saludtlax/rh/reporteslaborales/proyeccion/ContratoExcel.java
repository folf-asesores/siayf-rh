/*
 *
 */

package mx.gob.saludtlax.rh.reporteslaborales.proyeccion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesDTO;

/**
 * @author Eduardo Mex
 *
 */
public class ContratoExcel {

    private final InputStream is = ContratoExcel.class.getResourceAsStream("/plantillas/contrato/Contrato_Estatal_Federal.xlsx");

    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Contrato_Estatal_Federal";

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
    private static final int FILA_INICIO_DETALLE = 11;

    private static final int CAPITULO_8000 = 0;
    private static final int PARTIDA_ESPECIFICA = 1;
    private static final int CONCEPTO = 2;
    private static final int ENERO = 3;
    private static final int FEBRERO = 4;
    private static final int MARZO = 5;
    private static final int ABRIL = 6;
    private static final int MAYO = 7;
    private static final int JUNIO = 8;
    private static final int JULIO = 9;
    private static final int AGOSTO = 10;
    private static final int SEPTIEMBRE = 11;
    private static final int OCTUBRE = 12;
    private static final int NOVIEMBRE = 13;
    private static final int DICIEMBRE = 14;
    private static final int TOTAL = 15;
    private static final int DISPONIBILIDAD = 16;

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
    public byte[] generar(List<ProyeccionesPresupuestalesDTO> detalles) {
        try {

            cargarPlantilla();
            llenarDetalles(detalles);

            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Ocurrio un error al leer la platilla", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }

    }

    private void llenarDetalles(List<ProyeccionesPresupuestalesDTO> estructura) {
        int i = FILA_INICIO_DETALLE;
        int numeroConsecutivo = 1;

        String pattern = "#,##0.00#";

        for (ProyeccionesPresupuestalesDTO detalle : estructura) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaCapitulo = filaDetalle.createCell(CAPITULO_8000);
            celdaCapitulo.setCellValue("");

            Cell celdaPartidaEspecifica = filaDetalle.createCell(PARTIDA_ESPECIFICA);
            celdaPartidaEspecifica.setCellValue(detalle.getIdPartida());

            Cell celdaConcepto = filaDetalle.createCell(CONCEPTO);
            celdaConcepto.setCellValue(detalle.getPartida());

            Cell celdaEnero = filaDetalle.createCell(ENERO);
            celdaEnero.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getEnero()));

            Cell celdaFebrero = filaDetalle.createCell(FEBRERO);
            celdaFebrero.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getFebrero()));

            Cell celdaMarzo = filaDetalle.createCell(MARZO);
            celdaMarzo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getMarzo()));

            Cell celdaAbril = filaDetalle.createCell(ABRIL);
            celdaAbril.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getAbril()));

            Cell celdaMayo = filaDetalle.createCell(MAYO);
            celdaMayo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getMayo()));

            Cell celdaJunio = filaDetalle.createCell(JUNIO);
            celdaJunio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getJunio()));

            Cell celdaJulio = filaDetalle.createCell(JULIO);
            celdaJulio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getJulio()));

            Cell celdaAgosto = filaDetalle.createCell(AGOSTO);
            celdaAgosto.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getAgosto()));

            Cell celdaSeptiembre = filaDetalle.createCell(SEPTIEMBRE);
            celdaSeptiembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getSeptiembre()));

            Cell celdaOctubre = filaDetalle.createCell(OCTUBRE);
            celdaOctubre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getOctubre()));

            Cell celdaNoviembre = filaDetalle.createCell(NOVIEMBRE);
            celdaNoviembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getNoviembre()));

            Cell celdaDiciembre = filaDetalle.createCell(DICIEMBRE);
            celdaDiciembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getDiciembre()));

            Cell celdaTotal = filaDetalle.createCell(TOTAL);
            celdaTotal.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getTotal()));

            Cell celdaDisponibilidad = filaDetalle.createCell(DISPONIBILIDAD);
            celdaDisponibilidad.setCellValue("$ - " + new DecimalFormat(pattern).format(detalle.getTotal()));

            numeroConsecutivo++;
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
