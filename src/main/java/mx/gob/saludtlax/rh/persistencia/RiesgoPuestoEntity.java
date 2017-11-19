/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 18:45:38
 */
@Entity
@Table(name = "riesgos_puestos")
public class RiesgoPuestoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5862446207985560825L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_riesgo_puesto")
	private Integer idRiesgoPuesto;

	@Column(name = "clave")
	private Integer clave;

	@Column(name = "descripcion_riesgo_puesto")
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
