/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 17:57:03
 * 
 */
public class ProyectoTempRepository extends
		GenericRepository<ProyectoTempEntity, Integer> {

	public List<ProyectoTempEntity> proyectosPorUnidadDependencia(
			Integer idUnidad, Integer idDependencia) {
		List<ProyectoTempEntity> proyectos = em
				.createQuery(
						"SELECT p FROM ProyectoTempEntity AS p WHERE p.idDependencia =:idDependencia AND p.idUnidadResponsable =:idUnidadResponsable",
						ProyectoTempEntity.class)
				.setParameter("idDependencia", idDependencia)
				.setParameter("idUnidadResponsable", idUnidad).getResultList();
		return proyectos;
	}

	public List<ProyectoTempEntity> consultarProyectos() {
		List<ProyectoTempEntity> proyectos = em.createQuery(
				"SELECT p FROM ProyectoTempEntity AS p",
				ProyectoTempEntity.class).getResultList();
		return proyectos;
	}

}
