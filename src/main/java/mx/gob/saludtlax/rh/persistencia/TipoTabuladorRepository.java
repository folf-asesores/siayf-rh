/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

/**
 * @author eduardo
 *
 */
public class TipoTabuladorRepository extends GenericRepository<TipoTabuladorEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4818365157863704093L;

	public List<TipoTabuladorEntity> obtenerListaTipoTabulador() {
		return em.createQuery("SELECT t FROM TipoTabuladorEntity as t", TipoTabuladorEntity.class).getResultList();
	}
	
	

}
