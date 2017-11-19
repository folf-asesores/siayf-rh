/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 03/06/2016 14:25:24
 */
public class BancoSatRepository
        extends GenericRepository<BancoSatEntity, Integer> {
    private static final long serialVersionUID = 6726137706686273833L;

    public List<BancoSatEntity> obtenerListaBanco() {
        return em.createQuery("SELECT b FROM BancoSatEntity AS b",
                BancoSatEntity.class).getResultList();
    }

    public Boolean validarClaveBanco(String clave) {
        Boolean resultado = Boolean.FALSE;
        try {
            @SuppressWarnings("unused")
            BancoSatEntity bancoEntity = em.createQuery(
                    "SELECT b FROM BancoSatEntity AS b WHERE b.clave =:clave",
                    BancoSatEntity.class).setParameter("clave", clave)
                    .getSingleResult();

            resultado = true;
        } catch (NoResultException e) {
            resultado = false;
        }
        return resultado;
    }

}
