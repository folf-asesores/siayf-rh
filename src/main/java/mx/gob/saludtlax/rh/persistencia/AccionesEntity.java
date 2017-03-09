package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="acciones")
public class AccionesEntity implements Serializable{

	
	/**
	 * @author Edgar RZM
	 */
	private static final long serialVersionUID = -8251187276207600193L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_accion")
	private Integer id_accion;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "id_area")
	private AreaEntity area;



	public Integer getId_accion() {
		return id_accion;
	}

	public void setId_accion(Integer id_accion) {
		this.id_accion = id_accion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}
	
	
}
