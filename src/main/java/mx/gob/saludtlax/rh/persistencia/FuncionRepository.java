/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 10:15:27
 *
 */
public class FuncionRepository extends GenericRepository<FuncionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 250259574162646528L;

    public List<FuncionEntity> consultarFunciones() {
        return em.createQuery("SELECT f FROM FuncionEntity AS f", FuncionEntity.class).getResultList();
    }

    public FuncionEntity obtenerFuncionPorDescripcion(String funcion) {
        try {
            return em.createQuery("SELECT f FROM FuncionEntity AS f WHERE f.funcion =:funcion", FuncionEntity.class).setParameter("funcion", funcion)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
