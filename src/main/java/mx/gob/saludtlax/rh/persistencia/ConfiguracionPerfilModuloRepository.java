package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ConfiguracionPerfilModuloRepository extends GenericRepository<ConfiguracionPerfilModuloEntity, Integer>{

	 @PersistenceContext(name = "siayfrhPU")
	    protected EntityManager em;
	 	
	 
	 public void eliminarPorIdPerfil(Integer idPerfil){
		 List<ConfiguracionPerfilModuloEntity> list = em.createQuery("Select c from ConfiguracionPerfilModuloEntity as c where c.perfil.idPerfil=:idPerfil", ConfiguracionPerfilModuloEntity.class)
				 .setParameter("idPerfil", idPerfil).getResultList();

				 for(ConfiguracionPerfilModuloEntity cE:list){
					 em.remove(cE);
				 }
	 
	 }
	 
	 public List<ConfiguracionPerfilModuloEntity> obtenerListaPorIdPerfil(Integer idPerfil){
		
		List<ConfiguracionPerfilModuloEntity> list = new ArrayList<>();
		
		list=em.createQuery("Select c From ConfiguracionPerfilModuloEntity as c where c.perfil.idPerfil=:idPerfil", ConfiguracionPerfilModuloEntity.class)
				.setParameter("idPerfil", idPerfil)
				.getResultList();
		
		return list;
		}
		
		public List<ConfiguracionPerfilModuloEntity> obtenerLista(){
			
			List<ConfiguracionPerfilModuloEntity> list = new ArrayList<>();
			
			list=em.createQuery("Select c From ConfiguracionPerfilModuloEntity", ConfiguracionPerfilModuloEntity.class)
					.getResultList();
			
			return list;
	}
	
}
