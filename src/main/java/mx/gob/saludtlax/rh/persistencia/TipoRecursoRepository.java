/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public class TipoRecursoRepository extends GenericRepository<TipoRecursoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2654173405934027833L;

	public List<TipoRecursoEntity> consultarTipoRecurso() {
		List<TipoRecursoEntity> tipos_recursos_temp = em
				.createQuery("select t from TipoRecursoEntity AS t", TipoRecursoEntity.class).getResultList();
		return tipos_recursos_temp;
	}

}
