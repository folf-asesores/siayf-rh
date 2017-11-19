/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 11/09/2016 14:03:27
 *
 */
public class ServicioRepository extends GenericRepository<ServicioEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -1466605639801738247L;

    public List<ServicioEntity> consultarActividades() {
        return em.createQuery("SELECT a FROM ServicioEntity AS a", ServicioEntity.class).getResultList();
    }

    public ServicioEntity obtenerServicioPorDescripcion(String servicio) {
        try {
            return em.createQuery("SELECT s FROM ServicioEntity AS s WHERE s.servicio =:servicio", ServicioEntity.class).setParameter("servicio", servicio)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
