
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 *
 * @author José Pablo
 *
 */
public class OtrosPagosComprobantesReporitory extends GenericRepository<OtrosPagosComprobantesEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = 4925798740557612363L;

    public List<OtrosPagosComprobantesEntity> obtenerOtrosPagosPorIdComprobante(Integer idComprobante) {

        return super.em.createQuery("FROM OtrosPagosComprobantesEntity p WHERE p.idComprobante=:idComprobante", OtrosPagosComprobantesEntity.class)
                .setParameter("idComprobante", idComprobante).getResultList();

    }
}
