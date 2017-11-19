/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author L. Miguel. S. Tizapantzi
 *
 */
public class ConceptoNominaFederalesRepository extends GenericRepository<ConceptoNominaFederalesEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6037939792695211904L;

    public Integer obtenerIdConceptoPorTipoyConceptoSiif(Integer tipoConcepto, String conceptoSiif) {
        try {
            return em
                    .createQuery("SELECT cn.idConceptoNomina FROM ConceptoNominaFederalesEntity AS cn WHERE cn.tipo =:tipoConcepto AND cn.clave =:conceptoSiif",
                            Integer.class)
                    .setParameter("tipoConcepto", tipoConcepto).setParameter("conceptoSiif", conceptoSiif).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

    public ConceptoNominaFederalesEntity obtenerConceptoPorClave(String clave) {
        try {
            return em.createQuery("SELECT cn FROM ConceptoNominaFederalesEntity AS cn WHERE cn.clave =:clave", ConceptoNominaFederalesEntity.class)
                    .setParameter("clave", clave).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}