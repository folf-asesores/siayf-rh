
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class HoraExtraComprobanteRepository
        extends GenericRepository<HoraExtraComprobanteEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = 9119950044906261207L;

    public List<HoraExtraComprobanteEntity> obtenerPercepcionesPorIdComprobante(
            Integer idComprobante) {

        return super.em.createQuery(
                "FROM HoraExtraComprobanteEntity h WHERE h.idComprobante=:idComprobante",
                HoraExtraComprobanteEntity.class)
                .setParameter("idComprobante", idComprobante).getResultList();

    }

}
