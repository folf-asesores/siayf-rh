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
@Table(name="modulos")
public class ModuloEntity implements Serializable{

	
	/**
	 * @author Edgar RZM
	 * 
	 */
	private static final long serialVersionUID = -960950112475455728L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_modulo")
	private Integer id_modulo;
	
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "habilitado")
	private Boolean habilitado;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "id_area")
	private AreaEntity  area;

	public Integer getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(Integer id_modulo) {
		this.id_modulo = id_modulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}
	
	
	
}
