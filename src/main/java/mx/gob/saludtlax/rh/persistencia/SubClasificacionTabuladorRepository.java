/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class SubClasificacionTabuladorRepository
        extends GenericRepository<SubclasificacionTabuladorEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 94791204994715025L;

    public List<SubclasificacionTabuladorEntity> obtenerListaSubClasificaionTabulador() {
        try {
            return em.createQuery(
                    "Select s from SubclasificacionTabuladorEntity As s",
                    SubclasificacionTabuladorEntity.class).getResultList();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
