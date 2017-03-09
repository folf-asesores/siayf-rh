package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ConceptoNominaFederalesDTO implements Serializable {
	private static final long serialVersionUID = 1963160974733762593L;

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
	private Boolean tratamiento;
	private String nombramiento;
	private Integer idNombramiento;
	private String nivelEmpleado;
	private Integer idNivelEmpleado;
	private String categoriaSAT;
	private Integer idCategoriaSAT;
	private Date alta;
	private String observacion;
	private BigDecimal importe;
	
	private List<ConceptoNominaNombramientoDTO> conceptoNominaNombramientoLista;

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
	public String getTipoDescripcion() {
		return tipo < 2 ? "Percepción":"Deducción";
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
	public Boolean getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Boolean tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getNombramiento() {
		return nombramiento;
	}
	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}
	public Integer getIdNombramiento() {
		return idNombramiento;
	}
	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}
	public String getNivelEmpleado() {
		return nivelEmpleado;
	}
	public void setNivelEmpleado(String nivelEmpleado) {
		this.nivelEmpleado = nivelEmpleado;
	}
	public Integer getIdNivelEmpleado() {
		return idNivelEmpleado;
	}
	public void setIdNivelEmpleado(Integer idNivelEmpleado) {
		this.idNivelEmpleado = idNivelEmpleado;
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
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public List<ConceptoNominaNombramientoDTO> getConceptoNominaNombramientoLista() {
		return conceptoNominaNombramientoLista;
	}
	public void setConceptoNominaNombramientoLista(List<ConceptoNominaNombramientoDTO> conceptoNominaNombramientoLista) {
		this.conceptoNominaNombramientoLista = conceptoNominaNombramientoLista;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}