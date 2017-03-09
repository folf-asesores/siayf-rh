package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class HoraExtraComprobanteRepository extends GenericRepository<HoraExtraComprobanteEntity, Integer> {
	public List<HoraExtraComprobanteEntity> obtenerPercepcionesPorIdComprobante(Integer idComprobante) {

		return super.em.createQuery("FROM HoraExtraComprobanteEntity h WHERE h.idComprobante=:idComprobante",
				HoraExtraComprobanteEntity.class).setParameter("idComprobante", idComprobante).getResultList();

	}

}
