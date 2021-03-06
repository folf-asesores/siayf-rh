/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 *
 */
public class SeguroVidaRepository extends GenericRepository<SeguroVidaEntity, Integer> {

	public boolean existeNumeroExpediente(String numeroExpediente) {
		boolean resultado = false;
		try {
			SeguroVidaEntity seguroVidaEntity = em
					.createQuery("SELECT s FROM SeguroVidaEntity AS s WHERE s.numeroExpediente =:numeroExpediente",
							SeguroVidaEntity.class)
					.setParameter("numeroExpediente", numeroExpediente).getSingleResult();

			resultado = true;
		} catch (NoResultException e) {
			resultado = false;
		}
		return resultado;
	}

	public Integer existeEmpleado(Integer idEmpleado) {
		try {
			Integer idSeguro = em
					.createQuery("SELECT s.idSeguroVida FROM SeguroVidaEntity AS s WHERE s.idEmpleado =:idEmpleado",
							Integer.class)
					.setParameter("idEmpleado", idEmpleado).getSingleResult();

			return idSeguro;
		} catch (NoResultException e) {
			return 0;
		}
	}

}
