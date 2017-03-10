package mx.gob.saludtlax.rh.quinquenios;

import java.io.Serializable;
import java.util.Date;

public class ConfiguracionQuinquenioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3182948250457751859L;
	private Integer id_configuracion_quinquenio;
	private Integer id_empleado;
	private String rfc;
	private String nombreEmpleado;
	private String clave_concepto;
	private Date fecha_alta;
	private Date fecha_actualizacion;
	private Boolean estatus;
	private Integer idnombramiento;
	private String nombramiento;
	private Integer idConfiguracionP;
	
	public ConfiguracionQuinquenioDTO(){
		super();
	}
	
	
	
	
	public ConfiguracionQuinquenioDTO(Integer id_configuracion_quinquenio, Integer id_empleado, String rfc,
			String clave_concepto, Date fecha_alta, Date fecha_actualizacion, Boolean estatus,
			Integer idnombramiento,Integer idconfiguracionPresupuestal) {
		super();
		this.id_configuracion_quinquenio = id_configuracion_quinquenio;
		this.id_empleado = id_empleado;
		this.rfc = rfc;
		this.clave_concepto = clave_concepto;
		this.fecha_alta = fecha_alta;
		this.fecha_actualizacion = fecha_actualizacion;
		this.estatus = estatus;
		this.idnombramiento = idnombramiento;
		
		this.idConfiguracionP=idconfiguracionPresupuestal;
	}




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
	public String getClave_concepto() {
		return clave_concepto;
	}
	public void setClave_concepto(String clave_concepto) {
		this.clave_concepto = clave_concepto;
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
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Integer getIdnombramiento() {
		return idnombramiento;
	}
	public void setIdnombramiento(Integer idnombramiento) {
		this.idnombramiento = idnombramiento;
	}




	public Integer getIdConfiguracionP() {
		return idConfiguracionP;
	}




	public void setIdConfiguracionP(Integer idConfiguracionP) {
		this.idConfiguracionP = idConfiguracionP;
	}




	public String getNombramiento() {
		return nombramiento;
	}




	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}
	
	
	
}
