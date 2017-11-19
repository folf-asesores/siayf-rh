
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class CentroPagoRepository
        extends GenericRepository<CentroPagoEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = 7428219665911361355L;

    public List<CentroPagoEntity> consultarCentrosPago() {
        List<CentroPagoEntity> centros_pago = em
                .createQuery("select c from CentroPagoEntity as c",
                        CentroPagoEntity.class)
                .getResultList();
        return centros_pago;
    }

}