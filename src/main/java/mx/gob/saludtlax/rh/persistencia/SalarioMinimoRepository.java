package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class SalarioMinimoRepository extends GenericRepository<SalarioMinimoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -996125250499849083L;

	private final static String BUSCAR_SALARIO_MININO_ACTURA = "FROM SalarioMinimoEntity s ORDER BY s.fecha DESC ";

	public SalarioMinimoEntity obtenerSalarioMinimoActual() {
		SalarioMinimoEntity salarioMinimoEntity = null;
		try {
			salarioMinimoEntity = em.createQuery(BUSCAR_SALARIO_MININO_ACTURA, SalarioMinimoEntity.class)
					.setFirstResult(0).setMaxResults(1).getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
			salarioMinimoEntity = null;
		}
		return salarioMinimoEntity;

	}

	public List<SalarioMinimoEntity> obtenerListaSalarioMinimo() {
		return em.createQuery("SELECT s FROM SalarioMinimoEntity AS s", SalarioMinimoEntity.class).getResultList();
	}

}
