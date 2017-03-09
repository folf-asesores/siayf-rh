/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 *
 */
public class EstructuraReporteRepository extends GenericRepository<EstructuraReporteEntity, Integer> {

	public EstructuraReporteEntity obtenerPorIdClasificacion(Integer idClasificacion) {
		try {
			return em
					.createQuery(
							"SELECT e FROM EstructuraReporteEntity AS e WHERE e.clasificacionReporte.idClasificacionReporte =:idClasificacion",
							EstructuraReporteEntity.class)
					.setParameter("idClasificacion", idClasificacion).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
