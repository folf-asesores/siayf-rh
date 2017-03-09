/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 * @since 27/07/2016 10:57:29
 * @version 1.0
 * @email lic.eduardo_mex@hotmail.com
 */
public class RamaRepository extends GenericRepository<RamaEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423693135035295909L;

	public List<RamaEntity> obtenerListaRama() {
		try {
			return em.createQuery("SELECT r FROM RamaEntity AS r", RamaEntity.class).getResultList();
		} catch (NoResultException exception) {
			return null;
		}
	}

}
