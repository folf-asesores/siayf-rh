package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dependencias_demo")
public class DependenciasEntity {

	
	@Id
	@Column(name = "id_dependencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_tipo_dependencia")
	private Integer id_tipo_dependencia;
	
	@Column(name="descripcion")
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_tipo_dependencia() {
		return id_tipo_dependencia;
	}

	public void setId_tipo_dependencia(Integer id_tipo_dependencia) {
		this.id_tipo_dependencia = id_tipo_dependencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
