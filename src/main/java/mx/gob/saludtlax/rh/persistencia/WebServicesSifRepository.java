package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.Configuracion;
/**
 * 
 * @author kisin-hp1 Eduardo N Castillo Caballero <eduardo.castillo.caballero@hotmail.com>
 * @version 1.0 {date}
 * @descripcion: repository  para guardar los registros que trae el consumo del web services del sif.
 * 
 */
public class WebServicesSifRepository {

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	
	
	public void guardarConceptoPartida(ConceptoPartidaNominaEntity  entity){
		entityManager.persist(entity);
	}
	
	
	public void guardarDetalleRetencion(DetalleRetencionEntity entity){
		entityManager.persist(entity);	
	}
	
	
	
	public void limpiarTablaConceptoPartidaNomina(){
		try {
			entityManager
					.createNativeQuery("TRUNCATE TABLE consulta_conceptos_partida_nomina") 
					.executeUpdate();
			

		} catch (NoResultException exception) {
		  System.out.println("ERROR :"+exception.getMessage());
		} 
	}
	
	
	public void limpiarTablaDetalleRetenciones(){
		try {
			entityManager
					.createNativeQuery("TRUNCATE TABLE consulta_detalle_retenciones") 
					.executeUpdate();
			

		} catch (NoResultException exception) {
		  System.out.println("ERROR :"+exception.getMessage());
		} 
	}
}
