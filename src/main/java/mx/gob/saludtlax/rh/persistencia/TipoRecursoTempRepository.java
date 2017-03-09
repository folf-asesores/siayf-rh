package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class TipoRecursoTempRepository extends
		GenericRepository<TipoRecursoTempEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6072538028711183298L;

	public List<TipoRecursoTempEntity> consultarTipoRecurso() {
		List<TipoRecursoTempEntity> tipos_recursos_temp = em.createQuery(
				"select t from TipoRecursoTempEntity AS t",
				TipoRecursoTempEntity.class).getResultList();
		return tipos_recursos_temp;
	}
}
