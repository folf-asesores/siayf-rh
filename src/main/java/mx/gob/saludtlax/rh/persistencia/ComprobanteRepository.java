package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

public class ComprobanteRepository extends GenericRepository<ComprobanteEntity, Integer> {

	public List<ComprobanteEntity> obtenerLista() {
		List<ComprobanteEntity> comprobanteEstatalList = super.em
				.createQuery("FROM ComprobanteEntity AS c WHERE c.uUID = :uuid", ComprobanteEntity.class)
				.setParameter("uuid", "").getResultList();
		
		/*List<ComprobanteEntity> comprobanteEstatalList = super.em
				.createQuery("FROM ComprobanteEntity AS c WHERE c.idComprobante = 168603", ComprobanteEntity.class)
				.getResultList();*/
		
		return comprobanteEstatalList;
	}

	public List<ComprobanteEntity> obtenerListaSinUIID() {
		List<ComprobanteEntity> comprobanteEstatalList = super.em
				.createQuery("FROM ComprobanteEntity AS c WHERE c.uUID = :uuid ", ComprobanteEntity.class)
				.setParameter("uuid", "").getResultList();
		return comprobanteEstatalList;
	}

	public ComprobanteEntity obtenerUUID(String uiid) {
		try {
			ComprobanteEntity comprobanteEstatal = super.em
					.createQuery("FROM ComprobanteEntity AS c WHERE c.uUID = :uuid", ComprobanteEntity.class)
					.setParameter("uuid", uiid).getSingleResult();
			return comprobanteEstatal;
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Long totalNominasPorTimbrar() {
		Long totalNomina = super.em
				.createQuery("SELECT COUNT(*) FROM ComprobanteEntity AS c WHERE c.uUID = :uuid", Long.class)
				.setParameter("uuid", "").getSingleResult();

		return totalNomina;
	}

	public boolean buscarPorUUID(String uuid) {

		try {
			ComprobanteEntity comprobante = super.em
					.createQuery("FROM ComprobanteEntity AS c WHERE c.uUID =:uuid ", ComprobanteEntity.class)
					.setParameter("uuid", uuid).getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}

	}

	public ComprobanteEntity buscarComprobanteTimbrado(String rfc, Date fecha_pago, BigDecimal total, Integer id) {

		try {
			ComprobanteEntity comprobante = super.em
					.createQuery(
							"FROM ComprobanteEntity AS c WHERE c.uUID ='' AND c.rFC=:rfc AND c.fechaPago=:fechaPago AND c.total=:total and c.idComprobante=:id   ",
							ComprobanteEntity.class)
					.setParameter("rfc", rfc).setParameter("fechaPago", fecha_pago).setParameter("total", total)
					.setParameter("id", id).getSingleResult();
			return comprobante;

		} catch (NoResultException ex) {
			return null;
		}

	}

}
