/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/03/2016-11:43:12
 */
public class DireccionRepository
        extends GenericRepository<DireccionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5390700860467962214L;

    public DireccionEntity consultarDireccionEmpleadoPorId(Integer idEmpleado) {
        try {
            DireccionEntity direccion = em.createQuery(
                    "SELECT d FROM DireccionEntity AS d WHERE d.idEmpleado =:idEmpleado",
                    DireccionEntity.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();
            return direccion;
        } catch (NoResultException exception) {
            return null;
        }
    }

    public DireccionEntity consultarDireccionAspirantePorId(
            Integer idAspirante) {
        try {
            DireccionEntity direccion = em.createQuery(
                    "SELECT d FROM DireccionEntity AS d WHERE d.idAspirante =:idAspirante",
                    DireccionEntity.class)
                    .setParameter("idAspirante", idAspirante).getSingleResult();
            return direccion;
        } catch (NoResultException exception) {
            return null;
        }
    }

}
