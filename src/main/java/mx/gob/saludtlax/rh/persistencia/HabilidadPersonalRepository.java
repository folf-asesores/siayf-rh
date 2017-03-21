/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Eduardo Mex
 * @version 22/03/2016 17:58:09
 * @email Lic.Eduardo_Mex@hotmail.com
 */
public class HabilidadPersonalRepository
		extends GenericRepository<HabilidadPersonalEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2583376921596711108L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;


	/**
	 * Valida si existe el registro del historial academico del aspirante
	 * 
	 * @param idAspirante
	 * @return
	 */
	public boolean existeIdAspirante(Integer idAspirante) {
		Boolean resultado = Boolean.FALSE;
		String contexto = "Registro Habilidad Personal: ";
		try {
			HabilidadPersonalEntity experienciaLaboral = entityManager
					.createQuery(
							"SELECT e FROM HabilidadPersonalEntity AS e WHERE e.aspirante.idAspirante =:idAspirante",
							HabilidadPersonalEntity.class)
					.setParameter("idAspirante", idAspirante).getSingleResult();
			resultado = true;
		} catch (NoResultException ex) {
			resultado = false;
		} catch (NonUniqueResultException ex) {
			throw new BusinessException(
					contexto + "La encuesta laboral del aspirante ya se encuentra registrado mas de una vez");
		}

		return resultado;
	}

	/***
	 * Encuesta personal por identificador del aspirante
	 * 
	 * @param idAspirante
	 * @return
	 */
	public HabilidadPersonalEntity encuentaPersonalPorIdAspirante(Integer idAspirante) {
		// String contexto = "Encuesta laboral: ";
		try {
			HabilidadPersonalEntity experienciaLaboral = entityManager
					.createQuery(
							"SELECT e FROM HabilidadPersonalEntity AS e WHERE e.aspirante.idAspirante =:idAspirante",
							HabilidadPersonalEntity.class)
					.setParameter("idAspirante", idAspirante).getSingleResult();
			return experienciaLaboral;
		} catch (NoResultException ex) {
			//throw new BusinessException("No se encontro registrado su habilidad personal del aspirante");
			return null;
		} catch (NonUniqueResultException ex) {
			throw new BusinessException("La  habilidad personal ya se encuentra registrado mas de una vez");
		}

	}



}
