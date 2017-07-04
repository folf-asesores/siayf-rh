package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

public class SubfuenteFinanciamientoDTO {
	private Integer idSubfuenteFinanciamiento;
	private FuenteFinanciamientoDTO idFuenteFinanciamiento;
	private FuenteFinanciamientoOPDDTO idFuenteFinanciamientoOPD;
	private String idBase36;
	private String descripcion;
	private Boolean nombramiento;
	private Boolean estatales;
	private Boolean federales;
	
//	<Getters & Setters>
	
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
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
	public FuenteFinanciamientoDTO getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(FuenteFinanciamientoDTO idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}
	public FuenteFinanciamientoOPDDTO getIdFuenteFinanciamientoOPD() {
		return idFuenteFinanciamientoOPD;
	}
	public void setIdFuenteFinanciamientoOPD(FuenteFinanciamientoOPDDTO idFuenteFinanciamientoOPD) {
		this.idFuenteFinanciamientoOPD = idFuenteFinanciamientoOPD;
	}
	public Boolean getNombramiento() {
		return nombramiento;
	}
	public void setNombramiento(Boolean nombramiento) {
		this.nombramiento = nombramiento;
	}
	public Boolean getEstatales() {
		return estatales;
	}
	public void setEstatales(Boolean estatales) {
		this.estatales = estatales;
	}
	public Boolean getFederales() {
		return federales;
	}
	public void setFederales(Boolean federales) {
		this.federales = federales;
	}
	
	
}
