/*
 * NominaFederalesTest.java
 * Creado el 15/Feb/2017 5:17:27 PM
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

import static org.junit.Assert.*;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@RunWith(Arquillian.class)
public class NominaFederalesTest {

    private static final Logger LOGGER = Logger.getLogger(NominaFederalesTest.class.getName());

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
        jar.addAsResource("reportes/nomina_federales.jrxml");
        jar.addAsResource("reportes/nomina_federales--percepciones.jrxml");
        jar.addAsResource("reportes/nomina_federales--percepciones-centro-responsabilidad.jrxml");
        jar.addAsResource("reportes/nomina_federales--percepciones-total.jrxml");
        jar.addAsResource("reportes/nomina_federales--deducciones.jrxml");
        jar.addAsResource("reportes/nomina_federales--deducciones-centro-responsabilidad.jrxml");
        jar.addAsResource("reportes/nomina_federales--deducciones-total.jrxml");
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
        LOGGER.info("Iniciando test :: obtenerReferencia");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] {
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "nomina_federales",
            "TIPO_REPORTE",  "txt",
            "ID_PRODUCTO_NOMINA", "4"
        };

        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        assertNotNull(referencia);
    }

    @Ignore
    @Test
    public void obtenerReporte() throws IOException {
        LOGGER.info("Iniciando test :: obtenerReporte");
        String referencia = "73952277-fcd0-4f00-960d-bb51a6e1";
        AdministradorReportes instance = new AdministradorReportes();
        byte[] result = instance.obtenerReporte(referencia);
        
        ArchivoUtil.guardarEnCarpetaUsuario(result, "nomina-federales.txt");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void testReporte() throws IOException {
        LOGGER.info("Iniciando test :: Reporte");
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] {
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "nomina_federales",
            "TIPO_REPORTE",  "txt",
            "ID_PRODUCTO_NOMINA", "4"
        };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = adm.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "nomina-federales.txt");
        assertNotNull(result);
    }

}
