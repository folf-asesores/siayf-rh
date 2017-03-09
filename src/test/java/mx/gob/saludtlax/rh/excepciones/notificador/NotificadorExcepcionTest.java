/*
 *  NotificadorExcepcionTest.java
 *  Creado el Jun 16, 2016 2:05:55 PM
 * 
 */
package mx.gob.saludtlax.rh.excepciones.notificador;


import java.io.File;

import javax.inject.Inject;

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

import static org.jboss.logging.Logger.getLogger;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Freddy Barrera <freddy.barrera@folfasesores.com.mx>
 */
@RunWith(Arquillian.class)
public class NotificadorExcepcionTest {

    @Inject private NotificadorExcepcion instance;
    
    private static final Logger LOGGER = getLogger(NotificadorExcepcionTest.class.getName());
    
     @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                ArchivePaths.create("beans.xml"));
        
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addClass(NotificadorExcepcion.class)
                .addClass(NotificadorExcepcionEJB.class)
                ;
        
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity().asFile();
        
        war.addAsLibraries(files);
        
        return war;
    }
    
    /**
     * Test of notificar method, of class NotificadorExcepcion.
     */
    @Ignore
    @Test
    public void testNotificar() {
        LOGGER.info("notificar");
        String tipo = "IllegalArgumetException";
        String mensaje = "Argumento invalido";
        String pilaSeguimiento = "A StackTrace";
        instance.notificar(null, tipo, mensaje, pilaSeguimiento, null);
        
        assertTrue(true);
    }
    
}
