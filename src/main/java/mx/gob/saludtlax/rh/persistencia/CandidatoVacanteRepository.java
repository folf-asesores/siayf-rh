/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/***
 * 
 * @author L.I. Eduardo B. C. Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 16/08/2016 12:25:34
 */
public class CandidatoVacanteRepository extends GenericRepository<CandidatoVacanteEntity, Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4926720203321429203L;

	public boolean existeIdContexto(Integer idContexto) {
		boolean resultado = false;
		try {
			em.createQuery(
					"SELECT c.idContexto FROM CandidatoVacanteEntity AS c WHERE c.idContexto =:idContexto AND c.seleccionado = 0",
					Integer.class).setParameter("idContexto", idContexto).getSingleResult();
			resultado = true;
		} catch (NoResultException e) {
			resultado = false;
		} catch (NonUniqueResultException e) {
			resultado = true;
		}
		return resultado;
	}

	public boolean existeIdContextoPostuladoVacante(Integer idContexto, Integer idInventarioVacante) {
		boolean resultado = false;
		try {
			em.createQuery(
					"SELECT c.idContexto FROM CandidatoVacanteEntity AS c WHERE c.idContexto =:idContexto AND c.postuladoVacante.inventarioVacante.idVacante =:idInventarioVacante AND c.seleccionado = 0",
					Integer.class).setParameter("idContexto", idContexto)
					.setParameter("idInventarioVacante", idInventarioVacante).getSingleResult();
			resultado = true;
		} catch (NoResultException e) {
			resultado = false;
		} catch (NonUniqueResultException e) {
			resultado = true;
		}
		return resultado;
	}

	public List<CandidatoVacanteEntity> listaCandidatoVacante() {
		return em.createQuery("SELECT c FROM CandidatoVacanteEntity AS c WHERE c.seleccionado = 0",
				CandidatoVacanteEntity.class).getResultList();
	}

	public List<CandidatoVacanteEntity> listaCandidatosVacantesNoSeleccionadosPorIdPostulado(
			Integer idPostuladoVacante) {
		try {
			return em
					.createQuery(
							"SELECT cv FROM CandidatoVacanteEntity AS cv WHERE cv.postuladoVacante.idPostuladoVacante =:idPostuladoVacante AND cv.seleccionado = 0",
							CandidatoVacanteEntity.class)
					.setParameter("idPostuladoVacante", idPostuladoVacante).getResultList();

		} catch (NoResultException e) {
			return null;
		}

	}

	public boolean tienePostulacionActivaCandidato(Integer idContexto, Integer idTipoCandidato) {
		try {
			em.createQuery(
					"SELECT c.idCandidatoVacante FROM CandidatoVacanteEntity AS c WHERE c.postuladoVacante.disponible = 'SI' AND c.tipoCandidato =:idTipoCandidato AND c.idContexto =:idContexto",
					Integer.class).setParameter("idTipoCandidato", idTipoCandidato)
					.setParameter("idContexto", idContexto).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		} catch (NonUniqueResultException exception) {
			throw new SistemaException("Se ha encontrado más de una postulación activa al empleado.",
					SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
		}
	}

}
