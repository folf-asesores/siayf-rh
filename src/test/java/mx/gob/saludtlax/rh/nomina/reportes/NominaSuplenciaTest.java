/*
 * NominaSuplenciaTest.java
 * Creado el 04/Dec/2016 9:41:57 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

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

import mx.gob.saludtlax.rh.excepciones.SistemaException;
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
import mx.gob.saludtlax.rh.reportes.jasperreports.AlmacenReportesJasperReports;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReporte;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReportsCompilador;
import mx.gob.saludtlax.rh.reportes.jasperreports.JasperReportsGenerador;
import mx.gob.saludtlax.rh.reportes.jasperreports.NumeroALetraScriptlet;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class NominaSuplenciaTest {

    private static final Logger LOGGER = Logger.getLogger(NominaSuplenciaTest.class.getName());

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource("log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml").addClass(AdministradorReportes.class).addClass(AlmacenReportes.class)
                .addClass(AlmacenReportesJasperReports.class).addClass(ArchivoUtil.class).addClass(BitacoraReporte.class).addClass(BitacoraReporteEntity.class)
                .addClass(BitacoraReporteEJB.class).addClass(BitacoraReporteRepository.class).addClass(Generador.class).addClass(GenericRepository.class)
                .addClass(JasperReporte.class).addClass(JasperReportsCompilador.class).addClass(JasperReportsGenerador.class)
                .addClass(PerfilUsuarioEntity.class).addClass(Reporte.class).addClass(ReporteParametroEntity.class).addClass(Repository.class)
                .addClass(SistemaException.class).addClass(TipoArchivo.class).addClass(UsuarioRepository.class).addClass(UsuarioEntity.class)
                .addClass(NumeroALetraScriptlet.class).addClass(NumeroALetra.class).addAsResource("reportes/nomina_suplencias.jrxml")
                .addAsResource("reportes/nomina_suplencias--percepciones.jrxml")
                .addAsResource("reportes/nomina_suplencias--percepciones-centro-responsabilidad.jrxml")
                .addAsResource("reportes/nomina_suplencias--percepciones-total.jrxml").addAsResource("reportes/nomina_suplencias--deducciones.jrxml")
                .addAsResource("reportes/nomina_suplencias--deducciones-centro-responsabilidad.jrxml")
                .addAsResource("reportes/nomina_suplencias--deducciones-total.jrxml");
        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void obtenerReferencia() {
        LOGGER.info("Iniciando test obtenerReferencia");
        String[] parametros = new String[] { "ID_USUARIO", "18", "REPORTE_NOMBRE", "nomina_suplencias", "TIPO_REPORTE", "txt", "ID_PRODUCTO_NOMINA", "1" };
        AdministradorReportes admin = new AdministradorReportes();
        String referencia = admin.obtenerReferencia(parametros);
        LOGGER.infov("Referencia: {0}", referencia);
        assertNotNull(referencia);
    }

    @Ignore
    @Test
    public void obtenerReporte() throws IOException {
        LOGGER.info("Iniciando test obtenerReporte");
        String referencia = "2233869f-b41a-4bc1-bbd6-eb96ebd5";
        AdministradorReportes admin = new AdministradorReportes();
        byte[] result = admin.obtenerReporte(referencia);

        ArchivoUtil.guardarEnCarpetaUsuario(result, "nomina-suplencias.txt");
        assertNotNull(result);
    }
}
