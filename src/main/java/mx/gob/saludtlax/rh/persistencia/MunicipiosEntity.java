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
 * @since 07/03/2016-14:18:16
 */
@Entity
@Table(name = "municipios")
public class MunicipiosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5481812985416881508L;
	@Id
	@Column(name = "id_municipio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMunicipio;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "id_estado")
	private Integer idEstado;

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

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
