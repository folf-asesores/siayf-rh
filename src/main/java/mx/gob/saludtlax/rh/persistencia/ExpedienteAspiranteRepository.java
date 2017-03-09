/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.BusinessException;

/**
 * @author Eduardo Mex
 * @since 27/06/2016 18:13:56
 * @version 1.0
 * @email lic.eduardo_mex@hotmail.com
 */
public class ExpedienteAspiranteRepository extends GenericRepository<ExpedienteAspiranteEntity, Integer> {

    private static final long serialVersionUID = 1984137744161408097L;

	/**
	 * Consulta si el empleado tiene aperturado un expediente.
	 * 
	 * @param idAspirante
	 */
	public boolean existeExpedienteAsignadoAspirante(Integer idAspirante) {
		try {
			em.createQuery(
					"SELECT e.idExpedienteAspirante FROM ExpedienteAspiranteEntity AS e WHERE e.idAspirante =:idAspirante",
					Integer.class).setParameter("idAspirante", idAspirante).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		} catch (NonUniqueResultException exception) {
			throw new BusinessException("Se ha encontrado mas de un expediente asignado al aspirante.");
		}
	}

	/**
	 * Consulta si el numero de expediente ya está asignado.
	 * 
	 * @param numeroExpediente
	 */
	public boolean existeNumeroExpediente(String numeroExpediente) {
		try {
			em.createQuery(
					"SELECT e.idExpedienteAspirante FROM ExpedienteAspiranteEntity AS e WHERE e.numeroExpediente =:numeroExpediente",
					Integer.class).setParameter("numeroExpediente", numeroExpediente.trim()).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	/**
	 * Consulta el numero de expediente del aspirante
	 * 
	 * @param numeroExpediente
	 */
	public String numeroExpedienteAspirante(Integer idAspirante) {

		try {
			return em.createQuery(
					"SELECT e.numeroExpediente FROM ExpedienteAspiranteEntity AS e WHERE e.idAspirante =:idAspirante",
					String.class).setParameter("idAspirante", idAspirante).getSingleResult();

		} catch (NoResultException exception) {
			throw new BusinessException("El aspirante no tiene un expediente aperturado.");
		}
	}

	public Integer obtenerIdExpedienteAspirante(Integer idAspirante) {

		try {
			return em.createQuery(
					"SELECT e.idExpedienteAspirante FROM ExpedienteAspiranteEntity AS e WHERE e.idAspirante =:idAspirante",
					Integer.class).setParameter("idAspirante", idAspirante).getSingleResult();

		} catch (NoResultException exception) {
			throw new BusinessException("El aspirante no tiene un expediente aperturado.");
		}
	}

}
