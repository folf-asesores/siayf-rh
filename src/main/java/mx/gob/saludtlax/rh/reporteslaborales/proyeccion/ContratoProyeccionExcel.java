/**
 * 
 */
package mx.gob.saludtlax.rh.reporteslaborales.proyeccion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class ContratoProyeccionExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5446860642409906926L;

	private final InputStream is = ContratoExcel.class
			.getResourceAsStream("/plantillas/contrato/Contrato_Estatal_Federal_Proyectado.xlsx");

	/**
	 * El nombre de la hoja donde se encuentra el detalle
	 */
	private static final String NOMBRE_HOJA = "Contrato_Estatal_Federal_Proyec";

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

	/**
	 * columna de cada detalles
	 */
	private static final int CAPITULO_8000 = 0;
	private static final int PARTIDA_ESPECIFICA = 1;
	private static final int CONCEPTO = 2;
	private static final int ENERO = 3;
	private static final int PROYECCION_ENERO = 4;
	private static final int FEBRERO = 5;
	private static final int PROYECCION_FEBRERO = 6;
	private static final int MARZO = 7;
	private static final int PROYECCION_MARZO = 8;
	private static final int ABRIL = 9;
	private static final int PROYECCION_ABRIL = 10;
	private static final int MAYO = 11;
	private static final int PROYECCION_MAYO = 12;
	private static final int JUNIO = 13;
	private static final int PROYECCION_JUNIO = 14;
	private static final int JULIO = 15;
	private static final int PROYECCION_JULIO = 16;
	private static final int AGOSTO = 17;
	private static final int PROYECCION_AGOSTO = 18;
	private static final int SEPTIEMBRE = 19;
	private static final int PROYECCION_SEPTIEMBRE = 20;
	private static final int OCTUBRE = 21;
	private static final int PROYECCION_OCTUBRE = 22;
	private static final int NOVIEMBRE = 23;
	private static final int PROYECCION_NOVIEMBRE = 24;
	private static final int DICIEMBRE = 25;
	private static final int PROYECCION_DICIEMBRE = 26;
	private static final int TOTAL = 27;
	private static final int DISPONIBILIDAD = 28;

	/**
	 * Totales
	 */
	private static BigDecimal TOTAL_ENERO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_ENERO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_FEBRERO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_FEBRERO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_MARZO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_MARZO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_ABRIL = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_ABRIL = BigDecimal.ZERO;
	private static BigDecimal TOTAL_MAYO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_MAYO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_JUNIO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_JUNIO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_JULIO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_JULIO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_AGOSTO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_AGOSTO = BigDecimal.ZERO;
	private static BigDecimal TOTAL_SEPTIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_SEPTIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_OCTUBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_OCTUBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_NOVIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_NOVIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_DICIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_PROYECCION_DICIEMBRE = BigDecimal.ZERO;
	private static BigDecimal TOTAL_TOTAL = BigDecimal.ZERO;
	private static BigDecimal TOTAL_DISPONIBILIDAD = BigDecimal.ZERO;

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
			throw new SistemaException("Ocurrio un error al leer la platilla",
					SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
		}

	}

	private void llenarDetalles(List<ProyeccionesPresupuestalesDTO> estructura) {
		int i = FILA_INICIO_DETALLE;
		int filaTotales = FILA_INICIO_DETALLE;

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
			TOTAL_ENERO = TOTAL_ENERO.add(detalle.getEnero());

			Cell celdaProyeccionEnero = filaDetalle.createCell(PROYECCION_ENERO);
			celdaProyeccionEnero.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionEnero()));
			TOTAL_PROYECCION_ENERO = TOTAL_PROYECCION_ENERO.add(detalle.getProyeccionEnero());

			Cell celdaFebrero = filaDetalle.createCell(FEBRERO);
			celdaFebrero.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getFebrero()));
			TOTAL_FEBRERO = TOTAL_FEBRERO.add(detalle.getFebrero());

			Cell celdaProyeccionFebrero = filaDetalle.createCell(PROYECCION_FEBRERO);
			celdaProyeccionFebrero
					.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionFebrero()));
			TOTAL_PROYECCION_FEBRERO = TOTAL_PROYECCION_FEBRERO.add(detalle.getProyeccionFebrero());

			Cell celdaMarzo = filaDetalle.createCell(MARZO);
			celdaMarzo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getMarzo()));
			TOTAL_MARZO = TOTAL_MARZO.add(detalle.getMarzo());

			Cell celdaProyeccionMarzo = filaDetalle.createCell(PROYECCION_MARZO);
			celdaProyeccionMarzo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionMarzo()));
			TOTAL_PROYECCION_MARZO = TOTAL_PROYECCION_MARZO.add(detalle.getProyeccionMarzo());

			Cell celdaAbril = filaDetalle.createCell(ABRIL);
			celdaAbril.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getAbril()));
			TOTAL_ABRIL = TOTAL_ABRIL.add(detalle.getAbril());

			Cell celdaProyeccionAbril = filaDetalle.createCell(PROYECCION_ABRIL);
			celdaProyeccionAbril.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionAbril()));
			TOTAL_PROYECCION_ABRIL = TOTAL_PROYECCION_ABRIL.add(detalle.getProyeccionAbril());

			Cell celdaMayo = filaDetalle.createCell(MAYO);
			celdaMayo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getMayo()));
			TOTAL_MAYO = TOTAL_MAYO.add(detalle.getMayo());

			Cell celdaProyeccionMayo = filaDetalle.createCell(PROYECCION_MAYO);
			celdaProyeccionMayo.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionMayo()));
			TOTAL_PROYECCION_MAYO = TOTAL_PROYECCION_MAYO.add(detalle.getProyeccionMayo());

			Cell celdaJunio = filaDetalle.createCell(JUNIO);
			celdaJunio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getJunio()));
			TOTAL_JUNIO = TOTAL_JUNIO.add(detalle.getJunio());

			Cell celdaProyeccionJunio = filaDetalle.createCell(PROYECCION_JUNIO);
			celdaProyeccionJunio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionJunio()));
			TOTAL_PROYECCION_JUNIO = TOTAL_PROYECCION_JUNIO.add(detalle.getProyeccionJunio());

			Cell celdaJulio = filaDetalle.createCell(JULIO);
			celdaJulio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getJulio()));
			TOTAL_JULIO = TOTAL_JULIO.add(detalle.getJulio());

			Cell celdaProyeccionJulio = filaDetalle.createCell(PROYECCION_JULIO);
			celdaProyeccionJulio.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionJulio()));
			TOTAL_PROYECCION_JULIO = TOTAL_PROYECCION_JULIO.add(detalle.getProyeccionJulio());

			Cell celdaAgosto = filaDetalle.createCell(AGOSTO);
			celdaAgosto.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getAgosto()));
			TOTAL_AGOSTO = TOTAL_AGOSTO.add(detalle.getAgosto());

			Cell celdaProyeccionAgosto = filaDetalle.createCell(PROYECCION_AGOSTO);
			celdaProyeccionAgosto.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionAgosto()));
			TOTAL_PROYECCION_AGOSTO = TOTAL_PROYECCION_AGOSTO.add(detalle.getProyeccionAgosto());

			Cell celdaSeptiembre = filaDetalle.createCell(SEPTIEMBRE);
			celdaSeptiembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getSeptiembre()));
			TOTAL_SEPTIEMBRE = TOTAL_SEPTIEMBRE.add(detalle.getSeptiembre());

			Cell celdaProyeccionSeptiembre = filaDetalle.createCell(PROYECCION_SEPTIEMBRE);
			celdaProyeccionSeptiembre
					.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionSeptiembre()));
			TOTAL_PROYECCION_SEPTIEMBRE = TOTAL_PROYECCION_SEPTIEMBRE.add(detalle.getProyeccionSeptiembre());

			Cell celdaOctubre = filaDetalle.createCell(OCTUBRE);
			celdaOctubre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getOctubre()));
			TOTAL_OCTUBRE = TOTAL_OCTUBRE.add(detalle.getOctubre());

			Cell celdaProyeccionOctubre = filaDetalle.createCell(PROYECCION_OCTUBRE);
			celdaProyeccionOctubre
					.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionOctubre()));
			TOTAL_PROYECCION_OCTUBRE = TOTAL_PROYECCION_OCTUBRE.add(detalle.getProyeccionOctubre());

			Cell celdaNoviembre = filaDetalle.createCell(NOVIEMBRE);
			celdaNoviembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getNoviembre()));
			TOTAL_NOVIEMBRE = TOTAL_NOVIEMBRE.add(detalle.getNoviembre());

			Cell celdaProyeccionNoviembre = filaDetalle.createCell(PROYECCION_NOVIEMBRE);
			celdaProyeccionNoviembre
					.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionNoviembre()));
			TOTAL_PROYECCION_NOVIEMBRE = TOTAL_PROYECCION_NOVIEMBRE.add(detalle.getProyeccionNoviembre());

			Cell celdaDiciembre = filaDetalle.createCell(DICIEMBRE);
			celdaDiciembre.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getDiciembre()));
			TOTAL_DICIEMBRE = TOTAL_DICIEMBRE.add(detalle.getDiciembre());

			Cell celdaProyeccionDiciembre = filaDetalle.createCell(PROYECCION_DICIEMBRE);
			celdaProyeccionDiciembre
					.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getProyeccionDiciembre()));
			TOTAL_PROYECCION_DICIEMBRE = TOTAL_PROYECCION_DICIEMBRE.add(detalle.getProyeccionDiciembre());

			Cell celdaTotal = filaDetalle.createCell(TOTAL);
			celdaTotal.setCellValue("$ " + new DecimalFormat(pattern).format(detalle.getTotal()));
			TOTAL_TOTAL = TOTAL_TOTAL.add(detalle.getTotal());

			Cell celdaDisponibilidad = filaDetalle.createCell(DISPONIBILIDAD);
			celdaDisponibilidad.setCellValue("$ - " + new DecimalFormat(pattern).format(detalle.getTotal()));
			TOTAL_DISPONIBILIDAD = TOTAL_DISPONIBILIDAD.add(detalle.getTotal());

			filaTotales++;
			i++;
			hoja.shiftRows(i, i + 1, 1);
		}

		if (filaTotales > FILA_INICIO_DETALLE) {

//			filaTotales = filaTotales + 1;

			String totales = "TOTALES: ";

			Row filaDetalle = hoja.createRow(filaTotales);

			Cell celdaConcepto = filaDetalle.createCell(CONCEPTO);
			celdaConcepto.setCellValue(totales);

			Cell celdaEnero = filaDetalle.createCell(ENERO);
			celdaEnero.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_ENERO));

			Cell celdaProyeccionEnero = filaDetalle.createCell(PROYECCION_ENERO);
			celdaProyeccionEnero.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_ENERO));

			Cell celdaFebrero = filaDetalle.createCell(FEBRERO);
			celdaFebrero.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_FEBRERO));

			Cell celdaProyeccionFebrero = filaDetalle.createCell(PROYECCION_FEBRERO);
			celdaProyeccionFebrero.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_FEBRERO));

			Cell celdaMarzo = filaDetalle.createCell(MARZO);
			celdaMarzo.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_MARZO));

			Cell celdaProyeccionMarzo = filaDetalle.createCell(PROYECCION_MARZO);
			celdaProyeccionMarzo.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_MARZO));

			Cell celdaAbril = filaDetalle.createCell(ABRIL);
			celdaAbril.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_ABRIL));

			Cell celdaProyeccionAbril = filaDetalle.createCell(PROYECCION_ABRIL);
			celdaProyeccionAbril.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_ABRIL));

			Cell celdaMayo = filaDetalle.createCell(MAYO);
			celdaMayo.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_MAYO));

			Cell celdaProyeccionMayo = filaDetalle.createCell(PROYECCION_MAYO);
			celdaProyeccionMayo.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_MAYO));

			Cell celdaJunio = filaDetalle.createCell(JUNIO);
			celdaJunio.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_JUNIO));

			Cell celdaProyeccionJunio = filaDetalle.createCell(PROYECCION_JUNIO);
			celdaProyeccionJunio.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_JUNIO));

			Cell celdaJulio = filaDetalle.createCell(JULIO);
			celdaJulio.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_JULIO));

			Cell celdaProyeccionJulio = filaDetalle.createCell(PROYECCION_JULIO);
			celdaProyeccionJulio.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_JULIO));

			Cell celdaAgosto = filaDetalle.createCell(AGOSTO);
			celdaAgosto.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_AGOSTO));

			Cell celdaProyeccionAgosto = filaDetalle.createCell(PROYECCION_AGOSTO);
			celdaProyeccionAgosto.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_AGOSTO));

			Cell celdaSeptiembre = filaDetalle.createCell(SEPTIEMBRE);
			celdaSeptiembre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_SEPTIEMBRE));

			Cell celdaProyeccionSeptiembre = filaDetalle.createCell(PROYECCION_SEPTIEMBRE);
			celdaProyeccionSeptiembre
					.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_SEPTIEMBRE));

			Cell celdaOctubre = filaDetalle.createCell(OCTUBRE);
			celdaOctubre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_OCTUBRE));

			Cell celdaProyeccionOctubre = filaDetalle.createCell(PROYECCION_OCTUBRE);
			celdaProyeccionOctubre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_OCTUBRE));

			Cell celdaNoviembre = filaDetalle.createCell(NOVIEMBRE);
			celdaNoviembre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_NOVIEMBRE));

			Cell celdaProyeccionNoviembre = filaDetalle.createCell(PROYECCION_NOVIEMBRE);
			celdaProyeccionNoviembre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_NOVIEMBRE));

			Cell celdaDiciembre = filaDetalle.createCell(DICIEMBRE);
			celdaDiciembre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_DICIEMBRE));

			Cell celdaProyeccionDiciembre = filaDetalle.createCell(PROYECCION_DICIEMBRE);
			celdaProyeccionDiciembre.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_PROYECCION_DICIEMBRE));

			Cell celdaTotal = filaDetalle.createCell(TOTAL);
			celdaTotal.setCellValue("$ " + new DecimalFormat(pattern).format(TOTAL_TOTAL));

			Cell celdaDisponibilidad = filaDetalle.createCell(DISPONIBILIDAD);
			celdaDisponibilidad.setCellValue("$ - " + new DecimalFormat(pattern).format(TOTAL_DISPONIBILIDAD));

		}

	}

}
