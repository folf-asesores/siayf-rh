/**
 * Copyright ® 2016
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
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17/06/2016 16:07:56
 */
@Entity
@Table(name = "tipos_puestos")
public class TipoPuestoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2718139230901785394L;

	@Id
	@Column(name = "id_tipo_puesto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoPuesto;

	@Column(name = "descripcion")
	private String descripcion;

	/******************* Getters and Setters *************/

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdTipoPuesto() {
		return idTipoPuesto;
	}

	public void setIdTipoPuesto(Integer idTipoPuesto) {
		this.idTipoPuesto = idTipoPuesto;
	}

}
