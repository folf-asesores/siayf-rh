package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="perfiles")
public class PerfilEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6278101879279395164L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_perfil")
	private Integer idPerfil;
	
	
	@Column(name="nombre")
	private String nombre;
	
	
	@Column(name="descripcion")
	private String descripcion;


	public Integer getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
