/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:17:29
 */
public class BuzonAutorizacionesRepository extends
		GenericRepository<BuzonAutorizacionesEntity, Integer> {

	/**
	 * Consulta el buzon de autorizaciones del usuario por estatus
	 * 
	 * @param idUsuario
	 * @param autorizado
	 *            (verdadero si quiere autorizaciones aprobadas, falso si quiere
	 *            pendientes)
	 * */
	public List<BuzonAutorizacionesEntity> solicitudesAutorizacionesUsuarioPorEstatus(
			Integer idUsuario, boolean autorizado) {

		List<BuzonAutorizacionesEntity> autorizaciones = em
				.createQuery(
						"SELECT b FROM BuzonAutorizacionesEntity AS b WHERE b.idUsuario =:idUsuario AND b.autorizado =:autorizado",
						BuzonAutorizacionesEntity.class)
				.setParameter("autorizado", autorizado)
				.setParameter("idUsuario", idUsuario).getResultList();
		return autorizaciones;
	}


}
