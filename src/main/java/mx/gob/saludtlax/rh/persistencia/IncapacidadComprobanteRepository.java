package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class IncapacidadComprobanteRepository extends GenericRepository<IncapacidadComprobanteEntity, Integer> {
	public List<IncapacidadComprobanteEntity> obtenerPercepcionesPorIdComprobante(Integer idComprobante) {

		return super.em
				.createQuery("FROM IncapacidadComprobanteEntity i WHERE i.idComprobante=:idComprobante",
						IncapacidadComprobanteEntity.class)
				.setParameter("idComprobante", idComprobante).getResultList();

	}

}
