/*
 * ProductoNominaFederalesTest.java
 * Creado el 16/Mar/2017 5:02:04 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.File;
import java.io.IOException;
import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.reportes.federales.ProductoNominaFederalReporte;
import mx.gob.saludtlax.rh.nomina.reportes.federales.ProductoNominaFederalReporteEJB;
import mx.gob.saludtlax.rh.nomina.reportes.federales.ProductoNominaFederalReporteExcelService;
import mx.gob.saludtlax.rh.nomina.reportes.federales.ProductoNominaFederalReporteService;
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
import mx.gob.saludtlax.rh.reportes.excel.AlmacenReportesExcel;
import mx.gob.saludtlax.rh.reportes.excel.ExcelGenerador;
import mx.gob.saludtlax.rh.reportes.excel.ExcelReporte;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;
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
import static org.junit.Assert.*;
import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@RunWith(Arquillian.class)
public class ProductoNominaFederalesTest {

    private static final Logger LOGGER = Logger.getLogger(ProductoNominaFederalesTest.class.getName());
    
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
        jar.addClass(AlmacenReportesExcel.class);
        jar.addClass(ArchivoUtil.class);
        jar.addClass(BitacoraReporte.class);
        jar.addClass(BitacoraReporteEJB.class);
        jar.addClass(BitacoraReporteRepository.class);
        jar.addClass(BitacoraReporteEntity.class);
        jar.addClass(CodigoError.class);
        jar.addClass(ExcelReporte.class);
        jar.addClass(ExcelGenerador.class);
        jar.addClass(FechaUtil.class);
        jar.addClass(Generador.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(ProductoNominaFederalReporte.class);
        jar.addClass(ProductoNominaFederalReporteEJB.class);
        jar.addClass(ProductoNominaFederalReporteExcelService.class);
        jar.addClass(ProductoNominaFederalReporteService.class);
        jar.addClass(Reporte.class);
        jar.addClass(Repository.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(ReglaNegocioException.class);
        jar.addClass(SistemaException.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(ValidacionException.class);
        jar.addClass(ValidacionUtil.class);
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
        LOGGER.info("Iniciando test obtenerReferencia");
        String[] parametros = new String[] {
            "ID_USUARIO", "30",
            "REPORTE_NOMBRE", "producto_nomina_federales",
            "TIPO_REPORTE",  "xlsx",
            "ID_PRODUCTO_NOMINA", "27"
        };

        AdministradorReportes admin = new AdministradorReportes();
        String referencia = admin.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        assertNotNull(referencia);
        
    }
    
    @Ignore
    @Test
    public void obtenerReporte() throws IOException {
        LOGGER.info("Iniciando test obtenerReporte");
        String referencia = "c9cb86e7-de32-4ed3-ba79-a226ea93";
        AdministradorReportes admin = new AdministradorReportes();
        byte[] result = admin.obtenerReporte(referencia);
        
        ArchivoUtil.guardarEnCarpetaUsuario(result, "producto-nomina-federales.xlsx");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void testReporteCompleto() throws IOException {
        LOGGER.info("Iniciando test completo");
        String[] parametros = new String[] {
            "ID_USUARIO", "30",
            "REPORTE_NOMBRE", "producto_nomina_federales",
            "TIPO_REPORTE",  "xlsx",
            "ID_PRODUCTO_NOMINA", "27"
        };
        AdministradorReportes admin = new AdministradorReportes();
        String referencia = admin.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = admin.obtenerReporte(referencia);
        LOGGER.infov("Bytes son nulos: {0}", result == null);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "producto-nomina-federales.xlsx");
        assertNotNull(result);
    }
    
    
}
