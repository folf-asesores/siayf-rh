/*
 * JasperReportsGenerador.java
 * Creado el 9/Sep/2016 1:37:04 PM
 *
 */

package mx.gob.saludtlax.rh.reportes.jasperreports;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.reportes.Generador;
import net.sf.jasperreports.engine.JasperReport;
import org.jboss.logging.Logger;

/**
 * @author Eduardo Mex
 *
 */
public class JasperReportsGenerador implements Generador {

    private static final Logger LOGGER = Logger.getLogger(JasperReportsGenerador.class.getName());

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesJasperReports almacen = new AlmacenReportesJasperReports();

        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        JasperReporte reporte = almacen.obtenerReporte(nombreReporte);
        Map<String, Object> parametrosReporte = new HashMap<>();
        JasperReportsCompilador compilador = new JasperReportsCompilador();

        if (reporte.tieneSubreportes()) {
            Map<String, JasperReporte> subreportes = reporte.getSubreportes();

            for (Map.Entry<String, JasperReporte> entry : subreportes.entrySet()) {
                String clave = entry.getKey();
                JasperReporte subreporte = entry.getValue();
                JasperReport subjr = compilador.compilar(subreporte.getInputStream());

                parametrosReporte.put(clave, subjr);
            }
        }

        Map<String, Class<?>> parametrosTipos = reporte.getParametrosTipos();

        for (Map.Entry<String, Class<?>> entry : parametrosTipos.entrySet()) {
            String parametroClave = entry.getKey();
            Class<?> patametroTipo = entry.getValue();
            String parametroValor = parametros.get(parametroClave);

            parametrosReporte.put(parametroClave, obtenerValorCasteado(parametroValor, patametroTipo));
        }

        JasperReport jr = compilador.compilar(reporte.getInputStream());

        return compilador.generarReporte(jr, parametrosReporte, parametros.get("TIPO_REPORTE"));
    }

    public <T> T obtenerValorCasteado(String valor, Class<T> t) {
        if (valor == null) {
            throw new NullPointerException("No se puede castear un valor nulo");
        }

        if (Boolean.class.isAssignableFrom(t)) {
            return (T) Boolean.valueOf(valor);
        } else if (Integer.class.isAssignableFrom(t)) {
            return (T) Integer.valueOf(valor);
        } else if (Long.class.isAssignableFrom(t)) {
            return (T) Long.valueOf(valor);
        } else if (Float.class.isAssignableFrom(t)) {
            return (T) Float.valueOf(valor);
        } else if (Double.class.isAssignableFrom(t)) {
            return (T) Double.valueOf(valor);
        } else if (BigDecimal.class.isAssignableFrom(t)) {
            return (T) new BigDecimal(valor);
        } else if (Date.class.isAssignableFrom(t)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date date = sdf.parse(valor);
                return (T) date;
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if(String.class.isAssignableFrom(t)) {
            return (T) String.valueOf(valor);
        }

        throw new RuntimeException("No se ha logro convertir el valor:" + valor + " en " + t.getCanonicalName());
    }
}
