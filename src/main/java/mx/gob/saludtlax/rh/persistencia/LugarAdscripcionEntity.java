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
 * @since 04/09/2016 21:32:48
 */
@Entity
@Table(name = "lugares_adscripciones")
public class LugarAdscripcionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8819800332556410485L;
	@Id
	@Column(name = "id_lugar_adscripcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLugarAdscripcion;

	@Column(name = "lugar_adscripcion")
	private String lugarAdscripcion;

	public String getLugarAdscripcion() {
		return lugarAdscripcion;
	}

	public void setLugarAdscripcion(String lugarAdscripcion) {
		this.lugarAdscripcion = lugarAdscripcion;
	}

	public Integer getIdLugarAdscripcion() {
		return idLugarAdscripcion;
	}

}
