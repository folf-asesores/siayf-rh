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
 * @Since 24/10/2016 11:09:01
 */
@Entity
@Table(name = "tipos_postulacion")
public class TipoPostulacionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7070869966878542452L;

	@Id
	@Column(name = "id_tipo_postulacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoPostulacion;

	@Column(name = "tipo_postulacion")
	private String tipoPostulacion;

	public String getTipoPostulacion() {
		return tipoPostulacion;
	}

	public void setTipoPostulacion(String tipoPostulacion) {
		this.tipoPostulacion = tipoPostulacion;
	}

	public void setIdTipoPostulacion(Integer idTipoPostulacion) {
		this.idTipoPostulacion = idTipoPostulacion;
	}

}
