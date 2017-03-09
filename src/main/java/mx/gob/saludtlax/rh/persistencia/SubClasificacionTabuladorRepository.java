/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author eduardo
 *
 */
public class SubClasificacionTabuladorRepository extends GenericRepository<SubclasificacionTabuladorEntity, Integer> {

	public List<SubclasificacionTabuladorEntity> obtenerListaSubClasificaionTabulador() {
		try {
			return em.createQuery("Select s from SubclasificacionTabuladorEntity As s",
					SubclasificacionTabuladorEntity.class).getResultList();
		} catch (NoResultException exception) {
			return null;
		}
	}

}
