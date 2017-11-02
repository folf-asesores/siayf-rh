/*
 * PagoGeneralReporteService.java
 * Creado el 15/Feb/2017 5:30:39 AM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;

import static mx.gob.saludtlax.rh.util.Configuracion.DATASOURCE_ESPEJO;
import static mx.gob.saludtlax.rh.util.PlantillaMensaje.SQL_ERROR_MESSAGE;

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
import org.jboss.logging.Logger;
/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PagoGeneralReporteService {

    @Resource(mappedName = DATASOURCE_ESPEJO)
    private DataSource ds;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralReporteService.class.getName());
    private static final String USP_PAGO_GENERAL = "CALL usp_reporte_pago_general(?)";
    private static final String CONSULTA_DESCRIPCION =
            "  SELECT cn.descripcion"
            + "  FROM conceptos_nominas_contratos AS cn"
            + " WHERE cn.clave = ?";
    private static final Map<String, String> TITULOS;

    static {
        TITULOS  = new HashMap<>();
        TITULOS.put("programa","PROGRAMA");
        TITULOS.put("noConsecutivoRegistro", "NO. CONSECUTIVO REGISTRO");
        TITULOS.put("mes","MES");
        TITULOS.put("entidad","ENTIDAD");
        TITULOS.put("tipoCentroSalud","TIPO CENTRO DE SALUD");
        TITULOS.put("claveClues","CLAVE CLUES");
        TITULOS.put("nombreUnidad","NOMBRE DE LA UNIDAD");
        TITULOS.put("areaAdscripcion","ÁREA DE ADSCRIPCIÓN");
        TITULOS.put("puesto","PUESTO");
        TITULOS.put("clavePuesto","CLAVE PUESTO");
        TITULOS.put("servicio","SERVICIO");
        TITULOS.put("rama","RAMA");
        TITULOS.put("nombre","NOMBRE");
        TITULOS.put("rfc","RFC");
        TITULOS.put("turno","TURNO");
        TITULOS.put("fechaIngreso","FECHA DE INGRESO");
        TITULOS.put("centroResponsabilidad","CENTRO DE RESPONSABILIDAD");
        TITULOS.put("funcion","FUNCIÓN");
        TITULOS.put("percepcionNeta","PERCEPCIÓN NETA");
        TITULOS.put("deduccionTotal","DEDUCCIÓN TOTAL");
        TITULOS.put("percepcionTotal","PERCEPCIÓN TOTAL");
        TITULOS.put("total","TOTAL");
        TITULOS.put("idPagoNomina","idPagoNomina");
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
                if (TITULOS.containsKey(nombreEtiqueta)){
                    String titulo = TITULOS.get(nombreEtiqueta);
                    titulos.add(titulo);
                } else if("id_nomina_empleado".equals(nombreEtiqueta)) {
                    posicionIdNominaEmpleado = i;
                } else {
                    String titulo = obtenerDescripcion(nombreEtiqueta);
                    titulos.add(titulo);
                }
            }

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
                        case "java.util.Date":
                        case "java.sql.Date":
                            objDatos[i] = rs.getDate(columnLabel);
                            break;
                        default:
                            objDatos[i] = rs.getObject(columnLabel);
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
            LOGGER.errorv(SQL_ERROR_MESSAGE, ex.getMessage(), ex.getSQLState(), ex.getErrorCode());
        }
        return null;
    }

}
