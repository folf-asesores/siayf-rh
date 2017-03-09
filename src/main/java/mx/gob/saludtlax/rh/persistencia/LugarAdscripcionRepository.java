/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 21:35:42
 */
public class LugarAdscripcionRepository extends GenericRepository<LugarAdscripcionEntity, Integer> {

	public List<LugarAdscripcionEntity> consultarAdscripciones() {
		return em.createQuery("SELECT l FROM LugarAdscripcionEntity AS l", LugarAdscripcionEntity.class)
				.getResultList();
	}

	public String obtenerDescripcionLugarAdscripcionPorId(Integer idLugarAdscripcion) {
		try {
			return em.createQuery(
					"SELECT l.lugarAdscripcion FROM LugarAdscripcionEntity AS l WHERE l.idLugarAdscripcion =:idLugarAdscripcion",
					String.class).setParameter("idLugarAdscripcion", idLugarAdscripcion).getSingleResult();
		} catch (NoResultException e) {
			return "";
		}
	}
}
