/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */
public class TipoMovimientoEmpleadoRepository extends GenericRepository<TipoMovimientoEmpleadoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2258006964600481355L;

	/**
	 * Consulta los movimientos por su nodo padre
	 * 
	 * @param idPadre
	 */
	public List<TipoMovimientoEmpleadoEntity> consultaMovimientosPorPadre(Integer idPadre) {
		List<TipoMovimientoEmpleadoEntity> movimientos = em.createQuery(
				"SELECT m FROM TipoMovimientoEmpleadoEntity AS m WHERE m.idPadre =:idPadre AND  m.visualizarMovimiento =true",
				TipoMovimientoEmpleadoEntity.class).setParameter("idPadre", idPadre).getResultList();
		return movimientos;
	}

	public Integer obtenerMovimientoPadre(Integer idMovimiento) {
		try {
			return em.createQuery(
					"SELECT t.idPadre FROM TipoMovimientoEmpleadoEntity AS t WHERE t.idTipoMovimiento =:idMovimiento and t.visualizarMovimiento = true",
					Integer.class).setParameter("idMovimiento", idMovimiento).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	public List<TipoMovimientoEmpleadoEntity> consultaMovimientos() {
		List<TipoMovimientoEmpleadoEntity> movimientos = em.createQuery(
				"SELECT m FROM TipoMovimientoEmpleadoEntity AS m WHERE m.visualizarMovimiento =true",
				TipoMovimientoEmpleadoEntity.class).getResultList();
		return movimientos;
	}

}
