package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class DependenciasRepository extends
		GenericRepository<DependenciaEntity, Integer> {
	
	
	public List<DependenciaEntity> consultarDependencias() {
		List<DependenciaEntity> dependencias = em.createQuery(
				"select d from DependenciaEntity  AS d", DependenciaEntity.class)
				.getResultList();
		return dependencias;
	}

}