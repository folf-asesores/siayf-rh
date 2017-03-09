/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/08/2016 22:50:50
 */
public class DependenciaTempRepository extends GenericRepository<DependenciaTempEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5053724808417971804L;

	public List<DependenciaTempEntity> consultarDependencias() {
		return em.createQuery("SELECT d FROM DependenciaTempEntity AS d", DependenciaTempEntity.class).getResultList();
	}

}
