/**
 * 
 */
package mx.gob.saludtlax.rh.reporteslaborales.relacionpersonalsuplente;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal.RelacionPersonalSuplenteDTO;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;

/**
 * @author Eduardo Mex
 *
 */
public class RelacionPersonalSuplenteExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9179526340442714804L;

	private final InputStream is = ContratoExcel.class
			.getResourceAsStream("/plantillas/suplencia/Relacion_Personal_Suplente.xlsx");

	/**
	 * El nombre de la hoja donde se encuentra el detalle
	 */
	private static final String NOMBRE_HOJA = "Relacion_Personal_Suplente";

	/**
	 * Instancia de la plantilla de Excel en memoria
	 */
	private Workbook libro;
	/**
	 * Instancia que representa la hoja de Excel en la que se esta trabajando
	 */
	private Sheet hoja;

	/**
	 * Fila en la que se iniciara a escribir el centro de responsabilidad
	 */
	private static final int FILA_CENTRO_RESPONSABILIDAD = 6;
	/**
	 * Fila en la que se iniciara a escribir la fecha de entrega
	 */
	private static final int FILA_FECHA_ENTREGA = 8;
	/**
	 * Fila en la que se iniciara a escribir los detalles.
	 */
	private static final int FILA_INICIO_DETALLE = 10;
	/**
	 * Columna de escabezados
	 */
	private static final int COLUMNA_DESCRIPCION_CENTRO_RESPONSABILIDAD = 0;
	private static final int COLUMNA_CENTRO_RESPONSABILIDAD = 3;
	private static final int COLUMNA_DESCRIPCION_FECHA_ENTREGA = 1;
	private static final int COLUMNA_FECHA_ENTREGA = 3;

	/**
	 * Columna de los encabezados
	 */
	private static final int NUMERO_CONSECUTIVO = 0;
	private static final int NUMERO_FOLIO = 1;
	private static final int RFC = 2;
	private static final int NOMBRE_COMPLETO = 3;
	private static final int FUNCION = 4;
	private static final int AREA_SUPLE = 5;
	private static final int PERIODO_SUPLENCIA = 6;
	private static final int IMPORTE = 7;
	private static final int OBSERVACIONES = 8;

	private int numeroConsecutivo = 1;

	private BigDecimal totalImporte = BigDecimal.ZERO;

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
	public byte[] generar(List<RelacionPersonalSuplenteDTO> detalles) {
		try {

			cargarPlantilla();
			llenarEncabezados(detalles.get(0).getCentroResponsabilidad());
			llenarDetalles(detalles);
			obtenerTotal(detalles);

			return obtenerBytes();
		} catch (IOException e) {
			throw new SistemaException("Ocurrio un error al leer la platilla",
					SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
		}

	}

	private void llenarEncabezados(String centroResponsabilidad) {

		SimpleDateFormat fechaFormat = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'de' yyyy");

		Row filaDetalle = hoja.createRow(FILA_CENTRO_RESPONSABILIDAD);

		Cell celdaCentroResponsabilidadDescripcion = filaDetalle.createCell(COLUMNA_DESCRIPCION_CENTRO_RESPONSABILIDAD);
		celdaCentroResponsabilidadDescripcion.setCellValue("C. DE RESPONSABILIDAD:");
		estiloCeldaSeleccionado(celdaCentroResponsabilidadDescripcion);

		Cell celdaCentroResponsabilidad = filaDetalle.createCell(COLUMNA_CENTRO_RESPONSABILIDAD);
		celdaCentroResponsabilidad.setCellValue(centroResponsabilidad.toUpperCase());
		celdaNormalSinCentrado(celdaCentroResponsabilidad);

		filaDetalle = hoja.createRow(FILA_FECHA_ENTREGA);

		Cell celdaFechaEntregaDescripcion = filaDetalle.createCell(COLUMNA_DESCRIPCION_FECHA_ENTREGA);
		celdaFechaEntregaDescripcion.setCellValue("FECHA DE ENTREGA:");
		estiloCeldaSeleccionado(celdaFechaEntregaDescripcion);

		Cell celdaFechaEntrega = filaDetalle.createCell(COLUMNA_FECHA_ENTREGA);
		Date fechaActual = new Date();
		String fechaCelda = fechaFormat.format(fechaActual);
		celdaFechaEntrega.setCellValue(fechaCelda);
		celdaNormalSinCentrado(celdaFechaEntrega);

	}

	private void llenarDetalles(List<RelacionPersonalSuplenteDTO> estructura) {
		int i = FILA_INICIO_DETALLE;

		int puntero = 1;

		for (RelacionPersonalSuplenteDTO detalle : estructura) {
			Row filaDetalle = hoja.createRow(i);

			Cell celdaNumeroConsecutivo = filaDetalle.createCell(NUMERO_CONSECUTIVO);
			celdaNumeroConsecutivo.setCellValue(puntero);
			celdaNormal(celdaNumeroConsecutivo);

			Cell celdaNumeroFolio = filaDetalle.createCell(NUMERO_FOLIO);
			celdaNumeroFolio.setCellValue(detalle.getNumeroFolio() == null ? "" : detalle.getNumeroFolio());
			celdaNormal(celdaNumeroFolio);

			Cell celdaRfc = filaDetalle.createCell(RFC);
			celdaRfc.setCellValue(detalle.getRfc() == null ? "" : detalle.getRfc());
			celdaNormal(celdaRfc);

			Cell celdaNombreCompleto = filaDetalle.createCell(NOMBRE_COMPLETO);
			celdaNombreCompleto.setCellValue(detalle.getNombreEmpleado() == null ? "" : detalle.getNombreEmpleado());
			celdaNormal(celdaNombreCompleto);

			Cell celdaFuncion = filaDetalle.createCell(FUNCION);
			celdaFuncion.setCellValue(detalle.getFuncion() == null ? "" : detalle.getFuncion());
			celdaNormal(celdaFuncion);

			Cell celdaAreaSuple = filaDetalle.createCell(AREA_SUPLE);
			celdaAreaSuple.setCellValue(detalle.getAreaSuple() == null ? "" : detalle.getAreaSuple());
			celdaNormal(celdaAreaSuple);

			Cell celdaPeriodoSuplencia = filaDetalle.createCell(PERIODO_SUPLENCIA);
			celdaPeriodoSuplencia
					.setCellValue(detalle.getPeriodoSuplencia() == null ? "" : detalle.getPeriodoSuplencia());
			celdaNormal(celdaPeriodoSuplencia);

			Cell celdaImporte = filaDetalle.createCell(IMPORTE);
			celdaImporte.setCellValue(
					detalle.getImporte() == null ? BigDecimal.ZERO.toString() : "$ " + detalle.getImporte().toString());
			estiloTotal(celdaImporte);

			Cell celdaObservaciones = filaDetalle.createCell(OBSERVACIONES);
			celdaObservaciones.setCellValue(detalle.getObservaciones() == null ? "" : detalle.getObservaciones());
			celdaNormal(celdaObservaciones);

			puntero++;
			i++;
			hoja.shiftRows(i, i + 1, 1);
		}

		setNumeroConsecutivo(puntero);
	}

	private void obtenerTotal(List<RelacionPersonalSuplenteDTO> estructura) {

		for (RelacionPersonalSuplenteDTO detalle : estructura) {
			totalImporte = totalImporte.add(detalle.getImporte() == null ? BigDecimal.ZERO : detalle.getImporte());
		}

		int ultimaFila = hoja.getLastRowNum();

		Row filaDetalle = hoja.createRow(ultimaFila + 1);

		Cell celdaTotal = filaDetalle.createCell(IMPORTE);
		celdaTotal.setCellValue("$ " + totalImporte.toString());
		estiloTotal(celdaTotal);

	}

	/*********** colocar firmas *********/
	// public void colocarFirmas() {
	// int ultimaFila = hoja.getLastRowNum();
	//
	// Row firmas = hoja.createRow(ultimaFila + 4);
	// Row firmasElaboro = hoja.createRow(ultimaFila + 5);
	// Row firmasAcciones = hoja.createRow(ultimaFila + 6);
	//
	// Cell celdaElaboro = firmas.createCell(COLUMNA_DESCRIPCION);
	// celdaElaboro.setCellValue(" ");
	// celdaNormal(celdaElaboro);
	//
	// Cell celdaFirmaElaboro = firmasElaboro.createCell(COLUMNA_DESCRIPCION);
	// celdaFirmaElaboro.setCellValue("NOMBRE, CARGO Y FIRMA");
	// celdaFirmas(celdaFirmaElaboro);
	//
	// Cell firmaElaboro = firmasAcciones.createCell(COLUMNA_DESCRIPCION);
	// firmaElaboro.setCellValue("ELABORO");
	// celdaFirmas(firmaElaboro);
	//
	// Cell celdaReviso = firmas.createCell(columnaCentro + 1);
	// celdaReviso.setCellValue(" ");
	// celdaNormal(celdaReviso);
	//
	// Cell celdaFirmaReviso = firmasElaboro.createCell(columnaCentro + 1);
	// celdaFirmaReviso.setCellValue("NOMBRE, CARGO Y FIRMA");
	// celdaFirmas(celdaFirmaReviso);
	//
	// Cell firmaReviso = firmasAcciones.createCell(columnaCentro + 1);
	// firmaReviso.setCellValue("REVISO");
	// celdaFirmas(firmaReviso);
	//
	// Cell celdaAutorizo = firmas.createCell(columnaFinal - 2);
	// celdaAutorizo.setCellValue(" ");
	// celdaNormal(celdaAutorizo);
	//
	// Cell celdaFirmaAutorizo = firmasElaboro.createCell(columnaFinal - 2);
	// celdaFirmaAutorizo.setCellValue("NOMBRE, CARGO Y FIRMA");
	// celdaFirmas(celdaFirmaAutorizo);
	//
	// Cell firmaAutorizo = firmasAcciones.createCell(columnaFinal - 2);
	// firmaAutorizo.setCellValue("AUTORIZO");
	// celdaFirmas(firmaAutorizo);
	//
	// }

	/****** pinta las celdas del proveedor que fue seleccionado ********/
	public void estiloCeldaSeleccionado(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 10);
		CellStyle estilo = libro.createCellStyle();
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setAlignment(CellStyle.ALIGN_LEFT);
		estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		estilo.setFillPattern(CellStyle.SOLID_FOREGROUND);
		estilo.setFont(fuenteTitulo);
		DataFormat dataFormat = libro.createDataFormat();
		estilo.setDataFormat(dataFormat.getFormat("$#,#0.00"));
		celda.setCellStyle(estilo);
	}

	public void estiloTotal(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 13);
		CellStyle estilo = libro.createCellStyle();
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setAlignment(CellStyle.ALIGN_RIGHT);
		estilo.setFont(fuenteTitulo);
		DataFormat dataFormat = libro.createDataFormat();
		estilo.setDataFormat(dataFormat.getFormat("$#,#0.00"));
		celda.setCellStyle(estilo);
	}

	public void celdaNormal(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 10);
		CellStyle estilo = libro.createCellStyle();
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFont(fuenteTitulo);
		celda.setCellStyle(estilo);
	}

	public void celdaNormalSinCentrado(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 10);
		CellStyle estilo = libro.createCellStyle();
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setAlignment(CellStyle.ALIGN_LEFT);
		estilo.setVerticalAlignment(CellStyle.ALIGN_LEFT);
		estilo.setFont(fuenteTitulo);
		celda.setCellStyle(estilo);
	}

	public void celdaFirmas(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 10);
		CellStyle estilo = libro.createCellStyle();
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFont(fuenteTitulo);
		celda.setCellStyle(estilo);
	}

	public void celdaFirmasAlineadoDerecha(Cell celda) {
		Font fuenteTitulo = libro.createFont();
		fuenteTitulo.setBold(true);
		fuenteTitulo.setFontHeightInPoints((short) 10);
		CellStyle estilo = libro.createCellStyle();
		estilo.setAlignment(CellStyle.ALIGN_RIGHT);
		estilo.setVerticalAlignment(CellStyle.ALIGN_RIGHT);
		estilo.setFont(fuenteTitulo);
		celda.setCellStyle(estilo);
	}

	/**************** Getters and Setters ***************/

	/**
	 * @return the totalImporte
	 */
	public BigDecimal getTotalImporte() {
		return totalImporte;
	}

	/**
	 * @param totalImporte
	 *            the totalImporte to set
	 */
	public void setTotalImporte(BigDecimal totalImporte) {
		this.totalImporte = totalImporte;
	}

	/**
	 * @return the numeroConsecutivo
	 */
	public int getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	/**
	 * @param numeroConsecutivo
	 *            the numeroConsecutivo to set
	 */
	public void setNumeroConsecutivo(int numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

}
