/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conceptos_nominas_contratos")
public class ConceptoNominaContratosEntity implements Serializable {

	private static final long serialVersionUID = 8499494595355526148L;

	@Id
	@Column(name = "id_concepto_nomina_contrato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConceptoNomina;
	@Column(name = "clave")
	private String clave;
	@Column(name = "tipo")
	private Integer tipo;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "formula")
	private String formula;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estatus_concepto_nomina")
	private EstatusConceptoNominaEntity estatusConceptoNomina;
	@Column(name = "base")
	private Boolean base;
	@Column(name = "aguinaldo")
	private Boolean aguinaldo;
	@Column(name = "retroactivo")
	private Boolean retroactivo;
	@Column(name = "tratamiento")
	private Boolean tratamiento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_sat")
	private CategoriaSatEntity categoriaSAT;
	@Column(name = "alta")
	private Date alta;
	@Column(name = "observacion")
	private String observacion;

	public Integer getIdConceptoNomina() {
		return idConceptoNomina;
	}
	public void setIdConceptoNomina(Integer idConceptoNomina) {
		this.idConceptoNomina = idConceptoNomina;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public EstatusConceptoNominaEntity getEstatusConceptoNomina() {
		return estatusConceptoNomina;
	}
	public void setEstatusConceptoNomina(EstatusConceptoNominaEntity estatusConceptoNomina) {
		this.estatusConceptoNomina = estatusConceptoNomina;
	}
	public Boolean getBase() {
		return base;
	}
	public void setBase(Boolean base) {
		this.base = base;
	}
	public Boolean getAguinaldo() {
		return aguinaldo;
	}
	public void setAguinaldo(Boolean aguinaldo) {
		this.aguinaldo = aguinaldo;
	}
	public Boolean getRetroactivo() {
		return retroactivo;
	}
	public void setRetroactivo(Boolean retroactivo) {
		this.retroactivo = retroactivo;
	}
	public Boolean getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Boolean tratamiento) {
		this.tratamiento = tratamiento;
	}
	public CategoriaSatEntity getCategoriaSAT() {
		return categoriaSAT;
	}
	public void setCategoriaSAT(CategoriaSatEntity categoriaSAT) {
		this.categoriaSAT = categoriaSAT;
	}
	public Date getAlta() {
		return alta;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}