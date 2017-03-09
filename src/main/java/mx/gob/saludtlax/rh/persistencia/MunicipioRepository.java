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
 * @since 07/03/2016-21:09:47
 */
public class MunicipioRepository extends
		GenericRepository<MunicipiosEntity, Integer> {

	public List<MunicipiosEntity> municipios() {
		List<MunicipiosEntity> municipios = em.createQuery(
				"SELECT m FROM MunicipiosEntity AS m", MunicipiosEntity.class)
				.getResultList();
		return municipios;
	}

	public List<MunicipiosEntity> consultarMunicipiosPorEstado(Integer idEstado) {
		List<MunicipiosEntity> municipios = em
				.createQuery(
						"SELECT m FROM MunicipiosEntity AS m WHERE m.idEstado =:idEstado",
						MunicipiosEntity.class)
				.setParameter("idEstado", idEstado).getResultList();
		return municipios;
	}
}
