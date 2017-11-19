/*
 *
 * ConfiguracionAplicacionRepository.java
 * Creado el Aug 26, 2016 1:15:38 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ConfiguracionAplicacionRepository extends GenericRepository<ConfiguracionAplicacionEntity, Integer> {

    private static final long serialVersionUID = -7880280065570438339L;
    private static final String OBTENER_CONFIGURACION = "select config.valor" + " from ConfiguracionAplicacionEntity as config"
            + " where config.clave = :clave";

    private static final String CONSULTAR_TODAS_LAS_CONFIGURACIONES = "from ConfiguracionAplicacionEntity as config";

    private static final String CONSULTAR_CONFIGURACIONES = "select config" + " from ConfiguracionAplicacionEntity as config"
            + " where config.clave in (:claves)";

    public String getConfiguracion(String clave) {
        TypedQuery<String> query = em.createQuery(OBTENER_CONFIGURACION, String.class);
        query.setParameter("clave", clave);

        return query.getSingleResult();
    }

    public List<ConfiguracionAplicacionEntity> getTodasConfiguraciones() {
        TypedQuery<ConfiguracionAplicacionEntity> query = em.createQuery(CONSULTAR_TODAS_LAS_CONFIGURACIONES, classType);

        return query.getResultList();
    }

    public List<ConfiguracionAplicacionEntity> getConfiguraciones(List<String> claves) {
        TypedQuery<ConfiguracionAplicacionEntity> query = em.createQuery(CONSULTAR_CONFIGURACIONES, classType);
        query.setParameter("claves", claves);

        return query.getResultList();
    }

    @Override
    public void eliminar(ConfiguracionAplicacionEntity entity) {
        throw new SistemaException("No se permite la eliminación de las propiedades del sistema.", SistemaCodigoError.ELIMINACION_NO_PERMITIDA);
    }

    @Override
    public void eliminarPorId(Integer key) {
        throw new SistemaException("No se permite la eliminación de las propiedades del sistema.", SistemaCodigoError.ELIMINACION_NO_PERMITIDA);
    }
}
