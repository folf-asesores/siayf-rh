/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2017
 */
public class EstructuraRepository extends GenericRepository<EstructuraEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 3883380915760270098L;

    public EstructuraEntity obtenerEstructuraPorIdPuesto(Integer idPuesto) {

        try {
            return em.createQuery("SELECT e FROM EstructuraEntity AS e WHERE e.idPuesto =:idPuesto", EstructuraEntity.class).setParameter("idPuesto", idPuesto)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException("El puesto tiene asignada más de una estructura.", SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }
    }

}
