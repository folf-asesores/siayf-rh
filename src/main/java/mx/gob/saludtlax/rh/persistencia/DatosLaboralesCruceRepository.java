/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 15/11/2016 17:25:52
 */
public class DatosLaboralesCruceRepository extends
		GenericRepository<DatosLaboralesCruceEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7269106581411692456L;

	public List<DatosLaboralesCruceEntity> obtenerDatoLaboralEventual(String rfc) {
		return em
				.createQuery(
						"SELECT d FROM DatosLaboralesCruceEntity AS d WHERE d.rfc =:rfc AND d.estatus =:estatus",
						DatosLaboralesCruceEntity.class)
				.setParameter("rfc", rfc).setParameter("estatus", "A")
				.getResultList();

	}

	public DatosLaboralesCruceEntity obtenerDatoLaboral(String rfc,
			String nombramiento) {
		try {
			return em
					.createQuery(
							"SELECT d FROM DatosLaboralesCruceEntity AS d WHERE d.rfc =:rfc AND "
									+ "d.estatus =:estatus AND d.nombramiento =:nombramiento",
							DatosLaboralesCruceEntity.class)
					.setParameter("rfc", rfc).setParameter("estatus", "A")
					.setParameter("nombramiento", nombramiento)
					.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			return null;
		}

	}

	public List<DatosLaboralesCruceEntity> obtenerDatoLaboral(String rfc) {
		return em
				.createQuery(
						"SELECT d FROM DatosLaboralesCruceEntity AS d WHERE d.rfc =:rfc AND d.estatus =:estatus",
						DatosLaboralesCruceEntity.class)
				.setParameter("rfc", rfc).setParameter("estatus", "A")
				.getResultList();

	}

	public List<DatosLaboralesCruceEntity> obtenerDatoLaboralSuplente(String rfc) {
		return em
				.createQuery(
						"SELECT d FROM DatosLaboralesCruceEntity AS d WHERE d.rfc =:rfc AND d.estatus =:estatus AND d.nombramiento =:nombramiento",
						DatosLaboralesCruceEntity.class)
				.setParameter("rfc", rfc).setParameter("estatus", "A")
				.setParameter("nombramiento", "V").getResultList();

	}

}
