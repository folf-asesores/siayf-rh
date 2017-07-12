/*
 * TxtGenerador.java
 * Creado el 07/Dec/2016 10:53:53 PM
 *
 */
package mx.gob.saludtlax.rh.reportes.txt;

import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.gob.saludtlax.rh.nomina.reportes.comprobante.ComprobanteEmpleado;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion;
import mx.gob.saludtlax.rh.nomina.reportes.prenomina.PrenominaReporte;
import mx.gob.saludtlax.rh.reportes.Generador;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class TxtGenerador implements Generador {

    private static final Logger LOGGER = Logger.getLogger(TxtGenerador.class.getName());

    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String COMPROBANTE_BEAN = "java:module/ComprobanteEmpleadoEJB";
    private static final String PRENOMINA_BEAN = "java:module/PrenominaReporteEJB";

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesTxt almacen = new AlmacenReportesTxt();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        byte[] bytes = null;

        if (almacen.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                case "dispersion_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    bytes = obtenerDispersion().generarReporte(idProductoNomina);
                }
                break;
                case "comprobante_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    bytes = obtenerComprobante().generarReporte(idProductoNomina);
                }
                break;
                case "prenomina_eventuales": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    bytes = obtenerPrenominaReporte().generarReporte(idProductoNomina);
                }
                break;
            }
        }

        return bytes;
    }


    private Dispersion obtenerDispersion() {
        try {
            Context initContext = new InitialContext();
            Dispersion dispersionEJB = (Dispersion) initContext.lookup(DISPERSION_BEAN);

            return dispersionEJB;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", DISPERSION_BEAN,
                    ex.getCause());
            return null;
        }
    }

    private ComprobanteEmpleado obtenerComprobante() {
        try {
            Context initContext = new InitialContext();
            ComprobanteEmpleado comprobanteEJB = (ComprobanteEmpleado) initContext.lookup(COMPROBANTE_BEAN);

            return comprobanteEJB;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", COMPROBANTE_BEAN,
                    ex.getCause());
            return null;
        }
    }

    private PrenominaReporte obtenerPrenominaReporte() {
        try {
            Context initContext = new InitialContext();
            PrenominaReporte comprobanteEJB = (PrenominaReporte) initContext.lookup(PRENOMINA_BEAN);

            return comprobanteEJB;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", PRENOMINA_BEAN,
                    ex.getCause());
            return null;
        }
    }

}
