/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Eduardo Mex
 *
 */
public class CalendarioGlobalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11870576361471495L;

	private Integer idCalendarioGlobal;

	private Integer partida8000;

	private Integer partida1000;

	private String concepto;

	private BigDecimal importeAnual;

	@Override
	public String toString() {
		return "CalendarioGlobalDTO [idCalendarioGlobal=" + idCalendarioGlobal + ", partida8000=" + partida8000
				+ ", partida1000=" + partida1000 + ", concepto=" + concepto + ", importeAnual=" + importeAnual + "]";
	}

	public CalendarioGlobalDTO() {
		super();
	}

	public CalendarioGlobalDTO(Integer idCalendarioGlobal, Integer partida8000, Integer partida1000, String concepto,
			BigDecimal importeAnual) {

		this.idCalendarioGlobal = idCalendarioGlobal;
		this.partida8000 = partida8000;
		this.partida1000 = partida1000;
		this.concepto = concepto;
		this.importeAnual = importeAnual;
	}

	

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
