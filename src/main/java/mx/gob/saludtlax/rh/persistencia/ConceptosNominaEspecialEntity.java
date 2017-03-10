package mx.gob.saludtlax.rh.persistencia;

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
@Table(name="conceptos_especiales_federales")
public class ConceptosNominaEspecialEntity {

	@Id
	@Column(name = "id_concepto_especial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConceptoEspecial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concepto_nomina_federal")
	private ConceptoNominaFederalesEntity idConceptobase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concepto_compensacion")
	private ConceptoNominaFederalesEntity idConceptoCompensacion;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_concepto_isr")
	private ConceptoNominaFederalesEntity idConceptoISR;


	public Integer getIdConceptoEspecial() {
		return idConceptoEspecial;
	}


	public void setIdConceptoEspecial(Integer idConceptoEspecial) {
		this.idConceptoEspecial = idConceptoEspecial;
	}


	public ConceptoNominaFederalesEntity getIdConceptobase() {
		return idConceptobase;
	}


	public void setIdConceptobase(ConceptoNominaFederalesEntity idConceptobase) {
		this.idConceptobase = idConceptobase;
	}


	public ConceptoNominaFederalesEntity getIdConceptoCompensacion() {
		return idConceptoCompensacion;
	}


	public void setIdConceptoCompensacion(ConceptoNominaFederalesEntity idConceptoCompensacion) {
		this.idConceptoCompensacion = idConceptoCompensacion;
	}


	public ConceptoNominaFederalesEntity getIdConceptoISR() {
		return idConceptoISR;
	}


	public void setIdConceptoISR(ConceptoNominaFederalesEntity idConceptoISR) {
		this.idConceptoISR = idConceptoISR;
	}
	
	
	
}
