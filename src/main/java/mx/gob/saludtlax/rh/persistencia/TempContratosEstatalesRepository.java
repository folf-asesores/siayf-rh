/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/08/2016 21:49:13
 */
public class TempContratosEstatalesRepository extends
		GenericRepository<TempContratosEstatalesEntity, Integer> {

	public TempContratosEstatalesEntity contratoEstatalPorRfc(String rfc) {
		try {
			return em
					.createQuery(
							"SELECT c FROM TempContratosEstatalesEntity AS c WHERE c.rfc =:rfc",
							TempContratosEstatalesEntity.class)
					.setParameter("rfc", rfc).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}

	}

}
