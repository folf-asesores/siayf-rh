/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;


/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/06/2016 23:45:47
 */
public class ComprobanteEstudioRepository extends
		GenericRepository<ComprobanteEstudioEntity, Integer> {

	public List<ComprobanteEstudioEntity> consultarComprobantesEstudios() {
		return em.createQuery("SELECT c FROM ComprobanteEstudioEntity AS c",
				ComprobanteEstudioEntity.class).getResultList();
	}
}
