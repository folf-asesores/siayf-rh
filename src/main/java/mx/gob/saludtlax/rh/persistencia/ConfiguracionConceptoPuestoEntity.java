package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name="configuracion_concepto_puesto")
public class ConfiguracionConceptoPuestoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5390670476557355471L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_configuracion_concepto")
	private Integer id_configuracion_concepto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concepto_nomina")
	private ConceptoNominaFederalesEntity conceptoNomina;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tabulador")
	private TabuladorEntity tabulador;
	
	@Column(name="importe_concepto")
	private BigDecimal importe_concepto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto_general")
	private PuestoGeneralEntity puestoGeneral;
	
	@Column(name="formula")
	private String formula;

	public Integer getId_configuracion_concepto() {
		return id_configuracion_concepto;
	}

	public void setId_configuracion_concepto(Integer id_configuracion_concepto) {
		this.id_configuracion_concepto = id_configuracion_concepto;
	}

	public ConceptoNominaFederalesEntity getConceptoNomina() {
		return conceptoNomina;
	}

	public void setConceptoNomina(ConceptoNominaFederalesEntity conceptoNomina) {
		this.conceptoNomina = conceptoNomina;
	}

	public TabuladorEntity getTabulador() {
		return tabulador;
	}

	public void setTabulador(TabuladorEntity tabulador) {
		this.tabulador = tabulador;
	}

	public BigDecimal getImporte_concepto() {
		return importe_concepto;
	}

	public void setImporte_concepto(BigDecimal importe_concepto) {
		this.importe_concepto = importe_concepto;
	}

	public PuestoGeneralEntity getPuestoGeneral() {
		return puestoGeneral;
	}

	public void setPuestoGeneral(PuestoGeneralEntity puestoGeneral) {
		this.puestoGeneral = puestoGeneral;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}


	
}
