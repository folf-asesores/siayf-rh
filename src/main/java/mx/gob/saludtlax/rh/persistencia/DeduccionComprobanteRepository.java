
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class DeduccionComprobanteRepository extends GenericRepository<DeduccionComprobanteEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -5724218417502246983L;

    public List<DeduccionComprobanteEntity> obtenerPercepcionesPorIdComprobante(Integer idComprobante) {

        return super.em.createQuery("FROM DeduccionComprobanteEntity d WHERE d.idComprobante=:idComprobante", DeduccionComprobanteEntity.class)
                .setParameter("idComprobante", idComprobante).getResultList();

    }

}
