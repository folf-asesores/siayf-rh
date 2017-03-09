/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;

public class ConceptoNominaNombramientoDTO implements Serializable {
	private static final long serialVersionUID = 5255824447773534623L;

	private Integer idConceptoNominaNombramiento;
	private Integer idConceptoNomina;
	private Integer idTipoNombramiento;
	private Boolean aplica;

	public Integer getIdConceptoNominaNombramiento() {
		return idConceptoNominaNombramiento;
	}
	public void setIdConceptoNominaNombramiento(Integer idConceptoNominaNombramiento) {
		this.idConceptoNominaNombramiento = idConceptoNominaNombramiento;
	}
	public Integer getIdConceptoNomina() {
		return idConceptoNomina;
	}
	public void setIdConceptoNomina(Integer idConceptoNomina) {
		this.idConceptoNomina = idConceptoNomina;
	}
	public Integer getIdTipoNombramiento() {
		return idTipoNombramiento;
	}
	public void setIdTipoNombramiento(Integer idTipoNombramiento) {
		this.idTipoNombramiento = idTipoNombramiento;
	}
	public Boolean getAplica() {
		return aplica;
	}
	public void setAplica(Boolean aplica) {
		this.aplica = aplica;
	}
}