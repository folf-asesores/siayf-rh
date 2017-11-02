/*

 * PagoGeneralTest.java
 * Creado el 15/Feb/2017 6:17:08 AM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.File;
import java.io.IOException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteDTO;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteEJB;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteExcelService;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporteService;
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
import mx.gob.saludtlax.rh.reportes.excel.ReporteVacio;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;
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

import static org.junit.Assert.assertNotNull;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporte;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class PagoGeneralTest {

    private static final Logger LOGGER = Logger.getLogger(PagoGeneralTest.class.getName());

    @Inject
    private PagoGeneralReporte pagoGeneral;
    
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
        jar.addClass(Generador.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(FechaUtil.class);
        jar.addClass(PagoGeneralReporte.class);
        jar.addClass(PagoGeneralReporteDTO.class);
        jar.addClass(PagoGeneralReporteEJB.class);
        jar.addClass(PagoGeneralReporteExcelService.class);
        jar.addClass(PagoGeneralReporteService.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(ReglaNegocioException.class);
        jar.addClass(ReglaNegocioCodigoError.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(ReporteVacio.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(ValidacionCodigoError.class);
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
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "pago_general",
            "TIPO_REPORTE",  "xlsx",
            "ID_PRODUCTO_NOMINA", "9"
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
        String referencia = "e8dc2e63-de43-4e65-9c16-0963d2b0";
        AdministradorReportes admin = new AdministradorReportes();
        byte[] result = admin.obtenerReporte(referencia);
        
        ArchivoUtil.guardarEnCarpetaUsuario(result, "pago-general.xlsx");
        assertNotNull(result);
    }

    @Ignore
    @Test
    public void testCompleto() throws IOException {
        LOGGER.info("Iniciando test completo");
        String[] parametros = new String[] {
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "pago_general",
            "TIPO_REPORTE",  "xlsx",
            "ID_PRODUCTO_NOMINA", "28"
        };
        AdministradorReportes admin = new AdministradorReportes();
        String referencia = admin.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = admin.obtenerReporte(referencia);
        LOGGER.infov("Bytes son nulos: {0}", result == null);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "pago-general.xlsx");
        assertNotNull(result);
    }
    
    @Ignore
    @Test
    public void testEjb() throws IOException {
        byte[] bytes = pagoGeneral.generarReporte(9);
        ArchivoUtil.guardarEnCarpetaUsuario(bytes, "pago-general.xlsx");
        assertNotNull(bytes);
    }
}
