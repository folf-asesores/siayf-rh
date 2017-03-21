package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

public class ConfiguracionModuloAccionRepository extends GenericRepository<ConfiguracionModuloAccionEntity, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4736774820048524697L;
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	protected EntityManager em;
	
	public List<ConfiguracionModuloAccionEntity> obtenerRegistros(){
		List<ConfiguracionModuloAccionEntity> list = new ArrayList<>();
		
		list = em.createQuery("Select c From ConfiguracionModuloAccionEntity as c", ConfiguracionModuloAccionEntity.class).getResultList();
		return list;
	}
	
	public List<ConfiguracionModuloAccionEntity> obtenerRegistrosPorModulo(Integer idModulo){
		List<ConfiguracionModuloAccionEntity> list = new ArrayList<>();
		
		list = em.createQuery("Select c From ConfiguracionModuloAccionEntity as c where c.id_Modulo.id_modulo=:idModulo", ConfiguracionModuloAccionEntity.class).setParameter("idModulo",idModulo).getResultList();
		return list;
	}
	
	public List<ConfiguracionModuloAccionEntity> obtenerRegistrosPorAccion(Integer idAccion){
		List<ConfiguracionModuloAccionEntity> list = new ArrayList<>();
		
		list = em.createQuery("Select c From ConfiguracionModuloAccionEntity as c where c.id_Accion.id_accion=:idAccion", ConfiguracionModuloAccionEntity.class).setParameter("idAccion",idAccion).getResultList();
		return list;
	}
	
}
