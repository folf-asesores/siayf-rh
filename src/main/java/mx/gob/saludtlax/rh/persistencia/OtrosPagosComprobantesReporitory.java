package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * 
 * @author José Pablo
 *
 */
public class OtrosPagosComprobantesReporitory extends GenericRepository<OtrosPagosComprobantesEntity, Integer> {
	public List<OtrosPagosComprobantesEntity> obtenerOtrosPagosPorIdComprobante(Integer idComprobante) {

		return super.em.createQuery("FROM OtrosPagosComprobantesEntity p WHERE p.idComprobante=:idComprobante",
				OtrosPagosComprobantesEntity.class).setParameter("idComprobante", idComprobante).getResultList();

	}
}
