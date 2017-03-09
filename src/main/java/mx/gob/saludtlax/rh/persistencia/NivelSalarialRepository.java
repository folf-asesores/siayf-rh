/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public class NivelSalarialRepository extends GenericRepository<NivelSalarialEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1707418442533924111L;

	public List<NivelSalarialEntity> listaNivelSalarial() {
		return em.createQuery("SELECT n FROM NivelSalarialEntity AS n", NivelSalarialEntity.class).getResultList();
	}

}
