
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

/**
 *
 * @author José Pablo
 *
 */
public class PresupuestoAutorizadoRepository extends GenericRepository<PresupuestoAutorizadoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1438327192068657606L;

    public List<PresupuestoAutorizadoEntity> obtenerListaPresupuestoAutorizado() {
        try {
            return em.createQuery("SELECT cr FROM PresupuestoAutorizadoEntity AS cr", PresupuestoAutorizadoEntity.class).getResultList();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
