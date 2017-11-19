/*
 *
 */

package mx.gob.saludtlax.rh.siif.serica;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
public class SericaService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8480935879571983679L;

    private static final Logger LOGGER = Logger.getLogger(SericaService.class.getName());
    private File archivoTxt;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    /***
     * Trae la lista de detalles de la tabla serica
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<DetalleSericaDTO> consultarDetalleSerica() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_serica_nomina()");

        query.setResultTransformer(Transformers.aliasToBean(DetalleSericaDTO.class));

        List<DetalleSericaDTO> listDetallesSerica = query.list();

        return listDetallesSerica;
    }

    @SuppressWarnings("unchecked")
    protected List<DetalleSericaDTO> consultarDetalleSericaEncabezados() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_serica_nomina_encabezado()");

        query.setResultTransformer(Transformers.aliasToBean(DetalleSericaDTO.class));

        List<DetalleSericaDTO> listDetallesSerica = query.list();

        return listDetallesSerica;
    }

    @SuppressWarnings("unchecked")
    protected List<SericaDTO> consultarDetalleSericaEncabezadosPeriodo(Integer periodo, Integer ejercicioFiscal) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_serica(:periodo, :ejercicio_fiscal)").setParameter("periodo", periodo).setParameter("ejercicio_fiscal",
                ejercicioFiscal);

        query.setResultTransformer(Transformers.aliasToBean(SericaDTO.class));

        List<SericaDTO> listDetallesSerica = query.list();

        return listDetallesSerica;
    }

    protected byte[] generarTxt(List<DetalleSericaDTO> listaEncabezados, List<DetalleSericaDTO> listaDetalles) {
        try {
            Path path = Files.createTempFile("NOM-01229001Q201622O1", ".txt");
            archivoTxt = path.toFile();
            llenarDetallesSerica(listaEncabezados, listaDetalles);
            return obtenerBytesTxt();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    protected byte[] generarDetailTxt(List<DetalleSericaDTO> listaEncabezados, List<SericaDTO> listaDetalles) {
        try {
            Path path = Files.createTempFile("NOM-01229001Q201622O1", ".txt");
            archivoTxt = path.toFile();
            llenarDetailSerica(listaEncabezados, listaDetalles);
            return obtenerBytesTxt();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private void llenarDetallesSerica(List<DetalleSericaDTO> listaDetallesEncabezados, List<DetalleSericaDTO> listaDetalles) {
        try (FileWriter writer = new FileWriter(archivoTxt)) {

            // Encabezados
            for (DetalleSericaDTO detalle : listaDetallesEncabezados) {
                writer.append(detalle.getCol1() == null ? "" : detalle.getCol1());
                writer.append('|');
                writer.append(detalle.getCol2() == null ? "" : detalle.getCol2());
                writer.append('|');
                writer.append(detalle.getCol3() == null ? "" : detalle.getCol3());
                writer.append('|');
                writer.append(detalle.getCol4() == null ? "" : detalle.getCol4());
                writer.append('|');
                writer.append(detalle.getCol5() == null ? "" : detalle.getCol5());
                writer.append('|');
                writer.append(detalle.getCol6() == null ? "" : detalle.getCol6());
                writer.append('|');
                writer.append(detalle.getCol7() == null ? "" : detalle.getCol7());
                writer.append('|');
                writer.append(detalle.getCol8() == null ? "" : detalle.getCol8());
                writer.append('|');
                writer.append(detalle.getCol9() == null ? "" : detalle.getCol9());
                writer.append('|');
                writer.append(detalle.getCol10() == null ? "" : detalle.getCol10());
                writer.append('|');
                writer.append(detalle.getCol11() == null ? "" : detalle.getCol11());
                writer.append('|');
                writer.append(detalle.getCol12() == null ? "" : detalle.getCol12());
                writer.append('|');
                writer.append(detalle.getCol13() == null ? "" : detalle.getCol13());
                writer.append('|');
                writer.append(detalle.getCol14() == null ? "" : detalle.getCol14());
                writer.append('|');
                writer.append(detalle.getCol15() == null ? "" : detalle.getCol15());
                writer.append('|');
                writer.append(detalle.getCol16() == null ? "" : detalle.getCol16());
                writer.append('|');
                writer.append(detalle.getCol17() == null ? "" : detalle.getCol17());
                writer.append('|');
                writer.append(detalle.getCol18() == null ? "" : detalle.getCol18());
                writer.append('|');
                writer.append(detalle.getCol19() == null ? "" : detalle.getCol19());
                writer.append('|');
                writer.append(detalle.getCol20() == null ? "" : detalle.getCol20());
                writer.append('|');
                writer.append(detalle.getCol21() == null ? "" : detalle.getCol21());
                writer.append('|');
                writer.append(detalle.getCol22() == null ? "" : detalle.getCol22());
                writer.append('|');
                writer.append(detalle.getCol23() == null ? "" : detalle.getCol23());
                writer.append('|');
                writer.append(detalle.getCol24() == null ? "" : detalle.getCol24());
                writer.append('|');
                writer.append(detalle.getCol25() == null ? "" : detalle.getCol25());
                writer.append('|');
                writer.append(detalle.getCol26() == null ? "" : detalle.getCol26());
                writer.append('|');
                writer.append(detalle.getCol27() == null ? "" : detalle.getCol27());
                writer.append('|');
                writer.append(detalle.getCol28() == null ? "" : detalle.getCol28());
                writer.append('|');
                writer.append(detalle.getCol29() == null ? "" : detalle.getCol29());
                writer.append('|');
                writer.append(detalle.getCol30() == null ? "" : detalle.getCol30());
                writer.append('|');

                writer.append('\n');
            }

            // Detalles
            for (DetalleSericaDTO detalle : listaDetalles) {
                writer.append(detalle.getCol1() == null ? "" : detalle.getCol1());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol2()) ? "0.0" : detalle.getCol2());
                writer.append('|');
                writer.append(detalle.getCol3() == null ? "" : detalle.getCol3());
                writer.append('|');
                writer.append(detalle.getCol4() == null ? "" : detalle.getCol4());
                writer.append('|');
                writer.append(detalle.getCol5() == null ? "" : detalle.getCol5());
                writer.append('|');
                writer.append(detalle.getCol6() == null ? "" : detalle.getCol6());
                writer.append('|');
                writer.append(detalle.getCol7() == null ? "" : detalle.getCol7());
                writer.append('|');
                writer.append(detalle.getCol8() == null ? "" : detalle.getCol8());
                writer.append('|');
                writer.append(detalle.getCol9() == null ? "" : detalle.getCol9());
                writer.append('|');
                writer.append(detalle.getCol10() == null ? "" : detalle.getCol10());
                writer.append('|');
                writer.append(detalle.getCol11() == null ? "" : detalle.getCol11());
                writer.append('|');
                writer.append(detalle.getCol12() == null ? "" : detalle.getCol12());
                writer.append('|');
                writer.append(detalle.getCol13() == null ? "" : detalle.getCol13());
                writer.append('|');
                writer.append(detalle.getCol14() == null ? "" : detalle.getCol14());
                writer.append('|');
                writer.append(detalle.getCol15() == null ? "" : detalle.getCol15());
                writer.append('|');
                writer.append(detalle.getCol16() == null ? "" : detalle.getCol16());
                writer.append('|');
                writer.append(detalle.getCol17() == null ? "" : detalle.getCol17());
                writer.append('|');
                writer.append(detalle.getCol18() == null ? "" : detalle.getCol18());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol19()) ? "0.0" : detalle.getCol19());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol20()) ? "0.0" : detalle.getCol20());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol21()) ? "0.0" : detalle.getCol21());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol22()) ? "0.0" : detalle.getCol22());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol23()) ? "0.0" : detalle.getCol23());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol24()) ? "0.0" : detalle.getCol24());
                writer.append('|');
                writer.append(detalle.getCol25() == null ? "" : detalle.getCol25());
                writer.append('|');
                writer.append(detalle.getCol26() == null ? "" : detalle.getCol26());
                writer.append('|');
                writer.append(detalle.getCol27() == null ? "" : detalle.getCol27());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol28()) ? "0.0" : detalle.getCol28());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol29()) ? "0.0" : detalle.getCol29());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol30()) ? "0.0" : detalle.getCol30());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol31()) ? "0.0" : detalle.getCol31());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol32()) ? "0.0" : detalle.getCol32());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol33()) ? "0.0" : detalle.getCol33());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol34()) ? "0.0" : detalle.getCol34());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol35()) ? "0.0" : detalle.getCol35());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol36()) ? "0.0" : detalle.getCol36());
                writer.append('|');
                writer.append(detalle.getCol37() == null ? "" : detalle.getCol37());
                writer.append('|');
                writer.append(detalle.getCol38() == null ? "" : detalle.getCol38());
                writer.append('|');
                writer.append(detalle.getCol39() == null ? "" : detalle.getCol39());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol40()) ? "0.0" : detalle.getCol40());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol41()) ? "0.0" : detalle.getCol41());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol42()) ? "0.0" : detalle.getCol42());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol43()) ? "0.0" : detalle.getCol43());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol44()) ? "0.0" : detalle.getCol44());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol45()) ? "0.0" : detalle.getCol45());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol46()) ? "0.0" : detalle.getCol46());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol47()) ? "0.0" : detalle.getCol47());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol48()) ? "0.0" : detalle.getCol48());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol49()) ? "0.0" : detalle.getCol49());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol50()) ? "0.0" : detalle.getCol50());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol51()) ? "0.0" : detalle.getCol51());
                writer.append('|');
                writer.append(detalle.getCol52() == null ? "" : detalle.getCol52());
                writer.append('|');
                writer.append(detalle.getCol53() == null ? "" : detalle.getCol53());
                writer.append('|');
                writer.append(detalle.getCol54() == null ? "" : detalle.getCol54());
                writer.append('|');
                writer.append(detalle.getCol55() == null ? "" : detalle.getCol55());
                writer.append('|');
                writer.append(detalle.getCol56() == null ? "" : detalle.getCol56());
                writer.append('|');
                writer.append(detalle.getCol57() == null ? "" : detalle.getCol57());
                writer.append('|');
                writer.append(detalle.getCol58() == null ? "" : detalle.getCol58());
                writer.append('|');
                writer.append(detalle.getCol59() == null ? "" : detalle.getCol59());
                writer.append('|');
                writer.append(detalle.getCol60() == null ? "" : detalle.getCol60());
                writer.append('|');
                writer.append(detalle.getCol61() == null ? "" : detalle.getCol61());
                writer.append('|');
                writer.append(detalle.getCol62() == null ? "" : detalle.getCol62());
                writer.append('|');
                writer.append(detalle.getCol63() == null ? "" : detalle.getCol63());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol64()) ? "0.0" : detalle.getCol64());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol65()) ? "0.0" : detalle.getCol65());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol66()) ? "0.0" : detalle.getCol66());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol67()) ? "0.0" : detalle.getCol67());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol68()) ? "0.0" : detalle.getCol68());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol69()) ? "0.0" : detalle.getCol69());
                writer.append('|');
                writer.append(detalle.getCol70() == null ? "" : detalle.getCol70());
                writer.append('|');
                writer.append(detalle.getCol71() == null ? "" : detalle.getCol71());
                writer.append('|');
                writer.append(detalle.getCol72() == null ? "" : detalle.getCol72());
                writer.append('|');
                writer.append(detalle.getCol73() == null ? "" : detalle.getCol73());
                writer.append('|');
                writer.append(detalle.getCol74() == null ? "" : detalle.getCol74());
                writer.append('|');
                writer.append(detalle.getCol75() == null ? "" : detalle.getCol75());
                writer.append('|');
                writer.append(detalle.getCol76() == null ? "" : detalle.getCol76());
                writer.append('|');
                writer.append(detalle.getCol77() == null ? "" : detalle.getCol77());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol78()) ? "0.0" : detalle.getCol78());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol79()) ? "0.0" : detalle.getCol79());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol80()) ? "0.0" : detalle.getCol80());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol81()) ? "0.0" : detalle.getCol81());
                writer.append('|');
                writer.append('\n');
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private void llenarDetailSerica(List<DetalleSericaDTO> listaDetallesEncabezados, List<SericaDTO> listaDetalles) {
        try (FileWriter writer = new FileWriter(archivoTxt)) {

            // Encabezados
            for (DetalleSericaDTO detalle : listaDetallesEncabezados) {
                writer.append(detalle.getCol1() == null ? "" : detalle.getCol1());
                writer.append('|');
                writer.append(detalle.getCol2() == null ? "" : detalle.getCol2());
                writer.append('|');
                writer.append(detalle.getCol3() == null ? "" : detalle.getCol3());
                writer.append('|');
                writer.append(detalle.getCol4() == null ? "" : detalle.getCol4());
                writer.append('|');
                writer.append(detalle.getCol5() == null ? "" : detalle.getCol5());
                writer.append('|');
                writer.append(detalle.getCol6() == null ? "" : detalle.getCol6());
                writer.append('|');
                writer.append(detalle.getCol7() == null ? "" : detalle.getCol7());
                writer.append('|');
                writer.append(detalle.getCol8() == null ? "" : detalle.getCol8());
                writer.append('|');
                writer.append(detalle.getCol9() == null ? "" : detalle.getCol9());
                writer.append('|');
                writer.append(detalle.getCol10() == null ? "" : detalle.getCol10());
                writer.append('|');
                writer.append(detalle.getCol11() == null ? "" : detalle.getCol11());
                writer.append('|');
                writer.append(detalle.getCol12() == null ? "" : detalle.getCol12());
                writer.append('|');
                writer.append(detalle.getCol13() == null ? "" : detalle.getCol13());
                writer.append('|');
                writer.append(detalle.getCol14() == null ? "" : detalle.getCol14());
                writer.append('|');
                writer.append(detalle.getCol15() == null ? "" : detalle.getCol15());
                writer.append('|');
                writer.append(detalle.getCol16() == null ? "" : detalle.getCol16());
                writer.append('|');
                writer.append(detalle.getCol17() == null ? "" : detalle.getCol17());
                writer.append('|');
                writer.append(detalle.getCol18() == null ? "" : detalle.getCol18());
                writer.append('|');
                writer.append(detalle.getCol19() == null ? "" : detalle.getCol19());
                writer.append('|');
                writer.append(detalle.getCol20() == null ? "" : detalle.getCol20());
                writer.append('|');
                writer.append(detalle.getCol21() == null ? "" : detalle.getCol21());
                writer.append('|');
                writer.append(detalle.getCol22() == null ? "" : detalle.getCol22());
                writer.append('|');
                writer.append(detalle.getCol23() == null ? "" : detalle.getCol23());
                writer.append('|');
                writer.append(detalle.getCol24() == null ? "" : detalle.getCol24());
                writer.append('|');
                writer.append(detalle.getCol25() == null ? "" : detalle.getCol25());
                writer.append('|');
                writer.append(detalle.getCol26() == null ? "" : detalle.getCol26());
                writer.append('|');
                writer.append(detalle.getCol27() == null ? "" : detalle.getCol27());
                writer.append('|');
                writer.append(detalle.getCol28() == null ? "" : detalle.getCol28());
                writer.append('|');
                writer.append(detalle.getCol29() == null ? "" : detalle.getCol29());
                writer.append('|');
                writer.append(detalle.getCol30() == null ? "" : detalle.getCol30());
                writer.append('|');

                writer.append('\n');
            }

            // Detalles
            for (SericaDTO detalle : listaDetalles) {
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol1()) ? "0.0" : detalle.getCol1());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol2()) ? "0.0" : detalle.getCol2());
                writer.append('|');
                writer.append(detalle.getCol3() == null ? "" : detalle.getCol3());
                writer.append('|');
                writer.append(detalle.getCol4() == null ? "" : detalle.getCol4());
                writer.append('|');
                writer.append(detalle.getCol5() == null ? "" : detalle.getCol5());
                writer.append('|');
                writer.append(detalle.getCol6() == null ? "" : detalle.getCol6());
                writer.append('|');
                writer.append(detalle.getCol7() == null ? "" : detalle.getCol7());
                writer.append('|');
                writer.append(detalle.getCol8() == null ? "" : detalle.getCol8());
                writer.append('|');
                writer.append(detalle.getCol9() == null ? "" : detalle.getCol9());
                writer.append('|');
                writer.append(detalle.getCol10() == null ? "" : detalle.getCol10());
                writer.append('|');
                writer.append(detalle.getCol11() == null ? "" : detalle.getCol11());
                writer.append('|');
                writer.append(detalle.getCol12() == null ? "" : detalle.getCol12());
                writer.append('|');
                writer.append(detalle.getCol13() == null ? "" : detalle.getCol13());
                writer.append('|');
                writer.append(detalle.getCol14() == null ? "" : detalle.getCol14().toString());
                writer.append('|');
                writer.append(detalle.getCol15() == null ? "" : detalle.getCol15().toString());
                writer.append('|');
                writer.append(detalle.getCol16() == null ? "" : detalle.getCol16());
                writer.append('|');
                writer.append(detalle.getCol17() == null ? "" : detalle.getCol17());
                writer.append('|');
                writer.append(detalle.getCol18() == null ? "" : detalle.getCol18());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol19()) ? "0.0" : detalle.getCol19());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol20()) ? "0.0" : detalle.getCol20());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol21()) ? "0.0" : detalle.getCol21());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol22()) ? "0.0" : detalle.getCol22());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol23()) ? "0.0" : detalle.getCol23());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol24()) ? "0.0" : detalle.getCol24());
                writer.append('|');
                writer.append(detalle.getCol25() == null ? "" : detalle.getCol25());
                writer.append('|');
                writer.append(detalle.getCol26() == null ? "" : detalle.getCol26());
                writer.append('|');
                writer.append(detalle.getCol27() == null ? "" : detalle.getCol27());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol28()) ? "0.0" : detalle.getCol28());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol29()) ? "0.0" : detalle.getCol29());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol30()) ? "0.0" : detalle.getCol30());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol31()) ? "0.0" : detalle.getCol31());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol32()) ? "0.0" : detalle.getCol32());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol33()) ? "0.0" : detalle.getCol33());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol34()) ? "0.0" : detalle.getCol34());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol35()) ? "0.0" : detalle.getCol35());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol36()) ? "0.0" : detalle.getCol36());
                writer.append('|');
                writer.append(detalle.getCol37() == null ? "" : detalle.getCol37());
                writer.append('|');
                writer.append(detalle.getCol38() == null ? "" : detalle.getCol38());
                writer.append('|');
                writer.append(detalle.getCol39() == null ? "" : detalle.getCol39());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol40()) ? "0.0" : detalle.getCol40());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol41()) ? "0.0" : detalle.getCol41());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol42()) ? "0.0" : detalle.getCol42());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol43()) ? "0.0" : detalle.getCol43());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol44()) ? "0.0" : detalle.getCol44());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol45()) ? "0.0" : detalle.getCol45());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol46()) ? "0.0" : detalle.getCol46());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol47()) ? "0.0" : detalle.getCol47());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol48()) ? "0.0" : detalle.getCol48());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol49()) ? "0.0" : detalle.getCol49());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol50()) ? "0.0" : detalle.getCol50());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol51()) ? "0.0" : detalle.getCol51());
                writer.append('|');
                writer.append(detalle.getCol52() == null ? "" : detalle.getCol52());
                writer.append('|');
                writer.append(detalle.getCol53() == null ? "" : detalle.getCol53());
                writer.append('|');
                writer.append(detalle.getCol54() == null ? "" : detalle.getCol54());
                writer.append('|');
                writer.append(detalle.getCol55() == null ? "" : detalle.getCol55());
                writer.append('|');
                writer.append(detalle.getCol56() == null ? "" : detalle.getCol56());
                writer.append('|');
                writer.append(detalle.getCol57() == null ? "" : detalle.getCol57());
                writer.append('|');
                writer.append(detalle.getCol58() == null ? "" : detalle.getCol58());
                writer.append('|');
                writer.append(detalle.getCol59() == null ? "" : detalle.getCol59());
                writer.append('|');
                writer.append(detalle.getCol60() == null ? "" : detalle.getCol60());
                writer.append('|');
                writer.append(detalle.getCol61() == null ? "" : detalle.getCol61());
                writer.append('|');
                writer.append(detalle.getCol62() == null ? "" : detalle.getCol62());
                writer.append('|');
                writer.append(detalle.getCol63() == null ? "" : detalle.getCol63());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol64()) ? "0.0" : detalle.getCol64());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol65()) ? "0.0" : detalle.getCol65());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol66()) ? "0.0" : detalle.getCol66());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol67()) ? "0.0" : detalle.getCol67());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol68()) ? "0.0" : detalle.getCol68());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol69()) ? "0.0" : detalle.getCol69());
                writer.append('|');
                writer.append(detalle.getCol70() == null ? "" : detalle.getCol70());
                writer.append('|');
                writer.append(detalle.getCol71() == null ? "" : detalle.getCol71());
                writer.append('|');
                writer.append(detalle.getCol72() == null ? "" : detalle.getCol72());
                writer.append('|');
                writer.append(detalle.getCol73() == null ? "" : detalle.getCol73());
                writer.append('|');
                writer.append(detalle.getCol74() == null ? "" : detalle.getCol74());
                writer.append('|');
                writer.append(detalle.getCol75() == null ? "" : detalle.getCol75());
                writer.append('|');
                writer.append(detalle.getCol76() == null ? "" : detalle.getCol76());
                writer.append('|');
                writer.append(detalle.getCol77() == null ? "" : detalle.getCol77());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol78()) ? "0.0" : detalle.getCol78());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol79()) ? "0.0" : detalle.getCol79());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol80()) ? "0.0" : detalle.getCol80());
                writer.append('|');
                writer.append(ValidacionUtil.esCadenaVacia(detalle.getCol81()) ? "0.0" : detalle.getCol81());
                writer.append('|');
                writer.append('\n');
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SistemaException("No fue posible escribir/leer el archivo temporal", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    private byte[] obtenerBytesTxt() throws IOException {
        try (FileInputStream fis = new FileInputStream(archivoTxt); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }

            byte[] bytes = bos.toByteArray();
            archivoTxt.delete();
            return bytes;
        }
    }

}
