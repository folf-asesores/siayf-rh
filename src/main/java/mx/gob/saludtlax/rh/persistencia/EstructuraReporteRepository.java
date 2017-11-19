/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 *
 */
public class EstructuraReporteRepository extends GenericRepository<EstructuraReporteEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 8626183799985381858L;

    public EstructuraReporteEntity obtenerPorIdClasificacion(Integer idClasificacion) {
        try {
            return em.createQuery("SELECT e FROM EstructuraReporteEntity AS e WHERE e.clasificacionReporte.idClasificacionReporte =:idClasificacion",
                    EstructuraReporteEntity.class).setParameter("idClasificacion", idClasificacion).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
