/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 17:57:03
 * 
 */
public class ProyectoTempRepository extends GenericRepository<ProyectoTempEntity, Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1264497675051377457L;
	private static final String CONSULTAR_PROYECTOS_POR_EJERCICIO_FISCAL = "from ProyectoTempEntity as proyecto"
			+ " where proyecto.ejercicioFiscal = :ejercicioFiscal";
	private static final String EXISTE_PROYECTO_EN_EJERCICIO_FISCAL = "select" + "   case count(proyecto.nombre)"
			+ "      when 0 then false" + "      else true" + "   end" + " from ProyectoTempEntity as proyecto"
			+ "    where proyecto.ejercicioFiscal = :ejercicioFiscal" + "        and proyecto.nombre = :nombreProyecto";

	public List<ProyectoTempEntity> proyectosPorUnidadDependencia(Integer idUnidad, Integer idDependencia) {
		List<ProyectoTempEntity> proyectos = em
				.createQuery(
						"SELECT p FROM ProyectoTempEntity AS p WHERE p.idDependencia =:idDependencia AND p.idUnidadResponsable =:idUnidadResponsable",
						ProyectoTempEntity.class)
				.setParameter("idDependencia", idDependencia).setParameter("idUnidadResponsable", idUnidad)
				.getResultList();
		return proyectos;
	}

	public List<ProyectoTempEntity> consultarProyectos() {
		List<ProyectoTempEntity> proyectos = em
				.createQuery("SELECT p FROM ProyectoTempEntity AS p", ProyectoTempEntity.class).getResultList();
		return proyectos;
	}

	/**
	 * Retorna una lista con los proyectos según su ejercicio fiscal.
	 * 
	 * @param ejercicioFiscal
	 *            el ejercicio fiscal que servira como criterio de busqueda.
	 * @return una lista con los proyectos del ejercicio fiscal indicado.
	 */
	public List<ProyectoTempEntity> consultarProyectosPorEjercicioFiscal(Integer ejercicioFiscal) {
		TypedQuery<ProyectoTempEntity> query = em.createQuery(CONSULTAR_PROYECTOS_POR_EJERCICIO_FISCAL, classType);

		return query.setParameter("ejercicioFiscal", ejercicioFiscal).getResultList();
	}

	/**
	 * Permite verificar si ya existe un proyecto con el nombre indicado en el
	 * ejercicio fiscal.
	 * 
	 * @param nombre
	 *            el nombre del proyecto a consultar.
	 * @param ejercicioFiscal
	 *            el ejercicio fiscal a consultar.
	 * @return devuelve falso si y solo no existe ningún un proyecto con el
	 *         nombre del proyecto.
	 */
	public boolean existeProyectoEnEjercicioFiscal(String nombre, int ejercicioFiscal) {
		TypedQuery<Boolean> query = em.createQuery(EXISTE_PROYECTO_EN_EJERCICIO_FISCAL, Boolean.class);
		query.setParameter("nombreProyecto", nombre);
		query.setParameter("ejercicioFiscal", ejercicioFiscal);

		return query.getSingleResult();
	}

	public List<ProyectoTempEntity> consultarProyecto() {
		List<ProyectoTempEntity> proyectos = em
				.createQuery("select p from ProyectoTempEntity AS p", ProyectoTempEntity.class).getResultList();
		return proyectos;
	}

}
