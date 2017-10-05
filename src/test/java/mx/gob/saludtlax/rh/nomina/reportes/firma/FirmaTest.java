/*
 * FirmaTest.java
 * Creado el 11/sep/2017 1:58:21 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.File;
import java.io.IOException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.ReporteParametroEntity;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.reportes.AlmacenReportes;
import mx.gob.saludtlax.rh.reportes.BitacoraReporte;
import mx.gob.saludtlax.rh.reportes.BitacoraReporteEJB;
import mx.gob.saludtlax.rh.reportes.Generador;
import mx.gob.saludtlax.rh.reportes.Reporte;
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReporte;
import mx.gob.saludtlax.rh.reportes.txt.AlmacenReportesTxt;
import mx.gob.saludtlax.rh.reportes.txt.TxtGenerador;
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
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
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class FirmaTest {

    private static final Logger LOGGER = Logger.getLogger(FirmaTest.class.getName());

    @Inject
    private FirmaService firmaService;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "firmas-test.war");
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(ArchivoUtil.class);
        jar.addClass(AdministradorReportes.class);
        jar.addClass(AlmacenReportes.class);
        jar.addClass(AlmacenReportesTxt.class);
        jar.addClass(BitacoraReporte.class);
        jar.addClass(BitacoraReporteEJB.class);
        jar.addClass(BitacoraReporteEntity.class);
        jar.addClass(BitacoraReporteRepository.class);
        jar.addClass(CodigoError.class);
        jar.addClass(FechaUtil.class);
        jar.addClass(Firma.class);
        jar.addClass(FirmaBean.class);
        jar.addClass(FirmaDTO.class);
        jar.addClass(FirmaEmpleadoDTO.class);
        jar.addClass(FirmaMotor.class);
        jar.addClass(FirmaPojo.class);
        jar.addClass(FirmaService.class);
        jar.addClass(Generador.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(ProgramaDTO.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaCodigoError.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TxtGenerador.class);
        jar.addClass(TxtReporte.class);
        jar.addClass(UnidadResponsableDTO.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(ValidacionException.class);
        jar.addClass(ValidacionUtil.class);
        
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.BitacoraReporteEntity.class);
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.FirmaReporteQuery.class);
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.PerfilUsuarioEntity.class);        
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.ReporteParametroEntity.class);
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.ReporteParametroEntity.class);
        jar.addClass(mx.gob.saludtlax.rh.persistencia.espejo.UsuarioEntity.class);

        jar.addClass(AlmacenReportesJasperReports.class);
        jar.addClass(JasperReporte.class);
        
        
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
    public void generarReporte() throws IOException {
        Integer idProductoNomina = 32;
        FirmaDTO firma = firmaService.obtenerFirmaEmpleado(idProductoNomina);
        FirmaMotor firmaMotor = new FirmaMotor();
        byte[] reporte = firmaMotor.obtenerArchivo(firma);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "firmas-test.txt");
        Assert.assertNotNull(firma);
    }

    @Ignore
    @Test
    public void generarReporteCompleto() throws IOException {
        LOGGER.info("Iniciando test completo.");
        String[] parametros = new String[]{
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "listado-firmas",
            "TIPO_REPORTE", "txt",
            "ID_PRODUCTO_NOMINA", "30"
        };

        AdministradorReportes administradorReportes = new AdministradorReportes();
        String referencia = administradorReportes.obtenerReferencia(parametros);
        LOGGER.infov("Referencia {0}", referencia);
        byte[] reporte = administradorReportes.obtenerReporte(referencia);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "firmas-test.txt");
        Assert.assertNotNull(reporte);
    }
}
