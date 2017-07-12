package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;

public class UnidadResponsableDTO {
	private Integer idUnidadResponsable;
	private DependenciaDTO dependencia;
	private Integer idUnidadXDependencia;
	private String idBase36;
	private String descripcion;
	
//	<Getters & Setters>
	
	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}
	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
	}
	
	
	
	public DependenciaDTO getDependencia() {
		return dependencia;
	}
	public void setDependencia(DependenciaDTO dependencia) {
		this.dependencia = dependencia;
	}
	public Integer getIdUnidadXDependencia() {
		return idUnidadXDependencia;
	}
	public void setIdUnidadXDependencia(Integer idUnidadXDependencia) {
		this.idUnidadXDependencia = idUnidadXDependencia;
	}
	public String getIdBase36() {
		return idBase36;
	}
	public void setIdBase36(String idBase36) {
		this.idBase36 = idBase36;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
