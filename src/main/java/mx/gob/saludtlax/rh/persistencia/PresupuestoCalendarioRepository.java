/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 *
 */
public class PresupuestoCalendarioRepository extends GenericRepository<PresupuestoCalendarioEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 591073866593021588L;

    public List<PresupuestoCalendarioEntity> obtenerListaPresupuestoCalendario() {
        try {
            return em.createQuery("SELECT p FROM PresupuestoCalendarioEntity AS p", PresupuestoCalendarioEntity.class).getResultList();

        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<PresupuestoCalendarioEntity> obtenerListaPresupuestoCalendarioPorAnio(Integer anio) {
        try {
            return em.createQuery("SELECT p FROM PresupuestoCalendarioEntity AS p WHERE p.anio =:anio", PresupuestoCalendarioEntity.class)
                    .setParameter("anio", anio).getResultList();

        } catch (NoResultException ex) {
            return null;
        }
    }
}
