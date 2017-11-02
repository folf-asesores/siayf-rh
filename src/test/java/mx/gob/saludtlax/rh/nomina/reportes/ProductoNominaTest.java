/*
 * ProductoNominaTest.java
 * Creado el 13/Feb/2017 8:26:47 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.File;
import java.io.IOException;

import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNomina;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaEJB;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaService;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;
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
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReporte;
import mx.gob.saludtlax.rh.reportes.txt.AlmacenReportesTxt;
import mx.gob.saludtlax.rh.reportes.txt.TxtGenerador;
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
import mx.gob.saludtlax.rh.reporteslaborales.productonomina.ProductoNominaExcel;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
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

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class ProductoNominaTest {

    private static final Logger LOGGER = Logger.getLogger(ProductoNominaTest.class.getName());

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
        jar.addClass(AlmacenReportesJasperReports.class);
        jar.addClass(AlmacenReportesTxt.class);
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
        jar.addClass(JasperReporte.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(ProductoNomina.class);
        jar.addClass(ProductoNominaEJB.class);
        jar.addClass(ContratoExcel.class);
        jar.addClass(ProductoNominaExcel.class);
        jar.addClass(ProductoNominaService.class);
        jar.addClass(ProductosNominaExcelDTO.class);
        jar.addClass(ReglaNegocioException.class);
        jar.addClass(ReglaNegocioCodigoError.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TxtReporte.class);
        jar.addClass(TxtGenerador.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(ValidacionException.class);
        jar.addClass(ValidacionUtil.class);
        jar.addAsResource("plantillas/nomina/Producto_Nomina.xlsx");
        war.addAsLibrary(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity()
                .asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void obtenerReferencia() {
        LOGGER.info("Iniciando test obtenerReferencia");
        String[] parametros = new String[] {
            "ID_USUARIO", "42",
            "REPORTE_NOMBRE", "producto_nomina",
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
//        String referencia = "4c25e610-8d82-4130-979b-b952022f";
        String referencia = "7fb464b2-260f-43ec-afd9-eab002d8";
        AdministradorReportes admin = new AdministradorReportes();
        byte[] result = admin.obtenerReporte(referencia);

        ArchivoUtil.guardarEnCarpetaUsuario(result, "producto_nomina.xlsx");
        assertNotNull(result);
    }
}
