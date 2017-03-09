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
 * @since 11/09/2016 13:58:34
 * 
 */
@Entity
@Table(name = "servicios")
public class ServicioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1640705088369503664L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private Integer idServicio;

	@Column(name = "servicio")
	private String servicio;

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

}
