/*
 * SIIFDatService.java
 * Creado el 06/Dec/2016 10:29:33 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.siif.EstructuraNominaDatDTO;
import org.jboss.logging.Logger;

/**
 *
 * @author Zaid Perez
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFDatService {

    private static final Logger LOGGER = Logger.getLogger(SIIFDatService.class.getName());
    private File archivoDat;

    protected byte[] generarDat(List<EstructuraNominaDatDTO> listaDetalles) {
        try {
            Path path = Files.createTempFile("prdo", ".dat");
            archivoDat = path.toFile();
            llenarDetallesDat(listaDetalles);
            return obtenerBytesDat();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private void llenarDetallesDat(List<EstructuraNominaDatDTO> listaDetallesAnexo) {
        try(FileWriter writer = new FileWriter(archivoDat)) {
            for (EstructuraNominaDatDTO detalle : listaDetallesAnexo) {
                writer.append(detalle.getRfc() == null ? "" : detalle.getRfc());
                writer.append('|');
                writer.append(detalle.getNumEmp() == null ? "" : detalle.getNumEmp());
                writer.append('|');
                writer.append(detalle.getCurp() == null ? "": detalle.getCurp());
                writer.append('|');
                writer.append(detalle.getNombre() == null ? "" : detalle.getNombre());
                writer.append('|');
                writer.append(detalle.getSar() == null ? "" : detalle.getSar());
                writer.append('|');
                writer.append(detalle.getBancoA() == null ? "" : detalle.getBancoA());
                writer.append('|');
                writer.append(detalle.getBancoN() == null ? "" : detalle.getBancoN());
                writer.append('|');
                writer.append(detalle.getNumCta() == null ? "" : detalle.getNumCta());
                writer.append('|');
                writer.append(detalle.getClabe() == null ? "" : detalle.getClabe());
                writer.append('|');
                writer.append(detalle.getFuncion() == null ? "" : detalle.getFuncion());
                writer.append('|');
                writer.append(detalle.getCp() == null ? "" : detalle.getCp());
                writer.append('|');
                writer.append(detalle.getCalle() == null ? "" : detalle.getCalle());
                writer.append('|');
                writer.append(detalle.getPuesto() == null ? "" : detalle.getPuesto());
                writer.append('|');
//                writer.append(detalle.getCrespDes() == null ? "" : detalle.getDesPuesto());
//                writer.append('|');
                writer.append(detalle.getUr() == null ? "" : detalle.getUr());
                writer.append('|');
                writer.append(detalle.getGf() == null ? "" : detalle.getGf());
                writer.append('|');
                writer.append(detalle.getFn() == null ? "" : detalle.getFn());
                writer.append('|');
                writer.append(detalle.getSf() == null ? "" : detalle.getSf());
                writer.append('|');
                writer.append(detalle.getPg() == null ? "" : detalle.getPg());
                writer.append('|');
                writer.append(detalle.getAi() == null ? "" :detalle.getAi());
                writer.append('|');
                writer.append(detalle.getPp() == null ? "" : detalle.getPp());
                writer.append('|');
                writer.append(detalle.getPartida() == null ? "" : detalle.getPartida());
                writer.append('|');
//                writer.append(detalle.getPuestoTab() == null ? "" : detalle.getPuestoTab());
//                writer.append('|');
                writer.append(detalle.getNumPto() == null ? "" : detalle.getNumPto());
                writer.append('|');
                writer.append(detalle.getEdo() == null ? "" : detalle.getEdo());
                writer.append('|');
                writer.append(detalle.getMpio() == null ? "" : detalle.getMpio());
                writer.append('|');
                writer.append(detalle.getCr() == null ? "" : detalle.getCr());
                writer.append('|');
                writer.append(detalle.getCi() == null ? "" : detalle.getCi());
                writer.append('|');
                writer.append(detalle.getPagaD() == null ? "" : detalle.getPagaD());
                writer.append('|');
                writer.append(detalle.getFinanciamiento() == null ? "" : detalle.getFinanciamiento());
                writer.append('|');
                writer.append(detalle.getTabPto() == null ? "" : detalle.getTabPto());
                writer.append('|');
                writer.append(detalle.getNivel() == null ? "" : detalle.getNivel());
                writer.append('|');
                writer.append(detalle.getRango() == null ? "" : detalle.getRango());
                writer.append('|');
                writer.append(detalle.getIndMando() == null ? "" : detalle.getIndMando());
                writer.append('|');
                writer.append(detalle.getHora() == null ? "" : detalle.getHora());
                writer.append('|');
                writer.append(detalle.getPorcent() == null ? "" : detalle.getPorcent());
                writer.append('|');
                writer.append(detalle.getTipoTrab() == null ? "" : detalle.getTipoTrab());
                writer.append('|');
                writer.append(detalle.getNivelPto() == null ? "" : detalle.getNivelPto());
                writer.append('|');
                writer.append(detalle.getIndEmp() == null ? "" : detalle.getIndEmp());
                writer.append('|');
                writer.append(detalle.getfIgf() == null ? "" : detalle.getfIgf());
                writer.append('|');
                writer.append(detalle.getfIssa()== null ? "" : detalle.getfIssa());
                writer.append('|');
                writer.append(detalle.getfReing() == null ? "" : detalle.getfReing());
                writer.append('|');
                writer.append(detalle.getTipoMov() == null ? "" : detalle.getTipoMov());
                writer.append('|');
                writer.append(detalle.getfPago() == null ? "" : detalle.getfPago());
                writer.append('|');
                writer.append(detalle.getpPagoI() == null ? "" : detalle.getpPagoI());
                writer.append('|');
                writer.append(detalle.getpPagoF() == null ? "" : detalle.getpPagoF());
                writer.append('|');
                writer.append(detalle.getpQnaI() == null ? "" : detalle.getpQnaI());
                writer.append('|');
                writer.append(detalle.getpQnaF() == null ? "" : detalle.getpQnaF());
                writer.append('|');
                writer.append(detalle.getQnaReal() == null ? "" : detalle.getQnaReal());
                writer.append('|');
                writer.append(detalle.getAnioReal() == null ? "" : detalle.getAnioReal());
                writer.append('|');
                writer.append(detalle.getTipoPago() == null ? "" : detalle.getTipoPago().toString());
                writer.append('|');
                writer.append(detalle.getInstruA() == null ? "" : detalle.getInstruA());
                writer.append('|');
                writer.append(detalle.getInstruN() == null ? "" : detalle.getInstruN());
                writer.append('|');
                writer.append(detalle.getPer() == null ? "0.00" : detalle.getPer().toPlainString());
                writer.append('|');
                writer.append(detalle.getDed() == null ? "0.00" : detalle.getDed().toString());
                writer.append('|');
                writer.append(detalle.getNeto() == null ? "0.00" : detalle.getNeto().toPlainString());
                writer.append('|');
                writer.append(detalle.getNoTrail() == null ? "0" : detalle.getNoTrail().toString());
                writer.append('|');
                writer.append(detalle.getDiasLab() == null ? "0" : detalle.getDiasLab().toString());
                writer.append('|');
                writer.append(detalle.getNomProd() == null ? "" : detalle.getNomProd());
                writer.append('|');
                writer.append(detalle.getNumCtrol() == null ? "0" : detalle.getNumCtrol().toString());
                writer.append('|');
                writer.append(detalle.getNumCheq() == null ? "" : detalle.getNumCheq());
                writer.append('|');
                writer.append(detalle.getDigVer() == null ? "" : detalle.getDigVer());
                writer.append('|');
                writer.append(detalle.getJornada() == null ? "" : detalle.getJornada().toString());
                writer.append('|');
                writer.append(detalle.getDiasP() == null ? "" : detalle.getDiasP());
                writer.append('|');
                writer.append(detalle.getCicloF() == null ? "" : detalle.getCicloF());
                writer.append('|');
                writer.append(detalle.getNumAport() == null ? "" : detalle.getNumAport());
                writer.append('|');
                writer.append(detalle.getAcumF() == null ? "0.00" : detalle.getAcumF().toString());
                writer.append('|');
                writer.append(detalle.getFaltas() == null ? "0" : detalle.getFaltas().toString());
                writer.append('|');
                writer.append(detalle.getClues() == null ? "" : detalle.getClues());
                writer.append('|');
                writer.append(detalle.getPorPen01() == null ? "0.00" : detalle.getPorPen01().toString());
                writer.append('|');
                writer.append(detalle.getPorPen02() == null ? "0.00" : detalle.getPorPen02().toString());
                writer.append('|');
                writer.append(detalle.getPorPen03() == null ? "0.00" : detalle.getPorPen03().toString());
                writer.append('|');
                writer.append(detalle.getPorPen04() == null ? "0.00" : detalle.getPorPen04().toString());
                writer.append('|');
                writer.append(detalle.getPorPen05() == null ? "0.00" : detalle.getPorPen05().toString());
                writer.append('|');
                writer.append(detalle.getIssste() == null ? "0" : detalle.getIssste().toString());
                writer.append('|');
                writer.append(detalle.getTipoUni() == null ? "0" : detalle.getTipoUni().toString());
                writer.append('|');
                writer.append(detalle.getCrespDes() == null ? "" : detalle.getCrespDes());
                writer.append('|');
                writer.append('\n');
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private byte[] obtenerBytesDat() throws IOException {
        try (FileInputStream fis = new FileInputStream(archivoDat);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }

            byte[] bytes = bos.toByteArray();
            archivoDat.delete();
            return bytes;
        }
    }

}
