package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.persistencia.EstructuraContratoTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFDeudoresDiversosEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFDispersionChequesEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFSeguroPopularEntity;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoExcelEntity;

public class EstructuraContratosTrailersQuery implements Serializable {

	private static final long serialVersionUID = 1135974749754039111L;
	
	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	public String registroEstructuraExcel(EstructuraContratoExcelEntity estructura) {
		entityManager.persist(estructura);
		return estructura.getIdEstructura();
	}	
	
	public String registroEstructura(EstructuraContratoTrailersEntity estructura) {
		entityManager.persist(estructura);
		return estructura.getNumEmp();
	}	
	
	public String registroEstructura(EstructuraContratoEntity estructura) {
		entityManager.persist(estructura);
		return estructura.getNumEmp();
	}	
	
	public String registroSeguroPopular(SIIFSeguroPopularEntity entity) {
		entityManager.persist(entity);
		return entity.getRfc();
	}
	
	public String registroDeudoresDiversos(SIIFDeudoresDiversosEntity entity) {
		entityManager.persist(entity);
		return entity.getRfc();
	}
	
	public String registroDispersionCheques(SIIFDispersionChequesEntity entity) {
		entityManager.persist(entity);
		return entity.getRfc();
	}
	
}