/*
 * PagoGeneralServiceTest.java
 * Creado el 16/Feb/2017 4:53:43 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.File;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.productosnomina.PagoGeneralService;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@RunWith(Arquillian.class)
public class PagoGeneralServiceTest {
    @Inject
    private PagoGeneralService pagoGeneralService;

    private static final Logger LOGGER = Logger.getLogger(PagoGeneralServiceTest.class.getName());
    
    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");
        
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(BancoSatEntity.class);
        jar.addClass(CentroResponsabilidadEntity.class);
        jar.addClass(ConfiguracionPresupuestoEntity.class);
        jar.addClass(CuentasBancariasEntity.class);
        jar.addClass(DependenciaTempEntity.class);
        jar.addClass(EmpleadoEntity.class);
        jar.addClass(EjercicioFiscalEntity.class);
        jar.addClass(EstatusConfiguracionesEntity.class);
        jar.addClass(EstatusNominasEmpleadoEntity.class);
        jar.addClass(EstatusProductoNominaEntity.class);
        jar.addClass(FuenteFinanciamientoEntity.class);
        jar.addClass(FuncionEntity.class);
        jar.addClass(NominaEmpleadoEntity.class);
        jar.addClass(PagoGeneralService.class);
        jar.addClass(PaisEntity.class);
        jar.addClass(PeriodoCalendariosEntity.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(ProductoNominaEntity.class);
        jar.addClass(ProgramaEntity.class);
        jar.addClass(ProyectoTempEntity.class);
        jar.addClass(PuestoGeneralEntity.class);
        jar.addClass(RamaEntity.class);
        jar.addClass(SubclasificacionTabuladorEntity.class);
        jar.addClass(SubFuenteFinanciamientoTempEntity.class);
        jar.addClass(TabuladorEntity.class);
        jar.addClass(TipoContratacionEntity.class);
        jar.addClass(TipoEmpleadoEntity.class);
        jar.addClass(TipoNominaEntity.class);
        jar.addClass(TipoPeriodoEntity.class);
        jar.addClass(TipoPuestoEntity.class);
        jar.addClass(TipoRecursoTempEntity.class);
        jar.addClass(TiposRecursosSatEntity.class);
        jar.addClass(TiposNombramientosEntity.class);
        jar.addClass(TipoTabuladorEntity.class);
        jar.addClass(UnidadResponsableEntity.class);
        jar.addClass(UsuarioEntity.class);
        war.addAsLibraries(jar);

        File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                        .withTransitivity().asFile();
        war.addAsLibraries(dependencies);

        return war;
    }
    
    @Ignore
    @Test
    public void test() {
        Boolean resultado = true;
        // Comentado porque el método es privado.
        // Boolean resultado = pagoGeneralService.rfcPerteneceAProductoNomina("CUTJ8809095A0", 19);
        assertTrue(resultado);
    }

}
