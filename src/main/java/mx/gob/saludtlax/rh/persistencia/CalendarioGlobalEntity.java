/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "calendarios_globales")
public class CalendarioGlobalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6129723957469498119L;

	@Id
	@Column(name = "id_calendario_global")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCalendarioGlobal;

	@Column(name = "partida_8000")
	private Integer partida8000;

	@Column(name = "partida_1000")
	private Integer partida1000;

	@Column(name = "concepto")
	private String concepto;

	@Column(name = "importe_anual")
	private BigDecimal importeAnual;

	@Override
	public String toString() {
		return "CalendarioGlobalEntity [idCalendarioGlobal=" + idCalendarioGlobal + ", partida8000=" + partida8000
				+ ", partida1000=" + partida1000 + ", concepto=" + concepto + ", importeAnual=" + importeAnual + "]";
	}

	/*********** Getters and Setters ***************/

	public Integer getIdCalendarioGlobal() {
		return idCalendarioGlobal;
	}

	public void setIdCalendarioGlobal(Integer idCalendarioGlobal) {
		this.idCalendarioGlobal = idCalendarioGlobal;
	}

	public Integer getPartida8000() {
		return partida8000;
	}

	public void setPartida8000(Integer partida8000) {
		this.partida8000 = partida8000;
	}

	public Integer getPartida1000() {
		return partida1000;
	}

	public void setPartida1000(Integer partida1000) {
		this.partida1000 = partida1000;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getImporteAnual() {
		return importeAnual;
	}

	public void setImporteAnual(BigDecimal importeAnual) {
		this.importeAnual = importeAnual;
	}

}
