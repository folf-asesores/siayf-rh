/*
 * JasperReporte.java
 * Creado el 9/Sep/2016 1:37:04 PM
 *
 */

package mx.gob.saludtlax.rh.reportes.jasperreports;

import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.reportes.Reporte;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class JasperReporte extends Reporte {

    private final Map<String, JasperReporte> subreportes;
    private final Map<String, Class<?>> parametrosTipos;

    public JasperReporte(String nombreArchivo, String ruta) {
        super(nombreArchivo, ruta);
        subreportes = new HashMap<>();
        parametrosTipos = new HashMap<>();
    }

    public boolean tieneSubreportes() {
        return ((subreportes != null) && (!subreportes.isEmpty()));
    }

    public void agregarSubreporte(String nombreParametro,
            JasperReporte subreporte) {
        subreportes.put(nombreParametro, subreporte);
    }

    public <T> void agregarParametro(String clave, Class<T> tipo) {
        parametrosTipos.put(clave, tipo);
    }

    public Class<?> obtenerTipoParametro(String nombreParametro) {
        return parametrosTipos.get(nombreParametro);
    }

    public Map<String, JasperReporte> getSubreportes() {
        return subreportes;
    }

    public Map<String, Class<?>> getParametrosTipos() {
        return parametrosTipos;
    }
}
