/*
 * FirmaTest.java
 * Creado el 11/Sep/2017 1:58:21 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.File;
import java.io.IOException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.espejo.FirmaReporteQuery;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class FirmaTest {

    private static final Logger LOGGER = Logger.getLogger(FirmaTest.class.getName());
    
    @Inject
    private FirmaService firmaService;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(ArchivoUtil.class);
        jar.addClass(FechaUtil.class);
        jar.addClass(FirmaDTO.class);
        jar.addClass(FirmaEmpleadoDTO.class);
        jar.addClass(FirmaMotor.class);
        jar.addClass(FirmaPOJO.class);
        jar.addClass(FirmaService.class);
        jar.addClass(FirmaReporteQuery.class);
        
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
    public void dummyTest() {
        LOGGER.info("Hola mundo");
    }
    
    @Ignore
    @Test
    public void generarReporte() throws IOException {
        Integer idProductoNomina = 37;
        FirmaDTO firma = firmaService.obtenerFirmaEmpleado(idProductoNomina);
        FirmaMotor firmaMotor = new FirmaMotor();
        byte[] reporte = firmaMotor.obtenerArchivo(firma);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "firmas-test.txt");
        Assert.assertNotNull(firma);
    }
}
