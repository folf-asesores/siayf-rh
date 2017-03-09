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
 * @since       25/07/2016 14:21:32
 */
@Entity
@Table(name = "unidades_responsables_temp")
public class UnidadResponsableTempEntity {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidad_responsable")
	private Integer idUnidadResponsable;

	@Column(name = "id_dependencia")
	private Integer idDependencia;

	@Column(name = "id_unidad_x_dependencia")
	private Integer idUnidadXDependencia;

	@Column(name = "id_base_36")
	private String idBase36;

	@Column(name = "descripcion")
	private String descripcion;

	@Override
	public String toString() {
		return "UnidadResponsableEntity [id unidad resposable=" + idUnidadResponsable + ", id dependencia="
				+ idDependencia + ", id unidad x dependencia=" + idUnidadXDependencia + ", id base 36=" + idBase36
				+ ", descripcion=" + descripcion + "]";
	}

	/**
	 * @return the idUnidadResponsable
	 */
	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}

	/**
	 * @param idUnidadResponsable the idUnidadResponsable to set
	 */
	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
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
	 * @return the idUnidadXDependencia
	 */
	public Integer getIdUnidadXDependencia() {
		return idUnidadXDependencia;
	}

	/**
	 * @param idUnidadXDependencia the idUnidadXDependencia to set
	 */
	public void setIdUnidadXDependencia(Integer idUnidadXDependencia) {
		this.idUnidadXDependencia = idUnidadXDependencia;
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
