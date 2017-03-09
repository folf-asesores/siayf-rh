package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

public class SubfuenteFinanciamientoDTO {
	private Integer idSubfuenteFinanciamiento;
	private Integer idFuenteFinanciamiento;
	private Integer idFuenteFinanciamientoOPD;
	private String idBase36;
	private String descripcion;
	
//	<Getters & Setters>
	
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}
	public Integer getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}
	public Integer getIdFuenteFinanciamientoOPD() {
		return idFuenteFinanciamientoOPD;
	}
	public void setIdFuenteFinanciamientoOPD(Integer idFuenteFinanciamientoOPD) {
		this.idFuenteFinanciamientoOPD = idFuenteFinanciamientoOPD;
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
