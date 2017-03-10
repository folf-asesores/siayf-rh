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

	private static final long serialVersionUID = -1159203469352201806L;

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

	public Boolean esUsuarioAutorizaNomina(OperacionSistemaEntity accion, Integer idEntidadContexto,
			Integer idUsuario) {
		List<BuzonAutorizacionesEntity> autorizaciones = em
				.createQuery(
						" SELECT b                                                                       "
								+ " FROM BuzonAutorizacionesEntity AS b                                  "
								+ " WHERE                                                                "
								+ " b.accion =:accion                                                    "
								+ " AND                                                                  "
								+ " b.idEntidadContexto =:idEntidadContexto                              "
								+ " AND                                                                  "
								+ " b.idUsuario =:idUsuario                                              ",
						BuzonAutorizacionesEntity.class)
				.setParameter("accion", accion).setParameter("idEntidadContexto", idEntidadContexto)
				.setParameter("idUsuario", idUsuario).getResultList();
		return !autorizaciones.isEmpty();
	}
}