
package mx.gob.saludtlax.rh.presupuesto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

public class ReporteDistribucionPresupuesto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8421387223843989645L;

    private static final Logger LOGGER = Logger.getLogger(ReporteDistribucionPresupuesto.class);
    //Nombre de las columnas
    private static final int FILA_ENCABEZADOS = 0;
    private static final int FILA_INICIO_DETALLE = 1;
    private static final int COLUMNA_PARTIDA = 0;
    private static final int COLUMNA_TOTAL = 1;
    private static final int COLUMNA_ENERO = 2;
    private static final int COLUMNA_FEBRERO = 3;
    private static final int COLUMNA_MARZO = 4;
    private static final int COLUMNA_ABRIL = 5;
    private static final int COLUMNA_MAYO = 6;
    private static final int COLUMNA_JUNIO = 7;
    private static final int COLUMNA_JULIO = 8;
    private static final int COLUMNA_AGOSTO = 9;
    private static final int COLUMNA_SEPTIEMBRE = 10;
    private static final int COLUMNA_OCTUBRE = 11;
    private static final int COLUMNA_NOVIEMBRE = 12;
    private static final int COLUMNA_DICIEMBRE = 13;

    //Totales de las columnas
    private BigDecimal TOTAL_ENERO = BigDecimal.ZERO;
    private BigDecimal TOTAL_FEBRERO = BigDecimal.ZERO;
    private BigDecimal TOTAL_MARZO = BigDecimal.ZERO;
    private BigDecimal TOTAL_ABRIL = BigDecimal.ZERO;
    private BigDecimal TOTAL_MAYO = BigDecimal.ZERO;
    private BigDecimal TOTAL_JUNIO = BigDecimal.ZERO;
    private BigDecimal TOTAL_JULIO = BigDecimal.ZERO;
    private BigDecimal TOTAL_AGOSTO = BigDecimal.ZERO;
    private BigDecimal TOTAL_SEPTIEMBRE = BigDecimal.ZERO;
    private BigDecimal TOTAL_OCTUBRE = BigDecimal.ZERO;
    private BigDecimal TOTAL_NOVIEMBRE = BigDecimal.ZERO;
    private BigDecimal TOTAL_DICIEMBRE = BigDecimal.ZERO;
    private BigDecimal TOTAL_TOTAL = BigDecimal.ZERO;

    // Libro de excel
    private final XSSFWorkbook reporteDistribucion = new XSSFWorkbook();
    // Hoja
    private final Sheet sheet = reporteDistribucion.createSheet("Reporte");

    private static final List<String> ENCABEZADOS;

    static {
        ENCABEZADOS = new ArrayList<>();
        ENCABEZADOS.add("PARTIDA");
        ENCABEZADOS.add("TOTAL");
        ENCABEZADOS.add("ENERO");
        ENCABEZADOS.add("FEBRERO");
        ENCABEZADOS.add("MARZO");
        ENCABEZADOS.add("ABRIL");
        ENCABEZADOS.add("MAYO");
        ENCABEZADOS.add("JUNIO");
        ENCABEZADOS.add("JULIO");
        ENCABEZADOS.add("AGOSTO");
        ENCABEZADOS.add("SEPTIEMBRE");
        ENCABEZADOS.add("OCTUBRE");
        ENCABEZADOS.add("NOVIEMBRE");
        ENCABEZADOS.add("DICIEMBRE");
    }

    public byte[] generarArchivoExcel(List<DistribucionPresupuestoDTO> listaReporte) throws IOException {

        byte[] archivoExcel = null;

        Row headerRow = sheet.createRow(FILA_ENCABEZADOS);
        int i = 0;
        for (String nombre : ENCABEZADOS) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(nombre);
            estiloCeldaEncabezado(cell);
            i++;
        }
        LOGGER.debugv("lista reporte es nulo: {0}", listaReporte == null);
        llenarDetalles(listaReporte); //
        ByteArrayOutputStream reporte = new ByteArrayOutputStream();

        reporteDistribucion.write(reporte);
        archivoExcel = reporte.toByteArray();
        reporte.close();
        reporteDistribucion.close();
        return archivoExcel;
    }

    //Detalle del reporte
    public void llenarDetalles(List<DistribucionPresupuestoDTO> listaReporte) {

        //LOGGER.debugv("lista reporte es nulo: {0}", listaReporte == null);
        int inicio_detalle = FILA_INICIO_DETALLE;
        for (DistribucionPresupuestoDTO partida : listaReporte) {

            Row dataRow = sheet.createRow(inicio_detalle);

            //Celdas Detalle
            Cell celdaPartida = dataRow.createCell(COLUMNA_PARTIDA);
            celdaPartida.setCellValue((partida.getPartida() != null) ? partida.getPartida() : "");
            estiloCelda(celdaPartida);

            Cell celdaTotal = dataRow.createCell(COLUMNA_TOTAL);
            celdaTotal.setCellValue((partida.getTotal() != null) ? partida.getTotal().doubleValue() : 0);
            estiloCeldaImporte(celdaTotal);

            Cell celdaEnero = dataRow.createCell(COLUMNA_ENERO);
            celdaEnero.setCellValue((partida.getEnero() != null) ? partida.getEnero().doubleValue() : 0);
            estiloCeldaImporte(celdaEnero);

            Cell celdaFebrero = dataRow.createCell(COLUMNA_FEBRERO);
            celdaFebrero.setCellValue((partida.getFebrero() != null) ? partida.getFebrero().doubleValue() : 0);
            estiloCeldaImporte(celdaFebrero);

            Cell celdaMarzo = dataRow.createCell(COLUMNA_MARZO);
            celdaMarzo.setCellValue((partida.getMarzo() != null) ? partida.getMarzo().doubleValue() : 0);
            estiloCeldaImporte(celdaMarzo);

            Cell celdaAbril = dataRow.createCell(COLUMNA_ABRIL);
            celdaAbril.setCellValue((partida.getAbril() != null) ? partida.getAbril().doubleValue() : 0);
            estiloCeldaImporte(celdaAbril);

            Cell celdaMayo = dataRow.createCell(COLUMNA_MAYO);
            celdaMayo.setCellValue((partida.getMayo() != null) ? partida.getMayo().doubleValue() : 0);
            estiloCeldaImporte(celdaMayo);

            Cell celdaJunio = dataRow.createCell(COLUMNA_JUNIO);
            celdaJunio.setCellValue((partida.getJunio() != null) ? partida.getJunio().doubleValue() : 0);
            estiloCeldaImporte(celdaJunio);

            Cell celdaJulio = dataRow.createCell(COLUMNA_JULIO);
            celdaJulio.setCellValue((partida.getJulio() != null) ? partida.getJulio().doubleValue() : 0);
            estiloCeldaImporte(celdaJulio);

            Cell celdaAgosto = dataRow.createCell(COLUMNA_AGOSTO);
            celdaAgosto.setCellValue((partida.getAgosto() != null) ? partida.getAgosto().doubleValue() : 0);
            estiloCeldaImporte(celdaAgosto);

            Cell celdaSeptiembre = dataRow.createCell(COLUMNA_SEPTIEMBRE);
            celdaSeptiembre.setCellValue((partida.getSeptiembre() != null) ? partida.getSeptiembre().doubleValue() : 0);
            estiloCeldaImporte(celdaSeptiembre);

            Cell celdaOctubre = dataRow.createCell(COLUMNA_OCTUBRE);
            celdaOctubre.setCellValue((partida.getOctubre() != null) ? partida.getOctubre().doubleValue() : 0);
            estiloCeldaImporte(celdaOctubre);

            Cell celdaNoviembre = dataRow.createCell(COLUMNA_NOVIEMBRE);
            celdaNoviembre.setCellValue((partida.getNoviembre() != null) ? partida.getNoviembre().doubleValue() : 0);
            estiloCeldaImporte(celdaNoviembre);

            Cell celdaDiciembre = dataRow.createCell(COLUMNA_DICIEMBRE);
            celdaDiciembre.setCellValue((partida.getDiciembre() != null) ? partida.getDiciembre().doubleValue() : 0);
            estiloCeldaImporte(celdaDiciembre);

            //Celdas totales
            TOTAL_ENERO = TOTAL_ENERO.add(partida.getEnero() == null ? BigDecimal.ZERO : partida.getEnero());
            TOTAL_FEBRERO = TOTAL_FEBRERO.add(partida.getFebrero() == null ? BigDecimal.ZERO : partida.getFebrero());
            TOTAL_MARZO = TOTAL_MARZO.add(partida.getMarzo() == null ? BigDecimal.ZERO : partida.getMarzo());
            TOTAL_ABRIL = TOTAL_ABRIL.add(partida.getAbril() == null ? BigDecimal.ZERO : partida.getAbril());
            TOTAL_MAYO = TOTAL_MAYO.add(partida.getMayo() == null ? BigDecimal.ZERO : partida.getMayo());
            TOTAL_JUNIO = TOTAL_JUNIO.add(partida.getJunio() == null ? BigDecimal.ZERO : partida.getJunio());
            TOTAL_JULIO = TOTAL_JULIO.add(partida.getJulio() == null ? BigDecimal.ZERO : partida.getJulio());
            TOTAL_AGOSTO = TOTAL_AGOSTO.add(partida.getAgosto() == null ? BigDecimal.ZERO : partida.getAgosto());
            TOTAL_SEPTIEMBRE = TOTAL_SEPTIEMBRE.add(partida.getSeptiembre() == null ? BigDecimal.ZERO : partida.getSeptiembre());
            TOTAL_OCTUBRE = TOTAL_OCTUBRE.add(partida.getOctubre() == null ? BigDecimal.ZERO : partida.getOctubre());
            TOTAL_NOVIEMBRE = TOTAL_NOVIEMBRE.add(partida.getNoviembre() == null ? BigDecimal.ZERO : partida.getNoviembre());
            TOTAL_DICIEMBRE = TOTAL_DICIEMBRE.add(partida.getDiciembre() == null ? BigDecimal.ZERO : partida.getDiciembre());
            TOTAL_TOTAL = TOTAL_TOTAL.add(partida.getTotal() == null ? BigDecimal.ZERO : partida.getTotal());

            inicio_detalle++;
            sheet.shiftRows(inicio_detalle, inicio_detalle + 1, 1);

        }
        totales(inicio_detalle, listaReporte);
    }

    public void totales(int ultimaFila, List<DistribucionPresupuestoDTO> listaReporte) {

        for (DistribucionPresupuestoDTO partida : listaReporte) {

            Row dataRow = sheet.createRow(ultimaFila);

            Cell celdaTotalMensual = dataRow.createCell(COLUMNA_PARTIDA);
            celdaTotalMensual.setCellValue("TOTAL:");
            estiloCeldaFin(celdaTotalMensual);

            Cell celdaTotalEnero = dataRow.createCell(COLUMNA_ENERO);
            celdaTotalEnero.setCellValue(TOTAL_ENERO.doubleValue());
            estiloCeldaImporte(celdaTotalEnero);

            Cell celdaTotalFebrero = dataRow.createCell(COLUMNA_FEBRERO);
            celdaTotalFebrero.setCellValue(TOTAL_FEBRERO.doubleValue());
            estiloCeldaImporte(celdaTotalFebrero);

            Cell celdaTotalMarzo = dataRow.createCell(COLUMNA_MARZO);
            celdaTotalMarzo.setCellValue(TOTAL_MARZO.doubleValue());
            estiloCeldaImporte(celdaTotalMarzo);

            Cell celdaTotalAbril = dataRow.createCell(COLUMNA_ABRIL);
            celdaTotalAbril.setCellValue(TOTAL_ABRIL.doubleValue());
            estiloCeldaImporte(celdaTotalAbril);

            Cell celdaTotalMayo = dataRow.createCell(COLUMNA_MAYO);
            celdaTotalMayo.setCellValue(TOTAL_MAYO.doubleValue());
            estiloCeldaImporte(celdaTotalMayo);

            Cell celdaTotalJunio = dataRow.createCell(COLUMNA_JUNIO);
            celdaTotalJunio.setCellValue(TOTAL_JUNIO.doubleValue());
            estiloCeldaImporte(celdaTotalJunio);

            Cell celdaTotalJulio = dataRow.createCell(COLUMNA_JULIO);
            celdaTotalJulio.setCellValue(TOTAL_JULIO.doubleValue());
            estiloCeldaImporte(celdaTotalJulio);

            Cell celdaTotalAgosto = dataRow.createCell(COLUMNA_AGOSTO);
            celdaTotalAgosto.setCellValue(TOTAL_AGOSTO.doubleValue());
            estiloCeldaImporte(celdaTotalAgosto);

            Cell celdaTotalSeptiembre = dataRow.createCell(COLUMNA_SEPTIEMBRE);
            celdaTotalSeptiembre.setCellValue(TOTAL_SEPTIEMBRE.doubleValue());
            estiloCeldaImporte(celdaTotalSeptiembre);

            Cell celdaTotalOctubre = dataRow.createCell(COLUMNA_OCTUBRE);
            celdaTotalOctubre.setCellValue(TOTAL_OCTUBRE.doubleValue());
            estiloCeldaImporte(celdaTotalOctubre);

            Cell celdaTotalNoviembre = dataRow.createCell(COLUMNA_NOVIEMBRE);
            celdaTotalNoviembre.setCellValue(TOTAL_NOVIEMBRE.doubleValue());
            estiloCeldaImporte(celdaTotalNoviembre);

            Cell celdaTotalDiciembre = dataRow.createCell(COLUMNA_DICIEMBRE);
            celdaTotalDiciembre.setCellValue(TOTAL_DICIEMBRE.doubleValue());
            estiloCeldaImporte(celdaTotalDiciembre);

            Cell celdaTotalTotal = dataRow.createCell(COLUMNA_TOTAL);
            celdaTotalTotal.setCellValue(TOTAL_TOTAL.doubleValue());
            estiloCeldaImporte(celdaTotalTotal);
        }
    }

    // pinta las celdas del proveedor que fue seleccionado
    public void estiloCelda(Cell celda) {
        Font fuenteTitulo = reporteDistribucion.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 10);
        CellStyle estilo = reporteDistribucion.createCellStyle();
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        estilo.setAlignment(HorizontalAlignment.LEFT);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFont(fuenteTitulo);
        celda.setCellStyle(estilo);
        DataFormat dataFormat = reporteDistribucion.createDataFormat();
        estilo.setDataFormat(dataFormat.getFormat("$#,#0.00"));
    }

    public void estiloCeldaFin(Cell celda) {
        Font fuenteTitulo = reporteDistribucion.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 10);
        CellStyle estilo = reporteDistribucion.createCellStyle();
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        estilo.setAlignment(HorizontalAlignment.RIGHT);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFont(fuenteTitulo);
        celda.setCellStyle(estilo);
        DataFormat dataFormat = reporteDistribucion.createDataFormat();
        estilo.setDataFormat(dataFormat.getFormat("$#,#0.00"));
    }

    public void estiloCeldaImporte(Cell celda) {
        Font fuenteTitulo = reporteDistribucion.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 10);
        CellStyle estilo = reporteDistribucion.createCellStyle();
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        estilo.setAlignment(HorizontalAlignment.RIGHT);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFont(fuenteTitulo);
        celda.setCellStyle(estilo);
        DataFormat dataFormat = reporteDistribucion.createDataFormat();
        estilo.setDataFormat(dataFormat.getFormat("$#,#0.00"));
    }

    public void estiloCeldaEncabezado(Cell celda) {
        Font fuenteTitulo = reporteDistribucion.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 10);
        CellStyle estilo = reporteDistribucion.createCellStyle();
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFont(fuenteTitulo);
        celda.setCellStyle(estilo);
    }

}
