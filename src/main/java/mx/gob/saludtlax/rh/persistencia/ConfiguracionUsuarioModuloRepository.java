package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionUsuarioModuloRepository extends GenericRepository<ConfiguracionUsuarioModuloEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7602676446253278373L;

	public List<ConfiguracionUsuarioModuloEntity> obtenerRegistros(){
		List<ConfiguracionUsuarioModuloEntity> list = new ArrayList<>();
		
		list = em.createQuery("Select c From ConfiguracionUsuarioModuloEntity as c", ConfiguracionUsuarioModuloEntity.class).getResultList();
		return list;
	}
	
	public List<ConfiguracionUsuarioModuloEntity> obtenerRegistrosPorUsuario(Integer idUsuario){
		List<ConfiguracionUsuarioModuloEntity> list = new ArrayList<>();
		
		list = em.createQuery("Select c From ConfiguracionUsuarioModuloEntity as c where c.usuario.idUsuario=:idUsuario", ConfiguracionUsuarioModuloEntity.class)
				.setParameter("idUsuario",idUsuario).getResultList();
		return list;
	}
	
}
