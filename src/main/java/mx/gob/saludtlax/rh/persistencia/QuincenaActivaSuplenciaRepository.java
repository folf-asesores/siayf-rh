/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 28/12/2016 13:58:03
 */
public class QuincenaActivaSuplenciaRepository
        extends GenericRepository<QuincenaActivaSuplenciaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1949372150519860417L;

    public QuincenaActivaSuplenciaEntity obtenerQuincenaActiva() {
        try {
            return em.createQuery(
                    "SELECT q FROM QuincenaActivaSuplenciaEntity AS q WHERE q.activo =true",
                    QuincenaActivaSuplenciaEntity.class).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
