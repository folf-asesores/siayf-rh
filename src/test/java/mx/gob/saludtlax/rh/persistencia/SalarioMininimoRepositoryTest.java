
package mx.gob.saludtlax.rh.persistencia;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SalarioMininimoRepositoryTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6418975419731024628L;

    @Inject
    SalarioMinimoRepository salarioMinimoRepository;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass("mx.gob.saludtlax.rh.persistencia.SalarioMinimoRepository")
                .addClass("mx.gob.saludtlax.rh.persistencia.SalarioMinimoEntity").addClass("mx.gob.saludtlax.rh.persistencia.GenericRepository")
                .addClass("mx.gob.saludtlax.rh.persistencia.Repository").addClass("mx.gob.saludtlax.rh.excepciones.SistemaException")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml").addAsManifestResource("META-INF/persistence.xml", "persistence.xml");

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;

    }

    @Test
    public void obtenerSalarioMinimoActual() {

        SalarioMinimoEntity salarioMinimoEntity = salarioMinimoRepository.obtenerSalarioMinimoActual();

        Assert.assertEquals(salarioMinimoEntity.getSalarioMinimo(), new BigDecimal("80.0000"));
    }

}
