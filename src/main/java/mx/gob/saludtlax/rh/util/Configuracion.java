package mx.gob.saludtlax.rh.util;

/**
 * Contantes de configuracion general de la aplicacion.
 * 
 * @author Juan Carlos Ivan Ganzo Dominguez.
 *
 */
public class Configuracion {

	public static final String CONTROL_ASISTENCIA_RS_HOST = "http://192.168.0.17:8080";
	public static final String CONTROL_ASISTENCIA_RS_CONTEXT = "/controlasistencia-rs";
	public static final String DFACURA_RS_HOST_TEST = "http://timbradotest.dfacture.com:3002/api/timbrarCFDI";
	public static final String DFACURA_RS_HOST_PRODUCTIVO = "http://timbradobalancer.dfacture.com/api/timbrarCFDI";

	public static final String USUARIO_DFACTURA = "SaludTlax";
	public static final String PASSWORD_DFACTURA = "Stlaxcala.2016";

//	public static final String USUARIO_DFACTURA = "DEMOMomapa";
//	public static final String PASSWORD_DFACTURA = "cfdi";

	public static final String LOGGER_CONTROL_ASISTENCIA = "mx.gob.saludtlax.rh.rest.cliente.ca";
	public static final String LOGGER_API_NOMINA = "mx.gob.saludtlax.rh.api.nomina";
	public static final String LOGGER_TIMBRADO = "mx.gob.saludtlax.rh.rest.dfacture.timbrado";
	public static final String PATH_XML_TIMBRADO = "C:\\ArchivosXSLT\\xmlTimbrado\\";
	
	public static final String UNIDAD_PERSISTENCIA = "siayfrhPU";
	public static final String UNIDAD_PERSISTENCIA_ESPEJO = "siayfrhespPU";
	public static final String DATASOURCE = "java:jboss/datasources/QaDS";
	public static final String DATASOURCE_ESPEJO = "java:jboss/datasources/QaDS";
	
	private Configuracion() {}
	
	
}
