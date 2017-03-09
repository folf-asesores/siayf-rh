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
 *
 * @version 10/03/2016 11:02:43
 */
public class ProfesionRepository extends GenericRepository<ProfesionEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3837863046702903172L;

	public List<ProfesionEntity> obtenerListaProfesion() {
		return em.createQuery("SELECT p FROM ProfesionEntity AS p", ProfesionEntity.class).getResultList();
	}

	public boolean existeIdProfesion(Integer idProfesion) {
		boolean resultado = false;

		try {

			Integer existeId = em
					.createQuery("SELECT p.idProfesion FROM ProfesionEntity AS p WHERE p.idProfesion =:idProfesion",
							Integer.class)
					.setParameter("idProfesion", idProfesion).getSingleResult();

			resultado = true;

		} catch (NoResultException ex) {
			resultado = false;
		} catch (NonUniqueResultException e) {
			resultado = true;
		}
		return resultado;
	}

}
