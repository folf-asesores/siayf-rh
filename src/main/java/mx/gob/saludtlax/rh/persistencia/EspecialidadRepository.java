/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 14:13:11 05/08/2016
 */
public class EspecialidadRepository extends GenericRepository<EspecialidadEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2618964647054090640L;

	public List<EspecialidadEntity> obtenerListaEspecialidad() {
		return em.createQuery("SELECT e FROM EspecialidadEntity as e", EspecialidadEntity.class).getResultList();
	}

	public boolean existeIdEspecialidad(Integer idEspecialidad) {
		boolean resultado = false;

		try {

			Integer existeId = em.createQuery(
					"SELECT e.idEspecialidad FROM EspecialidadEntity AS e WHERE e.idEspecialidad =:idEspecialidad",
					Integer.class).setParameter("idEspecialidad", idEspecialidad).getSingleResult();

			resultado = true;

		} catch (NoResultException ex) {
			resultado = false;
		} catch (NonUniqueResultException e) {
			resultado = true;
		}
		return resultado;
	}

}
