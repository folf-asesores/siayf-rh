
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

public class CluesRepository extends GenericRepository<CluesEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 8110142957688336986L;

    public CluesEntity obtenerCluePorClave(String clues) {
        try {
            return em.createQuery(
                    "SELECT c FROM CluesEntity AS c WHERE c.clues =:clues",
                    CluesEntity.class).setParameter("clues", clues)
                    .getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

}
