/*
 * PagoGeneralReporteService.java
 * Creado el 15/Feb/2017 5:30:39 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
//import mx.gob.saludtlax.rh.util.Configuracion;
import org.jboss.logging.Logger;
/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class PagoGeneralReporteService {

//    @Resource(mappedName = Configuracion.DATASOURCE_ESPEJO)
    @Resource(mappedName = "java:jboss/datasources/SIAYFRHDS")
    private DataSource ds; 
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralReporteService.class.getName());
    private static final String USP_PAGO_GENERAL = "CALL usp_reporte_pago_general(?)";
    private static final String CONSULTA_DESCRIPCION = 
            "  SELECT cn.descripcion"
            + "  FROM conceptos_nominas_contratos AS cn" 
            + " WHERE cn.clave = ?";
    private static final Map<String, String> TITULO;

    static {
        TITULO  = new HashMap<>();
        TITULO.put("programa","PROGRAMA");
        TITULO.put("noConsecutivoRegistro", "NO CONSECUTIVO REGISTRO");
        TITULO.put("mes","MES");
        TITULO.put("nombre_entidad","ENTIDAD");
        TITULO.put("tipo_centro_salud","TIPO CENTRO DE SALUD");
        TITULO.put("clues","CLAVE CLUES");
        TITULO.put("nombre_unidad","NOMBRE UNIDAD");
        TITULO.put("area_adscripcion","AREA ADSCRIPCION");
        TITULO.put("puesto","PUESTO");
        TITULO.put("codigo","CLAVE PRESUPUESTO");
        TITULO.put("servicio","SERVICIO");
        TITULO.put("rama","RAMA");
        TITULO.put("nombre","NOMBRE");
        TITULO.put("rfc","RFC");
        TITULO.put("turno","TURNO");
        TITULO.put("fechaIngreso","FECHA DE INGRESO");
        TITULO.put("descripcion","CENTRO RESPONSABILIDAD");
        TITULO.put("funcion","FUNCION");
        TITULO.put("percepciones","PERCEPCION NETA");
        TITULO.put("deducciones","DEDUCCION TOTAL");
        TITULO.put("percepcionTotal","PERCEPCION TOTAL");
        TITULO.put("neto","TOTAL");
        TITULO.put("id_pago_nomina","idPagoNomina");
    }

    /**
     * Permite obtener los datos del reporte de pago general, así como los
     * titulos de la consulta.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @param titulos en este arreglo se guardaran los titulos de la consulta.
     * @param datos los datos de la consulta.
     */
    public void obtenerInformacion(Integer idProductoNomina, List<String> titulos, List<Object []> datos) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(USP_PAGO_GENERAL);
            pstmt.setInt(1, idProductoNomina);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int totalColumnas = metaData.getColumnCount();
            int posicionIdNominaEmpleado = 0;

            for (int i = 1; i <= totalColumnas; i++) {
                String nombreColumna = metaData.getColumnName(i);
                if (TITULO.containsKey(nombreColumna)){
                    String titulo = TITULO.get(nombreColumna);
                    titulos.add(titulo);
                } else if("id_nomina_empleado".equals(nombreColumna)) {
                    posicionIdNominaEmpleado = i;
                } else {
                    String titulo = obtenerDescripcion(nombreColumna);
                    titulos.add(titulo);
                }
            }

        // TODO: No incluir la columna id_nomina_empleado

            while(rs.next()) {
                Object [] objDatos = new Object[titulos.size()];

                for (int i = 1; i <= totalColumnas; i++) {
                    if(i == posicionIdNominaEmpleado) {
                        objDatos[i - 1] = new Object();
                        i =+ 1;
                    } else {
                        objDatos[i - 1] = new Object();
                    }

                    switch(metaData.getColumnClassName(i)) {
                        case "java.lang.String":
                            objDatos[i - 1] = rs.getString(i);
                            break;
                        case "java.lang.Long":
                            objDatos[i - 1] = rs.getLong(i);
                            break;
                        case "java.math.BigDecimal":
                            objDatos[i - 1] = rs.getBigDecimal(i);
                            break;
                        default:
                            objDatos[i - 1] = null;
                            break;
                    }
                }
                datos.add(objDatos);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    private String obtenerDescripcion(String columnName) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(CONSULTA_DESCRIPCION);
            pstmt.setString(1, columnName);
            ResultSet rs = pstmt.executeQuery();
            String descripcion = null;
            while (rs.next()) {
                descripcion = rs.getString(1);
            }
            return descripcion;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return null;
    }

}
