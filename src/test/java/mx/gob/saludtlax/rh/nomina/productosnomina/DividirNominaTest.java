/*
 * DividirNominaTest.java
 * Creado el 26/Dec/2016 7:27:21 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Collections;
import java.util.List;

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

import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.BancoSatEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDEntity;
import mx.gob.saludtlax.rh.persistencia.FuncionEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.PaisEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.RamaEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubclasificacionTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposRecursosSatEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class DividirNominaTest {

    private static final Logger LOGGER = Logger.getLogger(DividirNominaTest.class.getName());

    @Inject
    private DividirNomina dividirNominaBean;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource("log4j-jboss.properties", "log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(BancoSatEntity.class);
        jar.addClass(CentroResponsabilidadEntity.class);
        jar.addClass(ConfiguracionPresupuestoEntity.class);
        jar.addClass(CodigoError.class);
        jar.addClass(CuentasBancariasEntity.class);
        jar.addClass(DependenciaTempEntity.class);
        jar.addClass(DividirNomina.class);
        jar.addClass(DividirNominaDTO.class);
        jar.addClass(DividirNominaEJB.class);
        jar.addClass(DividirNominaFiltro.class);
        jar.addClass(DividirNominaService.class);
        jar.addClass(EjercicioFiscalEntity.class);
        jar.addClass(EmpleadoEntity.class);
        jar.addClass(EstatusConfiguracionesEntity.class);
        jar.addClass(EstatusNominasEmpleadoEntity.class);
        jar.addClass(EstatusProductoNominaEntity.class);
        jar.addClass(FuenteFinanciamientoEntity.class);
        jar.addClass(FuenteFinanciamientoOPDEntity.class);
        jar.addClass(FuncionEntity.class);
        jar.addClass(NominaEmpleadoEntity.class);
        jar.addClass(PaisEntity.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(PeriodoCalendariosEntity.class);
        jar.addClass(ProductoNominaEntity.class);
        jar.addClass(ProgramaEntity.class);
        jar.addClass(ProyectoTempEntity.class);
        jar.addClass(PuestoGeneralEntity.class);
        jar.addClass(RamaEntity.class);
        jar.addClass(SubclasificacionTabuladorEntity.class);
        //    jar.addClass(SubfuenteFinanciamientoEntity.class);
        jar.addClass(SubFuenteFinanciamientoTempEntity.class);
        jar.addClass(TabuladorEntity.class);
        jar.addClass(TipoContratacionEntity.class);
        jar.addClass(TipoEmpleadoEntity.class);
        jar.addClass(TipoNominaEntity.class);
        jar.addClass(TipoPeriodoEntity.class);
        jar.addClass(TipoPuestoEntity.class);
        jar.addClass(TipoRecursoTempEntity.class);
        jar.addClass(TipoTabuladorEntity.class);
        jar.addClass(TiposNombramientosEntity.class);
        jar.addClass(TiposRecursosSatEntity.class);
        jar.addClass(UnidadResponsableEntity.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(ValidacionCodigoError.class);
        jar.addClass(ValidacionException.class);
        jar.addClass(ValidacionUtil.class);
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    /**
     * Test of obtenerProductoNomina method, of class DividirNomina.
     */
    // @Ignore
    @Test
    public void testObtenerProductoNomina() {
        LOGGER.info("obtenerProductoNomina");
        Integer idProductoNomina = 4;
        List<DividirNominaDTO> result = dividirNominaBean.obtenerProductoNomina(20, 20, idProductoNomina);

        for (DividirNominaDTO dividirNominaDTO : result) {
            LOGGER.info(dividirNominaDTO);
        }

        assertNotNull(result);
    }

    /**
     * Test of dividirProductoNomina method, of class DividirNomina.
     */
    @Ignore
    @Test
    public void testDividirProductoNomina() {
        LOGGER.info("dividirProductoNomina");
        //        DividirNominaFiltro filtro = new DividirNominaFiltro(4, "PruProNom-1", "VAPM731015AF0", "DIRECCION DE ADMINISTRACION", "RAMA AFIN");
        //        DividirNominaFiltro filtro = new DividirNominaFiltro(4, "PruProNom-1", "VAPM731015AF0", "", "");
        //        DividirNominaFiltro filtro = new DividirNominaFiltro(4, "PruProNom-1", "", "DIRECCION DE ADMINISTRACION", "");
        DividirNominaFiltro filtro = new DividirNominaFiltro(4, "PruProNom-1", Collections.EMPTY_LIST, "", "RAMA AFIN");
        List<DividirNominaDTO> result = dividirNominaBean.dividirProductoNomina(filtro, 1);

        for (DividirNominaDTO dividirNominaDTO : result) {
            LOGGER.info(dividirNominaDTO);
        }

        //assertNotNull(result);
    }

}
