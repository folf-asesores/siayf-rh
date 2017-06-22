/*
 * AperturaNominaRfcTest.java
 * Creado el 01/Jan/2017 1:12:46 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.InfoSuplenciaDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.SuplenciasQuincenaDTO;
import mx.gob.saludtlax.rh.excepciones.CodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.BancoSatEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalEntity;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusProductoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoOPDEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.FuncionEntity;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PaisEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilUsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosEntity;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosRepository;
import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoEntity;
import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.persistencia.RamaEntity;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.persistencia.SubclasificacionTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.SuplenteAutorizadoEntity;
import mx.gob.saludtlax.rh.persistencia.TabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoTabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.TiposRecursosSatEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@RunWith(Arquillian.class)
public class AperturaNominaRfcTest {

    private static final Logger LOGGER = Logger.getLogger(AperturaNominaRfcTest.class.getName());
    
    @Inject
    private AperturaNominaRfc aperturaNominaRfc;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("logging.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(AperturaNominaRfc.class);
        jar.addClass(AperturaNominaRfcEJB.class);
        jar.addClass(AperturaNominaRfcService.class);
        jar.addClass(ArchivoUtil.class);
        jar.addClass(BancoSatEntity.class);
        jar.addClass(CentroResponsabilidadEntity.class);
        jar.addClass(ConceptosNominaEmpleadosDTO.class);
        jar.addClass(ConfiguracionPresupuestoEntity.class);
        jar.addClass(DatoGeneralCandidatoDTO.class);
        jar.addClass(DependenciaTempEntity.class);
        jar.addClass(EjercicioFiscalEntity.class);
        jar.addClass(EjercicioFiscalRepository.class);
        jar.addClass(EstatusConfiguracionesEntity.class);
        jar.addClass(EstatusNominasEmpleadoEntity.class);
        jar.addClass(EstatusNominasEmpleadoRepository.class);
        jar.addClass(CuentasBancariasEntity.class);
        jar.addClass(CodigoError.class);
        jar.addClass(EstatusProductoNominaDTO.class);
        jar.addClass(EstatusProductoNominaEntity.class);
        jar.addClass(EstatusProductoNominaRepository.class);
        jar.addClass(EmpleadoEntity.class);
        jar.addClass(EmpleadoRepository.class);
        jar.addClass(FuncionEntity.class);
        jar.addClass(FuenteFinanciamientoEntity.class);
        jar.addClass(FuenteFinanciamientoOPDEntity.class);
        jar.addClass(FuenteFinanciamientoRepository.class);
        jar.addClass(GenericRepository.class);
        jar.addClass(InfoEmpleadoDTO.class);
        jar.addClass(InfoSuplenciaDTO.class);
        jar.addClass(InfoVacantePostularDTO.class);
        jar.addClass(NominaAcumuladoDTO.class);
        jar.addClass(NominaErroneaDTO.class);
        jar.addClass(NominaEmpleadoDTO.class);
        jar.addClass(NominaEmpleadoEntity.class);
        jar.addClass(NominaEmpleadoRepository.class);
        jar.addClass(PaisEntity.class);
        jar.addClass(PerfilUsuarioEntity.class);
        jar.addClass(PeriodoCalendariosEntity.class);
        jar.addClass(PeriodoCalendariosRepository.class);
        jar.addClass(ProcesoCalculoEntity.class);
        jar.addClass(ProcesoCalculoRepository.class);
        jar.addClass(ProductoNominaDTO.class);
        jar.addClass(ProductoNominaEntity.class);
        jar.addClass(ProductoNominaFiltroDTO.class);
        jar.addClass(ProductoNominaListaDTO.class);
        jar.addClass(ProductoNominaRepository.class);
        jar.addClass(ProductosNominaService.class);
        jar.addClass(ProgramaEntity.class);
        jar.addClass(ProyectoTempEntity.class);
        jar.addClass(PuestoGeneralEntity.class);
        jar.addClass(QuincenasSuplenciasEntity.class);
        jar.addClass(QuincenasSuplenciasRepository.class);
        jar.addClass(RamaEntity.class);
        jar.addClass(SistemaException.class);
        jar.addClass(SubclasificacionTabuladorEntity.class);
     //   jar.addClass(SubfuenteFinanciamientoEntity.class);
        jar.addClass(SubFuenteFinanciamientoTempEntity.class);
        jar.addClass(SubFuenteFinanciamientoTempRepository.class);
        jar.addClass(SuplenciasQuincenaDTO.class);
        jar.addClass(SuplenteAutorizadoEntity.class);
        jar.addClass(TabuladorEntity.class);
        jar.addClass(TipoArchivo.class);
        jar.addClass(TipoContratacionDTO.class);
        jar.addClass(TipoContratacionEntity.class);
        jar.addClass(TipoContratacionRepository.class);
        jar.addClass(TipoEmpleadoEntity.class);
        jar.addClass(TipoNominaEntity.class);
        jar.addClass(TipoNominaRepository.class);
        jar.addClass(TipoPeriodoEntity.class);
        jar.addClass(TipoPeriodoRepository.class);
        jar.addClass(TipoPuestoEntity.class);
        jar.addClass(TipoTabuladorEntity.class);
        jar.addClass(TipoRecursoTempEntity.class);
        jar.addClass(TiposNombramientosEntity.class);
        jar.addClass(TiposNombramientosRepository.class);
        jar.addClass(TiposRecursosSatEntity.class);
        jar.addClass(UsuarioEntity.class);
        jar.addClass(UsuarioRepository.class);
        jar.addClass(UnidadResponsableEntity.class);
        jar.addClass(Repository.class);
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
     public void testAbrirProductoNomina() throws IOException {
        LOGGER.info("Iniciando testAbrirProductoNomina");
        byte[] archivoRfc = ArchivoUtil.leerArchivo(null, "rfc-qna--22.txt", true);
        aperturaNominaRfc.abrirProductoNomina(2, archivoRfc);
    }

    @Ignore
    @Test
    public void leerRfcDesdeArchivo() throws IOException {
        byte[] archivoRfc = ArchivoUtil.leerArchivo(null, "rfc-qna--22.txt", true);
        List<String> listaRfc = new ArrayList<>();
        int linea = 1;

        try(ByteArrayInputStream bis = new ByteArrayInputStream(archivoRfc); 
                BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {
            String rfc;

            while((rfc = br.readLine()) != null) {
                if(ValidacionUtil.validarRfc(rfc)) {
                    listaRfc.add(rfc);
                } else {
                    System.out.printf("El RFC \"%s\" no es valido, en la línea %d.%n", rfc, linea);
                }
                linea++;
            }
        }
        
        int cantidad = 1;

        for(String rfc : listaRfc) {
            System.out.printf("%d : %s%n", cantidad, rfc);
            cantidad++;
        }
        
        Assert.assertNotNull(listaRfc);
    }
}
