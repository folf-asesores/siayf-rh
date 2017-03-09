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
 * @since 16/06/2016 14:15:47
 */
public class NivelEmpleadoRepository extends GenericRepository<NivelEmpleadoEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1510529362213993951L;

	public List<NivelEmpleadoEntity> obtenerListaNivelEmpleado() {
		return em.createQuery("SELECT n FROM NivelEmpleadoEntity AS n", NivelEmpleadoEntity.class).getResultList();
	}

}
