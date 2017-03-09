/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/11/2016 00:19:36
 */
public class TipoSuplenciaRepository extends GenericRepository<TipoSuplenciaEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5486544609025702493L;
	
	public List<TipoSuplenciaEntity> consultarTiposSuplencias(){
		return em.createQuery("SELECT t FROM TipoSuplenciaEntity AS t ", TipoSuplenciaEntity.class).getResultList();
	}

}
