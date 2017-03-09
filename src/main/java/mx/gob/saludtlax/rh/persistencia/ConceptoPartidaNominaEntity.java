package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author kisin-hp1 Eduardo N Castillo Caballero <eduardo.castillo.caballero@hotmail.com>
 * @version 1.0 10/01/2017
 */

@Entity
@Table(name = "consulta_conceptos_partida_nomina")
public class ConceptoPartidaNominaEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1056810171697710884L;

	@Id
	@Column(name = "id_concepto_partida_nomina")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCconceptoPartidaNomina;

	@Column(name = "id_concepto_nomina")
	private Integer idConceptoNomina;
	
	@Column(name = "descripcion_concepto_nomina")
	private String descripcionConceptoNomina;
	
	
	@Column(name = "id_nombramiento")
	private Integer idNombramiento;
	
	@Column(name = "descripcion_nombramiento")
	private String descripcionNombramiento;
	
	
	@Column(name = "id_partida")
	private String idPartida;

		
	@Column(name = "descripcion_partida")
	private String descripcionPartida;


	public Integer getIdCconceptoPartidaNomina() {
		return idCconceptoPartidaNomina;
	}


	public void setIdCconceptoPartidaNomina(Integer idCconceptoPartidaNomina) {
		this.idCconceptoPartidaNomina = idCconceptoPartidaNomina;
	}


	public Integer getIdConceptoNomina() {
		return idConceptoNomina;
	}


	public void setIdConceptoNomina(Integer idConceptoNomina) {
		this.idConceptoNomina = idConceptoNomina;
	}


	public String getDescripcionConceptoNomina() {
		return descripcionConceptoNomina;
	}


	public void setDescripcionConceptoNomina(String descripcionConceptoNomina) {
		this.descripcionConceptoNomina = descripcionConceptoNomina;
	}


	public Integer getIdNombramiento() {
		return idNombramiento;
	}


	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}


	public String getDescripcionNombramiento() {
		return descripcionNombramiento;
	}


	public void setDescripcionNombramiento(String descripcionNombramiento) {
		this.descripcionNombramiento = descripcionNombramiento;
	}


	public String getIdPartida() {
		return idPartida;
	}


	public void setIdPartida(String idPpartida) {
		this.idPartida = idPpartida;
	}


	public String getDescripcionPartida() {
		return descripcionPartida;
	}


	public void setDescripcionPartida(String descripcionPartida) {
		this.descripcionPartida = descripcionPartida;
	}
	

	
	

	
}
