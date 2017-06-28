/**
 *
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.excepciones.BusinessException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 01/04/2016-12:14:52
 */
public class DependienteEconomicoRepository extends
		GenericRepository<DependienteEconomicoEntity, Integer> {

    private static final long serialVersionUID = -6779559576630731185L;

	public List<DependienteEconomicoEntity> hijosEmpleadoPorIdEmpleado(
			Integer idEmpleado) {
		List<DependienteEconomicoEntity> hijos = em
				.createQuery(
						"SELECT h FROM DependienteEconomicoEntity AS h WHERE h.idEmpleado =:idEmpleado",
						DependienteEconomicoEntity.class)
				.setParameter("idEmpleado", idEmpleado).getResultList();
		return hijos;

	}

	public boolean existeDependientePorCurp(String curp) {
		try {
			em.createQuery(
					"SELECT h.idDependiente FROM DependienteEconomicoEntity AS h WHERE h.curp =:curp",
					Integer.class).setParameter("curp", curp).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}

	}

	public boolean existeDependientePorCurp(String curp, Integer idDependienteEconomico) {
		try {
			Integer idDepenedienteEncontrado = em.createQuery(
					"SELECT h.idDependiente FROM DependienteEconomicoEntity AS h WHERE h.curp =:curp",
					Integer.class).setParameter("curp", curp).getSingleResult();
			return idDependienteEconomico.intValue() != idDepenedienteEncontrado.intValue();
		} catch (NoResultException exception) {
			return false;
		}

	}

	public boolean existeDependientePorNombre(String nombreCompleto) {
		try {
			em.createQuery(
					"SELECT h.idDependiente FROM DependienteEconomicoEntity AS h WHERE h.nombreCompleto =:nombreCompleto",
					Integer.class)
					.setParameter("nombreCompleto", nombreCompleto)
					.getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}

	}
	public boolean existeDependientePorNombre(String nombreCompleto, Integer idDependienteEconomico) {
		try {
			Integer idDepenedienteEncontrado = em.createQuery(
					"SELECT h.idDependiente FROM DependienteEconomicoEntity AS h WHERE h.nombreCompleto =:nombreCompleto",
					Integer.class)
					.setParameter("nombreCompleto", nombreCompleto)
					.getSingleResult();
			return idDependienteEconomico.intValue() != idDepenedienteEncontrado.intValue();
		} catch (NoResultException exception) {
			return false;
		}

	}

	public List<DependienteEconomicoEntity> consultarDependientesEmpleado(
			Integer idEmpleado) {
		return em
				.createQuery(
						"SELECT d FROM DependienteEconomicoEntity AS d WHERE d.idEmpleado =:idEmpleado",
						DependienteEconomicoEntity.class)
				.setParameter("idEmpleado", idEmpleado).getResultList();

	}

	/**
	 * Obtiene el total de dependientes registrados con el parentesco enviado,
	 * ejemplo cuantos hijos tiene registrados
	 *
         * @param idEmpleado el ID del empleado.
	 * @param idParentesco el ID que la reaclión entre el empleado y el dependiente económico.
	 * */
	public int obtenerNumeroDependientesRegistradosParentesco(
			Integer idEmpleado, String idParentesco) {
		Long numero = em
				.createQuery(
						"SELECT COUNT(d.idDependiente) FROM DependienteEconomicoEntity AS d WHERE d.idEmpleado = :idEmpleado and d.idParentesco =:idParentesco",
						Long.class)
                        .setParameter("idParentesco", idParentesco)
                        .setParameter("idEmpleado", idEmpleado)
				.getSingleResult();
		return numero.intValue();

	}

	public String obtenerNombreDependientePorId(Integer idDependiente) {
		try {
			String nombreCompleto = em
					.createQuery(
							"SELECT d.nombreCompleto FROM DependienteEconomicoEntity AS d WHERE d.idDependiente =:idDependiente",
							String.class)
					.setParameter("idDependiente", idDependiente)
					.getSingleResult();
			return nombreCompleto;
		} catch (NoResultException exception) {
			throw new BusinessException(
					"obtenerNombreDependientePorId: Error obteniendo el nombre del dependiente, identificador no encontrado.");
		}

	}

    public DependienteEconomicoEntity obtenerDependienteEconimicoPorCurp(String curp) {
        try {
            return em.createQuery(
                    "FROM DependienteEconomicoEntity AS d WHERE d.curp =:curp ",
                    DependienteEconomicoEntity.class).setParameter("curp", curp).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }
}
