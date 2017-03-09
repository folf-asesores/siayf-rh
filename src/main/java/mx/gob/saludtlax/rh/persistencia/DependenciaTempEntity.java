/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 		Eduardo Mex
 * @email		Lic.Eduardo_Mex@hotmail.com
 * @version     1.0
 * @since       25/07/2016 14:11:10
 */
@Entity
@Table(name = "dependencias_temp")
public class DependenciaTempEntity {
	
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dependencia")
	private Integer idDependencia;

	@Column(name = "id_base_36")
	private String idBase36;

	@Column(name = "Descripcion")
	private String descripcion;

	@Override
	public String toString() {
		return "DependenciaEntity [Id Dependencia =" + idDependencia + ", id base 36=" + idBase36 + ", descripcion="
				+ descripcion + "]";
	}

	/**
	 * @return the idDependencia
	 */
	public Integer getIdDependencia() {
		return idDependencia;
	}

	/**
	 * @param idDependencia the idDependencia to set
	 */
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	/**
	 * @return the idBase36
	 */
	public String getIdBase36() {
		return idBase36;
	}

	/**
	 * @param idBase36 the idBase36 to set
	 */
	public void setIdBase36(String idBase36) {
		this.idBase36 = idBase36;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
