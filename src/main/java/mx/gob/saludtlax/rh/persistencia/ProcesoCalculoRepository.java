/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 01/12/2016 16:59:25
 */
public class ProcesoCalculoRepository extends GenericRepository<ProcesoCalculoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 8802846926599287917L;

    public ProcesoCalculoEntity obtenerProcesoPorTipo(Integer tipoProceso) {
        try {
            return em.createQuery("SELECT p FROM ProcesoCalculoEntity AS p WHERE p.tipoProceso =:tipoProceso", ProcesoCalculoEntity.class)
                    .setParameter("tipoProceso", tipoProceso).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
