/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_nominas")
public class TipoNominaEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_tipo_nomina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoNomina;

	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "id_tipo_afectacion_nomina")
	private String idTipoAfectacionNomina;
	@Column(name = "id_clasificacion_nomina")
	private String idClasificacionNomina;
	@Column(name = "es_reposicion")
	private Boolean esReposicion;
	@Column(name = "id_fuente_financiamiento")
	private Integer idFuenteFinanciemaiento;
	@Column(name = "id_subfuente_financiamiento")
	private Integer idSubfuenteFinanciemaiento;
	@Column(name = "id_tipo_recurso")
	private Integer idTipoRecurso;
	@Column(name = "ordinaria")
	private boolean ordinaria;
	@Column(name = "retroactiva")
	private boolean retroactiva;
	@Column(name = "prima_vacacional")
	private boolean primaVacacional;
	@Column(name = "aguinaldo")
	private boolean aguinaldo;
	@Column(name = "area")
	private Integer area;

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

	public Integer getIdFuenteFinanciemaiento() {
		return idFuenteFinanciemaiento;
	}

	public void setIdFuenteFinanciemaiento(Integer idFuenteFinanciemaiento) {
		this.idFuenteFinanciemaiento = idFuenteFinanciemaiento;
	}

	public Integer getIdSubfuenteFinanciemaiento() {
		return idSubfuenteFinanciemaiento;
	}

	public void setIdSubfuenteFinanciemaiento(Integer idSubfuenteFinanciemaiento) {
		this.idSubfuenteFinanciemaiento = idSubfuenteFinanciemaiento;
	}

	public Integer getIdTipoRecurso() {
		return idTipoRecurso;
	}

	public void setIdTipoRecurso(Integer idTipoRecurso) {
		this.idTipoRecurso = idTipoRecurso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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