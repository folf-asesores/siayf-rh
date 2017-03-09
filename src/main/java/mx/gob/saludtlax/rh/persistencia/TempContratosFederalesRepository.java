/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/08/2016 11:34:39
 * 
 */
public class TempContratosFederalesRepository extends
		GenericRepository<TempContratosFederalesEntity, Integer> {

	public TempContratosFederalesEntity obtenerContratoFederalPorRfc(String rfc) {
		try {
			return em
					.createQuery(
							"SELECT t FROM TempContratosFederalesEntity AS t WHERE t.rfc =:rfc AND t.duplicados =false",
							TempContratosFederalesEntity.class)
					.setParameter("rfc", rfc).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}
}
