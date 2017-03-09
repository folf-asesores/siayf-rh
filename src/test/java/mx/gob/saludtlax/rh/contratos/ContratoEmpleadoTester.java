/**
 * 
 */
package mx.gob.saludtlax.rh.contratos;

import java.io.File;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.model.fields.merge.MailMerger.OutputField;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.NumeroUtil;

/**
 * @author eduardo Mex
 *
 */
@RunWith(Arquillian.class)
@Transactional
public class ContratoEmpleadoTester {

	private static final Logger LOGGER = Logger.getLogger(ContratoEmpleadoTester.class.getName());

	private static final String urlDoc = "plantillas/contrato/CONTRATO_INDIVIDUAL.docx";
	private static final String home = System.getProperty("user.home");

	// Valores de Word
	private static final String nombreTrabajador = "DRA. MARIBEL CAMACHO GOMEZ";
	private static final String fechaInicio = "2016-01-01";
	private static final String fechaFin = "2016-12-31";
	private static final String puestoGeneral = "Médico Especialista (Pediatra)";
	private static final String domicilioServicio = "Prolongación Hidalgo Pueblo de la Cruz, Huamantla, Tlaxcala";
	private static final BigDecimal sueldoMensual = new BigDecimal("9468.78");

	@Deployment
	public static WebArchive createDeployment() {
		// Crear War
		WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE,
				ArchivePaths.create("beans.xml"));
		// Se añade las clases que se requieren
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addAsManifestResource("META-INF/beans.xml", "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
				.addAsResource("plantillas/contrato/CONTRATO_INDIVIDUAL.docx").addPackage("mx.gob.saludtlax.rh.util")
		// .addAsDirectories(home+"\\Documentos\\CONTRATO_MEDICO_2016.docx")
		;

		// Se se agregar el jar al war
		war.addAsLibraries(jar);
		// Se cear el pom
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
				.withTransitivity().asFile();
		// El pom se le agrega al war
		war.addAsLibraries(files);

		return war;
	}

	@Before
	public void before() {
		LOGGER.info("Mensaje informativo: Corriendo before, iniciando test");

	}

	@Ignore
	@Test
	public void creandoContratoEmpleadoWordTest() {
		// File templeateContrato = new File(context.getRealPath(urlDoc));

		try {

			// String doc =
			// "\\home\\eduardo\\Documentos\\CONTRATO_MEDICO_2016.docx";

			// WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
			// .load(new File(urlDoc));
			//
			InputStream url = ContratoEmpleadoTester.class.getClassLoader().getResourceAsStream(urlDoc);

			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(url);

			// Campos a combinar
			ArrayList<Map<DataFieldName, String>> listaRegitrosCombinar = new ArrayList<Map<DataFieldName, String>>();
			Map<DataFieldName, String> camposCombinar = new HashMap<DataFieldName, String>();

			DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG);

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

			Date fechaInit = null;
			Date fechaEnd = null;
			Date fechaInit2 = null;
			Date fechaEnd2 = null;
			Date fechaInit3 = null;
			Date fechaInit4 = null;

			fechaInit = formatoDelTexto.parse(fechaInicio);
			fechaEnd = formatoDelTexto.parse(fechaFin);

			fechaInit2 = formatoDelTexto.parse(fechaInicio);
			fechaEnd2 = formatoDelTexto.parse(fechaFin);

			fechaInit3 = formatoDelTexto.parse(fechaInicio);
			fechaInit4 = formatoDelTexto.parse(fechaInicio);

			camposCombinar.put(new DataFieldName("nombreTrabajador"), nombreTrabajador);
			camposCombinar.put(new DataFieldName("fechaInicio"), formatoFecha.format(fechaInit));
			camposCombinar.put(new DataFieldName("fechaFin"), formatoFecha.format(fechaEnd));
			camposCombinar.put(new DataFieldName("fechaInicio2"), formatoFecha.format(fechaInit2));
			camposCombinar.put(new DataFieldName("fechaFin2"), formatoFecha.format(fechaEnd2));
			camposCombinar.put(new DataFieldName("puestoGeneral"), puestoGeneral);
			camposCombinar.put(new DataFieldName("domicilioServicio"), domicilioServicio);
			camposCombinar.put(new DataFieldName("sueldoMensualNumero"),
					"$" + NumeroUtil.formatBigDecimal(sueldoMensual));
			camposCombinar.put(new DataFieldName("sueldoMensualLetra"), NumeroALetra.convertir(sueldoMensual));
			camposCombinar.put(new DataFieldName("sueldoMensualNumero2"),
					"$" + NumeroUtil.formatBigDecimal(sueldoMensual));
			camposCombinar.put(new DataFieldName("sueldoMensualLetra2"), NumeroALetra.convertir(sueldoMensual));
			camposCombinar.put(new DataFieldName("fechaInicio3"), formatoFecha.format(fechaInit3));
			camposCombinar.put(new DataFieldName("fechaInicio4"), formatoFecha.format(fechaInit4));
			camposCombinar.put(new DataFieldName("nombreTrabajadorFirma"), nombreTrabajador);

			listaRegitrosCombinar.add(camposCombinar);

			// ByteArrayOutputStream documentoCombinado = new
			// ByteArrayOutputStream();

			MailMerger.setMERGEFIELDInOutput(OutputField.KEEP_MERGEFIELD);
			WordprocessingMLPackage output = MailMerger.getConsolidatedResultCrude(wordMLPackage, listaRegitrosCombinar,
					true);

			output.save(new java.io.File(home + "/CONTRATO_INDIVIDUAL_DE_TRABAJO_POR_TIEMPO_DETERMINADO.docx"));

			// return documentoCombinado;
		} catch (NullPointerException nullPointerException) {
			nullPointerException.printStackTrace();
			LOGGER.severe("Mensaje crítico..." + nullPointerException.getMessage());
		} catch (Docx4JException docx4jException) {
			docx4jException.printStackTrace();
			LOGGER.severe("Ocurrio un error al general el documento: " + docx4jException.getMessage());
		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.severe("Ocurrio un error al general el documento: " + exception.getMessage());
		}
	}

	@After
	public void after() {
		LOGGER.info("Mensaje informativo: Corriendo after, terminando test");
	}

}
