package mx.gob.saludtlax.rh.siif;

public class ProyectosTempDTO {
	
	private Integer idProyecto;
    private Integer idDependencia;
    private Integer idUnidadResponsable;
    private String idBase36;
    private String descripcion;
    
//    <<<Getters & Setters>>>
    
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}
	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}
	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
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