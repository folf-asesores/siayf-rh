/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 21:05:29
 */
public class AdscripcionRepository extends GenericRepository<AdscripcionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -8295680064056255439L;

    public List<AdscripcionEntity> consultarAdscripciones() {
        return em.createQuery("SELECT a FROM AdscripcionEntity AS a", AdscripcionEntity.class).getResultList();
    }

    public String obtenerDescripcionAdscripcionPorId(Integer idAdscripcion) {
        try {
            return em.createQuery("SELECT a.adscripcion FROM AdscripcionEntity AS a WHERE a.idAdscripcion =:idAdscripcion", String.class)
                    .setParameter("idAdscripcion", idAdscripcion).getSingleResult();
        } catch (NoResultException e) {
            return "";
        }
    }

    public AdscripcionEntity obtenerAdscripcionPorNombre(String adscripcion) {
        try {
            return em.createQuery("SELECT a FROM AdscripcionEntity AS a WHERE a.adscripcion =:adscripcion", AdscripcionEntity.class)
                    .setParameter("adscripcion", adscripcion).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
