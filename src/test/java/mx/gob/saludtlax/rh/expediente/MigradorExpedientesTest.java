/*
 * MigradorExpedientesTest.java
 * Creado el Sep 6, 2016 1:06:59 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente;

import java.io.File;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacion;
import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacionEJB;
import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacionService;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleadoEJB;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleadoService;
import mx.gob.saludtlax.rh.expediente.exportacion.CantidadExpedienteDTO;
import mx.gob.saludtlax.rh.expediente.exportacion.ExpedientesProcessor;
import mx.gob.saludtlax.rh.expediente.exportacion.ExpedientesReader;
import mx.gob.saludtlax.rh.expediente.exportacion.ExpedientesWriter;
import mx.gob.saludtlax.rh.expediente.exportacion.Exportacion;
import mx.gob.saludtlax.rh.expediente.exportacion.ExportacionDTO;
import mx.gob.saludtlax.rh.expediente.exportacion.ExportacionEJB;
import mx.gob.saludtlax.rh.expediente.exportacion.ExportacionService;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.AdjuntosEmpleadosOldEntity;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.CentroPagoEntity;
import mx.gob.saludtlax.rh.persistencia.CluesEntity;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionRepository;
import mx.gob.saludtlax.rh.persistencia.DepartamentoEntity;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoEntity;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoRepository;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntosEmpleadosOldEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntosEmpleadosOldRepository;
import mx.gob.saludtlax.rh.persistencia.PaisEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.RamaEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoEmpleadoRepository;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class MigradorExpedientesTest {
    
    public MigradorExpedientesTest() {
    }
    
    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, 
                        ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/batch-jobs/expedientesExportacion.xml")
                ;
        
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addClass(GenericRepository.class)
                .addClass(Adjunto.class)
                .addClass(AdjuntoEmpleado.class)
                .addClass(AdjuntoEmpleadoEJB.class)
                .addClass(AdjuntoEmpleadoService.class)
                .addClass(AdjuntosEmpleadosOldEntity.class)
                .addClass(ArchivoUtil.class)
                .addClass(SubadscripcionEntity.class)
                .addClass(BusinessException.class)
                .addClass(CantidadExpedienteDTO.class)
                .addClass(CentroPagoEntity.class)
                .addClass(CluesEntity.class)
                .addClass(CodigoError.class)
                .addClass(ComprobanteEstudioEntity.class)
                .addClass(ConfiguracionAplicacion.class)
                .addClass(ConfiguracionAplicacionEJB.class)
                .addClass(ConfiguracionAplicacionEntity.class)
                .addClass(ConfiguracionAplicacionService.class)
                .addClass(ConfiguracionAplicacionRepository.class)
                .addClass(DepartamentoEntity.class)
                .addClass(DependienteEconomicoEntity.class)
                .addClass(DependienteEconomicoRepository.class)
                .addClass(DocumentoAdjuntableDTO.class)
                .addClass(DocumentoAdjuntableEntity.class)
                .addClass(DocumentoAdjuntableRepository.class)
                .addClass(EmpleadoEntity.class)
                .addClass(EntidadContexto.class)
                .addClass(EscolaridadEntity.class)
                .addClass(EscolaridadRepository.class)
                .addClass(ExpedienteEmpleadoEntity.class)
                .addClass(ExpedienteEmpleadoRepository.class)
                .addClass(ExpedientesProcessor.class)
                .addClass(ExpedientesReader.class)
                .addClass(ExpedientesWriter.class)
                .addClass(Exportacion.class)
                .addClass(ExportacionDTO.class)
                .addClass(ExportacionEJB.class)
                .addClass(ExportacionService.class)
                .addClass(HistorialAcademicoEntity.class)
                .addClass(HistorialAcademicoRepository.class)
                .addClass(InformacionAdjuntoDTO.class)
                .addClass(InformacionAdjuntoEmpleadoEntity.class)
                .addClass(InformacionAdjuntoEmpleadoRepository.class)
                .addClass(InformacionAdjuntosEmpleadosOldEntity.class)
                .addClass(InformacionAdjuntosEmpleadosOldRepository.class)
                .addClass(TipoArchivo.class)
                .addClass(TipoPuestoEntity.class)
                .addClass(ValidacionCodigoError.class)
                .addClass(ValidacionException.class)
                .addClass(VistaPreviaAdjuntoEmpleadoEntity.class)
                .addClass(VistaPreviaAdjuntoEmpleadoRepository.class)
                .addClass(PaisEntity.class)
                .addClass(PuestoGeneralEntity.class)
                .addClass(RamaEntity.class)
                .addClass(ReglaNegocioException.class)
                .addClass(ReglaNegocioCodigoError.class)
                .addClass(Repository.class)
                .addClass(SistemaCodigoError.class)
                .addClass(SistemaException.class)
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
    public void batchTest() {
        Logger logger = Logger.getLogger(MigradorExpedientesTest.class.getName());
        logger.info("Iniciando Test");
        JobOperator batch = BatchRuntime.getJobOperator();
        Properties properties = new Properties();
        properties.setProperty("posicion", "0");
        
        long executionID = batch.start("expedientesExportacion", properties);
        
        Assert.assertTrue(executionID != 0);
    }
}
