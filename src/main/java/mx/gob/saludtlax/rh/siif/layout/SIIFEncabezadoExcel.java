/*
 * SIIFEncabezadoExcel.java
 * Creado el 4/07/2016 05:05:57 PM
 *
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.siif.EstructuraSeguroPopularDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFEncabezadoExcel implements Serializable {

    private static final long serialVersionUID = 749345586381372642L;
    private static final Logger LOGGER = Logger.getLogger(SIIFEncabezadoExcel.class.getName());

    private final InputStream is = SIIFEncabezadoExcel.class.getResourceAsStream("/encabezado--plantilla.xlsx");
    private final InputStream isFinal = SIIFEncabezadoExcel.class.getResourceAsStream("/encabezado--plantilla--final.xlsx");
    private final InputStream isSegPop = SIIFEncabezadoExcel.class.getResourceAsStream("/plantilla--segpop.xlsx");

    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Encabezado";
    /**
     * El nombre de la hoja donde se encuentra el detalle de la plantilla para
     * el seguro popular
     */
    private static final String NOMBRE_HOJA_SEGURO_POPULAR = "SeguroPopular";
    /**
     * Instancia de la plantilla de Excel en memoria
     */
    private Workbook libro;
    /**
     * Instancia que representa la hoja de Excel en la que se esta trabajando
     */
    private Sheet hoja;

    private static final int FILA_INICIO_DETALLE = 1;

    private static final int COLUMNA_ID_NOMINA = 'A' - 'A';
    private static final int COLUMNA_ID_PODER = 'B' - 'A';
    private static final int COLUMNA_ID_TIPO_NOMINA = 'C' - 'A';
    private static final int COLUMNA_FECHA_FIN_QUINCENA = 'D' - 'A';
    private static final int COLUMNA_ID_TIPO_EMISION_NOMINA = 'E' - 'A';
    private static final int COLUMNA_ID_CUENTA_BANCARIA = 'F' - 'A';
    private static final int COLUMNA_PERCEPCIONES = 'G' - 'A';
    private static final int COLUMNA_DEDDUCCIONES = 'H' - 'A';
    private static final int COLUMNA_NETO = 'I' - 'A';
    private static final int COLUMNA_ID_ESTADO_NOMINA = 'J' - 'A';

    private static final int COLUMNA_CLAVE_CUENTA_BANCARIA = 'F' - 'A';

    private static final int COLUMNA_NUMERO = 'A' - 'A';
    private static final int COLUMNA_MES = 'B' - 'A';
    private static final int COLUMNA_ESTADO = 'C' - 'A';
    private static final int COLUMNA_CENTRO = 'D' - 'A';
    private static final int COLUMNA_CLUES = 'E' - 'A';
    private static final int COLUMNA_UNIDAD = 'F' - 'A';
    private static final int COLUMNA_ADSCRIPCION = 'G' - 'A';
    private static final int COLUMNA_PUESTO = 'H' - 'A';
    private static final int COLUMNA_CLAVE = 'I' - 'A';
    private static final int COLUMNA_SERVICIO = 'J' - 'A';
    private static final int COLUMNA_RAMA = 'K' - 'A';
    private static final int COLUMNA_NOMBRE = 'L' - 'A';
    private static final int COLUMNA_RFC = 'M' - 'A';
    private static final int COLUMNA_TURNO = 'N' - 'A';
    private static final int COLUMNA_FECHA = 'O' - 'A';
    private static final int COLUMNA_SUELDO = 'P' - 'A';
    private static final int COLUMNA_PERCEPCIONES_NETAS = 'Q' - 'A';
    private static final int COLUMNA_DEDUCCIONES_NETAS = 'R' - 'A';
    private static final int COLUMNA_NETAS = 'S' - 'A';
    private FileWriter writer = null;
    private File archivo;

    /**
     * Este método carga de la plantilla y prepara el libro y la hoja que se
     * pueda usaer.
     *
     * @throws IOException
     *             en caso de que no se encuentre el archivo o este
     *             dañado lanzara esta excepción.
     */
    private void cargarPlantilla() throws IOException {
        libro = new XSSFWorkbook(is);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    private void cargarPlantillaFinal() throws IOException {
        libro = new XSSFWorkbook(isFinal);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    private void cargarPlantillaSegPop() throws IOException {
        libro = new XSSFWorkbook(isSegPop);
        hoja = libro.getSheet(NOMBRE_HOJA_SEGURO_POPULAR);
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException
     *             en caso de no poder tener acceso al archivo se
     *             lanzara esta excepción.
     */
    private byte[] obtenerBytes() throws IOException {
        byte[] bytes;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            libro.close();
            is.close();
            isFinal.close();
        }

        return bytes;
    }

    private byte[] obtenerBytesTraZip() throws IOException {
        // File file = new File("c:/Users/FOLF-LMST/Documents/datytra/PRDO.tra");
        // File file = new File("/PRDO.tra");
        byte[] buf = new byte[1024];
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(archivo); ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                //System.out.println("read " + readNum + " bytes,");
            }
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return bytes;
    }

    private byte[] obtenerBytesDatZip() throws IOException {
        // File file = new File("c:/Users/FOLF-LMST/Documents/datytra/PRDO.dat");
        // File file = new File("/PRDO.dat");
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(archivo); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); // no doubt here is 0
                                           // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }

            bytes = bos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return bytes;
    }

    private byte[] obtenerBytesTraContZip(String producto) throws IOException {
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(archivo); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); // no doubt here is 0
                                           // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return bytes;
    }

    private byte[] obtenerBytesDatContZip(String producto) throws IOException {
        //File file = new File("c:/Users/FOLF-LMST/Documents/datytra/"+producto+".dat");
        //File file = new File("/PRDO.dat");
        byte[] buf = new byte[1024];
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(archivo); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); // no doubt here is 0
                                           // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                                           //System.out.println("read " + readNum + " bytes,");
            }
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
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
        System.out.println("Lista Encabezado:::" + listaDetallesAnexo.size());

        for (SIIFEncabezadoDTO detalle : listaDetallesAnexo) {
            Row filaDetalle = hoja.createRow(i);
            Cell celdaIdNomina = filaDetalle.createCell(COLUMNA_ID_NOMINA);

            celdaIdNomina.setCellValue(detalle.getIdNomina() == null ? 0 : detalle.getIdNomina());

            Cell celdaIdPoder = filaDetalle.createCell(COLUMNA_ID_PODER);
            celdaIdPoder.setCellValue(detalle.getIdPoder().toString());

            Cell celdaIdTipoNomina = filaDetalle.createCell(COLUMNA_ID_TIPO_NOMINA);
            celdaIdTipoNomina.setCellValue(detalle.getIdTipoNomina());

            //Verificar FechaFinQuincena
            String fechaString = detalle.getFechaFinQuincena() != null ? FechaUtil.formatearFecha("dd/MM/yyyy", detalle.getFechaFinQuincena()) : "01/01/2016";

            Cell celdaFechaFinQuincena = filaDetalle.createCell(COLUMNA_FECHA_FIN_QUINCENA);
            celdaFechaFinQuincena.setCellValue(fechaString);
            //celdaFechaFinQuincena.setCellValue(detalle.getFechaFinQuincena());

            Cell celdaIdTipoEmisionNomina = filaDetalle.createCell(COLUMNA_ID_TIPO_EMISION_NOMINA);
            celdaIdTipoEmisionNomina.setCellValue(detalle.getIdTipoEmisionNomina());

            Cell celdaIdCuentaBancaria = filaDetalle.createCell(COLUMNA_CLAVE_CUENTA_BANCARIA);
            celdaIdCuentaBancaria.setCellValue(detalle.getClaveCuentaBancaria());

            Cell celdaPercepciones = filaDetalle.createCell(COLUMNA_PERCEPCIONES);
            if (detalle.getPercepciones() != null) {
                celdaPercepciones.setCellValue(detalle.getPercepciones().doubleValue());
            } else {
                celdaPercepciones.setCellValue(0.0);
            }

            Cell celdaDeducciones = filaDetalle.createCell(COLUMNA_DEDDUCCIONES);
            if (detalle.getDeducciones() != null) {
                celdaDeducciones.setCellValue(detalle.getDeducciones().doubleValue());
            } else {
                celdaDeducciones.setCellValue(0.0);
            }

            Cell celdaNeto = filaDetalle.createCell(COLUMNA_NETO);
            if (detalle.getDeducciones() != null) {
                celdaNeto.setCellValue(detalle.getNeto().doubleValue());
            } else {
                celdaNeto.setCellValue(0.0);
            }

            Cell celdaIdEstadoNomina = filaDetalle.createCell(COLUMNA_ID_ESTADO_NOMINA);
            celdaIdEstadoNomina.setCellValue(detalle.getIdEstadoNomina().toString());

            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

    private void llenarDetallesFinal(List<SIIFEncabezadoDTO> listaDetallesAnexo) {
        int i = FILA_INICIO_DETALLE;
        System.out.println("Lista Encabezado:::" + listaDetallesAnexo.size());

        for (SIIFEncabezadoDTO detalle : listaDetallesAnexo) {
            Row filaDetalle = hoja.createRow(i);
            Cell celdaIdNomina = filaDetalle.createCell(COLUMNA_ID_NOMINA);

            celdaIdNomina.setCellValue(detalle.getIdNomina() == null ? 0 : detalle.getIdNomina());

            Cell celdaIdPoder = filaDetalle.createCell(COLUMNA_ID_PODER);
            celdaIdPoder.setCellValue(detalle.getIdPoder().toString());

            Cell celdaIdTipoNomina = filaDetalle.createCell(COLUMNA_ID_TIPO_NOMINA);
            celdaIdTipoNomina.setCellValue(detalle.getIdTipoNomina());

            //Verificar FechaFinQuincena
            Date fecha = detalle.getFechaFinQuincena() != null ? detalle.getFechaFinQuincena() : FechaUtil.getFecha("2016-07-31", "YYYY-MM-dd");
            System.out.println("Fecha Final :: " + fecha);

            Cell celdaFechaFinQuincena = filaDetalle.createCell(COLUMNA_FECHA_FIN_QUINCENA);
            celdaFechaFinQuincena.setCellValue(fecha);
            //celdaFechaFinQuincena.setCellValue(detalle.getFechaFinQuincena());

            Cell celdaIdTipoEmisionNomina = filaDetalle.createCell(COLUMNA_ID_TIPO_EMISION_NOMINA);
            celdaIdTipoEmisionNomina.setCellValue(detalle.getIdTipoEmisionNomina());

            Cell celdaIdCuentaBancaria = filaDetalle.createCell(COLUMNA_CLAVE_CUENTA_BANCARIA);
            celdaIdCuentaBancaria.setCellValue(detalle.getClaveCuentaBancaria());

            Cell celdaPercepciones = filaDetalle.createCell(COLUMNA_PERCEPCIONES);
            celdaPercepciones.setCellValue(detalle.getPercepciones().doubleValue());

            Cell celdaDeducciones = filaDetalle.createCell(COLUMNA_DEDDUCCIONES);
            celdaDeducciones.setCellValue(detalle.getDeducciones().doubleValue());

            Cell celdaNeto = filaDetalle.createCell(COLUMNA_NETO);
            celdaNeto.setCellValue(detalle.getNeto().doubleValue());

            Cell celdaIdEstadoNomina = filaDetalle.createCell(COLUMNA_ID_ESTADO_NOMINA);
            celdaIdEstadoNomina.setCellValue(detalle.getIdEstadoNomina().toString());

            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

    private void llenarDetallesSeguroPopular(List<EstructuraSeguroPopularDTO> listaDetallesAnexo) {
        int i = FILA_INICIO_DETALLE;
        System.out.println("Lista Seguro Popular:::" + listaDetallesAnexo.size());

        for (EstructuraSeguroPopularDTO detalle : listaDetallesAnexo) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaNumero = filaDetalle.createCell(COLUMNA_NUMERO);
            celdaNumero.setCellValue(detalle.getNumero().toString());

            Cell celdaMes = filaDetalle.createCell(COLUMNA_MES);
            celdaMes.setCellValue(detalle.getMes());

            Cell celdaEstado = filaDetalle.createCell(COLUMNA_ESTADO);
            celdaEstado.setCellValue(detalle.getEstado());

            Cell celdaCentro = filaDetalle.createCell(COLUMNA_CENTRO);
            celdaCentro.setCellValue(detalle.getCentro());

            Cell celdaClues = filaDetalle.createCell(COLUMNA_CLUES);
            celdaClues.setCellValue(detalle.getClues());

            Cell celdaUnidad = filaDetalle.createCell(COLUMNA_UNIDAD);
            celdaUnidad.setCellValue(detalle.getUnidad());

            Cell celdaPuesto = filaDetalle.createCell(COLUMNA_ADSCRIPCION);
            celdaPuesto.setCellValue(detalle.getAdscripcion());

            Cell celdaClave = filaDetalle.createCell(COLUMNA_CLAVE);
            celdaClave.setCellValue(detalle.getClave());

            Cell celdaServicio = filaDetalle.createCell(COLUMNA_SERVICIO);
            celdaServicio.setCellValue(detalle.getServicio());

            Cell celdaRama = filaDetalle.createCell(COLUMNA_RAMA);
            celdaRama.setCellValue(detalle.getRama());

            Cell celdaNombre = filaDetalle.createCell(COLUMNA_NOMBRE);
            celdaNombre.setCellValue(detalle.getNombre());

            Cell celdaRfc = filaDetalle.createCell(COLUMNA_RFC);
            celdaRfc.setCellValue(detalle.getRfc());

            Cell celdaTurno = filaDetalle.createCell(COLUMNA_TURNO);
            if (detalle.getTurno() != null) {
                celdaTurno.setCellValue(detalle.getTurno());
            } else {
                celdaTurno.setCellValue(" ");
            }

            Cell celdaFecha = filaDetalle.createCell(COLUMNA_FECHA);
            celdaFecha.setCellValue(detalle.getFingreso());

            Cell celdaSueldo = filaDetalle.createCell(COLUMNA_SUELDO);
            if (detalle.getSueldoBase() != null) {
                celdaSueldo.setCellValue(detalle.getSueldoBase().toString());
            } else {
                celdaSueldo.setCellValue("0.0");
            }

            Cell celdaPercepcion = filaDetalle.createCell(COLUMNA_PERCEPCIONES_NETAS);
            if (detalle.getPercepcion() != null) {
                celdaPercepcion.setCellValue(detalle.getPercepcion().toString());
            } else {
                celdaPercepcion.setCellValue("0.0");
            }

            Cell celdaDeduccion = filaDetalle.createCell(COLUMNA_DEDUCCIONES_NETAS);
            if (detalle.getDeduccion() != null) {
                celdaDeduccion.setCellValue(detalle.getDeduccion().toString());
            } else {
                celdaDeduccion.setCellValue("0.0");
            }

            Cell celdaNetas = filaDetalle.createCell(COLUMNA_NETAS);
            if (detalle.getNeto() != null) {
                celdaNetas.setCellValue(detalle.getNeto().toString());
            } else {
                celdaNetas.setCellValue("0.0");
            }

            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

    private void llenarDetallesTra(List<EstructuraContratosTrailersDTO> listaDetallesAnexo) {
        //int i = FILA_INICIO_DETALLE;
        System.out.println("Lista Tra:::" + listaDetallesAnexo.size());
        String fileName = "c:\\Users\\FOLF-LMST\\Documents\\datytra\\PRDO.tra"; //location of generated report
        //String fileName = "\\PRDO.tra"; //location of generated report

        try {
            writer = new FileWriter(fileName);

            for (EstructuraContratosTrailersDTO detalle : listaDetallesAnexo) {

                writer.append(detalle.getRfc());
                writer.append('|');
                writer.append(detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.gettConcep().toString());
                writer.append('|');
                writer.append(detalle.getConcep());
                writer.append('|');
                writer.append(detalle.getImporte().toString());
                writer.append('|');
                writer.append(detalle.getAnio());
                writer.append('|');
                writer.append(detalle.getQna());
                writer.append('|');
                writer.append(detalle.getPtaAnt());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getTotPagos());
                writer.append('|');
                writer.append(detalle.getPagoEfec());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumControl().toString());
                writer.append('\n');
            }
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void llenarDetallesDat(List<EstructuraContratosDatDTO> listaDetallesAnexo) {
        //int i = FILA_INICIO_DETALLE;
        System.out.println("Lista Dat:::" + listaDetallesAnexo.size());
        String fileName = "c:\\Users\\FOLF-LMST\\Documents\\datytra\\PRDO.dat"; //location of generated report        
        //String fileName = "\\PRDO.dat"; //location of generated report

        try {
            writer = new FileWriter(fileName);

            for (EstructuraContratosDatDTO detalle : listaDetallesAnexo) {
                writer.append(detalle.getRfc());
                writer.append('|');
                writer.append(detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getCurp());
                writer.append('|');
                writer.append(detalle.getNombre());
                writer.append('|');
                writer.append(detalle.getSar());
                writer.append('|');
                writer.append(detalle.getBancoA());
                writer.append('|');
                writer.append(detalle.getBancoN());
                writer.append('|');
                writer.append(detalle.getNumCta());
                writer.append('|');
                writer.append(detalle.getClabe());
                writer.append('|');
                writer.append(detalle.getFuncion());
                writer.append('|');
                writer.append(detalle.getCp());
                writer.append('|');
                writer.append(detalle.getCalle());
                writer.append('|');
                writer.append(detalle.getPuesto());
                writer.append('|');
                writer.append(detalle.getDesPuesto());
                writer.append('|');
                writer.append(detalle.getUr());
                writer.append('|');
                writer.append(detalle.getGf());
                writer.append('|');
                writer.append(detalle.getFn());
                writer.append('|');
                writer.append(detalle.getSf());
                writer.append('|');
                writer.append(detalle.getPg());
                writer.append('|');
                writer.append(detalle.getAi());
                writer.append('|');
                writer.append(detalle.getPp());
                writer.append('|');
                writer.append(detalle.getPartida());
                writer.append('|');
                writer.append(detalle.getPuestoTab());
                writer.append('|');
                writer.append(detalle.getNumPto());
                writer.append('|');
                writer.append(detalle.getEdo());
                writer.append('|');
                writer.append(detalle.getMpio());
                writer.append('|');
                writer.append(detalle.getCr());
                writer.append('|');
                writer.append(detalle.getCi());
                writer.append('|');
                writer.append(detalle.getPagaD());
                writer.append('|');
                writer.append(detalle.getFinanciamiento());
                writer.append('|');
                writer.append(detalle.getTabPto());
                writer.append('|');
                writer.append(detalle.getNivel());
                writer.append('|');
                writer.append(detalle.getRango());
                writer.append('|');
                writer.append(detalle.getIndMando());
                writer.append('|');
                writer.append(detalle.getHoras());
                writer.append('|');
                writer.append(detalle.getPorcent());
                writer.append('|');
                writer.append(detalle.getTipoTrab());
                writer.append('|');
                writer.append(detalle.getNivelPto());
                writer.append('|');
                writer.append(detalle.getIndEmp());
                writer.append('|');
                writer.append(detalle.getFecInicial());
                writer.append('|');
                writer.append(detalle.getFecFinal());
                writer.append('|');
                writer.append(detalle.getFecIngreso());
                writer.append('|');
                writer.append(detalle.getTipoMov());
                writer.append('|');
                writer.append(detalle.getfPago());
                writer.append('|');
                writer.append(detalle.getpPagoI());
                writer.append('|');
                writer.append(detalle.getpPagoF());
                writer.append('|');
                writer.append(detalle.getpQnaI());
                writer.append('|');
                writer.append(detalle.getpQnaF());
                writer.append('|');
                writer.append(detalle.getQnaReal());
                writer.append('|');
                writer.append(detalle.getAnioReal());
                writer.append('|');
                writer.append(detalle.getTipoPago().toString());
                writer.append('|');
                writer.append(detalle.getInstruA());
                writer.append('|');
                writer.append(detalle.getInstruN());
                writer.append('|');
                writer.append(detalle.getPer().toPlainString());
                writer.append('|');
                writer.append(detalle.getDed() == null ? "0" : detalle.getDed().toString());
                writer.append('|');
                writer.append(detalle.getNeto().toPlainString());
                writer.append('|');
                writer.append(detalle.getNoTrail().toString());
                writer.append('|');
                writer.append(detalle.getDiasLab().toString());
                writer.append('|');
                writer.append(detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumCtrol().toString());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getDigVer());
                writer.append('|');
                writer.append(detalle.getJornada());
                writer.append('|');
                writer.append(detalle.getDiasP());
                writer.append('|');
                writer.append(detalle.getCicloF());
                writer.append('|');
                writer.append(detalle.getNumAport());
                writer.append('|');
                writer.append(detalle.getAcumF().toString());
                writer.append('|');
                writer.append(detalle.getFaltas().toString());
                writer.append('|');
                writer.append(detalle.getClues());
                writer.append('|');
                writer.append(detalle.getPorPen01().toString());
                writer.append('|');
                writer.append(detalle.getPorPen02().toString());
                writer.append('|');
                writer.append(detalle.getPorPen03().toString());
                writer.append('|');
                writer.append(detalle.getPorPen04().toString());
                writer.append('|');
                writer.append(detalle.getPorPen05().toString());
                writer.append('|');
                writer.append(detalle.getIssste().toString());
                writer.append('|');
                writer.append(detalle.getTipoUni().toString());
                writer.append('|');
                writer.append(detalle.getCrespDes());
                writer.append('|');

                writer.append('\n');

            }
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void llenarDetallesTraCont(List<EstructuraContratosTrailersDTO> listaDetallesAnexo, String producto) {
        try {
            Path path = Files.createTempFile(producto, ".tra");
            archivo = path.toFile();
            writer = new FileWriter(archivo);

            for (EstructuraContratosTrailersDTO detalle : listaDetallesAnexo) {

                writer.append(detalle.getRfc());
                writer.append('|');
                writer.append(detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.gettConcep().toString());
                writer.append('|');
                writer.append(detalle.getConcep());
                writer.append('|');
                writer.append(detalle.getImporte().toString());
                writer.append('|');
                writer.append(detalle.getAnio());
                writer.append('|');
                writer.append(detalle.getQna());
                writer.append('|');
                writer.append(detalle.getPtaAnt());
                writer.append('|');
                writer.append(detalle.getTotPagos());
                writer.append('|');
                writer.append(detalle.getPagoEfec());
                writer.append('|');
                writer.append(detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumControl().toString());
                writer.append('\n');

            }
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void llenarDetallesDatCont(List<EstructuraContratosDatDTO> listaDetallesAnexo, String producto) {
        try {
            Path path = Files.createTempFile(producto, ".dat");
            archivo = path.toFile();
            writer = new FileWriter(archivo);

            for (EstructuraContratosDatDTO detalle : listaDetallesAnexo) {
                //

                writer.append(detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getRfc());
                writer.append('|');
                writer.append(detalle.getCurp());
                writer.append('|');
                writer.append(detalle.getNombre());
                writer.append('|');
                writer.append(detalle.getSar());
                writer.append('|');
                writer.append(detalle.getBancoA());
                writer.append('|');
                writer.append(detalle.getBancoN());
                writer.append('|');
                writer.append(detalle.getNumCta());
                writer.append('|');
                writer.append(detalle.getClabe());
                writer.append('|');
                writer.append(detalle.getFuncion());
                writer.append('|');
                writer.append(detalle.getCp());
                writer.append('|');
                writer.append(detalle.getCalle());
                writer.append('|');
                writer.append(detalle.getPuesto());
                writer.append('|');
                writer.append(detalle.getDesPuesto());
                writer.append('|');
                writer.append(detalle.getUr());
                writer.append('|');
                writer.append(detalle.getGf());
                writer.append('|');
                writer.append(detalle.getFn());
                writer.append('|');
                writer.append(detalle.getSf());
                writer.append('|');
                writer.append(detalle.getPg());
                writer.append('|');
                writer.append(detalle.getAi());
                writer.append('|');
                writer.append(detalle.getPp());
                writer.append('|');
                writer.append(detalle.getPartida());
                writer.append('|');
                writer.append(detalle.getPuestoTab());
                writer.append('|');
                writer.append(detalle.getNumPto());
                writer.append('|');
                writer.append(detalle.getEdo());
                writer.append('|');
                writer.append(detalle.getMpio());
                writer.append('|');
                writer.append(detalle.getCr());
                writer.append('|');
                writer.append(detalle.getCi());
                writer.append('|');
                writer.append(detalle.getPagaD());
                writer.append('|');
                writer.append(detalle.getFinanciamiento());
                writer.append('|');
                writer.append(detalle.getTabPto());
                writer.append('|');
                writer.append(detalle.getNivel());
                writer.append('|');
                writer.append(detalle.getRango());
                writer.append('|');
                writer.append(detalle.getIndMando());
                writer.append('|');
                writer.append(detalle.getHoras());
                writer.append('|');
                writer.append(detalle.getPorcent());
                writer.append('|');
                writer.append(detalle.getTipoTrab());
                writer.append('|');
                writer.append(detalle.getNivelPto());
                writer.append('|');
                writer.append(detalle.getIndEmp());
                writer.append('|');
                writer.append(detalle.getFecInicial());
                writer.append('|');
                writer.append(detalle.getFecFinal());
                writer.append('|');
                writer.append(detalle.getFecIngreso());
                writer.append('|');
                writer.append(detalle.getTipoMov());
                writer.append('|');
                writer.append(detalle.getfPago());
                writer.append('|');
                writer.append(detalle.getpPagoI());
                writer.append('|');
                writer.append(detalle.getpPagoF());
                writer.append('|');
                writer.append(detalle.getpQnaI());
                writer.append('|');
                writer.append(detalle.getpQnaF());
                writer.append('|');
                writer.append(detalle.getQnaReal());
                writer.append('|');
                writer.append(detalle.getAnioReal());
                writer.append('|');
                writer.append(detalle.getTipoPago().toString());
                writer.append('|');
                writer.append(detalle.getInstruA());
                writer.append('|');
                writer.append(detalle.getInstruN());
                writer.append('|');
                writer.append(detalle.getPer() == null ? "0" : detalle.getPer().toPlainString());
                writer.append('|');
                writer.append(detalle.getDed() == null ? "0" : detalle.getDed().toString());
                writer.append('|');
                writer.append(detalle.getNeto() == null ? "0" : detalle.getNeto().toPlainString());
                writer.append('|');
                writer.append(detalle.getNoTrail().toString());
                writer.append('|');
                writer.append(detalle.getDiasLab().toString());
                writer.append('|');
                writer.append(detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumCtrol().toString());
                writer.append('|');
                writer.append(detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getDigVer());
                writer.append('|');
                writer.append(detalle.getJornada());
                writer.append('|');//modificado
                writer.append(detalle.getDiasP());
                writer.append('|');
                writer.append(detalle.getCicloF());
                writer.append('|');
                writer.append(detalle.getNumAport());
                writer.append('|');
                //writer.append(detalle.getAcumF().toString());writer.append('|');
                writer.append(' ');
                writer.append('|');
                //writer.append(detalle.getFaltas().toString());writer.append('|');
                writer.append(' ');
                writer.append('|');
                writer.append(detalle.getClues());
                writer.append('|');
                writer.append(detalle.getPorPen01().toString());
                writer.append('|');
                writer.append(detalle.getPorPen02().toString());
                writer.append('|');
                writer.append(detalle.getPorPen03().toString());
                writer.append('|');
                writer.append(detalle.getPorPen04().toString());
                writer.append('|');
                writer.append(detalle.getPorPen05().toString());
                writer.append('|');
                writer.append(detalle.getIssste().toString());
                writer.append('|');
                writer.append(detalle.getTipoUni().toString());
                writer.append('|');
                writer.append(detalle.getCrespDes());
                writer.append('|');

                writer.append('\n');

            }
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
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
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla");
        }
    }

    public byte[] generarFinal(List<SIIFEncabezadoDTO> listaDetalles) {
        try {
            cargarPlantillaFinal();
            llenarDetallesFinal(listaDetalles);
            return obtenerBytes();
        } catch (IOException e) {
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla");
        }

    }

    public byte[] generarDat(List<EstructuraContratosDatDTO> listaDetalles) {
        try {

            llenarDetallesDat(listaDetalles);
            return obtenerBytesDatZip();
        } catch (IOException e) {
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla");
        }
    }

    public byte[] generarTraCont(List<EstructuraContratosTrailersDTO> listaDetalles, String producto) {
        try {

            llenarDetallesTraCont(listaDetalles, producto);
            return obtenerBytesTraContZip(producto);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla: " + e.getMessage());

        }
    }

    public byte[] generarDatCont(List<EstructuraContratosDatDTO> listaDetalles, String producto) {
        try {

            llenarDetallesDatCont(listaDetalles, producto);
            return obtenerBytesDatContZip(producto);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla");
        }
    }

    public byte[] generarSeguroPopular(List<EstructuraSeguroPopularDTO> listaDetalles) {
        try {
            cargarPlantillaSegPop();
            llenarDetallesSeguroPopular(listaDetalles);
            return obtenerBytes();
        } catch (IOException e) {
            throw new ReglaNegocioException("Ocurrio un error al leer la platilla");
        }
    }

}
