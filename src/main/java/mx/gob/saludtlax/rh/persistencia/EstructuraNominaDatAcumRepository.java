/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 17:14:35 26/09/2016
 */
public class EstructuraNominaDatAcumRepository extends GenericRepository<EstructuraNominaDatAcumEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -408325370483203004L;

    public Integer obtenerIdEstructuraNominaPorRfcyIdContexto(String rfc, String idContexto) {
        try {
            return em.createQuery("SELECT e.idEstructurasNominas FROM EstructuraNominaDatAcumEntity As e WHERE e.rfc =:rfc AND e.idContexto =:idContexto",
                    Integer.class).setParameter("rfc", rfc).setParameter("idContexto", idContexto).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
