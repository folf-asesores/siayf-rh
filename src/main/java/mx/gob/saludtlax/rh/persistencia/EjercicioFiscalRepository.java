package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class EjercicioFiscalRepository extends GenericRepository<EjercicioFiscalEntity, Integer> {

	private static final long serialVersionUID = -6340034374254111151L;

	public List<EjercicioFiscalEntity> obtenerListaEjercicioFiscal() {
		return em.createQuery("SELECT e FROM EjercicioFiscalEntity AS e", EjercicioFiscalEntity.class).getResultList();
	}

	public EjercicioFiscalEntity obtenerEjercioPorEjercicionFiscal(Integer ejercicioFiscal) {
		try {
			return em
					.createQuery("SELECT e FROM EjercicioFiscalEntity AS e WHERE e.ejercicioFiscal =:ejercicioFiscal",
							EjercicioFiscalEntity.class)
					.setParameter("ejercicioFiscal", ejercicioFiscal).getSingleResult();
		} catch (NoResultException noResultException) {
			return null;
		}
	}

}
