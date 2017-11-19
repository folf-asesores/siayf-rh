/*
 * FirmaReporteQuery.java
 * Creado el 11/sep/2017 12:53:29 PM
 * 
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import mx.gob.saludtlax.rh.nomina.reportes.firma.FirmaPojo;
import mx.gob.saludtlax.rh.util.Configuracion;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaReporteQuery implements Serializable {

    private static final long serialVersionUID = 8646160355015622370L;
    private static final Logger LOGGER = Logger.getLogger(FirmaReporteQuery.class.getName());
    
    private static final String OBTENER_DATOS_FIRMAS = "CALL usp_listado_firma(?)";

    private static final String COLUMNA_ID_PRODUCTO = "idProductoNomina";
    private static final String COLUMNA_ID_PROGRAMA = "idPrograma";
    private static final String COLUMNA_PROGRAMA = "programa";
    private static final String COLUMNA_CLAVE = "clave"; 
    private static final String COLUMNA_DESCRIPCION = "descripcion";
    private static final String COLUMNA_FECHA_PAGO = "fechaPago";
    private static final String COLUMNA_FILIACION = "filiacion";
    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_INICIO_PERIODO = "inicioPeriodo";
    private static final String COLUMNA_FIN_PERIODO = "finPeriodo";
    private static final String COLUMNA_NUMERO_CHEQUE = "numeroCheque";
    private static final String COLUMNA_NETO = "neto";
    private static final String COLUMNA_CONSECUTIVO = "consecutivo";
    private static final String COLUMNA_JEFE_1_NOMBRE = "jefe1Nombre";
    private static final String COLUMNA_JEFE_1_CARGO = "jefe1Cargo";
    private static final String COLUMNA_JEFE_2_NOMBRE = "jefe2Nombre";
    private static final String COLUMNA_JEFE_2_CARGO = "jefe2Cargo";
    private static final String COLUMNA_JEFE_3_NOMBRE = "jefe3Nombre";
    private static final String COLUMNA_JEFE_3_CARGO = "jefe3Cargo";
    
    @Resource(mappedName = Configuracion.DATASOURCE)
    private DataSource dataSource;
    
    public List<FirmaPojo> obtenerDatos(Integer idProductoNomina) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(OBTENER_DATOS_FIRMAS);
            prepareStatement.setInt(1, idProductoNomina);
            ResultSet rs = prepareStatement.executeQuery();

            List<FirmaPojo> firmas = new ArrayList<>();

            while (rs.next()) {
                FirmaPojo firma = new FirmaPojo();
                
                int idProductoNominaSalida = rs.getInt(COLUMNA_ID_PRODUCTO);
                firma.setIdProductoNomina(idProductoNominaSalida);
                
                int idPrograma = rs.getInt(COLUMNA_ID_PROGRAMA);
                firma.setIdPrograma(idPrograma);
                
                String programa = rs.getString(COLUMNA_PROGRAMA);
                firma.setPrograma(programa);
                
                String clave = rs.getString(COLUMNA_CLAVE) == null ?
                        "" : rs.getString(COLUMNA_CLAVE);
                firma.setClave(clave);
                
                String descripcion = rs.getString(COLUMNA_DESCRIPCION);
                firma.setDescripcion(descripcion);
                
                Date fechaPago = rs.getDate(COLUMNA_FECHA_PAGO);
                firma.setFechaPago(fechaPago);
                
                String filiacion = rs.getString(COLUMNA_FILIACION);
                firma.setFiliacion(filiacion);
                
                String nombre = rs.getString(COLUMNA_NOMBRE);
                firma.setNombre(nombre);
                
                Date inicioPeriodo = rs.getDate(COLUMNA_INICIO_PERIODO);
                firma.setInicioPeriodo(inicioPeriodo);
                
                Date finPeriodo = rs.getDate(COLUMNA_FIN_PERIODO);
                firma.setFinPeriodo(finPeriodo);
                
                String numeroCheque = 
                        rs.getString(COLUMNA_NUMERO_CHEQUE) ==  null ?
                        "0" : rs.getString(COLUMNA_NUMERO_CHEQUE);
                firma.setNumeroCheque(numeroCheque);
                
                BigDecimal neto = rs.getBigDecimal(COLUMNA_NETO);
                firma.setNeto(neto);
                
                Integer consecutivo = rs.getInt(COLUMNA_CONSECUTIVO);
                firma.setConsecutivo(consecutivo);
                
                String jefe1Nombre = rs.getString(COLUMNA_JEFE_1_NOMBRE);
                firma.setJefe1Nombre(jefe1Nombre);
                
                String jefe1Cargo = rs.getString(COLUMNA_JEFE_1_CARGO);
                firma.setJefe1Cargo(jefe1Cargo);

                String jefe2Nombre = rs.getString(COLUMNA_JEFE_2_NOMBRE);
                firma.setJefe2Nombre(jefe2Nombre);
                
                String jefe2Cargo = rs.getString(COLUMNA_JEFE_2_CARGO);
                firma.setJefe2Cargo(jefe2Cargo);

                String jefe3Nombre = rs.getString(COLUMNA_JEFE_3_NOMBRE);
                firma.setJefe3Nombre(jefe3Nombre);
                
                String jefe3Cargo = rs.getString(COLUMNA_JEFE_3_CARGO);
                firma.setJefe3Cargo(jefe3Cargo);
                
                firmas.add(firma);
            }
            
            return firmas;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            return null;
        }
    }
}