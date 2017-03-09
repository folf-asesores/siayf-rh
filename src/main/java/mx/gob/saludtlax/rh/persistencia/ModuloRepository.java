package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ModuloRepository extends GenericRepository<ModuloEntity,Integer>{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(name = "siayfrhPU")
	    protected EntityManager em;
	
	public List<ModuloEntity> obtenerListaModulos(){
		
		List<ModuloEntity> list = new ArrayList<>();
		list = em.createQuery("Select m From ModuloEntity as m", ModuloEntity.class).getResultList();
		return list;
	}
	
	
	public List<ModuloEntity> obtenerModulosPorIdArea(Integer idArea){
		List<ModuloEntity> list = new ArrayList<>();
		list = em.createQuery("Select m From ModuloEntity as m where m.area.idArea=:idArea", ModuloEntity.class)
				.setParameter("idArea", idArea).getResultList();
		return list;
	}
	
}
