/*
 * SeguroPopularReporteTest.java
 * Creado el 09/Dec/2016 8:15:27 PM
 * 
 */
package mx.gob.saludtlax.rh.siif;

import java.io.File;
import java.io.IOException;
import mx.gob.saludtlax.rh.acumulados.AcumuladosDTO;
import mx.gob.saludtlax.rh.acumulados.ConceptoNominaAcumuladoDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ConsentradoAltaBajaExcelDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.MovimientoEmpleadoReporteService;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersRepository;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.ReporteParametroEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoEntity;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
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
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosPlantillaExcelDTO;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporte;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporteDTO;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporteEJB;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporteExcel;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporteService;
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
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class SeguroPopularReporteTest {

    private static final Logger LOGGER = Logger.getLogger(SeguroPopularReporteTest.class.getName());
    
    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource("log4j.properties");
        
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addClass(AdministradorReportes.class)
                .addClass(AlmacenReportesExcel.class)
                .addClass(ExcelReporte.class)
                .addClass(ExcelGenerador.class)
                .addClass(SeguroPopularReporte.class)
                .addClass(SeguroPopularReporteDTO.class)
                .addClass(SeguroPopularReporteEJB.class)
                .addClass(SeguroPopularReporteExcel.class)
                .addClass(SeguroPopularReporteService.class)
                .addClass(AlmacenReportes.class)
                .addClass(ArchivoUtil.class)
                .addClass(BitacoraReporte.class)
                .addClass(BitacoraReporteEntity.class)
                .addClass(BitacoraReporteEJB.class)
                .addClass(BitacoraReporteRepository.class)
                .addClass(Generador.class)
                .addClass(GenericRepository.class)
                .addClass(PerfilUsuarioEntity.class)
                .addClass(Reporte.class)
                .addClass(ReporteParametroEntity.class)
                .addClass(Repository.class)
                .addClass(SistemaException.class)
                .addClass(TipoArchivo.class)
                .addClass(UsuarioRepository.class)
                .addClass(UsuarioEntity.class)
                .addClass(ConsultaNominaService.class)
                .addClass(EstructuraNominaDatRepository.class)
                .addClass(EstructuraNominaTrailersRepository.class)
                .addClass(EstructuraNominaDatDTO.class)
                .addClass(EstructuraNominaTrailersDTO.class)
                .addClass(EstructuraNominaTrailersEntity.class)
                .addClass(EstructuraNominaDatEntity.class)
                .addClass(SIIFEncabezadoEntity.class)
                .addClass(TipoNominaEntity.class)
                .addClass(SiifBitacoraEntity.class)
                .addClass(CuentasBancariasEntity.class)
                .addClass(EstructuraContratosPlantillaExcelDTO.class)
                .addClass(ConceptoNominaAcumuladoDTO.class)
                .addClass(AcumuladosDTO.class)
                .addClass(MovimientoEmpleadoReporteService.class)
                .addClass(ConsentradoAltaBajaExcelDTO.class)
                .addClass(ComisionadoLicenciaExcelDTO.class)
                .addClass(ValidacionUtil.class)
                .addAsResource("plantillas/siif/plantilla--seguro-popular.xlsx", "plantillas/siif/plantilla--seguro-popular.xlsx")
                ;
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
            "REPORTE_NOMBRE", "seguro_popular",
            "TIPO_REPORTE",  "xlsx",
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
        String referencia = "5f306bb5-e4b0-4885-a4cc-3e1c0d4e";
        AdministradorReportes admin = new AdministradorReportes();
        byte[] result = admin.obtenerReporte(referencia);
        
        ArchivoUtil.guardarEnCarpetaUsuario(result, "seguro_popular.xlsx");
        assertNotNull(result);
    }
}
