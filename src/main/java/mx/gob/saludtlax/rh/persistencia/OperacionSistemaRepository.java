/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:47:02
 */
public class OperacionSistemaRepository
        extends GenericRepository<OperacionSistemaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -6819079619931387392L;

    public List<OperacionSistemaEntity> consultarOperaciones() {
        List<OperacionSistemaEntity> lista = em
                .createQuery(" SELECT o FROM OperacionSistemaEntity AS o",
                        OperacionSistemaEntity.class)
                .getResultList();
        return lista;
    }

    public String obtenerDescripcionOperacion(Integer idOperacion) {
        return em.createQuery(
                "SELECT o.operacion FROM OperacionSistemaEntity AS o WHERE o.idOperacion =:idOperacion",
                String.class).setParameter("idOperacion", idOperacion)
                .getSingleResult();
    }

}
