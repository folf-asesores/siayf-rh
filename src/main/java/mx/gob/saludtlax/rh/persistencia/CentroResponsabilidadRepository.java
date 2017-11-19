
package mx.gob.saludtlax.rh.persistencia;

/**
 *
 * @author José Pablo
 */
import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

public class CentroResponsabilidadRepository
        extends GenericRepository<CentroResponsabilidadEntity, Integer>
        implements Serializable {

    private static final long serialVersionUID = -2152310785804187576L;

    public List<CentroResponsabilidadEntity> obtenerListaPuestoGeneral() {
        try {
            return em.createQuery("FROM CentroResponsabilidadEntity AS cr",
                    CentroResponsabilidadEntity.class).getResultList();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public CentroResponsabilidadEntity obtenerCentroResponsabilidad(
            String clave) {
        try {
            return em.createQuery(
                    "SELECT c FROM CentroResponsabilidadEntity AS c WHERE c.clave =:clave",
                    CentroResponsabilidadEntity.class)
                    .setParameter("clave", clave).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
