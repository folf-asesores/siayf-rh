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
@Table(name = "causas_bajas_issste")
public class CausaBajaIsssteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6712280530495002989L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_causa_baja")
	private Integer idCausaBaja;
	
	@Column(name = "causa_baja")
	private String causaBaja;

	public Integer getIdCausaBaja() {
		return idCausaBaja;
	}

	public void setIdCausaBaja(Integer idCausaBaja) {
		this.idCausaBaja = idCausaBaja;
	}

	public String getCausaBaja() {
		return causaBaja;
	}

	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}

	

}
