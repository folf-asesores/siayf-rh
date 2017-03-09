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
 * @since 02/09/2016 14:45:10
 * 
 */
@Entity
@Table(name = "tipos_duracion_jornadas")
public class TiposDuracionJornadaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666051976259876610L;
	@Id
	@Column(name = "id_tipo_jornada")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoJornada;

	@Column(name = "jornada")
	private String jornada;

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public Integer getIdTipoJornada() {
		return idTipoJornada;
	}

}
