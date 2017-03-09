/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-20:22:45
 */
public class PerfilEmpleadoRepository {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	
	/**
	 * Consulta el perfil del empleado por identificador
	 * */
	public PerfilEmpleadoEntiy perfilPorId(Integer idPerfilEmpleado) {
		return entityManager.find(PerfilEmpleadoEntiy.class, idPerfilEmpleado);
	}

}
