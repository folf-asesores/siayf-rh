/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17/06/2016 16:24:54
 */
public class TipoPuestoRepository extends GenericRepository<TipoPuestoEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3914672684276312125L;

	public List<TipoPuestoEntity> obtenerListaTipoTabulador() {
		return em.createQuery("SELECT t FROM TipoPuestoEntity AS t", TipoPuestoEntity.class).getResultList();
	}

}
