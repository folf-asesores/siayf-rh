/*
 * SIIFEncabezadoExcel.java
 * Creado el 4/07/2016 05:05:57 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mx.gob.saludtlax.rh.siif.reportarcontratos.ReglaNegocioException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class SIIFEncabezadoExcelContratos {

    private final InputStream is = SIIFEncabezadoExcelContratos.class.getResourceAsStream("/encabezado--plantilla.xlsx");
    
    /** El nombre de la hoja donde se encuentra el detalle */
    private static final String NOMBRE_HOJA = "Encabezado";
    /** Instancia de la plantilla de Excel en memoria */
    private Workbook libro;
    /** Instancia que representa la hoja de Excel en la que se esta trabajando */
    private Sheet hoja;
    
    private static final int FILA_INICIO_DETALLE = 1;
    
    private static final int COLUMNA_NUM_EMP =		  'A' - 'A';
    private static final int COLUMNA_RFC =   		  'B' - 'A';
    private static final int COLUMNA_CURP =   		  'C' - 'A';
    private static final int COLUMNA_NOMBRE =  		  'D' - 'A';
    private static final int COLUMNA_SAR =     		  'E' - 'A';
    private static final int COLUMNA_BANCO_A = 		  'F' - 'A';
    private static final int COLUMNA_BANCO_N = 		  'G' - 'A';
    private static final int COLUMNA_NUM_CTA = 		  'H' - 'A';
    private static final int COLUMNA_CLABE =   		  'I' - 'A';
    private static final int COLUMNA_FUNCION = 		  'J' - 'A';
    
    private static final int COLUMNA_CP = 		 	  'K' - 'A';
    private static final int COLUMNA_CALLE =      	  'L' - 'A';
    private static final int COLUMNA_PUESTO =     	  'M' - 'A';
    private static final int COLUMNA_DES_PUESTO = 	  'N' - 'A';
    private static final int COLUMNA_UR = 	      	  'O' - 'A';
    private static final int COLUMNA_GF = 		  	  'P' - 'A';
    private static final int COLUMNA_FN = 		  	  'Q' - 'A';
    private static final int COLUMNA_SF = 		  	  'R' - 'A';
    private static final int COLUMNA_PG = 		  	  'S' - 'A';
    private static final int COLUMNA_AI =      	  	  'T' - 'A';
    
    private static final int COLUMNA_PP = 			  'U' - 'A';
    private static final int COLUMNA_PARTIDA =     	  'V' - 'A';
    private static final int COLUMNA_PUESTO_TAB =     'W' - 'A';
    private static final int COLUMNA_NUM_PTO =  	  'X' - 'A';
    private static final int COLUMNA_EDO =     		  'Y' - 'A';
    private static final int COLUMNA_MUNICIPIO = 	  'Z' - 'A';
    private static final int COLUMNA_CR = 			  'A' - 'A' + 26;
    private static final int COLUMNA_CI = 			  'B' - 'A' + 26;
    private static final int COLUMNA_PAGAD = 		  'C' - 'A' + 26;
    private static final int COLUMNA_FINANCIAMIENTO = 'D' - 'A' + 26;
    
    private static final int COLUMNA_TABPTO = 		  'E' - 'A' + 26;
    private static final int COLUMNA_NIVEL =     	  'F' - 'A' + 26;
    private static final int COLUMNA_RANGO =     	  'G' - 'A' + 26;
    private static final int COLUMNA_INDMANDO =  	  'H' - 'A' + 26;
    private static final int COLUMNA_HORAS =     	  'I' - 'A' + 26;
    private static final int COLUMNA_PORCENTAJE = 	  'J' - 'A' + 26;
    private static final int COLUMNA_TIPOTRAB = 	  'K' - 'A' + 26;
    private static final int COLUMNA_NIVELPTO = 	  'L' - 'A' + 26;
    private static final int COLUMNA_INDEMP = 		  'M' - 'A' + 26;
    private static final int COLUMNA_FEC_INICIAL = 	  'N' - 'A' + 26;
    
    private static final int COLUMNA_FEC_FINAL = 	  'O' - 'A' + 26;
    private static final int COLUMNA_FEC_INGRESO =    'P' - 'A' + 26;
    private static final int COLUMNA_TIPOMOV =     	  'Q' - 'A' + 26;
    private static final int COLUMNA_FPAGO =  	  	  'R' - 'A' + 26;
    private static final int COLUMNA_PPAGOI =     	  'S' - 'A' + 26;
    private static final int COLUMNA_PPAGOF = 	  	  'T' - 'A' + 26;
    private static final int COLUMNA_PQNAI = 		  'U' - 'A' + 26;
    private static final int COLUMNA_PQNAF = 		  'V' - 'A' + 26;
    private static final int COLUMNA_QNAREAL = 		  'W' - 'A' + 26;
    private static final int COLUMNA_ANIOREAL = 	  'X' - 'A' + 26;
    
    private static final int COLUMNA_TIPOPAGO = 	  'Y' - 'A' + 26;
    private static final int COLUMNA_INSTRUI =  	  'Z' - 'A' + 26;
    private static final int COLUMNA_INSTRUN =     	  'A' - 'A' + 26;
    private static final int COLUMNA_PER =  	  	  'B' - 'A' + 26;
    private static final int COLUMNA_DED =     		  'C' - 'A' + 52;
    private static final int COLUMNA_NETO = 	  	  'D' - 'A' + 52;
    private static final int COLUMNA_NOTRAIL = 		  'E' - 'A' + 52;
    private static final int COLUMNA_DIASLAB = 		  'F' - 'A' + 52;
    private static final int COLUMNA_NOMPROD = 		  'G' - 'A' + 52;
    private static final int COLUMNA_NUMCTROL = 	  'H' - 'A' + 52;
    
    private static final int COLUMNA_NUMCHEQ = 		  'I' - 'A' + 52;
    private static final int COLUMNA_DIGVER =  	 	  'J' - 'A' + 52;
    private static final int COLUMNA_JORNADA =     	  'K' - 'A' + 52;
    private static final int COLUMNA_DIASP =  	  	  'L' - 'A' + 52;
    private static final int COLUMNA_CICLOF =         'M' - 'A' + 52;
    private static final int COLUMNA_NUMAPORT = 	  'N' - 'A' + 52;
    private static final int COLUMNA_ACUMF = 		  'O' - 'A' + 52;
    private static final int COLUMNA_FALTAS = 		  'P' - 'A' + 52;
    private static final int COLUMNA_CLUES = 		  'Q' - 'A' + 52;
    private static final int COLUMNA_PORPEN01 = 	  'R' - 'A' + 52;
    
    private static final int COLUMNA_PORPEN02 =		  'S' - 'A' + 52;
    private static final int COLUMNA_PORPEN03 =  	  'T' - 'A' + 52;
    private static final int COLUMNA_PORPEN04 =       'U' - 'A' + 52;
    private static final int COLUMNA_PORPEN05 =  	  'V' - 'A' + 52;
    private static final int COLUMNA_ISSSTE =         'W' - 'A' + 52;
    private static final int COLUMNA_TIPOUNI = 		  'X' - 'A' + 52;
    private static final int COLUMNA_CRESPDES = 	  'Y' - 'A' + 52;
    

    
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
     * @throws IOException
     *             en caso de no poder tener acceso al archivo se lanzara esta
     *             excepción.
     */
    private byte[] obtenerBytes() throws IOException {
        byte[] bytes;
        
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        }

        return bytes;
    }

    
    /**
     * Este método se encarga de llenar la fila de detalles en los anexos.
     * 
     * @param listaDetallesAnexo
     *            los datos que se guardaran en archivo de Excel.
     */
    private void llenarDetalles(List<SIIFEncabezadoDTO> listaDetallesAnexo) {
        int i = FILA_INICIO_DETALLE;
        
        for (SIIFEncabezadoDTO detalle : listaDetallesAnexo) {
            Row filaDetalle = hoja.createRow(i);
            Cell celdaIdNomina = filaDetalle.createCell(COLUMNA_NUM_EMP);
            
            Iterator<Row> fi = hoja.iterator();
            int count = 0;
            
            while (fi.hasNext()) {
            	if(count > 1) {
					Row row = fi.next();
					row.getCell(COLUMNA_NOMBRE);
					row.getCell(COLUMNA_NUM_CTA);
					row.getCell(COLUMNA_FUNCION);
				}
            	
				count++;
			}
            
            for(Row fila : hoja) {
            	if(count > 1) {
            		fila.getCell(COLUMNA_NUM_CTA);
            	}
            }
            
            celdaIdNomina.setCellValue(detalle.getIdNomina() == null ? 0: detalle.getIdNomina());

            Cell celdaIdPoder = filaDetalle.createCell(COLUMNA_RFC);
            celdaIdPoder.setCellValue(detalle.getIdPoder().toString());
            
            Cell celdaIdTipoNomina = filaDetalle.createCell(COLUMNA_CURP);
            celdaIdTipoNomina.setCellValue(detalle.getIdTipoNomina());
            
            //Verificar FechaFinQuincena
            Date fecha= new Date(2016-07-31);
            SimpleDateFormat formato=new SimpleDateFormat("YYYY-MM-dd");
            String fechaString ="2016-07-31";
            try{
            	fecha=formato.parse(fechaString);
            }catch(ParseException ex){
            	ex.printStackTrace();
            }
            if( detalle.getFechaFinQuincena() != null)
            	fecha=detalle.getFechaFinQuincena();
            System.out.println("Fecha:: "+fecha);
            
            Cell celdaFechaFinQuincena = filaDetalle.createCell(COLUMNA_NOMBRE);
            celdaFechaFinQuincena.setCellValue(fecha);
            //celdaFechaFinQuincena.setCellValue(detalle.getFechaFinQuincena());
            
            Cell celdaIdTipoEmisionNomina = filaDetalle.createCell(COLUMNA_SAR);
            celdaIdTipoEmisionNomina.setCellValue(detalle.getIdTipoEmisionNomina().toString());
            
            Cell celdaIdCuentaBancaria = filaDetalle.createCell(COLUMNA_BANCO_A);
            celdaIdCuentaBancaria.setCellValue(detalle.getIdCuentaBancaria());
            
            Cell celdaPercepciones = filaDetalle.createCell(COLUMNA_BANCO_N);   
            celdaPercepciones.setCellValue(detalle.getPercepciones().doubleValue());
            
            Cell celdaDeducciones = filaDetalle.createCell(COLUMNA_NUM_CTA);
            celdaDeducciones.setCellValue(detalle.getDeducciones().doubleValue());
            
            Cell celdaNeto = filaDetalle.createCell(COLUMNA_CLABE);
            celdaNeto.setCellValue(detalle.getNeto().doubleValue());
            
            Cell celdaIdEstadoNomina = filaDetalle.createCell(COLUMNA_FUNCION);
            celdaIdEstadoNomina.setCellValue(detalle.getIdEstadoNomina().toString());
            
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }
    
/**
     * Este método carga la plantilla según el anexo, llama al método
     * {@link #llenarDetalles(List) llenarDetalles} para llenar el archivo de
     * Excel con la información que recibe por parametro y finalmente devuelve
     * la plantilla llena como arreglo de bytes.
     * 
     * @param listaDetalles
     *            la información para llenar el archivo de Excel.
     * @return un arreglo de bytes que representan al archivo de Excel.
     */
    public byte[] generar(List<SIIFEncabezadoDTO> listaDetalles) {
            try {
                    cargarPlantilla();
                    llenarDetalles(listaDetalles);
                    return obtenerBytes();
            } catch (IOException e) {
                    throw new ReglaNegocioException(
                                    "Ocurrio un error al leer la platilla");
            }
    }
}