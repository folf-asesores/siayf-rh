/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 20:35:56
 */
public class ClasificacionNombramientoRepository extends GenericRepository<ClasificacionNombramientoEntity, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7301529531357971089L;

	public List<ClasificacionNombramientoEntity> listaClasificacionNombramiento() {
		try {
			return em.createQuery("SELECT c FROM ClasificacionNombramientoEntity AS c",
					ClasificacionNombramientoEntity.class).getResultList();
		} catch (NullPointerException ex) {
			return null;
		}
	}

}
