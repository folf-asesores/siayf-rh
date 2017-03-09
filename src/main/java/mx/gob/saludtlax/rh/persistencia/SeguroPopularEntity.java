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
 * @Since 09/12/2016 14:23:41
 */
@Entity
@Table(name = "seguro_popular_final")
public class SeguroPopularEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1996731692491805986L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "id_inventario")
	private Integer idInventario;

	@Column(name = "tipo_jornada")
	private String tipoJornada;

	@Column(name = "departamento")
	private String departamento;

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public Integer getId() {
		return id;
	}

}
