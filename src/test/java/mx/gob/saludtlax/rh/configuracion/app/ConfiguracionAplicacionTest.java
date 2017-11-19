/*
 * ConfiguracionAplicacionTest.java
 * Creado el Sep 19, 2016 12:45:59 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.app;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionRepository;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class ConfiguracionAplicacionTest {

    @Inject
    ConfiguracionAplicacion config;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml").addClass(GenericRepository.class).addClass(CodigoError.class)
                .addClass(ConfiguracionAplicacion.class).addClass(ConfiguracionAplicacionEJB.class).addClass(ConfiguracionAplicacionEntity.class)
                .addClass(ConfiguracionAplicacionRepository.class).addClass(ConfiguracionAplicacionService.class).addClass(SistemaCodigoError.class)
                .addClass(SistemaException.class).addClass(ReglaNegocioCodigoError.class).addClass(ReglaNegocioException.class).addClass(Repository.class)
                .addClass(ValidacionCodigoError.class).addClass(ValidacionException.class).addClass(ValidacionUtil.class);

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();

        war.addAsLibraries(files);

        return war;
    }

    @Test
    public void configuracionTest() {
        //assertNotNull(config);
        //assertNotNull(config.getConfiguraciones("adjuntos.basepath", "app.path"));
        assertTrue(config.getConfiguraciones("adjuntos.basepath", "app.path").size() == 2);
    }
}
