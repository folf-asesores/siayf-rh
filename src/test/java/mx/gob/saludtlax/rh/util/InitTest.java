
package mx.gob.saludtlax.rh.util;

import java.io.File;
import java.io.Serializable;

import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class InitTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4253520296541999184L;

    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addPackage("mx.gob.saludtlax.rh").addPackage("mx.gob.saludtlax.rh.api.adjuntos")
                .addPackage("mx.gob.saludtlax.rh.api.areas").addPackage("mx.gob.saludtlax.rh.api.bolsa_trabajo")
                .addPackage("mx.gob.saludtlax.rh.api.bolscaTrabajo").addPackage("mx.gob.saludtlax.rh.api.ca").addPackage("mx.gob.saludtlax.rh.api.catalogos")
                .addPackage("mx.gob.saludtlax.rh.api.conceptosnomina").addPackage("mx.gob.saludtlax.rh.api.documentosAdjuntos")
                .addPackage("mx.gob.saludtlax.rh.api.empleados").addPackage("mx.gob.saludtlax.rh.api.movimientos")
                .addPackage("mx.gob.saludtlax.rh.api.perfiles").addPackage("mx.gob.saludtlax.rh.configuracion.plazas")
                .addPackage("mx.gob.saludtlax.rh.api.nomina").addPackage("mx.gob.saludtlax.rh.api.nomina.timbrado")
                .addPackage("mx.gob.saludtlax.rh.api.nomina.calculonomina").addPackage("mx.gob.saludtlax.rh.api.nomina.importarcfdi")
                .addPackage("mx.gob.saludtlax.rh.api.nomina.importarconstancia").addPackage("mx.gob.saludtlax.rh.api.nomina.importarprenomina")
                .addPackage("mx.gob.saludtlax.rh.api.nomina.calculonomina.isr").addPackage("mx.gob.saludtlax.rh.api.retenciones")
                .addPackage("mx.gob.saludtlax.rh.api.aspirantes").addPackage("mx.gob.saludtlax.rh.configuracion.profesion")
                .addPackage("mx.gob.saludtlax.rh.api.configuracion.puesto").addPackage("mx.gob.saludtlax.rh.api.usuarios")
                .addPackage("mx.gob.saludtlax.rh.api.bolsaTrabajo").addPackage("mx.gob.saludtlax.rh.persistencia").addPackage("mx.gob.saludtlax.rh.excepciones")
                .addPackage("mx.gob.saludtlax.rh.expediente").addPackage("mx.gob.saludtlax.rh.util").addPackage("mx.gob.saludtlax.rh.rest")
                .addPackage("mx.gob.saludtlax.rh.rest.cliente.ca.incidencia").addPackage("mx.gob.saludtlax.rh.rest.dfacture.timbrado")
                .addPackage("mx.gob.saludtlax.rh.sat.xml.nomina").addPackage("mx.gob.saludtlax.rh.sat.xml.nomina.paquete")
                .addPackage("mx.gob.saludtlax.rh.sat.xml.timbre").addPackage("mx.gob.saludtlax.rh.sat.xml.cfdi")
                .addPackage("mx.gob.saludtlax.rh.sat.xml.retenciones")

                .addPackage("mx.gob.saludtlax.rh.nomina.timbrarnominaestatal").addPackage("mx.gob.saludtlax.rh.api.nomina.timbrado.exportarxml")
                .addPackage("mx.gob.saludtlax.rh.nomina.exportarxml")

                .addAsManifestResource("META-INF/beans.xml", "beans.xml").addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsResource("Incidencias/logowiki.png", "logowiki.png");

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;

    }

}
