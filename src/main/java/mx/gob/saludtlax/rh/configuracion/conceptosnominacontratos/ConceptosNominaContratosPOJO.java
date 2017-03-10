package mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos;

import java.util.Date;

/**
 * 
 * @author José Pablo
 *
 */
public class ConceptosNominaContratosPOJO {
	private Integer idConceptoNomina;
	private String clave;
	private Integer tipo;
	private String descripcion;
	private String formula;
	private String estatusConceptoNomina;
	private Integer idEstatusConceptoNomina;
	private Boolean base;
	private Boolean aguinaldo;
	private Boolean retroactivo;
	private Boolean aplicaMovimiento;
	private Boolean tratamiento;
	private String categoriaSAT;
	private Integer idCategoriaSAT;
	private Date alta;
	private Integer idTipoMovimiento;
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
	public String getEstatusConceptoNomina() {
		return estatusConceptoNomina;
	}
	public void setEstatusConceptoNomina(String estatusConceptoNomina) {
		this.estatusConceptoNomina = estatusConceptoNomina;
	}
	public Integer getIdEstatusConceptoNomina() {
		return idEstatusConceptoNomina;
	}
	public void setIdEstatusConceptoNomina(Integer idEstatusConceptoNomina) {
		this.idEstatusConceptoNomina = idEstatusConceptoNomina;
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
	public Boolean getAplicaMovimiento() {
		return aplicaMovimiento;
	}
	public void setAplicaMovimiento(Boolean aplicaMovimiento) {
		this.aplicaMovimiento = aplicaMovimiento;
	}
	public Boolean getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Boolean tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getCategoriaSAT() {
		return categoriaSAT;
	}
	public void setCategoriaSAT(String categoriaSAT) {
		this.categoriaSAT = categoriaSAT;
	}
	public Integer getIdCategoriaSAT() {
		return idCategoriaSAT;
	}
	public void setIdCategoriaSAT(Integer idCategoriaSAT) {
		this.idCategoriaSAT = idCategoriaSAT;
	}
	public Date getAlta() {
		return alta;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
