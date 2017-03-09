package mx.gob.saludtlax.rh.persistencia;

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
@Table(name="detalle_configuracion_modulo_accion")
public class DetalleConfiguracionModuloAccionEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_detalle_configuracion_modulo_accion")
	private Integer id_detalle_configuracion_modulo_accion;
	
	@Column(name="id_configuracion_modulo_accion")
	private Integer idConfiguracionModuloAccion;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_accion")
	private AccionesEntity id_accion;


	public Integer getId_detalle_configuracion_modulo_accion() {
		return id_detalle_configuracion_modulo_accion;
	}


	public void setId_detalle_configuracion_modulo_accion(Integer id_detalle_configuracion_modulo_accion) {
		this.id_detalle_configuracion_modulo_accion = id_detalle_configuracion_modulo_accion;
	}


	public Integer getIdConfiguracionModuloAccion() {
		return idConfiguracionModuloAccion;
	}


	public void setIdConfiguracionModuloAccion(Integer idConfiguracionModuloAccion) {
		this.idConfiguracionModuloAccion = idConfiguracionModuloAccion;
	}


	public AccionesEntity getId_accion() {
		return id_accion;
	}


	public void setId_accion(AccionesEntity id_accion) {
		this.id_accion = id_accion;
	}
	
	
	
	
}
