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
 * @since 12/09/2016 10:13:43
 * 
 */
@Entity
@Table(name = "funciones")
public class FuncionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4795072933244983238L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcion")
	private Integer idFuncion;

	@Column(name = "funcion")
	private String funcion;

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

}
