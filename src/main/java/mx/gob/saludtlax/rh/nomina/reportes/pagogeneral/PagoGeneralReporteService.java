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
        TITULO.put("noConsecutivoRegistro", "NO. CONSECUTIVO REGISTRO");
        TITULO.put("mes","MES");
        TITULO.put("entidad","ENTIDAD");
        TITULO.put("tipoCentroSalud","TIPO CENTRO DE SALUD");
        TITULO.put("claveClues","CLAVE CLUES");
        TITULO.put("nombreUnidad","NOMBRE DE LA UNIDAD");
        TITULO.put("areaAdscripcion","ÁREA DE ADSCRIPCIÓN");
        TITULO.put("puesto","PUESTO");
        TITULO.put("clavePuesto","CLAVE PUESTO");
        TITULO.put("servicio","SERVICIO");
        TITULO.put("rama","RAMA");
        TITULO.put("nombre","NOMBRE");
        TITULO.put("rfc","RFC");
        TITULO.put("turno","TURNO");
        TITULO.put("fechaIngreso","FECHA DE INGRESO");
        TITULO.put("centroResponsabilidad","CENTRO DE RESPONSABILIDAD");
        TITULO.put("funcion","FUNCIÓN");
        TITULO.put("percepcionNeta","PERCEPCIÓN NETA");
        TITULO.put("deduccionTotal","DEDUCCIÓN TOTAL");
        TITULO.put("percepcionTotal","PERCEPCIÓN TOTAL");
        TITULO.put("total","TOTAL");
        TITULO.put("idPagoNomina","idPagoNomina");
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
                String nombreEtiqueta = metaData.getColumnLabel(i);
                if (TITULO.containsKey(nombreEtiqueta)){
                    String titulo = TITULO.get(nombreEtiqueta);
                    titulos.add(titulo);
                } else if("id_nomina_empleado".equals(nombreEtiqueta)) {
                    posicionIdNominaEmpleado = i;
                } else {
                    String titulo = obtenerDescripcion(nombreEtiqueta);
                    titulos.add(titulo);
                }
            }

        // TODO: No incluir la columna id_nomina_empleado

            while(rs.next()) {
                Object [] objDatos = new Object[titulos.size()];

                int pos = 1;
                for (int i = 0; i < titulos.size(); i++) {
                    String columnClassName = metaData.getColumnClassName(pos);
                    String columnLabel = metaData.getColumnLabel(pos);
                    switch (columnClassName) {
                        case "java.lang.String":
                            objDatos[i] = rs.getString(columnLabel);
                            break;
                        case "java.lang.Integer":
                            objDatos[i] = rs.getInt(columnLabel);
                            break;
                        case "java.lang.Long":
                            objDatos[i] = rs.getLong(columnLabel);
                            break;
                        case "java.math.BigDecimal":
                            objDatos[i] = rs.getBigDecimal(columnLabel);
                            break;
                        default:
                            objDatos[i] = null;
                            break;
                    }
                    
                    pos = pos + 1 != posicionIdNominaEmpleado ? pos + 1 : pos + 2;
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
