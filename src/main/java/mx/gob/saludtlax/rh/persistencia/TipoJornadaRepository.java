/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 12/12/2016 13:12:13
 */
public class TipoJornadaRepository extends GenericRepository<TipoJornadaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5889130381247959535L;

    public TipoJornadaEntity obtenerJornadaPorDescripcion(String tipoJornada) {
        try {
            return em.createQuery("SELECT t FROM TipoJornadaEntity AS t WHERE t.tipoJornada =:tipoJornada", TipoJornadaEntity.class)
                    .setParameter("tipoJornada", tipoJornada).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

    }
}
