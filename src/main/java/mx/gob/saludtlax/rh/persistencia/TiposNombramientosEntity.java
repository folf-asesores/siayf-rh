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

@Entity
@Table(name = "tipos_nombramientos")
public class TiposNombramientosEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_tipo_nombramiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoNombramiento;

	@Column(name = "nombramiento")
	private String nombramiento;
	@Column(name = "id_poder")
	private String idPoder;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "clave")
	private String clave;
	@Column(name = "id_subfuente_financiamiento")
	private Integer idSubfuenteFinanciamiento;

	public String getNombramiento() {
		return nombramiento;
	}
	public Integer getIdTipoNombramiento() {
		return idTipoNombramiento;
	}
	public void setIdTipoNombramiento(Integer idTipoNombramiento) {
		this.idTipoNombramiento = idTipoNombramiento;
	}
	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}
	public String getIdPoder() {
		return idPoder;
	}
	public void setIdPoder(String idPoder) {
		this.idPoder = idPoder;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}
}