/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2016-21:49:22
 */
public class DepartamentoRepository {

	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	/**
	 * Consulta el departamento por identificador.
	 * 
	 * @param idDepartamento
	 * */
	public DepartamentoEntity departamentoPorId(Integer idDepartamento) {
		return entityManager.find(DepartamentoEntity.class, idDepartamento);
	}

	/**
	 * Retorna el listado de departamentos
	 * */
	public List<DepartamentoEntity> departamentos() {
		List<DepartamentoEntity> departamentos = entityManager.createQuery(
				"SELECT d FROM DepartamentoEntity AS d",
				DepartamentoEntity.class).getResultList();
		return departamentos;
	}
}
