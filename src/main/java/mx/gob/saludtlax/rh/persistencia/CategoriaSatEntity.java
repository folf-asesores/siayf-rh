package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias_sat")
public class CategoriaSatEntity implements Serializable {

	private static final long serialVersionUID = 5005884829174263379L;

	@Id
	@Column(name = "id_categoria_sat")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoriaSAT;

	@Column(name = "clave")
	private String clave;

	@Column(name = "categoria_sat")
	private String categoriaSAT;

	@Column(name = "tipo")
	private Boolean tipo;

	public Integer getIdCategoriaSAT() {
		return idCategoriaSAT;
	}
	public void setIdCategoriaSAT(Integer idCategoriaSAT) {
		this.idCategoriaSAT = idCategoriaSAT;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCategoriaSAT() {
		return categoriaSAT;
	}
	public void setCategoriaSAT(String categoriaSAT) {
		this.categoriaSAT = categoriaSAT;
	}
	public Boolean getTipo() {
		return tipo;
	}
	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}
}