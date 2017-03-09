/*
 * AcumuladoExcel.java
 * Creado el Sep 23, 2016 8:47:25 PM
 * 
 */
package mx.gob.saludtlax.rh.acumulados;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */


public class AcumuladoExcel {

    private final InputStream is = AcumuladoExcel.class.getResourceAsStream("/plantillas/acumulados/acumulados--plantilla.xlsx");
    
    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Acumulados";
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
    private static final int FILA_INICIO_DETALLE = 1;

    private static final int COLUMNA_NUM_EMP    =  0;
    private static final int COLUMNA_NOMBRE     =  1;
    private static final int COLUMNA_RFC        =  2;
    private static final int COLUMNA_FUNCION    =  3;
    private static final int COLUMNA_UR         =  4;
    private static final int COLUMNA_GF         =  5;
    private static final int COLUMNA_FN         =  6;
    private static final int COLUMNA_SF         =  7;
    private static final int COLUMNA_PARTIDA    =  8;
    private static final int COLUMNA_PUESTO     =  9;
    private static final int COLUMNA_P_QNA_I    = 10;
    private static final int COLUMNA_P_QNA_F    = 11;
    private static final int COLUMNA_QNA_REAL   = 12;
    private static final int COLUMNA_ANIO_REAL  = 13;

    /**
     * Este método carga de la plantilla y prepara el libro y la hoja que se
     * pueda usaer.
     *
     * @throws IOException en caso de que no se encuentre el archivo o este
     * dañado lanzara esta excepción.
     */
    private void cargarPlantilla() throws IOException {
        libro = new XSSFWorkbook(is);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException en caso de no poder tener acceso al archivo se
     * lanzara esta excepción.
     */
    private byte[] obtenerBytes() throws IOException {
        byte[] bytes;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        }

        return bytes;
    }

    private void llenarDetalles (List<AcumuladoExcelDTO> estructura) {
        int i = FILA_INICIO_DETALLE;
        
        for (AcumuladoExcelDTO detalle : estructura) {
            Row filaDetalle = hoja.createRow(i);
            
            Cell celdaNumEmp = filaDetalle.createCell(COLUMNA_NUM_EMP);
            celdaNumEmp.setCellValue(detalle.numEmpleado);

            Cell celdaNombre = filaDetalle.createCell(COLUMNA_NOMBRE);
            celdaNombre.setCellValue(detalle.nombre);

            Cell celdaRfc = filaDetalle.createCell(COLUMNA_RFC);
            celdaRfc.setCellValue(detalle.rfc);

            Cell celdaFuncion = filaDetalle.createCell(COLUMNA_FUNCION);
            celdaFuncion.setCellValue(detalle.funcion);

            Cell celdaUr = filaDetalle.createCell(COLUMNA_UR);
            celdaUr.setCellValue(detalle.ur);

            Cell celdaGf = filaDetalle.createCell(COLUMNA_GF);
            celdaGf.setCellValue(detalle.gf);

            Cell celdaFn = filaDetalle.createCell(COLUMNA_FN);
            celdaFn.setCellValue(detalle.fn);

            Cell celdaSf = filaDetalle.createCell(COLUMNA_SF);
            celdaSf.setCellValue(detalle.sf);

            Cell celdaPartida = filaDetalle.createCell(COLUMNA_PARTIDA);
            celdaPartida.setCellValue(detalle.partida);

            Cell celdaPuesto = filaDetalle.createCell(COLUMNA_PUESTO);
            celdaPuesto.setCellValue(detalle.puesto);

            Cell celdaPQnaI = filaDetalle.createCell(COLUMNA_P_QNA_I);
            celdaPQnaI.setCellValue(detalle.pQnaI);

            Cell celdaPQnaF = filaDetalle.createCell(COLUMNA_P_QNA_F);
            celdaPQnaF.setCellValue(detalle.pQnaF);

            Cell celdaQnaReal = filaDetalle.createCell(COLUMNA_QNA_REAL);
            celdaQnaReal.setCellValue(detalle.qnaReal);

            Cell celdaAnioReal = filaDetalle.createCell(COLUMNA_ANIO_REAL);
            celdaAnioReal.setCellValue(detalle.anioReal);
            
            //Llena los conceptos de nomina
            for (int j = 0; j < 144; j++) {
                BigDecimal importe = detalle.conceptoNomina[j];
                Integer columna = j + COLUMNA_ANIO_REAL + 1;
                Cell celdaConcepto = filaDetalle.createCell(columna);
                celdaConcepto.setCellValue(importe.doubleValue());
            }
            
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }
    
    private void llenarTotal(List<AcumuladoExcelDTO> detalles) {
        BigDecimal[] totalesConceptosNomina = new BigDecimal[144];
        
        for(int i = 0; i < 144; i++) {
            totalesConceptosNomina[i] = BigDecimal.ZERO;
        }
        
        for (AcumuladoExcelDTO detalle : detalles) {
            for(int i = 0;  i < 144; i++) {
                totalesConceptosNomina[i] = totalesConceptosNomina[i].add(detalle.conceptoNomina[i]);
            }
        }
        
        Row filaTotales = hoja.createRow(hoja.getLastRowNum() + 1);
        
        for (int i = 0; i < 144; i++) {
            Integer columna = i + COLUMNA_ANIO_REAL + 1;
            Cell celdaTotal = filaTotales.createCell(columna);
            celdaTotal.setCellValue(totalesConceptosNomina[i].doubleValue());
        }
    }
    
    private void sumatoriaEspecial(List<AcumuladoExcelDTO> detalles) {
        int[] ids = {
            1, 60, 61, 63, 64, 65, 66, 67, 69, 70, 71, 72, 122, 133, 142
        };
        int cursor = hoja.getLastRowNum() + 2;
        
        Font fuenteTitulo = libro.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 10);
        
        CellStyle tituloCellStyle = libro.createCellStyle();
        tituloCellStyle.setFont(fuenteTitulo);
        tituloCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        tituloCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        tituloCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        tituloCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        Row filaTitulo = hoja.createRow(cursor);

        Cell celdaTitutloRfc = filaTitulo.createCell(0);
        celdaTitutloRfc.setCellStyle(tituloCellStyle);
        celdaTitutloRfc.setCellValue("RFC");

        Cell celdaTitutloTotal = filaTitulo.createCell(1);
        celdaTitutloTotal.setCellStyle(tituloCellStyle);
        celdaTitutloTotal.setCellValue("Total");
        
        for (AcumuladoExcelDTO detalle : detalles) {
            
            BigDecimal totalConcepto = BigDecimal.ZERO;
            
            for(int id : ids) {
                BigDecimal concepto = detalle.conceptoNomina[id - 1];
                totalConcepto = totalConcepto.add(concepto);
            }

            cursor += 1;

            Row filaTotales = hoja.createRow(cursor);
            Cell celdaRfc = filaTotales.createCell(0);
            celdaRfc.setCellValue(detalle.rfc);
            Cell celdaTotal = filaTotales.createCell(1);
            celdaTotal.setCellValue(totalConcepto.doubleValue());
        }
    }
    
    /**
     * Permite crear un reporte con los detalle con una lista de acumulados.
     *
     * @param detalles una lista de acumulados.
     * @return un arreglo de bytes que representa el archivo de excel.
     */
    public byte[] generar(List<AcumuladosDTO> detalles) {
        try {
            List<AcumuladoExcelDTO> estructura = generarEstructura(detalles);
            
            cargarPlantilla();
            llenarDetalles(estructura);
            llenarTotal(estructura);
            sumatoriaEspecial(estructura);

            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Ocurrio un error al leer la platilla",
                    SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
        
    }
    
    private List<AcumuladoExcelDTO> generarEstructura (List<AcumuladosDTO> detalles) {
        Map<String, List<Integer>> iteradores = new HashMap<>();

            for (int i = 0; i < detalles.size(); i++) {
                AcumuladosDTO detalle = detalles.get(i);
                
                if(!iteradores.containsKey(detalle.getRfc())){
                    List<Integer> indices = new ArrayList<>();
                    indices.add(i);
                    iteradores.put(detalle.getRfc(), indices);
                } else {
                    List<Integer> indices = iteradores.get(detalle.getRfc());
                    indices.add(i);
                }
            }
            
            List<AcumuladoExcelDTO> dtos = new ArrayList<>();
            
            for (Map.Entry<String, List<Integer>> iterador : iteradores.entrySet()) {
                AcumuladoExcelDTO dto = new AcumuladoExcelDTO();
                List<Integer> indices = iterador.getValue();
                
                for (int i = 0; i < indices.size(); i++) {
                    Integer indice = indices.get(i);
                    AcumuladosDTO acumulado = detalles.get(indice);

                    if(i == 0) {
                        dto.numEmpleado = acumulado.getNumEmpleado();
                        dto.rfc = acumulado.getRfc();
                        dto.nombre = acumulado.getNombre();
                        dto.funcion = acumulado.getFuncion();
                        dto.ur = acumulado.getUr();
                        dto.gf = acumulado.getGf();
                        dto.fn = acumulado.getFn();
                        dto.sf = acumulado.getSf();
                        dto.partida = acumulado.getPartida();
                        dto.puesto =  acumulado.getPuesto();
                        dto.pQnaI = acumulado.getP_qna_i();
                        dto.pQnaF = acumulado.getP_qna_f();
                        dto.qnaReal = acumulado.getQna_real();
                        dto.anioReal = acumulado.getAnio_real();
                        
                        Integer idConceptoNomina = acumulado.getId_concepto_nomina();
                     //   System.out.println("importe::"+acumulado.getIdAcumulado()+"--"+acumulado.getImporte()+"--"+acumulado.getId_concepto_nomina());
                        dto.conceptoNomina[idConceptoNomina - 1] = acumulado.getImporte();
                        
                    } else {
                        Integer idConceptoNomina = acumulado.getId_concepto_nomina();
                       // System.out.println("importe2::"+acumulado.getIdAcumulado()+"--"+acumulado.getImporte()+"--"+acumulado.getId_concepto_nomina());
                        dto.conceptoNomina[idConceptoNomina - 1] = acumulado.getImporte();
                    }
                }
                
                dtos.add(dto);
            }
            
            Collections.sort(dtos);

            return dtos;
    }
    
    private class AcumuladoExcelDTO implements Comparable<AcumuladoExcelDTO> {
	private String numEmpleado;
	private String rfc;
	private String nombre;
	private String funcion;
	private String ur;
	private String gf;
	private String fn;
	private String sf;
	private String partida;
	private String puesto;
	private String pQnaI;
	private String pQnaF;
	private Integer qnaReal;
	private Integer anioReal;

        // Conceptos de nomina.
        private final BigDecimal[] conceptoNomina = new BigDecimal[144];
        
        public AcumuladoExcelDTO() {
            for (int i = 0; i < 144; i++) {
                conceptoNomina[i] = BigDecimal.ZERO;
            }
        }

        @Override
        public int compareTo(AcumuladoExcelDTO o) {
            return rfc.compareTo(o.rfc);
        }
    }
}
