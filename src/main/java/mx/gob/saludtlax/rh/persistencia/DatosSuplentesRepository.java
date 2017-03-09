package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public class DatosSuplentesRepository extends
		GenericRepository<DatosSuplentesEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 313730294365173421L;

	public DatosSuplentesEntity obtenerDatosPorRfc(String rfc) {
		try {
			return em
					.createQuery(
							"SELECT d FROM DatosSuplentesEntity AS d WHERE d.rfc =:rfc",
							DatosSuplentesEntity.class)
					.setParameter("rfc", rfc).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			System.out.println("mas de un resultado " + rfc);
			return null;
		}
	}

}
