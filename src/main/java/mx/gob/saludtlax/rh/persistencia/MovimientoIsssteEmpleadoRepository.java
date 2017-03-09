/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.empleado.issste.InfoMovimientoIsssteDTO;

/**
 * @author Eduardo Mex
 *
 */
public class MovimientoIsssteEmpleadoRepository extends GenericRepository<MovimientoIsssteEmpleadoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040798906794844669L;

	public boolean existeEmpleado(Integer idEmpleado) {
		boolean resultado = false;
		try {
			Integer idEmpleadoConfirmado = em.createQuery(
					"SELECT m.empleado.idEmpleado FROM MovimientoIsssteEmpleadoEntity AS m WHERE m.empleado.idEmpleado =:idEmpleado",
					Integer.class).setParameter("idEmpleado", idEmpleado).getSingleResult();
			resultado = true;
		} catch (NoResultException e) {
			resultado = false;
		}
		return resultado;
	}

	public List<MovimientoIsssteEmpleadoEntity> obtenerListaMovimientoPorCriterio(String criterio) {
		try {
			List<MovimientoIsssteEmpleadoEntity> lista = em.createQuery("SELECT m FROM MovimientoIsssteEmpleadoEntity AS m WHERE (m.empleado.nombreCompleto LIKE :criterio OR m.empleado.rfc LIKE :criterio OR m.empleado LIKE :criterio)", MovimientoIsssteEmpleadoEntity.class)
					.setParameter("criterio", "%" + criterio + "%").getResultList();

			return lista;
		} catch (NoResultException e) {
			return null;
		}
	}

}
