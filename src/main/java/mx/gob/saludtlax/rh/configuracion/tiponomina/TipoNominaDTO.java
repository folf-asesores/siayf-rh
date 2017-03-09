package mx.gob.saludtlax.rh.configuracion.tiponomina;

import java.io.Serializable;


public class TipoNominaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7390094848308703697L;
	
	private Integer idTipoNomina;
	private String descripcion;
	private String idTipoAfectacionNomina;
	private String idClasificacionNomina;
	private Boolean esReposicion = Boolean.FALSE;
	private Integer idFuenteFinanciamiento;
	private Integer idSubfuenteFinanciamiento;
	private Integer idTipoRecurso;

	
	private boolean ordinaria = false;
	private boolean retroactiva = false;
	private boolean primaVacacional = false;
	private boolean aguinaldo = false;
	private Integer area;
	
//	<<<<<Getters & Setters>>>>>
	
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
	public Boolean getEsReposicion() {
		return esReposicion;
	}
	public void setEsReposicion(Boolean esReposicion) {
		this.esReposicion = esReposicion;
	}
	public Integer getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}
	public Integer getIdTipoRecurso() {
		return idTipoRecurso;
	}
	public void setIdTipoRecurso(Integer idTipoRecurso) {
		this.idTipoRecurso = idTipoRecurso;
	}

	
}
