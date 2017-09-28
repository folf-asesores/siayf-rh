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

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.nomina.reportes.comprobante.ComprobanteEmpleado;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion;
import mx.gob.saludtlax.rh.nomina.reportes.firma.Firma;
import mx.gob.saludtlax.rh.nomina.reportes.prenomina.PrenominaReporte;
import mx.gob.saludtlax.rh.reportes.Generador;

/**
 * Esta clase se encarga de generar los reporte de texto plano.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class TxtGenerador implements Generador {

    private static final Logger LOGGER = Logger.getLogger(TxtGenerador.class.getName());

    private static final String COMPROBANTE_BEAN = "java:module/ComprobanteEmpleadoBean";
    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String PRENOMINA_BEAN = "java:module/PrenominaReporteEJB";
    private static final String FIRMA_BEAN = "java:module/FirmaBean";

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesTxt almacen = new AlmacenReportesTxt();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        byte[] bytes = null;

        if (almacen.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                case "comprobante_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ComprobanteEmpleado comprobanteEmpleadoBean = getBean(ComprobanteEmpleado.class);
                    bytes = comprobanteEmpleadoBean.generarReporte(idProductoNomina);
                }
                break;
                case "dispersion_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Dispersion dispersionBean = getBean(Dispersion.class);
                    bytes = dispersionBean.generarReporte(idProductoNomina);
                }
                break;
                case "listado-firmas": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Firma firmaBean = getBean(Firma.class);
                    bytes = firmaBean.generarReporte(idProductoNomina);
                }
                break;
                case "prenomina_eventuales": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    PrenominaReporte prenominaReporteBean = getBean(PrenominaReporte.class);
                    bytes = prenominaReporteBean.generarReporte(idProductoNomina);
                }
            }
        }

        return bytes;
    }

    private <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                case "mx.gob.saludtlax.rh.nomina.reportes.comprobante.ComprobanteEmpleado":
                    bean = COMPROBANTE_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion":
                    bean = DISPERSION_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.reportes.firma.Firma":
                    bean = FIRMA_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.reportes.prenomina.PrenominaReporte":
                    bean = PRENOMINA_BEAN;
                    break;
                default:
                    return null;
            }

            Context initContext = new InitialContext();
            return (T) initContext.lookup(bean);
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", bean, ex.getCause());
            return null;
        }
    }
}
