package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class PercepcionComprobanteRepository extends GenericRepository<PercepcionComprobanteEntity, Integer> {

	public List<PercepcionComprobanteEntity> obtenerPercepcionesPorIdComprobante(Integer idComprobante) {

		return super.em.createQuery("FROM PercepcionComprobanteEntity p WHERE p.idComprobante=:idComprobante",
				PercepcionComprobanteEntity.class).setParameter("idComprobante", idComprobante).getResultList();

	}

}
