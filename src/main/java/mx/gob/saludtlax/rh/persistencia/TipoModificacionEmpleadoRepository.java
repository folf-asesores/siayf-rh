/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-22:19:43
 */
public class TipoModificacionEmpleadoRepository extends GenericRepository<TipoModificacionEmpleadoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7217142933884066432L;

	public TipoModificacionEmpleadoEntity obtenerTipoModificacionPorCriterio(String criterio) {

		try {
			return em.createQuery("SELECT t FROM TipoModificacionEmpleadoEntity AS t WHERE t.movimiento=:criterio",
					TipoModificacionEmpleadoEntity.class).setParameter("criterio", criterio).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}
