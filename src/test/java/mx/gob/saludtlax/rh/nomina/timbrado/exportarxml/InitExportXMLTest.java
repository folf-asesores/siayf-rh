
package mx.gob.saludtlax.rh.nomina.timbrado.exportarxml;

import java.io.File;
import java.io.Serializable;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class InitExportXMLTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4253520296541999184L;

    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addPackage("mx.gob.saludtlax.rh").addPackage("mx.gob.saludtlax.rh.nomina")
                .addPackage("mx.gob.saludtlax.rh.nomina.exportarxml").addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsResource("Incidencias/logowiki.png", "logowiki.png");

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;

    }

}
