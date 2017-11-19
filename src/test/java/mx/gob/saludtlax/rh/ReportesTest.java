
package mx.gob.saludtlax.rh;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;

import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.nomina.reportes.ProductoNominaTest;
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
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class ReportesTest {

    private static final Logger LOGGER = Logger.getLogger(ProductoNominaTest.class.getName());

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
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TxtReporte.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(NumeroALetraScriptlet.class);
        jar.addClass(NumeroALetra.class);
        jar.addClass(ValidacionUtil.class);
        jar.addAsResource("reportes/prenomina_eventuales.jrxml");
        jar.addAsResource("reportes/movimientos_prevalidados.jrxml");
        jar.addAsResource("reportes/afectacion.jrxml");
        jar.addAsResource("reportes/presupuestal.jrxml");
        jar.addAsResource("reportes/contabilidad.jrxml");
        jar.addAsResource("reportes/listado_firmas.jrxml");
        jar.addAsResource("reportes/reporte_previo_nomina_extraordinaria.jrxml");
        jar.addAsResource("reportes/listado_pension_alimenticia.jrxml");
        jar.addAsResource("reportes/proceso_posterior_quincena.jrxml");
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Test
    public void obtenerReferenciaMovimientos() throws IOException {
        LOGGER.info("Iniciando test obtenerReferenciaMovimientos");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "movimientos_prevalidados", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA",
                "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "movimientos_prevalidados.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaAfectacion() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "afectacion", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "afectacion.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaPresupuestal() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "presupuestal", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "presupuestal.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaContabilidad() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "contabilidad", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "contabilidad.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaFirmas() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "listado_firmas", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "listado_firmas.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaNomina() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "reporte_previo_nomina_extraordinario", "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "reporte_previo_nomina_extraordinario.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaPension() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "listado_pension_alimenticia", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA",
                "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "listado_pension_alimenticia.txt");
        assertNotNull(result);
    }

    @Test
    public void obtenerReferenciaQuincena() throws IOException {
        LOGGER.info("Iniciando test obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] { "ID_USUARIO", "42", "REPORTE_NOMBRE", "proceso_posterior_quincena", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA",
                "9" };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "proceso_posterior_quincena.txt");
        assertNotNull(result);
    }
}