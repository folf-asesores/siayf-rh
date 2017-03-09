package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccionesRepository extends GenericRepository<AccionesEntity, Integer>{
	
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
	
}
