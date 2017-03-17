/*
 * ProductoNominaFederalReporteService.java
 * Creado el 16/Mar/2017 11:21:03 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.federales;

import java.io.Serializable;
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
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ProductoNominaFederalReporteService implements Serializable {
    
    private static final long serialVersionUID = -3622224990346218269L;
    private static final Logger LOGGER = Logger.getLogger(ProductoNominaFederalReporteService.class.getName());
    private static final String USP_PRODUCTO_NOMINA_FEDERAL =
            "CALL usp_reporte_producto_nomina_federales(?)";

//    @Resource(mappedName = Configuracion.DATASOURCE_ESPEJO)
    @Resource(mappedName = "java:jboss/datasources/SIAYFRHDS")
    private DataSource ds; 
    private static final Map<String, String> TITULOS = new HashMap<>();
    
    static {
        TITULOS.put("numeroEmpleado", "NÚMERO DE EMPLEADO");
        TITULOS.put("nombreEmpleado", "NOMBRE DEL EMPLEADO");
        TITULOS.put("rfc", "RFC");
        TITULOS.put("funcionTrabajador", "FUNCIÓN DEL TRABAJADOR");
        TITULOS.put("unidadResponsable", "UNIDAD RESPONSABLE");
        TITULOS.put("grupoFuncional", "GRUPO FUNCIONAL");
        TITULOS.put("funcion", "FUNCIÓN");
        TITULOS.put("subfuncion", "SUBFUNCIÓN");
        TITULOS.put("partida", "PARTIDA");
        TITULOS.put("puesto", "PUESTO");
        TITULOS.put("fechaInicialQuincena", "PERIODO INICIAL DE QUINCENA");
        TITULOS.put("fechaFinalQuincena", "PERIODO FINAL DE QUINCENA");
        TITULOS.put("fechaPago", "QUINCENA REAL DE PAGO");
        TITULOS.put("anyoRealPago", "AÑO REAL DE PAGO");
    }

    /**
     * Permite obtener los datos del reporte de producto de nómina de empleados 
     * federales, así como los titulos de la consulta.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @param titulos en este arreglo se guardaran los titulos de la consulta.
     * @param datos los datos de la consulta.
     */
    public void obtenerInformacion(Integer idProductoNomina, List<String> titulos, List<Object []> datos) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(USP_PRODUCTO_NOMINA_FEDERAL);
            pstmt.setInt(1, idProductoNomina);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int totalColumnas = metaData.getColumnCount();
            int posicionIdNominaEmpleado = 0;

            for (int i = 1; i <= totalColumnas; i++) {
                String etiquetaColumna = metaData.getColumnLabel(i);
                if (TITULOS.containsKey(etiquetaColumna)) {
                    String titulo = TITULOS.get(etiquetaColumna);
                    titulos.add(titulo);
                } else if("id_nomina_empleado".equals(etiquetaColumna)) {
                    posicionIdNominaEmpleado = i;
                } else {
                    titulos.add(etiquetaColumna);
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
}
