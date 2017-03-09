/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoSuplenteEntity;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 13/01/2017 12:32:14
 */
public class MovimientoSuplenteRepository extends GenericRepository<MovimientoSuplenteEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1061037579812507971L;

	public Integer obtenerVacacionesEjercicioFiscal(int ejercicioFiscal, Integer idSuplente) {
		return em
				.createQuery(
						"SELECT SUM(m.totalDias) FROM MovimientoSuplenteEntity AS m WHERE m.suplente.idSuplenteAutorizado =:idSuplente "
								+ "AND m.ejercicioFiscalPeriodo =:ejercicio",
						Integer.class)
				.setParameter("ejercicio", ejercicioFiscal).setParameter("idSuplente", idSuplente).getSingleResult();

	}

}
