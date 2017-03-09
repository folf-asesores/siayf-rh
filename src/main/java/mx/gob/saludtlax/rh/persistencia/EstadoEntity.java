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
 * @since 07/03/2016-14:14:35
 */
@Entity
@Table(name = "estados")
public class EstadoEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8562954173444985096L;

	@Id
	@Column(name = "id_estado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstado;

	@Column(name = "nombre")
	private String nombre;

	/******************* +**Getters and Setters *******************/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

}
