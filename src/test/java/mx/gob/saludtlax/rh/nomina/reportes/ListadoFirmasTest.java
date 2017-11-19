/*
 * ListadoFirmasTest.java
 * Creado el 23/Nov/2016 6:38:50 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.ReporteParametroEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.reportes.AlmacenReportes;
import mx.gob.saludtlax.rh.reportes.BitacoraReporte;
import mx.gob.saludtlax.rh.reportes.BitacoraReporteEJB;
import mx.gob.saludtlax.rh.reportes.Generador;
import mx.gob.saludtlax.rh.reportes.Reporte;
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReporte;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReportsCompilador;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReportsGenerador;
import mx.gob.saludtlax.rh.reportes.jasperreports.NumeroALetraScriptlet;
import mx.gob.saludtlax.rh.reportes.txt.AlmacenReportesTxt;
import mx.gob.saludtlax.rh.reportes.txt.TxtGenerador;
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.NumeroUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class ListadoFirmasTest {

    private static final String DATASOURCE_NAME = "java:jboss/datasources/SIAYFRHDS";
    private static final Logger LOGGER = Logger.getLogger(ListadoFirmasTest.class.getName());

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(AdministradorReportes.class);
        jar.addClass(AlmacenReportes.class);
        jar.addClass(AlmacenReportesJasperReports.class);
        jar.addClass(AlmacenReportesTxt.class);
        jar.addClass(ArchivoUtil.class);
        jar.addClass(BitacoraReporte.class);
        jar.addClass(BitacoraReporteEntity.class);
        jar.addClass(BitacoraReporteEJB.class);
        jar.addClass(BitacoraReporteRepository.class);
        jar.addClass(Generador.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(JasperReporte.class);
        jar.addClass(JasperReportsCompilador.class);
        jar.addClass(JasperReportsGenerador.class);
        jar.addClass(NumeroALetra.class);
        jar.addClass(NumeroALetraScriptlet.class);
        jar.addClass(NumeroUtil.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TxtGenerador.class);
        jar.addClass(TxtReporte.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(ValidacionUtil.class);
        jar.addAsResource("reportes/listado-firmas.jrxml");
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void obtenerReferenciaListadoFirmas() {
        LOGGER.info("Iniciando :: obtenerReferenciaListadoFirmas");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "listado-firmas", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "1" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        assertNotNull(referencia);
    }

    @Ignore
    @Test
    public void obtenerListadoFirmas() throws IOException {
        LOGGER.info("obtenerReporte");
        String referencia = "863f1a11-e747-4e06-9e59-e6a7643a";
        AdministradorReportes instance = new AdministradorReportes();
        byte[] result = instance.obtenerReporte(referencia);

        ArchivoUtil.guardarEnCarpetaUsuario(result, "listado-firmas.txt");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void testListadoFirmas() throws IOException {
        LOGGER.info("Iniciando :: testListadoFirmas");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "listado-firmas", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "6" };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        AdministradorReportes instance = new AdministradorReportes();
        byte[] result = instance.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "listado-firmas.txt");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void generateReportWithJdbcDataSource() throws JRException, NamingException, SQLException {
        String reportSource = "/home/neo_cs/Development/NetBeansProjects/SVN/trabajo/saludtlax/rh/siayf-rh/src/main/resources/reportes/listado-firmas.jrxml";
        String output = "/home/neo_cs/listado-firmas.txt";

        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup(DATASOURCE_NAME);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID_PRODUCTO_NOMINA", 8);

        try (Connection conexion = ds.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
            SimpleExporterInput sei = new SimpleExporterInput(jasperPrint);
            SimpleTextReportConfiguration strc = new SimpleTextReportConfiguration();
            SimpleWriterExporterOutput sweo = new SimpleWriterExporterOutput(output);

            JRTextExporter exporter = new JRTextExporter();
            exporter.setExporterInput(sei);
            exporter.setConfiguration(strc);
            exporter.setExporterOutput(sweo);

            exporter.exportReport();
        }
    }

    @Ignore
    @Test
    public void generateReportWithCsvDataSource() throws JRException {
        String csvFileName = "/home/neo_cs/listado-firmas__ds.csv";
        String reportSource = "/home/neo_cs/listado-firmas.jrxml";
        String output = "/home/neo_cs/listado-firmas.txt";

        JRCsvDataSource dataSource = new JRCsvDataSource(JRLoader.getLocationInputStream(csvFileName));
        dataSource.setRecordDelimiter("\n");
        dataSource.setUseFirstRowAsHeader(true);
        dataSource.setColumnNames(new String[] { "USER_NAME", "LAST_ACCESSED", "IS_ACTIVE", "POINTS" });

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        SimpleExporterInput sei = new SimpleExporterInput(jasperPrint);
        SimpleTextReportConfiguration strc = new SimpleTextReportConfiguration();

        SimpleWriterExporterOutput sweo = new SimpleWriterExporterOutput(output);

        JRTextExporter exporter = new JRTextExporter();
        exporter.setExporterInput(sei);
        exporter.setConfiguration(strc);
        exporter.setExporterOutput(sweo);

        exporter.exportReport();
    }
}
