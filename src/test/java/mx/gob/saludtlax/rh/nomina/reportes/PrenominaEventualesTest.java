/*
 * PrenominaEventualesTest.java
 * Creado el 28/Nov/2016 9:38:10 AM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.File;
import java.io.IOException;

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
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

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

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class PrenominaEventualesTest {

    private static final Logger LOGGER = Logger.getLogger(PrenominaEventualesTest.class.getName());

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
        jar.addAsResource("reportes/prenomina_eventuales--percepciones.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--percepciones-centro-responsabilidad.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--percepciones-total.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--percepciones-programa.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--deducciones.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--deducciones-centro-responsabilidad.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--deducciones-programa.jrxml");
        jar.addAsResource("reportes/prenomina_eventuales--deducciones-total.jrxml");
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void obtenerReferencia() {
        LOGGER.info("Corre");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[]{
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "prenomina_eventuales",
            "TIPO_REPORTE", "txt",
            "ID_PRODUCTO_NOMINA", "6"
        };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        assertNotNull(referencia);
    }

    @Ignore
    @Test
    public void obtenerReporte() throws IOException {
        LOGGER.info("obtenerReporte");
        // ID_NOMINA = 28
//        String referencia = "80fbbf0e-76b3-4a22-8ee4-ac167a1f";
        // ID_NOMINA = 30
//        String referencia = "730234d9-a8e7-41fb-95b8-ee2a472a";
        // ID_NOMINA = 32
        String referencia = "312b6624-dce5-4680-a644-7b9ff3a5";

        AdministradorReportes instance = new AdministradorReportes();
        byte[] result = instance.obtenerReporte(referencia);

        ArchivoUtil.guardarEnCarpetaUsuario(result, "prenomina-eventuales.txt");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void testReporte() throws IOException {
        LOGGER.info("Iniciando test : Reporte");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] {
            "ID_USUARIO", "33",
            "REPORTE_NOMBRE", "prenomina_eventuales",
            "TIPO_REPORTE", "txt",
            "ID_PRODUCTO_NOMINA", "30"
        };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "prenomina-eventuales.txt");
        assertNotNull(result);
    }

}
