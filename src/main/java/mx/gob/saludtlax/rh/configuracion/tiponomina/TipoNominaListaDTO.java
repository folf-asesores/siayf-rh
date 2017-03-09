package mx.gob.saludtlax.rh.configuracion.tiponomina;

public class TipoNominaListaDTO {
	private Integer idTipoNomina;
	private String descripcion;
	private String idTipoAfectacionNomina;
	private String idClasificacionNomina;
	private String esReposicion;
	private String idFuenteFinanciamiento;
	private String idSubfuenteFinanciamiento;
	private String idTipoRecurso;

	private boolean ordinaria;
	private boolean retroactiva;
	private boolean primaVacacional;
	private boolean aguinaldo;
	private Integer area;

	// <<<<<Getters & Setters>>>>>

	public Integer getIdTipoNomina() {
		return idTipoNomina;
	}

	public void setIdTipoNomina(Integer idTipoNomina) {
		this.idTipoNomina = idTipoNomina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdTipoAfectacionNomina() {
		return idTipoAfectacionNomina;
	}

	public void setIdTipoAfectacionNomina(String idTipoAfectacionNomina) {
		this.idTipoAfectacionNomina = idTipoAfectacionNomina;
	}

	public String getIdClasificacionNomina() {
		return idClasificacionNomina;
	}

	public void setIdClasificacionNomina(String idClasificacionNomina) {
		this.idClasificacionNomina = idClasificacionNomina;
	}

	public String getEsReposicion() {
		return esReposicion;
	}

	public void setEsReposicion(String esReposicion) {
		this.esReposicion = esReposicion;
	}

	public String getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}

	public void setIdFuenteFinanciamiento(String idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}

	public String getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}

	public void setIdSubfuenteFinanciamiento(String idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}

	public String getIdTipoRecurso() {
		return idTipoRecurso;
	}

	public void setIdTipoRecurso(String idTipoRecurso) {
		this.idTipoRecurso = idTipoRecurso;
	}

	public boolean isOrdinaria() {
		return ordinaria;
	}

	public void setOrdinaria(boolean ordinaria) {
		this.ordinaria = ordinaria;
	}

	public boolean isRetroactiva() {
		return retroactiva;
	}

	public void setRetroactiva(boolean retroactiva) {
		this.retroactiva = retroactiva;
	}

	public boolean isPrimaVacacional() {
		return primaVacacional;
	}

	public void setPrimaVacacional(boolean primaVacacional) {
		this.primaVacacional = primaVacacional;
	}

	public boolean isAguinaldo() {
		return aguinaldo;
	}

	public void setAguinaldo(boolean aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

}
