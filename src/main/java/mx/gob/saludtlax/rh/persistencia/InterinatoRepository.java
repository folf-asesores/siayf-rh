/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 27/09/2016 12:43:10
 * 
 */
public class InterinatoRepository extends GenericRepository<InterinatoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5218765620408273977L;

	public InterinatoEntity obtenerInterinatoPorIdSolicitud(Integer idSolicitud) {
		try {
			return em.createQuery("SELECT i FROM InterinatoEntity AS i WHERE i.idSolicitud =:idSolicitud",
					InterinatoEntity.class).setParameter("idSolicitud", idSolicitud).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}



}
