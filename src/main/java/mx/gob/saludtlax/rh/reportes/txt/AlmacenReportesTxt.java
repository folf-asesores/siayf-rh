/*
 * AlmacenReportesTxt.java
 * Creado el 07/Dec/2016 10:45:36 PM
 *
 */

package mx.gob.saludtlax.rh.reportes.txt;

import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.reportes.AlmacenReportes;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AlmacenReportesTxt implements AlmacenReportes<TxtReporte> {
    private static final Map<String, TxtReporte> REPORTES = new HashMap<>();

    static {
        TxtReporte dispersionNomina = new TxtReporte();
        REPORTES.put("dispersion_nomina", dispersionNomina);
        TxtReporte comprobanteNomina = new TxtReporte();
        REPORTES.put("comprobante_nomina", comprobanteNomina);
        TxtReporte prenominaEventuales = new TxtReporte();
        REPORTES.put("prenomina_eventuales", prenominaEventuales);
        TxtReporte listadoFirmas = new TxtReporte();
        REPORTES.put("listado-firmas", listadoFirmas);
    }

    @Override
    public TxtReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
    public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
