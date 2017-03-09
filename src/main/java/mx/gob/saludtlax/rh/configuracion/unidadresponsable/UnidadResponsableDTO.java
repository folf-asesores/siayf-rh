package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

public class UnidadResponsableDTO {
	private Integer idUnidadResponsable;
	private Integer idDependencia;
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
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
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
