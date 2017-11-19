/*
 *
 * ConfiguracionAplicacionService.java
 * Creado el Aug 26, 2016 1:35:40 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAplicacionRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ConfiguracionAplicacionService {

    @Inject
    private ConfiguracionAplicacionRepository configuracionAplicacionRepository;

    protected void setConfiguracion(String clave, String valor) {
        ConfiguracionAplicacionEntity entity = new ConfiguracionAplicacionEntity(
                clave, valor);
        configuracionAplicacionRepository.crear(entity);
    }

    protected String getConfiguracion(String clave) {
        try {
            return configuracionAplicacionRepository.getConfiguracion(clave);
        } catch (NoResultException ex) {
            throw new SistemaException("La clave no existe",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }
    }

    protected Map<String, String> getTodasConfiguraciones() {
        Map<String, String> configuraciones = new HashMap<>();

        for (ConfiguracionAplicacionEntity configuracionEntidad : configuracionAplicacionRepository
                .getTodasConfiguraciones()) {
            configuraciones.put(configuracionEntidad.getClave(),
                    configuracionEntidad.getValor());
        }

        return configuraciones;
    }

    protected Map<String, String> getConfiguraciones(List<String> claves) {
        Map<String, String> configuraciones = new HashMap<>();

        for (ConfiguracionAplicacionEntity configuracionEntidad : configuracionAplicacionRepository
                .getConfiguraciones(claves)) {
            configuraciones.put(configuracionEntidad.getClave(),
                    configuracionEntidad.getValor());
        }

        return configuraciones;
    }

}
