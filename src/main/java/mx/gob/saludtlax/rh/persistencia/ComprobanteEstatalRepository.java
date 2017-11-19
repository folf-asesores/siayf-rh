
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ComprobanteEstatalRepository
        extends GenericRepository<ComprobanteEstatalEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5251285031414321963L;

    public List<ComprobanteEstatalEntity> obtenerLista() {
        List<ComprobanteEstatalEntity> comprobanteEstatalList = super.em
                .createQuery(
                        "FROM ComprobanteEstatalEntity AS c WHERE c.selloSAT = :selloSAT AND c.idComprobante = 12 ",
                        ComprobanteEstatalEntity.class)
                .setParameter("selloSAT", "").getResultList();
        return comprobanteEstatalList;
    }

}