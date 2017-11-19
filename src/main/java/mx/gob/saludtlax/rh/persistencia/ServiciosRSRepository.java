
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class ServiciosRSRepository implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2219259060115434375L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    protected EntityManager entityManager;

    /**
     * Regresa todos los servicios registrados.
     *
     * @return Listado
     */
    public List<ServiciosRSEntity> obtenerListadoServicios() {

        List<ServiciosRSEntity> listadoServiciosRSEntity = entityManager.createNamedQuery(ServiciosRSEntity.OBTENER_LISTADO, ServiciosRSEntity.class)
                .getResultList();
        return listadoServiciosRSEntity;
    }

    /**
     * Valida que un servicio ya este registrao de manera activa
     *
     * @return verdadero si existe un servicio activo
     */
    public boolean existeServicioActivo(ServicioWebEnum servicio) {

        try {

            entityManager.createNamedQuery(ServiciosRSEntity.BUSCAR_SERVICIO_ACTIVO, ServiciosRSEntity.class).setParameter("servicioEnum", servicio)
                    .getSingleResult();

            return true;
        } catch (NoResultException ex) {

            return false;
        }

    }

    /**
     * Regresa los datos del servicio activo
     *
     * @param servicio
     *            servicio que se quiere consultar
     * @return ServiciosRSEntity con la informacion del servicio
     */
    public ServiciosRSEntity getDatosServicioActivo(ServicioWebEnum servicio) {
        try {
            return entityManager.createNamedQuery(ServiciosRSEntity.BUSCAR_SERVICIO_ACTIVO, ServiciosRSEntity.class).setParameter("servicioEnum", servicio)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
     * Valida que un servicio ya este registrado de manera activa, buscando un
     * id en espesifico.
     *
     * @return verdadero si existe un servicio activo
     */
    public boolean existeServicioActivo(ServicioWebEnum servicio, Integer idServicio) {

        try {

            entityManager.createNamedQuery(ServiciosRSEntity.BUSCAR_SERVICIO_ACTIVO_ID, ServiciosRSEntity.class).setParameter("servicioEnum", servicio)
                    .setParameter("idServicio", idServicio).getSingleResult();

            return true;
        } catch (NoResultException ex) {

            return false;
        }

    }

}
