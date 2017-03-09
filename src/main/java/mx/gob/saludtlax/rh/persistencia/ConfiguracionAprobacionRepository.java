/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:48:09
 */
public class ConfiguracionAprobacionRepository extends GenericRepository<ConfiguracionAprobacionEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1218998568053386871L;

	public List<ConfiguracionAprobacionEntity> usuariosPorAccion(Integer idAccion) {
		List<ConfiguracionAprobacionEntity> acciones = em
				.createQuery("SELECT u FROM ConfiguracionAprobacionEntity AS u WHERE u.accion.idOperacion =:idAccion",
						ConfiguracionAprobacionEntity.class)
				.setParameter("idAccion", idAccion).getResultList();

		return acciones;
	}

	public List<ConfiguracionAprobacionEntity> usuariosPorAccionMovimiento(Integer idAccion, Integer idMovimiento) {
		List<ConfiguracionAprobacionEntity> acciones = em
				.createQuery(
						"SELECT u FROM ConfiguracionAprobacionEntity AS u WHERE u.accion.idOperacion =:idAccion AND u.tipoMovimientoEmpleado.idTipoMovimiento =:idMovimiento",
						ConfiguracionAprobacionEntity.class)
				.setParameter("idAccion", idAccion).setParameter("idMovimiento", idMovimiento).getResultList();

		return acciones;
	}

	public List<ConfiguracionAprobacionEntity> usuariosPorCriterio(Integer activo) {
		List<ConfiguracionAprobacionEntity> acciones = em
				.createQuery("SELECT u FROM ConfiguracionAprobacionEntity AS u WHERE u.accion.idOperacion =:idAccion",
						ConfiguracionAprobacionEntity.class)
				.setParameter("activo", activo).getResultList();

		return acciones;
	}

	public List<ConfiguracionAprobacionEntity> usuarios() {
		try {
			List<ConfiguracionAprobacionEntity> acciones = em
					.createQuery("SELECT u FROM ConfiguracionAprobacionEntity AS u ",
							ConfiguracionAprobacionEntity.class)
					.getResultList();
			return acciones;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Integer usuarioPorAcciones(Integer idAccion, Integer idUsuario) {
		try {
			Integer existe = em
					.createQuery(
							"SELECT u.accion.idOperacion FROM ConfiguracionAprobacionEntity AS u "
									+ "WHERE u.accion.idOperacion =:idAccion " + "AND u.usuario.idUsuario =:idUsuario",
							Integer.class)
					.setParameter("idAccion", idAccion).setParameter("idUsuario", idUsuario).getSingleResult();
			return existe;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<ConfiguracionAprobacionEntity> usuariosInactivos() {
		List<ConfiguracionAprobacionEntity> acciones = em
				.createQuery("SELECT u FROM ConfiguracionAprobacionEntity AS u WHERE u.activo = 0",
						ConfiguracionAprobacionEntity.class)
				.getResultList();
		return acciones;
	}

	// <<<<<Listas por Vistas>>>>>

	public List<ConfiguracionAprobacionEntity> consultarConfiguraciones() {
		return em.createQuery("SELECT c FROM ConfiguracionAprobacionEntity AS c", ConfiguracionAprobacionEntity.class)
				.getResultList();
	}

	public List<ConfiguracionAprobacionEntity> consultarUsuariosConfiguraciones(Integer idOperacionSistema) {
		return em
				.createQuery(
						"SELECT c FROM ConfiguracionAprobacionEntity AS c WHERE c.accion.idOperacion =:idOperacion",
						ConfiguracionAprobacionEntity.class)
				.setParameter("idOperacion", idOperacionSistema).getResultList();
	}

	public boolean tieneProcesoAprobacionMovimientoPersonal(Integer idMovimiento) {
		try {
			em.createQuery(
					"SELECT c.idConfiguracionAprobacion FROM ConfiguracionAprobacionEntity AS c WHERE c.accion.idOperacion =5 AND c.tipoMovimientoEmpleado.idTipoMovimiento =:idTipoMovimiento ",
					Integer.class).setParameter("idTipoMovimiento", idMovimiento).getSingleResult();
			return true;

		} catch (NoResultException exception) {
			return false;
		}

	}

}
