package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ComprobanteEstatalRepository extends GenericRepository<ComprobanteEstatalEntity, Integer> {

	
	
	
	
	public List<ComprobanteEstatalEntity> obtenerLista() {
		List<ComprobanteEstatalEntity> comprobanteEstatalList = super.em
				.createQuery("FROM ComprobanteEstatalEntity AS c WHERE c.selloSAT = :selloSAT AND c.idComprobante = 12 ", ComprobanteEstatalEntity.class)
				.setParameter("selloSAT", "").getResultList();
		return comprobanteEstatalList;
	}
	
	
}