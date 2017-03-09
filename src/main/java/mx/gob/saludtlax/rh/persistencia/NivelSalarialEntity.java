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
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "niveles_salariales")
public class NivelSalarialEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555898655086119963L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nivel_salarial")
	private Integer idNivelSalarial;
	
	@Column(name = "nivel_salarial")
	private String nivelSalarial;

	public Integer getIdNivelSalarial() {
		return idNivelSalarial;
	}

	public void setIdNivelSalarial(Integer idNivelSalarial) {
		this.idNivelSalarial = idNivelSalarial;
	}

	public String getNivelSalarial() {
		return nivelSalarial;
	}

	public void setNivelSalarial(String nivelSalarial) {
		this.nivelSalarial = nivelSalarial;
	}
	
	

}
