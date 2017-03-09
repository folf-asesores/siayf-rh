/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 30/10/2016 14:37:46
 */
public class ConfiguracionQuincenaRepository extends GenericRepository<ConfiguracionQuincenaEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4566746267905189463L;

	public Integer obtenerConfiguracionQuincena(int mes, int dia) {

		try {
			return em.createQuery(
					"SELECT c.numeroQuincena FROM ConfiguracionQuincenaEntity AS c WHERE c.mes =:mes AND :dia BETWEEN c.limiteInferior AND c.limiteSuperior",
					Integer.class).setParameter("mes", mes).setParameter("dia", dia).getSingleResult();
		} catch (NoResultException exception) {
			throw new ValidacionException("No se encontró registro de quincena para el intervalo especificado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

	}

	public Integer obtenerUltimoDiaMes(int mes) {

		try {
			return em.createQuery(
					"SELECT c.limiteSuperior FROM ConfiguracionQuincenaEntity AS c WHERE c.mes =:mes AND c.limiteInferior = 16",
					Integer.class).setParameter("mes", mes).getSingleResult();
		} catch (NoResultException exception) {
			throw new ValidacionException("No se encontró registro de quincena para el intervalo especificado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

	}

	public Integer mesPorNumQuincena(Integer numQuincena) {
		return em.createQuery("SELECT c.mes FROM ConfiguracionQuincenaEntity AS c WHERE c.numeroQuincena =:qna",
				Integer.class).setParameter("qna", numQuincena).getSingleResult();

	}

	public boolean esValidaQuincena(Integer numQuincena) {
		try {
			em.createQuery(
					"SELECT c.idConfiguracion FROM ConfiguracionQuincenaEntity AS c WHERE c.numeroQuincena =:qna",
					Integer.class).setParameter("qna", numQuincena).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}

	}

}
