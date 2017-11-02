/*
 * AdjuntoTester.java
 * Creado el May 12, 2016 10:51:04 AM
 * 
 */
package mx.gob.saludtlax.rh.expediente.aspirante;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
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

import mx.gob.saludtlax.rh.expediente.Adjunto;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.CentroPagoEntity;
import mx.gob.saludtlax.rh.persistencia.CluesEntity;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionRepository;
import mx.gob.saludtlax.rh.persistencia.DepartamentoEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.PaisEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.RamaEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoAspiranteRepository;
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


/**
* @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
@RunWith(Arquillian.class)
public class AdjuntoTester {

    @Inject private AdjuntoAspirante adjuntoEJB;
    
    private static final int ID_ADJUNTO_PROBAR = 4;
    private static final int ID_EXPEDIENTE_A_PROBAR = 1;
    private static final int ID_ASPIRANTE_PROBAR = 1;

    private static final Logger LOGGER = Logger.getLogger(AdjuntoTester.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addClass(GenericRepository.class)
                .addClass(ArchivoUtil.class)
                .addClass(SubadscripcionEntity.class)
                .addClass(Adjunto.class)
                .addClass(AdjuntoAspirante.class)
                .addClass(AdjuntoAspiranteEJB.class)
                .addClass(AdjuntoAspiranteService.class)
                .addClass(AspiranteEntity.class)
                .addClass(AspiranteRepository.class)
                .addClass(BusinessException.class)
                .addClass(CentroPagoEntity.class)
                .addClass(CluesEntity.class)
                .addClass(CodigoError.class)
                .addClass(ComprobanteEstudioEntity.class)
                .addClass(ConfiguracionAplicacion.class)
                .addClass(ConfiguracionAplicacionEJB.class)
                .addClass(ConfiguracionAplicacionEntity.class)
                .addClass(ConfiguracionAplicacionRepository.class)
                .addClass(ConfiguracionAplicacionService.class)
                .addClass(DepartamentoEntity.class)
                .addClass(DocumentoAdjuntableDTO.class)
                .addClass(DocumentoAdjuntableEntity.class)
                .addClass(DocumentoAdjuntableRepository.class)
                .addClass(EmpleadoEntity.class)
                .addClass(EntidadContexto.class)
                .addClass(EscolaridadEntity.class)
                .addClass(EscolaridadRepository.class)
                .addClass(ExpedienteAspiranteEntity.class)
                .addClass(ExpedienteAspiranteRepository.class)
                .addClass(HistorialAcademicoEntity.class)
                .addClass(InformacionAdjuntoDTO.class)
                .addClass(InformacionAdjuntoAspiranteEntity.class)
                .addClass(InformacionAdjuntoAspiranteRepository.class)
                .addClass(RamaEntity.class)
                .addClass(ReglaNegocioCodigoError.class)
                .addClass(ReglaNegocioException.class)
                .addClass(Repository.class)
                .addClass(PaisEntity.class)
                .addClass(PerfilUsuarioEntity.class)
                .addClass(PuestoGeneralEntity.class)
                .addClass(SistemaException.class)
                .addClass(SistemaCodigoError.class)
                .addClass(TipoArchivo.class)
                .addClass(TipoPuestoEntity.class)
                .addClass(UsuarioEntity.class)
                .addClass(ValidacionCodigoError.class)
                .addClass(ValidacionException.class)
                .addClass(ValidacionUtil.class)
                .addClass(VistaPreviaAdjuntoAspiranteEntity.class)
                .addClass(VistaPreviaAdjuntoAspiranteRepository.class)
                .addAsResource("plantillas/pdf/adjunto--test.pdf")
                .addAsResource("imagenes/acta_de_nacimiento.jpg")
                .addAsResource("imagenes/tif-icon.png")
                .addAsResource("imagenes/1.tif")
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
    public void obtenerInformacionAdjuntoPorIdAdjunto() {
        InformacionAdjuntoDTO resultado = adjuntoEJB.obtenerInformacionAdjuntoPorIdAdjunto(ID_ADJUNTO_PROBAR);
        
        assertNotNull(resultado);
    }

    @Ignore
    @Test
    public void obtenerInformacionAduntosPor() {
        List<InformacionAdjuntoDTO> listaDTOs = adjuntoEJB
                .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(EntidadContexto.EXPERIENCIA_LABORAL, 1, 1);
        
        //assertNotNull(listaDTOs);
        assertTrue(listaDTOs.size() == 1);
    }
    
    @Ignore
    @Test
    public void obtenerAduntosDelAspirante() {
    	List<InformacionAdjuntoDTO> listaDTOs = adjuntoEJB.consultarInformacionAdjuntosPorIdAspirante(ID_ASPIRANTE_PROBAR);
    	
        //assertNotNull(listaDTOs);
    	assertFalse(listaDTOs.isEmpty());
    }

    @Ignore
    @Test
    public void obtenerAdjunto() {
        byte[] bytes = adjuntoEJB.obtenerAdjuntoPorIdAdjunto(ID_ADJUNTO_PROBAR);
        assertNotNull(bytes);
    }
    
    @Ignore
    @Test
    public void obtenerVistaPreviaPorIdAdjunto() {
        byte[] bytes = adjuntoEJB.obtenerVistaPreviaPorIdAdjunto(ID_ADJUNTO_PROBAR);
        assertNotNull(bytes);
    }

    @Ignore
    @Test
    public void guardar() throws IOException {
    	byte[] adjunto = getBytesFromFile("imagenes/acta_de_nacimiento.jpg");        
        InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();
        
        info.setIdAspirante(1);
        info.setEntidadContexto(EntidadContexto.EMPLEADO);
        info.setIdEntidadContexto(1);
        info.setIdAdjunto(null);
        info.setNombreAdjunto("acta_de_nacimiento.jpg");
        info.setExtension(TipoArchivo.JPG);
        info.setDocumentoAdjuntable(new DocumentoAdjuntableDTO(2));
        //info.setIdExpediente(ID_EXPEDIENTE_A_PROBAR);
        info.setIdExpediente(2);
        
        int id = adjuntoEJB.crear(info, adjunto);
        
        assertTrue(id > 0);
    }
    
    @Ignore
    @Test
    public void actualizar() throws IOException{
    	byte[] data = getBytesFromFile("imagenes/1.tif");
    	
    	Map<String, Object> parametros = new HashMap<>();
    	parametros.put("idAdjunto", ID_ADJUNTO_PROBAR);
    	parametros.put("adjunto", data);
    	parametros.put("nombreAdjunto", "portada.tif");
    	parametros.put("extension", TipoArchivo.TIF);
    	
    	adjuntoEJB.actualizar(parametros);
    }

    @Ignore
    @Test
    public void eliminar(){
    	adjuntoEJB.elimnar(ID_ADJUNTO_PROBAR);
    }

    private byte[] getBytesFromFile(String archivo) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(archivo);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            
            int nRead;
            byte[] data = new byte[16384];
            
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            
            buffer.flush();
            
            return buffer.toByteArray();
        } catch (IOException ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }
}
