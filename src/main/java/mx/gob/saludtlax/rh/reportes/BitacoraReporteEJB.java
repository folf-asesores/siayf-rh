/*
 * BitacoraReporteEJB.java
 * Creado el 9/Sep/2016 1:37:04 PM
 * 
 */

package mx.gob.saludtlax.rh.reportes;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.persistencia.BitacoraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.ReporteParametroEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;

import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

/**
 * Este EJB ayuda a registrar en la bitácora la información de generación de los
 * reportes.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author Eduardo Mex
 */
@Stateless
public class BitacoraReporteEJB implements BitacoraReporte {

    @Inject private BitacoraReporteRepository bitacoraReporteRepository;
    @Inject private UsuarioRepository empleadoRepository;
    
    private static final Logger LOGGER = Logger.getLogger(BitacoraReporteEJB.class);
        
    @Override
    public String obtenerReferencia(Map<String, String> parametros) {
        if(parametros == null || parametros.isEmpty()) {
            throw new NullPointerException("No se puede generar un reporte sin parametros");
        }

        BitacoraReporteEntity entidad = new BitacoraReporteEntity();
        UsuarioEntity  usuario = empleadoRepository.obtenerPorId(Integer.parseInt(parametros.get("ID_USUARIO")));
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        entidad.setUsuario(usuario);
        entidad.setNombreReporte(nombreReporte);

        parametros.remove("ID_USUARIO");
        parametros.remove("REPORTE_NOMBRE");

        Set<ReporteParametroEntity> reporteParametros = new HashSet<>();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            ReporteParametroEntity reporteParametro = new ReporteParametroEntity();

            reporteParametro.setIdReporteParametro(null);
            reporteParametro.setClave(key);
            reporteParametro.setValor(value);
            reporteParametro.setBitacoraReporte(entidad);

            reporteParametros.add(reporteParametro);
        }

        String uuid = UUID.randomUUID().toString().substring(0, 32);
        entidad.setIdReferencia(uuid);
        Date fecha = Calendar.getInstance().getTime();
        entidad.setFechaGeneracion(fecha);
        //entidad.setHoraGeneracion(fecha);

        entidad.setReporteParametros(reporteParametros);

        String id = bitacoraReporteRepository.crear(entidad).getIdReferencia();
        return id;
    }

    @Override
    public Map<String, String> obtenerParametros(String referencia) {
        Map<String, String> parametros = new HashMap<>();
        BitacoraReporteEntity bitacoraReporteEntity = bitacoraReporteRepository.obtenerPorId(referencia);
        
        if (bitacoraReporteEntity == null) {
            LOGGER.warn("No se encontro la entidad");
            return Collections.emptyMap();
        }
        
        parametros.put("ID_USUARIO", String.valueOf(bitacoraReporteEntity.getUsuario().getIdUsuario()));
        parametros.put("REPORTE_NOMBRE", bitacoraReporteEntity.getNombreReporte());
        
        for (ReporteParametroEntity reporteParametroEntity : bitacoraReporteEntity.getReporteParametros()) {
            String key = reporteParametroEntity.getClave();
            String value = reporteParametroEntity.getValor();
            
            parametros.put(key, value);
        }
        
        return parametros;
    }

    @Override
    public String obtenerNombreReporte(String referencia) {
        Map<String, String> parametros = obtenerParametros(referencia);
        return parametros.get("REPORTE_NOMBRE");
    }

    @Override
    public String obtenerTipoReporte(String referencia) {
        Map<String, String> parametros = obtenerParametros(referencia);
        return parametros.get("TIPO_REPORTE");
    }

}
