/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 21:20:36
 */
public class SubadscripcionRepository
        extends GenericRepository<SubadscripcionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -1321409601971684852L;

    public List<SubadscripcionEntity> consultarAreasAdscripcion() {
        return em.createQuery("SELECT  a FROM SubadscripcionEntity AS a",
                SubadscripcionEntity.class).getResultList();
    }

    public String obtenerDescripcionAreaAdscripcionPorId(
            Integer idAreaAdscripcion) {
        try {
            return em.createQuery(
                    "SELECT a.subadscripcion FROM SubadscripcionEntity AS a WHERE a.idSubadscripcion =:idSubadscripcion",
                    String.class)
                    .setParameter("idSubadscripcion", idAreaAdscripcion)
                    .getSingleResult();
        } catch (NoResultException e) {
            return "";
        }
    }

    public SubadscripcionEntity obtenerSubadscripcionPorDescripcion(
            String subadscripcion) {
        try {
            return em.createQuery(
                    "SELECT a FROM SubadscripcionEntity AS a WHERE a.subadscripcion =:subadscripcion",
                    SubadscripcionEntity.class)
                    .setParameter("subadscripcion", subadscripcion)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
