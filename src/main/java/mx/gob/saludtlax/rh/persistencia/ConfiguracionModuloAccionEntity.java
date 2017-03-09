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
@Table(name="configuracion_modulo_accion")	
public class ConfiguracionModuloAccionEntity implements Serializable{

	/**
	 * @author Edgar RZM
	 */
	private static final long serialVersionUID = -8103539765440484242L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_configuracion_modulo_accion")
	private Integer id_configuracion_modulo_accion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_modulo")
	private ModuloEntity id_Modulo;
	
	@Column(name="nombre_configuracion")
	private String descripcion;
	


	public Integer getId_configuracion_modulo_accion() {
		return id_configuracion_modulo_accion;
	}

	public void setId_configuracion_modulo_accion(Integer id_configuracion_modulo_accion) {
		this.id_configuracion_modulo_accion = id_configuracion_modulo_accion;
	}

	public ModuloEntity getId_Modulo() {
		return id_Modulo;
	}

	public void setId_Modulo(ModuloEntity id_Modulo) {
		this.id_Modulo = id_Modulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
	
}
