/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.io.Serializable;


/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 18:58:29
 */
public class RiesgoPuestoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 906841269353612971L;

	private Integer idRiesgoPuesto;

	private Integer clave;

	private String descripcionRiesgoPuesto;

	

	/**
	 * @return the idRiesgoPuesto
	 */
	public Integer getIdRiesgoPuesto() {
		return idRiesgoPuesto;
	}

	/**
	 * @param idRiesgoPuesto
	 *            the idRiesgoPuesto to set
	 */
	public void setIdRiesgoPuesto(Integer idRiesgoPuesto) {
		this.idRiesgoPuesto = idRiesgoPuesto;
	}

	/**
	 * @return the clave
	 */
	public Integer getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(Integer clave) {
		this.clave = clave;
	}

	/**
	 * @return the descripcionRiesgoPuesto
	 */
	public String getDescripcionRiesgoPuesto() {
		return descripcionRiesgoPuesto;
	}

	/**
	 * @param descripcionRiesgoPuesto
	 *            the descripcionRiesgoPuesto to set
	 */
	public void setDescripcionRiesgoPuesto(String descripcionRiesgoPuesto) {
		this.descripcionRiesgoPuesto = descripcionRiesgoPuesto;
	}

}
