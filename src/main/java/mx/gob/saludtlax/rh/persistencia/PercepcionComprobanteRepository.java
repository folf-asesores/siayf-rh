
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class PercepcionComprobanteRepository
        extends GenericRepository<PercepcionComprobanteEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 7987703554142466890L;

    public List<PercepcionComprobanteEntity> obtenerPercepcionesPorIdComprobante(
            Integer idComprobante) {

        return super.em.createQuery(
                "FROM PercepcionComprobanteEntity p WHERE p.idComprobante=:idComprobante",
                PercepcionComprobanteEntity.class)
                .setParameter("idComprobante", idComprobante).getResultList();

    }

}
