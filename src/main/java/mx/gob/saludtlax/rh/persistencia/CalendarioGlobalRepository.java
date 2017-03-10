/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 *
 */
public class CalendarioGlobalRepository extends GenericRepository<CalendarioGlobalEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2450889342088211811L;

	public List<CalendarioGlobalEntity> obtenerListaCalendarioGlobal() {
		try {
			return em.createQuery("SELECT c FROM CalendarioGlobalEntity AS c", CalendarioGlobalEntity.class)
					.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
