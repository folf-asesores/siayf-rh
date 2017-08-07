/**
 * 
 */
package mx.gob.saludtlax.rh.reporteslaborales.productonomina;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaProgramasExcelDTO;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoExcel;

/**
 * @author Eduardo Mex
 *
 */
public class ProductoNominaProgramasExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -603631720915196921L;

	private final InputStream is = ProductoNominaProgramasExcel.class.getResourceAsStream("/plantillas/nomina/Producto_Nomina_Programas.xlsx");
	//private final InputStream is = SIIFEncabezadoExcel.class.getResourceAsStream("/encabezado--plantilla.xlsx");

	/**
	 * El nombre de la hoja donde se encuentra el detalle
	 */
	private static final String NOMBRE_HOJA = "Producto_Nomina_Programas";

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
	private static final int FILA_INICIO_PROGRAMAS_DETALLE = 8;

	/**
	 * columna de cada detalles
	 */
	private static final int RFC = 0;
	private static final int NOMBRE_EMPLEADO = 1;
	private static final int FECHA_INGRESO = 2;
	private static final int CENTRO_RESPONSABILIDAD = 3;
	private static final int PROGRAMA = 4;
	private static final int FUNCION = 5;	
	private static final int SUELDO = 6;
	private static final int ISR = 7;
	private static final int PENSION_ALIMENTICIA = 8;
	private static final int COLUMNA_TOTAL = 9;

	/**
	 * Totales
	 */
	private BigDecimal TOTAL_SUELDO = BigDecimal.ZERO;	
	private BigDecimal TOTAL_ISR = BigDecimal.ZERO;	
	private BigDecimal TOTAL_PENSION_ALIMENTICIA = BigDecimal.ZERO;
	private BigDecimal TOTALES = BigDecimal.ZERO;

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
	public byte[] generar(List<ProductosNominaProgramasExcelDTO> detalles) {
		try {
			cargarPlantilla();
			llenarDetalles(detalles);
			return obtenerBytes();
		} catch (IOException e) {
			throw new SistemaException("Ocurrio un error al leer la platilla",
					SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
		}
	}

	private void llenarDetalles(List<ProductosNominaProgramasExcelDTO> estructura) {
		int i = FILA_INICIO_PROGRAMAS_DETALLE;
		int filaTotales = FILA_INICIO_DETALLE;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println("tamaño lista: "+ estructura.size());

		for (ProductosNominaProgramasExcelDTO detalle : estructura) {
			//System.out.println("Hoja EXCEL: " + hoja.getSheetName());
			Row filaDetalle = hoja.createRow(i);

			Cell celdaRfc = filaDetalle.createCell(RFC);
			celdaRfc.setCellValue(detalle.getRfc());

			Cell celdaNombreEmpleado = filaDetalle.createCell(NOMBRE_EMPLEADO);
			celdaNombreEmpleado.setCellValue(detalle.getNombreEmpleado());

			Cell celdaFechaIngreso = filaDetalle.createCell(FECHA_INGRESO);
			celdaFechaIngreso.setCellValue(
					detalle.getFechaIngreso() == null ? "" : simpleDateFormat.format(detalle.getFechaIngreso()));

			Cell celdaCentroResponsabilidad = filaDetalle.createCell(CENTRO_RESPONSABILIDAD);
			celdaCentroResponsabilidad.setCellValue(detalle.getCentroResponsabilidad());

			Cell celdaPrograma = filaDetalle.createCell(PROGRAMA);
			celdaPrograma.setCellValue(detalle.getPrograma());
			
			Cell celdaFuncion = filaDetalle.createCell(FUNCION);
			celdaFuncion.setCellValue(detalle.getFuncion());

			
			Cell sueldo = filaDetalle.createCell(SUELDO, Cell.CELL_TYPE_NUMERIC);
			sueldo.setCellValue((double) (detalle.getSueldo() == null ? 0
					: detalle.getSueldo().doubleValue()));
			TOTAL_SUELDO = TOTAL_SUELDO.add(
					detalle.getSueldo() == null ? BigDecimal.ZERO : detalle.getSueldo());


			Cell celdaIsr = filaDetalle.createCell(ISR);
			celdaIsr.setCellValue((double) (detalle.getIsr() == null ? 0 : detalle.getIsr().doubleValue()));
			TOTAL_ISR = TOTAL_ISR.add(detalle.getIsr() == null ? BigDecimal.ZERO : detalle.getIsr());

					
			Cell celdaPensionAlimenticia = filaDetalle.createCell(PENSION_ALIMENTICIA);
			celdaPensionAlimenticia.setCellValue((double) (detalle.getPensionAlimenticia() == null ? 0
					: detalle.getPensionAlimenticia().doubleValue()));
			TOTAL_PENSION_ALIMENTICIA = TOTAL_PENSION_ALIMENTICIA
					.add(detalle.getPensionAlimenticia() == null ? BigDecimal.ZERO : detalle.getPensionAlimenticia());

			Cell celdaTotal = filaDetalle.createCell(COLUMNA_TOTAL);
			celdaTotal.setCellValue(
					(double) (detalle.getTotal() == null ? 0 : detalle.getTotal().doubleValue()));
			TOTALES = TOTALES.add(detalle.getTotal() == null ? BigDecimal.ZERO : detalle.getTotal());

			filaTotales++;
			i++;
			hoja.shiftRows(i, i + 1, 1);
		}

		if (filaTotales > FILA_INICIO_DETALLE) {

			String general = "general";

			Row filaDetalle = hoja.createRow(filaTotales);

			Cell celdaGeneral = filaDetalle.createCell(RFC);
			celdaGeneral.setCellValue(general);

			Cell celdaFaltasRetardos = filaDetalle.createCell(SUELDO);
			celdaFaltasRetardos.setCellValue(TOTAL_SUELDO.doubleValue());

			Cell celdaTotalIsr = filaDetalle.createCell(ISR);
			celdaTotalIsr.setCellValue(TOTAL_ISR.doubleValue());;

			Cell celdaTotalPensionAlimenticia = filaDetalle.createCell(PENSION_ALIMENTICIA);
			celdaTotalPensionAlimenticia.setCellValue(TOTAL_PENSION_ALIMENTICIA.doubleValue());

			Cell celdaTotalTotales = filaDetalle.createCell(COLUMNA_TOTAL);
			celdaTotalTotales.setCellValue(TOTALES.doubleValue());

		}
	}
}