/*
 * ComprobanteEmpleadoTest.java
 * Creado el 13/Jan/2017 8:08:31 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.File;
import java.io.IOException;
import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.DispersionDTO;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.DispersionEJB;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.DispersionReporteService;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.DispersionService;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEmpleadoRepository;
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
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReporte;
import mx.gob.saludtlax.rh.reportes.txt.AlmacenReportesTxt;
import mx.gob.saludtlax.rh.reportes.txt.TxtGenerador;
import mx.gob.saludtlax.rh.reportes.txt.TxtReporte;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroUtil;
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
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@RunWith(Arquillian.class)
public class ComprobanteEmpleadoTest {

    private static final Logger LOGGER = Logger.getLogger(ComprobanteEmpleadoTest.class.getName());

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(AdministradorReportes.class);
        jar.addClass(AlmacenReportes.class);
        jar.addClass(AlmacenReportesJasperReports.class);
        jar.addClass(AlmacenReportesTxt.class);
        jar.addClass(ArchivoUtil.class);
        jar.addClass(BitacoraReporte.class);
        jar.addClass(BitacoraReporteEJB.class);
        jar.addClass(BitacoraReporteEntity.class);
        jar.addClass(BitacoraReporteRepository.class);
        jar.addClass(CodigoError.class);
        jar.addClass(ComprobanteEmpleado.class);
        jar.addClass(ComprobanteEmpleadoDTO.class);
        jar.addClass(ComprobanteEmpleadoEJB.class);
        jar.addClass(ComprobanteEmpleadoPOJO.class);
        jar.addClass(ComprobanteEmpleadoService.class);
        jar.addClass(ComprobanteEmpleadoRepository.class);
        jar.addClass(ComprobanteEmpleadoRep.class);
        jar.addClass(ConceptoComprobanteDTO.class);
        jar.addClass(Dispersion.class);
        jar.addClass(DispersionDTO.class);
        jar.addClass(DispersionEJB.class);
        jar.addClass(DispersionReporteService.class);
        jar.addClass(DispersionService.class);
        jar.addClass(FechaUtil.class);
        jar.addClass(Generador.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(JasperReporte.class);
        jar.addClass(NumeroUtil.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(Reporte.class);
        jar.addClass(ReporteParametroEntity.class);
        jar.addClass(Repository.class);
        jar.addClass(SistemaCodigoError.class);
        jar.addClass(SistemaException.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TxtReporte.class);
        jar.addClass(TxtGenerador.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(ValidacionUtil.class);
        jar.addClass(ValidacionCodigoError.class);
        jar.addClass(ValidacionException.class);
        war.addAsLibrary(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity()
                .asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void generarReporte() throws IOException {
        LOGGER.info("Iniciando test obtener comprobante de nomina");
        String[] parametros = new String[] {
            "ID_USUARIO", "18",
            "REPORTE_NOMBRE", "comprobante_nomina",
            "TIPO_REPORTE",  "txt",
            "ID_PRODUCTO_NOMINA", "1"
        };
        AdministradorReportes admin = new AdministradorReportes();
        String referencia = admin.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        byte[] result = admin.obtenerReporte(referencia);

        ArchivoUtil.guardarEnCarpetaUsuario(result, "comprobates-empleados.txt");
        assertNotNull(result);
    }
}
