/*
 *
 * ConfiguracionAplicacionEJB.java
 * Creado el Aug 26, 2016 1:33:02 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.app;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ConfiguracionAplicacionEJB implements ConfiguracionAplicacion {

    private static final long serialVersionUID = -1605013708923071508L;
    private static final String PATRON_VALIDACION_CLAVE = "([a-z]+[0-9]*[\\-]?[\\.]?)+([^\\.])$";

    @Inject
    private ConfiguracionAplicacionService configuracionAplicacionService;

    @Override
    public void setConfiguracion(String clave, String valor) {
        if (ValidacionUtil.esCadenaVacia(clave)) {
            throw new ValidacionException("La clave no puede ser nula o vacia.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!esClaveValida(clave)) {
            throw new ValidacionException("La clave no tiene un formato valido.", ValidacionCodigoError.FORMATO_INVALIDO);
        }

        configuracionAplicacionService.setConfiguracion(clave, valor);
    }

    @Override
    public void setConfiguraciones(Map<String, String> configuraciones) {
        for (Map.Entry<String, String> entry : configuraciones.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            setConfiguracion(key, value);
        }
    }

    @Override
    public String getConfiguracion(String clave) {
        if (ValidacionUtil.esCadenaVacia(clave)) {
            throw new ValidacionException("La clave no puede ser nula o vacia.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        try {
            return configuracionAplicacionService.getConfiguracion(clave);
        } catch (SistemaException ex) {
            if (SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS.equals(ex.getCodigoError())) {
                throw new ReglaNegocioException("La clave no existe como una configuración.", ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public Map<String, String> getConfiguraciones(String... claves) {
        return claves == null || claves.length == 0 ? configuracionAplicacionService.getTodasConfiguraciones()
                : configuracionAplicacionService.getConfiguraciones(Arrays.asList(claves));
    }

    private boolean esClaveValida(String clave) {
        Pattern pattern = Pattern.compile(PATRON_VALIDACION_CLAVE);
        Matcher matcher = pattern.matcher(clave);

        return matcher.matches();
    }

}
