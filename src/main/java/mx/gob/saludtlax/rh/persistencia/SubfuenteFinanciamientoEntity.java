/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subfuentes_financiamientos")
public class SubfuenteFinanciamientoEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_subfuente_financiamiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubfuenteFinanciamiento;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fuente_financiamiento")
	private FuenteFinanciamientoEntity idFuenteFinanciamiento;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fuente_financiamiento_opd")
	private FuenteFinanciamientoOPDEntity idFuenteFinanciamientoOPD;
	@Column(name = "id_base_36")
	private String idBase36;
	@Column(name = "descripcion")
	private String descripcion;
	
//	<Getters & Setters>
	
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}
	public FuenteFinanciamientoEntity getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}
	public void setIdFuenteFinanciamiento(FuenteFinanciamientoEntity fuenteFinanciamiento) {
		this.idFuenteFinanciamiento = fuenteFinanciamiento;
	}
	
	public FuenteFinanciamientoOPDEntity getIdFuenteFinanciamientoOPD() {
		return idFuenteFinanciamientoOPD;
	}
	public void setIdFuenteFinanciamientoOPD(FuenteFinanciamientoOPDEntity fuenteFinanciamientoOPD) {
		this.idFuenteFinanciamientoOPD = fuenteFinanciamientoOPD;
	}
	public String getIdBase36() {
		return idBase36;
	}
	public void setIdBase36(String idBase36) {
		this.idBase36 = idBase36;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}