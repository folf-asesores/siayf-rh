/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 15/11/2016 16:28:25
 */
public class PadronEventualRepository extends GenericRepository<PadronEventualEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5617657707351785868L;

	public List<PadronEventualEntity> consultarEventuales() {
		return em
				.createQuery("SELECT p FROM PadronEventualEntity AS p WHERE p.conPuesto =0", PadronEventualEntity.class)
				.getResultList();
	}

	public List<PadronEventualEntity> consultarEventualesSinEmpleado() {
		return em.createQuery(
				"SELECT p FROM PadronEventualEntity AS p WHERE p.suplencia = false AND p.sinEmpleado = true AND p.tipoContratacion.id =11",
				PadronEventualEntity.class).getResultList();
	}

	public List<PadronEventualEntity> consultarSuplentes() {
		return em
				.createQuery(
						"SELECT p FROM PadronEventualEntity AS p WHERE p.conPuesto =0 AND p.tipoContratacion.id =:idTipoContratacion",
						PadronEventualEntity.class)
				.setParameter("idTipoContratacion", EnumTipoContratacion.SUPLENCIA).getResultList();
	}

	public PadronEventualEntity obtenerEventualRfcContratacion(String rfc, Integer tipoContratacion) {
		try {
			return em
					.createQuery(
							"SELECT p FROM PadronEventualEntity AS p WHERE p.rfc =:rfc AND p.tipoContratacion.id =:tipoContratacion",
							PadronEventualEntity.class)
					.setParameter("rfc", rfc).setParameter("tipoContratacion", tipoContratacion).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			return null;
		}

	}

}
