/*
 *
 */

package mx.gob.saludtlax.rh.nombramientos;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.model.fields.merge.MailMerger.OutputField;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.util.NumeroUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)

 * @version 1.0
 * @since 17:29:26 12/09/2016
 */
@RunWith(Arquillian.class)
@Transactional
public class NombramientoEmpleadoTester {

    private static final Logger LOGGER = Logger.getLogger(NombramientoEmpleadoTester.class.getName());

    private static final String urlDoc = "plantillas/nombramiento/NOMBRAMIENTO_GENERICO.docx";
    //	private static final String membrete = "plantillas/nombramiento/plantilla_nombramiento.jpg";
    private static final String home = System.getProperty("user.home");

    // Valores de Word
    private static final String posicionUno = "Hago de su conocimiento que con motivo del Proceso de Escalafón en el que usted participó, se le comunica que a partir del 31 de enero de 2014, deja la función de Apoyo Administrativo en Salud-A5, con clave presupuestal I0024161103 M03021290040002 (federal); de acuerdo a lo anterior y con fundamento en los Artículos 90 del Reglamento de Escalafón y 17 de las Condiciones Generales de Trabajo de la Secretaría de Salud";
    private static final String nombreTipoNombramiento = "Nombramiento por Escalafon";
    private static final String nombreEmpleado = "Hernández León Julia";
    private static final String rfc = "HELJ5412288S4";
    private static final String curp = "HELJ541228MTLRNL00";
    private static final String edad = "61 años";
    private static final String nacionalidad = "Mexicana";
    private static final String sexo = "femenino";
    private static final String estadoCivil = "Soltera";
    private static final String domicilioEmpleado = "Avenida 12 de Octubre No. 6, Barrio de San Lucas, Huamantla, Tlaxcala";
    private static final String clavePresupuestal = "I0024161103 M02035 29004 0270";
    private static final String funcion = "Enfermera General Titulada 2ª";
    private static final String tipoNombramiento = "Base (Federal)";
    private static final String jornadaTrabajo = "8 horas";
    private static final String horarioTrabajo = "El que le asigne su Unidad";
    private static final BigDecimal sueldo = new BigDecimal("9468.78");
    private static final String lugarAdscripcion = "C.S. Chapultepec, Jurisdicción Sanitaria No. II";
    private static final String vigenciaFechaIngresoEmpleado = "1980-01-01";
    private static final String sustituye = "C. Ma. De Lourdes Vázquez Jiménez (por jubilación)";
    private static final String posicionDos = "Por lo que deberá presentarse con el Dr. José Gómez González, Director de la Unidad de referencia, quien le asignará su área, turno y horario de labores.Asimismo se le exhorta a cumplir de mejor forma en el desempeño de las tareas inherentes a su nuevo código funcional.";
    private static final String nombreSecretario = "DR. ALEJANDRO GUARNEROS CHUMACERO";

    @Deployment
    public static WebArchive createDeployment() {
        // Crear War
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        // Se añade las clases que se requieren
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml").addAsResource("plantillas/nombramiento/NOMBRAMIENTO_GENERICO.docx")
                //				.addAsResource("plantillas/nombramiento/plantilla_nombramiento.jpg")
                .addPackage("mx.gob.saludtlax.rh.util");

        // Se se agregar el jar al war
        war.addAsLibraries(jar);
        // Se cear el pom
        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        // El pom se le agrega al war
        war.addAsLibraries(files);

        return war;
    }

    @Before
    public void before() {
        LOGGER.info("Mensaje informativo: Corriendo before, iniciando test");

    }

    // @Ignore
    @Test
    public void creandoNombramientoWordTest() {

        try {

            InputStream url = NombramientoEmpleadoTester.class.getClassLoader().getResourceAsStream(urlDoc);
            //			InputStream urlMenbrete = NombramientoEmpleadoTester.class.getClassLoader().getResourceAsStream(membrete);

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(url);
            //			wordMLPackage = WordprocessingMLPackage.load(urlMenbrete);

            // Campos a combinar
            ArrayList<Map<DataFieldName, String>> listaRegitrosCombinar = new ArrayList<>();
            Map<DataFieldName, String> camposCombinar = new HashMap<>();

            DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG);

            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

            Date vigencia = null;

            vigencia = formatoDelTexto.parse(vigenciaFechaIngresoEmpleado);

            camposCombinar.put(new DataFieldName("posicionUno"), posicionUno);
            camposCombinar.put(new DataFieldName("nombreTipoNombramiento"), nombreTipoNombramiento);
            camposCombinar.put(new DataFieldName("nombreEmpleado"), nombreEmpleado);
            camposCombinar.put(new DataFieldName("rfc"), rfc);
            camposCombinar.put(new DataFieldName("curp"), curp);
            camposCombinar.put(new DataFieldName("edad"), edad);
            camposCombinar.put(new DataFieldName("nacionalidad"), nacionalidad);
            camposCombinar.put(new DataFieldName("sexo"), sexo);
            camposCombinar.put(new DataFieldName("estadoCivil"), estadoCivil);
            camposCombinar.put(new DataFieldName("domicilioEmpleado"), domicilioEmpleado);
            camposCombinar.put(new DataFieldName("clavePresupuestal"), clavePresupuestal);
            camposCombinar.put(new DataFieldName("funcion"), funcion);
            camposCombinar.put(new DataFieldName("tipoNombramiento"), tipoNombramiento);
            camposCombinar.put(new DataFieldName("jornadaTrabajo"), jornadaTrabajo);
            camposCombinar.put(new DataFieldName("horarioTrabajo"), horarioTrabajo);
            camposCombinar.put(new DataFieldName("sueldo"), "$" + NumeroUtil.formatBigDecimal(sueldo));
            camposCombinar.put(new DataFieldName("lugarAdscripcion"), lugarAdscripcion);
            camposCombinar.put(new DataFieldName("vigenciaFechaIngresoEmpleado"), formatoFecha.format(vigencia));
            camposCombinar.put(new DataFieldName("sustituye"), sustituye);
            camposCombinar.put(new DataFieldName("posicionDos"), posicionDos);
            camposCombinar.put(new DataFieldName("NOMBRESECRETARIO"), nombreSecretario);

            listaRegitrosCombinar.add(camposCombinar);

            MailMerger.setMERGEFIELDInOutput(OutputField.KEEP_MERGEFIELD);
            WordprocessingMLPackage output = MailMerger.getConsolidatedResultCrude(wordMLPackage, listaRegitrosCombinar, true);

            output.save(new java.io.File(home + "/NOMBRAMIENTO_DEFINITIVO.docx"));

        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            LOGGER.error("Mensaje crítico..." + nullPointerException.getMessage());
        } catch (Docx4JException docx4jException) {
            docx4jException.printStackTrace();
            LOGGER.error("Ocurrio un error al general el documento: " + docx4jException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error("Ocurrio un error al general el documento: " + exception.getMessage());
        }
    }

    @After
    public void after() {
        LOGGER.info("Mensaje informativo: Corriendo after, terminando test");
    }

}
