/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class CausaBajaIsssteRepository
        extends GenericRepository<CausaBajaIsssteEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -2414969078663699235L;

    public List<CausaBajaIsssteEntity> listaCausaBajaIssste() {
        return em.createQuery("SELECT c FROM CausaBajaIsssteEntity AS c",
                CausaBajaIsssteEntity.class).getResultList();
    }

}
