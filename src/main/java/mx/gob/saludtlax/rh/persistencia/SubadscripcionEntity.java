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
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 21:12:08
 */
@Entity
@Table(name = "subadscripciones")
public class SubadscripcionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3662282481005981762L;

	@Id
	@Column(name = "id_subadscripcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubadscripcion;

	@Column(name = "subadscripcion")
	private String subadscripcion;

	public String getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(String subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public Integer getIdSubadscripcion() {
		return idSubadscripcion;
	}

}
