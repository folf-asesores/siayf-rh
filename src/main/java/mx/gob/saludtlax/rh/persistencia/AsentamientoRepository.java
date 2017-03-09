/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-21:11:33
 */
public class AsentamientoRepository extends
		GenericRepository<AsentamientoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7585551820421827539L;

	public List<AsentamientoEntity> consultarAsentamientos() {
		List<AsentamientoEntity> lista = em.createQuery(
				"SELECT p FROM AsentamientoEntity AS p",
				AsentamientoEntity.class).getResultList();
		return lista;
	}

	/**
	 * Consulta los asentamientos asignados a un municipio
	 * 
	 * @param idMunicipio
	 * */
	public List<AsentamientoEntity> consultarAsentamientosPorIdMunicipio(
			Integer idMunicipio) {
		List<AsentamientoEntity> poblaciones = em
				.createQuery(
						"SELECT p FROM AsentamientoEntity AS p WHERE p.idMunicipio =:idMunicipio",
						AsentamientoEntity.class)
				.setParameter("idMunicipio", idMunicipio).getResultList();
		return poblaciones;

	}
}
