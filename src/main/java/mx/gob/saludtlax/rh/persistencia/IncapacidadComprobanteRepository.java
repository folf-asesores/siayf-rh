
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class IncapacidadComprobanteRepository extends GenericRepository<IncapacidadComprobanteEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = 7594756808445562703L;

    public List<IncapacidadComprobanteEntity> obtenerPercepcionesPorIdComprobante(Integer idComprobante) {

        return super.em.createQuery("FROM IncapacidadComprobanteEntity i WHERE i.idComprobante=:idComprobante", IncapacidadComprobanteEntity.class)
                .setParameter("idComprobante", idComprobante).getResultList();

    }

}
