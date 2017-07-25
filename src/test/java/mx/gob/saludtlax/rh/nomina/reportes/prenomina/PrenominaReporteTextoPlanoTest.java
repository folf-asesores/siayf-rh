/*
 * PrenominaReporteTextoPlanoTest.java
 * Creado el 27/Mar/2017 10:45:10 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
 * @author Freddy Barrera (freddy.barrera@gmail.com)
 */
@RunWith(Arquillian.class)
public class PrenominaReporteTextoPlanoTest {

    @Inject
    private PrenominaReporteService prenominaReporteEJB;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource("META-INF/beans.xml", "beans.xml");
        jar.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
        jar.addClass(PrenominaDTO.class);
        jar.addClass(PrenominaReporteTextoPlano.class);
        jar.addClass(PrenominaReporteTextoPlanoTools.class);
        jar.addClass(PrenominaReporteService.class);
        jar.addClass(DeduccionDTO.class);
        jar.addClass(PercepcionDTO.class);
        jar.addClass(NominaEmpleadoDTO.class);
        jar.addClass(ProductoNominaDTO.class);
        jar.addClass(ProductoNominaDTOBuilder.class);
        jar.addClass(ProgramaDTO.class);
        jar.addClass(ProgramaDTOBuilder.class);
        jar.addClass(UnidadResponsableDTO.class);
        jar.addClass(UnidadResponsableDTOBuilder.class);
        jar.addClass(Configuracion.class);
        jar.addClass(ArchivoUtil.class);
        jar.addClass(NumeroALetra.class);
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
    public void generarReporteEjb() throws IOException {
        ProductoNominaDTO productoNomina = prenominaReporteEJB.obtenerProductoNomina(32);

        PrenominaReporteTextoPlano reporteTextoPlano = new PrenominaReporteTextoPlano();
        byte[] reporte = reporteTextoPlano.generar(productoNomina);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "prenomina.txt");
        Assert.assertNotNull(productoNomina);
    }

    @Ignore
    @Test
    public void generarReporte() throws IOException {
        // Percepciones y deducciones
        Map<String, PercepcionDTO> percepciones1 = new HashMap<>();
        percepciones1.put("05", new PercepcionDTO("05", "SUPLENCIAS", new BigDecimal("2180")));
        Map<String, PercepcionDTO> percepciones2 = new HashMap<>();
        percepciones2.put("05", new PercepcionDTO("05", "SUPLENCIAS", new BigDecimal("2180")));
        percepciones2.put("26", new PercepcionDTO("26", "SUBSIDIO", new BigDecimal("55.5")));
        Map<String, PercepcionDTO> percepciones3 = new HashMap<>();
        percepciones3.put("05", new PercepcionDTO("05", "SUPLENCIAS", new BigDecimal("2180")));
        percepciones3.put("26", new PercepcionDTO("26", "SUBSIDIO", new BigDecimal("55.5")));
        percepciones3.put("32", new PercepcionDTO("32", "OTROS", new BigDecimal("3300.00")));
        Map<String, DeduccionDTO> deducciones1 = new HashMap<>();
        deducciones1.put("52", new DeduccionDTO("52", "I.S.R.", new BigDecimal("342.5")));
        Map<String, DeduccionDTO> deducciones2 = new HashMap<>();
        deducciones2.put("51", new DeduccionDTO("51", "FALTAS Y RETARDOS", new BigDecimal("34.415")));
        deducciones2.put("52", new DeduccionDTO("52", "I.S.R.", new BigDecimal("342.5")));
        Map<String, DeduccionDTO> deducciones3 = new HashMap<>();
        deducciones3.put("51", new DeduccionDTO("51", "FALTAS Y RETARDOS", new BigDecimal("34.415")));
        deducciones3.put("52", new DeduccionDTO("52", "I.S.R.", new BigDecimal("342.5")));
        deducciones3.put("62", new DeduccionDTO("62", "PENSION ALIMENTICIA", new BigDecimal("3516")));

        // Nomina empleados
        NominaEmpleadoDTO nominaCaporal = new NominaEmpleadoDTO();
        nominaCaporal.setRfc("CAME750818R3A");
        nominaCaporal.setNombre("CAPORAL MENDIETA MA ELENA");
        nominaCaporal.setPercepciones(percepciones1);
        nominaCaporal.setDeducciones(deducciones1);

        NominaEmpleadoDTO nominaCordero = new NominaEmpleadoDTO();
        nominaCordero.setRfc("COGO770228MU5");
        nominaCordero.setNombre("CORDERO GONZALEZ OSVALDO");
        nominaCordero.setPercepciones(percepciones2);
        nominaCordero.setDeducciones(deducciones2);

        NominaEmpleadoDTO nominaContreras = new NominaEmpleadoDTO();
        nominaContreras.setRfc("COHS910628CD4");
        nominaContreras.setNombre("CONTRERAS HERNANDEZ SANDRA NELY");
        nominaContreras.setPercepciones(percepciones3);
        nominaContreras.setDeducciones(deducciones3);

        NominaEmpleadoDTO nominaCuecuecha = new NominaEmpleadoDTO();
        nominaCuecuecha.setRfc("CUMM8702236M7");
        nominaCuecuecha.setNombre("CUECUECHA MENDIETA MARTHA PAOLA");
        nominaCuecuecha.setPercepciones(percepciones2);
        nominaCuecuecha.setDeducciones(deducciones2);

        NominaEmpleadoDTO nominaCuecuecha2 = new NominaEmpleadoDTO();
        nominaCuecuecha2.setRfc("CUMY910419HI3");
        nominaCuecuecha2.setNombre("CUECUECHA MENDOZA YANET");
        nominaCuecuecha2.setPercepciones(percepciones1);
        nominaCuecuecha2.setDeducciones(deducciones1);

        NominaEmpleadoDTO nominaCuamatzi = new NominaEmpleadoDTO();
        nominaCuamatzi.setRfc("CUSE890829LS7");
        nominaCuamatzi.setNombre("CUAMATZI SANCHEZ EDUARDO");
        nominaCuamatzi.setPercepciones(percepciones3);
        nominaCuamatzi.setDeducciones(deducciones3);

        NominaEmpleadoDTO nominaCuapio = new NominaEmpleadoDTO();
        nominaCuapio.setRfc("CUSJ870703AG6");
        nominaCuapio.setNombre("CUAPIO SANCHEZ JAVIER");
        nominaCuapio.setPercepciones(percepciones2);

        NominaEmpleadoDTO nominaCuahutecatl = new NominaEmpleadoDTO();
        nominaCuahutecatl.setRfc("CUTJ8809095A0");
        nominaCuahutecatl.setNombre("CUAHUTECATL TETLACUILO JEMMY");
        nominaCuahutecatl.setPercepciones(percepciones3);
        nominaCuahutecatl.setDeducciones(deducciones3);

        NominaEmpleadoDTO nominaDelgado = new NominaEmpleadoDTO();
        nominaDelgado.setRfc("DESR741226269");
        nominaDelgado.setNombre("DELGADO SANTIAGO RUT");
        nominaDelgado.setDeducciones(deducciones1);

        NominaEmpleadoDTO nominaDominguez = new NominaEmpleadoDTO();
        nominaDominguez.setRfc("DOHE860510UT7");
        nominaDominguez.setNombre("DOMINGUEZ HERNANDEZ ELMAR ARMANDO");
        nominaDominguez.setPercepciones(percepciones3);

        Map<String, NominaEmpleadoDTO> nominasEmpleados = new HashMap<>();
        nominasEmpleados.put("CAME750818R3A", nominaCaporal);
        nominasEmpleados.put("COGO770228MU5", nominaCordero);
        nominasEmpleados.put("COHS910628CD4", nominaContreras);
        nominasEmpleados.put("CUMM8702236M7", nominaCuecuecha);
        nominasEmpleados.put("CUMY910419HI3", nominaCuecuecha2);
        nominasEmpleados.put("CUSE890829LS7", nominaCuamatzi);
        nominasEmpleados.put("CUSJ870703AG6", nominaCuapio);
        nominasEmpleados.put("CUTJ8809095A0", nominaCuahutecatl);
        nominasEmpleados.put("DESR741226269", nominaDelgado);
        nominasEmpleados.put("DOHE860510UT7", nominaDominguez);

        // Unidades responsables
        UnidadResponsableDTOBuilder unidadResponsableBuilder = new UnidadResponsableDTOBuilder("1300", "SUBDIRECCIÓN DE PRIMER NIVEL");
        unidadResponsableBuilder.setNominasEmpleados(nominasEmpleados);
        UnidadResponsableDTO unidadResponsable = unidadResponsableBuilder.createUnidadResponsableDTO();

        Map<String, UnidadResponsableDTO> unidadesResponsables = new HashMap<>();
        unidadesResponsables.put("1300", unidadResponsable);

        // Programas
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 5);
        ProgramaDTOBuilder programaBuilder = new ProgramaDTOBuilder(1, "ATENCIÓN A LA SALUD", cal.getTime(), Calendar.getInstance().getTime());
        programaBuilder.setUnidadesResponsables(unidadesResponsables);
        ProgramaDTO programa = programaBuilder.createProgramaDTO();

        Map<Integer, ProgramaDTO> programas = new HashMap<>();
        programas.put(1, programa);

        // Producto de nómina
        ProductoNominaDTOBuilder productoNominaBuilder = new ProductoNominaDTOBuilder(30, Calendar.getInstance().getTime(), programas);
        productoNominaBuilder.setNombreElaboro("LIC. VICTOR JOSE LEAL CRUZ");
        productoNominaBuilder.setCargoElaboro("JEFE DE DEPTO. DE RECURSOS HUMANOS");

        productoNominaBuilder.setNombreReviso("C.P. LUZ MARIA PORTILLA GARCIA");
        productoNominaBuilder.setCargoReviso("DIRECTOR  DE ADMINISTRACION");

        productoNominaBuilder.setNombreAutorizo("DR. ALEJANDRO GUARNEROS CHUMACERO");
        productoNominaBuilder.setCargoAutorizo("DIRECTOR GENERAL DE SALUD DE TLAXCALA");

        ProductoNominaDTO productoNomina = productoNominaBuilder.createProductoNominaDTO();

        PrenominaReporteTextoPlano reporteTextoPlano = new PrenominaReporteTextoPlano();
        byte[] reporte = reporteTextoPlano.generar(productoNomina);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "prenomina.txt");
    }

}
