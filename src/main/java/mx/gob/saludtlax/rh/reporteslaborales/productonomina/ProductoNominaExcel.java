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
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;

/**
 * @author Eduardo Mex
 *
 */
public class ProductoNominaExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -603631720915196921L;

	private final InputStream is = ContratoExcel.class.getResourceAsStream("/plantillas/nomina/Producto_Nomina.xlsx");

	/**
	 * El nombre de la hoja donde se encuentra el detalle
	 */
	private static final String NOMBRE_HOJA = "Producto_Nomina";

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

	/**
	 * columna de cada detalles
	 */
	private static final int RFC = 0;
	private static final int NOMBRE_EMPLEADO = 1;
	private static final int FECHA_INGRESO = 2;
	private static final int CENTRO_RESPONSABILIDAD = 3;
	private static final int CONCEPTO_CENTRO_RESPONSABILIDAD = 4;
	private static final int FUNCION = 5;
	private static final int PROGRAMA = 6;
	private static final int HONORARIOS_ASIMILABLES = 7;
	private static final int SUPLENCIAS = 8;
	private static final int DIAS_ECONOMICOS = 9;
	private static final int PERCEPCION_COMPLEMENTARIA = 10;
	private static final int BONO = 11;
	private static final int AGUINALDO = 12;
	private static final int SUBSIDIO = 13;
	private static final int PRIMA_VACACIONAL = 14;
	private static final int BONIFICACION_FALTA = 15;
	private static final int RETROACTIVO = 16;
	private static final int OTROS = 17;
	private static final int FALTAS_RETARDOS = 18;
	private static final int ISR = 19;
	private static final int RESPONSABILIDADES = 20;
	private static final int PRESTAMO = 21;
	private static final int JUICIO_MERCANTIL = 22;
	private static final int CUOTA_SINDICAL = 23;
	private static final int PENSION_ALIMENTICIA = 24;
	private static final int COLUMNA_TOTAL = 25;

	/**
	 * Totales
	 */
	private BigDecimal TOTAL_HONORARIOS_ASIMILABLES = BigDecimal.ZERO;
	private BigDecimal TOTAL_SUPLENCIAS = BigDecimal.ZERO;
	private BigDecimal TOTAL_DIAS_ECONOMICOS = BigDecimal.ZERO;
	private BigDecimal TOTAL_PERCEPCION_COMPLEMENTARIA = BigDecimal.ZERO;
	private BigDecimal TOTAL_BONO = BigDecimal.ZERO;
	private BigDecimal TOTAL_AGUINALDO = BigDecimal.ZERO;
	private BigDecimal TOTAL_SUBSIDIO = BigDecimal.ZERO;
	private BigDecimal TOTAL_PRIMA_VACACIONAL = BigDecimal.ZERO;
	private BigDecimal TOTAL_BONIFICACION_FALTA = BigDecimal.ZERO;
	private BigDecimal TOTAL_RETROACTIVO = BigDecimal.ZERO;
	private BigDecimal TOTAL_OTROS = BigDecimal.ZERO;
	private BigDecimal TOTAL_FALTAS_RETARDOS = BigDecimal.ZERO;
	private BigDecimal TOTAL_ISR = BigDecimal.ZERO;
	private BigDecimal TOTAL_RESPONSABILIDADES = BigDecimal.ZERO;
	private BigDecimal TOTAL_PRESTAMO = BigDecimal.ZERO;
	private BigDecimal TOTAL_JUICIO_MERCANTIL = BigDecimal.ZERO;
	private BigDecimal TOTAL_CUOTA_SINDICAL = BigDecimal.ZERO;
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
	public byte[] generar(List<ProductosNominaExcelDTO> detalles) {
		try {
			cargarPlantilla();
			llenarDetalles(detalles);

			return obtenerBytes();
		} catch (IOException e) {
			throw new SistemaException("Ocurrio un error al leer la platilla",
					SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
		}

	}

	private void llenarDetalles(List<ProductosNominaExcelDTO> estructura) {
		int i = FILA_INICIO_DETALLE;
		int filaTotales = FILA_INICIO_DETALLE;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (ProductosNominaExcelDTO detalle : estructura) {

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

			Cell celdaConceptoCentroResponsabilidad = filaDetalle.createCell(CONCEPTO_CENTRO_RESPONSABILIDAD);
			celdaConceptoCentroResponsabilidad.setCellValue(detalle.getConceptoCentroResponsabilidad());

			Cell celdaFuncion = filaDetalle.createCell(FUNCION);
			celdaFuncion.setCellValue(detalle.getFuncion());

			Cell celdaPrograma = filaDetalle.createCell(PROGRAMA);
			celdaPrograma.setCellValue(detalle.getPrograma());
			
			Cell celdaHonorariosAsimilables = filaDetalle.createCell(HONORARIOS_ASIMILABLES, Cell.CELL_TYPE_NUMERIC);
			celdaHonorariosAsimilables.setCellValue((double) (detalle.getHonorariosAsimilables() == null ? 0
					: detalle.getHonorariosAsimilables().doubleValue()));
			TOTAL_HONORARIOS_ASIMILABLES = TOTAL_HONORARIOS_ASIMILABLES.add(
					detalle.getHonorariosAsimilables() == null ? BigDecimal.ZERO : detalle.getHonorariosAsimilables());

			Cell celdaSuplencias = filaDetalle.createCell(SUPLENCIAS);
			celdaSuplencias.setCellValue(
					(double) (detalle.getSuplencias() == null ? 0 : detalle.getSuplencias().doubleValue()));
			TOTAL_SUPLENCIAS = TOTAL_SUPLENCIAS
					.add(detalle.getSuplencias() == null ? BigDecimal.ZERO : detalle.getSuplencias());

			Cell celdaDiasEconomicos = filaDetalle.createCell(DIAS_ECONOMICOS);
			celdaDiasEconomicos.setCellValue((double) (detalle.getDiasEconomicos() == null ? 0
					: detalle.getDiasEconomicos().doubleValue()));
			TOTAL_DIAS_ECONOMICOS = TOTAL_DIAS_ECONOMICOS
					.add(detalle.getDiasEconomicos() == null ? BigDecimal.ZERO : detalle.getDiasEconomicos());

			Cell celdaPercepcionComplementaria = filaDetalle.createCell(PERCEPCION_COMPLEMENTARIA);
			celdaPercepcionComplementaria.setCellValue((double) (detalle.getPercepcionComplementaria() == null ? 0
					: detalle.getPercepcionComplementaria().doubleValue()));
			TOTAL_PERCEPCION_COMPLEMENTARIA = TOTAL_PERCEPCION_COMPLEMENTARIA
					.add(detalle.getPercepcionComplementaria() == null ? BigDecimal.ZERO
							: detalle.getPercepcionComplementaria());

			Cell celdaBono = filaDetalle.createCell(BONO);
			celdaBono.setCellValue(
					(double) (detalle.getBono() == null ? 0 : detalle.getBono().doubleValue()));
			TOTAL_BONO = TOTAL_BONO.add(detalle.getBono() == null ? BigDecimal.ZERO : detalle.getBono());

			Cell celdaAguinaldo = filaDetalle.createCell(AGUINALDO);
			celdaAguinaldo.setCellValue(
					(double) (detalle.getAguinaldo() == null ? 0 : detalle.getAguinaldo().doubleValue()));
			TOTAL_AGUINALDO = TOTAL_AGUINALDO
					.add(detalle.getAguinaldo() == null ? BigDecimal.ZERO : detalle.getAguinaldo());

			Cell celdaSubsidio = filaDetalle.createCell(SUBSIDIO);
			celdaSubsidio.setCellValue(
					(double) (detalle.getSubsidio() == null ? 0 : detalle.getSubsidio().doubleValue()));
			TOTAL_SUBSIDIO = TOTAL_SUBSIDIO
					.add(detalle.getSubsidio() == null ? BigDecimal.ZERO : detalle.getSubsidio());

			Cell celdaPrimaVacacional = filaDetalle.createCell(PRIMA_VACACIONAL);
			celdaPrimaVacacional.setCellValue((double) (detalle.getPrimaVacacional() == null ? 0
					: detalle.getPrimaVacacional().doubleValue()));
			TOTAL_PRIMA_VACACIONAL = TOTAL_PRIMA_VACACIONAL
					.add(detalle.getPrimaVacacional() == null ? BigDecimal.ZERO : detalle.getPrimaVacacional());

			Cell celdaBonificacionFalta = filaDetalle.createCell(BONIFICACION_FALTA);
			celdaBonificacionFalta.setCellValue((double) (detalle.getBonificacionFalta() == null ? 0
					: detalle.getBonificacionFalta().doubleValue()));
			TOTAL_BONIFICACION_FALTA = TOTAL_BONIFICACION_FALTA
					.add(detalle.getBonificacionFalta() == null ? BigDecimal.ZERO : detalle.getBonificacionFalta());

			Cell celdaRetroactivo = filaDetalle.createCell(RETROACTIVO);
			celdaRetroactivo.setCellValue((double) (detalle.getRetroactivo() == null ? 0
					: detalle.getRetroactivo().doubleValue()));
			TOTAL_RETROACTIVO = TOTAL_RETROACTIVO
					.add(detalle.getRetroactivo() == null ? BigDecimal.ZERO : detalle.getRetroactivo());

			Cell celdaOtros = filaDetalle.createCell(OTROS);
			celdaOtros.setCellValue(
					(double) (detalle.getOtros() == null ? 0 : detalle.getOtros().doubleValue()));
			TOTAL_OTROS = TOTAL_OTROS.add(detalle.getOtros() == null ? BigDecimal.ZERO : detalle.getOtros());

			Cell celdaFaltaRetardo = filaDetalle.createCell(FALTAS_RETARDOS);
			celdaFaltaRetardo.setCellValue((double) (detalle.getFaltasRetardos() == null ? 0
					: detalle.getFaltasRetardos().doubleValue()));
			TOTAL_FALTAS_RETARDOS = TOTAL_FALTAS_RETARDOS
					.add(detalle.getFaltasRetardos() == null ? BigDecimal.ZERO : detalle.getFaltasRetardos());

			Cell celdaIsr = filaDetalle.createCell(ISR);
			celdaIsr.setCellValue((double) (detalle.getIsr() == null ? 0 : detalle.getIsr().doubleValue()));
			TOTAL_ISR = TOTAL_ISR.add(detalle.getIsr() == null ? BigDecimal.ZERO : detalle.getIsr());

			Cell celdaResponsabilidad = filaDetalle.createCell(RESPONSABILIDADES);
			celdaResponsabilidad.setCellValue(detalle.getResponsabilidades() == null ? 0
					: detalle.getResponsabilidades().doubleValue());
			TOTAL_RESPONSABILIDADES = TOTAL_RESPONSABILIDADES
					.add(detalle.getResponsabilidades() == null ? BigDecimal.ZERO : detalle.getResponsabilidades());

			Cell celdaPrestamo = filaDetalle.createCell(PRESTAMO);
			celdaPrestamo.setCellValue(
					(double) (detalle.getPrestamo() == null ? 0 : detalle.getPrestamo().doubleValue()));
			TOTAL_PRESTAMO = TOTAL_PRESTAMO
					.add(detalle.getPrestamo() == null ? BigDecimal.ZERO : detalle.getPrestamo());

			Cell celdaJuicioMercantil = filaDetalle.createCell(JUICIO_MERCANTIL);
			celdaJuicioMercantil.setCellValue((double) (detalle.getJuicioMercantil() == null ? 0
					: detalle.getJuicioMercantil().doubleValue()));
			TOTAL_JUICIO_MERCANTIL = TOTAL_JUICIO_MERCANTIL
					.add(detalle.getJuicioMercantil() == null ? BigDecimal.ZERO : detalle.getJuicioMercantil());

			Cell celdaCuotaSindical = filaDetalle.createCell(CUOTA_SINDICAL);
			celdaCuotaSindical.setCellValue((double) (detalle.getCuotaSindical() == null ? 0
					: detalle.getCuotaSindical().doubleValue()));
			TOTAL_CUOTA_SINDICAL = TOTAL_CUOTA_SINDICAL
					.add(detalle.getCuotaSindical() == null ? BigDecimal.ZERO : detalle.getCuotaSindical());

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

			Cell celdaTotalHonorarios = filaDetalle.createCell(HONORARIOS_ASIMILABLES);
			celdaTotalHonorarios.setCellValue(TOTAL_HONORARIOS_ASIMILABLES.doubleValue());

			Cell celdaTotalSuplencias = filaDetalle.createCell(SUPLENCIAS);
			celdaTotalSuplencias.setCellValue(TOTAL_SUPLENCIAS.doubleValue());

			Cell celdaTotalDiasEconomicos = filaDetalle.createCell(DIAS_ECONOMICOS);
			celdaTotalDiasEconomicos.setCellValue(TOTAL_DIAS_ECONOMICOS.doubleValue());

			Cell celdaTotalPercepcionComplementaria = filaDetalle.createCell(PERCEPCION_COMPLEMENTARIA);
			celdaTotalPercepcionComplementaria
					.setCellValue(TOTAL_PERCEPCION_COMPLEMENTARIA.doubleValue());

			Cell celdaTotalBono = filaDetalle.createCell(BONO);
			celdaTotalBono.setCellValue(TOTAL_BONO.doubleValue());

			Cell celdaTotalAguinaldo = filaDetalle.createCell(AGUINALDO);
			celdaTotalAguinaldo.setCellValue(TOTAL_AGUINALDO.doubleValue());

			Cell celdaTotalSubsidio = filaDetalle.createCell(SUBSIDIO);
			celdaTotalSubsidio.setCellValue(TOTAL_SUBSIDIO.doubleValue());

			Cell celdaTotalPrimaVacacional = filaDetalle.createCell(PRIMA_VACACIONAL);
			celdaTotalPrimaVacacional.setCellValue(TOTAL_PRIMA_VACACIONAL.doubleValue());

			Cell celdaTotalBonificacionFalta = filaDetalle.createCell(BONIFICACION_FALTA);
			celdaTotalBonificacionFalta.setCellValue(TOTAL_BONIFICACION_FALTA.doubleValue());

			Cell celdaTotalRetroactivo = filaDetalle.createCell(RETROACTIVO);
			celdaTotalRetroactivo.setCellValue(TOTAL_RETROACTIVO.doubleValue());

			Cell celdaTotalOtros = filaDetalle.createCell(OTROS);
			celdaTotalOtros.setCellValue(TOTAL_OTROS.doubleValue());

			Cell celdaFaltasRetardos = filaDetalle.createCell(FALTAS_RETARDOS);
			celdaFaltasRetardos.setCellValue(TOTAL_FALTAS_RETARDOS.doubleValue());

			Cell celdaTotalIsr = filaDetalle.createCell(ISR);
			celdaTotalIsr.setCellValue(TOTAL_ISR.doubleValue());

			Cell celdaTotalResponsabilidades = filaDetalle.createCell(RESPONSABILIDADES);
			celdaTotalResponsabilidades.setCellValue(TOTAL_RESPONSABILIDADES.doubleValue());

			Cell celdaTotalPrestamo = filaDetalle.createCell(PRESTAMO);
			celdaTotalPrestamo.setCellValue(TOTAL_PRESTAMO.doubleValue());

			Cell celdaTotalJuicioMercantil = filaDetalle.createCell(JUICIO_MERCANTIL);
			celdaTotalJuicioMercantil.setCellValue(TOTAL_JUICIO_MERCANTIL.doubleValue());

			Cell celdaTotalCuotaSindical = filaDetalle.createCell(CUOTA_SINDICAL);
			celdaTotalCuotaSindical.setCellValue(TOTAL_CUOTA_SINDICAL.doubleValue());

			Cell celdaTotalPensionAlimenticia = filaDetalle.createCell(PENSION_ALIMENTICIA);
			celdaTotalPensionAlimenticia.setCellValue(TOTAL_PENSION_ALIMENTICIA.doubleValue());

			Cell celdaTotalTotales = filaDetalle.createCell(COLUMNA_TOTAL);
			celdaTotalTotales.setCellValue(TOTALES.doubleValue());

		}
	}
}