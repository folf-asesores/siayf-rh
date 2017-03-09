package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//import java.util.Stack;
//
//import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

public class UploadExcelFileAnexo {

	private List<EstructuraDTO> estructuraDTOs;

	private Logger log = Logger.getLogger(UploadExcelFileAnexo.class.getName());

	public void validate(UploadedFile dat) {
		try {

			InputStream is = dat.getInputstream();

			estructuraDTOs = new ArrayList<>();

			Workbook wb;
			DataFormatter formatter = new DataFormatter();

			/*
			 * VALIDACION DE LA EXTENSION DEL ARCHIVO DE EXCEL XLX(2007 O MENOR)
			 * O XLSX(2010 O SUPERIOR)
			 */
			if (FilenameUtils.getExtension(dat.getFileName()).equalsIgnoreCase("xlsx")) {
				wb = new XSSFWorkbook(is);

			} else {
				wb = new HSSFWorkbook(is);

			}
			log.info("::Inicia carga de datos:: Archivo excel ");
			Sheet sheet = wb.getSheetAt(0);

			for (int fila = 1; fila <= sheet.getLastRowNum(); fila++) {

				Row row = sheet.getRow(fila);

				EstructuraDTO dto = new EstructuraDTO();

				dto.agregarDato(-1, fila);

				for (int columna = 0; columna < row.getLastCellNum(); columna++) {
					// log.info("::Inicia carga de datos:: Fila:: " + fila + "
					// Columna:: " + columna);
					Cell cell = row.getCell(columna);
					if (cell == null) {
						dto.agregarDato(columna, null);
					} else {
						String text = formatter.formatCellValue(cell);
						//log.info("Celda::"+text);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							dto.agregarDato(cell.getColumnIndex(), cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								dto.agregarDato(cell.getColumnIndex(), cell.getDateCellValue());
							} else {
								log.debug("Valor númerico en el Excel:" + cell.getNumericCellValue());
								dto.agregarDato(cell.getColumnIndex(), cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							dto.agregarDato(cell.getColumnIndex(), cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							//// toma la formula del archivo excel como valor
							//// numerico
							dto.agregarDato(cell.getColumnIndex(), cell.getNumericCellValue());
							break;

						case Cell.CELL_TYPE_BLANK:
							dto.agregarDato(columna, "");
							break;
						case Cell.CELL_TYPE_ERROR:
							dto.agregarDato(columna, "");
							break;

						default:
							dto.agregarDato(columna, null);
							break;
						}
					}
				}
				estructuraDTOs.add(dto);
			}
			log.info(":: Termina carga de datos :: ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<EstructuraDTO> getAnexoDTOs() {
		return estructuraDTOs;
	}

	public void setAnexoDTOs(List<EstructuraDTO> anexoDTOs) {
		this.estructuraDTOs = anexoDTOs;
	}
}