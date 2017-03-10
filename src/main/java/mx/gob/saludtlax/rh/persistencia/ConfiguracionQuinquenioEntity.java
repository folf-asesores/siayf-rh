package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="configuracion_quinquenios")
public class ConfiguracionQuinquenioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6264552387437312340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_configuracion_quinquenio")
	private Integer id_configuracion_quinquenio;
	
	@Column(name="id_empleado")
	private Integer id_empleado;
	
	@Column(name="rfc")
	private String rfc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_concepto_nomina")
	private ConceptoNominaFederalesEntity id_concepto_nomina;
	
	@Column(name="fecha_alta")
	private Date fecha_alta;
	
	@Column(name="fecha_actualizacion")
	private Date fecha_actualizacion;
	
	@Column(name="estatus")
	private Boolean estatus;
	
	@Column(name="id_nombramiento")
	private Integer idnombramiento;
	
	@Column(name="id_configuracion_presupuestal")
	private Integer idConfiguracionPresupestal;

	public Integer getId_configuracion_quinquenio() {
		return id_configuracion_quinquenio;
	}

	public void setId_configuracion_quinquenio(Integer id_configuracion_quinquenio) {
		this.id_configuracion_quinquenio = id_configuracion_quinquenio;
	}

	public Integer getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(Integer id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public ConceptoNominaFederalesEntity getId_concepto_nomina() {
		return id_concepto_nomina;
	}

	public void setId_concepto_nomina(ConceptoNominaFederalesEntity id_concepto_nomina) {
		this.id_concepto_nomina = id_concepto_nomina;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Integer getIdnombramiento() {
		return idnombramiento;
	}

	public void setIdnombramiento(Integer idnombramiento) {
		this.idnombramiento = idnombramiento;
	}

	public Integer getIdConfiguracionPresupestal() {
		return idConfiguracionPresupestal;
	}

	public void setIdConfiguracionPresupestal(Integer idConfiguracionPresupestal) {
		this.idConfiguracionPresupestal = idConfiguracionPresupestal;
	}
	
	
}
