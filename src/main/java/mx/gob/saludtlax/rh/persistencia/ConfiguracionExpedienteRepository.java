/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 18:03:58
 * 
 */
public class ConfiguracionExpedienteRepository extends
		GenericRepository<ConfiguracionExpedienteEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3527694552368371190L;

	public List<ConfiguracionExpedienteEntity> consultaDocumentosPorClasificacionExpediente(
			String clasificacion) {
		return em
				.createQuery(
						"SELECT c FROM ConfiguracionExpedienteEntity AS c WHERE c.clasificacionExpediente=:clasificacion",
						ConfiguracionExpedienteEntity.class)
				.setParameter("clasificacion", clasificacion).getResultList();
	}

}
