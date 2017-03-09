/*
 * ProyectoRepository.java
 * Creado el 23/07/2016 09:07:25 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ProyectoRepository extends
		GenericRepository<ProyectoEntity, Integer> {

	private static final String CONSULTAR_PROYECTOS_POR_EJERCICIO_FISCAL = "from ProyectoEntity as proyecto"
			+ " where proyecto.ejercicioFiscal = :ejercicioFiscal";
	private static final String EXISTE_PROYECTO_EN_EJERCICIO_FISCAL = "select"
			+ "   case count(proyecto.nombre)" + "      when 0 then false"
			+ "      else true" + "   end" + " from ProyectoEntity as proyecto"
			+ "    where proyecto.ejercicioFiscal = :ejercicioFiscal"
			+ "        and proyecto.nombre = :nombreProyecto";

	/**
	 * Retorna una lista con los proyectos según su ejercicio fiscal.
	 * 
	 * @param ejercicioFiscal
	 *            el ejercicio fiscal que servira como criterio de busqueda.
	 * @return una lista con los proyectos del ejercicio fiscal indicado.
	 */
	public List<ProyectoEntity> consultarProyectosPorEjercicioFiscal(
			Integer ejercicioFiscal) {
		TypedQuery<ProyectoEntity> query = em.createQuery(
				CONSULTAR_PROYECTOS_POR_EJERCICIO_FISCAL, classType);

		return query.setParameter("ejercicioFiscal", ejercicioFiscal)
				.getResultList();
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
	public boolean existeProyectoEnEjercicioFiscal(String nombre,
			int ejercicioFiscal) {
		TypedQuery<Boolean> query = em.createQuery(
				EXISTE_PROYECTO_EN_EJERCICIO_FISCAL, Boolean.class);
		query.setParameter("nombreProyecto", nombre);
		query.setParameter("ejercicioFiscal", ejercicioFiscal);

		return query.getSingleResult();
	}

	public List<ProyectoTempEntity> consultarProyecto() {
		List<ProyectoTempEntity> proyectos = em.createQuery(
				"select p from ProyectoTempEntity AS p",
				ProyectoTempEntity.class).getResultList();
		return proyectos;
	}
}
