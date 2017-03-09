package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

public class FuenteFinanciamientoDTO {
	private Integer idFuenteFinanciamiento;
	private String idBase36;
	private String descripcion;
	
//	<Getters & Setters>
	
	public Integer getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
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
