package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccionesRepository extends GenericRepository<AccionesEntity, Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1926101187209405659L;
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	
	public List<AccionesEntity> obtenerListaAcciones(){
		List<AccionesEntity> list = new ArrayList<>();
		
		list= entityManager.createQuery("Select a from AccionesEntity as a", AccionesEntity.class).getResultList();
		
		return list;
	}
	
	public List<AccionesEntity> obtenerListaAccionesPorIdArea(Integer idArea){
		List<AccionesEntity> list = new ArrayList<>();
		
		list= entityManager.createQuery("Select a from AccionesEntity as a where a.area.idArea=:idArea", AccionesEntity.class)
				.setParameter("idArea", idArea).getResultList();
		
		return list;
	}
	
	
	public List<AccionesEntity> obtenerListaAccionesPorIdModulo(Integer idModulo){
		List<AccionesEntity> list = new ArrayList<>();
		
		list= entityManager.createQuery("Select a from AccionesEntity as a where a.modulo.id_modulo=:idModulo", AccionesEntity.class)
				.setParameter("idModulo", idModulo).getResultList();
		
		return list;
	}
	
}
