package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class DetalleConfiguracionModuloAccionRepository   extends GenericRepository<DetalleConfiguracionModuloAccionEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3929617400912039730L;

	
	public List<DetalleConfiguracionModuloAccionEntity> obtenerDetallesPorIdConfiguracion(Integer idConfiguracion){
		List<DetalleConfiguracionModuloAccionEntity> list = new ArrayList<>();
		list = em.createQuery("Select c From DetalleConfiguracionModuloAccionEntity as c where c.idConfiguracionModuloAccion=:idConfiguracion", DetalleConfiguracionModuloAccionEntity.class)
				.setParameter("idConfiguracion",idConfiguracion)
				.getResultList();
		return list;
	}
	
}
