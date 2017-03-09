/**
 * 
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
 * 
 * @author  Eduardo Mex
 * @email   lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since   13/06/2016 13:55:03
 */
@Entity
@Table(name = "ramas_puestos")
public class RamaPuestoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_rama_puesto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRamaPuesto;

	@Column(name = "nombre_rama_puesto")
	private String nombreRamaPuesto;

	/**************** Geters and Setters ********************/

	/**
	 * @return the nombreRamaPuesto
	 */
	public String getNombreRamaPuesto() {
		return nombreRamaPuesto;
	}

	/**
	 * @param nombreRamaPuesto
	 *            the nombreRamaPuesto to set
	 */
	public void setNombreRamaPuesto(String nombreRamaPuesto) {
		this.nombreRamaPuesto = nombreRamaPuesto;
	}

	/**
	 * @return the idRamaPuesto
	 */
	public Integer getIdRamaPuesto() {
		return idRamaPuesto;
	}

	/**
	 * @param idRamaPuesto
	 *            the idRamaPuesto to set
	 */
	public void setIdRamaPuesto(Integer idRamaPuesto) {
		this.idRamaPuesto = idRamaPuesto;
	}

}
