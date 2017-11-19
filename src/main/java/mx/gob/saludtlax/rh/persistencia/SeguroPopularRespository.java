/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 09/12/2016 14:33:28
 */
public class SeguroPopularRespository extends GenericRepository<SeguroPopularEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 2022366011722377868L;

    public List<SeguroPopularEntity> consultarSeguroPopularSinPuesto() {
        return em.createQuery("SELECT s FROM SeguroPopularEntity AS s WHERE s.idInventario IS NULL", SeguroPopularEntity.class).getResultList();
    }

}
