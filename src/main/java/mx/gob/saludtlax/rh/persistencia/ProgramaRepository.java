package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class ProgramaRepository extends GenericRepository<ProgramaEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8662251444801112011L;

	public ProgramaEntity programaPorDescripcion(String descripcion) {
		try {
			return em.createQuery("SELECT p FROM ProgramaEntity AS p WHERE p.programa =:programa", ProgramaEntity.class)
					.setParameter("programa", descripcion).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	public List<ProgramaEntity> consultarProgramas() {
		return em.createQuery("SELECT p FROM ProgramaEntity AS p ORDER BY p.programa ASC ", ProgramaEntity.class)
				.getResultList();
	}

}
