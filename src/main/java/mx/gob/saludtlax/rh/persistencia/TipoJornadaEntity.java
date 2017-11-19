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
 * @Since 12/12/2016 12:51:34
 */
@Entity
@Table(name = "tipos_jornadas")
public class TipoJornadaEntity implements Serializable {
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_jornada")
	private Integer id;

	@Column(name = "tipo_jornada")
	private String tipoJornada;

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public Integer getId() {
		return id;
	}

}
